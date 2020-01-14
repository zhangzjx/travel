package com.zhang.service;

import com.zhang.dao.AttractionsDao;
import com.zhang.dao.HotelDao;
import com.zhang.dao.Page;
import com.zhang.domain.Attractions;
import com.zhang.domain.Hotel;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class AdminAttractionsService {
    AttractionsDao  attractionsDao = new  AttractionsDao();

    /**添加酒店
     * @param attractions
     * @return*/
    public Boolean addAttractions(Attractions attractions) {
        attractionsDao.addAttractions(attractions);
        return true;
    }

    /**查询景点数据并分页*/
    /**查询景点数目及分页，查询搜索结果数目及分页（搜索功能）*/
    public Page findAllAt(int currentPage, String skey, String svalue) {
        int totalSize = attractionsDao.findCountAttractions(skey,svalue);
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = attractionsDao.findAllAttractions(page.getStartIndex(),page.getPageSize(),skey,svalue);
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    public static List<Map<String,Object>> findAllHotel() {
        return HotelDao.findAllHotel();
    }
    /**查找一条数据*/
    public Map<String, Object> findOneAt(int id) {
        return attractionsDao.findOneAt(id);
    }
    /**删除酒店*/
    public Boolean delete(int id){
        attractionsDao.delete(id);
        return true;
    }
    /**删除多条数据*/
    public void delMore(String[] ids){
        attractionsDao.delMore(ids);
    }
    /**修改酒店*/
    public void update(Hotel hotel, Object action) {
        HotelDao.update(hotel, action);
    }


}
