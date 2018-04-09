package com.moyou.moyouRms.model.msg;

import com.moyou.moyouRms.constants.CONSTANT;
import com.moyou.moyouRms.util.PropertiesUtil;

/**
 * 评论推送
 * 
 * @author PzC.
 * @time 2016年12月1日 上午10:41:39
 * 
 */
public class MsgUserComment extends SuperMsg {
	private String targetId;
	private String commentContent;

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId == null ? null : targetId.trim();
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent == null ? null : commentContent.trim();
	}

	/**
	 * @param commentContent
	 *            评论内容
	 * @param type
	 *            推送类型区分 : {@link CONSTANT}
	 *            {"dynamic.comment","dynamic.recomment","circle.topic.recomment","circle.topic.comment"}
	 * @param value
	 *            话题的标题对应 -----------------------------> circle.topic.comment
	 *            话题某一条评论的内容对应 ------------------> circle.topic.recomment
	 *            动态的内容对应 ------------------------------> dynamic.comment
	 *            动态某一条评论的内容对应 -----------------------------> dynamic.recomment
	 * @param sendUserId
	 *            发送者 id
	 * @param nickname
	 *            发送者昵称
	 * @param targetId
	 *            目标 id , 既话题 id 或者首页动态 id , 用于跳转
	 * @return
	 */
	public static MsgUserComment instance(String commentContent, String type, String value, String sendUserId,
			String nickname, String targetId) {
		MsgUserComment msgUserComment = new MsgUserComment();
		msgUserComment.setCommentContent(commentContent);
		msgUserComment.setMsgContent(
				PropertiesUtil.bundle(type.concat(CONSTANT.MSG_CONTENT_KEY), (Object[]) new String[] { value }));
		msgUserComment.setMsgSendType(type);
		msgUserComment.setSendUserId(sendUserId);
		msgUserComment.setShortMsgContent(PropertiesUtil.bundle(type.concat(CONSTANT.SHORT_MSG_CONTENT_KEY),
				(Object[]) new String[] { nickname }));
		msgUserComment.setTargetId(targetId);
		return msgUserComment;
	}
}