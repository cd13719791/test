package com.moyou.moyouRms.service.task.pushmsg;

import java.text.ParseException;
import java.util.List;

import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.msgarticle.MsgArticle;
import com.moyou.moyouRms.response.ApiResult;

public interface PushMsgService {
	/**
	 * 添加一条运营推送
	 * 
	 * @param pushMsg
	 * @return
	 * @throws ParseException
	 */
	ApiResult insertPushMsg(MsgArticle pushMsg) throws ParseException;

	/**
	 * 根据Id删除一条运营推送
	 * 
	 * @param pushMsg
	 * @return
	 */
	int deletePushMsg(MsgArticle pushMsg);
	/**
	 * 修改推送文章
	 * 
	 * @param pushMsg
	 * @return
	 */
	int updatePushMsg(MsgArticle pushMsg);
	/**
	 * 初始化运营推送所有数据
	 * 
	 * @param pageBean
	 * @return
	 */
	List<MsgArticle> queryPushMsgList(PageBean page);

	MsgArticle selectById(Integer valueOf);

}
