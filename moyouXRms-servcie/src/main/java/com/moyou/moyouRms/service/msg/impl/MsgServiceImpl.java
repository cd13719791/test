package com.moyou.moyouRms.service.msg.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.dao.msg.MsgSystemMapper;
import com.moyou.moyouRms.dao.msg.MsgUserCommentMapper;
import com.moyou.moyouRms.dao.msg.MsgUserConcernMapper;
import com.moyou.moyouRms.dao.msg.MsgUserContactMapper;
import com.moyou.moyouRms.dao.msg.UserContactMapper;
import com.moyou.moyouRms.interceptor.PageBean;
import com.moyou.moyouRms.model.jpush.PushEntity;
import com.moyou.moyouRms.model.msg.MsgIndexReturn;
import com.moyou.moyouRms.model.msg.MsgItemReturn;
import com.moyou.moyouRms.model.msg.MsgSystem;
import com.moyou.moyouRms.model.msg.MsgUserComment;
import com.moyou.moyouRms.model.msg.MsgUserConcern;
import com.moyou.moyouRms.model.msg.MsgUserContact;
import com.moyou.moyouRms.model.msg.PhoneContactInfo;
import com.moyou.moyouRms.model.msg.SuperMsg;
import com.moyou.moyouRms.model.msg.UserContact;
import com.moyou.moyouRms.model.msg.UserContactInfo;
import com.moyou.moyouRms.model.msg.UserContactReturn;
import com.moyou.moyouRms.model.msg.UserContactSqlResult;
import com.moyou.moyouRms.service.msg.MsgService;
import com.moyou.moyouRms.util.DateTimeUtil;
import com.moyou.moyouRms.util.DistanceUtil;
import com.moyou.moyouRms.util.EntityUtil;
import com.moyou.moyouRms.util.JsonUtil;
import com.moyou.moyouRms.util.RelativeDateFormat;
import com.moyou.moyouRms.util.StreamUtils;

@Service
public class MsgServiceImpl implements MsgService {
	@Resource
	private MsgUserConcernMapper msgUserConcernMapper;
	@Resource
	private MsgUserCommentMapper msgUserCommentMapper;
	@Resource
	private MsgSystemMapper msgSystemMapper;
	@Resource
	private MsgUserContactMapper msgUserContactMapper;
	@Resource
	private UserContactMapper userContactMapper;

	/**
	 * 判断是否需要覆盖推送并返回极光推送回调 id
	 * 
	 * @param pushEntity
	 * @return 数据库查询的极光推送回调 id , 如果未查询到则返回 ""
	 */
	@Override
	public boolean checkWetherNeedOverride(PushEntity pushEntity) {
		return CollectionUtils.contains(CONSTANT.NEED_OVERRIDE_PUSH.iterator(),
				pushEntity.getMsgEntity().getMsgSendType());
	}

	/**
	 * 查询极光推送回调 id
	 * 
	 * @param pushEntity
	 * @return 数据库查询的极光推送回调 id , 如果未查询到则返回 ""
	 */
	@Override
	public String queryJpushMsgId(PushEntity pushEntity) {
		if (checkWetherNeedOverride(pushEntity)) {
			List<Map<String, Object>> jpushMsgIdList = getJpushMsgIdList(pushEntity);
			if (!CollectionUtils.isEmpty(jpushMsgIdList)) {
				Map<String, Object> firstElement = StreamUtils.findFirst(jpushMsgIdList);
				if (DateTimeUtil.calculateDiffFromNow((Date) firstElement.get("updateTime")).getDays() < 1) {
					return firstElement.get("jpushMsgId").toString();
				}
			}
		}
		return "";
	}

