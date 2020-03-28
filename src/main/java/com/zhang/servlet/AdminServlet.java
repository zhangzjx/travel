package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static final String FIND_SEARCH = "findSearch";



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
        }  else if(GET_USER_INF.equals(action)){
            getUserInf(request, response);
        } else if(CHANGE_MY_INF.equals(action)){
            changeMyInf(request, response);
        } else if(CHANGE_PASSWORD.equals(action)){
            changePassword(request, response);
        } else if(CHANGE_NC.equals(action)){
            changeNc(request, response);
        } else if(CHANGE_PT.equals(action)){
            changePt(request, response);
        }
    }

    /**注册*/
    private void regist(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
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
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
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


    /**获得个人信息**/
    private void getUserInf(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String uId = request.getParameter("uid");
        //List<Map<String,Object>> result= userService.getUserInf(Integer.parseInt(uId));
        Map<String,Object> result= userService.getUserInf(Integer.parseInt(uId));
        //System.out.println(result);
        String json= JSON.toJSONString(result);
        //System.out.println("json"+json);
        response.getWriter().print(json);

    }
    /**修改个人信息**/
    private void changeMyInf(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String uid = request.getParameter("uid");
        String customerName = request.getParameter("customerName");
        String sex = request.getParameter("sex");
        String phone = request.getParameter("phone");
        String email= request.getParameter("email");

        //System.out.println("获得的内容"+uid+" "+customerName+" "+sex+" "+phone+" "+email);
        System.out.println("获得的内容"+phone+"结尾");
        User user=new User();
        user.setUid(Integer.parseInt(uid));
        user.setName(customerName);
        user.setSex(sex);
        user.setPhone(phone);
        user.setEmail(email);

        Boolean result = userService.changeMyInf(user);
        //返回添加成功的信息
        response.getWriter().print(result);
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
        //response.sendRedirect(request.getContextPath()+"/User/centerSettingAddress.jsp");

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
