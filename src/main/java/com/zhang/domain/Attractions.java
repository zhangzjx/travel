package com.zhang.domain;

import java.sql.Time;

/**
 * @author Administrator
 */
public class Attractions {
    private int spId;
    private int uId;
    private String attractionsName;
    private String attractionsPrice;
    private String attractionsLabel;
    private String attractionsAddress;
    private String attractionsPhone;
    private String attractionsInf;
    private String time;
    private String status;
    private String timeStart;
    private String timeEnd;
    private String attractionsStar;
    private String entryTime;
    private int quantity;
    private double price;

    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getAttractionsName() {
        return attractionsName;
    }

    public void setAttractionsName(String attractionsName) {
        this.attractionsName = attractionsName;
    }

    public String getAttractionsPrice() {
        return attractionsPrice;
    }

    public void setAttractionsPrice(String attractionsPrice) {
        this.attractionsPrice = attractionsPrice;
    }

    public String getAttractionsLabel() {
        return attractionsLabel;
    }

    public void setAttractionsLabel(String attractionsLabel) {
        this.attractionsLabel = attractionsLabel;
    }

    public String getAttractionsAddress() {
        return attractionsAddress;
    }

    public void setAttractionsAddress(String attractionsAddress) {
        this.attractionsAddress = attractionsAddress;
    }

    public String getAttractionsPhone() {
        return attractionsPhone;
    }

    public void setAttractionsPhone(String attractionsPhone) {
        this.attractionsPhone = attractionsPhone;
    }

    public String getAttractionsInf() {
        return attractionsInf;
    }

    public void setAttractionsInf(String attractionsInf) {
        this.attractionsInf = attractionsInf;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getAttractionsStar() {
        return attractionsStar;
    }

    public void setAttractionsStar(String attractionsStar) {
        this.attractionsStar = attractionsStar;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Attractions{" +
                "spId=" + spId +
                ", uId=" + uId +
                ", attractionsName='" + attractionsName + '\'' +
                ", attractionsPrice='" + attractionsPrice + '\'' +
                ", attractionsLabel='" + attractionsLabel + '\'' +
                ", attractionsAddress='" + attractionsAddress + '\'' +
                ", attractionsPhone='" + attractionsPhone + '\'' +
                ", attractionsInf='" + attractionsInf + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", attractionsStar='" + attractionsStar + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }



}
