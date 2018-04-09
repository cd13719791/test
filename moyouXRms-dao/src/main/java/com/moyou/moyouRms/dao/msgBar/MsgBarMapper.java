package com.moyou.moyouRms.dao.msgBar;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgBar.MsgBar;

public interface MsgBarMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MsgBar record);

	int insertSelective(MsgBar record);

	MsgBar selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MsgBar record);

	int updateByPrimaryKey(MsgBar record);

	List<MsgBar> queryMsgBarList(PageBean page);

	/**
	 * 添加通知栏推送
	 * 
	 * @param msgBar
	 * @return
	 */
	int insertMsgBarList(MsgBar msgBar);

	List<MsgBar> selectUnPushMsgBar();
}
