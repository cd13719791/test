package com.moyou.moyouRms.dao.version;

import com.moyou.moyouRms.model.version.Version;

public interface H5VersionMapper {

	Version queryLatestVersion();
	
	
}
