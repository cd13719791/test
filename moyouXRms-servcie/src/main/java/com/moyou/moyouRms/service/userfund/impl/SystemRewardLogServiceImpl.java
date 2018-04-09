package com.moyou.moyouRms.service.userfund.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.userfund.SystemRewardLogMapper;
import com.moyou.moyouRms.dao.userfund.SystemRewardModelMapper;
import com.moyou.moyouRms.model.userfund.SystemRewardLog;
import com.moyou.moyouRms.service.userfund.SystemRewardLogService;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年7月4日 上午9:55:55 
 * 类说明 
 */
@Service
public class SystemRewardLogServiceImpl implements SystemRewardLogService {
@Resource
private SystemRewardLogMapper systemRewardLogMapper;
@Resource
SystemRewardModelMapper systemRewardModelMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return systemRewardLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SystemRewardLog record) {
		// TODO Auto-generated method stub
		return systemRewardLogMapper.insert(record);
	}

	@Override
	public int insertSelective(SystemRewardLog record) {
		// TODO Auto-generated method stub
		return systemRewardLogMapper.insertSelective(record);
	}

	@Override
	public SystemRewardLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return systemRewardLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SystemRewardLog record) {
		// TODO Auto-generated method stub
		return systemRewardLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SystemRewardLog record) {
		// TODO Auto-generated method stub
		return systemRewardLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SystemRewardLog> selectByRewardId(Integer id) {
		// TODO Auto-generated method stub
		return systemRewardLogMapper.selectByRewardId(id);
	}

	@Override
	public int updateSysRewardState() {
		// TODO Auto-generated method stub
		return systemRewardModelMapper.updateSysRewardState();
	}
	
	
	
}
