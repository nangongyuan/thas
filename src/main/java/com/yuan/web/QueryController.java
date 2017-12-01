package com.yuan.web;

import com.yuan.dto.PageResult;
import com.yuan.dto.QueryGetwayResult;
import com.yuan.dto.TemperHumidityResult;
import com.yuan.entity.UserInfo;
import com.yuan.service.HumidTemperService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private HumidTemperService humidTemperService;

    private String removeIP = "101.132.163.95";

    @RequestMapping(value = "/getQueryGetway")   //获取网关和网关节点的ID信息
    @ResponseBody
    public List<QueryGetwayResult> getQueryGetway(HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return humidTemperService.getQueryGetway(userInfo);
    }

    @RequestMapping(value = "/temperHumidity")    //查询温度和湿度
    @ResponseBody
    public PageResult<TemperHumidityResult> queryTemperHumidity(String nodeMark, String startTime, String endTime, int page, int rows) {
        return humidTemperService.queryTemperHumidity(nodeMark, startTime, endTime, page, rows);
    }

    @RequestMapping(value = "/temperHumidityAvg")    //查询温度和湿度
    @ResponseBody
    public List<TemperHumidityResult> temperHumidityAvg(String nodeMark, int type, String parameter) {
        return humidTemperService.temperHumidityAvg(nodeMark, type, parameter);
    }

    @RequestMapping(value = "/TemperHumidServ")
    public void TemperHumidServ(HttpServletRequest request, HttpServletResponse response){
        String temp = sendGet("http://"+removeIP + "/TemperHumid/TemperHumidServ",request);
        try {
            response.getWriter().print(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/GetWayServ")
    public void GetWayServ(HttpServletRequest request, HttpServletResponse response){
        String temp = sendGet("http://"+removeIP + "/TemperHumid/GetWayServ",request);
        try {
            response.getWriter().print(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/NodeServ")
    public void NodeServ(HttpServletRequest request, HttpServletResponse response){
        String temp = sendGet("http://"+removeIP + "/TemperHumid/NodeServ",request);
        try {
            response.getWriter().print(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private   String sendGet(String url, HttpServletRequest request) {
        String param ="";
        Enumeration enu=request.getParameterNames();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            String para = request.getParameter(paraName);
            if (param.length()>0)
                param+="&";
            param += paraName+"="+para;
        }
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
