package com.moyou.moyouRms.service.userdynamic;

import com.moyou.moyouRms.model.userdynamic.UserDynamic;

public interface UserDynamicService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserDynamic record);

	int insertSelective(UserDynamic record);

	UserDynamic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserDynamic record);

	int updateByPrimaryKey(UserDynamic record);

	int deleteByDataIdAnDType(UserDynamic record);
}