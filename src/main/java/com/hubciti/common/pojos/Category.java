package com.hubciti.common.pojos;

import java.util.List;

/**
 * The POJO class for Category.
 * 
 * @author shyamsundara_hm
 */
public class Category {
	/**
	 * for product list.
	 */
	// private ArrayList<ProductDetail> productDetails;
	/**
	 * Variable parentCategoryName declared as String.
	 */
	private String parentCategoryName;

	/**
	 * Variable categoryId declared as Integer.
	 */
	private Integer categoryId;
	/**
	 * Variable categoryName declared as String.
	 */
	private String categoryName;
	/**
	 * Variable parentCategoryId declared as Integer.
	 */
	private Integer parentCategoryId;
	/**
	 * Variable subcategory declared as List.
	 */
	private List<SubCategory> subcategory;

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
	 * Variable category declared as String.
	 */
	private String category;

	/**
	 * Variable catId declared as String.
	 */
	private String catId;

	/**
	 * Gets the value of the subcategory property.
	 * 
	 * @return the subcategory
	 */
	public List<SubCategory> getSubcategory() {
		return subcategory;
	}

	/**
	 * Sets the value of the subcategory property.
	 * 
	 * @param subcategory
	 *            as of type List.
	 */
	public void setSubcategory(List<SubCategory> subcategory) {
		this.subcategory = subcategory;
	}

	/**
	 * Gets the value of the parentCategoryId property.
	 * 
	 * @return the parentCategoryId
	 */
	public Integer getParentCategoryId() {
		return parentCategoryId;
	}

	/**
	 * Sets the value of the parentCategoryId property.
	 * 
	 * @param parentCategoryId
	 *            as of type Integer.
	 */
	public void setParentCategoryId(Integer parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	/**
	 * to get parentCategoryName.
	 * 
	 * @return the parentCategoryName
	 */
	public String getParentCategoryName() {
		return parentCategoryName;
	}

	/**
	 * to set parentCategoryName.
	 * 
	 * @param parentCategoryName
	 *            the parentCategoryName to set
	 */
	public void setParentCategoryName(String parentCategoryName) {
		this.parentCategoryName = parentCategoryName;
	}

	/**
	 * to get productDetails.
	 * 
	 * @return the productDetails
	 */
	/*
	 * public ArrayList<ProductDetail> getProductDetails() { return
	 * productDetails; }
	 *//**
	 * to set productDetails.
	 * 
	 * @param productDetails
	 *            the productDetails to set
	 */
	/*
	 * public void setProductDetails(ArrayList<ProductDetail> productDetails) {
	 * this.productDetails = productDetails; }
	 */
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the catId
	 */
	public String getCatId() {
		return catId;
	}

	/**
	 * @param catId
	 *            the catId to set
	 */
	public void setCatId(String catId) {
		this.catId = catId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
