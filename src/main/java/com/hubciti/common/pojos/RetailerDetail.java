package com.hubciti.common.pojos;

import java.text.DecimalFormat;
import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

public class RetailerDetail {

	private String responseCode;

	private String responseText;
	/**
	 * The rowNumber property.
	 */
	private Integer rowNumber;

	/**
	 * The retailerId property.
	 */

	private Integer retailerId;
	/**
	 * The couponId property.
	 */

	private Integer couponId;
	/**
	 * The cateId property.
	 */

	private Integer cateId;

	private Integer linkId;

	private String latitude;

	private String longitude;

	private String pageTempLink;

	private String fValueId;

	private String filterId;
	
	
	private Boolean isFeatured;
	private Boolean isNonFeatured;
	
	private String lCount;
	
	private String distanceTxt;

	private String bandName;
	private String bandImgPath;
	private Integer bandID;
	private String bandURL;
	private String bandURLImgPath;
	
	private String sDate;
	private String eDate;
	private String sTime;
	private String eTime;
	private String bEvtName;
	private Integer bEvtId;
	
	
	private Integer claimExist;
	
	private String claimTxtMsg;
	private String claimCategory;
	
	private Integer retailId;
	
	private String couponName;
	
	private String couponImagePath;
	
	private String bannerTitle;
	
	private Integer counts;
	
	private Integer retId;
	
	private String retName;
	
	private String type;
	
	private String label;
	/**
	 * @return the claimCategory
	 */
	public String getClaimCategory() {
		return claimCategory;
	}

	/**
	 * @param claimCategory the claimCategory to set
	 */
	public void setClaimCategory(String claimCategory) {
		this.claimCategory = claimCategory;
	}

	private String claimCategoryURL;

	/**
	 * @return the claimCategoryURL
	 */
	public String getClaimCategoryURL() {
		return claimCategoryURL;
	}

	/**
	 * @param claimCategoryURL the claimCategoryURL to set
	 */
	public void setClaimCategoryURL(String claimCategoryURL) {
		this.claimCategoryURL = claimCategoryURL;
	}

	/**
	 * @return the cateId
	 */
	public Integer getCateId() {
		return cateId;
	}

	/**
	 * @param cateId
	 *            the cateId to set
	 */
	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	/**
	 * variable for Max Count
	 */
	private Integer maxCount;

	/**
	 * Variable for cheching page
	 */
	private Boolean nextPageFlag;

	/**
	 * declared for category name
	 */
	private String cateName;

	/**
	 * The retailerName property.
	 */

	private String retailerName;

	/**
	 * The retailLocationID property.
	 */
	private Integer retailLocationId;

	/**
	 * Variable for Retailer Address1.
	 */
	private String retaileraddress1;


	/**
	 * Variable for Retailer Address2.
	 */

	private String retaileraddress2;

	/**
	 * Variable for Retailer Address3.
	 */
	private String retaileraddress3;

	/**
	 * Variable for Retailer Address4.
	 */
	private String retaileraddress4;

	/**
	 * Variable for City.
	 */
	private String city;

	/**
	 * Variable for state.
	 */
	private String state;

	/**
	 * Variable for postalCode.
	 */
	private String postalCode;
	/**
	 * Variable for distance.
	 */
	private String distance;
	/**
	 * Variable for completeAddress.
	 */
	private String completeAddress;
	/**
	 * Variable for retailAddress.
	 */
	private String retailAddress;

	/**
	 * The imagePath property.
	 */

	private String logoImagePath;
	/**
	 * The imagePath property.
	 */

	private String retailImgPath;

	/**
	 * The bannerAdImagePath property.
	 */

	private String bannerAdImagePath;

	/**
	 * The ribbonAdImagePath property.
	 */

	private String ribbonAdImagePath;

	/**
	 * The ribbonAdURL property.
	 */

	private String ribbonAdURL;

	/**
	 * The advertisementID property.
	 */

	private String advertisementId;

	/**
	 * FOR RetailLocationLatitude
	 */
	private String retLatitude;
	/**
	 * for retailer longitude.
	 */
	private String retLongitude;

	private Integer userId;

	private Integer scanTypeId;

	private Integer mainMenuId;

	/**
	 * for ribben ad url
	 */
	private String splashAdId;

	/**
	 * For retailer details ID
	 */
	private Integer retDetId;

