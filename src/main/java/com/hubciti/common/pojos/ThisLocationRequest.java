package com.hubciti.common.pojos;

/**
 * The POJO class for ThisLocationRequest.
 * 
 * @author Kumar D
 */

public class ThisLocationRequest {

	private String responseCode;

	private String responseText;

	private Integer userId;

	private Integer retAffId;

	private String catIds;

	private String searchKey;

	private Integer lowerLimit;

	private String latitude;

	private String longitude;

	private String postalCode;

	private Integer partnerId;

	private Integer hubCitiId;

	private Integer mainMenuId;

	private Integer citiExpId;

	private Integer mItemId;

	private Integer bottomBtnId;

	private Integer retGroupId;

	private String platform;

	private Integer eventId;

	private Integer locSpecials;
	
	private String interests;
	

	/**
	 * For preferred Radius.
	 */
	private Double preferredRadius;
	/**
	 * for zipcode.
	 */
	private String zipcode;
	/**
	 * for lastVisitedRecord.
	 */
	private Integer lastVisitedRecord;
	/**
	 * Is located on map boolean value
	 */
	private Boolean locOnMap;
	private Integer cuisineTypeId;
	/**
	 * for gpsEnabled.
	 */
	private Boolean gpsEnabled;

	private String sortOrder;

	private String sortColumn;
	
	private String groupBy;
	
	private String sortBy;
	/**
	 * for cityIds.
	 */
	private String cityIds;
	
	private String requestedTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRetAffId() {
		return retAffId;
	}

	public void setRetAffId(Integer retAffId) {
		this.retAffId = retAffId;
	}

	public String getCatIds() {
		return catIds;
	}

	public void setCatIds(String catIds) {
		this.catIds = catIds;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public Integer getCitiExpId() {
		return citiExpId;
	}

	public void setCitiExpId(Integer citiExpId) {
		this.citiExpId = citiExpId;
	}

	public Integer getmItemId() {
		return mItemId;
	}

	public void setmItemId(Integer mItemId) {
		this.mItemId = mItemId;
	}

	public Integer getBottomBtnId() {
		return bottomBtnId;
	}

	public void setBottomBtnId(Integer bottomBtnId) {
		this.bottomBtnId = bottomBtnId;
	}

	/**
	 * @return the retGroupId
	 */
	public Integer getRetGroupId() {
		return retGroupId;
	}

	/**
	 * @param retGroupId
	 *            the retGroupId to set
	 */
	public void setRetGroupId(Integer retGroupId) {
		this.retGroupId = retGroupId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**
	 * @return the gpsEnabled
	 */
	public Boolean getGpsEnabled() {
		return gpsEnabled;
	}

	/**
	 * @param gpsEnabled
	 *            the gpsEnabled to set
	 */
	public void setGpsEnabled(Boolean gpsEnabled) {
		this.gpsEnabled = gpsEnabled;
	}

	/**
	 * @return the preferredRadius
	 */
	public Double getPreferredRadius() {
		return preferredRadius;
	}

	/**
	 * @param preferredRadius
	 *            the preferredRadius to set
	 */
	public void setPreferredRadius(Double preferredRadius) {
		this.preferredRadius = preferredRadius;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the lastVisitedRecord
	 */
	public Integer getLastVisitedRecord() {
		return lastVisitedRecord;
	}

	/**
	 * @param lastVisitedRecord
	 *            the lastVisitedRecord to set
	 */
	public void setLastVisitedRecord(Integer lastVisitedRecord) {
		this.lastVisitedRecord = lastVisitedRecord;
	}

	/**
	 * @return the locOnMap
	 */
	public Boolean getLocOnMap() {
		return locOnMap;
	}

	/**
	 * @param locOnMap
	 *            the locOnMap to set
	 */
	public void setLocOnMap(Boolean locOnMap) {
		this.locOnMap = locOnMap;
	}

	/**
	 * @return the cuisineTypeId
	 */
	public Integer getCuisineTypeId() {
		return cuisineTypeId;
	}

	/**
	 * @param cuisineTypeId
	 *            the cuisineTypeId to set
	 */
	public void setCuisineTypeId(Integer cuisineTypeId) {
		this.cuisineTypeId = cuisineTypeId;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
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

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	/**
	 * @return the cityIds
	 */
	public String getCityIds() {
		return cityIds;
	}

	/**
	 * @param cityIds the cityIds to set
	 */
	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the locSpecials
	 */
	public Integer getLocSpecials() {
		return locSpecials;
	}

	/**
	 * @return the interests
	 */
	public String getInterests() {
		return interests;
	}

	/**
	 * @param locSpecials the locSpecials to set
	 */
	public void setLocSpecials(Integer locSpecials) {
		this.locSpecials = locSpecials;
	}

	/**
	 * @param interests the interests to set
	 */
	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(String requestedTime) {
		this.requestedTime = requestedTime;
	}

}
