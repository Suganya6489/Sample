package com.hubciti.common.pojos;

/**
 * The class has getter and setter methods for UserRegistrationInfo.
 * 
 * @author Kumar D.
 */

public class UserRegistrationInfo {

	/**
	 * for university ids
	 */
	private String universityIDs;

	/**
	 * for facebook flag
	 */
	protected Integer fromFacebook;
	/**
	 * for push notification.
	 */

	protected String userTokenID;
	// Commented as there is change in User table
	// protected String userName;
	/**
	 * Variable email declared as String.
	 */
	protected String email;
	/**
	 * Variable password declared as String.
	 */
	protected String password;
	/**
	 * Variable dateCreated declared as String.
	 */
	protected String dateCreated;
	/**
	 * Variable deviceID declared as String.
	 */
	protected String deviceID;
	/**
	 * Variable userLongitude declared as Double.
	 */
	protected Double userLongitude;
	/**
	 * Variable userLatitude declared as Double.
	 */
	protected Double userLatitude;
	/**
	 * Variable firstName declared as String.
	 */
	protected String firstName;
	/**
	 * Variable lastName declared as String.
	 */
	protected String lastName;
	/**
	 * Variable address1 declared as String.
	 */
	protected String address1;
	/**
	 * Variable address2 declared as String.
	 */
	protected String address2;
	/**
	 * Variable address3 declared as String.
	 */
	protected String address3;
	/**
	 * Variable address4 declared as String.
	 */
	protected String address4;
	/**
	 * Variable state declared as String.
	 */
	protected String state;
	/**
	 * Variable city declared as String.
	 */
	protected String city;
	/**
	 * Variable postalCode declared as Integer.
	 */
	protected String postalCode;
	/**
	 * Variable mobileNumber declared as String.
	 */
	protected String mobileNumber;
	/**
	 * Variable fieldAgent declared as Integer.
	 */
	protected Integer fieldAgent;
	/**
	 * Variable pushNotify declared as Integer.
	 */
	protected Integer pushNotify;
	/**
	 * Variable firstUseComplete declared as Integer.
	 */
	protected Integer firstUseComplete;
	/**
	 * Variable gender declared as Integer.
	 */
	protected Integer gender;
	/**
	 * Variable relationShip declared as String.
	 */
	protected String relationShip;
	/**
	 * Variable userId declared as Integer.
	 */
	protected Integer userId;
	/**
	 * Variable countryID declared as Integer.
	 */
	protected Integer countryID;
	/**
	 * Variable incomeRangeID declared as Integer.
	 */
	protected Integer incomeRangeID;
	/**
	 * Variable dob declared as String.
	 */
	protected String dob;
	/**
	 * property for educationLevelId.
	 */
	protected Integer educationLevelId;

	/**
	 * property for maritalStatus.
	 */

	protected String maritalStatus;

	/**
	 * property for children.
	 */

	protected Integer children;
	/**
	 * property for homeOwner.
	 */

	protected Integer homeOwner;

	private String userName;

	/**
	 * for app version
	 */
	private String appVersion;

	/**
	 * For subscription from ScanSee
	 */
	private Integer subscribe;

	/**
	 * For email body
	 */
	private String message;

	/**
	 * For email subject
	 */
	private String subject;
	/**
	 * For platform
	 */
	private String platform;

	private String hcKey;

	/**
	 *hubCitiId declred as Integer
	 */
	private Integer hubCitiId;

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * HubCitiKey Declared as hubCitikey
	 */
	private String hubCitiKey;

	public String getHubCitiKey() {
		return hubCitiKey;
	}

	public void setHubCitiKey(String hubCitiKey) {
		this.hubCitiKey = hubCitiKey;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Variable login declared as String.
	 */
	protected String login;

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the value of the dob property.
	 * 
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets the value of the dob property.
	 * 
	 * @param dob
	 *            as of type String.
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * Gets the value of the incomeRangeID property.
	 * 
	 * @return the incomeRangeID
	 */
	public Integer getIncomeRangeID() {
		return incomeRangeID;
	}

	/**
	 * Sets the value of the incomeRangeID property.
	 * 
	 * @param incomeRangeID
	 *            as of type Integer.
	 */
	public void setIncomeRangeID(Integer incomeRangeID) {
		this.incomeRangeID = incomeRangeID;
	}

	/**
	 * Gets the value of the countryID property.
	 * 
	 * @return the countryID
	 */
	public Integer getCountryID() {
		return countryID;
	}

	/**
	 * Sets the value of the countryID property.
	 * 
	 * @param countryID
	 *            as of type Integer.
	 */
	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}

	/**
	 * Gets the value of the dateCreated property.
	 * 
	 * @return the dateCreated
	 */
	public String getDateCreated() {
		return dateCreated;
	}

	/**
	 * Sets the value of the dateCreated property.
	 * 
	 * @param dateCreated
	 *            as of type String.
	 */
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	/**
	 * Gets the value of the deviceID property.
	 * 
	 * @return the deviceID
	 */
	public String getDeviceID() {
		return deviceID;
	}

