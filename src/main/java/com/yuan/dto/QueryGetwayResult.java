package com.yuan.dto;

import java.util.List;

public class QueryGetwayResult {
    private int getwayId;
    private String getwayMark;
    private int status;
    private double temper;
    private double humidity;
    private int power;
    private List<QueryNodeResult> queryNodeResultList;

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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
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

    public List<QueryNodeResult> getQueryNodeResultList() {
        return queryNodeResultList;
    }

    public void setQueryNodeResultList(List<QueryNodeResult> queryNodeResultList) {
        this.queryNodeResultList = queryNodeResultList;
    }

    @Override
    public String toString() {
        return "QueryGetwayResult{" +
                "getwayId=" + getwayId +
                ", getwayMark='" + getwayMark + '\'' +
                ", queryNodeResultList=" + queryNodeResultList +
                '}';
    }
}
