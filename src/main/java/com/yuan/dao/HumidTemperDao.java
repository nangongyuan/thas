package com.yuan.dao;

import com.yuan.dto.TemperHumidityResult;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HumidTemperDao {
    int insertHumidTemper(@Param("nodeMark") String nodeMark,
                          @Param("humidity") double humidity, @Param("temper") double temper, @Param("type") int type, @Param("date") Date date);

    List<TemperHumidityResult> queryTemperHumidity(@Param("nodeMark") String nodeMark,
                                                   @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    TemperHumidityResult queryTemperHumidityAvg(@Param("nodeMark") String nodeMark,
                                                @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
