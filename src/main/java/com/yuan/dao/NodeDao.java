package com.yuan.dao;

import com.yuan.entity.Getway;
import com.yuan.entity.Node;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface NodeDao {

    Node getNodeByNodeNum(String nodeNum);

    Node getNodeByNodeMark(String nodeMark);

    Node getNodeByGetwayIdNodeNum(@Param("getwayId") int getwayId, @Param("nodeNum") String nodeNum);

    List<Node> getNodeByGetwayId(@Param("getwayId") int getwayId, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<Node> getNodeByGetwayIdAndUserId(@Param("getwayId") int getwayId, @Param("userId") int userId);

    int addNode(Node node) throws DataAccessException;

    int deleteNode(int nodeId) throws DataAccessException;

    int updateNode(Node node) throws DataAccessException;

    Node isNodeNum(@Param("getwayId") int getwayId, @Param("nodeNum") String nodeNum);

    Integer getNodeIdByGetwayMark(String getwayMark);

}
