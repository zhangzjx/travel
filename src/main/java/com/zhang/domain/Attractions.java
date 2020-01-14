package com.zhang.domain;

public class Attractions {


    public int getSpId() {
        return spId;
    }

    public void setSpId(int spId) {
        this.spId = spId;
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

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getAttractionsStar() {
        return attractionsStar;
    }

    public void setAttractionsStar(String attractionsStar) {
        this.attractionsStar = attractionsStar;
    }

    @Override
    public String toString() {
        return "Attractions{" +
                "spId=" + spId +
                ", attractionsName='" + attractionsName + '\'' +
                ", attractionsPrice='" + attractionsPrice + '\'' +
                ", attractionsLabel='" + attractionsLabel + '\'' +
                ", attractionsAddress='" + attractionsAddress + '\'' +
                ", attractionsPhone='" + attractionsPhone + '\'' +
                ", attractionsInf='" + attractionsInf + '\'' +
                ", StartTime='" + StartTime + '\'' +
                ", EndTime='" + EndTime + '\'' +
                ", attractionsStar='" + attractionsStar + '\'' +
                '}';
    }

    private int spId;
    private String attractionsName;
    private String attractionsPrice;
    private String attractionsLabel;
    private String attractionsAddress;
    private String attractionsPhone;
    private String attractionsInf;
    private String StartTime;
    private String EndTime;
    private String attractionsStar;

}
