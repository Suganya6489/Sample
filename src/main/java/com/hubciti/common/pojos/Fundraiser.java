/**
* @author Kumar D
* @version 0.1
*
* Class to store the details of a Fundraiser.
*/
package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;


public class Fundraiser {
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
	private Integer bottomBtn;
	/**
	 * 
	 */
	/**
	 * Variable for AppSite Id
	 */
	private Integer appSiteId;
	
	/**
	 * variable for App Site Name
	 */
	private String appSiteName;
	
	private Integer fundId;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private String fundName;

	/**
	 * 
	 */
	private String retailName;
	/**
	 * 
	 */
	private String sDescription;
	/**
	 * 
	 */
	private String lDescription;
	/**
	 * 
	 */
	private Integer hubCitiId;
	/**
	 * 
	 */
	private String imgPath;
	/**
	 * 
	 */
	private String startDate;
	/**
	 * 
	 */
	private String sDate;
	/**
	 * 
	 */
	private String eDate;
	/**
	 * 
	 */
	private String endDate;
	/**
	 * 
	 */
	private Integer mItemExist;
	/**
	 * 
	 */
	private Integer retailId;
	/**
	 * 
	 */
	private String retailerName;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private Integer mainMenuId;
	/**
	 * 
	 */
	private Integer mItemId;
	/**
	 * 
	 */
	private Integer bottomBtnId;
	/**
	 * 
	 */
	private Integer isDeptFlag;
	/**
	 * 
	 */
	private String platform;
	/**
	 * 
	 */
	private Integer rowNum;
	/**
	 * 
	 */
	private Boolean isDateFormated;
	/**
	 * 
	 */
	private Integer maxCount;
	/**
	 * 
	 */
	private Integer nextPage;
	/**
	 * 
	 */
	private Integer maxRowNum;
	/**
	 * 
	 */
	private Integer fundListId;
	/**
	 * 
	 */
	private String sortColumn;
	/**
	 * 
	 */
	private String sortOrder;
	/**
	 * The retailLocationID property.
	 */
	private Integer retailLocationId;
	/**
	 * 
	 */
	private String sortBy;
	/**
	 * 
	 */
	private String groupBy;
	/**
	 */
	private String imagePath;
	/**
	 * 
	 */
	private String moreInfoURL;
	/**
	 * 
	 */
	private Integer isOnGoing;
	/**
	 * 
	 */
	private Integer isUserOutOfRange;
	/**
	 * 
	 */
	private Integer isAppSiteFlag;
	/**
	 * 
	 */
	private Integer isRetailLocationFlag;
	/**
	 * 
	 */
	private List<EventDetails> eventList;
	/**
	 * 
	 */
	private List<CategoryInfo> categoryList;
	/**
	 * 
	 */
	private List<Fundraiser> fundraiserList;
	/**
	 * 
	 */
	private String orgztnHosting;
	/**
	 * 
	 */
	private String moreInformation;
	/**
	 * 
	 */
	private String purchaseProducts;
	/**
	 * 
	 */
	private String currentLevel;
	/**
	 * 
	 */
	private List<BottomButton> bottomBtnList;
	/**
	 * 
	 */
	private String department;
	/**
	 * 
	 */
	private String fundGoal;
	/**
	 * 
	 */
	private String address;
	/**
	 * 
	 */
	private String address1;
	/**
	 * 
	 */
	private String completeAddress;
	/**
	 * 
	 */
	private String city;
	/**
	 * 
	 */
	private String state;
	/**
	 * 
	 */
	private String postalCode;
	/**
	 * 
	 */
	private Integer isEventFlag;
	/**
	 * 
	 */
	private Integer fundCatId;
	/**
	 * 
	 */
	private String fundCatIds;
	/**
	 * 
	 */
	private String fundCatName;
	/**
	 * 
	 */
	private String shareText;
	/**
	 * 
	 */
	private String qrUrl;
	
	/**
	 * Variable for user Identification
	 */
	private String userId;
	
	/**
	 * variable for storing latitude
	 */
	private String latitude;
	/**
	 * Variable for storing longitude
	 */
	private String longitude;
	
