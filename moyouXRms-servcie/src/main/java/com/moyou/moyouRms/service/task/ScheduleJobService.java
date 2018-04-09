package com.moyou.moyouRms.service.task;

import com.moyou.moyouRms.model.po.task.base.ScheduleJob;
import com.moyou.moyouRms.service.BaseService;
/**
 * 定时任务service
 */
public interface ScheduleJobService extends BaseService<ScheduleJob>{

    /**
     * 初始化定时任务
     */
    public void initScheduleJob();

    /**
     * 新增
     * 
     * @param TaskLog
     * @return
     */
    public int createScheduleJob(ScheduleJob o);

    
    /**
     * 直接修改 只能修改运行的时间，参数、同异步等无法修改
     * 
     * @param TaskLog
     */
    public int updateScheduleJob(ScheduleJob o);

    /**
     * 删除
     * 
     * @param scheduleJobId
     */
    public int deleteScheduleJob(ScheduleJob o);

    /**
     * 运行一次任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    public int runOnce(ScheduleJob o);

    /**
     * 暂停任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    public int pauseJob(ScheduleJob o);

    /**
     * 恢复任务
     *
     * @param scheduleJobId the schedule job id
     * @return
     */
    public int resumeJob(ScheduleJob o);
    /**
     * 启用禁用任务 0禁用，1启用
     * @param record
     * @return
     */
	public int updateStartOrStop(ScheduleJob record);
	/**
	 * 数据库查看是否有运行。运行了就返回true;
	 * @param str
	 * @return
	 */
	public boolean check(String str);
}
