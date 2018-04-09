package com.moyou.moyouRms.model.notification;

public class DataCondition<Data extends DataModel> {
	private Integer businessType;// 业务分类 1说说相关 2专辑故事相关
	private Data data;

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}
