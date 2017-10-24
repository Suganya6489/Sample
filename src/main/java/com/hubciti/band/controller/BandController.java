package com.hubciti.band.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hubciti.common.constants.HubCitiURLPath;

/**
 * Class for BandController
 * 
 * @author Kumar D
 */
@Path(HubCitiURLPath.BAND)
public interface BandController {

  	/**
	 * This Controller method is for fetching EventType List information. 
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.GETEVENT_TYPES)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getAllEventTypes(String strJSON);
	
	
  	/**
	 * This Controller method is for fetching Band List information. 
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.ALL_BAND)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getBandList(String strJSON);
	
	
	/**
	 * This Controller method fetches Band information.
	 * 
	 * @param strJSON
	 */
	@POST
	@Path(HubCitiURLPath.BAND_SUMMARY)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getBandSummary(String strJSON);
	
	/**
	 * This controller method for Non Location Event List
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
	 * This controller method for displaying News BookMark and Non BookMark catagories.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.NEWS_BOOKMARK_CATAGORIES)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getNewsBookMarkCategories(String strJSON);
	
	
	/**
	 * This controller method for Updating News BookMark and HubCiti Functionality.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.UPDATE_BOOKMARK_CATAGORIES)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String updateNewsBookMarkCategories(String strJSON);
	
	/**
	 * This controller method for get News detail from news ID.
	 * Type:GET
	 * 
	 * @param newsID as request
	 */
	@GET
	@Path(HubCitiURLPath.GET_NEWS_DETAIL)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getNewsDetail(@QueryParam("newsID") Integer newsID);
	
	
	/**
	 * This controller method for displaying both HubCiti Functionality and News categories.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.GET_NEWS_SIDE_NAVIGATION_MENU)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getNewsSideNavigationMenu(String strJSON);
	
	
  	/**
	 * This Controller method is for fetching News List by Category's information. 
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.NEWS_BY_CATEGORY)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getNewsListByCategories(String strJSON);
	
  	/**
	 * This Controller method is for fetching Combinational News List by Category's information. 
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.COMBINATION_TEMPLATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getCombNewsListByCategories(String strJSON);
	
  	/**
	 * This Controller method is for fetching Scrolling News List by Category's information. 
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.SCROLLING_TEMPLATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getScrollingNewsListByCategories(String strJSON);
	
	
  	/**
	 * This Controller method is for displaying all News  Category's.
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.ALL_NEWS_CATEGORY)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getAllNewsCategories(String strJSON);
	
	/**
	 * This controller method for displaying News BookMark and HubCiti Functionality.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.GET_SIDE_NAVIGTN_NEWS_HUBCITI)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getNewsBookMarkAndHubCitiFunctn(String strJSON);
	
	/**
	 * This controller method for displaying All News Sub categories.
	 * Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.GET_SUB_CATAGORIES)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getAllNewsSubCategoriesByCatID(String strJSON);
	
	
	
  	/**
	 * This Controller method is for fetching Block News List by Category's information. 
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.BLOCK_TEMPLATE)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getBlockNewsListByCategories(String strJSON);
	
	
  	/**
	 * This Controller method is for fetching Event Band List Information. 
	 * Method Type:POST
	 * 
	 * @param strJSON as request
	 */
	@POST
	@Path(HubCitiURLPath.MULTIPLE_BANDS_EVENT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getEventBandsList(String strJSON);
	
	
}
