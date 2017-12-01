package com.yuan.web;

import com.yuan.dto.LoginResult;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.dto.ShowUserResult;
import com.yuan.entity.UserInfo;
import com.yuan.service.UserService;
import com.yuan.utils.VerifiCodeUtils;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/getVerifiCode")
    public void GetWayServ(HttpServletRequest request, HttpServletResponse response){
        VerifiCodeUtils.VerifiCodeStyle verifiCodeStyle = new VerifiCodeUtils.VerifiCodeStyle();
        verifiCodeStyle.setWidth(125);
        verifiCodeStyle.setHeight(80);
        verifiCodeStyle.setBgColor(new Color(231,242,249));
        VerifiCodeUtils vcImgObj = new VerifiCodeUtils(verifiCodeStyle);
        BufferedImage vcImg = vcImgObj.getVCImg();
        vcImgObj.outPutVCImg(response,vcImg);
        String reVerfiC = vcImgObj.getCodeValue().toString();
        request.getSession().setAttribute("verifiCode",reVerfiC);
        System.out.println("验证码："+reVerfiC);
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public LoginResult loginUser(String userName, String password, String verifiCode,HttpSession session) {
        return userService.login(userName, password, verifiCode,session);
    }

    @RequestMapping(value = "/loginAndroid")
    @ResponseBody
    public LoginResult loginAndroid(String userName, String password, HttpSession session) {
        return userService.loginAndroid(userName, password,session);
    }

    @RequestMapping(value = "/exit")
    @ResponseBody
    public Result loginOut(HttpSession session) {
        session.removeAttribute("user");
        Result result = new Result();
        result.setSucceed(true);
        result.setMsg("退出成功");
        return result;
    }

    @RequestMapping(value = "/getUserByGetwayId")
    @ResponseBody
    public PageResult<ShowUserResult> getUserByGetwayId(int getwayId, int page, int rows, HttpSession session) {   //页号   每页数据条数
        return userService.getUserByGetwayId(getwayId, page, rows);
    }

    @RequestMapping(value = "/getUserByUnitId")
    @ResponseBody
    public PageResult<UserInfo> getUserByUnitId(int unitId, int page, int rows, HttpSession session) {   //页号   每页数据条数
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return userService.getUserByUnitId(unitId, userInfo, page, rows);
    }

    @RequestMapping(value = "/addUser")
    @ResponseBody
    public Result addUser(UserInfo userInfo, HttpSession session) {
        System.out.println(userInfo);
        return userService.addUser(userInfo);
    }


    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public Result deleteUser(int userId, HttpSession session) {
        return userService.deleteUser(userId);
    }


    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public Result updateUser(UserInfo userInfo, HttpSession session) {
        System.out.println(userInfo);
        return userService.updateUser(userInfo);
    }
}



