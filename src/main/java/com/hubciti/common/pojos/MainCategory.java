package com.hubciti.common.pojos;

import java.util.List;

public class MainCategory {

	private Integer parCatId;

	private String parCatName;
	
	private Integer isAdded;
	
	private Integer catId;

	private String catName;
	
	private String catColor;
	private String catTxtColor;
	
	private Integer subCatId;

	private String subCatName;
	private Integer isSubCategory;
	
	
	
	private String sortOrder;
	private Integer flag;

	private List<MainCategory> mainCatList;
	
	private List<SubCategory> listSubCat;
	
	private String nonfeedlink;

	public Integer getParCatId() {
		return parCatId;
	}

	public void setParCatId(Integer parCatId) {
		this.parCatId = parCatId;
	}

	public String getParCatName() {
		return parCatName;
	}

	public void setParCatName(String parCatName) {
		if (null == parCatName ) {
			this.parCatName = parCatName;
		} else {
			this.parCatName = "<![CDATA[" + parCatName + "]]>";
		}
	}

	public List<SubCategory> getListSubCat() {
		return listSubCat;
	}

	public void setListSubCat(List<SubCategory> listSubCat) {
		this.listSubCat = listSubCat;
	}

	public Integer getIsAdded() {
		return isAdded;
	}

	public void setIsAdded(Integer isAdded) {
		this.isAdded = isAdded;
	}

	/**
	 * @return the catId
	 */
	public Integer getCatId() {
		return catId;
	}

	/**
	 * @param catId the catId to set
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
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
	 * @return the subCatId
	 */
	public Integer getSubCatId() {
		return subCatId;
	}

	/**
	 * @param subCatId the subCatId to set
	 */
	public void setSubCatId(Integer subCatId) {
		this.subCatId = subCatId;
	}

	/**
	 * @return the subCatName
	 */
	public String getSubCatName() {
		return subCatName;
	}

	/**
	 * @param subCatName the subCatName to set
	 */
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}

	/**
	 * @return the sortOrder
	 */
	public String getSortOrder() {
		return sortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the flag
	 */
	public Integer getFlag() {
		return flag;
	}

	/**
	 * @param flag the flag to set
	 */
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	/**
	 * @return the mainCatList
	 */
	public List<MainCategory> getMainCatList() {
		return mainCatList;
	}

	/**
	 * @param mainCatList the mainCatList to set
	 */
	public void setMainCatList(List<MainCategory> mainCatList) {
		this.mainCatList = mainCatList;
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
	 * @return the isSubCategory
	 */
	public Integer getIsSubCategory() {
		return isSubCategory;
	}

	/**
	 * @param isSubCategory the isSubCategory to set
	 */
	public void setIsSubCategory(Integer isSubCategory) {
		this.isSubCategory = isSubCategory;
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
}
