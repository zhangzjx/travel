package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.zhang.dao.PageOther;
import com.zhang.dao.PageTwo;
import com.zhang.domain.*;
import com.zhang.exception.UserException;
import com.zhang.service.AdminHotelService;
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


/**
 * @author prayers
 * @date 2020/1/9 21:17
 */
@WebServlet(name = "UserServlet", urlPatterns="/UserServlet")
public class UserServlet extends HttpServlet {

    public static final String REGISTER = "register";
    public static final String VALIDATE_NAME = "validate";
    public static final String LOGIN = "login";
    public static final String GET_INDEX = "getIndex";
    public static final String GET_IMG = "getImg";
    public static final String ADD_AT_CART = "addAt";
    public static final String SUB_ORDER = "subOrder";
    public static final String PAY_ORDER = "payOrder";
    public static final String GET_USER_INF = "getUserInf";
    public static final String CHANGE_PASSWORD = "changePassword";
    public static final String CHANGE_PT = "changePt";
    public static final String CHANGE_NC = "changeNc";
    public static final String CHANGE_MY_INF = "changeMyInf";
    public static final String FIND_ALL_ORDER = "findAllOrder";
    public static final String ORDER_STATUS = "orderStatus";
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String FIND_SEARCH = "findSearch";
    public static final String SUB_HOTEL_ORDER = "subHotelOrder";
    public static final String FIND_ALL_ORDER_HOTEL = "findAllOrderHotel";
    public static final String PAY_ORDER_HOTEL = "payOrderHotel";
    public static final String GET_ONE_ORDER_HT = "getOneOrderHt";
    public static final String GET_ONE_ORDER_AT = "getOneOrderAt";
    public static final String WRITE_COMMENT = "writeComment";
    public static final String GET_ONE_COMMENT = "getOneComment";
    public static final String GET_ALL_COMMENT = "getAllComment";


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
            getIndex(response);
        }else if(GET_IMG.equals(action)){
            getImg(request,response);
        } else if(FIND_SEARCH.equals(action)){
            findSearch(request, response);
        } else if(REGISTER.equals(action)){
            register(request,response);
        } else if(VALIDATE_NAME.equals(action)){
            validateName(request, response);
        } else if(LOGIN.equals(action)){
            login(request, response);
        }else if(ADD_AT_CART.equals(action)){
            addAtCart(request,response);
        } else if(SUB_ORDER.equals(action)){
            subOrder(request, response);
        } else if(PAY_ORDER.equals(action)){
            payOrder(request, response);
        } else if(SUB_HOTEL_ORDER.equals(action)){
            subHotelOrder(request, response);
        } else if(PAY_ORDER_HOTEL.equals(action)){
            payOrderHotel(request, response);
        } else if(GET_USER_INF.equals(action)){
            getUserInf(request, response);
        } else if(CHANGE_MY_INF.equals(action)){
            changeMyInf(request, response);
        } else if(CHANGE_PASSWORD.equals(action)){
            changePassword(request, response);
        } else if(CHANGE_NC.equals(action)){
            changeNc(request, response);
        } else if(CHANGE_PT.equals(action)){
            changePt(request, response);
        } else if(FIND_ALL_ORDER.equals(action)){
            findAllOrder(request, response);
        } else if(ORDER_STATUS.equals(action)){
            orderStatus(request, response);
        } else if(FIND_ALL_ORDER_HOTEL.equals(action)){
            findAllOrderHotel(request, response);
        } else if(ORDER_STATUS.equals(action)){
            orderStatusHotel(request, response);
        } else if(GET_ONE_ORDER_HT.equals(action)){
            getOneOrderHt(request, response);
        } else if(GET_ONE_ORDER_AT.equals(action)){
            getOneOrderAt(request, response);
        } else if(WRITE_COMMENT.equals(action)){
            writeComment(request, response);
        } else if(GET_ALL_COMMENT.equals(action)){
            getAllComment(request, response);
        } else if(GET_ONE_COMMENT.equals(action)){
            getOneComment(request, response);
        }
    }


    /**注册*/
    private void register(HttpServletRequest request,HttpServletResponse response)
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

        /**
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
*/
        password = MD5.md5(password);
        User user=new User();
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);

        boolean result =  userService.regist(user);;
        response.getWriter().print(result);
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
            response.getWriter().print(1);
            //两个页面合到一起jsp:.forwrad
            //response.sendRedirect(request.getContextPath()+"/User/login.html");
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
    private void getIndex(HttpServletResponse response) throws IOException {
        List<Map<String,Object>> result= userService.getIndex();
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }

    /**获得所有评论信息**/
    private void getAllComment(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String current = request.getParameter("currentPage");
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        PageTwo page = userService.getAllComment(user_id, currentPage);
        String json= JSON.toJSONString(page);
        response.getWriter().print(json);
    }
    /**获得个人信息**/
    private void getUserInf(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String uId = request.getParameter("user_id");
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
        String uid = request.getParameter("user_id");
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
        String uid = request.getParameter("user_id");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");

        //System.out.println("获得的内容"+uid+" "+oldPassword+" "+newPassword);
        oldPassword = MD5.md5(oldPassword);

        boolean result = userService.validatePw(uid,oldPassword);

        if (result == true){
            User m = new User();
            newPassword = MD5.md5(newPassword);
            System.out.println(newPassword);
            m.setUid(Integer.parseInt(uid));
            m.setPassword(newPassword);
            userService.changePw(m);
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
        String uid = request.getParameter("user_id");
        String customerName = request.getParameter("customerName");

        User user = new User();
        user.setUid(Integer.parseInt(uid));
        user.setName(customerName);
        Boolean result = userService.changeNc(user);
        //返回添加成功的信息
        response.getWriter().print(result);
    }
    /*****添加订单第二步，提交商品信息，等待付款******/
    private void subOrder(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("user_id"));
        String phone = request.getParameter("phone");
        String receiver = request.getParameter("receiver");
        int id = Integer.parseInt(request.getParameter("id"));

        String spName = request.getParameter("spName");
        int ticketId = Integer.parseInt(request.getParameter("ticket_id"));
        String ticketType = request.getParameter("ticket_type");
        String ticketName = spName+ticketType;
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        int status = Integer.parseInt(request.getParameter("status"));

        System.out.println(uid+" "+quantity+" "+totalPrice+" "+phone+" "+receiver+""+status);

        Cart order = new Cart();
        Cart orderItem = new Cart();
        //oid存入session
        HttpSession session = request.getSession();

        try {
            String oid = DateUtils.nowTimeName();
            order.setOid(oid);
            orderItem.setOid(oid);
            session.setAttribute("oid", oid);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        order.setUid(uid);
        order.setPhone(phone);
        order.setReceiver(receiver);
        order.setTotalPrice(totalPrice);
        order.setStatus(status);
        try {
            order.setAddTime(DateUtils.nowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        orderItem.setId(id);
        orderItem.setTicket_id(ticketId);
        orderItem.setName(ticketName);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);

        userService.subOrder(order);
        userService.subOrderItem(orderItem);
        session.setAttribute("totalPrice", totalPrice);
        //返回添加成功的信息
        //创建Cookie
        String payPrice = String.valueOf(totalPrice);;
        Cookie cookie = new Cookie("totalPrice", payPrice);
        //设置Cookie的最大生命周期,否则浏览器关闭后Cookie即失效
        cookie.setMaxAge(Integer.MAX_VALUE);
        //将Cookie加到response中
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath()+"/User/pay.jsp");
    }
    /*****添加订单第三步，付款成功，等待发货******/
    private void payOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oid = request.getParameter("oid");
        String status = request.getParameter("status");
        System.out.println("状态"+oid+status);
        Cart order = new Cart();

        order.setOid(oid);
        order.setStatus(Integer.parseInt(status));
        userService.payOrder(order);
        response.sendRedirect(request.getContextPath()+"/User/paySuccess.html");
    }
    /*****添加订单第二步，提交商品信息，等待付款******/
    private void subHotelOrder(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("user_id"));
        String phone = request.getParameter("phone");
        String receiver = request.getParameter("receiver");

        int hotelId = Integer.parseInt(request.getParameter("hid"));
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        int bookDays = Integer.parseInt(request.getParameter("bookDays"));

        String hotelName = request.getParameter("hName");
        int roomId = Integer.parseInt(request.getParameter("room_id"));
        String roomStandard = request.getParameter("room_standard");
        String ticketName = hotelName+roomStandard;
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double totalPrice = Double.parseDouble(request.getParameter("totalPrice"));
        int status = Integer.parseInt(request.getParameter("status"));

        System.out.println(uid+" "+quantity+" "+totalPrice+" "+phone+" "+receiver+" "+status);

        Cart order = new Cart();
        Cart orderItem = new Cart();
        //oid存入session
        HttpSession session = request.getSession();

        try {
            String oid = DateUtils.nowTimeName();
            order.setOid(oid);
            orderItem.setOid(oid);
            session.setAttribute("oid", oid);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        order.setUid(uid);
        order.setPhone(phone);
        order.setReceiver(receiver);
        order.setTotalPrice(totalPrice);
        order.setStatus(status);
        try {
            order.setAddTime(DateUtils.nowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        orderItem.setHotelId(hotelId);
        orderItem.setBookDays(bookDays);
        orderItem.setStartTime(startTime);
        orderItem.setEndTime(endTime);
        orderItem.setRoomId(roomId);
        orderItem.setName(ticketName);
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);

        userService.subOrderHotel(order);
        userService.subOrderHotelItem(orderItem);
        session.setAttribute("totalPrice", totalPrice);
        //返回添加成功的信息
        //创建Cookie
        String payPrice = String.valueOf(totalPrice);;
        Cookie cookie = new Cookie("totalPrice", payPrice);
        //设置Cookie的最大生命周期,否则浏览器关闭后Cookie即失效
        cookie.setMaxAge(Integer.MAX_VALUE);
        //将Cookie加到response中
        response.addCookie(cookie);

        response.sendRedirect(request.getContextPath()+"/User/payHotel.jsp");
    }
    /*****添加订单第三步，付款成功，等待发货******/
    private void payOrderHotel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oid = request.getParameter("oid");
        String status = request.getParameter("status");
        System.out.println("状态"+oid+status);
        Cart order = new Cart();

        order.setOid(oid);
        order.setStatus(Integer.parseInt(status));
        userService.payOrderHotel(order);
        response.sendRedirect(request.getContextPath()+"/User/paySuccess.html");
    }
    /*****查看所有订单******/
    private void findAllOrder(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("user_id"));
        String current = request.getParameter("currentPage");
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        PageOther page = userService.findAllOrder(uid, currentPage);
        String json= JSON.toJSONString(page);
        response.getWriter().print(json);
    }

    /********查看订单状态**********/
    private void orderStatus(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("user_id"));
        String status = request.getParameter("status");
        String current = request.getParameter("currentPage");
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        if(ONE.equals(status)) {
            PageOther page = userService.orderStatus(uid, status,currentPage);
            String json= JSON.toJSONString(page);
            response.getWriter().print(json);
        } else if(TWO.equals(status)) {
            PageOther page = userService.orderStatus(uid, status,currentPage);
            String json= JSON.toJSONString(page);
            response.getWriter().print(json);
        }
    }
    /*****查看酒店所有订单******/
    private void findAllOrderHotel(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("user_id"));
        String current = request.getParameter("currentPage");
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        PageOther page = userService.findAllOrderHotel(uid, currentPage);
        String json= JSON.toJSONString(page);
        response.getWriter().print(json);
    }

    /********查看酒店订单状态**********/
    private void orderStatusHotel(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        int uid = Integer.parseInt(request.getParameter("user_id"));
        String status = request.getParameter("status");
        String current = request.getParameter("currentPage");
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        if(ONE.equals(status)) {
            PageOther page = userService.orderStatusHotel(uid, status,currentPage);
            String json= JSON.toJSONString(page);
            response.getWriter().print(json);
        } else if(TWO.equals(status)) {
            PageOther page = userService.orderStatusHotel(uid, status,currentPage);
            String json= JSON.toJSONString(page);
            response.getWriter().print(json);
        }
    }
    /**查询数据并分页
     * 根据关键词查找数据并传递到另一个页面(搜索功能)
     */
    private void findSearch(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        String svalue=request.getParameter("sValue");
        String current = request.getParameter("currentPage");
        System.out.println("跳转的页数"+svalue);
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        PageOther page = userService.findSearch(currentPage, svalue);
        /**request.setAttribute("myList",page);*/
        /**System.out.println("结果为"+page.getList());*/
        String json= JSON.toJSONString(page);
        response.getWriter().print(json);

    }
    /**获得一条酒店订单信息**/
    private void getOneOrderHt(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        Map<String,Object> result= userService.getOneOrderHt(orderId);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);

    }
    /**获得一条门票订单信息**/
    private void getOneOrderAt(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        Map<String,Object> result= userService.getOneOrderAt(orderId);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /*******撰写点评*******/
    private void writeComment(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String comment_title = request.getParameter("comment_title");
        String comment_content = request.getParameter("comment_content");
        int sc_id = Integer.parseInt(request.getParameter("sc_id"));

        System.out.println(user_id+" "+comment_title+" "+comment_content);

        Comment comment = new Comment();
        comment.setUser_id(user_id);
        comment.setComment_title(comment_title);
        comment.setComment_content(comment_content);
        comment.setScenicspot_id(sc_id);
        try {
            comment.setComment_time(DateUtils.nowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Boolean result= userService.writeComment(comment);
        //返回添加成功的信息
        response.getWriter().print(result);
        response.getWriter().print(result);

    }
    /**查看一条评论**/
    private void getOneComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String comment_id = request.getParameter("comment_id");
        System.out.println("hflahfja"+comment_id);
        Map<String,Object> result= userService.getOneComment(Integer.parseInt(comment_id));
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




    private void validate(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String name = request.getParameter("hotel_name");
        System.out.println("获得的内容"+name);
        // boolean result = adminHotelService.validateName(name);
        // response.getWriter().print(result);
    }


}
