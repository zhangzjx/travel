package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.dao.Page;
import com.zhang.domain.Attractions;
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

    public static final String ADD_HOTEL = "addHotel";
    public static final String FIND_ALL_HOTEL = "findAllHotel";
    public static final String SEARCH_GO = "searchGo";
    public static final String VALIDATE_NAME = "validateName";
    public static final String ADD_HOTEL_INF = "addHotelInf";
    public static final String GET_PROVINCE = "getProvince";
    public static final String GET_HOTEL_INF = "getHotelInf";
    public static final String GET_HOTEL = "getHotel";
    public static final String DELETE_H = "deleteH";
    public static final String DEL_H_MORE = "delHMore";
    public static final String FIND_ONE_H = "findOneH";
    public static final String CHANGE_H_INF = "changeHInf";
    public static final String FIND_ALL_ORDER_HT = "findAllOrderHt";
    public static final String SEARCH_ORDER_HT_GO = "searchOrderHtGo";
    public static final String GET_ONE_ORDER_HT = "getOneOrderHt";
    public static final String DELETE_ORDER_HT = "deleteOrderHt";
    public static final String DELETE_ORDER_HT_MORE = "deleteOrderHtMore";



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
            } else if(DELETE_H.equals(action)){
                deleteH(request, response);
            } else if(DEL_H_MORE.equals(action)) {
                delHMore(request, response);
            } else if(FIND_ONE_H.equals(action)) {
                findOneH(request, response);
            } else if(CHANGE_H_INF.equals(action)) {
                changeHInf(request, response);
            } else if(FIND_ALL_ORDER_HT.equals(action)||SEARCH_ORDER_HT_GO.equals(action)) {
                findAllOrderHt(request, response);
            } else if(GET_ONE_ORDER_HT.equals(action)){
                getOneOrderHt(request, response);
            } else if(DELETE_ORDER_HT.equals(action)){
                deleteOrderHt(request, response);
            } else if(DELETE_ORDER_HT_MORE.equals(action)){
                deleteOrderHtMore(request, response);
            }
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


    }
    private void delHMore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取前台的ids
        String[] ids = request.getParameter("ids").split(",");
        Boolean result = adminHotelService.delMore(ids);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);

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
    /*******获得酒店信息*******/
    private void getHotel(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String province = request.getParameter("pro");

        List<Map<String,Object>> result= adminHotelService.getHotelInf(province);
        //创建Jackson的核心对象  ObjectMapper
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(),result);
    }
    /*******添加酒店信息*******/
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
        String price = request.getParameter("price_room");
        System.out.println(standard);

        Hotel hotel = new Hotel();
        hotel.setHotelId(Integer.parseInt(hId));
        hotel.setRoomStandard(standard);
        hotel.setRoomNumber(number);
        hotel.setPriceRoom(Double.valueOf(price));
        //goods.setBid(Integer.parseInt(bid));
        //hotel.setEntryTime(DateUtils.StrTime());
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

            String json= JSON.toJSONString(page);
            response.getWriter().print(json);
            /**request.setAttribute("myList",page);*/
            /**request.getSession().setAttribute("myList",page);
            response.sendRedirect(request.getContextPath()+"/Admin/html/hotelList.jsp");
            //request.getRequestDispatcher("/Admin/html/ts.jsp").forward(request, response);
             */
        }

    /*****查看所有订单******/
    private void  findAllOrderHt(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        String skey = request.getParameter("sKey");
        String svalue=request.getParameter("sValue");
        String current = request.getParameter("currentPage");
        /** System.out.println("关键字"+skey+"关键字"+svalue+"跳转的页数"+current);*/
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        Page page = adminHotelService.findAllOrderHt(currentPage,skey,svalue);
        String json= JSON.toJSONString(page);
        response.getWriter().print(json);

    }
    /**获得一条门票订单信息**/
    private void getOneOrderHt(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        //System.out.println("输出"+orderId);;
        Map<String,Object> result= adminHotelService.getOneOrderHt(orderId);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }


    /**删除一条订单**/
    private void deleteOrderHt(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        System.out.println("删除的id为："+orderId);
        Boolean result = adminHotelService.delOrderHt(orderId);
        response.getWriter().print(result);
    }
    /**删除多条订单**/
    private void deleteOrderHtMore(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        String[] ids = request.getParameter("ids").split(",");
        Boolean result = adminHotelService.delOrderHtMore(ids);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
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
