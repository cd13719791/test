package com.moyou.moyouRms.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * 
 * @author yubing
 * @time 2016年9月27日
 */
public class Md5Util {
	/**
	 * MD5摘要
	 * 
	 * @param pw
	 *            要进行MD5摘要的字符串
	 * @return
	 */
	public static String getMd5(String pw) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(pw.getBytes());
		return bytesToHex(messageDigest.digest());
	}

	/**
	 * byte数组转换成16进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	private static String bytesToHex(byte[] bytes) {
		int i;
		StringBuffer sBuffer = new StringBuffer("");
		for (int index = 0; index < bytes.length; index++) {
			i = bytes[index];
			if (i < 0)
				i += 256;
			if (i < 16)
				sBuffer.append("0");
			sBuffer.append(Integer.toHexString(i));
		}
		return sBuffer.toString().toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(getMd5(""));
	}
}
