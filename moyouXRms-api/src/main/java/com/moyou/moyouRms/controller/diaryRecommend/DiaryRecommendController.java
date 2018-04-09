package com.moyou.moyouRms.controller.diaryRecommend;

import java.util.List;
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
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diaryRecommend.DiaryRecommend;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.service.diaryrecommend.DiaryRecommendService;

@Controller
@RequestMapping("diaryRecommend")
public class DiaryRecommendController extends BaseController {
	@Resource
	private DiaryRecommendService diaryRecommendService;
	@Resource
	private DiaryContentService diaryContentService;
@Resource
private  DiaryService diaryService;
	/**
	 * 添加一条推荐故事
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody ApiResult insert(@RequestBody DiaryRecommend record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				diaryRecommendService.insertSelective(record));
	}

	/**
	 * 故事详情
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectByPrimaryKey(
			@RequestBody Map<String, Integer> record) {
		Assert.notNull(record.get("id"), "参数不能为空");
		Diary diary = diaryService.selectByPrimaryKey(Integer.valueOf(record
				.get("id") + ""));
		if (null == diary) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		diary.setDiaryContents(diaryContentService
				.selectDiaryContentListByDiaryId(diary.getId()));
		return new ApiResult(RESPONSE.SUCCESS, "成功", diary);
	}
	/**
	 * 推荐故事初始化接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectDiarysBySelctive", method = RequestMethod.POST)
	public @ResponseBody ApiResult selectDiarysBySelctive(@RequestBody Map<String,Object> record) {
		Assert.notNull(record, "参数不能为空");
		PageBean pb = super.getJsonWrapPageBean(record);
		List<Diary> list = diaryRecommendService.queryDiaryRecommendList (pb);
		list.forEach(diary -> {
			diary.setFirstTextAndPic(diaryContentService
					.selectFirstDiaryContentListByDiaryId(diary.getId()));
		});
		pb.setResults(list);
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}
	/**
	 * 取消一条推荐故事
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public @ResponseBody ApiResult updateByPrimaryKeySelective(@RequestBody DiaryRecommend record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				diaryRecommendService.updateByPrimaryKeySelective(record));
	}
}
