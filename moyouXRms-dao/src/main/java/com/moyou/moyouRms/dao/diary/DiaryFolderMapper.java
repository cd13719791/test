package com.moyou.moyouRms.dao.diary;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.DiaryFolder;


public interface DiaryFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryFolder record);

    int insertSelective(DiaryFolder record);

    DiaryFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryFolder record);

    int updateByPrimaryKey(DiaryFolder record);

	List<DiaryFolder> selectDiaryFolderBySelective(PageBean pb);
}