package com.moyou.moyouRms.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Page implements Serializable {
	/**
	 * 分页
	 */
	private static final long serialVersionUID = 1165589467666187499L;
	/** 当前页 */
	private int pageNumber;
	/* 每页显示条目数 */
	private int pageSize;
	/**
	 * 总条数
	 */
	private int total;
	private List<?> results;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private String festivalTitle;
	private String sortRule;// 排序

	public String getSortRule() {
		return sortRule;
	}

	public Page(int total, List<?> results) {
		super();
		this.total = total;
		this.results = results;
	}

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(int pageNumber, int pageSize) {
		super();
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public void setSortRule(String sortRule) {
		this.sortRule = sortRule;
	}

	public String getFestivalTitle() {
		return festivalTitle;
	}

	public void setFestivalTitle(String festivalTitle) {
		this.festivalTitle = festivalTitle;
	}

	// 性别：1.男 2.女
	private Integer sex;
	// 地址
	private String address;
	// 手机号
	private String phoneNumber;
	// 昵称
	private String nickname;
	// 用户状态 1.可用 ,意见详情里面的状态1,未处理，2以处理
	private Integer state;
	// 创建时间
	private Date createTime;
	// 出生日期
	private Date birthday;
	// 开始时间
	private String starttime;
	// 结束时间
	private String endtime;
	// 开始年龄
	private int startage;
	// 结束年龄
	private int endage;
	// 用户ID
	private String userId;
	// 圈子分类名称
	private String circleCategoryName;
	// 圈子数量
	private String countCircleId;
	// 话题数量
	private Integer topicTotal;
	// 已加入成员数
	private Integer memberTotal;

	private Integer crowdId;

	public Integer getCrowdId() {
		return crowdId;
	}

	public void setCrowdId(Integer crowdId) {
		this.crowdId = crowdId;
	}

	public String getRecommentState() {
		return recommentState;
	}

	public void setRecommentState(String recommentState) {
		this.recommentState = recommentState;
	}

	private String recommentState;

	public String getCountCircleId() {
		return countCircleId;
	}

	public void setCountCircleId(String countCircleId) {
		this.countCircleId = countCircleId;
	}

	public Integer getTopicTotal() {
		return topicTotal;
	}

	public void setTopicTotal(Integer topicTotal) {
		this.topicTotal = topicTotal;
	}

	public Integer getMemberTotal() {
		return memberTotal;
	}

	public void setMemberTotal(Integer memberTotal) {
		this.memberTotal = memberTotal;
	}

	public String getCircleCategoryName() {
		return circleCategoryName;
	}

	public void setCircleCategoryName(String circleCategoryName) {
		this.circleCategoryName = circleCategoryName;
	}

	/*
	 * 用户类型 1.真实用户 2.后台生成
	 */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/*
	 * 资源文件格式： 1.音频 2.视频 3.文件
	 */
	private Integer mediaType;
	// 举报列表分类
	private Integer reportType;
	// 举报列表举报类型
	private String displayName;

	private String dynamicId;

	public String getDynamicId() {
		return dynamicId;
	}

	public void setDynamicId(String dynamicId) {
		this.dynamicId = dynamicId;
	}

	public Integer getReportType() {
		return reportType;
	}

	public void setReportType(Integer reportType) {
		this.reportType = reportType;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getMediaType() {
		return mediaType;
	}

	public void setMediaType(Integer mediaType) {
		this.mediaType = mediaType;
	}

	// 评论量开始
	private String startcomment;

	// 评论量结束
	private String endcomment;

	public String getStartcomment() {
		return startcomment;
	}

	public void setStartcomment(String startcomment) {
		this.startcomment = startcomment;
	}

	public String getEndcomment() {
		return endcomment;
	}

	public void setEndcomment(String endcomment) {
		this.endcomment = endcomment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStartage() {
		return startage;
	}

	public void setStartage(int startage) {
		this.startage = startage;
	}

	public int getEndage() {
		return endage;
	}

	public void setEndage(int endage) {
		this.endage = endage;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(Long moyouId) {
		this.moyouId = moyouId;
	}

	// 陌友id
	private Long moyouId;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getResults() {
		return results;
	}

	public void setResults(List<?> results) {
		this.results = results;
	}

}
