package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * pojo for LoyaltyDetail.
 * 
 * @author Kumar D
 */
public class LoyaltyDetail {
	/***
	 * for LoyaltyURL
	 */
	private String loyaltyURL;

	/**
	 * for loyalty share info through twitter
	 */
	private String titleText;

	/**
	 * for loyalty share info
	 */
	private String titleText2;

	/**
	 * for UserClaimTypeID
	 */
	private Integer userClaimTypeID;
	/**
	 * for APIPartnerID.
	 */
	private Integer aPIPartnerID;

	/**
	 * for used flag
	 */
	private Integer usedFlag;
	/**
	 * for user gallery userLoyaltyGalleryID id
	 */
	private Integer userLoyaltyGalleryID;
	/**
	 * for userId
	 */
	private Integer userId;
	/**
	 * for LoyaltyDealPayoutMethod
	 */
	private String loyaltyDealPayoutMethod;
	/**
	 * for LoyaltyDealRedemptionDate
	 */
	private String loyaltyDealRedemptionDate;
	/**
	 * for product id.
	 */
	private Integer productId;
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
	 * variable loyaltyDealName declared as String.
	 */
	private String loyaltyDealName;
	/**
	 * variable loyaltyDealSource declared as String.
	 */
	private String loyaltyDealSource;
	/**
	 * variable loyaltyDealDiscountType declared as String.
	 */
	private String loyaltyDealDiscountType;
	/**
	 * variable loyaltyDealDiscountAmount declared as String.
	 */
	private String loyaltyDealDiscountAmount;
	/**
	 * variable loyaltyDealDiscountPct declared as String.
	 */
	private String loyaltyDealDiscountPct;
	/**
	 * variable loyaltyDealDescription declared as String.
	 */
	private String loyaltyDealDescription;
	/**
	 * variable loyaltyDealDateAdded declared as String.
	 */
	private String loyaltyDealDateAdded;
	/**
	 * variable loyaltyDealStartDate declared as String.
	 */
	private String loyaltyDealStartDate;
	/**
	 * variable loyaltyDealExpireDate declared as String.
	 */
	private String loyaltyDealExpireDate;
	/**
	 * variable usage declared as String.
	 */
	private String usage;

	/**
	 * variable usage declared as String.
	 */
	private String imagePath;

	/**
	 * variable loyaltyDealId declared as Integer.
	 */

	private Integer loyaltyDealId;

	/**
	 * Variable added declared as of type String.
	 */
	private String added;

	/**
	 * for retailer id
	 */
	private Integer retId;
	/**
	 * for retailer name
	 */
	private String retName;
	/**
	 * for RetailerImagePath
	 */
	private String retImagePath;

	/**
	 * for APIPartnerName
	 */
	private String aPIPartName;
	/**
	 * for APIPartnerImagePath
	 */
	private String aPIPartImage;

	/**
	 * for favFlag
	 */
	private Boolean favFlag;
	/**
	 * for category id
	 */
	private Integer cateId;
	/**
	 * for category name
	 */
	private String cateName;

	private String startDate;

	private String endDate;
	/**
	 * for loyalty long description
	 */
	private String loyLngDesc;

	private String merchantId;

	/**
	 * For retailer List ID
	 */
	private Integer retListID;

	/**
	 * For loyaltyListID
	 */
	private Integer loyaltyListID;

	/**
	 * variable loyaltyDealDateAdded declared as String.
	 */
	private String loyDealDateAdded;

	/**
	 * * for getting loyaltyDealName.
	 * 
	 * @return loyaltyDealName
	 */

	public String getLoyaltyDealName() {
		return loyaltyDealName;
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
		if (null == imagePath || "".equals(imagePath)) {
			this.imagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.imagePath = imagePath;
		}
	}

	/**
	 * for setting loyaltyDealName.
	 * 
	 * @param loyaltyDealName
	 *            The setter for loyaltyDealName property.
	 */
	public void setLoyaltyDealName(String loyaltyDealName) {
		this.loyaltyDealName = loyaltyDealName;
	}

	/**
	 * * for getting loyaltyDealSource.
	 * 
	 * @return loyaltyDealSource
	 */
	public String getLoyaltyDealSource() {
		return loyaltyDealSource;
	}

	/**
	 * for setting loyaltyDealSource.
	 * 
	 * @param loyaltyDealSource
	 *            The setter for loyaltyDealSource property.
	 */
	public void setLoyaltyDealSource(String loyaltyDealSource) {
		if (null == loyaltyDealSource) {
			this.loyaltyDealSource = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyaltyDealSource = loyaltyDealSource;
		}
	}

	/**
	 * * for getting loyaltyDealDiscountType.
	 * 
	 * @return loyaltyDealDiscountType
	 */
	public String getLoyaltyDealDiscountType() {
		return loyaltyDealDiscountType;
	}

