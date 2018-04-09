package com.moyou.moyouRms.dao.report;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moyou.moyouRms.model.report.CommonResource;

public interface CommonResourceMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(CommonResource record);

    int insertSelective(CommonResource record);

    CommonResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommonResource record);

    int updateByPrimaryKey(CommonResource record);

    /**
     * 插入举报图片
     * 
     * @param list
     * @return
     */
    Integer insertReporResouce(List<CommonResource> list);

    /**
     * 根据数据Id 查询资源
     * 
     * @param id
     * @return
     */
    List<CommonResource> selectCommonResourceByObjectId(CommonResource commonResource);

    /**
     * 根据数据Id 查询第一张图片资源
     * 
     * @param id
     * @return
     */
    List<CommonResource> selectFirstCommonResourceByObjectId(CommonResource commonResource);

    /**
     * 查询文案
     * 
     * @param commonResource
     * @return
     */
    CommonResource selectCommonResourceByObjectType(CommonResource commonResource);

    /**
     * 修改文案
     * 
     * @param commonResource objectType extendData
     * @return
     */
    int updateCommonResourceExtendData(CommonResource commonResource);

    int updateCommonResourceExtendInt(CommonResource commonResource);

    List<Object> selectCommonResourceByObjectTypeForGoldSet(@Param("type") Integer type);

}
