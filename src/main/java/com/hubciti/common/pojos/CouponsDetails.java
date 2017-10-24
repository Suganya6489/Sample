package com.hubciti.common.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * The class has getter and setter methods for CouponsDetails.
 * 
 * @author Kumar D
 */
public class CouponsDetails {
	private String responseCode;

	private String responseText;
	/**
	 * 
	 */
	private Integer bottomBtn;
	/**
	 * Variable couponDetail declared as of type List.
	 */
	protected ArrayList<String> productNamesLst;

	/**
	 * Variable couponDetail declared as of type List.
	 */
	protected ArrayList<ProductDetail> productLst;

	/**
	 * Variable couponDetail declared as of type List.
	 */
	protected CouponDetails couponInfo;

	/**
	 * Variable couponDetail declared as of type List.
	 */
	protected List<CouponDetails> couponDetail;

	private Integer nextPageFlag;

	private Integer mainMenuId;

	private List<CategoryInfo> categoryInfoList;

	private Integer maxCnt;

	private List<RetailersDetails> retDetailsList;

	private List<CategoryInfo> catInfoList;

	private List<RetailerDetail> retailDetailsList;

	private Integer maxRowNum;
	/**
	 * 
	 */
	private List<BottomButton> arBottomBtnList;

	/**
	 * Gets the value of the couponDetail property.
	 * 
	 * @return possible object is {@link List }
	 */
	public List<CouponDetails> getCouponDetail() {
		return couponDetail;
	}

	/**
	 * Sets the value of the couponDetail property.
	 * 
	 * @param couponDetail
	 *            as of type List.
	 */
	public void setCouponDetail(List<CouponDetails> couponDetail) {
		this.couponDetail = couponDetail;
	}

	/**
	 * @return the couponInfo
	 */
	public CouponDetails getCouponInfo() {
		return couponInfo;
	}

	/**
	 * @param couponInfo
	 *            the couponInfo to set
	 */
	public void setCouponInfo(CouponDetails couponInfo) {
		this.couponInfo = couponInfo;
	}

	public ArrayList<ProductDetail> getProductLst() {
		return productLst;
	}

	public void setProductLst(ArrayList<ProductDetail> productLst) {
		this.productLst = productLst;
	}

	public Integer getNextPageFlag() {
		return nextPageFlag;
	}

	public void setNextPageFlag(Integer nextPageFlag) {
		this.nextPageFlag = nextPageFlag;
	}

	public List<CategoryInfo> getCategoryInfoList() {
		return categoryInfoList;
	}

	public void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
		this.categoryInfoList = categoryInfoList;
	}

	public Integer getMaxCnt() {
		return maxCnt;
	}

	public void setMaxCnt(Integer maxCnt) {
		this.maxCnt = maxCnt;
	}

	public List<RetailersDetails> getRetDetailsList() {
		return retDetailsList;
	}

	public void setRetDetailsList(List<RetailersDetails> retDetailsList) {
		this.retDetailsList = retDetailsList;
	}

	public List<CategoryInfo> getCatInfoList() {
		return catInfoList;
	}

	public void setCatInfoList(List<CategoryInfo> catInfoList) {
		this.catInfoList = catInfoList;
	}

	public List<RetailerDetail> getRetailDetailsList() {
		return retailDetailsList;
	}

	public void setRetailDetailsList(List<RetailerDetail> retailDetailsList) {
		this.retailDetailsList = retailDetailsList;
	}

	public Integer getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
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
	 * @return the productNamesLst
	 */
	public ArrayList<String> getProductNamesLst() {
		return productNamesLst;
	}

	/**
	 * @param productNamesLst
	 *            the productNamesLst to set
	 */
	public void setProductNamesLst(ArrayList<String> productNamesLst) {
		this.productNamesLst = productNamesLst;
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
}
