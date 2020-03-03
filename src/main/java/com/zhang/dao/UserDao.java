package com.zhang.dao;

import com.zhang.domain.Attractions;
import com.zhang.domain.User;
import com.zhang.utils.JdbcUtils;
import com.zhang.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class UserDao {

    /**注册*/
    public static void regist(User user){
        String sql = "insert into t_customer values(null,?,?,null,null,?,?,null,null,null)";
        Object []params={
                user.getName(),
                user.getPassword(),
                user.getPhone(),
                user.getEmail()

        };
        JdbcUtils.update(sql,params);
    }
    /** 验证账号唯一性**/
    public User validateByName(String name){
        /**
         * 1.通过用户名查找用户是否存在
         * 2.存在的话，给user对象赋值
         * 3.不存在，user=null
         */
        User user = null;
        String sql ="select * from t_customer where username=?";
        List<Map<String,Object>> list = JdbcUtils.find(sql,name);
        if(list.size()>0){
            user = new User();
            Map<String,Object> record = list.get(0);
            user.setName((String)record.get("username"));
        }
        return user;
    }
    /**登录*/
    public User login(String name){
        /*
         * 1.通过用户名查找用户是否存在
         * 2.存在的话，给user对象赋值
         * 3.不存在，user=null
         */
        User user = null;
        String sql ="select * from t_customer where username=?";

        List<Map<String,Object>> list = JdbcUtils.find(sql,name);
        if(list.size()>0){
            user = new User();
            Map<String,Object> record = list.get(0);
            user.setUid((Integer)record.get("uid"));
            user.setName((String)record.get("username"));
            user.setPassword((String)record.get("password"));

        }
        return user;
    }
    /**更新登陆时间*/
    public void updateLastLoginTime(User user) {
        String sql = "update t_customer set entryTime=? where username=?";
        Object []params={
                //new java.sql.Date(user.getLasttime().getTime()),
                user.getEntryTime(),
                user.getName()
        };

        JdbcUtils.update(sql,params);
    }
    /**首页显示的商品*/
    public List<Map<String,Object>> getImg() {
        String sql = "select imgName,imgId from t_main_img limit 0,6";
        return JdbcUtils.find(sql);
    }
    /**首页显示的商品*/
    public List<Map<String,Object>> getIndex() {
        String sql = "select spId,spName,spStar from t_scenicspot limit 0,5";
        return JdbcUtils.find(sql);
    }
    /**获得一条信息**/
    public List<Map<String,Object>> getOneAt(int spId) {
        String sql = "select * from t_scenicspot where spId=?";
        return JdbcUtils.find(sql,spId);
       // List<Map<String, Object>> list=JdbcUtils.find(sql, id);
        //return list.get(0);
    }
    /**添加景点信息
     * @return*/
    public Attractions addAtCart(Attractions attractions) {
        String sql = "insert into t_cart values(null,?,?,?,?,?)";
        Object[] params ={
                //占位
                attractions.getuId(),
                attractions.getSpId(),
                attractions.getQuantity(),
                attractions.getPrice(),
                attractions.getEntryTime(),
        };
        JdbcUtils.insert(sql, params);
        return attractions;
    }
}
