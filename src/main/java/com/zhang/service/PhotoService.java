package com.zhang.service;

import com.zhang.dao.AdminDao;
import com.zhang.dao.HotelDao;
import com.zhang.dao.Photo;
import com.zhang.domain.Hotel;
import com.zhang.domain.User;

/**
 * @author prayers
 * @date 2020/2/17 22:11
 */
public class PhotoService {
    /**添加酒店图片*/
    public void addHotelImg(Photo photo) {
        /**获取酒店信息*/
        Hotel hotel =  HotelDao.findOneH(photo);
        /**验证是否第一次上传图片*/
        Hotel hotelImg =  HotelDao.validateHotel(photo);
        if(hotelImg!=null){
            int img_priority = 2;
            HotelDao.addHotelImg(photo,hotel,img_priority);
        }else{
            int img_priority = 1;
            HotelDao.addHotelImg(photo,hotel,img_priority);
        }

    }
    /**添加头像信息*/
    public void addAdminImg(Photo photo) {
        AdminDao.addAdminImg(photo);
    }
}
