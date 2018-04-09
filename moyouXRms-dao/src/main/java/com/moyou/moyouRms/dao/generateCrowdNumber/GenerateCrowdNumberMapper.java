package com.moyou.moyouRms.dao.generateCrowdNumber;

import com.moyou.moyouRms.model.generateCrowdNumber.GenerateCrowdNumber;


public interface GenerateCrowdNumberMapper {
    int deleteByPrimaryKey(String id);

    int insert(GenerateCrowdNumber record);

    int insertSelective(GenerateCrowdNumber record);

    GenerateCrowdNumber selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GenerateCrowdNumber record);

    int updateByPrimaryKey(GenerateCrowdNumber record);
    
    GenerateCrowdNumber queryRangeOfCrowdId();
}