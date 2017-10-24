package com.hubciti.common.pojos;

/**
 * The POJO class for ShareProductInfo.
 * 
 * @author Kumar D
 */

public class ShareProductInfo {
	/**
	 * for layalty id
	 */
	private Integer loyaltyId;
	/**
	 * for rebate id
	 */
	private Integer rebateId;
	/**
	 * for hotdeal id
	 */
	private Integer hotdealId;
	/**
	 * for coupon Id
	 */
	private Integer couponId;
	/**
	 * for coupon Id
	 */
	private Integer hubCitiId;
	/**
	 * The toEmail property.
	 */

	private String toEmail;

	/**
	 * The productId property.
	 */

	private Integer productId;

	/**
	 * The userId property.
	 */

	private Integer userId;
	
	/**
	 * The userId property.
	 */

	private Integer newsId;

	/**
	 * The userId property.
	 */

	private Integer isFromThisLocation;
	/**
	 * The retailerId property.
	 */
	private Integer retailerId;

	/**
	 * The retailerLocationId property.
	 */
	private Integer retailerLocationId;

	private String clrFlag;

	/**
	 * for pageID
	 */
	private Long pageId;
	/**
	 * The shareType property.
	 */
	private String shareType;
	/**
	 * The userId property.
	 */
	private Integer mainMenuID;
	/**
	 * The userId property.
	 */
	private Integer eventId;

	/**
	 * The userId property.
	 */
	private Integer fundraiserId;
	/**
	 * The retailerLocationId property.
	 */
	private Integer qrTypeId;
	/**
	 * The retailerLocationId property.
	 */
	private String qrTypeCode;

	/**
	 * Variable for platform identification
	 */
	private String platform;

	/**
	 * Variable for bandId identification
	 */
	private String bandId;

	public String getBandId() {
		return bandId;
	}

	public void setBandId(String bandId) {
		this.bandId = bandId;
	}

	/**
	 * 
	 * @return the platform to set
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * 
	 * @param The
	 *            platform
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * For getting isFromThisLocation.
	 * 
	 * @return the isFromThisLocation
	 */
	public Integer getIsFromThisLocation() {
		return isFromThisLocation;
	}

	/**
	 * For setting isFromThisLocation.
	 * 
	 * @param isFromThisLocation
	 *            the isFromThisLocation to set
	 */
	public void setIsFromThisLocation(Integer isFromThisLocation) {
		this.isFromThisLocation = isFromThisLocation;
	}

	/**
	 * For getting retailerId.
	 * 
	 * @return the retailerId
	 */
	public Integer getRetailerId() {
		return retailerId;
	}

	/**
	 * For setting retailerId.
	 * 
	 * @param retailerId
	 *            the retailerId to set
	 */
	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}

	/**
	 * For getting retailerLocationId..
	 * 
	 * @return the retailerLocationId
	 */
	public Integer getRetailerLocationId() {
		return retailerLocationId;
	}

	/**
	 * For setting retailerLocationId.
	 * 
	 * @param retailerLocationId
	 *            the retailerLocationId to set
	 */
	public void setRetailerLocationId(Integer retailerLocationId) {
		this.retailerLocationId = retailerLocationId;
	}

	/**
	 * For getting toEmail.
	 * 
	 * @return toEmail To get
	 */
	public String getToEmail() {
		return toEmail;
	}

	/**
	 * For setting toEmail.
	 * 
	 * @param toEmail
	 *            The toEmail property.
	 */

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	/**
	 * For getting productId.
	 * 
	 * @return The productId property.
	 */

	public Integer getProductId() {
		return productId;
	}

	/**
	 * For setting productId.
	 * 
	 * @param productId
	 *            The productId property.
	 */

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * For getting userId.
	 * 
	 * @return The userId property.
	 */

	public Integer getUserId() {
		return userId;
	}

	/**
	 * For setting userId.
	 * 
	 * @param userId
	 *            The userId property.
	 */

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public Integer getHotdealId() {
		return hotdealId;
	}

	public void setHotdealId(Integer hotdealId) {
		this.hotdealId = hotdealId;
	}

	public String getClrFlag() {
		return clrFlag;
	}

	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}

	public Integer getLoyaltyId() {
		return loyaltyId;
	}

	public void setLoyaltyId(Integer loyaltyId) {
		this.loyaltyId = loyaltyId;
	}

	public Integer getRebateId() {
		return rebateId;
	}

	public void setRebateId(Integer rebateId) {
		this.rebateId = rebateId;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the shareType
	 */
	public String getShareType() {
		return shareType;
	}

	/**
	 * @param shareType
	 *            the shareType to set
	 */
	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	/**
	 * @return the mainMenuID
	 */
	public Integer getMainMenuID() {
		return mainMenuID;
	}

	/**
	 * @param mainMenuID
	 *            the mainMenuID to set
	 */
	public void setMainMenuID(Integer mainMenuID) {
		this.mainMenuID = mainMenuID;
	}

	/**
	 * @return the hubCitiId
	 */
	public Integer getHubCitiId() {
		return hubCitiId;
	}

	/**
	 * @param hubCitiId
	 *            the hubCitiId to set
	 */
	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the fundraiserId
	 */
	public Integer getFundraiserId() {
		return fundraiserId;
	}

	/**
	 * @param fundraiserId
	 *            the fundraiserId to set
	 */
	public void setFundraiserId(Integer fundraiserId) {
		this.fundraiserId = fundraiserId;
	}
	/**
	 * @return the qrTypeId
	 */
	public Integer getQrTypeId() {
		return qrTypeId;
	}

	/**
	 * @param qrTypeId
	 *            the qrTypeId to set
	 */
	public void setQrTypeId(Integer qrTypeId) {
		this.qrTypeId = qrTypeId;
	}
	/**
	 * @return the qrTypeCode
	 */
	public String getQrTypeCode() {
		return qrTypeCode;
	}

	/**
	 * @param qrTypeCode
	 *            the qrTypeCode to set
	 */
	public void setQrTypeCode(String qrTypeCode) {
		this.qrTypeCode = qrTypeCode;
	}

	/**
	 * @return the newsId
	 */
	public Integer getNewsId() {
		return newsId;
	}

	/**
	 * @param newsId the newsId to set
	 */
	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

}
