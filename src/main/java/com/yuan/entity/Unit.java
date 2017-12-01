package com.yuan.entity;

public class Unit {
    private int unitId;
    private String title;
    private String address;
    private String phone;
    private String person;
    private String email;
    private int unitType;
    private String memo;

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "unitId=" + unitId +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", person='" + person + '\'' +
                ", email='" + email + '\'' +
                ", unitType=" + unitType +
                ", memo='" + memo + '\'' +
                '}';
    }
}
