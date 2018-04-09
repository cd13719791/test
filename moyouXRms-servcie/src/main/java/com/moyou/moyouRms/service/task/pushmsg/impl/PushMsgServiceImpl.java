package com.moyou.moyouRms.service.task.pushmsg.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.constants.RESPONSE;
import com.moyou.moyouRms.dao.msgarticle.MsgArticleMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.manager.pushmsg.PushMsgManager;
import com.moyou.moyouRms.model.msgarticle.MsgArticle;
import com.moyou.moyouRms.model.msgsystem.MsgSystemX;
import com.moyou.moyouRms.response.ApiResult;
import com.moyou.moyouRms.service.jpush.MessagePushService;
import com.moyou.moyouRms.service.msgsystem.MsgSystemXService;
import com.moyou.moyouRms.service.task.pushmsg.PushMsgService;
import com.moyou.moyouRms.util.PropertiesUtil;

@Service
public class PushMsgServiceImpl implements PushMsgService {
	@Resource
	private MessagePushService messagePushService;
	@Resource
	private MsgArticleMapper msgArticleMapper;
	@Resource
	private MsgSystemXService msgSystemXService;
	@Resource
	private PushMsgManager pushMsgManager;

	/**
	 * 添加一条运营推送
	 * 
	 * @param pushMsg
	 * @return
	 * @throws ParseException
	 */
	@Override
	public ApiResult insertPushMsg(MsgArticle msgArticle) throws ParseException {
		msgArticle.setState(MsgArticle.STATE_DEFAULT);
		msgArticle.setPublishState(MsgArticle.PUBLISH_WAIT);
		msgArticle.setCreateTime(new Date());
		if (msgArticle.getPublishTime() == null) {
			msgArticle.setPublishTime(new Date());
		}
		msgArticleMapper.insert(msgArticle);
		String h5Url = PropertiesUtil.getValueByKey(
				CONSTANT.SYSTEM_H5_ARTICLE_PATH, CONSTANT.SYS_CONF_PATH)
				+ msgArticle.getId();
		MsgSystemX msgSystemX = new MsgSystemX();
		msgSystemX.setMsgSendType(CONSTANT.SYS_ART_MSG);
		msgSystemX.setMsgTitle(msgArticle.getArticleTitle());
		msgSystemX.setMsgContent(msgArticle.getCover());
		msgSystemX.setShortMsgContent(msgArticle.getArticleAbstract());
		msgSystemX.setReceiveUserId(0);
		msgSystemX.setH5Url(h5Url);
		msgSystemX.setSendUserId(0);
		msgArticle.setH5Url(h5Url);
		msgArticle.setPublishState(MsgArticle.PUBLISH_SUCCESS);
		if (msgArticle.getPublishTime() == null) {
			// 推送一条自定义消息给用户
			msgArticleMapper.updateByPrimaryKeySelective(msgArticle);
			/**
			 * 无差别真实用户推送！！！！威力大 慎用
			 */
			pushMsgManager
					.sendPushMsg(msgSystemX, msgArticle.getArticleTitle());
		} else
		// 如果有发布时间 就按照发布时间执行
		{
			msgArticle.setPublishState(MsgArticle.PUBLISH_ING);
			msgArticleMapper.updateByPrimaryKeySelective(msgArticle);
			Timer time = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					try {
						pushMsgManager.sendPushMsg(msgSystemX,
								msgArticle.getArticleTitle());
						msgArticle.setPublishState(MsgArticle.PUBLISH_SUCCESS);
						msgArticle.setPublishTime(new Date());
						msgArticleMapper
								.updateByPrimaryKeySelective(msgArticle);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						time.cancel();
					}
				}
			};
			time.schedule(task, msgArticle.getPublishTime());
		}
		return new ApiResult(RESPONSE.SUCCESS, "成功");
	}

	/**
	 * 根据Id删除一条运营推送
	 * 
	 * @param pushMsg
	 * @return
	 */
	@Override
	public int deletePushMsg(MsgArticle pushMsg) {
		pushMsg.setState(MsgArticle.STATE_DELETE);
		return msgArticleMapper.updateByPrimaryKeySelective(pushMsg);
	}

	/**
	 * 初始化运营推送所有数据
	 * 
	 * @param pageBean
	 * @return
	 */
	@Override
	public List<MsgArticle> queryPushMsgList(PageBean page) {
		return msgArticleMapper.queryPushMsgList(page);
	}

	@Override
	public int updatePushMsg(MsgArticle pushMsg) {
		if (pushMsg.getId() == null
				|| msgArticleMapper.selectByPrimaryKey(pushMsg.getId()) == null) {
			return RESPONSE.ERROR;
		}
		// TODO Auto-generated method stub
		Short pushState = msgArticleMapper.selectByPrimaryKey(pushMsg.getId())
				.getPublishState();
		if (pushState.compareTo(MsgArticle.PUBLISH_ING) != 0) {
			return msgArticleMapper.updateByPrimaryKeySelective(pushMsg);
		}
		return RESPONSE.ERROR;
	}

	@Override
	public MsgArticle selectById(Integer valueOf) {
		// TODO Auto-generated method stub
		return msgArticleMapper.selectByPrimaryKey(valueOf);
	}

}
