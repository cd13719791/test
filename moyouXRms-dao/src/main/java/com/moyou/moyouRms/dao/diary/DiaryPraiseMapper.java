package com.moyou.moyouRms.dao.diary;

import java.util.List;

import com.moyou.moyouRms.model.diary.DiaryPraise;


public interface DiaryPraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryPraise record);

    int insertSelective(DiaryPraise record);

    DiaryPraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryPraise record);

    int updateByPrimaryKey(DiaryPraise record);
    /**
	 * 初始化h5分享故事接口
	 * @param id talkId
	 * @return
	 */
	List<DiaryPraise> selectDiaryPraiseListByDiaryId(Integer id);

	int insertByParam(DiaryPraise transMap2Bean);
	
	List<DiaryPraise> selectDiaryPraiseListByDiaryIdAndUserId(DiaryPraise id);

	List<DiaryPraise> querySameDiary();
}