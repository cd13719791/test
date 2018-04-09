package com.moyou.moyouRms.dao.systemFund;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.systemFund.SystemFund;

public interface SystemFundMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(SystemFund record);

	int insertSelective(SystemFund record);

	SystemFund selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(SystemFund record);

	int updateByPrimaryKey(SystemFund record);

	List<SystemFund> selectSystemFundLog(PageBean pb);

	Map<String, Object> selectSystemFundSum();
}