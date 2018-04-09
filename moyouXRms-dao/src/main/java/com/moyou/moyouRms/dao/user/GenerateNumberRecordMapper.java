package com.moyou.moyouRms.dao.user;

import com.moyou.moyouRms.model.user.GenerateNumberRecord;


public interface GenerateNumberRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(GenerateNumberRecord record);

    int insertSelective(GenerateNumberRecord record);

    GenerateNumberRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GenerateNumberRecord record);

    int updateByPrimaryKey(GenerateNumberRecord record);
    
    GenerateNumberRecord queryRangeOfMoyouId();
}