package com.moyou.moyouRms.model.liveshow;

import com.moyou.moyouRms.model.BaseModel;

public class LiveFakeRoomEdit extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键
	private String roomName;//房间名称
	private String cover;//房间封面图
	private String city;//创建房间的城市
	private Integer consume;//房间消费
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * @return the cover
	 */
	public String getCover() {
		return cover;
	}
	/**
	 * @param cover the cover to set
	 */
	public void setCover(String cover) {
		this.cover = cover;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the consume
	 */
	public Integer getConsume() {
		return consume;
	}
	/**
	 * @param consume the consume to set
	 */
	public void setConsume(Integer consume) {
		this.consume = consume;
	}
	
	

}
