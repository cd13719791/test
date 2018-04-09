package com.moyou.moyouRms.controller.diary;

import java.util.ArrayList;
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
import com.moyou.moyouRms.constants.enums.ResponseEnum;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diary.DiaryComment;
import com.moyou.moyouRms.model.diary.DiaryInsertDomain;
import com.moyou.moyouRms.model.diaryRecommend.DiaryRecommend;
import com.moyou.moyouRms.model.talk.Talk;
import com.moyou.moyouRms.model.userdynamic.UserDynamic;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.diary.DiaryService;
import com.moyou.moyouRms.service.diarycomment.DiaryCommentService;
import com.moyou.moyouRms.service.diarycontent.DiaryContentService;
import com.moyou.moyouRms.service.diaryrecommend.DiaryRecommendService;
import com.moyou.moyouRms.service.talk.TalkService;
import com.moyou.moyouRms.service.userdynamic.UserDynamicService;
import com.moyou.moyouRms.util.StringUtil;

@Controller
@RequestMapping("diary")
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
	UserDynamicService userDynamicService;
	@Resource
	TalkService talkService;

	/**
	 * 日记初始化接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/selectDiarysBySelctive", method = RequestMethod.POST)
	public ApiResult selectDiarysBySelctive(@RequestBody Map<String, Object> record) {
		Assert.notNull(record, "参数不能为空");
		if (StringUtil.isEmpty(record.get("orderBy"))) {
			record.put("orderBy", 0);
		}
		PageBean pb = super.getJsonWrapPageBean(record);
		List<Diary> list = diaryService.selectDiaryListBySelective(pb);
		list.forEach(diary -> {
			diary.setFirstTextAndPic(diaryContentService.selectFirstDiaryContentListByDiaryId(diary
					.getId()));
		});
		pb.setResults(list);
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 日记统计接口
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectDiaryCount", method = RequestMethod.POST)
	public ApiResult selectDiaryCount() {
		return new ApiResult(RESPONSE.SUCCESS, "成功", diaryService.selectDiaryCount());
	}

	/**
	 * 日记详情接口
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/selectDiaryDetailByDiaryId", method = RequestMethod.POST)
	public ApiResult selectDiaryDetailByDiaryId(@RequestBody Map<String, Integer> record) {
		Assert.notNull(record.get("id"), "参数不能为空");
		Diary diary = diaryService.selectByPrimaryKey(Integer.valueOf(record.get("id") + ""));
		if (null == diary) {
			return new ApiResult(RESPONSE.ERROR, "失败");
		}
		diary.setDiaryContents(diaryContentService.selectDiaryContentListByDiaryId(diary.getId()));
		// List<DiaryComment>
		// list=diaryCommentService.selectDiaryCommentListByDiaryId(diary.getId());
		// list=this.setChildComment(list);
		// diary.setDiaryComments(list);
		return new ApiResult(RESPONSE.SUCCESS, "成功", diary);
	}

	/**
	 * 日记评论接口
	 * 
	 * @param record
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectDiaryCommentByDiaryId", method = RequestMethod.POST)
	public ApiResult selectDiaryCommentByDiaryId(@RequestBody Map<String, Object> record) {
		Assert.notNull(record.get("id"), "参数不能为空");
		PageBean pb = this.getJsonWrapPageBean(record);
		pb.setResults(diaryCommentService.selectDiaryCommentListByDiaryIdLimit(pb));
		return new ApiResult(RESPONSE.SUCCESS, "成功", pb);
	}

	/**
	 * 日记屏蔽接口
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateDiaryContentByDiaryId", method = RequestMethod.POST)
	public ApiResult updateDiaryContentByDiaryId(@RequestBody Map<String, Integer> record) {
		Assert.notNull(record.get("id"), "参数不能为空");
		Diary diary = diaryService.selectByPrimaryKey(Integer.valueOf(record.get("id") + ""));
		if (diary != null && diary.getState().intValue() == Diary.DIARY_NORMAL_NUMBER.intValue()) {
			diary.setState(Integer.valueOf(Diary.DIARY_DELETE_NUMBER));
			diaryService.updateByPrimaryKeySelective(diary);
			diaryService.updateUserCountDiaryJian1(diary.getCreatorId());
			// 修改推荐状态为不推荐
			diaryRecommendService.updateByDiaryId(new DiaryRecommend(
					(int) Diary.DIARY_DELETE_NUMBER, diary.getId()));
			/**
			 * 删除动态数据
			 */
			userDynamicService.deleteByDataIdAnDType(new UserDynamic(diary.getId(),
					UserDynamic.DIARY));
			/**
			 * 删除推荐到首页的数据
			 */
			talkService.deleteTalkByReferenceIdAndReferenceType(new Talk(Talk.REFERENCE_TYPE_DIARY,
					diary.getId(), Talk.STATE_XIANZHI));
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	/**
	 * 日记评论删除接口
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/deleteDiaryCommentByCommentId", method = RequestMethod.POST)
	public ApiResult deleteDiaryCommentByCommentId(@RequestBody Map<String, Integer> record) {
		Assert.notNull(record.get("id"), "参数不能为空");
		DiaryComment diaryComment = diaryCommentService.selectByPrimaryKey(Integer.valueOf(record
				.get("id") + ""));
		Diary diary = diaryService.selectByPrimaryKey(diaryComment.getDiaryId());
		if (diaryComment != null) {
			diary.setCommentTotal(diary.getCommentTotal() - 1);
			diaryService.updateByPrimaryKeySelective(diary);
			diaryComment.setState(Integer.valueOf(DiaryComment.DIARY_COMMENT_DELETE_NUMBER));
			diaryCommentService.updateByPrimaryKeySelective(diaryComment);
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	/**
	 * 初始化子评论
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<DiaryComment> setChildComment(List<DiaryComment> list) {
		/**
		 * 给评论初始化 子级评论
		 */
		List<DiaryComment> diaryList = new ArrayList<DiaryComment>();
		list.forEach(diaryComment -> {
			list.forEach(child -> {
				if (child.getParentId() == diaryComment.getId()) {
					List<DiaryComment> newList = diaryComment.getChildComment();
					newList.add(child);
					diaryComment.setChildComment(newList);
					diaryList.add(child);
				}
			});
		});
		list.removeAll(diaryList);
		diaryList.clear();
		/**
		 * 删除所有没有父级的评论 ------有可能查询出来的子评论父级已经被删除
		 */
		// list.forEach(diaryComment ->{
		// if(0!=diaryComment.getParentId()){
		// list.remove(diaryComment);
		// }
		// });
		return list;
	}

	/**
	 * 发布日记
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ApiResult insertDiary(@RequestBody DiaryInsertDomain domain) {
		return new ApiResult(ResponseEnum.SUCCESS, diaryService.insertDiary(domain));
	}

	/**
	 * 发布日记评论
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/insertComment", method = RequestMethod.POST)
	public ApiResult insertComment(@RequestBody DiaryComment diaryComment) {
		return new ApiResult(ResponseEnum.SUCCESS, diaryService.insertComment(diaryComment));
	}

	/**
	 * 推荐首页日志
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/insertReferenceDiary", method = RequestMethod.POST)
	public ApiResult insertReferenceDiaty(@RequestBody Map<String, Object> diary) {
		Assert.notNull(diary);
		Assert.notNull(diary.get("diaryId"));
		return new ApiResult(ResponseEnum.SUCCESS, diaryService.insertReferenceDiary(Integer
				.valueOf(diary.get("diaryId").toString())));
	}

	/**
	 * 取消首页推荐
	 * 
	 * @param domain
	 * @return
	 */
	@RequestMapping(value = "/deleteReferenceDiary", method = RequestMethod.POST)
	public ApiResult deleteReferenceDiary(@RequestBody Map<String, Object> diary) {
		Assert.notNull(diary);
		Assert.notNull(diary.get("diaryId"));
		return new ApiResult(ResponseEnum.SUCCESS, diaryService.deleteReferenceDiary(Integer
				.valueOf(diary.get("diaryId").toString())));
	}

	/**
	 * 初始化首页推荐
	 * 
	 * @param domain
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectReferenceDiary", method = RequestMethod.POST)
	public ApiResult selectReferenceDiary(@RequestBody Map<String, Object> record) {
		record.put("referenceType", Talk.REFERENCE_TYPE_DIARY);
		record.put("orderBy", 4);
		PageBean pb = super.getJsonWrapPageBean(record);
		List<Diary> list = diaryService.selectReferenceDiary(pb);
		list.forEach(diary -> {
			diary.setFirstTextAndPic(diaryContentService.selectFirstDiaryContentListByDiaryId(diary
					.getId()));
		});
		pb.setResults(list);
		return new ApiResult(ResponseEnum.SUCCESS, pb);
	}

}
