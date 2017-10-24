/**
 * 
 */
package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * @author kumar_dodda
 */
public class Menu {
	/**
	 * 
	 */
	private String responseCode;
	/**
	 * 
	 */
	private String responseText;
	/**
	 * 
	 */
	public Integer menuId;
	/**
	 * 
	 */
	public Integer mTypeId;
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
	public Integer departFlag;
	/**
	 * 
	 */
	private String downLoadLink;
	/**
	 * 
	 */
	public Integer isTempChanged;
	/**
	 * 
	 */
	private String androidLink;

	private String retGroupImg;

	private String appIconImg;

	private Integer retAffCount;

	private Integer retAffId;

	private String retAffName;
	/**
	 * 
	 */
	private Integer isRegApp;
	/**
	 * 
	 */
	private Integer isNewTicker;
	private String hamburgerImg;
	
	private Boolean sideNaviPersonalizatn;
	/**
	 * @return the isNewTicker
	 */
	public Integer getIsNewTicker() {
		return isNewTicker;
	}

	/**
	 * @param isNewTicker the isNewTicker to set
	 */
	public void setIsNewTicker(Integer isNewTicker) {
		this.isNewTicker = isNewTicker;
	}

	/**
	 * 
	 */
	public Integer typeFlag;
	/**
	 * 
	 */
	public String dateCreated;
	/**
	 * 
	 */
	public String dateModified;
	/**
	 * 
	 */
	public Integer createdUserId;
	/**
	 * 
	 */
	public Integer modifiedUserId;
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
	public Integer position;
	/**
	 * 
	 */
	public String mItemName;
	/**
	 * 
	 */
	private Integer typeId;
	/**
	 * 
	 */
	private Integer departmentId;
	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 
	 */
	private String mItemImg;
	/**
	 * 
	 */
	private String mBannerImg;

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
	public String menuName;
	/**
	 * 
	 */
	private String mFontColor;
	/**
	 * 
	 */
	private String smFontColor;
	/**
	 * 
	 */
	private String smBtnColor;
	/**
	 * 
	 */
	private String smBtnFontColor;

	/**
	 * Variable for storing template background color
	 */
	private String templateBgColor;
	
	/**
	 * 
	 */
	private List<MainCategory> listMainCat;
	/**
	 * 
	 */
	private List<MenuItem> arMItemList;
	/**
	 * 
	 */
	private List<BottomButton> arBottomBtnList;
	
	/**
	 * 
	 */
	private List<Item> arItemList;

	/**
	 * 
	 */
	private String platform;

	/**
	 * 
	 */
	private String sortOrder;

	private List<MenuItem> mItemList;

	private String deviceId;
	
	private String devType;

	private String osVersion;

	private Integer noOfColumns;

	/**
	 * for sortColumn.
	 */
	private String sortColumn;

	/**
	 * for groupBy.
	 */
	private String groupBy;

	/**
	 * for cityIds.
	 */
	private String cityIds;

	private String noRecordMsg;
	
	/**
	 * 
	 */
	private String mItemIPadImg;
	/**
	 * 
	 */
	private String appVersion;
	/**
	 * 
	 */
	private Integer primaryDevice;
	
	private String TempBkgrdImg;
	private Integer isDisplayLabel;
	
	
	private String btnBkgrdColor;
	private String btnLblColor;

	private String bkImgPath;
	private String homeImgPath;
	private String titleTxtColor;
	private String titleBkGrdColor;
	
	private String pgnColorActive;
	private String pgnColorInActive;
	
	private String tickerTxtColor;
	private String tickerBkgrdColor;
	private String tickerDirection;
	private String tickerMode;
	
	

	

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

	/**
	 * @return the noRecordMsg
	 */
	public String getNoRecordMsg() {
		return noRecordMsg;
	}

	/**
	 * @param noRecordMsg
	 *            the noRecordMsg to set
	 */
	public void setNoRecordMsg(String noRecordMsg) {
		this.noRecordMsg = noRecordMsg;
	}

