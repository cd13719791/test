package com.moyou.moyouRms.service.limitip.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.limitip.LimitIpMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.limitip.LimitIp;
import com.moyou.moyouRms.model.user.Page;
import com.moyou.moyouRms.service.limitip.LimitIpService;

@Service
public class LimitIpServiceImpl implements LimitIpService {

	@Resource
	private LimitIpMapper limitIpMapper;

	@Override
	public List<LimitIp> queryLimitIpList(Page page) {
		PageBean pageBean = new PageBean();
		pageBean.setPageNumber(page.getPageNumber());
		pageBean.setPageSize(page.getPageSize());
		List<LimitIp> results = limitIpMapper.queryLimitIpList(pageBean);
		page.setTotal(pageBean.getTotalRecord());
		page.setResults(results);
		return results;
	}

	@Override
	public int updateByPrimaryKeySelective(LimitIp record) {
		record.setUpdateTime(new Date());
		return limitIpMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertLimitIp(LimitIp record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LimitIp queryByIpAddress(String ipAddress) {
		return limitIpMapper.queryByIpAddress(ipAddress);
	}

	@Override
	public int insert(LimitIp record) {
		record.setState(LimitIp.STATE_OK);
		return limitIpMapper.insert(record);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean queryLimitIpListByType(PageBean page) {
		// TODO Auto-generated method stub
		page.setResults(limitIpMapper.queryLimitIpListByType(page));
		return page;
	}

	@Override
	public int updatelimitip(LimitIp record) {
		// TODO Auto-generated method stub
		return limitIpMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int insertLimitip(LimitIp record) {
		// TODO Auto-generated method stub
		record.setModeType(LimitIp.MODE_TYPE_PHONE);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setState(LimitIp.STATE_OK);
		return limitIpMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return limitIpMapper.deleteByPrimaryKey(id);
	}
}
