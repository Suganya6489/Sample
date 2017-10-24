package com.hubciti.common.pojos;

/**
 * The POJO class for TutorialMediaResultSet
 * 
 * @author Kumar D
 */
import java.util.ArrayList;
import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * The class has getter and setter methods for TutorialMediaResultSet.
 * 
 * @author shyamsundara_hm
 */
public class TutorialMediaResultSet {

	private String responseCode;

	private String responseText;
	/**
	 * Variable sectionContent declared as ArrayList.
	 */

	private ArrayList<SectionContent> sectionContent;
	/**
	 * property for List storing type tutorialMedia.
	 */

	private String screenImgPath;

	private List<TutorialMedia> tutorialMedia;
	
	

	private String welcomeVideo;
	
	private String logoPath;
	
	
	private String link;
	
	private Integer Type;
	
	private String isAnythingPageOrWebLink;
	
	private String isAnythingPageOrWebLinkValue;
	
	private List<TutorialMediaResultSet> slideImages;
	
	/**
	 * This for getting TutorialMedialist.
	 * 
	 * @return List of type tutorialMedia
	 */

	public List<TutorialMedia> getTutorialMedia() {
		return tutorialMedia;
	}

	/**
	 * This is for setting tutorialMedia List.
	 * 
	 * @param tutorialMedia
	 *            the tutorialMedia to set
	 */

	public void setTutorialMedia(List<TutorialMedia> tutorialMedia) {
		this.tutorialMedia = tutorialMedia;
	}

	/**
	 * This for getting SectionContent.
	 * 
	 * @return ArrayList of type sectionContent
	 */
	public ArrayList<SectionContent> getSectionContent() {
		return sectionContent;
	}

	/**
	 * This is for setting sectionContent.
	 * 
	 * @param sectionContent
	 *            the sectionContent to set
	 */
	public void setSectionContent(ArrayList<SectionContent> sectionContent) {
		this.sectionContent = sectionContent;
	}

	/**
	 * @return the screenImgPath
	 */
	public String getScreenImgPath() {
		return screenImgPath;
	}

	/**
	 * @param screenImgPath
	 *            the screenImgPath to set
	 */
	public void setScreenImgPath(String screenImgPath) {
		if (null == screenImgPath) {
			this.screenImgPath = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.screenImgPath = screenImgPath;
		}
	}

	public String getWelcomeVideo() {
		return welcomeVideo;
	}

	public void setWelcomeVideo(String welcomeVideo) {
		this.welcomeVideo = welcomeVideo;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	

	public List<TutorialMediaResultSet> getTutorialMediaResultSets() {
		return slideImages;
	}

	public void setTutorialMediaResultSets(List<TutorialMediaResultSet> slideImages) {
		this.slideImages = slideImages;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getIsAnythingPageOrWebLink() {
		return isAnythingPageOrWebLink;
	}

	public void setIsAnythingPageOrWebLink(String isAnythingPageOrWebLink) {
		this.isAnythingPageOrWebLink = isAnythingPageOrWebLink;
	}

	public String getIsAnythingPageOrWebLinkValue() {
		return isAnythingPageOrWebLinkValue;
	}

	public void setIsAnythingPageOrWebLinkValue(String isAnythingPageOrWebLinkValue) {
		this.isAnythingPageOrWebLinkValue = isAnythingPageOrWebLinkValue;
	}

}
