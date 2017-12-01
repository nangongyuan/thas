package com.yuan.service;

import com.github.pagehelper.Page;
import com.yuan.dao.GetwayDao;
import com.yuan.dao.HumidTemperDao;
import com.yuan.dao.NodeDao;
import com.yuan.dto.PageResult;
import com.yuan.dto.QueryGetwayResult;
import com.yuan.dto.QueryNodeResult;
import com.yuan.dto.Result;
import com.yuan.dto.TemperHumidityResult;
import com.yuan.entity.HumidTemper;
import com.yuan.entity.Node;
import com.yuan.entity.UserInfo;
import java.io.PipedReader;
import java.sql.SQLSyntaxErrorException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HumidTemperService {

    @Autowired
    private HumidTemperDao humidTemperDao;
    @Autowired
    private GetwayDao getwayDao;
    @Autowired
    private NodeDao nodeDao;


    public int insertHumidTemper(String nodeMark, double humiditydouble, double temper, int type, Date date) {
        return humidTemperDao.insertHumidTemper(nodeMark, humiditydouble, temper, type, new Date());
    }

    public List<QueryGetwayResult> getQueryGetway(UserInfo userInfo) {
        int userType = userInfo.getUserType();
        List<QueryGetwayResult> list = null;
        if (userType == 0) {
            list = getwayDao.getQueryGetway();
        } else if (userType == 1 || userType == 2 || userType == 3) {
            list = getwayDao.getQueryGetwayByUnitId(userInfo.getUnitId());
        }
        for (QueryGetwayResult getway : list) {
            List<Node> nodeList = nodeDao.getNodeByGetwayId(getway.getGetwayId(), 1, 10000000);
            List<QueryNodeResult> results = new ArrayList<QueryNodeResult>();
            for (Node node : nodeList) {
                QueryNodeResult queryNodeResult = new QueryNodeResult();
                queryNodeResult.setNodeMark(node.getNodeMark());
                results.add(queryNodeResult);
            }
            getway.setQueryNodeResultList(results);
        }
        return list;
    }

    public PageResult<TemperHumidityResult> queryTemperHumidity(String nodeMark, String startTime, String endTime, int pageNum, int pageSize) {
        PageResult<TemperHumidityResult> result = new PageResult<TemperHumidityResult>();
        List<TemperHumidityResult> list = humidTemperDao.queryTemperHumidity(nodeMark, stringToDate(startTime), stringToDate(endTime), pageNum, pageSize);
        for (TemperHumidityResult temperHumidityResult : list) {
            temperHumidityResult.setGetWay(temperHumidityResult.getType() == 1 ? "节点" : "网关");
        }
        result.setRows(list);
        result.setTotal(((Page) list).getTotal());
        return result;
    }

    private Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date = java.sql.Date.valueOf(str);
        return date;
    }

    public List<TemperHumidityResult> temperHumidityAvg(String nodeMark, int type, String parameter) {
        int hs = 1000 * 60 * 60;
        List<TemperHumidityResult> results = null;
        Date date = stringToDate(parameter);
        if (type == 1) {
            Date startTime = new Date(date.getTime());
            Date endTime = new Date(date.getTime() + hs * 24 - 1);
            results = humidTemperDao.queryTemperHumidity(nodeMark,startTime,endTime,1,1000000);
        } else if (type == 2) {
            results = new ArrayList<TemperHumidityResult>();
            int count = getDaysOfMonth(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            for (int i = 1; i <= count; i++) {
                calendar.set(y,m,i,0,0);
                Date startTime = calendar.getTime();

//                DateFormat df2 = DateFormat.getDateTimeInstance();//可以精确到时分秒
//                System.out.println("开始："+df2.format(startTime));

                calendar.set(y,m,i,23,59);
                Date endTime = calendar.getTime();

                results.add(humidTemperDao.queryTemperHumidityAvg(nodeMark, startTime, endTime));
            }
        } else if (type == 3) {
            results = new ArrayList<TemperHumidityResult>();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int y = calendar.get(Calendar.YEAR);
            for (int i = 1; i <= 12; i++) {
                calendar.set(y, i, 1);
                Date startTime = calendar.getTime();
                calendar.set(y, i, getDaysOfMonth(calendar.getTime()), 23, 59);
                Date endTime = calendar.getTime();
                results.add(humidTemperDao.queryTemperHumidityAvg(nodeMark, startTime, endTime));
            }
        }
        return results;
    }

    private int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
