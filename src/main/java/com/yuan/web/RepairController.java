package com.yuan.web;

import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.entity.Repair;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import com.yuan.service.RepairService;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("repair")
public class RepairController {

    @Autowired
    private RepairService repairService;

    @RequestMapping(value = "/getRepair")
    @ResponseBody
    public PageResult<Repair> getRepair(HttpSession session, int page, int rows) {
        UserInfo userInfo = (UserInfo) session.getAttribute("user");
        return repairService.getRepair(userInfo, page, rows);
    }

    @RequestMapping(value = "/addRepair")
    @ResponseBody
    public Result addRepair(Repair repair) {
        return repairService.addRepair(repair);
    }

    @RequestMapping(value = "/deleteRepair")
    @ResponseBody
    public Result deleteRepair(int repairId) {
        return repairService.deleteRepair(repairId);
    }

    @RequestMapping(value = "/updateRepair")
    @ResponseBody
    public Result updateRepair(Repair repair) {
        System.out.println(repair);
        return repairService.updateRepair(repair);
    }

}
