package com.moyou.moyouRms.service.user;

import com.moyou.moyouRms.model.user.BindData;

public interface BindDataService {
	  int insert(BindData record);
	  BindData queryBindDateByUserId(int userId);
}
