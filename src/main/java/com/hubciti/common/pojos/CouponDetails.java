package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

public class CouponDetails {

	private String responseCode;

	private String responseText;
	/**
	 * for ViewableOnWeb to check coupon is added from web
	 */
	private Boolean viewableOnWeb;
	/**
	 * for ViewableOnWeb to check coupon is added from web
	 */
	private Integer viewOnWeb;
	/**
	 * coupon CouponTermsAndConditions
	 */
	private String termsAndConditions;
	/**
	 * coupon termAndConditions
	 */
	private String termAndConditions;
	/**
	 * for used flag
	 */
	private Integer usedFlag;
	/**
	 * for user gallery coupon Id
	 */
	private Integer userCouponGalleryId;
	/**
	 * for coupon share info through twitter
	 */
	private String titleText;
	
	private Boolean isFeatured;

	/**
	 * for coupon share info
	 */
	private String titleText2;

	/**
	 * for userId
	 */
	private Integer userId;
	/**
	 * for user coupon claim type Id
	 */
	private Integer userCouponClaimTypeId;
	/**
	 * for CouponPayoutMethod
	 */
	private String couponPayoutMethod;
	/**
	 * for RetailLocationId
	 */
	private Integer retailLocationId;
	/**
	 * for CouponClaimDate
	 */
	private String couponClaimDate;
	/**
	 * for coupon url
	 */

	private String couponURL;
	/**
	 * for coupon expired or not
	 */
	private Integer couponExpired;
	/**
	 * for product Id.
	 */
	private Integer productId;
	/**
	 * for coupon image path.
	 */
	private String imagePath;
	/**
	 * for product name.
	 */
	private String productName;
	/**
	 * for rowNum.
	 * 
	 * @return the rowNum
	 */
	private Integer rowNum;

	/**
	 * Variable couponId declared as of type Integer.
	 */
	private Integer couponId;
	/**
	 * Variable couponName declared as of type String.
	 */
	private String couponName;
	/**
	 * Variable couponDiscountType declared as of type String.
	 */
	private String couponDiscountType;
	/**
	 * Variable couponDiscountAmount declared as of type String.
	 */
	private String couponDiscountAmount;
	/**
	 * Variable couponDiscountPct declared as of type String.
	 */
	private String couponDiscountPct;
	/**
	 * Variable couponDescription declared as of type String.
	 */
	private String couponShortDescription;
	/**
	 * Variable shortDescription declared as of type String.
	 */
	private String shortDescription;
	/**
	 * Variable couponDescription declared as of type String.
	 */
	private String couponLongDescription;
	/**
	 * Variable longDescription declared as of type String.
	 */
	private String longDescription;
	/**
	 * Variable couponDateAdded declared as of type String.
	 */
	private String couponDateAdded;
	/**
	 * Variable couponStartDate declared as of type String.
	 */
	private String couponStartDate;
	/**
	 * Variable couponExpireDate declared as of type String.
	 */
	private String couponExpireDate;
	/**
	 * Variable couponEndDate declared as of type String.
	 */
	private String couponEndDate;
	/**
	 * Variable usage declared as of type String.
	 */
	private String usage;

	/**
	 * Variable usage declared as of type String.
	 */
	private String couponImagePath;

	/**
	 * Variable added declared as of type String.
	 */
	private String added;
	/**
	 * for Fav_Flag
	 */
	private Boolean favFlag;
	/**
	 * for category Id
	 */
	private Integer cateId;
	/**
	 * for category name
	 */
	private String cateName;
	/**
	 * for coupon description
	 */
	private String coupDesptn;

	/**
	 * Variable coupDateAdded declared as of type String.
	 */
	private String coupDateAdded;

	/**
	 * Variable couponStartDate declared as of type String.
	 */
	private String coupStartDate;

	/**
	 * Variable couponExpireDate declared as of type String.
	 */
	private String coupExpireDate;
	/**
	 * Variable coupEndDate declared as of type String.
	 */
	private String coupEndDate;

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
	 * For coupon List Id
	 */
	private Integer couponListId;

