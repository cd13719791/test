package com.moyou.moyouRms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * moyouId List工具类
 * 
 * @author yubing
 * @time 2016年9月27日
 */
public class GenerateListUtil {
	/**
	 * 返回从 start 到 end 的 List<String> 数组 包含 start 并且包含 end
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static List<String> getList(long start, long end) {
		List<String> moyouIdList = new ArrayList<>();
		for (Long l = Math.min(start, end); l <= Math.max(start, end); l++) {
			moyouIdList.add(l.toString());
		}
		Collections.shuffle(moyouIdList);
		return moyouIdList;
	}
}
