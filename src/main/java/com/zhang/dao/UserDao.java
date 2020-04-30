package com.zhang.dao;

import com.zhang.domain.Attractions;
import com.zhang.domain.Cart;
import com.zhang.domain.Comment;
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
        String sql = "insert into t_customer values(null,?,?,?,null,?,?,null,null)";
        Object []params={
                user.getName(),
                user.getPassword(),
                user.getName(),
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
    /**首页显示的轮播图*/
    public List<Map<String,Object>> getImg() {
        String sql = "select imgName,imgId from t_main_img limit 0,6";
        return JdbcUtils.find(sql);
    }
    /**首页显示的商品*/
    public List<Map<String,Object>> getIndex() {
        String sql = "select a.spId,a.spName,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_scenicspot a,t_scenicspot_img b where a.spId=b.spId and b.img_priority=1 limit 0,5";
        return JdbcUtils.find(sql);
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
        String sql = "insert into t_orderitem values(null,?,?,?,null,?,?,?,null)";
        Object[] params = {
                orderItem.getOid(),
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getTicket_id(),
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
    /*****添加订单第二步，提交商品信息及地址信息，等待付款******/
    public void subOrderHotel(Cart order) {
        String sql = "insert into t_order_hotel_inf values(?,?,?,?,?,?,?,null)";
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
    public void subOrderHotelItem(Cart orderItem) {
        String sql = "insert into t_order_hotel_item values(null,?,?,?,?,?,?,?,?,?,null)";
        Object[] params = {
                orderItem.getOid(),
                orderItem.getHotelId(),
                orderItem.getStartTime(),
                orderItem.getEndTime(),
                orderItem.getBookDays(),
                orderItem.getQuantity(),
                orderItem.getRoomId(),
                orderItem.getName(),
                orderItem.getPrice(),
        };
        JdbcUtils.insert(sql, params);
    }
    /*****添加订单第三步，付款成功，等待发货******/
    public void payOrderHotel(Cart order) {
        String sql="update t_order_hotel_inf set status=? where oid=?";
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
        String sql = "select a.spName,b.oid,b.ordertime,b.totalprice,b.status,c.spId,c.price,c.buycount,c.name,c.total " +
                "from t_scenicspot a,t_orderinf b,t_orderitem c " +
                "where a.spId=c.spId and b.oid=c.oid and b.uid=? limit ?,?";
        return JdbcUtils.find(sql, uid, startIndex, pageSize);
    }

    public static List<Map<String, Object>> orderStatus(int uid, String status,int startIndex, int pageSize) {
        String sql = "select a.spName,b.oid,b.ordertime,b.totalprice,b.status,c.spId,c.price,c.buycount,c.name,c.total " +
                "from t_scenicspot a,t_orderinf b,t_orderitem c " +
                "where a.spId=c.spId and b.oid=c.oid and b.uid=? and b.status=?  limit ?,?";
        return JdbcUtils.find(sql,uid,status,startIndex, pageSize);
    }
    /***分页结束****/
    /*******查看酒店所有订单状态*******/
    /***分页开始****/
    public int findCountOrderHotel(int uid,String status) {
        StringBuilder sql=new StringBuilder("select count(*) from t_order_hotel_inf  where uid="+uid+" ");
        if(status!=null&&status.length()>0){
            sql.append(" and status="+status);
        }
        return ((Number) JdbcUtils.selectScalar(sql.toString(), (Object[]) null)).intValue();
    }
    public static List<Map<String, Object>> findAllOrderHotel(int uid,int startIndex, int pageSize) {
        String sql = "select a.hName,b.oid,b.ordertime,b.totalprice,b.status,c.hotel_id,c.price,c.buycount,c.name,c.photo " +
                "from t_hotel a,t_order_hotel_inf b,t_order_hotel_item c  " +
                "where a.hId=c.hotel_id and b.oid=c.order_id and b.uid=? limit ?,?";
        return JdbcUtils.find(sql, uid, startIndex, pageSize);
    }

    public static List<Map<String, Object>> orderStatusHotel(int uid, String status,int startIndex, int pageSize) {
        String sql = "select a.hName,b.oid,b.ordertime,b.totalprice,b.status,c.hotel_id,c.price,c.buycount,c.name,c.photo " +
                "from t_hotel a,t_order_hotel_inf b,t_order_hotel_item c  " +
                "where a.hId=c.hotel_id and b.oid=c.order_id and b.uid=? and b.status=?  limit ?,?";
        return JdbcUtils.find(sql,uid,status,startIndex, pageSize);
    }
    /***分页结束****/
    /**搜索结果信息及分页*/
    /**搜索结果总记录数*/
    public  int findCountSearch(String svalue) {
        StringBuilder sql=new StringBuilder("select count(*) from t_scenicspot");
        if(svalue!=null&&svalue.length()>0){
            //'%123%'
            sql.append(" where spName like \"%"+svalue+"%\" ");
        }
        return ((Number) JdbcUtils.selectScalar(sql.toString(), (Object[]) null)).intValue();
    }
    /**开始记录的索引*/
    /**开始记录的索引（搜索结果）skey代表哪一列，svalue是具体的值
     /**select a.*,b.brand_name from goods a,goods_brand b where a.bid=b.id
     * and a.gid like 100000001 limit 0,3 条件是a.bid=b。id和当a.gid=100000001时
     * */
    public  List<Map<String, Object>> findSearch(int startIndex, int pageSize,
                                                 String svalue) {
        String sql = "select a.spId,a.spName,a.spLabel,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_scenicspot a,t_scenicspot_img b " +
                " where a.spId=b.spId and b.img_priority=1 and a.spName like \"%"+svalue+"%\" limit ?,?";
        return JdbcUtils.find(sql, startIndex, pageSize);
    }

    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderHt(String orderId) {
        String sql = "select a.*,b.* from t_order_hotel_inf a,t_order_hotel_item b " +
                "where a.oid=b.order_id and  a.oid=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql,orderId);
        return list.get(0);
    }
    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderAt(String orderId) {
        String sql = "select a.*,b.* from t_orderinf a,t_orderitem b " +
                "where a.oid=b.oid and  a.oid=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql,orderId);
        return list.get(0);
    }
    /*******撰写点评*******/
    public void writeComment(Comment comment) {
        String sql = "insert into t_scenicspot_comment values(null,?,?,?,?,?,null)";
        Object[] params ={
               comment.getUser_id(),
                comment.getComment_title(),
                comment.getComment_content(),
                comment.getComment_time(),
                comment.getScenicspot_id(),
        };
        JdbcUtils.insert(sql, params);
    }
    /**获得一条评论信息**/
    public Map<String, Object> getOneComment(int comment_id) {
        String sql = "select a.comment_content,a.space from t_scenicspot_comment a where a.comment_id=?";
        List<Map<String, Object>> list=JdbcUtils.find(sql, comment_id);
        return list.get(0);
    }
    /**获得所有评论信息并分页**/
    public int findCountComment(int user_id) {
        String sql = "select count(*) from t_scenicspot_comment where  user_id="+user_id;
        return ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
    }
    public List<Map<String, Object>> getAllComment(int user_id, int startIndex, int pageSize) {
        String sql = "select a.*,b.customerName,b.jf from t_scenicspot_comment a,t_customer b where a.user_id=b.uid and a.user_id=? limit ?,?";
        return JdbcUtils.find(sql, user_id, startIndex, pageSize);
    }
    /** 验证账号密码**/
    public User validateByCollect(int user_id,int scenicspot_id) {
        User inf = null;
        String sql ="select * from t_collect where user_id=? and scenicspot_id=?";
        List<Map<String,Object>> list = JdbcUtils.find(sql,user_id,scenicspot_id);
        if(list.size()>0){
            inf = new User();
            Map<String,Object> record = list.get(0);
            inf.setUid((Integer)record.get("user_id"));
        }
        return inf;
    }

    /**收藏**/
    public void addCollect(User collect) {
        String sql = "insert into t_collect values(null,?,?,?,null)";
        Object []params={
                collect.getUid(),
                collect.getScenicspot_id(),
                collect.getSort(),
        };
        JdbcUtils.update(sql, params);
    }

    /**获得所有收藏信息并分页**/
    public int findCountCollect(int user_id) {
        String sql = "select count(*) from t_collect where  user_id="+user_id;
        return ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
    }
    public List<Map<String, Object>> getAllCollect(int user_id, int startIndex, int pageSize) {
        String sql = "select a.*,b.spId,b.spName,c.img_id,c.img_name,c.space from t_collect a,t_scenicspot b,t_scenicspot_img c " +
                "where a.scenicspot_id=b.spId and b.spId=c.spId and c.img_priority=1 and a.user_id=? limit ?,?";
        return JdbcUtils.find(sql, user_id, startIndex, pageSize);
    }
}
