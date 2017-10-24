package com.hubciti.common.pojos;

/**
 * The POJO class for HotDealsListRequest.
 * 
 * @author shyamsundara_hm.
 */
public class HotDealsListRequest {

	/**
	 * for PopulationCentreID
	 */
	private Integer populationCentreId;

	/**
	 * for hotdeal category.
	 */
	private String category;
	/**
	 * Variable userId declared as Integer.
	 */
	private Integer userId;
	/**
	 * Variable showType declared as String.
	 */
	private String showType;
	/**
	 * Variable LowerLimit declared as Integer.
	 */
	private Integer LowerLimit;
	/**
	 * Variable sortType declared as String.
	 */
	private String sortType;
	/**
	 * Variable searchItem declared as String.
	 */
	private String searchItem;
	/**
	 * Variable lastVisitedProductNo declared as Integer.
	 */
	private Integer lastVisitedProductNo;
	/**
	 * Variable hotDealId declared as Integer.
	 */
	private Integer hotDealId;
	/**
	 * Variable hDInterested declared as Integer.
	 */
	private Integer hDInterested;
	/**
	 * Variable isFaveCategory declared as Boolean.
	 */
	private Boolean isFaveCategory;

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

	/**
	 * Variable radius declared as integer.
	 */
	private Integer radius;

	/**
	 * Variable city declared as string.
	 */
	private String city;

	/**
	 * For user tracking
	 */
	private Integer mainMenuId;

	private Integer mItemId;

	private Integer bottomBtnId;

	private String platform;

	private Integer hubCitiId;

	/**
	 * Gets the value of city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the values of city.
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the value of latitude.
	 * 
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * sets the value of latitude.
	 * 
	 * @param latitude
	 *            -the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the value of longitude.
	 * 
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * Sets the value of longitude.
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * Gets the value of zipCode.
	 * 
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the value of zipCode.
	 * 
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * gets the value of radius.
	 * 
	 * @return the radius
	 */
	public Integer getRadius() {
		return radius;
	}

	/**
	 * Sets the value of radius.
	 * 
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	/**
	 * Gets the value of the isFaveCategory property.
	 * 
	 * @return the isFaveCategory
	 */

	public Boolean getIsFaveCategory() {
		return isFaveCategory;
	}

	/**
	 * Sets the value of the isFaveCategory property.
	 * 
	 * @param isFaveCategory
	 *            as of type Boolean.
	 */
	public void setIsFaveCategory(Boolean isFaveCategory) {
		this.isFaveCategory = isFaveCategory;
	}

	/**
	 * Gets the value of the hDInterested property.
	 * 
	 * @return the hDInterested
	 */

	public Integer gethDInterested() {
		return hDInterested;
	}

	/**
	 * Sets the value of the hDInterested property.
	 * 
	 * @param hDInterested
	 *            as of type Integer.
	 */
	public void sethDInterested(Integer hDInterested) {
		this.hDInterested = hDInterested;
	}

	/**
	 * Gets the value of the hotDealId property.
	 * 
	 * @return the hotDealId
	 */

	public Integer getHotDealId() {
		return hotDealId;
	}

	/**
	 * Sets the value of the hotDealId property.
	 * 
	 * @param hotDealId
	 *            as of type Integer.
	 */
	public void setHotDealId(Integer hotDealId) {
		this.hotDealId = hotDealId;
	}

	/**
	 * Gets the value of the lastVisitedProductNo property.
	 * 
	 * @return the lastVisitedProductNo
	 */
	public Integer getLastVisitedProductNo() {
		return lastVisitedProductNo;
	}

	/**
	 * Sets the value of the lastVisitedProductNo property.
	 * 
	 * @param lastVisitedProductNo
	 *            as of type Integer.
	 */
	public void setLastVisitedProductNo(Integer lastVisitedProductNo) {
		this.lastVisitedProductNo = lastVisitedProductNo;
	}

	/**
	 * Gets the value of the sortType property.
	 * 
	 * @return the sortType
	 */
	public String getSortType() {
		return sortType;
	}

	/**
	 * Sets the value of the sortType property.
	 * 
	 * @param sortType
	 *            as of type String.
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	/**
	 * Gets the value of the searchItem property.
	 * 
	 * @return the searchItem
	 */

	public String getSearchItem() {
		return searchItem;
	}

	/**
	 * Sets the value of the searchItem property.
	 * 
	 * @param searchItem
	 *            as of type String.
	 */
	public void setSearchItem(String searchItem) {
		this.searchItem = searchItem;
	}

	/**
	 * Gets the value of the userId property.
	 * 
	 * @return the userId
	 */

	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the value of the userId property.
	 * 
	 * @param userId
	 *            as of type Integer.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the value of the showType property.
	 * 
	 * @return the showType
	 */

	public String getShowType() {
		return showType;
	}

	/**
	 * Sets the value of the showType property.
	 * 
	 * @param showType
	 *            as of type String.
	 */
	public void setShowType(String showType) {
		this.showType = showType;
	}

	/**
	 * Gets the value of the lowerLimit property.
	 * 
	 * @return the lowerLimit
	 */

	public Integer getLowerLimit() {
		return LowerLimit;
	}

	/**
	 * Sets the value of the lowerLimit property.
	 * 
	 * @param lowerLimit
	 *            as of type Integer.
	 */
	public void setLowerLimit(Integer lowerLimit) {
		LowerLimit = lowerLimit;
	}

	/**
	 * get category.
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * set category.
	 * 
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPopulationCentreId() {
		return populationCentreId;
	}

	public void setPopulationCentreId(Integer populationCentreId) {
		this.populationCentreId = populationCentreId;
	}

	/**
	 * For user tracking
	 * 
	 * @return mainMenuID
	 */
	public Integer getMainMenuId() {
		return mainMenuId;
	}

	/**
	 * For user tracking
	 * 
	 * @param mainMenuID
	 */
	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

}
