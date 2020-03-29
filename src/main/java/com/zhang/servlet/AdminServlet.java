package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.dao.Page;
import com.zhang.dao.PageOther;
import com.zhang.domain.*;
import com.zhang.exception.UserException;
import com.zhang.service.AdminHotelService;
import com.zhang.service.AdminService;
import com.zhang.service.UserService;
import com.zhang.utils.DateUtils;
import com.zhang.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author prayers
 * @date 2020/1/9 21:17
 */
@WebServlet(name = "AdminServlet", urlPatterns="/AdminServlet")
public class AdminServlet extends HttpServlet {

    public static final String REGIST = "register";
    public static final String VALIDATE_NAME = "validate";
    public static final String LOGIN = "login";
    public static final String GET_USER_INF = "getUserInf";
    public static final String CHANGE_PASSWORD = "changePassword";
    public static final String CHANGE_PT = "changePt";
    public static final String CHANGE_NC = "changeNc";
    public static final String CHANGE_MY_INF = "changeMyInf";
    public static final String CHARTS_COUNT_ORDER_AT = "chartsCountOrderAt";
    public static final String MONTH_COUNT_ORDER_AT = "monthCountOrderAt";
    public static final String WEEK_COUNT_ORDER_AT = "weekCountOrderAt";
    public static final String DAY_COUNT_ORDER_AT = "dayCountOrderAt";
    public static final String DAY_COUNT_MONEY_AT = "dayCountMoneyAt";
    public static final String WEEK_COUNT_MONEY_AT = "weekCountMoneyAt";
    public static final String LAST_COUNT_MONEY_AT = "lastCountMoneyAt";
    public static final String GET_ALL_COMMENT = "getAllComment";
    public static final String GET_ONE_COMMENT = "getOneComment";
    public static final String DEL_COMMENT_MORE = "delCommentMore";
    public static final String DELETE_COMMENT = "deleteComment";
    public static final String ECHARTS_COUNT_ORDER_AT = "echartsCountOrderAt";


    private AdminService adminService = new AdminService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        /**
         * 1.处理中文乱码问题
         * 2.根据不同的action进行处理不同的请求
         */
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取action的值
        String action = request.getParameter("action");

