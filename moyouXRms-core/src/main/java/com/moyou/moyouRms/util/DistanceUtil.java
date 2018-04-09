package com.moyou.moyouRms.util;

import java.text.DecimalFormat;

import org.springframework.util.StringUtils;

/**
 * 根据经纬度计算距离（单位为KM）
 * 
 * @author zkd
 *
 */
public class DistanceUtil {

	private static final double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double getDistance(double lngme, double latme, double long2,
			double lat2) {
		double a, b, d, sa2, sb2;
		latme = rad(latme);
		lat2 = rad(lat2);
		a = latme - lat2;
		b = rad(lngme - long2);

		sa2 = Math.sin(a / 2.0);
		sb2 = Math.sin(b / 2.0);
		d = 2
				* EARTH_RADIUS
				* Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(latme)
						* Math.cos(lat2) * sb2 * sb2));
		return d;
	}

	public static void main(String[] args) {
		System.out.println(handleDataInfo(1523));
	}

	/**
	 * 格式化人数
	 * 
	 * @param data
	 * @return
	 */
	public static String handleData(Integer data) {
		DecimalFormat df = new DecimalFormat("######0.0");
		if (data < 10000) {
			return String.valueOf(data);
		}
		String result = df
				.format(Double.parseDouble(String.valueOf(data)) / 10000D);
		if (StringUtils.endsWithIgnoreCase(String.valueOf(result), ".0")) {
			result = String.valueOf((int) Double.parseDouble(result));
		}
		return result + "W";
	}

	/**
	 * 格式化“千” * @param data
	 * 
	 * @return
	 */
	public static String handleDataInfo(Integer data) {
		if (data == null) {
			return "0";
		}
		DecimalFormat df = new DecimalFormat("######0.0");
		if (data < 1000) {
			return String.valueOf(data);
		}
		String result = df
				.format(Double.parseDouble(String.valueOf(data)) / 1000D);
		if (StringUtils.endsWithIgnoreCase(String.valueOf(result), ".0")) {
			result = String.valueOf((int) Double.parseDouble(result));
		}

		return result + "K";

	}

}