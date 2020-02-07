package com.zhang.dao;

import com.zhang.domain.Hotel;
import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class HotelDao {
    /**添加酒店信息
     * @return*/
    public static Hotel addHotel(Hotel hotel) {
        String sql = "insert into hotel values(null,?,?,?,?,?,?,?)";
        Object[] params ={
                hotel.getHotelName(),
                hotel.getHotelLabel(),
                hotel.getHotelAddress(),
                hotel.getHotelStar(),
                hotel.getHotelPhone(),

                hotel.getHotelContent(),
                hotel.getEntryTime(),
        };
        JdbcUtils.insert(sql, params);
        return hotel;
    }
    /**添加酒店房型信息
     * @return*/
    public static Hotel addHotelInf(Hotel hotel) {
        String sql = "insert into hotel values(null,?,?,?,?,?)";
        Object[] params ={
                hotel.getHotelId(),
                hotel.getRoomNumber(),
                hotel.getRoomStandard(),
                hotel.getPriceRoom(),
                hotel.getEntryTime(),
        };
        JdbcUtils.insert(sql, params);
        return hotel;
    }
    /**查询所有酒店信息并分页*/
    /**搜索结果总记录数*/
    public static int findCountHotel(String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select count(*) from hotel");
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
    public static List<Map<String, Object>> findAllGoods(int startIndex, int pageSize,
                                                         String skey,String svalue) {
        StringBuilder sql=new StringBuilder("select h.* from" +
                " hotel h");
        if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
            sql.append("  where h."+skey+" like \"%"+svalue+"%\" limit ?,?");
        }else{
            sql.append(" limit ?,?");
        }
        return JdbcUtils.find(sql.toString(), startIndex, pageSize);
    }
    public static List<Map<String, Object>> findAllHotel() {
        String sql = "select * from hotel";
        return JdbcUtils.find(sql);
    }
    /**查找一条数据*/
    public static Map<String, Object> findOne(int id) {
        String sql = "select * from hotel where id=?";
        List<Map<String, Object>> list=JdbcUtils.find(sql, id);
        return list.get(0);
    }
    /**删除酒店*/
    public static void delete(int id){
        String sql = "delete from hotel where id=?";
        JdbcUtils.update(sql,id);
    }
    /**删除多条数据*/
    public static void delMore(String[] ids,Object action) {
        String sql = "delete from hotel where id=?";
        for (int i = 0; i < ids.length; i++) {
            System.out.println("删除数据的id为" + ids[i]);
            JdbcUtils.update(sql, ids[i]);
        }
    }
    /**修改酒店*/
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
}
