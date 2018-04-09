package com.moyou.moyouRms.service.secretrobot.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.secretRobot.SecretRobotMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.secretRobot.SecretRobot;
import com.moyou.moyouRms.model.secretRobot.SecretRobotDetailResult;
import com.moyou.moyouRms.service.secretrobot.SecretRobotContentService;
import com.moyou.moyouRms.service.secretrobot.SecretRobotService;

@Service
public class SecretRobotServiceImpl implements SecretRobotService
{
    @Resource
    private SecretRobotMapper secretRobotMapper;
    @Resource
    private SecretRobotContentService secretRobotContentService;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SecretRobot record)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.insert(record);
    }

    @Override
    public int insertSelective(SecretRobot record)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.insertSelective(record);
    }

    @Override
    public SecretRobot selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(SecretRobot record)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(SecretRobot record)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SecretRobot> selectSecretRobotList(PageBean pb)
    {
        // TODO Auto-generated method stub
        List<SecretRobot> list = secretRobotMapper.selectSecretRobotList(pb);
        for (int i = 0; i < list.size(); i++)
        {
            list.get(i)
                .setSecretContentList(secretRobotContentService.selectSecretContentBySecretId(list.get(i)
                    .getId()));
        }
        return list;
    }

    @Override
    public int selectSecretRobotYesterdayCount()
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.selectSecretRobotYesterdayCount();
    }

    @Override
    public int updatePushStateByPrimaryKey(SecretRobot dRobot)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.updatePushStateByPrimaryKey(dRobot);
    }

    @Override
    public Map<String, Integer> selectSecretRobotCount()
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.selectSecretRobotCount();
    }

    @Override
    public List<SecretRobot> selectSecretRobotListExtendsContent(PageBean pb)
    {
        // TODO Auto-generated method stub
        return secretRobotMapper.selectSecretRobotList(pb);
    }

    @Override
    public SecretRobotDetailResult selectSecretRobotDetailBySecretId(Integer id)
    {
        SecretRobotDetailResult secretRobotDetailResult =
            secretRobotMapper.selectSecretRobotDetailBySecretId(id);
        secretRobotDetailResult.setSecretContentList(secretRobotContentService.selectSecretContentBySecretId(id));
        return secretRobotDetailResult;
    }

}
