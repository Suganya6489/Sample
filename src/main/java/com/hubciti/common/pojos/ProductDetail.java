package com.hubciti.common.pojos;

import java.util.HashMap;
import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * The POJO class for ProductDetail.
 * 
 * @author Kumar D
 */

public class ProductDetail {
	private String responseCode;

	private String responseText;

	/**
	 * nextPage declared as Integer. for next page
	 */
	private Integer nextPage;
	/**
	 * to check product exists or there not
	 */
	private Integer productIsThere;
	/**
	 * for product exits or not
	 */
	private Boolean productExists;
	/**
	 * for Regular Price
	 */
	private String regularPrice;
	/**
	 * for product discount.
	 */
	private String discount;
	/**
	 * for gpsEnabled.
	 */
	private Boolean gpsEnabled;
	/**
	 * for radius.
	 */
	private Integer radius;
	/**
	 * for zipcode.
	 */
	private String zipcode;

	/**
	 * for wish list history date.
	 */
	private String wishListAddDate;

	/**
	 * for wishlist deleted product date.
	 */

	private String productDeletedDate;

	/**
	 * for ProductImagePath.
	 */
	private String productImagePath;
	/**
	 * 
	 */
	public Integer hubCitiId;
	/**
	 * for setting subCategoryName.
	 */
	private String subCategoryName;
	/**
	 * for setting subcategoryId.
	 */
	private Integer subCategoryId;
	/**
	 * for button flag.
	 */
	private Integer buttonFlag;
	/**
	 * for lastVisitedProductNo.
	 */
	private Integer lastVistedProductNo;

	/**
	 * for product Sale price..
	 */

	private String salePrice;
	/**
	 * for clr flag.
	 */
	private Integer CLRFlag;
	/**
	 * for category Id.
	 */
	private Integer categoryId;
	/**
	 * for parent category name.
	 */
	private String parentCategoryName;
	/**
	 * For to Identify product clr and preference .
	 */
	private Integer clr;

	/**
	 * For product row number.
	 */
	private Integer row_Num;

	/**
	 * for userProductId.
	 */
	private Integer userProductId;
	/**
	 * for productId.
	 */
	private Integer productId;
	/**
	 * for productName.
	 */
	private String productName;
	/**
	 * for productDescription.
	 */
	private String productDescription;
	/**
	 * for productShortDescription.
	 */
	private String productShortDescription;
	/**
	 * for productLongDescription.
	 */
	private String productLongDescription;

	/**
	 * for product Attribute name.
	 */
	private HashMap<String, String> productAttributesMap;

	/**
	 * for manufacturersName.
	 */
	private String manufacturersName;
	/**
	 * for imagePath.
	 */
	private String imagePath;
	/**
	 * for modelNumber.
	 */
	private String modelNumber;
	/**
	 * for suggestedRetailPrice.
	 */
	private String suggestedRetailPrice;
	/**
	 * for productWeight.
	 */
	private String productWeight;
	/**
	 * for productExpDate.
	 */
	private String productExpDate;
	/**
	 * for weightUnits.
	 */
	private String weightUnits;
	/**
	 * for rowNumber.
	 */
	private Integer rowNumber;
	/**
	 * for rebateId.
	 */
	private Integer rebateId;
	/**
	 * for deviceId.
	 */
	private String deviceId;
	/**
	 * for scanLongitude.
	 */
	private Double scanLongitude;
	/**
	 * for scanLatitude.
	 */
	private Double scanLatitude;
	/**
	 * for fieldAgentRequest.
	 */
	private Integer fieldAgentRequest;
	/**
	 * for scanTypeId.
	 */
	private Integer scanTypeId;
	/**
	 * for coupons.
	 */
	private Integer coupons;
	/**
	 * for rebates.
	 */
	private Integer rebates;
	/**
	 * for loyalties.
	 */
	private Integer loyalties;
	/**
	 * for coupon_Status.
	 */
	private String coupon_Status;
	/**
	 * for rebate_Status.
	 */
	private String rebate_Status;
	/**
	 * for loyalty_Status.
	 */
	private String loyalty_Status;
	/**
	 * for ShopCartItem.
	 */
	private Integer shopCartItem;
	/**
	 * for ShopListItem.
	 */
	private Integer shopListItem;

	/**
	 * for usage.
	 */
	private String usage;
	/**
	 * for productVideoUrl.
	 */
	private String productVideoUrl;
	/**
	 * for userId.
	 */
	private Integer userId;
	/**
	 * for barCode.
	 */
	private String barCode;
	/**
	 * for searchkey.
	 */
	private String searchkey;
	/**
	 * for VideoFlag.
	 */
	private String VideoFlag;
	/**
	 * for audioFlag.
	 */
	private String audioFlag;
	/**
	 * for fileFlag.
	 */
	private String fileFlag;
	//
	/**
	 * for productMediaId.
	 */
	private Integer productMediaId;
	/**
	 * for productMediaPath.
	 */
	private String productMediaPath;

	/**
	 * for scanDate.
	 */
	private String scanDate;
	/**
	 * for scanStatus.
	 */
	private String scanStatus;
	/**
	 * for scanType.
	 */

	private String scanType;
	/**
	 * Distance is used for findnearbyRetailer method.
	 */
	private Integer distance;
	/**
	 * for productPrice.
	 */
	private String productPrice;

	/**
	 * for couponFlag.
	 */

	private String couponFlag;

	/**
	 * for loyaltyFlag.
	 */

	private String loyaltyFlag;

	/**
	 * for rebateFlag.
	 */

