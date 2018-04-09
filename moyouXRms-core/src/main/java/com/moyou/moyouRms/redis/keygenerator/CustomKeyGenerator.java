package com.moyou.moyouRms.redis.keygenerator;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/7/11 0011.
 */
@Configuration
@ComponentScan("com.immouo.moyou.core.redis.keygenerator")
public class CustomKeyGenerator extends CachingConfigurerSupport {

	@Bean
	public KeyGenerator myKeyGenerator() {
		return (o, method, objects) -> {
			StringBuilder sb = new StringBuilder();
			sb.append(o.getClass().getName());
			sb.append(method.getName());
			for (Object obj : objects) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};
	}
}
