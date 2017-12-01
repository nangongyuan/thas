package com.yuan.dao;

import com.yuan.dto.GetwayResult;
import com.yuan.dto.QueryGetwayResult;
import com.yuan.entity.Getway;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface GetwayDao {
    //delete from use_author as u,rent as r,node as n where u.NodeId=n.NodeId and n.GetwayId=r.GetwayId and (#{now}<StartTime or #{now}>EndTime)

    int addGetway(Getway getway) throws DataAccessException;

    int deleteGetway(int getwayId) throws DataAccessException;

    int updateGetway(Getway getway) throws DataAccessException;

    List<GetwayResult> getGetway( @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<GetwayResult> getGetWayByUnitId(@Param("unitId") int unitId,  @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<GetwayResult> getGetWayByUserId(@Param("userId") int userId, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<QueryGetwayResult> getQueryGetway();

    List<QueryGetwayResult> getQueryGetwayByUnitId(@Param("unitId") int unitId);

    List<QueryGetwayResult> getQueryGetwayByUserId(@Param("userId") int userId);

    int addGetwayUser(@Param("nodeId") int nodeId, @Param("userId") int userId);

    int deleteGetwayUser(@Param("nodeId") int nodeId, @Param("userId") int userId);

    List<GetwayResult> getGetwayByGetwayMark(@Param("getwayMark") String getwayMark, @Param("now") Date now, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<GetwayResult> getUnitByTitle(@Param("title") String title, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);
}
