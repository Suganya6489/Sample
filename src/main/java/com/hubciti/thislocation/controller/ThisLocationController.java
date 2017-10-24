package com.hubciti.thislocation.controller;

import static com.hubciti.common.constants.HubCitiURLPath.CITIEXPRET;
import static com.hubciti.common.constants.HubCitiURLPath.GETCATFORGROUP;
import static com.hubciti.common.constants.HubCitiURLPath.GETCATFORPARTNER;
import static com.hubciti.common.constants.HubCitiURLPath.GETPARTNERS;
import static com.hubciti.common.constants.HubCitiURLPath.PARTNERRET;
import static com.hubciti.common.constants.HubCitiURLPath.RETSUMMARY;
import static com.hubciti.common.constants.HubCitiURLPath.THISLOCATIONBASEURL;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hubciti.common.constants.HubCitiURLPath;

/**
 * Class for ThisLocaltionController
 * 
 * @author Kumar D
 */
@Path(THISLOCATIONBASEURL)
public interface ThisLocationController {

	/**
	 * Controller method is to get partners for Citi Experience Method Type: GET
	 * 
	 * @param citiExperId
	 *            of Integer type.
	 * @return response XML as String containing partner list
	 */
	@GET
	@Path(GETPARTNERS)
	@Produces("text/xml")
	String getPartners(@QueryParam("citiExperId") Integer retGroupId, @QueryParam("userId") Integer userID,
			@QueryParam("bottomBtnId") Integer bottomBtnId, @QueryParam("menuItemId") Integer menuItemId);

	/**
	 * Controller method to get retailers for Partners/Affiliates.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	@POST
	@Path(PARTNERRET)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String getRetailersForPartner(String xml);

	/**
	 * Controller method to get retailers for Citi Experience.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	@POST
	@Path(CITIEXPRET)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getRetailersForCitiExpirence(String xml);

	/**
	 * This is restEasy webservice method to get retailer summary.
	 * 
	 * @param xml
	 * @return String xm l containing retailer summary.
	 */
	@POST
	@Path(RETSUMMARY)
	@Produces("text/xml;charset=UTF-8")
	String getRetailerSummary(String xml);

	/**
	 * This is restEasy webservice method to get categories for Specified
	 * partner retailers. Method Type: POST
	 * 
	 * @param xml
	 * @return response XML as String containing partner list
	 */
	@POST
	@Path(GETCATFORPARTNER)
	@Produces("text/xml")
	String getCategoriesForPartners(String xml);

	/**
	 * This is restEasy webservice method to get categories for Specified group
	 * retailers. Method Type: POST
	 * 
	 * @param xml
	 * @return response XML as String containing partner list
	 */
	@POST
	@Path(GETCATFORGROUP)
	@Produces("text/xml")
	String getCategoriesForGroupRetailers(String xml);

	/**
	 * This is restEasy webservice method to get retiler special deals.
	 * 
	 * @param xml
	 * @return xml.
	 */
	@POST
	@Path(HubCitiURLPath.RETSPEDEALOFF)
	@Consumes("text/xml")
	@Produces("text/xml")
	String getRetailerSpecialDeals(String xml);

	/**
	 * This Rest Easy method for fetching retailer location hot deals.
	 * 
	 * @param xml
	 *            as the request XML containing userId, retaileID,
	 *            retailerLocationID info and lowerLimit
	 * @return returns response XML As String Containing hot deal list or
	 *         exception it will return error message XML.
	 */
	@POST
	@Path(HubCitiURLPath.GETRETHOTDEALS)
	@Consumes("text/xml")
	@Produces("text/xml")
	String getRetailerHotDeals(String xml);

	/**
	 * This Rest Easy method for fetching retailer location coupon. Method Type:
	 * POST
	 * 
	 * @param xml
	 *            as the request XML containing userId, retaileID,
	 *            retailerLocationID info and lowerLimit
	 * @return returns response XML As String Containing hot deal list or
	 *         exception it will return error message XML.
	 */
	@POST
	@Path(HubCitiURLPath.RETLOCCOUP)
	@Consumes("text/xml")
	@Produces("text/xml; charset=utf-8" ) 
	String getRetailerLocationCoupon(String xml);

