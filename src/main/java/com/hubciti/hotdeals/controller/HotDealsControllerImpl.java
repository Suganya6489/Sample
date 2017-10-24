package com.hubciti.hotdeals.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.hotdeals.service.HotDealsService;

public class HotDealsControllerImpl implements HotDealsController {

	/**
	 * Getting the logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(HotDealsControllerImpl.class);

	/**
	 * The HotDealsControllerImpl Constructor.
	 */
	public HotDealsControllerImpl() {

	}

	/**
	 * This is a RestEasyImplimentation WebService Method for fetching each Hot
	 * deals information for the given Hot deal ID. Method Type:GET
	 * 
	 * @param userId
	 *            for which Hot deals information need to be fetched
	 * @param hotDealId
	 *            for which Hot deals information need to be fetched.If Hot deal
	 *            ID is null then it is invalid request.
	 * @param hotDealListID
	 *            For user tracking, hotDealListID is captures
	 * @return XML containing Hot deals information in the response.
	 */
	public String getHotDealDetail(String xml) {
		final String methodName = "getHdProdInfo in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested parameters are " + xml);
		}
		String response = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		try {
			response = hotDealsService.getHotDealDetail(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMCODE);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned for " + methodName + response);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This is a RestEasyImplimentation WebService method for get the hodDeal
	 * list. Calls method in service layer. accepts a POST request, MIME type is
	 * text/xml.
	 * 
	 * @param xml
	 *            input request for which category or search information needed
	 *            to fetch Hot deals list.If category is null then it is invalid
	 *            request.
	 * @return XML containing Hot deals list information in the response.
	 */
	public String getHotDealsList(String xml) {
		final String methodName = "getHotDeallist in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request recieved " + xml);
		}
		String response = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		try {
			response = hotDealsService.getHotDealslist(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned for" + methodName + response);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This is a RestEasy WebService Method for removal Hot Deals information
	 * for the given Hot deal ID. Method Type:POST
	 * 
	 * @param xml
	 *            containing hot deal id and user id for which Hot Deals to be
	 *            removed.If Hot Deal ID is null then it is invalid request.
	 * @return XML containing success or failure in the response.
	 */
	public String removeHdProd(String xml) {
		final String methodName = "removeHdProd in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request recieved " + xml);
		}
		String response = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		try {
			response = hotDealsService.removeHDProd(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This is a RestEasy WebService Method for Catching get hot deal click Need
	 * to be executed when user taps on get hot deal in Hot deals screen
	 * 
	 * @param userId
	 *            for which Hot deals information need to be fetched
	 * @param hotDealId
	 *            for which Hot deals information need to be fetched.If Hot deal
	 *            ID is null then it is invalid request.
	 * @param hotDealListID
	 *            For user tracking, hotDealListID is captures
	 */
	public void userTrackingGetHotDealClick(Integer userId, Integer hotDealId, Integer hotDealListID) {
		final String methodName = "UserTrackingGetHotDealClick in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response;

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested parameters are userId" + userId + "hotDealId" + hotDealId + "hotDealListID" + hotDealListID);
		}
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		try {
			response = hotDealsService.userTrackingGetHotDealClick(userId, hotDealId, hotDealListID);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned for " + methodName + response);
		}
	}

	/**
	 * This is a RestEasy WebService Method to claim hot deal. Method Type: POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 */
	public String hotDealClaim(String xml) {
		final String methodName = "hotDealClaim";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String strResponse = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		try {
			strResponse = hotDealsService.hotDealClaim(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			strResponse = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return strResponse;
	}

	/**
	 * This is a RestEasy WebService Method to redeem hot deal. Method Type:
	 * POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 */
	public String hotDealRedeem(String xml) {
		final String methodName = "getHDProdByCategory";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String strResponse = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		try {
			strResponse = hotDealsService.hotDealRedeem(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			strResponse = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return strResponse;
	}
	
	/**
	 * ControllerImpl Method to get List of user deals.
	 *  Method Type : POST.
	 * 
	 * @param xml, 
	 * @return XML containing the List of user deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String userDealsDisplay(String xml) {
		LOG.info("Inside HotDealsControllerImpl : userDealsDisplay");
		
		String strResponse = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		
		try {
			strResponse = hotDealsService.userDealsDisplay(xml);
		} catch (HubCitiException e) {
			LOG.error("Inside HotDealsControllerImpl : userDealsDisplay : " + e.toString());
			strResponse = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit HotDealsControllerImpl : userDealsDisplay");
		return strResponse;
	}
	
	
	/**
	 * ControllerImpl Method to get List of User Gallery Deals.
	 *  Method Type : POST.
	 * 
	 * @param xml, 
	 * @return XML containing the List of User Gallery Deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String userGalleryDisplay(String xml) {
		LOG.info("Inside HotDealsControllerImpl : userGalleryDisplay");
		
		String strResponse = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		
		try {
			strResponse = hotDealsService.userGalleryDisplay(xml);
		} catch (HubCitiException e) {
			LOG.error("Inside HotDealsControllerImpl : userGalleryDisplay : " + e.toString());
			strResponse = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit HotDealsControllerImpl : userGalleryDisplay");
		return strResponse;
	}
	
	
	
	/**
	 * Controller Method to get List of Fundraisers Deals.
	 * Method : POST
	 * @param xml,
	 * @return XML containing the List of Fundraisers.
	 */

	public String getFundraisersList(String xml) {
		LOG.info("Inside HotDealsControllerImpl : getFundraisersList ");
		String strResponse = null;
		final HotDealsService hotDealsService =ServiceFactory.getHotDealsService();
		try {
			strResponse = hotDealsService.getFundraisersList(xml);
		} catch(HubCitiException e) {
			LOG.error("Inside HotDealsControllerImpl : getFundraisersList " + e.toString());
			strResponse = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponse;
	}
	
	
	
	/**
	 * ControllerImpl Method to get  Clicks and impressions .
	 *  Method Type : PUT.
	 * 
	 * @param strJSON, 
	 * @throws HubCitiException throws if exception occurs.
	 */
	public void retailerClickImpression(String strJSON) {
		LOG.info("Inside HotDealsControllerImpl : retailerClickImpression");
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		
		try {
			 hotDealsService.retailerClickImpression(strJSON);
		} catch (HubCitiException e) {
			LOG.error("Inside HotDealsControllerImpl : retailerClickImpression : " + e.toString());
		}
		LOG.info("Exit HotDealsControllerImpl : retailerClickImpression");
	}
	
	
	/**
	 * This controllerImpl method to get Fundraisers Deals.
	 * 
	 * @param strJSON as request
	 * @return JSON containing fundraiser list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFundraiserListJson(String strJSON) {
		LOG.info("Inside HotDealsControllerImpl : getFundraiserListJson");
		String strJsonResponse = null;

		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();
		
		try {
			strJsonResponse = hotDealsService.getFundraiserListJson(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside HotDealsControllerImpl : getFundraiserListJson : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit HotDealsControllerImpl : getFundraiserListJson ");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl Method to get List of user deals.
	 *  Method Type : POST.
	 * 
	 * @param strJson, 
	 * @return strJson containing the List of user deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String userDealsDisplayJson(String strJson) {
		LOG.info("Inside HotDealsControllerImpl : userDealsDisplayJson");

		String strResponse = null;
		final HotDealsService hotDealsService = ServiceFactory.getHotDealsService();

		try {
			strResponse = hotDealsService.userDealsDisplayJson(strJson);
		} catch (HubCitiException he) {
			LOG.error("Inside HotDealsControllerImpl : userDealsDisplayJson : " + he.toString());
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit HotDealsControllerImpl : userDealsDisplayJson");
		return strResponse;
	}
	
}
