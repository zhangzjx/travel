package com.zhang.servlet;

import com.zhang.dao.Page;
import com.zhang.domain.Invoice;
import com.zhang.service.FpService;
import com.zhang.utils.DateUtils;
import com.zhang.utils.ExportExcel;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Administrator
 */
public class FpServlet extends javax.servlet.http.HttpServlet {
    public static final String TEST = "test";
    public static final String TEST1 = "test1";
    public static final String FIND_ALL = "findAll";
    public static final String TEST_EXCEL = "testExcel";

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request,
                         javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request,
                          javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取action的值
        String action = request.getParameter("action");

        if (TEST1.equals(action)) {
            add(request, response);
        } else if (FIND_ALL.equals(action)) {
            findAll(request, response);
        } else if (TEST_EXCEL.equals(action)) {
            testExcel(request, response);
        }
    }

    //从数据库获取数据导出
    private void testExcel(HttpServletRequest request, HttpServletResponse response) {

        FpService fpService = new FpService();
        List<Map<String, Object>> grouppList = fpService.findFp();

        System.out.println("第一步");
        System.out.println("数据库中共有"+grouppList.size());
        System.out.println("每条数据长度"+grouppList.get(1).size());
        //输出list内容
        for(int i = 0;i < grouppList.size();i++){
            System.out.println("grouppList属性"+grouppList.get(i));
        }

        System.out.println("第二步");
        //根据时间保存文件，确保文件名唯一
        long time = System.currentTimeMillis();
        Date date = new Date(time);
        String ma = "yyyyMMddhhmmssSSS";
        SimpleDateFormat sdf = new SimpleDateFormat(ma);
        String filename = sdf.format(date);

        String title = "待开票订单" + filename + ".xlsx";
        //设置表格标题行
        String[] headers = new String[]{"序号", "流水编号", "发票类型", "发票内容", "发票抬头", "纳税人识别号", "收票人", "联系电话", "详细地址"};

        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        //每个list中属性的长度
        //System.out.println("每条数据长度"+grouppList.get(1).size());
            for (int i = 0; i < grouppList.size(); i++) {
                objs = new Object[headers.length];

                Map<String, Object> map = grouppList.get(i);
                //设置序号,在工具类中会出现
                objs[0] = 0;
                objs[1] = map.get("ddbh").toString();
                objs[2] = map.get("kplx").toString();
                objs[3] = map.get("spmc").toString();
                objs[4] = map.get("fptt").toString();
                objs[5] = map.get("nsrsbh").toString();
                objs[6] = map.get("sky").toString();
                objs[7] = map.get("fkfdh").toString();
                objs[8] = map.get("fkfdz").toString();

                //数据添加到excel表格
                dataList.add(objs);
            }


        //使用流将数据导出
        OutputStream out = null;
        try {
            //防止中文乱码
            String headStr = "attachment; filename=\"" + new String(title.getBytes("gb2312"), "ISO8859-1") + "\"";
            response.setContentType("octets/stream");
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", headStr);
            out = response.getOutputStream();
            //没有标题
            ExportExcel ex = new ExportExcel(title, headers, dataList);
            System.out.println("导出成功");
            ex.export(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out=response.getWriter();       //向客户端发送字符数据
        response.setContentType("text/text");          //设置请求以及响应的内容类型以及编码方式
        response.setCharacterEncoding("UTF-8");
       // JSONArray json = JSONArray.fromObject(newsList);   //将newsList对象转换为json对象

        //String str = json.toString();                //将json对象转换为字符串
        //out.write(str);                     //将str字符传输到前台

        /**获得页面数据*/
        String DDBH = request.getParameter("DDBH");
        String KPLX = request.getParameter("KPLX");
        String SPMC = request.getParameter("SPMC");
        String FPTT = request.getParameter("FPTT");
        String nsrsbh = request.getParameter("nsrsbh");
        String SKY = request.getParameter("SKY");
        String FKFDH = request.getParameter("FKFDH");
        String FKFDZ = request.getParameter("FKFDZ");

        System.out.println(DDBH+KPLX+SPMC+FPTT+nsrsbh+SKY+FKFDH+FKFDZ);
        Invoice user=new Invoice();
        user.setDDBH(DDBH);
        user.setKPLX(KPLX);
        user.setSPMC(SPMC);
        user.setFPTT(FPTT);
        user.setNsrsbh(nsrsbh);
        user.setSKY(SKY);
        user.setFKFDH(FKFDH);
        user.setFKFDZ(FKFDZ);

        DateUtils dateutils = new DateUtils();
        try {
            user.setTime(DateUtils.nowTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        FpService fpService = new FpService();
        fpService.add(user);
        request.setAttribute("msg","<script>alert('添加成功')</script>");
        this.findAll(request,response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FpService fpService = new FpService();

        String current = request.getParameter("currentPage");
        int currentPage = 1;
        try{
            currentPage = Integer.parseInt(current);
        }catch(Exception e){
            currentPage = 1;
        }
        //1.获取待开发票表，调用Service的findAll方法,
        // 2.将获取的列表保存到request中
        Page page = fpService.findAll(currentPage);
        request.getSession().setAttribute("invoice",page);
        //3.将请求转发到invoice.jsp页面
        response.sendRedirect(request.getContextPath()+"/invoice.jsp");
    }


}
