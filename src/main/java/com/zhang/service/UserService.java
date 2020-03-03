package com.zhang.service;

import com.zhang.dao.UserDao;
import com.zhang.domain.Attractions;
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
    public void regist(User user) {
        UserDao.regist(user);
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

    public List<Map<String,Object>> getImg(){
        return userDao.getImg();
    }
    public List<Map<String,Object>> getIndex(){
        return userDao.getIndex();
    }
    public List<Map<String,Object>> getOneAt(int spId){
        return userDao.getOneAt(spId);
    }


}
