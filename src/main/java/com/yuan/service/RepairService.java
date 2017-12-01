package com.yuan.service;


import com.github.pagehelper.Page;
import com.yuan.dao.RepairDao;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.entity.Repair;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RepairService {
    @Autowired
    private RepairDao repairDao;

    public PageResult<Repair> getRepair(UserInfo userInfo, int pageNum, int pageSize) {
        List<Repair> list = null;
        if (userInfo.getUserType() == 0) {
            list = repairDao.getShowRepair(pageNum, pageSize);
        } else if (userInfo.getUserType() == 1 || userInfo.getUserType() == 2) {
            list = repairDao.getRepairByUnit(userInfo.getUnitId(),  pageNum, pageSize);
        }
        PageResult<Repair> pageResult = new PageResult<Repair>();
        pageResult.setTotal(((Page) list).getTotal());
        pageResult.setRows(list);
        return pageResult;
    }

    public Result addRepair(Repair repair) {
        Result result = new Result();
        try {
            if (repairDao.addRepair(repair) > 0) {
                result.setSucceed(true);
            } else {
                result.setSucceed(false);
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }

    public Result deleteRepair(int repairId) {
        Result result = new Result();
        try {
            if (repairDao.deleteRepair(repairId) > 0) {
                result.setSucceed(true);
            } else {
                result.setSucceed(false);
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }

    public Result updateRepair(Repair repair) {
        Result result = new Result();
        try {
            if (repairDao.updateRepair(repair) > 0) {
                result.setSucceed(true);
            } else {
                result.setSucceed(false);
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }
}