	/**
	 * @return the menuId
	 */
	public Integer getMenuId() {
		return menuId;
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
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @return the dateCreated
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * @return the dateModified
	 */
	public String getDateModified() {
		return dateModified;
	}

	/**
	 * @return the createdUserId
	 */
	public Integer getCreatedUserId() {
		return createdUserId;
	}

	/**
	 * @return the modifiedUserId
	 */
	public Integer getModifiedUserId() {
		return modifiedUserId;
	}

	/**
	 * @return the linkTypeId
	 */
	public Integer getLinkTypeId() {
		return linkTypeId;
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
	 * @return the mItemName
	 */
	public String getmItemName() {
		return mItemName;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param menuId
	 *            the menuId to set
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the mTypeId
	 */
	public Integer getmTypeId() {
		return mTypeId;
	}

	/**
	 * @return the mItemId
	 */
	public Integer getmItemId() {
		return mItemId;
	}

	/**
	 * @param mTypeId
	 *            the mTypeId to set
	 */
	public void setmTypeId(Integer mTypeId) {
		this.mTypeId = mTypeId;
	}

	/**
	 * @param mItemId
	 *            the mItemId to set
	 */
	public void setmItemId(Integer mItemId) {
		this.mItemId = mItemId;
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
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @param dateCreated
	 *            the dateCreated to set
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * @param dateModified
	 *            the dateModified to set
	 */
	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}

	/**
	 * @param createdUserId
	 *            the createdUserId to set
	 */
	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}

	/**
	 * @param modifiedUserId
	 *            the modifiedUserId to set
	 */
	public void setModifiedUserId(Integer modifiedUserId) {
		this.modifiedUserId = modifiedUserId;
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
	 * @param mItemName
	 *            the mItemName to set
	 */
	public void setmItemName(String mItemName) {
		this.mItemName = mItemName;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the mItemImg
	 */
	public String getmItemImg() {
		return mItemImg;
	}

	/**
	 * @return the arMItemList
	 */
	public List<MenuItem> getArMItemList() {
		return arMItemList;
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
	 * @param arMItemList
	 *            the arMItemList to set
	 */
	public void setArMItemList(List<MenuItem> arMItemList) {
		this.arMItemList = arMItemList;
	}

	/**
	 * @return the arBottomBtnList
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

	/**
	 * @return the mBannerImg
	 */
	public String getmBannerImg() {
		return mBannerImg;
	}

	/**
	 * @param mBannerImg
	 *            the mBannerImg to set
	 */
	public void setmBannerImg(String mBannerImg) {
		this.mBannerImg = mBannerImg;
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

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
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
		if (null != mBkgrdColor) {
			this.mBkgrdColor = mBkgrdColor;
		} else {
			this.mBkgrdColor = HubCitiConstants.NOTAPPLICABLE;
		}
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
		if (null != mBkgrdImage) {
			this.mBkgrdImage = mBkgrdImage;
		} else {
			this.mBkgrdImage = HubCitiConstants.NOTAPPLICABLE;
		}
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
		this.mBtnColor = mBtnColor;
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
		this.mBtnFontColor = mBtnFontColor;
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
		if (null != smBkgrdColor) {
			this.smBkgrdColor = smBkgrdColor;
		} else {
			this.smBkgrdColor = HubCitiConstants.NOTAPPLICABLE;
		}
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
		if (null != smBkgrdImage) {
			this.smBkgrdImage = smBkgrdImage;
		} else {
			this.smBkgrdImage = HubCitiConstants.NOTAPPLICABLE;
		}
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
		this.smBtnColor = smBtnColor;
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
		this.smBtnFontColor = smBtnFontColor;
	}

	/**
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the departFlag
	 */
	public Integer getDepartFlag() {
		return departFlag;
	}

	/**
	 * @param departFlag
	 *            the departFlag to set
	 */
	public void setDepartFlag(Integer departFlag) {
		this.departFlag = departFlag;
	}

	/**
	 * @return the typeFlag
	 */
	public Integer getTypeFlag() {
		return typeFlag;
	}

	/**
	 * @param typeFlag
	 *            the typeFlag to set
	 */
	public void setTypeFlag(Integer typeFlag) {
		this.typeFlag = typeFlag;
	}

	/**
	 * @return the downLoadLink
	 */
	public String getDownLoadLink() {
		return downLoadLink;
	}

	/**
	 * @param downLoadLink
	 *            the downLoadLink to set
	 */
	public void setDownLoadLink(String downLoadLink) {
		if ("".equals(Utility.checkNull(downLoadLink))) {
			this.downLoadLink = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.downLoadLink = downLoadLink;
		}
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public List<MenuItem> getmItemList() {
		return mItemList;
	}

	public void setmItemList(List<MenuItem> mItemList) {
		this.mItemList = mItemList;
	}

	public String getRetGroupImg() {
		return retGroupImg;
	}

	public void setRetGroupImg(String retGroupImg) {
		this.retGroupImg = retGroupImg;
	}

	/**
	 * @return the androidLink
	 */
	public String getAndroidLink() {
		return androidLink;
	}

	/**
	 * @param androidLink
	 *            the androidLink to set
	 */
	public void setAndroidLink(String androidLink) {
		if ("".equals(Utility.checkNull(androidLink))) {
			this.androidLink = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.androidLink = androidLink;
		}
	}

	public String getAppIconImg() {
		return appIconImg;
	}

	public void setAppIconImg(String appIconImg) {
		if (appIconImg != null) {
			this.appIconImg = appIconImg;
		} else {
			this.appIconImg = HubCitiConstants.IMAGENOTFOUND;
		}
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public Integer getNoOfColumns() {
		return noOfColumns;
	}

	public void setNoOfColumns(Integer noOfColumns) {
		this.noOfColumns = noOfColumns;
	}

	public Integer getRetAffCount() {
		return retAffCount;
	}

	public void setRetAffCount(Integer retAffCount) {
		this.retAffCount = retAffCount;
	}

	public Integer getRetAffId() {
		return retAffId;
	}

	public void setRetAffId(Integer retAffId) {
		this.retAffId = retAffId;
	}

	public String getRetAffName() {
		return retAffName;
	}

	public void setRetAffName(String retAffName) {
		this.retAffName = retAffName;
	}

	/**
	 * @return the menuName
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName
	 *            the menuName to set
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
	 * @return the sortColumn
	 */
	public String getSortColumn() {
		return sortColumn;
	}

	/**
	 * @param sortColumn
	 *            the sortColumn to set
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	/**
	 * @return the groupBy
	 */
	public String getGroupBy() {
		return groupBy;
	}

	/**
	 * @param groupBy
	 *            the groupBy to set
	 */
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
	 * @param cityIds
	 *            the cityIds to set
	 */
	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	/**
	 * @return the isTempChanged
	 */
	public Integer getIsTempChanged() {
		return isTempChanged;
	}

	/**
	 * @param isTempChanged
	 *            the isTempChanged to set
	 */
	public void setIsTempChanged(Integer isTempChanged) {
		this.isTempChanged = isTempChanged;
	}

	/**
	 * @return the mItemIPadImg
	 */
	public String getmItemIPadImg() {
		return mItemIPadImg;
	}

	/**
	 * @param mItemIPadImg the mItemIPadImg to set
	 */
	public void setmItemIPadImg(String mItemIPadImg) {
		this.mItemIPadImg = mItemIPadImg;
	}

	/**
	 * @return the devType
	 */
	public String getDevType() {
		return devType;
	}

	/**
	 * @param devType the devType to set
	 */
	public void setDevType(String devType) {
		this.devType = devType;
	}

	/**
	 * @return the appVersion
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * @param appVersion the appVersion to set
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * @return the primaryDevice
	 */
	public Integer getPrimaryDevice() {
		return primaryDevice;
	}

	/**
	 * @param primaryDevice the primaryDevice to set
	 */
	public void setPrimaryDevice(Integer primaryDevice) {
		this.primaryDevice = primaryDevice;
	}

	public String getTempBkgrdImg() {
		return TempBkgrdImg;
	}

	public void setTempBkgrdImg(String tempBkgrdImg) {
		TempBkgrdImg = tempBkgrdImg;
	}

	public Integer getIsDisplayLabel() {
		return isDisplayLabel;
	}

	public void setIsDisplayLabel(Integer isDisplayLabel) {
		this.isDisplayLabel = isDisplayLabel;
	}

	public String getBtnBkgrdColor() {
		return btnBkgrdColor;
	}

	public void setBtnBkgrdColor(String btnBkgrdColor) {
		this.btnBkgrdColor = btnBkgrdColor;
	}

	public String getBtnLblColor() {
		return btnLblColor;
	}

	public void setBtnLblColor(String btnLblColor) {
		this.btnLblColor = btnLblColor;
	}

	public String getBkImgPath() {
		return bkImgPath;
	}

	public void setBkImgPath(String bkImgPath) {
		this.bkImgPath = bkImgPath;
	}

	public String getHomeImgPath() {
		return homeImgPath;
	}

	public void setHomeImgPath(String homeImgPath) {
		this.homeImgPath = homeImgPath;
	}

	public String getTitleTxtColor() {
		return titleTxtColor;
	}

	public void setTitleTxtColor(String titleTxtColor) {
		this.titleTxtColor = titleTxtColor;
	}

	public String getTitleBkGrdColor() {
		return titleBkGrdColor;
	}

	public void setTitleBkGrdColor(String titleBkGrdColor) {
		this.titleBkGrdColor = titleBkGrdColor;
	}

	/**
	 * @return the pgnColorActive
	 */
	public String getPgnColorActive() {
		return pgnColorActive;
	}

	/**
	 * @param pgnColorActive the pgnColorActive to set
	 */
	public void setPgnColorActive(String pgnColorActive) {
		this.pgnColorActive = pgnColorActive;
	}

	/**
	 * @return the pgnColorInActive
	 */
	public String getPgnColorInActive() {
		return pgnColorInActive;
	}

	/**
	 * @param pgnColorInActive the pgnColorInActive to set
	 */
	public void setPgnColorInActive(String pgnColorInActive) {
		this.pgnColorInActive = pgnColorInActive;
	}

	/**
	 * @return the arItemList
	 */
	public List<Item> getArItemList() {
		return arItemList;
	}

	/**
	 * @param arItemList the arItemList to set
	 */
	public void setArItemList(List<Item> arItemList) {
		this.arItemList = arItemList;
	}

	/**
	 * @return the tickerTxtColor
	 */
	public String getTickerTxtColor() {
		return tickerTxtColor;
	}

	/**
	 * @param tickerTxtColor the tickerTxtColor to set
	 */
	public void setTickerTxtColor(String tickerTxtColor) {
		this.tickerTxtColor = tickerTxtColor;
	}

	/**
	 * @return the tickerBkgrdColor
	 */
	public String getTickerBkgrdColor() {
		return tickerBkgrdColor;
	}

	/**
	 * @param tickerBkgrdColor the tickerBkgrdColor to set
	 */
	public void setTickerBkgrdColor(String tickerBkgrdColor) {
		this.tickerBkgrdColor = tickerBkgrdColor;
	}

	/**
	 * @return the tickerDirection
	 */
	public String getTickerDirection() {
		return tickerDirection;
	}

	/**
	 * @param tickerDirection the tickerDirection to set
	 */
	public void setTickerDirection(String tickerDirection) {
		this.tickerDirection = tickerDirection;
	}

	/**
	 * @return the tickerMode
	 */
	public String getTickerMode() {
		return tickerMode;
	}

	/**
	 * @param tickerMode the tickerMode to set
	 */
	public void setTickerMode(String tickerMode) {
		this.tickerMode = tickerMode;
	}

	/**
	 * @return the hamburgerImg
	 */
	public String getHamburgerImg() {
		return hamburgerImg;
	}

	/**
	 * @param hamburgerImg the hamburgerImg to set
	 */
	public void setHamburgerImg(String hamburgerImg) {
		this.hamburgerImg = hamburgerImg;
	}

	/**
	 * @return the listMainCat
	 */
	public List<MainCategory> getListMainCat() {
		return listMainCat;
	}

	/**
	 * @param listMainCat the listMainCat to set
	 */
	public void setListMainCat(List<MainCategory> listMainCat) {
		this.listMainCat = listMainCat;
	}

	public Boolean getSideNaviPersonalizatn() {
		return sideNaviPersonalizatn;
	}

	public void setSideNaviPersonalizatn(Boolean sideNaviPersonalizatn) {
		this.sideNaviPersonalizatn = sideNaviPersonalizatn;
	}
}
