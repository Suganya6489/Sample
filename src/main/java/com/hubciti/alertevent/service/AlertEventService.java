package com.hubciti.alertevent.service;

import com.hubciti.common.exception.HubCitiException;

public interface AlertEventService {

	/**
	 * Service method to get alert list, also convert request xml to object and
	 * response object to xml.
	 * 
	 * @param xml
	 * @return xml string containing alerts list.
	 * @throws HubCitiException
	 */
	String getAlertsList(String xml) throws HubCitiException;

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
	String getDiningRetInfoForLocation(String xml) throws HubCitiException;

	/**
	 * Method to capture click on Alert List.
	 * 
	 * @param xml
	 * @return String xml containing alert details.
	 * @throws HubCitiException.
	 */
	String userTrackingAlertClick(Integer alertListId) throws HubCitiException;

	/**
	 * Method for getting dining categories
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return category list.
	 * @throws HubCitiException
	 */
	String getDiningCategories(Integer userId, Integer hubCitiId) throws HubCitiException;

	/**
	 * Service method method for Event List or Band Event List.
	 * @param strJSON as request
	 */
	String getEventList(String strJSON) throws HubCitiException;

	/**
	 * This Service method for validates the input parameters and returns
	 * validation error if validation fails. This Method fetches event
	 * information.
	 * 
	 * @param xml
	 *            contains information to fetch event details.
	 * @return String XML with Event Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getEventDetails(Integer eventId, Integer eventsListId, Integer hubCitiId,String platform) throws HubCitiException;

	/**
	 * The service method for for fetch the hotel. Calls the XStreams helper to
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
	String getEventHotelDisplay(String xml) throws HubCitiException;

	/**
	 * Method for getting dining categories
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return category list.
	 * @throws HubCitiException
	 */
	String getEventCategories(String xml) throws HubCitiException;

	/**
	 * This Service method for validates the input parameters and returns
	 * validation error if validation fails. This Method fetches event hotel
	 * information.
	 * 
	 * @param xml
	 *            contains information to the HubCitiID, HcEventID,
	 *            RetailLocationID and RetailerListID.
	 * @return String XML with Event hotel Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getEventHotelDetails(String xml) throws HubCitiException;

	/**
	 * This Controller method to to get locations (App Sites) associated to
	 * events.
	 * 
	 * @param xml
	 * @return app site list.
	 * @throws HubCitiException
	 */
	String getEventAppSiteLocation(String xml) throws HubCitiException;

	/**
	 * Service method to get fundraiser list on tap on bottom button on menu.
	 * 
	 * @param xml contains information about the MenuItem.
	 * @return xml string containing fundraiser list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String getFundraiserList(String xml) throws HubCitiException;

	/**
	 * Service Method to get list of fundraiser department list.
	 * @param xml 
	 * @return XML in the response containing DepartMent details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getDepartmentList(String xml) throws HubCitiException;

	/**
	 * Service method to get locations (App Sites) associated to
	 * fundraiser.
	 * 
	 * @param xml
	 * @return app site list.
	 * @throws HubCitiException
	 *  				throws if exception occurs.
	 */
	String getFundAppSiteLocation(String xml) throws HubCitiException;

	/**
	 * Service method for fetches fundraiser information.
	 * 
	 * @param xml
	 *            
	 * @return String XML with fundraiser Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getFundraiserDetails(String xml) throws HubCitiException;

	/**
	 * Service method to get event logistics information.
	 * @param  contains information event details.
	 * @return XML in the response containing event logistics information.  
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getEventLogistics(String xml) throws HubCitiException;

	/**
	 * Service method to get Fundraiser location if it Associated to Hubciti Appsite or Retailer AppSite
	 * 
	 * @param xml 
	 * 
	 *  @return XML response containing the Fundraiser Address information with lat & long. 
	 */
	String getFundraiserLocationList(String xml) throws HubCitiException;

	/**
	 * Service method is remove EventList information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 */
	String remEventListCache() throws HubCitiException;
	
	/**
	 * Service method to get fundraiser list on tap on bottom button on menu.
	 * 
	 * @param strJson contains information about the MenuItem.
	 */
	String getFundraiserListJson(String strJson) throws HubCitiException;

}
