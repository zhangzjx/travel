package com.zhang.service;

import com.zhang.dao.HotelDao;
import com.zhang.dao.PageOther;
import com.zhang.dao.PageTwo;
import com.zhang.dao.UserDao;
import com.zhang.domain.Attractions;
import com.zhang.domain.Cart;
import com.zhang.domain.Comment;
import com.zhang.domain.User;
import com.zhang.exception.UserException;
import com.zhang.utils.DateUtils;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class UserService {
    UserDao userDao = new UserDao();
    /**验证账号唯一性**/
    public boolean validateName(String name) {
        User user = userDao.validateByName(name);
        if(user!=null){
            return false;
        }else{
            return true;
        }
    }
    /**注册账号*/
    public boolean regist(User user) {
        UserDao.regist(user);
        return true;
    }
    /**登录账号*/
    public User login(String name,String password) throws UserException {
        User user = userDao.login(name);
        if(user==null){
            throw new UserException("该用户不存在");
        }
        if(!password.equals(user.getPassword())){
            throw new UserException("密码不正确");
        }
        return user;
    }
    /**更新登陆时间*/
    public void updateLastLoginTime(User user) {
        user.setEntryTime(DateUtils.StrTime());
        userDao.updateLastLoginTime(user);
    }
    public Boolean addAtCart(Attractions attractions) {
        userDao.addAtCart(attractions);
        return true;
    }
    /*****添加订单第二步，提交商品信息及地址信息******/
    public void subOrder(Cart order) {
        //添加订单信息
        userDao.subOrder(order);
    }
    public void subOrderItem(Cart orderItem) {
        userDao.subOrderItem(orderItem);
    }
    /*****添加订单第三步，付款成功，等待发货******/
    public void payOrder(Cart order) {
        userDao.payOrder(order);
    }
    /*****添加订单第二步，提交商品信息及地址信息******/
    public void subOrderHotel(Cart order) {
        userDao.subOrderHotel(order);
    }
    public void subOrderHotelItem(Cart orderItem) {
        userDao.subOrderHotelItem(orderItem);
    }
    /*****添加订单第三步，付款成功，等待发货******/
    public void payOrderHotel(Cart order) {
        userDao.payOrderHotel(order);
    }
    /******查看所有订单*****/
    public PageOther findAllOrder(int uid, int currentPage) {
        String status=null;
        int totalSize = userDao.findCountOrder(uid,status);
        PageOther page = new PageOther(currentPage,totalSize);
        List<Map<String,Object>> list = UserDao.findAllOrder(uid,page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /******查看所有订单状态*****/
    public PageOther orderStatus(int uid, String status,int currentPage) {
        int totalSize = userDao.findCountOrder(uid,status);
        PageOther page = new PageOther(currentPage,totalSize);
        List<Map<String,Object>> list = UserDao.orderStatus(uid,status,page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /******查看酒店所有订单*****/
    public PageOther findAllOrderHotel(int uid, int currentPage) {
        String status=null;
        int totalSize = userDao.findCountOrderHotel(uid,status);
        PageOther page = new PageOther(currentPage,totalSize);
        List<Map<String,Object>> list = UserDao.findAllOrderHotel(uid,page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /******查看酒店所有订单状态*****/
    public PageOther orderStatusHotel(int uid, String status,int currentPage) {
        int totalSize = userDao.findCountOrderHotel(uid,status);
        PageOther page = new PageOther(currentPage,totalSize);
        List<Map<String,Object>> list = UserDao.orderStatusHotel(uid,status,page.getStartIndex(),page.getPageSize());
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /**获得首页轮播图**/
    public List<Map<String,Object>> getImg(){
        return userDao.getImg();
    }
    /**获得首页信息**/
    public List<Map<String,Object>> getIndex(){
        return userDao.getIndex();
    }

    /**获得个人信息**/
    public Map<String,Object> getUserInf(int uId){
        return userDao.getUserInf(uId);
    }
    /**修改个人信息**/
    public Boolean changeMyInf(User user) {
        userDao.changeMyInf(user);

        if(user!=null){
            return true;
        }else{
            return false;
        }
    }
    /**验证密码**/
    public boolean validatePw(String uid,String oldPassword) {
        User user = userDao.validateByPw(uid,oldPassword);
        if(user!=null){
            return true;
        }else{
            return false;
        }
    }
    /**修改密码**/
    public void changePw(User m) {
        userDao.changePw(m);
    }
    /**修改昵称**/
    public Boolean changeNc(User user) {
        userDao.changeNc(user);
        if(user!=null){
            return true;
        }else{
            return false;
        }
    }
    /**查询景点数目及分页，查询搜索结果数目及分页（搜索功能）*/
    public PageOther findSearch(int currentPage, String svalue) {
        int totalSize = userDao.findCountSearch(svalue);
        PageOther page = new PageOther(currentPage,totalSize);
        List<Map<String,Object>> list = userDao.findSearch(page.getStartIndex(),page.getPageSize(), svalue);
        page.setList(list);
        return page;
    }
    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderHt(String orderId){
        return userDao.getOneOrderHt(orderId);
    }
    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderAt(String orderId){
        return userDao.getOneOrderAt(orderId);
    }
    /*******撰写点评*******/
    public Boolean writeComment(Comment comment) {
        userDao.writeComment(comment);
        return true;
    }
    /**查看一条评论**/
    public Map<String, Object> getOneComment(int comment_id) {
        return userDao.getOneComment(comment_id);
    }
    /**获得所有评论信息**/
    public PageTwo getAllComment(int user_id, int currentPage) {
        int totalSize = userDao.findCountComment(user_id);
        PageTwo page = new PageTwo(currentPage,totalSize);
        List<Map<String,Object>> list = userDao.getAllComment(user_id,page.getStartIndex(),page.getPageSize());
        page.setList(list);
        return page;
    }
}
