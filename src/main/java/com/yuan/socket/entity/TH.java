package com.yuan.socket.entity;

import java.util.Date;

public class TH {
    private double temper;
    private double humidity;
    private Date date;

    public TH() {
    }

    public TH(double temper, double humidity, Date date) {
        this.temper = temper;
        this.humidity = humidity;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
