package com.hubciti.common.pojos;

import java.util.List;

/**
 * The POJO class for Categories.
 * 
 * @author shyamsundara_hm
 */

public class Categories {
	
	private String responseCode;

	private String responseText;
	private Integer bottomBtn;
	/**
	 * For mainMenuID
	 */
	private Integer mainMenuId;

	/**
	 * Variable userId declared as Integer.
	 */
	private Integer userId;

	private Integer maxCnt;

	private Integer nextPage;

	private Integer maxRowNum;
	private String mColor;
	private String mFontColor;
	private String sectionColor;
	private String noRecordMsg;
	/**
	 * Variable category declared as List.
	 */
	private List<Category> categoryInfo;

	/**
	 * For GooleCategory list
	 */
	private List<GoogleCategory> catList;

	private List<FAQDetails> faqCatList;
	
	private List<BottomButton> bottomBtnList;

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
	 * @return the categoryInfo
	 */
	public List<Category> getCategoryInfo() {
		return categoryInfo;
	}

	/**
	 * @param categoryInfo
	 *            the categoryInfo to set
	 */
	public void setCategoryInfo(List<Category> categoryInfo) {
		this.categoryInfo = categoryInfo;
	}

	/**
	 * To get {@link GoogleCategory} list
	 * 
	 * @return list of {@link GoogleCategory} catList object
	 */
	public List<GoogleCategory> getCatList() {
		return catList;
	}

	/**
	 * To set {@link GoogleCategory} list
	 * 
	 * @param catList
	 */
	public void setCatList(List<GoogleCategory> catList) {
		this.catList = catList;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
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

	public Integer getMaxCnt() {
		return maxCnt;
	}

	public void setMaxCnt(Integer maxCnt) {
		this.maxCnt = maxCnt;
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

	public List<FAQDetails> getFaqCatList() {
		return faqCatList;
	}

	public void setFaqCatList(List<FAQDetails> faqCatList) {
		this.faqCatList = faqCatList;
	}

	public List<BottomButton> getBottomBtnList() {
		return bottomBtnList;
	}

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
	 * @return the mColor
	 */
	public String getmColor() {
		return mColor;
	}

	/**
	 * @return the mFontColor
	 */
	public String getmFontColor() {
		return mFontColor;
	}

	/**
	 * @return the sectionColor
	 */
	public String getSectionColor() {
		return sectionColor;
	}

	/**
	 * @param mColor the mColor to set
	 */
	public void setmColor(String mColor) {
		this.mColor = mColor;
	}

	/**
	 * @param mFontColor the mFontColor to set
	 */
	public void setmFontColor(String mFontColor) {
		this.mFontColor = mFontColor;
	}

	/**
	 * @param sectionColor the sectionColor to set
	 */
	public void setSectionColor(String sectionColor) {
		this.sectionColor = sectionColor;
	}

	/**
	 * @return the noRecordMsg
	 */
	public String getNoRecordMsg() {
		return noRecordMsg;
	}

	/**
	 * @param noRecordMsg the noRecordMsg to set
	 */
	public void setNoRecordMsg(String noRecordMsg) {
		this.noRecordMsg = noRecordMsg;
	}
}
