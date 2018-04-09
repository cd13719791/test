package com.moyou.moyouRms.service.limitip;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.limitip.LimitIp;
import com.moyou.moyouRms.model.user.Page;

public interface LimitIpService {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 初始化IP限制
	 */
	List<LimitIp> queryLimitIpList(Page Page);

	/**
	 * 删除一条IP限制记录
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(LimitIp record);

	/**
	 * 添加一条iP限制
	 * 
	 * @param record
	 * @return
	 */
	int insertLimitIp(LimitIp record);

	/**
	 * 查询该Ip是否已被限制
	 * 
	 * @param ipAddress
	 * @return
	 */
	LimitIp queryByIpAddress(String ipAddress);

	/**
	 * 添加一条IP限制
	 * 
	 * @param record
	 * @return
	 */
	int insert(LimitIp record);

	/**
	 * 根据type 查询ip限制表
	 * 
	 * @param page
	 *            `mode_type` '1后台2前台'
	 * @return
	 */
	PageBean queryLimitIpListByType(PageBean page);

	int updatelimitip(LimitIp limitIp);

	/**
	 * 
	 * @param record
	 * @return
	 */
	int insertLimitip(LimitIp record);
}
