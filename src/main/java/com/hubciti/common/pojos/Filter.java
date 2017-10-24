package com.hubciti.common.pojos;

import java.math.BigDecimal;
import java.util.List;

public class Filter {
	private String responseCode;
	private String responseText;
	private String fHeader;
	private Integer userId;
	private Integer filterId;
	private String filterName;
	private String fCategory;
	private String fCategoryName;
	private String fCategoryHidden;
	private String filterOption;
	private String fValue;
	private String fValueHidden;
	private Integer fValueFlag;
	private Integer filterFlag;
	private String searchKey;
	private Integer lowerLimit;
	private String viewName;
	private String recordCount;
	private String removeMsg;
	private Integer rowNum;
	private Integer maxRowNum;
	private Integer fValueId;
	private String fValueName;
	
	private Integer retailId;
	private Integer retailLocationId;
	private Integer fundId;
	
	public Integer evtTypeID;

	private Integer bandID;
	
	/**
	 * Variable for requestedTime;
	 */
	private String requestedTime;
	/**
	 * 
	 * @return the RetailerID
	 */
	public Integer getRetailId() {
		return retailId;
	}
	/**
	 * 
	 * @param retailId to set
	 */
	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * 
	 * @return The Retailer Location ID 
	 */
	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	/**
	 *  
	 * @param retailLocationId to set
	 */
	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	/**
	 * 
	 * @return The Fund Id
	 */
	public Integer getFundId() {
		return fundId;
	}

	/**
	 * 
	 * @param fundId to set
	 */
	public void setFundId(Integer fundId) {
		this.fundId = fundId;
	}

	/**
	 * 
	 * Variable for BusinessID
	 * 
	 */
	private Integer businessId;
	
	/**
	 * Variable for Radius
	 */
	private Integer radius;
	
	/**
	 * variable for Zipcode 
	 */
	private String zipCode;
	
	private List<Filter> filterList;
	private List<CategoryInfo> categoryList;
	
	
	
	
	
	// These fields for retrieving FilterList
	/**
	 * variable for Latitude
	 */
	private BigDecimal latitude;

	/**
	 * Variable for Longitude
	 */
	private BigDecimal longitude;

	/**
	 * Variable for hubcitiId
	 */
	private Integer hubCitiId;

	/**
	 * variable for mItemId
	 */
	private Integer mItemId;

	/**
	 * Variable for bottomBtnId
	 */
	private Integer bottomBtnId;

	/**
	 * 
	 * Variable for BusinessSubCategoryID
	 * 
	 */
	private Integer bsubCatId;

	/**
	 * variable for calling SP and retrieving Filter list
	 */
	private String spType;
	
	/**
	 * variable for CityExperienceID
	 */
	private Integer citiExpId;
	
	/**
	 * Variable for CategoryID
	 */
	private String categoryId;
	
	/**
	 * Variable for ScreenName
	 */
	private String screenName;
	
	private String cityId;
	
	/**
	 * Variable for ModuleName selection
	 */
	private String moduleName;
	
	//Setters & Getters
	
	/**
	 * 
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 
	 * @param moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * 
	 * @return the radius
	 */
	public Integer getRadius() {
		return radius;
	}
	
	/**
	 * 
	 * @param radius to set
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}
	
	/**
	 * 
	 * @return the ZipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 
	 * @param The zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/**
	 * 
	 * @return the businesssubcategoryId
	 */
	public Integer getBusinessId() {
		return businessId;
	}

