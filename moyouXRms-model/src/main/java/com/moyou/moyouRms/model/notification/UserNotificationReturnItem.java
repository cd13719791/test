package com.moyou.moyouRms.model.notification;

@SuppressWarnings("rawtypes")
public class UserNotificationReturnItem {
	private Integer actionType;// 消息类型 1评论说说或专辑故事 2点赞 3打赏 4转发 5回复评论 （1和5都显示评论）
	private DataCondition dataCondition;// 数据

	public Integer getActionType() {
		return actionType;
	}

	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}

	public DataCondition getDataCondition() {
		return dataCondition;
	}

	public void setDataCondition(DataCondition dataCondition) {
		this.dataCondition = dataCondition;
	}
}
