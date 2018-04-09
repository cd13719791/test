package com.moyou.moyouRms.service.usersignlog.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.userSignLog.UserSignLogMapper;
import com.moyou.moyouRms.model.userSignLog.UserSignLog;
import com.moyou.moyouRms.service.usersignlog.UserSignLogService;

@Service
public class UserSignLogServiceImpl implements UserSignLogService {

	@Resource
	private UserSignLogMapper userSignLogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userSignLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserSignLog record) {
		// TODO Auto-generated method stub
		return userSignLogMapper.insert(record);
	}

	@Override
	public int insertSelective(UserSignLog record) {
		// TODO Auto-generated method stub
		return userSignLogMapper.insertSelective(record);
	}

	@Override
	public UserSignLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userSignLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserSignLog record) {
		// TODO Auto-generated method stub
		return userSignLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserSignLog record) {
		// TODO Auto-generated method stub
		return userSignLogMapper.updateByPrimaryKey(record);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<UserSignLog> selectUserSignLogListByDate(Map param) {
		// TODO Auto-generated method stub
		return userSignLogMapper.selectUserSignLogListByDate(param);
	}

}
