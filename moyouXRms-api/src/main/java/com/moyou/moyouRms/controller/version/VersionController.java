package com.moyou.moyouRms.controller.version;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.controller.BaseController;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.version.Version;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.version.VersionService;
import com.moyou.moyouRms.util.EntityUtil;

/**
 * @author PzC.
 * @time 2017年1月12日 下午1:47:54
 * 
 */
@RestController
@RequestMapping(value = "/version")
public class VersionController extends BaseController {
	@Resource
	private VersionService versionService;

	/**
	 * 查询版本相关信息
	 * 
	 * @param versionName
	 * @return
	 */
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	public ApiResult queryVersion(@RequestBody String versionName) {
		Assert.notNull(versionName, "参数不能为空");
		Version version = StringUtils.isEmpty(versionName) ? versionService
				.queryLatestVersion() : versionService
				.queryVersionByVersionName(EntityUtil
						.processSingleParam(versionName));
		Map<String, Object> map = Maps.newHashMap();
		map.put("versionName", version.getVersionName());
		map.put("downloadUrl", version.getDownloadUrl());
		map.put("phoneRegOn", version.getPhoneRegOn());
		map.put("content", version.getContent());
		map.put("forceUpdateState", version.getForceUpdateState());
		return Objects.isNull(version) ? new ApiResult(RESPONSE.ERROR, "无版本信息")
				: new ApiResult(RESPONSE.SUCCESS, "成功", map);
	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public ApiResult check() {
		return new ApiResult(versionService.queryPhoneRegOn());
	}

	/**
	 * 插入一条版本信息
	 * 
	 * @param greetingUserSendLog
	 * @return
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ApiResult insert(@RequestBody Version record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				versionService.insert(record));
	}

	/**
	 * 修改发布状态
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public ApiResult updateByPrimaryKeySelective(@RequestBody Version record) {
		return new ApiResult(RESPONSE.SUCCESS, "成功",
				versionService.updateByPrimaryKeySelective(record));
	}

	/**
	 * 初始化版本管理
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/queryVersionList", method = RequestMethod.POST)
	public ApiResult queryVersionList(@RequestBody Page page) {
		versionService.queryVersionList(page);
		return new ApiResult(RESPONSE.SUCCESS, "成功", page);
	}
}
