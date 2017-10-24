package com.hubciti.common.pojos;

public class Temp {

	private String userName;

	private Integer userId;

	private Integer hubCitiId;
	
	private String bkMarkOrder;
	
	private String sectnOrder;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the hubCitiId
	 */
	public Integer getHubCitiId() {
		return hubCitiId;
	}

	/**
	 * @param hubCitiId the hubCitiId to set
	 */
	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * @return the bkMarkOrder
	 */
	public String getBkMarkOrder() {
		return bkMarkOrder;
	}

	/**
	 * @param bkMarkOrder the bkMarkOrder to set
	 */
	public void setBkMarkOrder(String bkMarkOrder) {
		this.bkMarkOrder = bkMarkOrder;
	}

	/**
	 * @return the sectnOrder
	 */
	public String getSectnOrder() {
		return sectnOrder;
	}

	/**
	 * @param sectnOrder the sectnOrder to set
	 */
	public void setSectnOrder(String sectnOrder) {
		this.sectnOrder = sectnOrder;
	}



}
