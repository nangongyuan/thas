package com.yuan.socket;

import com.yuan.dao.HumidTemperDao;
import com.yuan.dao.NodeDao;
import com.yuan.service.GetwayService;
import com.yuan.service.SocketService;
import com.yuan.socket.entity.Getway;
import com.yuan.socket.entity.MsgInfo;
import com.yuan.socket.entity.Node;
import java.io.IOException;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class ReceiveThread implements Runnable {
    private NodeDao nodeDao ;
    private boolean isStop;
    private Getway getway;
    private HumidTemperDao humidTemperDao ;
    private GetwayService getwayService;

    public ReceiveThread(Getway getway,SocketService socketService,GetwayService getwayService) throws SocketException {
        this.getway = getway;
        isStop=false;
        getway.getSocket().setSoTimeout(20000);
        nodeDao = socketService.getNodeDao();
        humidTemperDao = socketService.getHumidTemperDao();
        this.getwayService = getwayService;
    }

    public void run() {
        while (!isStop && !getway.getSocket().isClosed()) {
            try {
                if(getway.getInputStream().available()>0){
                    byte[] buf = new byte[getway.getInputStream().available()];
                    getway.getInputStream().read(buf);
                    splitPackage(buf);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private void splitPackage(byte[] data) throws SocketException {
        int start = 0;
        for (int i=0;i<data.length;i++){
            if (data[i]==0x7d){
                doMessage(Arrays.copyOfRange(data,start,i+1));
                start = i+1;
            }
        }
    }
    private void doMessage(byte[] message) throws SocketException {
        System.out.println("处理后的数据："+ SocketUtils.BinaryToHexString(message));
        String crc16 = SocketUtils.getCRC16(Arrays.copyOfRange(message,1,message.length-3)).toUpperCase();
        //System.out.println("crc:"+ SocketUtils.BinaryToHexString(Arrays.copyOfRange(message,message.length-3,message.length-1)));
        if (!crc16.equals(SocketUtils.BinaryToHexString(Arrays.copyOfRange(message,message.length-3,message.length-1)))){
            throw new SocketException("CRC校验错误");
        }
        String cla = SocketUtils.getCLA(message);
        System.out.println("cla:"+cla);
        if (cla.equals("wd")){
            doWD(SocketUtils.getData(message));
        }else if (cla.equals("xt")){             //心跳包
            doXT(SocketUtils.getData(message));
        }else if (cla.equals("cq")){
            doCQ();
        }else if (cla.equals("sb")){
            doSB();
        }else if (cla.equals("by")){
            doBY();
        }else if (cla.equals("cx")){
            doCX(SocketUtils.getData(message));
        }
    }


    // 7B 77 64 08 05 01 02 06 04 01 00 00 01 00 17 2B 22 85 4E 7D
    private void doWD(byte[] data){   //自动上传的温度
        String getwayNode= SocketUtils.BinaryToHexString(Arrays.copyOfRange(data,0,6));
        System.out.println("自动上传温度网关号："+getwayNode);
        byte count = data[6];
        byte gTemper = data[7];
        byte gHumid = data[8];
        byte nodeNum = data[9];
        byte backNode = data[10];
        byte temper = data[11];
        byte humid = data[12];
        byte power = data[13];
        getway.setNodeCount(count);
        if (getway.getNodeMark()==null || getway.getNodeMark().equals("")){
            com.yuan.entity.Node eNode = nodeDao.getNodeByNodeNum(getwayNode);
            if (eNode==null){
                System.out.println("该网关在数据库中不存在"+getwayNode);
                return;
            }
            getway.setNodeNum(String.valueOf(getwayNode));
            getway.setNodeMark(eNode.getNodeMark());
            getway.setGetwayId(eNode.getGetwayId());
        }
        Node node = getway.getNodeMap().get(nodeNum);
        if (node==null){
            node = new Node();
            node.setNodeNum(nodeNum);
            node.addTh(temper,humid);
            com.yuan.entity.Node eNode = nodeDao.getNodeByGetwayIdNodeNum(getway.getGetwayId(), String.valueOf(nodeNum));
            if (eNode==null){
                System.out.println("该节点在数据库中不存在"+nodeNum);
                return;
            }
            node.setNodeMark(eNode.getNodeMark());
            node.setNodeId(eNode.getNodeId());
            node.setSpareNode(backNode);
            node.setPower(power);
            getway.getNodeMap().put((int) nodeNum,node);
        }else {
            node.addTh(temper,humid);
            node.setSpareNode(backNode);
            node.setPower(power);
        }
        getway.addTh(gTemper,gHumid);
        String crc = SocketUtils.getCRC16("WD".getBytes());
        String temp="{"+"WD"+crc+"}";
        SocketUtils.sendString(temp,getway.getOutputStream());
    }



    //7b7874080501020604991d7d
    private void doXT(byte[] data){
        String NodeNum = SocketUtils.BinaryToHexString(data);
        System.out.println("心跳包："+NodeNum);
        String crc = SocketUtils.getCRC16("XT".getBytes());
        String temp="{"+"XT"+crc+"}";
        SocketUtils.sendString(temp,getway.getOutputStream());

    }

    private void doCQ(){
        System.out.println("收到重启响应");
        List<MsgInfo> list = LoopThread.getMsgInfoList();
        for (Iterator<MsgInfo> it = list.iterator(); it.hasNext();){
            MsgInfo msgInfo = it.next();
            if (msgInfo.getType()==1 && msgInfo.getGetwayMark().equals(getway.getNodeMark())){
                msgInfo.setSuccess(true);
            }
        }
    }
    private void doSB(){
        System.out.println("收到间隔响应");
        List<MsgInfo> list = LoopThread.getMsgInfoList();
        for (Iterator<MsgInfo> it = list.iterator(); it.hasNext();){
            MsgInfo msgInfo = it.next();
            if (msgInfo.getType()==2 && msgInfo.getGetwayMark().equals(getway.getNodeMark())){
                msgInfo.setSuccess(true);
                //getwayService.setGetwayTimeInter(msgInfo.getGetwayMark(), (Integer) msgInfo.getParameter());
            }
        }
    }
    private void doBY(){

    }

    private void doCX(byte[] data) {
        int status = data[0];
        int nodeNum = data[1];
        List<MsgInfo> list = LoopThread.getMsgInfoList();
        for (Iterator<MsgInfo> it = list.iterator(); it.hasNext();){
            MsgInfo msgInfo = it.next();
            if (msgInfo.getType()==3 && msgInfo.getGetwayMark().equals(getway.getNodeMark())){
                msgInfo.setSuccess(true);
                Node node = getway.getNodeMap().get(nodeNum);
                getwayService.setNodeStatus(node.getNodeMark(),status);
            }
        }
    }
}
