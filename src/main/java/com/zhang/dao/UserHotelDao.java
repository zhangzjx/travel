package com.zhang.dao;

import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class UserHotelDao {
    /**首页显示的信息*/
    public List<Map<String,Object>> getIndexHt() {
        String sql = "select a.hId,a.hName,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_hotel a,t_hotel_img b where a.hId=b.hotel_id and b.img_priority=1 limit 0,5";
        return JdbcUtils.find(sql);
    }

    /**获得所有景点信息**/
    /***分页开始****/
    public int findCountHt() {
        String sql = "select count(*) from t_hotel ";
        return ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
    }

    public List<Map<String, Object>> getAllHotelInf(String sort,int startIndex, int pageSize) {
        StringBuilder sql = new StringBuilder("select a.hId,a.hName,a.hLabel,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_hotel a,t_hotel_img b where a.hId=b.hotel_id and b.img_priority=1 ");
        if(sort!=null&&sort.length()>0){
            sql.append(" order by a.hotel_min_price "+sort+" ");
        }
        return JdbcUtils.find(sql.toString()+"limit ?,?", startIndex, pageSize);
    }
    /**获得一条信息**/
    public List<Map<String,Object>> getOneHt(int hId) {
        String sql = "select a.*,b.img_id,b.img_name,b.space from t_hotel a,t_hotel_img b" +
                " where a.hId=b.hotel_id and b.img_priority=1 and a.hId=?";
        return JdbcUtils.find(sql,hId);
        // List<Map<String, Object>> list=JdbcUtils.find(sql, id);
        //return list.get(0);
    }

    /**获得一条景点图片信息**/
    public List<Map<String, Object>> getOneHtImg(int hId) {
        String sql = "select * from t_hotel_img where hotel_id=?";
        return JdbcUtils.find(sql,hId);
    }
    /**获得一条景点门票信息**/
    public List<Map<String, Object>> getRoom(String room_id) {
        StringBuilder sql=new StringBuilder("select * from t_hotel_room");
        if(room_id!=null&&room_id!=""){
            sql.append(" where room_id="+room_id);
        }else {
            sql.append(" ");
        }
        return JdbcUtils.find(sql.toString());
    }

}
