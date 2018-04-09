package com.moyou.moyouRms.service.goldSignConfigure.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.goldSignConfigure.GoldSignConfigureMapper;
import com.moyou.moyouRms.model.goldSignConfigure.GoldSignConfigure;
import com.moyou.moyouRms.service.goldSignConfigure.GoldSignConfigureService;

/**
 * @author 作者:陈旭
 * @version 创建时间：2017年8月4日 上午11:25:11 类说明
 */
@Service
public class GoldSignConfigureServiceImpl implements GoldSignConfigureService
{
    @Resource
    GoldSignConfigureMapper goldSignConfigureMapper;

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return goldSignConfigureMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(GoldSignConfigure record)
    {
        // TODO Auto-generated method stub
        return goldSignConfigureMapper.insert(record);
    }

    @Override
    public int insertSelective(GoldSignConfigure record)
    {
        // TODO Auto-generated method stub
        return goldSignConfigureMapper.insertSelective(record);
    }

    @Override
    public GoldSignConfigure selectByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return goldSignConfigureMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(GoldSignConfigure record)
    {
        // TODO Auto-generated method stub
        return goldSignConfigureMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(GoldSignConfigure record)
    {
        // TODO Auto-generated method stub
        return goldSignConfigureMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Object> selectAllForGoldSet()
    {
        // TODO Auto-generated method stub
        return goldSignConfigureMapper.selectAllForGoldSet();
    }

}
