package com.hubciti.common.pojos;

/**
 * The class has getter and setter methods for CountryCode.
 * 
 * @author sowjanya_d
 */
public class CountryCode {

	/**
	 * for college
	 */
	private String college;
	/**
	 * for UniversityID.
	 */
	private Integer universityID;
	/**
	 * for state Stateabbrevation.
	 */
	private String stateabbrevation;
	/**
	 * Variable countryID declared as of type Integer.
	 */
	protected Integer countryID;
	/**
	 * Variable countryName declared as of type String.
	 */
	protected String countryName;

	/**
	 * Variable stateName declared as of type String.
	 */
	protected String stateName;

	/**
	 * Variable cityName declared as of type String.
	 */
	protected String cityName;

	/**
	 * Variable cityName declared as of type String.
	 */
	protected String postalCode;

	/**
	 * Gets the value of the countryID property.
	 * 
	 * @return possible object is {@link Integer }
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
	 * Gets the value of the countryName property.
	 * 
	 * @return possible object is {@link String }
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the value of the countryName property.
	 * 
	 * @param countryName
	 *            as of type String.
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Gets the value of stateName.
	 * 
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}

	/**
	 * Sets value of stateName.
	 * 
	 * @param stateName
	 *            the stateName to set.
	 */
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	/**
	 * Gets the value of cityName.
	 * 
	 * @return the cityName.
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * Sets the value of cityName.
	 * 
	 * @param cityName
	 *            the citiName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateabbrevation() {
		return stateabbrevation;
	}

	public void setStateabbrevation(String stateabbrevation) {
		this.stateabbrevation = stateabbrevation;
	}

	public Integer getUniversityID() {
		return universityID;
	}

	public void setUniversityID(Integer universityID) {
		this.universityID = universityID;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {

		this.college = "<![CDATA[" + college + "]]>";
		// this.college =college;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
