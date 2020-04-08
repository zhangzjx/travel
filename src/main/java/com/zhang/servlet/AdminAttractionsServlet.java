package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.dao.Page;
import com.zhang.dao.PageOther;
import com.zhang.domain.Attractions;
import com.zhang.domain.Hotel;
import com.zhang.service.AdminAttractionsService;
import com.zhang.service.AdminHotelService;
import com.zhang.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

/**
 * @author Administrator
 */
@WebServlet(name = "AdminAttractionsServlet", urlPatterns="/AdminAttractionsServlet")
public class AdminAttractionsServlet extends HttpServlet {

    public static final String ADD_ATTRACTIONS = "addAttractions";
    public static final String SEARCH_GO = "searchGo";
    public static final String DELETE_AT = "deleteAt";
    public static final String DEL_AT_MORE = "delAtMore";
    public static final String FIND_ONE_AT = "findOneAt";
    public static final String FIND_ALL_ATTRACTIONS = "findAllAttractions";
    public static final String CHANGE_AT_INF = "changeAtInf";
    public static final String FIND_ALL_ORDER_AT = "findAllOrderAt";
    public static final String GET_ONE_ORDER_AT = "getOneOrderAt";
    public static final String SEARCH_ORDER_GO = "searchOrderGo";
    public static final String DELETE_ORDER_AT = "deleteOrderAt";
    public static final String DELETE_ORDER_AT_MORE = "deleteOrderAtMore";

    private AdminAttractionsService adminAttractionsService = new AdminAttractionsService();
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.处理中文乱码问题
         * 2.根据不同的action进行处理不同的请求
         */
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取action的值
        String action = request.getParameter("action");

