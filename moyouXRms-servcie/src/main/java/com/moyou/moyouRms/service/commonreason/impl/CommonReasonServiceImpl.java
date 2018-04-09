package com.moyou.moyouRms.service.commonreason.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.commonReason.CommonReasonMapper;
import com.moyou.moyouRms.model.commonReason.CommonReason;
import com.moyou.moyouRms.service.commonreason.CommonReasonService;
@Service
public class CommonReasonServiceImpl implements CommonReasonService {
@Resource
private CommonReasonMapper commonReasonMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return commonReasonMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(CommonReason record) {
		// TODO Auto-generated method stub
		return commonReasonMapper.insert(record);
	}

	@Override
	public int insertSelective(CommonReason record) {
		// TODO Auto-generated method stub
		return commonReasonMapper.insertSelective(record);
	}

	@Override
	public CommonReason selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return commonReasonMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CommonReason record) {
		// TODO Auto-generated method stub
		return commonReasonMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(CommonReason record) {
		// TODO Auto-generated method stub
		return commonReasonMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(CommonReason record) {
		// TODO Auto-generated method stub
		return commonReasonMapper.updateByPrimaryKey(record);
	}

	@Override
	public CommonReason selectCommonReasonByObjectId(CommonReason commonReason) {
		// TODO Auto-generated method stub
		return commonReasonMapper.selectCommonReasonByObjectId(commonReason);
	}

}
