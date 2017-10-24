package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;

public class MenuItem {
	/**
	 * 
	 */
	public Integer mItemId;
	/**
	 * 
	 */
	public Integer hubCitiId;
	/**
	 * 
	 */
	public Integer eventId;
	/**
	 * 
	 */
	public Integer templateId;
	/**
	 * 
	 */
	public String templateName;
	/**
	 * 
	 */
	public Integer level;
	/**
	 * 
	 */
	public Integer menuId;

	/**
	 * 
	 */
	public String mItemName;
	/**
	 * 
	 */
	public Integer linkTypeId;
	/**
	 * 
	 */
	public Integer linkId;
	/**
	 * 
	 */
	public String linkTypeName;
	/**
	 * 
	 */
	public Integer position;
	/**
	 * 
	 */
	public String mItemImg;

	/**
	 * 
	 */
	private List<BottomButton> arBottomBtnList;

	private Integer bottomBtnId;

	private Integer fundListId;

	private Integer userId;

	private String platform;

	private Integer mainMenuId;
	/**
	 * 
	 */
	private Integer gpsEnabled;  

	/**
	 * 
	 */
	private String mBkgrdColor;
	/**
	 * 
	 */
	private String mBkgrdImage;
	/**
	 * 
	 */
	private String mBtnColor;
	/**
	 * 
	 */
	private String mBtnFontColor;
	/**
	 * 
	 */
	private String smBkgrdColor;
	/**
	 * 
	 */
	private String smBkgrdImage;
	/**
	 * 
	 */
	private String smBtnColor;
	/**
	 * 
	 */
	private String smBtnFontColor;
	/**
	 * 
	 */
	private String mGrpBkgrdColor;
	/**
	 * 
	 */
	private String mGrpFntColor;
	/**
	 * 
	 */
	private String smGrpBkgrdColor;
	/**
	 * 
	 */
	private String smGrpFntColor;
	/**
	 * 
	 */
	private Integer lowerLimit;
	/**
	 * 
	 */
	private Integer catId;
	/**
	 * 
	 */
	private String catIds;
	/**
	 * 
	 */
	private String longitude;
	/**
	 * 
	 */
	private String latitude;
	/**
	 * 
	 */
	private String postalCode;
	/**
	 * 
	 */
	private String searchKey;
	/**
	 * 
	 */
	private String sortColumn;
	/**
	 * 
	 */
	private String sortOrder;
	/**
	 * 
	 */
	private String groupBy;
	/**
	 * 
	 */
	private String sortBy;
	/**
	 * 
	 */
	private String mItemTypeName;
	/**
	 * 
	 */
	private String departmentName;
	/**
	 * 
	 */
	private Integer departmentId;
	/**
	 * 
	 */
	private Integer mItemTypeId;
	/**
	 * Variable categoryId declared as Integer.
	 */
	private Integer categoryId;
	/**
	 * 
	 */
	private Integer mShapeId;
	/**
	 * 
	 */
	private String mShapeName;
	/**
	 * 
	 */
	private String mFontColor;
	/**
	 * 
	 */
	private String smFontColor;

	/**
	 * The retailerId property.
	 */

	private Integer retailId;

	/**
	 * The retailLocationID property.
	 */
	private Integer retailLocationId;

	/**
	 * 
	 */
	private String cityIds;
	/**
	 * 
	 */
	public Integer isRegApp;
	/**
	 * 
	 */
	private Integer fundId;

	/**
	 * 
	 */
	private Integer radius;

	/**
	 * 
	 */
	public String dateModified;
	/**
	 * 
	 */
	public String evtDate;
	/**
	 * 
	 */
	private Boolean levelFlag;
	/**
	 * 
	 */
	private String fName;
	/**
	 * @return the mItemId
	 */

	/**
	 * Variable for storing template background color
	 */
	private String templateBgColor;
	
	/**
	 * 
	 */
	public String mItemIPadImg;
	
	/**
	 * Variable for postalCode.
	 */
	private String userPostalCode;
	
	/**
	 * 
	 */
	public String listType;
	
	/**
	 * 
	 */
	public Integer evtTypeID;


	private Integer bandId;
	
	private Integer flag;