        if(ADD_ATTRACTIONS.equals(action)){
            addAttractions(request,response);
        } else if(FIND_ALL_ATTRACTIONS.equals(action)||SEARCH_GO.equals(action)){
            findAllAttractions(request, response);
        } else if(DELETE_AT.equals(action)){
            deleteAt(request, response);
        } else if(DEL_AT_MORE.equals(action)) {
            delAtMore(request, response);
        } else if(FIND_ONE_AT.equals(action)) {
            findOneAt(request, response);
        } else if(CHANGE_AT_INF.equals(action)) {
            changeAtInf(request, response);
        } else if(FIND_ALL_ORDER_AT.equals(action)||SEARCH_ORDER_GO.equals(action)){
            findAllOrderAt(request, response);
        } else if(GET_ONE_ORDER_AT.equals(action)){
            getOneOrderAt(request, response);
        } else if(DELETE_ORDER_AT.equals(action)){
            deleteOrderAt(request, response);
        } else if(DELETE_ORDER_AT_MORE.equals(action)){
            deleteOrderAtMore(request, response);
        }

    }

    /*****查看所有订单******/
    private void  findAllOrderAt(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String skey = request.getParameter("sKey");
        String svalue=request.getParameter("sValue");
        String current = request.getParameter("currentPage");
        System.out.println("关键字"+skey+"关键字"+svalue+"跳转的页数"+current);
        /**System.out.println("跳转的页数"+current);*/
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        Page page = adminAttractionsService.findAllOrderAt(currentPage,skey,svalue);
        String json= JSON.toJSONString(page);
        response.getWriter().print(json);

    }
    private void changeAtInf(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
        int spId = Integer.parseInt(request.getParameter("spId"));
        String name = request.getParameter("attractions_name");
        String label = request.getParameter("attractions_label");
        String price_min = request.getParameter("price_min");
        String price_max = request.getParameter("price_max");
        String address = request.getParameter("attractions_address");
        String time_start = request.getParameter("time_min");
        String time_end = request.getParameter("time_max");
        //String time = time_start+"-"+time_end;
        String phone= request.getParameter("attractions_phone");
        String star = request.getParameter("attractions_star");
        String content = request.getParameter("content");

        Attractions attractions = new Attractions();
        attractions.setSpId(spId);
        attractions.setAttractionsName(name);
        attractions.setAttractionsLabel(label);
        attractions.setAttractionsAddress(address);
        attractions.setTimeStart(time_start);
        attractions.setTimeEnd(time_end);
        attractions.setAttractionsPhone(phone);
        attractions.setAttractionsStar(star);
        attractions.setAttractionsInf(content);

        attractions.setEntryTime(DateUtils.StrTime());
        //4.调用Service中add方法添加一条新闻
        Boolean result = adminAttractionsService.changeAtInf(attractions);
        //返回添加成功的信息
        response.getWriter().print(result);
    }


    /**根据id查看一条数据并传递到另一个页面**/
    private void findOneAt(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("spId");
        Map<String, Object> result = adminAttractionsService.findOneAt(Integer.parseInt(id));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
        System.out.println(json);

        //request.setAttribute("map",map);
        //request.getRequestDispatcher("/Admin/goodsDes.jsp").forward(request, response);
    }

    private void deleteAt(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        /**1.获取id
         * 2.调用Service进行删除
         * 3.获取商品列表，直接调用findAll方法
         */
        //1.获取id
        String id = request.getParameter("spId");
        System.out.println("删除的id为："+id);
        Boolean result = adminAttractionsService.delete(Integer.parseInt(id));
        //返回添加成功的信息
        response.getWriter().print(result);
        //findAllAttractions(request,response);

    }
    private void delAtMore(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取前台的ids
        String[] ids = request.getParameter("ids").split(",");
        Boolean result = adminAttractionsService.delMore(ids);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
        // findAllAttractions(request,response);

    }

    /**查询数据并分页
     * 根据关键词查找数据并传递到另一个页面(搜索功能)
     */
    private void findAllAttractions(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        /**1.获取当前页码，如无当前页码默认为1
         * 2.获取商品列表，调用Service的findAll方法
         *   2.1调用Service层根据页码来获取Page对象
         * 3.将获取的商品列表保存到request中
         *   3.1将Page对象保存到request中
         * 4.将请求转发到goods_list.jsp页面
         */
        String skey = request.getParameter("sKey");
        String svalue=request.getParameter("sValue");
        String current = request.getParameter("currentPage");
        System.out.println("关键字"+skey+"关键字"+svalue+"跳转的页数"+current);
        /**System.out.println("跳转的页数"+current);*/
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        Page page = adminAttractionsService.findAllAt(currentPage,skey,svalue);

        String json= JSON.toJSONString(page);
        response.getWriter().print(json);
        /**request.setAttribute("myList",page);*/
        /**System.out.println("结果为"+page.getList());*/


    }

    /**获得一条门票订单信息**/
    private void getOneOrderAt(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        //System.out.println("输出"+orderId);;
        Map<String,Object> result= adminAttractionsService.getOneOrderAt(orderId);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }

    /**删除一条订单**/
    private void deleteOrderAt(HttpServletRequest request,
                               HttpServletResponse response) throws IOException {
        String orderId = request.getParameter("orderId");
        System.out.println("删除的id为："+orderId);
        Boolean result = adminAttractionsService.delOrderAt(orderId);
        response.getWriter().print(result);
    }
    /**删除多条订单**/
    private void deleteOrderAtMore(HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        String[] ids = request.getParameter("ids").split(",");
        Boolean result = adminAttractionsService.delOrderAtMore(ids);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }

    private void addAttractions(HttpServletRequest request,
                                HttpServletResponse response) throws IOException {

        String name = request.getParameter("attractions_name");
        String price_min = request.getParameter("price_min");
        String price_max = request.getParameter("price_max");
        String time_start = request.getParameter("time_min");
        String time_end = request.getParameter("time_max");
        String time = time_start+"-"+time_end;
        String label = request.getParameter("attractions_label");
        String address = request.getParameter("attractions_address");
        String phone= request.getParameter("attractions_phone");
        String star = request.getParameter("attractions_star");
        String content = request.getParameter("content");

        Attractions attractions = new Attractions();
        attractions.setAttractionsName(name);
        attractions.setAttractionsPrice(price_min);
        attractions.setAttractionsAddress(address);
        attractions.setAttractionsLabel(label);
        attractions.setAttractionsPhone(phone);
        attractions.setTimeStart(time_start);
        attractions.setTimeEnd(time_end);
        attractions.setAttractionsInf(content);
        attractions.setAttractionsStar(star);
        attractions.setEntryTime(DateUtils.StrTime());
        //4.调用Service中add方法添加一条新闻
        Boolean result = adminAttractionsService.addAttractions(attractions);
        //返回添加成功的信息
        response.getWriter().print(result);

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
