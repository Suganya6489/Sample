package com.hubciti.common.pojos;

import java.util.ArrayList;

import com.hubciti.common.constants.HubCitiConstants;


/**
 * POJO class for TutorialMedia.
 * 
 * @author Kumar D
 */

public class TutorialMedia {

	/**
	 * sectionContent declared as ArrayList.
	 */
	private ArrayList<SectionContent> sectionContent;

	/**
	 * The property for Tutorial ID.
	 */

	private int tutorialID;

	/**
	 * The property for Tutorial Name.
	 */

	private String tutorialName;

	/**
	 * The property for tutorialMediaPath.
	 */

	private String tutorialMediaPath;
	/**
	 * for TutorialImagePath.
	 */
	private String tutorialImagePath;
	/**
	 * The property for Tutorial ID.
	 */

	/* private int tutorialSort; */
	/**
	 * for TutorialImagePath.
	 */
	private String tutVidDuration;

	/**
	 * To get tutorialImagePath.
	 * 
	 * @return the tutorialImagePath
	 */
	public String getTutorialImagePath() {
		return tutorialImagePath;
	}

	/**
	 * To set tutorialImagePath.
	 * 
	 * @param tutorialImagePath
	 *            the tutorialImagePath to set
	 */
	public void setTutorialImagePath(String tutorialImagePath) {
		if (null == tutorialImagePath) {
			this.tutorialImagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.tutorialImagePath = tutorialImagePath;
		}
	}

	/**
	 * To get tutorialID.
	 * 
	 * @return The Tutorial ID property
	 */
	public int getTutorialID() {
		return tutorialID;
	}

	/**
	 * To set tutorialID.
	 * 
	 * @param tutorialID
	 *            The TutorialID property
	 */

	public void setTutorialID(int tutorialID) {
		this.tutorialID = tutorialID;
	}

	/**
	 * To get tutorialName.
	 * 
	 * @return tutorialName The TutorialName property
	 */

	public String getTutorialName() {
		return tutorialName;
	}

	/**
	 * To set tutorialName.
	 * 
	 * @param tutorialName
	 *            The TutorialName property
	 */
	public void setTutorialName(String tutorialName) {
		if (null == tutorialName) {
			this.tutorialName = "N/A";
		} else {
			this.tutorialName = tutorialName;
		}

	}

	/**
	 * To get tutorialMediaPath.
	 * 
	 * @return The TutorialMediaPath() property.
	 */

	public String getTutorialMediaPath() {
		return tutorialMediaPath;
	}

	/**
	 * To set tutorialMediaPath.
	 * 
	 * @param tutorialMediaPath
	 *            The tutorialMediaPath property.
	 */

	public void setTutorialMediaPath(String tutorialMediaPath) {
		if (null == tutorialMediaPath) {
			this.tutorialMediaPath = "N/A";
		} else {
			this.tutorialMediaPath = tutorialMediaPath;
		}

	}

	/**
	 * To get sectionContent.
	 * 
	 * @return the sectionContent
	 */
	public ArrayList<SectionContent> getSectionContent() {
		return sectionContent;
	}

	/**
	 * To set sectionContent.
	 * 
	 * @param sectionContent
	 *            the sectionContent to set
	 */
	public void setSectionContent(ArrayList<SectionContent> sectionContent) {
		this.sectionContent = sectionContent;
	}

	/**
	 * @return the tutVidDuration
	 */
	public String getTutVidDuration() {
		return tutVidDuration;
	}

	/**
	 * @param tutVidDuration
	 *            the tutVidDuration to set
	 */
	public void setTutVidDuration(String tutVidDuration) {
		if (null == tutVidDuration) {
			this.tutVidDuration = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.tutVidDuration = tutVidDuration;
		}

	}

	/*	*//**
	 * @return the tutorialSort
	 */
	/*
	 * public int getTutorialSort() { return tutorialSort; }
	 *//**
	 * @param tutorialSort
	 *            the tutorialSort to set
	 */
	/*
	 * public void setTutorialSort(int tutorialSort) { this.tutorialSort =
	 * tutorialSort; }
	 */

}
