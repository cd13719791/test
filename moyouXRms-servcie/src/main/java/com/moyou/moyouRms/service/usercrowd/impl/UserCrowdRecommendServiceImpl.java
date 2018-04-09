package com.moyou.moyouRms.service.usercrowd.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdMembersMapper;
import com.moyou.moyouRms.dao.usercrowd.UserCrowdRecommendMapper;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.model.usercrowd.UserCrowdRecommend;
import com.moyou.moyouRms.service.usercrowd.UserCrowdRecommendService;

@Service
public class UserCrowdRecommendServiceImpl implements UserCrowdRecommendService {

	@Resource
	private UserCrowdMapper userCrowdMapper;
	@Resource
	EaseMobService easeMobService;
	@Resource
	UserInfoMapper userInfoMapper;
	@Resource
	UserCrowdMembersMapper userCrowdMembersMapper;
	@Resource
	UserCrowdRecommendMapper userCrowdRecommendMapper;

	@Override
	public void addUserCrowdRecommend(UserCrowdRecommend userCrowdRecommend) {
		UserCrowdRecommend ucr = getUserCrowdRecommend(userCrowdRecommend.getCrowedId(), userCrowdRecommend.getModeType());
		if (ucr == null) {
			userCrowdRecommendMapper.insert(userCrowdRecommend);
		} else {
			UserCrowdRecommend p = new UserCrowdRecommend();
			p.setRecommedStatus((short)1);//  推荐状态0否1是
			p.setUpdateTime(new Date());
			userCrowdRecommendMapper.updateByPrimaryKeySelective(p);
		}
	}

	@Override
	public UserCrowdRecommend getUserCrowdRecommend(int crowdId, int modeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserCrowdRecommendState(UserCrowdRecommend userCrowdRecommend) {
		userCrowdRecommendMapper.updateByPrimaryKeySelective(userCrowdRecommend);
		
	}

	@Override
	public UserCrowdRecommend getUserCrowdRecommend(int id) {
		// TODO Auto-generated method stub
		return userCrowdRecommendMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserCrowdRecommend> queryUserCrowdRecommendList(UserCrowdRecommend record) {
		// TODO Auto-generated method stub
		return userCrowdRecommendMapper.queryUserCrowdRecommendList(record);
	}
}
