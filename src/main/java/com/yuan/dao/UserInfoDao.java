package com.yuan.dao;


import com.yuan.dto.LoginResult;
import com.yuan.dto.ShowUserResult;
import com.yuan.entity.UserInfo;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

public interface UserInfoDao {
    List<UserInfo> getUserInfo(@Param("map") Map map, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<ShowUserResult> getUserBygetwayId(@Param("getwayId") int getwayId, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    List<ShowUserResult> getShowUserResult(@Param("map") Map map, @Param("pageNumKey") int pageNum, @Param("pageSizeKey") int pageSize);

    int addUser(UserInfo userInfo) throws DataAccessException;

    int deleteUser(int userId) throws DataAccessException;

    int updateUser(UserInfo userInfo) throws DataAccessException;

}
