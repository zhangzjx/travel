package com.zhang.servlet;

import com.alibaba.fastjson.JSON;
import com.zhang.dao.PageOther;
import com.zhang.domain.Attractions;
import com.zhang.service.UserAttractionsService;
import com.zhang.service.UserService;
import com.zhang.utils.DateUtils;

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
@WebServlet(name = "UserAttractionsServlet", urlPatterns="/UserAttractionsServlet")
public class UserAttractionsServlet extends HttpServlet {

    public static final String GET_HOT_INF = "getHotInf";
    public static final String GET_LANDMARKS_INF = "getLandmarksInf";
    public static final String GET_SPARKS_INF = "getSparksInf";
    public static final String GET_ONE_AT = "getOneAt";
    public static final String GET_ONE_AT_IMG = "getOneAtImg";
    public static final String GET_ALL_HOT_INF = "getAllHotInf";
    public static final String GET_TICKET = "getTicket";
    public static final String GET_ALL_COMMENT = "getAllComment";

    private UserAttractionsService userAtService = new UserAttractionsService();
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

        if(GET_HOT_INF.equals(action)){
            getHotInf(request, response);
        } else if(GET_LANDMARKS_INF.equals(action)){
            getLandmarksInf(request, response);
        } else if(GET_SPARKS_INF.equals(action)){
            getSparksInf(request, response);
        } else if(GET_ALL_HOT_INF.equals(action)){
            getAllHotInf(request, response);
        } else if(GET_ONE_AT.equals(action)){
            getOneAt(request,response);
        } else if(GET_ONE_AT_IMG.equals(action)){
            getOneAtImg(request,response);
        } else if(GET_TICKET.equals(action)){
            getTicket(request,response);
        } else if(GET_ALL_COMMENT.equals(action)){
            getAllComment(request,response);
        }

    }
    /**获得所有景点信息**/
    private void getAllHotInf(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String current = request.getParameter("currentPage");
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        PageOther page = userAtService.getAllHotInf(currentPage);

        String json= JSON.toJSONString(page);
        //System.out.println("json"+json);
        response.getWriter().print(json);

    }
    /**获得热门景点信息**/
    private void getHotInf(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        String num = request.getParameter("num");
        List<Map<String,Object>> result= userAtService.getHotInf(Integer.parseInt(num));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**获得景点与地标信息**/
    private void getLandmarksInf(HttpServletRequest request,
                                 HttpServletResponse response) throws IOException {
        String label = request.getParameter("label");
        String num = request.getParameter("num");
        System.out.println(label);
        List<Map<String,Object>> result= userAtService.getLandmarksInf(label,Integer.parseInt(num));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }

    /**获得自然与公园信息**/
    private void getSparksInf(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {
        String label = request.getParameter("label");
        String num = request.getParameter("num");
        System.out.println(label);
        List<Map<String,Object>> result= userAtService.getSparksInf(label,Integer.parseInt(num));
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
        List<Map<String,Object>> result= userAtService.getOneAt(Integer.parseInt(spId));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);

    }
    /**获得一条景点图片信息**/
    private void getOneAtImg(HttpServletRequest request,
                        HttpServletResponse response) throws IOException {
        String spId = request.getParameter("spId");
        System.out.println(spId);
        List<Map<String,Object>> result= userAtService.getOneAtImg(Integer.parseInt(spId));
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }

    /**获得本景点门票信息**/
    private void getTicket(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
         String ticket_id = request.getParameter("ticket_id");
        List<Map<String,Object>> result= userAtService.getTicket(ticket_id);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    /**获取景点评论信息**/
    private void getAllComment(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        int sc_id = Integer.parseInt(request.getParameter("spId"));
        List<Map<String,Object>> result= userAtService.getAllComment(sc_id);
        String json= JSON.toJSONString(result);
        response.getWriter().print(json);
    }
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
