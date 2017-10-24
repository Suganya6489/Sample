package com.hubciti.common.pojos;

import java.util.List;

import net.webqa.WebQACustomer;

import com.net.webqa.WebQACustomField;
import com.net.webqa.WebQAFilterItem;
import com.net.webqa.WebQAIssue;

/*-------------------------------------------------------------
 *  Author : Kirankumar Garaddi
 *  written : 25/08/2015
 * 
 *  Compilation : Response.java
 *  Execution   : Java Response
 *  
 *  Response POJO used for generating GOVQA Resultset
 *  Basic responsecode,responsetext,remaining resultset   
 * 
 * 
 *-------------------------------------------------------------*/
public class Response {

	/**
	 * ResponseCode variable
	 */
	private String responseCode;
	/**
	 * Response text variable
	 */
	private String responseText;
	/**
	 * variable for nextFlag
	 */
	private Integer isNext;
	/**
	 * Customer session Id
	 */
	private String sessionId;

	/**
	 * array for custom fields user defined object variable
	 */
	private WebQACustomField[] customFieldList;

	/**
	 * Variable for Customer Information storing
	 */
	private WebQACustomer customer;

	/**
	 * 
	 * Variable for categories list
	 * 
	 */
	WebQAFilterItem[] categories;

	/**
	 * variable for faqs
	 */
	private List<WebQAIssue> faqs;

	/**
	 * 
	 * @return for nextFlag
	 */
	public Integer getIsNext() {
		return isNext;
	}

	/**
	 * 
	 * @param nextFlag
	 *            to set
	 */

	public void setIsNext(Integer isNext) {
		this.isNext = isNext;
	}
	/**
	 * 
	 * @return for faqs
	 */

	public List<WebQAIssue> getFaqs() {
		return faqs;
	}

	/**
	 * 
	 * @param faqs
	 *            to set
	 */

	public void setFaqs(List<WebQAIssue> faqs) {
		this.faqs = faqs;
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
	 * @param responseCode
	 *            to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * 
	 * @return the response Text
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * 
	 * @param responseText
	 *            to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**
	 * 
	 * @return get Session Id
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * 
	 * @param sessionId
	 *            to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * 
	 * @return the customer information
	 */
	public WebQACustomer getCustomer() {
		return customer;
	}

	/**
	 * 
	 * @param customer
	 *            to set
	 */
	public void setCustomer(WebQACustomer customer) {
		this.customer = customer;
	}

	/**
	 * 
	 * @return the customfields array
	 */
	public WebQACustomField[] getCustomFieldList() {
		return customFieldList;
	}

	/**
	 * 
	 * @param customFieldList
	 *            to set
	 */
	public void setCustomFieldList(WebQACustomField[] customFieldList) {
		this.customFieldList = customFieldList;
	}

	/**
	 * 
	 * @return the Faq categories
	 */
	public WebQAFilterItem[] getCategories() {
		return categories;
	}

	/**
	 * 
	 * @param categcategories
	 *            to set
	 */
	public void setCategories(WebQAFilterItem[] categories) {
		this.categories = categories;
	}

}
