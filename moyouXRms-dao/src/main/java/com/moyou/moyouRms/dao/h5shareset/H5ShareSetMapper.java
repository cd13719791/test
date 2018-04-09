package com.moyou.moyouRms.dao.h5shareset;

import java.util.List;

import com.moyou.moyouRms.model.h5shareset.H5ShareSet;

public interface H5ShareSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(H5ShareSet record);

    int insertSelective(H5ShareSet record);

    H5ShareSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(H5ShareSet record);

    int updateByPrimaryKey(H5ShareSet record);
    
    List<H5ShareSet> selectH5ShareSetList();
}