package com.moyou.moyouRms.controller.talkrobot;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.talkrobot.TalkRobotComment;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.report.CommonResourceService;
import com.moyou.moyouRms.service.talkrobot.TalkRobotService;

@RestController
@RequestMapping(value = "/talkCommentRobot")
public class TalkCommentRobotController {

	@Resource
	TalkRobotService talkRobotService;
	@Resource
	private CommonResourceService commonResourceService;
	/**
	 * 添加一条评论模板
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody ApiResult insert(@RequestBody TalkRobotComment record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				talkRobotService.insert(record));
	}

	/**
	 * 查询评论模板集合
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryTalkRobotCommentList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryTalkRobotCommentList(
			@RequestBody Page page) {
		talkRobotService.queryTalkRobotCommentList(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}

	/**
	 * 修改状态
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateTalkRobotCommentState", method = RequestMethod.POST)
	public ApiResult updateTalkRobotCommentState(
			@RequestBody TalkRobotComment record) {
		talkRobotService.updateTalkRobotCommentState(record);
		return new ApiResult(RESPONSE.SUCCESS, "成功", record);
	}
}
