package com.moyou.moyouRms.util;

import java.util.UUID;

import org.springframework.util.StringUtils;

public class UUIDUtil {
	/**
	 * @return UUID随机数
	 */
	public static String getUUID() {
		return StringUtils.replace(UUID.randomUUID().toString(), "-", "");
	}

	public static void main(String[] args) {
		System.out.println(UUIDUtil.getUUID());
	}

}
