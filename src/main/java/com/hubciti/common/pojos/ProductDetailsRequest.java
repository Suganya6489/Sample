package com.hubciti.common.pojos;

/**
 * POJO class for ProductDetailsRequest.
 * 
 * @author Kumar D
 */

public class ProductDetailsRequest {
	/**
	 * The responseCode declared as String.
	 */
	private String responseCode;
	/**
	 * The responseText declared as String.
	 */
	private String responseText;
	/**
	 * The postalcode declared as String.
	 */
	private String postalcode;

	/**
	 * for adding from wish list history.
	 */

	private Boolean isWishlstHistory;

	/**
	 * userRetailPreferenceId declared as Integer.
	 */
	private Integer userRetailPreferenceId;
	/**
	 * retailId declared as Integer.
	 */
	private Integer retailId;

	/**
	 * retailLocationId declared as Integer.
	 */

	private Integer retailLocationId;

	/**
	 * lowerLimit declared as Integer.
	 */
	private Integer lowerLimit;

	/**
	 * parentCategoryId declared as Integer.
	 */
	private Integer parentCategoryId;

	/**
	 * for userId.
	 */
	private Integer userId;
	/**
	 * for categoryId.
	 */
	private Integer categoryId;
	/**
	 * for lastVisitedProductNo.
	 */
	private Integer lastVisitedProductNo;
	/**
	 * for productName.
	 */
	private String productName;
	/**
	 * for productDescription.
	 */
	private String productDescription;
	/**
	 * for productId.
	 */
	private Integer productId;

	private Integer mItemId;
	private String platform;
	private Integer bottomBtnId;

	/**
	 * for productDetails.
	 */
	// private ArrayList<ProductDetail> productDetails;

	/**
	 * for latitude.
	 */
	private String latitude;

	/**
	 * for longitude.
	 */
	private String longitude;

	/**
	 * for flag to know flow.
	 */
	private String addedTo;

	/**
	 * For user tracking
	 */
	private Integer mainMenuId;

	/**
	 * For user tracking
	 */
	private Integer prodListId;

	/**
	 * For user tracking
	 */
	private Integer saleListId;

	/**
	 * For user tracking
	 */
	private Integer scanId;

	/**
	 * for user tracking
	 */
	private Integer retListId;

	/**
	 * For scanTypeId
	 */
	private Integer scanTypeId;

	/**
	 * For Business Category Ids
	 */
	private String busCatIds;

	/**
	 * For category Ids
	 */
	private String catIds;

	/**
	 * For population centre Id
	 */
	private Integer popCentId;

	/**
	 * For seaarch key
	 */
	private String searchKey;

	/**
	 * For module Id
	 */
	private Integer moduleId;

	/**
	 * 
	 */
	private String type;
	/**
	 * 
	 */
	private Integer hubCitiId;

	private String mediaType;

	/**
	 * To get postalcode.
	 * 
	 * @return the postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}

	/**
	 * To set postalcode.
	 * 
	 * @param postalcode
	 *            the postalcode to set
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * for getting lowerLimit.
	 * 
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * for setting lowerLimit.
	 * 
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * for getting retailLocationId.
	 * 
	 * @return the retailLocationId
	 */
	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	/**
	 * for setting retailLocationId.
	 * 
	 * @param retailLocationId
	 *            the retailLocationId to set
	 */
	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	/**
	 * for getting userRetailPreferenceId.
	 * 
	 * @return the userRetailPreferenceId
	 */
	public Integer getUserRetailPreferenceId() {
		return userRetailPreferenceId;
	}

	/**
	 * for setting userRetailPreferenceId.
	 * 
	 * @param userRetailPreferenceId
	 *            the userRetailPreferenceId to set
	 */
	public void setUserRetailPreferenceId(Integer userRetailPreferenceId) {
		this.userRetailPreferenceId = userRetailPreferenceId;
	}

	/**
	 * for getting retailId.
	 * 
	 * @return the retailId
	 */
	public Integer getRetailId() {
		return retailId;
	}

	/**
	 * for setting retailId.
	 * 
	 * @param retailId
	 *            the retailId to set
	 */
	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * for retrieving product details.
	 * 
	 * @return productDetails
	 */
	// public ArrayList<ProductDetail> getProductDetails()
	// {
	// return productDetails;
	// }

	/**
	 * for setting product details.
	 * 
	 * @param productDetails
	 *            as request.
	 */
	// public void setProductDetails(ArrayList<ProductDetail> productDetails)
	// {
	// this.productDetails = productDetails;
	// }

