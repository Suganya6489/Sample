package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * The FindNearByPartialResponse pojo class contains setter and getter methods.
 * 
 * @author dileepa_cc
 */
public class FindNearByPartialResponse {

	/**
	 * The totalLocations declared as String.
	 */
	private String totalLocations;

	/**
	 * The lowestPrice declared as String.
	 */
	private String lowestPrice;

	/**
	 * The nearestRetailer declared as String.
	 */
	private String nearestRetailer;

	/**
	 * The page declared as String.
	 */
	private String page;

	/**
	 * for imagePath.
	 */
	private String imagePath;

	/**
	 * To get imagePath .
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
		if (imagePath == null) {
			this.imagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.imagePath = imagePath;
		}
	}

	/**
	 * To get page.
	 * 
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * To set page.
	 * 
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * To get totalLocations.
	 * 
	 * @return the totalLocations
	 */
	public String getTotalLocations() {
		return totalLocations;
	}

	/**
	 * To set totalLocations.
	 * 
	 * @param totalLocations
	 *            the totalLocations to set
	 */
	public void setTotalLocations(String totalLocations) {
		this.totalLocations = totalLocations;
	}

	/**
	 * To get nearestRetailer.
	 * 
	 * @return the nearestRetailer
	 */
	public String getNearestRetailer() {
		return nearestRetailer;
	}

	/**
	 * To get nearestRetailer.
	 * 
	 * @param nearestRetailer
	 *            the nearestRetailer to set
	 */
	public void setNearestRetailer(String nearestRetailer) {
		if (null == nearestRetailer || "".equals(nearestRetailer)) {
			this.nearestRetailer = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.nearestRetailer = Utility.roundNearestValues(nearestRetailer);
		}
	}

	/**
	 * To get lowestPrice.
	 * 
	 * @return the lowestPrice
	 */
	public String getLowestPrice() {
		return lowestPrice;
	}

	/**
	 * To set lowestPrice.
	 * 
	 * @param lowestPrice
	 *            the lowestPrice to set
	 */
	public void setLowestPrice(String lowestPrice) {
		if (null == lowestPrice) {
			this.lowestPrice = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (!lowestPrice.contains("$") && !HubCitiConstants.NOTAPPLICABLE.equals(lowestPrice)) {
				this.lowestPrice = "$" + lowestPrice;
			} else {
				this.lowestPrice = lowestPrice;
			}

		}

	}

}
