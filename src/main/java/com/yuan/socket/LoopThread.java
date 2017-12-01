package com.yuan.socket;

import com.yuan.service.GetwayService;
import com.yuan.service.HumidTemperService;
import com.yuan.socket.entity.Getway;
import com.yuan.socket.entity.MsgInfo;
import com.yuan.socket.entity.Node;
import com.yuan.socket.entity.TH;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LoopThread extends Thread {
    private static List<MsgInfo> msgInfoList = new ArrayList<MsgInfo>();
    private HumidTemperService humidTemperService;

    public LoopThread(HumidTemperService humidTemperService,GetwayService getwayService) {
        this.humidTemperService = humidTemperService;
    }

    public static List<MsgInfo> getMsgInfoList() {
        return msgInfoList;
    }

    public void setMsgInfoList(List<MsgInfo> msgInfoList) {
        this.msgInfoList = msgInfoList;
    }

    @Override
    public void run() {
        while (true){
            //checkOnline();
            for (Iterator<MsgInfo> it = msgInfoList.iterator();it.hasNext();){
                MsgInfo msgInfo = it.next();
                if (msgInfo.getMsgId()<3 && !msgInfo.getSuccess()){
                    sendMsg(msgInfo);
                }
                msgInfo.setMsgId(msgInfo.getMsgId()+1);
                if (msgInfo.getMsgId()>10){
                    System.out.println("把消息从消息队列中移除");
                    it.remove();
                }
            }
            saveData();
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Boolean isServerClose(Socket socket){
        try{
            socket.sendUrgentData(0);//发送1个字节的紧急数据，默认情况下，服务器端没有开启紧急数据处理，不影响正常通信
            return false;
        }catch(Exception se){
            System.out.println("连接关闭");
            return true;
        }
    }

    private void checkOnline(){
        List<Getway> list = ServerThread.getGetwayList();
        for (Iterator<Getway> it=list.iterator() ;it.hasNext(); ){
            if (isServerClose(it.next().getSocket())){
                it.remove();
                System.out.println("移除socket");
            }
        }
    }
    private void sendMsg(MsgInfo msgInfo){
        String getwayMark = msgInfo.getGetwayMark();
        List<Getway> list = ServerThread.getGetwayList();
        for (int i=0;i<list.size();i++){
            Getway getway = list.get(i);
            if (getway.getNodeMark().equals(getwayMark)){
                System.out.println("发送数据");
                SocketUtils.sendBytes(msgInfo.getData(),getway.getOutputStream());
            }
        }
    }
    private void saveData(){
        List<Getway> getways = ServerThread.getGetwayList();
        for (int i=0;i<getways.size();i++){
            Getway getway = getways.get(i);
            Set<Integer> keys = getway.getNodeMap().keySet();
            for (Iterator<Integer> it = keys.iterator();it.hasNext(); ){
                Node node = getway.getNodeMap().get(it.next());
                node.saveRemoveTH(humidTemperService);

            }
            getway.saveRemoveTH(humidTemperService);
        }

    }

}
