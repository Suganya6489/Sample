package com.hubciti.common.pojos;

import java.util.List;

/**
 * POJO class for user details
 * 
 * @author dhruvanath_mm
 */
public class UserDetails {

	private String responseCode;

	private String responseText;

	private String userName;

	private String password;

	private String appVersion;

	private String deviceId;

	private String latitude;

	private String longitude;

	private Integer userId;

	private Integer hubCitiId;

	private String hubCitiName;

	private String hubCitiKey;

	private Integer loginFlag;

	private Integer isHCActive;

	private Integer isPrefSet;

	private Integer addDevToUser;

	private String firstName;

	private String lastName;

	private String email;

	private String postalCode;

	private String defPostalCode;

	private String gender;

	private String dob;

	private String phoneNum;

	private String catIds;

	private Integer catId;

	private String module;

	private Integer mItemId;

	private Integer bottomBtnId;

	private Integer citiExpId;

	private Integer filterId;

	private Integer isFaqExist;

	public Integer evtTypeID;

	private Integer bandID;
	/**
	 * 
	 */

	private Integer fundId;

	private Integer subCatId;
	private Integer radius;
	private String catName;

	private Integer lowerLimit;

	private List<UserDetails> listUserDetails;

	private List<EducationLevel> arEducationList;
	private List<MaritalStatus> arMaritalList;
	private List<IncomeRange> arIncomeRangeList;

	private List<City> cityList = null;

	private String msg;

	/*
	 * For University Id
	 */
	private String univId;

	private String state;

	private String univName;

	private LoginFlowDetails regSuccessUI;

	private String iTunesURL;

	private String googlePlayURL;

	private String iTunesImg;

	private String androidImg;

	private Integer incomeRangeId;

	private Integer martialStatusId;

	private Integer educatonLevelId;

	private String position;

	private String fieldName;

	private String value;

	private Integer isUserOutOfCiti;

	private String cityIds;
	/**
	 * 
	 */
	public Integer linkId;
	/**
	 * 
	 */
	private Integer typeId;
	/**
	 * 
	 */
	private Integer departmentId;
	/**
	 * 
	 */
	public Integer level;
	/**
	 * The retailId property.
	 */
	private Integer retailId;
	/**
	 * The retailLocationID property.
	 */
	private Integer retailLocationId;

	private String srchKey;

	/**
	 * 
	 */
	public Integer menuId;

	/**
	 * 
	 */
	private String platform;

	/**
	 * 
	 */
	private Boolean requiredField;

	/**
	 * isTempUser Declared as a Integer
	 */
	private Integer isTempUser;

	/**
	 * noEmailCount is Declared Integer
	 */
	private Integer noEmailCount;

	public Integer getIsTempUser() {
		return isTempUser;
	}

	public void setIsTempUser(Integer isTempUser) {
		this.isTempUser = isTempUser;
	}

	public Integer getNoEmailCount() {
		return noEmailCount;
	}

	public void setNoEmailCount(Integer noEmailCount) {
		this.noEmailCount = noEmailCount;
	}

	public Boolean getRequiredField() {
		return requiredField;
	}

