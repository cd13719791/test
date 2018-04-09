package com.moyou.moyouRms.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.UserGoldConvertModel;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.user.UserMsgInfo;
import com.moyou.moyouRms.service.user.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Resource
	private UserInfoMapper userInfoMapper;

	@Override
	public UserInfo selectUserInfoByUserId(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUserInfoByUserId(userInfo);
	}

	@Override
	public Integer insertUserInfoByUserId(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userInfoMapper.insertSelective(userInfo);
	}

	@Override
	public UserInfo selectUsreNickNameAndAvatar(Integer userId) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUsreNickNameAndAvatar(userId);
	}

	@Override
	public List<UserInfo> queryUserRecommend(PageBean pb) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryUserRecommend(pb);
	}

	@Override
	public List<UserInfo> queryUserMoyouIdCount2() {
		// TODO Auto-generated method stub
		return userInfoMapper.queryUserMoyouIdCount2();
	}

	@Override
	public List<UserInfo> queryByMoyouId(String moyouId) {
		// TODO Auto-generated method stub
		return userInfoMapper.queryByMoyouId(moyouId);
	}

	@Override
	public int updateMoyouId(UserInfo u) {
		// TODO Auto-generated method stub
		return userInfoMapper.updateMoyouId(u);
	}

	@Override
	public UserMsgInfo selectUsreDetailInfo(Integer userId) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectUsreDetailInfo(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.moyou.moyouRms.service.user.UserInfoService#
	 * queryReallyUserIdAndPushChatId()
	 */
	@Override
	public List<UserInfo> queryReallyUserIdAndPushChatId() {
		// TODO Auto-generated method stub
		return userInfoMapper.queryReallyUserIdAndPushChatId();
	}

	@Override
	public List<String> queryReallyUserPushChatId() {
		// TODO Auto-generated method stub
		return userInfoMapper.queryReallyUserPushChatId();
	}

	@Override
	public UserGoldConvertModel selectGoldConvertModel(Integer userId) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectGoldConvertModel(userId);
	}
}
