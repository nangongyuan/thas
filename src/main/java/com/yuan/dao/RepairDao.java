package com.yuan.dao;

import com.yuan.entity.Repair;
import com.yuan.entity.Unit;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface RepairDao {
    Repair getRepairByRepairId(int repairId);

    List<Repair> getShowRepair(@Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<Repair> getRepairByUnit(@Param("unit") int unit, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    int addRepair(Repair repair) throws DataAccessException;

    int deleteRepair(int repairId) throws DataAccessException;

    int updateRepair(Repair repair) throws DataAccessException;
}
