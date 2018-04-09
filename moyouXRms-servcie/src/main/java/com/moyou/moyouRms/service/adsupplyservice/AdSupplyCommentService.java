package com.moyou.moyouRms.service.adsupplyservice;

import com.moyou.moyouRms.model.adsupply.AdSupplyComment;

public interface AdSupplyCommentService {
    int deleteByPrimaryKey(Integer id);

    int insert(AdSupplyComment record);

    int insertSelective(AdSupplyComment record);

    AdSupplyComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AdSupplyComment record);

    int updateByPrimaryKey(AdSupplyComment record);
}