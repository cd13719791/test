package com.moyou.moyouRms.model.diary;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 描述:t_diary表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-02-28
 */
public class Diary {
	/**
	 * 日记 状态 0删除 1正常
	 */
	public static final Short DIARY_DELETE_NUMBER = 0;
	public static final Short DIARY_NORMAL_NUMBER = 1;
	private int readTotal;
	public final static int SOURCE_TYPE_APP = 0;
	public final static int SOURCE_TYPE_HTML = 1;
	public final static int SOURCE_TYPE_OTHER = 2;
	public final static int URL_ANALYS_SUCCESS = 1;
	public final static int URL_ANALYSIS_ERRO = 0;
	private int sourceType;// '0App格式发布1后台html发布2第三方分享',
	// 'h5地址，默认空',
	private String h5Url;
	// 'url解析0未成功1成功'
	private int urlAnalysisSuccess;
	private Integer referenceType;
	private String shareAppName;// 分享过来的名字
	private Integer relateDiaryId;
	private Integer shareMedia;
	private String extendData;
	private String rawUrl;
	private String videoUrl;
	private String moyouId;

	public Diary(Integer id2, String extendData2, String url) {
		super();
		this.id = id2;
		this.extendData = extendData2;
		this.videoUrl = url;

	}

	public Diary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Diary(int readTotal, int sourceType, String h5Url, int urlAnalysisSuccess,
			Integer referenceType, String shareAppName, Integer relateDiaryId, Integer shareMedia,
			String extendData, String rawUrl, String videoUrl, String moyouId, int type,
			Integer picTotal, Integer orderBy, String searchContent, int recommedStatus,
			Integer id, Integer diaryFolderId, String diaryTitle, String city, Integer creatorId,
			String creatorName, Integer praiseTotal, Integer commentTotal, Integer rewardTotal,
			Integer state, Date createTime, List<DiaryContent> diaryContents,
			Map<String, String> firstTextAndPic, List<DiaryComment> diaryComments, String avatar,
			List<DiaryPraise> diaryPraiseList) {
		super();
		this.readTotal = readTotal;
		this.sourceType = sourceType;
		this.h5Url = h5Url;
		this.urlAnalysisSuccess = urlAnalysisSuccess;
		this.referenceType = referenceType;
		this.shareAppName = shareAppName;
		this.relateDiaryId = relateDiaryId;
		this.shareMedia = shareMedia;
		this.extendData = extendData;
		this.rawUrl = rawUrl;
		this.videoUrl = videoUrl;
		this.moyouId = moyouId;
		this.type = type;
		this.picTotal = picTotal;
		this.orderBy = orderBy;
		this.searchContent = searchContent;
		this.recommedStatus = recommedStatus;
		this.id = id;
		this.diaryFolderId = diaryFolderId;
		this.diaryTitle = diaryTitle;
		this.city = city;
		this.creatorId = creatorId;
		this.creatorName = creatorName;
		this.praiseTotal = praiseTotal;
		this.commentTotal = commentTotal;
		this.rewardTotal = rewardTotal;
		this.state = state;
		this.createTime = createTime;
		this.diaryContents = diaryContents;
		this.firstTextAndPic = firstTextAndPic;
		this.diaryComments = diaryComments;
		this.avatar = avatar;
		this.diaryPraiseList = diaryPraiseList;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	/**
	 * @return the videoUrl
	 */
	public String getVideoUrl() {
		return videoUrl;
	}

	/**
	 * @param videoUrl
	 *            the videoUrl to set
	 */
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	/**
	 * @return the rawUrl
	 */
	public String getRawUrl() {
		return rawUrl;
	}

	/**
	 * @param rawUrl
	 *            the rawUrl to set
	 */
	public void setRawUrl(String rawUrl) {
		this.rawUrl = rawUrl;
	}

	public Integer getShareMedia() {
		return shareMedia;
	}

	public void setShareMedia(Integer shareMedia) {
		this.shareMedia = shareMedia;
	}

	public String getExtendData() {
		return extendData;
	}

	public void setExtendData(String extendData) {
		this.extendData = extendData;
	}

	public Integer getRelateDiaryId() {
		return relateDiaryId;
	}

	public void setRelateDiaryId(Integer relateDiaryId) {
		this.relateDiaryId = relateDiaryId;
	}

	public String getShareAppName() {
		return shareAppName;
	}

	public void setShareAppName(String shareAppName) {
		this.shareAppName = shareAppName;
	}

	public Integer getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(Integer referenceType) {
		this.referenceType = referenceType;
	}

	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public String getH5Url() {
		return h5Url;
	}

	public void setH5Url(String h5Url) {
		this.h5Url = h5Url;
	}

	public int getUrlAnalysisSuccess() {
		return urlAnalysisSuccess;
	}

	public void setUrlAnalysisSuccess(int urlAnalysisSuccess) {
		this.urlAnalysisSuccess = urlAnalysisSuccess;
	}

	private int type;
	/**
	 * 图片数量
	 */
	private Integer picTotal;

	public Integer getPicTotal() {
		return picTotal;
	}

	public void setPicTotal(Integer picTotal) {
		this.picTotal = picTotal;
	}

	/**
	 * 排序字段 用于 标记用什么内容排序 0日期 1点赞数 2 评论数 3打赏数
	 */
	private Integer orderBy;

	/**
	 * 根据内容模糊查询 检索属性
	 */
	private String searchContent;

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	private int recommedStatus;

	public int getRecommedStatus() {
		return recommedStatus;
	}

	public void setRecommedStatus(int recommedStatus) {
		this.recommedStatus = recommedStatus;
	}

	/**
     * 
     */
	private Integer id;

	/**
	 * 日记本 id
	 */
	private Integer diaryFolderId;

	/**
	 * 日记标题
	 */
	private String diaryTitle;

	/**
	 * 城市定位
	 */
	private String city;

	/**
	 * 创建用户 id
	 */
	private Integer creatorId;
	/**
	 * 创建用户名
	 */
	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * 点赞数统计
	 */
	private Integer praiseTotal;

	/**
	 * 评论数统计
	 */
	private Integer commentTotal;

	/**
	 * 打赏数
	 */
	private Integer rewardTotal;

	/**
	 * 0删除 1正常
	 */
	private Integer state;

	/**
     * 
     */
	private Date createTime;

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 日记本 id
	 * 
	 * @return diary_folder_id 日记本 id
	 */
	public Integer getDiaryFolderId() {
		return diaryFolderId;
	}

	/**
	 * 日记本 id
	 * 
	 * @param diaryFolderId
	 *            日记本 id
	 */
	public void setDiaryFolderId(Integer diaryFolderId) {
		this.diaryFolderId = diaryFolderId;
	}

	/**
	 * 日记标题
	 * 
	 * @return diary_title 日记标题
	 */
	public String getDiaryTitle() {
		return diaryTitle;
	}

	/**
	 * 日记标题
	 * 
	 * @param diaryTitle
	 *            日记标题
	 */
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle == null ? null : diaryTitle.trim();
	}

