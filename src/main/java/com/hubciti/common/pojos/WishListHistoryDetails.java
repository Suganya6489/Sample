package com.hubciti.common.pojos;

import java.util.ArrayList;

/**
 * This pojo contains wish list product history list.
 * 
 * @author shyamsundara_hm
 */
public class WishListHistoryDetails {
	String responseCode;

	String responseText;

	/**
	 * for pagination.
	 */
	private Integer nextPage;

	/**
	 * for wish list history details.
	 */
	private ArrayList<WishListResultSet> productHistoryInfo;

	/**
	 * Gets the value of productHistoryInfo property.
	 * 
	 * @return the productHistoryInfo.
	 */
	public ArrayList<WishListResultSet> getProductHistoryInfo() {
		return productHistoryInfo;
	}

	/**
	 * Sets the value of productHistoryInfo property.
	 * 
	 * @param productHistoryInfo
	 *            the productHistoryInfo to set
	 */
	public void setProductHistoryInfo(ArrayList<WishListResultSet> productHistoryInfo) {
		this.productHistoryInfo = productHistoryInfo;
	}

	/**
	 * Gets the value of nextPage property.
	 * 
	 * @return the nextPage
	 */
	public Integer getNextPage() {
		return nextPage;
	}

	/**
	 * Sets the value of nextPage property.
	 * 
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

}
