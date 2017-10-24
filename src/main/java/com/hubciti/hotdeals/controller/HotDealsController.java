package com.hubciti.hotdeals.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hubciti.common.constants.HubCitiURLPath;

@Path(HubCitiURLPath.HOTDEALS)
public interface HotDealsController {

	/**
	 * This is a RestEasy WebService Method for fetching each Hot deals
	 * information for the given Hot deal ID. Method Type:POST
	 * 
	 * @param userId
	 *            for which Hot deals information need to be fetched
	 * @param hotDealId
	 *            for which Hot deals information need to be fetched.If Hot deal
	 *            ID is null then it is invalid request.
	 * @return XML containing Hot deals information in the response.
	 */
	@POST
	@Path(HubCitiURLPath.HDDETAIL)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getHotDealDetail(String xml);

	/**
	 * This is a RestEasy WebService Method for fetching Hot deals list for the
	 * given Search text or category. Method Type:POST
	 * 
	 * @param xml
	 *            for which category or search information needed to fetch Hot
	 *            deals list.If category is null then it is invalid request.
	 * @return XML containing Hot deals list information in the response.
	 */
	@POST
	@Path(HubCitiURLPath.GETHOTDEALPRODS)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String getHotDealsList(String xml);

	/**
	 * This is a RestEasy WebService Method for removal Hot Deals information
	 * for the given Hot deal ID. Method Type:POST
	 * 
	 * @param xml
	 *            containing hot deal id and user id for which Hot Deals to be
	 *            removed.If Hot Deal ID is null then it is invalid request.
	 * @return XML containing success or failure in the response.
	 */
	@POST
	@Path(HubCitiURLPath.REMOVEHDPROD)
	@Produces("text/xml")
	@Consumes("text/xml")
	String removeHdProd(String xml);

	/**
	 * This is a RestEasy WebService Method for Catching get hot deal click Need
	 * to be executed when user taps on get hot deal in Hot deals screen
	 * 
	 * @param userId
	 *            for which Hot deals information need to be fetched
	 * @param hotDealId
	 *            for which Hot deals information need to be fetched.If Hot deal
	 *            ID is null then it is invalid request.
	 */
	@GET
	@Path(HubCitiURLPath.UTGETHOTDEALCLICK)
	@Produces("text/xml;charset=UTF-8")
	void userTrackingGetHotDealClick(@QueryParam("userId") Integer userId, @QueryParam("hotDealId") Integer hotDealId,
			@QueryParam("hotDealListId") Integer hotDealListId);

	/**
	 * This is a RestEasy WebService Method to claim hot deal. Method Type: POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 */
	@POST
	@Path(HubCitiURLPath.HOTDEALCLAIM)
	@Produces("text/xml")
	@Consumes("text/xml")
	String hotDealClaim(String xml);

	/**
	 * This is a RestEasy WebService Method to redeem hot deal. Method Type:
	 * POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 */
	@POST
	@Path(HubCitiURLPath.HOTDEALREDEEM)
	@Produces("text/xml")
	@Consumes("text/xml")
	String hotDealRedeem(String xml);
	
	/**
	 * Controller Method to get List of user deals.
	 *  Method Type : POST.
	 * 
	 * @param xml, 
	 * @return XML containing the List of user deals.
	 */
	@POST
	@Path(HubCitiURLPath.DEALSDISPLAY)
	@Produces("text/xml")
	@Consumes("text/xml")
	String userDealsDisplay(String xml);
	
	/**
	 * Controller Method to get List of User Gallery Deals.
	 *  Method Type : POST.
	 * 
	 * @param xml, 
	 * @return XML containing the List of User Gallery Deals.
	 */
	@POST
	@Path(HubCitiURLPath.GALLERYDISPLAY)
	@Produces("text/xml")
	@Consumes("text/xml")
	String userGalleryDisplay(String xml);
	
	
	
	/**
	 * Controller Method to get List of Fundraisers Deals.
	 * Method : POST
	 * @param xml,
	 * @return XML containing the List of Fundraisers.
	 */
	@POST
	@Path(HubCitiURLPath.FUNDRAISERDISPLAY)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getFundraisersList(String xml);
	
	
	/**
	 * Controller Method to get Clicks and impressions .
	 *  Method Type : PUT.
	 * 
	 * @param strJSON, 
	 */
	@POST
	@Path(HubCitiURLPath.RETAILER_TRACKING)
	@Produces(MediaType.APPLICATION_JSON)
	void retailerClickImpression(String strJSON);
	
	
	/**
	 * Controller method to get retailer fundraisers Deals.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.FUNDRAISERLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getFundraiserListJson(String strJSON);
	
	
	/**
	 * Controller Method to get List of user deals.
	 *  Method Type : POST.
	 * 
	 * @param strJson, 
	 * @return strJson containing the List of user deals.
	 */
	@POST
	@Path(HubCitiURLPath.DEALSDISPLAY_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String userDealsDisplayJson(String strJson);
	
}
