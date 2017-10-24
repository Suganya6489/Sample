package com.hubciti.common.pojos;

public class News {

	private String userName;

	private Integer userId;
	
	private Integer catId;
	private Integer newsId;

	/**
	 * Variable for platform identification
	 */
	private String platform;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	private Integer hubCitiId;
	
	private String bkMarkOrder;
	
	private String navigOrder;


	
	private Integer templateId;
	
	private String sectnOrder;

	private Integer isBkMark;
	private String catName;
	
	/**
	 * 
	 */
	public Integer linkId;
	/**
	 * 
	 */
	public Integer level;
	
	private Boolean sideNaviPersonalizatn;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	/**
	 * @return the sectnOrder
	 */
	public String getSectnOrder() {
		return sectnOrder;
	}

	/**
	 * @param sectnOrder the sectnOrder to set
	 */
	public void setSectnOrder(String sectnOrder) {
		this.sectnOrder = sectnOrder;
	}

	/**
	 * @return the isBkMark
	 */
	public Integer getIsBkMark() {
		return isBkMark;
	}

	/**
	 * @param isBkMark the isBkMark to set
	 */
	public void setIsBkMark(Integer isBkMark) {
		this.isBkMark = isBkMark;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public String getBkMarkOrder() {
		return bkMarkOrder;
	}

	public void setBkMarkOrder(String bkMarkOrder) {
		this.bkMarkOrder = bkMarkOrder;
	}

	public String getNavigOrder() {
		return navigOrder;
	}

	public void setNavigOrder(String navigOrder) {
		this.navigOrder = navigOrder;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
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
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getSideNaviPersonalizatn() {
		return sideNaviPersonalizatn;
	}

	public void setSideNaviPersonalizatn(Boolean sideNaviPersonalizatn) {
		this.sideNaviPersonalizatn = sideNaviPersonalizatn;
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

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	

	

	


}
