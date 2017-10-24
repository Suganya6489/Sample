package com.hubciti.common.pojos;

import java.util.ArrayList;
import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * POJO class for retailers details
 * 
 * @author dhruvanath_mm
 */
public class RetailersDetails {

	private String responseCode;

	private String responseText;

	private Integer bottomBtn;

	private String appIcon;
	
	private String noRecordMsg;

	private String userMailId;

	private Integer maxCnt;

	private Integer nextPage;

	private Integer eventExist;

	private Integer fundExist;

	private String retGroupImg;

	private String retImage;

	private Integer retAffCount;

	private Integer retAffId;

	private String retAffName;

	private Integer retLocId;

	private Integer mainMenuId;

	private Float minRetDist;

	private String sortOrder;

	private String sortColumn;

	private String sortBy;

	private String groupBy;
	private Integer claimExist;

	private String claimTxtMsg;
	
	private List<Coupons> couponList;
	
	
	
	
	
	/**
	 * 
	 */
	private String pageTitle;
	/**
	 * 
	 */
	private String sDescription;
	
	private List<RetailersDetails> retTest;
	
	
	/**
	 * 
	 */
	private String pageDescription;
	
	/**
	 * @return the pageTitle
	 */
	public String getPageTitle() {
		return pageTitle;
	}

	/**
	 * @return the sDescription
	 */
	public String getsDescription() {
		return sDescription;
	}

	/**
	 * @return the pageDescription
	 */
	public String getPageDescription() {
		return pageDescription;
	}

	/**
	 * @return the lDescription
	 */
	public String getlDescription() {
		return lDescription;
	}

	/**
	 * @param pageTitle the pageTitle to set
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	/**
	 * @param sDescription the sDescription to set
	 */
	public void setsDescription(String sDescription) {
		this.sDescription = sDescription;
	}

	/**
	 * @param pageDescription the pageDescription to set
	 */
	public void setPageDescription(String pageDescription) {
		this.pageDescription = pageDescription;
	}

	/**
	 * @param lDescription the lDescription to set
	 */
	public void setlDescription(String lDescription) {
		this.lDescription = lDescription;
	}

	/**
	 * 
	 */
	private String lDescription;



	private List<Retailer> retList;
	
	
	


	/**
	 * @return the minRetDist
	 */
	public Float getMinRetDist() {
		return minRetDist;
	}

	/**
	 * @param minRetDist
	 *            the minRetDist to set
	 */
	public void setMinRetDist(Float minRetDist) {
		this.minRetDist = minRetDist;
	}

	/**
	 * for shareType
	 */
	private String shareType;
	/**
	 * for shareText
	 */
	private String shareText;
	/**
	 * for city
	 */
	private String city;
	/**
	 * for state
	 */
	private String state;
	/**
	 * for postalCode
	 */
	private String postalCode;
	/**
	 * for retailer url
	 */
	private String qrUrl;

	/**
	 * for retailer url
	 */
	private String retailerURL;

	private Integer retailerId;

	private Integer RetailLocationID;
	/**
	 * for retailer name
	 */
	private String retName;
	/**
	 * For retailer retailerAddress
	 */
	private String retailerAddress;
	
	/**
	 * for powered by
	 */
	private String poweredby;

	/**
	 * For find retailer search ID
	 */
	private Integer findRetSeaId;

	/**
	 * for scansee image.
	 */
	private String scanseeimg;

	/**
	 * for facebook image.
	 */
	private String facebookimg;

	/**
	 * for twitter image
	 */
	private String twitterimg;
	/**
	 * arrow scansee image.
	 */
	private String arrowscanseeimg;
	/**
	 * For city experience
	 */
	private Boolean cityExp;
	/**
	 * For retailGroupID
	 */
	private Integer retGroupID;

	private Integer maxRowNum;

	private Integer lastRowNum;
	
	private Integer mRow;

	/**
	 * for retailer id
	 */
	private Integer retId;

	/**
	 * retailerDetail for retailers list.
	 */
	private List<RetailerDetail> retailerDetail;
	/**
	 * couponDetailsList for retailers list.
	 */
	private ArrayList<CouponDetails> couponDetailsList;

	/**
	 * thisLocationRetailerInfo for thisLocationRetailers list.
	 */
	private List<Retailer> retailers;

	private List<RetailerCreatedPages> retailerCreatedPageList;

	private List<RetailersDetails> retDetailsList;

	private ArrayList<HotDealsDetails> hDDetailsList;

	private List<CategoryInfo> catList;
	
	
	
	private String Label;

	
	
	/**
	 * featuredSpecialList for retailers list.
	 */
	private List<RetailerDetail> featuredSpecialList;
	
