package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * Thye ProductReview contains setter and getter methods for review fields.
 * 
 * @author dileepa_cc
 */
public class ProductReview {

	/**
	 * The totalReviews declared as String.
	 */
	private String totalReviews;

	/**
	 * The productReviewsID declared as integer.
	 */
	private Integer productReviewsID;

	/**
	 * The productId declared as integer.
	 */
	private Integer productId;

	/**
	 * The reviewURL declared as String.
	 */
	private String reviewURL;

	/**
	 * The reviewComments declared as String.
	 */
	private String reviewComments;

	/**
	 * To get productReviewsID.
	 * 
	 * @return the productReviewsID
	 */
	public Integer getProductReviewsID() {
		return productReviewsID;
	}

	/**
	 * To set productReviewsID.
	 * 
	 * @param productReviewsID
	 *            the productReviewsID to set
	 */
	public void setProductReviewsID(Integer productReviewsID) {
		this.productReviewsID = productReviewsID;
	}

	/**
	 * To get totalReviews.
	 * 
	 * @return the totalReviews
	 */
	public String getTotalReviews() {
		return totalReviews;
	}

	/**
	 * To set totalReviews.
	 * 
	 * @param totalReviews
	 *            the totalReviews to set
	 */
	public void setTotalReviews(String totalReviews) {
		this.totalReviews = totalReviews;
	}

	/**
	 * To get productId.
	 * 
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * To set productId.
	 * 
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * To get reviewURL.
	 * 
	 * @return the reviewURL
	 */
	public String getReviewURL() {
		return reviewURL;
	}

	/**
	 * To set reviewURL.
	 * 
	 * @param reviewURL
	 *            the reviewURL to set
	 */
	public void setReviewURL(String reviewURL) {
		this.reviewURL = reviewURL;
	}

	/**
	 * To get reviewComments.
	 * 
	 * @return the reviewComments
	 */
	public String getReviewComments() {
		return reviewComments;
	}

	/**
	 * To set reviewComments.
	 * 
	 * @param reviewComments
	 *            the reviewComments to set
	 */
	public void setReviewComments(String reviewComments) {
		if (null != reviewComments) {
			this.reviewComments = "<![CDATA[" + reviewComments + "]]>";
		} else {
			this.reviewComments = HubCitiConstants.NOTAPPLICABLE;
		}

	}

}
