package com.yuan.entity;

import java.util.Date;

public class RentInfo {
    private int rentId;
    private int getwayId;
    private int unitId;
    private String startTime;
    private String endTime;
    private double pay;

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public int getGetwayId() {
        return getwayId;
    }

    public void setGetwayId(int getwayId) {
        this.getwayId = getwayId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }


    @Override
    public String toString() {
        return "RentInfo{" +
                "rentId=" + rentId +
                ", getwayId=" + getwayId +
                ", unitId=" + unitId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pay=" + pay +
                '}';
    }
}