        if(REGIST.equals(action)){
            regist(request,response);
        } else if(VALIDATE_NAME.equals(action)){
            validateName(request, response);
        } else if(LOGIN.equals(action)){
            login(request, response);
        } else if(CHANGE_PASSWORD.equals(action)){
            changePassword(request, response);
        } else if(CHANGE_NC.equals(action)){
            changeNc(request, response);
        } else if(CHANGE_PT.equals(action)){
            changePt(request, response);
        } else if(ECHARTS_COUNT_ORDER_AT.equals(action)){
            echartsCountOrderAt(request, response);
        } else if(DAY_COUNT_ORDER_AT.equals(action)){
            dayCountOrderAt(request, response);
        } else if(WEEK_COUNT_ORDER_AT.equals(action)){
            weekCountOrderAt(request, response);
        } else if(MONTH_COUNT_ORDER_AT.equals(action)){
            monthCountOrderAt(request, response);
        } else if(DAY_COUNT_MONEY_AT.equals(action)){
            dayCountMoneyAt(request, response);
        } else if(LAST_COUNT_MONEY_AT.equals(action)){
            lastCountMoneyAt(request, response);
        } else if(WEEK_COUNT_MONEY_AT.equals(action)){
            weekCountMoneyAt(request, response);
        } else if(GET_ALL_COMMENT.equals(action)){
            getAllComment(request,response);
        } else if(GET_ONE_COMMENT.equals(action)){
            getOneComment(request,response);
        } else if(DELETE_COMMENT.equals(action)){
            deleteComment(request,response);
        } else if(DEL_COMMENT_MORE.equals(action)){
            delCommentMore(request,response);
        }
    }

    /**注册*/
    private void regist(HttpServletRequest request,HttpServletResponse response)
            throws IOException {
        /*
         * 1.获取前台用户输入的数据
         * 2.对数据进行验证
         * 3.把数据封装到User中，提交给Service层进行添加
         * 4.提示添加成功，跳转到登录页面（防止重复刷新问题）
         */
        String name = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        System.out.println(name+email+password);

        if(name==null||name.trim().isEmpty()){
            request.setAttribute("error", "用户名不可为空");
            System.out.println("用户名为空");
            response.sendRedirect(request.getContextPath()+"/User/register.html");
            return;
        }else if(password==null||password.trim().isEmpty()){
            request.setAttribute("error", "密码不可为空");
            response.sendRedirect(request.getContextPath()+"/User/register.html");
            return;
        }
        //验证邮箱的正则表达式
        String regex="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p=Pattern.compile(regex);
        Matcher m=p.matcher(email);
        if(!m.find()){
            request.setAttribute("error", "邮箱格式不正确");
            response.sendRedirect(request.getContextPath()+"/User/register.html");
            return;
        }

        password = MD5.md5(password);
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        userService.regist(user);
        request.setAttribute("msg","<script>alert('注册成功')</script>");
        response.sendRedirect(request.getContextPath()+"/User/login.html");
    }
    /**验证账号唯一性**/
    private void validateName(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String name = request.getParameter("userName");
        boolean result = userService.validateName(name);
        response.getWriter().print(result);
    }
    /**登录*/
    private void login(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        /**
         * 1.获取用户名和密码
         * 2.对数据进行验证
         * 3.调用Service层进行用户的查找
         * 4.匹配成功则跳转到index.html页面
         */
        String username = request.getParameter("username");
        String password = request.getParameter("password");

       // System.out.println(username+password);
        password = MD5.md5(password);
        try {
            Admin admin = adminService.login(username,password);
                adminService.updateLastLoginTime(admin);
                //把用户信息保存到session中
                HttpSession session = request.getSession();
                System.out.println("最终值为"+admin);
                request.getSession().setAttribute("admin",admin);
                String json= JSON.toJSONString(admin);
                response.getWriter().print(json);

        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            response.getWriter().print(1);
            //两个页面合到一起jsp:.forwrad
            //response.sendRedirect(request.getContextPath()+"/Admin/login.html");
        }
    }
    /**一天订单总数**/
    private void dayCountOrderAt(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        int result= adminService.dayCountOrderAt();
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**一周订单总数**/
    private void weekCountOrderAt(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
        int result= adminService.weekCountOrderAt( );
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**一个月订单总数**/
    private void monthCountOrderAt(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        int result= adminService.monthCountOrderAt( );
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**查询今日销售总额**/
    private void dayCountMoneyAt(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        List<Map<String, Object>> result= adminService.dayCountMoneyAt( );
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**查询昨天销售总额**/
    private void lastCountMoneyAt(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        List<Map<String, Object>> result= adminService.lastCountMoneyAt( );
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**近七天销售总额**/
    private void weekCountMoneyAt(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        List<Map<String, Object>> result= adminService.weekCountMoneyAt( );
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**echarts**/
    private void echartsCountOrderAt(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        String number = request.getParameter("days");
        System.out.println("天数"+number);
        int days = 7;
        try{
            days = Integer.parseInt(number);
        }catch(Exception e){
            days = 7;
        }
        LinkedHashMap<String, Integer> result= adminService.echartsCountOrderAt(days);
       // System.out.println(result);
        String json= JSON.toJSONString(result);
      //  System.out.println("json"+json);
        response.getWriter().print(json);

    }
    /**获取景点评论信息**/
    private void getAllComment(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String skey = request.getParameter("sKey");
        String svalue=request.getParameter("sValue");
        String current = request.getParameter("currentPage");
        System.out.println("关键字"+skey+"关键字"+svalue+"跳转的页数"+current);
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        Page page = adminService.getAllComment(currentPage,skey,svalue);
        String json= JSON.toJSONString(page);
        response.getWriter().print(json);
    }
 
 
    /**查看一条评论**/
    private void getOneComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String comment_id = request.getParameter("comment_id");
        Map<String,Object> result= adminService.getOneComment(Integer.parseInt(comment_id));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    private void deleteComment(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        /**1.获取id
         * 2.调用Service进行删除
         * 3.获取商品列表，直接调用findAll方法
         */
        //1.获取id
        String comment_id = request.getParameter("comment_id");
        System.out.println("删除的id为："+comment_id);
        Boolean result = adminService.delete(Integer.parseInt(comment_id));
        //返回添加成功的信息
        response.getWriter().print(result);
        //findAllAttractions(request,response);

    }
    private void delCommentMore(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //获取前台的ids
        String[] ids = request.getParameter("ids").split(",");
        Boolean result = adminService.delMore(ids);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
        // findAllAttractions(request,response);

    }


    /**修改密码*/
    private void changePassword(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        String admin_id= request.getParameter("admin_id");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

         System.out.println("获得的内容"+admin_id+" "+oldPassword+" "+newPassword);
        oldPassword = MD5.md5(oldPassword);

        boolean result = adminService.validatePw(admin_id,oldPassword);

        if (result == true){
            Admin m = new Admin();
            newPassword = MD5.md5(newPassword);
            System.out.println(newPassword);
            m.setAdmin_id(Integer.parseInt(admin_id));
            m.setAdmin_password(newPassword);
            adminService.changePw(m);
            response.getWriter().print(0);
        }else {
            response.getWriter().print(1);
        }
    }
    /**修改头像**/
    private void changePt(HttpServletRequest request,
                          HttpServletResponse response) {
    }
    /**修改昵称**/
    private void changeNc(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        String customerName = request.getParameter("customerName");

        User user = new User();
        user.setUid(Integer.parseInt(uid));
        user.setName(customerName);
        Boolean result = userService.changeNc(user);
        //返回添加成功的信息
        response.getWriter().print(result);
    }

    private void validate(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String name = request.getParameter("hotel_name");
        System.out.println("获得的内容"+name);
        // boolean result = adminHotelService.validateName(name);
        // response.getWriter().print(result);
    }


}
