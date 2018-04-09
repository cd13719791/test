package com.moyou.moyouRms.dao.diaryRecommend;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.Diary;
import com.moyou.moyouRms.model.diaryRecommend.DiaryRecommend;


public interface DiaryRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryRecommend record);

    int insertSelective(DiaryRecommend record);

    DiaryRecommend selectByPrimaryKey(Integer id);


    Integer  selectByDiaryId(Integer diaryId);
    
    int updateByPrimaryKeySelective(DiaryRecommend record);

    int updateByPrimaryKey(DiaryRecommend record);
    List<Diary> queryDiaryRecommendList(PageBean pageBean);

	int updateByDiaryId(DiaryRecommend record);
}