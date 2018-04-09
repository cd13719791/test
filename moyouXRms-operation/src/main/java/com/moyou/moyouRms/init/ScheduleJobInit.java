package com.moyou.moyouRms.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.moyou.moyouRms.service.task.ScheduleJobService;
import com.moyou.moyouRms.spring.SpringBeanUtils;

/**
 * 定时任务初始化
 */
public class ScheduleJobInit {
	protected static final String TASK_REDIS_COUNT_KEY = "task_redis_count_key";
	/** 日志对象 */
	private static final Logger LOG = LoggerFactory.getLogger(ScheduleJobInit.class);
	@SuppressWarnings("unchecked")
	RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
			.getFirstBeanOfType(RedisTemplate.class);
	/** 定时任务service */
	@Autowired
	private ScheduleJobService scheduleJobService;

	/**
	 * 项目启动时初始化
	 */
	public void init() {
		LOG.info("定时任务初始化init");
		scheduleJobService.initScheduleJob();
		if (stringRedisTemplate.hasKey(TASK_REDIS_COUNT_KEY)) {
			stringRedisTemplate.delete(TASK_REDIS_COUNT_KEY);
		}
		LOG.info("定时任务初始化end");
	}

}
