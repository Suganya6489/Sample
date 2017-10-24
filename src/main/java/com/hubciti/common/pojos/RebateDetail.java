package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * POJO class for RebateDetail.
 * 
 * @author Kumar D
 */
public class RebateDetail {
	/**
	 * for RebateURL
	 */

	private String rebateURL;
	/**
	 * for loyalty share info through twitter
	 */
	private String titleText;

	/**
	 * for loyalty share info
	 */
	private String titleText2;
	/**
	 * for used flag
	 */
	private Integer usedFlag;
	/**
	 * for userRebateGalleryID
	 */
	private Integer userRebateGalleryID;
	/**
	 * for userId
	 */
	private Integer userId;
	/**
	 * for RetailLocationID
	 */
	private Integer retailLocationID;
	/**
	 * for RedemptionStatusID
	 */
	private Integer redemptionStatusID;
	/**
	 * for PurchaseAmount
	 */
	private String PurchaseAmount;
	/**
	 * for PurchaseDate
	 */
	private String purchaseDate;
	/**
	 * for ProductSerialNumber
	 */
	private String productSerialNumber;
	/**
	 * for Scan image.
	 */
	private String scanImage;

	/**
	 * for productId.
	 */
	private Integer productId;
	/***
	 * for rebate image path
	 */
	private String imagePath;
	/**
	 * for product name
	 */
	private String productName;
	/**
	 * for rowNum.
	 * 
	 * @return the rowNum
	 */
	private Integer rowNum;

	/**
	 * The rebateName property.
	 */

	private String rebateName;
	/**
	 * The rebateId property.
	 */

	private Integer rebateId;

	/**
	 * The manufacturerId property.
	 */

	private Integer manufacturerId;

	/**
	 * The retailId; property.
	 */

	private Integer retailId;

	/**
	 * The manufacturerName property.
	 */

	private String manufacturerName;

	/**
	 * The rebateAmount property.
	 */

	private String rebateAmount;

	/**
	 * The rebateStartDate property.
	 */

	private String rebateStartDate;

	/**
	 * The rebateEndDate property.
	 */

	private String rebateEndDate;

	/**
	 * The usage;property.
	 */

	private String productImagePath;
	/**
	 * Variable added declared as of type String.
	 */
	private String added;
	/**
	 * For rebListID
	 */
	private Integer rebListID;
	/**
	 * The rebateShortDescription property.
	 */

	private String rebateShortDescription;

	public String getRebateShortDescription() {
		return rebateShortDescription;
	}

	public void setRebateShortDescription(String rebateShortDescription) {
		if (null == rebateShortDescription) {
			this.rebateShortDescription = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.rebateShortDescription = rebateShortDescription;
		}
	}

	public String getRebateLongDescription() {
		return rebateLongDescription;
	}

	public void setRebateLongDescription(String rebateLongDescription) {

		if (null == rebateLongDescription) {
			this.rebateLongDescription = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.rebateLongDescription = rebateLongDescription;
		}
	}

	/**
	 * The rebateLongDescription property.
	 */

	private String rebateLongDescription;

	/**
	 * @return the productImagePath
	 */
	public String getProductImagePath() {
		return productImagePath;
	}

	/**
	 * @param productImagePath
	 *            the productImagePath to set
	 */
	public void setProductImagePath(String productImagePath) {

		if (null == productImagePath || "".equals(productImagePath)) {
			this.productImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.productImagePath = productImagePath;
		}
	}

	private String usage;

	/**
	 * for getting usage.
	 * 
	 * @return usage.
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
	 * for getting rebateId.
	 * 
	 * @return rebateId.
	 */

	public Integer getRebateId() {
		return rebateId;
	}

	/**
	 * for setting rebateId.
	 * 
	 * @param rebateId
	 *            The setter for rebateId property.
	 */

	public void setRebateId(Integer rebateId) {
		this.rebateId = rebateId;
	}

	/**
	 * * for getting manufacturerId.
	 * 
	 * @return manufacturerId
	 */

	public Integer getManufacturerId() {
		return manufacturerId;
	}

	/**
	 * for setting manufacturerId.
	 * 
	 * @param manufacturerId
	 *            The setter for manufacturerId property.
	 */

