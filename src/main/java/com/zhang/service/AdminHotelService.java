package com.zhang.service;

import com.zhang.dao.HotelDao;
import com.zhang.dao.Page;
import com.zhang.domain.User;
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
    /**添加酒店房型
     * @param hotel*/
    public Boolean addHotelInf(Hotel hotel) {
        HotelDao.addHotelInf(hotel);
        return true;
    }

    /**查看酒店*/
    /**查询酒店数据并分页*/
    /**查询商品数目及分页，查询搜索结果数目及分页（搜索功能）*/
    public Page findAll(int currentPage, String skey, String svalue) {
        int totalSize = HotelDao.findCountHotel(skey,svalue);
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = HotelDao.findAllGoods(page.getStartIndex(),page.getPageSize(),skey,svalue);
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
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
