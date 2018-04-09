package com.moyou.moyouRms.service.everyActivityRule;

import java.util.List;

import com.moyou.moyouRms.model.everyActivityRule.EveryActivityRule;

public interface EveryActivityRuleService {
	int deleteByPrimaryKey(Integer id);

	int insert(EveryActivityRule record);

	int insertSelective(EveryActivityRule record);

	EveryActivityRule selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EveryActivityRule record);

	int updateByPrimaryKey(EveryActivityRule record);

	/**
	 * 初始化活动规则设置
	 * 
	 * @param id
	 * @return
	 */
	List<EveryActivityRule> selectEveryActivityRuleList();

}