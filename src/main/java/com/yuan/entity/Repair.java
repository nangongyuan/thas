package com.yuan.entity;

import java.util.Date;

public class Repair {
    private int repairId;
    private String nodeMark;
    private String faultDesc;
    private String phone;
    private String person;
    private String address;
    private String faultTime;
    private int status;

    public int getRepairId() {
        return repairId;
    }

    public void setRepairId(int repairId) {
        this.repairId = repairId;
    }

    public String getNodeMark() {
        return nodeMark;
    }

    public void setNodeMark(String nodeMark) {
        this.nodeMark = nodeMark;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFaultTime() {
        return faultTime;
    }

    public void setFaultTime(String faultTime) {
        this.faultTime = faultTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairId=" + repairId +
                ", nodeMark='" + nodeMark + '\'' +
                ", faultDesc='" + faultDesc + '\'' +
                ", phone='" + phone + '\'' +
                ", person='" + person + '\'' +
                ", address='" + address + '\'' +
                ", faultTime=" + faultTime +
                ", status=" + status +
                '}';
    }
}
