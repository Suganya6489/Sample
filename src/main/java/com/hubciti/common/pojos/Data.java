package com.hubciti.common.pojos;

import java.util.List;

public class Data {
	
	private String responseCode;

	private String responseText;

	private String mColor;
	private String mFontColor;
	private String mBackGrdColor;
	private String sectionColor;
	
	private Integer bottomBtn;
	
	private String message;
	
	private List<RSSFeedMessage> rssFeedList;
	
	private List<Deal> dealList;
	
	private List<Deal> dealStateList;
	
	private String link;
	
	/**
	 * 
	 */
	private List<BottomButton> arBottomBtnList;

	
	public List<RSSFeedMessage> getRssFeedList() {
		return rssFeedList;
	}

	public void setRssFeedList(List<RSSFeedMessage> rssFeedList) {
		this.rssFeedList = rssFeedList;
	}

	public List<Deal> getDealList() {
		return dealList;
	}

	public void setDealList(List<Deal> dealList) {
		this.dealList = dealList;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
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
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @param responseText the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
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
	 * @return the arBottomBtnList
	 */
	public List<BottomButton> getArBottomBtnList() {
		return arBottomBtnList;
	}

	/**
	 * @param arBottomBtnList the arBottomBtnList to set
	 */
	public void setArBottomBtnList(List<BottomButton> arBottomBtnList) {
		this.arBottomBtnList = arBottomBtnList;
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
	 * @return the dealStateList
	 */
	public List<Deal> getDealStateList() {
		return dealStateList;
	}

	/**
	 * @param dealStateList the dealStateList to set
	 */
	public void setDealStateList(List<Deal> dealStateList) {
		this.dealStateList = dealStateList;
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
	 * @return the mBackGrdColor
	 */
	public String getmBackGrdColor() {
		return mBackGrdColor;
	}

	/**
	 * @param mBackGrdColor the mBackGrdColor to set
	 */
	public void setmBackGrdColor(String mBackGrdColor) {
		this.mBackGrdColor = mBackGrdColor;
	}


}
