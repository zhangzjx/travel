package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.zhang.dao.PageOther;
import com.zhang.service.UserAttractionsService;
import com.zhang.service.UserHotelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@WebServlet(name = "UserHotelServlet", urlPatterns="/UserHotelServlet")
public class UserHotelServlet extends HttpServlet {

    public static final String GET_ALL_HOTEL_INF = "getAllHotelInf";
    public static final String GET_ONE_HT = "getOneHt";
    public static final String GET_ONE_HT_IMG = "getOneHtImg";
    public static final String GET_TICKET = "getTicket";

    private UserHotelService userHtService = new UserHotelService();

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

        if(GET_ALL_HOTEL_INF.equals(action)){
            getAllHotelInf(request, response);
        } else if(GET_ONE_HT.equals(action)){
            getOneHt(request,response);
        } else if(GET_ONE_HT_IMG.equals(action)){
            getOneHtImg(request,response);
        } else if(GET_TICKET.equals(action)){
            getTicket(request,response);
        }

    }
    /**获得所有酒店信息**/
    private void getAllHotelInf(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String current = request.getParameter("currentPage");

        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        PageOther page = userHtService.getAllHotelInf(currentPage);

        String json= JSON.toJSONString(page);
        //System.out.println("json"+json);
        response.getWriter().print(json);

    }

    /**获得一条酒店信息**/
    private void getOneHt(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String hId = request.getParameter("hId");
        //System.out.println("输出的值"+spId);
        //1.获取商品列表，调用Service的findAll方法,2.将获取的商品列表保存到request中
        //Map<String, Object> map = userService.findOne(Integer.parseInt(id));
        List<Map<String,Object>> result= userHtService.getOneHt(Integer.parseInt(hId));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);

    }
    /**获得一条酒店图片信息**/
    private void getOneHtImg(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        String hId = request.getParameter("hId");

        List<Map<String,Object>> result= userHtService.getOneHtImg(Integer.parseInt(hId));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }

    /**获得本酒店价格信息**/
    private void getTicket(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
         String ticket_id = request.getParameter("ticket_id");
        //System.out.println(spId);

        List<Map<String,Object>> result= userHtService.getTicket(ticket_id);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
