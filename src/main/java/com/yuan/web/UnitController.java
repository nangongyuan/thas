package com.yuan.web;

import com.yuan.dao.RentDao;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.dto.ShowUserResult;
import com.yuan.entity.RentInfo;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import com.yuan.service.RentService;
import com.yuan.service.UnitService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    private UnitService unitService;

    @Autowired
    private RentService rentService;

    @RequestMapping(value = "/getUnit")
    @ResponseBody
    public PageResult<Unit> getUnit(int page, int rows, HttpSession session) {   //页号   每页数据条数
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return unitService.getUnit(userInfo, page, rows);
    }

    @RequestMapping(value = "/addUnit")
    @ResponseBody
    public Result addUser(Unit unit, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return unitService.addUnit(userInfo, unit);
    }

    @RequestMapping(value = "/deleteUnit")
    @ResponseBody
    public Result deleteUser(int unitId, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return unitService.deleteUnit(userInfo, unitId);
    }

    @RequestMapping(value = "/updateUnit")
    @ResponseBody
    public Result updateUser(Unit unit, HttpSession session) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        Result result = unitService.updateUnit(userInfo, unit);
        return result;
    }

    @RequestMapping(value = "/getUnitByTitle")
    @ResponseBody
    public PageResult<Unit> searchUnit(String title, int page, int rows) {
        return unitService.getUnitByTitle(title, page, rows);
    }

    @RequestMapping(value = "/getRent")
    @ResponseBody
    public PageResult<RentInfo> getRent(HttpSession httpSession,int page,int rows) {
        UserInfo userInfo = (UserInfo) httpSession.getAttribute("user");
        return rentService.getRent(userInfo,page,rows);
    }

    @RequestMapping(value = "/addRent")
    @ResponseBody
    public Result addRent(RentInfo rentInfo) {
        return rentService.addRent(rentInfo);
    }

    @RequestMapping(value = "/deleteRent")
    @ResponseBody
    public Result deleteRent(int rentId) {
        return rentService.deleteRent(rentId);
    }

    @RequestMapping(value = "/updateRent")
    @ResponseBody
    public Result updateRent(RentInfo rentInfo) {
        return rentService.updateRent(rentInfo);
    }
}
