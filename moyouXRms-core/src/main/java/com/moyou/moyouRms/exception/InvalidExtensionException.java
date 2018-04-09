package com.moyou.moyouRms.exception;

import java.util.Arrays;

import com.moyou.moyouRms.constants.FILECONSTANT;

public final class InvalidExtensionException extends RuntimeException {
	private static final long serialVersionUID = -7321086503077898399L;

	public InvalidExtensionException(int i) {
		super("文件名称最大长度为" + i);
	}

	public InvalidExtensionException(String[] strings, String string) {
		super(string + "文件格式异常 默认支持格式为" 
				+ Arrays.asList(FILECONSTANT.DEFAULT_ALLOWED_EXTENSION).toString() 
				+ " 传入格式为" + Arrays.asList(strings).toString());
	}
}
