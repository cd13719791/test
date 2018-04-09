package com.moyou.moyouRms.dao.userfund;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.userfund.SystemRewardModel;

public interface SystemRewardModelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemRewardModel record);

    int insertSelective(SystemRewardModel record);

    SystemRewardModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemRewardModel record);

    int updateByPrimaryKey(SystemRewardModel record);
    List<SystemRewardModel> selectSystemRewardLog(PageBean pb);

	Map<String, Object> selectSystemRewardLogCount();

	Map<String,Object> selectRewardSystemInfoCount();
	/**
	 * 初始化所有的正在执行的打赏 为异常
	 * @return
	 */
	int updateSysRewardState();
}