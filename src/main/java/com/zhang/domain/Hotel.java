package com.zhang.domain;

public class Hotel {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelPrice() {
        return hotelPrice;
    }

    public void setHotelPrice(String hotelPrice) {
        this.hotelPrice = hotelPrice;
    }

    public String getHotelLabel() {
        return hotelLabel;
    }

    public void setHotelLabel(String hotelLabel) {
        this.hotelLabel = hotelLabel;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", bid=" + bid +
                ", gid='" + gid + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", hotelPrice='" + hotelPrice + '\'' +
                ", hotelLabel='" + hotelLabel + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", hotelStar='" + hotelStar + '\'' +
                '}';
    }

    private int id;
    private int bid;
    private String gid;
    private String hotelName;
    private String hotelPrice;
    private String hotelLabel;
    private String hotelAddress;
    private String hotelStar;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private String time;

}
