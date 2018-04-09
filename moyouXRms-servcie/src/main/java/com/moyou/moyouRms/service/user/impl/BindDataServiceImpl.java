package com.moyou.moyouRms.service.user.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.moyou.moyouRms.dao.user.BindDataMapper;
import com.moyou.moyouRms.model.user.BindData;
import com.moyou.moyouRms.service.user.BindDataService;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.StringUtil;

@Service
public class BindDataServiceImpl implements BindDataService {
	@Resource
	private BindDataMapper bindDataMapper;
	Logger logger=Logger.getLogger(this.getClass());

	/**
	 * 绑定微信opendId
	 */
	@Override
	public int insert(BindData record) {
		String bindData = bindDataMapper.queryBindData(record.getUserId());
		logger.info(JsonUtil.toJson(record));
		logger.info(JsonUtil.toJson(bindData));
		if (StringUtil.isNotEmpty(bindData)) {
			return 0;
		}
		return bindDataMapper.insert(record);
		
	}

	@Override
	public BindData queryBindDateByUserId(int userId) {
		// TODO Auto-generated method stub
		return bindDataMapper.queryBindDataInfo(userId);
	}

}
