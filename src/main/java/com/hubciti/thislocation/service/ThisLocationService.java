package com.hubciti.thislocation.service;

import com.hubciti.common.exception.HubCitiException;

/**
 * Class For ThisLocationService
 * 
 * @author Kumar D
 */
public interface ThisLocationService {
	/**
	 * Service method is to get partners.
	 * 
	 * @param iCitiExpID
	 * @return response XML as a string containing partners
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getPartners(Integer iCitiExpId, Integer userId,Integer bottomBtnId,Integer menuItemId) throws HubCitiException;

	/**
	 * Service method is to get partner retailers.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	String getRetailersForPartner(String xml) throws HubCitiException;

	/**
	 * Service method is to get Citi Experience retailers.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	String getRetailersForCitiExpirence(String xml) throws HubCitiException;

	/**
	 * Service method to get retailer summary.
	 * 
	 * @param xml
	 * @return esponse XML .
	 * @throws HubCitiException
	 */
	String getRetailerSummary(String xml) throws HubCitiException;

	/**
	 * The service method to get categories for partner retailers. It also
	 * validates the required fields.
	 * 
	 * @param xml
	 * @return response XML as a string containing categories
	 * @throws HubCitiException
	 */
	String getCategoriesForPartners(String xml) throws HubCitiException;

	/**
	 * The service method to get categories for group retailers. It also
	 * validates the required fields.
	 * 
	 * @param xml
	 * @return response XML as a string containing categories
	 * @throws HubCitiException
	 */
	String getCategoriesForGroupRetailers(String xml) throws HubCitiException;

	/**
	 * This Service Layer method for fetching retailer special deals.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing hot deals or if
	 *         exception it will return error message XML.
	 */
	String getRetailerSpecialDeals(String xml) throws HubCitiException;

	/**
	 * This Service Layer method for fetching retailer location hot deal
	 * information.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing hot deals or if
	 *         exception it will return error message XML.
	 */
	String getRetailerHotDeals(String xml) throws HubCitiException;

	String getRetailerLocationCoupon(String xml) throws HubCitiException;

	/**
	 * The Service method for fetching Products list for the given Category and
	 * Retailer location ID.
	 * 
	 * @param xml
	 *            - the request XML containingCategory and Retailer location ID.
	 * @return response XML as String with Product list or No Product found
	 *         message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	String getProductsInfo(String xml) throws HubCitiException;

	/**
	 * The Service method for fetching retailer special offer information.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing retailer details or if
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getRetailerSpecialOffDetails(String xml) throws HubCitiException;

	/**
	 * For user tracking retailer summary clicks. To be called whenever user
	 * taps on any of the item in the Retailer summary screen.
	 * 
	 * @param xml
	 * @return xml containing SUCCESS or FAILURE response
	 * @throws ScanSeeException
	 */
	String userTrackingRetailerSummaryClick(String xml) throws HubCitiException;

	/**
	 * This Service method for fetching retailer Special Offer list.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing retailer details or
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String fetchSpecialDealsDetails(String xml) throws HubCitiException;

	/**
	 * This Service method for display retailer summary anything page details.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing anything page or
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getRetSumAnyThingDetails(String xml) throws HubCitiException;

	/**
	 * This Service method for fetching Coupon details.
	 * 
	 * @param xml
	 *            contains couponId,userId,CouponListID which are needed for
	 *            fetching coupon details.
	 * @return String XML with coupon information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String fetchCouponDetails(String xml) throws HubCitiException;

	/**
	 * This Service method for display HubCiti anything page details.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing anything page or
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getHubCitiAnyThingDetails(String xml) throws HubCitiException;

	/**
	 * This is Rest Easy Webservice for getting Latitude and Longitude for the
	 * given Zipcode.
	 * 
	 * @param zipcode
	 *            - as request parameter for Zipcode.
	 * @return returns response XML Containing User's Location attributes if
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer. exception it will return error message XML.
	 */
	String getLatLong(Long zipcode) throws HubCitiException;

	/**
	 * The service method for searching retailers. Calls the XStreams helper to
	 * parse the given request XML and validates the required fields.
	 * 
	 * @param xml
	 *            - the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response XML as a String containing Retailers list or No
	 *         retailers found message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getRetailersInfoForLocation(String xml) throws HubCitiException;

	/**
	 * Method to get app site details.
	 * 
	 * @param xml
	 * @return xml.
	 * @throws HubCitiException.
	 */
	String getAppSiteDetails(String xml) throws HubCitiException;

	/**
	 * The service method for fetching user's Latitude, Longitude info.This
	 * method validates user id and calls DAO method.
	 * 
	 * @param userID
	 *            - whose location attributes need to be fetched.
	 * @return returns response XML Containing User's Location attributes or No
	 *         records found message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getUserLocationPoints(Integer userID) throws HubCitiException;

	/**
	 * The service for update user zip code information .
	 * 
	 * @param userId
	 *            as request parameter.
	 * @param zipcode
	 *            as request parameter
	 * @return String with success or failure response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String updateZipcode(Long userId, String zipcode, Integer hubCitiId) throws HubCitiException;
	
	/**
	 * Method to get Special Offer retailer location List.
	 * @param xml input
	 * @return response code contains list else error Message
	 * @throws HubCitiException 
	 * 					throws if Exception Occurs
	 */
	String getSpecialOfferRetLocList(String xml) throws HubCitiException;

	/**
	 * Method to get Special Offer Details
	 * @param xml input
	 * @return response code contains list else error Message
	 * @throws HubCitiException 
	 * 					throws if Exception Occurs
	 */
	String getSpecialOfferDetails(String xml) throws HubCitiException ;
	
	/**
	 * Service method is to Claim Business.
	 * 
	 * @param Json String type.
	 */
	String claimYourBusiness(String strJson) throws HubCitiException ;

	/**
	 * Service method is to get Claim Business details.
	 * 
	 * @param Json String type.
	 */
	String getClaimYourBusinessdetails(String strJson) throws HubCitiException ;
	

	/**
	 * Service method is to get Citi Experience retailers.
	 * 
	 * @param strJSON
	 */
	String getRetailersForCitiExpirenceJson(String strJSON) throws HubCitiException;
	
	/**
	 * The service method for searching retailers. Calls the XStreams helper to
	 * parse the given request XML and validates the required fields.
	 * 
	 * @param strJson
	 *            - the input request contains search parameters like zipcode or
	 *            location attributes.
	 */
	String getRetailersInfoForLocationJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to get fetching Coupon details.
	 * 
	 * @param strJson
	 */
	String getCouponDetailsJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to get retailers for Partners/Affiliates.
	 * 
	 * @param strJson
	 */
	String getRetailersForPartnerJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to get Special Offer Details.
	 * 
	 * @param strJson
	 */
	String getSpecialOfferDetailJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to display HubCiti anything page details.
	 * 
	 * @param strJson
	 */
	String getHubCitiAnyThingDetailsJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to display retailer Special Offer list.
	 * 
	 * @param strJson
	 */
	String getRetailerSpecialOffDetailsJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to display  Special Offer list for Location's.
	 * 
	 * @param strJson
	 */
	String getSpecialOfferLocationListJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to display  Special Offer list for map Location's.
	 * 
	 * @param strJson
	 */
	String getSpecialOfferLocationMapListJson(String strJSON) throws HubCitiException;
	
}
