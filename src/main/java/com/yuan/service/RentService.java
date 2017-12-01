package com.yuan.service;

import com.github.pagehelper.Page;
import com.yuan.dao.RentDao;
import com.yuan.dao.UnitDao;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.entity.RentInfo;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class RentService {
    @Autowired
    private RentDao rentDao;
    @Autowired
    private UnitDao unitDao;

    public PageResult<RentInfo> getRent(UserInfo userInfo,int page,int pageSize){
        PageResult<RentInfo> result = new PageResult<RentInfo>();
        if (userInfo.getUserType()==0){
            List<RentInfo> list = rentDao.getRentInfo(new HashMap(),page,pageSize);
            result.setRows(list);
            result.setTotal(((Page)list).getTotal());
        }
        return result;
    }
    public Result addRent(RentInfo rentInfo) {
        Result result = new Result();
        if (isRented(rentInfo.getGetwayId())) {
            result.setSucceed(false);
            result.setMsg("该网关以出租");
            return result;
        }
        try {
            if (rentDao.addRent(rentInfo) > 0) {
                result.setSucceed(true);
            } else {
                result.setSucceed(false);
                result.setMsg("添加失败");
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }

    public Result deleteRent(int rentId) {
        Result result = new Result();
        try {
            if (rentDao.deleteRent(rentId) > 0) {
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

    public Result updateRent(RentInfo rentInfo) {
        Result result = new Result();
        if (isRented(rentInfo.getGetwayId())) {
            result.setSucceed(false);
            result.setMsg("该网关以出租");
        }
        try {
            if (rentDao.updateRent(rentInfo) > 0) {
                result.setSucceed(true);
            } else {
                result.setSucceed(false);
                result.setMsg("修改失败");
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }

    private boolean isRented(int getwayId) {
        List<RentInfo> list = rentDao.getRented();
        if (list != null) {
            for (RentInfo rent : list) {
                if (rent.getGetwayId() == getwayId) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("unidId", rent.getUnitId());
                    List<Unit> units = unitDao.getUnit(map, 1, 1);
                    if (units != null) {
                        if (units.get(0).getUnitType() == 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
