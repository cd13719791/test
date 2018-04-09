package com.moyou.moyouRms.controller.live;

import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.StringUtil;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.LiveUserInfo;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.liveshow.LiveRoomService;
import com.moyou.moyouRms.service.liveshow.LiveUserInfoService;
import com.moyou.moyouRms.service.userfund.UserFundLogService;
import com.moyou.moyouRms.util.DateTimeUtil;

/**
 * @author created by Chenxv
 * @date 2017年9月8日 下午2:06:28 直播管理
 */
@Controller
@RequestMapping(value = "live")
public class LiveController extends BaseController {
	@Resource
	private LiveRoomService liveRoomService;
	@Resource
	private LiveUserInfoService liveUserInfoService;
	@Resource
	private UserFundLogService userFundLogService;

	/**
	 * 查询主播列表
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectliveuserlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveUserList(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(map);
		pageBean.setResults(liveUserInfoService.selectLiveUserList(pageBean).stream().peek(s -> {
			if (s.getBirthyDay() != null) {
				s.setAge(Integer.valueOf(DateTimeUtil.getPersonAgeByBirthDate(s.getBirthyDay())));
			}
		}).collect(Collectors.toList()));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 查询列表直播 统计数据
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectliveusercount", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveUserCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", liveUserInfoService.selectLiveUserCount());
	}

	/**
	 * 查询主播详情
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectliveuserinfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveUserInfo(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		Integer userId = Integer.valueOf(map.get("userId").toString());
		return new ApiResult(RESPONSE.SUCCESS, "成功", liveUserInfoService.selectliveUserInfo(userId));
	}

	/**
	 * 操作主播详情
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateliveuserinfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateliveuserinfo(@RequestBody LiveUserInfo enti) {
		Assert.notNull(enti, "参数不能为空");
		if (StringUtil.isEmpty(enti.getUserId().toString())) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		enti.setOperationUser(this.getAdminUser() == null ? "未登录" : getAdminUser().getLoginName());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserInfoService.updateNonEmptyLiveUserInfoById(enti));
	}

	/**
	 * 主播 备注
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateliveuserremark", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateLiveUserRemark(@RequestBody LiveUserInfo enti) {
		Assert.notNull(enti, "参数不能为空");
		if (StringUtil.isEmpty(enti.getUserId().toString())) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		enti.setOperationUser(this.getAdminUser() == null ? "未登录" : getAdminUser().getLoginName());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserInfoService.updateLiveUserRemarkById(enti));
	}

	/**
	 * 限制主播详情
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updatelimitliveuser", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateLimitLiveUser(@RequestBody LiveUserInfo enti) {
		Assert.notNull(enti, "参数不能为空");
		if (StringUtil.isEmpty(enti.getUserId().toString())) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		enti.setOperationUser(this.getAdminUser() == null ? "未登录" : getAdminUser().getLoginName());
		int i = liveUserInfoService.updateLimitLiveUser(enti);
		return new ApiResult(i == 1 ? RESPONSE.SUCCESS : RESPONSE.ERROR, i == 1 ? "成功" : "失败");
	}

	/**
	 * 停播
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updatestopliveuser", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateStopLiveUser(@RequestBody LiveUserInfo enti) {
		Assert.notNull(enti, "参数不能为空");
		if (StringUtil.isEmpty(enti.getUserId().toString())) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		enti.setOperationUser(this.getAdminUser() == null ? "未登录" : getAdminUser().getLoginName());
		int i = liveUserInfoService.updateLimitLiveUser(enti);
		return new ApiResult(i == 1 ? RESPONSE.SUCCESS : RESPONSE.ERROR, i == 1 ? "成功" : "失败");
	}

	/**
	 * 查询开播记录
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectliverecordlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveRecordList(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(map);
		pageBean.setResults(liveRoomService.selectLiveRecordList(pageBean));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 查询观众详情
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectuserfundlogservicebylivemodeid", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectUserFundLogServiceByLiveModeId(
			@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		Integer modeId = Integer.valueOf(map.get("modeId").toString());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				userFundLogService.selectUserFundLogServiceByLiveModeId(modeId));
	}

	/**
	 * 查询主播开播 统计数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectliverecordcount", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveRecordCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", liveRoomService.selectLiveRecordCount());
	}

	/**
	 * 直播-房间管理
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectliveroomlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveRoomList(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(map);
		pageBean.setResults(liveRoomService.selectLiveRoomList(pageBean));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 直播-房间管理 统计数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectliveroomcount", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveRoomCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", liveRoomService.selectLiveRoomCount());
	}

	/**
	 * 直播-房间管理 查询推荐剩余排序
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectliveroomrecommentnumber", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveRoomRecommentNumber() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveRoomService.selectLiveRoomRecommentNumber());
	}

	/**
	 * 直播-房间管理 查询推荐剩余排序
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectliveuserrecommentnumber", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveUserRecommentNumber() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserInfoService.selectLiveUserRecommentNumber());
	}

	/**
	 * 直播-房间管理 推荐直播间 state sort id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateliveroomrecomment", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateLiveRoomRecomment(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		// Integer state =
		// Integer.valueOf(map.get("recommentState").toString());
		// Integer sort = Integer.valueOf(map.get("sort").toString());
		// Integer id = Integer.valueOf(map.get("id").toString());
		return liveRoomService.updateliveroomrecomment(map);
	}

	/**
	 * 直播-房间管理 推荐主播 state sort id
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateliveuserrecomment", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateLiveUserRecomment(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		Integer state = Integer.valueOf(map.get("state").toString());
		Integer sort = Integer.valueOf(map.get("sort") == null ? "99" : map.get("sort").toString());
		Integer id = Integer.valueOf(map.get("id").toString());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserInfoService
						.updateNonEmptyLiveUserInfoById(new LiveUserInfo(state, sort, id)));
	}

	/**
	 * 初始化直播间推荐列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectliverecommendlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveRecommendList(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(map);
		pageBean.setResults(liveRoomService.selectLiveRecommendList(pageBean));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}
}
