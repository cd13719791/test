package com.moyou.moyouRms.service.diaryrobot;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.diary.DiaryH5Insert;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobot;
import com.moyou.moyouRms.model.diaryRobot.DiaryRobotDetailResult;

public interface DiaryRobotService
{
    int deleteByPrimaryKey(Integer id);

    int insert(DiaryRobot record);

    int insertSelective(DiaryRobot record);

    DiaryRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DiaryRobot record);

    int updateByPrimaryKeyWithBLOBs(DiaryRobot record);

    int updateByPrimaryKey(DiaryRobot record);

    /**
     * 初始化
     * 
     * @param pb
     * @return
     */
    List<DiaryRobot> queryDiaryRobotList(PageBean pb);

    /**
     * 初始化 只获取第一张图片与文本
     * 
     * @param pb
     * @return
     */
    List<DiaryRobot> queryDiaryRobotFirstResourceList(PageBean pb);

    /**
     * 修改发布状态，与发布人
     * 
     * @param dRobot
     * @return
     */
    int updatePushStateByPrimaryKey(DiaryRobot dRobot);

    /**
     * 查询机器人故事统计
     * 
     * @return
     */
    Map<String, Object> selectDiaryCount();

    /**
     * h5 代码发布diary
     * 
     * @param record
     * @return
     */
    int insertDiarybyH5(DiaryH5Insert record);

    /**
     * 查询故事详情
     * 
     * @param valueOf
     * @return
     */
    DiaryRobotDetailResult queryDiaryByDiaryId(Integer valueOf);

}
