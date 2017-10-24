package com.hubciti.hotdeals.dao;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.Data;
import com.hubciti.common.pojos.Deal;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.HotDealsCategoryInfo;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.HotDealsListRequest;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.RetailerTrack;

public interface HotDealsDao {

	/**
	 * The DAO method fetches the hotdeal information.
	 * 
	 * @param userId
	 *            as Query parameter
	 * @param hotDealId
	 *            as Query parameter
	 * @return HotDealsListResultSet.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	HotDealsCategoryInfo getHotDealDetail(HotDealsDetails objHotDealsDetails) throws HubCitiException;

	/**
	 * The DAO method fetches the HotDeals Details.
	 * 
	 * @param hotDealsListRequest
	 *            Instance of HotDealsListRequest.
	 * @param screenName
	 *            as query parameter
	 * @return HotDealsListResultSet.
	 * @throws ScanSeeException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	HotDealsListResultSet getHotDealslist(HotDealsListRequest hotDealsListRequest, String screenName) throws HubCitiException;

	/**
	 * The DAO method removes hotDeal product.
	 * 
	 * @param hotDealsListRequest
	 *            as Query parameter
	 * @return response based on success or failure.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	String removeHDProduct(HotDealsListRequest hotDealsListRequest) throws HubCitiException;

	/**
	 * The DAO method to update hot deal click impression to database.
	 * 
	 * @param userId
	 *            as Query parameter
	 * @param hotDealId
	 *            as Query parameter
	 * @return String SUCCESS or FAILURE
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	String userTrackingGetHotDealClick(Integer userId, Integer hotDealId, Integer hotDealListId) throws HubCitiException;

	/**
	 * This DAO Method to claim hot deal. Method Type: POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 */
	String hotDealClaim(HotDealsListRequest objHotDealListRequest) throws HubCitiException;

	/**
	 * This DAO Method to redeem hot deal. Method Type: POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 */
	String hotDealRedeem(HotDealsListRequest objHotDealListRequest) throws HubCitiException;

	/**
	 * DAO Method to get List of user deals.
	 * 
	 * @param objDeal Instance of Deal, 
	 * @return XML containing the List of user deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	Data userDealsDisplay(Deal objDeal) throws HubCitiException;
	
	/**
	 * DAO Method to get List of User Gallery Deals.
	 * 
	 * @param objDeal Instance of Deal, 
	 * @return XML containing the List of User Gallery Deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	Data userGalleryDisplay(Deal objDeal) throws HubCitiException;

	/**
	 * DAO Method to list Fundraisers Deals
	 * @param menuItem instance of fundraisers
	 * @return XML  containing list for fundraisers
	 * @throws HubCitiException throws if exception occurs.
	 *  
	 */
	Fundraiser getFundraisersList(MenuItem menuItem) throws HubCitiException;
	
	/**
	 * DAO Method to get retailer Clicks and impressions.
	 * 
	 * @param objRetailerTrack instance of RetailerTrack
	 */
	void retailerClickImpression(RetailerTrack objRetailerTrack) throws HubCitiException;
	
	
}
