package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * The POJO class for ProductDetail.
 * 
 * @author Kumar D
 */

public class ProductDetails {

	private String responseCode;

	private String responseText;
	/**
	 * For product smart search ID
	 */
	private Integer prodSmaSeaId;
	/**
	 * for product mutiple images
	 */
	private String productMediaPath;

	/**
	 * to check product exists or there not
	 */
	private Integer productIsThere;
	/**
	 * For shopping list response response flag.
	 */
	private String responseFlag;
	/**
	 * for indicating list flag.
	 */
	private String listFlag;

	/**
	 * for indicating favorites flag.
	 */
	private String favoritesFlag;

	/**
	 * nextPage declared as Integer. for next page
	 */
	private Integer nextPage;
	/**
	 * for lastVisitedProductNo.
	 */
	private Integer lastVistedProductNo;
	/**
	 * For maxCount
	 */
	private Integer maxCount;
	/**
	 * for mediaType.
	 */
	private String mediaType;
	
	private String mColor;
	private String mFontColor;

	/**
	 * This is for productDetail list.
	 */
	private List<ProductDetail> productDetail;
	/**
	 * This is for special offer list.
	 */
	private List<RetailerDetail> specialOfferlst;

	/**
	 * This method for getting lastVistedProductNo.
	 * 
	 * @return the lastVistedProductNo
	 */

	public Integer getLastVistedProductNo() {
		return lastVistedProductNo;
	}

	/**
	 * This method for setting lastVistedProductNo.
	 * 
	 * @param lastVistedProductNo
	 *            the lastVistedProductNo to set
	 */
	public void setLastVistedProductNo(Integer lastVistedProductNo) {
		this.lastVistedProductNo = lastVistedProductNo;
	}

	/**
	 * this method for getting productDetails.
	 * 
	 * @return the productDetail
	 */
	public List<ProductDetail> getProductDetail() {
		return productDetail;
	}

	/**
	 * this method for setting productDetails.
	 * 
	 * @param productDetail
	 *            the productDetail to set
	 */
	public void setProductDetail(List<ProductDetail> productDetail) {
		this.productDetail = productDetail;
	}

	/**
	 * this method for getting mediaType.
	 * 
	 * @return the mediaType
	 */
	public String getMediaType() {
		return mediaType;
	}

	/**
	 * this method for setting mediaType.
	 * 
	 * @param mediaType
	 *            the mediaType to set
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	/**
	 * for getting nextPage.
	 * 
	 * @return the nextPage
	 */
	public Integer getNextPage() {
		return nextPage;
	}

	/**
	 * for setting nextPage.
	 * 
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * TO set listFlag.
	 * 
	 * @param listFlag
	 *            the listFlag to set
	 */
	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}

	/**
	 * To set favoritesFlag.
	 * 
	 * @param favoritesFlag
	 *            the favoritesFlag to set
	 */
	public void setFavoritesFlag(String favoritesFlag) {
		this.favoritesFlag = favoritesFlag;
	}

	/**
	 * To get listFlag.
	 * 
	 * @return the listFlag
	 */
	public String getListFlag() {
		return listFlag;
	}

	/**
	 * To set favoritesFlag.
	 * 
	 * @return the favoritesFlag
	 */
	public String getFavoritesFlag() {
		return favoritesFlag;
	}

	/**
	 * To get responseFlag.
	 * 
	 * @return the responseFlag
	 */
	public String getResponseFlag() {
		return responseFlag;
	}

	/**
	 * To set responseFlag.
	 * 
	 * @param responseFlag
	 *            the responseFlag to set
	 */
	public void setResponseFlag(String responseFlag) {
		this.responseFlag = responseFlag;
	}

	public Integer getProductIsThere() {
		return productIsThere;
	}

	public void setProductIsThere(Integer productIsThere) {
		this.productIsThere = productIsThere;
	}

	public String getProductMediaPath() {
		return productMediaPath;
	}

	public void setProductMediaPath(String productMediaPath) {
		if (productMediaPath == null) {
			this.productMediaPath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.productMediaPath = productMediaPath;
		}
	}

	public List<RetailerDetail> getSpecialOfferlst() {
		return specialOfferlst;
	}

	public void setSpecialOfferlst(List<RetailerDetail> specialOfferlst) {
		this.specialOfferlst = specialOfferlst;
	}

	public Integer getProdSmaSeaId() {
		return prodSmaSeaId;
	}

	public void setProdSmaSeaId(Integer prodSmaSeaId) {
		this.prodSmaSeaId = prodSmaSeaId;
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

}
