package com.moyou.moyouRms.dao.diaryContentRobot;

import java.util.List;

import com.moyou.moyouRms.model.diary.DiaryContentInsertDomain;
import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;

public interface DiaryContentRobotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryContentRobot record);

    int insertSelective(DiaryContentRobot record);

    DiaryContentRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryContentRobot record);

    int updateByPrimaryKey(DiaryContentRobot record);
    int insertDiaryContents(List<DiaryContentInsertDomain> diaryContens);
    /**
     * 根据专辑id 获取专辑资源
     * @param id
     * @return
     */
	List<DiaryContentRobot> selectByDiaryRobotId(Integer diaryRobotId);
	/**
	 * 获取专辑封面资源(第一章图，第一段文字)
	 * @param id
	 * @return
	 */
	List<DiaryContentRobot> selectByDiaryRobotIdFirstResource(Integer id);
}