package com.moyou.moyouRms.service.liveshow;
import java.util.List;

import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.model.liveshow.LiveUserLevelSet;
public interface LiveUserLevelSetService{
	/**
	 * 获得LiveUserLevelSet数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    long getLiveUserLevelSetRowCount(Assist assist);
	/**
	 * 获得LiveUserLevelSet数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * @param assist
	 * @return
	 */
    List<LiveUserLevelSet> selectLiveUserLevelSet(Assist assist);
	/**
	 * 获得一个LiveUserLevelSet对象,以参数LiveUserLevelSet对象中不为空的属性作为条件进行查询
	 * @param obj
	 * @return
	 */
    LiveUserLevelSet selectLiveUserLevelSetByObj(LiveUserLevelSet obj);
	/**
	 * 通过LiveUserLevelSet的id获得LiveUserLevelSet对象
	 * @param id
	 * @return
	 */
    LiveUserLevelSet selectLiveUserLevelSetById(Integer id);
	/**
	 * 插入LiveUserLevelSet到数据库,包括null值
	 * @param value
	 * @return
	 */
    int insertLiveUserLevelSet(LiveUserLevelSet value);
	/**
	 * 插入LiveUserLevelSet中属性值不为null的数据到数据库
	 * @param value
	 * @return
	 */
    int insertNonEmptyLiveUserLevelSet(LiveUserLevelSet value);
	/**
	 * 通过LiveUserLevelSet的id删除LiveUserLevelSet
	 * @param id
	 * @return
	 */
    int deleteLiveUserLevelSetById(Integer id);
	/**
	 * 通过辅助工具Assist的条件删除LiveUserLevelSet
	 * @param assist
	 * @return
	 */
    int deleteLiveUserLevelSet(Assist assist);
	/**
	 * 通过LiveUserLevelSet的id更新LiveUserLevelSet中的数据,包括null值
	 * @param enti
	 * @return
	 */
    int updateLiveUserLevelSetById(LiveUserLevelSet enti);
 	/**
	 * 通过辅助工具Assist的条件更新LiveUserLevelSet中的数据,包括null值
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateLiveUserLevelSet(LiveUserLevelSet value,  Assist assist);
	/**
	 * 通过LiveUserLevelSet的id更新LiveUserLevelSet中属性不为null的数据
	 * @param enti
	 * @return
	 */
    int updateNonEmptyLiveUserLevelSetById(LiveUserLevelSet enti);
 	/**
	 * 通过辅助工具Assist的条件更新LiveUserLevelSet中属性不为null的数据
	 * @param value
	 * @param assist
	 * @return
	 */
    int updateNonEmptyLiveUserLevelSet(LiveUserLevelSet value, Assist assist);
}