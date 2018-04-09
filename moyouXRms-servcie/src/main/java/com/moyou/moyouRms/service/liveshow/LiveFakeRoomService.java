package com.moyou.moyouRms.service.liveshow;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.LiveFakeRoom;
import com.moyou.moyouRms.model.liveshow.LiveFakeRoomEdit;
import com.moyou.moyouRms.model.po.task.TaskSeting;

public interface LiveFakeRoomService {
	/**
	 * 获得LiveFakeRoom数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	long getLiveFakeRoomRowCount(Assist assist);

	/**
	 * 获得LiveFakeRoom数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	List<LiveFakeRoom> selectLiveFakeRoom(Assist assist);

	/**
	 * 获得一个LiveFakeRoom对象,以参数LiveFakeRoom对象中不为空的属性作为条件进行查询
	 * 
	 * @param obj
	 * @return
	 */
	LiveFakeRoom selectLiveFakeRoomByObj(LiveFakeRoom obj);

	/**
	 * 通过LiveFakeRoom的id获得LiveFakeRoom对象
	 * 
	 * @param id
	 * @return
	 */
	LiveFakeRoom selectLiveFakeRoomById(Integer id);

	/**
	 * 插入LiveFakeRoom到数据库,包括null值
	 * 
	 * @param value
	 * @return
	 */
	int insertLiveFakeRoom(LiveFakeRoom value);

	/**
	 * 插入LiveFakeRoom中属性值不为null的数据到数据库
	 * 
	 * @param value
	 * @return
	 */
	int insertNonEmptyLiveFakeRoom(LiveFakeRoom value);

	/**
	 * 通过LiveFakeRoom的id删除LiveFakeRoom
	 * 
	 * @param id
	 * @return
	 */
	int deleteLiveFakeRoomById(Integer id);

	/**
	 * 通过辅助工具Assist的条件删除LiveFakeRoom
	 * 
	 * @param assist
	 * @return
	 */
	int deleteLiveFakeRoom(Assist assist);

	/**
	 * 通过LiveFakeRoom的id更新LiveFakeRoom中的数据,包括null值
	 * 
	 * @param enti
	 * @return
	 */
	int updateLiveFakeRoomById(LiveFakeRoom enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveFakeRoom中的数据,包括null值
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateLiveFakeRoom(LiveFakeRoom value, Assist assist);

	/**
	 * 通过LiveFakeRoom的id更新LiveFakeRoom中属性不为null的数据
	 * 
	 * @param enti
	 * @return
	 */
	int updateNonEmptyLiveFakeRoomById(LiveFakeRoom enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveFakeRoom中属性不为null的数据
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateNonEmptyLiveFakeRoom(LiveFakeRoom value, Assist assist);

	/**
	 * 初始化运营直播间列表
	 * 
	 * @param pageBean
	 * @return
	 */
	List<LiveFakeRoom> selectLiveFakeRoomList(PageBean pageBean);

	/**
	 * 查看编辑页面
	 * 
	 * @param id
	 * @return
	 */
	LiveFakeRoomEdit selectLiveFakeRoomEditById(Integer id);

	/**
	 * 查询运营主播统计数据
	 * 
	 * @return
	 */
	Map<String, Object> selectLiveUserCount();

	/**
	 * 初始化运营直播间设置
	 * 
	 * @param pageBean
	 * @return
	 */
	TaskSeting selectLiveFakeRoomSet();

	/**
	 * 修改假直播都为不推荐状态
	 * 
	 * @return
	 */
	int updateLiveFakeNoRecomment();

	/**
	 * 分页随机查询有多少个运营直播
	 * 
	 * @param executeDataCount
	 * @return
	 */
	List<LiveFakeRoom> selectLiveFakeRoomLimit(int executeDataCount);
}