	public void setRequiredField(Boolean requiredField) {
		this.requiredField = requiredField;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getHubCitiName() {
		return hubCitiName;
	}

	public void setHubCitiName(String hubCitiName) {
		this.hubCitiName = hubCitiName;
	}

	public Integer getLoginFlag() {
		return loginFlag;
	}

	public void setLoginFlag(Integer loginFlag) {
		this.loginFlag = loginFlag;
	}

	public Integer getIsHCActive() {
		return isHCActive;
	}

	public void setIsHCActive(Integer isHCActive) {
		this.isHCActive = isHCActive;
	}

	public Integer getIsPrefSet() {
		return isPrefSet;
	}

	public void setIsPrefSet(Integer isPrefSet) {
		this.isPrefSet = isPrefSet;
	}

	public Integer getAddDevToUser() {
		return addDevToUser;
	}

	public void setAddDevToUser(Integer addDevToUser) {
		this.addDevToUser = addDevToUser;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			this.firstName = " ";
		} else {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			this.lastName = " ";
		} else {
			this.lastName = lastName;
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		if (postalCode == null || postalCode.equals("")) {
			this.postalCode = " ";
		} else {
			this.postalCode = postalCode;
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		if (gender == null || gender.equals("")) {
			this.gender = " ";
		} else {
			this.gender = gender;
		}
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		if (dob == null || dob.equals("")) {
			this.dob = " ";
		} else {
			this.dob = dob;
		}
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		if (phoneNum == null || phoneNum.equals("")) {
			this.phoneNum = " ";
		} else {
			this.phoneNum = phoneNum;
		}
	}

	public String getUnivId() {
		return univId;
	}

	public void setUnivId(String univId) {
		this.univId = univId;
	}

	public List<UserDetails> getListUserDetails() {
		return listUserDetails;
	}

	public void setListUserDetails(List<UserDetails> listUserDetails) {
		this.listUserDetails = listUserDetails;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (state == null || state.equals("")) {
			this.state = " ";
		} else {
			this.state = state;
		}
	}

	public String getUnivName() {
		return univName;
	}

	public void setUnivName(String univName) {
		if (univName == null || univName.equals("")) {
			this.univName = " ";
		} else {
			this.univName = univName;
		}
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

	public String getHubCitiKey() {
		return hubCitiKey;
	}

	public void setHubCitiKey(String hubCitiKey) {
		this.hubCitiKey = hubCitiKey;
	}

	public LoginFlowDetails getRegSuccessUI() {
		return regSuccessUI;
	}

	public void setRegSuccessUI(LoginFlowDetails regSuccessUI) {
		this.regSuccessUI = regSuccessUI;
	}

	public String getiTunesURL() {
		return iTunesURL;
	}

	public void setiTunesURL(String iTunesURL) {
		this.iTunesURL = iTunesURL;
	}

	public String getGooglePlayURL() {
		return googlePlayURL;
	}

	public void setGooglePlayURL(String googlePlayURL) {
		this.googlePlayURL = googlePlayURL;
	}

	public String getiTunesImg() {
		return iTunesImg;
	}

	public void setiTunesImg(String iTunesImg) {
		this.iTunesImg = iTunesImg;
	}

	public String getAndroidImg() {
		return androidImg;
	}

	public void setAndroidImg(String androidImg) {
		this.androidImg = androidImg;
	}

	public Integer getIncomeRangeId() {
		return incomeRangeId;
	}

	public void setIncomeRangeId(Integer incomeRangeId) {
		this.incomeRangeId = incomeRangeId;
	}

	public Integer getMartialStatusId() {
		return martialStatusId;
	}

	public void setMartialStatusId(Integer martialStatusId) {
		this.martialStatusId = martialStatusId;
	}

	public Integer getEducatonLevelId() {
		return educatonLevelId;
	}

	public void setEducatonLevelId(Integer educatonLevelId) {
		this.educatonLevelId = educatonLevelId;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		if (value == null) {
			this.value = "";
		} else {
			this.value = value;
		}
	}

	/**
	 * @return the arEducationList
	 */
	public List<EducationLevel> getArEducationList() {
		return arEducationList;
	}

	/**
	 * @param arEducationList
	 *            the arEducationList to set
	 */
	public void setArEducationList(List<EducationLevel> arEducationList) {
		this.arEducationList = arEducationList;
	}

	/**
	 * @return the arMaritalList
	 */
	public List<MaritalStatus> getArMaritalList() {
		return arMaritalList;
	}

	/**
	 * @param arMaritalList
	 *            the arMaritalList to set
	 */
	public void setArMaritalList(List<MaritalStatus> arMaritalList) {
		this.arMaritalList = arMaritalList;
	}

	/**
	 * @return the arIncomeRangeList
	 */
	public List<IncomeRange> getArIncomeRangeList() {
		return arIncomeRangeList;
	}

	/**
	 * @param arIncomeRangeList
	 *            the arIncomeRangeList to set
	 */
	public void setArIncomeRangeList(List<IncomeRange> arIncomeRangeList) {
		this.arIncomeRangeList = arIncomeRangeList;
	}

	public Integer getIsUserOutOfCiti() {
		return isUserOutOfCiti;
	}

	public void setIsUserOutOfCiti(Integer isUserOutOfCiti) {
		this.isUserOutOfCiti = isUserOutOfCiti;
	}

	public String getDefPostalCode() {
		return defPostalCode;
	}

	public void setDefPostalCode(String defPostalCode) {
		this.defPostalCode = defPostalCode;
	}

	/**
	 * @return the cityIds
	 */
	public String getCityIds() {
		return cityIds;
	}

	/**
	 * @param cityIds
	 *            the cityIds to set
	 */
	public void setCityIds(String cityIds) {
		this.cityIds = cityIds;
	}

	/**
	 * @return the srchKey
	 */
	public String getSrchKey() {
		return srchKey;
	}

	/**
	 * @param srchKey
	 *            the srchKey to set
	 */
	public void setSrchKey(String srchKey) {
		this.srchKey = srchKey;
	}

	/**
	 * @return the cityExpId
	 */
	public Integer getCitiExpId() {
		return citiExpId;
	}

	/**
	 * @param cityExpId
	 *            the cityExpId to set
	 */
	public void setCitiExpId(Integer citiExpId) {
		this.citiExpId = citiExpId;
	}

	/**
	 * @return the catIds
	 */
	public String getCatIds() {
		return catIds;
	}

	/**
	 * @param catIds
	 *            the catIds to set
	 */
	public void setCatIds(String catIds) {
		this.catIds = catIds;
	}

	/**
	 * @return the catId
	 */
	public Integer getCatId() {
		return catId;
	}

	/**
	 * @param catId
	 *            the catId to set
	 */
	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module
	 *            the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the mItemId
	 */
	public Integer getmItemId() {
		return mItemId;
	}

	/**
	 * @param mItemId
	 *            the mItemId to set
	 */
	public void setmItemId(Integer mItemId) {
		this.mItemId = mItemId;
	}

	/**
	 * @return the bottomBtnId
	 */
	public Integer getBottomBtnId() {
		return bottomBtnId;
	}

	/**
	 * @param bottomBtnId
	 *            the bottomBtnId to set
	 */
	public void setBottomBtnId(Integer bottomBtnId) {
		this.bottomBtnId = bottomBtnId;
	}

	/**
	 * @return the subCatId
	 */
	public Integer getSubCatId() {
		return subCatId;
	}

	/**
	 * @param subCatId
	 *            the subCatId to set
	 */
	public void setSubCatId(Integer subCatId) {
		this.subCatId = subCatId;
	}

	/**
	 * @return the radius
	 */
	public Integer getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            the radius to set
	 */
	public void setRadius(Integer radius) {
		this.radius = radius;
	}

	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}

	/**
	 * @param catName
	 *            the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}

	/**
	 * @return the retailId
	 */
	public Integer getRetailId() {
		return retailId;
	}

	/**
	 * @param retailId
	 *            the retailId to set
	 */
	public void setRetailId(Integer retailId) {
		this.retailId = retailId;
	}

	/**
	 * @return the retailLocationId
	 */
	public Integer getRetailLocationId() {
		return retailLocationId;
	}

	/**
	 * @param retailLocationId
	 *            the retailLocationId to set
	 */
	public void setRetailLocationId(Integer retailLocationId) {
		this.retailLocationId = retailLocationId;
	}

	/**
	 * @return the linkId
	 */
	public Integer getLinkId() {
		return linkId;
	}

	/**
	 * @param linkId
	 *            the linkId to set
	 */
	public void setLinkId(Integer linkId) {
		this.linkId = linkId;
	}

	/**
	 * @return the typeId
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the departmentId
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId
	 *            the departmentId to set
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the fundId
	 */
	public Integer getFundId() {
		return fundId;
	}

	/**
	 * @param fundId
	 *            the fundId to set
	 */
	public void setFundId(Integer fundId) {
		this.fundId = fundId;
	}

	/**
	 * @return the isFaqExist
	 */
	public Integer getIsFaqExist() {
		return isFaqExist;
	}

	/**
	 * @param isFaqExist
	 *            the isFaqExist to set
	 */
	public void setIsFaqExist(Integer isFaqExist) {
		this.isFaqExist = isFaqExist;
	}

	/**
	 * @return the platform
	 */
	public String getPlatform() {
		return platform;
	}

	/**
	 * @param platform
	 *            the platform to set
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	/**
	 * @return the lowerLimit
	 */
	public Integer getLowerLimit() {
		return lowerLimit;
	}

	/**
	 * @param lowerLimit
	 *            the lowerLimit to set
	 */
	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * @return the menuId
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            the menuId to set
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the filterId
	 */
	public Integer getFilterId() {
		return filterId;
	}

	/**
	 * @param filterId
	 *            the filterId to set
	 */
	public void setFilterId(Integer filterId) {
		this.filterId = filterId;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public Integer getEvtTypeID() {
		return evtTypeID;
	}

	public void setEvtTypeID(Integer evtTypeID) {
		this.evtTypeID = evtTypeID;
	}

	public Integer getBandID() {
		return bandID;
	}

	public void setBandID(Integer bandID) {
		this.bandID = bandID;
	}
}
