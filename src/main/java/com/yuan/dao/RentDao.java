package com.yuan.dao;

import com.yuan.entity.Node;
import com.yuan.entity.RentInfo;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface RentDao {
    List<RentInfo> getRentInfo(@Param("map") Map map, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<RentInfo> getRented();

    int addRent(RentInfo rentInfo) throws DataAccessException;

    int deleteRent(int rentId) throws DataAccessException;

    int updateRent(RentInfo rentInfo) throws DataAccessException;
}
