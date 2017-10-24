package com.hubciti.common.pojos;

import java.util.List;

/**
 * The Item class implements an application Rss News.
 * 
 * @author Kumar D
 */
public class Item {
	
	private String responseCode;
	private String responseText;
	private Integer itemID;
	private Integer userId;
	private Integer rowNum;
	private String title;
	private String image;
	private String link;
	private Integer catId;
	private String catName;
	private String catImgPath;
	private String section;
	private String adcopy;
	private String date;
	private String sDesc;
	private String lDesc;
	private String apiPatnerName;
	private String reason;
	private Integer rowCount;
	private Integer status;
	private String hubCitiID;
	private String HubCitiName;
	private String classification;
	private List<Item> items;
	private String message;
	private String searchKey;
	private String time;
	private String imgPosition;
	private String displayType;
	private String bannerImg;
	private Integer lowerLimit;
	private String author;
	private String subcategory;
	private String videoLink;
	private String templateType;
	private Integer maxCnt;
	private Integer nextPage;
	private Integer lowerLimitFlag;
	private Integer hubCitiId;
	private Integer isSideBar;
	public String dateCreated;
	private Integer linkId;
	private Integer level; 
	private String newtempName;
	private Integer templateChanged;
    private String modifiedDate;
    private String thumbImg;
	public String catColor;
	public String catTxtColor;
	private String subPageName;
	private String weatherURL;
	private Integer isSubcategory;
	
	private String bkImgPath;
	private String homeImgPath;
	private String titleTxtColor;
	private String titleBkGrdColor;
	private String hamburgerImg;
	private String backButtonColor;
	private String nonfeedlink;
	private String shareText;
	private String qrUrl;
	private String newsType;
	
	private List<Item> item;
	
