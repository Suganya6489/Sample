package com.hubciti.common.pojos;

import java.util.List;

/**
 * The class has getter and setter methods for CountryCodes.
 * 
 * @author sowjanya_d
 */
public class CountryCodes {

	private String state;
	/**
	 * Variable countryCode declared as of type List.
	 */
	private List<CountryCode> countryCode;

	/**
	 * Gets the value of the countryCode property.
	 * 
	 * @return possible object is {@link List }
	 */
	public List<CountryCode> getCountryCode() {
		return countryCode;
	}

	/**
	 * Sets the value of the countryCode property.
	 * 
	 * @param countryCode
	 *            as of type List.
	 */
	public void setCountryCode(List<CountryCode> countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

}
