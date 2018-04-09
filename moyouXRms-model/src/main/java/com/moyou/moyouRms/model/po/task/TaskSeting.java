package com.moyou.moyouRms.model.po.task;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

/**
 * 描述:sys_task_seting表的实体类
 * @version
 * @author:  liuxinyi
 * @创建时间: 2017-03-07
 */
public class TaskSeting extends BaseModel{
	private Integer status;
    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -402236992757483540L;

	/**
     * 主键
     */
    private String id;

    /**
     * 
     */
    private String scheduleId;

    /**
     * 任务执行间隔秒
     */
    private Integer intervalSecond;

    /**
     * 单个小号多少秒不重复操作
     */
    private Integer userRepeatOperationSecond;

    /**
     * 执行数据条数
     */
    private Integer executeDataCount;

    /**
     * 1点赞2评论3发说说...
     */
    private Integer modeType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 主键
     * @return id 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 
     * @return schedule_id 
     */
    public String getScheduleId() {
        return scheduleId;
    }

    /**
     * 
     * @param scheduleId 
     */
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId == null ? null : scheduleId.trim();
    }

    /**
     * 任务执行间隔秒
     * @return interval_second 任务执行间隔秒
     */
    public Integer getIntervalSecond() {
        return intervalSecond;
    }

    /**
     * 任务执行间隔秒
     * @param intervalSecond 任务执行间隔秒
     */
    public void setIntervalSecond(Integer intervalSecond) {
        this.intervalSecond = intervalSecond;
    }

    /**
     * 单个小号多少秒不重复操作
     * @return user_repeat_operation_second 单个小号多少秒不重复操作
     */
    public Integer getUserRepeatOperationSecond() {
        return userRepeatOperationSecond;
    }

    /**
     * 单个小号多少秒不重复操作
     * @param userRepeatOperationSecond 单个小号多少秒不重复操作
     */
    public void setUserRepeatOperationSecond(Integer userRepeatOperationSecond) {
        this.userRepeatOperationSecond = userRepeatOperationSecond;
    }

    /**
     * 执行数据条数
     * @return execute_data_count 执行数据条数
     */
    public Integer getExecuteDataCount() {
        return executeDataCount;
    }

    /**
     * 执行数据条数
     * @param executeDataCount 执行数据条数
     */
    public void setExecuteDataCount(Integer executeDataCount) {
        this.executeDataCount = executeDataCount;
    }

    /**
     * 1点赞2评论3发说说...
     * @return mode_type 1点赞2评论3发说说...
     */
    public Integer getModeType() {
        return modeType;
    }

    /**
     * 1点赞2评论3发说说...
     * @param modeType 1点赞2评论3发说说...
     */
    public void setModeType(Integer modeType) {
        this.modeType = modeType;
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}