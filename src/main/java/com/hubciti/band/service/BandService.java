package com.hubciti.band.service;

import com.hubciti.common.exception.HubCitiException;

public interface BandService {

	/**
	 * Service method is displaying Event Type List . 
	 * 
	 * @param strJSON as request
	 */
	String getAllEventTypes(String strJSON) throws HubCitiException;

	/**
	 * Service method is displaying Band List . 
	 * 
	 * @param strJSON as request
	 */
	String getBandList(String strJSON) throws HubCitiException;


	/**
	 * This Service method fetches Band information.
	 * @param strJson
	 * @return String Json with Band details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getBandSummary(String strJson) throws HubCitiException;

	/**
	 * Service method method for Band Event Listing on Event Listing.
	 * @param strJSON as request
	 */
	String getEventList(String strJSON) throws HubCitiException;

	/**
	 * Service method for displaying News BookMark and Non BookMark catagories.
	 * 
	 * @param strJSON as request
	 */
	String getNewsBookMarkCategories(String strJSON) throws HubCitiException;

	/**
	 * Service method for Updating News BookMark and HubCiti Functionality.
	 * 
	 * @param strJSON as request
	 */
	String updateNewsBookMarkCategories(String strJSON) throws HubCitiException;

	/**
	 * Service method for get News detail from news ID.
	 * 
	 * @param newsID as request
	 */
	String getNewsDetail(Integer newsID) throws HubCitiException;

	/**
	 * Service method for displaying both HubCiti Functionality and News categories.
	 * 
	 * @param strJSON as request
	 */
	String getNewsSideNavigationMenu(String strJSON) throws HubCitiException;

	/**
	 * Service method for fetching News List by Category's. 
	 * 
	 * @param strJSON as request
	 */
	String getNewsListByCategories(String strJSON) throws HubCitiException;

	/**
	 * Service method for fetching Combination pattern News List by Category's information. 
	 * 
	 * @param strJSON as request
	 */
	String getCombNewsListByCategories(String strJSON) throws HubCitiException;

	/**
	 * Service method for fetching Scrolling pattern News List by Category's information. 
	 * 
	 * @param strJSON as request
	 */
	String getScrollingNewsListByCategories(String strJSON) throws HubCitiException;

	/**
	 * Service method for displaying all News  Category's. 
	 * 
	 * @param strJSON as request
	 */
	String getAllNewsCategories(String strJSON) throws HubCitiException;

	/**
	 * Service method for displaying News BookMark and HubCiti Functionality.
	 * 
	 * @param strJSON as request
	 */
	String getNewsBookMarkAndHubCitiFunctn(String strJSON) throws HubCitiException;
	
	/**
	 * Service method for displaying All News Sub categories.
	 * 
	 * @param strJSON as request
	 */
	String getAllNewsSubCategoriesByCatID(String strJSON) throws HubCitiException;
	
	/**
	 * Service method for fetching Block Pattern News List by Category's information. 
	 * 
	 * @param strJSON as request
	 */
	String getBlockNewsListByCategories(String strJSON) throws HubCitiException;
	
	/**
	 * Service method is for fetching Event Band List Information . 
	 * 
	 * @param strJSON as request
	 */
	String getEventBandsList(String strJSON) throws HubCitiException;

}
