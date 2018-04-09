package com.moyou.moyouRms.model.talk;

import com.moyou.moyouRms.model.BaseModel;

public class TalkInfoList extends BaseModel{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer newTalkCount;
private Integer countTalk;
private Integer countVideoTalk;
private Integer countPicTalk;
public Integer getNewTalkCount() {
	return newTalkCount;
}
public void setNewTalkCount(Integer newTalkCount) {
	this.newTalkCount = newTalkCount;
}
public Integer getCountTalk() {
	return countTalk;
}
public void setCountTalk(Integer countTalk) {
	this.countTalk = countTalk;
}
public Integer getCountVideoTalk() {
	return countVideoTalk;
}
public void setCountVideoTalk(Integer countVideoTalk) {
	this.countVideoTalk = countVideoTalk;
}
public Integer getCountPicTalk() {
	return countPicTalk;
}
public void setCountPicTalk(Integer countPicTalk) {
	this.countPicTalk = countPicTalk;
}
}
