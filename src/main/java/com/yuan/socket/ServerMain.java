package com.yuan.socket;

import com.yuan.service.GetwayService;
import com.yuan.service.HumidTemperService;
import com.yuan.service.SocketService;
import java.io.IOException;
import java.net.ServerSocket;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ServerMain implements ApplicationListener<ContextRefreshedEvent>{
    private ServerThread serverThread = null;
    private LoopThread loopThread;

    public LoopThread getLoopThread() {
        return loopThread;
    }

    @Autowired
    private SocketService socketService;
    @Autowired
    private HumidTemperService humidTemperService;
    @Autowired
    private GetwayService getwayService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        if (serverThread==null){
//            try {
//                serverThread = new ServerThread(socketService,getwayService);
//                serverThread.start();
//                loopThread = new LoopThread(humidTemperService,getwayService);
//                loopThread.start();
//                System.out.println("Socket线程启动成功");
//            } catch (IOException e) {
//                System.out.println("Socket线程启动失败");
//                e.printStackTrace();
//            }
//        }
    }

    public ServerThread getServerThread() {
        return serverThread;
    }
}
