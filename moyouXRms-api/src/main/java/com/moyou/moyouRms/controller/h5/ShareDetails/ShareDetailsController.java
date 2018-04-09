package com.moyou.moyouRms.controller.h5.ShareDetails;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.constants.enums.DiarySourceTypeEnum;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.shareDetails.ShareDetailsService;
import com.moyou.moyouRms.urlshareanalysis.entity.DiaryH5ShareResult;
import com.moyou.moyouRms.urlshareanalysis.service.AnalysisService;
import com.moyou.moyouRms.util.JsonUtil;

@Controller
@RequestMapping("h5/shareDetails")
public class ShareDetailsController {
	@Resource
	private ShareDetailsService shareDetailsService;
	@Resource
	private DiaryService diaryService;
	@Resource
	private AnalysisService analysisService;


	/**
	 * 故事列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserTalk", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryUserFundList(@RequestBody Map<String, Object> params)
			throws Exception {
		return new ApiResult(RESPONSE.SUCCESS, "成功", shareDetailsService.queryUserTalk(params));
	}

	/**
	 * 关注列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryOtherInterestList", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryOtherInterestList(@RequestBody Map<String, Object> params)
			throws Exception {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				shareDetailsService.queryOtherInterestList(params));
	}

	/**
	 * 分享视频接口
	 * 
	 * @param params
	 *            url
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/querydiaryvideourl", method = RequestMethod.POST)
	public @ResponseBody ApiResult queryDiaryVideoUrl(@RequestBody Map<String, Object> params)
	{
		Integer id = Integer.valueOf(params.get("id").toString());
		DiaryH5ShareResult diaryH5ShareResult = JsonUtil.toObject(
				diaryService.selectExtendDataById(id), DiaryH5ShareResult.class);
		String mapStr = diaryH5ShareResult.getExtendData();
		params = JsonUtil.toMap(mapStr);
		String videoId = "";
		videoId = params.get("videoId").toString();
		return new ApiResult(RESPONSE.SUCCESS, "成功", analysisService.analysisToutiaoVideoUrl(videoId, DiarySourceTypeEnum
				.getEnumByStr(diaryH5ShareResult.getShareAppName().toString())));
	}
}
