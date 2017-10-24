package com.hubciti.find.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.find.service.FindService;
import com.scansee.externalapi.common.constants.ApplicationConstants;


public class FindControllerImpl implements FindController {

	/**
	 * Getting the logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FindControllerImpl.class);

	/**
	 * debugger flag for logging.
	 */
	private static final boolean ISDEBUGENABLED = LOG.isDebugEnabled();

	/**
	 * The FirstUseControllerImpl Constructor.
	 */
	public FindControllerImpl() {

	}

	/**
	 * This is a RestEasy WebService Method for fetching Google categories
	 * information. Method Type:POST
	 * 
	 * @param XML
	 *            containing userId, hubCitiId, mainMenuId/(bottomBtnId,
	 *            mItemId), platform.
	 * @return XML containing success or failure in the response.
	 */
	public String getCategory(String xml) {
		LOG.info("Inside FindControllerImpl : getCategory");
		String response = null;
		//		Integer iGpsEnabled = null;
		final FindService findService = ServiceFactory.getFindService();
		try {

			//			siGpsEnabled = Utility.DOMParseToFindCategory(xml);
			//			response = findService.getCategoryInfo(xml, iGpsEnabled);
			response = findService.getCategoryInfo(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : getCategory : ", exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindControllerImpl : getCategory");
		return response;
	}

	/**
	 * This is a RestEasyImpl WebService Method to find retailer for ScanSee
	 * data.
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing the ScanSee data search details in the response.
	 */
	public String findScanSeeRetSearch(String inputXml) {
		final String methodName = "findScanSeeRetSearch";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			inputXml = inputXml.replace("<searchKey>", "<searchKey><![CDATA[");
			inputXml = inputXml.replace("</searchKey>", "]]></searchKey>");
			response = findService.findScanSeeRetSearch(inputXml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This is a RestEasyImpl WebService Method to find retailer category for
	 * ScanSee data. retailers from ScanSee given coordinates.
	 * 
	 * @param xml
	 * @return xml containing retailer list
	 */
	public String findScanSeeCategorySearch(String xml) {

		LOG.info("Inside FindControllerImpl : findScanSeeCategorySearch");

		String response = null;

		final FindService findService = ServiceFactory.getFindService();

		try {

			if(xml.contains("platform") && xml.contains("Android")){
				xml = URLDecoder.decode(xml, "UTF-8");
			}			

			xml = xml.replace("<catName>", "<catName><![CDATA[");
			xml = xml.replace("</catName>", "]]></catName>");
			xml = xml.replace("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replace("</searchKey>", "]]></searchKey>");
			response = findService.findScanSeeCategorySearch(xml);

		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : findScanSeeCategorySearch : ", exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (UnsupportedEncodingException exception) {
			LOG.error("Inside FindControllerImpl : findScanSeeCategorySearch : ", exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindControllerImpl : findScanSeeCategorySearch");
		return response;
	}

	/**
	 * This is a RestEasyImpl WebService Method to find location details for
	 * Google data given the keyword and coordinates.
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing the Google data search details in the response.
	 */
	public String findGoogleRetSearch(String xml) {
		final String methodName = "findGoogleRetSearch";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			xml = xml.replace("<keyword>", "<keyword><![CDATA[");
			xml = xml.replace("</keyword>", "]]></keyword>");
			xml = xml.replace("<pagetoken>", "<pagetoken><![CDATA[");
			xml = xml.replace("</pagetoken>", "]]></pagetoken>");
			response = findService.findGoogleRetSearch(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This is a Rest Easy Web service for category search for retailers from
	 * Google results given coordinates.
	 * 
	 * @param xml
	 * @return Retailers List
	 */
	public String findGoogleCategorySearch(String xml) {
		final String methodName = "findCatSearchForSSData";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request xml --> " + xml);
		}
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			xml = xml.replace("<type>", "<type><![CDATA[");
			xml = xml.replace("</type>", "]]></type>");
			xml = xml.replace("<pagetoken>", "<pagetoken><![CDATA[");
			xml = xml.replace("</pagetoken>", "]]></pagetoken>");
			response = findService.findGoogleCategorySearch(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + methodName);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This is a RestEasy WebService Method for displaying audio,video and other
	 * information.Method Type:GET.
	 * 
	 * @param userId
	 *            , productId, mediaType, hubCitiId
	 * @return XML containing media information as in the response.
	 */
	public String getMediaInfo(String xml) {
		final String methodName = "getMediaInfo";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Request recieved xml" + xml);
		}
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			response = findService.getMediaInfo(xml);
		} catch (HubCitiException e) {
			LOG.error(HubCitiConstants.ERROROCCURRED + methodName, e);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Response returned is" + response);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This is a RestEasy WebService Method for capturing Product Media click
	 * Method Type:GET.
	 * 
	 * @param pmListID
	 * @return {@link Null} no return parameter
	 */
	public void userTrackingProdMediaClick(Integer pmListId) {
		final String methodName = "userTrackingProdMediaClick";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Request: Product Media List ID: " + pmListId);
		}
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			response = findService.userTrackingProdMediaClick(pmListId);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.ERROROCCURRED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Response" + " {} ", response);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
	}

	/**
	 * This is a RestEasy WebService Method for fetching product summary details
	 * for the given input as XML.
	 * 
	 * @param xml
	 *            -As a request parameter
	 * @return response containing product summary.
	 */
	public String getProductSummary(String xml) {
		final String methodName = "getProductSummary";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Request recieved {}", xml);
		}
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			response = findService.getProductSummary(xml);
		} catch (HubCitiException e) {
			LOG.error(HubCitiConstants.ERROROCCURRED + methodName, e);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * the GET method which fetches Product Attributes information.
	 * 
	 * @param userId
	 *            of the user
	 * @param productId
	 *            are in the request.
	 * @return returns response XML based on Success or Error.
	 */
	public String getProductAttributes(Integer userId, Integer productId) {
		final String methodName = "getProductAttributes";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Recieved Data: userId:" + userId + "productId:" + productId);
		}
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			response = findService.fetchProductAttributes(userId, productId);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * ControllerImpl method to displaying coupon information.Method Type:POST.
	 * 
	 * @param xml
	 *            needed for displaying coupon information
	 * @return xml containing coupon information as response.
	 */
	public String fetchMSLCLRInfo(String xml) {
		final String methodName = "fetchMSLCLRInfo ";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			response = findService.fetchMSLCLRDetails(xml);
		} catch (HubCitiException e) {
			LOG.error(HubCitiConstants.ERROROCCURRED + methodName, e);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Response returned is " + response);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * ControllerImpl method for capturing Online Product Click.
	 * 
	 * @param xml
	 *            needed for displaying information.
	 */
	public void userTrackingOnlineStoreClick(String xml) {
		LOG.info("Inside FindControllerImpl : userTrackingOnlineStoreClick");
		String responseXML = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			responseXML = findService.userTrackingOnlineStoreClick(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : userTrackingOnlineStoreClick : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
	}

	public String findSingleCategoryRetailers(String xml) {
		final String methodName = "findCatSearchForScanSeeData";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request xml --> " + xml);
		}
		String response = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			xml = xml.replace("<catName>", "<catName><![CDATA[");
			xml = xml.replace("</catName>", "]]></catName>");
			xml = xml.replace("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replace("</searchKey>", "]]></searchKey>");
			response = findService.findSingleCategoryRetailers(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + methodName);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This Controller method for getting sub categories for find single
	 * category.
	 * 
	 * @param input
	 *            userId, hubCitiId.
	 * @return response XML as String containing category list.
	 */
	public String getSubCategories(String xml) {
		LOG.info("Inside FindControllerImpl : getSubCategories");
		String responseXML = null;
		final FindService findService = ServiceFactory.getFindService();
		try {
			responseXML = findService.getSubCategories(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : getSubCategories : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This ControllerImpl method to get filter list.
	 * 
	 * @param xml.
	 * @return XML in the response containing filter details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFilterList(String xml) {
		LOG.info("Inside FindControllerImpl : getFilterList");

		String strResponseXML = null;

		final FindService findService = ServiceFactory.getFindService();
		try {

			strResponseXML = findService.getFilterList(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : getFilterList : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindControllerImpl : getFilterList");
		return strResponseXML;
	}


	/**
	 * This ControllerImpl method to get Sort and Filter List. 
	 * 
	 * @param xml.
	 * @return XML in the response containing Sort and Filter details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getSortFilterList(String xml) {
		LOG.info("Inside FindControllerImpl : getSortFilterList");

		String strResponseXML = null;

		final FindService findService = ServiceFactory.getFindService();
		try {
			xml = xml.replace("<catName>", "<catName><![CDATA[");
			xml = xml.replace("</catName>", "]]></catName>");
			strResponseXML = findService.getSortFilterList(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : getSortFilterList : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindControllerImpl : getSortFilterList");
		return strResponseXML;
	}


	/**
	 * This controller method for getting Interest list based on specified type
	 * 
	 * @param xml
	 *            input
	 * @return response XML as String containing filter List
	 * 
	 */
	public String getInterestList(String xml) {


		LOG.info(ApplicationConstants.METHODSTART
				+ "  Inside FindControllerImpl : " + "getInterestList");

		String strResponseXML = null;

		final FindService findService = ServiceFactory.getFindService();

		try {

			strResponseXML = findService.getInterestList(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside  FindControllerImpl : getInterestListDisplay : "
					+ exception);
			strResponseXML = Utility.formResponseXml(
					HubCitiConstants.TECHNICALPROBLEMCODE,
					HubCitiConstants.TECHNICALPROBLEMTEXT);

		}

		LOG.info(ApplicationConstants.METHODEND
				+ "inside FindControllerImpl : " + "getInterestList");

		return strResponseXML;
	}


	/**
	 *  This controller method for getting option list based on specified type
	 * @param xml
	 * @return
	 */
	public String getOptionList(String xml) {


		LOG.info(ApplicationConstants.METHODSTART
				+ "  Inside FindControllerImpl : " + "getOptionList");

		String strResponseXML = null;

		final FindService findService = ServiceFactory.getFindService();

		try {

			strResponseXML = findService.getOptionList(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside  FindControllerImpl : getOptionListDisplay : "
					+ exception);
			strResponseXML = Utility.formResponseXml(
					HubCitiConstants.TECHNICALPROBLEMCODE,
					HubCitiConstants.TECHNICALPROBLEMTEXT);

		}

		LOG.info(ApplicationConstants.METHODEND
				+ "inside FindControllerImpl : " + "getOptionList");

		return strResponseXML;
	}

	/**
	 *  This controller method for getting Category List
	 * @param xml
	 * @return
	 */
	public String getCategoryList(String xml) {



		LOG.info(ApplicationConstants.METHODSTART + "  Inside FindControllerImpl : " + "getCategoryList");

		String strResponseXML = null;
		final FindService findService = ServiceFactory.getFindService();

		try {

			strResponseXML = findService.getCategoryList(xml);

		} catch (HubCitiException exception) {

			LOG.error("Inside  FindControllerImpl : getOptionListDisplay : " + exception);
			strResponseXML = Utility.formResponseXml( HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);

		}
		LOG.info(ApplicationConstants.METHODEND
				+ "inside FindControllerImpl : " + "getCategoryList");

		return strResponseXML;
	}


	/**
	 * This is a RestEasy WebService Method for fetching Google categories
	 * information. Method Type:POST
	 * 
	 * @param strJSON
	 *            containing userId, hubCitiId, mainMenuId/(bottomBtnId,
	 *            mItemId), platform.
	 * @return strJSON containing success or failure in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getCategoryJson(String strJSON) {
		LOG.info("Inside FindControllerImpl : getCategoryJson");

		String strJsonResponse = null;
		//		Integer iGpsEnabled = null;
		final FindService findService = ServiceFactory.getFindService();
		try {

			//			siGpsEnabled = Utility.DOMParseToFindCategory(xml);
			//			response = findService.getCategoryJsonInfo(xml, iGpsEnabled);
			strJsonResponse = findService.getCategoryJsonInfo(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : getCategoryJson : ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindControllerImpl : getCategoryJson");
		return strJsonResponse;
	}

	/**
	 * This controllerImpl method for category search for retailer.
	 * 
	 * @param strJSON input parameter.
	 * @return JSON Retailers List
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String findSingleCategoryRetailersJson(String strJSON) {
		LOG.info("Inside FindControllerImpl : findSingleCategoryRetailersJson");

		String strJsonResponse = null;
		final FindService findService = ServiceFactory.getFindService();
		try {

			strJsonResponse = findService.findSingleCategoryRetailersJson(strJSON);

		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : findSingleCategoryRetailersJson ", exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Inside FindControllerImpl : findSingleCategoryRetailersJson");
		return strJsonResponse;
	}


	/**
	 * This is a RestEasyImpl WebService Method to find retailer for ScanSee
	 * data.
	 * 
	 * 
	 * @param JSON
	 * @return JSON containing the ScanSee data search details in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * 
	 */
	public String findScanSeeRetSearchJson(String strJSON) {
		LOG.info("Inside FindControllerImpl : findSingleCategoryRetailersJson");

		String response = null;
		final FindService findService = ServiceFactory.getFindService();

		try {
			response = findService.findScanSeeRetSearchJson(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : findScanSeeRetSearchJson ", exception);
			response = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Inside FindControllerImpl : findSingleCategoryRetailersJson");
		return response;
	}


	/**
	 * This controllerImpl method  for category search for retailers given
	 * coordinates from ScanSee database
	 * 
	 * @param strJSON
	 *            as request
	 * @return strJSON containing the ScanSee data search details in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String findScanSeeCategorySearchJson(String strJSON) {
		LOG.info("Inside FindControllerImpl : findScanSeeCategorySearchJson");

		String response = null;
		final FindService findService = ServiceFactory.getFindService();

		try {

			/*if(xml.contains("platform") && xml.contains("Android")){
				xml = URLDecoder.decode(xml, "UTF-8");
			}			

			xml = xml.replace("<catName>", "<catName><![CDATA[");
			xml = xml.replace("</catName>", "]]></catName>");
			xml = xml.replace("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replace("</searchKey>", "]]></searchKey>");*/
			response = findService.findScanSeeCategorySearchJson(strJSON);

		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : findScanSeeCategorySearchJson : ", exception);
			response = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindControllerImpl : findScanSeeCategorySearchJson");
		return response;
	}

	/**
	 * ControllerImpl method is remove Category information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String delCategoryJson() {
		LOG.info("Inside FindControllerImpl : delCategoryJson");

		String strResponseJson = null;
		final FindService findService = ServiceFactory.getFindService();

		try {
			strResponseJson = findService.delCategoryJson();
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : delCategoryJson : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * ControllerImpl method is remove category search for retailer from cached.  
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String delSingleCategoryRetailersJson() {
		LOG.info("Inside FindControllerImpl : delSingleCategoryRetailersJson");

		String strResponseJson = null;
		final FindService findService = ServiceFactory.getFindService();

		try {
			strResponseJson = findService.delSingleCategoryRetailersJson();
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : delSingleCategoryRetailersJson : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * Controller method is remove location details for ScanSee data from cached.
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String delScanSeeRetSearchJson() {
		LOG.info("Inside FindControllerImpl : delScanSeeRetSearchJson");

		String strResponseJson = null;
		final FindService findService = ServiceFactory.getFindService();

		try {
			strResponseJson = findService.delScanSeeRetSearchJson();
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : delScanSeeRetSearchJson : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		return strResponseJson;
	}

	/**
	 * Controller method is remove category search for retailers  from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String delScanSeeCategorySearchJson() {
		LOG.info("Inside FindControllerImpl : delScanSeeCategorySearchJson");

		String strResponseJson = null;
		final FindService findService = ServiceFactory.getFindService();

		try {
			strResponseJson = findService.delScanSeeCategorySearchJson();
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : delScanSeeCategorySearchJson : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;

	}


	/**
	 * Controller method is remove Find All Cache from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String delFindAllCacheJson() {
		LOG.info("Inside FindControllerImpl : delFindAllCacheJson");

		String strResponseJson = null;
		final FindService findService = ServiceFactory.getFindService();

		try {
			strResponseJson = findService.delFindAllCacheJson();
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : delFindAllCacheJson : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;

	}
}