	/**
	 * for contact phone
	 */
	private String contactPhone;
	private String phoneImg;

	private String mail;
	private String mailImg;
	/**
	 * for retailer url
	 */
	private String retailerURL;
	private String retURLImg;
	/**
	 * for retailer image;
	 */
	private String image;

	private String retailerPageQRURL;

	private Integer banAdId;

	private Integer ribAdImgPath;

	private Boolean specialOffFlag;
	private Boolean hotDealFlag;
	private Boolean clrFlag;
	private Boolean saleFlag;

	private Integer rowNum;
	/**
	 * 
	 */
	private Integer specialsListId;
	/**
	 * 
	 */
	private Integer pageId;
	/**
	 * 
	 */
	private String pageIds;
	/**
	 * 
	 */
	private String pageTitle;
	/**
	 * 
	 */
	private String sDescription;
	/**
	 * 
	 */
	private String pageLink;
	/**
	 * 
	 */
	private Integer extLinkFlag;
	/**
	 * 
	 */
	private String externalLink;

	/**
	 * 
	 */
	private Integer hubCitiId;
	/**
	 * 
	 */
	private String pageDescription;
	/**
	 * 
	 */
	private String longDescription;
	/**
	 * 
	 */
	private String imageName;
	/**
	 * 
	 */
	private String mediaName;
	/**
	 * 
	 */
	private String mediaPath;
	/**
	 * 
	 */
	private String qrType;
	/**
	 * For expired
	 */
	private Integer expired;
	/**
	 * 
	 */
	private String startDate;

	private Integer lastVisitedNo;

	/**
	 * For retailer list ID
	 */
	private Integer retListId;

	/**
	 * For retailer group button image path
	 */
	private String retGroupImg;

	private String searchKey;

	private String catName;
	/**
	 * 
	 */
	private String couponListId;
	/**
	 * 
	 */
	private String pathName;
	/**
	 * 
	 */
	private String pathType;
	/**
	 * 
	 */
	private String imageIconId;

	/**
	 * The salePrice declared as string.
	 */
	private String salePrice;

	private String onProdDetId;

	/**
	 * for online url
	 */
	private String retailOnlineURL;

	private String price;

	/**
	 * for online BuyURL
	 */
	private String buyURL;

	/**
	 * for online ShipmentCost
	 */
	private String shipmentCost;

	private Integer mItemId;

	private Integer bottomBtnId;

	private String platform;
	/**
	 * For rating.
	 */
	private String rating;

	private Integer appSiteId;

	private String appSiteName;

	private Integer ribbonAdId;

	private String appDownloadLink;

	private Integer radius;

	private Integer subCatId;
	private String subCatIds;

	private String sortOrder;

	private String sortColumn;

	private String sortBy;

	private String groupBy;

	private String subCatName;

	private String maxRowNum;

	private Integer catId;
	/**
	 * 
	 */
	private String cityIds;

	private String specialNames;

	private Integer locSpecials;

	private String interests;

	/**
	 * Variable for Short Description
	 */
	private String shortDesc;

	/**
	 * Variable for long description
	 */
	private String longDesc;

	/**
	 * Variable for Retailer Image Path
	 */
	private String retImagePath;

	/**
	 * Variable for LoworLimit
	 */
	private Integer lowerLimit;

	/**
	 * Variable for postalCode.
	 */
	private String userPostalCode;
	
	/**
	 * 
	 */
	private String catIds;
	
	


	/**
	 * Variable for RetailerDetailList
	 */
	private List<RetailerDetail> retDetailList;

	/**
	 * Variable for External Flag
	 */
	private Boolean extFlag;
	
	/**
	 * Variable for requestedTime;
	 */
	private String requestedTime;
	
	
	/**
	 * Variable for requestedTime;
	 */
	private String locationOpen;
	
	
	private Integer isFeatOrNonFeat;
	
	
	private String couponIds;
	
	private String location;


	/**
	 * 
	 * @return the external flag
	 */
	public Boolean getExtFlag() {
		return extFlag;
	}

	/**
	 * 
	 * @param extFlag
	 *            to set
	 */
	public void setExtFlag(Boolean extFlag) {
		this.extFlag = extFlag;
	}

	/**
	 * 
	 * @return the retailerDetail
	 */

