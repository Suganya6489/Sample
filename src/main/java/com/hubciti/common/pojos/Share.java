package com.hubciti.common.pojos;

/**
 * class Constructed & used for sharing Information by Email.
 * 
 * @author kirankumar.garaddi
 *
 */
public class Share {

	/**
	 * Variable for success code
	 */
	private String responseCode;
	/**
	 * variable for response text
	 */
	private String responseText;
	/**
	 * Variable for ShareTest it contains HTML data
	 */
	private String shareText;
	/*
	 * Variable for Image Absolute path
	 */
	private String imagePath;

	/**
	 * Variable for mail body
	 */
	private String subject;

	/**
	 * Variable for ITune Image
	 */
	private String iTunesImg;

	/**
	 * Google play Image
	 */
	private String androidImg;

	/**
	 * 
	 * @return the iTune Image
	 */
	public String getiTunesImg() {
		return iTunesImg;
	}

	/**
	 * 
	 * @param iTuneImage
	 *            to set
	 */
	public void setiTunesImg(String iTunesImg) {
		this.iTunesImg = iTunesImg;
	}

	/**
	 * 
	 * @return the Google play Image
	 */

	public String getAndroidImg() {
		return androidImg;
	}

	/**
	 * 
	 * @param gPlayImage
	 *            to set
	 */
	public void setAndroidImg(String androidImg) {
		this.androidImg = androidImg;
	}

	/**
	 * 
	 * @return the response code
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * 
	 * @param the
	 *            responseCode
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * 
	 * @return the response text
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * 
	 * @param The
	 *            responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**
	 * 
	 * @return the email Subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 
	 * @param The
	 *            subject to Set
	 */
	public void setSubject(String subject) {

		this.subject = subject;

	}

	/**
	 * 
	 * @return the Image path
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * 
	 * @param The
	 *            imagePath to set
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	/**
	 * 
	 * @return the Sharetext in HTML Contains
	 */
	public String getShareText() {
		return shareText;
	}

	/**
	 * 
	 * @param The
	 *            shareText to set
	 */
	public void setShareText(String shareText) {
		this.shareText = shareText;
	}

}
