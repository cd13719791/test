package com.moyou.moyouRms.model.po.task.base;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.moyou.moyouRms.model.BaseModel;
@Alias("ScheduleJob")
public class ScheduleJob extends BaseModel{

	public static final Integer SCHEDULEJOB_START=1;//启动
	public static final Integer SCHEDULEJOB_END=0;//关闭
	/**
	 * 说说发布 白天夜晚规则模型
	 */
	public static final Integer TALK_MODE_DAY=3;
	public static final Integer TALK_MODE_NIGHT=5;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6244774995734583670L;

	/** 任务id */
	private String  scheduleJobId;

	/** 任务名称 */
	private String jobName;

	/** 任务分组 */
	private String jobGroup;
	
    /** 任务别名 */
    private String  aliasName;
	
	/** 指定执行类 */
	private String jobClass;

	/** 任务状态  0停用 1启用 2删除 */
	private Integer status;

	/** 任务运行时间表达式 */
	private String cronExpression;

	/** 任务描述 */
	private String description;
	/** 扩展数据 */
	private Object extend;
	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date updateTime;

	private String keyWord;
	
	public String getScheduleJobId() {
		return scheduleJobId;
	}

	public void setScheduleJobId(String scheduleJobId) {
		this.scheduleJobId = scheduleJobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getJobClass() {
		return jobClass;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}
	
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @return the extend
	 */
	public Object getExtend() {
		return extend;
	}

	/**
	 * @param extend the extend to set
	 */
	public void setExtend(Object extend) {
		this.extend = extend;
	}

		
	
}
