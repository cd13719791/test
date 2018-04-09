package com.moyou.moyouRms.service.userfund;

import java.util.List;

import com.moyou.moyouRms.model.userfund.SystemRewardLog;

public interface SystemRewardLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemRewardLog record);

    int insertSelective(SystemRewardLog record);

    SystemRewardLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemRewardLog record);

    int updateByPrimaryKey(SystemRewardLog record);
    
    List<SystemRewardLog> selectByRewardId(Integer id);
    /**
     * 初始化所有正在执行的打赏为异常
     * @return
     */
    int updateSysRewardState();
}