package com.moyou.moyouRms.controller.citymanager;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.liveshow.LiveUserLevelSet;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.liveshow.LiveFakeRoomService;
import com.moyou.moyouRms.service.liveshow.LiveRoomService;
import com.moyou.moyouRms.service.liveshow.LiveUserInfoService;
import com.moyou.moyouRms.service.liveshow.LiveUserLevelSetService;

/**
 * @author created by Chenxv
 * @date 2017年9月8日 下午2:07:22 主播等级
 */
@Controller
@RequestMapping(value = "liveUserLevel")
public class LiveUserLevelController extends BaseController {
	@Resource
	private LiveRoomService liveRoomService;
	@Resource
	private LiveUserInfoService liveUserInfoService;
	@Resource
	private LiveUserLevelSetService liveUserLevelSetService;
	@Resource
	private LiveFakeRoomService liveFakeRoomService;
	

	/**
	 * 查询主播等级规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectliveuserlevelset", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveUserLevelSet() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserLevelSetService.selectLiveUserLevelSet(null));
	}

	/**
	 * 修改主播等级规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatenonemptyliveuserlevelsetbyid", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateNonEmptyLiveUserLevelSetById(
			@RequestBody LiveUserLevelSet liveUserLevelSet) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserLevelSetService.updateNonEmptyLiveUserLevelSetById(liveUserLevelSet));
	}

	/**
	 * 
	 * 添加主播等级规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertnonemptyliveuserlevelset", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertNonEmptyLiveUserLevelSet(
			@RequestBody LiveUserLevelSet liveUserLevelSet) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserLevelSetService.insertNonEmptyLiveUserLevelSet(liveUserLevelSet));
	}

	/**
	 * 删除主播等级规则
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteliveuserlevelsetbyid", method = RequestMethod.POST)
	public @ResponseBody ApiResult deleteLiveUserLevelSetById(
			@RequestBody LiveUserLevelSet liveUserLevelSet) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveUserLevelSetService.deleteLiveUserLevelSetById(liveUserLevelSet.getId()));
	}
	
	/**
	 * 运营直播间设置初始化
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectlivefakeroomset", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveFakeRoomSet() {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveFakeRoomService.selectLiveFakeRoomSet());
	}
	/**
	 * 统计运营主播
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectliveusercount", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveUserCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", liveFakeRoomService.selectLiveUserCount());
	}
}