	/**
	 * 城市定位
	 * 
	 * @return city 城市定位
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 城市定位
	 * 
	 * @param city
	 *            城市定位
	 */
	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	/**
	 * 创建用户 id
	 * 
	 * @return creator_id 创建用户 id
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * 创建用户 id
	 * 
	 * @param creatorId
	 *            创建用户 id
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 点赞数统计
	 * 
	 * @return praise_total 点赞数统计
	 */
	public Integer getPraiseTotal() {
		return praiseTotal;
	}

	/**
	 * 点赞数统计
	 * 
	 * @param praiseTotal
	 *            点赞数统计
	 */
	public void setPraiseTotal(Integer praiseTotal) {
		this.praiseTotal = praiseTotal;
	}

	/**
	 * 评论数统计
	 * 
	 * @return comment_total 评论数统计
	 */
	public Integer getCommentTotal() {
		return commentTotal;
	}

	/**
	 * 评论数统计
	 * 
	 * @param commentTotal
	 *            评论数统计
	 */
	public void setCommentTotal(Integer commentTotal) {
		this.commentTotal = commentTotal;
	}

	/**
	 * 打赏数
	 * 
	 * @return reward_total 打赏数
	 */
	public Integer getRewardTotal() {
		return rewardTotal;
	}

	/**
	 * 打赏数
	 * 
	 * @param rewardTotal
	 *            打赏数
	 */
	public void setRewardTotal(Integer rewardTotal) {
		this.rewardTotal = rewardTotal;
	}

	/**
	 * 0删除 1正常
	 * 
	 * @return state 0删除 1正常
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 0删除 1正常
	 * 
	 * @param state
	 *            0删除 1正常
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 
	 * @return create_time
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<DiaryContent> getDiaryContents() {
		return diaryContents;
	}

	public void setDiaryContents(List<DiaryContent> diaryContents) {
		this.diaryContents = diaryContents;
	}

	/**
	 * 日记内容集合
	 */
	private List<DiaryContent> diaryContents;
	/**
	 * 日记第一段内容和图片
	 */
	private Map<String, String> firstTextAndPic;

	public Map<String, String> getFirstTextAndPic() {
		return firstTextAndPic;
	}

	public void setFirstTextAndPic(Map<String, String> firstTextAndPic) {
		this.firstTextAndPic = firstTextAndPic;
	}

	/**
	 * 日记评论
	 */
	private List<DiaryComment> diaryComments;

	public List<DiaryComment> getDiaryComments() {
		return diaryComments;
	}

	public void setDiaryComments(List<DiaryComment> diaryComments) {
		this.diaryComments = diaryComments;
	}

	/**
	 * 头像
	 */
	private String avatar;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<DiaryPraise> getDiaryPraiseList() {
		return diaryPraiseList;
	}

	public void setDiaryPraiseList(List<DiaryPraise> diaryPraiseList) {
		this.diaryPraiseList = diaryPraiseList;
	}

	public int getReadTotal() {
		return readTotal;
	}

	public void setReadTotal(int readTotal) {
		this.readTotal = readTotal;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 点赞集合
	 */
	private List<DiaryPraise> diaryPraiseList;
}
