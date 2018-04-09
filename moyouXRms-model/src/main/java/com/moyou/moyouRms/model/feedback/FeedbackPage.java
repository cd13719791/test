package com.moyou.moyouRms.model.feedback;

import java.util.Date;
import java.util.List;

import com.moyou.moyouRms.model.BaseModel;

public class FeedbackPage extends BaseModel{
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		/** 当前页 */
		private int pageNumber;
		/* 每页显示条目数 */
		private int pageSize;
		/**
		 * 总条数
		 */
		private int total;
		private List<?> results;
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

		private Integer id;

	    private Integer userId;

	    private String content;

	    private Short formApp;

	    private String phone;

	    private String email;

	    private String moyouVersion;

	    private int state;
	   private  String nickname;
	    public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

		private Date createTime=new Date();

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

	    public String getContent() {
	        return content;
	    }

	    public void setContent(String content) {
	        this.content = content == null ? null : content.trim();
	    }

	    public Short getFormApp() {
	        return formApp;
	    }

	    public void setFormApp(Short formApp) {
	        this.formApp = formApp;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone == null ? null : phone.trim();
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email == null ? null : email.trim();
	    }

	    public String getMoyouVersion() {
	        return moyouVersion;
	    }

	    public void setMoyouVersion(String moyouVersion) {
	        this.moyouVersion = moyouVersion == null ? null : moyouVersion.trim();
	    }

	    public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public Date getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(Date createTime) {
	        this.createTime = createTime;
	    }

}
