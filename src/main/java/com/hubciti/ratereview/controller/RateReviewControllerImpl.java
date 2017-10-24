package com.hubciti.ratereview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.ratereview.service.RateReviewService;

public class RateReviewControllerImpl implements RateReviewController {
	/**
	 * Getting the logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(RateReviewControllerImpl.class);

	/**
	 * The RateReviewControllerImpl Constructor.
	 */
	public RateReviewControllerImpl() {
	}

	/**
	 * This ControllerImpl method for fetching User product rating for the given
	 * userId,productId. Method POST
	 * 
	 * @param ProductDetailsRequest
	 *            as productDetReq.
	 * @return XML containing productId,current rating and average rating
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String fetchUserProductRating(String xml) {
		LOG.info("Inside RateReviewControllerImpl : fetchhUserProductRating");
		String responseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			responseXML = rateReviewService.fetchUserProductRating(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : fetchUserProductRating : exception : " + exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}


	/**
	 * This ControllerImpl method to saving user product rating Calls method in
	 * service layer. Method Type: POST
	 * 
	 * @param xml
	 *            as request parameter. If userId or productId is null then it
	 *            is invalid request.
	 * @return XML containing success or failure response
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String saveUserProductRating(String xml) {
		LOG.info("Inside ThisLocationControllerImpl : saveUserProductRating");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.saveUserProductRating(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : saveUserProductRating : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This ControllerImpl method to share product information from This
	 * Location and other modules. Retailer Information is also shared if
	 * product is shared from This Location module. Method Type: POST
	 * 
	 * @param xml
	 *            contains information about the Product to be shared.
	 * @return XML in the response containing product and retailer information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareProductRetailerInfo(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareProductRetailerInfo");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareProductRetailerInfo(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareProductRetailerInfo : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This ControllerImpl method to share hot deal or coupon information Method
	 * Type: GET
	 * 
	 * @param xml
	 *            contains information about the hot deal to be shared.
	 * @return XML in the response containing hot deal or coupon information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
/*	public String shareHotDealInfo(Integer hotdealId, Integer hubCitiId) {
		LOG.info("Inside RateReviewControllerImpl : shareHotDealInfo");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareHotdealInfo(hotdealId, hubCitiId);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareHotDealInfo : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}*/

	/**
	 * This ControllerImpl method to share coupon information Method Type: GET
	 * 
	 * @param xml
	 *            contains information about the coupon to be shared.
	 * @return XML in the response containing coupon.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

/*	public String shareCLRInfo(Integer couponId, Integer hubCitiId) {
		LOG.info("Inside RateReviewControllerImpl : shareCLRInfo");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareCLRInfo(couponId, hubCitiId);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareCLRInfo : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}*/

	/**
	 * This ControllerImpl method to share product information via Email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains information about the Product to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	public String shareProductInfo(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareProductInfo");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareProductInfo(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareProductInfo : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareProductInfo");
		return strResponseXML;
	}


	/**
	 * This ControllerImpl method to share coupon information via Email Method
	 * Type: POST
	 * 
	 * @param xml
	 *            contains information about the coupon to be shared.
	 * @return XML in the response containing coupon.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
/*	public String shareCLRbyEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareCLRbyEmail");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareCLRbyEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareCLRbyEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}*/