	public Integer getBandId() {
		return bandId;
	}

	public void setBandId(Integer bandId) {
		this.bandId = bandId;
	}

	/**
	 * 
	 * @return Template Background color
	 */
	public String getTemplateBgColor() {
		return templateBgColor;
	}

	/**
	 * 
	 * @param templateBgColor
	 *            to set
	 */
	public void setTemplateBgColor(String templateBgColor) {
		this.templateBgColor = templateBgColor;
	}

	public Integer getmItemId() {
		return mItemId;
	}

	/**
	 * @return the menuId
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * @return the mItemName
	 */
	public String getmItemName() {
		return mItemName;
	}

	/**
	 * @return the linkTypeId
	 */
	public Integer getLinkTypeId() {
		return linkTypeId;
	}

	public String getLinkTypeName() {
		return linkTypeName;
	}

	public void setLinkTypeName(String linkTypeName) {
		this.linkTypeName = linkTypeName;
	}

	/**
	 * @return the linkId
	 */
	public Integer getLinkId() {
		return linkId;
	}

	/**
	 * @return the position
	 */
	public Integer getPosition() {
		return position;
	}

	/**
	 * @return the mItemImg
	 */
	public String getmItemImg() {
		return mItemImg;
	}

	/**
	 * @param mItemId
	 *            the mItemId to set
	 */
	public void setmItemId(Integer mItemId) {
		this.mItemId = mItemId;
	}

	/**
	 * @param menuId
	 *            the menuId to set
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * @param mItemName
	 *            the mItemName to set
	 */
	public void setmItemName(String mItemName) {
		this.mItemName = mItemName;
	}

	/**
	 * @param linkTypeId
	 *            the linkTypeId to set
	 */
	public void setLinkTypeId(Integer linkTypeId) {
		this.linkTypeId = linkTypeId;
	}

	/**
	 * @param linkId
	 *            the linkId to set
	 */
	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * @param mItemImg
	 *            the mItemImg to set
	 */
	public void setmItemImg(String mItemImg) {
		if (null == mItemImg) {
			this.mItemImg = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.mItemImg = mItemImg;
		}
	}

	/**
	 * @return the hubCitiId
	 */
	public Integer getHubCitiId() {
		return hubCitiId;
	}

