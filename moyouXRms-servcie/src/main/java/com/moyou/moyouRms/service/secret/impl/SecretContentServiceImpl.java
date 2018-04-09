package com.moyou.moyouRms.service.secret.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.secret.SecretContentMapper;
import com.moyou.moyouRms.model.secret.SecretContent;
import com.moyou.moyouRms.service.secret.SecretContentService;
@Service
public class SecretContentServiceImpl implements SecretContentService {
	@Resource
	private SecretContentMapper secretContentMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretContentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SecretContent record) {
		// TODO Auto-generated method stub
		return secretContentMapper.insert(record);
	}

	@Override
	public int insertSelective(SecretContent record) {
		// TODO Auto-generated method stub
		return secretContentMapper.insertSelective(record);
	}

	@Override
	public SecretContent selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SecretContent record) {
		// TODO Auto-generated method stub
		return secretContentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SecretContent record) {
		// TODO Auto-generated method stub
		return secretContentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SecretContent> selectSecretContentBySecretId(Integer id) {
		// TODO Auto-generated method stub
		return secretContentMapper.selectSecretContentBySecretId(id);
	}


}