	/**
	 * This is Rest Easy webservice method for fetching Products information for
	 * the specified Category and Retailer location ID. Method: POST Method
	 * Type: POST
	 * 
	 * @param xml
	 *            input request contains Category and Retailer location ID.
	 * @return returns response XML As String Containing Product List or if
	 *         exception it will return error message XML.
	 */

	@POST
	@Path(HubCitiURLPath.GETPRODUCTS)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String getProductsInfo(String xml);

	/**
	 * This Controller method for fetching retailer Special Offer list. Method
	 * Type: POST
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing retailer details or
	 *         exception it will return error message XML.
	 */
	@POST
	@Path(HubCitiURLPath.GETRETSPEOFFLIST)
	@Consumes("text/xml")
	@Produces("text/xml")
	String getRetailerSpecialOffDetails(String xml);

	/**
	 * This Controller method is used display special offer details. Method
	 * Type: POST
	 * 
	 * @param xml
	 *            containing special deal information
	 * @return xml containing special deal details.
	 */
	@POST
	@Path(HubCitiURLPath.GETRETSPEOFFINFO)
	@Consumes("text/xml")
	@Produces("text/xml")
	String fetchSpecialOffDetails(String xml);

	/**
	 * This Controller method is used display retailer summary anything page
	 * details. Method Type: POST
	 * 
	 * @param xml
	 *            containing anything page details information
	 * @return xml containing anything page details.
	 */
	@POST
	@Path(HubCitiURLPath.RETSUMMARY_GETANYTHINGINFO)
	@Consumes("text/xml")
	@Produces("text/xml")
	String getRetSumAnyThingDetails(String xml);

	/**
	 * This is RestEasy web service method for user tracking retailer summary
	 * clicks. To be called whenever user taps on any of the item in the
	 * Retailer summary screen. Method Type: POST
	 * 
	 * @param xml
	 * @return xml response containing SUCCESS or FAILURE.
	 */
	@POST
	@Path(HubCitiURLPath.UTRETSUMCLICK)
	@Consumes("text/xml")
	@Produces("text/xml")
	void userTrackingRetailerSummaryClick(String xml);

	/**
	 * This Controller method for fetching the Coupon Details. Method Type: POST
	 * 
	 * @param xml
	 *            containing Coupon details information
	 * @return xml containing Coupon details.
	 */
	@POST
	@Path(HubCitiURLPath.GETCOUPONDETAILS)
	@Consumes("text/xml")
	@Produces("text/xml")
	String fetchCouponDetails(String xml);

	/**
	 * This Controller method is used display HubCiti anything page details.
	 * Method Type: POST
	 * 
	 * @param xml
	 *            containing anything page details information
	 * @return xml containing anything page details.
	 */
	@POST
	@Path(HubCitiURLPath.HUBCITI_GETANYTHINGINFO)
	@Consumes("text/xml")
	@Produces("text/xml")
	String getHubCitiAnyThingDetails(String xml);

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
	@GET
	@Path(HubCitiURLPath.FETCHLATLONG)
	@Produces("text/xml")
	String getLatLong(@QueryParam("zipcode") Long zipcode, @QueryParam("userId") Long userId);

	/**
	 * This Controller method for searching the retailer for the given zip code
	 * or location attributes(Latitude or longitude). Method Type: POST.
	 * 
	 * @param xml
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response XML as String containing Retailers list.
	 */
	@POST
	@Path(HubCitiURLPath.GETRETAILERSINFO)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String getRetailersInfoForLocation(String xml);

	/**
	 * This is restEasy webservice method to get app site details.
	 * 
	 * @param xml
	 * @return String xml containing retailer summary.
	 */
	@POST
	@Path(HubCitiURLPath.APPSITEDETAILS)
	@Produces("text/xml")
	String getAppSiteDetails(String xml);

