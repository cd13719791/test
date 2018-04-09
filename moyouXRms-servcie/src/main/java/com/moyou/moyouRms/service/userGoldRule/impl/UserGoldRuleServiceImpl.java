package com.moyou.moyouRms.service.userGoldRule.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.userGoldRule.UserGoldRuleMapper;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.userGoldRule.UserGoldRule;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.goldSignConfigure.GoldSignConfigureService;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.userGoldRule.UserGoldRuleService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年8月2日 下午4:12:31 类说明
 */
@Service
public class UserGoldRuleServiceImpl implements UserGoldRuleService {
	@Resource
	private UserGoldRuleMapper userGoldRuleMapper;
	@Resource
	private GoldSignConfigureService goldSignConfigureService;
	@Resource
	private CommonResourceService commonResourceService;

	@Override
	public UserGoldRule getUserGoldRule(Integer id) {
		return userGoldRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {

		return userGoldRuleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserGoldRule record) {

		return userGoldRuleMapper.insert(record);
	}

	@Override
	public int insertSelective(UserGoldRule record) {

		return userGoldRuleMapper.insertSelective(record);
	}

	@Override
	public UserGoldRule selectByPrimaryKey(Integer id) {

		return userGoldRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserGoldRule record) {

		return userGoldRuleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserGoldRule record) {

		return userGoldRuleMapper.updateByPrimaryKey(record);
	}

	@Override
	public Map<String, List<Object>> selectGoldSet() {
		Map<String, List<Object>> mapList = new HashMap<String, List<Object>>();
		mapList.put("android", userGoldRuleMapper
				.selectGoldRuleByModeRuleType(UserGoldRule.MODETYPE_ANDROID.intValue()));// 安卓消费规则
		mapList.put("ios", userGoldRuleMapper
				.selectGoldRuleByModeRuleType(UserGoldRule.MODETYPE_IOS.intValue()));// ios消费规则
		mapList.put("anchor", userGoldRuleMapper
				.selectGodRuleByModeTypeAndModeRuleType(UserGoldRule.MODE_RULE_TYPE_ANCHOR
						.intValue()));// 主播消费规则
		mapList.put("goldSignConfigure", goldSignConfigureService.selectAllForGoldSet());
		mapList.put(
				"adsupplyFlash",
				commonResourceService
						.selectCommonResourceByObjectTypeForGoldSet((CommonResource.OBJECT_TYPE_ADSUPPLY_FLASH)
								.intValue()));
		mapList.put(
				"adsupplyUpdate",
				commonResourceService
						.selectCommonResourceByObjectTypeForGoldSet((CommonResource.OBJECT_TYPE_ADSUPPLY_UPDATE)
								.intValue()));
		mapList.put(
				"adsupplyPush",
				commonResourceService
						.selectCommonResourceByObjectTypeForGoldSet((CommonResource.OBJECT_TYPE_ADSUPPLY_PUSH)
								.intValue()));
		return mapList;
	}

	@Override
	public int updateRechargeGold(UserGoldRule userGoldRule) {
		return userGoldRuleMapper.updateByPrimaryKeySelective(userGoldRule);
	}

	@Override
	public ApiResult selectUserGoldRuleByType(Integer systemType) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userGoldRuleMapper.selectGoldRuleByModeRuleType(systemType));
	}

	@Override
	public int insertChargeGold(UserGoldRule userGoldRule) {
		// TODO Auto-generated method stub
		userGoldRule.setCreateTime(new Date());
		userGoldRule.setDataStatus(UserGoldRule.Status_NORMAL.intValue());
		return userGoldRuleMapper.insertSelective(userGoldRule);
	}

	@Override
	public Map<String, List<Object>> selectGoldSetForH5() {
		Map<String, List<Object>> mapList = new HashMap<String, List<Object>>();
		mapList.put("android", userGoldRuleMapper
				.selectGoldRuleByModeRuleType(UserGoldRule.MODETYPE_ANDROID.intValue()));// 安卓消费规则
		mapList.put("ios", userGoldRuleMapper
				.selectGoldRuleByModeRuleType(UserGoldRule.MODETYPE_IOS.intValue()));// ios消费规则
		return mapList;
	}
}
