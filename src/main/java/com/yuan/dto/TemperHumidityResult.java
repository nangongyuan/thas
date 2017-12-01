package com.yuan.dto;

import java.util.Date;

public class TemperHumidityResult {
    private String nodeMark;
    private double temper;
    private double humidity;
    private Date reportTime;
    private int type;
    private String getWay;

    public String getGetWay() {
        return getWay;
    }

    public void setGetWay(String getWay) {
        this.getWay = getWay;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getNodeMark() {
        return nodeMark;
    }

    public void setNodeMark(String nodeMark) {
        this.nodeMark = nodeMark;
    }

    public double getTemper() {
        return temper;
    }

    public void setTemper(double temper) {
        this.temper = temper;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public String toString() {
        return "TemperHumidityResult{" +
                "nodeMark='" + nodeMark + '\'' +
                ", temper=" + temper +
                ", humidity=" + humidity +
                ", reportTime=" + reportTime +
                ", type=" + type +
                ", getWay='" + getWay + '\'' +
                '}';
    }
}
