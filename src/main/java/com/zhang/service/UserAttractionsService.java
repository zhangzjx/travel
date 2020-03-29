package com.zhang.service;

import com.zhang.dao.PageOther;
import com.zhang.dao.UserAttractionsDao;
import com.zhang.dao.UserDao;
import com.zhang.domain.Attractions;
import com.zhang.domain.Cart;
import com.zhang.domain.User;
import com.zhang.exception.UserException;
import com.zhang.utils.DateUtils;

import java.util.List;
import java.util.Map;

/**
 * @author prayers
 * @date 2020/2/20 21:53
 */
public class UserAttractionsService {
    UserAttractionsDao userAtDao = new UserAttractionsDao();


    /**获得热门景点信息**/
    public List<Map<String,Object>> getHotInf(int num){
        return userAtDao.getHotInf(num);
    }
    /**获得景点与地标信息**/
    public List<Map<String,Object>> getLandmarksInf(String label,int num){
        return userAtDao.getLandmarksInf(label,num);
    }
    /**获得自然与公园信息**/
    public List<Map<String,Object>> getSparksInf(String label,int num){
        return userAtDao.getSparksInf(label,num);
    }

    /**获得所有景点信息**/
    public PageOther getAllHotInf(int currentPage) {

        int totalSize = userAtDao.findCountAt();
        PageOther page = new PageOther(currentPage,totalSize);
        List<Map<String,Object>> list = UserAttractionsDao.getAllHotInf(page.getStartIndex(),page.getPageSize());
        page.setList(list);
        //System.out.println("页码"+page.getCurrentPage());
        return page;
    }
    /**获得一条景点信息**/
    public List<Map<String,Object>> getOneAt(int spId){
        return userAtDao.getOneAt(spId);
    }
    /**获得一条景点图片信息**/
    public List<Map<String, Object>> getOneAtImg(int spId) {
        return userAtDao.getOneAtImg(spId);
    }
    /**获得一条景点门票信息**/
    public List<Map<String, Object>> getTicket(String ticket_id) {
        return userAtDao.getTicket(ticket_id);
    }
    /**获取景点评论信息**/
    public List<Map<String, Object>> getAllComment(int sc_id) {
        return userAtDao.getAllComment(sc_id);
    }
}
