package com.hubciti.common.pojos;

import java.util.List;

/**
 * Pojo for City. 
 * @author Kumar D
 */
public class City
{
	/**
	 * variable for responseCode
	 */
	private String responseCode;
	/**
	 * variable for responseText
	 */
	private String responseText;
	/**
	 * variable for cityId
	 */
	private String cityId;
	/**
	 * variable for city name
	 */
	private String cityName;
	/**
	 * variable for city name
	 */
	private Integer isCityChecked;
	/**
	 * variable for city name
	 */
	private List<City> cityList;
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
	 * @return the cityId
	 */
	public String getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return the cityList
	 */
	public List<City> getCityList() {
		return cityList;
	}
	/**
	 * @param cityList the cityList to set
	 */
	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}
	/**
	 * @return the isCityChecked
	 */
	public Integer getIsCityChecked() {
		return isCityChecked;
	}
	/**
	 * @param isCityChecked the isCityChecked to set
	 */
	public void setIsCityChecked(Integer isCityChecked) {
		this.isCityChecked = isCityChecked;
	}

}
