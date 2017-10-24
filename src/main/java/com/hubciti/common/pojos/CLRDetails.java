package com.hubciti.common.pojos;

import java.util.List;

/**
 * pojo for CLRDetails.
 * 
 * @author Kumar D
 */
public class CLRDetails {
	private String responseCode;

	private String responseText;

	private Integer hubCitiId;

	private Integer nextPageFlag;
	/**
	 * for coupon next page
	 */
	private Integer clrC;
	/**
	 * for rebate next page
	 */
	private Integer clrR;
	/**
	 * for loyalty next page
	 */
	private Integer clrL;

	/**
	 * for couponId.
	 */
	private Integer couponId;
	/**
	 * for rebateId.
	 */
	private Integer rebateId;
	/**
	 * for loyaltyId.
	 */
	private Integer loyaltyDealId;
	/**
	 * for productId.
	 */
	private Integer productId;
	/**
	 * for userId.
	 */
	private Integer userId;
	/**
	 * for retailerId.
	 */
	private Integer retailerId;

	/**
	 * Variable nextPage declared as of type Integer.
	 */
	private Integer nextPage;

	/**
	 * Variable galleryFlag declared as of type String.
	 */
	private String type;

	/**
	 * for lowerLimit.
	 */
	private Integer lowerLimit;

	/**
	 * variable declare couponDetails as List.
	 */
	protected List<CouponDetails> couponDetails;

	/**
	 * for searching coupon , loyalty.
	 */
	private String searchKey;

	/**
	 * for category id
	 */
	private Integer categoryId;

	/**
	 * for isCouponthere is there or not..
	 */
	private Boolean isCouponthere;

	/**
	 * for rebate is there or not..
	 */
	private Boolean isRebatethere;

	/**
	 * for rebate is there or not..
	 */
	private Boolean isLoyaltythere;

	/**
	 * for retailer name
	 */
	private String retName;
	/**
	 * for RetailerImagePath
	 */
	private String retImagePath;

	private List<RetailerDetail> loygrpbyRetlst = null;

	/**
	 * for retailer details list
	 */
	private List<RetailersDetails> loycatretgrplst = null;

	/**
	 * for user tracking
	 */
	private Integer couponListId;

	/**
	 * for user tracking
	 */
	private Integer loyaltyListID;

	/**
	 * for user tracking
	 */
	private Integer rebateListID;

	/**
	 * for user tracking
	 */
	private Integer mainMenuId;

	/**
	 * for user tracking
	 */
	private String retListID;

	/**
	 * for module ID
	 */
	private Integer moduleID;

	/**
	 * For latitude
	 */
	private Double lat;

	/**
	 * for longitude
	 */
	private Double lng;

	/**
	 * For zipcode
	 */
	private String zipcode;

	/**
	 * for getting isCouponthere.
	 * 
	 * @return the isCouponthere
	 */
	public Boolean getIsCouponthere() {
		return isCouponthere;
	}

	/**
	 * for setting isCouponthere.
	 * 
	 * @param isCouponthere
	 *            the isCouponthere to set
	 */
	public void setIsCouponthere(Boolean isCouponthere) {
		this.isCouponthere = isCouponthere;
	}

	/**
	 * for getting isRebatethere.
	 * 
	 * @return the isRebatethere
	 */
	public Boolean getIsRebatethere() {
		return isRebatethere;
	}

	/**
	 * for setting isRebatethere.
	 * 
	 * @param isRebatethere
	 *            the isRebatethere to set
	 */
	public void setIsRebatethere(Boolean isRebatethere) {
		this.isRebatethere = isRebatethere;
	}

	/**
	 * for getting isLoyaltythere.
	 * 
	 * @return the isLoyaltythere
	 */
	public Boolean getIsLoyaltythere() {
		return isLoyaltythere;
	}

	/**
	 * for setting isLoyaltythere.
	 * 
	 * @param isLoyaltythere
	 *            the isLoyaltythere to set
	 */
	public void setIsLoyaltythere(Boolean isLoyaltythere) {
		this.isLoyaltythere = isLoyaltythere;
	}

	/**
	 * for getting getCouponDetails list.
	 * 
	 * @return couponDetails
	 */
	public List<CouponDetails> getCouponDetails() {
		return couponDetails;
	}

