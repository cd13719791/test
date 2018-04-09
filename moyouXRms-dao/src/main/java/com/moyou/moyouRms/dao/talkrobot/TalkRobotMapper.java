package com.moyou.moyouRms.dao.talkrobot;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.statistics.Robot;
import com.moyou.moyouRms.model.talkrobot.TalkRobot;

public interface TalkRobotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TalkRobot record);

    int insertSelective(TalkRobot record);

    TalkRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TalkRobot record);

    int updateByPrimaryKey(TalkRobot record);
    List<TalkRobot> queryTalkRobotList(PageBean pb);
    List<TalkRobot> queryTalkRobotListForAutoPublish(PageBean pb);
    Map<String,Long> queryNewTalkRobotAndAllTalkRobotCount();
    int updateTalkRobotState(TalkRobot record);
    Robot queryTalkRobot();
}