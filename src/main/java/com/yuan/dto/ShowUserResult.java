package com.yuan.dto;

public class ShowUserResult {
    private int userId;
    private String Name;
    private int userType;
    private String unitTitle;
    private String memo;
    private int unitId;


    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "ShowUserResult{" +
                "userId=" + userId +
                ", Name='" + Name + '\'' +
                ", userType=" + userType +
                ", unitTitle='" + unitTitle + '\'' +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }
}
