package com.moyou.moyouRms.service.msgsystem;

import java.util.List;

import com.moyou.moyouRms.model.msgBar.MsgBar;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.model.user.UserInfo;

public interface MsgSystemXService {

	/**
	 * 添加一条系统消息并推送自定义消息
	 * 
	 * @param record
	 * @return
	 */
	int addSysMsgAndPushCustomMsg(MsgSystemX record);

	/**
	 * 添加一条系统消息并推送自定义消息
	 * 
	 * @param record
	 * @param push_content
	 *            推送显示内容
	 * @return
	 */
	int addSysMsgAndPushCustomMsg(MsgSystemX record, String push_content);

	int insertWarn(MsgSystemX record);

	/**
	 * 推送一条系统广播
	 * 
	 * @param record
	 * @return
	 */
	boolean addSysMsg(List<UserInfo> tmpUserList, MsgSystemX record,
			String push_content);

	/**
	 * 通知栏推送给所有用户
	 * 
	 * @param push_content
	 * @return
	 */
	boolean addSysMsgAndPushCustomMsgToAllUser(MsgBar msgBar);

	/**
	 * 根据类型 和id查询系统消息
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	List<MsgSystemX> queryMsgSystemByModeTypeAndModeId(Integer type, Integer id);
}
