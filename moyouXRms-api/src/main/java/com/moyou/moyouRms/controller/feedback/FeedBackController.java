package com.moyou.moyouRms.controller.feedback;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.feedback.Feedback;
import com.moyou.moyouRms.model.feedback.FeedbackPage;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.feedback.FeedBackService;

@Controller
@RequestMapping(value = "/feedback")
public class FeedBackController extends BaseController {
	@Resource
	private FeedBackService feedbackService;

	/**
	 * 查询帮助反馈意见中心
	 * 
	 * @param pageBean
	 * @return
	 */
	@RequestMapping(value = "/selectFeedBackList", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectFeedBackList(
			@RequestBody FeedbackPage feedbackPage) {
		feedbackService.selectFeedBackList(feedbackPage);
		return new ApiResult(RESPONSE.SUCCESS, "成功", feedbackPage);
	}

	/**
	 * 根据意见反馈主键ID查询意见反馈详情
	 * 
	 * @param feedbackId
	 * @return
	 */
	@RequestMapping(value = "/selectFeedBackInfo", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectFeedBackInfo(
			@RequestBody Map<String, Object> feedbackId) {
		String processId = feedbackId.get("feedbackId").toString();
		Integer it = Integer.valueOf(processId);
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				feedbackService.selectFeedBackInfo(it));
	}

	/**
	 * 意见反馈修改状态
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateFeedBackState", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateFeedBackState(
			@RequestBody Feedback record) {
		if (this.getAdminUser() != null) {
			record.setAccount(this.getAdminUser().getLoginName());
		} else {
			record.setAccount("未登录");
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				feedbackService.updateFeedBackState(record));
	}

	/**
	 * 意见反馈修改状态为忽略
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateByPrimaryKeySelective(
			@RequestBody Feedback record) {
		if (this.getAdminUser() != null) {
			record.setAccount(this.getAdminUser().getLoginName());
		} else {
			record.setAccount("未登录");
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				feedbackService.updateByPrimaryKeySelective(record));
	}

	/**
	 * 意见反馈
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/h5/feedBack/insertFeedBack", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertTalk(@RequestBody Feedback record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				feedbackService.insert(record));
	}
}