	/**
	 * For coupon fav flag
	 */
	private Integer coupFavFlag;

	/**
	 * For Coupon expired flag
	 */
	private Boolean expFlag;
	/**
	 * For Coupon expired flag
	 */
	private Integer expireFlag;

	/**
	 * For BusinessCategoryId
	 */
	private Integer busCatId;

	/**
	 * For BusinessCategoryName
	 */
	private String busCatName;

	/**
	 * For Category Id
	 */
	private Integer catId;

	/**
	 * For Categoty Name
	 */
	private String catName;

	/**
	 * For RetailerId
	 */
	private Integer retId;

	/**
	 * For Retailer Name
	 */
	private String retName;

	/**
	 * For RetailLocationId
	 */
	private Integer retLocId;

	/**
	 * For address
	 */
	private String address;

	/**
	 * For city
	 */
	private String city;

	/**
	 * For state
	 */
	private String state;

	/**
	 * For postalCode
	 */
	private String postalCode;

	/**
	 * For coupon description
	 */
	private String coupDesc;

	/**
	 * For distance
	 */
	private Float distance;

	/**
	 * For claim flag
	 */
	private Integer claimFlag;

	/**
	 * For redeem flag
	 */
	private Integer redeemFlag;

	/**
	 * For new flag
	 */
	private Integer newFlag;

	/**
	 * For used count flag
	 */
	private Integer used;

	/**
	 * Number of coupon to issue
	 */
	private Integer coupToIssue;

	/**
	 * For Population center Id
	 */
	private Integer popCentId;

	/**
	 * For DMA name
	 */
	private String dmaName;

	/**
	 * For Coupon Count
	 */
	private Integer coupCount;
	/**
	 * for Fav_Flag
	 */
	private Boolean productFlag;
	/**
	 * for Fav_Flag
	 */
	private Integer prodFlag;
	/**
	 * for Fav_Flag
	 */
	private Boolean locationFlag;
	/**
	 * for Fav_Flag
	 */
	private Integer locatnFlag;

	/**
	 * For Population center Id
	 */
	private Integer coupProductFlag;
	/**
	 * For Population center Id
	 */
	private Integer coupLocationFlag;

	private Integer userCoupGallId;

	private Integer rowNumber;

	private Boolean isDateFormated;

	private List<CouponDetails> coupDetails;
	
	/**
	 * Variable shareText declared as of type String.
	 */
	private String shareText;
	/**
	 * Variable couponDiscountType declared as of type String.
	 */
	private String qrUrl;
	
	
	private String couponDesc;
	
	
	private String bannerName;
	/**
	 * @return the couponImagePath
	 */
	public String getCouponImagePath() {
		return couponImagePath;
	}

	/**
	 * @param couponImagePath
	 *            the couponImagePath to set
	 */
	public void setCouponImagePath(String couponImagePath) {

		if (couponImagePath == null || "".equals(couponImagePath)) {
			this.couponImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.couponImagePath = couponImagePath;
		}
	}

	/**
	 * Gets the value of the couponId property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getCouponId() {
		return couponId;
	}

	/**
	 * Sets the value of the couponId property.
	 * 
	 * @param couponId
	 *            as of type Integer.
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	/**
	 * Gets the value of the couponName property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCouponName() {
		return couponName;
	}

	/**
	 * Sets the value of the couponName property.
	 * 
	 * @param couponName
	 *            as of type String.
	 */
	public void setCouponName(String couponName) {
		if (couponName == null) {
			this.couponName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.couponName = couponName;
		}
	}

	/**
	 * Gets the value of the couponDiscountType property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCouponDiscountType() {
		return couponDiscountType;
	}

	/**
	 * Sets the value of the couponDiscountType property.
	 * 
	 * @param couponDiscountType
	 *            as of type String.
	 */
	public void setCouponDiscountType(String couponDiscountType) {
		if (null == couponDiscountType || "".equals(couponDiscountType)) {
			this.couponDiscountType = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.couponDiscountType = couponDiscountType;
		}
	}

