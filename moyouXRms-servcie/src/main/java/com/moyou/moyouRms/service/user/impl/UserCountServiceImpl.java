package com.moyou.moyouRms.service.user.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.user.UserCountMapper;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserCount;
import com.moyou.moyouRms.service.user.UserCountService;

@Service
public class UserCountServiceImpl implements UserCountService {
	@Resource
	private UserCountMapper userCountMapper;

	@Override
	public int insert(UserCount record) {
		// TODO Auto-generated method stub
		return userCountMapper.insert(record);
	}

	@Override
	public int insertSelective(UserCount record) {
		// TODO Auto-generated method stub
		return userCountMapper.insertSelective(record);
	}

	@Override
	public UserCount queryUserCount(UserCount u) {
		// TODO Auto-generated method stub
		return userCountMapper.queryUserCount(u);
	}

	//
	// @Override
	// public int updateUserCountTalkJia1(UserCount u)
	// {
	// // TODO Auto-generated method stub
	// return userCountMapper.updateUserCountTalkJia1(u.getUserId());
	// }

	@Override
	public int updateUserCountDiaryJia1(User u) {
		// TODO Auto-generated method stub
		return userCountMapper.updateUserCountDiaryJia1(u.getUserId());
	}

	@Override
	public int updateUserCountSecretJia1(User u) {
		// TODO Auto-generated method stub
		return userCountMapper.updateUserCountSecretJia1(u.getUserId());
	}

	@Override
	public int updateInterestCountJIA1(Integer userId) {
		// TODO Auto-generated method stub
		return userCountMapper.updateInterestCountJIA1(userId);
	}

	@Override
	public int updateFollowersCountJIA1(Integer userId) {
		// TODO Auto-generated method stub
		return userCountMapper.updateFollowersCountJIA1(userId);
	}

	@Override
	public UserCount selectUserCountByUserId(String userId) {
		// TODO Auto-generated method stub
		return userCountMapper.selectUserCountByUserId(userId);
	}

}
