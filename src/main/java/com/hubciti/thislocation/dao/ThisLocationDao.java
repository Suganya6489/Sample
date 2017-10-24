package com.hubciti.thislocation.dao;

import java.util.ArrayList;
import java.util.List;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.CitiExperience;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.CustomerInfo;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.RetailerCreatedPages;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;
import com.hubciti.common.pojos.UserTrackingData;

public interface ThisLocationDao {

	/**
	 * DAO method to fetches the partners from the database for the given
	 * location.
	 * 
	 * @param iCitiExpID
	 * @return returns CitiExperience object container List of partners.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CitiExperience getPartners(Integer iCitiExpID,  Integer userId,Integer bottomBtnId,Integer menuItemId) throws HubCitiException;

	/**
	 * DAO method to fetches the partner retailers from the database for the
	 * given location.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	RetailersDetails getRetailersForPartner(ThisLocationRequest thisLocationReq, String screenName) throws HubCitiException;

	/**
	 * DAO method to fetches the City Experience retailers from the database for
	 * the given location.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	RetailersDetails getRetailersForCitiExpirence(ThisLocationRequest thisLocationReq, String screenName) throws HubCitiException;

	List<RetailerDetail> getRetailerSummary(RetailerDetail objRetailerDetail) throws HubCitiException;

	List<RetailerCreatedPages> getRetCreatePages(RetailerDetail objRetailerDetail) throws HubCitiException;

	/**
	 * The method fetches the categories for partner retailers from the database
	 * for the given location.
	 * 
	 * @param objThisLocationReq
	 *            .
	 * @return returns CategoryInfo object container List of categories.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<CategoryInfo> getCategoriesForPartners(ThisLocationRequest objThisLocationReq) throws HubCitiException;

	/**
	 * The method fetches the categories for group retailers from the database
	 * for the given location.
	 * 
	 * @param objThisLocationReq
	 *            .
	 * @return returns CategoryInfo object container List of categories.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<CategoryInfo> getCategoriesForGroupRetailers(ThisLocationRequest objThisLocationReq) throws HubCitiException;

	/**
	 * DAO Method list all see current specials details .
	 * 
	 * @param productDetailsRequest instance of ProductDetailsRequest.
	 * @return ProductDetails details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	ProductDetails getRetailerSpecialDeals(ProductDetailsRequest productDetailsRequest) throws HubCitiException;

	/**
	 * This DAO method for fetching retailer location hot deals.
	 * 
	 * @param xml
	 *            as the request object containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response object or exception.
	 */
	List<HotDealsDetails> getRetailerHotDeals(RetailerDetail retailerDetailObj) throws HubCitiException;

	List<CouponDetails> getRetailerLocationCoupon(RetailerDetail retailerDetailObj) throws HubCitiException;

	/**
	 * The DAO method fetches the Product Details.
	 * 
	 * @param productDetailsRequest
	 *            Instance of ProductDetailsRequest.
	 * @param screenName
	 *            screenName parameter constant used for Pagination.
	 * @return ProductDetails returns list of Products.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ProductDetails fetchProductDetails(ProductDetailsRequest productDetailsRequest, String screenName) throws HubCitiException;

	/**
	 * This DAO method for fetching special offer display.
	 * 
	 * @param specialOfferRequest
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	RetailersDetails fetchSpecialOffers(RetailerDetail specialOfferRequest) throws HubCitiException;

	/**
	 * This DAO method for fetching retailer Special Offer Details list.
	 * 
	 * @param specialOfferRequest
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<RetailerDetail> fetchSpecialDealsDetails(RetailerDetail specialOfferRequest) throws HubCitiException;

	/**
	 * This DAO method for display retailer summary anything page details.
	 * 
	 * @param objAnyThing
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<RetailerDetail> getRetSumAnyThingDetails(RetailerDetail objAnyThing) throws HubCitiException;

	/**
	 * For user tracking. To be called whenever user taps on any of the item in
	 * the Retailer summary screen.
	 * 
	 * @param userTrackingData
	 * @return xml response containing SUCCESS or FAILURE.
	 * @throws ScanSeeException
	 */
	String userTrackingRetailerSummaryClick(UserTrackingData userTrackingData) throws HubCitiException;