	private String rebateFlag;

	/**
	 * for productMediaName.
	 */

	private String productMediaName;

	/**
	 * for retailerName.
	 */

	private String retailerName;

	/**
	 * for retailerId.
	 */

	private Integer retailerId;
	/**
	 * for skuCode.
	 */
	private String skuCode;
	
	/**
	 * for qrUrl.
	 */
	private String qrUrl;

	/**
	 * This variable used for Wish List module for indicating cause for Sales
	 * price changed for retailer.
	 */

	private String retailFlag;

	/**
	 * This variable used for Wish List module for indicating cause for Sales
	 * price changed for Coupon.
	 */

	private Integer couponSaleFlag;

	/**
	 * This variable used for Wish List module for indicating cause for Sales
	 * price changed for hotdeal.
	 */

	private Integer hotDealFlag;

	/**
	 * for PushNotifyFlag.
	 */
	private Integer pushNotifyFlag;

	/**
	 * the pushNotifyFlag declared as string.
	 */

	private String emailTemplateURL;

	/**
	 * starFlag declared as integer.
	 */
	private Integer starFlag;

	/**
	 * for product attributeName.
	 */

	private String attributeName;

	/**
	 * For product display value.
	 */
	private String displayValue;

	/**
	 * To get retailLocationId value.
	 */
	private String retailLocationId;

	/**
	 * product warranty service information
	 */
	private String warrantyServiceInfo;

	/**
	 * for sale price.
	 */
	private String saleDiscount;
	
	/**
	 * for shareText
	 */
	private String shareText;
	/**
	 * for product multiple images
	 */
	/**
	 * For product attributes list.
	 */
	private List<ProductDetails> productImageslst;
	/**
	 * for share product info by email
	 */
	private String shareProdName;

	/**
	 * for share product long description
	 */
	private String shareProdLongDesc;

	private Long scanHistoryId;

	/**
	 * for find categories
	 */
	private String categs;

	/**
	 * for parent category name.
	 */
	private Long parCatId;

	/**
	 * for product scancode
	 */
	private String scanCode;
	/**
	 * for product ISBN
	 */
	private String prdISBN;
	/**
	 * for parent category name.
	 */
	private Integer prdCount;

	/**
	 * For user tracking
	 */
	private Integer mainMenuId;

	/**
	 * For user tracking
	 */
	private Integer scanId;

	/**
	 * For user tracking
	 */
	private Integer prodSmaSeaId;

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
	 * For sale List Id
	 */
	private Integer saleListId;

	/**
	 * For product list Id
	 */
	private Integer prodListId;

	/**
	 * For Module Id
	 */
	private Integer moduleId;

	/**
	 * For alerted product Id
	 */
	private Integer alertProdId;

	/**
	 * for productMediaList Id
	 */
	private Integer pmLstId;

	/**
	 * maxCount declared as Integer. for next page
	 */
	private Integer maxCount;
	/**
	 * Below flag is used to Identify whether product is checked out
	 */
	private boolean isShopCartItem;

	/**
	 * For product attributes list.
	 */
	private List<ProductAttributes> productAttributeslst;

	/**
	 * @return the isShopCartItem
	 */
	public boolean isShopCartItem() {
		return isShopCartItem;
	}

	/**
	 * To get productAttributeslst values.
	 * 
	 * @return the productAttributeslst
	 */
	public List<ProductAttributes> getProductAttributeslst() {
		return productAttributeslst;
	}

	/**
	 * To set productAttributeslst values.
	 * 
	 * @param productAttributeslst
	 *            the productAttributeslst to set
	 */
	public void setProductAttributeslst(List<ProductAttributes> productAttributeslst) {
		this.productAttributeslst = productAttributeslst;
	}

	/**
	 * To get productAttributesMap values.
	 * 
	 * @return the productAttributesMap
	 */
	public HashMap<String, String> getProductAttributesMap() {
		return productAttributesMap;
	}

	/**
	 * To set productAttributesMap values.
	 * 
	 * @param productAttributesMap
	 *            the productAttributesMap to set
	 */
	public void setProductAttributesMap(HashMap<String, String> productAttributesMap) {
		this.productAttributesMap = productAttributesMap;
	}

	/**
	 * To get retailLocationId value.
	 * 
	 * @return the retailLocationId
	 */
	public String getRetailLocationId() {
		return retailLocationId;
	}

