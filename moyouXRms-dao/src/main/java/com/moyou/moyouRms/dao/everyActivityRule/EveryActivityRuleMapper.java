package com.moyou.moyouRms.dao.everyActivityRule;

import java.util.List;

import com.moyou.moyouRms.model.everyActivityRule.EveryActivityRule;

public interface EveryActivityRuleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(EveryActivityRule record);

	int insertSelective(EveryActivityRule record);

	EveryActivityRule selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EveryActivityRule record);

	int updateByPrimaryKey(EveryActivityRule record);

	List<EveryActivityRule> selectEveryActivityRuleList();
}