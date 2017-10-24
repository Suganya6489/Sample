package com.hubciti.find.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hubciti.common.constants.HubCitiURLPath;

@Path("/find")
public interface FindController {

	/**
	 * This is a RestEasy WebService Method for fetching Google categories
	 * information. Method Type:POST
	 * 
	 * @param XML
	 *            containing userId, hubCitiId, mainMenuId/(bottomBtnId,
	 *            mItemId), platform.
	 * @return XML containing success or failure in the response.
	 */
	@POST
	@Path(HubCitiURLPath.GETCATEGORY)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getCategory(String xml);

	/**
	 * This is a RestEasy WebService Method to find location details for ScanSee
	 * data given the keyword and coordinates. Method Type : POST.
	 * 
	 * @param xml
	 *            as request
	 * @return XML containing the ScanSee data search details in the response.
	 */

	@POST
	@Path(HubCitiURLPath.SSRETSEARCH)
	@Produces("text/xml")
	@Consumes("text/xml")
	String findScanSeeRetSearch(String xml);

	/**
	 * This is a Rest Easy Web service for category search for retailers given
	 * coordinates from ScanSee database
	 * 
	 * @param xml
	 * @return Retailers List
	 */
	@POST
	@Path(HubCitiURLPath.SSCATSEARCH)
	@Consumes("text/xml")
	@Produces("text/xml")
	String findScanSeeCategorySearch(String xml);

	/**
	 * This is a RestEasy WebService Method to find location details for Google
	 * data given the keyword and coordinates. Method Type : POST.
	 * 
	 * @param xml
	 *            as request
	 * @return XML containing the Google data search details in the response.
	 */
	@POST
	@Path(HubCitiURLPath.GOOGLERETSEARCH)
	@Produces("text/xml")
	@Consumes("text/xml")
	String findGoogleRetSearch(String xml);

	/**
	 * This is a Rest Easy Web service for category search for retailers given
	 * coordinates from google results
	 * 
	 * @param xml
	 * @return Retailers List
	 */
	@POST
	@Path(HubCitiURLPath.GOOGLECATSEARCH)
	@Consumes("text/xml")
	@Produces("text/xml")
	String findGoogleCategorySearch(String xml);

	/**
	 * This is a RestEasy WebService Method for displaying audio,video and other
	 * information.Method Type:GET.
	 * 
	 * @param userId
	 *            , productId, mediaType, hubCitiId
	 * @return XML containing media information as in the response.
	 */
	@POST
	@Path(HubCitiURLPath.MEDIAINFO)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getMediaInfo(String xml);

	/**
	 * This is a RestEasy WebService Method for capturing Product Media click
	 * Method Type:GET.
	 * 
	 * @param pmListID
	 * @return {@link Null} no return parameter
	 */
	@GET
	@Path(HubCitiURLPath.UTPMCLICK)
	void userTrackingProdMediaClick(@QueryParam("pmListId") Integer pmListId);

	/**
	 * This is a RestEasy WebService Method for fetching product summary details
	 * for the given input as XML. Method Type:POST.
	 * 
	 * @param xml
	 *            containing input information need to be fetched product
	 *            summary
	 * @return XML containing product summary details in the response.
	 */
	@POST
	@Path(HubCitiURLPath.GETPRODUCTSUMMARY)
	@Produces("text/xml;charset=UTF-8")
	String getProductSummary(String xml);

	/**
	 * the GET method which fetches wishList Product Attributes info.
	 * 
	 * @param userId
	 *            of the user
	 * @param productId
	 *            are in the request.
	 * @return returns response XML based on Success or Error.
	 */
	@GET
	@Path(HubCitiURLPath.FETCHPROUDCTATTRIBUTES)
	@Produces("text/xml;charset=UTF-8")
	String getProductAttributes(@QueryParam("userId") Integer userId, @QueryParam("productId") Integer productId);

	/**
	 * This is a RestEasy WebService Method for displaying coupon,rebates and
	 * loyalty information.Method Type:GET.
	 * 
	 * @param xml
	 *            needed to fetch coupon,rebates and loyalty information
	 * @return XML containing coupon,rebates and loyalty information as
	 *         response.
	 */
	@POST
	@Path(HubCitiURLPath.FETCHPRODUCTCLRINFO)
	@Produces("text/xml;charset=UTF-8")
	String fetchMSLCLRInfo(String xml);

