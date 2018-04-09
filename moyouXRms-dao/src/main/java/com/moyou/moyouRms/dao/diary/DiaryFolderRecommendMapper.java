package com.moyou.moyouRms.dao.diary;

import com.moyou.moyouRms.model.diary.DiaryFolderRecommend;


public interface DiaryFolderRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryFolderRecommend record);

    int insertSelective(DiaryFolderRecommend record);

    DiaryFolderRecommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryFolderRecommend record);

    int updateByPrimaryKey(DiaryFolderRecommend record);
}