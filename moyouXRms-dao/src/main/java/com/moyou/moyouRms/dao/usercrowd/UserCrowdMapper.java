package com.moyou.moyouRms.dao.usercrowd;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.usercrowd.UserCrowd;
import com.moyou.moyouRms.model.usercrowd.UserCrowdInfoResult;

public interface UserCrowdMapper {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 创建群
	 * 
	 * @param record
	 * @return
	 */
	int insert(UserCrowd record);

	int insertSelective(UserCrowd record);

	UserCrowd selectByPrimaryKey(Integer id);

	/**
	 * 修改群资料
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(UserCrowd UserCrowd);

	/**
	 * 根据群主键ID修改群成员数
	 * 
	 * @param id
	 * @return
	 */
	int updateByCrowdMember(UserCrowd UserCrowd);

	/**
	 * 修改群成员数
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeyWithBLOBs(UserCrowd record);

	int updateCrowdMember(UserCrowd record);

	int updateByPrimaryKey(UserCrowd record);

	/**
	 * 修改群专用
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeyCrowd(UserCrowd record);

	/*
	 * 查询群总数
	 */
	Integer queryCountUserCrowd();

	/*
	 * 查询新增群
	 */
	Integer queryNewUserCrowd();

	/**
	 * 群運營初始化
	 * 
	 * @param pagebean
	 * @return
	 */
	List<UserCrowd> queryUserCrowdList(PageBean pagebean);

	List<UserCrowd> queryUserCrowd(UserCrowd userCrowd);

	/**
	 * 查询CrowdId列最大值
	 * 
	 * @return
	 */
	Integer queryMaxCrowdId();

	/**
	 * 根据群编号查询群信息
	 * 
	 * @param crowdId
	 * @return
	 */
	UserCrowd queryUserCrowdInfo(Integer crowdId);

	/**
	 * 根据群编号解散群（假删除）
	 * 
	 * @param crowdId
	 * @return
	 */
	int updateUserCrowdState(Integer crowdId);

	/**
	 * 根据群编号查询群成员有多少个
	 * 
	 * @param crowdId
	 * @return
	 */
	Integer queryCrowdCountFakeUser(Integer id);

	/**
	 * 根据群编号查询群成员有多少个
	 * 
	 * @param crowdId
	 * @return
	 */
	Integer queryCrowdCountAllUser(Integer id);

	/**
	 * 根据群id查询群的环信ID
	 * 
	 * @param crowdPkId
	 * @return
	 */
	String queryEasemodCroweId(Integer crowdPkId);

	List<UserInfo> queryCrowdMemberList(Long crowdPkId);

	String queryContaninsCrowdId(@Param("crowdId") String crowdId);

	UserCrowdInfoResult selectCorwdInfo(Integer id);

	List<Integer> selectSortList();

	Integer queryRecommedCount();
}
