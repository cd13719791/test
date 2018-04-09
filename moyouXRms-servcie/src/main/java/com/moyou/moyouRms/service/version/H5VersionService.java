package com.moyou.moyouRms.service.version;

import com.moyou.moyouRms.model.version.Version;

public interface H5VersionService {
	
	/**
	 * H5查询最近的Version信息
	 * @return
	 */
	Version queryLatestVersion();
	
	
	
}
