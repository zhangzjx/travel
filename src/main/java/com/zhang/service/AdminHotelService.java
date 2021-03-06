package com.zhang.service;

import com.zhang.dao.AttractionsDao;
import com.zhang.dao.HotelDao;
import com.zhang.dao.Page;
import com.zhang.domain.Attractions;
import com.zhang.domain.User;
import com.zhang.domain.Hotel;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
public class AdminHotelService {
    HotelDao hotelDao = new HotelDao();
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
    /*******获得省份信息*******/
    public  List<Map<String,Object>> findProvince() {
        return HotelDao.findProvince();
    }
    /*******获得酒店信息*******/
    public  List<Map<String,Object>> getHotelInf(String province) {
        return HotelDao.getHotelInf(province);
    }

    /**查看酒店*/
    /**查询酒店数据并分页*/
    /**查询商品数目及分页，查询搜索结果数目及分页（搜索功能）*/
    public Page findAll(int currentPage, String skey, String svalue) {
        int totalSize = HotelDao.findCountHotel(skey,svalue);
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = HotelDao.findAllHotel(page.getStartIndex(),page.getPageSize(),skey,svalue);
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    public  List<Map<String,Object>> findAllHotel() {
        return HotelDao.findAllHotel();
    }
    /**查找一条数据*/
    public Map<String, Object> findOneH(int id) {
        return HotelDao.findOne(id);
    }
    /**删除酒店*/
    public Boolean delete(int id){
        HotelDao.delete(id);
        return true;
    }
    /**删除多条数据*/
    public Boolean delMore(String[] ids){
        HotelDao.delMore(ids);
        return true;
    }
    /**修改酒店*/
    public void update(Hotel hotel, Object action) {
        HotelDao.update(hotel, action);
    }
    public Boolean changeAtInf(Hotel hotel) {
        HotelDao.changeAtInf(hotel);
        return true;
    }
    /**查询景点订单数据并分页*/
    /**查询景点订单数目及分页，查询搜索结果数目及分页（搜索功能）*/
    public Page findAllOrderHt(int currentPage, String skey, String svalue) {
        int totalSize = hotelDao.findCountOrderHt(skey,svalue);
        Page page = new Page(currentPage,totalSize);
        List<Map<String,Object>> list = hotelDao.findAllOrderHt(page.getStartIndex(),page.getPageSize(),skey,svalue);
        page.setList(list);
        System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /**获得一条订单信息**/
    public Map<String,Object> getOneOrderHt(String orderId){
        return hotelDao.getOneOrderHt(orderId);
    }

    /**删除一条订单*/
    public Boolean delOrderHt(String orderId){
        System.out.println("ssssssssssssssssssss");
        hotelDao.delOrderHt(orderId);
        return true;
    }
    /**删除多条订单*/
    public Boolean delOrderHtMore(String[] ids){
        hotelDao.delOrderHtMore(ids);
        return true;
    }

}
