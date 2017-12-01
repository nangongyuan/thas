package com.yuan.socket.entity;

import java.util.Date;

public class MsgInfo {
    private int msgId;
    private byte[] data;
    private Boolean isSuccess;
    private String getwayMark;
    private int type;   //消息类型  重启1
    private Object parameter;
    private Object result1;
    private Object result2;

    public MsgInfo(int msgId, byte[] data, Boolean isSuccess, String getwayMark, int type, Object parameter) {
        this.msgId = msgId;
        this.data = data;
        this.isSuccess = isSuccess;
        this.getwayMark = getwayMark;
        this.type = type;
        this.parameter = parameter;
    }

    public Object getResult1() {
        return result1;
    }

    public void setResult1(Object result1) {
        this.result1 = result1;
    }

    public Object getResult2() {
        return result2;
    }

    public void setResult2(Object result2) {
        this.result2 = result2;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGetwayMark() {
        return getwayMark;
    }

    public void setGetwayMark(String getwayMark) {
        this.getwayMark = getwayMark;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}
