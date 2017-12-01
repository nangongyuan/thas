package com.yuan.web;

import com.yuan.dto.GetwayResult;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.entity.Getway;
import com.yuan.entity.Node;
import com.yuan.entity.UserInfo;
import com.yuan.service.GetwayService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/getway")
public class GetwayController {

    @Autowired
    private GetwayService getwayService;

    @RequestMapping(value = "/getGetway")
    @ResponseBody
    public PageResult<GetwayResult> getGetway(int page, int rows, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        System.out.println(userInfo);
        return getwayService.getGetway(page, rows, userInfo);
    }


    @RequestMapping(value = "/getNodeByGetwayId")
    @ResponseBody
    public PageResult<Node> getNodeByGetwayId(int getwayId, int page, int rows) {
        return getwayService.getNodeByGetwayId(getwayId, page, rows);
    }

    @RequestMapping(value = "/addGetwayUser")   //添加用户对用户对网关的控制
    @ResponseBody
    public Result addGetwayUser(String getwayMark, int userId) {
        return getwayService.addGetwayUser(getwayMark, userId);
    }

    @RequestMapping(value = "/deleteGetwayUser")    //删除用户对网关的控制
    @ResponseBody
    public Result deleteGetwayUser(String getwayMark, int userId) {
        return getwayService.deleteGetwayUser(getwayMark, userId);
    }

    @RequestMapping(value = "/getGetwayByGetwayMark")
    @ResponseBody
    public PageResult<GetwayResult> getGetwayByGetwayMark(String getwayMark, int page, int rows) {   //盒子ID
        return getwayService.getGetwayByGetwayMark(getwayMark, page, rows);
    }

    @RequestMapping(value = "/searchUnit")
    @ResponseBody
    public PageResult<GetwayResult> searchUnit(String unit, int page, int rows) {
        return getwayService.searchUnit(unit, page, rows);
    }

    @RequestMapping(value = "/addGetway")
    @ResponseBody
    public Result addGetway(Getway getway) {
        System.out.println(getway);
        return getwayService.addGetway(getway);
    }

    @RequestMapping(value = "/deleteGetway")
    @ResponseBody
    public Result deleteGetway(int getwayId) {
        return getwayService.deleteGetway(getwayId);
    }

    @RequestMapping(value = "/updateGetway")
    @ResponseBody
    public Result updateGetway(Getway getway) {
        System.out.println(getway);
        return getwayService.updateGetway(getway);
    }

    @RequestMapping(value = "/addNode")
    @ResponseBody
    public Result addNode(Node node) {
        System.out.println(node);
        return getwayService.addNode(node);
    }

    @RequestMapping(value = "/deleteNode")
    @ResponseBody
    public Result deleteNode(int nodeId) {
        return getwayService.deleteNode(nodeId);
    }

    @RequestMapping(value = "/updateNode")
    @ResponseBody
    public Result updateNode(Node node) {
        System.out.println(node);
        return getwayService.updateNode(node);
    }


}
