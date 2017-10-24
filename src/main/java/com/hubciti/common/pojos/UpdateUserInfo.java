package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * The class has getter and setter methods for UpdateUserInfo.
 * 
 * @author dhruvanath_mm
 */

public class UpdateUserInfo {

	/**
	 * <screenContent>Your privacy is important to us. ScanSee will never sell
	 * or give your information away. 100% Guaranteed.
	 * </screenContent><universityName> </universityName>
	 */

	/**
	 * for UniversityState
	 */
	private String univStateName;
	/**
	 * Variable userName declared as String.
	 */
	protected String userName;
	/**
	 * Variable password declared as String.
	 */
	protected String password;
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
	 * Variable postalCode declared as String.
	 */
	protected String postalCode;
	/**
	 * Variable countryID declared as String.
	 */
	protected String countryID;
	/**
	 * Variable mobileNumber declared as String.
	 */
	protected String mobileNumber;
	/**
	 * Variable email declared as String.
	 */
	protected String email;
	/**
	 * Variable fieldAgent declared as String.
	 */
	protected String fieldAgent;
	/**
	 * Variable gender declared as String.
	 */
	protected String gender;
	/**
	 * Variable dob declared as String.
	 */
	protected String dob;
	/**
	 * Variable maritalStatus declared as String.
	 */
	protected String maritalStatus;
	/**
	 * Variable incomeRangeID declared as String.
	 */
	protected String incomeRangeID;
	/**
	 * Variable selectedCountryId declared as String.
	 */
	protected String selectedCountryId;
	/**
	 * Variable selectedIncomeRangeID declared as String.
	 */
	protected String selectedIncomeRangeID;
	/**
	 * property for homeOwner.
	 */
	protected String homeOwner;

	/**
	 * property for numberOfChildren.
	 */
	protected String numberOfChildren;

	/**
	 * property for educationLevelId.
	 */
	protected String educationLevelId;

	/**
	 * property for addressCaption.
	 */
	protected String addressCaption;

	private String isCFShareInfoEnabled;
	/**
	 * Variable privacy declared as String.
	 */
	private String screenContent;

	private String universityName;

	private String yob;

	/**
	 * for getting screenContent.
	 * 
	 * @return the screenContent
	 */
	public String getScreenContent() {
		return screenContent;
	}

	/**
	 * for setting screenContent.
	 * 
	 * @param screenContent
	 *            the screenContent to set
	 */
	public void setScreenContent(String screenContent) {
		if (screenContent == null || screenContent.equals("")) {
			this.screenContent = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.screenContent = screenContent;
		}
	}

	/**
	 * Gets the value of the selectedCountryId property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getSelectedCountryId() {

		return selectedCountryId;
	}

	/**
	 * Sets the value of the selectedCountryId property.
	 * 
	 * @param selectedCountryId
	 *            as of type String.
	 */

	public void setSelectedCountryId(String selectedCountryId) {

		if (null == selectedCountryId || selectedCountryId.equals("")) {
			this.selectedCountryId = HubCitiConstants.EMPTYSTRING;
		} else {
			this.selectedCountryId = selectedCountryId;
		}
	}

