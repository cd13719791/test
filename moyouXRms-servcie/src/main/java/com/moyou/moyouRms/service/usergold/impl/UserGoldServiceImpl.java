package com.moyou.moyouRms.service.usergold.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.userGold.UserGoldMapper;
import com.moyou.moyouRms.model.userGold.UserGold;
import com.moyou.moyouRms.service.usergold.UserGoldService;
import com.moyou.moyouRms.util.JsonUtil;

@Service
public class UserGoldServiceImpl implements UserGoldService {
	@Resource
	private UserGoldMapper userGoldMappr;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userGoldMappr.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserGold record) {
		// TODO Auto-generated method stub
		return userGoldMappr.insert(record);
	}

	@Override
	public int insertSelective(UserGold record) {
		// TODO Auto-generated method stub
		return userGoldMappr.insertSelective(record);
	}

	@Override
	public UserGold selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userGoldMappr.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserGold record) {
		// TODO Auto-generated method stub
		return userGoldMappr.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserGold record) {
		// TODO Auto-generated method stub
		return userGoldMappr.updateByPrimaryKey(record);
	}

	@Override
	public UserGold selectByUserId(String userId) {
		// TODO Auto-generated method stub
		return userGoldMappr.selectByUserId(userId);
	}

	@Override
	public UserGold selectByUserIdLock(String userId) {
		return userGoldMappr.selectByUserIdLock(userId);
	}

	@Override
	public Integer addOrUpdateGold(int userId, int userGold) {
		UserGold ug = selectByUserIdLock(userId + "");
		if (ug == null) {// 添加
			ug = new UserGold();
			ug.setUpdateTime(new Date());
			ug.setUserGold(userGold);
			ug.setUserId(userId);
			userGoldMappr.insert(ug);
		} else {// 修改
			ug.setUpdateTime(new Date());
			ug.setUserGold(ug.getUserGold() + userGold);
			userGoldMappr.updateByPrimaryKey(ug);
		}
		return ug.getUserGold();
	}

	@Override
	public UserGold queryLiveUserGoldByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userGoldMappr.queryLiveUserGoldByUserId(userId);
	}

	@Override
	public Integer addOrUpdateGoldForLive(int userId, int userGold) {
		UserGold ug = selectByUserIdLock(userId + "");
		if (ug == null) {// 添加
			ug = new UserGold();
			ug.setUpdateTime(new Date());
			ug.setLiveGold(userGold);
			ug.setUserId(userId);
			userGoldMappr.insertSelective(ug);
		} else {// 修改
			ug.setUpdateTime(new Date());
			ug.setLiveGold(ug.getLiveGold() + userGold);
			int index = userGoldMappr.updateByPrimaryKeyForVerSion(new UserGold(ug.getId(), ug
					.getLiveGold(), ug.getLockVersion(), new Date()));
			if (index != 1) {
				try {
					throw new Exception("提现失败" + "[" + JsonUtil.toJson(ug) + "]");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ug.getLiveGold();
	}
}
