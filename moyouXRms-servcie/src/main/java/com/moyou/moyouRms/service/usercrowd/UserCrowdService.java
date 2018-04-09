package com.moyou.moyouRms.service.usercrowd;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.usercrowd.UserCrowd;
import com.moyou.moyouRms.model.usercrowd.UserCrowdInfoResult;
import com.moyou.moyouRms.model.usercrowd.UserCrowdPage;
import com.moyou.moyouRms.response.ApiResult;

public interface UserCrowdService {
	/*
	 * 查询群总数+新增群
	 */
	UserCrowd queryCountUserCrowd();

	/**
	 * 创建群
	 * 
	 * @param record
	 * @return
	 */
	int addCrowd(UserCrowd record);

	/**
	 * 群運營初始化
	 * 
	 * @param pagebean
	 * @return
	 */
	List<UserCrowd> queryUserCrowdList(UserCrowdPage userCrowdPage);

	/**
	 * 根据群编号查询群信息
	 * 
	 * @param crowdId
	 * @return
	 */
	UserCrowd queryUserCrowdInfo(Integer crowdId);

	/**
	 * 根据加入群的编号查询群成员
	 * 
	 * @param crowdId
	 * @return
	 */
	List<UserInfo> queryUserCrowdMembersList(Page page);

	/**
	 * 根据群编号解散群（假删除）
	 * 
	 * @param crowdId
	 * @return
	 */
	int updateUserCrowdState(Integer crowdId);

	/**
	 * 修改群资料
	 * 
	 * @param record
	 * @return
	 */
	ApiResult updateByPrimaryKeySelective(UserCrowd record);

	/**
	 * 添加群成员数
	 * 
	 * @return
	 */
	ApiResult addCrowdUser(UserCrowd userCrowd);
	
	 /**
     * 根据用户ID删除群成员
     * @param crowdPkid
     * @return
     */
	ApiResult deleteMemberCrowd(UserCrowd userCrowd);
	/**
	 * 查询群列表
	 * @param userCrowd
	 * @return
	 */
	List<UserCrowd> queryUserCrowdList(UserCrowd userCrowd);
	
	List<UserInfo> queryCrowdMemberList(Long crowdPkIds);

	Object addCrowdUser2();
	/**
	 * 判断群id是否存在
	 * @param moyouId
	 * @return
	 */
	boolean isExistCrowdId(String crowdId);
	/**
	 * 推荐群 或者取消推荐
	 * @param map
	 * @return
	 */
	int updateCrowdRecommend(Map<String, Object> map);

	UserCrowdInfoResult selectCrowdInfo(Map<String, Object> map);
}
