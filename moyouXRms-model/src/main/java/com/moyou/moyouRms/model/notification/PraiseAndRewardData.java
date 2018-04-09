package com.moyou.moyouRms.model.notification;

/**
 * 点赞和打赏数据
 * 
 * @author PzC.
 * @time 2017年3月22日 下午2:50:27
 * 
 */
public class PraiseAndRewardData implements DataModel {
	private Integer userId;// 用户 id
	private String nickname;// 昵称
	private String avatar;// 头像
	private Integer businessId;// 说说或者专辑故事 id
	private String businessTextContent;// 说说或专辑故事的文本内容
	private String pic;// 图片地址

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getBusinessTextContent() {
		return businessTextContent;
	}

	public void setBusinessTextContent(String businessTextContent) {
		this.businessTextContent = businessTextContent;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
