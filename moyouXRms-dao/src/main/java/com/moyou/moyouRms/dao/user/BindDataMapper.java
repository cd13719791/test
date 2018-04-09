package com.moyou.moyouRms.dao.user;

import com.moyou.moyouRms.model.user.BindData;

public interface BindDataMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(BindData record);

	int insertSelective(BindData record);

	BindData selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(BindData record);

	int updateByPrimaryKey(BindData record);

	/**
	 * 根据userId查询用户是否绑定了微信
	 * 
	 * @param userId
	 * @return
	 */
	String queryBindData(Integer userId);

	/**
	 * 根据用户ID查询绑定的用户详情
	 * 
	 * @param userId
	 * @return
	 */
	BindData queryBindDataInfo(Integer userId);

	/**
	 * 查询用户微信绑定数据
	 * 
	 * @param userId
	 * @return
	 */
	BindData queryBindDataInfoByUserIdForWEIXIN(Integer userId);
}
