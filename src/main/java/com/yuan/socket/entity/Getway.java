package com.yuan.socket.entity;

import com.yuan.service.HumidTemperService;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Getway {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private Map<Integer,Node> nodeMap=new HashMap<Integer, Node>();
    private String nodeMark;
    private String nodeNum;
    private int nodeCount;
    private int getwayId;
    private List<TH> thList=new ArrayList<TH>();
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

    public int getGetwayId() {
        return getwayId;
    }

    public void setGetwayId(int getwayId) {
        this.getwayId = getwayId;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(int nodeCount) {
        this.nodeCount = nodeCount;
    }

    public List<TH> getThList() {
        return thList;
    }

    public void setThList(List<TH> thList) {
        this.thList = thList;
    }

    public String getNodeNum() {
        return nodeNum;
    }

    public void setNodeNum(String nodeNum) {
        this.nodeNum = nodeNum;
    }

    public String getNodeMark() {
        return nodeMark;
    }

    public void setNodeMark(String nodeMark) {
        this.nodeMark = nodeMark;
    }

    public Map<Integer, Node> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<Integer, Node> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public synchronized void  addTh(double t, double h){
//        if (thList.size()==0 || t!=thList.get(thList.size()-1).getTemper() || h!=thList.get(thList.size()-1).getHumidity()){
//            thList.add(new TH(t,h,new Date()));
//        }
        temper = t;
        humidity = h;
        thList.add(new TH(t,h,new Date()));
    }
    public synchronized void saveRemoveTH(HumidTemperService humidTemperService){
        if (thList.size()>=10){
            System.out.println("保存网关温湿度数据");
            for (TH th : thList){
                humidTemperService.insertHumidTemper(nodeMark,th.getHumidity(),th.getTemper(),1,th.getDate());
            }
            thList.clear();
        }
    }
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
