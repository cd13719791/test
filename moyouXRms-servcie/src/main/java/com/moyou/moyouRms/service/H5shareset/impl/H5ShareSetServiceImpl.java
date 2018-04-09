package com.moyou.moyouRms.service.H5shareset.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.h5shareset.H5ShareSetMapper;
import com.moyou.moyouRms.model.h5shareset.H5ShareSet;
import com.moyou.moyouRms.service.H5shareset.H5ShareSetService;


@Service
public class H5ShareSetServiceImpl implements H5ShareSetService {
	@Resource
	H5ShareSetMapper h5ShareSetMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return h5ShareSetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(H5ShareSet record) {
		// TODO Auto-generated method stub
		return h5ShareSetMapper.insert(record);
	}

	@Override
	public int insertSelective(H5ShareSet record) {
		// TODO Auto-generated method stub
		return h5ShareSetMapper.insertSelective(record);
	}

	@Override
	public H5ShareSet selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return h5ShareSetMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(H5ShareSet record) {
		// TODO Auto-generated method stub
		return h5ShareSetMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(H5ShareSet record) {
		// TODO Auto-generated method stub
		return h5ShareSetMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<H5ShareSet> selectH5ShareSetList() {
		// TODO Auto-generated method stub
		return h5ShareSetMapper.selectH5ShareSetList();
	}

}