	/**
	 * for setting couponDetails.
	 * 
	 * @param couponDetails
	 *            set as couponDetails
	 */
	public void setCouponDetails(List<CouponDetails> couponDetails) {

		this.couponDetails = couponDetails;
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
	 * to fetch couponId.
	 * 
	 * @return the couponId
	 */
	public Integer getCouponId() {
		return couponId;
	}

	/**
	 * to set couponId.
	 * 
	 * @param couponId
	 *            the couponId to set
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	/**
	 * to fetch rebateId.
	 * 
	 * @return the rebateId
	 */
	public Integer getRebateId() {
		return rebateId;
	}

	/**
	 * to set rebateId.
	 * 
	 * @param rebateId
	 *            the rebateId to set
	 */
	public void setRebateId(Integer rebateId) {
		this.rebateId = rebateId;
	}

	/**
	 * to fetch productId.
	 * 
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * to set productId.
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * to fetch userId.
	 * 
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * to get userId.
	 * 
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * to get retailerId.
	 * 
	 * @return the retailerId
	 */
	public Integer getRetailerId() {
		return retailerId;
	}

	/**
	 * to set retailerId.
	 * 
	 * @param retailerId
	 *            the retailerId to set
	 */
	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}

	/**
	 * for getting lowerLimit.
	 * 
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * for setting lowerLimit.
	 * 
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public void setLastVistedProductNo(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * for getting type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * for setting type.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the clrC
	 */
	public Integer getClrC() {
		return clrC;
	}

	/**
	 * @return the loyaltyDealID
	 */
	public Integer getLoyaltyDealID() {
		return loyaltyDealId;
	}

	/**
	 * @param loyaltyDealId
	 *            the loyaltyDealId to set
	 */
	public void setLoyaltyDealID(Integer loyaltyDealId) {
		this.loyaltyDealId = loyaltyDealId;
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
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @param clrC
	 *            the clrC to set
	 */
	public void setClrC(Integer clrC) {
		this.clrC = clrC;
	}

	/**
	 * @return the clrR
	 */
	public Integer getClrR() {
		return clrR;
	}

	/**
	 * @param clrR
	 *            the clrR to set
	 */
	public void setClrR(Integer clrR) {
		this.clrR = clrR;
	}

	/**
	 * @return the clrL
	 */
	public Integer getClrL() {
		return clrL;
	}

	/**
	 * @param clrL
	 *            the clrL to set
	 */
	public void setClrL(Integer clrL) {
		this.clrL = clrL;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getRetName() {
		return retName;
	}

	public void setRetName(String retName) {
		this.retName = retName;
	}

	public String getRetImagePath() {
		return retImagePath;
	}

	public void setRetImagePath(String retImagePath) {
		this.retImagePath = retImagePath;
	}

	public List<RetailerDetail> getLoygrpbyRetlst() {
		return loygrpbyRetlst;
	}

	public void setLoygrpbyRetlst(List<RetailerDetail> loygrpbyRetlst) {
		this.loygrpbyRetlst = loygrpbyRetlst;
	}

	public List<RetailersDetails> getLoycatretgrplst() {
		return loycatretgrplst;
	}

	public void setLoycatretgrplst(List<RetailersDetails> loycatretgrplst) {
		this.loycatretgrplst = loycatretgrplst;
	}

	/**
	 * For user tracking, to get couponListId
	 * 
	 * @return Integer couponListId
	 */
	public Integer getCouponListId() {
		return couponListId;
	}

	/**
	 * For user tracking, to set couponListId
	 * 
	 * @param couponListId
	 */
	public void setCouponListId(Integer couponListId) {
		this.couponListId = couponListId;
	}

	/**
	 * For user tracking, to get loyaltyListId
	 * 
	 * @return Integer loyaltyListId
	 */
	public Integer getLoyaltyListID() {
		return loyaltyListID;
	}

	/**
	 * For user tracking, to set loyaltyListId
	 * 
	 * @param loyaltyListId
	 */
	public void setLoyaltyListID(Integer loyaltyListID) {
		this.loyaltyListID = loyaltyListID;
	}

	/**
	 * For user tracking, to get rebateListId
	 * 
	 * @return Integer rebateListId
	 */
	public Integer getRebateListID() {
		return rebateListID;
	}

	/**
	 * For user tracking, to set rebateListId
	 * 
	 * @param rebateListId
	 */
	public void setRebateListID(Integer rebateListID) {
		this.rebateListID = rebateListID;
	}

	/**
	 * For user tracking, to get mainMenuId
	 * 
	 * @return Integer mainMenuId
	 */
	public Integer getMainMenuId() {
		return mainMenuId;
	}

	/**
	 * For user tracking, to set mainMenuId
	 * 
	 * @param mainMenuId
	 */
	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public String getRetListID() {
		return retListID;
	}

	public void setRetListID(String retListID) {
		this.retListID = retListID;
	}

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
}
