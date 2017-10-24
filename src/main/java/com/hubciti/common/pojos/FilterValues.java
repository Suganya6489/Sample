package com.hubciti.common.pojos;

public class FilterValues {
	private Integer userId;
	private Integer fValueId;
	private String fValueName;
	private String screenName;
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @return the fValueId
	 */
	public Integer getfValueId() {
		return fValueId;
	}
	/**
	 * @return the fValueName
	 */
	public String getfValueName() {
		return fValueName;
	}
	/**
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @param fValueId the fValueId to set
	 */
	public void setfValueId(Integer fValueId) {
		this.fValueId = fValueId;
	}
	/**
	 * @param fValueName the fValueName to set
	 */
	public void setfValueName(String fValueName) {
		this.fValueName = fValueName;
	}
	/**
	 * @param screenName the screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
}
