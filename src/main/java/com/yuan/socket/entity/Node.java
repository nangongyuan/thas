package com.yuan.socket.entity;

import com.yuan.service.HumidTemperService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Node {
    private List<TH> thList=new ArrayList<TH>();
    private int nodeNum;
    private String nodeMark;
    private int spareNode;
    private int power;
    private int status;
    private int nodeId;
    private double temper;
    private double humidity;

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

    public int getNodeId() {
        return nodeId;
    }

    public void setNodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSpareNode() {
        return spareNode;
    }

    public void setSpareNode(int spareNode) {
        this.spareNode = spareNode;
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

    public int getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(int nodeNum) {
        this.nodeNum = nodeNum;
    }

    public List<TH> getThList() {
        return thList;
    }

    public void setThList(List<TH> thList) {
        this.thList = thList;
    }
    public synchronized void  addTh(double t, double h){
        if (thList.size()==0 || t!=thList.get(thList.size()-1).getTemper() || h!=thList.get(thList.size()-1).getHumidity()){
            thList.add(new TH(t,h,new Date()));
        }
        temper = t;
        humidity = h;
    }
    public synchronized void saveRemoveTH(HumidTemperService humidTemperService){
        if (thList.size()>=10){
            System.out.println("保存节点温湿度数据");
            for (TH th : thList){
                humidTemperService.insertHumidTemper(nodeMark,th.getHumidity(),th.getTemper(),2,th.getDate());
            }
            thList.clear();
        }
    }
}
