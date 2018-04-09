/**
 */
package com.moyou.moyouRms.service.redis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.service.BaseJunit4;

/**
 * @describe TODO
 * @author liuxinyi
 * @date 2017年1月19日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class RedisUnit extends BaseJunit4 {
	@Autowired
	private RedisTemplate<String, String>  redisTemplate;
	@Test
	@Rollback(false)
	public void unit() {
		try {
			redisTemplate.opsForValue().set("liuxinyi", "lxy");
			String test = redisTemplate.opsForValue().get("liuxinyi");
			System.out.println("================="+test);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
