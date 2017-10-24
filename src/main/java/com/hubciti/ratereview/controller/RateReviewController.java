package com.hubciti.ratereview.controller;

import static com.hubciti.common.constants.HubCitiURLPath.RATEREVIEWBASEURL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.hubciti.common.constants.HubCitiURLPath;

@Path(RATEREVIEWBASEURL)
public interface RateReviewController {

	/**
	 * This Controller method for fetching User product rating. Method Type:
	 * POST
	 * 
	 * @param xml
	 *            as request parameter.
	 * @return XML containing productId,current rating and average rating.
	 */
	@POST
	@Path(HubCitiURLPath.FETCHUSERRATING)
	@Produces("text/xml")
	@Consumes("text/xml")
	String fetchUserProductRating(String xml);


	/**
	 * This Controller method to saving User product rating for the given
	 * userId,productId. Method Type: POST
	 * 
	 * @param xml
	 *            as request parameter. If userId or productId is null then it
	 *            is invalid request.
	 * @return XML containing success or failure response
	 */
	@POST
	@Path(HubCitiURLPath.SAVEUSERRATING)
	@Produces("text/xml")
	@Consumes("text/xml")
	String saveUserProductRating(String xml);

	/**
	 * This Controller method to used to share product information from This
	 * Location and other modules. Retailer Information is also shared if
	 * product is shared from This Location module. Method Type: POST
	 * 
	 * @param xml
	 *            contains information about the Product to be shared.
	 * @return XML in the response containing product and retailer information.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREPRODUCTRETAILERINFO)
	@Produces("text/xml")
	@Consumes("text/xml")
	String shareProductRetailerInfo(String xml);

	/**
	 * This Controller method to share hot deal or coupon information from
	 * modules.
	 * 
	 * @param xml
	 *            contains information about the hot deal to be shared.
	 * @return XML in the response containing hot deal or coupon information.
	 */

	/**
	 * This Controller method to share product information via Email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains information about the Product to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREPRODUCTINFO)
	@Produces("text/xml")
	@Consumes("text/xml")
	String shareProductInfo(String xml);

	/**
	 * This Controller method used to share app site via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, share type, user ID, retailer ID
	 *            and retailer location ID
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREAPPSITEEMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareAppSiteByEmail(String xml);

	/**
	 * This Controller method used to share app site. Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML containing retailer details.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREAPPSITE)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareAppsite(String xml);

	/**
	 * This Controller method used to share retailer special offer information
	 * via Email.Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHARESPECIALOFFEMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareSpecialOffByEmail(String xml);

	/**
	 * This Controller method used to share retailer special offer information
	 * via Facebook, Text, Twitter .Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHARESPECIALOFF)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareSpecialOffer(String xml);


	/**
	 * This Controller method used to share app download link. via Email.Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREAPPLINK)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareAppDownloadLink(String xml);


	/**
	 * This Controller method used to share event via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, user ID, event ID
	 *            and hubciti ID.
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREEVENTEMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareEventByEmail(String xml);

	/**
	 * This Controller method used to share event via facebook, twitter and sms. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination user ID, event ID
	 *            and hubciti ID.
	 * @return XML as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREEVENT)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareEvent(String xml);


	/**
	 * This Controller method used to share Fundraiser via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, user ID, Fundraiser ID
	 *            and hubciti ID.
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREFUNDRAISEREMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareFundraiserByEmail(String xml);

	/**
	 * This Controller method used to share Fundraiser via facebook, twitter and sms. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination user ID, Fundraiser ID
	 *            and hubciti ID.
	 * @return XML as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREFUNDRAISERE)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareFundraiser(String xml);	


	/**
	 * This Controller method to share hot deal Via Email.
	 * Method Type:POST.
	 * @param xml
	 *            contains information about the hot deal to be shared.
	 * @return XML in the response containing hot deal information.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREHOTDEALBYEMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareHotdealByEmail(String xml);


	/**
	 * This Controller method to share hot deal information via Facebook, Text, Twitter.
	 * Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the hot deal to be shared.
	 * @return XML in the response containing hot deal information.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREHOTDEAL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareHotdeal(String xml);


	/**
	 * This Controller method to share Coupon Via Email.
	 * Method Type:POST.
	 * @param xml
	 *            contains information about the Coupon to be shared.
	 * @return XML in the response containing Coupon information.
	 */
	@POST
	@Path(HubCitiURLPath.SHARECOUPONBYEMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareCouponByEmail(String xml);


	/**
	 * This Controller method to share Coupon information via Facebook, Text, Twitter.
	 * Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the Coupon to be shared.
	 * @return XML in the response containing Coupon information.
	 */
	@POST
	@Path(HubCitiURLPath.SHARECOUPON)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareCoupon(String xml);


	/**
	 * This Controller method to update receiver share type. 
	 * Method Type: POST
	 * 
	 * @param xml as request parameter.
	 */
	@POST
	@Path(HubCitiURLPath.RECEIVERSHARETRACKING)
	@Produces("text/xml")
	@Consumes("text/xml")
	void updateReceiverShare(String xml);

	/**
	 * This Controller method to update sender share type. 
	 * Method Type: POST
	 * 
	 * @param xml as request parameter.
	 */
	@POST
	@Path(HubCitiURLPath.SENDERSHARETRACKING)
	@Produces("text/xml")
	@Consumes("text/xml")
	void updateSenderShare(String xml);
	
	/**
	 * This Controller method used to share event via email. Method
	 * 
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, user ID, event ID
	 *            and hubciti ID.
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREBANDEVENTEMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareBandEventByEmail(String xml);
	
	/**
	 * This Controller method used to share event via facebook, twitter and sms. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination user ID, event ID
	 *            and hubciti ID.
	 * @return XML as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREBANDEVENT)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareBandEvent(String xml);
	
	/**
	 * This Controller method used to share Band via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, share type, user ID, retailer ID
	 *            and retailer location ID
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREBANDEMAIL)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareBandByEmail(String xml);

	/**
	 * This Controller method used to share Band. Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML containing retailer details.
	 */
	@POST
	@Path(HubCitiURLPath.SHAREBAND)
	@Consumes("text/xml")
	@Produces("text/xml")
	String shareBand(String xml);
	
	/**
	 * This Controller method used to share News via email. 
	 * Method Type:POST.
	 * 
	 * @param newsID
	 * @return XML success or failure as response.
	 */
	@POST
	@Path(HubCitiURLPath.SHARE_NEWS_EMAIL)
	@Produces("text/xml")
	String shareNewsByEmail(String xml);

	/**
	 * This Controller method used to share News. Method Type:POST.
	 * 
	 * @param newsID.
	 * @return XML containing News detail.
	 */
	@POST
	@Path(HubCitiURLPath.SHARE_NEWS_MEDIA)
	@Produces("text/xml;charset=UTF-8")
	String shareNewsBySocailMedia(String xml);
}
