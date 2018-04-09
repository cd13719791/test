package com.moyou.moyouRms.service.report;

import java.util.List;

import com.moyou.moyouRms.model.report.CommonResource;

public interface CommonResourceService
{
    int deleteByPrimaryKey(Integer id);

    /**
     * 根据数据Id 查询资源
     * 
     * @param id
     * @return
     */
    List<CommonResource> selectCommonResourceByObjectId(CommonResource commonResource);

    /**
     * 根据数据Id 查询第一个图片资源
     * 
     * @param id
     * @return
     */
    List<CommonResource> selectFirstCommonResourceByObjectId(CommonResource commonResource);

    /**
     * 插入一条公共资源图片
     * 
     * @param commonResource
     * @return
     */
    int insertcommonResource(CommonResource commonResource);

    /**
     * 查询当前文案
     * 
     * @return
     */
    CommonResource selectCommonResource();

    /**
     * 查询当前文案
     * 
     * @return
     */
    CommonResource selectCommonResourceByTyp(int type);

    int updateCommonResourceExtendData(Integer index, String string);

    CommonResource selectCommonResourceByObjectType(CommonResource commonResource);

    int updateByPrimaryKeySelective(CommonResource commonResource);

    int updateConsumerCoin(CommonResource commonResource);

    List<Object> selectCommonResourceByObjectTypeForGoldSet(Integer s);
}