	/**
	 * To set retailLocationId value.
	 * 
	 * @param retailLocationId
	 *            the retailLocationId to set
	 */
	public void setRetailLocationId(String retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	/**
	 * To get emailTemplateURL value.
	 * 
	 * @return emailTemplateURL To get
	 */
	public String getEmailTemplateURL() {
		return emailTemplateURL;
	}

	/**
	 * To set emailTemplateURL value.
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setEmailTemplateURL(String emailTemplateURL) {
		this.emailTemplateURL = emailTemplateURL;
	}

	/**
	 * To get pushNotifyFlag value.
	 * 
	 * @return pushNotifyFlag To get
	 */
	public Integer getPushNotifyFlag() {
		return pushNotifyFlag;
	}

	/**
	 * To setpushNotifyFlag value.
	 * 
	 * @param pushNotifyFlag
	 *            the pushNotifyFlag to set
	 */
	public void setPushNotifyFlag(Integer pushNotifyFlag) {
		this.pushNotifyFlag = pushNotifyFlag;
	}

	/**
	 * To get skuCode value.
	 * 
	 * @return the skuCode
	 */
	public String getSkuCode() {
		return skuCode;
	}

	/**
	 * To set skuCode value.
	 * 
	 * @param skuCode
	 *            the skuCode to set
	 */
	public void setSkuCode(String skuCode) {
		if (null == skuCode) {
			this.skuCode = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.skuCode = skuCode;
		}
	}

	/**
	 * To get row_Num value.
	 * 
	 * @return the row_Num
	 */
	public Integer getRow_Num() {
		return row_Num;
	}

	/**
	 * for setting rowNum.
	 * 
	 * @param rowNum
	 *            the row_Num to set
	 */
	public void setRow_Num(Integer rowNum) {
		row_Num = rowNum;
	}

	/**
	 * for getting productMediaName.
	 * 
	 * @return the productMediaName
	 */

	public String getProductMediaName() {
		return productMediaName;
	}

	/**
	 * to set productMediaName.
	 * 
	 * @param productMediaName
	 *            . the productMediaName to set
	 */
	public void setProductMediaName(String productMediaName) {
		if (null == productMediaName) {
			this.productMediaName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.productMediaName = productMediaName;
		}
	}

	/**
	 * this method for getting productMediaId.
	 * 
	 * @return the productMediaId
	 */
	public Integer getProductMediaId() {
		return productMediaId;
	}

	/**
	 * this method for setting productMediaId.
	 * 
	 * @param productMediaId
	 *            the productMediaId to set
	 */
	public void setProductMediaId(Integer productMediaId) {
		this.productMediaId = productMediaId;
	}

	/**
	 * this method of r getting productMediaPath.
	 * 
	 * @return the productMediaPath
	 */
	public String getProductMediaPath() {
		return productMediaPath;
	}

	/**
	 * this method for setting productMediaPath.
	 * 
	 * @param productMediaPath
	 *            the productMediaPath to set
	 */
	public void setProductMediaPath(String productMediaPath) {
		if (null == productMediaPath) {
			this.productMediaPath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.productMediaPath = productMediaPath;
		}
	}

	/**
	 * this method for getting VideoFlag.
	 * 
	 * @return the VideoFlag
	 */
	public String getVideoFlag() {
		return VideoFlag;
	}

	/**
	 * this method for setting VideoFlag.
	 * 
	 * @param VideoFlag
	 *            the VideoFlag to set
	 */
	public void setVideoFlag(String VideoFlag) {
		if (null == VideoFlag) {
			this.VideoFlag = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.VideoFlag = VideoFlag;
		}

	}

	/**
	 * this method for getting audioFlag.
	 * 
	 * @return the audioFlag
	 */
	public String getAudioFlag() {
		return audioFlag;
	}

	/**
	 * this method for setting audioFlag.
	 * 
	 * @param audioFlag
	 *            the audioFlag to set
	 */
	public void setAudioFlag(String audioFlag) {
		if (null == audioFlag) {

			this.audioFlag = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.audioFlag = audioFlag;
		}

	}

	/**
	 * this method for getting fileFlag.
	 * 
	 * @return the fileFlag
	 */
	public String getFileFlag() {
		return fileFlag;
	}

	/**
	 * this method for setting fileFlag.
	 * 
	 * @param fileFlag
	 *            the fileFlag to set
	 */
	public void setFileFlag(String fileFlag) {
		if (null == fileFlag) {

			this.fileFlag = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.fileFlag = fileFlag;
		}

	}

	/**
	 * for getting userId.
	 * 
	 * @return userId.
	 */

	public Integer getUserId() {
		return userId;
	}

	/**
	 * this method for setting userId.
	 * 
	 * @param userId
	 *            as request parameter.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * this method for getting userProductId.
	 * 
	 * @return the userProductId
	 */
	public Integer getUserProductId() {
		return userProductId;
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
	 * this method for setting userProductId.
	 * 
	 * @param userProductId
	 *            the userProductId to set
	 */
	public void setUserProductId(Integer userProductId) {
		this.userProductId = userProductId;
	}

	/**
	 * this method for getting shopCartItem.
	 * 
	 * @return the shopCartItem
	 */
	public Integer getShopCartItem() {
		return shopCartItem;
	}

	/**
	 * this method for setting shopCartItem.
	 * 
	 * @param shopCartItem
	 *            the shopCartItem to set
	 */
	public void setShopCartItem(Integer shopCartItem) {
		this.shopCartItem = shopCartItem;
	}

	/**
	 * this method for getting shopListItem.
	 * 
	 * @return the shopListItem
	 */
	public Integer getShopListItem() {
		return shopListItem;
	}

	/**
	 * this method for setting shopListItem.
	 * 
	 * @param shopListItem
	 *            the shopListItem to set
	 */
	public void setShopListItem(Integer shopListItem) {
		this.shopListItem = shopListItem;
	}

	/**
	 * this method for getting distance.
	 * 
	 * @return the distance
	 */
	public Integer getDistance() {
		return distance;
	}

	/**
	 * this method for setting distance.
	 * 
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	/**
	 * this method for getting productPrice.
	 * 
	 * @return the productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * this method for setting productPrice.
	 * 
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(String productPrice) {
		if (productPrice == null) {
			this.productPrice = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!productPrice.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(productPrice)) {
				// this.productPrice = Utility.formatDecimalValue(productPrice);
				this.productPrice = "$" + Utility.formatDecimalValue(productPrice);
			} else {
				this.productPrice = productPrice;
			}
		}

	}

	/**
	 * for getting scanTypeId.
	 * 
	 * @return scanTypeId
	 */
	public Integer getScanTypeId() {
		return scanTypeId;
	}

	/**
	 * for setting scanTypeId.
	 * 
	 * @param scanTypeId
	 *            as request parameter.
	 */
	public void setScanTypeId(Integer scanTypeId) {
		this.scanTypeId = scanTypeId;
	}

	/**
	 * for getting productWeight.
	 * 
	 * @return productWeight
	 */
	public String getProductWeight() {
		return productWeight;
	}

	/**
	 * for setting productWeight.
	 * 
	 * @param productWeight
	 *            as request parameter.
	 */
	public void setProductWeight(String productWeight) {
		if (null == productWeight) {
			this.productWeight = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.productWeight = productWeight;
		}

	}

	/**
	 * for getting rebateId.
	 * 
	 * @return rebateId
	 */

	public Integer getRebateId() {
		return rebateId;
	}

	/**
	 * for setting rebateId.
	 * 
	 * @param rebateId
	 *            as request parameter.
	 */
	public void setRebateId(Integer rebateId) {
		this.rebateId = rebateId;
	}

	/**
	 * for getting deviceId.
	 * 
	 * @return deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * for setting deviceId.
	 * 
	 * @param deviceId
	 *            as request parameter.
	 */
	public void setDeviceId(String deviceId) {
		if (null == deviceId) {
			this.deviceId = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.deviceId = deviceId;
		}

	}

	/**
	 * for getting scanLongitude.
	 * 
	 * @return scanLongitude
	 */
	public Double getScanLongitude() {
		return scanLongitude;
	}

	/**
	 * for setting scanLongitude.
	 * 
	 * @param scanLongitude
	 *            as request parameter.
	 */
	public void setScanLongitude(Double scanLongitude) {
		this.scanLongitude = scanLongitude;
	}

	/**
	 * for getting scanLatitude.
	 * 
	 * @return scanLatitude
	 */
	public Double getScanLatitude() {
		return scanLatitude;
	}

	/**
	 * for setting scanLatitude.
	 * 
	 * @param scanLatitude
	 *            as parameter.
	 */
	public void setScanLatitude(Double scanLatitude) {
		this.scanLatitude = scanLatitude;
	}

	/**
	 * for getting fieldAgentRequest.
	 * 
	 * @return fieldAgentRequest
	 */
	public Integer getFieldAgentRequest() {
		return fieldAgentRequest;
	}

	/**
	 * for setting fieldAgentRequest.
	 * 
	 * @param fieldAgentRequest
	 *            as request parameter.
	 */
	public void setFieldAgentRequest(Integer fieldAgentRequest) {
		this.fieldAgentRequest = fieldAgentRequest;
	}

	/**
	 * this method for getting rowNumber.
	 * 
	 * @return the rowNumber
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}

	/**
	 * this method for setting rowNumber.
	 * 
	 * @param rowNumber
	 *            the rowNumber to set
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * this method for getting productDescription.
	 * 
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}

	/**
	 * this method for setting productDescription.
	 * 
	 * @param productDescription
	 *            the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		if ("".equals(Utility.checkNull(productDescription))) {
			this.productDescription = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (productDescription.contains("<![CDATA[")) {
				this.productDescription = productDescription;
			} else {
				this.productDescription = "<![CDATA[" + productDescription + "]]>";
			}
	}

	/**
	 * this method for getting productId.
	 * 
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * this method for setting productId.
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * this method for getting productName.
	 * 
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * this method for setting productName.
	 * 
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		productName = Utility.removeHTMLTags(productName);
		if (null == productName || "".equals(productName)) {
			this.productName = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (productName.contains("<![CDATA[")) {
				this.productName = productName;
			} else {
				this.productName = "<![CDATA[" + productName + "]]>";
			}

	}

	/**
	 * this method for getting manufacturersName.
	 * 
	 * @return the manufacturersName
	 */
	public String getManufacturersName() {
		return manufacturersName;
	}

	/**
	 * this method for setting manufacturersName.
	 * 
	 * @param manufacturersName
	 *            the manufacturersName to set
	 */
	public void setManufacturersName(String manufacturersName) {
		if (null == manufacturersName) {

			this.manufacturersName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.manufacturersName = manufacturersName;
		}

	}

	/**
	 * this method for getting imagePath.
	 * 
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * this method for setting imagePath.
	 * 
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
	 * this method for getting modelNumber.
	 * 
	 * @return the modelNumber
	 */
	public String getModelNumber() {
		return modelNumber;
	}

	/**
	 * this method for setting modelNumber.
	 * 
	 * @param modelNumber
	 *            the modelNumber to set
	 */
	public void setModelNumber(String modelNumber) {
		if (null == modelNumber || "".equals(modelNumber)) {
			this.modelNumber = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.modelNumber = modelNumber;
		}

	}

	/**
	 * this method for getting suggestedRetailPrice.
	 * 
	 * @return the suggestedRetailPrice
	 */
	public String getSuggestedRetailPrice() {
		return suggestedRetailPrice;
	}

	/**
	 * this method for setting suggestedRetailPrice.
	 * 
	 * @param suggestedRetailPrice
	 *            the suggestedRetailPrice to set
	 */
	public void setSuggestedRetailPrice(String suggestedRetailPrice) {
		if (null == suggestedRetailPrice) {
			this.suggestedRetailPrice = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!suggestedRetailPrice.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(suggestedRetailPrice)) {
				this.suggestedRetailPrice = "$" + Utility.formatDecimalValue(suggestedRetailPrice);
			} else {
				this.suggestedRetailPrice = suggestedRetailPrice;
			}
		}
	}

	/**
	 * this method for getting weight.
	 * 
	 * @return the weight
	 */
	public String getWeight() {
		return productWeight;
	}

	/**
	 * this method for setting weight.
	 * 
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		if (null == productWeight) {
			this.productWeight = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.productWeight = weight;
		}

	}

	/**
	 * this method for getting productExpDate.
	 * 
	 * @return the productExpDate
	 */
	public String getProductExpDate() {
		return productExpDate;
	}

	/**
	 * this method for setting productExpDate.
	 * 
	 * @param productExpDate
	 *            the productExpDate to set
	 */
	public void setProductExpDate(String productExpDate) {
		if (productExpDate != null) {
			this.productExpDate = productExpDate;
			this.productExpDate = Utility.convertDBdate(productExpDate);
		} else {
			this.productExpDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * this method for getting weightUnits.
	 * 
	 * @return the weightUnits
	 */
	public String getWeightUnits() {
		return weightUnits;
	}

	/**
	 * this method for setting weightUnits.
	 * 
	 * @param weightUnits
	 *            the weightUnits to set
	 */
	public void setWeightUnits(String weightUnits) {
		if (null == weightUnits) {
			this.weightUnits = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.weightUnits = weightUnits;
		}

	}

	/**
	 * thId method for getting coupons.
	 * 
	 * @return the coupons
	 */
	public Integer getCoupons() {
		return coupons;
	}

	/**
	 * this method for setting coupons.
	 * 
	 * @param coupons
	 *            the coupons to set
	 */
	public void setCoupons(Integer coupons) {
		this.coupons = coupons;
	}

	/**
	 * this method for getting rebates.
	 * 
	 * @return the rebates
	 */
	public Integer getRebates() {
		return rebates;
	}

	/**
	 * this method for setting rebates.
	 * 
	 * @param rebates
	 *            the rebates to set
	 */
	public void setRebates(Integer rebates) {
		this.rebates = rebates;
	}

	/**
	 * this method for getting loyalties.
	 * 
	 * @return the loyalties
	 */
	public Integer getLoyalties() {
		return loyalties;
	}

	/**
	 * this method for setting loyalties.
	 * 
	 * @param loyalties
	 *            the loyalties to set
	 */
	public void setLoyalties(Integer loyalties) {
		this.loyalties = loyalties;
	}

	/**
	 * this method for getting coupon_Status.
	 * 
	 * @return the coupon_Status
	 */
	public String getCoupon_Status() {
		return coupon_Status;
	}

	/**
	 * this method for setting coupon_Status.
	 * 
	 * @param couponStatus
	 *            the coupon_Status to set
	 */
	public void setCoupon_Status(String couponStatus) {
		if (null == couponStatus) {
			this.coupon_Status = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.coupon_Status = couponStatus;
		}

	}

	/**
	 * this method for getting rebate_Status.
	 * 
	 * @return the rebate_Status
	 */
	public String getRebate_Status() {
		return rebate_Status;
	}

	/**
	 * this method for setting rebate_Status.
	 * 
	 * @param rebateStatus
	 *            the rebate_Status to set
	 */
	public void setRebate_Status(String rebateStatus) {
		if (null == rebateStatus) {

			this.rebate_Status = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.rebate_Status = rebateStatus;
		}

	}

	/**
	 * this method for getting loyalty_Status.
	 * 
	 * @return the loyalty_Status
	 */
	public String getLoyalty_Status() {
		return loyalty_Status;
	}

	/**
	 * this method for setting loyaltyStatus.
	 * 
	 * @param loyaltyStatus
	 *            the loyalty_Status to set
	 */
	public void setLoyalty_Status(String loyaltyStatus) {
		if (null == loyaltyStatus) {
			this.loyalty_Status = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyalty_Status = loyaltyStatus;
		}

	}

	/**
	 * this method for setting productShortDescription.
	 * 
	 * @return productShortDescription
	 */
	public String getProductShortDescription() {
		return productShortDescription;
	}

	/**
	 * this method for setting productShortDescription. the loyalty_Status to
	 * set
	 * 
	 * @param productShortDescription
	 *            the productShortDescription to set
	 */

	public void setProductShortDescription(String productShortDescription) {

		productShortDescription = Utility.removeHTMLTags(productShortDescription);
		if (null == productShortDescription || "".equals(productShortDescription)) {
			this.productShortDescription = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (productShortDescription.contains("<![CDATA[")) {
				this.productShortDescription = productShortDescription;
			} else {
				this.productShortDescription = "<![CDATA[" + productShortDescription + "]]>";
			}

	}

	/**
	 * this method for getting productLongDescription.
	 * 
	 * @return productLongDescription
	 */
	public String getProductLongDescription() {
		return productLongDescription;
	}

	/**
	 * this method for setting productLongDescription.
	 * 
	 * @param productLongDescription
	 *            as request parameter.
	 */
	public void setProductLongDescription(String productLongDescription) {

		if (null == productLongDescription || "".equals(productLongDescription)) {
			this.productLongDescription = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (productLongDescription.contains("<![CDATA[")) {
				this.productLongDescription = productLongDescription;
			} else {
				this.productLongDescription = "<![CDATA[" + productLongDescription + "]]>";
			}
	}

	/**
	 * this method for getting usage.
	 * 
	 * @return the usage
	 */
	public String getUsage() {
		return usage;
	}

	/**
	 * for setting usage.
	 * 
	 * @param usage
	 *            the usage to set
	 */
	public void setUsage(String usage) {
		if (null == usage) {
			this.usage = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.usage = usage;
		}

	}

	/**
	 * for getting productVideoUrl.
	 * 
	 * @return productVideoUrl
	 */
	public String getProductVideoUrl() {
		return productVideoUrl;
	}

	/**
	 * for setting productVideoUrl.
	 * 
	 * @param productVideoUrl
	 *            the productVideoUrl to set
	 */
	public void setProductVideoUrl(String productVideoUrl) {
		if (null == productVideoUrl) {
			this.productVideoUrl = HubCitiConstants.NOTAPPLICABLE;
		} else {

			this.productVideoUrl = productVideoUrl;
		}

	}

	/**
	 * for getting barCode.
	 * 
	 * @return barCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * for setting barCode.
	 * 
	 * @param barCode
	 *            as parameter
	 */
	public void setBarCode(String barCode) {
		if (barCode == null) {
			this.barCode = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.barCode = barCode;
		}

	}

	/**
	 * this method for getting searchkey.
	 * 
	 * @return searchkey
	 */
	public String getSearchkey() {
		return searchkey;
	}

	/**
	 * for setting searchkey.
	 * 
	 * @param searchkey
	 *            to be set.
	 */
	public void setSearchkey(String searchkey) {
		if (null == searchkey) {
			this.searchkey = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.searchkey = "<![CDATA[" + searchkey + "]]>";
		}
	}

	/**
	 * for getting scanDate.
	 * 
	 * @return scanDate
	 */
	public String getScanDate() {
		return scanDate;
	}

	/**
	 * for setting scanDate.
	 * 
	 * @param scanDate
	 *            to be set.
	 */

	public void setScanDate(String scanDate) {
		if (scanDate != null) {

			this.scanDate = scanDate;
			this.scanDate = Utility.convertDBdate(scanDate);
		} else {
			this.scanDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * for getting scanStatus.
	 * 
	 * @return scanStatus
	 */

	public String getScanStatus() {
		return scanStatus;
	}

	/**
	 * for setting scanStatus.
	 * 
	 * @param scanStatus
	 *            to be set.
	 */

	public void setScanStatus(String scanStatus) {
		if (null == scanStatus) {

			this.scanStatus = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.scanStatus = scanStatus;
		}

	}

	/**
	 * for getting couponFlag.
	 * 
	 * @return couponFlag
	 */

	public String getCouponFlag() {
		return couponFlag;
	}

	/**
	 * for setting couponFlag.
	 * 
	 * @param couponFlag
	 *            to be set.
	 */

	public void setCouponFlag(String couponFlag) {
		if (null == couponFlag) {
			this.couponFlag = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.couponFlag = couponFlag;
		}

	}

	/**
	 * for getting loyaltyFlag.
	 * 
	 * @return loyaltyFlag
	 */

	public String getLoyaltyFlag() {
		return loyaltyFlag;
	}

	/**
	 * for setting loyaltyFlag.
	 * 
	 * @param loyaltyFlag
	 *            to be set.
	 */

	public void setLoyaltyFlag(String loyaltyFlag) {
		if (null == loyaltyFlag) {
			this.loyaltyFlag = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.loyaltyFlag = loyaltyFlag;
		}

	}

	/**
	 * for getting rebateFlag.
	 * 
	 * @return rebateFlag
	 */

	public String getRebateFlag() {
		return rebateFlag;
	}

	/**
	 * for setting rebateFlag.
	 * 
	 * @param rebateFlag
	 *            to be set.
	 */

	public void setRebateFlag(String rebateFlag) {
		if (null == rebateFlag) {
			this.rebateFlag = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.rebateFlag = rebateFlag;
		}

	}

	/**
	 * for getting scanType.
	 * 
	 * @return scanType
	 */
	public String getScanType() {
		return scanType;
	}

	/**
	 * for setting scanType.
	 * 
	 * @param scanType
	 *            to be set.
	 */
	public void setScanType(String scanType) {
		if (null == scanType) {
			this.scanType = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.scanType = scanType;

		}
	}

	/**
	 * for getting clr.
	 * 
	 * @return clr
	 */
	public Integer getClr() {
		return clr;
	}

	/**
	 * for setting clr.
	 * 
	 * @param clr
	 *            the clr to set
	 */
	public void setClr(Integer clr) {
		this.clr = clr;
	}

	/**
	 * for getting categoryId.
	 * 
	 * @return categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * for setting categoryId.
	 * 
	 * @param categoryId
	 *            to be set.
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * for getting parentCategoryName.
	 * 
	 * @return parentCategoryName
	 */
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	/**
	 * for setting parentCategoryName.
	 * 
	 * @param parentCategoryName
	 *            to be set.
	 */
	public void setParentCategoryName(String parentCategoryName) {
		if (null == parentCategoryName) {
			this.parentCategoryName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.parentCategoryName = parentCategoryName;
		}
	}

	/**
	 * for getting CLRFlag.
	 * 
	 * @return CLRFlag
	 */
	public Integer getCLRFlag() {
		return CLRFlag;
	}

	/**
	 * for setting cLRFlag.
	 * 
	 * @param cLRFlag
	 *            to be set.
	 */
	public void setCLRFlag(Integer cLRFlag) {
		CLRFlag = cLRFlag;
	}

	/**
	 * for getting retailerName.
	 * 
	 * @return retailerName
	 */
	public String getRetailerName() {
		return retailerName;
	}

	/**
	 * for setting retailerName.
	 * 
	 * @param retailerName
	 *            to be set.
	 */
	public void setRetailerName(String retailerName) {
		if (null == retailerName) {
			this.retailerName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retailerName = retailerName;
		}

	}

	/**
	 * for getting retailerId.
	 * 
	 * @return retailerId
	 */
	public Integer getRetailerId() {
		return retailerId;
	}

	/**
	 * for setting retailerId.
	 * 
	 * @param retailerId
	 *            to be set.
	 */
	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}

	/**
	 * for getting salePrice.
	 * 
	 * @return salePrice
	 */
	public String getSalePrice() {
		return salePrice;
	}

	/**
	 * for setting salePrice.
	 * 
	 * @param salePrice
	 *            to be set.
	 */
	public void setSalePrice(String salePrice) {
		if (salePrice == null) {
			this.salePrice = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!salePrice.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(salePrice)) {

				// this.salePrice = Utility.formatDecimalValue(salePrice);
				this.salePrice = "$" + Utility.formatDecimalValue(salePrice);

			} else {
				this.salePrice = salePrice;
			}
		}
	}

	/**
	 * to get lastVistedProductNo.
	 * 
	 * @return the lastVistedProductNo
	 */
	public Integer getLastVistedProductNo() {
		return lastVistedProductNo;
	}

	/**
	 * to set lastVistedProductNo.
	 * 
	 * @param lastVistedProductNo
	 *            the lastVistedProductNo to set
	 */
	public void setLastVistedProductNo(Integer lastVistedProductNo) {
		this.lastVistedProductNo = lastVistedProductNo;
	}

	/**
	 * to get buttonFlag.
	 * 
	 * @return the buttonFlag
	 */
	public Integer getButtonFlag() {
		return buttonFlag;
	}

	/**
	 * to set buttonFlag.
	 * 
	 * @param buttonFlag
	 *            the buttonFlag to set
	 */
	public void setButtonFlag(Integer buttonFlag) {
		this.buttonFlag = buttonFlag;
	}

	/**
	 * to get subCategoryName.
	 * 
	 * @return the subCategoryName
	 */
	public String getSubCategoryName() {
		return subCategoryName;
	}

	/**
	 * to set subCategoryName.
	 * 
	 * @param subCategoryName
	 *            the subCategoryName to set
	 */
	public void setSubCategoryName(String subCategoryName) {
		if (null == subCategoryName) {
			this.subCategoryName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.subCategoryName = subCategoryName;
		}

	}

	/**
	 * to get subCategoryId.
	 * 
	 * @return the subCategoryId
	 */
	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	/**
	 * to set subCategoryId.
	 * 
	 * @param subCategoryId
	 *            the subCategoryId to set
	 */
	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	/**
	 * to get productImagePath.
	 * 
	 * @return the productImagePath
	 */
	public String getProductImagePath() {
		return productImagePath;
	}

	/**
	 * to set productImagePath.
	 * 
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

	/**
	 * To get productDeletedDate value.
	 * 
	 * @return the productDeletedDate
	 */
	public String getProductDeletedDate() {
		return productDeletedDate;
	}

	/**
	 * To set productDeletedDate value.
	 * 
	 * @param productDeletedDate
	 *            the productDeletedDate to set
	 */
	public void setProductDeletedDate(String productDeletedDate) {
		if (null == productDeletedDate) {
			this.productDeletedDate = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.productDeletedDate = productDeletedDate;
		}
	}

	/**
	 * To get wishListAddDate value.
	 * 
	 * @return the wishListAddDate
	 */
	public String getWishListAddDate() {
		return wishListAddDate;
	}

	/**
	 * To set wishListAddDate value.
	 * 
	 * @param wishListAddDate
	 *            the wishListAddDate to set
	 */
	public void setWishListAddDate(String wishListAddDate) {
		if (null != wishListAddDate) {
			this.wishListAddDate = wishListAddDate;
			this.wishListAddDate = Utility.convertDBdate(wishListAddDate);
		} else {
			this.wishListAddDate = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	/**
	 * To get retailFlag value.
	 * 
	 * @return the retailFlag
	 */
	public String getRetailFlag() {
		return retailFlag;
	}

	/**
	 * To set retailFlag value.
	 * 
	 * @param retailFlag
	 *            the retailFlag to set
	 */
	public void setRetailFlag(String retailFlag) {
		this.retailFlag = retailFlag;
	}

	/**
	 * To get couponSaleFlag value.
	 * 
	 * @return the couponSaleFlag
	 */
	public Integer getCouponSaleFlag() {
		return couponSaleFlag;
	}

	/**
	 * To set couponSaleFlag value.
	 * 
	 * @param couponSaleFlag
	 *            the couponSaleFlag to set
	 */
	public void setCouponSaleFlag(Integer couponSaleFlag) {
		this.couponSaleFlag = couponSaleFlag;
	}

	/**
	 * To get hotDealFlag avlue.
	 * 
	 * @return the hotDealFlag
	 */
	public Integer getHotDealFlag() {
		return hotDealFlag;
	}

	/**
	 * To set hotDealFlag value.
	 * 
	 * @param hotDealFlag
	 *            the hotDealFlag to set
	 */
	public void setHotDealFlag(Integer hotDealFlag) {
		this.hotDealFlag = hotDealFlag;
	}

	/**
	 * To get attributeName.
	 * 
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * To set attributeName.
	 * 
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		if (attributeName == null) {
			this.attributeName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.attributeName = attributeName;
		}
	}

	/**
	 * To get displayValue.
	 * 
	 * @return the displayValue
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * To set displayValue.
	 * 
	 * @param displayValue
	 *            the displayValue to set
	 */
	public void setDisplayValue(String displayValue) {
		if (null == displayValue) {
			this.displayValue = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.displayValue = Utility.removeHTMLTags(displayValue);
		}
	}

	/**
	 * To get starFlag.
	 * 
	 * @return the starFlag
	 */
	public Integer getStarFlag() {
		return starFlag;
	}

	/**
	 * To set starFlag.
	 * 
	 * @param starFlag
	 *            the starFlag to set
	 */
	public void setStarFlag(Integer starFlag) {
		this.starFlag = starFlag;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode
	 *            the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the radius
	 */
	public Integer getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	/**
	 * @return the gpsEnabled
	 */
	public Boolean getGpsEnabled() {
		return gpsEnabled;
	}

	/**
	 * @param gpsEnabled
	 *            the gpsEnabled to set
	 */
	public void setGpsEnabled(Boolean gpsEnabled) {
		this.gpsEnabled = gpsEnabled;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		if (null == discount) {
			this.discount = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.discount = discount + "% Off";
		}
	}

	public String getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(String regularPrice) {
		if (null == regularPrice) {
			this.regularPrice = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!regularPrice.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(regularPrice)) {
				// this.regularPrice = Utility.formatDecimalValue(regularPrice);
				this.regularPrice = "$" + Utility.formatDecimalValue(regularPrice);
			} else {
				this.regularPrice = regularPrice;
			}
		}
	}

	public Boolean isProductExists() {
		return productExists;
	}

	public void setProductExists(Boolean productExists) {
		this.productExists = productExists;
	}

	public Integer getProductIsThere() {
		return productIsThere;
	}

	public void setProductIsThere(Integer productIsThere) {
		this.productIsThere = productIsThere;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public String getWarrantyServiceInfo() {
		return warrantyServiceInfo;
	}

	public void setWarrantyServiceInfo(String warrantyServiceInfo) {
		if (warrantyServiceInfo == null || "".equals(warrantyServiceInfo)) {
			this.warrantyServiceInfo = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.warrantyServiceInfo = warrantyServiceInfo;
		}
	}

	public List<ProductDetails> getProductImageslst() {
		return productImageslst;
	}

	public void setProductImageslst(List<ProductDetails> productImageslst) {
		this.productImageslst = productImageslst;
	}

	public Boolean getProductExists() {
		return productExists;
	}

	public String getSaleDiscount() {
		return saleDiscount;
	}

	public void setSaleDiscount(String saleDiscount) {
		if (saleDiscount == null || saleDiscount.equals("")) {
			this.saleDiscount = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!saleDiscount.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(saleDiscount)) {
				// this.salePrice = Utility.formatDecimalValue(salePrice);
				this.saleDiscount = "$" + Utility.formatDecimalValue(saleDiscount);
			} else {
				this.saleDiscount = saleDiscount;
			}
		}
	}

	public String getShareProdName() {
		return shareProdName;
	}

	public void setShareProdName(String shareProdName) {
		this.shareProdName = shareProdName;
	}

	public String getShareProdLongDesc() {
		return shareProdLongDesc;
	}

	public void setShareProdLongDesc(String shareProdLongDesc) {
		this.shareProdLongDesc = shareProdLongDesc;
	}

	public Long getScanHistoryId() {
		return scanHistoryId;
	}

	public void setScanHistoryId(Long scanHistoryId) {
		this.scanHistoryId = scanHistoryId;
	}

	public String getCategs() {
		return categs;
	}

	public void setCategs(String categs) {
		if (categs == null || categs.equals("")) {
			this.categs = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.categs = categs;
		}

	}

	public Long getParCatId() {
		return parCatId;
	}

	public void setParCatId(Long parCatId) {
		this.parCatId = parCatId;
	}

	public Integer getPrdCount() {
		return prdCount;
	}

	public void setPrdCount(Integer prdCount) {
		this.prdCount = prdCount;
	}

	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		if (scanCode == null || scanCode.equals("")) {
			this.scanCode = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.scanCode = scanCode;
		}
	}

	public String getPrdISBN() {
		return prdISBN;
	}

	public void setPrdISBN(String prdISBN) {
		if (prdISBN == null || prdISBN.equals("")) {
			this.prdISBN = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.prdISBN = prdISBN;
		}
	}

	public Integer getMainMenuId() {
		return mainMenuId;
	}

	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}

	public Integer getScanId() {
		return scanId;
	}

	public void setScanId(Integer scanId) {
		this.scanId = scanId;
	}

	public Integer getProdSmaSeaId() {
		return prodSmaSeaId;
	}

	public void setProdSmaSeaId(Integer prodSmaSeaId) {
		this.prodSmaSeaId = prodSmaSeaId;
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

	public Integer getRetListId() {
		return retListId;
	}

	public void setRetListId(Integer retListId) {
		this.retListId = retListId;
	}

	public Integer getSaleListId() {
		return saleListId;
	}

	public void setSaleListId(Integer saleListId) {
		this.saleListId = saleListId;
	}

	public Integer getProdListId() {
		return prodListId;
	}

	public void setProdListId(Integer prodListId) {
		this.prodListId = prodListId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	public Integer getAlertProdId() {
		return alertProdId;
	}

	public void setAlertProdId(Integer alertProdId) {
		if (alertProdId != null) {
			this.alertProdId = alertProdId;
		} else {
			this.alertProdId = 0;
		}
	}

	public Integer getPmLstId() {
		return pmLstId;
	}

	public void setPmLstId(Integer pmLstId) {
		this.pmLstId = pmLstId;
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
}
