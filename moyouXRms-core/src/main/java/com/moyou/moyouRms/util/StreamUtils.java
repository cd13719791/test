package com.moyou.moyouRms.util;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Stream 工具
 * 
 * @author PzC.
 * @time 2016年12月1日 下午3:39:54
 * 
 */
public class StreamUtils {
	/**
	 * 获取 Stream 中第一个元素
	 * 
	 * @param collection
	 * @return
	 */
	public static <E> E findFirst(Collection<E> collection) {
		return findFirst(collection.stream());
	}
	
	/**
	 * 获取 Stream 中第一个元素
	 * 
	 * @param stream
	 * @return
	 */
	public static <E> E findFirst(Stream<E> stream) {
		return stream.findFirst().get();
	}
}
