/**
 */
package com.moyou.moyouRms.service.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;

import com.moyou.moyouRms.constants.CONSTANT;
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
	 @Resource(name = "redisTemplate_KVString")
		private RedisTemplate<String, String> redisTemplate_KVString;
	@Test
	@Rollback(false)
	public void unit() {
		try {
			String tokenValue = redisTemplate_KVString.opsForValue().get(CONSTANT.H5_USER_TOKEN_PREFIX + "da392d347e274ae683f59c1a4ce0d5f6");// 如果访问接口的时间戳与当前时间差值大于1个小时，则返回接口访问超时
			System.out.println(tokenValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
