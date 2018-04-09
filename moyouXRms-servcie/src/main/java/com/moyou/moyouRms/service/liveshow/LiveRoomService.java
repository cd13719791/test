package com.moyou.moyouRms.service.liveshow;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.LiveRecommend;
import com.moyou.moyouRms.model.liveshow.LiveRecordInfo;
import com.moyou.moyouRms.model.liveshow.LiveRoom;
import com.moyou.moyouRms.response.ApiResult;

public interface LiveRoomService {
	/**
	 * 获得LiveRoom数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	long getLiveRoomRowCount(Assist assist);

	/**
	 * 获得LiveRoom数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	List<LiveRoom> selectLiveRoom(Assist assist);

	/**
	 * 获得一个LiveRoom对象,以参数LiveRoom对象中不为空的属性作为条件进行查询
	 * 
	 * @param obj
	 * @return
	 */
	LiveRoom selectLiveRoomByObj(LiveRoom obj);

	/**
	 * 通过LiveRoom的id获得LiveRoom对象
	 * 
	 * @param id
	 * @return
	 */
	LiveRoom selectLiveRoomById(Integer id);

	/**
	 * 插入LiveRoom到数据库,包括null值
	 * 
	 * @param value
	 * @return
	 */
	int insertLiveRoom(LiveRoom value);

	/**
	 * 插入LiveRoom中属性值不为null的数据到数据库
	 * 
	 * @param value
	 * @return
	 */
	int insertNonEmptyLiveRoom(LiveRoom value);

	/**
	 * 通过LiveRoom的id删除LiveRoom
	 * 
	 * @param id
	 * @return
	 */
	int deleteLiveRoomById(Integer id);

	/**
	 * 通过辅助工具Assist的条件删除LiveRoom
	 * 
	 * @param assist
	 * @return
	 */
	int deleteLiveRoom(Assist assist);

	/**
	 * 通过LiveRoom的id更新LiveRoom中的数据,包括null值
	 * 
	 * @param enti
	 * @return
	 */
	int updateLiveRoomById(LiveRoom enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveRoom中的数据,包括null值
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateLiveRoom(LiveRoom value, Assist assist);

	/**
	 * 通过LiveRoom的id更新LiveRoom中属性不为null的数据
	 * 
	 * @param enti
	 * @return
	 */
	int updateNonEmptyLiveRoomById(LiveRoom enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveRoom中属性不为null的数据
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateNonEmptyLiveRoom(LiveRoom value, Assist assist);

	/**
	 * 初始化开播记录列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<LiveRecordInfo> selectLiveRecordList(PageBean pageBean);

	/**
	 * 查询主播开播统计数据
	 * 
	 * @return
	 */
	Map<String, Object> selectLiveRecordCount();

	/**
	 * 直播 房间管理初始化
	 * 
	 * @param pageBean
	 * @return
	 */
	List<LiveRoom> selectLiveRoomList(PageBean pageBean);

	/**
	 * 查询统计数据
	 * 
	 * @return
	 */
	Map<String, Object> selectLiveRoomCount();

	/**
	 * 查询推荐统计数据
	 * @return
	 */
	Integer selectLiveRoomRecommentCount(Integer recommendState);
	/**
	 * 查询推荐剩余排序
	 * 
	 * @return
	 */
	List<Map<String, Integer>> selectLiveRoomRecommentNumber();

	/**
	 * 推荐直播间
	 * 
	 * @param map
	 * @return
	 */
	ApiResult updateliveroomrecomment(Map<String, Object> map);

	/**
	 * 推荐直播间列表初始化
	 * 
	 * @param pageBean
	 * @return
	 */
	List<LiveRecommend> selectLiveRecommendList(PageBean pageBean);

}