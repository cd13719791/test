package com.moyou.moyouRms.dao.liveshow;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.H5LiveShow;
import com.moyou.moyouRms.model.liveshow.LiveUserInfo;
import com.moyou.moyouRms.model.liveshow.LiveUserListResult;

public interface LiveUserInfoDao {
	/**
	 * 获得LiveUserInfo数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	long getLiveUserInfoRowCount(Assist assist);

	/**
	 * 获得LiveUserInfo数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	List<LiveUserInfo> selectLiveUserInfo(Assist assist);

	/**
	 * 获得一个LiveUserInfo对象,以参数LiveUserInfo对象中不为空的属性作为条件进行查询
	 * 
	 * @param obj
	 * @return
	 */
	LiveUserInfo selectLiveUserInfoByObj(LiveUserInfo obj);

	/**
	 * 通过LiveUserInfo的id获得LiveUserInfo对象
	 * 
	 * @param id
	 * @return
	 */
	LiveUserInfo selectLiveUserInfoById(Integer id);

	/**
	 * 插入LiveUserInfo到数据库,包括null值
	 * 
	 * @param value
	 * @return
	 */
	int insertLiveUserInfo(LiveUserInfo value);

	/**
	 * 插入LiveUserInfo中属性值不为null的数据到数据库
	 * 
	 * @param value
	 * @return
	 */
	int insertNonEmptyLiveUserInfo(LiveUserInfo value);

	/**
	 * 通过LiveUserInfo的id删除LiveUserInfo
	 * 
	 * @param id
	 * @return
	 */
	int deleteLiveUserInfoById(Integer id);

	/**
	 * 通过辅助工具Assist的条件删除LiveUserInfo
	 * 
	 * @param assist
	 * @return
	 */
	int deleteLiveUserInfo(Assist assist);

	/**
	 * 通过LiveUserInfo的id更新LiveUserInfo中的数据,包括null值
	 * 
	 * @param enti
	 * @return
	 */
	int updateLiveUserInfoById(LiveUserInfo enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveUserInfo中的数据,包括null值
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateLiveUserInfo(@Param("enti") LiveUserInfo value, @Param("assist") Assist assist);

	/**
	 * 通过LiveUserInfo的id更新LiveUserInfo中属性不为null的数据
	 * 
	 * @param enti
	 * @return
	 */
	int updateNonEmptyLiveUserInfoById(LiveUserInfo enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveUserInfo中属性不为null的数据
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateNonEmptyLiveUserInfo(@Param("enti") LiveUserInfo value, @Param("assist") Assist assist);

	List<LiveUserListResult> selectLiveUserList(PageBean pageBean);

	Map<String, Object> selectLiveUserCount();

	LiveUserListResult selectliveUserInfo(@Param("userId") Integer userId);

	Integer checkIsLiveUser(@Param("userId") Integer userId);

	Integer selectRecommentCount();

	List<Integer> selectLiveUserRecommentNumber();

	H5LiveShow queryH5ShareLiveShow(@Param("id") Integer id);
}