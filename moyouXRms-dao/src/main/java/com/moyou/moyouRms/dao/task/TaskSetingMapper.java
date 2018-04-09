package com.moyou.moyouRms.dao.task;

import java.util.List;

import com.moyou.moyouRms.model.po.task.TaskSeting;

public interface TaskSetingMapper {
    int deleteByPrimaryKey(String id);

    int insert(TaskSeting record);

    int insertSelective(TaskSeting record);

    TaskSeting selectByPrimaryKey(String id);
    TaskSeting selectByScheduleId(String scheduleId);
    TaskSeting selectByModeType(Integer modeType);
    int updateByPrimaryKeySelective(TaskSeting record);

    int updateByPrimaryKey(TaskSeting record);
    /*
     * 
     * 初始化机器人设置
     */
    List<TaskSeting> queryTaskSetingList();
    /**
     * 机器人设置修改
     * @param record
     * @return
     */
    int updateByModeType(TaskSeting record);
}