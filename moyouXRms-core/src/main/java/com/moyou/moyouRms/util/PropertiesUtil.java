package com.moyou.moyouRms.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.moyou.moyouRms.constants.CONSTANT;

/**
 * @ClassName: PropertiesUtil
 * @author PzC.
 * @date 2016年11月28日 下午5:58:39
 * 
 */
public class PropertiesUtil {
	private static final String FILEPATH = "com.moyou.moyouRms.property.common-config";

	public static int bundleToInt(String key) {
		return Integer.parseInt(ResourceBundle.getBundle(FILEPATH).getString(
				key));
	}

	public static double bundleToDouble(String key) {
		return Double.parseDouble(ResourceBundle.getBundle(FILEPATH).getString(
				key));
	}

	/**
	 * 获取属性文件数据
	 * 
	 * @param key
	 *            文件内容kay
	 * @param filePath
	 *            属性文件路径
	 * @return
	 */
	public static String getValueByKey(String key, String filePath) {
		String str = StringUtil.getStr(ResourceBundle.getBundle(filePath)
				.getString(key));
		/*try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}*/
		return str;
	}
	public static String getApiCodeMsgByKey(String key) {
		return StringUtil.getStr(ResourceBundle.getBundle(CONSTANT.DEFAULT_CODE_MSG_FILE_PATH).getString(key));
	}
	public static String getValueByKey(String key) {
		return StringUtil.getStr(ResourceBundle.getBundle(CONSTANT.DEFAULT_CODE_MSG_FILE_PATH).getString(key));
	}
	/**
	 * <font color="red">无占位符的字符资源</font>
	 * 
	 * @Title bundle
	 * @param key
	 * @return {@link String}
	 * @since 1.0
	 */

	public static String bundle(String key) {
		return ResourceBundle.getBundle(FILEPATH).getString(key);
	}

	/**
	 * <font color="red">有占位符的字符资源</font>
	 * 
	 * @Title bundle
	 * @param key
	 * @param arguments
	 * @return {@link String}
	 * @since 1.0
	 */
	public static String bundle(String key, Object... arguments) {
		String str = StringUtil.getStr(ResourceBundle.getBundle(FILEPATH)
				.getString(key));
		/*try {
			str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return MessageFormat.format(str, arguments);
	}

	public static void main(String[] args) {
		System.out
				.println(getValueByKey("reg.push", FILEPATH));
	}
}
