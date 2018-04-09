package com.moyou.moyouRms.service.goldSignConfigure;

import java.util.List;

import com.moyou.moyouRms.model.goldSignConfigure.GoldSignConfigure;

public interface GoldSignConfigureService
{
    int deleteByPrimaryKey(Integer id);

    int insert(GoldSignConfigure record);

    int insertSelective(GoldSignConfigure record);

    GoldSignConfigure selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoldSignConfigure record);

    int updateByPrimaryKey(GoldSignConfigure record);

    /**
     * 为了接口兼容性 用了object
     * 
     * @return
     */
    List<Object> selectAllForGoldSet();
}