	/**
	 * 插入关注推送消息
	 * 
	 * @param pushEntity
	 * @param jpushMsgId
	 * @return
	 */
	@Override
	public int insertMsgUserConcernList(MsgUserConcern msgEntity, List<String> aliases) {
		List<MsgUserConcern> list = new ArrayList<>();
		aliases.forEach(receiveUserId -> {
			MsgUserConcern msgUserConcern = new MsgUserConcern();
			BeanUtils.copyProperties(msgEntity, msgUserConcern, "id");
			msgUserConcern.setReceiveUserId(receiveUserId);
			list.add(msgUserConcern);
		});
		return msgUserConcernMapper.insertList(list);
	}

	/**
	 * 插入评论推送消息
	 * 
	 * @param msgEntity
	 * @param jpushMsgId
	 * @param aliases
	 * @return
	 */
	@Override
	public int insertMsgUserCommentList(MsgUserComment msgEntity, List<String> aliases) {
		List<MsgUserComment> list = new ArrayList<>();
		aliases.forEach(receiveUserId -> {
			MsgUserComment msgUserComment = new MsgUserComment();
			BeanUtils.copyProperties(msgEntity, msgUserComment, "id");
			msgUserComment.setReceiveUserId(receiveUserId);
			list.add(msgUserComment);
		});
		return msgUserCommentMapper.insertList(list);
	}

	/**
	 * 插入添加联系人好友消息
	 * 
	 * @param msgEntity
	 * @param aliases
	 * @return
	 */
	@Override
	public int insertMsgUserContactList(MsgUserContact msgEntity, List<String> aliases) {
		List<MsgUserContact> list = new ArrayList<>();
		aliases.forEach(receiveUserId -> {
			MsgUserContact msgUserContact = new MsgUserContact();
			BeanUtils.copyProperties(msgEntity, msgUserContact, "id");
			msgUserContact.setReceiveUserId(receiveUserId);
			list.add(msgUserContact);
		});
		return msgUserContactMapper.insertList(list);
	}

	/**
	 * 更新关注覆盖推送
	 * 
	 * @param pushEntity
	 * @param jpushMsgId
	 * @return
	 */
	@Override
	public int updateMsgConcernByJpushMsgIdAndSendUserId(MsgUserConcern msgUserConcern) {
		return msgUserConcernMapper.updateByJpushMsgId(msgUserConcern);
	}

	/**
	 * 更新联系人好友覆盖推送
	 * 
	 * @param pushEntity
	 * @param jpushMsgId
	 * @return
	 */
	@Override
	public int updateMsgContactByJpushMsgIdAndSendUserId(MsgUserContact msgUserContact) {
		return msgUserContactMapper.updateByJpushMsgId(msgUserContact);
	}

	/**
	 * 插入系统消息
	 * 
	 * @param msgSystem
	 * @return
	 */
	@Override
	public int insertMsgSystem(MsgSystem msgSystem) {
		return msgSystemMapper.insert(msgSystem);
	}

	/**
	 * 更新消息推送操作
	 * 
	 * @param pushEntity
	 * @return
	 */
	@Override
	public int updateMsgOperation(PushEntity pushEntity) {
		int result = 0;
		SuperMsg superMsg = pushEntity.getMsgEntity();
		if (superMsg instanceof MsgUserConcern) {
			MsgUserConcern msgUserConcern = (MsgUserConcern) superMsg;
			result = updateMsgConcernByJpushMsgIdAndSendUserId(msgUserConcern);
		}
		if (superMsg instanceof MsgUserContact) {
			MsgUserContact msgUserContact = (MsgUserContact) superMsg;
			result = updateMsgContactByJpushMsgIdAndSendUserId(msgUserContact);
		}
		return result;
	}

