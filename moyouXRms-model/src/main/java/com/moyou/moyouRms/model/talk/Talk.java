package com.moyou.moyouRms.model.talk;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;
import com.moyou.moyouRms.model.user.UserInfo;

public class Talk extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final Integer STATE_XIANZHI = 1;
	private Integer id;
	public static final Integer STATE_ZHENGCHANG = 0;
	private String content;// 内容
	private String address;// 地址
	private Double longitude;// 经度
	private Double latitude;// 纬度
	private Integer creatorId;// 创建者的id
	private Integer commentTotal;// 评论统计
	private Integer supportTotal;// 点赞统计
	private Integer rewardTotal;// 打赏统计
	private Integer readTotal;// 阅读量统计
	private Integer userId;// 发表说说的用户ID
	private int relateTalkId;// 转发说说的id
	private int relateState;// 转发数据状态 转发说说的状态 0正常 1删除
	private String pushChatId;
	private int type;
	private Integer mediaTypes;//
	private Integer referenceType;// 1故事'
	public static final Integer REFERENCE_TYPE_DIARY = 1;
	public static final Integer REFERENCE_TYPE_DEFAULT = 0;
	private Integer referenceId;// '引用数据id'

	/**
	 * 删除首页推荐数据
	 * 
	 * @param referenceType
	 * @param referenceId
	 */
	public Talk(Integer referenceType, Integer referenceId, int state) {
		super();
		this.referenceType = referenceType;
		this.referenceId = referenceId;
		this.state = state;
	}

	public Talk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Talk(Integer id, String content, String address, Double longitude, Double latitude,
			Integer creatorId, Integer commentTotal, Integer supportTotal, Integer rewardTotal,
			Integer readTotal, Integer userId, int relateTalkId, int relateState,
			String pushChatId, int type, Integer mediaTypes, Integer referenceType,
			Integer referenceId, List<String> userIdList, List<String> commentText,
			List<TalkPraise> talkPraiseList, List<TalkComment> talkCommentList, UserInfo userInfo,
			Date createTime, Date updateTime, Integer state, String city, String sig, String age,
			Date birthday, Integer gender, String url, short mediaType, String moyouId,
			List<TalkResource> talkResource, Integer picCount, String countCommentTotal,
			String countsupportTotal, String countRewardTotal, String countReadTotal,
			Integer talkId, String nickname, String avatar, List<ComentTwo> comentList) {
		super();
		this.id = id;
		this.content = content;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.creatorId = creatorId;
		this.commentTotal = commentTotal;
		this.supportTotal = supportTotal;
		this.rewardTotal = rewardTotal;
		this.readTotal = readTotal;
		this.userId = userId;
		this.relateTalkId = relateTalkId;
		this.relateState = relateState;
		this.pushChatId = pushChatId;
		this.type = type;
		this.mediaTypes = mediaTypes;
		this.referenceType = referenceType;
		this.referenceId = referenceId;
		this.userIdList = userIdList;
		this.commentText = commentText;
		this.talkPraiseList = talkPraiseList;
		this.talkCommentList = talkCommentList;
		this.userInfo = userInfo;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.state = state;
		this.city = city;
		this.sig = sig;
		this.age = age;
		this.birthday = birthday;
		this.gender = gender;
		this.url = url;
		this.mediaType = mediaType;
		this.moyouId = moyouId;
		this.talkResource = talkResource;
		this.picCount = picCount;
		this.countCommentTotal = countCommentTotal;
		this.countsupportTotal = countsupportTotal;
		this.countRewardTotal = countRewardTotal;
		this.countReadTotal = countReadTotal;
		this.talkId = talkId;
		this.nickname = nickname;
		this.avatar = avatar;
		this.comentList = comentList;
	}

	public Integer getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(Integer referenceType) {
		this.referenceType = referenceType;
	}

	public Integer getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(Integer referenceId) {
		this.referenceId = referenceId;
	}

	public Integer getMediaTypes() {
		return mediaTypes;
	}

	public void setMediaTypes(Integer mediaTypes) {
		this.mediaTypes = mediaTypes;
	}

	private List<String> userIdList;// 评论用户Id集合.
	private List<String> commentText;// 评论内容集合

	public List<String> getCommentText() {
		return commentText;
	}

	public void setCommentText(List<String> commentText) {
		this.commentText = commentText;
	}

	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}

	public String getPushChatId() {
		return pushChatId;
	}

	public void setPushChatId(String pushChatId) {
		this.pushChatId = pushChatId;
	}

	private List<TalkPraise> talkPraiseList;// 点赞集合
	List<TalkComment> talkCommentList;// 评论集合
	private UserInfo userInfo;// 用户信息

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public List<TalkComment> getTalkCommentList() {
		return talkCommentList;
	}

	public void setTalkCommentList(List<TalkComment> talkCommentList) {
		this.talkCommentList = talkCommentList;
	}

	public List<TalkPraise> getTalkPraiseList() {
		return talkPraiseList;
	}

	public void setTalkPraiseList(List<TalkPraise> talkPraiseList) {
		this.talkPraiseList = talkPraiseList;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReadTotal() {
		return readTotal;
	}

	public void setReadTotal(Integer readTotal) {
		this.readTotal = readTotal;
	}

	private Date createTime;
	private Date updateTime;
	private Integer state;
	private String city;
	private String sig;// 个性签名
	private String age;// 年龄
	// 出生日期
	private Date birthday;
	private Integer gender;// 性别
	private String url;
	private short mediaType;// 资源类型 资源文件格式：\n1.音频\n2.视频\n3.图片

	private String moyouId;

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	public short getMediaType() {
		return mediaType;
	}

	public void setMediaType(short mediaType) {
		this.mediaType = mediaType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	private List<TalkResource> talkResource;// 图片集合

	private Integer picCount;// 图片数量

	public Integer getPicCount() {
		return picCount;
	}

	public void setPicCount(Integer picCount) {
		this.picCount = picCount;
	}

	public List<ComentTwo> getComentList() {
		return comentList;
	}

	public void setComentList(List<ComentTwo> comentList) {
		this.comentList = comentList;
	}

	private String countCommentTotal;// 格式化评论量
	private String countsupportTotal;// 格式化点赞量
	private String countRewardTotal;// 格式化打赏
	private String countReadTotal;// 格式化阅读量

	public String getCountReadTotal() {
		return countReadTotal;
	}

	public void setCountReadTotal(String countReadTotal) {
		this.countReadTotal = countReadTotal;
	}

	public String getCountRewardTotal() {
		return countRewardTotal;
	}

	public void setCountRewardTotal(String countRewardTotal) {
		this.countRewardTotal = countRewardTotal;
	}

	private Integer talkId;// 主键ID
	private String nickname;
	private String avatar;
	private List<ComentTwo> comentList;// 评论集合

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getTalkId() {
		return talkId;
	}

	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}

	public String getCountCommentTotal() {
		return countCommentTotal;
	}

	public void setCountCommentTotal(String countCommentTotal) {
		this.countCommentTotal = countCommentTotal;
	}

	public String getCountsupportTotal() {
		return countsupportTotal;
	}

	public void setCountsupportTotal(String countsupportTotal) {
		this.countsupportTotal = countsupportTotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getCommentTotal() {
		return commentTotal;
	}

	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}

	public Integer getSupportTotal() {
		return supportTotal;
	}

	public void setSupportTotal(Integer supportTotal) {
		this.supportTotal = supportTotal;
	}

	public Integer getRewardTotal() {
		return rewardTotal;
	}

	public void setRewardTotal(Integer rewardTotal) {
		this.rewardTotal = rewardTotal;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<TalkResource> getTalkResource() {
		return talkResource;
	}

	public void setTalkResource(List<TalkResource> talkResource) {
		this.talkResource = talkResource;
	}

	/**
	 * @return the relateTalkId
	 */
	public int getRelateTalkId() {
		return relateTalkId;
	}

	/**
	 * @param relateTalkId
	 *            the relateTalkId to set
	 */
	public void setRelateTalkId(int relateTalkId) {
		this.relateTalkId = relateTalkId;
	}

	/**
	 * @return the relateState
	 */
	public int getRelateState() {
		return relateState;
	}

	/**
	 * @param relateState
	 *            the relateState to set
	 */
	public void setRelateState(int relateState) {
		this.relateState = relateState;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
