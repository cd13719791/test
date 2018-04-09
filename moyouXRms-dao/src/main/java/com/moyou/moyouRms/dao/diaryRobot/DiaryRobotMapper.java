package com.moyou.moyouRms.dao.diaryRobot;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobotDetailResult;
import com.moyou.moyouRms.model.statistics.Robot;

public interface DiaryRobotMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryRobot record);

    int insertSelective(DiaryRobot record);

    DiaryRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryRobot record);

    int updateByPrimaryKeyWithBLOBs(DiaryRobot record);

    int updateByPrimaryKey(DiaryRobot record);

    /**
     * 初始化专辑库
     * 
     * @param pb
     * @return
     */
    List<DiaryRobot> queryDiaryRobotList(PageBean pb);

    int updatePushStateByPrimaryKey(DiaryRobot dRobot);

    /**
     * 查询机器人故事统计数据
     * 
     * @return
     */
    Map<String, Object> selectDiaryCount();

    Robot queryDiaryY();

    DiaryRobotDetailResult queryDiaryByDiaryId(@Param("id") Integer id);
}
