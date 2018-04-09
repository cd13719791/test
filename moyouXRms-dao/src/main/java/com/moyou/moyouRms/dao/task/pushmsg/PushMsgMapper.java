package com.moyou.moyouRms.dao.task.pushmsg;

import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.jpush.pushmsg.PushMsg;

public interface PushMsgMapper {
	/**
	 * 添加一条运营推送
	 * 
	 * @param pushMsg
	 * @return
	 */
	int insertPushMsg(PushMsg pushMsg);

	/**
	 * 根据Id删除一条运营推送
	 * 
	 * @param pushMsg
	 * @return
	 */
	int deletePushMsg(PushMsg pushMsg);

	/**
	 * 初始化运营推送所有数据
	 * 
	 * @param pageBean
	 * @return
	 */
	List<PushMsg> queryPushMsgList(PageBean pageBean);

	/**
	 * 根据Id查询状态
	 * 
	 * @param id
	 * @return
	 */
	int queryPushMsgState(Integer id);

	/**
	 * 根据id修改推送状态
	 * 
	 * @param pushMsg
	 * @return
	 */
	int updatePushMsgType(Integer id);
}
