package com.moyou.moyouRms.dao.version;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.version.Version;

public interface VersionMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Version record);

	int insertSelective(Version record);

	Version selectByPrimaryKey(Integer id);
/**
 * 修改发布状态
 * @param record
 * @return
 */
	int updateByPrimaryKeySelective(Version record);

	int updateByPrimaryKey(Version record);

	/**
	 * 查询最新的版本信息
	 * 
	 * @return
	 */
	Version queryTheLatestVersion();

	/**
	 * 根据版本号查询版本信息
	 * 
	 * @param versionName
	 * @return
	 */
	Version queryVersionByVersionName(String versionName);

	/**
	 * 初始化版本管理
	 * 
	 * @return
	 */
	List<Version> queryVersionList(PageBean pagebean);
	Integer queryPhoneRegOn();
}