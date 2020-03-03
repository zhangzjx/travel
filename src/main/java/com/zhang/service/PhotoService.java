package com.zhang.service;

import com.zhang.dao.AdminDao;
import com.zhang.dao.HotelDao;
import com.zhang.dao.Photo;
import com.zhang.domain.Hotel;

/**
 * @author prayers
 * @date 2020/2/17 22:11
 */
public class PhotoService {
    /**添加品牌信息*/
    public void addHotelImg(Photo photo) {
        HotelDao.addHotelImg(photo);
    }
    /**添加头像信息*/
    public void addAdminImg(Photo photo) {
        AdminDao.addAdminImg(photo);
    }
}
