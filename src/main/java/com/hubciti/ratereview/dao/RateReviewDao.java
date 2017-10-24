package com.hubciti.ratereview.dao;

import java.util.ArrayList;
import java.util.List;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.ProductReview;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ShareProductInfo;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.pojos.UserRatingInfo;
import com.hubciti.common.pojos.UserTrackingData;

public interface RateReviewDao {
	/**
	 * This DAO method for fetches user product rating information.
	 * 
	 * @param ProductDetailsRequest
	 *            as productDetReq.
	 * @return UserRatingInfo details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	UserRatingInfo fetchUserProductRating(ProductDetailsRequest productDetReq) throws HubCitiException;

	/**
	 * The DAO method for fetching product reviews.
	 * 
	 * @param userId
	 *            as a request parameter
	 * @param productId
	 *            as a request parameter
	 * @return ProductReview list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ArrayList<ProductReview> getProductReviews(Integer userId, Integer productId) throws HubCitiException;

	/**
	 * This method fetches user product rating information.
	 * 
	 * @param userId
	 *            as request parameter.
	 * @param productId
	 *            as request parameter.
	 * @return xml containing user,average product ratings.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	UserRatingInfo fecthUserProductRating(Integer userId, Integer productId, Integer hubCitiId) throws HubCitiException;

	/**
	 * This method for saving user product rating information.
	 * 
	 * @param userRatingInfo
	 *            as the request parameter.
	 * @return xml containing success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs..
	 */
	String saveUserProductRating(UserRatingInfo userRatingInfo) throws HubCitiException;

	/**
	 * The DAO method for fetching product information.
	 * 
	 * @param shareProductInfo
	 *            as parameter containing userId and productId.
	 * @return ProductDetail object.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ProductDetail getProductInfo(ShareProductInfo shareProductInfo) throws HubCitiException;

	/**
	 * The DAO method for fetching Retailer information.
	 * 
	 * @param shareProductInfo
	 *            as parameter containing userId and productId.
	 * @return RetailerDetail object.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	RetailerDetail getRetailerInfoWithSalePrice( ShareProductInfo shareProductInfo) throws HubCitiException;

	/**
	 * The DAO method Fetching App Configuration details.
	 * 
	 * @return ArrayList.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ArrayList<AppConfiguration> getAppConfig(String configType) throws HubCitiException;

	/**
	 * The DAO method for fetching share hot deal information
	 * 
	 * @param hotdealId
	 *            ,hubCitiId as request parameter.
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
/*	List<HotDealsDetails> getShareHotdealInfo(Integer hotdealId, Integer hubCitiId) throws HubCitiException;*/

	/**
	 * The DAO method for fetching share coupon information
	 * 
	 * @param couponId
	 *            , hubCitiId as request parameter.
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	/*List<CouponDetails> getShareCouponInfo(Integer couponId, Integer hubCitiId) throws HubCitiException;*/

	/**
	 * The DAo method for fetching product information.
	 * 
	 * @param shareProductInfo
	 *            as request parameter
	 * @param retailerDetail
	 *            as request parameter
	 * @param userId
	 *            as request parameter
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<ProductDetail> getProductInfo(ShareProductInfo shareProductInfo, RetailerDetail retailerDetail, int userId, String shareURL) throws HubCitiException;

	/**
	 * The DAo method for fetching user information for sending mail to share.
	 * product information
	 * 
	 * @param shareProductInfo
	 *            as parameter.
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getUserInfo(ShareProductInfo shareProductInfo) throws HubCitiException;


	/**
	 * The DAO method for adding share app site by email details to database.
	 * 
	 * @param objShareProductInfo
	 * @return String message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<RetailersDetails> shareAppSiteByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException;

	/**
	 * The DAO method when the user clicks Share, they will be prompted to share
	 * via different options Text/Twitter/Facebook will send wording, retailer
	 * name, and link to the AppSite information.
	 * 
	 * @param retailerDetailObj
	 *            as request parameter contains retailer page id,retailer id
	 *            ,location id and user id
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<RetailersDetails> getShareAppsite(ShareProductInfo objShareProduct) throws HubCitiException;

	/**
	 * DAO method to get app download link
	 * 
	 * @param instance of ShareProductInfo.
	 * @return instance of UserDetails.
	 * @throws HubCitiException
	 */
	UserDetails shareAppDownloadLink(ShareProductInfo objShareDetails) throws HubCitiException;
		