	/**
	 * this method for getting productId.
	 * 
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * this method for setting productId.
	 * 
	 * @param productId
	 *            as request.
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * this method for getting userId.
	 * 
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * this method for setting userId.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * this method for getting categoryId.
	 * 
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * this method for setting category Id.
	 * 
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * This method for getting productName.
	 * 
	 * @return productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * this method for setting product name.
	 * 
	 * @param productName
	 *            as request parameter.
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * this method for getting product description.
	 * 
	 * @return productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * this method for setting product description.
	 * 
	 * @param productDescription
	 *            as the request.
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	/**
	 * This method for getting lastVisitedProductNo.
	 * 
	 * @return lastVisitedProductNo
	 */
	public Integer getLastVisitedProductNo() {
		return lastVisitedProductNo;
	}

	/**
	 * this method for setting lastVisitedProductNo.
	 * 
	 * @param lastVisitedProductNo
	 *            as request parameter.
	 */
	public void setLastVisitedProductNo(Integer lastVisitedProductNo) {
		this.lastVisitedProductNo = lastVisitedProductNo;
	}

	/**
	 * for getting parentCategoryId.
	 * 
	 * @return the parentCategoryId
	 */
	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * for setting parentCategoryId.
	 * 
	 * @param parentCategoryId
	 *            the parentCategoryId to set
	 */
	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * This method return latitude value.
	 * 
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * This method set the value to latitude.
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * This method return longitude value.
	 * 
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * This method set the value to longitude.
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * This method return addedTo value.
	 * 
	 * @return the addedTo
	 */
	public String getAddedTo() {
		return addedTo;
	}

	/**
	 * This method set the value to addedTo.
	 * 
	 * @param addedTo
	 *            the addedTo to set
	 */
	public void setAddedTo(String addedTo) {
		this.addedTo = addedTo;
	}

	/**
	 * To get isWishlstHistory.
	 * 
	 * @return the isWishlstHistory
	 */
	public Boolean getIsWishlstHistory() {
		return isWishlstHistory;
	}

	/**
	 * To set isWishlstHistory value.
	 * 
	 * @param isWishlstHistory
	 *            the isWishlstHistory to set
	 */
	public void setIsWishlstHistory(Boolean isWishlstHistory) {
		this.isWishlstHistory = isWishlstHistory;
	}

	/**
	 * for user tracking
	 * 
	 * @return mainMenuId
	 */
	public Integer getMainMenuId() {
		return mainMenuId;
	}

	/**
	 * for user tracking
	 * 
	 * @param mainMenuId
	 */
	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	/**
	 * for user tracking
	 * 
	 * @return prodListId
	 */
	public Integer getProdListId() {
		return prodListId;
	}

	/**
	 * for user tracking
	 * 
	 * @param prodListId
	 */
	public void setProdListId(Integer prodListId) {
		this.prodListId = prodListId;
	}

	/**
	 * for user tracking
	 * 
	 * @return saleListId
	 */
	public Integer getSaleListId() {
		return saleListId;
	}

	/**
	 * for user tracking
	 * 
	 * @param saleListId
	 */
	public void setSaleListId(Integer saleListId) {
		this.saleListId = saleListId;
	}

	/**
	 * for user tracking
	 * 
	 * @return scanId
	 */
	public Integer getScanId() {
		return scanId;
	}

	/**
	 * for user tracking
	 * 
	 * @param scanId
	 */
	public void setScanId(Integer scanId) {
		this.scanId = scanId;
	}

	/**
	 * @return retListId
	 */
	public Integer getRetListId() {
		return retListId;
	}

	/**
	 * @param retListId
	 */
	public void setRetListId(Integer retListId) {
		this.retListId = retListId;
	}

	public Integer getScanTypeId() {
		return scanTypeId;
	}

	public void setScanTypeId(Integer scanTypeId) {
		this.scanTypeId = scanTypeId;
	}

	public String getBusCatIds() {
		return busCatIds;
	}

	public void setBusCatIds(String busCatIds) {
		this.busCatIds = busCatIds;
	}

	public Integer getPopCentId() {
		return popCentId;
	}

	public void setPopCentId(Integer popCentId) {
		this.popCentId = popCentId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getCatIds() {
		return catIds;
	}

	public void setCatIds(String catIds) {
		this.catIds = catIds;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * @return the mItemId
	 */
	public Integer getmItemId() {
		return mItemId;
	}

	/**
	 * @param mItemId
	 *            the mItemId to set
	 */
	public void setmItemId(Integer mItemId) {
		this.mItemId = mItemId;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * @return the bottomBtnId
	 */
	public Integer getBottomBtnId() {
		return bottomBtnId;
	}

	/**
	 * @param bottomBtnId
	 *            the bottomBtnId to set
	 */
	public void setBottomBtnId(Integer bottomBtnId) {
		this.bottomBtnId = bottomBtnId;
	}

}
