package com.zhang.dao;

import com.zhang.domain.Attractions;
import com.zhang.domain.Hotel;
import com.zhang.utils.DateUtils;
import com.zhang.utils.JdbcUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class AttractionsDao {

    /*******添加图片 获得景点信息*******/
    public static List<Map<String, Object>> getAttractionInf(String province) {
        String sql = "select spId,spName,spPlace from t_scenicspot where province=?";
        return JdbcUtils.find(sql,province);
    }
    /**添加景点信息
     * @return*/
    public Attractions addAttractions(Attractions attractions) {
        String sql = "insert into t_scenicspot values(null,?,?,?,?,?,?,null,?,?,?,?,?,null)";
        Object[] params ={
                //占位
                attractions.getAttractionsName(),
                attractions.getAttractionsPrice(),
                attractions.getProvince(),
                attractions.getAttractionsAddress(),

                attractions.getAttractionsLabel(),
                attractions.getAttractionsPhone(),
                //占位
                attractions.getTimeStart(),
                attractions.getTimeEnd(),

                attractions.getAttractionsInf(),
                attractions.getAttractionsStar(),
                attractions.getEntryTime(),
                //占位
        };
        JdbcUtils.insert(sql, params);
        return attractions;
    }
    /**查找一条酒店数据上传图片*/
    public static Attractions findOneAt(Photo photo) {
        String attraction_id =  photo.gethName();
        Attractions attractions = null;
        String sql = "select * from t_scenicspot where spId=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql, attraction_id);
        if(list.size()>0){
            attractions = new Attractions();
            Map<String,Object> record = list.get(0);
            attractions.setAttractionsName((String)record.get("spName"));
            attractions.setSpId((int)record.get("spId"));
        }
        return attractions;
    }
    /**验证是否第一次上传图片*/
    public static Attractions validateAt(Photo photo) {
        String attraction_id =  photo.gethName();
        Attractions attractions_img = null;
        String sql = "select * from t_scenicspot_img where spId=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql, attraction_id);

        if(list.size()>0){
            attractions_img = new Attractions();
            Map<String,Object> record = list.get(0);
            attractions_img.setAttractionsName((String)record.get("spName"));
        }
        return attractions_img;
    }
    /**上传景点照片信息*/
    public static void addAttractionsImg(Photo photo,Attractions attractions,int img_priority) {
        String sql = "insert into t_scenicspot_img values(null,?,?,?,?,?,null)";
        Object[] params ={
                photo.gethName(),
                attractions.getAttractionsName(),
                photo.getPhotoName(),
                photo.getFilePath(),
                img_priority
        };
        JdbcUtils.insert(sql, params);
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
        StringBuilder sql=new StringBuilder("select s.* from  t_scenicspot s");
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

        String sql = "select a.*,b.img_id,b.img_name,b.space from t_scenicspot a,t_scenicspot_img b" +
                " where a.spId=b.spId and b.img_priority=1 and a.spId=?";
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
    /**修改信息*/
    public static Attractions changeAtInf(Attractions attractions) {
        String sql="update t_scenicspot set spName=?,spAddress=?,spLabel=?,spPhone=?,spTimeStart=?," +
                "spTimeEnd=?,spFormation=?,spStar=?,spEntryTime=? where spId=?";
        Object[] params ={

                attractions.getAttractionsName(),
                attractions.getAttractionsAddress(),
                attractions.getAttractionsLabel(),
                attractions.getAttractionsPhone(),
                attractions.getTimeStart(),

                attractions.getTimeEnd(),
                attractions.getAttractionsInf(),
                attractions.getAttractionsStar(),
                attractions.getEntryTime(),
                attractions.getSpId()

        };
        JdbcUtils.update(sql, params);
        return attractions;
    }

    /**查询所有酒店信息并分页*/
    /**搜索结果总记录数*/
    public  int findCountOrderAt(String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select count(*) from t_orderinf");
        if(skey!=null&&skey.length()>0&&svalue!=null&&svalue.length()>0){
            //'%123%'
            sql.append(" where "+skey+" like \"%"+svalue+"%\" ");
        }
        return ((Number) JdbcUtils.selectScalar(sql.toString(), (Object[]) null)).intValue();
    }
    public  List<Map<String, Object>> findAllOrderAt(int startIndex, int pageSize,
                                                         String skey, String svalue) {
        StringBuilder sql=new StringBuilder("select a.spName,b.*,c.spId,c.price,c.buycount,c.name,c.total" +
                " from  t_scenicspot a,t_orderinf b,t_orderitem c where a.spId=c.spId and b.oid=c.oid ");
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
    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderAt(String orderId) {
        String sql = "select a.*,b.* from t_orderinf a,t_orderitem b " +
                "where a.oid=b.oid and  a.oid=?";
        List<Map<String, Object>> list = JdbcUtils.find(sql,orderId);
        return list.get(0);
    }
    /**删除一条订单*/
    public void delOrderAt(String orderId){
        String sql = "DELETE a,b FROM t_orderinf AS a LEFT JOIN t_orderitem AS b ON a.oid=b.oid WHERE a.oid=?";
        System.out.println("order      "+orderId);
        JdbcUtils.update(sql,orderId);
        //return attractions;
    }
    /**删除多条订单*/
    public  void delOrderAtMore(String[] ids) {
        String sql = "DELETE a,b FROM t_orderinf AS a LEFT JOIN t_orderitem AS b ON a.oid = b.oid WHERE a.oid=?";
        for (int i = 0; i < ids.length; i++) {
            System.out.println("删除数据的id为" + ids[i]);
            JdbcUtils.update(sql, ids[i]);
        }
    }
}
