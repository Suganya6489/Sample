package com.hubciti.find.dao;

import java.util.ArrayList;
import java.util.List;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.CLRDetails;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.Filter;
import com.hubciti.common.pojos.FindNearByIntactResponse;
import com.hubciti.common.pojos.GoogleCategory;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.UserDetails;
import com.scansee.externalapi.common.pojos.ExternalAPISearchParameters;
import com.scansee.externalapi.common.pojos.ExternalAPIVendor;

public interface FindDao {

	/**
	 * The DAO method fetches Category Details. no Query parameter
	 * 
	 * @return HotDealsCategoryInfo.
	 * @throws ScanSeeException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	Categories getCategoryDetail(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * The DAO method fetches retailer details.
	 * 
	 * @param ServiceSerachRequest
	 *            object. .
	 * @return RetailersDetails object.
	 * @throws HubCitiException.
	 */
	RetailersDetails findScanSeeRetSearch(RetailerDetail objRetDet) throws HubCitiException;

	/**
	 * The DAO method fetches retailer details.
	 * 
	 * @param ServiceSerachRequest
	 *            object. .
	 * @return RetailersDetails object.
	 * @throws HubCitiException.
	 */
	RetailersDetails findScanSeeCategorySearch(RetailerDetail objRetDet) throws HubCitiException;

	/**
	 * Methods to get the products media info based on the media type from
	 * database. information.
	 * 
	 * @param RetailerDetail
	 *            object.
	 * @return ProductDetails onject.
	 * @throws HubCitiException.
	 */
	ProductDetails getMediaInfo(ProductDetailsRequest objProdDetail) throws HubCitiException;

	/**
	 * To update click on product media list to database for user tracking
	 * 
	 * @param pmListID
	 * @return success or failure string
	 * @throws ScanSeeException
	 */
	String userTrackingProdMediaClick(Integer pmListID) throws HubCitiException;

	/**
	 * The service method to fetch user configured radius from database.
	 * 
	 * @param userId
	 *            , module name as request parameter
	 * @return xml based on success or failure
	 * @throws ScanSeeException
	 *             for exception
	 */
	Integer getUserRadius(Integer userId, String moduleName) throws HubCitiException;

	/**
	 * methods to get the external API List.
	 * 
	 * @param moduleName
	 *            requested moduleName.
	 * @return externalAPIList
	 * @throws ScanSeeException
	 *             throws if exception occurs.
	 */
	ArrayList<ExternalAPIVendor> getExternalAPIList(String moduleName) throws HubCitiException;

	/**
	 * methods to get the external API Input parameter List.
	 * 
	 * @param apiUsageID
	 *            requested APIUsageID.
	 * @param moduleName
	 *            requested moduleName.
	 * @return externalAPIInputParameters
	 * @throws ScanSeeException
	 *             throws if exception occurs.
	 */
	ArrayList<ExternalAPISearchParameters> getExternalAPIInputParameters(Integer apiUsageID, String moduleName) throws HubCitiException;

	/**
	 * The DAO method fetches Category Details. no Query parameter
	 * 
	 * @param mainCat
	 *            as request parameter.
	 * @return HotDealsCategoryInfo.
	 * @throws ScanSeeException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	List<GoogleCategory> getAllCategories(String mainCat) throws HubCitiException;

	/**
	 * The method for fetching product details.
	 * 
	 * @param productId
	 *            request parameter
	 * @param userId
	 *            request parameter
	 * @param retailId
	 *            request parameter
	 * @return The ProductDetail Information.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	ProductDetail getProductDetail(int userId, int productId, int retailId, Integer saleListId, Integer prodListId, Integer mainMenuId,
			Integer scanId, Integer hubCitiId) throws HubCitiException;

	/**
	 * method to get the Retailers from the database based on query parameters.
	 * 
	 * @param userID
	 *            requested user.
	 * @param productId
	 *            products to be searched for.
	 * @param latitude
	 *            current location of user.
	 * @param longitude
	 *            current location of user.
	 * @param postalcode
	 *            current location of user.
	 * @param radius
	 *            search distance.
	 * @return FindNearByLowestPrice list of retailers.
	 * @throws ScanSeeException
	 *             throws if exception occurs
	 */
	FindNearByIntactResponse fetchNearByLowestPrice(int userID, Double latitude, int productId, Double longitude, String postalcode, double radius,
			Integer mainMenuId, Integer hubCitiId) throws HubCitiException;

