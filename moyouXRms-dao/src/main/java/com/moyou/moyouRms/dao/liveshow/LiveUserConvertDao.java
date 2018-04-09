package com.moyou.moyouRms.dao.liveshow;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.model.liveshow.LiveUserConvert;

public interface LiveUserConvertDao {
	/**
	 * 获得LiveUserConvert数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	long getLiveUserConvertRowCount(Assist assist);

	/**
	 * 获得LiveUserConvert数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	List<LiveUserConvert> selectLiveUserConvert(Assist assist);

	/**
	 * 获得一个LiveUserConvert对象,以参数LiveUserConvert对象中不为空的属性作为条件进行查询
	 * 
	 * @param obj
	 * @return
	 */
	LiveUserConvert selectLiveUserConvertByObj(LiveUserConvert obj);

	/**
	 * 通过LiveUserConvert的id获得LiveUserConvert对象
	 * 
	 * @param id
	 * @return
	 */
	LiveUserConvert selectLiveUserConvertById(Integer id);

	/**
	 * 插入LiveUserConvert到数据库,包括null值
	 * 
	 * @param value
	 * @return
	 */
	int insertLiveUserConvert(LiveUserConvert value);

	/**
	 * 插入LiveUserConvert中属性值不为null的数据到数据库
	 * 
	 * @param value
	 * @return
	 */
	int insertNonEmptyLiveUserConvert(LiveUserConvert value);

	/**
	 * 通过LiveUserConvert的id删除LiveUserConvert
	 * 
	 * @param id
	 * @return
	 */
	int deleteLiveUserConvertById(Integer id);

	/**
	 * 通过辅助工具Assist的条件删除LiveUserConvert
	 * 
	 * @param assist
	 * @return
	 */
	int deleteLiveUserConvert(Assist assist);

	/**
	 * 通过LiveUserConvert的id更新LiveUserConvert中的数据,包括null值
	 * 
	 * @param enti
	 * @return
	 */
	int updateLiveUserConvertById(LiveUserConvert enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveUserConvert中的数据,包括null值
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateLiveUserConvert(@Param("enti") LiveUserConvert value, @Param("assist") Assist assist);

	/**
	 * 通过LiveUserConvert的id更新LiveUserConvert中属性不为null的数据
	 * 
	 * @param enti
	 * @return
	 */
	int updateNonEmptyLiveUserConvertById(LiveUserConvert enti);

	/**
	 * 通过辅助工具Assist的条件更新LiveUserConvert中属性不为null的数据
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateNonEmptyLiveUserConvert(@Param("enti") LiveUserConvert value,
			@Param("assist") Assist assist);

	Map<String, Object> selectUserConvertCount();
}