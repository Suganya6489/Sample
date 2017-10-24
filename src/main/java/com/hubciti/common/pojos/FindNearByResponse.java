package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * POJO class for findnearny details.
 * 
 * @author dhurvanath_mm
 */
public class FindNearByResponse {

	/**
	 * for productName.
	 */
	private String productName;
	/**
	 * for retailerId.
	 */
	private String retailerId;
	/**
	 * for retailerName.
	 */
	private String retailerName;
	/**
	 * for retaileraddress1.
	 */
	private String retaileraddress1;
	/**
	 * The retailerUrl property declared as String.
	 */
	private String retailerUrl;
	/**
	 * for retaileraddress2.
	 */
	private String retaileraddress2;

	/**
	 * The retaileraddress3 declared as String.
	 */
	private String retaileraddress3;

	/**
	 * The retaileraddress4 declared as String.
	 */
	private String retaileraddress4;

	/**
	 * The city declared as String.
	 */
	private String city;

	/**
	 * The state declared as String.
	 */
	private String state;

	/**
	 * The page declared as String.
	 */
	private String postalCode;

	/**
	 * The phone property declared as String.
	 */
	private String phone;
	/**
	 * The productPrice declared as String.
	 */
	private String productPrice;

	/**
	 * The imagePath declared as String.
	 */
	private String imagePath;

	/**
	 * The distance declared as String.
	 */
	private String distance;

	/**
	 * The latitude declared as double.
	 */
	private double latitude;

	/**
	 * The longitude declared as double.
	 */
	private double longitude;

	/**
	 * The poweredby declared as String.
	 */
	private String poweredby;

	/**
	 * The address property declared as String.
	 */
	private String address;

	/**
	 * its used to store retail location id.
	 */
	private String retLocId;

	private Integer retListId;

	private String bannerAdImagePath;

	private String ribbonAdImagePath;

	private String ribbonAdURL;

	private String advertisementId;

	public String getRetLocId() {
		return retLocId;
	}

	public void setRetLocId(String retLocId) {
		this.retLocId = retLocId;
	}

	/**
	 * Retrieve the value of address.
	 * 
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Set the value of address.
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		if (null == address || "".equals(address)) {
			this.address = HubCitiConstants.NOTAPPLICABLE;
		} else {

			this.address = address;
		}

	}

	/**
	 * To get productName.
	 * 
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * To set productName.
	 * 
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * To get retailerId.
	 * 
	 * @return the retailerId
	 */
	public String getRetailerId() {
		return retailerId;
	}

