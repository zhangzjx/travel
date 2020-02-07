package com.zhang.dao;

import com.zhang.domain.Attractions;
import com.zhang.domain.Hotel;
import com.zhang.utils.JdbcUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class AttractionsDao {
    /**添加景点信息
     * @return*/
    public Attractions addAttractions(Attractions attractions) {
        String sql = "insert into t_scenicspot values(null,?,?,?,?,?,?,?,?,null,null,?,null)";
        Object[] params ={
                //占位
                attractions.getAttractionsName(),
                attractions.getAttractionsPrice(),
                attractions.getAttractionsAddress(),
                attractions.getAttractionsLabel(),

                attractions.getAttractionsPhone(),
                attractions.getTime(),
                attractions.getAttractionsInf(),
                attractions.getAttractionsStar(),
                //占位
                //占位
                attractions.getEntryTime(),
                //占位
        };
        JdbcUtils.insert(sql, params);
        return attractions;
    }
    /**查询所有酒店信息并分页*/
    /**搜索结果总记录数*/
    public  int findCountAttractions(String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select count(*) from t_scenicspot");
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
    public  List<Map<String, Object>> findAllAttractions(int startIndex, int pageSize,
                                                         String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select s.* from" +
                " t_scenicspot s");
        if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
            sql.append("  where s."+skey+" like \"%"+svalue+"%\" limit ?,?");
        }else{
            sql.append(" limit ?,?");
        }
        return JdbcUtils.find(sql.toString(), startIndex, pageSize);
    }
    public static List<Map<String, Object>> findAllHotel() {
        String sql = "select id,name,price,label,country,province,content,address,star,time from hotel limit 1,10";
        return JdbcUtils.find(sql);
    }
    /**查找一条数据*/
    public Map<String, Object> findOneAt(int id) {
        String sql = "select s.spName,s.spLabel,s.spTime,s.spAddress,s.spPhone,s.spStar " +
                " ,s.spFormation,s.spPlace from t_scenicspot s where spId=?";
        List<Map<String, Object>> list=JdbcUtils.find(sql, id);
        return list.get(0);
    }
    /**删除酒店*/
    public void delete(int id){
        String sql = "delete from t_scenicspot where spId=?";
        JdbcUtils.update(sql,id);
        //return attractions;
    }
    /**删除多条数据*/
    public  void delMore(String[] ids) {
        String sql = "delete from t_scenicspot where spId=?";
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