	/**
	 * Gets the value of the couponDiscountAmount property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCouponDiscountAmount() {
		return couponDiscountAmount;
	}

	/**
	 * Sets the value of the couponDiscountAmount property.
	 * 
	 * @param couponDiscountAmount
	 *            as of type String.
	 */
	public void setCouponDiscountAmount(String couponDiscountAmount) {
		if (couponDiscountAmount == null) {
			this.couponDiscountAmount = HubCitiConstants.NOTAPPLICABLE;
		} else {

			if (!couponDiscountAmount.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(couponDiscountAmount)) {
				this.couponDiscountAmount = "$" + Utility.formatDecimalValue(couponDiscountAmount);
			} else {
				this.couponDiscountAmount = couponDiscountAmount;
			}
		}

	}

	/**
	 * Gets the value of the couponDiscountPct property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCouponDiscountPct() {
		return couponDiscountPct;
	}

	/**
	 * Sets the value of the couponDiscountPct property.
	 * 
	 * @param couponDiscountPct
	 *            as of type String.
	 */
	public void setCouponDiscountPct(String couponDiscountPct) {
		if (couponDiscountPct == null) {
			this.couponDiscountPct = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.couponDiscountPct = couponDiscountPct;
		}
	}

	/**
	 * Gets the value of the couponDateAdded property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCouponDateAdded() {
		return couponDateAdded;
	}

	/**
	 * Sets the value of the couponDateAdded property.
	 * 
	 * @param couponDateAdded
	 *            as of type String.
	 */
	public void setCouponDateAdded(String couponDateAdded) {
		if (couponDateAdded != null) {
			// this.couponDateAdded = Utility.convertDBdate(couponDateAdded);
			this.couponDateAdded = couponDateAdded;
		} else {
			this.couponDateAdded = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * Gets the value of the couponStartDate property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCouponStartDate() {
		return couponStartDate;
	}

	/**
	 * Sets the value of the couponStartDate property.
	 * 
	 * @param couponStartDate
	 *            as of type String.
	 */
	public void setCouponStartDate(String couponStartDate) {
		/*if (null != couponStartDate) {
			if (getIsDateFormated() != null && getIsDateFormated() == true) {
				this.couponStartDate = couponStartDate;
			} else {
				this.couponStartDate = Utility.convertDBdate(couponStartDate);
			}
		} else {
			this.couponStartDate = HubCitiConstants.NOTAPPLICABLE;
		}*/
		this.couponStartDate = couponStartDate;
	}

	/**
	 * Gets the value of the couponExpireDate property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCouponExpireDate() {
		return couponExpireDate;
	}

	/**
	 * Sets the value of the couponExpireDate property.
	 * 
	 * @param couponExpireDate
	 *            as of type String.
	 */
	public void setCouponExpireDate(String couponExpireDate) {
		/*if (couponExpireDate != null) {
			if (getIsDateFormated() != null && getIsDateFormated() == true) {
				this.couponExpireDate = couponExpireDate;
			} else {
				this.couponExpireDate = Utility.convertDBdate(couponExpireDate);
			}
		} else {
			this.couponExpireDate = HubCitiConstants.NOTAPPLICABLE;
		}*/
		
		this.couponExpireDate = couponExpireDate;
	}

	/**
	 * Gets the value of the usage property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * Sets the value of the usage property.
	 * 
	 * @param usage
	 *            as of type String.
	 */
	public void setUsage(String usage) {
		if (usage == null) {
			this.usage = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.usage = usage;
		}
	}

	/**
	 * @return the couponShortDescription
	 */
	public String getCouponShortDescription() {
		return couponShortDescription;
	}

	/**
	 * @param couponShortDescription
	 *            the couponShortDescription to set
	 */
	public void setCouponShortDescription(String couponShortDescription) {
		if ("".equals(Utility.checkNull(couponShortDescription))) {
			this.couponShortDescription = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.couponShortDescription = "<![CDATA[" + couponShortDescription + "]]>";
		}
	}

	/**
	 * @return the couponLongDescription
	 */
	public String getCouponLongDescription() {
		return couponLongDescription;
	}

	/**
	 * @param couponLongDescription
	 *            the couponLongDescription to set
	 */
	public void setCouponLongDescription(String couponLongDescription) {
		if ("".equals(Utility.checkNull(couponLongDescription))) {
			this.couponLongDescription = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.couponLongDescription = "<![CDATA[" + couponLongDescription + "]]>";
		}
	}

	/**
	 * to fetch rowNum.
	 * 
	 * @return the rowNum
	 */
	public Integer getRowNum() {
		return rowNum;
	}

	/**
	 * to set rowNum.
	 * 
	 * @param rowNum
	 *            the rowNum to set
	 */
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * to fetch added.
	 * 
	 * @return the added
	 */
	public String getAdded() {
		return added;
	}

	/**
	 * to set added.
	 * 
	 * @param added
	 *            the added to set
	 */
	public void setAdded(String added) {
		this.added = added;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		if (imagePath == null || "".equals(imagePath)) {
			this.imagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.imagePath = imagePath;
		}
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCouponExpired() {
		return couponExpired;
	}

	public void setCouponExpired(Integer couponExpired) {
		this.couponExpired = couponExpired;
	}

	public String getCouponURL() {
		return couponURL;
	}

	public void setCouponURL(String couponURL) {
		if ("".equals(Utility.checkNull(couponURL))) {
			this.couponURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.couponURL = couponURL;
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserCouponClaimTypeId() {
		return userCouponClaimTypeId;
	}

	public void setUserCouponClaimTypeId(Integer userCouponClaimTypeId) {
		this.userCouponClaimTypeId = userCouponClaimTypeId;
	}

	public String getCouponPayoutMethod() {
		return couponPayoutMethod;
	}

	public void setCouponPayoutMethod(String couponPayoutMethod) {
		this.couponPayoutMethod = couponPayoutMethod;
	}

	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	public String getCouponClaimDate() {
		return couponClaimDate;
	}

	public void setCouponClaimDate(String couponClaimDate) {
		this.couponClaimDate = couponClaimDate;
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

	public Integer getUserCouponGalleryId() {
		return userCouponGalleryId;
	}

	public void setUserCouponGalleryId(Integer userCouponGalleryId) {
		this.userCouponGalleryId = userCouponGalleryId;
	}

	public Integer getUsedFlag() {
		return usedFlag;
	}

	public void setUsedFlag(Integer usedFlag) {
		this.usedFlag = usedFlag;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		if ("".equals(Utility.checkNull(termsAndConditions))) {
			this.termsAndConditions = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.termsAndConditions = "<![CDATA[" + termsAndConditions + "]]>";
		}
	}

	// public boolean isViewableOnWeb()
	// {
	// return viewableOnWeb;
	// }

	// public void setViewableOnWeb(boolean viewableOnWeb)
	// {
	// this.viewableOnWeb = viewableOnWeb;
	// }

	public Boolean isViewableOnWeb() {
		return viewableOnWeb;
	}

	public void setViewableOnWeb(Boolean viewableOnWeb) {
		this.viewableOnWeb = viewableOnWeb;
	}

	public Boolean getFavFlag() {
		return favFlag;
	}

	public void setFavFlag(Boolean favFlag) {
		this.favFlag = favFlag;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getCoupDesptn() {
		return coupDesptn;
	}

	public void setCoupDesptn(String coupDesptn) {
		if (coupDesptn == null || "".equals(coupDesptn)) {
			this.coupDesptn = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.coupDesptn = coupDesptn;
		}
	}

	/**
	 * To get coupDate Added
	 * 
	 * @return coupDateAdded
	 */
	public String getCoupDateAdded() {
		return coupDateAdded;
	}

	/**
	 * to set coupDateAdded
	 * 
	 * @param coupDateAdded
	 */
	public void setCoupDateAdded(String coupDateAdded) {
		this.coupDateAdded = coupDateAdded;
	}

	/**
	 * To get coupStartDate
	 * 
	 * @return String coupStartDate
	 */
	public String getCoupStartDate() {
		return coupStartDate;
	}

	/**
	 * To set String coupStartDate
	 * 
	 * @param coupStartDate
	 */
	public void setCoupStartDate(String coupStartDate) {
		this.coupStartDate = coupStartDate;
	}

	/**
	 * To get coupExpireDate
	 * 
	 * @return String coupExpireDate
	 */
	public String getCoupExpireDate() {
		return coupExpireDate;
	}

	/**
	 * To set String coupExpireDate
	 * 
	 * @param coupExpireDate
	 */
	public void setCoupExpireDate(String coupExpireDate) {
		this.coupExpireDate = coupExpireDate;
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

	public Integer getCouponListId() {
		return couponListId;
	}

	public void setCouponListId(Integer couponListId) {
		this.couponListId = couponListId;
	}

	public Integer getRetListId() {
		return retListId;
	}

	public void setRetListId(Integer retListId) {
		this.retListId = retListId;
	}

	public Integer getCoupFavFlag() {
		return coupFavFlag;
	}

	public void setCoupFavFlag(Integer coupFavFlag) {
		this.coupFavFlag = coupFavFlag;
	}

	public Boolean getExpFlag() {
		return expFlag;
	}

	public void setExpFlag(Boolean expFlag) {
		this.expFlag = expFlag;
	}

	public Integer getBusCatId() {
		return busCatId;
	}

	public void setBusCatId(Integer busCatId) {
		this.busCatId = busCatId;
	}

	public String getBusCatName() {
		return busCatName;
	}

	public void setBusCatName(String busCatName) {
		this.busCatName = busCatName;
	}

	public Integer getRetId() {
		return retId;
	}

	public void setRetId(Integer retId) {
		this.retId = retId;
	}

	public String getRetName() {
		return retName;
	}

	public void setRetName(String retName) {
		this.retName = retName;
	}

	public Integer getRetLocId() {
		return retLocId;
	}

	public void setRetLocId(Integer retLocId) {
		this.retLocId = retLocId;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCoupDesc() {
		return coupDesc;
	}

	public void setCoupDesc(String coupDesc) {
		if (null != coupDesc) {
			this.coupDesc = coupDesc;
		} else {
			this.coupDesc = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
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

	public Integer getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(Integer newFlag) {
		this.newFlag = newFlag;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public Integer getCoupToIssue() {
		return coupToIssue;
	}

	public void setCoupToIssue(Integer coupToIssue) {
		this.coupToIssue = coupToIssue;
	}

	public Integer getPopCentId() {
		return popCentId;
	}

	public void setPopCentId(Integer popCentId) {
		this.popCentId = popCentId;
	}

	public String getDmaName() {
		return dmaName;
	}

	public void setDmaName(String dmaName) {
		this.dmaName = dmaName;
	}

	public Integer getCoupCount() {
		return coupCount;
	}

	public void setCoupCount(Integer coupCount) {
		this.coupCount = coupCount;
	}

	public Boolean getProductFlag() {
		return productFlag;
	}

	public void setProductFlag(Boolean productFlag) {
		this.productFlag = productFlag;
	}

	public Boolean getLocationFlag() {
		return locationFlag;
	}

	public void setLocationFlag(Boolean locationFlag) {
		this.locationFlag = locationFlag;
	}

	public Integer getCoupProductFlag() {
		return coupProductFlag;
	}

	public void setCoupProductFlag(Integer coupProductFlag) {
		this.coupProductFlag = coupProductFlag;
	}

	public Integer getCoupLocationFlag() {
		return coupLocationFlag;
	}

	public void setCoupLocationFlag(Integer coupLocationFlag) {
		this.coupLocationFlag = coupLocationFlag;
	}

	public Integer getUserCoupGallId() {
		return userCoupGallId;
	}

	public void setUserCoupGallId(Integer userCoupGallId) {
		this.userCoupGallId = userCoupGallId;
	}

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
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

	public List<CouponDetails> getCoupDetails() {
		return coupDetails;
	}

	public void setCoupDetails(List<CouponDetails> coupDetails) {
		this.coupDetails = coupDetails;
	}

	/**
	 * @return the viewOnWeb
	 */
	public Integer getViewOnWeb() {
		return viewOnWeb;
	}

	/**
	 * @param viewOnWeb
	 *            the viewOnWeb to set
	 */
	public void setViewOnWeb(Integer viewOnWeb) {
		this.viewOnWeb = viewOnWeb;
	}

	/**
	 * @return the expireFlag
	 */
	public Integer getExpireFlag() {
		return expireFlag;
	}

	/**
	 * @param expireFlag
	 *            the expireFlag to set
	 */
	public void setExpireFlag(Integer expireFlag) {
		this.expireFlag = expireFlag;
	}

	/**
	 * @return the prodFlag
	 */
	public Integer getProdFlag() {
		return prodFlag;
	}

	/**
	 * @param prodFlag
	 *            the prodFlag to set
	 */
	public void setProdFlag(Integer prodFlag) {
		this.prodFlag = prodFlag;
	}

	/**
	 * @return the locatnFlag
	 */
	public Integer getLocatnFlag() {
		return locatnFlag;
	}

	/**
	 * @param locatnFlag
	 *            the locatnFlag to set
	 */
	public void setLocatnFlag(Integer locatnFlag) {
		this.locatnFlag = locatnFlag;
	}

	/**
	 * @return the viewableOnWeb
	 */
	public Boolean getViewableOnWeb() {
		return viewableOnWeb;
	}

	/**
	 * @return the termAndConditions
	 */
	public String getTermAndConditions() {
		return termAndConditions;
	}

	/**
	 * @param termAndConditions
	 *            the termAndConditions to set
	 */
	public void setTermAndConditions(String termAndConditions) {
		if ("".equals(Utility.checkNull(termAndConditions))) {
			this.termAndConditions = termAndConditions;
		} else {
			this.termAndConditions = "<![CDATA[" + termAndConditions + "]]>";
		}
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription
	 *            the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		if ("".equals(Utility.checkNull(shortDescription))) {
			this.shortDescription = shortDescription;
		} else {
			this.shortDescription = "<![CDATA[" + shortDescription + "]]>";
		}
	}

	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription
	 *            the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		if ("".equals(Utility.checkNull(longDescription))) {
			this.longDescription = longDescription;
		} else {
			this.longDescription = "<![CDATA[" + longDescription + "]]>";
		}
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
	 * @return the couponEndDate
	 */
	public String getCouponEndDate() {
		return couponEndDate;
	}

	/**
	 * @param couponEndDate the couponEndDate to set
	 */
	public void setCouponEndDate(String couponEndDate) {
		this.couponEndDate = couponEndDate;
	}

	/**
	 * @return the coupEndDate
	 */
	public String getCoupEndDate() {
		return coupEndDate;
	}

	/**
	 * @param coupEndDate the coupEndDate to set
	 */
	public void setCoupEndDate(String coupEndDate) {
		this.coupEndDate = coupEndDate;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	/**
	 * @return the isFeatured
	 */
	public Boolean getIsFeatured() {
		return isFeatured;
	}

	/**
	 * @param isFeatured the isFeatured to set
	 */
	public void setIsFeatured(Boolean isFeatured) {
		this.isFeatured = isFeatured;
	}
}
