package com.moyou.moyouRms.service.statistics;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.model.statistics.UserFundRegulation;

public interface UserFundRegulationService {
	/**
	 * 获得UserFundRegulation数据的总行数,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	long getUserFundRegulationRowCount(Assist assist);

	/**
	 * 获得UserFundRegulation数据集合,可以通过辅助工具Assist进行条件查询,如果没有条件则传入null
	 * 
	 * @param assist
	 * @return
	 */
	List<UserFundRegulation> selectUserFundRegulation(Assist assist);

	/**
	 * 获得一个UserFundRegulation对象,以参数UserFundRegulation对象中不为空的属性作为条件进行查询
	 * 
	 * @param obj
	 * @return
	 */
	UserFundRegulation selectUserFundRegulationByObj(UserFundRegulation obj);

	/**
	 * 通过UserFundRegulation的id获得UserFundRegulation对象
	 * 
	 * @param id
	 * @return
	 */
	UserFundRegulation selectUserFundRegulationById(Integer id);

	/**
	 * 插入UserFundRegulation到数据库,包括null值
	 * 
	 * @param value
	 * @return
	 */
	int insertUserFundRegulation(UserFundRegulation value);

	/**
	 * 插入UserFundRegulation中属性值不为null的数据到数据库
	 * 
	 * @param value
	 * @return
	 */
	int insertNonEmptyUserFundRegulation(UserFundRegulation value);

	/**
	 * 通过UserFundRegulation的id删除UserFundRegulation
	 * 
	 * @param id
	 * @return
	 */
	int deleteUserFundRegulationById(Integer id);

	/**
	 * 通过辅助工具Assist的条件删除UserFundRegulation
	 * 
	 * @param assist
	 * @return
	 */
	int deleteUserFundRegulation(Assist assist);

	/**
	 * 通过UserFundRegulation的id更新UserFundRegulation中的数据,包括null值
	 * 
	 * @param enti
	 * @return
	 */
	int updateUserFundRegulationById(UserFundRegulation enti);

	/**
	 * 通过辅助工具Assist的条件更新UserFundRegulation中的数据,包括null值
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateUserFundRegulation(UserFundRegulation value, Assist assist);

	/**
	 * 通过UserFundRegulation的id更新UserFundRegulation中属性不为null的数据
	 * 
	 * @param enti
	 * @return
	 */
	int updateNonEmptyUserFundRegulationById(UserFundRegulation enti);

	/**
	 * 通过辅助工具Assist的条件更新UserFundRegulation中属性不为null的数据
	 * 
	 * @param value
	 * @param assist
	 * @return
	 */
	int updateNonEmptyUserFundRegulation(UserFundRegulation value, Assist assist);

	/**
	 * 资金池统计
	 * 
	 * @param record
	 * @return
	 */
	List<UserFundRegulation> queryUserFundRegulationList(Map<String, Object> record);
}