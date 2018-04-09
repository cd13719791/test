package com.moyou.moyouRms.service.report.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.report.CommonResourceMapper;
import com.moyou.moyouRms.model.report.CommonResource;
import com.moyou.moyouRms.service.report.CommonResourceService;

@Service
public class CommonResourceServiceImpl implements CommonResourceService
{
    @Resource
    private CommonResourceMapper commonResourceMapper;

    @Override
    public List<CommonResource> selectCommonResourceByObjectId(CommonResource commonResource)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.selectCommonResourceByObjectId(commonResource);
    }

    @Override
    public List<CommonResource> selectFirstCommonResourceByObjectId(CommonResource commonResource)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.selectFirstCommonResourceByObjectId(commonResource);
    }

    @Override
    public int insertcommonResource(CommonResource commonResource)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.insertSelective(commonResource);
    }

    @Override
    public CommonResource selectCommonResource()
    {
        // TODO Auto-generated method stub.
        CommonResource commonResource = new CommonResource();
        commonResource.setObjectType(CommonResource.OBJECT_TYPE_TEXT);
        return commonResourceMapper.selectCommonResourceByObjectType(commonResource);
    }

    @Override
    public int updateCommonResourceExtendData(Integer index, String string)
    {
        CommonResource commonResource =
            commonResourceMapper.selectCommonResourceByObjectType(new CommonResource(Short.valueOf(index.toString()),
                string));
        if (commonResource == null)
        {
            return commonResourceMapper.insertSelective(new CommonResource(Short.valueOf(index.toString()),
                string));
        }
        return commonResourceMapper.updateCommonResourceExtendData(new CommonResource(Short.valueOf(index.toString()),
            string));
    }

    @Override
    public CommonResource selectCommonResourceByTyp(int type)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.selectCommonResourceByObjectType(new CommonResource(CommonResource.OBJECT_TYPE_CHAT,
            ""));
    }

    @Override
    public CommonResource selectCommonResourceByObjectType(CommonResource commonResource)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.selectCommonResourceByObjectType(commonResource);
    }

    @Override
    public int updateByPrimaryKeySelective(CommonResource commonResource)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.updateCommonResourceExtendInt(commonResource);
    }

    @Override
    public int updateConsumerCoin(CommonResource commonResource)
    {
        commonResource.setObjectType(CommonResource.OBJECT_TYPE_CONSUMER_COIN);
        if (commonResourceMapper.selectCommonResourceByObjectType(commonResource) != null)
        {
            return commonResourceMapper.updateCommonResourceExtendInt(commonResource);
        }
        else
        {
            return commonResourceMapper.insertSelective(commonResource);
        }

    }

    @Override
    public List<Object> selectCommonResourceByObjectTypeForGoldSet(Integer s)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.selectCommonResourceByObjectTypeForGoldSet(s);
    }

    @Override
    public int deleteByPrimaryKey(Integer id)
    {
        // TODO Auto-generated method stub
        return commonResourceMapper.deleteByPrimaryKey(id);
    }

}
