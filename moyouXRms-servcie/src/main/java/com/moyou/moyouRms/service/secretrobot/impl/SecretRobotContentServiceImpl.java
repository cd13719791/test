package com.moyou.moyouRms.service.secretrobot.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.secretRobot.SecretRobotContentMapper;
import com.moyou.moyouRms.model.secretRobot.SecretRobotContent;
import com.moyou.moyouRms.service.secretrobot.SecretRobotContentService;
@Service
public class SecretRobotContentServiceImpl implements SecretRobotContentService {
	@Resource
	private SecretRobotContentMapper secretRobotContentMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretRobotContentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SecretRobotContent record) {
		// TODO Auto-generated method stub
		return secretRobotContentMapper.insert(record);
	}

	@Override
	public int insertSelective(SecretRobotContent record) {
		// TODO Auto-generated method stub
		return secretRobotContentMapper.insertSelective(record);
	}

	@Override
	public SecretRobotContent selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return secretRobotContentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SecretRobotContent record) {
		// TODO Auto-generated method stub
		return secretRobotContentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SecretRobotContent record) {
		// TODO Auto-generated method stub
		return secretRobotContentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<SecretRobotContent> selectSecretContentBySecretId(Integer id) {
		// TODO Auto-generated method stub
		return secretRobotContentMapper.selectSecretContentBySecretId(id);
	}

}
