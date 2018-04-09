package com.moyou.moyouRms.service.liveshow;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.H5LiveShow;
import com.moyou.moyouRms.model.liveshow.LiveUserInfo;
import com.moyou.moyouRms.model.liveshow.LiveUserListResult;

public interface LiveUserInfoService {
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
	int updateLiveUserInfo(LiveUserInfo value, Assist assist);

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
	int updateNonEmptyLiveUserInfo(LiveUserInfo value, Assist assist);

	/**
	 * 初始化主播列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<LiveUserListResult> selectLiveUserList(PageBean pageBean);

	/**
	 * 查询主播统计数据
	 * 
	 * @return
	 */
	Map<String, Object> selectLiveUserCount();

	/**
	 * 查看主播详情
	 * 
	 * @param userId
	 * @return
	 */
	LiveUserListResult selectliveUserInfo(Integer userId);

	/**
	 * 检查是不是主播
	 * 
	 * @param userId
	 * @return
	 */
	Integer checkIsLiveUser(Integer userId);

	/**
	 * 查询剩余推荐排序
	 * 
	 * @return
	 */
	List<Map<String, Integer>> selectLiveUserRecommentNumber();

	/**
	 * 限制主播
	 * 
	 * @param enti
	 * @return
	 */
	Integer updateLimitLiveUser(LiveUserInfo enti);

	/**
	 * 备注
	 * 
	 * @param enti
	 * @return
	 */
	Integer updateLiveUserRemarkById(LiveUserInfo enti);

	/**
	 * 停播主播
	 * 
	 * @param enti
	 * @return
	 */
	public Integer updateStopLiveUser(LiveUserInfo enti);

	H5LiveShow queryH5ShareLiveShow(Map<String, Object> map);
}