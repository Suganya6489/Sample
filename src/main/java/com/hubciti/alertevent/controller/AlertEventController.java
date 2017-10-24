package com.hubciti.alertevent.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.constants.HubCitiURLPath;

/**
 * Class containing services related to alerts/events.
 * 
 * @author kumar_dodda
 *
 */
@Path(HubCitiConstants.ALERTEVENT)
public interface AlertEventController {

	/**
	 * Method to get alert list on tap on Alert button on menu.
	 * 
	 * @param xml
	 * @return String xml containing alert list.
	 */
	@POST
	@Path(HubCitiURLPath.ALERTLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getAlertsList(String xml);

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
	@Path(HubCitiURLPath.GETDININGRETINFO)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String getDiningRetInfoForLocation(String xml);

	/**
	 * Method to capture click on Alert List.
	 * 
	 * @param xml
	 * @return String xml containing alert details.
	 */
	@GET
	@Path(HubCitiURLPath.UTALERTCLICK)
	@Produces("text/xml")
	void userTrackingAlertClick(@QueryParam("alertListId") Integer alertListId);

	/**
	 * This Controller method for getting dining categories Method Type: GET.
	 * 
	 * @param input
	 *            userId, hubCitiId.
	 * @return response XML as String containing category list.
	 */
	@GET
	@Path(HubCitiURLPath.DININGCATEGORY)
	@Produces("text/xml;charset=UTF-8")
	String getDiningCategories(@QueryParam("userId") Integer userId, @QueryParam("hubCitiId") Integer hubCitiId);



	/**
	 * This Controller method to event details information .
	 * 
	 * @param eventId
	 *            contains information event.
	 * @return XML in the response containing event information.
	 */
	@GET
	@Path(HubCitiURLPath.FETCHEVENTDETAIL)
	@Produces("text/xml;charset=UTF-8")
	String getEventDetails(@QueryParam("eventId") Integer eventId, @QueryParam("eventsListId") Integer eventsListId, @QueryParam("hubCitiId") Integer hubCitiId, @QueryParam("platform")String platform);

	/**
	 * This Controller method for fetch the hotel for the given zip code or
	 * location attributes(Latitude or longitude). Method Type: POST.
	 * 
	 * @param xml
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response XML as String containing Retailers list.
	 */

	@POST
	@Path(HubCitiURLPath.GETEVENNTHOTELDISPLAY)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String getEventHotelDisplay(String xml);

	/**
	 * This Controller method for getting event categories Method Type: GET.
	 * 
	 * @param input
	 *            userId, hubCitiId.
	 * @return response XML as String containing category list.
	 */
	@POST
	@Path(HubCitiURLPath.EVENTCATEGORY)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String getEventCategories(String xml);
//	String getEventCategories(@QueryParam("userId") Integer userId, @QueryParam("hubCitiId") Integer hubCitiId);

	/**
	 * This Controller method to event Hotel details information . Method Type:
	 * GET.
	 * 
	 * @param eventId
	 *            contains information HubCitiID, HcEventID, RetailLocationID
	 *            and RetailerListID.
	 * @return XML in the response containing event information.
	 */
	@POST
	@Path(HubCitiURLPath.FETCHEVENTHOTELDETAIL)
	@Produces("text/xml;charset=UTF-8")
	String getEventHotelDetails(String xml);

	/**
	 * This Controller method to to get locations (App Sites) associated to
	 * events. Method Type: GET.
	 * 
	 * @param eventId
	 *            contains information HubCitiID, HcEventID, RetailLocationID
	 *            and RetailerListID.
	 * @return XML in the response containing event information.
	 */
	@POST
	@Path(HubCitiURLPath.EVENTAPPSITELOC)
	@Produces("text/xml;charset=UTF-8")
	String getEventAppSiteLocation(String xml);
	
	
	/**
	 * This Controller method to get retailer fundraiser on tap on bottom button on menu.
	 * 
	 * @param xml contains information of MenuItem.
	 * @return response XML as String containing Fundraiser list.
	 */
	@POST
	@Path(HubCitiURLPath.FUNDRAISERLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getFundraiserList(String xml);
	
	/**
	 * Method to get list of Fundraiser department list.
	 * 
	 * @param xml
	 * @return response XML as String containing Fundraiser department List.
	 */
	@POST
	@Path(HubCitiURLPath.FUNDRAISERDEPTLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getDepartmentList(String xml);
	
	/**
	 * This Controller method to to get locations (App Sites) associated to fundraiser.
	 * Method Type: POST.
	 * 
	 * @param xml.
	 * @return XML in the response containing fundraiser information.
	 */
	@POST
	@Path(HubCitiURLPath.FUNDAPPSITELOC)
	@Produces("text/xml;charset=UTF-8")
	String getFundAppSiteLocation(String xml);
	
	
	/**
	 * This Controller method to get fundraiser information.
	 * Method :POST
	 * @param  contains information fundraiser.
	 * @return XML in the response containing fundraiser information.  
	 */
	@POST
	@Path(HubCitiURLPath.FETCHFUNDETAIL)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getFundraiserDetails(String xml);
	

	/**
	 * This Controller method to get event logistics information.
	 * Method :POST
	 * @param  contains information event details.
	 * @return XML in the response containing event logistics information.  
	 */
	@POST
	@Path(HubCitiURLPath.EVENTLOGISTICS)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getEventLogistics(String xml);
	
	
	
	/**
	 * This Controller method to get fundraiser Location display organization hosting or HubCiti AppSite or Retailer AppSite
	 * ; 
	 * Method :POST
	 * @param  contains information fundraiser information
	 * @return XML in the response containing fundraiser information.  
	 */
	@POST
	@Path(HubCitiURLPath.GETFUNDLOCLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getFundraiserLocationList(String xml);
	
	/**
	 * Controller method is remove Event List information from cached. 
	 * Method Type:GET.
	 * 
	 * @return XML containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_EVENT_LIST)
	@Produces(MediaType.APPLICATION_JSON)
	String remEventListCache();
	
	
  	/**
	 * This controller method for Event List or Band Event List.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.EVENTLIST)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getEventList(String strJSON);
	

  	/**
	 * Controller method to get retailer fundraiser on tap on bottom button on menu.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.FUNDRAISERLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getFundraiserListJson(String strJSON);
	
}
