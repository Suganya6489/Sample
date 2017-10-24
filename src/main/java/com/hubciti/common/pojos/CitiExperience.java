package com.hubciti.common.pojos;

import java.util.List;

public class CitiExperience {

	private String responseCode;

	private String responseText;

	private Integer maxCnt;

	/**
	 * For Retailer Affiliate ID
	 */
	private Integer retAffId;

	/**
	 * For Retailer Affiliate Name
	 */
	private String retAffName;

	/**
	 * or Retailer Affiliate Image
	 */
	private String retAffImg;

	private List<CitiExperience> partnerList;
	
	private List<BottomButton> bottomBtnList;

	private Integer bottomBtn;

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

	public String getRetAffImg() {
		return retAffImg;
	}

	public void setRetAffImg(String retAffImg) {
		this.retAffImg = retAffImg;
	}

	public Integer getMaxCnt() {
		return maxCnt;
	}

	public void setMaxCnt(Integer maxCnt) {
		this.maxCnt = maxCnt;
	}

	public List<CitiExperience> getPartnerList() {
		return partnerList;
	}

	public void setPartnerList(List<CitiExperience> partnerList) {
		this.partnerList = partnerList;
	}

	/**
	 * @return the bottomBtnList
	 */
	public List<BottomButton> getBottomBtnList() {
		return bottomBtnList;
	}

	/**
	 * @param bottomBtnList the bottomBtnList to set
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

}
