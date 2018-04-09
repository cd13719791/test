package com.moyou.moyouRms.service.userrecommend.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.userRecommend.UserRecommendMapper;
import com.moyou.moyouRms.model.userRecommend.UserRecommend;
import com.moyou.moyouRms.service.userrecommend.UserRecommendService;
@Service
public class UserRecommendServiceImpl implements UserRecommendService{

	@Resource
	private UserRecommendMapper userRecommendMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userRecommendMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserRecommend record) {
		// TODO Auto-generated method stub
		return userRecommendMapper.insert(record);
	}

	@Override
	public int insertSelective(UserRecommend record) {
		// TODO Auto-generated method stub
		return userRecommendMapper.insertSelective(record);
	}

	@Override
	public UserRecommend selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userRecommendMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserRecommend record) {
		// TODO Auto-generated method stub
		return userRecommendMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserRecommend record) {
		// TODO Auto-generated method stub
		return userRecommendMapper.updateByPrimaryKey(record);
	}

	@Override
	public UserRecommend selectByUsreId(Integer id) {
		// TODO Auto-generated method stub
		return userRecommendMapper.selectByUsreId( id);
	}

	@Override
	public int updateByParam(UserRecommend record) {
		// TODO Auto-generated method stub
		return userRecommendMapper.updateByParam(record);
	}

	@Override
	public int updateRecommedStatusByUserId(UserRecommend userRecommend) {
		// TODO Auto-generated method stub
		return userRecommendMapper.updateRecommedStatusByUserId(userRecommend);
	}

}
