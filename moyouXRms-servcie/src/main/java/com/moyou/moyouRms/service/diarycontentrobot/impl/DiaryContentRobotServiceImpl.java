package com.moyou.moyouRms.service.diarycontentrobot.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.diaryContentRobot.DiaryContentRobotMapper;
import com.moyou.moyouRms.model.diaryContentRobot.DiaryContentRobot;
import com.moyou.moyouRms.service.diarycontentrobot.DiaryContentRobotService;
@Service
public class DiaryContentRobotServiceImpl implements DiaryContentRobotService{
	@Resource 
	private DiaryContentRobotMapper diaryContentRobotMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryContentRobotMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DiaryContentRobot record) {
		// TODO Auto-generated method stub
		return diaryContentRobotMapper.insert(record);
	}

	@Override
	public int insertSelective(DiaryContentRobot record) {
		// TODO Auto-generated method stub
		return diaryContentRobotMapper.insertSelective(record);
	}

	@Override
	public DiaryContentRobot selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return diaryContentRobotMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DiaryContentRobot record) {
		// TODO Auto-generated method stub
		return diaryContentRobotMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(DiaryContentRobot record) {
		// TODO Auto-generated method stub
		return diaryContentRobotMapper.updateByPrimaryKeySelective(record);
	}

}