	public List<RetailerDetail> getRetDetailList() {
		return retDetailList;
	}

	/**
	 * 
	 * @return the short description
	 */
	public String getShortDesc() {
		return shortDesc;
	}

	/**
	 * 
	 * @param shortDesc to set
	 */
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	/**
	 * 
	 * @return the LongDescription
	 */
	public String getLongDesc() {
		return longDesc;
	}

	/**
	 * 
	 * @param longDesc to set
	 */
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	/**
	 * 
	 * @param retailerDetail
	 *            to set
	 */
	public void setRetDetailList(List<RetailerDetail> retailerDetail) {
		this.retDetailList = retailerDetail;
	}

	/**
	 * 
	 * @return The NextPageFlag
	 */
	public Boolean getNextPageFlag() {
		return nextPageFlag;
	}

	/**
	 * 
	 * @param nextPageFlag
	 *            to Set
	 * 
	 */
	public void setNextPageFlag(Boolean nextPageFlag) {
		this.nextPageFlag = nextPageFlag;
	}

	/**
	 * 
	 * @return the max Count
	 */
	public Integer getMaxCount() {
		return maxCount;
	}

	/**
	 * 
	 * @param maxCount
	 *            to set
	 */
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	/**
	 * 
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * 
	 * @param lowerLimit
	 *            to set.
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * 
	 * @return The retailerImagePath
	 */
	public String getRetImagePath() {
		return retImagePath;
	}

	/**
	 * 
	 * @param retImagePath
	 *            to set
	 */
	public void setRetImagePath(String retImagePath) {
		this.retImagePath = retImagePath;
	}

	/**
	 * @return the pageDescription
	 */
	public String getPageDescription() {
		return pageDescription;
	}

	/**
	 * @param pageDescription
	 *            the pageDescription to set
	 */
	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription
	 *            the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		longDescription = Utility.removeHTMLTags(longDescription);
		if ("".equals(Utility.checkNull(longDescription))) {
			this.longDescription = HubCitiConstants.NOTAPPLICABLE;
		} else if (longDescription.contains("<![CDATA[")) {
			this.longDescription = longDescription;
		} else {
			this.longDescription = "<![CDATA[" + longDescription + "]]>";
		}
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName
	 *            the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the mediaName
	 */
	public String getMediaName() {
		return mediaName;
	}

	/**
	 * @param mediaName
	 *            the mediaName to set
	 */
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	/**
	 * @return the mediaPath
	 */
	public String getMediaPath() {
		return mediaPath;
	}

	/**
	 * @param mediaPath
	 *            the mediaPath to set
	 */
	public void setMediaPath(String mediaPath) {
		if ("".equals(Utility.checkNull(mediaPath))) {
			this.mediaPath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.mediaPath = mediaPath;
		}
	}

	/**
	 * @return the qrType
	 */
	public String getQrType() {
		return qrType;
	}

	/**
	 * @param qrType
	 *            the qrType to set
	 */
	public void setQrType(String qrType) {
		this.qrType = qrType;
	}

	/**
	 * @return the expired
	 */
	public Integer getExpired() {
		return expired;
	}