	/**
	 * To set retailerId.
	 * 
	 * @param retailerId
	 *            the retailerId to set
	 */
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}

	/**
	 * To get retailerName.
	 * 
	 * @return the retailerName
	 */
	public String getRetailerName() {
		return retailerName;
	}

	/**
	 * To set retailerName.
	 * 
	 * @param retailerName
	 *            the retailerName to set
	 */
	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	/**
	 * To get productPrice.
	 * 
	 * @return the productPrice
	 */
	public String getProductPrice() {
		return productPrice;
	}

	/**
	 * To set productPrice.
	 * 
	 * @param productPrice
	 *            the productPrice to set
	 */
	public void setProductPrice(String productPrice) {
		if (null == productPrice) {
			this.productPrice = HubCitiConstants.NOTAPPLICABLE;
		} else {

			this.productPrice = "$" + Utility.formatDecimalValue(productPrice);
		}
	}

	/**
	 * To get imagePath.
	 * 
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * To set imagePath.
	 * 
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
		if (null == distance || "".equals(distance)) {
			this.distance = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.distance = Utility.formatDecimalValue(distance);
		}

	}

	/**
	 * To get latitude.
	 * 
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * To set latitude.
	 * 
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * To get longitude.
	 * 
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * To set longitude.
	 * 
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * To get poweredby.
	 * 
	 * @return the poweredby
	 */
	public String getPoweredby() {
		return poweredby;
	}

	/**
	 * To set poweredby.
	 * 
	 * @param poweredby
	 *            the poweredby to set
	 */
	public void setPoweredby(String poweredby) {
		this.poweredby = poweredby;
	}

	/**
	 * To get retaileraddress1.
	 * 
	 * @return the retaileraddress1
	 */
	public String getRetaileraddress1() {
		return retaileraddress1;
	}

	/**
	 * To set retaileraddress1.
	 * 
	 * @param retaileraddress1
	 *            the retaileraddress1 to set
	 */
	public void setRetaileraddress1(String retaileraddress1) {
		if (null == retaileraddress1) {
			this.retaileraddress1 = "N/A";
		} else {
			this.retaileraddress1 = retaileraddress1;
		}
	}

	/**
	 * To get retaileraddress2.
	 * 
	 * @return the retaileraddress2
	 */
	public String getRetaileraddress2() {
		return retaileraddress2;
	}

	/**
	 * To set retaileraddress2.
	 * 
	 * @param retaileraddress2
	 *            the retaileraddress2 to set
	 */
	public void setRetaileraddress2(String retaileraddress2) {
		if (null == retaileraddress2) {
			this.retaileraddress2 = "N/A";
		} else {
			this.retaileraddress2 = retaileraddress2;
		}
	}

	/**
	 * To get retaileraddress3.
	 * 
	 * @return the retaileraddress3
	 */
	public String getRetaileraddress3() {
		return retaileraddress3;
	}

	/**
	 * To get retaileraddress3.
	 * 
	 * @param retaileraddress3
	 *            the retaileraddress3 to set
	 */
	public void setRetaileraddress3(String retaileraddress3) {
		if (null == retaileraddress3) {
			this.retaileraddress3 = "N/A";
		} else {
			this.retaileraddress3 = retaileraddress3;
		}
	}

	/**
	 * To get retaileraddress4.
	 * 
	 * @return the retaileraddress4
	 */
	public String getRetaileraddress4() {
		return retaileraddress4;
	}

	/**
	 * To set retaileraddress4.
	 * 
	 * @param retaileraddress4
	 *            the retaileraddress4 to set
	 */
	public void setRetaileraddress4(String retaileraddress4) {
		if (null == retaileraddress4) {
			this.retaileraddress4 = "N/A";
		} else {
			this.retaileraddress4 = retaileraddress4;
		}
	}

	/**
	 * To get city.
	 * 
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * To set city.
	 * 
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * To get state.
	 * 
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * To set state.
	 * 
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * To get postalCode.
	 * 
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * To set postalCode.
	 * 
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Retrieve the value of phone.
	 * 
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the value of phone.
	 * 
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {

		if (null == phone) {

			this.phone = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.phone = phone;
		}

	}

	/**
	 * @return the retailerUrl
	 */
	public String getRetailerUrl() {
		return retailerUrl;
	}

	/**
	 * @param retailerUrl
	 *            the retailerUrl to set
	 */
	public void setRetailerUrl(String retailerUrl) {
		if (null == retailerUrl) {
			this.retailerUrl = HubCitiConstants.NOTAPPLICABLE;

		} else {
			this.retailerUrl = retailerUrl;
		}

	}

	public Integer getRetListID() {
		return retListId;
	}

	public void setRetListID(Integer retListId) {
		this.retListId = retListId;
	}

	public String getBannerAdImagePath() {
		return bannerAdImagePath;
	}

	public void setBannerAdImagePath(String bannerAdImagePath) {

		if (null == bannerAdImagePath || "".equals(bannerAdImagePath)) {
			this.bannerAdImagePath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.bannerAdImagePath = bannerAdImagePath;
		}
	}

	public String getRibbonAdImagePath() {
		return ribbonAdImagePath;
	}

	public void setRibbonAdImagePath(String ribbonAdImagePath) {

		if (null == ribbonAdImagePath || "".equals(ribbonAdImagePath)) {
			this.ribbonAdImagePath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.ribbonAdImagePath = ribbonAdImagePath;
		}
	}

	public String getRibbonAdURL() {
		return ribbonAdURL;
	}

	public void setRibbonAdURL(String ribbonAdURL) {

		if (null == ribbonAdURL || "".equals(ribbonAdURL)) {
			this.ribbonAdURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.ribbonAdURL = ribbonAdURL;
		}
	}

	public String getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(String advertisementId) {

		if (null == advertisementId || "".equals(advertisementId)) {
			this.advertisementId = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.advertisementId = advertisementId;
		}
	}

}
