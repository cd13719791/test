package com.moyou.moyouRms.controller.citymanager;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.everyActivityRule.EveryActivityRule;
import com.moyou.moyouRms.model.everyactivity.EveryActivity;
import com.moyou.moyouRms.model.goldSignConfigure.GoldSignConfigure;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.userGoldRule.UserGoldRule;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.everyActivityRule.EveryActivityRuleService;
import com.moyou.moyouRms.service.everyactivity.EveryActivityService;
import com.moyou.moyouRms.service.goldSignConfigure.GoldSignConfigureService;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.userGoldRule.UserGoldRuleService;
import com.moyou.moyouRms.spring.SpringBeanUtils;
import com.moyou.moyouRms.util.JsonUtil;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年8月2日 下午4:07:10 类说明 金币购买 消耗配置
 */
@RestController
@RequestMapping("goldset")
public class GoldSetController {
	@Resource
	private UserGoldRuleService userGoldRuleService;
	@Resource
	private GoldSignConfigureService goldSignConfigureService;
	@Resource
	private CommonResourceService commonResourceService;
	@Resource
	EveryActivityRuleService everyActivityRuleService;
	@Resource
	private EveryActivityService everyActivityService;

	/**
	 * 查询充值金币消耗规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectgoldset", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectGoldSet() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userGoldRuleService.selectGoldSet());
	}

	/**
	 * 修改充值金币消耗规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updaterechargegold", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateRechargeGold(@RequestBody UserGoldRule userGoldRule) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userGoldRuleService.updateRechargeGold(userGoldRule));
	}

	/**
	 * 添加充值金币消耗规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertchargegold", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertChargeGold(@RequestBody UserGoldRule userGoldRule) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userGoldRuleService.insertChargeGold(userGoldRule));
	}

	/**
	 * 添加登录获取金币规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertlogingold", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertLoginGold(@RequestBody GoldSignConfigure goldSignConfigure) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				goldSignConfigureService.insertSelective(goldSignConfigure));
	}

	/**
	 * 修改登录获取金币规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatelogingold", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateLoginGold(@RequestBody GoldSignConfigure goldSignConfigure) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				goldSignConfigureService.updateByPrimaryKeySelective(goldSignConfigure));
	}

	/**
	 * 删除登录获取金币规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deletelogingold", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteLoginGold(@RequestBody GoldSignConfigure goldSignConfigure) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				goldSignConfigureService.deleteByPrimaryKey(goldSignConfigure.getId()));
	}

	/**
	 * 添加供求金币规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertadsupplygold", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertAdsupplyGold(@RequestBody CommonResource commonResource) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				commonResourceService.insertcommonResource(commonResource));
	}

	/**
	 * 修改供求金币规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateadsupplygold", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateAdsupplyGold(@RequestBody CommonResource commonResource) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				commonResourceService.updateByPrimaryKeySelective(commonResource));
	}

	/**
	 * 修改供求金币规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteadsupplygold", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteAdsupplyGold(@RequestBody CommonResource commonResource) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				commonResourceService.deleteByPrimaryKey(commonResource.getId()));
	}

	/**
	 * 每日活动设置 模块初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selecteveryactivityrulelist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectEveryActivityRuleList() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				everyActivityRuleService.selectEveryActivityRuleList());
	}

	/**
	 * 每日活动设置 修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateeveryactivityrule", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateEveryActivityRule(
			@RequestBody EveryActivityRule everyActivityRule) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				everyActivityRuleService.updateByPrimaryKeySelective(everyActivityRule));
	}

	/**
	 * 每日活动 初始化
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selecteveryactivitylist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectEveryActivity() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		if (stringRedisTemplate.hasKey(CONSTANT.SYS_EVERY_ACTIVITY)) {
			return new ApiResult(RESPONSE.SUCCESS, "成功", (EveryActivity) JsonUtil.toObject(
					stringRedisTemplate.opsForValue().get(CONSTANT.SYS_EVERY_ACTIVITY),
					EveryActivity.class));
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功", everyActivityService.selectEveryActivityList());
	}

	/**
	 * 每日活动 修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateEveryActivity", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateEveryActivity(@RequestBody EveryActivity everyActivity) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				everyActivityService.updateByPrimaryKeySelective(everyActivity));
	}
}