	/**
	 * This ControllerImpl method used to share app site via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains recipient email ID, share type, user ID, retailer ID
	 *            and retailer location ID.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareAppSiteByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareAppSiteByEmail");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareAppSiteByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareAppSiteByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareAppSiteByEmail");
		return strResponseXML;
	}

	/**
	 * This ControllerImpl method used when the user clicks Share, they will be
	 * prompted to share via different options Text/Twitter/Facebook will send
	 * wording, retailer name, and link to the AppSite information.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareAppsite(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareAppsite");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareAppsite(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareAppsite : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareAppsite");
		return strResponseXML;
	}



	/**
	 * This Controller method used to share app download link. via Email.Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	public String shareAppDownloadLink(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareAppDownloadLink");
		String response = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			response = rateReviewService.shareAppDownloadLink(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareAppDownloadLink() : exception : " + exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareAppDownloadLink");
		return response;
	}
	
	
	/**
	 * This ControllerImpl method used to share retailer special offer
	 * information via Email.Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	public String shareSpecialOffByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareSpecialOffByEmail");
		
		String response = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			response = rateReviewService.shareSpecialOffByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareSpecialOffByEmail : exception : " + exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareSpecialOffByEmail");
		return response;
	}
	
	/**
	 * This ControllerImpl method used to share retailer special offer
	 * via Facebook, Text, Twitter .Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 */
	public String shareSpecialOffer(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareSpecialOffer");
		
		String response = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			response = rateReviewService.shareSpecialOffer(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareSpecialOffer : exception : " + exception);
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareSpecialOffer");
		return response;
	}	
	
	
	/**
	 * This ControllerImpl method used to share event via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, user ID, event ID
	 *            and hubciti ID.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareEventByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareEventByEmail");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareEventByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareEventByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
	
	/**
	 * This ControllerImpl method used to share event via facebook, twitter and sms. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination user ID, event ID
	 *            and hubciti ID.
	 * @return XML as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareEvent(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareEvent");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareEvent(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareEvent : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
	
	/**
	 * This ControllerImpl method used to share Fundraiser via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, user ID, Fundraiser ID
	 *            and hubciti ID.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareFundraiserByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareFundraiserByEmail");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareFundraiserByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareFundraiserByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
	
	/**
	 * This ControllerImpl method used to share Fundraiser via facebook, twitter and sms. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination user ID, Fundraiser ID
	 *            and hubciti ID.
	 * @return XML as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareFundraiser(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareFundraiser");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareFundraiser(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareFundraiser : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
	
	
	/**
	 * This ControllerImpl method to share hot deal Via Email.
	 * Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the hot deal to be shared.
	 * @return XML in the response containing hot deal .
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareHotdealByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareHotdealByEmail");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareHotdealByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareHotdealByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareHotdealByEmail");
		return strResponseXML;
	}
	
	/**
	 * This ControllerImpl method to share hot deal information via Facebook, Text, Twitter.
	 * Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the hot deal to be shared.
	 * @return XML in the response containing hot deal.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareHotdeal(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareHotdeal");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareHotdeal(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareHotdeal : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareHotdeal");
		return strResponseXML;
	}
	
	
	/**
	 * This ControllerImpl method to share Coupon Via Email.
	 * Method Type:POST.
	 * 
	 * @param xml contains information about the Coupon to be shared.
	 * @return XML in the response containing Coupon .
	 * @throws HubCitiException  throws if exception occurs.
	 */
	public String shareCouponByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareCouponByEmail");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareCouponByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareHotdealByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareCouponByEmail");
		return strResponseXML;
	}
	
	/**
	 * This ControllerImpl method to share Coupon information via Facebook, Text, Twitter.
	 * Method Type:POST.
	 * 
	 * @param xml  contains information about the Coupon to be shared.
	 * @return XML in the response containing Coupon.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String shareCoupon(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareCoupon");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareCoupon(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareCoupon : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareCoupon");
		return strResponseXML;
	}
	
	
	/**
	 * This ControllerImpl method to update sender share information type.
	 * 
	 * @param xml as request parameter.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public void updateSenderShare(String xml) {
		LOG.info("Inside RateReviewControllerImpl : updateSenderShare");
		
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			rateReviewService.updateSenderShare(xml);
			
		} catch (HubCitiException exception) {
			
			LOG.error("Inside RateReviewControllerImpl : updateSenderShare : exception : " + exception);
			Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
	}
	

	/**
	 * This ControllerImpl method to update receiver share information type.
	 * 
	 * @param xml as request parameter.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public void updateReceiverShare(String xml) {
		LOG.info("Inside RateReviewControllerImpl : updateReceiverShare");
		
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			rateReviewService.updateReceiverShare(xml);
			
		} catch (HubCitiException exception) {
			
			LOG.error("Inside RateReviewControllerImpl : updateReceiverShare : exception : " + exception);
			Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
	}
	
	/**
	 * This ControllerImpl method used to share event via email. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination email, user ID, event ID
	 *            and hubciti ID.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareBandEventByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareBandEventByEmail");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareBandEventByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareBandEventByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
	
	/**
	 * Bandf Event Share
	 * This ControllerImpl method used to share event via facebook, twitter and sms. Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains destination user ID, event ID
	 *            and hubciti ID.
	 * @return XML as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareBandEvent(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareEvent");
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareBandEvent(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareEvent : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
	
	/**
	 * This ControllerImpl method used to share Band Details via email. Method
	 * 
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains recipient email ID, share type, user ID, Band ID
	 *                        
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareBandByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareBandByEmail");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareBandByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareBandByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareBandByEmail");
		return strResponseXML;
	}

	/**
	 * This ControllerImpl method used when the user clicks Share, they will be
	 * prompted to share via different options Text/Twitter/Facebook will send
	 * wording, retailer name, and link to the AppSite information.
	 * 
	 * @param xml
	 *            contains information about the special offer to be shared and
	 *            Recipients.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareBand(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareBand");
		
		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareBand(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareBand : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit RateReviewControllerImpl : shareBand");
		return strResponseXML;
	}
	

	/**
	 * This ControllerImpl method used to share News Details via email.
	 *  Method Type:POST.
	 * 
	 * @param newsID
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareNewsByEmail(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareNewsByEmail");

		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareNewsByEmail(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareNewsByEmail : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit RateReviewControllerImpl : shareNewsByEmail");
		return strResponseXML;
	}

	/**
	 * This ControllerImpl method used when the user clicks Share, they will be
	 * prompted to share via different options Text/Twitter/Facebook will send
	 * wording, retailer name, and link to the AppSite information.
	 * 
	 * @param newsID.
	 * @return XML success or failure as response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String shareNewsBySocailMedia(String xml) {
		LOG.info("Inside RateReviewControllerImpl : shareNewsBySocailMedia");

		String strResponseXML = null;
		final RateReviewService rateReviewService = ServiceFactory.getRateReviewService();
		try {
			strResponseXML = rateReviewService.shareNewsBySocailMedia(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside RateReviewControllerImpl : shareNewsBySocailMedia : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit RateReviewControllerImpl : shareNewsBySocailMedia");
		return strResponseXML;
	}
}
