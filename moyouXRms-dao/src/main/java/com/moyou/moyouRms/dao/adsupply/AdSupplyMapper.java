package com.moyou.moyouRms.dao.adsupply;

import java.util.List;
import java.util.Map;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.adsupply.AdSupply;

public interface AdSupplyMapper
{
    int deleteByPrimaryKey(Integer id);

    int insert(AdSupply record);

    int insertSelective(AdSupply record);

    AdSupply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdSupply record);

    int updateByPrimaryKey(AdSupply record);

    List<AdSupply> selectAdSupplyList(PageBean pb);

    Map<String, Object> selectAdsupplyCount();

    AdSupply selectAdsupplyDetailByPrimaryKey(Integer id);

    Map<String, Object> selectAdsupplyAllCount();
}
