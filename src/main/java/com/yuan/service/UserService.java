package com.yuan.service;

import com.github.pagehelper.Page;
import com.yuan.dao.UnitDao;
import com.yuan.dao.UserInfoDao;
import com.yuan.dto.LoginResult;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.dto.ShowUserResult;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpSession;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserInfoDao userInfoDao;
    @Resource
    private UnitDao unitDao;

    public LoginResult login(String userName, String password, String verifiCode,HttpSession session) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userName", userName);
        LoginResult loginResult = new LoginResult();
        String vc = (String) session.getAttribute("verifiCode");
        System.out.println(vc);
        if (verifiCode == null || verifiCode.equals("") || vc==null || vc.equals("") || !verifiCode.toLowerCase().equals(vc.toLowerCase()))
        {
            loginResult.setSucceed(false);
            loginResult.setMsg("验证码不正确");
        }else{
            List<UserInfo> list = userInfoDao.getUserInfo(userMap, 1, 1);
            if (list != null && list.size() > 0) {
                UserInfo userInfo = list.get(0);
                if (userInfo.getPassword().equals(password)) {
                    loginResult.setSucceed(true);
                    loginResult.setName(userInfo.getName());
                    loginResult.setUserType(userInfo.getUserType());
                    loginResult.setUserId(userInfo.getUserId());
                    loginResult.setUnitId(userInfo.getUnitId());
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("unitId", userInfo.getUnitId());
                    List<Unit> units = unitDao.getUnit(map, 1, 1);
                    loginResult.setUnit(units.get(0).getTitle());
                    System.out.println("成功登录：" + userInfo);
                    session.setAttribute("user", userInfo);
                } else {
                    loginResult.setSucceed(false);
                    loginResult.setMsg("密码错误");
                }
            } else {
                loginResult.setSucceed(false);
                loginResult.setMsg("用户不存在");
            }
        }
        return loginResult;

    }

    public LoginResult loginAndroid(String userName, String password,HttpSession session) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userName", userName);
        LoginResult loginResult = new LoginResult();
        List<UserInfo> list = userInfoDao.getUserInfo(userMap, 1, 1);
        if (list != null && list.size() > 0) {
            UserInfo userInfo = list.get(0);
            if (userInfo.getPassword().equals(password)) {
                loginResult.setSucceed(true);
                loginResult.setName(userInfo.getName());
                loginResult.setUserType(userInfo.getUserType());
                loginResult.setUserId(userInfo.getUserId());
                loginResult.setUnitId(userInfo.getUnitId());
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("unitId", userInfo.getUnitId());
                List<Unit> units = unitDao.getUnit(map, 1, 1);
                loginResult.setUnit(units.get(0).getTitle());
                System.out.println("成功登录：" + userInfo);
                session.setAttribute("user", userInfo);
            } else {
                loginResult.setSucceed(false);
                loginResult.setMsg("密码错误");
            }
        } else {
            loginResult.setSucceed(false);
            loginResult.setMsg("用户不存在");
        }

        return loginResult;

    }

    public PageResult<ShowUserResult> getUserByGetwayId(int getwayId, int pageNum, int pageSize) {
        PageResult<ShowUserResult> resultPageResult = new PageResult<ShowUserResult>();
        List<ShowUserResult> list = userInfoDao.getUserBygetwayId(getwayId, pageNum, pageSize);
        resultPageResult.setRows(list);
        resultPageResult.setTotal((int) ((Page) list).getTotal());
        return resultPageResult;
    }

    public PageResult<UserInfo> getUserByUnitId(int unitId, UserInfo userInfo, int pageNum, int pageSize) {
        PageResult<UserInfo> resultPageResult = new PageResult<UserInfo>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (userInfo.getUserType() == 0) {
            map.put("unitId", unitId);
        } else if (userInfo.getUserType() == 1 || userInfo.getUserType() == 2) {
            map.put("unitId", userInfo.getUnitId());
            map.put("userType",3);
        }
        List<UserInfo> list = userInfoDao.getUserInfo(map, pageNum, pageSize);
        resultPageResult.setRows(list);
        resultPageResult.setTotal(((Page) list).getTotal());
        return resultPageResult;
    }

    public Result addUser(UserInfo addUser) {
        Result result = new Result();
        try {
            if (userInfoDao.addUser(addUser) > 0) {
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

    public Result deleteUser(int userId) {
        Result result = new Result();
        try {
            if (userInfoDao.deleteUser(userId) > 0) {
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

    public Result updateUser(UserInfo userInfo) {
        Result result = new Result();
        try {
            if (userInfoDao.updateUser(userInfo) > 0) {
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
