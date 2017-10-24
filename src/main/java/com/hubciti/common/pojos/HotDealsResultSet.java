package com.hubciti.common.pojos;

import java.util.ArrayList;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * The POJO class for HotDealsResultSet.
 * 
 * @author shyamsundara_hm.
 */
/**
 * @author kumar_dodda
 */
public class HotDealsResultSet {

	/**
	 * for city.
	 */
	private String city;
	/**
	 * for hotdeals category.
	 */
	private String category;

	/**
	 * Variable categoryId declared as Integer.
	 */
	private Integer categoryId;
	/**
	 * Variable categoryName declared as String.
	 */
	private String categoryName;
	/**
	 * Variable apiPartnerId declared as Integer.
	 */
	private Integer apiPartnerId;
	/**
	 * Variable apiPartnerName declared as String.
	 */
	private String apiPartnerName;
	/**
	 * Variable hotDealName declared as String.
	 */
	private String hotDealName;
	/**
	 * Variable hotDealId declared as Integer.
	 */
	private Integer hotDealId;
	/**
	 * Variable hotDealImagePath declared as String.
	 */
	private String hotDealImagePath;
	/**
	 * Variable hDshortDescription declared as String.
	 */
	private String hDshortDescription;
	/**
	 * Variable hDLognDescription declared as String.
	 */
	private String hDLognDescription;
	/**
	 * Variable hDPrice declared as String.
	 */
	private String hDPrice;
	/**
	 * Variable hDSalePrice declared as String.
	 */
	private String hDSalePrice;
	/**
	 * Variable hDTermsConditions declared as String.
	 */
	private String hDTermsConditions;
	/**
	 * Variable hdURL declared as String.
	 */
	private String hdURL;
	/**
	 * Variable hDStartDate declared as String.
	 */
	private String hDStartDate;
	/**
	 * Variable hDEndDate declared as String.
	 */
	private String hDEndDate;
	/**
	 * Variable hDDiscountType declared as String.
	 */
	private String hDDiscountType;
	/**
	 * Variable hDDiscountAmount declared as double.
	 */
	private String hDDiscountAmount;
	/**
	 * Variable hDDiscountPct declared as of type double.
	 */
	private String hDDiscountPct;
	/**
	 * Variable productID declared as of type Integer.
	 */
	private Integer productId;

	/**
	 * Flag for Pagination which tell whether next set of records available or
	 * not.
	 */
	private Integer nextPage;

	/**
	 * FavCat declared as String.
	 */
	private Integer FavCat;

	/**
	 * distance declared as String.
	 */
	private String distance;

	/**
	 * The rowNumber property.
	 */
	private Integer rowNumber;

	private String catId;
	/**
	 * For hot deal list ID
	 */
	private Integer hotDealListId;

	/**
	 * For new Flag
	 */
	Integer newFlag;

	/**
	 * For external flag
	 */
	Integer extFlag;
	
	String dealType;
	
	private ArrayList<HotDealsDetails> hotDealsDetailslst;
	
	private ArrayList<HotDealsResultSet> hdResultSetList;

	/**
	 * Gets the value of distance.
	 * 
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * Sets the value of distance property.
	 * 
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(String distance) {
		this.distance = distance;
	}

	/**
	 * Gets the value of favCat property.
	 * 
	 * @return the favCat
	 */
	public Integer getFavCat() {
		return FavCat;
	}

	/**
	 * Sets the value of favCat property.
	 * 
	 * @param favCat
	 *            the favCat to set
	 */
	public void setFavCat(Integer favCat) {
		FavCat = favCat;
	}

	/**
	 * To get Rownumber.
	 * 
	 * @return the rowNumber
	 */
	public Integer getRowNumber() {
		return rowNumber;
	}

	/**
	 * To set row number.
	 * 
	 * @param rowNumber
	 *            the rowNumber to set
	 */
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * to get next page.
	 * 
	 * @return the nextPage
	 */
	public Integer getNextPage() {
		return nextPage;
	}