	/**
	 * Gets the value of the SelectedIncomeRangeID property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getSelectedIncomeRangeID() {
		return selectedIncomeRangeID;
	}

	/**
	 * Sets the value of the selectedIncomeRangeID property.
	 * 
	 * @param selectedIncomeRangeID
	 *            possible object is {@link String }
	 */
	public void setSelectedIncomeRangeID(String selectedIncomeRangeID) {

		if (null == selectedIncomeRangeID || selectedIncomeRangeID.equals("")) {
			this.selectedIncomeRangeID = HubCitiConstants.EMPTYSTRING;

		} else {

			this.selectedIncomeRangeID = selectedIncomeRangeID;

		}

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
	 *            possible object is {@link String }
	 */
	public void setAddress4(String address4) {

		if (null == address4 || address4.equals("")) {
			this.address4 = HubCitiConstants.EMPTYSTRING;
		} else {
			this.address4 = address4;
		}
	}

	/**
	 * Gets the value of the countryID property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCountryID() {
		return countryID;
	}

	/**
	 * Sets the value of the countryID property.
	 * 
	 * @param countryID
	 *            possible object is {@link String }
	 */
	public void setCountryID(String countryID) {

		if (null == countryID) {
			this.countryID = HubCitiConstants.EMPTYSTRING;

		} else {

			this.countryID = countryID;
		}

	}

	/**
	 * Gets the value of the gender property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the value of the gender property.
	 * 
	 * @param gender
	 *            possible object is {@link String }
	 */
	public void setGender(String gender) {

		if (null == gender || gender.equals("")) {
			this.gender = "0";

		} else {

			this.gender = gender;
		}

	}

	/**
	 * Gets the value of the dob property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets the value of the dob property.
	 * 
	 * @param dob
	 *            possible object is {@link String }
	 */
	public void setDob(String dob) {
		if (dob != null && !dob.equals("")) {
			this.dob = Utility.convertDBdate(dob);
		}
		// else {
		// this.dob=HubCitiConstants.NOTAPPLICABLE;
		// }
	}

	/**
	 * Gets the value of the maritalStatus property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * Sets the value of the maritalStatus property.
	 * 
	 * @param maritalStatus
	 *            possible object is {@link String }
	 */
	public void setMaritalStatus(String maritalStatus) {

		if (null == maritalStatus || maritalStatus.equals("")) {
			this.maritalStatus = HubCitiConstants.EMPTYSTRING;

		} else {

			this.maritalStatus = maritalStatus;
		}

	}

	/**
	 * Gets the value of the incomeRangeID property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getIncomeRangeID() {
		return incomeRangeID;
	}

	/**
	 * Sets the value of the incomeRangeID property.
	 * 
	 * @param incomeRangeID
	 *            possible object is {@link String }
	 */
	public void setIncomeRangeID(String incomeRangeID) {

		if (null == incomeRangeID || incomeRangeID.equals("")) {
			this.incomeRangeID = HubCitiConstants.EMPTYSTRING;

		}

		else {
			this.incomeRangeID = incomeRangeID;
		}

	}

	/**
	 * Gets the value of the userName property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the value of the userName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setUserName(String value) {

		if (null == value) {
			this.userName = HubCitiConstants.EMPTYSTRING;

		}

		else {
			this.userName = value;
		}

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

		if (null == value) {
			this.password = HubCitiConstants.EMPTYSTRING;

		}

		else {
			this.password = value;
		}

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

		if (null == value || value.equals("")) {
			this.firstName = HubCitiConstants.EMPTYSTRING;

		}

		else {
			this.firstName = value;
		}

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

		if (null == value || value.equals("")) {
			this.lastName = HubCitiConstants.EMPTYSTRING;

		}

		else {
			this.lastName = value;
		}

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

		if (null == value || value.equals("")) {
			this.address1 = HubCitiConstants.EMPTYSTRING;

		} else {
			this.address1 = value;
		}

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
		if (null == value || value.equals("")) {
			this.address2 = HubCitiConstants.EMPTYSTRING;
		} else {
			this.address2 = value;
		}
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
		if (null == value || value.equals("")) {
			this.address3 = HubCitiConstants.EMPTYSTRING;
		} else {
			this.address3 = value;
		}
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

		if (null == value || value.equals("")) {
			this.state = HubCitiConstants.EMPTYSTRING;
		} else {
			this.state = value;
		}

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

		if (null == value || value.equals("")) {

			this.city = HubCitiConstants.EMPTYSTRING;
		} else {

			this.city = value;
		}

	}

	/**
	 * Gets the value of the postalCode property.
	 * 
	 * @return postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the value of the postalCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setPostalCode(String value) {
		if (null == value || value.equals("")) {

			this.postalCode = HubCitiConstants.EMPTYSTRING;
		} else {

			this.postalCode = value;
		}

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
		if (null == value || value.equals("")) {

			this.mobileNumber = HubCitiConstants.EMPTYSTRING;
		} else {

			this.mobileNumber = value;
		}

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

		if (null == value || value.equals("")) {

			this.email = HubCitiConstants.EMPTYSTRING;
		} else {

			this.email = value;
		}

	}

	/**
	 * Gets the value of the fieldAgent property.
	 * 
	 * @return fieldAgent
	 */
	public String getFieldAgent() {
		return fieldAgent;
	}

	/**
	 * Sets the value of the fieldAgent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 */
	public void setFieldAgent(String value) {
		if (null == value || value.equals("")) {

			this.fieldAgent = HubCitiConstants.EMPTYSTRING;
		} else {

			this.fieldAgent = value;
		}

	}

	/**
	 * Gets the value of the homeOwner property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getHomeOwner() {
		return homeOwner;
	}

	/**
	 * Sets the value of the homeOwner property.
	 * 
	 * @param homeOwner
	 *            allowed object is {@link String }
	 */
	public void setHomeOwner(String homeOwner) {

		if (null == homeOwner || homeOwner.equals("")) {

			this.homeOwner = "0";
		} else {

			this.homeOwner = homeOwner;
		}

	}

	/**
	 * Gets the value of the numberOfChildren property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getNumberOfChildren() {
		return numberOfChildren;
	}

	/**
	 * Sets the value of the numberOfChildren property.
	 * 
	 * @param numberOfChildren
	 *            allowed object is {@link String }
	 */
	public void setNumberOfChildren(String numberOfChildren) {

		if (null == numberOfChildren || numberOfChildren.equals("")) {

			this.numberOfChildren = HubCitiConstants.EMPTYSTRING;
		} else {

			this.numberOfChildren = numberOfChildren;
		}

	}

	/**
	 * Gets the value of educationLevelId Property.
	 * 
	 * @return the educationLevelId
	 */
	public String getEducationLevelId() {
		return educationLevelId;
	}

	/**
	 * Sets the value of educationLevelId.
	 * 
	 * @param educationLevelId
	 *            the educationLevelId to set.
	 */
	public void setEducationLevelId(String educationLevelId) {

		if (null == educationLevelId || educationLevelId.equals("")) {

			this.educationLevelId = HubCitiConstants.EMPTYSTRING;
		} else {

			this.educationLevelId = educationLevelId;
		}

	}

	/**
	 * Gets the value of addressCaption.
	 * 
	 * @return the addressCaption.
	 */
	public String getAddressCaption() {
		return addressCaption;
	}

	/**
	 * Sets the Value of addressCaption.
	 * 
	 * @param addressCaption
	 *            the educationLevelId to set.
	 */
	public void setAddressCaption(String addressCaption) {

		if (null == addressCaption || addressCaption.equals("")) {

			this.addressCaption = HubCitiConstants.EMPTYSTRING;
		} else {

			this.addressCaption = addressCaption;
		}

	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		if (universityName == null || universityName.equals("")) {
			this.universityName = HubCitiConstants.EMPTYSTRING;
		} else {
			this.universityName = universityName;
		}
	}

	public String getUnivStateName() {
		return univStateName;
	}

	public void setUnivStateName(String univStateName) {
		if (univStateName == null || univStateName.equals("")) {
			this.univStateName = HubCitiConstants.EMPTYSTRING;
		} else {
			this.univStateName = univStateName;
		}
	}

	public String getYob() {
		return yob;
	}

	public void setYob(String value) {
		if (null == value || value.equals("")) {

			this.yob = HubCitiConstants.EMPTYSTRING;
		} else {

			this.yob = value;
		}
	}

	public String getIsCFShareInfoEnabled() {
		return isCFShareInfoEnabled;
	}

	public void setIsCFShareInfoEnabled(String isCFShareInfoEnabled) {
		this.isCFShareInfoEnabled = isCFShareInfoEnabled;
	}

}
