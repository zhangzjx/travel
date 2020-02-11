package com.zhang.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.dao.Page;
import com.zhang.domain.Hotel;
import com.zhang.service.AdminHotelService;
import com.zhang.utils.DateUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/1/9 21:17
 */
@WebServlet(name = "AdminHotelServlet", urlPatterns="/AdminHotelServlet")
public class AdminHotelServlet extends HttpServlet {

    public static final String LOGIN = "login";
    public static final String ADD_HOTEL = "addHotel";
    public static final String FIND_ALL_HOTEL = "findAllHotel";
    public static final String FIND_ALL_HOTEL_1 = "findAllHotel1";
    public static final String DEL_HOTEL = "delHotel";
    public static final String VALIDATE_NAME = "validateName";
    public static final String FIND_LIST_HOTEL = "findListHotel";
    public static final String FIND_JSON_HOTEL = "findJsonHotel";
    public static final String SEARCH_GO = "searchGo";
    public static final String ADD_HOTEL_INF = "addHotelInf";
    public static final String GET_PROVINCE = "getProvince";
    public static final String GET_HOTEL_INF = "getHotelInf";
    public static final String GET_HOTEL = "getHotel";


    private static final ServletRequest SESSION = null;
        private AdminHotelService adminHotelService = new AdminHotelService();

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

            if(ADD_HOTEL.equals(action)){
                addHotel(request,response);
            } else if(GET_PROVINCE.equals(action)){
                getProvince(request, response);
            } else if(GET_HOTEL.equals(action)){
                getHotel(request, response);
            } else if(ADD_HOTEL_INF.equals(action)){
                addHotelInf(request, response);
            } else if(VALIDATE_NAME.equals(action)){
                validateName(request, response);
            } else if(FIND_ALL_HOTEL.equals(action)||SEARCH_GO.equals(action)){
                findAllHotel(request, response);
            } else if(FIND_JSON_HOTEL.equals(action)){
                findJsonHotel(request, response);
            } else if(FIND_LIST_HOTEL.equals(action)){
                findListHotel(request, response);
            }
        }



    /*******获得省份信息*******/
    private void getProvince(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        List<Map<String,Object>> result= adminHotelService.findProvince();
        //创建Jackson的核心对象  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),result);
        System.out.println(result);

    }
    /*******获得酒店信息*******/
    private void getHotel(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String provinceId = request.getParameter("pro");
        System.out.println("省份id"+provinceId);
        List<Map<String,Object>> result= adminHotelService.getHotelInf(provinceId);
        //创建Jackson的核心对象  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),result);
    }
    /*******添加酒店信息*******/
    private void addHotel(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String name = request.getParameter("hotel_name");
        String label = request.getParameter("hotel_label");
        String address = request.getParameter("hotel_address");
        String star = request.getParameter("hotel_star");
        String phone = request.getParameter("hotel_phone");
        String content = request.getParameter("content");

        //String bid = request.getParameter("bid");

        System.out.println("获得的内容"+name+label+address+star);
        //3.封装到goods对象中
        Hotel hotel = new Hotel();
        hotel.setHotelName(name);
        hotel.setHotelLabel(label);
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
    /*******添加酒店房型信息*******/
    private void addHotelInf(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        String hId = request.getParameter("hotel_id");
        String standard = request.getParameter("room_Standard");
        String number = request.getParameter("room_Number");
        String price = request.getParameter("room_price");
        //String bid = request.getParameter("bid");

        Hotel hotel = new Hotel();
        hotel.setHotelId(Integer.parseInt(hId));
        hotel.setRoomStandard(standard);
        hotel.setRoomNumber(number);
        hotel.setPriceRoom(price);
        //goods.setBid(Integer.parseInt(bid));
        hotel.setEntryTime(DateUtils.StrTime());
        //4.调用Service中add方法添加一条新闻
        Boolean result = adminHotelService.addHotelInf(hotel);
        //返回添加成功的信息
        response.getWriter().print(result);
    }
        /**查询数据并分页
         * 根据关键词查找数据并传递到另一个页面(搜索功能)
         */
        public void findAllHotel(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            /**1.获取当前页码，如无当前页码默认为1
             * 2.获取商品列表，调用Service的findAll方法
             *   2.1调用Service层根据页码来获取Page对象
             * 3.将获取的商品列表保存到request中
             *   3.1将Page对象保存到request中
             * 4.将请求转发到jsp页面
             */
            String skey = request.getParameter("sKey");
            String svalue=request.getParameter("sValue");
            String current = request.getParameter("currentPage");
            System.out.println("跳转的页数"+current);
            int currentPage = 1;
            try{
                currentPage = Integer.parseInt(current);
            }catch(Exception e){
                currentPage = 1;
            }
            Page page = adminHotelService.findAll(currentPage,skey,svalue);
            /**request.setAttribute("myList",page);*/
            System.out.println("结果为"+page.getList());
            request.getSession().setAttribute("myList",page);
            response.sendRedirect(request.getContextPath()+"/Admin/html/hotelList.jsp");
            //request.getRequestDispatcher("/Admin/html/ts.jsp").forward(request, response);
        }
        /**生成json*/
        private void findJsonHotel(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
            List<Map<String,Object>> result= adminHotelService.findAllHotel();
            //创建Jackson的核心对象  ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),result);
            System.out.println(result);
            //response.getWriter().print(result);
        }

        /**生成固定格式json*/
        private void findListHotel(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {

            List<Map<String,Object>> res= adminHotelService.findAllHotel();

            JSONObject result = new JSONObject();
            result.put("code", 0);
            result.put("msg", "");
            result.put("count", 20);
            result.element("data", res);
            //创建Jackson的核心对象  ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),result);
            //创建json文件
            String filePath= "C:\\Users\\Administrator\\Desktop\\电子发票文档\\travel\\src\\main\\webapp\\Admin\\html\\hotel.json";
            /**覆盖原文件*/
            BufferedWriter writer = new BufferedWriter (new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
            /**不覆盖原文件
             BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (filePath,true),"UTF-8"));
             */
            mapper.writeValue(writer,result);
            System.out.println(result);
            //response.getWriter().print(result);
        }

        private void validateName(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
            /**
             * 1.从前台获取name
             * 2.调用Service层查找用户
             * 3.获取返回结果，提交给前台
             * */
            String name = request.getParameter("hotel_name");
            System.out.println("获得的内容"+name);
            // boolean result = adminHotelService.validateName(name);
            // response.getWriter().print(result);
        }

    }
