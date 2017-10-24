package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * pojo class for HubCitConfig.
 * 
 * @author Kumar D
 */
public class AppConfiguration {

	/**
	 * screenContent variable declared as String
	 */
	private String screenContent;
	/**
	 * screenName variable declared as String
	 */
	private String screenName;
	/**
	 * configurationType declared as String.
	 */
	private String configurationType;

	/**
	 * @return the configurationType
	 */
	public String getConfigurationType() {
		return configurationType;
	}

	/**
	 * @param configurationType
	 *            the configurationType to set
	 */
	public void setConfigurationType(String configurationType) {
		this.configurationType = configurationType;
	}

	/**
	 * Retrieve the value of screenContent.
	 * 
	 * @return the screenContent
	 */
	public String getScreenContent() {
		return screenContent;
	}

	/**
	 * Set the value of screenContent.
	 * 
	 * @param screenContent
	 *            the screenContent to set
	 */
	public void setScreenContent(String screenContent) {
		if (null == screenContent) {
			this.screenContent = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.screenContent = screenContent;
		}
	}

	/**
	 * Retrieve the value of screenName.
	 * 
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Set the value of screenName.
	 * 
	 * @param screenName
	 *            the screenName to set
	 */
	public void setScreenName(String screenName) {
		if (null == screenName) {
			this.screenName = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.screenName = screenName;
		}
	}
}