	/**
	 * variable for HubCitiAppSite Flag
	 */
	private Integer isHubAppFlag;
	/**
	 * Variable for Retailer AppSite Flag 
	 */
	private Integer isRetAppFlag;
	
	
	/**
	 * 
	 * @return the HubcitiAppSite Flag
	 */
	public Integer getIsHubAppFlag() {
		return isHubAppFlag;
	}

	/**
	 * 
	 * @param isHubAppFlag to set
	 */
	public void setIsHubAppFlag(Integer isHubAppFlag) {
		this.isHubAppFlag = isHubAppFlag;
	}
	
	/**
	 * 
	 * @return the Retailer AppSite Flag
	 */
	public Integer getIsRetAppFlag() {
		return isRetAppFlag;
	}
	
	/**
	 * 
	 * @param isRetAppFlag to set
	 */
	public void setIsRetAppFlag(Integer isRetAppFlag) {
		this.isRetAppFlag = isRetAppFlag;
	}

	/**
	 * 
	 * @return to get App site Id
	 */
	public Integer getAppSiteId() {
		return appSiteId;
	}

	/**
	 * 
	 * @param appSiteId to set
	 */
	public void setAppSiteId(Integer appSiteId) {
		this.appSiteId = appSiteId;
	}

	/**
	 * 	
	 * @return to get App Site name
	 */
	public String getAppSiteName() {
		return appSiteName;
	}
	
	/**
	 * 
	 * @param appSiteName to set 
	 */
	public void setAppSiteName(String appSiteName) {
		this.appSiteName = appSiteName;
	}

