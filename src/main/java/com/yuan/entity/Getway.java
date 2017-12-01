package com.yuan.entity;

import java.util.ArrayList;
import java.util.List;

public class Getway {
    private int getwayId;
    private String getwayMark;
    private String spareNode;
    private String nodeNum;
    private int status;
    private double temper;
    private double humidity;
    private int timeInter;
    private String memo;

    public Getway(int getwayId, String getwayMark, String spareNode, String nodeNum, int status, double temper, double humidity, int timeInter, String memo) {
        this.getwayId = getwayId;
        this.getwayMark = getwayMark;
        this.spareNode = spareNode;
        this.nodeNum = nodeNum;
        this.status = status;
        this.temper = temper;
        this.humidity = humidity;
        this.timeInter = timeInter;
        this.memo = memo;
    }

    public Getway() {
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

    @Override
    public String toString() {
        return "Getway{" +
                "getwayId=" + getwayId +
                ", getwayMark='" + getwayMark + '\'' +
                ", spareNode=" + spareNode +
                ", nodeNum='" + nodeNum + '\'' +
                ", status=" + status +
                ", temper=" + temper +
                ", humidity=" + humidity +
                ", timeInter=" + timeInter +
                ", memo='" + memo + '\'' +
                '}';
    }
}
