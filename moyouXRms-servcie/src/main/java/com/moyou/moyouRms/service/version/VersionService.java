package com.moyou.moyouRms.service.version;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.model.version.Version;

/**
 * @author PzC.
 * @time 2017年1月12日 下午1:51:21
 * 
 */
public interface VersionService {

	/**
	 * 查询最新的版本信息
	 * 
	 * @return
	 */
	Version queryLatestVersion();

	/**
	 * 根据版本号查询版本信息
	 * 
	 * @param versionName
	 * @return
	 */
	Version queryVersionByVersionName(String versionName);
	
	Map<String, Object> queryPhoneRegOn();
	/**
	 * 添加一条版本信息
	 */
	int insert(Version record);
	/**
	 * 修改发布状态
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Version record);
	/**
	 * 初始化版本管理
	 * 
	 * @return
	 */
	List<Version> queryVersionList(Page page);
}
