package com.moyou.moyouRms.service.diarycontent;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.diary.DiaryContent;

public interface DiaryContentService {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryContent record);

    int insertSelective(DiaryContent record);

    DiaryContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryContent record);

    int updateByPrimaryKey(DiaryContent record);
    /**
     * 根据日记id 获取日记内容集合
     * @param id
     * @return
     */
    List<DiaryContent> selectDiaryContentListByDiaryId(Integer id);
    /**
     * 根据日记id 获取日记第一段内容图片集合
     * @param id
     * @return
     */
    Map<String, String> selectFirstDiaryContentListByDiaryId(Integer id);

}