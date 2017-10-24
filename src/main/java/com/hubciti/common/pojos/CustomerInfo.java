package com.hubciti.common.pojos;

import java.util.List;

/*---------------------------------------------------------------
 * Author : Kirankumar Garaddi
 * written : 28/09/2015
 * 
 *  Compilation : Javac CustomerInfo.java
 *  Execution   : Java CustomerInfo
 *  
 *  CustomerInfo is a Pojo class for holding customer updated information
 *     
 * 
 ----------------------------------------------------------------*/
public class CustomerInfo {


	private String responseCode;
	private String responseText;
	//authkey variable;
	private String authKey;
	//loginEmail varibale
	private String loginEmail;
	//loginEmail varibale
	private String email;
	//password variable
	private String password;
	//groupAccessname variable
	private String groupAccessName;
	//firstName variable
	private String name;
	//firstName variable
	private String frmAddress;
	//firstName variable
	private String firstName;
	//lastName variable 
	private String lastName;
	//phone variable
	private String phone;
	//custCFName1 variable
	private String custCFName1;
	//custCFValue1 variable
	private String custCFValue1;
	//custCFName2 variable
	private String custCFName2;
	//custCFValue2 variable
	private String custCFValue2;
	//custCFName3 variable
	private String custCFName3;
	//custCFValue3 variable
	private String custCFValue3;
	//custCFName4 variable
	private String custCFName4;
	//custCFValue4 variable
	private String custCFValue4;
	//custCFName5 variable
	private String custCFName5;
	//custCFValue5 variable
	private String custCFValue5;
	private String retName;
	private String retAddress;
	private String retAddress2;
	private String city;
	private String state;
	private String postalCode;
	private String type;
	private String website;
	private String country;
	private Integer userId;
	private String claimImg;
	private String claimTxt;
	private String custEmail;
	private String custInfo;
	private String keywords;
	
	private String mailAddress;
	private String mailAddress2;
	private String mailCity;
	private String mailState;
	private String mailPhone;
	private String mailCountry;
	private String mailPostalCode;
	private Boolean isMailAddress;
	
	
	
	/**
	 * Variable for RetailerDetailList
	 */
	private List<CustomerInfo> customerInfoList;
	
	
	/**
	 * The retailLocationID property.
	 */
	private Integer retLocationId;
	
	/**
	 * Variable for hubcitiId
	 */
	private Integer hubCitiId;

	/**
	 * @return the hubCitiId
	 */
	public Integer getHubCitiId() {
		return hubCitiId;
	}

	/**
	 * @param hubCitiId the hubCitiId to set
	 */
	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * 
	 * @return the authKey
	 */
	public String getAuthKey() {
		return authKey;
	}

	/**
	 * 
	 * @param authKey to set
	 */
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	/**
	 * 
	 * @return the loginEmail
	 */
	public String getLoginEmail() {
		return loginEmail;
	}

	/**
	 * 
	 * @param loginEmail to set
	 */
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	/**
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return the groupAccessname
	 */
	public String getGroupAccessName() {
		return groupAccessName;
	}
	/**
	 * 
	 * @param groupAccessName to set
	 */
	public void setGroupAccessName(String groupAccessName) {
		this.groupAccessName = groupAccessName;
	}

	/**
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * 
	 * @return the phone number
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 
	 * @param phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @return the custCFName1
	 */
	public String getCustCFName1() {
		return custCFName1;
	}

	/**
	 * 
	 * @param custCFName1 to set
	 */
	public void setCustCFName1(String custCFName1) {
		this.custCFName1 = custCFName1;
	}

	/**
	 * 
	 * @return the custCFvalue1
	 */
	public String getCustCFValue1() {
		return custCFValue1;
	}

	/**
	 * 
	 * @param custCFValue1 to set
	 */
	public void setCustCFValue1(String custCFValue1) {
		this.custCFValue1 = custCFValue1;
	}

	/**
	 * 
	 * @return the custCFName2
	 */
	public String getCustCFName2() {
		return custCFName2;
	}

	/**
	 * 
	 * @param custCFName2 to set
	 */
	public void setCustCFName2(String custCFName2) {
		this.custCFName2 = custCFName2;
	}

	/**
	 * 
	 * @return the custCFValue2
	 */
	public String getCustCFValue2() {
		return custCFValue2;
	}

	/**
	 * 
	 * @param custCFValue2 to set
	 */
	public void setCustCFValue2(String custCFValue2) {
		this.custCFValue2 = custCFValue2;
	}

	/**
	 * 
	 * @return the custCFName3
	 */
	public String getCustCFName3() {
		return custCFName3;
	}

	/**
	 * 
	 * @param custCFName3 to set
	 */
	public void setCustCFName3(String custCFName3) {
		this.custCFName3 = custCFName3;
	}

	/**
	 * 
	 * @return the custCFValue3
	 */
	public String getCustCFValue3() {
		return custCFValue3;
	}

	/**
	 * 
	 * @param custCFValue3 to set
	 */
	public void setCustCFValue3(String custCFValue3) {
		this.custCFValue3 = custCFValue3;
	}

	/**
	 * 
	 * @return the custCFName4
	 */
	public String getCustCFName4() {
		return custCFName4;
	}

