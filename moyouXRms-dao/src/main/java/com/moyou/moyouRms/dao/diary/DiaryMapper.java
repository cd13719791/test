package com.moyou.moyouRms.dao.diary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.dao.diary.resultmodel.DirayH5ResultModel;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diary.DiaryContentInsertDomain;
import com.moyou.moyouRms.model.diary.DiaryInsertDomain;

public interface DiaryMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Diary record);

	int insertSelective(Diary record);

	Diary selectByPrimaryKey(Integer id);

	DirayH5ResultModel selectExtendDataById(@Param("id") Integer id);

	int updateVideoUrl(@Param("id") int in, @Param("videoUrl") String videoUrl);

	int updateByPrimaryKeySelective(Diary record);

	int updateDiaryPraisejian1(Integer id);

	int updateByPrimaryKey(Diary record);

	List<Diary> querySameDiary();

	DirayH5ResultModel selectExtendDataByvideoId(@Param("videoId") String videoId);

	/**
	 * insert Diary
	 * 
	 * @param diaryInsertDomain
	 * @return
	 */
	int insertDiary(DiaryInsertDomain diaryInsertDomain);

	/**
	 * insert DiaryContent
	 * 
	 * @param diaryContens
	 * @return
	 */
	int insertDiaryContent(List<DiaryContentInsertDomain> diaryContens);

	/**
	 * 根据条件查询日记集合
	 * 
	 * @param record
	 * @return
	 */
	List<Diary> selectDiaryListBySelective(PageBean record);

	/**
	 * 日记统计接口
	 * 
	 * @param record
	 * @return
	 */
	Map<String, Integer> selectDiaryCount();

	Map<String, Object> queryCountDiary();

	int updateDiaryRewardTotal(Integer id);

	/**
	 * 根据日记本Id 获取所有日记
	 * 
	 * @param id
	 * @return
	 */
	List<Diary> selectDiaryListByDiaryFolderId(Integer id);

	/**
	 * 根据用户Id 获取所有日记
	 * 
	 * @param id
	 * @return
	 */
	List<Diary> selectDiaryListByUserId(PageBean record);

	/**
	 * 评论数量加1
	 * 
	 * @param id
	 * @return
	 */
	int updateDiaryCommentTotalJIA1(Integer id);

	int updateDiaryReadCount(Diary diary);

	Integer queryPraiseState(Map<String, Object> params);

	int updateDiaryPraisejia1(Integer diaryId);

	List<Diary> selectReallyUserDiary(Integer time);

	int updateh5UrlById(Diary t);

	List<Diary> selectReferenceDiary(PageBean pb);

	Integer selectDiaryIsExistByDiaryId(Integer diaryId);

	/**
	 * 查询故事发布数量 group by userId
	 * 
	 * @param i
	 * @return
	 */
	Integer selectDiaryUserCount(@Param("time") int time);

	/**
	 * 查询故事 group by userId
	 * 
	 * @param i
	 * @return
	 */
	List<Diary> selectDiaryGroupByUser(@Param("time") int time);

	String queryDiaryTitleOrContent(Integer diaryId);

	List<Diary> selectUserReceiveDataAnalysis();

}
