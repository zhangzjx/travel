package com.zhang.service;

import com.zhang.dao.AdminDao;
import com.zhang.dao.PageOther;
import com.zhang.dao.UserDao;
import com.zhang.domain.Admin;
import com.zhang.domain.Attractions;
import com.zhang.domain.Cart;
import com.zhang.domain.User;
import com.zhang.exception.UserException;
import com.zhang.utils.DateUtils;

import java.util.List;
import java.util.Map;

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

}
