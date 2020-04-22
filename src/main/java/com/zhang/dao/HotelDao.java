package com.zhang.dao;

import com.zhang.domain.Attractions;
import com.zhang.domain.Hotel;
import com.zhang.dao.Photo;
import com.zhang.domain.User;
import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class HotelDao {
    /*******获得省份信息*******/
    public static List<Map<String, Object>> findProvince() {
        String sql = "select * from t_provinces";
        return JdbcUtils.find(sql);
    }
    /*******获得酒店名称*******/
    public static List<Map<String, Object>> getHotelInf(String province) {
        String sql = "select hId,hName,hPlace from t_hotel where province=?";
        return JdbcUtils.find(sql,province);
    }
    /**添加酒店信息
     * @return*/
    public static Hotel addHotel(Hotel hotel) {
        String sql = "insert into t_hotel values(null,?,?,?,?,?,null,?,?,?,null)";
        Object[] params ={
                hotel.getHotelName(),
                hotel.getHotelLabel(),
                hotel.getHotelPhone(),
                hotel.getProvince(),
                hotel.getHotelAddress(),

                hotel.getHotelStar(),
                hotel.getHotelContent(),
                hotel.getEntryTime(),
        };
        JdbcUtils.insert(sql, params);
        return hotel;
    }
    /**添加酒店房型信息
     * @return*/
    public static Hotel addHotelInf(Hotel hotel) {
        String sql = "insert into t_hotel_room values(null,?,?,?,?,null)";
        Object[] params ={
                hotel.getHotelId(),
                hotel.getRoomStandard(),
                hotel.getRoomNumber(),
                hotel.getPriceRoom(),
                //hotel.getEntryTime(),
        };
        JdbcUtils.insert(sql, params);
        return hotel;
    }
    /**查找一条酒店数据上传图片*/
    public static Hotel findOneH(Photo photo) {
        String hotel_id =  photo.gethName();
        Hotel hotel = null;
        String sql = "select * from t_hotel where hId=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql, hotel_id);
        if(list.size()>0){
            hotel = new Hotel();
            Map<String,Object> record = list.get(0);
            hotel.setHotelName((String)record.get("hName"));
            hotel.setHotelId((int)record.get("hId"));
        }
        return hotel;
    }
    /**验证是否第一次上传图片*/
    public static Hotel validateHotel(Photo photo) {
        String hotel_id =  photo.gethName();
        Hotel hotel_img = null;
        String sql = "select * from t_hotel_img where hotel_id=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql, hotel_id);

        if(list.size()>0){
            hotel_img = new Hotel();
            Map<String,Object> record = list.get(0);
            hotel_img.setHotelName((String)record.get("hName"));
        }
        return hotel_img;
    }
    /**上传酒店照片信息*/
    public static void addHotelImg(Photo photo,Hotel hotel,int img_priority) {
        String sql = "insert into t_hotel_img values(null,?,?,?,?,?,null)";
        Object[] params ={
                photo.gethName(),
                hotel.getHotelName(),
                photo.getPhotoName(),
                photo.getFilePath(),
                img_priority
        };
        JdbcUtils.insert(sql, params);
    }


    /**查询所有酒店信息并分页*/
    /**搜索结果总记录数*/
    public static int findCountHotel(String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select count(*) from t_hotel");
        if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
            //'%123%'
            sql.append(" where "+skey+" like \"%"+svalue+"%\" ");
        }
        return ((Number) JdbcUtils.selectScalar(sql.toString(), (Object[]) null)).intValue();
    }
    /**开始记录的索引*/
    /**开始记录的索引（搜索结果）skey代表哪一列，svalue是具体的值
     /**select a.*,b.brand_name from goods a,goods_brand b where a.bid=b.id
     * and a.gid like 100000001 limit 0,3 条件是a.bid=b。id和当a.gid=100000001时
     * */
    public static List<Map<String, Object>> findAllHotel(int startIndex, int pageSize,
                                                         String skey,String svalue) {
        StringBuilder sql=new StringBuilder("select h.* from  t_hotel h");
        if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
            sql.append("  where h."+skey+" like \"%"+svalue+"%\" limit ?,?");
        }else{
            sql.append(" limit ?,?");
        }
        return JdbcUtils.find(sql.toString(), startIndex, pageSize);
    }
    public static List<Map<String, Object>> findAllHotel() {
        String sql = "select * from t_hotel";
        return JdbcUtils.find(sql);
    }
    /**查找一条数据*/
    public static Map<String, Object> findOne(int id) {
        String sql = "select a.*,b.img_id,b.img_name,b.space from t_hotel a,t_hotel_img b" +
                " where a.hId=b.hotel_id and b.img_priority=1 and a.hId=?";
        List<Map<String, Object>> list=JdbcUtils.find(sql, id);
        return list.get(0);
    }
    /**删除酒店*/
    public static void delete(int id){
        String sql = "delete from t_hotel where hId=?";
        JdbcUtils.update(sql,id);
    }
    /**删除多条数据*/
    public static void delMore(String[] ids) {
        String sql = "delete from t_hotel where hId=?";
        for (int i = 0; i < ids.length; i++) {
            System.out.println("删除数据的id为" + ids[i]);
            JdbcUtils.update(sql, ids[i]);
        }
    }
    /**修改酒店*/
    /**修改信息*/
    public static Hotel changeAtInf(Hotel hotel) {
        String sql="update t_hotel set hName=?,hAddress=?,hLabel=?,hPhone=?," +
                " hFormation=?,hStar=?,hEntryTime=? where hId=?";
        Object[] params ={
                hotel.getHotelName(),
                hotel.getHotelAddress(),
                hotel.getHotelLabel(),
                hotel.getHotelPhone(),
                hotel.getHotelContent(),

                hotel.getHotelStar(),
                hotel.getEntryTime(),
                hotel.getHotelId(),

        };
        JdbcUtils.update(sql, params);
        return hotel;
    }
    public static void update(Hotel hotel, Object action) {
        String sql="update hotel set name=?,price=?,lable=?,address=?,star=? where id=?";
        Object []params={
                hotel.getHotelName(),
                hotel.getHotelPrice(),
                hotel.getHotelLabel(),
                hotel.getHotelAddress(),
                hotel.getHotelStar(),
                hotel.getEntryTime(),
                hotel.getId(),
        };
        JdbcUtils.update(sql, params);
    }
    /**查询所有酒店信息并分页*/
    /**搜索结果总记录数*/
    public  int findCountOrderHt(String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select count(*) from t_order_hotel_inf");
        if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
            //'%123%'
            sql.append(" where "+skey+" like \"%"+svalue+"%\" ");
        }
        return ((Number) JdbcUtils.selectScalar(sql.toString(), (Object[]) null)).intValue();
    }
    public  List<Map<String, Object>> findAllOrderHt(int startIndex, int pageSize,
                                                     String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select a.hName,b.*,c.hotel_id,c.price,c.buycount,c.name,c.photo" +
                " from  t_hotel a,t_order_hotel_inf b,t_order_hotel_item c where a.hId=c.hotel_id and b.oid=c.order_id ");
        if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
            sql.append(" and  b."+skey+" like \"%"+svalue+"%\" limit ?,?");
        }else{
            sql.append(" limit ?,?");
        }

        return JdbcUtils.find(sql.toString(), startIndex, pageSize);
    }
    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderHt(String orderId) {
        String sql = "select a.*,b.* from t_order_hotel_inf a,t_order_hotel_item b " +
                "where a.oid=b.order_id and  a.oid=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql,orderId);
        return list.get(0);
    }

    /**删除一条订单*/
    public void delOrderHt(String orderId){
        String sql = "DELETE a,b FROM t_order_hotel_inf AS a LEFT JOIN t_order_hotel_item AS b ON a.oid=b.order_id WHERE a.oid=?";
        System.out.println("order     "+orderId);
        JdbcUtils.update(sql,orderId);
        //return attractions;
    }
    /**删除多条订单*/
    public  void delOrderHtMore(String[] ids) {
        String sql = "DELETE a,b FROM  t_order_hotel_inf AS a LEFT JOIN t_order_hotel_item AS b ON a.oid=b.order_id WHERE a.oid=?";
        for (int i = 0; i < ids.length; i++) {
            System.out.println("删除数据的id为" + ids[i]);
            JdbcUtils.update(sql, ids[i]);
        }
    }


}
