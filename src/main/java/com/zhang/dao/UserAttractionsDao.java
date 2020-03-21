package com.zhang.dao;

import com.zhang.domain.Attractions;
import com.zhang.domain.Cart;
import com.zhang.domain.User;
import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class UserAttractionsDao {

    /**获得热门景点信息**/
    public List<Map<String,Object>> getHotInf(int num) {
        //String sql = "select spId,spName,spLabel,spStar from t_scenicspot limit 0,"+num;
        String sql = "select a.spId,a.spName,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_scenicspot a,t_scenicspot_img b where a.spId=b.spId and b.img_priority=1 limit 0,"+num;

        //随机查询 String sql = "select spId,spName,spStar from t_scenicspot order by rand() limit 0,"+num;
        return JdbcUtils.find(sql);
    }
    /**获得景点与地标信息**/
    public List<Map<String,Object>> getLandmarksInf(String label,int num) {
        String sql = "select a.spId,a.spName,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_scenicspot a,t_scenicspot_img b " +
                "where a.spId=b.spId and b.img_priority=1 and spLabel like \"%"+label+"%\" limit 0,"+num;
        return JdbcUtils.find(sql);
    }
    /**获得自然与公园信息**/
    public List<Map<String,Object>> getSparksInf(String label,int num) {
        String sql = "select a.spId,a.spName,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_scenicspot a,t_scenicspot_img b " +
                "where a.spId=b.spId and b.img_priority=1 and spLabel like \"%"+label+"%\" limit 0,"+num;
        //String sql = "select spId,spName,spLabel,spStar from t_scenicspot where spLabel like \"%"+label+"%\" limit 0,"+num;
        //  like \"%"+label+"%\"
        return JdbcUtils.find(sql);
    }
    /**获得所有景点信息**/
    /***分页开始****/
    public int findCountAt() {
        String sql = "select count(*) from t_scenicspot ";
        return ((Number) JdbcUtils.selectScalar(sql, (Object[]) null)).intValue();
    }

    public static List<Map<String, Object>> getAllHotInf(int startIndex, int pageSize) {
        String sql = "select a.spId,a.spName,a.spLabel,b.img_id,b.img_name,b.img_priority,b.space " +
                "from t_scenicspot a,t_scenicspot_img b where a.spId=b.spId and b.img_priority=1 limit ?,?";
        return JdbcUtils.find(sql, startIndex, pageSize);
    }
    /**获得一条信息**/
    public List<Map<String,Object>> getOneAt(int spId) {
        String sql = "select * from t_scenicspot where spId=?";
        return JdbcUtils.find(sql,spId);
        // List<Map<String, Object>> list=JdbcUtils.find(sql, id);
        //return list.get(0);
    }

    /**获得一条景点图片信息**/
    public List<Map<String, Object>> getOneAtImg(int spId) {
        String sql = "select * from t_scenicspot_img where spId=?";
        return JdbcUtils.find(sql,spId);
    }
}
