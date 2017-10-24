package com.hubciti.common.pojos;

import java.util.List;

/**
 * pojo for FAQDetails.
 * 
 * @author Kumar D
 */
public class FAQDetails {
	private String responseCode;

	private String responseText;

	private Integer hubCitiId;

	private Integer nextPageFlag;
	/**
	 * for row num
	 */
	private Integer rowNum;
	/**
	 * for faqId
	 */
	private Integer faqId;
	/**
	 * for rebate next page
	 */
	private Integer categoryId;
	/**
	 * for userId.
	 */
	private Integer userId;
	/**
	 * Variable galleryFlag declared as of type String.
	 */
	private String categoryName;
	/**
	 * for lowerLimit.
	 */
	private Integer lowerLimit;
	/**
	 * for searchKey.
	 */
	private String searchKey;
	/**
	 * For question
	 */
	private String question;
	/**
	 * For answer
	 */
	private String answer;
	/**
	 * for faqListID
	 */
	private Integer faqListId;
	/**
	 * for user tracking
	 */
	private Integer mainMenuId;
	private Integer maxCount;
	private Integer maxRowNum;
	
	private String platform;
	/**
	 * 
	 */
	private List<FAQDetails> faqDetail;

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
	 * @return the nextPageFlag
	 */
	public Integer getNextPageFlag() {
		return nextPageFlag;
	}

	/**
	 * @param nextPageFlag
	 *            the nextPageFlag to set
	 */
	public void setNextPageFlag(Integer nextPageFlag) {
		this.nextPageFlag = nextPageFlag;
	}

	/**
	 * @return the faqId
	 */
	public Integer getFaqId() {
		return faqId;
	}

	/**
	 * @param faqId
	 *            the faqId to set
	 */
	public void setFaqId(Integer faqId) {
		this.faqId = faqId;
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
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @return the searchKey
	 */
	public String getSearchKey() {
		return searchKey;
	}

	/**
	 * @param searchKey
	 *            the searchKey to set
	 */
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * @param answer
	 *            the answer to set
	 */
	public void setAnswer(String answer) {
		if (answer != null && !answer.contains("<![CDATA[")) {
			this.answer = "<![CDATA[" + answer + "]]>";
		} else {
			this.answer = answer;
		}
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
	 * @return the faqListId
	 */
	public Integer getFaqListId() {
		return faqListId;
	}

	/**
	 * @param faqListId
	 *            the faqListId to set
	 */
	public void setFaqListId(Integer faqListId) {
		this.faqListId = faqListId;
	}

	/**
	 * @return the mainMenuId
	 */
	public Integer getMainMenuId() {
		return mainMenuId;
	}

	/**
	 * @param mainMenuId
	 *            the mainMenuId to set
	 */
	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	/**
	 * @return the maxCount
	 */
	public Integer getMaxCount() {
		return maxCount;
	}

	/**
	 * @param maxCount
	 *            the maxCount to set
	 */
	public void setMaxCount(Integer maxCount) {
		this.maxCount = maxCount;
	}

	/**
	 * @return the maxRowNum
	 */
	public Integer getMaxRowNum() {
		return maxRowNum;
	}

	/**
	 * @param maxRowNum
	 *            the maxRowNum to set
	 */
	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	/**
	 * @return the faqDetail
	 */
	public List<FAQDetails> getFaqDetail() {
		return faqDetail;
	}

	/**
	 * @param faqDetail
	 *            the faqDetail to set
	 */
	public void setFaqDetail(List<FAQDetails> faqDetail) {
		this.faqDetail = faqDetail;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
