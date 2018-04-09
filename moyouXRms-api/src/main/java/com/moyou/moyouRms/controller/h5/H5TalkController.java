package com.moyou.moyouRms.controller.h5;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.talk.TalkService;
@RestController
@RequestMapping(value = "h5/talk")
public class H5TalkController {
	@Resource
	private TalkService talkService;
	  /**
     * h5 分享说说详情
     * @param talkCommentId
     * @return
     */
	@RequestMapping(value = "/queryTalkDetails", method = RequestMethod.POST)
	public ApiResult queryTalkDetails(@RequestBody Map<String, Object> params) {
		Assert.notNull(params.get("talkId"), "参数不能为空");
		return new ApiResult(RESPONSE.SUCCESS, "成功", talkService.queryTalkDetails(params));
	}
}
