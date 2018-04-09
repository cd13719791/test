package com.moyou.moyouRms.service.diarycontentrobot;

import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;

public interface DiaryContentRobotService {
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryContentRobot record);

    int insertSelective(DiaryContentRobot record);

    DiaryContentRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryContentRobot record);

    int updateByPrimaryKey(DiaryContentRobot record);
}