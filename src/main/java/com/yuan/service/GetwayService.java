package com.yuan.service;


import com.github.pagehelper.Page;
import com.yuan.dao.GetwayDao;
import com.yuan.dao.NodeDao;
import com.yuan.dao.UnitDao;
import com.yuan.dao.UserInfoDao;
import com.yuan.dto.GetwayResult;
import com.yuan.dto.PageResult;
import com.yuan.dto.Result;
import com.yuan.entity.Getway;
import com.yuan.entity.Node;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetwayService {

    @Autowired
    private GetwayDao getwayDao;
    @Autowired
    private NodeDao nodeDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private UnitDao unitDao;

    public PageResult<GetwayResult> getGetway(int pageNum, int pageSize, UserInfo userInfo) {
        int userType = userInfo.getUserType();
        PageResult<GetwayResult> result = new PageResult<GetwayResult>();
        List<GetwayResult> list = null;
        if (userType == 0) {
            list = getwayDao.getGetway( pageNum, pageSize);
            for (GetwayResult result1 : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("unitId", result1.getUnitId());
                List<Unit> units = unitDao.getUnit(map, 1, 1000);
                if (units.size()>0)
                    result1.setUnitTitle(units.get(0).getTitle());
            }
        } else if (userType == 1 || userType == 2 || userType == 3) {
            list = getwayDao.getGetWayByUnitId(userInfo.getUnitId(),  pageNum, pageSize);
        }
        result.setRows(list);
        result.setTotal(((Page) list).getTotal());
        return result;
    }

    public Result addGetwayUser(String getwayMark, int userId) {
        Result result = new Result();
        int nodeId = nodeDao.getNodeIdByGetwayMark(getwayMark);
        System.out.println(nodeId);
        try {
            if (getwayDao.addGetwayUser(nodeId, userId) > 0) {
                result.setSucceed(true);
            } else {
                result.setSucceed(false);
            }
        } catch (Exception e) {
            result.setSucceed(false);
        }
        return result;
    }

    public Result deleteGetwayUser(String getwayMark, int userId) {
        Result result = new Result();
        int nodeId = nodeDao.getNodeIdByGetwayMark(getwayMark);
        try {
            if (getwayDao.deleteGetwayUser(nodeId, userId) > 0) {
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
    @Transactional
    public Result addGetway(Getway getway) {
        Result result = new Result();
        try {
            if (getwayDao.addGetway(getway) > 0) {
                GetwayResult getway1 = getwayDao.getGetwayByGetwayMark(getway.getGetwayMark(), null, 1, 10000).get(0);
                nodeDao.addNode(new Node(1, getway.getGetwayMark(), getway1.getGetwayId(),
                        getway.getSpareNode(), getway.getNodeNum(), 0, getway.getStatus(), getway.getTemper(), getway.getHumidity(), getway.getMemo()));
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

    public Result deleteGetway(int getwayId) {
        Result result = new Result();
        try {
            if (getwayDao.deleteGetway(getwayId) > 0) {
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

    @Transactional
    public Result updateGetway(Getway getway) {
        Result result = new Result();
        try {

            Node node = nodeDao.getNodeByNodeMark(getway.getGetwayMark());
            if (getwayDao.updateGetway(getway) > 0 && nodeDao.updateNode(node) > 0) {
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

    public PageResult<GetwayResult> getGetwayByGetwayMark(String getwayMark, int page, int rows) {   //盒子ID
        PageResult<GetwayResult> pageResult = new PageResult<GetwayResult>();
        List<GetwayResult> list = getwayDao.getGetwayByGetwayMark(getwayMark, new Date(), page, rows);
        pageResult.setRows(list);
        pageResult.setTotal(((Page) list).getTotal());
        return pageResult;
    }

    public PageResult<GetwayResult> searchUnit(String unitTitle, int pageNum, int pageSize) {
        PageResult<GetwayResult> pageResult = new PageResult<GetwayResult>();
        List<GetwayResult> list = getwayDao.getUnitByTitle("%" + unitTitle + "%",  pageNum, pageSize);
        pageResult.setRows(list);
        pageResult.setTotal(((Page) list).getTotal());
        return pageResult;
    }


    public Result addNode(Node node) {
        Result result = new Result();
        if (nodeDao.getNodeByNodeMark(node.getNodeNum()) != null) {
            result.setSucceed(false);
            result.setMsg("盒子以存在");
        } else if (nodeDao.isNodeNum(node.getGetwayId(), node.getNodeNum()) != null) {
            result.setSucceed(false);
            result.setMsg("节点号以存在");
        } else {
            try {
                node.setType(1);
                if (nodeDao.addNode(node) > 0) {
                    result.setSucceed(true);
                } else {
                    result.setSucceed(false);
                }
            } catch (DataAccessException e) {
                System.out.println(e.getMessage());
                result.setSucceed(false);
            }
        }
        return result;
    }

    public Result deleteNode(int nodeId) {
        Result result = new Result();
        try {
            if (nodeDao.deleteNode(nodeId) > 0) {
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

    public Result updateNode(Node node) {
        Result result = new Result();
        try {
            if (nodeDao.updateNode(node) > 0) {
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

    public PageResult<Node> getNodeByGetwayId(int getwayId, int pageNum, int pageSize) {
        List<Node> list = nodeDao.getNodeByGetwayId(getwayId, pageNum, pageSize);
        PageResult<Node> result = new PageResult<Node>();
        result.setRows(list);
        result.setTotal(((Page) list).getTotal());
        return result;
    }

    //    public void setGetwayTimeInter(String getwayMark,int inter){
//        GetwayResult getway = getwayDao.getGetwayByGetwayMark(getwayMark,new Date());
//        getway.setTimeInter(inter);
//        Getway getway1 = new Getway();
//        getwayDao.updateGetway(getway);
//    }
    public void setNodeStatus(String nodeMark, int status) {
        Node node = nodeDao.getNodeByNodeMark(nodeMark);
        node.setStatus(status);
        nodeDao.updateNode(node);
    }
}
