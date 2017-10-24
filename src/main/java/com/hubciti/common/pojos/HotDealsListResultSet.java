package com.hubciti.common.pojos;

import java.util.List;

/**
 * The POJO class for HotDealsCategoryInfo.
 * 
 * @author shyamsundara_hm.
 */
public class HotDealsListResultSet {

	String responseCode;

	String responseText;
	/**
	 * for category flag
	 */
	private Integer categoryFlag;
	/**
	 * Flag for Pagination which tell whether next set of records available or
	 * not.
	 */
	private Integer nextPage;

	/**
	 * Variable FavCat declared as integer.
	 */
	private Integer FavCat;
	/**
	 * 
	 */
	private Integer maxCnt;
	/**
	 * 
	 */
	private Integer maxRowNum;
	/**
	 * 
	 */
	private Integer bottomBtn;
	
	private Integer mainMenuId;
	
	private String dealType;
	/**
	 * Variable hotDealsCategoryInfo declared as List.
	 */
	private List<HotDealsCategoryInfo> hotDealsCategoryInfo;

	private List<HotDealsResultSet> hotDealsListResponselst;

	private List<HotDealAPIResultSet> hdAPIResult;

	private List<Category> category;



	private List<HotDealsDetails> hdDetailsList;

	private List<CategoryInfo> categoryInfoList;

	private List<RetailersDetails> retDetailsList;
	
	private List<HotDealsListResultSet> dealTypeList;

	/**
	 * 
	 */
	private List<BottomButton> arBottomBtnList;
	
	private Integer dealTypeCount;
	
	/**
	 * Gets the value of favCat.
	 * 
	 * @return the favCat
	 */
	public Integer getFavCat() {
		return FavCat;
	}

	/**
	 * Sets the value of favCat.
	 * 
	 * @param favCat
	 *            the favCat to set
	 */
	public void setFavCat(Integer favCat) {
		FavCat = favCat;
	}

	/**
	 * Gets the value of nextPage.
	 * 
	 * @return the nextPage
	 */
	public Integer getNextPage() {
		return nextPage;
	}

	/**
	 * Sets the value of nextPage.
	 * 
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * Gets the value of the hotDealsCategoryInfo property.
	 * 
	 * @return the hotDealsCategoryInfo
	 */
	public List<HotDealsCategoryInfo> getHotDealsCategoryInfo() {
		return hotDealsCategoryInfo;
	}

	/**
	 * Sets the value of the hotDealsCategoryInfo property.
	 * 
	 * @param hotDealsCategoryInfo
	 *            as of type List.
	 */
	public void setHotDealsCategoryInfo(List<HotDealsCategoryInfo> hotDealsCategoryInfo) {
		this.hotDealsCategoryInfo = hotDealsCategoryInfo;
	}

	/**
	 * This method return hotDealsListResponselst value.
	 * 
	 * @return the hotDealsListResponselst
	 */
	public List<HotDealsResultSet> getHotDealsListResponselst() {
		return hotDealsListResponselst;
	}

	/**
	 * This method set the value to hotDealsListResponselst.
	 * 
	 * @param hotDealsListResponselst
	 *            the hotDealsListResponselst to set
	 */
	public void setHotDealsListResponselst(List<HotDealsResultSet> hotDealsListResponselst) {
		this.hotDealsListResponselst = hotDealsListResponselst;
	}

	public Integer getCategoryFlag() {
		return categoryFlag;
	}

	public void setCategoryFlag(Integer categoryFlag) {
		this.categoryFlag = categoryFlag;
	}

	/*
	 * private String categoryName; private Integer categoryId; private Integer
	 * lastVistedProductNo; private List<HotDealsDetails> hotDealsDetails;
	 * public String getCategoryName() { return categoryName; } public void
	 * setCategoryName(String categoryName) { this.categoryName = categoryName;
	 * } public Integer getCategoryId() { return categoryId; } public void
	 * setCategoryId(Integer categoryId) { this.categoryId = categoryId; }
	 *//**
	 * @return the hotDealsDetails
	 */
	/*
	 * public List<HotDealsDetails> getHotDealsDetails() { return
	 * hotDealsDetails; }
	 *//**
	 * @param hotDealsDetails
	 *            the hotDealsDetails to set
	 */
	/*
	 * public void setHotDealsDetails(List<HotDealsDetails> hotDealsDetails) {
	 * this.hotDealsDetails = hotDealsDetails; }
	 *//**
	 * @return the lastVistedProductNo
	 */
	/*
	 * public Integer getLastVistedProductNo() { return lastVistedProductNo; }
	 *//**
	 * @param lastVistedProductNo
	 *            the lastVistedProductNo to set
	 */
	/*
	 * public void setLastVistedProductNo(Integer lastVistedProductNo) {
	 * this.lastVistedProductNo = lastVistedProductNo; }
	 */

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	/**
	 * @return the category
	 */
	public List<Category> getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(List<Category> category) {
		this.category = category;
	}

	public List<HotDealsDetails> getHdDetailsList() {
		return hdDetailsList;
	}

	public void setHdDetailsList(List<HotDealsDetails> hdDetailsList) {
		this.hdDetailsList = hdDetailsList;
	}

	public Integer getMaxCnt() {
		return maxCnt;
	}

	public void setMaxCnt(Integer maxCnt) {
		this.maxCnt = maxCnt;
	}

	public List<CategoryInfo> getCategoryInfoList() {
		return categoryInfoList;
	}

	public void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
		this.categoryInfoList = categoryInfoList;
	}

	public List<RetailersDetails> getRetDetailsList() {
		return retDetailsList;
	}

	public void setRetDetailsList(List<RetailersDetails> retDetailsList) {
		this.retDetailsList = retDetailsList;
	}

	public Integer getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	public List<HotDealAPIResultSet> getHdAPIResult() {
		return hdAPIResult;
	}

	public void setHdAPIResult(List<HotDealAPIResultSet> hdAPIResult) {
		this.hdAPIResult = hdAPIResult;
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
	 * @return the bottomBtn
	 */
	public Integer getBottomBtn() {
		return bottomBtn;
	}

	/**
	 * @return the arBottomBtnList
	 */
	public List<BottomButton> getArBottomBtnList() {
		return arBottomBtnList;
	}

	/**
	 * @param bottomBtn the bottomBtn to set
	 */
	public void setBottomBtn(Integer bottomBtn) {
		this.bottomBtn = bottomBtn;
	}

	/**
	 * @param arBottomBtnList the arBottomBtnList to set
	 */
	public void setArBottomBtnList(List<BottomButton> arBottomBtnList) {
		this.arBottomBtnList = arBottomBtnList;
	}

	/**
	 * @return the dealType
	 */
	public String getDealType() {
		return dealType;
	}

	/**
	 * @param dealType the dealType to set
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	/**
	 * @return the dealTypeList
	 */
	public List<HotDealsListResultSet> getDealTypeList() {
		return dealTypeList;
	}

	/**
	 * @param dealTypeList the dealTypeList to set
	 */
	public void setDealTypeList(List<HotDealsListResultSet> dealTypeList) {
		this.dealTypeList = dealTypeList;
	}

	/**
	 * @return the dealTypeCount
	 */
	public Integer getDealTypeCount() {
		return dealTypeCount;
	}

	/**
	 * @param dealTypeCount the dealTypeCount to set
	 */
	public void setDealTypeCount(Integer dealTypeCount) {
		this.dealTypeCount = dealTypeCount;
	}

}
