package com.moyou.moyouRms.dao.userSignLog;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.statistics.MiniApps;
import com.moyou.moyouRms.model.userSignLog.UserSignLog;


public interface UserSignLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserSignLog record);

    int insertSelective(UserSignLog record);

    UserSignLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserSignLog record);

    int updateByPrimaryKey(UserSignLog record);
    @SuppressWarnings("all")
    List<UserSignLog> selectUserSignLogListByDate(Map param);
    /**
     * 昨日签到
     * @return
     */
    MiniApps queryYesterdaySig();
    /**
     * 总签到
     * @return
     */
    MiniApps queryCountSig();
}