	/**
	 * 
	 * @return to gte latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @param The latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 
	 * @return to get langitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @param The longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * 
	 * @return to get user Id 
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param The userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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


	public String getsDescription() {
		return sDescription;
	}

	public void setsDescription(String sDescription) {
		if ( null != sDescription && !sDescription.contains("<![CDATA[")) {
			this.sDescription = "<![CDATA[" + sDescription + "]]>";
		} else {
			this.sDescription = sDescription;
		}
	}

	public String getlDescription() {
		return lDescription;
	}

	public void setlDescription(String lDescription) {
		if ( null != lDescription && !lDescription.contains("<![CDATA[")) {
			this.lDescription = "<![CDATA[" + lDescription + "]]>";
		} else {
			this.lDescription = lDescription;
		}
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		if ( null == startDate || "".equals(startDate)) {
			this.startDate = HubCitiConstants.NOTAPPLICABLE;
		} else if (getIsDateFormated() != null && getIsDateFormated() == false) {
			this.startDate = Utility.convertDBdate(startDate);
		} else {
			this.startDate = startDate;
		}
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if ( null == endDate || "".equals(endDate)) {
			this.endDate = HubCitiConstants.NOTAPPLICABLE;
		} else if (getIsDateFormated() != null && getIsDateFormated() == false) {
			this.endDate = Utility.convertDBdate(endDate);
		} else {
			this.endDate = endDate;
		}
	}


	public Integer getmItemExist() {
		return mItemExist;
	}

	public void setmItemExist(Integer mItemExist) {
		this.mItemExist = mItemExist;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

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

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Boolean getIsDateFormated() {
		return isDateFormated;
	}

	public void setIsDateFormated(Boolean isDateFormated) {
		this.isDateFormated = isDateFormated;
	}

	public Integer getMaxCount() {
		return maxCount;
	}

	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	public List<EventDetails> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDetails> eventList) {
		this.eventList = eventList;
	}


	public List<CategoryInfo> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryInfo> categoryList) {
		this.categoryList = categoryList;
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

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	/**
	 * @return the retailLocationId
	 */
	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	/**
	 * @param retailLocationId
	 *            the retailLocationId to set
	 */
	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}


	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		if ("".equals(Utility.checkNull(imagePath))) {
			this.imagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.imagePath = imagePath;
		}
	}

	/**
	 * @return the retailerName
	 */
	public String getRetailerName() {
		return retailerName;
	}

	/**
	 * @param retailerName
	 *            the retailerName to set
	 */
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	/**
	 * @return the retailId
	 */
	public Integer getRetailId() {
		return retailId;
	}

	/**
	 * @param retailId
	 *            the retailId to set
	 */
	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * @return the retailName
	 */
	public String getRetailName() {
		return retailName;
	}

	/**
	 * @param retailName
	 *            the retailName to set
	 */
	public void setRetailName(String retailName) {
		this.retailName = retailName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		if ("".equals(Utility.checkNull(description))) {
			this.description = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.description = description;
		}
	}


	public String getMoreInfoURL() {
		return moreInfoURL;
	}

	public void setMoreInfoURL(String moreInfoURL) {
		this.moreInfoURL = moreInfoURL;
	}

	/**
	 * @return the isOnGoing
	 */
	public Integer getIsOnGoing() {
		return isOnGoing;
	}

	/**
	 * @param isOnGoing
	 *            the isOnGoing to set
	 */
	public void setIsOnGoing(Integer isOnGoing) {
		this.isOnGoing = isOnGoing;
	}



	public Integer getIsAppSiteFlag() {
		return isAppSiteFlag;
	}

	public void setIsAppSiteFlag(Integer isAppSiteFlag) {
		this.isAppSiteFlag = isAppSiteFlag;
	}

	/**
	 * @return the bottomBtnList
	 */
	public List<BottomButton> getBottomBtnList() {
		return bottomBtnList;
	}

	/**
	 * @param bottomBtnList
	 *            the bottomBtnList to set
	 */
	public void setBottomBtnList(List<BottomButton> bottomBtnList) {
		this.bottomBtnList = bottomBtnList;
	}

	/**
	 * @return the bottomBtn
	 */
	public Integer getBottomBtn() {
		return bottomBtn;
	}

	/**
	 * @param bottomBtn the bottomBtn to set
	 */
	public void setBottomBtn(Integer bottomBtn) {
		this.bottomBtn = bottomBtn;
	}

	/**
	 * @return the isRetailLocationFlag
	 */
	public Integer getIsRetailLocationFlag() {
		return isRetailLocationFlag;
	}

	/**
	 * @param isRetailLocationFlag the isRetailLocationFlag to set
	 */
	public void setIsRetailLocationFlag(Integer isRetailLocationFlag) {
		this.isRetailLocationFlag = isRetailLocationFlag;
	}

	/**
	 * @return the isUserOutOfRange
	 */
	public Integer getIsUserOutOfRange() {
		return isUserOutOfRange;
	}

	/**
	 * @param isUserOutOfRange the isUserOutOfRange to set
	 */
	public void setIsUserOutOfRange(Integer isUserOutOfRange) {
		this.isUserOutOfRange = isUserOutOfRange;
	}

	/**
	 * @return the fundraiserId
	 */
	public Integer getFundId() {
		return fundId;
	}

	/**
	 * @return the fundraiserName
	 */
	public String getFundName() {
		return fundName;
	}

	/**
	 * @return the fundCatId
	 */
	public Integer getFundCatId() {
		return fundCatId;
	}

	/**
	 * @return the fundCatIds
	 */
	public String getFundCatIds() {
		return fundCatIds;
	}

	/**
	 * @return the fundCatName
	 */
	public String getFundCatName() {
		return fundCatName;
	}

	/**
	 * @return the fundListId
	 */
	public Integer getFundListId() {
		return fundListId;
	}

	/**
	 * @return the fundraiserList
	 */
	public List<Fundraiser> getFundraiserList() {
		return fundraiserList;
	}

	/**
	 * @return the organizationHosting
	 */
	public String getOrgztnHosting() {
		return orgztnHosting;
	}

	/**
	 * @return the moreInformation
	 */
	public String getMoreInformation() {
		return moreInformation;
	}

	/**
	 * @return the purchaseProducts
	 */
	public String getPurchaseProducts() {
		return purchaseProducts;
	}

	/**
	 * @return the currentLevel
	 */
	public String getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * @param fundraiserId the fundraiserId to set
	 */
	public void setFundraiserId(Integer fundId) {
		this.fundId = fundId;
	}

	/**
	 * @param fundraiserName the fundraiserName to set
	 */
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}

	/**
	 * @param fundCatId the fundCatId to set
	 */
	public void setFundCatId(Integer fundCatId) {
		this.fundCatId = fundCatId;
	}

	/**
	 * @param fundCatIds the fundCatIds to set
	 */
	public void setFundCatIds(String fundCatIds) {
		this.fundCatIds = fundCatIds;
	}

	/**
	 * @param fundCatName the fundCatName to set
	 */
	public void setFundCatName(String fundCatName) {
		this.fundCatName = fundCatName;
	}

	/**
	 * @param fundListId the fundListId to set
	 */
	public void setFundListId(Integer fundListId) {
		this.fundListId = fundListId;
	}

	/**
	 * @param fundraiserList the fundraiserList to set
	 */
	public void setFundraiserList(List<Fundraiser> fundraiserList) {
		this.fundraiserList = fundraiserList;
	}

	/**
	 * @param organizationHosting the organizationHosting to set
	 */
	public void setOrganizationHosting(String orgztnHosting) {
		this.orgztnHosting = orgztnHosting;
	}

	/**
	 * @param moreInformation the moreInformation to set
	 */
	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}

	/**
	 * @param purchaseProducts the purchaseProducts to set
	 */
	public void setPurchaseProducts(String purchaseProducts) {
		this.purchaseProducts = purchaseProducts;
	}

	/**
	 * @param currentLevel the currentLevel to set
	 */
	public void setCurrentLevel(String currentLevel) {
		this.currentLevel = currentLevel;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the fundGoal
	 */
	public String getFundGoal() {
		return fundGoal;
	}

	/**
	 * @param fundGoal the fundGoal to set
	 */
	public void setFundGoal(String fundGoal) {
		this.fundGoal = fundGoal;
	}

	/**
	 * @param fundId the fundId to set
	 */
	public void setFundId(Integer fundId) {
		this.fundId = fundId;
	}

	/**
	 * @return the isEventFlag
	 */
	public Integer getIsEventFlag() {
		return isEventFlag;
	}

	/**
	 * @param isEventFlag the isEventFlag to set
	 */
	public void setIsEventFlag(Integer isEventFlag) {
		this.isEventFlag = isEventFlag;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @param orgztnHosting the orgztnHosting to set
	 */
	public void setOrgztnHosting(String orgztnHosting) {
		this.orgztnHosting = orgztnHosting;
	}

	/**
	 * @return the completeAddress
	 */
	public String getCompleteAddress() {
		return completeAddress;
	}

	/**
	 * @param completeAddress the completeAddress to set
	 */
	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the isDeptFlag
	 */
	public Integer getIsDeptFlag() {
		return isDeptFlag;
	}

	/**
	 * @param isDeptFlag the isDeptFlag to set
	 */
	public void setIsDeptFlag(Integer isDeptFlag) {
		this.isDeptFlag = isDeptFlag;
	}

	/**
	 * @return the sDate
	 */
	public String getsDate() {
		return sDate;
	}

	/**
	 * @param sDate the sDate to set
	 */
	public void setsDate(String sDate) {
		if ("".equals(Utility.checkNull(sDate))) {
			this.sDate = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.sDate = sDate;
		}
	}

	/**
	 * @return the eDate
	 */
	public String geteDate() {
		return eDate;
	}

	/**
	 * @param eDate the eDate to set
	 */
	public void seteDate(String eDate) {
		if ("".equals(Utility.checkNull(eDate))) {
			this.eDate = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.eDate = eDate;
		}
	}

	/**
	 * @return the shareText
	 */
	public String getShareText() {
		return shareText;
	}

	/**
	 * @param shareText the shareText to set
	 */
	public void setShareText(String shareText) {
		this.shareText = shareText;
	}

	/**
	 * @return the qrUrl
	 */
	public String getQrUrl() {
		return qrUrl;
	}

	/**
	 * @param qrUrl the qrUrl to set
	 */
	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}

}
