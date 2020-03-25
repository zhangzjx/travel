package com.zhang.dao;

import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class UserHotelDao {

    /**获得所有景点信息**/
    /***分页开始****/
    public int findCountHt() {
        String sql = "select count(*) from t_hotel ";
        return ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
    }

    public List<Map<String, Object>> getAllHotelInf(int startIndex, int pageSize) {
        String sql = "select a.hId,a.hName,a.hLabel,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_hotel a,t_hotel_img b where a.hId=b.hotel_id and b.img_priority=1 limit ?,?";
        return JdbcUtils.find(sql, startIndex, pageSize);
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
    public List<Map<String, Object>> getTicket(String ticket_id) {
        StringBuilder sql=new StringBuilder("select * from t_scenicspot_ticket");
        if(ticket_id!=null&&ticket_id!=""){
            sql.append(" ");
        }else {
            sql.append(" where ticket_id="+ticket_id);
        }
        return JdbcUtils.find(sql.toString());
    }

}