	/**
	 * Sets the value of the deviceID property.
	 * 
	 * @param deviceID
	 *            as of type String.
	 */
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}

	/**
	 * Gets the value of the userLongitude property.
	 * 
	 * @return the userLongitude
	 */
	public Double getUserLongitude() {
		return userLongitude;
	}

	/**
	 * Sets the value of the userLongitude property.
	 * 
	 * @param userLongitude
	 *            as of type Double.
	 */
	public void setUserLongitude(Double userLongitude) {
		this.userLongitude = userLongitude;
	}

	/**
	 * Gets the value of the isFaveCategory property.
	 * 
	 * @return the isFaveCategory
	 */
	public Double getUserLatitude() {
		return userLatitude;
	}

	/**
	 * Sets the value of the userLatitude property.
	 * 
	 * @param userLatitude
	 *            as of type Double.
	 */
	public void setUserLatitude(Double userLatitude) {
		this.userLatitude = userLatitude;
	}

	/**
	 * Gets the value of the userId property.
	 * 
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the value of the userId property.
	 * 
	 * @param userId
	 *            as of type Integer.
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * Gets the value of the gender property.
	 * 
	 * @return possible object is {@link String }
	 */
	public Integer getGender() {
		return gender;
	}

	/**
	 * Sets the value of the gender property.
	 * 
	 * @param gender
	 *            allowed object is {@link Integer }
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}

	/**
	 * Gets the value of the relationShip property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getRelationShip() {
		return relationShip;
	}

	/**
	 * Sets the value of the relationship property.
	 * 
	 * @param relationShip
	 *            allowed object is {@link String }
	 */
	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}

	/**
	 * Gets the value of the password property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the password property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setPassword(String value) {
		this.password = value;
	}

	/**
	 * Gets the value of the firstName property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the value of the firstName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setFirstName(String value) {
		this.firstName = value;
	}

	/**
	 * Gets the value of the lastName property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the value of the lastName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setLastName(String value) {
		this.lastName = value;
	}

	/**
	 * Gets the value of the address1 property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * Sets the value of the address1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setAddress1(String value) {
		this.address1 = value;
	}

	/**
	 * Gets the value of the address2 property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * Sets the value of the address2 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setAddress2(String value) {
		this.address2 = value;
	}

	/**
	 * Gets the value of the address3 property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * Sets the value of the address3 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setAddress3(String value) {
		this.address3 = value;
	}

	/**
	 * Gets the value of the address4 property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getAddress4() {
		return address4;
	}

	/**
	 * Sets the value of the address4 property.
	 * 
	 * @param address4
	 *            allowed object is {@link String }
	 */
	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	/**
	 * Gets the value of the state property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the value of the state property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setState(String value) {
		this.state = value;
	}

	/**
	 * Gets the value of the city property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the value of the city property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setCity(String value) {
		this.city = value;
	}

	/**
	 * Gets the value of the mobileNumber property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * Sets the value of the mobileNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setMobileNumber(String value) {
		this.mobileNumber = value;
	}

	/**
	 * Gets the value of the email property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the value of the email property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setEmail(String value) {
		this.email = value;
	}

	/**
	 * Gets the value of the fieldAgent property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getFieldAgent() {
		return fieldAgent;
	}

	/**
	 * Sets the value of the fieldAgent property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 */
	public void setFieldAgent(Integer value) {
		this.fieldAgent = value;
	}

	/**
	 * Gets the value of the pushNotify property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getPushNotify() {
		return pushNotify;
	}

	/**
	 * Sets the value of the PushNotify property.
	 * 
	 * @param pushNotify
	 *            allowed object is {@link Integer }
	 */
	public void setPushNotify(Integer pushNotify) {
		this.pushNotify = pushNotify;
	}

	/**
	 * Gets the value of the FirstUseComplete property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getFirstUseComplete() {
		return firstUseComplete;
	}

	/**
	 * Sets the value of the FirstUseComplete property.
	 * 
	 * @param firstUseComplete
	 *            allowed object is {@link Integer }
	 */
	public void setFirstUseComplete(Integer firstUseComplete) {
		this.firstUseComplete = firstUseComplete;
	}

	/**
	 * Gets the value of the educationLevelId property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getEducationLevelId() {
		return educationLevelId;
	}

	/**
	 * Sets the value of the educationLevelId property.
	 * 
	 * @param educationLevelId
	 *            allowed object is {@link Integer }
	 */
	public void setEducationLevelId(Integer educationLevelId) {
		this.educationLevelId = educationLevelId;
	}

	/**
	 * Gets the value of the children property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getChildren() {
		return children;
	}

	/**
	 * Gets the value of the homeOwner property.
	 * 
	 * @return possible object is {@link Integer }
	 */
	public Integer getHomeOwner() {
		return homeOwner;
	}

	/**
	 * Sets the value of the children property.
	 * 
	 * @param children
	 *            allowed object is {@link Integer }
	 */
	public void setChildren(Integer children) {
		this.children = children;
	}

	/**
	 * Sets the value of the homeOwner property.
	 * 
	 * @param homeOwner
	 *            allowed object is {@link Integer }
	 */
	public void setHomeOwner(Integer homeOwner) {
		this.homeOwner = homeOwner;
	}

	/**
	 * Gets the value of postalCode property.
	 * 
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the value of postalCode property.
	 * 
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * @param userTokenID
	 *            the userTokenID to set
	 */
	public String getUserTokenID() {
		return userTokenID;
	}

	/**
	 * @param userTokenID
	 *            the userTokenID to set
	 */
	public void setUserTokenID(String userTokenID) {
		this.userTokenID = userTokenID;
	}

	public Integer getFromFacebook() {
		return fromFacebook;
	}

	public void setFromFacebook(Integer fromFacebook) {
		this.fromFacebook = fromFacebook;
	}

	public void setUniversityIDs(String universityIDs) {
		this.universityIDs = universityIDs;
	}

	public String getUniversityIDs() {
		return universityIDs;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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

	public String getHcKey() {
		return hcKey;
	}

	public void setHcKey(String hcKey) {
		this.hcKey = hcKey;
	}

}
