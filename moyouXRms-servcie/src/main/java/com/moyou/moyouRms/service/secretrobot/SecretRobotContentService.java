package com.moyou.moyouRms.service.secretrobot;

import java.util.List;

import com.moyou.moyouRms.model.secretRobot.SecretRobotContent;


public interface SecretRobotContentService {
    int deleteByPrimaryKey(Integer id);

    int insert(SecretRobotContent record);

    int insertSelective(SecretRobotContent record);

    SecretRobotContent selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecretRobotContent record);

    int updateByPrimaryKey(SecretRobotContent record);
    /**
     * 获取机器人发布秘密内容
     * @param id
     * @return
     */
	List<SecretRobotContent> selectSecretContentBySecretId(Integer id);
}