	/**
	 * Controller method for capturing Online Product Click Method Type:POST.
	 * 
	 * @param xml
	 *            containing input information.
	 * @return the XML in response based on Success or failure code.
	 */
	@POST
	@Path(HubCitiURLPath.UTONSTOCLICK)
	@Produces("text/xml")
	@Consumes("text/xml")
	void userTrackingOnlineStoreClick(String xml);

	/**
	 * This is a Rest Easy Web service for category search for retailers given
	 * coordinates from ScanSee database
	 * 
	 * @param xml
	 * @return Retailers List
	 */
	@POST
	@Path(HubCitiURLPath.SINGLECATRETAILERS)
	@Consumes("text/xml")
	@Produces("text/xml")
	String findSingleCategoryRetailers(String xml);

	/**
	 * This Controller method for getting sub categories for find single
	 * category. Method Type: POST.
	 * 
	 * @param input
	 *            userId, hubCitiId.
	 * @return response XML as String containing category list.
	 */
	@POST
	@Path(HubCitiURLPath.SUBCATEGORY)
	@Consumes("text/xml")
	@Produces("text/xml")
	String getSubCategories(String xml);

	/**
	 * This Controller method for getting Filter List. 
	 * Method Type: POST.
	 * 
	 * @param input xml.
	 * @return response XML as String containing category list.
	 */
	@POST
	@Path(HubCitiURLPath.FILTERLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getFilterList(String xml);
	
	
	/**
	 * This Controller method for get Sort and Filter List.  
	 * Method Type: POST.
	 * 
	 * @param input xml.
	 * @return response XML as String containing category list.
	 */
	@POST
	@Path(HubCitiURLPath.SORT_AND_FILTERLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getSortFilterList(String xml);
	
	
	/**
	 *  This controller method for getting Interest list based on specified type
	 * @param xml
	 * @return
	 */
	@POST
	@Path(HubCitiURLPath.INTERESTLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getInterestList(String xml);
	
	
	/**
	 *  This controller method for getting option list based on specified type
	 * @param xml
	 * @return
	 */
	@POST
	@Path(HubCitiURLPath.OPTIONLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getOptionList(String xml);
	
	
	/**
	 *  This controller method for getting Category List
	 * @param xml
	 * @return
	 */
	@POST
	@Path(HubCitiURLPath.CATEGORYLIST)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getCategoryList(String xml);
	
	  
  	/**
	 * This controller method for fetching Google categories Method 
	 * Type:POST
	 * 
	 * @param strJSON as request
	 * @return JSON containing success or failure in the response.
	 */
	@POST
	@Path(HubCitiURLPath.GETCATEGORY_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getCategoryJson(String strJSON);
	
	
	/**
	 * This controller method for category search for retailer.
	 * 
	 * @param strJSON as request
	 * @return JSON Retailers List
	 */
	@POST
	@Path(HubCitiURLPath.SINGLECATRETAILERS_JSON)
	@Consumes("application/json")
	@Produces("application/json; charset=utf-8")
	String findSingleCategoryRetailersJson(String strJSON);
	
	/**
	 * This controller method  for find location details for ScanSee
	 * data given the keyword and coordinates. Method Type : POST.
	 * 
	 * @param strJSON
	 *            as request
	 * @return strJSON containing the ScanSee data search details in the response.
	 */

	@POST
	@Path(HubCitiURLPath.SSRETSEARCH_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String findScanSeeRetSearchJson(String strJSON);
	
	/**
	 * This controller method  for category search for retailers given
	 * coordinates from ScanSee database
	 * 
	 * @param strJSON
	 *            as request
	 * @return strJSON containing the ScanSee data search details in the response.
	 */
	@POST
	@Path(HubCitiURLPath.SSCATSEARCH_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String findScanSeeCategorySearchJson(String strJSON);
	
	/**
	 * Controller method is remove Category information from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_GETCATEGORY_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String delCategoryJson();
	
	
	/**
	 * Controller method is remove category search for retailer from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_SINGLECATRETAILERS_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String delSingleCategoryRetailersJson();
	

	/**
	 * Controller method is remove location details for ScanSee data from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_SSRETSEARCH_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String delScanSeeRetSearchJson();
	

	/**
	 * Controller method is remove category search for retailers  from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_SSCATSEARCH_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String delScanSeeCategorySearchJson();
	
	/**
	 * Controller method is remove Find All Cache from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_FIND_ALL)
	@Produces(MediaType.APPLICATION_JSON)
	String delFindAllCacheJson();
	
}
