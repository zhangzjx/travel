package com.zhang.utils;



import com.zhang.dao.AdminDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * @author prayers
 * @date 2020/3/28 18:48
 */
public class TestTwo {
    public static void main(String[] args) throws ParseException {
        AdminDao adminDao = new AdminDao();
 /**测试dao层方法**/
        //adminDao.echartsCountOrderAt(days);
        adminDao.dayCountMoneyAt();


/**
        for (int i = 1; i < 7; i++){
            System.out.println(now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH) + 1)+ "-"+(now.get(Calendar.DAY_OF_MONTH)-i));
        }
 */
        System.out.println(DateUtils.nowDate());
    }


}
