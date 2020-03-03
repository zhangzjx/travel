package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.domain.Attractions;
import com.zhang.domain.Hotel;
import com.zhang.domain.User;
import com.zhang.exception.UserException;
import com.zhang.service.AdminHotelService;
import com.zhang.service.UserService;
import com.zhang.utils.DateUtils;
import com.zhang.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author prayers
 * @date 2020/1/9 21:17
 */
@WebServlet(name = "UserServlet", urlPatterns="/UserServlet")
public class UserServlet extends HttpServlet {

    public static final String REGIST = "register";
    public static final String VALIDATE_NAME = "validate";
    public static final String LOGIN = "login";
    public static final String ADD_HOTEL = "addHotel";
    public static final String GET_INDEX = "getIndex";
    public static final String GET_IMG = "getImg";
    public static final String GET_ONE_AT = "getOneAt";
    public static final String ADD_AT_CART = "addAt";


    private AdminHotelService adminHotelService = new AdminHotelService();
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

        if(GET_INDEX.equals(action)){
            getIndex(request,response);
        }else if(GET_IMG.equals(action)){
            getImg(request,response);
        }else if(REGIST.equals(action)){
            regist(request,response);
        } else if(VALIDATE_NAME.equals(action)){
            validateName(request, response);
        } else if(LOGIN.equals(action)){
            login(request, response);
        }else if(GET_ONE_AT.equals(action)){
            getOneAt(request,response);
        }else if(ADD_AT_CART.equals(action)){
            addAtCart(request,response);
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
        user.setPhone(Integer.parseInt(phone));
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
        String name = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println(name+password);
        password = MD5.md5(password);
        try {
            User user = userService.login(name,password);

            userService.updateLastLoginTime(user);
            //把用户信息保存到session中
            HttpSession session = request.getSession();
            request.getSession().setAttribute("user",user);
            String json= JSON.toJSONString(user);
            response.getWriter().print(json);


        } catch (UserException e) {
            request.setAttribute("error", e.getMessage());
            //两个页面合到一起jsp:.forwrad
            response.sendRedirect(request.getContextPath()+"/User/login.html");
        }
    }
    private void addAtCart(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        int uId=Integer.parseInt(request.getParameter("uId"));
        int spId=Integer.parseInt(request.getParameter("spId"));
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        double price= Double.parseDouble(request.getParameter("price"));

        System.out.println("uid="+uId+"spid="+spId+"qe="+quantity+"pr="+price);
        //3.封装到goods对象中
        Attractions attractions = new Attractions();
        attractions.setPrice(price);
        attractions.setSpId(spId);
        attractions.setuId(uId);
        attractions.setQuantity(quantity);

        attractions.setEntryTime(DateUtils.StrTime());
        //4.调用Service中add方法添加一条新闻
        Boolean result = userService.addAtCart(attractions);
        //返回添加成功的信息
        response.getWriter().print(result);

    }

    /**获得首页轮播图**/
    private void getImg(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        List<Map<String,Object>> result= userService.getImg();
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }

    /**获得首页信息**/
    private void getIndex(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        List<Map<String,Object>> result= userService.getIndex();
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**获得一条信息**/
    private void getOneAt(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String spId = request.getParameter("spId");
        //System.out.println("输出的值"+spId);
        //1.获取商品列表，调用Service的findAll方法,2.将获取的商品列表保存到request中
        //Map<String, Object> map = userService.findOne(Integer.parseInt(id));
        List<Map<String,Object>> result= userService.getOneAt(Integer.parseInt(spId));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);

    }

    private void changeHInf(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        int hId = Integer.parseInt(request.getParameter("hId"));
        String name = request.getParameter("hotel_name");
        String label = request.getParameter("hotel_label");
        String address = request.getParameter("hotel_address");
        String phone= request.getParameter("hotel_phone");

        String star = request.getParameter("hotel_star");
        String content = request.getParameter("content");

        Hotel hotel = new Hotel();
        hotel.setHotelId(hId);
        hotel.setHotelName(name);
        hotel.setHotelLabel(label);
        hotel.setHotelAddress(address);
        hotel.setHotelPhone(phone);

        hotel.setHotelStar(star);
        hotel.setHotelContent(content);
        hotel.setEntryTime(DateUtils.StrTime());
        //4.调用Service中add方法添加一条新闻
        Boolean result = adminHotelService.changeAtInf(hotel);
        //返回添加成功的信息
        response.getWriter().print(result);
    }


    /**根据id查看一条数据并传递到另一个页面**/
    private void findOneH(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("hId");
        Map<String, Object> result = adminHotelService.findOneH(Integer.parseInt(id));
        //创建Jackson的核心对象  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        //返回页面参数
        mapper.writeValue(response.getWriter(),result);
        System.out.println(result);

        //request.setAttribute("map",map);
        //request.getRequestDispatcher("/Admin/goodsDes.jsp").forward(request, response);
    }

    private void deleteH(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        /**1.获取id
         * 2.调用Service进行删除
         * 3.获取商品列表，直接调用findAll方法
         */
        //1.获取id
        String id = request.getParameter("hId");
        System.out.println("删除的id为："+id);
        Boolean result = adminHotelService.delete(Integer.parseInt(id));;
        //返回添加成功的信息
        response.getWriter().print(result);
        //findAllAttractions(request,response);

    }
    private void delHMore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取前台的ids
        String[] ids = request.getParameter("ids").split(",");
        adminHotelService.delMore(ids);


    }
    /*******获得省份信息*******/
    private void getProvince(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        List<Map<String,Object>> result= adminHotelService.findProvince();
        //创建Jackson的核心对象  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),result);
        //System.out.println(result);

    }

    private void addHotel(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String name = request.getParameter("hotel_name");
        String label = request.getParameter("hotel_label");
        String province = request.getParameter("province");
        String address = request.getParameter("hotel_address");
        String star = request.getParameter("hotel_star");
        String phone = request.getParameter("hotel_phone");
        String content = request.getParameter("content");

        //3.封装到goods对象中
        Hotel hotel = new Hotel();
        hotel.setHotelName(name);
        hotel.setHotelLabel(label);
        hotel.setProvince(province);
        hotel.setHotelAddress(address);
        hotel.setHotelStar(star);

        hotel.setHotelPhone(phone);
        hotel.setHotelContent(content);
        //goods.setBid(Integer.parseInt(bid));
        hotel.setEntryTime(DateUtils.StrTime());
        //4.调用Service中add方法添加一条新闻
        Boolean result = adminHotelService.addHotel(hotel);
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
