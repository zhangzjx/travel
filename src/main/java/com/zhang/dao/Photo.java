package com.zhang.dao;

import java.sql.Time;
import java.util.Date;

/**
 * @author Administrator
 */
public class Photo {


    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getFileTime() {
        return fileTime;
    }

    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    public Time getcTime() {
        return cTime;
    }

    public void setcTime(Time cTime) {
        this.cTime = cTime;
    }

    private int aId;

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "aId=" + aId +
                ", hotel_id=" + hotel_id +
                ", hName='" + hName + '\'' +
                ", photoName='" + photoName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileTime=" + fileTime +
                ", cTime=" + cTime +
                '}';
    }

    private int hotel_id;
    private String hName;
    private String photoName;
    private String filePath;
    private Date fileTime;
    private Time cTime;



}
