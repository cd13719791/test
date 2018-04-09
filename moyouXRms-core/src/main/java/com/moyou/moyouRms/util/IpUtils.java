package com.moyou.moyouRms.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问ip
 * 
 * @author yuezixin
 * @date 2014年12月26日 上午10:48:48
 * @Version 1.0
 */
public class IpUtils {
	private IpUtils() {
	}

	/**
	 * 返回访问ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null)
			return "unknow";
		String ip = request.getHeader("x-forwarded-for");
		if (hasIp(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (hasIp(ip))
			ip = request.getHeader("X-Forwarded-For");
		if (hasIp(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (hasIp(ip))
			ip = request.getHeader("X-Real-IP");
		if (hasIp(ip))
			ip = request.getRemoteAddr();
		return ip;
	}

	/**
	 * 判断ip是否为null
	 * 
	 * @param ip
	 * @return
	 */
	public static boolean hasIp(String ip) {
		return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
	}
}