	/**
	 * The DAO method for fetching special offer information via  Facebook, Text, Twitter.
	 * 
	 * @param instance of ShareProductInfo.
	 * @return instance of RetailersDetails.
	 * @throws HubCitiException throws if exception occurs.
	 */


	RetailersDetails shareSpecialOffer(ShareProductInfo shareProductInfo) throws HubCitiException;
	
	/**
	 * The DAO method for fetching share hot deal information via Email.
	 * 
	 * @param instance of ShareProductInfo.
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	List<RetailersDetails> shareSpecialOffByEmail(ShareProductInfo shareProductInfo) throws HubCitiException;
	
	
	/**
	 * The DAO method for adding share Event by email details to database.
	 * 
	 * @param objShareProductInfo
	 * @return EventDetails.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	EventDetails shareEventByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException;
	
	/**
	 * The DAO method for adding share Fundraiser details to database.
	 * 
	 * @param objShareProductInfo
	 * @return EventDetails.
	 * @throws HubCitiException throws if exception occurs.
	 */
	Fundraiser shareFundraiserByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException;
	
	/**
	 * The DAOImpl method fetches share hot deal details via Email.
	 * 
	 * @param instance of ShareProductInfo.
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	List<HotDealsDetails> shareHotdealByEmail(ShareProductInfo shareProductInfo) throws HubCitiException;
		
	/**
	 * The DAO method for fetching share hot deal information via Facebook, Text, Twitter.
	 * 
	 * @param instance of ShareProductInfo.
	 * @param instance of HotDealsDetails.
	 * @throws HubCitiException throws if exception occurs.
	 */
	HotDealsDetails shareHotdeal(ShareProductInfo shareProductInfo) throws HubCitiException;
	
	/**
	 * The DAO method for fetching share coupon information via Email.
	 * 
	 * @param shareProductInfo as request parameter.
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	List<CouponDetails> shareCouponByEmail(ShareProductInfo shareProductInfo) throws HubCitiException;
	
	/**
	 * The DAO method for fetching share Coupon information via Facebook, Text, Twitter.
	 * 
	 * @param instance of ShareProductInfo.
	 * @param instance of CouponDetails.
	 * @throws HubCitiException throws if exception occurs.
	 */
	CouponDetails shareCoupon(ShareProductInfo shareProductInfo) throws HubCitiException;
	
	/**
	 * The DAO method to update receiver share information type details to the database.
	 * 
	 * @param instance of ShareProductInfo.
	 * @return SUCCESS or FAILURE string.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String updateReceiverShare(ShareProductInfo shareProductInfo) throws HubCitiException;
	
	/**
	 * The DAO method to get share types with its ID and name from database.
	 * 
	 * @return list containing shareTypeID and shareTypeName.
	 * @throws HubCitiException  throws if exception occurs.
	 */
	List<UserTrackingData> getShareTypes() throws HubCitiException;
	
	/**
	 * The DAO method to update sender share information type details to the database.
	 * 
	 * @param instance of UserTrackingData.
	 * @return SUCCESS or FAILURE string.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String updateSenderShare(UserTrackingData objUserTrackingData) throws HubCitiException;
	
	/**
	 * Band Event List
	 * The DAO method for adding share Event by email details to database.
	 * 
	 * @param objShareProductInfo
	 * @return EventDetails.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	EventDetails shareBandEventByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException;

	/**
	 * The DAO method for adding share Band by email details to database.
	 * 
	 * @param objShareProductInfo
	 * @return String message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<RetailersDetails> shareBandByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException;
	
	/**
	 * The DAO method when the user clicks Share, they will be prompted to share
	 * via different options Text/Twitter/Facebook will send wording, retailer
	 * name, and link to the AppSite information.
	 * 
	 * @param retailerDetailObj
	 *            as request parameter contains retailer page id,Band Id and user id
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<RetailersDetails> shareBand(ShareProductInfo objShareProduct) throws HubCitiException;
	
	

	/**
	 * The DAO method for adding share News by email details to database.
	 * 
	 * @param shareNewsInfo
	 * @return String message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<Item> shareNewsByEmail(ShareProductInfo shareNewsInfo) throws HubCitiException;

	/**
	 * The DAO method when the user clicks Share, they will be prompted to share
	 * via different options Text/Twitter/Facebook will send wording.
	 * 
	 * @param newsID
	 * @return XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<Item> shareNewsBySocailMedia(ShareProductInfo shareNewsInfo) throws HubCitiException;
	
}


