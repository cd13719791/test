package com.moyou.moyouRms.controller.h5token.userFundLog;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.Assist;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.LiveRoom;
import com.moyou.moyouRms.model.liveshow.LiveUserInfo;
import com.moyou.moyouRms.model.userGold.UserGold;
import com.moyou.moyouRms.model.userfund.UserFund;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.liveshow.LiveRoomService;
import com.moyou.moyouRms.service.liveshow.LiveUserConvertService;
import com.moyou.moyouRms.service.liveshow.LiveUserInfoService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.userfund.UserFundService;
import com.moyou.moyouRms.service.usergold.UserGoldService;
import com.moyou.moyouRms.util.StringUtil;

@RestController
@RequestMapping(value = "/h5token/userFundLog")
public class UserFundLogController extends BaseController {

	@Resource
	private UserFundLogService userFundLogService;
	@Resource
	private UserFundService userFundService;
	@Resource
	private LiveRoomService liveRoomService;
	@Resource
	private LiveUserInfoService liveUserInfoService;
	@Resource
	private LiveUserConvertService liveUserConvertService;
	@Resource
	private UserGoldService userGoldService;

	/**
	 * 查询用户所有的交易记录 初始化接口
	 * 
	 * @param record
	 *            modePlusType = 2 直播收益
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectUserFundLogBySelective", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserFundLogBySelective(@RequestBody Map userFundLog) {
		Assert.notNull(userFundLog, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(userFundLog);
		Integer userId = getUserIdByToken(userFundLog.get("token") + "");
		if (checkUserIdErro(userId))
			return new ApiResult(RESPONSE.ERROR, "失败", null);
		pageBean.getConditions().put("payUserId", userId);
		pageBean.getConditions().put("receiveUserId", userId);
		pageBean.getConditions().put("userId", userId);
		List<UserFundLog> list = userFundLogService.selectUserFundLogByUser(pageBean);
		return new ApiResult(RESPONSE.SUCCESS, "成功", list);
	}

	/**
	 * 查询用户 提现 打赏 红包 金币 接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectUserFundLogByParam", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserFundLogByParam(@RequestBody Map userFundLog) {
		Assert.notNull(userFundLog, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(userFundLog);
		Integer userId = getUserIdByToken(userFundLog.get("token") + "");
		if (checkUserIdErro(userId))
			return new ApiResult(RESPONSE.ERROR, "失败", null);
		pageBean.getConditions().put("payUserId", userId);
		pageBean.getConditions().put("receiveUserId", userId);
		pageBean.getConditions().put("userId", userId);
		List<UserFundLog> list = userFundLogService.selectUserFundLogByParam(pageBean);
		pageBean.setResults(list);
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 查询用户直播收益 用了新的crud
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectuserfundlogforzhibo", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserFundLogForZhiBo(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		Integer userId = getUserIdByToken(map.get("token") + "");
		if (checkUserIdErro(userId))
			return new ApiResult(RESPONSE.ERROR, "失败", null);
		Assist assist = getJsonWrapAssist(map);// 初始化assist
		assist.setRequires(Assist.andEq("user_id", userId));// 设置查询条件
		assist.setOrder("id", false);// 设置排序字段 (true=ASC/false=DESC)
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				new PageBean((int) liveRoomService.getLiveRoomRowCount(assist),
						liveRoomService
								.selectLiveRoom(assist)
								.stream()
								.map(LiveRoom::instansToResult)
								.peek(s -> {
									s.setSumEarnings(userFundLogService.selectWacthLiveShowEarn(s
											.getId()));
								}).collect(Collectors.toList())));
	}

	/**
	 * 查询用户直播总金币
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectusergoldforzhibo", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserGoldForZhiBo(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		Integer userId = getUserIdByToken(map.get("token") + "");
		if (checkUserIdErro(userId))
			return new ApiResult(RESPONSE.ERROR, "失败", null);
		return new ApiResult(RESPONSE.SUCCESS, "成功", liveUserInfoService.selectLiveUserInfoByObj(
				new LiveUserInfo(userId)).getUserGold());
	}

	/**
	 * 查询用户直播 兑换记录
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectuserconvertgoldforzhibo", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserConvertGoldForZhiBo(
			@RequestBody Map<String, Object> map) {
		// Assert.notNull(map, "参数不能为空");
		// Integer userId = getUserIdByToken(map.get("token") + "");
		// map.put("userId", userId);
		// PageBean pb = this.getJsonWrapPageBean(map);
		// if (checkUserIdErro(userId))
		// return new ApiResult(RESPONSE.ERROR, "失败", null);
		// pb.setResults(userFundLogService.selectUserFundLogForZhiBo(pb).stream()
		// .map(UserFundLog::instansToResult).collect(Collectors.toList()));
		Assist assist = getJsonWrapAssist(map);// 初始化assist
		Integer userId = getUserIdByToken(map.get("token") + "");
		assist.setRequires(Assist.andEq("user_id", userId), Assist.customRequire(
				"and date_format(create_time,'%Y-%m')=", map.get("time"), null));// 设置查询条件
		assist.setOrder("id", false);// 设置排序字段 (true=ASC/false=DESC)
		return new ApiResult(RESPONSE.SUCCESS, "成功", new PageBean(
				(int) liveUserConvertService.getLiveUserConvertRowCount(assist),
				liveUserConvertService.selectLiveUserConvert(assist)));
	}

	/**
	 * (根据用户金额日志Id获取用户的金币和余额) * @param record
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/selectUserFundLogById", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserFundLogId(@RequestBody Map userFundLog) {
		Assert.notNull(userFundLog, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(userFundLog);
		pageBean.setPageNumber(1);
		pageBean.setPageSize(1);
		if (StringUtil.isEmpty(userFundLog.get("id"))) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		List<UserFundLog> list = userFundLogService.selectUserFundLogBySelective(pageBean);
		if (list.size() != 1) {
			return new ApiResult(RESPONSE.ERROR, "异常");
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功", list.get(0));
	}

	/**
	 * (根据用户Id获取用户的金币和余额)
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryUserFundByUserId", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundByUserId(
			@RequestBody Map<String, Object> userFundLog) {
		Assert.notNull(userFundLog, "参数不能为空");
		Integer userId = getUserIdByToken(String.valueOf(userFundLog.get("token") + ""));
		if (checkUserIdErro(userId))
			return new ApiResult(RESPONSE.ERROR, "TOKEN过期", null);
		UserFund userFund = userFundService.queryUserFundByUserId(userId);
		return new ApiResult(RESPONSE.SUCCESS, "成功", userFund);
	}

	/**
	 * (根据用户Id获取用户直播金币数量)
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryliveuserfundbyuserid", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryLiveUserFundByUserId(
			@RequestBody Map<String, Object> userFundLog) {
		Assert.notNull(userFundLog, "参数不能为空");
		Integer userId = getUserIdByToken(String.valueOf(userFundLog.get("token") + ""));
		if (checkUserIdErro(userId))
			return new ApiResult(RESPONSE.ERROR, "TOKEN过期", null);
		UserGold userGold = userGoldService.queryLiveUserGoldByUserId(userId);
		return new ApiResult(RESPONSE.SUCCESS, "成功", userGold);
	}

	private boolean checkUserIdErro(Integer id) {
		if (id == null || id == 0)
			return true;
		UserFundLog UserFundLog=new UserFundLog();
		return false;
	}

}
