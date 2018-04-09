package com.moyou.moyouRms.service.user;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.UserGoldConvertModel;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.user.UserMsgInfo;

public interface UserInfoService {
	/**
	 * 查询用户信息
	 * 
	 * @param UserInfo
	 * @return
	 */
	UserInfo selectUserInfoByUserId(UserInfo userInfo);

	/**
	 * 添加用户信息
	 * 
	 * @param UserInfo
	 * @return
	 */
	Integer insertUserInfoByUserId(UserInfo userInfo);

	/**
	 * 查询用户头像和昵称
	 * 
	 * @param userId
	 * @return
	 */
	UserInfo selectUsreNickNameAndAvatar(Integer userId);

	/**
	 * 查询用户头像和昵称 环信Id 定位 性别 年龄
	 * 
	 * @param userId
	 * @return
	 */
	UserMsgInfo selectUsreDetailInfo(Integer userId);

	/**
	 * 查询所有被推荐用户
	 * 
	 * @param pb
	 * @return
	 */
	List<UserInfo> queryUserRecommend(PageBean pb);

	/**
	 * 查询所有重复的陌友Id
	 *
	 */
	List<UserInfo> queryUserMoyouIdCount2();

	List<UserInfo> queryByMoyouId(String moyouId);

	int updateMoyouId(UserInfo u);

	/**
	 * 查询所有的真实用户
	 * 
	 * @return
	 */
	List<UserInfo> queryReallyUserIdAndPushChatId();

	/**
	 * 查询推送id
	 * 
	 * @return
	 */
	List<String> queryReallyUserPushChatId();

	/**
	 * 查询用户兑换金币 必须参数
	 * 
	 * @param userId
	 * @return
	 */
	UserGoldConvertModel selectGoldConvertModel(Integer userId);
}