	/**
	 * for setting loyaltyDealDiscountType.
	 * 
	 * @param loyaltyDealDiscountType
	 *            The setter for loyaltyDealDiscountType property.
	 */
	public void setLoyaltyDealDiscountType(String loyaltyDealDiscountType) {
		if (null == loyaltyDealDiscountType) {
			this.loyaltyDealDiscountType = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyaltyDealDiscountType = loyaltyDealDiscountType;
		}
	}

	/**
	 * * for getting loyaltyDealDiscountAmount.
	 * 
	 * @return loyaltyDealDiscountAmount
	 */
	public String getLoyaltyDealDiscountAmount() {
		return loyaltyDealDiscountAmount;
	}

	/**
	 * for setting loyaltyDealDiscountAmount.
	 * 
	 * @param loyaltyDealDiscountAmount
	 *            The setter for loyaltyDealDiscountAmount property.
	 */

	public void setLoyaltyDealDiscountAmount(String loyaltyDealDiscountAmount) {
		if (null == loyaltyDealDiscountAmount) {
			this.loyaltyDealDiscountAmount = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!loyaltyDealDiscountAmount.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(loyaltyDealDiscountAmount)) {
				// this.loyaltyDealDiscountAmount =
				// Utility.formatDecimalValue(loyaltyDealDiscountAmount);
				this.loyaltyDealDiscountAmount = "$" + Utility.formatDecimalValue(loyaltyDealDiscountAmount);
			} else {
				this.loyaltyDealDiscountAmount = loyaltyDealDiscountAmount;
			}
		}
	}

	/**
	 * * for getting loyaltyDealDiscountPct.
	 * 
	 * @return loyaltyDealDiscountPct
	 */

	public String getLoyaltyDealDiscountPct() {
		return loyaltyDealDiscountPct;
	}

	/**
	 * for setting loyaltyDealDiscountPct.
	 * 
	 * @param loyaltyDealDiscountPct
	 *            The setter for loyaltyDealDiscountPct property.
	 */
	public void setLoyaltyDealDiscountPct(String loyaltyDealDiscountPct) {
		if (loyaltyDealDiscountPct == null) {
			this.loyaltyDealDiscountPct = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyaltyDealDiscountPct = loyaltyDealDiscountPct;
		}
	}

	/**
	 * * for getting loyaltyDealDescription.
	 * 
	 * @return loyaltyDealDescription
	 */
	public String getLoyaltyDealDescription() {
		return loyaltyDealDescription;
	}

	/**
	 * for setting loyaltyDealDescription.
	 * 
	 * @param loyaltyDealDescription
	 *            The setter for loyaltyDealDescription property.
	 */
	public void setLoyaltyDealDescription(String loyaltyDealDescription) {
		if (loyaltyDealDescription == null) {
			loyaltyDealDescription = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyaltyDealDescription = loyaltyDealDescription;
		}
	}

	/**
	 * * for getting loyaltyDealDateAdded.
	 * 
	 * @return loyaltyDealDateAdded
	 */
	public String getLoyaltyDealDateAdded() {
		return loyaltyDealDateAdded;
	}

	/**
	 * for setting loyaltyDealDateAdded.
	 * 
	 * @param loyaltyDealDateAdded
	 *            The setter for loyaltyDealDateAdded property.
	 */
	public void setLoyaltyDealDateAdded(String loyaltyDealDateAdded) {
		if (null != loyaltyDealDateAdded) {
			this.loyaltyDealDateAdded = loyaltyDealDateAdded;
			this.loyaltyDealDateAdded = Utility.convertDBdate(loyaltyDealDateAdded);
		} else {
			this.loyaltyDealDateAdded = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * * for getting loyaltyDealStartDate.
	 * 
	 * @return loyaltyDealStartDate
	 */
	public String getLoyaltyDealStartDate() {
		return loyaltyDealStartDate;
	}

	/**
	 * for setting loyaltyDealStartDate.
	 * 
	 * @param loyaltyDealStartDate
	 *            The setter for loyaltyDealStartDate property.
	 */
	public void setLoyaltyDealStartDate(String loyaltyDealStartDate) {
		if (null != loyaltyDealStartDate) {
			this.loyaltyDealStartDate = loyaltyDealStartDate;
			this.loyaltyDealStartDate = Utility.convertDBdate(loyaltyDealStartDate);
		} else {
			this.loyaltyDealStartDate = HubCitiConstants.CLSTARTDATETEXT;
		}
	}

	/**
	 * * for getting loyaltyDealExpireDate.
	 * 
	 * @return loyaltyDealExpireDate
	 */
	public String getLoyaltyDealExpireDate() {
		return loyaltyDealExpireDate;
	}

	/**
	 * for setting loyaltyDealExpireDate.
	 * 
	 * @param loyaltyDealExpireDate
	 *            The setter for loyaltyDealExpireDate property.
	 */

	public void setLoyaltyDealExpireDate(String loyaltyDealExpireDate) {
		this.loyaltyDealExpireDate = loyaltyDealExpireDate;
		if (loyaltyDealExpireDate != null) {
			this.loyaltyDealExpireDate = loyaltyDealExpireDate;
			this.loyaltyDealExpireDate = Utility.convertDBdate(loyaltyDealExpireDate);
		} else {
			this.loyaltyDealExpireDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * * for getting usage.
	 * 
	 * @return usage
	 */

	public String getUsage() {
		return usage;
	}

	/**
	 * for setting usage.
	 * 
	 * @param usage
	 *            The setter for usage property.
	 */

	public void setUsage(String usage) {
		if (null == usage) {
			this.usage = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.usage = usage;
		}
	}

	/**
	 * * for getting loyaltyDealId.
	 * 
	 * @return loyaltyDealId
	 */
	public Integer getLoyaltyDealId() {
		return loyaltyDealId;
	}

	/**
	 * for setting loyaltyDealId.
	 * 
	 * @param loyaltyDealId
	 *            The setter for loyaltyDealId property.
	 */
	public void setLoyaltyDealId(Integer loyaltyDealId) {
		this.loyaltyDealId = loyaltyDealId;
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
		if (null == added) {
			this.added = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.added = added;
		}
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLoyaltyDealPayoutMethod() {
		return loyaltyDealPayoutMethod;
	}

	public void setLoyaltyDealPayoutMethod(String loyaltyDealPayoutMethod) {
		this.loyaltyDealPayoutMethod = loyaltyDealPayoutMethod;
	}

	public String getLoyaltyDealRedemptionDate() {
		return loyaltyDealRedemptionDate;
	}

	public void setLoyaltyDealRedemptionDate(String loyaltyDealRedemptionDate) {
		this.loyaltyDealRedemptionDate = loyaltyDealRedemptionDate;
	}

	public Integer getUserLoyaltyGalleryID() {
		return userLoyaltyGalleryID;
	}

	public void setUserLoyaltyGalleryID(Integer userLoyaltyGalleryID) {
		this.userLoyaltyGalleryID = userLoyaltyGalleryID;
	}

	public Integer getUsedFlag() {
		return usedFlag;
	}

	public void setUsedFlag(Integer usedFlag) {
		this.usedFlag = usedFlag;
	}

	public Integer getUserClaimTypeID() {
		return userClaimTypeID;
	}

	public void setUserClaimTypeID(Integer userClaimTypeID) {
		this.userClaimTypeID = userClaimTypeID;
	}

	public Integer getaPIPartnerID() {
		return aPIPartnerID;
	}

	public void setaPIPartnerID(Integer aPIPartnerID) {
		this.aPIPartnerID = aPIPartnerID;
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

	public String getLoyaltyURL() {
		return loyaltyURL;
	}

	public void setLoyaltyURL(String loyaltyURL) {
		if (loyaltyURL == null) {
			this.loyaltyURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyaltyURL = loyaltyURL;
		}
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
		if (retName == null || "".equals(retName)) {
			this.retName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retName = retName;
		}
	}

	public String getRetImagePath() {
		return retImagePath;
	}

	public void setRetImagePath(String retImagePath) {
		if (retImagePath == null || "".equals(retImagePath)) {
			this.retImagePath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retImagePath = retImagePath;
		}

	}

	public String getaPIPartName() {
		return aPIPartName;
	}

	public void setaPIPartName(String aPIPartName) {
		if (null == aPIPartName || "".equals(aPIPartName)) {
			this.aPIPartName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.aPIPartName = aPIPartName;
		}

	}

	public String getaPIPartImage() {
		return aPIPartImage;
	}

	public void setaPIPartImage(String aPIPartImage) {
		if (null == aPIPartImage || "".equals(aPIPartImage)) {
			this.aPIPartImage = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.aPIPartImage = aPIPartImage;
		}
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
		if (cateName == null || "".equals(cateName)) {
			this.cateName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.cateName = cateName;
		}
	}

	public String getLoyLngDesc() {
		return loyLngDesc;
	}

	public void setLoyLngDesc(String loyLngDesc) {
		if (loyLngDesc == null || "".equals(loyLngDesc)) {
			this.loyLngDesc = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyLngDesc = "<![CDATA[" + loyLngDesc + "]]>";
		}
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		if (null == merchantId || "".equals(merchantId)) {
			this.merchantId = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.merchantId = merchantId;
		}
	}

	/**
	 * To get loyDealDateAdded
	 * 
	 * @return String loyDealDateAdded
	 */
	public String getLoyDealDateAdded() {
		return loyDealDateAdded;
	}

	/**
	 * To ser String loyDealDateAdded
	 * 
	 * @param loyDealDateAdded
	 */
	public void setLoyDealDateAdded(String loyDealDateAdded) {
		this.loyDealDateAdded = loyDealDateAdded;
	}

	public Integer getRetListID() {
		return retListID;
	}

	public void setRetListID(Integer retListID) {
		this.retListID = retListID;
	}

	public Integer getLoyaltyListID() {
		return loyaltyListID;
	}

	public void setLoyaltyListID(Integer loyaltyListID) {
		this.loyaltyListID = loyaltyListID;
	}
}
