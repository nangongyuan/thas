package com.yuan.dto;

public class Result {
    private boolean isSucceed;
    private String msg;
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(boolean isSucceed, String msg) {
        this.isSucceed = isSucceed;
        this.msg = msg;
    }

    public Result() {
    }
}
