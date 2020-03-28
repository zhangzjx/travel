package com.zhang.service;

import com.zhang.dao.PageOther;
import com.zhang.dao.UserAttractionsDao;
import com.zhang.dao.UserHotelDao;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class UserHotelService {
    UserHotelDao userHtDao = new UserHotelDao();
    /**获得首页信息**/
    public List<Map<String,Object>> getIndexHt(){
        return userHtDao.getIndexHt();
    }

    /**获得所有景点信息**/
    public PageOther getAllHotelInf(int currentPage, String sort) {
        int totalSize = userHtDao.findCountHt();
        PageOther page = new PageOther(currentPage,totalSize);
        List<Map<String,Object>> list = userHtDao.getAllHotelInf(sort,page.getStartIndex(),page.getPageSize());
        page.setList(list);
        //System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /**获得一条景点信息**/
    public List<Map<String,Object>> getOneHt(int hId){
        return userHtDao.getOneHt(hId);
    }
    /**获得一条景点图片信息**/
    public List<Map<String, Object>> getOneHtImg(int hIdId) {
        return userHtDao.getOneHtImg(hIdId);
    }
    /**获得一条景点门票信息**/
    public List<Map<String, Object>> getRoom(String room_id) {
        return userHtDao.getRoom(room_id);
    }
}
