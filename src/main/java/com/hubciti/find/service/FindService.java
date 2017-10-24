package com.hubciti.find.service;

import com.hubciti.common.exception.HubCitiException;

public interface FindService {

	/**
	 * The service method For displaying Categories.
	 * 
	 * @param userId
	 *            as request parameter
	 * @return String xml.
	 * @throws HubCitiException
	 *             for exception.
	 */
	//	String getCategoryInfo(String xml, Integer iGpsEnabled) throws HubCitiException;
	String getCategoryInfo(String xml) throws HubCitiException;

	/**
	 * This is a RestEasyImpl WebService Method to find retailer for ScanSee
	 * data.
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing the ScanSee data search details in the response.
	 */
	String findScanSeeRetSearch(String xml) throws HubCitiException;

	/**
	 * This is a RestEasyImpl WebService Method to find retailer category for
	 * ScanSee data. retailers from ScanSee given coordinates.
	 * 
	 * @param xml
	 * @return xml containing retailer list
	 */
	String findScanSeeCategorySearch(String xml) throws HubCitiException;

	/**
	 * The service method to find location details for Google data given the
	 * keyword and coordinates.
	 * 
	 * @param xml
	 *            as request.
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             for exception.
	 */
	String findGoogleRetSearch(String xml) throws HubCitiException;

	/**
	 * The service method to find retailers from Google APIs given the
	 * coordinates.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing retailer list
	 * @throws HubCitiException
	 *             for exception
	 */
	String findGoogleCategorySearch(String xml) throws HubCitiException;

	/**
	 * This is a Service Method for displaying audio,video and other
	 * information.
	 * 
	 * @param userId
	 *            , productId, mediaType, hubCitiId
	 * @return XML containing media information as in the response.
	 * @throws HubCitiException.
	 */
	String getMediaInfo(String xml) throws HubCitiException;

	/**
	 * This is a service Method for capturing product media click
	 * 
	 * @param pmListID
	 * @return String with Success or failure information.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String userTrackingProdMediaClick(Integer pmListId) throws HubCitiException;

	/**
	 * This is a service Method for fetching product summary details for the
	 * given input as XML.
	 * 
	 * @param xml
	 *            -Containing input information need to be fetched product
	 *            summary
	 * @return XML containing product summary details in the response.
	 * @throws HubCitiException
	 *             - Throws if exception occurs
	 */
	String getProductSummary(String xml) throws HubCitiException;

	/**
	 * the method fetches product Attributes information.
	 * 
	 * @param userId
	 *            as the request.
	 * @param productId
	 *            as request parameter
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	String fetchProductAttributes(Integer userId, Integer productId) throws HubCitiException;

	/**
	 * this method validates the input parameters and returns validation error
	 * if validation fails. Calls DAO methods to get the products media info
	 * based on the media type.
	 * 
	 * @param xml
	 *            as the parameter.
	 * @return String XML with CLR details.
	 * @throws ScanSeeException
	 *             throws if exception occurs
	 */
	String fetchMSLCLRDetails(String xml) throws HubCitiException;

	/**
	 * Service method for capturing Online Product Click
	 * 
	 * @param xml - the request xml.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String userTrackingOnlineStoreClick(String xml) throws HubCitiException;

	String findSingleCategoryRetailers(String xml) throws HubCitiException;

	/**
	 * Method for getting sub-categories for find single category
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return category list.
	 * @throws HubCitiException
	 */
	String getSubCategories(String xml) throws HubCitiException;

	/**
	 * Service method to get filter list.
	 * 
	 * @param xml - the request xml.
	 * @return xml string containing filter list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String getFilterList(String xml) throws HubCitiException;

	/**
	 * Service method to get Sort and Filter List. 
	 * 
	 * @param xml - the request xml.
	 * @return xml string containing sort and filter List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String getSortFilterList(String xml) throws HubCitiException;

	/**
	 * Service method to get filter list (Interest changes)
	 * 
	 * @param xml
	 *            string containing filter changes
	 * @return
	 * @throws HubCitiException
	 */
	String getInterestList(String xml) throws HubCitiException;

	/**
	 * Service method to get Option Filter list (option changes)
	 * 
	 * @param xml
	 *            string containing filter changes
	 * @return
	 * @throws HubCitiException
	 */
	String getOptionList(String xml) throws HubCitiException;

	/**
	 * Service method to get  Category List
	 * 
	 * @param xml
	 *            string containing Category Information
	 * @return
	 * @throws HubCitiException
	 */
	String getCategoryList(String xml) throws HubCitiException;

	/**
	 * Service method is displaying list Categories. 
	 * 
	 * @return JSON containing success or failure information.
	 */
	String getCategoryJsonInfo(String strJSON) throws HubCitiException;

	/**
	 * Service method for category search for retailer.
	 * 
	 * @return JSON string with retailer information list.
	 */
	String findSingleCategoryRetailersJson(String strJSON) throws HubCitiException;

	/**
	 * Service method to find retailer for ScanSee data.
	 * 
	 * @param strJSON as request.
	 * @return JSON containing the ScanSee data search details in the response.
	 */
	String findScanSeeRetSearchJson(String strJSON) throws HubCitiException;

	/**
	 * Service method to find retailer category for ScanSee data.
	 * 
	 * @param strJSON as request.
	 * @return JSON containing retailer list in the response.
	 */
	String findScanSeeCategorySearchJson(String strJSON) throws HubCitiException;

	/**
	 * Service method is remove Category information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 */
	String delCategoryJson() throws HubCitiException;
	
	/**
	 * Service method is remove category search for retailer from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 */
	String delSingleCategoryRetailersJson() throws HubCitiException;
	
	/**
	 * Service method is remove location details for ScanSee data from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 */
	String delScanSeeRetSearchJson() throws HubCitiException;
	
	/**
	 * Service method is remove category search for retailers  from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 */
	String delScanSeeCategorySearchJson() throws HubCitiException;

	/**
	 * Service method is remove Find All Cache from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 */
	String delFindAllCacheJson() throws HubCitiException;
}
