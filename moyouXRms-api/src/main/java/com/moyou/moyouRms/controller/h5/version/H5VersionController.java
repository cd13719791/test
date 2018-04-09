package com.moyou.moyouRms.controller.h5.version;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.model.version.Version;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.version.H5VersionService;

@RestController
@RequestMapping("/h5/version")
public class H5VersionController {
	
	@Resource
	H5VersionService h5VersionService;
	
	@RequestMapping(value=  "query_latest_version", method = RequestMethod.POST)
	public ApiResult queryLatestVersion() {
		Version version = h5VersionService.queryLatestVersion();
		return new ApiResult(RESPONSE.SUCCESS, "成功", version);
	}
	
	
	
	
	

}
