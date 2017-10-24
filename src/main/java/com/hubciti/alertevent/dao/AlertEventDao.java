package com.hubciti.alertevent.dao;

import java.util.List;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.AlertDetails;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.Retailer;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;

public interface AlertEventDao {

	/**
	 * Method to fetch alert list from database.
	 * 
	 * @param objMenuItem
	 * @return {@link AlertDetails} list.
	 * @throws HubCitiException
	 */
	AlertDetails getAlertsList(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * The DAO method fetches the Retailer Details from the database for the
	 * given search parameters(Zipcode or Latitude and longitude).
	 * 
	 * @param thisLocationRequest
	 *            instance of ThisLocationRequest.
	 * @param screenName
	 *            screenName to be used for Pagination size.
	 * @return RetailersDetails returns RetailerDetails object container List of
	 *         retailers.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	RetailersDetails getDiningRetInfoForLocation(ThisLocationRequest thisLocationRequest) throws HubCitiException;

	/**
	 * Method to capture click on Alert List.
	 * 
	 * @param xml
	 * @return String xml containing alert details.
	 * @throws HubCitiException.
	 */
	String userTrackingAlertClick(Integer alertListId) throws HubCitiException;

	/**
	 * The DAO method for fetching event details information
	 * 
	 * @param eventId
	 *            , eventsListId as request parameter.
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<EventDetails> getEventDetails(Integer eventId, Integer eventsListId, Integer hubCitiId) throws HubCitiException;

	/**
	 * The DAO method for fetch the all hotel from the database for the given
	 * search parameters(Zipcode or Latitude and longitude).
	 * 
	 * @param thisLocationRequest
	 *            instance of ThisLocationRequest.
	 * @param screenName
	 *            screenName to be used for Pagination size.
	 * @return RetailersDetails returns RetailerDetails object container List of
	 *         retailers.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	RetailersDetails getEventHotelDisplay(ThisLocationRequest thisLocationRequest) throws HubCitiException;

	/**
	 * Method for getting dining categories.
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return {@link CategoryDetails} object.
	 * @throws HubCitiException
	 */
	CategoryDetails getDiningCategories(Integer userId, Integer hubCitiId) throws HubCitiException;

	/**
	 * Method to fetch event list from database.
	 * 
	 * @param objMenuItem
	 * @return {@link EventDetails} object.
	 * @throws HubCitiException
	 */
	EventDetails getEventList(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * Method for getting Event categories.
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return {@link CategoryDetails} object.
	 * @throws HubCitiException
	 */
	CategoryDetails getEventCategories(MenuItem menuItemObj) throws HubCitiException;

	/**
	 * The DAO method for fetching event hotel details information
	 * 
	 * @param Retailer
	 *            as request parameter.
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<EventDetails> getEventHotelDetails(Retailer objRetailer) throws HubCitiException;

	/**
	 * DAO method to to fetch locations (App Sites) associated to events.
	 * 
	 * @param objRetailer
	 * @return App site list
	 * @throws HubCitiException
	 */
	List<Retailer> getEventAppSiteLocation(Retailer objRetailer) throws HubCitiException;

	/**
	 * DAO method to fetch fundraiser list from database.
	 * 
	 * @param MenuItem object	
	 * @return fundraiser object List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	Fundraiser getFundraiserList(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * DAO method to get list of fundraiser department list.
	 * 
	 * @param MenuItem object
	 * @return Department object List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	List<MenuItem> getDepartmentList(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * DAO method to to fetch locations (App Sites) associated to fundraiser.
	 * 
	 * @param objRetailer
	 * @return App site list
	 * @throws HubCitiException
	 */
	List<Retailer> getFundAppSiteLocation(Retailer objRetailer) throws HubCitiException;

	/**
	 * The DAO method for fetching fundraiser details information.
	 * 
	 * @param fundId
	 *            , fundListId, hubCitiId as request parameter.
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<Fundraiser> getFundraiserDetails(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * The DAO method method to get event logistics information.
	 * 
	 * @param  contains event details information.
	 * @return AlertDetails in the response containing event logistics information.  
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	AlertDetails getEventLogistics(MenuItem objMenuItem) throws HubCitiException;
	
	/**
	 * The DAO method to fetch Fundraiser location List
	 * @param fundraiserRequest 
	 * @return list of appsite Location associated to Fundraisers
	 *  @throws HubCitiException
	 *             throws if exception occurs.
	 */
	Fundraiser getFundraiserLocationList(Fundraiser fundraiserRequest) throws HubCitiException;
	
}
