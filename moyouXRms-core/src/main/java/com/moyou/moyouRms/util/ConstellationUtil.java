package com.moyou.moyouRms.util;

import java.time.LocalDate;
import java.util.Date;

import com.moyou.moyouRms.constants.CONSTANT;

/**
 * 星座工具类
 * 
 * @author yubing
 * @date 2016年10月18日 下午5:42:38
 */
public class ConstellationUtil {
	public static String getConstellation(int month, int day) {
		return day < CONSTANT.DAY_ARR[month - 1] ? CONSTANT.CONSTELLATION_ARR[month - 1]
				: CONSTANT.CONSTELLATION_ARR[month];
	}

	public static String getConstellation(Date date) {
		LocalDate localDate = DateTimeUtil.convertDateToLocalDate(date);
		return getConstellation(localDate.getMonthValue(),
				localDate.getDayOfMonth());
	}

	public static void main(String[] args) {
		System.out.println(getConstellation(3, 23));
	}
}
