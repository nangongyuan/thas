package com.yuan.service;


import com.github.pagehelper.Page;
import com.yuan.dao.UnitDao;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.dto.ShowUserResult;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UnitService {
    @Autowired
    private UnitDao unitDao;

    public PageResult<Unit> getUnit(UserInfo userInfo, int pageNum, int pageSize) {
        List<Unit> list = null;
        if (userInfo.getUserType() == 0) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("unitType", 0);
            list = unitDao.getUnit(map, pageNum, pageSize);
        } else if (userInfo.getUserType() == 1 || userInfo.getUserType() == 2) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("unitId", userInfo.getUnitId());
            list = unitDao.getUnit(map, pageNum, pageSize);
        }
        return new PageResult<Unit>(((Page) list).getTotal(), list);

    }

    public Result addUnit(UserInfo userInfo, Unit unit) {
        Result result = new Result();
        try {
            if (userInfo.getUserType() == 0) {
                if (unitDao.addUnit(unit) > 0) {
                    result.setSucceed(true);
                } else {
                    result.setSucceed(false);
                }
            } else {
                result.setSucceed(false);
                result.setMsg("用户权限不足");
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }

    public Result deleteUnit(UserInfo userInfo, int unitId) {
        Result result = new Result();
        try {
            if (userInfo.getUserType() == 0) {
                if (unitDao.deleteUnit(unitId) > 0) {
                    result.setSucceed(true);
                } else {
                    result.setSucceed(false);
                }
            } else {
                result.setSucceed(false);
                result.setMsg("用户权限不足");
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }

    public Result updateUnit(UserInfo userInfo, Unit unit) {
        Result result = new Result();
        try {
            if (userInfo.getUserType() == 0) {
                if (unitDao.updateUnit(unit) > 0) {
                    result.setSucceed(true);
                } else {
                    result.setSucceed(false);
                }
            } else if (userInfo.getUserType() == 1) {
                unit.setUnitType(1);
                if (unitDao.updateUnit(unit) > 0) {
                    result.setSucceed(true);
                } else {
                    result.setSucceed(false);
                }
            } else if (userInfo.getUserType() == 2) {
                unit.setUnitType(2);
                if (unitDao.updateUnit(unit) > 0) {
                    result.setSucceed(true);
                } else {
                    result.setSucceed(false);
                }
            } else {
                result.setSucceed(false);
                result.setMsg("用户权限不足");
            }
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
            result.setSucceed(false);
        }
        return result;
    }


    public PageResult<Unit> getUnitByTitle(String title, int pageNum, int pageSize) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("unitTitle", title);
        List<Unit> list = unitDao.getUnit(map, pageNum, pageSize);
        return new PageResult<Unit>(((Page) list).getTotal(), list);
    }

}
