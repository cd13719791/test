package com.moyou.moyouRms.confing;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.moyou.moyouRms.util.StringUtil;

/**
 * @title 应用配置文件
 * @author liuxinyi
 * @date 2016年11月14日
 * @version 1.0.0
 */
public class SysConf extends Properties {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4595789767351251968L;

	private static final Logger logger = LoggerFactory.getLogger(SysConf.class);

	private static SysConf properties = null;

	private SysConf() {
	};

	/**
	 * 初始化存储实体
	 * 
	 * @return
	 */
	public static SysConf getInstance() {
		if (properties == null) {
			syncLoadProperties();
		}
		return properties;
	}

	private static synchronized void syncLoadProperties() {
		try {
			if (properties == null) {
				properties = new SysConf();
				properties.load(new InputStreamReader(SysConf.class
						.getClassLoader().getResourceAsStream(
								"sysconf.properties"), "UTF-8"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace(System.out);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
	}

	public String getValueByKey(String strKey) {
		String ret = properties.getProperty(strKey);
		return ret;
	}

	public String getValueByKey(String strKey, Object... papamsArr) {
		String ret = properties.getProperty(strKey);
		if (ret != null && papamsArr != null && papamsArr.length > 0) {
			for (int i = 0; i < papamsArr.length; i++) {
				ret = ret.replace("{" + i + "}", papamsArr[i].toString());
			}
		}
		return ret;
	}

	public int getIntValueByKey(String strKey) {
		int result = 0;
		try {
			result = StringUtil.getInt(new String(properties
					.getProperty(strKey).getBytes("ISO-8859-1"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return result;
	}

	public static void main(String[] args) {
		SysConf AppConf = SysConf.getInstance();
		System.out.println(AppConf.getValueByKey("user.default.avatar"));
	}
}
