package com.moyou.moyouRms.controller.user;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.type.TypeReference;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.constants.enums.UserFundModeEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.dao.user.UserInfoMapper;
import com.moyou.moyouRms.easemob.service.EaseMobService;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.user.User;
import com.moyou.moyouRms.model.user.UserInfo;
import com.moyou.moyouRms.model.user.UserList;
import com.moyou.moyouRms.model.userRecommend.UserRecommend;
import com.moyou.moyouRms.model.userfund.UserFundLog;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.user.UserCountService;
import com.moyou.moyouRms.service.user.UserInfoService;
import com.moyou.moyouRms.service.user.UserService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.service.usergold.UserGoldService;
import com.moyou.moyouRms.service.userrecommend.UserRecommendService;
import com.moyou.moyouRms.util.BeanToMap;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.JsonUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	// private final static int DEFAULT_USER = 0;
	@Resource
	private UserService userService;
	@Resource
	private UserCountService userCountService;
	@Resource
	private DiaryService diaryService;
	@Resource
	private UserInfoService userInfoService;
	@Resource
	private UserFundLogService userFundLogService;
	@Resource
	private UserRecommendService userRecommendService;
	PageBean pageBean = null;
	@Resource
	MsgSystemXService msgSystemXService;
	@Resource
	EaseMobService easeMobService;
	@Resource
	private UserGoldService userGoldService;
	@Resource
	UserInfoMapper userInfoMapper;

	/**
	 * 查询所有用户(个人主页)
	 * 
	 * @param userId
	 * @return
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/queryUserLists", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserLists(@RequestBody Map map) {
		// map.put("state", "1");
		PageBean pageBean = getJsonWrapPageBean(map);
		List<UserList> list = userService.queryAllUsers(pageBean);
		// pageBean = new PageBean();
		pageBean.setResults(list);
		// pageBean.setTotal(list.size());
		return new ApiResult(ResponseEnum.SUCCESS, pageBean);
	}

	/**
	 * 查询用户统计数据
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/queryUserCount", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserCount() {
		Map map = new HashMap();
		// 总用户数量
		Integer allUserCount = userService.getUserCountByParams(map);
		map.put("gender", "0");
		// 总男数量
		Integer allManCount = userService.getUserCountByParams(map);
		Integer allGirlCount = allUserCount - allManCount;
		// 男女比例
		// Integer MF = allManCount / allGirlCount;
		Integer newUser = userService.selectNewUserCount();
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(0);
		// String MF = numberFormat.format((float) allManCount
		// / (float) allUserCount * 100);
		// 在线用户
		Integer onlineUser = userService.selectOnlineUserCount();
		map.clear();
		map = userService.getUserCountByQQWEIXIN();
		map.put("newUser", newUser);
		map.put("onlineUser", onlineUser);
		map.put("allUserCount", allUserCount);
		map.put("allManCount", allManCount);
		map.put("allGirlCount", allGirlCount);
		map.put("MF", allManCount + "/" + allGirlCount);
		PageBean pageBean = getJsonWrapPageBean(map);
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 查询用户ByUserId
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectUserInfoByUserId", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserInfoByUserId(@RequestBody UserInfo userInfo) {
		try {
			userInfo = userInfoService.selectUserInfoByUserId(userInfo);
			userInfo.setAge(DateTimeUtil.getPersonAgeByBirthDate(userInfo.getBirthday()));
			pageBean = getJsonWrapPageBean(BeanToMap.convertBean(userInfo));
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			return new ApiResult(RESPONSE.ERROR, "异常", e.getMessage());
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 限制用户ByUserId （修改用户状态） User.STATE_NO
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/updateUserStateByUserId", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserStateByUserId(@RequestBody User user) {
		if (user.getState() != null && user.getUserId() != null) {
			userService.updateByPrimaryKeySelective(user);
		} else {
			return new ApiResult(RESPONSE.ERROR, "失败", pageBean);
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 警告用户
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/wanrningUser", method = RequestMethod.POST)
	public @ResponseBody ApiResult wanrningUser(@RequestBody MsgSystemX record) {
		record.setModeType((short) 3);
		record.setModeId(record.getUserId());
		record.setSendUserId(record.getSendUserId());
		record.setMsgSendType(CONSTANT.SYS_USER_WARNING);
		if (record.getWarnType() == MsgSystemX.CUSTOM) {
			record.setMsgTitle(CONSTANT.SYS_CONSTOM_WARNING_TITLE);
		} else {
			record.setMsgTitle(CONSTANT.SYS_USER_WARNING_TITLE);
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				msgSystemXService.addSysMsgAndPushCustomMsg(record));
	}

	/**
	 * 添加用户信息
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/insertUserInfoByUserId", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertUserInfoByUserId(@RequestBody UserInfo userInfo) {
		try {
			pageBean = getJsonWrapPageBean(BeanToMap.convertBean(userInfoService
					.selectUserInfoByUserId(userInfo)));
		} catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
			return new ApiResult(RESPONSE.ERROR, "异常", e.getMessage());
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 添加用户和用户信息
	 * 
	 * @param UserRegCondition
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertUser(@RequestBody Map maps) {
		Map<String, Object> map = new HashMap<String, Object>();
		User user = new User();
		user.setAccount(maps.get("accountId").toString());
		user.setPassword(maps.get("password").toString());
		user.setFundState(User.FUND_STATE_NO);
		user.setState(User.STATE_YES);
		user.setAccountType(User.SHOUJI);
		userService.addUser(user);
		UserInfo userInfo = new UserInfo();
		userInfo.setAvatar(maps.get("avatar").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
		String dstr = maps.get("birthday").toString();
		java.util.Date date = null;
		try {
			date = sdf.parse(dstr);
		} catch (ParseException e1) {
			return new ApiResult(RESPONSE.ERROR, "异常", e1.getMessage());
		}
		userInfo.setBirthday((date));
		userInfo.setNickname(maps.get("nickname").toString());
		userInfo.setGender(Integer.parseInt(maps.get("sex").toString()));
		userInfo.setSig(maps.get("sig").toString());
		userInfo.setCity(maps.get("city").toString());
		userInfo.setProvince(maps.get("province").toString());
		userInfo.setUserId(user.getUserId());
		UserInfo userInfotemp = userInfoService.selectUserInfoByUserId(userInfo);
		if (userInfotemp == null) {
			userInfoService.insertUserInfoByUserId(userInfo);
		} else {
			map.put("msg", "该用户已有信息不能添加");
		}
		map.put("msg", "成功");
		try {
			pageBean = getJsonWrapPageBean(map);
		} catch (Exception e) {
			return new ApiResult(RESPONSE.ERROR, "异常", e.getMessage());
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 查询用户的日记
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryDiaryByUserId", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryDiaryByUserId(@RequestBody Map<String, Object> record) {
		PageBean pb = this.getJsonWrapPageBean(record);
		pb.setResults(diaryService.selectDiaryListByUserId(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 查询假用户，用于群运营，选择群主
	 */
	@RequestMapping(value = "/queryFakeUser", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryFakeUser() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", userService.queryFakeUser());
	}

	/**
	 * 查询用户详细数据
	 */
	@RequestMapping(value = "/queryUserDetailedData", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserDetailedData(@RequestBody Map<String, Object> user) {
		Assert.notNull(user.get("userId"), "参数为空");
		int userId = Integer.valueOf(user.get("userId").toString());
		return new ApiResult(RESPONSE.SUCCESS, "成功", userService.queryUserDetailedData(userId));
	}

	/**
	 * 查询用户修改金币记录
	 */
	@RequestMapping(value = "/queryUserUpdateGoldLog", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserUpdateGoldLog(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数为空");
		PageBean pb = super.getJsonWrapPageBean(map);
		pb.getConditions().put("modeType", UserFundModeEnum.SYSTEM_UPDATE.getValue());
		List<UserFundLog> list = userFundLogService.selectUserFundLogBySystemUpdate(pb);
		list.forEach(fundLog -> {// json数据 解析
			Map<String, Object> fundLogMap = JsonUtil.toCollection(fundLog.getExtnd(),
					new TypeReference<Map<String, Object>>() {
					});
			if (fundLogMap != null) {
				fundLog.setParam(fundLogMap);
				fundLog.setExtnd("");// 清空后台json格式数据
			}
		});
		return new ApiResult(RESPONSE.SUCCESS, "成功", list);
	}

	/**
	 * 推荐用户
	 */
	@RequestMapping(value = "/insertUserRecommend", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertUserRecommend(@RequestBody Map<String, Object> user) {
		Assert.notNull(user.get("userId"), "参数为空");
		int userId = Integer.valueOf(user.get("userId").toString());
		short recommendState = Short.valueOf(user.get("recommendState").toString());
		UserRecommend userRecommend = userRecommendService.selectByUsreId(userId);
		int index = 0;
		if (userRecommend == null) {
			userRecommend = new UserRecommend();
			userRecommend.setModeType(Short.valueOf(user.get("modeType") + ""));
			userRecommend.setRecommedStatus(recommendState);
			userRecommend.setUserId(userId);
			userRecommend.setRecommendSort((short) 1);
			userRecommend.setCreateTime(new Date());
			userRecommend.setUpdateTime(new Date());
			index = userRecommendService.insert(userRecommend);
		} else {
			userRecommend.setRecommedStatus(recommendState);
			index = userRecommendService.updateByPrimaryKeySelective(userRecommend);
		}
		if (1 == index) {
			return new ApiResult(RESPONSE.SUCCESS, "成功");
		} else {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
	}

	/**
	 * 查询所有推荐用户
	 * 
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectUserRecommend", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserRecommend(@RequestBody Map<String, Object> param) {
		Assert.notNull(param, "参数为空");
		PageBean pb = super.getJsonWrapPageBean(param);
		pb.setResults(userService.queryUserRecommend(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 取消推荐用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/updateUserRecommend", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateUserRecommend(@RequestBody Map<String, Object> param) {
		Assert.notNull(param, "参数为空");
		Integer userId = Integer.valueOf(param.get("userId").toString());
		short modeType = Short.valueOf(param.get("modeType").toString());
		UserRecommend userRecommend = userRecommendService.selectByUsreId(userId);
		userRecommend.setRecommedStatus(UserRecommend.STATE_NO);
		userRecommend.setModeType(modeType);
		userRecommend.setUpdateTime(new Date());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userRecommendService.updateByPrimaryKeySelective(userRecommend));
	}

}
