package com.hubciti.gallery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.gallery.service.GalleryService;


/**
 * The GalleryControllerImpl class.
 * 
 * @author Kumar D
 */
public class GalleryControllerImpl implements GalleryController {
	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GalleryControllerImpl.class);

	/**
	 * ControllerImpl method is to fetching the Coupon All Location or Product
	 * Details. Method Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return String response Coupon details will be returned.
	 */
	public String fetchCouponLocationProduct(String xml) {
		LOG.info("Inside GalleryControllerImpl : fetchCouponLocationProduct");
		String strResponseXML = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			strResponseXML = gallaryService.fetchCouponLocationProduct(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : fetchCouponLocationProduct : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * ControllerImpl method to get all coupons for Product. Method Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return xml containing coupon details
	 */
	public String getAllCouponsByProduct(String xml) {
		LOG.info("Inside GalleryControllerImpl : getAllCouponsByProduct");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");
			responseXml = gallaryService.getAllCouponsByProduct(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getAllCouponsByProduct : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * ControllerImpl method to get categories for coupons for products. Method
	 * Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return xml containing business categories.
	 */
	public String getCoupProductCategory(String xml) {
		LOG.info("Inside GalleryControllerImpl : getCoupProductCategory");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXml = gallaryService.getCoupProductCategory(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getCoupProductCategory : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * ControllerImpl method to get retailers for selected business category.
	 * Method Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return xml containing retailer list
	 */
	public String getRetailerForBusinessCategory(String xml) {
		LOG.info("Inside GalleryControllerImpl : getRetailerForBusinessCategory");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXml = gallaryService.getRetailerForBusinessCategory(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getRetailerForBusinessCategory : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * ControllerImpl method to get all coupons for Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupon details
	 */
	public String getAllCouponsByLocation(String xml) {
		LOG.info("Inside GalleryControllerImpl : getAllCouponsByLocation");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");
			responseXml = gallaryService.getAllCouponsByLocation(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getAllCouponsByLocation : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * Method to get business categories for coupons for Locations.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing business categories
	 */
	public String getCoupLocationBusinessCategory(String xml) {
		final String methodName = "getCoupLocationBusinessCategory";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXml = gallaryService.getCoupLocationBusinessCategory(xml);
		} catch (HubCitiException exception) {
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * ControllerImpl method for the Adding a Coupon(to the Coupon Gallery).
	 * Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the Coupon.
	 * @return String response as Success or failure.
	 */
	public String couponAdd(String xml) {
		LOG.info("Inside GalleryControllerImpl : couponAdd");
		String response = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			response = gallaryService.couponAdd(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : couponAdd : " + exception.toString());
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return response;
	}

	/**
	 * ControllerImpl method for Redeeming Coupon. The method is called from the
	 * service layer.
	 * 
	 * @param xml
	 *            as the request.
	 * @return response as String .
	 */
	public String userRedeemCoupon(String xml) {
		LOG.info("Inside GalleryControllerImpl : userRedeemCoupon");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXml = gallaryService.userRedeemCoupon(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : userRedeemCoupon : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * This is a RestEasy WebService Method for the removing a Coupon(from the
	 * Coupon Gallery). Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the Coupon.
	 * @return String response as Success or failure.
	 */
	public String couponRemove(String xml) {
		LOG.info("Inside GalleryControllerImpl : couponRemove");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXml = gallaryService.removeCoupon(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : couponRemove : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * Method to get gallery_HotDeals by Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing hotdeals sorted by retailers and category.
	 */
	public String getGalleryHotDealByLocation(String xml) {
		final String methodName = "getGalleryHotDealLocation";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");
			responseXml = gallaryService.getGalleryHotDealByLocation(xml);
		} catch (HubCitiException exception) {
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * Method to get gallery_HotDeals by Product.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing hotdeals sorted by category
	 */
	public String getGalleryHotDealByProduct(String xml) {
		final String methodName = "getGalleryHotDealProduct";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");
			responseXml = gallaryService.getGalleryHotDealByProduct(xml);
		} catch (HubCitiException exception) {
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * ControllerImpl method to get gallery coupons for All/Used/Expired in
	 * Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupons
	 */
	public String getGalleryCouponsByLocation(String xml) {
		LOG.info("Inside GalleryControllerImpl : getGalleryCouponsByLocation");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");
			responseXml = gallaryService.getGalleryCouponsByLocation(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getGalleryCouponsByLocation : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * ControllerImpl method to get gallery coupons for All/Used/Expired in
	 * Products.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupons sorted by category
	 */
	public String getGalleryCouponsByProduct(String xml) {
		LOG.info("Inside GalleryControllerImpl : getGalleryCouponsByProduct");
		String responseXml = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			xml = xml.replaceAll("<searchKey>", "<searchKey><![CDATA[");
			xml = xml.replaceAll("</searchKey>", "]]></searchKey>");
			responseXml = gallaryService.getGalleryCouponsByProduct(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getGalleryCouponsByProduct : " + exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * This is restEasy web service method to get deals present in the HubCiti
	 * (Coupon, Special Offer, Hot Deal, Sale).
	 * 
	 * @param xml
	 * @return xml.
	 */
	public String getHubCitiSpecialDeals(String xml) {
		final String methodName = "getHubCitiSpecialDeals";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXML = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXML = gallaryService.getHubCitiSpecialDeals(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * Controller method to get Special offer retailer display.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	public String getSpecialOffRetDisplay(String xml) {
		LOG.info("Inside GalleryControllerImpl : getSpecialOffRetDisplay");
		String responseXML = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXML = gallaryService.getSpecialOffRetDisplay(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getSpecialOffRetDisplay : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * Controller method to get Special offer retailer display.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	public String getSalesRetDisplay(String xml) {
		LOG.info("Inside GalleryControllerImpl : getSalesRetDisplay");
		String responseXML = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			responseXML = gallaryService.getSalesRetDisplay(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside GalleryControllerImpl : getSalesRetDisplay : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}
	
	
	
	/**
	 * ControllerImpl method for the Adding a Coupon(to the Coupon Gallery).
	 * Method Type:POST.
	 * 
	 * @param strJSON
	 *            contains information about the Coupon.
	 * @return String response as Success or failure.
	 */
	public String couponAddJson(String strJSON) {
		LOG.info("Inside GalleryControllerImpl : couponAddJson");
		
		String strJsonResponse = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			strJsonResponse = gallaryService.couponAddJson(strJSON);
		} catch (HubCitiException e) {
			LOG.error("Inside GalleryControllerImpl : couponAddJson : " + e.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method for Redeeming Coupon.
	 * 
	 * @param strJSON
	 *            contains information about the Coupon.
	 * @return String response as Success or failure.
	 */
	public String userRedeemCouponJson(String strJSON) {
		LOG.info("Inside GalleryControllerImpl : userRedeemCouponJson");
		
		String strJsonResponse = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();
		try {
			strJsonResponse = gallaryService.userRedeemCouponJson(strJSON);
		} catch (HubCitiException e) {
			LOG.error("Inside GalleryControllerImpl : userRedeemCouponJson : " + e.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method to display Coupons list for Location's.
	 * 
	 * @param strJson
	 * @return JSON as containing Coupons list(Featured & Non Featured).
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getCouponsLocationListJson(String strJSON) {
		LOG.info("Inside GalleryControllerImpl : getCouponsLocationListJson");

		String strJsonResponse = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();

		try {
			strJsonResponse = gallaryService.getCouponsLocationListJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside GalleryControllerImpl : getCouponsLocationListJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GalleryControllerImpl : getCouponsLocationListJson");
		return strJsonResponse;
	}
	
	/**
	 * ControllerImpl method to display Coupons  list for map Location's.
	 * 
	 * @param strJson
	 * @return JSON as containing coupons  list for map Location's.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getCouponsLocationMapListJson(String strJSON) {
		LOG.info("Inside GalleryControllerImpl : getCouponsLocationMapListJson");

		String strJsonResponse = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();

		try {
			strJsonResponse = gallaryService.getCouponsLocationMapListJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside GalleryControllerImpl : getCouponsLocationMapListJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GalleryControllerImpl : getCouponsLocationMapListJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ControllerImpl method to display My Accounts
	 * 
	 * @param strJson
	 * @return JSON as containing My Account list(Featured & Non Featured).
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getMyAccountsJson(String strJSON) {
		LOG.info("Inside GalleryControllerImpl : getMyAccountsJson");

		String strJsonResponse = null;
		final GalleryService gallaryService = ServiceFactory.getGalleryService();

		try {
			strJsonResponse = gallaryService.getMyAccountsJson(strJSON);
		} catch (HubCitiException ex) {
			LOG.error("Inside GalleryControllerImpl : getMyAccountsJson : " + ex.toString());
			strJsonResponse = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GalleryControllerImpl : getMyAccountsJson");
		return strJsonResponse;
	}
}
