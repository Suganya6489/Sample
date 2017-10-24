package com.hubciti.band.dao;

import java.util.List;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.Menu;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.News;
import com.hubciti.common.pojos.RetailerCreatedPages;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;

public interface BandDao {


	/**
	 * The DAO method fetches Category Details. no Query parameter
	 * @param instance of MenuItem.
	 */
	Categories getAllEventTypes(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * The DAO method fetches Band list details
	 * @param instance of RetailerDetail.
	 */
	RetailersDetails getBandList(RetailerDetail retailDetail) throws HubCitiException;


	/**
	 * The DAO method for fetching Band details information
	 * @param objBandDetail
	 */
	List <RetailerDetail> getBandSummary(RetailerDetail retail) throws HubCitiException;

	/**
	 * The DAO method fetches Band Anything pages details.
	 * @param objBandDetail
	 */
	List<RetailerCreatedPages> getBandCreatePages(RetailerDetail objBandDetail) throws HubCitiException;

	/**
	 * Method to fetch event list from database.
	 * 
	 * @param objMenuItem
	 * @return {@link EventDetails} object.
	 * @throws HubCitiException
	 */
	EventDetails getEventList(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * DAO method for displaying News BookMark and Non BookMark catagories.
	 * 
	 * @param objNews
	 */
	CategoryDetails getNewsBookMarkCategories(News objNews) throws HubCitiException;

	/**
	 * DAO method for Updating News BookMark and HubCiti Functionality.
	 * 
	 */
	String updateNewsBookMarkCategories(News objNews) throws HubCitiException;

	/**
	 * DAO method for get News detail from news ID.
	 * 
	 * @param newsID
	 */
	List<Item> getNewsDetail(Integer newsID) throws HubCitiException;

	/**
	 * DAO method for displaying both Hubciti Functionality and News catagories.
	 * 
	 * @param objMenu
	 */
	CategoryDetails getNewsSideNavigationMenu(Menu objMenu) throws HubCitiException;


	/**
	 * The DAO method for fetching News List by Category's. 
	 * @param instance of objItem.
	 */
	Item getNewsListByCategories(Item objItem) throws HubCitiException;

	/**
	 * The DAO method for fetching Combinational News  List by Category's information. 
	 * @param instance of objItem.
	 */
	Item getCombNewsListByCategories(Item objItem) throws HubCitiException;

	/**
	 * The DAO method for fetching Scrolling News  List by Category's information. 
	 * @param instance of objItem.
	 */
	Item getScrollingNewsListByCategories(Item objItem) throws HubCitiException;

	/**
	 * The DAO method for displaying all News  Category's
	 * @param instance of News.
	 */
	CategoryDetails getAllNewsCategories(News objNews) throws HubCitiException;
	
	/**
	 * DAO method for displaying News BookMark and HubCiti Functionality.
	 * 
	 * @param instance of News.
	 */
	CategoryDetails getNewsBookMarkAndHubCitiFunctn(News objNews) throws HubCitiException;


	/**
	 * DAO method for displaying All News Sub categories.
	 * 
	 * @param userId
	 * @param catName
	 */
	List<CategoryDetails> getAllNewsSubCategoriesByCatID(Integer userId, String catName) throws HubCitiException;
	

	/**
	 * The DAO method for fetching Scrolling News  List by Category's information. 
	 * @param instance of objItem.
	 */
	Item getBlockNewsListByCategories(Item objItem) throws HubCitiException;
	
	/**
	 * The DAO method is for fetching Event Band List Information details
	 * @param instance of RetailerDetail.
	 */
	RetailersDetails getEventBandsList(RetailerDetail retailDetail) throws HubCitiException;
}
