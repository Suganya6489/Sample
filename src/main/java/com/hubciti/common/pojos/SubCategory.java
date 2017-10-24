package com.hubciti.common.pojos;


public class SubCategory {

	private Integer subCatId;

	private String subCatName;

	private Integer catId;

	private Integer isAdded;

	public Integer getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(Integer subCatId) {
		this.subCatId = subCatId;
	}

	public String getSubCatName() {
		return subCatName;
	}

	public void setSubCatName(String subCatName) {
		if (null == subCatName ) {
			this.subCatName = subCatName;
		} else {
			this.subCatName = "<![CDATA[" + subCatName + "]]>";
		}
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getIsAdded() {
		return isAdded;
	}

	public void setIsAdded(Integer isAdded) {
		this.isAdded = isAdded;
	}

}
