package com.moyou.moyouRms.controller.diary;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.diary.UserReceivceParam;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.diarycomment.DiaryCommentService;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.service.diaryrecommend.DiaryRecommendService;
import com.moyou.moyouRms.util.JsonUtil;

@Controller
@RequestMapping("url")
@ResponseBody
public class DiaryController extends BaseController {
	@Resource
	private DiaryService diaryService;
	@Resource
	private DiaryContentService diaryContentService;
	@Resource
	private DiaryCommentService diaryCommentService;
	@Resource
	DiaryRecommendService diaryRecommendService;
	@Resource
	private HttpServletRequest request;

	@RequestMapping(value = "/html_analysis", method = RequestMethod.POST)
	public ApiResult userReceiveShare(
			@RequestBody UserReceivceParam userReceivceShareParam) {
		logger.error(JsonUtil.toJson(userReceivceShareParam));
		return diaryService.createUserReceive(userReceivceShareParam);
	}
/**
 * 视频类型专用解析接口
 * @param userReceivceShareParam
 * @return
 */
//	@RequestMapping(value = "/html_video", method = RequestMethod.POST)
//	public ApiResult userReceiveShareVideo(
//			@RequestBody UserReceivceParam userReceivceShareParam) {
//		logger.info(JsonUtil.toJson(userReceivceShareParam));
//		return diaryService.createUserReceiveVideo(userReceivceShareParam);
//	}
}
