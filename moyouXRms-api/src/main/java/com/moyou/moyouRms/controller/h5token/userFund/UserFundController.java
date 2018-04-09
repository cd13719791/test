package com.moyou.moyouRms.controller.h5token.userFund;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.user.BindDataService;
import com.moyou.moyouRms.service.userGoldRule.UserGoldRuleService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.userfund.UserFundService;

@Controller
@RequestMapping("/h5token/userFund")
public class UserFundController extends BaseController {

	@Resource
	private UserFundLogService userFundLogService;
	@Resource
	private UserFundService userFundService;
	@Autowired
	private HttpServletRequest request;
	@Resource
	private BindDataService bindDataService;
	@Resource
	UserGoldRuleService userGoldRuleService;

	/**
	 * 用户提现初始化
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/withdrawDeposit", method = RequestMethod.POST)
	public @ResponseBody ApiResult withdrawDeposit(@RequestBody UserFundLog userFundlog) {
		Assert.notNull(userFundlog, "参数不能为空");
		Integer userId = getUserIdByToken(userFundlog.getToken());
		userFundlog.setSearchUserid(userId);
		return userFundService.insertWithdrawDeposit(userFundlog, this.getRequest());
	}

	/**
	 * 用户余额
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/selectUserFund", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserFund(@RequestBody Map param) {
		Assert.notNull(param, "参数不能为空");
		Integer userId = getUserIdByToken(param.get("token") + "");
		logger.info("selectUserFundselectUserFundselectUserFundselectUserFund=" + userId);
		if (userId == 0) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		// Integer userId=Integer.valueOf(param.get("token")+"");
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundService.getUserFundByUserId(userId));
	}

	/**
	 * 
	 * 消耗余额 购买金币
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/inserUserGold", method = RequestMethod.POST)
	public @ResponseBody ApiResult inserUserGold(@RequestBody Map<String, Object> param) {
		Assert.notNull(param, "参数不能为空");
		Integer userId = getUserIdByToken(param.get("token") + "");
		Integer modeId = Integer.valueOf(param.get("modeId").toString());
		if (userId == 0) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		// Integer userId=Integer.valueOf(param.get("token")+"");
		return userFundService.inserUserGold(userId, modeId);
	}

	/**
	 * 金币兑换现金
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/updategoldtomoney", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateGoldToMoney(@RequestBody Map<String, Object> param) {
		Assert.notNull(param, "参数不能为空");
		Integer userId = getUserIdByToken(param.get("token") + "");
		Integer gold = Integer.valueOf(param.get("gold") + "");

		return userFundService.updateGoldToMoney(userId, gold, super.getRequest());
	}

	/**
	 * 查询金币充值规则
	 *
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/selectgoldset", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectGoldSet() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userGoldRuleService.selectGoldSetForH5());
	}
}
