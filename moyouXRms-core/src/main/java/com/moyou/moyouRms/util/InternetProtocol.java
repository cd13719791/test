package com.moyou.moyouRms.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;

/**
 * IP 解析
 * 
 * @author PzC.
 * @time 2016年12月13日 下午1:25:09
 * 
 */
public final class InternetProtocol {
	private static final Logger LOG = Logger.getLogger(InternetProtocol.class);

	/**
	 * 获取客户端IP地址.<br>
	 * 支持多级反向代理
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return 客户端真实IP地址
	 */
	public static String getRemoteAddr(final HttpServletRequest request) {
		try {
			String remoteAddr = request.getHeader("X-Forwarded-For");
			System.out.println(remoteAddr);
			// 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
			if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
				String[] array = remoteAddr.split(",");
				for (String element : array) {
					if (isEffective(element)) {
						remoteAddr = element;
						break;
					}
				}
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = request.getHeader("X-Real-IP");
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
				System.out.println(remoteAddr);
			}
			return remoteAddr;
		} catch (Exception e) {
			LOG.error("get romote ip error,error message:" + e.getMessage());
			return "";
		}
	}

	/**
	 * 获取客户端源端口
	 * 
	 * @param request
	 * @return
	 */
	public static Long getRemotePort(final HttpServletRequest request) {
		try {
			String port = request.getHeader("remote-port");
			if (!StringUtils.isEmpty(port)) {
				try {
					return Long.parseLong(port);
				} catch (NumberFormatException ex) {
					LOG.error("convert port to long error , port:	" + port);
					return 0l;
				}
			} else {
				return 0l;
			}
		} catch (Exception e) {
			LOG.error("get romote port error,error message:" + e.getMessage());
			return 0l;
		}
	}

	/**
	 * 远程地址是否有效.
	 * 
	 * @param remoteAddr
	 *            远程地址
	 * @return true代表远程地址有效，false代表远程地址无效
	 */
	private static boolean isEffective(final String remoteAddr) {
		boolean isEffective = false;
		if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
				&& (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
			isEffective = true;
		}
		return isEffective;
	}

	public static Map<String, Object> getAddressByIP() {
		Map<String, Object> returnMap = Maps.newHashMap();
		BufferedReader reader = null;
		URL url = null;
		URLConnection conn = null;
		String line = null;
		StringBuffer result = null;
		String processResult = null;

		try {
			url = new URL("http://whois.pconline.com.cn/ipJson.jsp?json=true");
			conn = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "GBK"));
			result = new StringBuffer();
			while ((line = reader.readLine()) != null) {
				result.append(line);
			}
			processResult = JsonUtil.toJson(result.toString());
		} catch (IOException e) {
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
				returnMap.put("error", e.getMessage());
				returnMap.put("cause", e.getCause());
				return returnMap;
			}
		}
		return JsonUtil.toMap(processResult);
	}

}