	/**
	 * This DAO method for display Coupon details.
	 * 
	 * @param objCouponDetail
	 *            instance of RetailerDetail.
	 * @return CouponsDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CouponDetails fetchCouponDetails(RetailerDetail objCouponDetail) throws HubCitiException;

	/**
	 * This DAO method for display HubCiti anything page details.
	 * 
	 * @param objAnyThing
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<RetailerDetail> getHubCitiAnyThingDetails(RetailerDetail objAnyThing) throws HubCitiException;

	/**
	 * This method is used to fetch the Latitude and Longitude for the given
	 * Zipcode.
	 * 
	 * @param zipcode
	 *            Zipcode for getting Lat and Long.
	 * @return ThisLocationRequest Container lat and long.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	ThisLocationRequest fetchLatLong(Long zipcode) throws HubCitiException;

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
	RetailersDetails fetchRetailerDetails(ThisLocationRequest thisLocationRequest, String screenName) throws HubCitiException;

	/**
	 * The DAO method to fetch App site details from database.
	 * 
	 * @param retailerDetailObj
	 * @return List<RetailerDetail> object.
	 * @throws HubCitiException
	 */
	List<RetailerDetail> getAppSiteDetails(RetailerDetail retailerDetailObj) throws HubCitiException;

	/**
	 * The DAO method fetches Location details from the database for the given
	 * user.
	 * 
	 * @param userID
	 *            The userID in the request.
	 * @return ThisLocationRequest info contains Latitude and Longitude.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ThisLocationRequest fetchUserLocationDetails(Integer userID) throws HubCitiException;

	/**
	 * The DAO method for update user zip code information .
	 * 
	 * @param userId
	 * @param zipcode
	 * @return String with success or failure response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String updateZipcode(Long userId, String zipcode, Integer hubCitiId) throws HubCitiException;
	
	/**
	 * The DAO method to get All Retailer Location List
	 * 
	 * @param requestRetailer
	 * @return returns retailer Location List
	 * @throws HubCitiException
	 * 			throws Exception
	 */
	RetailerDetail getSpecialOfferRetLocList (RetailerDetail requestRetailer) throws HubCitiException;
		
	/**
	 * The DAO method to get Special Offer Details
	 * 
	 * @param request includes retailer details
	 * @return returns special offer details
	 * @throws HubCitiException
	 * 			throws Exception
	 */
	RetailerDetail getSpecialOfferDetails(RetailerDetail requestretailerDetails) throws HubCitiException;
	
	/**
	 * DAO method Fetching App claim Configuration details.
	 * 
	 * @return SMTP details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ArrayList<AppConfiguration> getClaimAppConfig(String configType, Integer hubCitiId) throws HubCitiException;
	
	/**
	 * The DAO method fetches Claim Location details from the database for the given user.
	 * 
	 * @param retLocationId
	 *            The retLocationId in the request.
	 * @param hubCitiId
	 *            The hubCitiId in the request.
	 * @return RetailerDetail info contains Claim info.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	RetailerDetail getClaimBusinessInfo (Integer retLocationId,  Integer hubCitiId) throws HubCitiException;
	
	/**
	 * The DAO method fetches Claim Location details from the database for the given user.
	 * 
     * @param objCustomerInfo
     *            Instance of type CustomerInfo.
	 * @return CustomerInfo info contains Claim info.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CustomerInfo getClaimYourBusinessdetails (CustomerInfo objCustomerInfo) throws HubCitiException;
	
	/**
	 * The ServiceImpl method for displaying  special offer list for location's.
	 * 
	 * @param objRetDetail input
	 */
	RetailersDetails getSpecialOfferLocationListJson (RetailerDetail objRetDetail) throws HubCitiException;
	
	/**
	 * The ServiceImpl method for displaying  special offer list for map location's.
	 * 
	 * @param objRetDetail input
	 */
	RetailersDetails getSpecialOfferLocationMapListJson (RetailerDetail objRetDetail) throws HubCitiException;
}
