package com.hubciti.common.pojos;

import com.hubciti.common.utility.Utility;

public class Deal {

	private Integer dealId;

	private String dealName;

	private String dealDesc;

	private String type;

	private String splUrl;

	private String hcName;

	private String Name;
	private String imagePath;
	private Boolean flag;
	private Integer userId;
	private Integer hubCitiId;

	/**
	 * Variable latitude declared as string.
	 */
	private String latitude;

	/**
	 * Variable longitude declared as string.
	 */
	private String longitude;

	/**
	 * Variable zipCode declared as string.
	 */
	private String zipCode;

	private String retailerId;

	private String retailLocationId;

	private Boolean isPushNotification;

	public Boolean getIsPushNotification() {
		return isPushNotification;
	}

	public void setIsPushNotification(Boolean isPushNotification) {
		this.isPushNotification = isPushNotification;
	}

	public String getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailLocationId() {
		return retailLocationId;
	}

	public void setRetailLocationId(String retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	public Integer getDealId() {
		return dealId;
	}

	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		if (null !=this.getIsPushNotification() && this.getIsPushNotification()) {
			this.dealName = dealName;
		} else if (!"".equals(Utility.checkNull(dealName))) {
			this.dealName = "<![CDATA[" + dealName + "]]>";
		} else {
			this.dealName = dealName;
		}
	}

	public String getDealDesc() {
		return dealDesc;
	}

	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSplUrl() {
		return splUrl;
	}

	public void setSplUrl(String splUrl) {
		this.splUrl = splUrl;
	}

	public String getHcName() {
		return hcName;
	}

	public void setHcName(String hcName) {
		this.hcName = hcName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @return the flag
	 */
	public Boolean getFlag() {
		return flag;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return the hubCitiId
	 */
	public Integer getHubCitiId() {
		return hubCitiId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @param hubCitiId
	 *            the hubCitiId to set
	 */
	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
