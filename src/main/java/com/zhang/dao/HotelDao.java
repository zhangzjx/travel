package com.zhang.dao;

import com.zhang.domain.Hotel;
import com.zhang.utils.JdbcUtils;

import java.util.List;
import java.util.Map;

public class HotelDao {
    /**添加酒店信息
     * @return*/
    public static Hotel addHotel(Hotel hotel) {
        String sql = "insert into hotel values(null,?,?,?,?,?,?)";
        Object[] params ={
                hotel.getHotelName(),
                hotel.getHotelPrice(),
                hotel.getHotelLabel(),
                hotel.getHotelAddress(),
                hotel.getHotelStar(),
                hotel.getTime(),
        };
        JdbcUtils.insert(sql, params);
        return hotel;
    }
    /**查询所有酒店信息*/
    public static List<Map<String, Object>> findAllHotel() {
        String sql = "select id,name,price,lable,address,star,time from hotel";
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
                hotel.getTime(),
                hotel.getId(),
        };
        JdbcUtils.update(sql, params);
    }
}