package com.yuan.dto;

public class QueryNodeResult {
    private String nodeMark;

    private int status;
    private double temper;
    private double humidity;
    private int power;

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

    public String getNodeMark() {
        return nodeMark;
    }

    public void setNodeMark(String nodeMark) {
        this.nodeMark = nodeMark;
    }
}
