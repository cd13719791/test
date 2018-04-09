package com.moyou.moyouRms.controller.talkrobot;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.model.talkrobot.TalkRobot;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;

@RestController
@RequestMapping(value = "/talkRobot")
public class TalkRobotController extends BaseController {

	@Resource
	TalkRobotService talkRobotService;
	@Resource
	private CommonResourceService commonResourceService;

	/**
	 * 查询说说模板集合
	 * 
	 * @param record
	 *            state =0 正常 =1 已发布
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/queryTalkRobotList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkRobotList(
			@RequestBody Map<String, Object> map) {
		PageBean page = this.getJsonWrapPageBean(map);
		List<TalkRobot> list = talkRobotService.queryTalkRobotList(page);
		list.forEach(talkRobot -> {
			CommonResource commonResource = new CommonResource();
			commonResource.setObjectId(talkRobot.getId());
			commonResource.setObjectType(CommonResource.OBJECT_ROBOT_TALK);
			talkRobot.setResources(commonResourceService
					.selectFirstCommonResourceByObjectId(commonResource));
			// talkRobot.setPicCount(talkRobot.getResources().size());
		});
		page.setResults(list);
		Map<String, Long> countMap = talkRobotService
				.queryNewTalkRobotAndAllTalkRobotCount();
		Map<String, Object> newMap = countMap
				.entrySet()
				.stream()
				.collect(
						Collectors.toMap(Map.Entry::getKey,
								e -> (Long) e.getValue()));
		page.setConditions(newMap);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 查询说说模板详情
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryTalkRobotById", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkRobotById(
			@RequestBody Map<String, String> map) {
		TalkRobot talkRobot = talkRobotService.queryTalkRobotId(Integer
				.valueOf(map.get("id")));
		if (talkRobot != null) {
			CommonResource commonResource = new CommonResource();
			commonResource.setObjectId(talkRobot.getId());
			commonResource.setObjectType(CommonResource.OBJECT_ROBOT_TALK);
			talkRobot.setResources(commonResourceService
					.selectCommonResourceByObjectId(commonResource));
			return new ApiResult(RESPONSE.SUCCESS, "成功", talkRobot);
		} else {
			return new ApiResult(RESPONSE.ERROR, "数据异常或不存在要找的说说");
		}
	}

	/**
	 * 添加说说模板详情
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/insertTalkRobot", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertTalkRobot(@RequestBody TalkRobot record) {
		int index = talkRobotService.insertTalkRobot(record);
		if (index != 0) {
			return new ApiResult(RESPONSE.SUCCESS, "成功");
		} else {
			return new ApiResult(RESPONSE.ERROR, "数据异常");
		}
	}

	/**
	 * 添加说说模板详情
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("all")
	@RequestMapping(value = "/insertTalkRobotForYunYing", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertTalkRobotForYunYing(
			@RequestBody Map record) {
		TalkRobot talkRobot = new TalkRobot();
		talkRobot.setSex(Integer.valueOf(record.get("gender").toString()));
		talkRobot.setContent(record.get("content").toString());
		talkRobot.setMediaType(Integer.valueOf(record.get("mediaType")
				.toString()));
		talkRobot.setCreateTime(new Date());
		talkRobot.setCommentTotal(0);
		talkRobot.setState(TalkRobot.UNPUBLISH);
		talkRobot.setReadTotal(0);
		talkRobot.setRewardTotal(0);
		talkRobot.setSupportTotal(0);
		talkRobot.setState(0);
		int index = talkRobotService.insertTalkRobot(talkRobot);
		List commonResourceList = (List) record.get("talkResource");
		if (commonResourceList == null) {
			return new ApiResult(RESPONSE.ERROR, "图片为空");
		}
		for (int i = 0; i < commonResourceList.size(); i++) {
			Map<String, Object> map = (Map<String, Object>) commonResourceList
					.get(i);
			CommonResource commonResource = new CommonResource();
			commonResource.setPicOrder(Short.valueOf(map.get("picOrder") + ""));
			commonResource.setUrl(map.get("url").toString());
			commonResource.setObjectType(CommonResource.OBJECT_ROBOT_TALK);
			commonResource
					.setMediaType(Short.valueOf(map.get("mediaType") + ""));
			commonResource.setObjectId(talkRobot.getId());
			commonResource.setCreateTime(new Date());
			commonResourceService.insertcommonResource(commonResource);
		}
		if (index != 0) {
			return new ApiResult(RESPONSE.SUCCESS, "成功");
		} else {
			return new ApiResult(RESPONSE.ERROR, "数据异常");
		}
	}

	/**
	 * 修改说说状态 （删除说说）
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateTalkRobot", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateTalkRobot(@RequestBody TalkRobot record) {
		int index = talkRobotService.updateTalkRobotState(record);
		if (index != 0) {
			return new ApiResult(RESPONSE.SUCCESS, "成功");
		} else {
			return new ApiResult(RESPONSE.ERROR, "数据异常");
		}
	}
}
