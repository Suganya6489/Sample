package com.hubciti.common.pojos;

import java.text.DecimalFormat;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

public class Retailer {

	/**
	 * The rowNumber property.
	 */

	private Integer rowNumber;

	/**
	 * The retailerId property.
	 */

	private Integer retailerId;

	private Integer eventId;
	private Integer userId;
	private Integer hubCitiId;

	/**
	 * The retailLocationID property.
	 */
	private Integer retailLocationId;
	/**
	 * The retailerName property.
	 */

	private String retailerName;

	/**
	 * Variable for distance.
	 */
	private String distance;

	/**
	 * Variable for retailAddress.
	 */
	private String retailAddress;
	private String retailAddress1;
	private String retailAddress2;
	
	private String completeAddress;
	/**
	 * The imagePath property.
	 */

	private String logoImagePath;

	/**
	 * The bannerAdImagePath property.
	 */

	private String bannerAdImagePath;
	/**
	 * The ribbonAdImagePath property.
	 */

	private String ribbonAdImagePath;

	/**
	 * The ribbonAdURL property.
	 */

	private String ribbonAdURL;

	/**
	 * for sale flag to indicate retailer has sale or not
	 */
	private Boolean saleFlag;

	/**
	 * for sale flag to indicate retailer has sale or not
	 */
	private Integer saleFlg;

	/**
	 * For latitude
	 */
	private String latitude;

	/**
	 * For longitude
	 */
	private String longitude;

	/**
	 * for ribben ad url
	 */
	private String splashAdId;

	/**
	 * For retailer List ID
	 */
	private Integer retListId;

	/**
	 * For retailer group button image path
	 */
	private String retGroupImg;

	/**
	 * The retailLocationID property.
	 */
	private String advertisementId;

	/**
	 */
	private String hotelPrice;

	private Integer appSiteId;

	private String appSiteName;

	private String address;

	private String city;

	private String state;

	private String postalCode;
	
	private Integer catId;
	
	private String catName;

	private Integer retailId;
	
	private String locationOpen;


	/**
	 * @return the hotelPrice
	 */
	public String getHotelPrice() {
		return hotelPrice;
	}

	/**
	 * @param hotelPrice
	 *            the hotelPrice to set
	 */
	public void setHotelPrice(String hotelPrice) {
		this.hotelPrice = hotelPrice;
	}

	/**
	 * For rating.
	 */
	private String rating;

	/**
	 * The imagePath property.
	 */

	private String retailImgPath;

	public Integer getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	public Integer getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		if ("".equals(Utility.checkNull(distance))) {
			this.distance = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (!distance.contains(HubCitiConstants.NOTAPPLICABLE) && !distance.contains("mi")) {
				DecimalFormat forTwo = new DecimalFormat("#.##");
				this.distance = (forTwo.format(Float.valueOf(distance))).toString() + " mi";
			} else {
				this.distance = distance;
			}
	}

	public String getRetailAddress() {
		return retailAddress;
	}

	public void setRetailAddress(String retailAddress) {
		this.retailAddress = retailAddress;
	}

	public String getLogoImagePath() {
		return logoImagePath;
	}

	public void setLogoImagePath(String logoImagePath) {
		if (logoImagePath == null) {
			this.logoImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.logoImagePath = logoImagePath;
		}
	}

	public String getBannerAdImagePath() {
		return bannerAdImagePath;
	}

	public void setBannerAdImagePath(String bannerAdImagePath) {
		if (bannerAdImagePath == null) {
			this.bannerAdImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.bannerAdImagePath = bannerAdImagePath;
		}
	}

	public String getRibbonAdImagePath() {
		return ribbonAdImagePath;
	}

	public void setRibbonAdImagePath(String ribbonAdImagePath) {
		if (ribbonAdImagePath == null) {
			this.ribbonAdImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.ribbonAdImagePath = ribbonAdImagePath;
		}
	}

	public String getRibbonAdURL() {
		return ribbonAdURL;
	}

	public void setRibbonAdURL(String ribbonAdURL) {
		if (ribbonAdURL == null) {
			this.ribbonAdURL = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.ribbonAdURL = ribbonAdURL;
		}
	}

	public Boolean getSaleFlag() {
		return saleFlag;
	}

	public void setSaleFlag(Boolean saleFlag) {
		this.saleFlag = saleFlag;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getSplashAdId() {
		return splashAdId;
	}

	public void setSplashAdId(String splashAdId) {
		this.splashAdId = splashAdId;
	}

	public Integer getRetListId() {
		return retListId;
	}

	public void setRetListId(Integer retListId) {
		this.retListId = retListId;
	}

	public String getRetGroupImg() {
		return retGroupImg;
	}

	public void setRetGroupImg(String retGroupImg) {
		if (retGroupImg == null) {
			retGroupImg = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.retGroupImg = retGroupImg;
		}
	}

	/**
	 * @return the saleFlg
	 */
	public Integer getSaleFlg() {
		return saleFlg;
	}

	/**
	 * @param saleFlg
	 *            the saleFlg to set
	 */
	public void setSaleFlg(Integer saleFlg) {
		this.saleFlg = saleFlg;
	}

	/**
	 * @param advertisementId
	 *            the advertisementId to set
	 */
	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}

	/**
	 * @return the advertisementId
	 */
	public String getAdvertisementId() {
		return advertisementId;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the eventId
	 */
	public Integer getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	 * @return the retailImgPath
	 */
	public String getRetailImgPath() {
		return retailImgPath;
	}

	/**
	 * @param retailImgPath
	 *            the retailImgPath to set
	 */
	public void setRetailImgPath(String retailImgPath) {
		this.retailImgPath = retailImgPath;
	}

	public Integer getAppSiteId() {
		return appSiteId;
	}

	public void setAppSiteId(Integer appSiteId) {
		this.appSiteId = appSiteId;
	}

	public String getAppSiteName() {
		return appSiteName;
	}

	public void setAppSiteName(String appSiteName) {
		this.appSiteName = appSiteName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getRetailAddress2() {
		return retailAddress2;
	}

	public void setRetailAddress2(String retailAddress2) {
		this.retailAddress2 = retailAddress2;
	}

	public String getLocationOpen() {
		return locationOpen;
	}

	public void setLocationOpen(String locationOpen) {
		this.locationOpen = locationOpen;
	}


	public String getRetailAddress1() {
		return retailAddress1;
	}

	public void setRetailAddress1(String retailAddress1) {
		this.retailAddress1 = retailAddress1;
	}


	public String getCompleteAddress() {
		return completeAddress;
	}

	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}

}
