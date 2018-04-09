/**
 */
package com.moyou.moyouRms.intercept;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.security.TokenIpVerify;
import com.moyou.moyouRms.util.StringUtil;

/**
 * @describe h5需要用户登录的地址拦截
 * @author liuxinyi
 * @date 2017年2月11日
 * @email liuxinyi@mousns.com
 * @version 1.0.0
 */
public class H5TokenInterceptor implements HandlerInterceptor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisTemplate<String, byte[]> redisTemplate;
	@Autowired
	private TokenIpVerify tokenIpVerify;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.debug("接口拦截器");
		String token = request.getHeader("token");
		String deviceid = request.getHeader("deviceid");
		 
		// token验证
		int status = 200;
		byte[] tokenValueByte = redisTemplate.opsForValue().get(CONSTANT.H5_USER_TOKEN_PREFIX+token);// 如果访问接口的时间戳与当前时间差值大于1个小时，则返回接口访问超时
		String tokenValue = new String(tokenValueByte, Charset.defaultCharset());
		if (StringUtil.isEmpty(token) || StringUtil.isEmpty(tokenValue)) {
			logger.error("token不存在！");
			status = 4031;
			return false;
		} else if (StringUtil.isNotEmpty(tokenValue)) {
			String tokenValueArr[] = tokenValue.split("_");
			int userId = StringUtil.getInt(tokenValueArr[0]);
			String redisClientIp = StringUtil.getStr(tokenValueArr[1]);
			String redisDeviceid = StringUtil.getStr(tokenValueArr[2]);
			String clientIp = StringUtil.getIpAddr(request);
			if (! redisClientIp.equals(clientIp)) {// ip不相等则 
				boolean verifyIpFlag = tokenIpVerify.verify(userId, clientIp);// 当ip不等，则去常用ip池中验证ip
				if (verifyIpFlag) {
					return true;
				}
				status = 4032;//  第一步1.ip不相等 第二步2.常用ip池中验证ip错误 则 token无效
				logger.error("ip不相等则 token无效！");
				return false;
			} else if (! redisDeviceid.equals(deviceid)) {// 设备号不相等则 token无效
				logger.error("设备号不相等则 token无效！");
				status = 4033;
				return false;
			}
			tokenIpVerify.updateIpPool(userId, clientIp);// 更新常用ip存储池
		}
		response.setStatus(status);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	public static String getBodyString(BufferedReader br) {
		  String inputLine;
		       String str = "";
		     try {
		       while ((inputLine = br.readLine()) != null) {
		        str += inputLine;
		       }
		       br.close();
		     } catch (IOException e) {
//		       System.out.println("IOException: " + e);
		     }
		     return str;
		 }
}
