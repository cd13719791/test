package com.moyou.moyouRms.service.adsupplyservice.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.adsupply.AdSupplyCommentMapper;
import com.moyou.moyouRms.model.adsupply.AdSupplyComment;
import com.moyou.moyouRms.service.adsupplyservice.AdSupplyCommentService;

/** 
 * @author 作者:陈旭 
 * @version 创建时间：2017年7月18日 上午10:29:56 
 * 类说明 
 * 广告评论
 */
@Service
public class AdSupplyCommentServiceImpl implements AdSupplyCommentService {
	@Resource
	private AdSupplyCommentMapper adSupplyCommentMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return adSupplyCommentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AdSupplyComment record) {
		// TODO Auto-generated method stub
		return adSupplyCommentMapper.insert(record);
	}

	@Override
	public int insertSelective(AdSupplyComment record) {
		// TODO Auto-generated method stub
		return adSupplyCommentMapper.insertSelective(record);
	}

	@Override
	public AdSupplyComment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return adSupplyCommentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AdSupplyComment record) {
		// TODO Auto-generated method stub
		return adSupplyCommentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AdSupplyComment record) {
		// TODO Auto-generated method stub
		return adSupplyCommentMapper.updateByPrimaryKey(record);
	}

}
