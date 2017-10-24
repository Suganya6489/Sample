package com.hubciti.common.pojos;

import java.util.ArrayList;
import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * For Category information.
 * 
 * @author Kumar D
 */
public class CategoryInfo {
	private String responseCode;

	private String responseText;
	/**
	 * for categoryID.
	 */
	private Integer categoryId;
	/**
	 * for parentCategoryName.
	 */
	private String parentCategoryName;
	/**
	 * for parentCategoryID.
	 */
	private Integer parentCategoryId;

	/**
	 * for category name
	 */
	private String categoryName;
	private String catName;
	private String catColor;
	private String catTxtColor;
	private String nonfeedlink;
	private String backButtonColor;

	/**
	 * For categort image path
	 */
	private String catImg;

	private String busCatName;

	private Integer busCatId;

	private String groupContent;
	


	private List<AlertDetails> alertList;

	private List<EventDetails> eventList;

	private ArrayList<CouponDetails> couponDetailsList;

	private ArrayList<HotDealsDetails> hDDetailsList;

	private List<RetailerDetail> retDetList;
	
	private List<Retailer> retailerList;
	
	private List<Fundraiser> fundraiserList;
	
	private List<Filter> filterList;
	private List<Item> itemList;

	/**
	 * for categoryID.
	 * 
	 * @return the categoryID
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * To set categoryID.
	 * 
	 * @param categoryID
	 *            the categoryID to set
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * To get parentCategoryName.
	 * 
	 * @return the parentCategoryName
	 */
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	/**
	 * To set parentCategoryName.
	 * 
	 * @param parentCategoryName
	 *            the parentCategoryName to set
	 */
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	/**
	 * To get parentCategoryID.
	 * 
	 * @return the parentCategoryID
	 */
	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * To set parentCategoryID.
	 * 
	 * @param parentCategoryID
	 *            the parentCategoryID to set
	 */
	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		if (categoryName == null || "".equals(categoryName)) {
			this.categoryName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.categoryName = "<![CDATA[" + categoryName + "]]>";
		}
	}

	/**
	 * To get category image path
	 * 
	 * @return catImg
	 */
	public String getCatImg() {
		return catImg;
	}

	/**
	 * To set category image path
	 * 
	 * @param catImg
	 */
	public void setCatImg(String catImg) {
		if ("".equals(Utility.checkNull(catImg))) {
			this.catImg = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.catImg = catImg;
		}
	}

	public String getBusCatName() {
		return busCatName;
	}

	public void setBusCatName(String busCatName) {
		if (null != busCatName) {
			this.busCatName = busCatName;
		} else {
			this.busCatName = HubCitiConstants.NOTAPPLICABLE;
		}
	}

	public Integer getBusCatId() {
		return busCatId;
	}

	public void setBusCatId(Integer busCatId) {
		this.busCatId = busCatId;
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

	public List<AlertDetails> getAlertList() {
		return alertList;
	}

	public void setAlertList(List<AlertDetails> alertList) {
		this.alertList = alertList;
	}

	public List<EventDetails> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDetails> eventList) {
		this.eventList = eventList;
	}

	public String getGroupContent() {
		return groupContent;
	}

	public void setGroupContent(String groupContent) {
		this.groupContent = groupContent;
	}

	/**
	 * @return the couponDetailsList
	 */
	public ArrayList<CouponDetails> getCouponDetailsList() {
		return couponDetailsList;
	}

	/**
	 * @param couponDetailsList
	 *            the couponDetailsList to set
	 */
	public void setCouponDetailsList(ArrayList<CouponDetails> couponDetailsList) {
		this.couponDetailsList = couponDetailsList;
	}

	public ArrayList<HotDealsDetails> gethDDetailsList() {
		return hDDetailsList;
	}

	public void sethDDetailsList(ArrayList<HotDealsDetails> hDDetailsList) {
		this.hDDetailsList = hDDetailsList;
	}

	public List<RetailerDetail> getRetDetList() {
		return retDetList;
	}

	public void setRetDetList(List<RetailerDetail> retDetList) {
		this.retDetList = retDetList;
	}

	public List<Retailer> getRetailerList() {
		return retailerList;
	}

	public void setRetailerList(List<Retailer> retailerList) {
		this.retailerList = retailerList;
	}

	/**
	 * @return the fundraiserList
	 */
	public List<Fundraiser> getFundraiserList() {
		return fundraiserList;
	}

	/**
	 * @param fundraiserList the fundraiserList to set
	 */
	public void setFundraiserList(List<Fundraiser> fundraiserList) {
		this.fundraiserList = fundraiserList;
	}

	/**
	 * @return the filterList
	 */
	public List<Filter> getFilterList() {
		return filterList;
	}

	/**
	 * @param filterList the filterList to set
	 */
	public void setFilterList(List<Filter> filterList) {
		this.filterList = filterList;
	}

	/**
	 * @return the itemList
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}

	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/**
	 * @return the catColor
	 */
	public String getCatColor() {
		return catColor;
	}

	/**
	 * @param catColor the catColor to set
	 */
	public void setCatColor(String catColor) {
		this.catColor = catColor;
	}

	/**
	 * @return the catTxtColor
	 */
	public String getCatTxtColor() {
		return catTxtColor;
	}

	/**
	 * @param catTxtColor the catTxtColor to set
	 */
	public void setCatTxtColor(String catTxtColor) {
		this.catTxtColor = catTxtColor;
	}

	public String getNonfeedlink() {
		return nonfeedlink;
	}

	public void setNonfeedlink(String nonfeedlink) {
		this.nonfeedlink = nonfeedlink;
	}

	public String getBackButtonColor() {
		return backButtonColor;
	}

	public void setBackButtonColor(String backButtonColor) {
		this.backButtonColor = backButtonColor;
	}
}
