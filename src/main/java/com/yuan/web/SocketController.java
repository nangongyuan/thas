package com.yuan.web;


import com.yuan.dto.QueryGetwayResult;
import com.yuan.dto.Result;
import com.yuan.entity.UserInfo;
import com.yuan.service.SocketService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("socket")
public class SocketController {

    @Autowired
    private SocketService socketService;

    @RequestMapping(value = "/reloadGetway")
    @ResponseBody
    public Result reloadGetway(String getwayMark) {

        return socketService.reloadGetway(getwayMark);
    }

    @RequestMapping(value = "/isReloadGetway")
    @ResponseBody
    public Result isReloadGetway(String getwayMark) {
        return socketService.isReloadGetway(getwayMark);
    }

    @RequestMapping(value = "/setGetwayReportInterval")
    @ResponseBody
    public Result setGetwayReportInterval(String getwayMark, int interval) {
        return socketService.setReportInterval(getwayMark, interval);
    }

    @RequestMapping(value = "/isGetwayReportInterval")
    @ResponseBody
    public Result isGetwayReportInterval(String getwayMark) {
        return socketService.issetReportInterval(getwayMark);
    }

    @RequestMapping(value = "/getNodeAutoStatus")
    @ResponseBody
    public Result getNodeAutoStatus(String getwayMark) {
        return socketService.queryTH(getwayMark, 2);
    }

    @RequestMapping(value = "/setNodeAutoStatus")
    @ResponseBody
    public Result setNodeAutoStatus(String getwayMark) {
        return socketService.setNodeAutoStatus(getwayMark);
    }

    @RequestMapping(value = "/getRTInfo")
    @ResponseBody
    public List<QueryGetwayResult> getRTInfo(UserInfo userInfo) {
        return socketService.getRTInfo(userInfo);
    }


}
