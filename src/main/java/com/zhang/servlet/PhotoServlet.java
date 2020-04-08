package com.zhang.servlet;


import com.zhang.dao.Photo;
import com.zhang.service.PhotoService;
import com.zhang.utils.DateUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.util.List;
import java.util.Map;


/**
 * @author prayers
 * @date 2019/11/18 18:48
 */
@WebServlet(name = "PhotoServlet", urlPatterns="/PhotoServlet")
public class PhotoServlet extends HttpServlet {

    public static final String ADD_HOTEL_IMG = "addHotelImg";
    public static final String ADD_ADMIN_IMG = "addAdminImg";
    public static final String ADD_ATTRACTION_IMG = "addAttractionImg";
    Photo photo = new Photo();
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException, ServletException {
        doPost(request,response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //获取action的值
        String action = request.getParameter("action");

        if(ADD_HOTEL_IMG.equals(action)){
            addHotelImg(request,response);
        } else if(ADD_ATTRACTION_IMG.equals(action)){
            addAttractionImg(request,response);
        } else if(ADD_ADMIN_IMG.equals(action)){
            addAdminImg(request,response);
        }
    }
    /**景点添加照片*/
    private void addAttractionImg(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        //创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建一个文件上传解析器ServletFileUpload对象，并设置上传文件的大小限制
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try {
            Map<String, List<FileItem>> map = upload.parseParameterMap(request);
            FileItem fileItem = map.get("photo").get(0);
            String photoName = fileItem.getName();
            //获得字段名和字段值
            String brandName = map.get("id").get(0).getString("utf-8");

            System.out.println(photoName+brandName);
            InputStream inputStream = fileItem.getInputStream();

            /*在项目中需要对同个文件流进行两个操作，
            一个是上传文件流到HDFS上，一个是上传文件流到solr建立文件索引，
            将inputStream转换成ByteArrayOutputStream，
            重复使用流时再用byteArrayOutputStream转换回来*/
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            InputStream inputStream1 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            InputStream inputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            //String path = request.getServletContext().getRealPath("/WEB-INF/img/"+photoName);
            String path = "F:/IdeaProjects/eshop/web/WEB-INF/img/";
            String dataPath = "F:/IdeaProjects/eshop/web/WEB-INF/img/"+photoName;
            File file = new File(path);
            if (!file.exists() || !file.isDirectory()) {
                file.mkdirs();
            }
            //流的对拷
            FileOutputStream fileOutputStream = new FileOutputStream(path+photoName);

            byte[] bytes = new byte[512];
            int length;
            while ((length = inputStream1.read(bytes)) > -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

            //封装一下数据，存入数据库
            photo.sethName(brandName);
            photo.setPhotoName(photoName);
            photo.setFilePath(dataPath);

            System.out.println(inputStream2);
            System.out.println(path);

            PhotoService productService =  new PhotoService();
            productService.addHotelImg(photo);
            request.setAttribute("msg","添加成功");

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        AdminHotelServlet adminHotelServlet = new AdminHotelServlet();
        adminHotelServlet.findAllHotel(request,response);
    }

    /**管理员添加头像*/
    private void addAdminImg(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {

        //创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建一个文件上传解析器ServletFileUpload对象，并设置上传文件的大小限制
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try {
            Map<String, List<FileItem>> map = upload.parseParameterMap(request);
            FileItem fileItem = map.get("photo").get(0);
            String photoName = fileItem.getName();
            //获得字段名和字段值
             int admin_id = Integer.parseInt(map.get("admin_id").get(0).getString("utf-8"));

            System.out.println(photoName+admin_id);
            InputStream inputStream = fileItem.getInputStream();

            /*在项目中需要对同个文件流进行两个操作，
            一个是上传文件流到HDFS上，一个是上传文件流到solr建立文件索引，
            将inputStream转换成ByteArrayOutputStream，
            重复使用流时再用byteArrayOutputStream转换回来*/
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            InputStream inputStream1 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            InputStream inputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            //String path = request.getServletContext().getRealPath("/WEB-INF/img/"+photoName);
            String path = "F:/IdeaProjects/travel/src/main/webapp/res/img/adminImg/";
            String dataPath = "F:/IdeaProjects/travel/src/main/webapp/res/img/adminImg/"+photoName;
            File file = new File(path);
            if (!file.exists() || !file.isDirectory()) {
                file.mkdirs();
            }
            //流的对拷
            FileOutputStream fileOutputStream = new FileOutputStream(path+photoName);

            byte[] bytes = new byte[512];
            int length;
            while ((length = inputStream1.read(bytes)) > -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

            //封装一下数据，存入数据库
            photo.setaId(admin_id);
            photo.setPhotoName(photoName);

            System.out.println(inputStream2);
            System.out.println(dataPath);

            PhotoService productService =  new PhotoService();
            productService.addAdminImg(photo);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        //response.sendRedirect(request.getContextPath()+"/Admin/html/main.html");
    }
    /**酒店添加照片*/
    private void addHotelImg(HttpServletRequest request,
                             HttpServletResponse response) throws ServletException, IOException {

        //创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建一个文件上传解析器ServletFileUpload对象，并设置上传文件的大小限制
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        try {
            Map<String, List<FileItem>> map = upload.parseParameterMap(request);
            FileItem fileItem = map.get("photo").get(0);
            String photoName = fileItem.getName();
            //获得字段名和字段值
            String hName = map.get("hotel_id").get(0).getString("utf-8");

            System.out.println(photoName+hName);
            InputStream inputStream = fileItem.getInputStream();

            /*在项目中需要对同个文件流进行两个操作，
            一个是上传文件流到HDFS上，一个是上传文件流到solr建立文件索引，
            将inputStream转换成ByteArrayOutputStream，
            重复使用流时再用byteArrayOutputStream转换回来*/
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                byteArrayOutputStream.write(buffer, 0, len);
            }
            byteArrayOutputStream.flush();
            InputStream inputStream1 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            InputStream inputStream2 = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            //String path = request.getServletContext().getRealPath("/WEB-INF/img/"+photoName);
            String path = "F:/IdeaProjects/travel/src/main/webapp/res/img/hotelImg/";
            String dataPath = "F:/IdeaProjects/travel/src/main/webapp/res/img/hotelImg/"+photoName;
            File file = new File(path);
            if (!file.exists() || !file.isDirectory()) {
                file.mkdirs();
            }
            //流的对拷
            FileOutputStream fileOutputStream = new FileOutputStream(path+photoName);

            byte[] bytes = new byte[512];
            int length;
            while ((length = inputStream1.read(bytes)) > -1) {
                fileOutputStream.write(bytes, 0, length);
            }
            fileOutputStream.flush();
            fileOutputStream.close();

            //封装一下数据，存入数据库
            photo.sethName(hName);
            photo.setPhotoName(photoName);
            photo.setFilePath(dataPath);

            System.out.println(inputStream2);
            System.out.println(path);

            PhotoService productService =  new PhotoService();
            productService.addHotelImg(photo);

            request.getSession().setAttribute("msg","添加成功");

        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        response.sendRedirect(request.getContextPath()+"/Admin/html/addHotelImg.html");
        AdminHotelServlet adminHotelServlet = new AdminHotelServlet();
        //adminHotelServlet.findAllHotel(request,response);
    }

}
