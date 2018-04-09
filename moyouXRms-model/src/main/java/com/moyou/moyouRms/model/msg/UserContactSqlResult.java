package com.moyou.moyouRms.model.msg;

import java.util.Date;

/**
 * UserContactSqlResult
 * 
 * @author PzC.
 * @time 2016年12月7日 上午11:31:11
 * 
 */
public class UserContactSqlResult extends UserContactReturn {
	private Date birthday;
	private Double latitude;
	private Double longitude;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		if (latitude != null) {
			if (latitude.doubleValue() != 0.0D) {
				this.latitude = latitude;
				return;
			}
		}
		this.latitude = 30.663432;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		if (longitude != null) {
			if (longitude.doubleValue() != 0.0D) {
				this.longitude = longitude;
				return;
			}
		}
		this.longitude = 104.072273;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	private Date createTime;
}