	/**
	 * 
	 * @param bsubCatId
	 *            the businesssubcategoryId
	 */
	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}
	
	/**
	 * 
	 * @return the CityID
	 */
	public String getCityId() {
		return cityId;
	}

	/**
	 * 
	 * @param The cityId to set 
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}


	/**
	 * 
	 * @return the CategoryID
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * 
	 * @param categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 
	 * @return the ScreenName
	 */
	public String getScreenName() {
		return screenName;
	}
	
	/**
	 * 
	 * @param screenName to set
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * 
	 * @return the Filter type
	 */
	public String getSpType() {
		return spType;
	}

	/**
	 * 
	 * @param type
	 *            the Filter type to set
	 */
	public void setSpType(String spType) {
		this.spType = spType;
	}

	/**
	 * 
	 * @return the latitude
	 */
	public BigDecimal getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	/**
	 * 
	 * @return the longitude
	 */
	public BigDecimal getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/**
	 * 
	 * @return the hubcitiId
	 */
	public Integer getHubCitiId() {
		return hubCitiId;
	}

	/**
	 * 
	 * @param hubCitiId
	 *            the hubcitiId to set
	 */
	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * 
	 * @return the menuItemId
	 */
	public Integer getmItemId() {
		return mItemId;
	}

	/**
	 * 
	 * @param mItemId
	 *            the MenuItemId to set
	 */
	public void setmItemId(Integer mItemId) {
		this.mItemId = mItemId;
	}

	/**
	 * 
	 * @return the bottonbuttonId
	 */
	public Integer getBottomBtnId() {
		return bottomBtnId;
	}

	/**
	 * 
	 * @param bottomBtnId
	 *            the bottonbuttonId to set
	 */
	public void setBottomBtnId(Integer bottomBtnId) {
		this.bottomBtnId = bottomBtnId;
	}

	/**
	 * 
	 * @return the businesssubcategoryId
	 */
	public Integer getBsubCatId() {
		return bsubCatId;
	}

	/**
	 * 
	 * @param bsubCatId
	 *            the businesssubcategoryId
	 */
	public void setBsubCatId(Integer bsubCatId) {
		this.bsubCatId = bsubCatId;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @return the responseText
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return the filterId
	 */
	public Integer getFilterId() {
		return filterId;
	}

	/**
	 * @return the filterName
	 */
	public String getFilterName() {
		return filterName;
	}

	/**
	 * @return the fCategory
	 */
	public String getfCategory() {
		return fCategory;
	}

	/**
	 * @return the fCategoryName
	 */
	public String getfCategoryName() {
		return fCategoryName;
	}

	/**
	 * @return the fCategoryHidden
	 */
	public String getfCategoryHidden() {
		return fCategoryHidden;
	}

	/**
	 * @return the filterOption
	 */
	public String getFilterOption() {
		return filterOption;
	}

	/**
	 * @return the fValue
	 */
	public String getfValue() {
		return fValue;
	}

	/**
	 * @return the fValueHidden
	 */
	public String getfValueHidden() {
		return fValueHidden;
	}

	/**
	 * @return the fValueFlag
	 */
	public Integer getfValueFlag() {
		return fValueFlag;
	}

	/**
	 * @return the filterFlag
	 */
	public Integer getFilterFlag() {
		return filterFlag;
	}

	/**
	 * @return the searchKey
	 */
	public String getSearchKey() {
		return searchKey;
	}

	/**
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * @return the viewName
	 */
	public String getViewName() {
		return viewName;
	}

	/**
	 * @return the recordCount
	 */
	public String getRecordCount() {
		return recordCount;
	}

	/**
	 * @return the removeMsg
	 */
	public String getRemoveMsg() {
		return removeMsg;
	}

	/**
	 * @return the rowNum
	 */
	public Integer getRowNum() {
		return rowNum;
	}

	/**
	 * @return the maxRowNum
	 */
	public Integer getMaxRowNum() {
		return maxRowNum;
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
	 * @return the filterList
	 */
	public List<Filter> getFilterList() {
		return filterList;
	}

	/**
	 * @return the categoryList
	 */
	public List<CategoryInfo> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @param responseText
	 *            the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @param filterId
	 *            the filterId to set
	 */
	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}

	/**
	 * @param filterName
	 *            the filterName to set
	 */
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	/**
	 * @param fCategory
	 *            the fCategory to set
	 */
	public void setfCategory(String fCategory) {
		this.fCategory = fCategory;
	}

	/**
	 * @param fCategoryName
	 *            the fCategoryName to set
	 */
	public void setfCategoryName(String fCategoryName) {
		this.fCategoryName = fCategoryName;
	}

	/**
	 * @param fCategoryHidden
	 *            the fCategoryHidden to set
	 */
	public void setfCategoryHidden(String fCategoryHidden) {
		this.fCategoryHidden = fCategoryHidden;
	}

	/**
	 * @param filterOption
	 *            the filterOption to set
	 */
	public void setFilterOption(String filterOption) {
		this.filterOption = filterOption;
	}

	/**
	 * @param fValue
	 *            the fValue to set
	 */
	public void setfValue(String fValue) {
		this.fValue = fValue;
	}

	/**
	 * @param fValueHidden
	 *            the fValueHidden to set
	 */
	public void setfValueHidden(String fValueHidden) {
		this.fValueHidden = fValueHidden;
	}

	/**
	 * @param fValueFlag
	 *            the fValueFlag to set
	 */
	public void setfValueFlag(Integer fValueFlag) {
		this.fValueFlag = fValueFlag;
	}

	/**
	 * @param filterFlag
	 *            the filterFlag to set
	 */
	public void setFilterFlag(Integer filterFlag) {
		this.filterFlag = filterFlag;
	}

	/**
	 * @param searchKey
	 *            the searchKey to set
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @param viewName
	 *            the viewName to set
	 */
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}

	/**
	 * @param recordCount
	 *            the recordCount to set
	 */
	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * @param removeMsg
	 *            the removeMsg to set
	 */
	public void setRemoveMsg(String removeMsg) {
		this.removeMsg = removeMsg;
	}

	/**
	 * @param rowNum
	 *            the rowNum to set
	 */
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @param maxRowNum
	 *            the maxRowNum to set
	 */
	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	/**
	 * @param fValueId
	 *            the fValueId to set
	 */
	public void setfValueId(Integer fValueId) {
		this.fValueId = fValueId;
	}

	/**
	 * @param fValueName
	 *            the fValueName to set
	 */
	public void setfValueName(String fValueName) {
		this.fValueName = fValueName;
	}

	/**
	 * @param filterList
	 *            the filterList to set
	 */
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}

	/**
	 * @param categoryList
	 *            the categoryList to set
	 */
	public void setCategoryList(List<CategoryInfo> categoryList) {
		this.categoryList = categoryList;
	}

	/**
	 * @return the fHeader
	 */
	public String getfHeader() {
		return fHeader;
	}

	/**
	 * @param fHeader
	 *            the fHeader to set
	 */
	public void setfHeader(String fHeader) {
		this.fHeader = fHeader;
	}

	/**
	 * @return the citiExpId
	 */
	public Integer getCitiExpId() {
		return citiExpId;
	}

	/**
	 * @param citiExpId the citiExpId to set
	 */
	public void setCitiExpId(Integer citiExpId) {
		this.citiExpId = citiExpId;
	}
	public Integer getEvtTypeID() {
		return evtTypeID;
	}
	public void setEvtTypeID(Integer evtTypeID) {
		this.evtTypeID = evtTypeID;
	}
	public Integer getBandID() {
		return bandID;
	}
	public void setBandID(Integer bandID) {
		this.bandID = bandID;
	}
	/**
	 * @return the requestedTime
	 */
	public String getRequestedTime() {
		return requestedTime;
	}
	/**
	 * @param requestedTime the requestedTime to set
	 */
	public void setRequestedTime(String requestedTime) {
		this.requestedTime = requestedTime;
	}
}
