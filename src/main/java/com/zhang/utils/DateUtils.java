package com.zhang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {

    public static String StrTime() {
        //获得系统时间.
        //long time = System.currentTimeMillis();
        //Date date = new Date(time);
        Date date = new Date();
        String ma = "yyyyMMddhhmmss";
        SimpleDateFormat sdf = new SimpleDateFormat(ma);
        String f = sdf.format(date);
        //System.out.println(f);
        return f;

    }
    /**
     * 获取系统时间
     * 调用
     *    try {
     *             goods.setPublishedDate(DateUtils.nowTime());
     *         } catch (ParseException e) {
     *             e.printStackTrace();
     *         }
     **/
    public static Date nowTime() throws ParseException {
        //获得系统时间.
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String nowTime = sdf.format(date);
        Date time = sdf.parse(nowTime);
        return time;
    }

}
