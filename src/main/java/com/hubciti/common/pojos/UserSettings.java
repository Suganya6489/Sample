package com.hubciti.common.pojos;

public class UserSettings {

	private String responseCode;
	private String responseText;
	private Integer userId = null;
	private Integer localeRadius = null;
	private Boolean pushNotify = null;
	private String deviceId = null;
	private Integer hubCitiId;
	
	private Boolean bLocService = null;

	/**
	 * get userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * set userId
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * get wish list radius
	 */
	public Integer getLocaleRadius() {
		return localeRadius;
	}

	/**
	 * set wish list radius
	 */
	public void setLocaleRadius(Integer localeRadius) {
		this.localeRadius = localeRadius;
	}

	/**
	 * get push notification alert on/off
	 */
	public Boolean getPushNotify() {
		return pushNotify;
	}

	/**
	 * set push notification alert on/off
	 * 
	 * @param PushNotify
	 */
	public void setPushNotify(Boolean pushNotify) {
		this.pushNotify = pushNotify;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseText
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * @param responseText
	 *            the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * @return the bLocService
	 */
	public Boolean getbLocService() {
		return bLocService;
	}

	/**
	 * @param bLocService the bLocService to set
	 */
	public void setbLocService(Boolean bLocService) {
		this.bLocService = bLocService;
	}
}