	/**
	 * This Controller method for fetching user's Latitude, Longitude info.
	 * 
	 * @param userID
	 *            whose location attributes need to be fetched.
	 * @return returns response XML Containing User's Location attributes if
	 *         exception it will return error message XML.
	 */
	@GET
	@Path(HubCitiURLPath.FETCHUSERLOCATIONPOINTS)
	@Produces("text/xml")
	String getUserLocationPoints(@QueryParam("userId") Integer userID);

	/**
	 * This Controller method is Rest Easy Webservice for getting Latitude and
	 * Longitude for the given Zipcode.
	 * 
	 * @param zipcode
	 *            as request parameter for Zipcode.
	 * @param userId
	 *            as request parameter for USerid
	 * @return returns response XML Containing User's Location attributes if
	 *         exception it will return error message XML.
	 */
	@GET
	@Path(HubCitiURLPath.UPDATESUSRZIPCODE)
	@Produces("text/xml")
	String updateZipcode(@QueryParam("userId") Long userId, @QueryParam("zipcode") String zipcode, @QueryParam("hubCitiId") Integer hubCitiId);
	
	
	/**
	 * This is restEasy web service method to get Special Offer Retail Locations List (Native change)
	 * 
	 * @param xml contains required input details
	 * @return String xml containing retailer Information.
	 */
	@POST
	@Path(HubCitiURLPath.SPECIALOFFERRETLOCLIST)
	@Produces("text/xml")
	String getSpecialOfferRetLocList(String xml);
	
	/**
	 * This is restEasy web service method to get Special Offer Details
	 * 
	 * @param xml contains required input details
	 * @return String xml containing retailer Information.
	 */
	@POST
	@Path(HubCitiURLPath.SPECIALOFFERDETAILS)
	@Produces("text/xml")
	String getSpecialOfferDetails(String xml);
	
	
	/**
	 * 
	 * Controller method is to Claim Business.
	 * 
	 * @param Json String type.
	 */
	@POST
	@Path(HubCitiURLPath.CLAIM_BUSINESS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String claimYourBusiness(String request);
	
	
	/**
	 * 
	 * Controller method is to get Claim Business details.
	 * 
	 * @param Json String type.
	 */
	@POST
	@Path(HubCitiURLPath.GETCLAIM_BUSINESS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getClaimYourBusinessdetails(String request);
	
	/**
	 * Controller method to get retailers for Citi Experience.
	 * 
	 * @param String JSON.
	 */
	@POST
	@Path(HubCitiURLPath.CITIEXPRET_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getRetailersForCitiExpirenceJson(String strJson);
	
	
	/**
	 * This Controller method for searching the retailer for the given zip code
	 * or location attributes(Latitude or longitude). Method Type: POST.
	 * 
	 * @param strJson
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 */
	@POST
	@Path(HubCitiURLPath.GETRETAILERSINFO_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getRetailersInfoForLocationJson(String strJson);
	

	
	
	/**
	 * Controller method for fetching the Coupon Details. 
	 * Method Type: POST
	 * 
	 * @param strJson containing Coupon details information
	 */
	@POST
	@Path(HubCitiURLPath.GETCOUPONDETAILS_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getCouponDetailsJson(String strJson);
	
	/**
	 * Controller method to get retailers for Partners/Affiliates.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.PARTNERRET_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getRetailersForPartnerJson(String strJson);
	
	/**
	 * Controller method to get Special Offer Detail
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.SPECIALOFFERDETAILS_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getSpecialOfferDetailJson(String strJson);
	
	
	/**
	 * Controller method to display HubCiti anything page details.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.HUBCITI_GETANYTHINGINFO_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getHubCitiAnyThingDetailsJson(String strJson);
	
	
	/**
	 * Controller method to display retailer Special Offer list.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.GETRETSPEOFFLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getRetailerSpecialOffDetailsJson(String strJson);
	
	
	
	/**
	 * Controller method to display Special Offer list for Location's.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.GETSPECIALOFFERLOCATIONLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getSpecialOfferLocationListJson(String strJson);
	
	/**
	 * Controller method to display Special Offer list for map Location's map.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.GETSPECIALOFFERLOCATIONMAPLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getSpecialOfferLocationMapListJson(String strJson);
	
}