	/**
	 * to set next page.
	 * 
	 * @param nextPage
	 *            the nextPage to set
	 */
	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	/**
	 * Gets the value of the hDPrice property.
	 * 
	 * @return the hDPrice
	 */
	public String gethDPrice() {
		return hDPrice;
	}

	/**
	 * Sets the value of the hDPrice property.
	 * 
	 * @param hDPrice
	 *            as of type String.
	 */
	public void sethDPrice(String hDPrice) {
		if (null == hDPrice || hDPrice.contains(HubCitiConstants.NOTAPPLICABLE)) {
			this.hDPrice = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (!hDPrice.contains("$")) {
				this.hDPrice = Utility.formatDecimalValue(hDPrice);
			} else {
				this.hDPrice = hDPrice;
			}
	}

	/**
	 * Gets the value of the hDSalePrice property.
	 * 
	 * @return the hDSalePrice
	 */
	public String gethDSalePrice() {
		return hDSalePrice;
	}

	/**
	 * Sets the value of the hDSalePrice property.
	 * 
	 * @param hDSalePrice
	 *            as of type String.
	 */
	public void sethDSalePrice(String hDSalePrice) {
		if (null == hDSalePrice || hDSalePrice.contains(HubCitiConstants.NOTAPPLICABLE)) {
			this.hDSalePrice = HubCitiConstants.NOTAPPLICABLE;
		} else
			if (!hDSalePrice.contains("$")) {
				this.hDSalePrice = Utility.formatDecimalValue(hDSalePrice);
			} else {
				this.hDSalePrice = hDSalePrice;
			}

	}

	/**
	 * Gets the value of the productID property.
	 * 
	 * @return the productID
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * Sets the value of the productID property.
	 * 
	 * @param productID
	 *            as of type Integer.
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * Gets the value of the hDDiscountType property.
	 * 
	 * @return the hDDiscountType
	 */
	public String gethDDiscountType() {
		return hDDiscountType;
	}

	/**
	 * Sets the value of the hDDiscountType property.
	 * 
	 * @param hDDiscountType
	 *            as of type String.
	 */
	public void sethDDiscountType(String hDDiscountType) {
		if (null == hDDiscountType) {
			this.hDDiscountType = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDDiscountType = hDDiscountType;
		}
	}

	/**
	 * Gets the value of the hotDealImagePath property.
	 * 
	 * @return the hotDealImagePath
	 */
	public String getHotDealImagePath() {
		return hotDealImagePath;
	}

	/**
	 * Sets the value of the hotDealImagePath property.
	 * 
	 * @param hotDealImagePath
	 *            as of type String.
	 */
	public void setHotDealImagePath(String hotDealImagePath) {
		if (null == hotDealImagePath || "".equals(hotDealImagePath)) {
			this.hotDealImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.hotDealImagePath = hotDealImagePath;
		}

	}

	/**
	 * Gets the value of the apiPartnerId property.
	 * 
	 * @return the apiPartnerId
	 */
	public Integer getApiPartnerId() {
		return apiPartnerId;
	}

	/**
	 * Sets the value of the apiPartnerId property.
	 * 
	 * @param apiPartnerId
	 *            as of type Integer.
	 */
	public void setApiPartnerId(Integer apiPartnerId) {
		this.apiPartnerId = apiPartnerId;
	}

	/**
	 * Gets the value of the apiPartnerName property.
	 * 
	 * @return the apiPartnerName
	 */
	public String getApiPartnerName() {
		return apiPartnerName;
	}

	/**
	 * Sets the value of the apiPartnerName property.
	 * 
	 * @param apiPartnerName
	 *            as of type String.
	 */
	public void setApiPartnerName(String apiPartnerName) {
		if (null == apiPartnerName) {
			this.apiPartnerName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.apiPartnerName = apiPartnerName;
		}
	}

	/**
	 * Gets the value of the categoryId property.
	 * 
	 * @return the categoryId
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

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
		if (categoryName == null) {
			this.categoryName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.categoryName = categoryName.trim();
		}
	}

	/**
	 * Gets the value of the hotDealName property.
	 * 
	 * @return the hotDealName
	 */
	public String getHotDealName() {
		return hotDealName;
	}

	/**
	 * Sets the value of the hotDealName property.
	 * 
	 * @param hotDealName
	 *            as of type String.
	 */
	public void setHotDealName(String hotDealName) {
		if (null == hotDealName) {
			this.hotDealName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hotDealName = hotDealName;
		}
	}

	/**
	 * Gets the value of the hotDealId property.
	 * 
	 * @return the hotDealId
	 */
	public Integer getHotDealId() {
		return hotDealId;
	}

	/**
	 * Sets the value of the hotDealId property.
	 * 
	 * @param hotDealId
	 *            as of type Integer.
	 */
	public void setHotDealId(Integer hotDealId) {
		this.hotDealId = hotDealId;
	}

	/**
	 * Gets the value of the hDshortDescription property.
	 * 
	 * @return the hDshortDescription
	 */
	public String gethDshortDescription() {
		return hDshortDescription;
	}

	/**
	 * Sets the value of the hDshortDescription property.
	 * 
	 * @param hDshortDescription
	 *            as of type String.
	 */
	public void sethDshortDescription(String hDshortDescription) {
		// hDshortDescription = Utility.removeHTMLTags(hDshortDescription);
		// hDshortDescription = hDshortDescription.replaceAll("&#xd;", "");

		if (null == hDshortDescription) {
			this.hDshortDescription = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if ("".equals(hDshortDescription)) {
				this.hDshortDescription = HubCitiConstants.NOTAPPLICABLE;
			} else {
				this.hDshortDescription = hDshortDescription;
			}

		}
	}

	/**
	 * Gets the value of the hDLognDescription property.
	 * 
	 * @return the hDLognDescription
	 */
	public String gethDLognDescription() {
		return hDLognDescription;
	}

	/**
	 * Sets the value of the hDLognDescription property.
	 * 
	 * @param hDLognDescription
	 *            as of type String.
	 */
	public void sethDLognDescription(String hDLognDescription) {
		// hDLognDescription = Utility.removeHTMLTags(hDLognDescription);
		// hDLognDescription = hDLognDescription.replaceAll("&#xd;", "");

		if (null == hDLognDescription) {
			this.hDLognDescription = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDLognDescription = hDLognDescription;
		}
	}

	/**
	 * Gets the value of the hDTermsConditions property.
	 * 
	 * @return the hDTermsConditions
	 */
	public String gethDTermsConditions() {
		return hDTermsConditions;
	}

	/**
	 * Sets the value of the hDTermsConditions property.
	 * 
	 * @param hDTermsConditions
	 *            as of type String.
	 */
	public void sethDTermsConditions(String hDTermsConditions) {
		// hDTermsConditions = Utility.removeHTMLTags(hDTermsConditions);
		// hDTermsConditions = hDTermsConditions.replaceAll("&#xd;", "");

		if (null == hDTermsConditions) {
			this.hDTermsConditions = HubCitiConstants.NOTAPPLICABLE;
		} else {
			if (hDTermsConditions.contains("<![CDATA[")) {
				this.hDTermsConditions = hDTermsConditions;
			} else {
				this.hDTermsConditions = "<![CDATA[" + hDTermsConditions + "]]>";
			}
		}
	}

	/**
	 * Gets the value of the hdURL property.
	 * 
	 * @return the hdURL
	 */
	public String getHdURL() {
		return hdURL;
	}

	/**
	 * Sets the value of the hdURL property.
	 * 
	 * @param hdURL
	 *            as of type String.
	 */
	public void setHdURL(String hdURL) {
		if (null == hdURL) {
			this.hdURL = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hdURL = hdURL;
		}
	}

	/**
	 * Gets the value of the hDStartDate property.
	 * 
	 * @return the hDStartDate
	 */
	public String gethDStartDate() {
		return hDStartDate;
	}

	/**
	 * Sets the value of the hDStartDate property.
	 * 
	 * @param hDStartDate
	 *            as of type String.
	 */
	public void sethDStartDate(String hDStartDate) {
		if (null == hDStartDate) {
			this.hDStartDate = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDStartDate = hDStartDate;
		}
	}

	/**
	 * Gets the value of the hDEndDate property.
	 * 
	 * @return the hDEndDate
	 */
	public String gethDEndDate() {
		return hDEndDate;
	}

	/**
	 * Sets the value of the hDEndDate property.
	 * 
	 * @param hDEndDate
	 *            as of type String.
	 */
	public void sethDEndDate(String hDEndDate) {
		if (null == hDEndDate) {
			this.hDEndDate = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDEndDate = hDEndDate;
		}
	}

	/**
	 * to get category.
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * To set category.
	 * 
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		if (null == category) {
			this.category = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.category = category;
		}
	}

	/**
	 * to get hDDiscountAmount.
	 * 
	 * @return the hDDiscountAmount
	 */
	public String gethDDiscountAmount() {
		return hDDiscountAmount;
	}

	/**
	 * to set hDDiscountAmount.
	 * 
	 * @param hDDiscountAmount
	 *            the hDDiscountAmount to set
	 */
	public void sethDDiscountAmount(String hDDiscountAmount) {
		if (null == hDDiscountAmount) {
			this.hDDiscountAmount = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDDiscountAmount = hDDiscountAmount;
		}

	}

	/**
	 * to get hDDiscountPct.
	 * 
	 * @return the hDDiscountPct
	 */
	public String gethDDiscountPct() {
		return hDDiscountPct;
	}

	/**
	 * to set hDDiscountPct.
	 * 
	 * @param hDDiscountPct
	 *            the hDDiscountPct to set
	 */
	public void sethDDiscountPct(String hDDiscountPct) {
		if (null == hDDiscountPct) {
			this.hDDiscountPct = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hDDiscountPct = hDDiscountPct;
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getHotDealListId() {
		return hotDealListId;
	}

	public void setHotDealListID(Integer hotDealListId) {
		this.hotDealListId = hotDealListId;
	}

	/**
	 * @return the catID
	 */
	public String getCatId() {
		return catId;
	}

	/**
	 * @param catID
	 *            the catID to set
	 */
	public void setCatId(String catId) {
		this.catId = catId;
	}

	public Integer getNewFlag() {
		return newFlag;
	}

	public void setNewFlag(Integer newFlag) {
		this.newFlag = newFlag;
	}

	public Integer getExtFlag() {
		return extFlag;
	}

	public void setExtFlag(Integer extFlag) {
		this.extFlag = extFlag;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	/**
	 * @return the hotDealsDetailslst
	 */
	public ArrayList<HotDealsDetails> getHotDealsDetailslst() {
		return hotDealsDetailslst;
	}

	/**
	 * @param hotDealsDetailslst the hotDealsDetailslst to set
	 */
	public void setHotDealsDetailslst(ArrayList<HotDealsDetails> hotDealsDetailslst) {
		this.hotDealsDetailslst = hotDealsDetailslst;
	}

	/**
	 * @return the hdResultSetList
	 */
	public ArrayList<HotDealsResultSet> getHdResultSetList() {
		return hdResultSetList;
	}

	/**
	 * @param hdResultSetList the hdResultSetList to set
	 */
	public void setHdResultSetList(ArrayList<HotDealsResultSet> hdResultSetList) {
		this.hdResultSetList = hdResultSetList;
	}

}
