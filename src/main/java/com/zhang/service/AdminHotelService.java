package com.zhang.service;

import com.zhang.dao.HotelDao;
import com.zhang.dao.User;
import com.zhang.domain.Hotel;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class AdminHotelService {
    /**添加酒店
     * @param hotel*/
    public Boolean addHotel(Hotel hotel) {
        HotelDao.addHotel(hotel);
        return true;
    }

    /**查看酒店*/
    public  List<Map<String,Object>> findAllHotel() {
        return HotelDao.findAllHotel();
    }
    /**查找一条数据*/
    public Map<String, Object> findOne(int id) {
        return HotelDao.findOne(id);
    }
    /**删除酒店*/
    public  void delete(int id){
        HotelDao.delete(id);
    }
    /**删除多条数据*/
    public void delMore(String[] ids,Object action){
        HotelDao.delMore(ids,action);
    }
    /**修改酒店*/
    public void update(Hotel hotel, Object action) {
        HotelDao.update(hotel, action);
    }


}
