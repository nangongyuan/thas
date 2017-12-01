package com.yuan.dto;

import java.util.Date;

public class GetwayResult {
    private int getwayId;
    private String getwayMark;
    private String spareNode;
    private String nodeNum;
    private int status;
    private double temper;
    private double humidity;
    private int timeInter;
    private String memo;
    private Date endTime;
    private int unitId;
    private String unitTitle;

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public int getGetwayId() {
        return getwayId;
    }

    public void setGetwayId(int getwayId) {
        this.getwayId = getwayId;
    }

    public String getGetwayMark() {
        return getwayMark;
    }

    public void setGetwayMark(String getwayMark) {
        this.getwayMark = getwayMark;
    }

    public String getSpareNode() {
        return spareNode;
    }

    public void setSpareNode(String spareNode) {
        this.spareNode = spareNode;
    }

    public String getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(String nodeNum) {
        this.nodeNum = nodeNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getTemper() {
        return temper;
    }

    public void setTemper(double temper) {
        this.temper = temper;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getTimeInter() {
        return timeInter;
    }

    public void setTimeInter(int timeInter) {
        this.timeInter = timeInter;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "GetwayResult{" +
                "getwayId=" + getwayId +
                ", getwayMark='" + getwayMark + '\'' +
                ", spareNode='" + spareNode + '\'' +
                ", nodeNum='" + nodeNum + '\'' +
                ", status=" + status +
                ", temper=" + temper +
                ", humidity=" + humidity +
                ", timeInter=" + timeInter +
                ", memo='" + memo + '\'' +
                ", endTime=" + endTime +
                ", unitId=" + unitId +
                ", unitTitle='" + unitTitle + '\'' +
                '}';
    }
}
