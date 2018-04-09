package com.moyou.moyouRms.task.schedule.basetask;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import cn.jiguang.common.utils.StringUtils;

import com.moyou.moyouRms.redis.lock.RedisLock;
import com.moyou.moyouRms.spring.SpringBeanUtils;

/**
 * @author created by Chenxv
 * @date 2017年8月28日 上午9:51:46
 * @version =====>这个类是用来写一些task 公用方法的<=====
 */
@Component
public abstract class BaseTask {

	private static final String TASK_REDIS_COUNT_LOCK_KEY = "task_redis_count_lock_key";
	protected static final String TASK_REDIS_COUNT_KEY = "task_redis_count_key";
	private RedisLock redisLock;

	public BaseTask() {
		this.redisLock = new RedisLock(TASK_REDIS_COUNT_LOCK_KEY);
	}

	/**
	 * 
	 * 增加计数器数量
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Integer addTaskCount(Integer count) {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);

		Integer oldIndex = 0;
		try {
			redisLock.lock();
			ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
			String str = valueOperations.get(TASK_REDIS_COUNT_KEY);
			if (StringUtils.isEmpty(str)) {
				str = "0";
			}
			oldIndex = Integer.valueOf(str);
			oldIndex += count;
			valueOperations.set(TASK_REDIS_COUNT_KEY, (oldIndex).toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			redisLock.unlock();
		}
		return oldIndex;
	}

	/**
	 * 减少计数器数量
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Integer lowerTaskCount() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		Integer oldIndex = 0;
		try {
			redisLock.lock();
			ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
			oldIndex = Integer.valueOf(valueOperations.get(TASK_REDIS_COUNT_KEY));
			valueOperations.set(TASK_REDIS_COUNT_KEY, (oldIndex--).toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			redisLock.unlock();
		}
		return oldIndex;
	}

	/**
	 * 获取计数器数量
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Integer getTaskCount() {
		RedisTemplate<String, String> stringRedisTemplate = (RedisTemplate<String, String>) SpringBeanUtils
				.getBean("redisTemplate_KVString", RedisTemplate.class);
		Integer oldIndex = 0;
		ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
		oldIndex = Integer.valueOf(valueOperations.get(TASK_REDIS_COUNT_KEY));
		return oldIndex;
	}

}
