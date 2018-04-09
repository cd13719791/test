/**
 */
package com.moyou.moyouRms.security;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @describe token 对应的ip验证类
 * @author liuxinyi
 * @date 2017年2月14日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
@Component
public class TokenIpVerify {
	@Resource(name = "redisTemplate_KVString")
	private RedisTemplate<String, String> redisTemplate_KVString;
	private static final int TOKEN_USER_IP_LIMIT_COUNT = 4; // 用户常用ip地址数限制
	private static final String TOKEN_USER_IPS_REDIS_KEY_PREFIX = "TOKEN_USER_IPS_REDIS_KEY:"; // 用户常用ip存储到redis的key前缀
	
	/**
	 * 更新ip到存储池
	 * @param userId
	 * @param ip
	 */
	public void updateIpPool(int userId, String ip) {
		String key = TOKEN_USER_IPS_REDIS_KEY_PREFIX+userId;
		Long setSize = redisTemplate_KVString.opsForZSet().size(key);
		if (setSize > 0) {
			if (verify(userId, ip)) {// ip存在，给当前ip的请求次数加1
				redisTemplate_KVString.opsForZSet().incrementScore(key, ip, 1);
			} else {
				if (setSize >= TOKEN_USER_IP_LIMIT_COUNT) {// ip池存储上线，则删除使用次数最少的ip地址，将新的ip存入池中
					redisTemplate_KVString.opsForZSet().removeRange(key, 0, setSize-TOKEN_USER_IP_LIMIT_COUNT);
					redisTemplate_KVString.opsForZSet().add(key, ip, 1);// 将新的ip存入池中
				} else {// 存储池未达到上线，则将ip存入池中
					redisTemplate_KVString.opsForZSet().add(key, ip, 1);
				}
			}
		} else {
			redisTemplate_KVString.opsForZSet().add(key, ip, 1);
		}
	}
	/**
	 * 验证ip常用存储池中是否存在ip
	 * @param userId
	 * @param ip
	 */
	public boolean verify(int userId, String ip) {
		String key = TOKEN_USER_IPS_REDIS_KEY_PREFIX + userId;
		Set<String> set = redisTemplate_KVString.opsForZSet().range(key, 0, TOKEN_USER_IP_LIMIT_COUNT);
		for (String sIp : set) {
			if (sIp.equals(ip)) {
				return true;
			}
		}
		return false;
	}
}
