package com.zhang.dao;

import com.zhang.domain.Attractions;
import com.zhang.domain.Cart;
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
    /**获得个人信息**/
    public Map<String,Object> getUserInf(int uId) {
        String sql = "select * from t_customer where uId=?";
        List<Map<String, Object>> list=JdbcUtils.find(sql, uId);
        return list.get(0);
    }
    /**修改个人信息**/
    public User changeMyInf(User user) {
        String sql="update t_customer set customerName=?,sex=?,phone=?,email=? where uid=?";
        Object []params={
                user.getName(),
                user.getSex(),
                user.getPhone(),
                user.getEmail(),
                user.getUid()
        };
        JdbcUtils.update(sql, params);
        return user;
    }

    /**修改昵称**/
    public User changeNc(User user) {
        String sql="update t_customer set customerName=? where uid=?";
        Object []params={
                user.getName(),
                user.getUid()
        };
        JdbcUtils.update(sql, params);
        return user;
    }
    /** 验证账号密码**/
    public User validateByPw(String uid,String oldPassword) {

        User user = null;
        String sql ="select * from t_customer where uid=? and password=?";
        List<Map<String,Object>> list = JdbcUtils.find(sql,uid,oldPassword);
        if(list.size()>0){
            user = new User();
            Map<String,Object> record = list.get(0);
            user.setUid((Integer)record.get("uid"));
            user.setPassword((String)record.get("password"));
        }
        return user;
    }
    /*****修改密码******/
    public void changePw(User m) {
        String sql="update t_customer set password=? where uid=?";
        Object []params={
                m.getPassword(),
                m.getUid(),
        };
        JdbcUtils.update(sql, params);
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
    /*****添加订单第二步，提交商品信息及地址信息，等待付款******/
    public void subOrder(Cart order) {
        String sql = "insert into t_orderinf values(?,?,?,?,?,?,?,null)";
        Object[] params = {
                order.getOid(),
                order.getUid(),
                order.getReceiver(),
                order.getPhone(),
                order.getTotalPrice(),
                order.getAddTime(),
                order.getStatus(),
        };
        JdbcUtils.insert(sql, params);
    }
    public void subOrderItem(Cart orderItem) {
        String sql = "insert into t_orderitem values(null,?,?,?,null,?,?,null)";
        Object[] params = {
                orderItem.getOid(),
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getName(),
                orderItem.getPrice(),
        };
        JdbcUtils.insert(sql, params);
    }
    /*****添加订单第三步，付款成功，等待发货******/
    public void payOrder(Cart order) {
        String sql="update t_orderinf set status=? where oid=?";
        Object []params={
                order.getStatus(),
                order.getOid(),
        };
        JdbcUtils.update(sql, params);
    }
    /*******查看所有订单状态*******/
    /***分页开始****/
    public int findCountOrder(int uid,String status) {
        StringBuilder sql=new StringBuilder("select count(*) from t_orderinf  where uid="+uid+" ");
        if(status!=null&&status.length()>0){
            sql.append(" and status="+status);
        }
        return ((Number) JdbcUtils.selectScalar(sql.toString(), (Object[]) null)).intValue();
    }

    public static List<Map<String, Object>> findAllOrder(int uid,int startIndex, int pageSize) {
        String sql = "select a.spName,b.oid,b.ordertime,b.totalprice,b.status,c.spId,c.price,c.buycount,c.total " +
                "from t_scenicspot a,t_orderinf b,t_orderitem c " +
                "where a.spId=c.spId and b.oid=c.oid and b.uid=? limit ?,?";
        return JdbcUtils.find(sql, uid, startIndex, pageSize);
    }

    public static List<Map<String, Object>> orderStatus(int uid, String status,int startIndex, int pageSize) {
        String sql = "select a.spName,b.oid,b.ordertime,b.totalprice,b.status,c.spId,c.price,c.buycount,c.total " +
                "from t_scenicspot a,t_orderinf b,t_orderitem c " +
                "where a.spId=c.spId and b.oid=c.oid and b.uid=? and b.status=?  limit ?,?";
        return JdbcUtils.find(sql,uid,status,startIndex, pageSize);
    }
    /***分页结束****/
}
