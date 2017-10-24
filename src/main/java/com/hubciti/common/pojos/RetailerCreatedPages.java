package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;

/**
 * @author dhruvanath_mm
 */
public class RetailerCreatedPages {

	/**
	 * for page link
	 */
	private String pageLink;

	/**
	 * for pagetitle
	 */
	private String pageTitle;
	/**
	 * for retailer image;
	 */
	private String pageImage;

	/**
	 * for qr page Id
	 */
	private Long pageId;

	/**
	 * For retailer list Id
	 */
	private Integer retListId;

	/**
	 * For anything page list Id
	 */
	private Integer anythingPageListId;

	private String pageTempLink;
	

	private Integer eventExist;
	
	private Integer fundExist;

	public String getPageLink() {
		return pageLink;
	}

	public void setPageLink(String pageLink) {
		if (pageLink == null || pageLink.equals("")) {
			this.pageLink = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.pageLink = pageLink;
		}
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		if (pageTitle == null || pageTitle.equals("")) {
			this.pageTitle = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.pageTitle = pageTitle;
		}
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getPageImage() {
		return pageImage;
	}

	public void setPageImage(String pageImage) {
		if (pageImage == null || pageImage.equals("")) {
			this.pageImage = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.pageImage = pageImage;
		}
	}

	/**
	 * @return retListId
	 */
	public Integer getRetListId() {
		return retListId;
	}

	/**
	 * @param retListId
	 */
	public void setRetListId(Integer retListId) {
		this.retListId = retListId;
	}

	/**
	 * @return anythingPageListId
	 */
	public Integer getAnythingPageListId() {
		return anythingPageListId;
	}

	/**
	 * @param anythingPageListId
	 */
	public void setAnythingPageListId(Integer anythingPageListId) {
		this.anythingPageListId = anythingPageListId;
	}

	public String getPageTempLink() {
		return pageTempLink;
	}

	public void setPageTempLink(String pageTempLink) {
		if (pageTempLink == null || "".equals(pageTempLink)) {
			this.pageTempLink = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.pageTempLink = pageTempLink;
		}
	}

	public Integer getEventExist() {
		return eventExist;
	}

	public void setEventExist(Integer eventExist) {
		this.eventExist = eventExist;
	}

	/**
	 * @return the fundExist
	 */
	public Integer getFundExist() {
		return fundExist;
	}

	/**
	 * @param fundExist the fundExist to set
	 */
	public void setFundExist(Integer fundExist) {
		this.fundExist = fundExist;
	}
}
