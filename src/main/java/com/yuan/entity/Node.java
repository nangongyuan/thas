package com.yuan.entity;

public class Node {
    private int nodeId;
    private String nodeMark;
    private int getwayId;
    private String spareNode;
    private String nodeNum;
    private int type;
    private int status;
    private double temper;
    private double humidity;
    private String memo;

    public Node() {
    }

    public Node(int nodeId, String nodeMark, int getwayId, String spareNode, String nodeNum, int type, int status, double temper, double humidity, String memo) {
        this.nodeId = nodeId;
        this.nodeMark = nodeMark;
        this.getwayId = getwayId;
        this.spareNode = spareNode;
        this.nodeNum = nodeNum;
        this.type = type;
        this.status = status;
        this.temper = temper;
        this.humidity = humidity;
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeId=" + nodeId +
                ", nodeMark='" + nodeMark + '\'' +
                ", getWayId=" + getwayId +
                ", spareNode=" + spareNode +
                ", nodeNum='" + nodeNum + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", temper=" + temper +
                ", humidity=" + humidity +
                ", memo='" + memo + '\'' +
                '}';
    }

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeMark() {
        return nodeMark;
    }

    public void setNodeMark(String nodeMark) {
        this.nodeMark = nodeMark;
    }

    public int getGetwayId() {
        return getwayId;
    }

    public void setGetwayId(int getwayId) {
        this.getwayId = getwayId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
