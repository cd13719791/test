package com.moyou.moyouRms.model.user;

import java.util.Date;

import com.moyou.moyouRms.model.BaseModel;

public class BindData extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3852572914367246286L;

	private Integer id;

	private Integer userId;

	private Short bindType;

	private String bindData1;

	private String bindData2;

	private Integer bindData3;

	private String openId;
	private String WeiXinNickName;

	public String getWeiXinNickName() {
		return WeiXinNickName;
	}

	public void setWeiXinNickName(String weiXinNickName) {
		WeiXinNickName = weiXinNickName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Short getBindType() {
		return bindType;
	}

	public void setBindType(Short bindType) {
		this.bindType = bindType;
	}

	public String getBindData1() {
		return bindData1;
	}

	public void setBindData1(String bindData1) {
		this.bindData1 = bindData1 == null ? null : bindData1.trim();
	}

	public String getBindData2() {
		return bindData2;
	}

	public void setBindData2(String bindData2) {
		this.bindData2 = bindData2 == null ? null : bindData2.trim();
	}

	public Integer getBindData3() {
		return bindData3;
	}

	public void setBindData3(Integer bindData3) {
		this.bindData3 = bindData3;
	}
}