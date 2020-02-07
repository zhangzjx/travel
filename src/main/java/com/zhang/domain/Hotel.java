package com.zhang.domain;

import com.zhang.utils.DateUtils;

/**
 * @author Administrator
 */
public class Hotel {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
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

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(String hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getHotelContent() {
        return hotelContent;
    }

    public void setHotelContent(String hotelContent) {
        this.hotelContent = hotelContent;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPriceRoom() {
        return priceRoom;
    }

    public void setPriceRoom(String priceRoom) {
        this.priceRoom = priceRoom;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", hotelPrice='" + hotelPrice + '\'' +
                ", hotelLabel='" + hotelLabel + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", hotelPhone='" + hotelPhone + '\'' +
                ", hotelStar='" + hotelStar + '\'' +
                ", hotelContent='" + hotelContent + '\'' +
                ", entryTime='" + entryTime + '\'' +
                ", roomStandard='" + roomStandard + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
                ", priceRoom='" + priceRoom + '\'' +
                '}';
    }

    private int id;
    private int roomId;
    private int hotelId;
    private String hotelName;
    private String hotelPrice;
    private String hotelLabel;
    private String hotelAddress;
    private String hotelPhone;
    private String hotelStar;
    private String hotelContent;
    private String entryTime;
    private String roomStandard;
    private String roomNumber;
    private String priceRoom;
}