	public void setManufacturerId(Integer manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	/**
	 * for getting retailId.
	 * 
	 * @return retailId.
	 */

	public Integer getRetailId() {
		return retailId;
	}

	/**
	 * for setting retailId.
	 * 
	 * @param retailId
	 *            The setter for manufacturerId property.
	 */

	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * for getting manufacturerName.
	 * 
	 * @return manufacturerName.
	 */

	public String getManufacturerName() {
		return manufacturerName;
	}

	/**
	 * for setting manufacturerName.
	 * 
	 * @param manufacturerName
	 *            The setter for manufacturerName property.
	 */

	public void setManufacturerName(String manufacturerName) {
		if (null == manufacturerName) {
			this.manufacturerName = HubCitiConstants.NOTAPPLICABLE;

		} else {
			this.manufacturerName = manufacturerName;
		}
	}

	/**
	 * for getting rebateAmount.
	 * 
	 * @return rebateAmount.
	 */

	public String getRebateAmount() {
		return rebateAmount;
	}

	/**
	 * for setting rebateAmount.
	 * 
	 * @param rebateAmount
	 *            The setter for rebateAmount property.
	 */

	public void setRebateAmount(String rebateAmount) {
		if (null == rebateAmount) {
			this.rebateAmount = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!rebateAmount.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(rebateAmount)) {
				// this.rebateAmount = Utility.formatDecimalValue(rebateAmount);
				this.rebateAmount = "$" + Utility.formatDecimalValue(rebateAmount);
			} else {
				this.rebateAmount = rebateAmount;
			}
		}
	}

	/**
	 * for getting rebateStartDate.
	 * 
	 * @return rebateStartDate.
	 */

	public String getRebateStartDate() {
		return rebateStartDate;
	}

	/**
	 * for setting rebateStartDate.
	 * 
	 * @param rebateStartDate
	 *            The setter for rebateStartDate property.
	 */

	public void setRebateStartDate(String rebateStartDate) {
		if (rebateStartDate != null) {
			this.rebateStartDate = rebateStartDate;
			this.rebateStartDate = Utility.convertDBdate(rebateStartDate);
		} else {
			this.rebateStartDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * for getting rebateEndDate.
	 * 
	 * @return rebateEndDate.
	 */

	public String getRebateEndDate() {
		return rebateEndDate;
	}

	/**
	 * for setting rebateEndDate.
	 * 
	 * @param rebateEndDate
	 *            The setter for rebateEndDate property.
	 */

	public void setRebateEndDate(String rebateEndDate) {

		if (rebateEndDate != null) {
			this.rebateEndDate = rebateEndDate;
			this.rebateEndDate = Utility.convertDBdate(rebateEndDate);
		} else {
			this.rebateEndDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * for getting rebateName.
	 * 
	 * @return rebateName.
	 */
	public String getRebateName() {
		return rebateName;
	}

	/**
	 * for setting rebateName.
	 * 
	 * @param rebateName
	 *            the rebateName to set
	 */
	public void setRebateName(String rebateName) {
		if (rebateName == null) {
			this.rebateName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.rebateName = rebateName;
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
	 * to get rowNum.
	 * 
	 * @param rowNum
	 *            the rowNum to set
	 */
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * @return the rebListID
	 */
	public Integer getRebListID() {
		return rebListID;
	}

	/**
	 * @param rebListID
	 *            the rebListID to set
	 */
	public void setRebListID(Integer rebListID) {
		this.rebListID = rebListID;
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

	public Integer getRetailLocationID() {
		return retailLocationID;
	}

	public void setRetailLocationID(Integer retailLocationID) {
		this.retailLocationID = retailLocationID;
	}

	public Integer getRedemptionStatusID() {
		return redemptionStatusID;
	}

	public void setRedemptionStatusID(Integer redemptionStatusID) {
		this.redemptionStatusID = redemptionStatusID;
	}

	public String getPurchaseAmount() {
		return PurchaseAmount;
	}

	public void setPurchaseAmount(String purchaseAmount) {
		PurchaseAmount = purchaseAmount;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}

	public String getScanImage() {
		return scanImage;
	}

	public void setScanImage(String scanImage) {
		this.scanImage = scanImage;
	}

	public Integer getUserRebateGalleryID() {
		return userRebateGalleryID;
	}

	public void setUserRebateGalleryID(Integer userRebateGalleryID) {
		this.userRebateGalleryID = userRebateGalleryID;
	}

	public Integer getUsedFlag() {
		return usedFlag;
	}

	public void setUsedFlag(Integer usedFlag) {
		this.usedFlag = usedFlag;
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

	public String getRebateURL() {
		return rebateURL;
	}

	public void setRebateURL(String rebateURL) {
		if (rebateURL == null) {
			this.rebateURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.rebateURL = rebateURL;
		}
	}

}
