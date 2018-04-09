package com.moyou.moyouRms.controller.userfund;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.LogEnum;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.intercept.Log;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.commonReason.CommonReason;
import com.moyou.moyouRms.model.liveshow.LiveUserConvert;
import com.moyou.moyouRms.model.po.system.account.Account;
import com.moyou.moyouRms.model.userfund.SystemRewardModel;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.commonreason.CommonReasonService;
import com.moyou.moyouRms.service.liveshow.LiveUserConvertService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;

@Controller
@ResponseBody
@RequestMapping(value = "/userFund")
public class UserFundTakeController extends BaseController {
	@Resource
	private UserFundLogService userFundLogService;
	@Resource
	private UserFundService userFundService;
	@Resource
	private CommonReasonService commonReasonService;
	@Resource
	private LiveUserConvertService liveUserConvertService;
	@Resource
	private UserService userService;

	/**
	 * 初始化提现申请
	 * 
	 * @param state
	 *            =1 已处理 state=0 未处理 auditStatus 2通过3未通过,
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryUserFundByState", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundByState(@RequestBody Map<String, Object> map) {
		PageBean pb = super.getJsonWrapPageBean(map);
		pb.setResults(userFundLogService.selectUserFundLogByState(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 审核提现记录
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/updateUserFundState", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserFundState(@RequestBody UserFundLog userFundLog) {
		Assert.notNull(userFundLog.getAudit(), "参数不能为空");
		Assert.notNull(userFundLog.getId(), "参数不能为空");
		Account account = null;
		try {
			account = super.getAdminUser();
		} catch (Exception e) {
			account = new Account();
			account.setAccountId("未找到");
			account.setName("未登录");
			return null;
		}
		if (account == null) {
			account = new Account();
			account.setAccountId("未找到");
			account.setName("未登录");
			return null;
		}
		return userFundLogService.updateUserFundState(userFundLog.getId(), userFundLog.getAudit(),
				account, userFundLog.getReasonText());
		// if (index == 1) {
		// return new ApiResult(RESPONSE.SUCCESS, "成功");
		// } else {
		// return new ApiResult(RESPONSE.ERROR, "失败");
		// }
	}

	/**
	 * 查看详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryUserFundCommonReasonByUserFundTakeId", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundCommonReasonByUserFundTakeId(
			@RequestBody Map<String, String> map) {
		Assert.notNull(map.get("id"), "参数不能为空");
		Integer id = Integer.valueOf(map.get("id") + "");
		CommonReason commonReason = new CommonReason();
		commonReason.setModeId(id);
		commonReason.setModeType(CommonReason.USER_FUND_TAKE);
		commonReason = commonReasonService.selectCommonReasonByObjectId(commonReason);
		return new ApiResult(RESPONSE.SUCCESS, "成功", commonReason);
	}

	/**
	 * 后台交易记录初始化
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryUserFundLogByPageBean", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundLogByPageBean(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		PageBean pb = this.getJsonWrapPageBean(map);
		List<UserFundLog> list = userFundLogService.queryUserFundLogByPageBean(pb);
		list.forEach(s -> {
			if (StringUtil.isNotEmpty(s.getExtnd())) {
				s.setParam(JsonUtil.toMap(s.getExtnd()));
			}
		});
		pb.setResults(list);
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 资金池统计
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryGoldAndFundCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryGoldAndFundCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundLogService.queryGoldAndFundCount());
	}

	/**
	 * 交易记录备注更新
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateUserFundLogRemark", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserFundLogRemark(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		int id = Integer.valueOf(map.get("id").toString());
		String msg = map.get("msg").toString();
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundLogService.updateUserFundLogRemark(id,
				msg));
	}

	/**
	 * 交易记录查询
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/queryUserFundLogCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundLogCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundLogService.queryUserFundLogCount());
	}

	/**
	 * 系统打赏 （类似机器人打赏 后台自动运行）
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/insertSystemReward", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertSystemReward(
			@RequestBody SystemRewardModel systemRewardModel) {
		Account a = this.getAdminUser();
		if (a != null) {
			systemRewardModel.setAdmin(a.getLoginName());
		} else {
			systemRewardModel.setAdmin("未登录");
		}
		try {
			return userFundService.insertSystemReward(systemRewardModel);
		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResult(RESPONSE.ERROR, "执行太多啦！", e.getMessage());
		}
	}

	/**
	 * 初始化系统打赏
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectSystemRewardLog", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectSystemRewardLog(@RequestBody Map<String, Object> map) {
		PageBean pb = this.getJsonWrapPageBean(map);
		pb.setResults(userFundLogService.selectSystemRewardLog(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 初始化系统打赏统计信息
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectSystemRewardLogCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectSystemRewardLogCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userFundLogService.selectSystemRewardLogCount());
	}

	/**
	 * 系统打赏详情
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectSystemRewardLogById", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectSystemRewardLogById(@RequestBody Map<String, Object> map) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userFundLogService.selectSystemRewardLogById(map));
	}

	/**
	 * 系统打赏 修改备注
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/updateSystemRewardLogDesc", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateSystemRewardLogById(@RequestBody Map<String, Object> map) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userFundLogService.updateSystemRewardLogById(map));
	}

	/**
	 * 系统打赏 执行条数查询
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectRewardCountNumber", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectRewardCountNumber(@RequestBody Map<String, Object> map) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userFundLogService.selectRewardCountNumber(map));
	}

	/**
	 * 系统打赏 查询统计信息 最近打赏 ， 系统余额
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectRewardSystemInfoCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectRewardSystemInfoCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundService.selectRewardSystemInfoCount());
	}

	/**
	 * 请求验证码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getIdentifyingCode", method = RequestMethod.POST)
	public @ResponseBody ApiResult getIdentifyingCode(@RequestBody Map<String, Object> money) {
		Account account = this.getAdminUser();
		if (account == null) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		} else if (StringUtil.isEmpty(account.getPhone())) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "电话尚未登记");
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundService.getIdentifyingCode(
				account.getPhone(), account.getName(),
				Integer.valueOf(money.get("money").toString())));
	}

	/**
	 * 充值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertSystemFund", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertSystemFund(@RequestBody Map<String, Object> param) {
		Account account = this.getAdminUser();
		if (account == null) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "尚未登录");
		} else if (StringUtil.isEmpty(account.getPhone())) {
			return new ApiResult(ResponseEnum.ERROR.getValue(), "电话尚未登记");
		}
		param.put("phone", account.getPhone());
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundService.insertSystemFund(param,
				this.getRequest(), account.getLoginName()));
	}

	/**
	 * 初始化充值记录
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectSystemFundLog", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectSystemFundLog(@RequestBody Map<String, Object> param) {
		PageBean pb = this.getJsonWrapPageBean(param);
		pb.setResults(userFundService.selectSystemFundLog(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 获取系统余额
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getSystemFund", method = RequestMethod.POST)
	public @ResponseBody ApiResult getSystemFund() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundService.getCompanySysFund());
	}

	/**
	 * 获取系统充值总额
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectsystemfundsum", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectSystemFundSum() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFundService.selectSystemFundSum());
	}

	/**
	 * 查询主播兑换记录统计数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectuserconvertcount", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserConvertCount() {
		return new ApiResult(ResponseEnum.SUCCESS.getValue(), "成功",
				liveUserConvertService.selectUserConvertCount());
	}

	/**
	 * 修改主播兑换记录
	 * 
	 * @param id
	 *            state=1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateuserconvert", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserConvert(@RequestBody LiveUserConvert liveUserConvert) {
		liveUserConvert.setState(RESPONSE.SUCCESS);// 操作状态 0未处理 1已处理
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserConvertService.updateNonEmptyLiveUserConvertById(liveUserConvert));
	}

	/**
	 * 主播兑换记录初始化
	 * 
	 * @param id
	 *            state=1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectuserconvertlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserConvertList(@RequestBody Map<String, Object> map) {
		Assist assist = getJsonWrapAssist(map);
		assist.setRequires(Assist.andEq("state", Integer.valueOf(map.get("state").toString())));
		return new ApiResult(RESPONSE.SUCCESS, "成功", new PageBean(
				(int) liveUserConvertService.getLiveUserConvertRowCount(assist),
				liveUserConvertService.selectLiveUserConvert(assist)));
	}

	/**
	 * 修改用户金币
	 * 
	 * @param map
	 * @return
	 */
	@Log(operationType = "修改操作:", operationName = "修改用户金币", operationLogEnum = LogEnum.UPDATE_GOLD)
	@RequestMapping(value = "/updateUserGold", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserGold(@RequestBody Map<String, Object> map) {
		userService.updateUserGold(map, this.getRequest(), this.getAdminUser());
		return new ApiResult(ResponseEnum.SUCCESS, "成功");
	}
}
