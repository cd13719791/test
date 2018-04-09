package com.moyou.moyouRms.service.version.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.moyou.moyouRms.dao.version.VersionMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.version.Version;
import com.moyou.moyouRms.service.version.VersionService;

/**
 * @author PzC.
 * @time 2017年1月12日 下午1:51:17
 * 
 */
@Service
public class VersionServiceImpl implements VersionService{
	@Resource
	private VersionMapper versionMapper;
	
	@Override
	public Version queryLatestVersion() {
		return versionMapper.queryTheLatestVersion();
	}

	@Override
	public Version queryVersionByVersionName(String versionName) {
		return versionMapper.queryVersionByVersionName(versionName);
	}

	@Override
	public Map<String, Object> queryPhoneRegOn() {
		Map<String, Object> map = Maps.newHashMap();
		map.put("phoneRegOn", versionMapper.queryPhoneRegOn());
		return map;
	}

	@Override
	public int insert(Version record) {
		return versionMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Version record) {
			return versionMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Version> queryVersionList(Page page) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(page.getPageNumber());
		pageBean.setPageSize(page.getPageSize());
		List<Version> results = versionMapper
				.queryVersionList(pageBean);
		page.setTotal(pageBean.getTotalRecord());
		page.setResults(results);
		return results;
	}
	
}
