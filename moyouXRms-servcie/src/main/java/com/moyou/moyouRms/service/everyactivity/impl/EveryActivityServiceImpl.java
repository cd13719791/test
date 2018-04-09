package com.moyou.moyouRms.service.everyactivity.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.everyactivity.EveryActivityMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.everyactivity.EveryActivity;
import com.moyou.moyouRms.service.everyactivity.EveryActivityService;

@Service
public class EveryActivityServiceImpl implements EveryActivityService {
	@Resource
	private EveryActivityMapper everyActivityMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return everyActivityMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EveryActivity record) {
		// TODO Auto-generated method stub
		return everyActivityMapper.insert(record);
	}

	@Override
	public int insertSelective(EveryActivity record) {
		// TODO Auto-generated method stub
		return everyActivityMapper.insertSelective(record);
	}

	@Override
	public EveryActivity selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return everyActivityMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EveryActivity record) {
		// TODO Auto-generated method stub
		record.setUpdateTime(new Date());
		return everyActivityMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EveryActivity record) {
		// TODO Auto-generated method stub
		return everyActivityMapper.updateByPrimaryKey(record);
	}

	@Override
	public EveryActivity selectEveryActivityList() {
		// TODO Auto-generated method stub
		return everyActivityMapper.selectEveryActivityList();
	}

	@Override
	public List<EveryActivity> selectActivityInfoList(PageBean pb) {
		// TODO Auto-generated method stub
		return everyActivityMapper.selectActivityInfoList(pb);
	}

	@Override
	public Map<String, Object> selectActivitySum() {
		// TODO Auto-generated method stub
		return everyActivityMapper.selectActivitySum();
	}

	@Override
	public EveryActivity selectEveryActivityLimit() {
		// TODO Auto-generated method stub
		return everyActivityMapper.selectEveryActivityLimit();
	}

	@Override
	public EveryActivity selectYesterdayActivity() {
		// TODO Auto-generated method stub
		return everyActivityMapper.selectYesterdayActivity();
	}

	@Override
	public int updateState() {
		// TODO Auto-generated method stub
		return everyActivityMapper.updateState();
	}

}
