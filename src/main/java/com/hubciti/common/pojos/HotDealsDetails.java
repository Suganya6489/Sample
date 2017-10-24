package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * The POJO class for HotDealsDetails.
 * 
 * @author shyamsundara_hm.
 */
public class HotDealsDetails {

	private String responseCode;

	private String responseText;
	/**
	 * for hot deal Id
	 */
	private Integer productHotDealId;
	/**
	 * for coupon share info through twitter
	 */
	private String titleText;

	/**
	 * for coupon share info
	 */
	private String titleText2;
	/**
	 * for city
	 */
	private String city;
	/**
	 * for hot deal expired
	 */
	private Integer hotDealExpired;
	/**
	 * for api image path.
	 */
	private String apiPartnerImagePath;
	/**
	 * for rowNumber.
	 * 
	 * @return the rowNumber
	 */
	private Integer rowNumber;

	/**
	 * Variable apiPartnerId declared as Integer.
	 */
	private Integer apiPartnerId;
	/**
	 * Variable apiPartnerName declared as String.
	 */
	private String apiPartnerName;
	/**
	 * Variable hotDealName declared as String.
	 */
	private String hotDealName;
	/**
	 * Variable hotDealId declared as Integer.
	 */
	private Integer hotDealId;
	/**
	 * Variable hotDealImagePath declared as String.
	 */
	private String hotDealImagePath;


	/**
	 * Variable hDshortDescription declared as String.
	 */
	private String hDshortDescription;
	/**
	 * Variable hDLognDescription declared as String.
	 */
	private String hDLognDescription;
	/**
	 * Variable hDPrice declared as String.
	 */
	private String hDPrice;
	/**
	 * Variable hDSalePrice declared as String.
	 */
	private String hDSalePrice;
	/**
	 * Variable hDTermsConditions declared as String.
	 */
	private String hDTermsConditions;
	/**
	 * Variable hdURL declared as String.
	 */
	private String hdURL;
	/**
	 * Variable hDStartDate declared as String.
	 */
	private String hDStartDate;
	/**
	 * Variable hDEndDate declared as String.
	 */
	private String hDEndDate;
	/**
	 * Variable hDDiscountType declared as String.
	 */
	private String hDDiscountType;
	/**
	 * Variable hDDiscountAmount declared as double.
	 */
	private String hDDiscountAmount;
	/**
	 * Variable hDDiscountPct declared as double.
	 */
	private String hDDiscountPct;
	/**
	 * Variable productId declared as Integer.
	 */
	private Integer productId;

	/**
	 * The rowNumber property.
	 */

	private String distance;

	/**
	 * for hot deal category Id.
	 */
	private Integer cateId;
	/**
	 * for hot deal category name
	 */
	private String catName;

	/**
	 * for facebook image.
	 */
	private String facebookimg;

	/**
	 * for scansee image.
	 */
	private String scanseeimg;
	/**
	 * for twitter image
	 */
	private String twitterimg;
	/**
	 * arrow scansee image.
	 */
	private String arrowscanseeimg;

	/**
	 * For retailer list Id
	 */
	private Integer retListId;

	/**
	 * for hot deal list Id
	 */
	private Integer hotDealListId;

	/**
	 * For new Flag
	 */
	Integer newFlag;

	/**
	 * For external flag
	 */
	Integer extFlag;

	/**
	 * For claim flag
	 */
	Integer claimFlag;

	/**
	 * For redeem flag
	 */
	Integer redeemFlag;

	/**
	 * For user Name
	 */
	String userName;

	/**
	 * For retailer name
	 */
	String retName;

	/**
	 * For retailer location Ids
	 */
	String retLocIds;

	/**
	 * For retialer location
	 */
	String retLocs;

	/**
	 * Variable for hot deal expire date
	 */
	private String hDExpDate;

	/**
	 * For coupon code
	 */
	private String coupCode;

	private String hDGallId;

	private String hDDesc;

	private Integer usedFlag;

	private Integer catId;

	private Integer retId;

	private String address;

	private Integer retLocId;

	private Boolean isDateFormated;

	private List<HotDealsDetails> hdDetails;

	private Integer userId;

	private Integer hubCitiId;

	private Integer expired;
	
