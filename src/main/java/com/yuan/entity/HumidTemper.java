package com.yuan.entity;

import java.util.Date;

public class HumidTemper {
    private int humidId;
    private String nodeMark;
    private double humidity;
    private double temper;
    private Date reportTime;
    private int type;

    public int getHumidId() {
        return humidId;
    }

    public void setHumidId(int humidId) {
        this.humidId = humidId;
    }

    public String getNodeMark() {
        return nodeMark;
    }

    public void setNodeMark(String nodeMark) {
        this.nodeMark = nodeMark;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
