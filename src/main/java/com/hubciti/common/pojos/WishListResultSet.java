package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * The POJO class for WishListResultSet.
 * 
 * @author dhruvanath_mm.
 */
public class WishListResultSet {
	/**
	 * for user zipcode.
	 */
	private String zipcode;
	/**
	 * for zopcode exists or not.
	 */
	private Integer zipCodeExists;
	/**
	 * For wish list history product delete date.
	 */
	private String wishListAddDate;

	/**
	 * For wish list history product delete date.
	 */
	private String dateScanned;

	/**
	 * Variable productDetail declared as List.
	 */
	private List<ProductDetail> productDetail;

	/**
	 * Gets the value of the productDetail property.
	 * 
	 * @return the productDetail
	 */
	public List<ProductDetail> getProductDetail() {
		return productDetail;
	}

	/**
	 * Sets the value of the productDetail property.
	 * 
	 * @param productDetail
	 *            as of type List.
	 */
	public void setProductDetail(List<ProductDetail> productDetail) {
		this.productDetail = productDetail;
	}

	/**
	 * Gets the value of the wishListAddDate property.
	 * 
	 * @return the wishListAddDate
	 */
	public String getWishListAddDate() {
		return wishListAddDate;
	}

	/**
	 * Sets the value of the wishListAddDate property.
	 * 
	 * @param wishListAddDate
	 *            the wishListAddDate to set
	 */
	public void setWishListAddDate(String wishListAddDate) {
		if (null == wishListAddDate) {
			this.wishListAddDate = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.wishListAddDate = wishListAddDate;
		}
	}

	/**
	 * Gets the value of the dateScanned property.
	 * 
	 * @return the dateScanned
	 */
	public String getDateScanned() {
		return dateScanned;
	}

	/**
	 * Sets the value of the dateScanned property.
	 * 
	 * @param dateScanned
	 *            the dateScanned to set
	 */
	public void setDateScanned(String dateScanned) {
		this.dateScanned = dateScanned;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getZipCodeExists() {
		return zipCodeExists;
	}

	public void setZipCodeExists(Integer zipCodeExists) {
		this.zipCodeExists = zipCodeExists;
	}

}