	/**
	 * 
	 * @param custCFName4 to set
	 */
	public void setCustCFName4(String custCFName4) {
		this.custCFName4 = custCFName4;
	}

	/**
	 * 
	 * @return the custCFValue4
	 */
	public String getCustCFValue4() {
		return custCFValue4;
	}

	/**
	 * 
	 * @param custCFValue4 to set
	 */
	public void setCustCFValue4(String custCFValue4) {
		this.custCFValue4 = custCFValue4;
	}

	/**
	 * 
	 * @return the custCFName5
	 */
	public String getCustCFName5() {
		return custCFName5;
	}

	/**
	 * 
	 * @param custCFName5 to set
	 */
	public void setCustCFName5(String custCFName5) {
		this.custCFName5 = custCFName5;
	}

	/**
	 * 
	 * @return the custCFValue5
	 */
	public String getCustCFValue5() {
		return custCFValue5;
	}
	
	/**
	 * 
	 * @param custCFValue5 to set
	 */
	public void setCustCFValue5(String custCFValue5) {
		this.custCFValue5 = custCFValue5;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the frmAddress
	 */
	public String getFrmAddress() {
		return frmAddress;
	}

	/**
	 * @param frmAddress the frmAddress to set
	 */
	public void setFrmAddress(String frmAddress) {
		this.frmAddress = frmAddress;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the retLocationId
	 */
	public Integer getRetLocationId() {
		return retLocationId;
	}

	/**
	 * @param retLocationId the retLocationId to set
	 */
	public void setRetLocationId(Integer retLocationId) {
		this.retLocationId = retLocationId;
	}

	/**
	 * @return the retName
	 */
	public String getRetName() {
		return retName;
	}

	/**
	 * @param retName the retName to set
	 */
	public void setRetName(String retName) {
		this.retName = retName;
	}

	/**
	 * @return the retAddress
	 */
	public String getRetAddress() {
		return retAddress;
	}

	/**
	 * @param retAddress the retAddress to set
	 */
	public void setRetAddress(String retAddress) {
		this.retAddress = retAddress;
	}

	/**
	 * @return the retAddress2
	 */
	public String getRetAddress2() {
		return retAddress2;
	}

	/**
	 * @param retAddress2 the retAddress2 to set
	 */
	public void setRetAddress2(String retAddress2) {
		this.retAddress2 = retAddress2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @param website the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the customerInfoList
	 */
	public List<CustomerInfo> getCustomerInfoList() {
		return customerInfoList;
	}

	/**
	 * @param customerInfoList the customerInfoList to set
	 */
	public void setCustomerInfoList(List<CustomerInfo> customerInfoList) {
		this.customerInfoList = customerInfoList;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the responseText
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * @param responseText the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getClaimImg() {
		return claimImg;
	}

	public void setClaimImg(String claimImg) {
		this.claimImg = claimImg;
	}

	public String getClaimTxt() {
		return claimTxt;
	}

	public void setClaimTxt(String claimTxt) {
		this.claimTxt = claimTxt;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustInfo() {
		return custInfo;
	}

	public void setCustInfo(String custInfo) {
		this.custInfo = custInfo;
	}

	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * @return the mailAddress2
	 */
	public String getMailAddress2() {
		return mailAddress2;
	}

	/**
	 * @param mailAddress2 the mailAddress2 to set
	 */
	public void setMailAddress2(String mailAddress2) {
		this.mailAddress2 = mailAddress2;
	}

	/**
	 * @return the mailCity
	 */
	public String getMailCity() {
		return mailCity;
	}

	/**
	 * @param mailCity the mailCity to set
	 */
	public void setMailCity(String mailCity) {
		this.mailCity = mailCity;
	}

	/**
	 * @return the mailState
	 */
	public String getMailState() {
		return mailState;
	}

	/**
	 * @param mailState the mailState to set
	 */
	public void setMailState(String mailState) {
		this.mailState = mailState;
	}

	/**
	 * @return the mailPhone
	 */
	public String getMailPhone() {
		return mailPhone;
	}

	/**
	 * @param mailPhone the mailPhone to set
	 */
	public void setMailPhone(String mailPhone) {
		this.mailPhone = mailPhone;
	}


	/**
	 * @return the mailCountry
	 */
	public String getMailCountry() {
		return mailCountry;
	}

	/**
	 * @param mailCountry the mailCountry to set
	 */
	public void setMailCountry(String mailCountry) {
		this.mailCountry = mailCountry;
	}

	/**
	 * @return the mailPostalCode
	 */
	public String getMailPostalCode() {
		return mailPostalCode;
	}

	/**
	 * @param mailPostalCode the mailPostalCode to set
	 */
	public void setMailPostalCode(String mailPostalCode) {
		this.mailPostalCode = mailPostalCode;
	}

	/**
	 * @return the isMailAddress
	 */
	public Boolean getIsMailAddress() {
		return isMailAddress;
	}

	/**
	 * @param isMailAddress the isMailAddress to set
	 */
	public void setIsMailAddress(Boolean isMailAddress) {
		this.isMailAddress = isMailAddress;
	}

}
