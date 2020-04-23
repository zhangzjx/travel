package com.zhang.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author prayers
 * @date 2020/1/14 20:49
 */
public class User {


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLasttime() {
        return lasttime;
    }

    public void setLasttime(Date lasttime) {
        this.lasttime = lasttime;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getSessionIdString() {
        return sessionIdString;
    }

    public void setSessionIdString(String sessionIdString) {
        this.sessionIdString = sessionIdString;
    }

    public String getIpString() {
        return ipString;
    }

    public void setIpString(String ipString) {
        this.ipString = ipString;
    }

    public String getFirsttTimeString() {
        return firsttTimeString;
    }

    public void setFirsttTimeString(String firsttTimeString) {
        this.firsttTimeString = firsttTimeString;
    }

    /**用户id*/
    private int uid;
    /**状态码*/
    private int status;
    private String phone;
    private String name;

    private String sex;
    private String password;

    public int getScenicspot_id() {
        return scenicspot_id;
    }

    public void setScenicspot_id(int scenicspot_id) {
        this.scenicspot_id = scenicspot_id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", scenicspot_id=" + scenicspot_id +
                ", sort='" + sort + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", email='" + email + '\'' +
                ", lasttime=" + lasttime +
                ", entryTime='" + entryTime + '\'' +
                ", sessionIdString='" + sessionIdString + '\'' +
                ", ipString='" + ipString + '\'' +
                ", firsttTimeString='" + firsttTimeString + '\'' +
                '}';
    }

    private int scenicspot_id;
    private String sort;


    //@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String oldPassword;
    private String email;
    private Date lasttime;
    private String entryTime;
    /**统计在线人数*/
    private String sessionIdString;
    private String ipString;
    private String firsttTimeString;

}
