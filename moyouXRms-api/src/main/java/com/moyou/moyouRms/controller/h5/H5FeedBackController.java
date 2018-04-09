package com.moyou.moyouRms.controller.h5;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.feedback.Feedback;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.feedback.FeedBackService;

@Controller
@RequestMapping("/h5/FeedBack")
public class H5FeedBackController {
	@Resource
	private FeedBackService feedBackService;
	/**
	 * 意见反馈
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/AddFeedBack", method = RequestMethod.POST)
	public @ResponseBody ApiResult insertTalk(@RequestBody Feedback record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",feedBackService.insert(record));
	}
}
