package com.moyou.moyouRms.util.file;

import java.io.File;
import java.io.FilenameFilter;

public class CMyFilenameFilter extends Object implements FilenameFilter {

	private String sExt;

	public CMyFilenameFilter(String _extendName) {
		sExt = _extendName;
	}

	public boolean accept(File _dir, String _name) {
		return _name.endsWith(sExt);
	}

}