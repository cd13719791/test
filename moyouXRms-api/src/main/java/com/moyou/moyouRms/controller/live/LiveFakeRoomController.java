package com.moyou.moyouRms.controller.live;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.liveshow.LiveFakeRoom;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.liveshow.LiveFakeRoomService;



@Controller
@RequestMapping(value = "liveFakeRoom")
public class LiveFakeRoomController extends BaseController{
	@Resource
	private LiveFakeRoomService liveFakeRoomService;
	/**
	 * 初始化运营直播间列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectlivefakeroomlist", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveFakeRoomList(@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		PageBean pageBean = getJsonWrapPageBean(map);
		pageBean.setResults(liveFakeRoomService.selectLiveFakeRoomList(pageBean));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pageBean);
	}

	/**
	 * 修改运营直播间
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updatenonemptyliveuserlevelsetbyid", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateNonEmptyLiveUserLevelSetById(
			@RequestBody LiveFakeRoom liveFakeRoom) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveFakeRoomService.updateNonEmptyLiveFakeRoomById(liveFakeRoom));
	}

	/**
	 * 
	 * 添加运营直播间
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertnonemptyliveuserlevelset", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertNonEmptyLiveUserLevelSet(
			@RequestBody LiveFakeRoom liveFakeRoom) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveFakeRoomService.insertNonEmptyLiveFakeRoom(liveFakeRoom));
	}
	
	/**
	 * 查看编辑页面
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/selectlivefakeroomeditbyid", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectLiveFakeRoomEditById(
			@RequestBody Map<String, Object> map) {
		Assert.notNull(map, "参数不能为空");
		Integer id = Integer.valueOf(map.get("id").toString());
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				liveFakeRoomService.selectLiveFakeRoomEditById(id));
	}

}
