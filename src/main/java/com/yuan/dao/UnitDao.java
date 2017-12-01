package com.yuan.dao;


import com.yuan.dto.ShowUserResult;
import com.yuan.entity.Unit;
import com.yuan.entity.UserInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UnitDao {
    List<Unit> getUnit(@Param("map") Map map, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    int addUnit(Unit unit) throws DataAccessException;

    int deleteUnit(int unitId) throws DataAccessException;

    int updateUnit(Unit unit) throws DataAccessException;

}