	/**
	 * 插入推送消息操作
	 * 
	 * @param pushEntity
	 * @param jpushMsgId
	 * @return
	 */
	@Override
	public int insertMsgOperation(PushEntity pushEntity) {
		int result = 0;
		List<String> aliases = pushEntity.getAliases();
		SuperMsg superMsg = pushEntity.getMsgEntity();
		if (superMsg instanceof MsgUserConcern) {
			result = insertMsgUserConcernList((MsgUserConcern) superMsg, aliases);
		}
		if (superMsg instanceof MsgUserComment) {
			result = insertMsgUserCommentList((MsgUserComment) superMsg, aliases);
		}
		if (superMsg instanceof MsgUserContact) {
			result = insertMsgUserContactList((MsgUserContact) superMsg, aliases);
		}
		if (superMsg instanceof MsgSystem) {
			result = insertMsgSystem((MsgSystem) superMsg);
		}
		return result;
	}
	
	/**
	 * 确认添加消息联系人好友
	 * 
	 * @param userContact
	 * @return
	 */
	@Override
	public int addUserContact(UserContact userContact) {
		return userContactMapper.insert(userContact);
	}

	/**
	 * 消息联系人列表
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<UserContactReturn> queryUserContactList(Map<String, Object> params) {
		PageBean pageBean = EntityUtil.instanceOfPageBean(params);
		List<UserContactSqlResult> sqlResultList = userContactMapper.queryUserContactList(pageBean);
		List<UserContactReturn> returnList = new ArrayList<>();
		if (!CollectionUtils.isEmpty(sqlResultList)) {
			sqlResultList.forEach(sqlResult -> {
				UserContactReturn userContactReturn = JsonUtil.toObject(sqlResult, UserContactReturn.class);
				userContactReturn.setAge(DateTimeUtil.getPersonAgeByBirthDate(sqlResult.getBirthday()));
				userContactReturn.setDistance(RelativeDateFormat.format(sqlResult.getCreateTime()));
				userContactReturn.setDistance(String.valueOf(DistanceUtil.getDistance((double) params.get("longitude"),
						(double) params.get("latitude"), sqlResult.getLongitude(), sqlResult.getLatitude())));
				returnList.add(userContactReturn);
			});
		}
		return returnList;
	}

	/**
	 * 消息首页
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public MsgIndexReturn queryMsgIndex(String userId) {
		MsgIndexReturn indexReturn = new MsgIndexReturn();
		MsgItemReturn concernMsg = msgUserConcernMapper.queryConcernMsgItem(userId),
				commentMsg = msgUserCommentMapper.queryCommentMsgItem(userId),
				systemMsg = msgSystemMapper.querySystemMsgItem(userId);
		if (!ObjectUtils.isEmpty(commentMsg)) {
			commentMsg.setFromNow(RelativeDateFormat.format(commentMsg.getUpdateTime()));
		} else {
			commentMsg = new MsgItemReturn();
		}
		if (!ObjectUtils.isEmpty(concernMsg)) {
			concernMsg.setFromNow(RelativeDateFormat.format(concernMsg.getUpdateTime()));
		} else {
			concernMsg = new MsgItemReturn();
		}
		if (!ObjectUtils.isEmpty(systemMsg)) {
			systemMsg.setFromNow(RelativeDateFormat.format(systemMsg.getUpdateTime()));
		} else {
			systemMsg = new MsgItemReturn();
		}
		indexReturn.setCommentMsg(commentMsg);
		indexReturn.setConcernMsg(concernMsg);
		indexReturn.setSystemMsg(systemMsg);
		return indexReturn;
	}

	/**
	 * 关注消息列表
	 * 
	 * @param pageBean
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryConcernMsgList(PageBean pageBean) {
		List<Map<String, Object>> resultList = msgUserConcernMapper.queryConcernMsgList(pageBean);
		if (!CollectionUtils.isEmpty(resultList)) {
			resultList.forEach(resultMap -> {
				resultMap.put("age", DateTimeUtil.getPersonAgeByBirthDate((Date) resultMap.get("birthday")));
				resultMap.put("fromNow", RelativeDateFormat.format((Date) resultMap.get("updateTime")));
			});
		} else {
			resultList = Lists.newArrayList();
		}
		return resultList;
	}

	/**
	 * 评论消息列表
	 * 
	 * @param pageBean
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryCommentMsgList(PageBean pageBean) {
		List<Map<String, Object>> resultList = msgUserCommentMapper.queryCommentMsgList(pageBean);
		if (!CollectionUtils.isEmpty(resultList)) {
			resultList.forEach(resultMap -> {
				resultMap.put("age", DateTimeUtil.getPersonAgeByBirthDate((Date) resultMap.get("birthday")));
				resultMap.put("fromNow", RelativeDateFormat.format((Date) resultMap.get("updateTime")));
			});
		} else {
			resultList = Lists.newArrayList();
		}
		return resultList;
	}

	/**
	 * 系统消息列表
	 * 
	 * @param pageBean
	 * @return
	 */
	@Override
	public List<Map<String, Object>> querySystemMsgList(PageBean pageBean) {
		List<Map<String, Object>> resultList = msgSystemMapper.querySystemMsgList(pageBean);
		if (!CollectionUtils.isEmpty(resultList)) {
			resultList.forEach(resultMap -> {
				resultMap.put("fromNow", RelativeDateFormat.format((Date) resultMap.get("updateTime")));
			});
		} else {
			resultList = Lists.newArrayList();
		}
		return resultList;
	}