	private List<RetailerDetail> featuredCouponsList;
	
	private List<RetailerDetail> claimedCouponsList;
	private List<RetailerDetail> redeemedCouponsList;
	private List<RetailerDetail> expiredCouponsList;
	
	/**
	 * nonFeaturedSpecialList for retailers list.
	 */
	private List<RetailerDetail> nonFeaturedSpecialList;
	
	private List<RetailerDetail> nonFeaturedCouponsList;
	
	private List<BottomButton> bottomBtnList;
	
	
	private List<RetailerDetail> couponMapLocs;
	
	private Boolean isBtmBtn;
	
	
	/**
	 * 
	 */

	public List<RetailerDetail> getRetailerDetail() {
		return retailerDetail;
	}

	public void setRetailerDetail(List<RetailerDetail> retailerDetail) {
		this.retailerDetail = retailerDetail;
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

	public String getRetGroupImg() {
		return retGroupImg;
	}

	public void setRetGroupImg(String retGroupImg) {
		this.retGroupImg = retGroupImg;
	}

	public Integer getRetAffCount() {
		return retAffCount;
	}

	public void setRetAffCount(Integer retAffCount) {
		this.retAffCount = retAffCount;
	}

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

	public List<Retailer> getRetailers() {
		return retailers;
	}

	public void setRetailers(List<Retailer> retailers) {
		this.retailers = retailers;
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public List<RetailerCreatedPages> getRetailerCreatedPageList() {
		return retailerCreatedPageList;
	}

	public void setRetailerCreatedPageList(
			List<RetailerCreatedPages> retailerCreatedPageList) {
		this.retailerCreatedPageList = retailerCreatedPageList;
	}

	public String getPoweredby() {
		return poweredby;
	}

	public void setPoweredby(String poweredby) {
		this.poweredby = poweredby;
	}

	public Integer getFindRetSeaId() {
		return findRetSeaId;
	}

	public void setFindRetSeaId(Integer findRetSeaId) {
		this.findRetSeaId = findRetSeaId;
	}

	/**
	 * @return the shareText
	 */
	public String getShareText() {
		return shareText;
	}

	/**
	 * @param shareText
	 *            the shareText to set
	 */
	public void setShareText(String shareText) {
		this.shareText = shareText;
	}

	/**
	 * @return the retImage
	 */
	public String getRetImage() {
		return retImage;
	}

	/**
	 * @param retImage
	 *            the retImage to set
	 */
	public void setRetImage(String retImage) {
		if (null != retImage) {
			this.retImage = retImage;
		} else {
			this.retImage = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public String getQrUrl() {
		return qrUrl;
	}

	public void setQrUrl(String qrUrl) {
		if (null == qrUrl) {
			this.qrUrl = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.qrUrl = qrUrl;
		}
	}

	/**
	 * @return the shareType
	 */
	public String getShareType() {
		return shareType;
	}

	/**
	 * @param shareType
	 *            the shareType to set
	 */
	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the retName
	 */
	public String getRetName() {
		return retName;
	}

	/**
	 * @param retName
	 *            the retName to set
	 */
	public void setRetName(String retName) {
		this.retName = retName;
	}

	/**
	 * @return the retailerAddress
	 */
	public String getRetailerAddress() {
		return retailerAddress;
	}

	/**
	 * @param retailerAddress
	 *            the retailerAddress to set
	 */
	public void setRetailerAddress(String retailerAddress) {
		this.retailerAddress = retailerAddress;
	}

	/**
	 * @return the scanseeimg
	 */
	public String getScanseeimg() {
		return scanseeimg;
	}

	/**
	 * @param scanseeimg
	 *            the scanseeimg to set
	 */
	public void setScanseeimg(String scanseeimg) {
		this.scanseeimg = scanseeimg;
	}

	/**
	 * @return the facebookimg
	 */
	public String getFacebookimg() {
		return facebookimg;
	}

	/**
	 * @param facebookimg
	 *            the facebookimg to set
	 */
	public void setFacebookimg(String facebookimg) {
		this.facebookimg = facebookimg;
	}

	/**
	 * @return the twitterimg
	 */
	public String getTwitterimg() {
		return twitterimg;
	}

	/**
	 * @param twitterimg
	 *            the twitterimg to set
	 */
	public void setTwitterimg(String twitterimg) {
		this.twitterimg = twitterimg;
	}

	/**
	 * @return the arrowscanseeimg
	 */
	public String getArrowscanseeimg() {
		return arrowscanseeimg;
	}

	/**
	 * @param arrowscanseeimg
	 *            the arrowscanseeimg to set
	 */
	public void setArrowscanseeimg(String arrowscanseeimg) {
		this.arrowscanseeimg = arrowscanseeimg;
	}

	/**
	 * @return the retailerURL
	 */
	public String getRetailerURL() {
		return retailerURL;
	}

	/**
	 * @param retailerURL
	 *            the retailerURL to set
	 */
	public void setRetailerURL(String retailerURL) {
		this.retailerURL = retailerURL;
	}

	/**
	 * @return the retailerId
	 */
	public Integer getRetailerId() {
		return retailerId;
	}

	/**
	 * @param retailerId
	 *            the retailerId to set
	 */
	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}

	/**
	 * @return the retailLocationID
	 */
	public Integer getRetailLocationID() {
		return RetailLocationID;
	}

	/**
	 * @param retailLocationID
	 *            the retailLocationID to set
	 */
	public void setRetailLocationID(Integer retailLocationID) {
		RetailLocationID = retailLocationID;
	}

	/**
	 * @return the cityExp
	 */
	public Boolean getCityExp() {
		return cityExp;
	}

	/**
	 * @param cityExp
	 *            the cityExp to set
	 */
	public void setCityExp(Boolean cityExp) {
		this.cityExp = cityExp;
	}

	/**
	 * @return the retGroupID
	 */
	public Integer getRetGroupID() {
		return retGroupID;
	}

	/**
	 * @param retGroupID
	 *            the retGroupID to set
	 */
	public void setRetGroupID(Integer retGroupID) {
		this.retGroupID = retGroupID;
	}

	public Integer getMaxRowNum() {
		return maxRowNum;
	}

	public void setMaxRowNum(Integer maxRowNum) {
		this.maxRowNum = maxRowNum;
	}

	/**
	 * @return the couponDetailsList
	 */
	public ArrayList<CouponDetails> getCouponDetailsList() {
		return couponDetailsList;
	}

	/**
	 * @param couponDetailsList
	 *            the couponDetailsList to set
	 */
	public void setCouponDetailsList(ArrayList<CouponDetails> couponDetailsList) {
		this.couponDetailsList = couponDetailsList;
	}

	/**
	 * @return the retId
	 */
	public Integer getRetId() {
		return retId;
	}

	/**
	 * @param retId
	 *            the retId to set
	 */
	public void setRetId(Integer retId) {
		this.retId = retId;
	}

	/**
	 * @return the retLocId
	 */
	public Integer getRetLocId() {
		return retLocId;
	}

	/**
	 * @param retLocId
	 *            the retLocId to set
	 */
	public void setRetLocId(Integer retLocId) {
		this.retLocId = retLocId;
	}

	/**
	 * @return the retDetailsList
	 */
	public List<RetailersDetails> getRetDetailsList() {
		return retDetailsList;
	}

	/**
	 * @param retDetailsList
	 *            the retDetailsList to set
	 */
	public void setRetDetailsList(List<RetailersDetails> retDetailsList) {
		this.retDetailsList = retDetailsList;
	}

	public ArrayList<HotDealsDetails> gethDDetailsList() {
		return hDDetailsList;
	}

	public void sethDDetailsList(ArrayList<HotDealsDetails> hDDetailsList) {
		this.hDDetailsList = hDDetailsList;
	}

	public List<CategoryInfo> getCatList() {
		return catList;
	}

	public void setCatList(List<CategoryInfo> catList) {
		this.catList = catList;
	}

	/**
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder
	 *            the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the sortColumn
	 */
	public String getSortColumn() {
		return sortColumn;
	}

	/**
	 * @param sortColumn
	 *            the sortColumn to set
	 */
	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	/**
	 * @return the sortBy
	 */
	public String getSortBy() {
		return sortBy;
	}

	/**
	 * @param sortBy
	 *            the sortBy to set
	 */
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	/**
	 * @return the groupBy
	 */
	public String getGroupBy() {
		return groupBy;
	}

	/**
	 * @param groupBy
	 *            the groupBy to set
	 */
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public List<Retailer> getRetList() {
		return retList;
	}

	public void setRetList(List<Retailer> retList) {
		this.retList = retList;
	}

	/**
	 * @return the bottomBtnList
	 */
	public List<BottomButton> getBottomBtnList() {
		return bottomBtnList;
	}

	/**
	 * @param bottomBtnList
	 *            the bottomBtnList to set
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

	public Integer getEventExist() {
		return eventExist;
	}

	public void setEventExist(Integer eventExist) {
		this.eventExist = eventExist;
	}

	/**
	 * @return the fundExist
	 */
	public Integer getFundExist() {
		return fundExist;
	}

	/**
	 * @param fundExist the fundExist to set
	 */
	public void setFundExist(Integer fundExist) {
		this.fundExist = fundExist;
	}

	/**
	 * @return the lastRowNum
	 */
	public Integer getLastRowNum() {
		return lastRowNum;
	}

	/**
	 * @param lastRowNum the lastRowNum to set
	 */
	public void setLastRowNum(Integer lastRowNum) {
		this.lastRowNum = lastRowNum;
	}

	/**
	 * @return the appIcon
	 */
	public String getAppIcon() {
		return appIcon;
	}

	/**
	 * @param appIcon the appIcon to set
	 */
	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
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

	/**
	 * @return the claimExist
	 */
	public Integer getClaimExist() {
		return claimExist;
	}

	/**
	 * @param claimExist the claimExist to set
	 */
	public void setClaimExist(Integer claimExist) {
		this.claimExist = claimExist;
	}

	/**
	 * @return the claimTxtMsg
	 */
	public String getClaimTxtMsg() {
		return claimTxtMsg;
	}

	/**
	 * @param claimTxtMsg the claimTxtMsg to set
	 */
	public void setClaimTxtMsg(String claimTxtMsg) {
		this.claimTxtMsg = claimTxtMsg;
	}

	/**
	 * @return the userMailId
	 */
	public String getUserMailId() {
		return userMailId;
	}

	/**
	 * @param userMailId the userMailId to set
	 */
	public void setUserMailId(String userMailId) {
		this.userMailId = userMailId;
	}

	

	/**
	 * @return the featuredSpecialList
	 */
	public List<RetailerDetail> getFeaturedSpecialList() {
		return featuredSpecialList;
	}

	/**
	 * @param featuredSpecialList the featuredSpecialList to set
	 */
	public void setFeaturedSpecialList(List<RetailerDetail> featuredSpecialList) {
		this.featuredSpecialList = featuredSpecialList;
	}

	/**
	 * @return the nonFeaturedSpecialList
	 */
	public List<RetailerDetail> getNonFeaturedSpecialList() {
		return nonFeaturedSpecialList;
	}

	/**
	 * @param nonFeaturedSpecialList the nonFeaturedSpecialList to set
	 */
	public void setNonFeaturedSpecialList(List<RetailerDetail> nonFeaturedSpecialList) {
		this.nonFeaturedSpecialList = nonFeaturedSpecialList;
	}

	public List<RetailerDetail> getFeaturedCouponsList() {
		return featuredCouponsList;
	}

	public void setFeaturedCouponsList(List<RetailerDetail> featuredCouponsList) {
		this.featuredCouponsList = featuredCouponsList;
	}

	public List<RetailerDetail> getNonFeaturedCouponsList() {
		return nonFeaturedCouponsList;
	}

	public void setNonFeaturedCouponsList(List<RetailerDetail> nonFeaturedCouponsList) {
		this.nonFeaturedCouponsList = nonFeaturedCouponsList;
	}

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public List<RetailerDetail> getCouponMapLocs() {
		return couponMapLocs;
	}

	public void setCouponMapLocs(List<RetailerDetail> couponMapLocs) {
		this.couponMapLocs = couponMapLocs;
	}

	public Integer getmRow() {
		return mRow;
	}

	public void setmRow(Integer mRow) {
		this.mRow = mRow;
	}

	public List<RetailerDetail> getClaimedCouponsList() {
		return claimedCouponsList;
	}

	public void setClaimedCouponsList(List<RetailerDetail> claimedCouponsList) {
		this.claimedCouponsList = claimedCouponsList;
	}

	public List<RetailerDetail> getRedeemedCouponsList() {
		return redeemedCouponsList;
	}

	public void setRedeemedCouponsList(List<RetailerDetail> redeemedCouponsList) {
		this.redeemedCouponsList = redeemedCouponsList;
	}

	public List<RetailerDetail> getExpiredCouponsList() {
		return expiredCouponsList;
	}

	public void setExpiredCouponsList(List<RetailerDetail> expiredCouponsList) {
		this.expiredCouponsList = expiredCouponsList;
	}

	public List<RetailersDetails> getRetTest() {
		return retTest;
	}

	public void setRetTest(List<RetailersDetails> retTest) {
		this.retTest = retTest;
	}

	/**
	 * @return the couponList
	 */
	public List<Coupons> getCouponList() {
		return couponList;
	}

	/**
	 * @param couponList the couponList to set
	 */
	public void setCouponList(List<Coupons> couponList) {
		this.couponList = couponList;
	}

	public Boolean getIsBtmBtn() {
		return isBtmBtn;
	}

	public void setIsBtmBtn(Boolean isBtmBtn) {
		this.isBtmBtn = isBtmBtn;
	}

	

	
	
}