	private String shareText;

	private String qrUrl;
	
	private Integer retailId;
	
	private String retLocImgLogo;
	/**
	 * for latitude.
	 */
	private String latitude;

	/**
	 * for longitude.
	 */
	private String longitude;

	/**
	 * To get distance.
	 * 
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * To set distance.
	 * 
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * To get rowNumber.
	 * 
	 * @return rowNumber To get
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}

	/**
	 * To set rowNumber.
	 * 
	 * @param rowNumber
	 *            the rowNumber to set
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * Gets the value of the hDPrice property.
	 * 
	 * @return the hDPrice
	 */
	public String gethDPrice() {
		return hDPrice;
	}

	/**
	 * Sets the value of the hDPrice property.
	 * 
	 * @param hDPrice
	 *            as of type String.
	 */

	public void sethDPrice(String hDPrice) {
		if (null == hDPrice || hDPrice.contains(HubCitiConstants.NOTAPPLICABLE)) {
			this.hDPrice = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (!hDPrice.contains("$")) {
				this.hDPrice = "$" + Utility.formatDecimalValue(hDPrice);
			} else {
				this.hDPrice = hDPrice;
			}
	}

	/**
	 * Gets the value of the hDSalePrice property.
	 * 
	 * @return the hDSalePrice
	 */
	public String gethDSalePrice() {
		return hDSalePrice;
	}

	/**
	 * Sets the value of the hDSalePrice property.
	 * 
	 * @param hDSalePrice
	 *            as of type String.
	 */
	public void sethDSalePrice(String hDSalePrice) {
		if (null == hDSalePrice || hDSalePrice.contains(HubCitiConstants.NOTAPPLICABLE)) {
			this.hDSalePrice = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (!hDSalePrice.contains("$")) {
				this.hDSalePrice = "$" + Utility.formatDecimalValue(hDSalePrice);
			} else {
				this.hDSalePrice = hDSalePrice;
			}
	}

	/**
	 * Gets the value of the productId property.
	 * 
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * Sets the value of the productId property.
	 * 
	 * @param productId
	 *            as of type String.
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * Gets the value of the hDDiscountType property.
	 * 
	 * @return the hDDiscountType
	 */
	public String gethDDiscountType() {
		return hDDiscountType;
	}

	/**
	 * Sets the value of the hDDiscountType property.
	 * 
	 * @param hDDiscountType
	 *            as of type String.
	 */
	public void sethDDiscountType(String hDDiscountType) {
		if (null == hDDiscountType) {
			this.hDDiscountType = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDDiscountType = hDDiscountType;
		}
	}

	/**
	 * Gets the value of the hotDealImagePath property.
	 * 
	 * @return the hotDealImagePath
	 */
	public String getHotDealImagePath() {
		return hotDealImagePath;
	}

	/**
	 * Sets the value of the hotDealImagePath property.
	 * 
	 * @param hotDealImagePath
	 *            as of type String.
	 */
	public void setHotDealImagePath(String hotDealImagePath) {
		if (null == hotDealImagePath) {
			this.hotDealImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.hotDealImagePath = hotDealImagePath;
		}

	}

	/**
	 * Gets the value of the apiPartnerId property.
	 * 
	 * @return the apiPartnerId
	 */
	public Integer getApiPartnerId() {
		return apiPartnerId;
	}

	/**
	 * Sets the value of the apiPartnerId property.
	 * 
	 * @param apiPartnerId
	 *            as of type String.
	 */
	public void setApiPartnerId(Integer apiPartnerId) {
		this.apiPartnerId = apiPartnerId;
	}

	/**
	 * Gets the value of the apiPartnerName property.
	 * 
	 * @return the apiPartnerName
	 */
	public String getApiPartnerName() {
		return apiPartnerName;
	}

	/**
	 * Sets the value of the apiPartnerName property.
	 * 
	 * @param apiPartnerName
	 *            as of type String.
	 */
	public void setApiPartnerName(String apiPartnerName) {
		if (null == apiPartnerName) {
			this.apiPartnerName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.apiPartnerName = apiPartnerName;
		}
	}

	/**
	 * Gets the value of the hotDealName property.
	 * 
	 * @return the hotDealName
	 */
	public String getHotDealName() {
		return hotDealName;
	}

	/**
	 * Sets the value of the hotDealName property.
	 * 
	 * @param hotDealName
	 *            as of type String.
	 */
	public void setHotDealName(String hotDealName) {
		// hotDealName = Utility.removeHTMLTags(hotDealName);
		if (null == hotDealName) {
			this.hotDealName = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (hotDealName.contains("<![CDATA[")) {
				this.hotDealName = hotDealName;
			} else {
				this.hotDealName = "<![CDATA[" + hotDealName + "]]>";
			}

	}

	/**
	 * Gets the value of the hotDealId property.
	 * 
	 * @return the hotDealId
	 */
	public Integer getHotDealId() {
		return hotDealId;
	}

	/**
	 * Sets the value of the hotDealId property.
	 * 
	 * @param hotDealId
	 *            as of type String.
	 */
	public void setHotDealId(Integer hotDealId) {
		this.hotDealId = hotDealId;
	}

	/**
	 * Gets the value of the hDshortDescription property.
	 * 
	 * @return the hDshortDescription
	 */
	public String gethDshortDescription() {
		return hDshortDescription;
	}

	/**
	 * Sets the value of the hDshortDescription property.
	 * 
	 * @param hDshortDescription
	 *            as of type String.
	 */
	public void sethDshortDescription(String hDshortDescription) {
		// hDshortDescription = Utility.removeHTMLTags(hDshortDescription);

		if (null == hDshortDescription || "".equals(hDshortDescription)) {
			this.hotDealName = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (hDshortDescription.contains("<![CDATA[")) {
				this.hDshortDescription = hDshortDescription;
			} else {
				this.hDshortDescription = "<![CDATA[" + hDshortDescription + "]]>";
			}
	}

	/**
	 * Gets the value of the hDLognDescription property.
	 * 
	 * @return the hDLognDescription
	 */
	public String gethDLognDescription() {
		return hDLognDescription;
	}

	/**
	 * Sets the value of the hDLognDescription property.
	 * 
	 * @param hDLognDescription
	 *            as of type String.
	 */
	public void sethDLognDescription(String hDLognDescription) {
		// hDLognDescription = Utility.removeHTMLTags(hDLognDescription);
		// hDLognDescription = hDLognDescription.replaceAll("&#xd;", "");
		if (null == hDLognDescription || "".equals(hDLognDescription)) {
			this.hDLognDescription = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (hDLognDescription.contains("<![CDATA[")) {
				this.hDLognDescription = hDLognDescription;
			} else {
				this.hDLognDescription = "<![CDATA[" + hDLognDescription + "]]>";
			}

	}

	/**
	 * Gets the value of the hDTermsConditions property.
	 * 
	 * @return the hDTermsConditions
	 */
	public String gethDTermsConditions() {
		return hDTermsConditions;
	}

	/**
	 * Sets the value of the hDTermsConditions property.
	 * 
	 * @param hDTermsConditions
	 *            as of type String.
	 */
	public void sethDTermsConditions(String hDTermsConditions) {
		// hDTermsConditions = Utility.removeHTMLTags(hDTermsConditions);
		// hDTermsConditions = hDTermsConditions.replaceAll("&#xd;", "");

		if (null == hDTermsConditions || "".equals(hDTermsConditions)) {
			this.hDTermsConditions = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (hDTermsConditions.contains("<![CDATA[")) {
				this.hDTermsConditions = hDTermsConditions;
			} else {
				this.hDTermsConditions = "<![CDATA[" + hDTermsConditions + "]]>";
			}

		}

	}

	/**
	 * Gets the value of the hdURL property.
	 * 
	 * @return the hdURL
	 */
	public String getHdURL() {
		return hdURL;
	}

	/**
	 * Sets the value of the hdURL property.
	 * 
	 * @param hdURL
	 *            as of type String.
	 */
	public void setHdURL(String hdURL) {
		if (null == hdURL || hdURL.equals("")) {
			this.hdURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hdURL = hdURL;
		}
	}

	/**
	 * Gets the value of the hDStartDate property.
	 * 
	 * @return the hDStartDate
	 */
	public String gethDStartDate() {
		return hDStartDate;
	}

	/**
	 * Sets the value of the hDStartDate property.
	 * 
	 * @param hDStartDate
	 *            as of type String.
	 */
	public void sethDStartDate(String hDStartDate) {
		if (null != hDStartDate) {
			if (null != isDateFormated && isDateFormated == false) {
				this.hDStartDate = Utility.convertHDealDBdate(hDStartDate);
			} else {
				this.hDStartDate = hDStartDate;
			}
		} else {
			this.hDStartDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * Gets the value of the hDEndDate property.
	 * 
	 * @return the hDEndDate
	 */
	public String gethDEndDate() {
		return hDEndDate;
	}

	/**
	 * Sets the value of the hDEndDate property.
	 * 
	 * @param hDEndDate
	 *            as of type String.
	 */
	public void sethDEndDate(String hDEndDate) {
		if (hDEndDate != null && !hDEndDate.equals(HubCitiConstants.NOTAPPLICABLE)) {
			if (null != isDateFormated && isDateFormated == false) {
				this.hDEndDate = Utility.convertHDealDBdate(hDEndDate);
			} else {
				this.hDEndDate = hDEndDate;
			}
		} else {
			this.hDEndDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * gets the value of hDDiscountAmount.
	 * 
	 * @return the hDDiscountAmount
	 */
	public String gethDDiscountAmount() {
		return hDDiscountAmount;
	}

	/**
	 * sets the value of hDDiscountAmount.
	 * 
	 * @param hDDiscountAmount
	 *            the hDDiscountAmount to set
	 */
	public void sethDDiscountAmount(String hDDiscountAmount) {
		if (null == hDDiscountAmount) {
			this.hDDiscountAmount = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDDiscountAmount = hDDiscountAmount;
		}
	}

	/**
	 * gets the value of hDDiscountPct.
	 * 
	 * @return the hDDiscountPct
	 */
	public String gethDDiscountPct() {
		return hDDiscountPct;
	}

	/**
	 * sets the value of hDDiscountPct.
	 * 
	 * @param hDDiscountPct
	 *            the hDDiscountPct to set
	 */
	public void sethDDiscountPct(String hDDiscountPct) {
		if (null == hDDiscountPct) {
			this.hDDiscountPct = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDDiscountPct = hDDiscountPct;
		}
	}

	/**
	 * @return the apiPartnerImagePath
	 */
	public String getApiPartnerImagePath() {
		return apiPartnerImagePath;
	}

	/**
	 * @param apiPartnerImagePath
	 *            the apiPartnerImagePath to set
	 */
	public void setApiPartnerImagePath(String apiPartnerImagePath) {
		if (apiPartnerImagePath == null || "".equals(apiPartnerImagePath)) {
			this.apiPartnerImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.apiPartnerImagePath = apiPartnerImagePath;
		}
	}

	public Integer getHotDealExpired() {
		return hotDealExpired;
	}

	public void setHotDealExpired(Integer hotDealExpired) {
		this.hotDealExpired = hotDealExpired;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {

		this.city = city;

	}

	public String getTitleText() {
		return titleText;
	}

	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	public String getTitleText2() {
		return titleText2;
	}

	public void setTitleText2(String titleText2) {
		this.titleText2 = titleText2;
	}

	public Integer getProductHotDealId() {
		return productHotDealId;
	}

	public void setProductHotDealId(Integer productHotDealId) {
		this.productHotDealId = productHotDealId;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getFacebookimg() {
		return facebookimg;
	}

	public void setFacebookimg(String facebookimg) {
		this.facebookimg = facebookimg;
	}

	public String getScanseeimg() {
		return scanseeimg;
	}

	public void setScanseeimg(String scanseeimg) {
		this.scanseeimg = scanseeimg;
	}

	public String getTwitterimg() {
		return twitterimg;
	}

	public void setTwitterimg(String twitterimg) {
		this.twitterimg = twitterimg;
	}

	public String getArrowscanseeimg() {
		return arrowscanseeimg;
	}

	public void setArrowscanseeimg(String arrowscanseeimg) {
		this.arrowscanseeimg = arrowscanseeimg;
	}

	public Integer getHotDealListId() {
		return hotDealListId;
	}

	public void setHotDealListId(Integer hotDealListId) {
		this.hotDealListId = hotDealListId;
	}

	public Integer getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(Integer newFlag) {
		this.newFlag = newFlag;
	}

	public Integer getExtFlag() {
		return extFlag;
	}

	public void setExtFlag(Integer extFlag) {
		this.extFlag = extFlag;
	}

	public Integer getClaimFlag() {
		return claimFlag;
	}

	public void setClaimFlag(Integer claimFlag) {
		this.claimFlag = claimFlag;
	}

	public Integer getRedeemFlag() {
		return redeemFlag;
	}

	public void setRedeemFlag(Integer redeemFlag) {
		this.redeemFlag = redeemFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if (null == userName || "".equalsIgnoreCase(userName)) {
			this.userName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.userName = userName;
		}
	}

	public String getRetName() {
		return retName;
	}

	public void setRetName(String retName) {
		this.retName = retName;
	}

	public String getRetLocIds() {
		return retLocIds;
	}

	public void setRetLocIds(String retLocIds) {
		this.retLocIds = retLocIds;
	}

	public String getRetLocs() {
		return retLocs;
	}

	public void setRetLocs(String retLocs) {
		this.retLocs = retLocs;
	}

	public String gethDExpDate() {
		return hDExpDate;
	}

	public void sethDExpDate(String hDExpDate) {
		if (null != hDExpDate) {
			if (null != isDateFormated && isDateFormated == false) {
				this.hDExpDate = Utility.convertHDealDBdate(hDExpDate);
			} else {
				this.hDExpDate = hDExpDate;
			}
		} else {
			this.hDExpDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public String getCoupCode() {
		return coupCode;
	}

	public void setCoupCode(String coupCode) {
		if (null != coupCode) {
			this.coupCode = coupCode;
		} else {
			this.coupCode = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public String gethDGallId() {
		return hDGallId;
	}

	public void sethDGallId(String hDGallId) {
		this.hDGallId = hDGallId;
	}

	public String gethDDesc() {
		return hDDesc;
	}

	public void sethDDesc(String hDDesc) {
		if (null != hDDesc) {
			this.hDDesc = hDDesc;
		} else {
			this.hDDesc = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public Integer getUsedFlag() {
		return usedFlag;
	}

	public void setUsedFlag(Integer usedFlag) {
		this.usedFlag = usedFlag;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getRetId() {
		return retId;
	}

	public void setRetId(Integer retId) {
		this.retId = retId;
	}

	public Integer getRetListId() {
		return retListId;
	}

	public void setRetListId(Integer retListId) {
		this.retListId = retListId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (null != address) {
			this.address = address;
		} else {
			this.address = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public Integer getRetLocId() {
		return retLocId;
	}

	public void setRetLocId(Integer retLocId) {
		this.retLocId = retLocId;
	}

	public Boolean getIsDateFormated() {
		return isDateFormated;
	}

	public void setIsDateFormated(Boolean isDateFormated) {
		this.isDateFormated = isDateFormated;
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

	public List<HotDealsDetails> getHdDetails() {
		return hdDetails;
	}

	public void setHdDetails(List<HotDealsDetails> hdDetails) {
		this.hdDetails = hdDetails;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public Integer getExpired() {
		return expired;
	}

	public void setExpired(Integer expired) {
		this.expired = expired;
	}

	/**
	 * @return the shareText
	 */
	public String getShareText() {
		return shareText;
	}

	/**
	 * @param shareText the shareText to set
	 */
	public void setShareText(String shareText) {
		this.shareText = shareText;
	}

	/**
	 * @return the qrUrl
	 */
	public String getQrUrl() {
		return qrUrl;
	}

	/**
	 * @param qrUrl the qrUrl to set
	 */
	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the retailId
	 */
	public Integer getRetailId() {
		return retailId;
	}

	/**
	 * @return the retLocImgLogo
	 */
	public String getRetLocImgLogo() {
		return retLocImgLogo;
	}

	/**
	 * @param retailId the retailId to set
	 */
	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * @param retLocImgLogo the retLocImgLogo to set
	 */
	public void setRetLocImgLogo(String retLocImgLogo) {
		this.retLocImgLogo = retLocImgLogo;
	}


}
