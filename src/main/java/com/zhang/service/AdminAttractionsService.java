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

    /*******添加图片 获得景点信息*******/
    public  List<Map<String,Object>> getAttractionInf(String province) {
        return AttractionsDao.getAttractionInf(province);
    }
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
    public Boolean delMore(String[] ids){
        attractionsDao.delMore(ids);
        return true;
    }
    /**修改酒店*/
    public Boolean changeAtInf(Attractions attractions) {
        AttractionsDao.changeAtInf(attractions);
        return true;
    }
    /**查询景点订单数据并分页*/
    /**查询景点订单数目及分页，查询搜索结果数目及分页（搜索功能）*/
    public Page findAllOrderAt(int currentPage, String skey, String svalue) {
        int totalSize = attractionsDao.findCountOrderAt(skey,svalue);
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = attractionsDao.findAllOrderAt(page.getStartIndex(),page.getPageSize(),skey,svalue);
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }

    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderAt(String orderId){
        return attractionsDao.getOneOrderAt(orderId);
    }
    /**删除一条订单*/
    public Boolean delOrderAt(String orderId){
        System.out.println("ssssssssssssssssssss");
        attractionsDao.delOrderAt(orderId);
        return true;
    }
    /**删除多条订单*/
    public Boolean delOrderAtMore(String[] ids){
        attractionsDao.delOrderAtMore(ids);
        return true;
    }


}
