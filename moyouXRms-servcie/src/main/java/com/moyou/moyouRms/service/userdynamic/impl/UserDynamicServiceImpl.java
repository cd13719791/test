package com.moyou.moyouRms.service.userdynamic.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.userdynamic.UserDynamicMapper;
import com.moyou.moyouRms.model.userdynamic.UserDynamic;
import com.moyou.moyouRms.service.userdynamic.UserDynamicService;

/**
 * @author created by Chenxv
 * @date 2017年8月31日 上午10:18:48
 */
@Service
public class UserDynamicServiceImpl implements UserDynamicService {
	@Resource
	UserDynamicMapper userDynamicMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userDynamicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserDynamic record) {
		// TODO Auto-generated method stub
		return userDynamicMapper.insert(record);
	}

	@Override
	public int insertSelective(UserDynamic record) {
		// TODO Auto-generated method stub
		return userDynamicMapper.insertSelective(record);
	}

	@Override
	public UserDynamic selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userDynamicMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserDynamic record) {
		// TODO Auto-generated method stub
		return userDynamicMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserDynamic record) {
		// TODO Auto-generated method stub
		return userDynamicMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByDataIdAnDType(UserDynamic record) {
		// TODO Auto-generated method stub
		return userDynamicMapper.deleteByDataIdAnDType(record);
	}

}
