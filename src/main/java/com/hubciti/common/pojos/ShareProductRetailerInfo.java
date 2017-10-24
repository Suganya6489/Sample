package com.hubciti.common.pojos;

/**
 * The ShareProductRetailerInfo contains setter and getter methods for
 * ShareProductRetailerInfo fields.
 * 
 * @author Kumar D
 */
public class ShareProductRetailerInfo {
	private String responseCode;

	private String responseText;
	/**
	 * The productDetail declared as ShareProductInfoEmailLink object.
	 */
	private ShareProductInfoEmailLink productDetail;

	/**
	 * The retailerDetail declared as RetailerDetail object.
	 */
	private RetailerDetail retailerDetail;

	/**
	 * for shareProductText.
	 */
	private String titleText;

	/**
	 * for mediaTitleText.
	 */
	private String titleText2;

	/**
	 * To get productDetail.
	 * 
	 * @return the productDetail
	 */
	public ShareProductInfoEmailLink getProductDetail() {
		return productDetail;
	}

	/**
	 * To set productDetail.
	 * 
	 * @param productDetail
	 *            the productDetail to set
	 */
	public void setProductDetail(ShareProductInfoEmailLink productDetail) {
		this.productDetail = productDetail;
	}

	/**
	 * To get retailerDetail.
	 * 
	 * @return the retailerDetail
	 */
	public RetailerDetail getRetailerDetail() {
		return retailerDetail;
	}

	/**
	 * To set retailerDetail.
	 * 
	 * @param retailerDetail
	 *            the retailerDetail to set
	 */
	public void setRetailerDetail(RetailerDetail retailerDetail) {
		this.retailerDetail = retailerDetail;
	}

	/**
	 * To get titleText.
	 * 
	 * @return the titleText
	 */
	public String getTitleText() {
		return titleText;
	}

	/**
	 * To set titleText.
	 * 
	 * @param titleText
	 *            the titleText to set
	 */
	public void setTitleText(String titleText) {
		this.titleText = titleText;
	}

	/**
	 * To get productDetail.
	 * 
	 * @return the titleText2
	 */
	public String getTitleText2() {
		return titleText2;
	}

	/**
	 * To set titleText2.
	 * 
	 * @param titleText2
	 *            the titleText2 to set
	 */
	public void setTitleText2(String titleText2) {
		this.titleText2 = titleText2;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode
	 *            the responseCode to set
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
	 * @param responseText
	 *            the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
}
