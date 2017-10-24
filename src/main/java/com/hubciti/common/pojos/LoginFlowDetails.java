package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * POJO class to get details in login flow.
 * 
 * @author dhruvanath_mm
 */
public class LoginFlowDetails {

	private String responseCode;

	private String responseText;

	private Integer userId;

	private Integer hubCitiId;

	private String pageType;

	private String bkgndColor;

	private String fontColor;

	private String title;

	private String description;

	private String btnColor;

	private String btnFontColor;

	private String logoImg;

	private String smallLogo;

	private String hubCitiImg;

	private String versionNum;

	private String splashImg;
	private Integer maxCnt;

	private List<LoginFlowDetails> listConfigParam;

	private String hubCitiKey;

	private String pageLink;

	private String poweredBy;
	
	private String bkImgPath;
	private String homeImgPath;
	private String titleTxtColor;
	private String titleBkGrdColor;
	private String templateName;
	private String hamburgerImg;
	
	private String domainName;

	public String getBkImgPath() {
		return bkImgPath;
	}

	public void setBkImgPath(String bkImgPath) {
		this.bkImgPath = bkImgPath;
	}

	public String getHomeImgPath() {
		return homeImgPath;
	}

	public void setHomeImgPath(String homeImgPath) {
		this.homeImgPath = homeImgPath;
	}

	public String getTitleTxtColor() {
		return titleTxtColor;
	}

	public void setTitleTxtColor(String titleTxtColor) {
		this.titleTxtColor = titleTxtColor;
	}

	public String getTitleBkGrdColor() {
		return titleBkGrdColor;
	}

	public void setTitleBkGrdColor(String titleBkGrdColor) {
		this.titleBkGrdColor = titleBkGrdColor;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getHubCitiId() {
		return hubCitiId;
	}

	public void setHubCitiId(Integer hubCitiId) {
		this.hubCitiId = hubCitiId;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getBkgndColor() {
		return bkgndColor;
	}

	public void setBkgndColor(String bkgndColor) {
		if (bkgndColor == null || bkgndColor.equals("")) {
			this.bkgndColor = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.bkgndColor = bkgndColor;
		}
	}

	public String getFontColor() {
		return fontColor;
	}

	public void setFontColor(String fontColor) {
		if (fontColor == null || fontColor.equals("")) {
			this.fontColor = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.fontColor = fontColor;
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title == null || title.equals("")) {
			this.title = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.title = title;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (description == null || description.equals("")) {
			this.description = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.description = description;
		}
	}

	public String getBtnColor() {
		return btnColor;
	}

	public void setBtnColor(String btnColor) {
		if (btnColor == null || btnColor.equals("")) {
			this.btnColor = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.btnColor = btnColor;
		}
	}

	public String getBtnFontColor() {
		return btnFontColor;
	}

	public void setBtnFontColor(String btnFontColor) {
		if (btnFontColor == null || btnFontColor.equals("")) {
			this.btnFontColor = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.btnFontColor = btnFontColor;
		}
	}

	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		if (logoImg == null || logoImg.equals("")) {
			this.logoImg = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.logoImg = logoImg;
		}
	}

	public String getSmallLogo() {
		return smallLogo;
	}

	public void setSmallLogo(String smallLogo) {
		if (smallLogo == null || smallLogo.equals("")) {
			this.smallLogo = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.smallLogo = smallLogo;
		}
	}

	public String getHubCitiImg() {
		return hubCitiImg;
	}

	public void setHubCitiImg(String hubCitiImg) {
		if (hubCitiImg == null || hubCitiImg.equals("")) {
			this.hubCitiImg = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.hubCitiImg = hubCitiImg;
		}
	}

	public List<LoginFlowDetails> getListConfigParam() {
		return listConfigParam;
	}

	public void setListConfigParam(List<LoginFlowDetails> listConfigParam) {
		this.listConfigParam = listConfigParam;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		if (versionNum == null || versionNum.equals("")) {
			this.versionNum = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.versionNum = versionNum;
		}
	}

	public String getSplashImg() {
		return splashImg;
	}

	public void setSplashImg(String splashImg) {

		if (splashImg == null || splashImg.equals("")) {
			this.splashImg = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.splashImg = splashImg;
		}
	}

	public String getHubCitiKey() {
		return hubCitiKey;
	}

	public void setHubCitiKey(String hubCitiKey) {
		this.hubCitiKey = hubCitiKey;
	}

	public String getPageLink() {
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		this.pageLink = pageLink;
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

	public String getPoweredBy() {
		return poweredBy;
	}

	public void setPoweredBy(String poweredBy) {
		this.poweredBy = poweredBy;
	}

	
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the hamburgerImg
	 */
	public String getHamburgerImg() {
		return hamburgerImg;
	}

	/**
	 * @param hamburgerImg the hamburgerImg to set
	 */
	public void setHamburgerImg(String hamburgerImg) {
		this.hamburgerImg = hamburgerImg;
	}

	/**
	 * @return the maxCnt
	 */
	public Integer getMaxCnt() {
		return maxCnt;
	}

	/**
	 * @param maxCnt the maxCnt to set
	 */
	public void setMaxCnt(Integer maxCnt) {
		this.maxCnt = maxCnt;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	

}
