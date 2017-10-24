package com.hubciti.hotdeals.service;

import com.hubciti.common.exception.HubCitiException;

public interface HotDealsService {

	/**
	 * The service method For retrieving each hot deals details.
	 * 
	 * @param userId
	 *            as request
	 * @param hotDealId
	 *            as request
	 * @param hotDealListId
	 *            as request
	 * @return xml based on success or failure
	 * @throws ScanSeeException
	 *             for exception
	 */
	String getHotDealDetail(String xml) throws HubCitiException;

	/**
	 * The service method For displaying hot deals list based on category wise.
	 * 
	 * @param xml
	 *            as request.
	 * @return xml based on success or failure.
	 * @throws HubCitiException
	 *             for exception.
	 */
	String getHotDealslist(String xml) throws HubCitiException;

	/**
	 * The service method For remove hot deal details.
	 * 
	 * @param xml
	 *            as request
	 * @return String based success or failure
	 * @throws HubCitiException
	 *             for exception
	 */
	String removeHDProd(String xml) throws HubCitiException;

	/**
	 * The service method For capturing hot deal click.
	 * 
	 * @param userId
	 *            as request
	 * @param hotDealId
	 *            as request
	 * @param hotDealListId
	 *            as request
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             for exception
	 */
	String userTrackingGetHotDealClick(Integer userId, Integer hotDealId, Integer hotDealListId) throws HubCitiException;

	/**
	 * The Service Method to claim hot deals.
	 * 
	 * @param xml
	 *            is request parameter.
	 * @return xml containing SUCCESS or FAILURE message
	 * @throws HubCitiException
	 *             is thrown.
	 */
	String hotDealClaim(String xml) throws HubCitiException;

	/**
	 * The Service Method to redeem hot deals.
	 * 
	 * @param xml
	 *            is request.
	 * @return xml containing SUCCESS or FAILURE message
	 * @throws HubCitiException
	 *             is thrown.
	 */
	String hotDealRedeem(String xml) throws HubCitiException;
	
	/**
	 * Service Method to get List of user deals.
	 *  Method Type : POST.
	 * 
	 * @param xml, 
	 * @return XML containing the List of user deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String userDealsDisplay(String xml) throws HubCitiException;
	
	/**
	 * Service Method to get List of User Gallery Deals.
	 *  Method Type : POST.
	 * 
	 * @param xml, 
	 * @return XML containing the List of User Gallery Deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String userGalleryDisplay(String xml) throws HubCitiException;
	
	/**
	 * service Method to get List of Fundraisers.
	 * MethodType : POST
	 * 
	 * @param xml  is request
	 * @return XML containing the List of Fundraisers.
	 * @throws HubCitiException throws if execption occurs
	 */
	String getFundraisersList(String xml) throws HubCitiException;
	
	/**
	 * Service Method to get retailer Clicks and impressions.
	 * 
	 * @param strJSON, 
	 */
	void retailerClickImpression(String strJSON) throws HubCitiException;
	
	/**
	 * Service method to get Fundraisers Deals.
	 * 
	 * @param strJson contains information fundraiser list.
	 */
	String getFundraiserListJson(String strJson) throws HubCitiException;
	
	
	/**
	 * Service Method to get List of user deals.
	 * 
	 * @param strJson
	 * @throws HubCitiException throws if exception occurs.
	 */
	String userDealsDisplayJson(String strJson) throws HubCitiException;
	
}
