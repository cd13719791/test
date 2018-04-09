package com.moyou.moyouRms.dao.diary;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.diary.DiaryContent;


public interface DiaryContentMapper {

	int deleteByPrimaryKey(Integer id);

    int insert(DiaryContent record);

    int insertSelective(DiaryContent record);

    DiaryContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryContent record);

    int updateByPrimaryKey(DiaryContent record);

	List<DiaryContent> selectDiaryContentListByDiaryId(Integer id);

	Map<String,String> selectFirstDiaryContentListByDiaryId(Integer id);
	Map<String,String> selectFirstDiaryContentPicByDiaryId(Integer id);
}