/**
 * 
 */
package com.moyou.moyouRms.util.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @Description 文件过滤类
 * @author liuxinyi
 * @date 2016年12月16日 下午1:52:00
 * @version 1.0.0
 */
public class FileNameSelector implements FilenameFilter {
	private String fileName = "";

	public FileNameSelector(String fileExtensionNoDot) {
		fileName = fileExtensionNoDot;
	}

	@Override
	public boolean accept(File dir, String name) {
		return name.toUpperCase().equals(fileName.toUpperCase());
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
