package com.hubciti.common.pojos;

import java.util.ArrayList;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * The POJO class for HotDealsCategoryInfo.
 * 
 * @author shyamsundara_hm.
 */
public class HotDealsCategoryInfo {

	private String responseCode;

	private String responseText;
	/**
	 * for hotdeals count
	 */
	private String hotdealsCount;

	/**
	 * for PopulationCenterID
	 */
	private Integer populationCenterId;
	/**
	 * for DMAName.
	 */
	private String dMAName;

	/**
	 * Variable categoryId declared as Integer.
	 */
	private Integer categoryId;
	/**
	 * Variable categoryName declared as String.
	 */
	private String categoryName;
	/**
	 * Variable hotDealsDetailsArrayLst declared as ArrayList.
	 */
	private ArrayList<HotDealsDetails> hotDealsDetailsArrayLst;

	/**
	 * Variable hotDealAPIResultSetLst declared as ArrayList.
	 */
	// private ArrayList<HotDealAPIResultSet> hotDealAPIResultSetLst;

	/**
	 * To get hotDealAPIResultSetLst.
	 * 
	 * @return the hotDealAPIResultSetLst
	 */
	// public ArrayList<HotDealAPIResultSet> getHotDealAPIResultSetLst()
	// {
	// return hotDealAPIResultSetLst;
	// }

	/**
	 * To set hotDealAPIResultSetLst.
	 * 
	 * @param hotDealAPIResultSetLst
	 *            the hotDealAPIResultSetLst to set
	 */
	// public void setHotDealAPIResultSetLst(ArrayList<HotDealAPIResultSet>
	// hotDealAPIResultSetLst)
	// {
	// this.hotDealAPIResultSetLst = hotDealAPIResultSetLst;
	// }

	// HotDealAPIResultSet hotDealAPIResultSet = null;

	/**
	 * @return the hotDealAPIResultSet
	 */
	/*
	 * public HotDealAPIResultSet getHotDealAPIResultSet() { return
	 * hotDealAPIResultSet; }
	 *//**
	 * @param hotDealAPIResultSet
	 *            the hotDealAPIResultSet to set
	 */
	/*
	 * public void setHotDealAPIResultSet(HotDealAPIResultSet
	 * hotDealAPIResultSet) { this.hotDealAPIResultSet = hotDealAPIResultSet; }
	 */

	/**
	 * Gets the value of the categoryId property.
	 * 
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * Gets the value of the catID property.
	 * 
	 * @return the catID
	 */
	private String catID;

	/**
	 * Sets the value of the categoryId property.
	 * 
	 * @param categoryId
	 *            as of type Integer.
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * Gets the value of the categoryName property.
	 * 
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * Sets the value of the categoryName property.
	 * 
	 * @param categoryName
	 *            as of type String.
	 */
	public void setCategoryName(String categoryName) {
		if (null == categoryName) {
			this.categoryName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.categoryName = categoryName;
		}
	}

	/**
	 * Gets the value of the hotDealsDetailsArrayLst property.
	 * 
	 * @return the hotDealsDetailsArrayLst
	 */
	public ArrayList<HotDealsDetails> getHotDealsDetailsArrayLst() {
		return hotDealsDetailsArrayLst;
	}

	/**
	 * Sets the value of the hotDealsDetailsArrayLst property.
	 * 
	 * @param hotDealsDetailsArrayLst
	 *            as of type ArrayList.
	 */
	public void setHotDealsDetailsArrayLst(ArrayList<HotDealsDetails> hotDealsDetailsArrayLst) {
		this.hotDealsDetailsArrayLst = hotDealsDetailsArrayLst;
	}

	/**
	 * for retrieving populationCenterID
	 * 
	 * @return populationCenterID
	 */

	public Integer getPopulationCenterId() {
		return populationCenterId;
	}

	/**
	 * for setting populationCenterID
	 * 
	 * @return populationCenterID
	 */
	public void setPopulationCenterId(Integer populationCenterId) {
		this.populationCenterId = populationCenterId;
	}

	/**
	 * for getting dMAName
	 * 
	 * @return populationCenterID
	 */
	public String getdMAName() {
		return dMAName;
	}

	/**
	 * for setting dMAName
	 * 
	 * @return populationCenterID
	 */
	public void setdMAName(String dMAName) {
		this.dMAName = dMAName;
	}

	public String getHotdealsCount() {
		return hotdealsCount;
	}

	public void setHotdealsCount(String hotdealsCount) {
		this.hotdealsCount = hotdealsCount;
	}

	/**
	 * @return the catID
	 */
	public String getCatID() {
		return catID;
	}

	/**
	 * @param catID
	 *            the catID to set
	 */
	public void setCatID(String catID) {
		this.catID = catID;
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