	/**
	 * @param expired
	 *            the expired to set
	 */
	public void setExpired(Integer expired) {
		this.expired = expired;
	}

	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(String startDate) {

		if (null != startDate && startDate.contains("th at")) {
			this.startDate = startDate;
		} else if (null != startDate) {
			this.startDate = startDate;
			this.startDate = Utility.convertDBdate(startDate);
		} else {
			this.startDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(String endDate) {

		if (null != endDate && endDate.contains("th at")) {

			this.endDate = endDate;
		} else if (null != endDate) {
			this.endDate = Utility.convertDBdate(endDate);
		} else {
			this.endDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * 
	 */
	private String endDate;

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	public String getRetaileraddress1() {
		return retaileraddress1;
	}

	public void setRetaileraddress1(String retaileraddress1) {
		if (null == retaileraddress1) {
			this.retaileraddress1 = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retaileraddress1 = retaileraddress1;
		}
	}

	public String getRetaileraddress2() {
		return retaileraddress2;
	}

	public void setRetaileraddress2(String retaileraddress2) {
		if (null == retaileraddress2) {
			this.retaileraddress2 = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retaileraddress2 = retaileraddress2;
		}
	}

	public String getRetaileraddress3() {
		return retaileraddress3;
	}

	public void setRetaileraddress3(String retaileraddress3) {
		if (null == retaileraddress3) {
			this.retaileraddress3 = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retaileraddress3 = retaileraddress3;
		}
	}

	public String getRetaileraddress4() {
		return retaileraddress4;
	}

	public void setRetaileraddress4(String retaileraddress4) {
		if (null == retaileraddress4) {
			this.retaileraddress4 = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retaileraddress4 = retaileraddress4;
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		if ("".equals(Utility.checkNull(distance))) {
			this.distance = HubCitiConstants.NOTAPPLICABLE;
		} else if (!distance.contains(HubCitiConstants.NOTAPPLICABLE) && !distance.contains("mi")) {
			DecimalFormat forTwo = new DecimalFormat("#.##");
			this.distance = (forTwo.format(Float.valueOf(distance))).toString()
					+ " mi";
		} else {
			this.distance = distance;
		}
	}

	public String getCompleteAddress() {
		return completeAddress;
	}

	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}

	public String getRetailAddress() {
		return retailAddress;
	}

	public void setRetailAddress(String retailAddress) {
		if (retaileraddress1 != null) {
			completeAddress = retaileraddress1 + ",";
		}
		if (city != null) {
			completeAddress = completeAddress + city + ",";
		}
		if (postalCode != null && !HubCitiConstants.NOTAPPLICABLE.equals(postalCode)) {
			completeAddress = completeAddress + postalCode + ",";
		}
		if (state != null) {
			completeAddress = completeAddress + state;
		}
		this.retailAddress = completeAddress;
	}

	public String getLogoImagePath() {
		return logoImagePath;
	}

	public void setLogoImagePath(String logoImagePath) {
		if (logoImagePath == null || "".equals(logoImagePath)) {
			this.logoImagePath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.logoImagePath = logoImagePath;
		}
	}

	public String getBannerAdImagePath() {
		return bannerAdImagePath;
	}

	public void setBannerAdImagePath(String bannerAdImagePath) {
		if (bannerAdImagePath == null || "".equals(bannerAdImagePath)) {
			this.bannerAdImagePath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.bannerAdImagePath = bannerAdImagePath;
		}
	}

	public String getRibbonAdImagePath() {
		return ribbonAdImagePath;
	}

	public void setRibbonAdImagePath(String ribbonAdImagePath) {
		if (ribbonAdImagePath == null || "".equals(ribbonAdImagePath)) {
			this.ribbonAdImagePath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.ribbonAdImagePath = ribbonAdImagePath;
		}
	}

	public String getRibbonAdURL() {
		return ribbonAdURL;
	}

	public void setRibbonAdURL(String ribbonAdURL) {
		if (ribbonAdURL == null || "".equals(ribbonAdURL)) {
			this.ribbonAdURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.ribbonAdURL = ribbonAdURL;
		}
	}

	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}

	public Boolean getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(Boolean saleFlag) {
		this.saleFlag = saleFlag;
	}

	public String getRetLatitude() {
		return retLatitude;
	}

	public void setRetLatitude(String retLatitude) {
		this.retLatitude = retLatitude;
	}

	public String getRetLongitude() {
		return retLongitude;
	}

	public void setRetLongitude(String retLongitude) {
		this.retLongitude = retLongitude;
	}

	public String getSplashAdId() {
		return splashAdId;
	}

	public void setSplashAdId(String splashAdId) {
		this.splashAdId = splashAdId;
	}

	public Integer getLastVisitedNo() {
		return lastVisitedNo;
	}

	public void setLastVisitedNo(Integer lastVisitedNo) {
		this.lastVisitedNo = lastVisitedNo;
	}

	public Integer getRetListId() {
		return retListId;
	}

	public void setRetListId(Integer retListId) {
		this.retListId = retListId;
	}

	public String getRetGroupImg() {
		return retGroupImg;
	}

	public void setRetGroupImg(String retGroupImg) {
		this.retGroupImg = retGroupImg;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getScanTypeId() {
		return scanTypeId;
	}

	public void setScanTypeId(Integer scanTypeId) {
		this.scanTypeId = scanTypeId;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public Integer getRetDetId() {
		return retDetId;
	}

	public void setRetDetId(Integer retDetId) {
		this.retDetId = retDetId;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getRetailerURL() {
		return retailerURL;
	}

	public void setRetailerURL(String retailerURL) {
		if (retailerURL == null || "".equals(retailerURL)) {
			this.retailerURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retailerURL = retailerURL;
		}
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailerPageQRURL() {
		return retailerPageQRURL;
	}

	public void setRetailerPageQRURL(String retailerPageQRURL) {
		if (retailerPageQRURL == null || "".equals(retailerPageQRURL)) {
			this.retailerPageQRURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retailerPageQRURL = retailerPageQRURL;
		}
	}

	public Integer getBanAdId() {
		return banAdId;
	}

	public void setBanAdId(Integer banAdId) {
		this.banAdId = banAdId;
	}

	public Integer getRibAdImgPath() {
		return ribAdImgPath;
	}

	public void setRibAdImgPath(Integer ribAdImgPath) {
		this.ribAdImgPath = ribAdImgPath;
	}

	public Boolean getSpecialOffFlag() {
		return specialOffFlag;
	}

	public void setSpecialOffFlag(Boolean specialOffFlag) {
		this.specialOffFlag = specialOffFlag;
	}

	public Boolean getHotDealFlag() {
		return hotDealFlag;
	}

	public void setHotDealFlag(Boolean hotDealFlag) {
		this.hotDealFlag = hotDealFlag;
	}

	public Boolean getClrFlag() {
		return clrFlag;
	}

	public void setClrFlag(Boolean clrFlag) {
		this.clrFlag = clrFlag;
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

	/**
	 * @return the rowNum
	 */
	public Integer getRowNum() {
		return rowNum;
	}

	/**
	 * @param rowNum
	 *            the rowNum to set
	 */
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @return the pageId
	 */
	public Integer getPageId() {
		return pageId;
	}

	/**
	 * @param pageId
	 *            the pageId to set
	 */
	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @param pageTitle
	 *            the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * @return the sDescription
	 */
	public String getsDescription() {
		return sDescription;
	}

	/**
	 * @param sDescription
	 *            the sDescription to set
	 */
	public void setsDescription(String sDescription) {
		sDescription = Utility.removeHTMLTags(sDescription);
		if ("".equals(Utility.checkNull(sDescription))) {
			this.sDescription = HubCitiConstants.NOTAPPLICABLE;
		} else if (sDescription.contains("<![CDATA[")) {
			this.sDescription = sDescription;
		} else {
			this.sDescription = "<![CDATA[" + sDescription + "]]>";
		}
	}

	/**
	 * @return the pageLink
	 */
	public String getPageLink() {
		return pageLink;
	}

	/**
	 * @param pageLink
	 *            the pageLink to set
	 */
	public void setPageLink(String pageLink) {
		if ("".equals(Utility.checkNull(pageLink))) {
			this.pageLink = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.pageLink = pageLink;
		}
	}

	/**
	 * @return the specialsListId
	 */
	public Integer getSpecialsListId() {
		return specialsListId;
	}

	/**
	 * @param specialsListId
	 *            the specialsListId to set
	 */
	public void setSpecialsListId(Integer specialsListId) {
		this.specialsListId = specialsListId;
	}

	/**
	 * @return the extLinkFlag
	 */
	public Integer getExtLinkFlag() {
		return extLinkFlag;
	}

	/**
	 * @param extLinkFlag
	 *            the extLinkFlag to set
	 */
	public void setExtLinkFlag(Integer extLinkFlag) {
		this.extLinkFlag = extLinkFlag;
	}

	/**
	 * @return the externalLink
	 */
	public String getExternalLink() {
		return externalLink;
	}

	/**
	 * @param externalLink
	 *            the externalLink to set
	 */
	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	/**
	 * @return the couponId
	 */
	public Integer getCouponId() {
		return couponId;
	}

	/**
	 * @param couponId
	 *            the couponId to set
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	/**
	 * @return the couponListId
	 */
	public String getCouponListId() {
		return couponListId;
	}

	/**
	 * @param couponListId
	 *            the couponListId to set
	 */
	public void setCouponListId(String couponListId) {
		this.couponListId = couponListId;
	}

	/**
	 * @return the pathName
	 */
	public String getPathName() {
		return pathName;
	}

	/**
	 * @param pathName
	 *            the pathName to set
	 */
	public void setPathName(String pathName) {
		if ("".equals(Utility.checkNull(pathName))) {
			this.pathName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.pathName = pathName;
		}
	}

	/**
	 * @return the imageIconId
	 */
	public String getImageIconId() {
		return imageIconId;
	}

	/**
	 * @param imageIconId
	 *            the imageIconId to set
	 */
	public void setImageIconId(String imageIconId) {
		if ("".equals(Utility.checkNull(imageIconId))) {
			this.imageIconId = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.imageIconId = imageIconId;
		}
	}

	/**
	 * @return the pathType
	 */
	public String getPathType() {
		return pathType;
	}

	/**
	 * @param pathType
	 *            the pathType to set
	 */
	public void setPathType(String pathType) {
		if ("".equals(Utility.checkNull(pathType))) {
			this.pathType = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.pathType = pathType;
		}
	}

	/**
	 * To get salePrice value.
	 * 
	 * @return the salePrice
	 */
	public String getSalePrice() {
		return salePrice;
	}

	/**
	 * To set salePrice value.
	 * 
	 * @param salePrice
	 *            the salePrice to set
	 */
	public void setSalePrice(String salePrice) {
		if (salePrice == null) {
			this.salePrice = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!salePrice.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(salePrice)) {
				this.salePrice = Utility.formatDecimalValue(salePrice);
				this.salePrice = "$" + this.salePrice;
			} else {
				this.salePrice = salePrice;
			}
		}
	}

	public String getOnProdDetId() {
		return onProdDetId;
	}

	public void setOnProdDetId(String onProdDetId) {
		this.onProdDetId = onProdDetId;
	}

	public String getPrice() {
		return price;
	}

	/**
	 * To sety price value.
	 * 
	 * @param price
	 *            the price to set
	 */
	public void setPrice(String price) {
		if (price == null) {
			this.price = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!price.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(price)) {

				this.price = Utility.formatDecimalValue(price);
				this.price = "$" + this.price;
			} else {
				this.price = price;
			}
		}
	}

	public String getBuyURL() {
		return buyURL;
	}

	public void setBuyURL(String buyURL) {
		if (buyURL == null || "".equalsIgnoreCase(buyURL)) {
			this.buyURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.buyURL = buyURL;
		}
	}

	public String getShipmentCost() {
		return shipmentCost;
	}

	public void setShipmentCost(String shipmentCost) {
		if (shipmentCost == null || "".equalsIgnoreCase(shipmentCost)) {
			this.shipmentCost = HubCitiConstants.NOTAPPLICABLE;
		} else {

			if (!shipmentCost.contains("$")	&& !HubCitiConstants.NOTAPPLICABLE.equals(shipmentCost)	&& shipmentCost.matches("[0-9.]+")) {

				this.shipmentCost = Utility.formatDecimalValue(shipmentCost);
				this.shipmentCost = "$" + this.shipmentCost;

			} else {
				this.shipmentCost = shipmentCost;
			}
		}
	}

	public String getRetailOnlineURL() {
		return retailOnlineURL;
	}

	public void setRetailOnlineURL(String retailOnlineURL) {
		if (retailOnlineURL == null || "".equalsIgnoreCase(retailOnlineURL)) {
			this.retailOnlineURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retailOnlineURL = retailOnlineURL;
		}
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

	/**
	 * @return the cateName
	 */
	public String getCateName() {
		return cateName;
	}

	/**
	 * @param cateName
	 *            the cateName to set
	 */
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	public Integer getLinkId() {
		return linkId;
	}

	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
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

	public Integer getAppSiteId() {
		return appSiteId;
	}

	public void setAppSiteId(Integer appSiteId) {
		this.appSiteId = appSiteId;
	}

	public String getAppSiteName() {
		return appSiteName;
	}

	public void setAppSiteName(String appSiteName) {
		this.appSiteName = appSiteName;
	}

	public Integer getRibbonAdId() {
		return ribbonAdId;
	}

	public void setRibbonAdId(Integer ribbonAdId) {
		this.ribbonAdId = ribbonAdId;
	}

	public String getAppDownloadLink() {
		return appDownloadLink;
	}

	public void setAppDownloadLink(String appDownloadLink) {
		this.appDownloadLink = appDownloadLink;
	}

	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	public Integer getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(Integer subCatId) {
		this.subCatId = subCatId;
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

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	public String getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(String maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	/**
	 * @return the retailImgPath
	 */
	public String getRetailImgPath() {
		return retailImgPath;
	}

	/**
	 * @param retailImgPath
	 *            the retailImgPath to set
	 */
	public void setRetailImgPath(String retailImgPath) {
		if ("".equals(Utility.checkNull(retailImgPath))) {
			this.retailImgPath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.retailImgPath = retailImgPath;
		}
	}

	/**
	 * @return the pageTempLink
	 */
	public String getPageTempLink() {
		return pageTempLink;
	}

	/**
	 * @param pageTempLink
	 *            the pageTempLink to set
	 */
	public void setPageTempLink(String pageTempLink) {
		this.pageTempLink = pageTempLink;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
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
	 * @return the fValueId
	 */
	public String getfValueId() {
		return fValueId;
	}

	/**
	 * @return the filterId
	 */
	public String getFilterId() {
		return filterId;
	}

	/**
	 * @param fValueId
	 *            the fValueId to set
	 */
	public void setfValueId(String fValueId) {
		this.fValueId = fValueId;
	}

	/**
	 * @param filterId
	 *            the filterId to set
	 */
	public void setFilterId(String filterId) {
		this.filterId = filterId;
	}

	/**
	 * @return the specialNames
	 */
	public String getSpecialNames() {
		return specialNames;
	}

	/**
	 * @param specialNames
	 *            the specialNames to set
	 */
	public void setSpecialNames(String specialNames) {
		this.specialNames = specialNames;
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
	 * @param locSpecials
	 *            the locSpecials to set
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

	/**
	 * @return the subCatIds
	 */
	public String getSubCatIds() {
		return subCatIds;
	}

	/**
	 * @param subCatIds
	 *            the subCatIds to set
	 */
	public void setSubCatIds(String subCatIds) {
		this.subCatIds = subCatIds;
	} /*	*/
	/**
	 * @return the retailImgPath
	 */
	/*
	 * public String getRetailImgPath() { return RetailImgPath; }
	 *//**
	 * @param retailImgPath the retailImgPath to set
	 */
	/*
	 * public void setRetailImgPath(String retailImgPath) {
	 * if("".equals(Utility.checkNull(retailImgPath))) { this.logoImagePath =
	 * HubCitiConstants.IMAGENOTFOUND; } else { this.logoImagePath =
	 * logoImagePath; } RetailImgPath = retailImgPath; }
	 */

	public String getUserPostalCode() {
		return userPostalCode;
	}

	public void setUserPostalCode(String userPostalCode) {
		this.userPostalCode = userPostalCode;
	}

	public String getPhoneImg() {
		return phoneImg;
	}

	public void setPhoneImg(String phoneImg) {
		this.phoneImg = phoneImg;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMailImg() {
		return mailImg;
	}

	public void setMailImg(String mailImg) {
		this.mailImg = mailImg;
	}

	public String getRetURLImg() {
		return retURLImg;
	}

	public void setRetURLImg(String retURLImg) {
		this.retURLImg = retURLImg;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}


	public Integer getBandID() {
		return bandID;
	}

	public void setBandID(Integer bandID) {
		this.bandID = bandID;
	}

	public String getBandImgPath() {
		return bandImgPath;
	}

	public void setBandImgPath(String bandImgPath) {
		if (bandImgPath == null || "".equals(bandImgPath)) {
			this.bandImgPath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.bandImgPath = bandImgPath;
		}
	}

	public String getBandURL() {
		return bandURL;
	}

	public void setBandURL(String bandURL) {
		this.bandURL = bandURL;
	}

	public String getBandURLImgPath() {
		return bandURLImgPath;
	}

	public void setBandURLImgPath(String bandURLImgPath) {
		if (bandURLImgPath == null || "".equals(bandURLImgPath)) {
			this.bandURLImgPath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.bandURLImgPath = bandURLImgPath;
		}
	}
	
	public String getCatIds() {
		return catIds;
	}

	public void setCatIds(String catIds) {
		this.catIds = catIds;
	}

	public String getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime(String requestedTime) {
		this.requestedTime = requestedTime;
	}

	public String getLocationOpen() {
		return locationOpen;
	}

	public void setLocationOpen(String locationOpen) {
		this.locationOpen = locationOpen;
	}

	/**
	 * @return the claimExist
	 */
	public Integer getClaimExist() {
		return claimExist;
	}

	/**
	 * @param claimExist the claimExist to set
	 */
	public void setClaimExist(Integer claimExist) {
		this.claimExist = claimExist;
	}

	/**
	 * @return the claimTxtMsg
	 */
	public String getClaimTxtMsg() {
		return claimTxtMsg;
	}

	/**
	 * @param claimTxtMsg the claimTxtMsg to set
	 */
	public void setClaimTxtMsg(String claimTxtMsg) {
		if (claimTxtMsg == null || "".equals(claimTxtMsg)) {
			this.claimTxtMsg = claimTxtMsg;
		} else {
			this.claimTxtMsg = "<![CDATA[" + claimTxtMsg + "]]>";
		}
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
		this.sDate = sDate;
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
		this.eDate = eDate;
	}

	/**
	 * @return the sTime
	 */
	public String getsTime() {
		return sTime;
	}

	/**
	 * @param sTime the sTime to set
	 */
	public void setsTime(String sTime) {
		this.sTime = sTime;
	}

	/**
	 * @return the eTime
	 */
	public String geteTime() {
		return eTime;
	}

	/**
	 * @param eTime the eTime to set
	 */
	public void seteTime(String eTime) {
		this.eTime = eTime;
	}

	/**
	 * @return the bEvtName
	 */
	public String getbEvtName() {
		return bEvtName;
	}

	/**
	 * @param bEvtName the bEvtName to set
	 */
	public void setbEvtName(String bEvtName) {
		this.bEvtName = bEvtName;
	}

	/**
	 * @return the bEvtId
	 */
	public Integer getbEvtId() {
		return bEvtId;
	}

	/**
	 * @param bEvtId the bEvtId to set
	 */
	public void setbEvtId(Integer bEvtId) {
		this.bEvtId = bEvtId;
	}

	/**
	 * @return the isFeatured
	 */
	public Boolean getIsFeatured() {
		return isFeatured;
	}

	/**
	 * @param isFeatured the isFeatured to set
	 */
	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}

	/**
	 * @return the distanceTxt
	 */
	public String getDistanceTxt() {
		return distanceTxt;
	}

	/**
	 * @param distanceTxt the distanceTxt to set
	 */
	public void setDistanceTxt(String distanceTxt) {
		this.distanceTxt = distanceTxt;
	}

	/**
	 * @param lCount the lCount to set
	 */
	public void setlCount(String lCount) {
		this.lCount = lCount;
	}

	/**
	 * @return the pageIds
	 */
	public String getPageIds() {
		return pageIds;
	}

	/**
	 * @param pageIds the pageIds to set
	 */
	public void setPageIds(String pageIds) {
		this.pageIds = pageIds;
	}

	/**
	 * @return the lCount
	 */
	public String getlCount() {
		return lCount;
	}

	/**
	 * @return the isNonFeatured
	 */
	public Boolean getIsNonFeatured() {
		return isNonFeatured;
	}

	/**
	 * @param isNonFeatured the isNonFeatured to set
	 */
	public void setIsNonFeatured(Boolean isNonFeatured) {
		this.isNonFeatured = isNonFeatured;
	}

	public Integer getRetailId() {
		return retailId;
	}

	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponImagePath() {
		return couponImagePath;
	}

	public void setCouponImagePath(String couponImagePath) {
		this.couponImagePath = couponImagePath;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public Integer getCounts() {
		return counts;
	}

	public void setCounts(Integer counts) {
		this.counts = counts;
	}

	public Integer getRetId() {
		return retId;
	}

	public void setRetId(Integer retId) {
		this.retId = retId;
	}

	public String getRetName() {
		return retName;
	}

	public void setRetName(String retName) {
		this.retName = retName;
	}

	public Integer getIsFeatOrNonFeat() {
		return isFeatOrNonFeat;
	}

	public void setIsFeatOrNonFeat(Integer isFeatOrNonFeat) {
		this.isFeatOrNonFeat = isFeatOrNonFeat;
	}

	public String getCouponIds() {
		return couponIds;
	}

	public void setCouponIds(String couponIds) {
		this.couponIds = couponIds;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
