package com.yuan.service;

import com.yuan.dao.GetwayDao;
import com.yuan.dao.HumidTemperDao;
import com.yuan.dao.NodeDao;
import com.yuan.dto.GetwayResult;
import com.yuan.dto.QueryGetwayResult;
import com.yuan.dto.QueryNodeResult;
import com.yuan.dto.Result;
import com.yuan.entity.Getway;
import com.yuan.entity.UserInfo;
import com.yuan.socket.ServerThread;
import com.yuan.socket.SocketUtils;
import com.yuan.socket.ServerMain;
import com.yuan.socket.entity.MsgInfo;
import com.yuan.socket.entity.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocketService {
    @Autowired
    private NodeDao nodeDao;
    @Autowired
    private HumidTemperDao humidTemperDao;
    @Autowired
    private GetwayDao getwayDao;
    @Autowired
    private ServerMain serverMain;

    public NodeDao getNodeDao() {
        return nodeDao;
    }

    public HumidTemperDao getHumidTemperDao() {
        return humidTemperDao;
    }


    public Result reloadGetway(String getwayMark) {
        String crc = SocketUtils.getCRC16("CQ".getBytes());
        String temp = "{" + "CQ" + crc + "}";
        MsgInfo msgInfo = new MsgInfo(0, temp.getBytes(), false, getwayMark, 1, null);
        serverMain.getLoopThread().getMsgInfoList().add(msgInfo);
        return new Result(true, "");
    }

    public Result setReportInterval(String getwayMark, int interval) {
        byte[] bytes = new byte[8];
        bytes = SocketUtils.intToByteArray(interval);
        bytes[0] = 0x7B;
        bytes[4] = bytes[3];
        bytes[3] = bytes[2];
        bytes[1] = 0x53;
        bytes[2] = 0x43;
        String crc = SocketUtils.getCRC16(Arrays.copyOfRange(bytes, 1, 5));
        bytes[5] = crc.getBytes()[0];
        bytes[6] = crc.getBytes()[1];
        bytes[7] = 0x7D;
        MsgInfo msgInfo = new MsgInfo(0, bytes, false, getwayMark, 2, interval);
        serverMain.getLoopThread().getMsgInfoList().add(msgInfo);
        return new Result(true, "");
    }

    public Result queryTH(String getwayMark, int type) {//1查询温度  2查询手自动
        byte[] bytes = new byte[1];
        bytes[0] = 0x7B;
        bytes = SocketUtils.linkBytes(bytes, "CX".getBytes());
        if (type == 1)
            bytes = SocketUtils.linkBytes(bytes, new byte[]{0x01});
        else
            bytes = SocketUtils.linkBytes(bytes, new byte[]{0x02});
        String crc = SocketUtils.getCRC16(Arrays.copyOfRange(bytes, 1, 4));
        bytes = SocketUtils.linkBytes(bytes, crc.getBytes());
        bytes = SocketUtils.linkBytes(bytes, new byte[]{0x7d});
        MsgInfo msgInfo = new MsgInfo(0, bytes, false, getwayMark, 3, null);
        serverMain.getLoopThread().getMsgInfoList().add(msgInfo);
        return new Result(true, null);
    }

    public void setAuto(String getwayMark, int nodeNum, int type, int flag, int isStopA, int isStopB, byte a, byte b) {  //1手动  2自动
        byte[] bytes = new byte[1];
        bytes[0] = 0x7B;
        bytes = SocketUtils.linkBytes(bytes, "ST".getBytes());
        bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) nodeNum});
        if (type == 1) {
            if (flag == 1)  // 1关闭 2开启
                bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0xff});
            else
                bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0x0f, (byte) 0xff, (byte) 0xff, (byte) 0xff});
        } else {
            if (flag == 1)  //温度 1  湿度2
                bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0xf0});
            else
                bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0xff});
            if (a == 1) //a = 1 启动    2停止
                if (b == 1)
                    bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0xff});
                else
                    bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0xf0});
            else if (b == 1)
                bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0x0f});
            else
                bytes = SocketUtils.linkBytes(bytes, new byte[]{(byte) 0x00});

            bytes = SocketUtils.linkBytes(bytes, new byte[]{a, b});
        }
        String crc = SocketUtils.getCRC16(Arrays.copyOfRange(bytes, 1, 8));
        bytes = SocketUtils.linkBytes(bytes, crc.getBytes());
        bytes = SocketUtils.linkBytes(bytes, new byte[]{0x7d});
        MsgInfo msgInfo = new MsgInfo(0, bytes, false, getwayMark, 4, null);
        serverMain.getLoopThread().getMsgInfoList().add(msgInfo);
    }

    public Result isReloadGetway(String getwayMark) {
        Result result = new Result();
        List<MsgInfo> list = serverMain.getLoopThread().getMsgInfoList();
        for (Iterator<MsgInfo> it = list.iterator(); it.hasNext(); ) {
            MsgInfo msgInfo = it.next();
            if (msgInfo.getGetwayMark().equals(getwayMark) && msgInfo.getType() == 1) {
                result.setSucceed(msgInfo.getSuccess());
            }
        }
        return result;
    }

    public Result issetReportInterval(String getwayMark) {
        Result result = new Result();
        List<MsgInfo> list = serverMain.getLoopThread().getMsgInfoList();
        for (Iterator<MsgInfo> it = list.iterator(); it.hasNext(); ) {
            MsgInfo msgInfo = it.next();
            if (msgInfo.getGetwayMark().equals(getwayMark) && msgInfo.getType() == 2) {
                result.setSucceed(msgInfo.getSuccess());
            }
        }
        return result;
    }


    public List<QueryGetwayResult> getRTInfo(UserInfo userInfo) {
        List<com.yuan.socket.entity.Getway> list = ServerThread.getGetwayList();  //获取在线网关
        List<QueryGetwayResult> resultList = new ArrayList<QueryGetwayResult>();   //返回结果的变量
        if (userInfo.getUserType() == 2 || userInfo.getUserType() == 3) {   //单位管理员和特权管理员
            List<GetwayResult> getwayList = getwayDao.getGetWayByUnitId(userInfo.getUnitId(), 1, 10000000);//该单位下的所有网关
            for (GetwayResult getwayResult : getwayList) {  //该单位下的所有网关
                QueryGetwayResult result = new QueryGetwayResult();
                for (int i = 0; i < list.size(); i++) {                     //在线的所有网关
                    com.yuan.socket.entity.Getway temp = list.get(i);
                    if (getwayResult.getGetwayMark().equals(temp.getNodeMark())) {   //在线的里面如果有单位网关
                        result.setTemper(temp.getTemper());
                        result.setHumidity(temp.getHumidity());
                        resultList.add(result);
                        Set<Integer> keys = temp.getNodeMap().keySet();
                        for (Iterator<Integer> it = keys.iterator(); it.hasNext(); ) {
                            Node node = temp.getNodeMap().get(it.next());
                            QueryNodeResult nodeResult = new QueryNodeResult();
                            nodeResult.setHumidity(node.getHumidity());
                            nodeResult.setTemper(node.getTemper());
                            nodeResult.setPower(node.getPower());
                            nodeResult.setStatus(node.getStatus());
                            result.getQueryNodeResultList().add(nodeResult);
                        }
                    }
                }
            }
        }
        return resultList;
    }

    public Result setNodeAutoStatus(String getwayMark) {
        return null;
    }
}
