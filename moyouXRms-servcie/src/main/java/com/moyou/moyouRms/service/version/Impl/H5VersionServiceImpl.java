package com.moyou.moyouRms.service.version.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.version.H5VersionMapper;
import com.moyou.moyouRms.model.version.Version;
import com.moyou.moyouRms.service.version.H5VersionService;

@Service
public class H5VersionServiceImpl implements H5VersionService{
	
	@Resource
	H5VersionMapper h5VersionMapper;
	
	@Override
	public Version queryLatestVersion() {
		return  h5VersionMapper.queryLatestVersion();
	}
	

}
