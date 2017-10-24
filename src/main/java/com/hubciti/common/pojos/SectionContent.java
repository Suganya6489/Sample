package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * This pojo class contains setter and getter methods for section content
 * fields.
 * 
 * @author Kumar D
 */
public class SectionContent {

	/**
	 * sectionContent variable declared as String.
	 */
	private String sectionContent;

	/**
	 * sectionNumber variable declared as String.
	 */
	private Integer sectionNumber;

	/**
	 * configurationType variable declared as String.
	 */
	private String configurationType;

	/**
	 * screenContent variable declared as String.
	 */
	private String screenName;

	/**
	 * welcomeImage variable declared as String.
	 */
	private String screenContent;

	/**
	 * welcomeImage variable declared as String.
	 */
	private String welcomeImage;

	/**
	 * WelcomeVideo variable declared as String.
	 */
	private String WelcomeVideo;

	/**
	 * For getting sectionContent.
	 * 
	 * @return the sectionContent
	 */
	public String getSectionContent() {
		return sectionContent;
	}

	/**
	 * For setting sectionContent.
	 * 
	 * @param sectionContent
	 *            the sectionContent to set
	 */
	public void setSectionContent(String sectionContent) {

		this.sectionContent = sectionContent;

	}

	/**
	 * For getting sectionNumber.
	 * 
	 * @return the sectionNumber
	 */
	public Integer getSectionNumber() {
		return sectionNumber;
	}

	/**
	 * For setting sectionNumber.
	 * 
	 * @param sectionNumber
	 *            the sectionNumber to set
	 */
	public void setSectionNumber(Integer sectionNumber) {

		this.sectionNumber = sectionNumber;
	}

	/**
	 * For getting configurationType.
	 * 
	 * @return the configurationType
	 */
	public String getConfigurationType() {
		return configurationType;
	}

	/**
	 * For setting configurationType.
	 * 
	 * @param configurationType
	 *            the configurationType to set
	 */
	public void setConfigurationType(String configurationType) {

		if (null == configurationType) {
			this.configurationType = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.configurationType = configurationType;
		}
	}

	/**
	 * For getting screenName.
	 * 
	 * @return the screenName
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * For setting screenName.
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

	/**
	 * For getting screenContent.
	 * 
	 * @return the screenContent
	 */
	public String getScreenContent() {
		return screenContent;
	}

	/**
	 * For setting screenContent.
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
	 * For getting welcomeImage.
	 * 
	 * @return the welcomeImage
	 */
	public String getWelcomeImage() {
		return welcomeImage;
	}

	/**
	 * For setting welcomeImage.
	 * 
	 * @param welcomeImage
	 *            the welcomeImage to set
	 */
	public void setWelcomeImage(String welcomeImage) {
		this.welcomeImage = welcomeImage;
	}

	/**
	 * For getting WelcomeVideo.
	 * 
	 * @return the welcomeVideo
	 */
	public String getWelcomeVideo() {
		return WelcomeVideo;
	}

	/**
	 * HARDCODED Video path. For setting welcomeVideo.
	 * 
	 * @param welcomeVideo
	 *            the welcomeVideo to set
	 */
	public void setWelcomeVideo(String welcomeVideo) {
		// WelcomeVideo =
		// "http://media.scansee.net/videos/scanSee_Consumer.mp4";
		this.WelcomeVideo = welcomeVideo;
	}
}
