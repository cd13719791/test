package com.moyou.moyouRms.dao.secretRobot;

import java.util.List;

import com.moyou.moyouRms.model.secretRobot.SecretRobotContent;


public interface SecretRobotContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecretRobotContent record);

    int insertSelective(SecretRobotContent record);

    SecretRobotContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecretRobotContent record);

    int updateByPrimaryKey(SecretRobotContent record);

	List<SecretRobotContent> selectSecretContentBySecretId(Integer id);
}