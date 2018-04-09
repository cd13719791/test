package com.moyou.moyouRms.service.userGoldRule;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.userGoldRule.UserGoldRule;
import com.moyou.moyouRms.response.ApiResult;

public interface UserGoldRuleService {
	/**
	 * 获取金币规则
	 * 
	 * @param id
	 * @return
	 */
	UserGoldRule getUserGoldRule(Integer id);

	int deleteByPrimaryKey(Integer id);

	int insert(UserGoldRule record);

	int insertSelective(UserGoldRule record);

	UserGoldRule selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserGoldRule record);

	int updateByPrimaryKey(UserGoldRule record);

	/**
	 * 初始化金币设置页面
	 * 
	 * @return
	 */
	Map<String, List<Object>> selectGoldSet();

	/**
	 * 修改金币 充值规则
	 * 
	 * @param userGoldRule
	 * @return
	 */
	int updateRechargeGold(UserGoldRule userGoldRule);

	/**
	 * 根据系统类型查询金币消费规则
	 * 
	 * @param valueOf
	 * @return
	 */
	ApiResult selectUserGoldRuleByType(Integer valueOf);

	/**
	 * 添加充值规则
	 * 
	 * @param userGoldRule
	 * @return
	 */
	int insertChargeGold(UserGoldRule userGoldRule);

	Map<String, List<Object>> selectGoldSetForH5();

}
