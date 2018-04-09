package com.moyou.moyouRms.model.report;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.resource.BaseResource;

/**
 * 描述:t_report表的实体类
 * 
 * @version
 * @author: Administrator
 * @创建时间: 2017-02-25
 */
@SuppressWarnings("rawtypes")
public class Report extends BaseResource {
	/**
	 * 举报状态码
	 */
	public final static short REPORT_STATE_DEFAULT = 0;// 默认 未处理
	public final static short REPORT_STATE_LIMITT = 1;// 限制用户
	public final static short REPORT_STATE_WARNING = 2;// 警告用户
	public final static short REPORT_STATE_DELETE = 3;// 删除内容
	/**
	 * 举报类型： 1说说2日记3聊天4个人中心
	 */
	public final static short REPORT_OBJECT_TYPE_TALK = 1;// 说说
	public final static short REPORT_OBJECT_TYPE_DIARY = 2;// 日记
	public final static short REPORT_OBJECT_TYPE_CHAT = 3;// 聊天
	public final static short REPORT_OBJECT_TYPE_PERSONAL = 4;// 个人中心

	/**
	 * 自定义推送信息
	 */
	private String msg;

	/**
	 * 头像url
	 */
	private String avatar;
	private int sendUserId;

	// 举报资源集合
	private List<CommonResource> reportResourceList;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getSendUserId() {
		return sendUserId;
	}

	public void setSendUserId(int sendUserId) {
		this.sendUserId = sendUserId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * 被举报人的陌友Id
	 */
	private String moyouId;
	/**
	 * 被举报人的个性签名
	 */
	private String sig;

	public String getSig() {
		return sig;
	}

	public void setSig(String sig) {
		this.sig = sig;
	}

	public String getMoyouId() {
		return moyouId;
	}

	public void setMoyouId(String moyouId) {
		this.moyouId = moyouId;
	}

	/**
	 * 被投诉人名字
	 */
	private String toNickName;
	/**
	 * 基础数据类型 1=举报
	 */
	public static short BASE_RESOURCE_TYPE = 1;

	public String getToNickName() {
		return toNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5927894034649754560L;

	/**
	 * 举报者名字
	 */
	private String userName;

	/**
	 * 被举报人名字
	 */
	private String toUserName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * 主键自增
	 */
	private int id;

	/**
	 * 举报者Id
	 */
	private Integer userId;

	/**
	 * 举报类型： 1说说2专辑3聊天4个人中心
	 */
	private Short objectType;

	/**
	 * 举报数据的主键id
	 */
	private Integer objectId;

	/**
	 * 
	 */
	private String content;

	/**
	 * 举报状态： 0.未处理1.限制 2.警告 3.删除
	 */
	private int state;

	private int noDispose;// 未处理
	/**
	 * 举报分类id
	 */
	private String reportCategory;

	public int getNoDispose() {
		return noDispose;
	}

	public void setNoDispose(int noDispose) {
		this.noDispose = noDispose;
	}

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 修改时间
	 */
	private Date updateTime;

	/**
	 * 被举报userid
	 */
	private Integer toUserId;

	/**
	 * 主键自增
	 * 
	 * @return id 主键自增
	 */
	public int getId() {
		return id;
	}

	/**
	 * 主键自增
	 * 
	 * @param id
	 *            主键自增
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 举报者Id
	 * 
	 * @return user_id 举报者Id
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 举报者Id
	 * 
	 * @param userId
	 *            举报者Id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 举报类型： 1说说2专辑3聊天4个人中心
	 * 
	 * @return object_type 举报类型： 1说说2专辑3聊天4个人中心
	 */
	public short getObjectType() {
		return objectType;
	}

	/**
	 * 举报类型： 1说说2专辑3聊天4个人中心
	 * 
	 * @param objectType
	 *            举报类型： 1说说2专辑3聊天4个人中心
	 */
	public void setObjectType(Short objectType) {
		this.objectType = objectType;
	}

	/**
	 * 举报数据的主键id
	 * 
	 * @return object_id 举报数据的主键id
	 */
	public Integer getObjectId() {
		return objectId;
	}

	/**
	 * 举报数据的主键id
	 * 
	 * @param objectId
	 *            举报数据的主键id
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	/**
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * 举报分类id
	 * 
	 * @return report_category 举报分类id
	 */
	public String getReportCategory() {
		return reportCategory;
	}

	/**
	 * 举报分类id
	 * 
	 * @param reportCategory
	 *            举报分类id
	 */
	public void setReportCategory(String reportCategory) {
		this.reportCategory = reportCategory == null ? null : reportCategory.trim();
	}

	/**
	 * 创建时间
	 * 
	 * @return create_time 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改时间
	 * 
	 * @return update_time 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 * 
	 * @param updateTime
	 *            修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 被举报userid
	 * 
	 * @return to_user_id 被举报userid
	 */
	public Integer getToUserId() {
		return toUserId;
	}

	/**
	 * 被举报userid
	 * 
	 * @param toUserId
	 *            被举报userid
	 */
	public void setToUserId(Integer toUserId) {
		this.toUserId = toUserId;
	}

	public List<CommonResource> getReportResourceList() {
		return reportResourceList;
	}

	public void setReportResourceList(List<CommonResource> reportResourceList) {
		this.reportResourceList = reportResourceList;
	}

}