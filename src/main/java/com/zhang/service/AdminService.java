package com.zhang.service;

import com.zhang.dao.AdminDao;
import com.zhang.dao.Page;
import com.zhang.dao.UserDao;
import com.zhang.domain.Admin;
import com.zhang.domain.User;
import com.zhang.exception.UserException;
import com.zhang.utils.DateUtils;
import com.zhang.utils.JdbcUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class AdminService {
    UserDao userDao = new UserDao();
    AdminDao adminDao = new AdminDao();
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
    public void regist(User user) {
        UserDao.regist(user);
    }
    /**登录账号*/
    public Admin login(String username, String password) throws UserException {
        Admin admin = adminDao.login(username,password);
        if(admin == null){
            throw new UserException("该用户不存在或密码不正确");
        }
        return admin;
    }
    /**更新登陆时间*/
    public void updateLastLoginTime(Admin admin) {
        admin.setEntry_time(DateUtils.StrTime());
        adminDao.updateLastLoginTime(admin);
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
    public boolean validatePw(String admin_id,String oldPassword) {
        Admin admin = adminDao.validateByPw(admin_id,oldPassword);
        if(admin!=null){
            return true;
        }else{
            return false;
        }
    }
    /**修改密码**/
    public void changePw(Admin m) {
        adminDao.changePw(m);
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
    /**本日订单总数**/
    public int dayCountOrderAt() {
        return adminDao.dayCountOrderAt();
    }
    /**一周订单总数**/
    public int weekCountOrderAt() {
        return adminDao.weekCountOrderAt();
    }
    /**一个月订单总数**/
    public int monthCountOrderAt() {
        return adminDao.monthCountOrderAt();
    }
    /**echarts
     * @return*/
    public LinkedHashMap<String, Integer> echartsCountOrderAt(int days) {
        return adminDao.echartsCountOrderAt(days);
    }
    /**订单总数
     * @return**/
    /**查询今天销售总额**/
    public List<Map<String, Object>> dayCountMoneyAt() {
        return adminDao.dayCountMoneyAt();
    }
    /**查询昨天销售总额**/
    public List<Map<String, Object>> lastCountMoneyAt() {
        return adminDao.lastCountMoneyAt();
    }
    /**近七天销售总额**/
    public List<Map<String, Object>> weekCountMoneyAt() {
        return adminDao.weekCountMoneyAt();
    }
    /**获取景点评论信息**/

    public Page getAllComment(int currentPage, String skey, String svalue) {
        int totalSize = adminDao.findCountComment(skey,svalue);
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = adminDao.getAllComment(page.getStartIndex(),page.getPageSize(),skey,svalue);
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /**查看一条评论**/
    public Map<String, Object> getOneComment(int comment_id) {
        return adminDao.getOneComment(comment_id);
    }
    /**删除酒店*/
    public Boolean delete(int comment_id){
        adminDao.delete(comment_id);
        return true;
    }
    /**删除多条数据*/
    public Boolean delMore(String[] ids){
        adminDao.delMore(ids);
        return true;
    }
}
