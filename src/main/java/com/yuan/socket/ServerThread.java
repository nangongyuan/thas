package com.yuan.socket;

import com.yuan.service.GetwayService;
import com.yuan.service.SocketService;
import com.yuan.socket.entity.Getway;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ServerThread extends Thread{
    private static final int port=1234;
    private ServerSocket serverSocket ;
    private boolean isStop;
    private static List<Getway> getwayList = new ArrayList<Getway>();
    private ExecutorService executor = Executors.newCachedThreadPool();
    private SocketService socketService;
    private GetwayService getwayService;
    public static List<Getway> getGetwayList() {
        return getwayList;
    }

    public ServerThread(SocketService socketService,GetwayService getwayService) throws IOException {
        serverSocket = new ServerSocket(port);
        isStop=true;
        this.socketService = socketService;
        this.getwayService = getwayService;
    }
    @Override
    public void run() {     //监听
        isStop=false;
        while (!isStop && !serverSocket.isClosed()) {
            try {
                Getway getway = new Getway();
                getway.setSocket(serverSocket.accept());
                getway.setInputStream(getway.getSocket().getInputStream());
                getway.setOutputStream(getway.getSocket().getOutputStream());
                executor.execute(new ReceiveThread(getway,socketService,getwayService));
                getwayList.add(getway);
                System.out.println("客户端连入:"+getway.getSocket().getInetAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }
}
