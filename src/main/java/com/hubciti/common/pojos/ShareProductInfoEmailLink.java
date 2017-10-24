package com.hubciti.common.pojos;

import com.hubciti.common.constants.*;

/**
 * The ShareProductInfoEmailLink contains setter and getter methods for
 * ShareProductInfoEmailLink fields.
 * 
 * @author Kumar D
 */
public class ShareProductInfoEmailLink {
	/**
	 * for productName.
	 */
	private String productName;
	/**
	 * for emailTemplateURL.
	 */
	private String emailTemplateURL;
	/**
	 * for imagePath.
	 */
	private String imagePath;
	
	/**
	 * for productLongDescription.
	 */
	private String lDescription;
	
	
	/**
	 * for qrUrl.
	 */
	private String qrUrl;

	/**
	 * To get emailTemplateURL.
	 * 
	 * @return the emailTemplateURL
	 */
	public String getEmailTemplateURL() {
		return emailTemplateURL;
	}

	/**
	 * To set emailTemplateURL.
	 * 
	 * @param emailTemplateURL
	 *            the emailTemplateURL to set
	 */
	public void setEmailTemplateURL(String emailTemplateURL) {
		this.emailTemplateURL = emailTemplateURL;
	}

	/**
	 * To get imagePath.
	 * 
	 * @return the imagePath
	 */
	public String getImagePath() {
		return imagePath;
	}

	/**
	 * To set imagePath.
	 * 
	 * @param imagePath
	 *            the imagePath to set
	 */
	public void setImagePath(String imagePath) {
		if (null == imagePath) {
			this.imagePath = HubCitiConstants.IMAGENOTFOUND;
		} else {
			this.imagePath = imagePath;
		}
	}

	/**
	 * To get productName.
	 * 
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * To set productName.
	 * 
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the lDescription
	 */
	public String getlDescription() {
		return lDescription;
	}

	/**
	 * @param lDescription the lDescription to set
	 */
	public void setlDescription(String lDescription) {
		this.lDescription = lDescription;
	}

	/**
	 * @return the qrUrl
	 */
	public String getQrUrl() {
		return qrUrl;
	}

	/**
	 * @param qrUrl the qrUrl to set
	 */
	public void setQrUrl(String qrUrl) {
		this.qrUrl = qrUrl;
	}
}
