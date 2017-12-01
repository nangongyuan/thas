package com.yuan.entity;

public class UserInfo {
    private int userId;
    private int unitId;
    private String name;
    private int userType;
    private String userName;
    private String password;
    private String memo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", unitId=" + unitId +
                ", name='" + name + '\'' +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    public UserInfo() {
    }

    public UserInfo(int unitId, String name, int userType, String userName, String password, String memo) {
        this.unitId = unitId;
        this.name = name;
        this.userType = userType;
        this.userName = userName;
        this.password = password;
        this.memo = memo;
    }
}
