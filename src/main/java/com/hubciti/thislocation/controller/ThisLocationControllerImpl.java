package com.hubciti.thislocation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.thislocation.service.ThisLocationService;

/**
 * Class For ThisLocaltionControllerImpl
 * 
 * @author Kumar D
 */
public class ThisLocationControllerImpl implements ThisLocationController {
	/**
	 * Getting the logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ThisLocationControllerImpl.class);

	/**
	 * The FirstUseControllerImpl Constructor.
	 */
	public ThisLocationControllerImpl() {
	}

	/**
	 * Controller method is to get partners for Citi Experience. Method Type:
	 * GET
	 * 
	 * @param iCitiExpID
	 *            of Integer type.
	 * @return response XML as String containing partner list.
	 */
	public String getPartners(Integer iCitiExpId,  Integer userId,Integer bottomBtnId,Integer menuItemId) {
		LOG.info("Inside ThisLocationControllerImpl : getPartners");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getPartners(iCitiExpId, userId,bottomBtnId,menuItemId);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getPartners : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * Controller method to get retailers for Partners/Affiliates.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	public String getRetailersForPartner(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getPartners");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");
			responseXML = thisLocationService.getRetailersForPartner(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailersForPartner : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * Controller method to get retailers for Citi Experience.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	public String getRetailersForCitiExpirence(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailersForCitiExpirence");

		String responseXML = null;

		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");

			responseXML = thisLocationService.getRetailersForCitiExpirence(xml);

		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailersForCitiExpirence : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getRetailersForCitiExpirence");
		return responseXML;
	}

	public String getRetailerSummary(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailerSummary");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getRetailerSummary(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailerSummary : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This is restEasy webservice method to get categories for Specified
	 * partner retailers. Method Type: POST
	 * 
	 * @param xml
	 * @return response XML as String containing partner list
	 */
	public String getCategoriesForPartners(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getCategoriesForPartners");
		String strResponseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			strResponseXML = thisLocationService.getCategoriesForPartners(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getCategoriesForPartners : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This is restEasy webservice method to get categories for Specified group
	 * retailers. Method Type: POST
	 * 
	 * @param xml
	 * @return response XML as String containing partner list
	 */
	public String getCategoriesForGroupRetailers(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getCategoriesForGroupRetailers");
		String strResponseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			strResponseXML = thisLocationService.getCategoriesForGroupRetailers(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getCategoriesForGroupRetailers : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This is restEasy webservice method to get retiler special deals.
	 * 
	 * @param xml
	 * @return xml.
	 */
	public String getRetailerSpecialDeals(String xml) {
		final String methodName = "getRetailerSpeHotDealsCLRDetails";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getRetailerSpecialDeals(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	public String getRetailerHotDeals(String xml) {
		final String methodName = "getRetailerHotDeals";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request Recieved:" + xml);
		}
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getRetailerHotDeals(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returned Response to Client  :" + responseXML);
		}
		return responseXML;
	}

	public String getRetailerLocationCoupon(String xml) {
		final String methodName = "getRetailerLocationCoupon";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request Recieved:" + xml);
		}
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getRetailerLocationCoupon(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returned Response to Client  :" + responseXML);
		}
		return responseXML;
	}

	/**
	 * This is Rest Easy webservice method for fetching Products information for
	 * the specified Category and Retailer location ID. Method: POST
	 * 
	 * @param xml
	 *            input request contains Category and Retailer location ID.
	 * @return returns response XML As String Containing Product List or if
	 *         exception it will return error message XML.
	 */
	public String getProductsInfo(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getProductsInfo");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getProductsInfo(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getProductsInfo");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;

	}

	/**
	 * This RestImpl Easy method for fetching retailer Special Offer list.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing retailer details or
	 *         exception it will return error message XML.
	 */

	public String getRetailerSpecialOffDetails(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailerSpecialOffDetails");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getRetailerSpecialOffDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailerSpecialOffDetails");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This is RestEasy webservice method for user tracking retailer summary
	 * clicks. To be called whenever user taps on any of the item in the
	 * Retailer summary screen.
	 * 
	 * @param xml
	 * @return xml response SUCCESS or FAILURE
	 */
	public void userTrackingRetailerSummaryClick(String xml) {
		final String methodName = "userTrackingRetailerSummaryClick";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug(" Retailer Search Request :" + xml);
		}
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.userTrackingRetailerSummaryClick(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response :" + responseXML);
		}
	}

	/**
	 * This ControllerImpl method is used display special offer details.
	 * 
	 * @param xml
	 *            containing special deal information
	 * @return xml containing special deal details.
	 */
	public String fetchSpecialOffDetails(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : fetchSpecialOffDetails");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.fetchSpecialDealsDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : fetchSpecialOffDetails");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This ControllerImpl method is used display retailer summary anything page
	 * details.
	 * 
	 * @param xml
	 *            containing anything page information
	 * @return xml containing anything page details.
	 */
	public String getRetSumAnyThingDetails(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getRetSumAnyThingDetails");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getRetSumAnyThingDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getRetSumAnyThingDetails");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This Controller method for fetching the Coupon Details. Method Type: POST
	 * 
	 * @param xml
	 *            containing Coupon details information
	 * @return xml containing Coupon details.
	 */
	public String fetchCouponDetails(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : fetchCouponDetails");
		String responseXml = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXml = thisLocationService.fetchCouponDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getRetSumAnyThingDetails");
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * This ControllerImpl method is used display HubCiti anything page details.
	 * 
	 * @param xml
	 *            containing anything page information
	 * @return xml containing anything page details.
	 */
	public String getHubCitiAnyThingDetails(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getHubCitiAnyThingDetails");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getHubCitiAnyThingDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getHubCitiAnyThingDetails");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This is Rest Easy Webservice for getting Latitude and Longitude for the
	 * given Zipcode.
	 * 
	 * @param zipcode
	 *            as request parameter for Zipcode.
	 * @param userId
	 *            as request parameter for USerid
	 * @return returns response XML Containing User's Location attributes if
	 *         exception it will return error message XML.
	 */
	public String getLatLong(Long zipcode, Long userId) {
		final String methodName = "getLatLong";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Recieved Data zipcode:" + zipcode);
		}
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getLatLong(zipcode);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returned Response to Client  :" + responseXML);
		}
		return responseXML;
	}

	/**
	 * This ControllerImpl method for searching the retailer for the given zip
	 * code or location attributes(Latitude or longitude). Method Type:POST.
	 * 
	 * @param xml
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response XML as String containing Retailers list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getRetailersInfoForLocation(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailersInfoForLocation");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getRetailersInfoForLocation(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailersInfoForLocation");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	public String getAppSiteDetails(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getAppSiteDetails");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getAppSiteDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getAppSiteDetails : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This ControllerImpl method for fetching user's Latitude, Longitude info.
	 * Method Type:GET.
	 * 
	 * @param userID
	 *            whose location attributes need to be fetched.
	 * @return returns response XML Containing User's Location attributes if
	 *         exception it will return error message XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getUserLocationPoints(Integer userID) {
		LOG.info("Inside ThisLocationControllerImpl : getUserLocationPoints");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getUserLocationPoints(userID);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getUserLocationPoints");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This ControllerImpl for update user zip code information .
	 * 
	 * @param userId
	 *            as request parameter.
	 * @param zipcode
	 *            as request parameter.
	 * @return String with success or failure response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String updateZipcode(Long userId, String zipcode, Integer hubCitiId) {
		LOG.info("Inside ThisLocationControllerImpl : updateZipcode");
		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.updateZipcode(userId, zipcode, hubCitiId);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : updateZipcode");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}


	/**
	 * This is restEasy web service method to get Special Offer Retail Locations List (Native change)
	 * 
	 * @param xml contains required input details
	 * @return String xml containing retailer Information.
	 */
	public String getSpecialOfferRetLocList(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getSpecialOfferLocationList ");

		String responseXML = null;

		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			responseXML = thisLocationService.getSpecialOfferRetLocList(xml); 
		} catch(HubCitiException exception) {

			LOG.error("Exception Inside ThisLocationControllerImpl : getSpecialOfferLocationList ");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);

		}

		LOG.info("Exit ThisLocationControllerImpl : getSpecialOfferLocationList ");
		return responseXML;
	}

	/**
	 * This is restEasy web service method to get Special Offer Details
	 * 
	 * @param xml contains required input details
	 * @return String xml containing retailer Information.
	 */
	public String getSpecialOfferDetails(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : getSpecialOfferDetails ");

		String responseXML = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			responseXML = thisLocationService.getSpecialOfferDetails(xml);
		} catch(HubCitiException exception) { 

			LOG.error("Inside ThisLocationControllerImpl : getSpecialOfferDetails ");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getSpecialOfferDetails ");
		return responseXML;
	}

	/**
	 * 
	 * ControllerImpl method is to Claim Business.
	 * 
	 * @param Json String type.
	 * @return Json containing Success in the response.
	 */
	public String claimYourBusiness(String strJson) {
		LOG.info("Inside ThisLocationControllerImpl : claimYourBusiness ");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			strJsonResponse = thisLocationService.claimYourBusiness(strJson);
		} catch(HubCitiException exception) { 
			LOG.error("Inside ThisLocationControllerImpl : claimYourBusiness ");
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : claimYourBusiness ");
		return strJsonResponse;
	}


	/**
	 * 
	 * ControllerImpl method is to get Claim Business details.
	 * 
	 * @param Json String type.
	 * @return Json containing Success in the response.
	 */
	public String getClaimYourBusinessdetails(String strJson) {
		LOG.info("Inside ThisLocationControllerImpl : getClaimYourBusinessdetails ");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();
		try {
			strJsonResponse = thisLocationService.getClaimYourBusinessdetails(strJson);
		} catch(HubCitiException exception) { 
			LOG.error("Inside ThisLocationControllerImpl : getClaimYourBusinessdetails ");
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getClaimYourBusinessdetails ");
		return strJsonResponse;
	}


	/**
	 * Controller method to get retailers for Citi Experience.
	 * 
	 * @param String
	 *            strJSON.
	 * @return JSON containing retailers list.
	 */
	public String getRetailersForCitiExpirenceJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailersForCitiExpirenceJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getRetailersForCitiExpirenceJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailersForCitiExpirenceJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getRetailersForCitiExpirenceJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method for searching the retailer for the given zip
	 * code or location attributes(Latitude or longitude).
	 * 
	 * @param strJson
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return JSON as String containing Retailers list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getRetailersInfoForLocationJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailersInfoForLocationJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getRetailersInfoForLocationJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailersInfoForLocationJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getRetailersInfoForLocationJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method to get the Coupon Details.
	 * 
	 * @param strJson
	 * @return JSON as Coupon details information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getCouponDetailsJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getCouponDetailsJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getCouponDetailsJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getCouponDetailsJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getCouponDetailsJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method to get retailers for Partners/Affiliates.
	 * 
	 * @param strJson
	 * @return JSON as containing retailers list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getRetailersForPartnerJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailersForPartnerJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getRetailersForPartnerJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailersForPartnerJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getRetailersForPartnerJson");
		return strJsonResponse;
	}
	
	
	
	/**
	 * ControllerImpl method to get Special Offer Detail.
	 * 
	 * @param strJson
	 * @return JSON as containing retailer Information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getSpecialOfferDetailJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getSpecialOfferDetailJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getSpecialOfferDetailJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getSpecialOfferDetailJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getSpecialOfferDetailJson");
		return strJsonResponse;
	}
	
	
	
	/**
	 * ControllerImpl method to display HubCiti anything page details.
	 * 
	 * @param strJson
	 * @return JSON as containing HubCiti anything page details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getHubCitiAnyThingDetailsJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getHubCitiAnyThingDetailsJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getHubCitiAnyThingDetailsJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getHubCitiAnyThingDetailsJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getHubCitiAnyThingDetailsJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method to display retailer Special Offer list.
	 * 
	 * @param strJson
	 * @return JSON as containing Special Offer list(Featured & Non Featured).
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getRetailerSpecialOffDetailsJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getRetailerSpecialOffDetailsJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getRetailerSpecialOffDetailsJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getRetailerSpecialOffDetailsJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getRetailerSpecialOffDetailsJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method to display Special Offer list for Location's.
	 * 
	 * @param strJson
	 * @return JSON as containing Special Offer list(Featured & Non Featured).
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getSpecialOfferLocationListJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getSpecialOfferLocationListJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getSpecialOfferLocationListJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getSpecialOfferLocationListJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getSpecialOfferLocationListJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method to display Special Offer list for map Location's.
	 * 
	 * @param strJson
	 * @return JSON as containing Special Offer list for map Location's.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getSpecialOfferLocationMapListJson(String strJSON) {
		LOG.info("Inside ThisLocationControllerImpl : getSpecialOfferLocationMapListJson");

		String strJsonResponse = null;
		final ThisLocationService thisLocationService = ServiceFactory.getThisLocationService();

		try {
			strJsonResponse = thisLocationService.getSpecialOfferLocationMapListJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside ThisLocationControllerImpl : getSpecialOfferLocationMapListJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit ThisLocationControllerImpl : getSpecialOfferLocationMapListJson");
		return strJsonResponse;
	}

}