	/**
	 * 查询聊天相关信息
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryUserChatInfo(UserContactInfo params) {
//		System.out.println(JsonUtil.toJson(params.getQueryUserIds()));
		List<Map<String, Object>> resultList = userContactMapper.queryUserChatInfo(params.getUserId(),
				params.getQueryUserIds());
		resultList.forEach(map -> {
			map.put("wetherUserContact", !StringUtils.isEmpty(map.get("relationId")));
		});
		return resultList;
	}

	/**
	 * 删除消息联系人好友
	 * 
	 * @param relationId
	 * @return
	 */
	@Override
	public int deleteUserContact(String relationId) {
		return userContactMapper.deleteByPrimaryKey(relationId);
	}

	/**
	 * 查询手机联系人
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public Map<String, Object> queryPhoneContact(PhoneContactInfo params) {
		Map<String, Object> returnMap = Maps.newHashMap();
		List<Map<String, Object>> resultList = userContactMapper.queryPhoneContact(params.getUserId(),
				params.getPhoneList());
		int canAdd = 0;
		if (!CollectionUtils.isEmpty(resultList)) {
			for (Map<String, Object> map : resultList) {
				boolean wetherUserContact = !StringUtils.isEmpty(map.get("relationId"));
				if (!wetherUserContact) {
					canAdd++;
				}
				map.put("wetherUserContact", wetherUserContact);
			}
		} else {
			resultList = Lists.newArrayList();
		}
		returnMap.put("canAdd", canAdd);
		returnMap.put("phoneContactList", resultList);
		return returnMap;
	}

	/*
	 * |-----------------------------------------------------------------------|
	 * |------------------------------- inside -------------------------------|
	 * |-----------------------------------------------------------------------|
	 */

	/**
	 * getJpushMsgIdList
	 * 
	 * @param pushEntity
	 * @return
	 */
	private List<Map<String, Object>> getJpushMsgIdList(PushEntity pushEntity) {
		SuperMsg superMsg = pushEntity.getMsgEntity();
		List<Map<String, Object>> jpushMsgIdList = new ArrayList<>();
		if (superMsg instanceof MsgUserConcern) {
			jpushMsgIdList = msgUserConcernMapper.queryMsgIdAndUpdateTime((MsgUserConcern) superMsg,
					pushEntity.getAliases());
		}
		if (superMsg instanceof MsgUserContact) {
			jpushMsgIdList = msgUserContactMapper.queryMsgIdAndUpdateTime((MsgUserContact) superMsg,
					pushEntity.getAliases());
		}
		return jpushMsgIdList;
	}
}
