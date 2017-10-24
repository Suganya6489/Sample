package com.hubciti.ratereview.service;

import com.hubciti.common.exception.HubCitiException;

public interface RateReviewService {

	/**
	 * This Service method for fetching user product rating information.
	 * 
	 * @param ProductDetailsRequest
	 *            as productDetReq.
	 * @return user rating,average rating information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String fetchUserProductRating(String xml) throws HubCitiException;

	/**
	 * This Service method for Saving user product rating information.
	 * 
	 * @param xml
	 *            as the request parameter.It contains userId,productId and
	 *            rating information.
	 * @return xml containing success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	String saveUserProductRating(String xml) throws HubCitiException;

	/**
	 * This Service method for fetches product and retailer information.
	 * 
	 * @param xml
	 *            contains information to fetch product and retailer details.
	 * @return String XML with Product and Retailer Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareProductRetailerInfo(String xml) throws HubCitiException;

	/**
	 * This Service method for fetches hot deal information.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal details.
	 * @return String XML with hot deal Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
/*	String shareHotdealInfo(Integer hotdealId, Integer hubCitiId) throws HubCitiException;*/

	/**
	 * This Service method for validates the input parameters and returns
	 * validation error if validation fails. This Method fetches coupon
	 * information.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal details.
	 * @return String XML with hot deal Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	/*String shareCLRInfo(Integer couponId, Integer hubCitiId) throws HubCitiException;*/

	/**
	 * This Service method for validates the input parameters and returns
	 * validation error if validation fails. Sends email to recipients with
	 * product information.
	 * 
	 * @param xml
	 *            contains email id, product to be shared.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareProductInfo(String xml) throws HubCitiException;

	/**
	 * This Service method fetches coupon information via Email.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal details.
	 * @return String XML with hot deal Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
/*	String shareCLRbyEmail(String xml) throws HubCitiException;*/

	/**
	 * This Service method Shares app site information via email.
	 * 
	 * @param xml
	 *            contains recipient email ID, share type, user ID, retailer ID
	 *            and retailer location ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareAppSiteByEmail(String xml) throws HubCitiException;

	/**
	 * This Service method Shares app site information via email. This method
	 * shareType, userId, retailerId, retailerLocationId information.
	 * 
	 * @param xml
	 *            contains qrUrl,retailerAddress,retImage,shareText to be
	 *            shared.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareAppsite(String xml) throws HubCitiException;


	/**
	 * This Service method to share app download link by email, facebook,
	 * twitter, sms
	 * 
	 * @param xml
	 *            contains email id,special offers to be shared.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareAppDownloadLink(String xml) throws HubCitiException;
	
	/**
	 * This Service method Sends email to recipients with retailer special offer
	 * information via Email.
	 * 
	 * @param xml
	 *            contains email id,special offers to be shared.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareSpecialOffByEmail(String xml) throws HubCitiException;
	
	/**
	 * This Service method Sends  retailer special offer
	 * information via Facebook, Text, Twitter.
	 * 
	 * @param xml
	 *            contains special offers to be shared.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareSpecialOffer(String xml) throws HubCitiException;


	/**
	 * This Service method Shares event information via email.
	 * 
	 * @param xml
	 *             contains destination email, user ID, event ID
	 *			   and hubciti ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareEventByEmail(String xml) throws HubCitiException;
	/**
	 * This Service method Shares event information via via facebook, twitter and sms.
	 * 
	 * @param xml
	 *             contains destination user ID, event ID
	 *			   and hubciti ID.
	 * @return XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareEvent(String xml) throws HubCitiException;

	/**
	 * This Service method Shares Fundraiser information via email.
	 * 
	 * @param xml
	 *             contains destination email, user ID, event ID
	 *			   and hubciti ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareFundraiserByEmail(String xml) throws HubCitiException;
	/**
	 * This Service method Shares Fundraiser information via via facebook, twitter and sms.
	 * 
	 * @param xml
	 *             contains destination user ID, event ID
	 *			   and hubciti ID.
	 * @return XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareFundraiser(String xml) throws HubCitiException;

	/**
	 * This Service method for fetches hot deal information via Email.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal details.
	 * @return String XML with hot deal Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareHotdealByEmail(String xml) throws HubCitiException;
	
	/**
	 * This Service method for fetches hot deal information information via Facebook, Text, Twitter.
	 * 
	 * @param xml
	 *            contains information to fetch hot deal details.
	 * @return String XML with hot deal Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareHotdeal(String xml) throws HubCitiException;
	
	/**
	 * This Service method for fetches Coupon information via Email.
	 * 
	 * @param xml contains information to fetch Coupon details.
	 * @return String XML with Coupon Details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String shareCouponByEmail(String xml) throws HubCitiException;
	
	/**
	 * This Service method for fetches Coupon information information via Facebook, Text, Twitter.
	 * 
	 * @param xml contains information to fetch Coupon details.
	 * @return String XML with Coupon Details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String shareCoupon(String xml) throws HubCitiException;
		
	/**
	 * This Service method to update sender share information type.
	 * 
	 * @param xml as request parameter.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String updateSenderShare(String xml) throws HubCitiException;
	
	/**
	 * This Service method to update receiver share information type.
	 * 
	 * @param xml as request parameter.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String updateReceiverShare(String xml) throws HubCitiException;
	
	/**
	 * This Service method Shares event information via email.
	 * 
	 * @param xml
	 *             contains destination email, user ID, event ID
	 *			   and hubciti ID.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareBandEventByEmail(String xml) throws HubCitiException;
	
	
	/**
	 * This Service method Shares event information via via facebook, twitter and sms.
	 * 
	 * @param xml
	 *             contains destination user ID, event ID
	 *			   and hubciti ID.
	 * @return XML.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareBandEvent(String xml) throws HubCitiException;
	
	/**
	 * This Service method Shares Band information via email.
	 * 
	 * @param xml
	 *            contains recipient email ID, share type, user ID and  Band ID
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareBandByEmail(String xml) throws HubCitiException;

	/**
	 * This Service method Shares Band information via email. This method
	 * shareType, userId, bandId.
	 * 
	 * @param xml
	 *            contains qrUrl,retailerAddress,retImage,shareText to be
	 *            shared.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareBand(String xml) throws HubCitiException;
	
	
	/**
	 * This Service method Shares News information via email.
	 * 
	 * @param newsID
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareNewsByEmail(String xml) throws HubCitiException;

	/**
	 * This Service method Shares News information via email.
	 * @param newsID
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String shareNewsBySocailMedia(String xml) throws HubCitiException;
	
}