	ArrayList<RetailerDetail> fetchCommissionJunctionData(Integer userId, Integer productId, Integer productListId, Integer mainMenuId,
			Integer hubCitiId) throws HubCitiException;

	/**
	 * methods to get product info for external API.
	 * 
	 * @param productId
	 *            requested productId.
	 * @return ProductDetail object
	 * @throws ScanSeeException
	 *             throws if exception occurs.
	 */
	ProductDetail getProductInfoforExternalAPI(Integer productId) throws HubCitiException;

	/**
	 * This method is used to insert apiurls into FindNearByLog table.
	 * 
	 * @param apiURL
	 *            -As String parameter
	 * @param userId
	 *            -As int parameter
	 * @return response as failure or success
	 * @throws ScanSeeException
	 *             throws if any exception.
	 */
	String insertExternalAPIURL(String apiURL, int userId) throws HubCitiException;

	/**
	 * The method for getting Product Attribute information.
	 * 
	 * @param userId
	 *            are the request parameters.
	 * @param productId
	 *            are the request parameters.
	 * @return The XML with fetched information.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	ProductDetail fetchProductAttributes(Integer userId, Integer productId) throws HubCitiException;

	/**
	 * This method get the Master Shopping list product CLR from the database.
	 * 
	 * @param userId
	 *            as request
	 * @param retailerId
	 *            as request
	 * @param productId
	 *            as request.
	 * @param lowerLimit
	 *            as request.
	 * @return xml String XMl with list of shopping List items for the user.
	 * @throws ScanSeeException
	 *             throws if any exception.
	 */
	CLRDetails fetchMasterSLCLRDetails(CLRDetails clrDetails) throws HubCitiException;

	/**
	 * The DAO method To update click on online products to database for user
	 * tracking
	 * 
	 * @param objAddSLRequest
	 *            instance of CLRDetails pojo class.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String userTrackingOnlineStoreClick(CLRDetails objAddSLRequest) throws HubCitiException;

	/**
	 * DAO to get retailers for Find Single Category
	 * 
	 * @param xml
	 * @return Retailers List
	 */
	RetailersDetails findSingleCategoryRetailers(RetailerDetail objRetDet) throws HubCitiException;

	/**
	 * DAO for getting sub-categories for find single category
	 * 
	 * @return category list.
	 * @throws HubCitiException
	 */
	CategoryDetails getSubCategories(UserDetails objUserDetails) throws HubCitiException;

	/**
	 * This DAO method for getting Filter List. 
	 * 
	 * @param instance of MenuItem. 
	 * @return response XML as String containing Filter list.
	 */
	Filter getFilterList(MenuItem menuItem) throws HubCitiException;

	/**
	 * This DAO method to get Sort and Filter List.  
	 * 
	 * @param instance of UserDetails. 
	 * @return response XML as String containing sort and filter list.
	 */
	ArrayList<Filter> getSortFilterList(UserDetails objUserDetails) throws HubCitiException;

	/**
	 * This DAO method for getting filter List Display
	 * @param requestFilter
	 * @return response XML as String containing Filter list.
	 * @throws HubCitiException
	 */
	Filter getInterestList(Filter requestFilter) throws HubCitiException;


	/**
	 * This DAO method for getting Option List Display
	 * @param requestFilter
	 * @return response XML as String containing Option list.
	 * @throws HubCitiException
	 */
	Filter getOptionList(Filter requestFilter) throws HubCitiException;

	/**
	 * This DAO method for getting City Experience Category List
	 * @param requestFilter
	 * @return response XML as String containing Category List
	 * @throws HubCitiException
	 */
	Filter getCategoryList(Filter requestFilter) throws HubCitiException;


}
