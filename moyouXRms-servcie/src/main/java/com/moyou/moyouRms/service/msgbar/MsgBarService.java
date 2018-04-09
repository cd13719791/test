package com.moyou.moyouRms.service.msgbar;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgBar.MsgBar;
import com.moyou.moyouRms.response.ApiResult;

public interface MsgBarService {
	int deleteByPrimaryKey(Integer id);

	int insert(MsgBar record);

	int insertSelective(MsgBar record);

	MsgBar selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MsgBar record);

	int updateByPrimaryKey(MsgBar record);

	List<MsgBar> queryMsgBarList(PageBean page);

	/**
	 * 发布通知栏推送
	 * 
	 * @param msgBar
	 * @return
	 */
	ApiResult insertMsgBarList(MsgBar msgBar);

	void replayMsgBarList(MsgBar msgBar);

	List<MsgBar> selectUnPushMsgBar();
}
