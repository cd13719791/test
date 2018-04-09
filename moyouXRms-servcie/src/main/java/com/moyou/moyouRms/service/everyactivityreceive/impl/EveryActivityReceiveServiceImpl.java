package com.moyou.moyouRms.service.everyactivityreceive.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.everyactivityreceive.EveryActivityReceiveMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.everyactivityreceive.EveryActivityReceive;
import com.moyou.moyouRms.service.everyactivityreceive.EveryActivityReceiveService;

@Service
public class EveryActivityReceiveServiceImpl implements EveryActivityReceiveService {
	@Resource
	private EveryActivityReceiveMapper everyActivityReceivemapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EveryActivityReceive record) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.insert(record);
	}

	@Override
	public int insertSelective(EveryActivityReceive record) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.insertSelective(record);
	}

	@Override
	public EveryActivityReceive selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EveryActivityReceive record) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EveryActivityReceive record) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.updateByPrimaryKey(record);
	}

	@Override
	public List<EveryActivityReceive> selectActivityReceiveList(PageBean pb) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.selectActivityReceiveList(pb);
	}

	@Override
	public Integer selectActivityDoingCount(Integer integer) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.selectActivityDoingCount(integer);
	}

	@Override
	public Integer selectUnDoingFundSum(Integer id) {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.selectUnDoingFundSum(id);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<EveryActivityReceive> selectActivityReceiveForTestUnit() {
		// TODO Auto-generated method stub
		return everyActivityReceivemapper.selectActivityReceiveForTestUnit();
	}

}
