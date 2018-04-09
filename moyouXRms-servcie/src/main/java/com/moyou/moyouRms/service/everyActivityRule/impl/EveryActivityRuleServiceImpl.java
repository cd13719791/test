package com.moyou.moyouRms.service.everyActivityRule.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.everyActivityRule.EveryActivityRuleMapper;
import com.moyou.moyouRms.model.everyActivityRule.EveryActivityRule;
import com.moyou.moyouRms.service.everyActivityRule.EveryActivityRuleService;
import com.moyou.moyouRms.util.StringUtil;

@Service
public class EveryActivityRuleServiceImpl implements EveryActivityRuleService {

	@Resource
	private EveryActivityRuleMapper everyActivityRuleMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return everyActivityRuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EveryActivityRule record) {
		// TODO Auto-generated method stub
		return everyActivityRuleMapper.insert(record);
	}

	@Override
	public int insertSelective(EveryActivityRule record) {
		// TODO Auto-generated method stub
		return everyActivityRuleMapper.insertSelective(record);
	}

	@Override
	public EveryActivityRule selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return everyActivityRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EveryActivityRule record) {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(record.getId())) {
			record.setUpdateTime(new Date());
			record.setDataStatus(EveryActivityRule.NORMAL);
			everyActivityRuleMapper.insertSelective(record);
		} else {
			record.setDataStatus(EveryActivityRule.NORMAL);
			record.setUpdateTime(new Date());
			everyActivityRuleMapper.updateByPrimaryKeySelective(record);
		}
		return RESPONSE.SUCCESS;
	}

	@Override
	public int updateByPrimaryKey(EveryActivityRule record) {
		// TODO Auto-generated method stub
		return everyActivityRuleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<EveryActivityRule> selectEveryActivityRuleList() {
		// TODO Auto-generated method stub
		return everyActivityRuleMapper.selectEveryActivityRuleList();
	}

}
