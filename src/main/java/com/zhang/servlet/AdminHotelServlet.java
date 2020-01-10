package com.zhang.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhang.domain.Hotel;
import com.zhang.service.AdminHotelService;
import com.zhang.utils.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;
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
        public static final String DEL_HOTEL = "delHotel";
        public static final String VALIDATE_NAME = "validateName";


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
            } else if(VALIDATE_NAME.equals(action)){
                validateName(request, response);
            } else if(FIND_ALL_HOTEL.equals(action)){
                findAllHotel(request, response);
            }
        }

        private void findAllHotel(HttpServletRequest request,
                                  HttpServletResponse response) throws IOException {
            System.out.println("执行");

            List<Map<String,Object>> result= adminHotelService.findAllHotel();
            //result.put("success", true);
           // result.put("totalCount", "30");
            //创建Jackson的核心对象  ObjectMapper
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getWriter(),result);
            mapper.writeValue(new FileWriter("F:\\IdeaProjects\\travel\\src\\main\\webapp\\Admin\\html\\b.json"),result);
            //response.getWriter().print(result);
        }

        private void addHotel(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

            String name = request.getParameter("hotel_name");
            String price = request.getParameter("hotel_price");
            String label = request.getParameter("hotel_label");
            String address = request.getParameter("hotel_address");
            String star = request.getParameter("hotel_star");
            //String content = request.getParameter("content");
            //String bid = request.getParameter("bid");

            System.out.println("获得的内容"+name+price+label+address+star);
            //3.封装到goods对象中
            Hotel hotel = new Hotel();
            hotel.setHotelName(name);
            hotel.setHotelPrice(price);
            hotel.setHotelLabel(label);
            hotel.setHotelAddress(address);
            hotel.setHotelStar(star);

            //goods.setBid(Integer.parseInt(bid));
            hotel.setTime(DateUtils.StrTime());
            //4.调用Service中add方法添加一条新闻
            Boolean result = adminHotelService.addHotel(hotel);
            //返回添加成功的信息
            response.getWriter().print(result);
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
