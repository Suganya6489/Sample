package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * The POJO class for UserRatingInfo and share product comments.
 * 
 * @author Kumar D
 */

public class UserRatingInfo {
	private String responseCode;

	private String responseText;
	/**
	 * The isFromThisLocation declared as integer.
	 */
	private Integer isFromThisLocation;

	/**
	 * The retailerId declared as integer.
	 */
	private Integer retailerId;

	/**
	 * The retailerLocationId declared as integer.
	 */
	private Integer retailerLocationId;
	/**
	 * hubCitiId declared as integer.
	 */
	private Integer hubCitiId;

	/**
	 * The userId in request.
	 */
	private String userId;

	/**
	 * The productId in request.
	 */
	private String productId;

	/**
	 * for product description.
	 */
	private String prodShortDesc;

	/**
	 * for product email template text.
	 */
	private String prodStaticText;

	/**
	 * for scansee url.
	 */
	private String scanseeURL;

	/**
	 * for product price..
	 */
	private String prodSugPrice;

	/**
	 * For currentRating.
	 */
	private String currentRating;

	/**
	 * avgRating The average rating.
	 */
	private String avgRating;

	/**
	 * ratingInfo Rating Info.
	 */
	private String ratingInfo;

	/**
	 * for no of user rated the product.
	 */
	private String noOfUsersRated;
	/**
	 * for no of user rated the product.
	 */
	private List<UserRatingInfo> arUserRatingInfo;
	/**
	 * for no of user rated the product.
	 */
	private List<UserRatingInfo> userRatingInfo;

	/**
	 * Gets the value of retailerId property.
	 * 
	 * @return the retailerId
	 */
	public Integer getRetailerId() {
		return retailerId;
	}

	/**
	 * Sets the value of retailerId property.
	 * 
	 * @param retailerId
	 *            the retailerId to set
	 */
	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}

	/**
	 * Gets the value of retailerLocationId property.
	 * 
	 * @return the retailerLocationId
	 */
	public Integer getRetailerLocationId() {
		return retailerLocationId;
	}

	/**
	 * Sets the value of isFromThisLocation property.
	 * 
	 * @param retailerLocationId
	 *            the retailerLocationId to set
	 */
	public void setRetailerLocationId(Integer retailerLocationId) {
		this.retailerLocationId = retailerLocationId;
	}

	/**
	 * Gets the value of isFromThisLocation property.
	 * 
	 * @return the isFromThisLocation
	 */
	public Integer getIsFromThisLocation() {
		return isFromThisLocation;
	}

	/**
	 * Sets the value of prodSugPrice property.
	 * 
	 * @param isFromThisLocation
	 *            the isFromThisLocation to set
	 */
	public void setIsFromThisLocation(Integer isFromThisLocation) {
		this.isFromThisLocation = isFromThisLocation;
	}

	/**
	 * Gets the value of prodSugPrice property.
	 * 
	 * @return the prodSugPrice
	 */
	public String getProdSugPrice() {
		return prodSugPrice;
	}

	/**
	 * Sets the value of retailerId property.
	 * 
	 * @param prodSugPrice
	 *            the prodSugPrice to set
	 */
	public void setProdSugPrice(String prodSugPrice) {
		if (prodSugPrice == null) {
			this.prodSugPrice = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.prodSugPrice = prodSugPrice;
		}
	}

	/**
	 * Gets the value of prodShortDesc property.
	 * 
	 * @return the prodShortDesc
	 */
	public String getProdShortDesc() {
		return prodShortDesc;
	}

	/**
	 * Sets the value of prodShortDesc property.
	 * 
	 * @param prodShortDesc
	 *            the prodShortDesc to set
	 */
	public void setProdShortDesc(String prodShortDesc) {
		this.prodShortDesc = prodShortDesc;
	}

	/**
	 * Gets the value of prodStaticText property.
	 * 
	 * @return the prodStaticText
	 */
	public String getProdStaticText() {
		return prodStaticText;
	}

	/**
	 * Sets the value of prodStaticText property.
	 * 
	 * @param prodStaticText
	 *            the prodStaticText to set
	 */
	public void setProdStaticText(String prodStaticText) {
		this.prodStaticText = prodStaticText;

	}

	/**
	 * Gets the value of scanseeURL property.
	 * 
	 * @return the scanseeURL
	 */
	public String getScanseeURL() {
		return scanseeURL;
	}

	/**
	 * Sets the value of scanseeURL property.
	 * 
	 * @param scanseeURL
	 *            the scanseeURL to set
	 */
	public void setScanseeURL(String scanseeURL) {
		if ("".equals(Utility.checkNull(scanseeURL))) {
			this.scanseeURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.scanseeURL = scanseeURL;
		}
	}

	/**
	 * Gets the value of userId property.
	 * 
	 * @return userId To get
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the value of retailerId property.
	 * 
	 * @param userId
	 *            The userId in request.
	 */

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the value of productId property.
	 * 
	 * @return productId The productId in request.
	 */

	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the value of productId property.
	 * 
	 * @param productId
	 *            The productId in request.
	 */

	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Gets the value of currentRating property.
	 * 
	 * @return The currentRating.
	 */

	public String getCurrentRating() {
		return currentRating;
	}

	/**
	 * Sets the value of currentRating property.
	 * 
	 * @param currentRating
	 *            The currentRating.
	 */
	public void setCurrentRating(String currentRating) {
		this.currentRating = currentRating;
	}

	/**
	 * Gets the value of avgRating property.
	 * 
	 * @return avgRating The average rating.
	 */

	public String getAvgRating() {
		return avgRating;
	}

	/**
	 * Sets the value of avgRating property.
	 * 
	 * @param avgRating
	 *            avgRating The average rating.
	 */
	public void setAvgRating(String avgRating) {
		this.avgRating = avgRating;
	}

	/**
	 * Gets the value of ratingInfo property.
	 * 
	 * @return Rating Info.
	 */
	public String getRatingInfo() {
		return ratingInfo;
	}

	/**
	 * Sets the value of ratingInfo property.
	 * 
	 * @param ratingInfo
	 *            Rating Info.
	 */
	public void setRatingInfo(String ratingInfo) {
		this.ratingInfo = ratingInfo;
	}

	public String getNoOfUsersRated() {
		return noOfUsersRated;
	}

	public void setNoOfUsersRated(String noOfUsersRated) {
		if ("".equals(Utility.checkNull(noOfUsersRated))) {
			this.noOfUsersRated = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.noOfUsersRated = noOfUsersRated;
		}
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
	 * @return the arUserRatingInfo
	 */
	public List<UserRatingInfo> getArUserRatingInfo() {
		return arUserRatingInfo;
	}

	/**
	 * @param arUserRatingInfo
	 *            the arUserRatingInfo to set
	 */
	public void setArUserRatingInfo(List<UserRatingInfo> arUserRatingInfo) {
		this.arUserRatingInfo = arUserRatingInfo;
	}

	/**
	 * @return the userRatingInfo
	 */
	public List<UserRatingInfo> getUserRatingInfo() {
		return userRatingInfo;
	}

	/**
	 * @param userRatingInfo
	 *            the userRatingInfo to set
	 */
	public void setUserRatingInfo(List<UserRatingInfo> userRatingInfo) {
		this.userRatingInfo = userRatingInfo;
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

}
