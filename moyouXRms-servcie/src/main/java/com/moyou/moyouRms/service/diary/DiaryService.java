package com.moyou.moyouRms.service.diary;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.constants.enums.TalkPraiseSateEnum;
import com.moyou.moyouRms.dao.diary.resultmodel.DirayH5ResultModel;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diary.DiaryComment;
import com.moyou.moyouRms.model.diary.DiaryInsertDomain;
import com.moyou.moyouRms.model.diary.UserReceivceParam;
import com.moyou.moyouRms.response.ApiResult;

public interface DiaryService {
	int deleteByPrimaryKey(Integer id);

	int insert(Diary record);

	int insertSelective(Diary record);

	Diary selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Diary record);

	int updateByPrimaryKey(Diary record);

	/**
	 * 发布日记
	 * 
	 * @param domain
	 * @return
	 */
	int insertDiary(DiaryInsertDomain domain);

	/**
	 * 根据条件查询日记集合
	 * 
	 * @param record
	 * @return
	 */
	List<Diary> selectDiaryListBySelective(PageBean record);

	/**
	 * 首页 统计数据 今日日记 日记总数
	 * 
	 * @return
	 */
	Map<String, Integer> selectDiaryCount();

	/**
	 * 根据日记本Id查询所有日记 并封装第一段内容和图片
	 * 
	 * @param id
	 * @return
	 */
	List<Diary> selectDiaryListByDiaryFolderId(Integer id);

	/**
	 * 根据用户Id查询所有日记 并封装第一段内容和图片
	 * 
	 * @param id
	 * @return
	 */
	List<Diary> selectDiaryListByUserId(PageBean record);

	Diary selectH5DiaryByDiaryId(Integer valueOf);

	// 日记
	Integer updateUserCountDiaryJian1(Integer userId);

	int insertComment(DiaryComment diary);

	int updateDiaryReadCount(Diary diary);

	TalkPraiseSateEnum queryPraiseState(Map<String, Object> params);

	int insertPraise(Map<String, Object> params);

	int updateh5UrlById(Diary t);

	/**
	 * 推荐首页故事
	 * 
	 * @param talk
	 * @return
	 */
	public int insertReferenceDiary(Integer diaryId);

	int deleteReferenceDiary(Integer valueOf);

	List<Diary> selectReferenceDiary(PageBean pb);

	Integer selectDiaryIsExistByDiaryId(Integer valueOf);

	/**
	 * 用户接收的内容
	 * 
	 * @param UserReceivceParam
	 *            userReceivceShareParam
	 * @return
	 */
	ApiResult createUserReceive(UserReceivceParam userReceivceShareParam);

	// /**
	// * 用户接收的视频内容
	// * @param UserReceivceParam userReceivceShareParam
	// * @return
	// */
	// ApiResult createUserReceiveVideo(UserReceivceParam
	// userReceivceShareParam);

	DirayH5ResultModel selectExtendDataById(Integer id);

	List<Diary> selectUserReceiveDataAnalysis();

}