	/**
	 * @return the templateId
	 */
	public Integer getTemplateId() {
		return templateId;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param hubCitiId
	 *            the hubCitiId to set
	 */
	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * @param templateId
	 *            the templateId to set
	 */
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	/**
	 * @return the templateName
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName
	 *            the templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @param arBottomBtnList
	 *            the arBottomBtnList to set
	 */
	public List<BottomButton> getArBottomBtnList() {
		return arBottomBtnList;
	}

	/**
	 * @param arBottomBtnList
	 *            the arBottomBtnList to set
	 */
	public void setArBottomBtnList(List<BottomButton> arBottomBtnList) {
		this.arBottomBtnList = arBottomBtnList;
	}

	public Integer getBottomBtnId() {
		return bottomBtnId;
	}

	public void setBottomBtnId(Integer bottomBtnId) {
		this.bottomBtnId = bottomBtnId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	/**
	 * @return the mBkgrdColor
	 */
	public String getmBkgrdColor() {
		return mBkgrdColor;
	}

	/**
	 * @param mBkgrdColor
	 *            the mBkgrdColor to set
	 */
	public void setmBkgrdColor(String mBkgrdColor) {
		this.mBkgrdColor = mBkgrdColor;
	}

	/**
	 * @return the mBkgrdImage
	 */
	public String getmBkgrdImage() {
		return mBkgrdImage;
	}

	/**
	 * @param mBkgrdImage
	 *            the mBkgrdImage to set
	 */
	public void setmBkgrdImage(String mBkgrdImage) {
		this.mBkgrdImage = mBkgrdImage;
	}

	/**
	 * @return the mBtnColor
	 */
	public String getmBtnColor() {
		return mBtnColor;
	}

	/**
	 * @param mBtnColor
	 *            the mBtnColor to set
	 */
	public void setmBtnColor(String mBtnColor) {
		if (null != mBtnColor) {
			this.mBtnColor = mBtnColor;
		} else {
			this.mBtnColor = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * @return the mBtnFontColor
	 */
	public String getmBtnFontColor() {
		return mBtnFontColor;
	}

	/**
	 * @param mBtnFontColor
	 *            the mBtnFontColor to set
	 */
	public void setmBtnFontColor(String mBtnFontColor) {
		if (null != mBtnFontColor) {
			this.mBtnFontColor = mBtnFontColor;
		} else {
			this.mBtnFontColor = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * @return the smBkgrdColor
	 */
	public String getSmBkgrdColor() {
		return smBkgrdColor;
	}

	/**
	 * @param smBkgrdColor
	 *            the smBkgrdColor to set
	 */
	public void setSmBkgrdColor(String smBkgrdColor) {
		this.smBkgrdColor = smBkgrdColor;
	}

	/**
	 * @return the smBkgrdImage
	 */
	public String getSmBkgrdImage() {
		return smBkgrdImage;
	}

	/**
	 * @param smBkgrdImage
	 *            the smBkgrdImage to set
	 */
	public void setSmBkgrdImage(String smBkgrdImage) {
		this.smBkgrdImage = smBkgrdImage;
	}

	/**
	 * @return the smBtnColor
	 */
	public String getSmBtnColor() {
		return smBtnColor;
	}

	/**
	 * @param smBtnColor
	 *            the smBtnColor to set
	 */
	public void setSmBtnColor(String smBtnColor) {
		if (null != smBtnColor) {
			this.smBtnColor = smBtnColor;
		} else {
			this.smBtnColor = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * @return the smBtnFontColor
	 */
	public String getSmBtnFontColor() {
		return smBtnFontColor;
	}

	/**
	 * @param smBtnFontColor
	 *            the smBtnFontColor to set
	 */
	public void setSmBtnFontColor(String smBtnFontColor) {
		if (null != smBtnFontColor) {
			this.smBtnFontColor = smBtnFontColor;
		} else {
			this.smBtnFontColor = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public Integer getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the mItemTypeName
	 */
	public String getmItemTypeName() {
		return mItemTypeName;
	}

	/**
	 * @param mItemTypeName
	 *            the mItemTypeName to set
	 */
	public void setmItemTypeName(String mItemTypeName) {
		this.mItemTypeName = mItemTypeName;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName
	 *            the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getmItemTypeId() {
		return mItemTypeId;
	}

	public void setmItemTypeId(Integer mItemTypeId) {
		this.mItemTypeId = mItemTypeId;
	}

	/**
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the mShapeId
	 */
	public Integer getmShapeId() {
		return mShapeId;
	}

	/**
	 * @param mShapeId
	 *            the mShapeId to set
	 */
	public void setmShapeId(Integer mShapeId) {
		this.mShapeId = mShapeId;
	}

	/**
	 * @return the mShapeName
	 */
	public String getmShapeName() {
		return mShapeName;
	}

	/**
	 * @param mShapeName
	 *            the mShapeName to set
	 */
	public void setmShapeName(String mShapeName) {
		this.mShapeName = mShapeName;
	}

	/**
	 * @return the mGrpBkgrdColor
	 */
	public String getmGrpBkgrdColor() {
		return mGrpBkgrdColor;
	}

	/**
	 * @param mGrpBkgrdColor
	 *            the mGrpBkgrdColor to set
	 */
	public void setmGrpBkgrdColor(String mGrpBkgrdColor) {
		this.mGrpBkgrdColor = mGrpBkgrdColor;
	}

	/**
	 * @return the mGrpFntColor
	 */
	public String getmGrpFntColor() {
		return mGrpFntColor;
	}

	/**
	 * @param mGrpFntColor
	 *            the mGrpFntColor to set
	 */
	public void setmGrpFntColor(String mGrpFntColor) {
		this.mGrpFntColor = mGrpFntColor;
	}

	/**
	 * @return the smGrpBkgrdColor
	 */
	public String getSmGrpBkgrdColor() {
		return smGrpBkgrdColor;
	}

	/**
	 * @param smGrpBkgrdColor
	 *            the smGrpBkgrdColor to set
	 */
	public void setSmGrpBkgrdColor(String smGrpBkgrdColor) {
		this.smGrpBkgrdColor = smGrpBkgrdColor;
	}

	/**
	 * @return the smGrpFntColor
	 */
	public String getSmGrpFntColor() {
		return smGrpFntColor;
	}

	/**
	 * @param smGrpFntColor
	 *            the smGrpFntColor to set
	 */
	public void setSmGrpFntColor(String smGrpFntColor) {
		this.smGrpFntColor = smGrpFntColor;
	}

	/**
	 * @return the mFontColor
	 */
	public String getmFontColor() {
		return mFontColor;
	}

	/**
	 * @param mFontColor
	 *            the mFontColor to set
	 */
	public void setmFontColor(String mFontColor) {
		this.mFontColor = mFontColor;
	}

	/**
	 * @return the smFontColor
	 */
	public String getSmFontColor() {
		return smFontColor;
	}

	/**
	 * @param smFontColor
	 *            the smFontColor to set
	 */
	public void setSmFontColor(String smFontColor) {
		this.smFontColor = smFontColor;
	}

	/**
	 * @return the retailId
	 */
	public Integer getRetailId() {
		return retailId;
	}

	/**
	 * @return the retailLocationId
	 */
	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	/**
	 * @param retailId
	 *            the retailId to set
	 */
	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * @param retailLocationId
	 *            the retailLocationId to set
	 */
	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	/**
	 * @return the cityIds
	 */
	public String getCityIds() {
		return cityIds;
	}

	/**
	 * @param cityIds
	 *            the cityIds to set
	 */
	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	/**
	 * @return the isRegApp
	 */
	public Integer getIsRegApp() {
		return isRegApp;
	}

	/**
	 * @param isRegApp
	 *            the isRegApp to set
	 */
	public void setIsRegApp(Integer isRegApp) {
		this.isRegApp = isRegApp;
	}

	/**
	 * @return the fundId
	 */
	public Integer getFundId() {
		return fundId;
	}

	/**
	 * @param fundId
	 *            the fundId to set
	 */
	public void setFundId(Integer fundId) {
		this.fundId = fundId;
	}

	/**
	 * @return the dateModified
	 */
	public String getDateModified() {
		return dateModified;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @return the fundListId
	 */
	public Integer getFundListId() {
		return fundListId;
	}

	/**
	 * @param fundListId
	 *            the fundListId to set
	 */
	public void setFundListId(Integer fundListId) {
		this.fundListId = fundListId;
	}

	/**
	 * @return the radius
	 */
	public Integer getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	/**
	 * @return the levelFlag
	 */
	public Boolean getLevelFlag() {
		return levelFlag;
	}

	/**
	 * @param levelFlag
	 *            the levelFlag to set
	 */
	public void setLevelFlag(Boolean levelFlag) {
		this.levelFlag = levelFlag;
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
	 * @return the evtDate
	 */
	public String getEvtDate() {
		return evtDate;
	}

	/**
	 * @param evtDate
	 *            the evtDate to set
	 */
	public void setEvtDate(String evtDate) {
		this.evtDate = evtDate;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName
	 *            the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the catIds
	 */
	public String getCatIds() {
		return catIds;
	}

	/**
	 * @param catIds
	 *            the catIds to set
	 */
	public void setCatIds(String catIds) {
		this.catIds = catIds;
	}

	/**
	 * @return the mItemIPadImg
	 */
	public String getmItemIPadImg() {
		return mItemIPadImg;
	}

	public Integer getGpsEnabled() {
		return gpsEnabled;
	}

	public void setGpsEnabled(Integer gpsEnabled) {
		this.gpsEnabled = gpsEnabled;
	}

	/**
	 * @param mItemIPadImg the mItemIPadImg to set
	 */
	public void setmItemIPadImg(String mItemIPadImg) {
		this.mItemIPadImg = mItemIPadImg;
	}

	public String getUserPostalCode() {
		return userPostalCode;
	}

	public void setUserPostalCode(String userPostalCode) {
		this.userPostalCode = userPostalCode;
	}

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public Integer getEvtTypeID() {
		return evtTypeID;
	}

	public void setEvtTypeID(Integer evtTypeID) {
		this.evtTypeID = evtTypeID;
	}

	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}


}
