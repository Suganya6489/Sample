package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

public class CategoryDetails {

	private String responseCode;

	private String responseText;

	private Integer catId;
	private String header;
	private String headOne;
	private String headTwo;

	private String catName;

	private Integer parCatId;

	private String parCatName;
	
	private String catColor;

	private Integer subCatId;

	private String subCatName;
	
	private Integer isAdded;
	
	private Integer isBkMark;

	private Integer isSubCategory;
	
	private String sortOrder;
	
	private String catTxtColor;

	private Integer isHubFunctn;

	private List<CategoryDetails> listCatDetails;
	
	private List<CategoryDetails> bookMarkList;

	private String catIds;

	private Integer userId;
	
	private String defPostalCode;
	
	private String catImgPath;

	private List<MainCategory> listMainCat;
	
	private List<BottomButton> bottomBtnList;
	
	private String subCatIds;
	
	private Integer hubCitiId;
	
	private List<Menu> menuList;
	
	private String nonfeedlink;
	
	private String backButtonColor;
	
	private String bkImgPath;
	
	private String homeImgPath;
	
	private String weatherURL;
	
	private String bannerImg;
	
	private String titleBkGrdColor;
	
	private String hamburgerImg;
	

	
	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

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
		this.parCatName = parCatName;
	}

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
		this.subCatName = subCatName;
	}

	public List<CategoryDetails> getListCatDetails() {
		return listCatDetails;
	}

	public void setListCatDetails(List<CategoryDetails> listCatDetails) {
		this.listCatDetails = listCatDetails;
	}

	public String getCatIds() {
		return catIds;
	}

	public void setCatIds(String catIds) {
		this.catIds = catIds;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsAdded() {
		return isAdded;
	}

	public void setIsAdded(Integer isAdded) {
		this.isAdded = isAdded;
	}

	public List<MainCategory> getListMainCat() {
		return listMainCat;
	}

	public void setListMainCat(List<MainCategory> listMainCat) {
		this.listMainCat = listMainCat;
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

	public String getDefPostalCode() {
		return defPostalCode;
	}

	public void setDefPostalCode(String defPostalCode) {
		this.defPostalCode = defPostalCode;
	}

	public String getCatImgPath() {
		return catImgPath;
	}

	public void setCatImgPath(String catImgPath) {
		if ("".equals(Utility.checkNull(catImgPath))) {
			this.catImgPath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.catImgPath = catImgPath;
		}
	}

	public List<BottomButton> getBottomBtnList() {
		return bottomBtnList;
	}

	public void setBottomBtnList(List<BottomButton> bottomBtnList) {
		this.bottomBtnList = bottomBtnList;
	}

	public String getSubCatIds() {
		return subCatIds;
	}

	public void setSubCatIds(String subCatIds) {
		this.subCatIds = subCatIds;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public String getHeadOne() {
		return headOne;
	}

	public void setHeadOne(String headOne) {
		this.headOne = headOne;
	}

	public String getHeadTwo() {
		return headTwo;
	}

	public void setHeadTwo(String headTwo) {
		this.headTwo = headTwo;
	}

	/**
	 * @return the menuList
	 */
	public List<Menu> getMenuList() {
		return menuList;
	}

	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
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
	 * @return the bookMarkList
	 */
	public List<CategoryDetails> getBookMarkList() {
		return bookMarkList;
	}

	/**
	 * @param bookMarkList the bookMarkList to set
	 */
	public void setBookMarkList(List<CategoryDetails> bookMarkList) {
		this.bookMarkList = bookMarkList;
	}

	/**
	 * @return the header
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return the isHubFunctn
	 */
	public Integer getIsHubFunctn() {
		return isHubFunctn;
	}

	/**
	 * @param isHubFunctn the isHubFunctn to set
	 */
	public void setIsHubFunctn(Integer isHubFunctn) {
		this.isHubFunctn = isHubFunctn;
	}

	/**
	 * @return the isBkMark
	 */
	public Integer getIsBkMark() {
		return isBkMark;
	}

	/**
	 * @param isBkMark the isBkMark to set
	 */
	public void setIsBkMark(Integer isBkMark) {
		this.isBkMark = isBkMark;
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

	public String getBackButtonColor() {
		return backButtonColor;
	}

	public void setBackButtonColor(String backButtonColor) {
		this.backButtonColor = backButtonColor;
	}

	public String getBkImgPath() {
		return bkImgPath;
	}

	public void setBkImgPath(String bkImgPath) {
		this.bkImgPath = bkImgPath;
	}

	public String getHomeImgPath() {
		return homeImgPath;
	}

	public void setHomeImgPath(String homeImgPath) {
		this.homeImgPath = homeImgPath;
	}

	public String getWeatherURL() {
		return weatherURL;
	}

	public void setWeatherURL(String weatherURL) {
		this.weatherURL = weatherURL;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public String getTitleBkGrdColor() {
		return titleBkGrdColor;
	}

	public void setTitleBkGrdColor(String titleBkGrdColor) {
		this.titleBkGrdColor = titleBkGrdColor;
	}

	public String getHamburgerImg() {
		return hamburgerImg;
	}

	public void setHamburgerImg(String hamburgerImg) {
		this.hamburgerImg = hamburgerImg;
	}
}