	private List<CategoryInfo> categoryList;

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
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
	 * @param responseText the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	/**
	 * @return the itemID
	 */
	public Integer getItemID() {
		return itemID;
	}
	/**
	 * @param itemID the itemID to set
	 */
	public void setItemID(Integer itemID) {
		this.itemID = itemID;
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
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * @param section the section to set
	 */
	public void setSection(String section) {
		this.section = section;
	}
	/**
	 * @return the adcopy
	 */
	public String getAdcopy() {
		return adcopy;
	}
	/**
	 * @param adcopy the adcopy to set
	 */
	public void setAdcopy(String adcopy) {
		this.adcopy = adcopy;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the sDesc
	 */
	public String getsDesc() {
		return sDesc;
	}
	/**
	 * @param sDesc the sDesc to set
	 */
	public void setsDesc(String sDesc) {
		this.sDesc = sDesc;
	}
	/**
	 * @return the lDesc
	 */
	public String getlDesc() {
		return lDesc;
	}
	/**
	 * @param lDesc the lDesc to set
	 */
	public void setlDesc(String lDesc) {
		this.lDesc = lDesc;
	}
	/**
	 * @return the apiPatnerName
	 */
	public String getApiPatnerName() {
		return apiPatnerName;
	}
	/**
	 * @param apiPatnerName the apiPatnerName to set
	 */
	public void setApiPatnerName(String apiPatnerName) {
		this.apiPatnerName = apiPatnerName;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * @return the rowCount
	 */
	public Integer getRowCount() {
		return rowCount;
	}
	/**
	 * @param rowCount the rowCount to set
	 */
	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the hubCitiName
	 */
	public String getHubCitiName() {
		return HubCitiName;
	}
	/**
	 * @param hubCitiName the hubCitiName to set
	 */
	public void setHubCitiName(String hubCitiName) {
		HubCitiName = hubCitiName;
	}
	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}
	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the hubCitiID
	 */
	public String getHubCitiID() {
		return hubCitiID;
	}
	/**
	 * @param hubCitiID the hubCitiID to set
	 */
	public void setHubCitiID(String hubCitiID) {
		this.hubCitiID = hubCitiID;
	}
	/**
	 * @return the searchKey
	 */
	public String getSearchKey() {
		return searchKey;
	}
	/**
	 * @param searchKey the searchKey to set
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @return the imgPosition
	 */
	public String getImgPosition() {
		return imgPosition;
	}
	/**
	 * @param imgPosition the imgPosition to set
	 */
	public void setImgPosition(String imgPosition) {
		this.imgPosition = imgPosition;
	}
	/**
	 * @return the displayType
	 */
	public String getDisplayType() {
		return displayType;
	}
	/**
	 * @param displayType the displayType to set
	 */
	public void setDisplayType(String displayType) {
		this.displayType = displayType;
	}
	/**
	 * @return the bannerImg
	 */
	public String getBannerImg() {
		return bannerImg;
	}
	/**
	 * @param bannerImg the bannerImg to set
	 */
	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	/**
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}
	/**
	 * @param lowerLimit the lowerLimit to set
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the subcategory
	 */
	public String getSubcategory() {
		return subcategory;
	}
	/**
	 * @param subcategory the subcategory to set
	 */
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	/**
	 * @return the videoLink
	 */
	public String getVideoLink() {
		return videoLink;
	}
	/**
	 * @param videoLink the videoLink to set
	 */
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}
	/**
	 * @return the maxCnt
	 */
	public Integer getMaxCnt() {
		return maxCnt;
	}
	/**
	 * @param maxCnt the maxCnt to set
	 */
	public void setMaxCnt(Integer maxCnt) {
		this.maxCnt = maxCnt;
	}
	/**
	 * @return the nextPage
	 */
	public Integer getNextPage() {
		return nextPage;
	}
	/**
	 * @param nextPage the nextPage to set
	 */
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}
	/**
	 * @return the lowerLimitFlag
	 */
	public Integer getLowerLimitFlag() {
		return lowerLimitFlag;
	}
	/**
	 * @param lowerLimitFlag the lowerLimitFlag to set
	 */
	public void setLowerLimitFlag(Integer lowerLimitFlag) {
		this.lowerLimitFlag = lowerLimitFlag;
	}
	/**
	 * @return the hubCitiId
	 */
	public Integer getHubCitiId() {
		return hubCitiId;
	}
	/**
	 * @param hubCitiId the hubCitiId to set
	 */
	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}
	/**
	 * @return the rowNum
	 */
	public Integer getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum the rowNum to set
	 */
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	/**
	 * @return the categoryList
	 */
	public List<CategoryInfo> getCategoryList() {
		return categoryList;
	}
	/**
	 * @param categoryList the categoryList to set
	 */
	public void setCategoryList(List<CategoryInfo> categoryList) {
		this.categoryList = categoryList;
	}
	/**
	 * @return the templateType
	 */
	public String getTemplateType() {
		return templateType;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @param templateType the templateType to set
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the isSideBar
	 */
	public Integer getIsSideBar() {
		return isSideBar;
	}
	/**
	 * @param isSideBar the isSideBar to set
	 */
	public void setIsSideBar(Integer isSideBar) {
		this.isSideBar = isSideBar;
	}
	/**
	 * @return the dateCreated
	 */
	public String getDateCreated() {
		return dateCreated;
	}
	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	/**
	 * @return the catColor
	 */
	public String getCatColor() {
		return catColor;
	}
	/**
	 * @param catColor the catColor to set
	 */
	public void setCatColor(String catColor) {
		this.catColor = catColor;
	}

	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getNewtempName() {
		return newtempName;
	}
	public void setNewtempName(String newtempName) {
		this.newtempName = newtempName;
	}
	public Integer getTemplateChanged() {
		return templateChanged;
	}
	public void setTemplateChanged(Integer templateChanged) {
		this.templateChanged = templateChanged;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the subPageName
	 */
	public String getSubPageName() {
		return subPageName;
	}
	/**
	 * @param subPageName the subPageName to set
	 */
	public void setSubPageName(String subPageName) {
		this.subPageName = subPageName;
	}
	/**
	 * @return the thumbImg
	 */
	public String getThumbImg() {
		return thumbImg;
	}
	/**
	 * @param thumbImg the thumbImg to set
	 */
	public void setThumbImg(String thumbImg) {
		this.thumbImg = thumbImg;
	}

	/**
	 * @return the catId
	 */
	public Integer getCatId() {
		return catId;
	}
	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	/**
	 * @return the catImgPath
	 */
	public String getCatImgPath() {
		return catImgPath;
	}
	/**
	 * @param catImgPath the catImgPath to set
	 */
	public void setCatImgPath(String catImgPath) {
		this.catImgPath = catImgPath;
	}
	/**
	 * @return the isSubcategory
	 */
	public Integer getIsSubcategory() {
		return isSubcategory;
	}
	/**
	 * @param isSubcategory the isSubcategory to set
	 */
	public void setIsSubcategory(Integer isSubcategory) {
		this.isSubcategory = isSubcategory;
	}
	/**
	 * @return the catTxtColor
	 */
	public String getCatTxtColor() {
		return catTxtColor;
	}
	/**
	 * @param catTxtColor the catTxtColor to set
	 */
	public void setCatTxtColor(String catTxtColor) {
		this.catTxtColor = catTxtColor;
	}
	/**
	 * @return the weatherURL
	 */
	public String getWeatherURL() {
		return weatherURL;
	}
	/**
	 * @param weatherURL the weatherURL to set
	 */
	public void setWeatherURL(String weatherURL) {
		this.weatherURL = weatherURL;
	}
	/**
	 * @return the bkImgPath
	 */
	public String getBkImgPath() {
		return bkImgPath;
	}
	/**
	 * @param bkImgPath the bkImgPath to set
	 */
	public void setBkImgPath(String bkImgPath) {
		this.bkImgPath = bkImgPath;
	}
	/**
	 * @return the homeImgPath
	 */
	public String getHomeImgPath() {
		return homeImgPath;
	}
	/**
	 * @param homeImgPath the homeImgPath to set
	 */
	public void setHomeImgPath(String homeImgPath) {
		this.homeImgPath = homeImgPath;
	}
	/**
	 * @return the titleTxtColor
	 */
	public String getTitleTxtColor() {
		return titleTxtColor;
	}
	/**
	 * @param titleTxtColor the titleTxtColor to set
	 */
	public void setTitleTxtColor(String titleTxtColor) {
		this.titleTxtColor = titleTxtColor;
	}
	/**
	 * @return the titleBkGrdColor
	 */
	public String getTitleBkGrdColor() {
		return titleBkGrdColor;
	}
	/**
	 * @param titleBkGrdColor the titleBkGrdColor to set
	 */
	public void setTitleBkGrdColor(String titleBkGrdColor) {
		this.titleBkGrdColor = titleBkGrdColor;
	}
	/**
	 * @return the linkId
	 */
	public Integer getLinkId() {
		return linkId;
	}
	/**
	 * @param linkId the linkId to set
	 */
	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
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
	public String getBackButtonColor() {
		return backButtonColor;
	}
	public void setBackButtonColor(String backButtonColor) {
		this.backButtonColor = backButtonColor;
	}
	/**
	 * @return the nonfeedlink
	 */
	public String getNonfeedlink() {
		return nonfeedlink;
	}
	/**
	 * @param nonfeedlink the nonfeedlink to set
	 */
	public void setNonfeedlink(String nonfeedlink) {
		this.nonfeedlink = nonfeedlink;
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
	/**
	 * @return the newsType
	 */
	public String getNewsType() {
		return newsType;
	}
	/**
	 * @param newsType the newsType to set
	 */
	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}
	/**
	 * @return the item
	 */
	public List<Item> getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
}
