package com.hubciti.common.pojos;

import java.util.ArrayList;

/**
 * The ProductRatingReview class contains setter and getter methods for review
 * fields.
 * 
 * @author dileepa_cc
 */
public class ProductRatingReview {
	/**
	 * for product rate review image path
	 */
	private String stockImagePath;
	/**
	 * for product rare review header
	 */
	private String stockHeader;
	/**
	 * The totalReviews declared as String.
	 */
	private String totalReviews;
	/**
	 * The productReviews declared as ArrayList.
	 */
	private ArrayList<ProductReview> productReviews;

	/**
	 * The userRatingInfo declared as object.
	 */
	private UserRatingInfo userRatingInfo;

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
		if (totalReviews != null) {
			this.totalReviews = totalReviews + " " + "Reviews";
		} else {
			this.totalReviews = "0 " + "Reviews";
		}

	}

	/**
	 * To get productReviews.
	 * 
	 * @return the productReviews
	 */
	public ArrayList<ProductReview> getProductReviews() {
		return productReviews;
	}

	/**
	 * To set productReviews.
	 * 
	 * @param productReviews
	 *            the productReviews to set
	 */
	public void setProductReviews(ArrayList<ProductReview> productReviews) {
		this.productReviews = productReviews;
	}

	/**
	 * To get userRatingInfo.
	 * 
	 * @return the userRatingInfo
	 */
	public UserRatingInfo getUserRatingInfo() {
		return userRatingInfo;
	}

	/**
	 * To set userRatingInfo.
	 * 
	 * @param userRatingInfo
	 *            the userRatingInfo to set
	 */
	public void setUserRatingInfo(UserRatingInfo userRatingInfo) {
		this.userRatingInfo = userRatingInfo;
	}

	public String getStockImagePath() {
		return stockImagePath;
	}

	public void setStockImagePath(String stockImagePath) {
		this.stockImagePath = stockImagePath;
	}

	public String getStockHeader() {
		return stockHeader;
	}

	public void setStockHeader(String stockHeader) {
		this.stockHeader = stockHeader;
	}

}
