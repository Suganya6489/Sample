package com.hubciti.gallery.controller;

import static com.hubciti.common.constants.HubCitiURLPath.GALLERYBASEURL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.inject.ImplementedBy;
import com.hubciti.common.constants.HubCitiURLPath;

/**
 * This rest easy for gallery module.{@link ImplementedBy}
 * {@link GalleryController}.
 * 
 * @author Kumar D
 */
@Path(GALLERYBASEURL)
public interface GalleryController {

	/**
	 * Controller method is to fetching the Coupon All Location or Product
	 * Details. Method Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return String response Coupon details will be returned.
	 */
	@POST
	@Path(HubCitiURLPath.GETCOUPONPRODUCTLOCATIONLIST)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String fetchCouponLocationProduct(String xml);

	/**
	 * Controller method to get all coupons for Product.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupon details
	 */
	@POST
	@Path(HubCitiURLPath.GETALLCOUPBYPROD)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getAllCouponsByProduct(String xml);

	/**
	 * Controller method to get categories for coupons for products. Method
	 * Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return xml containing business categories
	 */
	@POST
	@Path(HubCitiURLPath.COUPPRODCAT)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getCoupProductCategory(String xml);

	/**
	 * Controller method to get retailers for selected business category. Method
	 * Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return xml containing retailer list
	 */
	@POST
	@Path(HubCitiURLPath.RETFORBUSCAT)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getRetailerForBusinessCategory(String xml);

	/**
	 * Controller method to get all coupons for Location. Method Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return xml containing coupon details.
	 */
	@POST
	@Path(HubCitiURLPath.GETALLCOUPBYLOC)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getAllCouponsByLocation(String xml);

	/**
	 * Method to get business categories for coupons for Locations.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing business categories
	 */
	@POST
	@Path(HubCitiURLPath.COUPLOCBUSCAT)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getCoupLocationBusinessCategory(String xml);

	/**
	 * Controller method for the Adding a Coupon(to the Coupon Gallery). Method
	 * Type:POST.
	 * 
	 * @param xml
	 *            contains information about the Coupon.
	 * @return String response as Success or failure.
	 */
	@POST
	@Path(HubCitiURLPath.ADDCOUPON)
	@Produces("text/xml")
	@Consumes("text/xml")
	String couponAdd(String xml);

	/**
	 * Controller method for Redeeming the coupon Calls the corresponding method
	 * in the service layer.Method Type:POST.
	 * 
	 * @param xml
	 *            used for redeeming the coupon.
	 * @return String response containing success or failure will be returned.
	 */
	@POST
	@Path(HubCitiURLPath.USERREDEEMCOUPON)
	@Produces("text/xml;charset=UTF-8")
	@Consumes("text/xml")
	String userRedeemCoupon(String xml);

	/**
	 * This is a RestEasy WebService Method for the removing a Coupon(from the
	 * Coupon Gallery). Method Type:POST.
	 * 
	 * @param xml
	 *            contains information about the Coupon.
	 * @return String response as Success or failure.
	 */
	@POST
	@Path(HubCitiURLPath.REMOVECOUPON)
	@Produces("text/xml")
	@Consumes("text/xml")
	String couponRemove(String xml);

	/**
	 * Method to get gallery_HotDeals by Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing hotdeals sorted by retailers and category.
	 */
	@POST
	@Path(HubCitiURLPath.GALLHDBYLOC)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getGalleryHotDealByLocation(String xml);

	/**
	 * Method to get gallery_HotDeals by Product.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing hotdeals sorted by category
	 */
	@POST
	@Path(HubCitiURLPath.GALLHDBYPROD)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getGalleryHotDealByProduct(String xml);

	/**
	 * Method to get gallery coupons for All/Used/Expired in Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupons
	 */
	@POST
	@Path(HubCitiURLPath.GALLCOUPBULOC)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getGalleryCouponsByLocation(String xml);

	/**
	 * Method to get gallery coupons for All/Used/Expired in Products.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupons sorted by category
	 */
	@POST
	@Path(HubCitiURLPath.GALLCOUPBYPROD)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getGalleryCouponsByProduct(String xml);

	/**
	 * Controller method to get Special offer retailer display. This is restEasy
	 * web service method to get deals present in the HubCiti (Coupon, Special
	 * Offer, Hot Deal, Sale).
	 * 
	 * @param xml
	 * @return xml.
	 */
	@POST
	@Path(HubCitiURLPath.HUBCITISPEDEALS)
	@Consumes("text/xml")
	@Produces("text/xml")
	String getHubCitiSpecialDeals(String xml);

	/**
	 * Controller method to get retailers for Partners/Affiliates.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	@POST
	@Path(HubCitiURLPath.GETSPECIALOFFERRETAILERDISPLAY)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getSpecialOffRetDisplay(String xml);

	/**
	 * Controller method to get Sales retailer display.
	 * 
	 * @param String
	 *            xml.
	 * @return response XMl containing retailers list.
	 */
	@POST
	@Path(HubCitiURLPath.GETSALESRETAILERDISPLAY)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getSalesRetDisplay(String xml);
	
	/**
	 * Controller method for the Adding a Coupon (to the Coupon Gallery).
	 * Method Type:POST.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.ADDCOUPON_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String couponAddJson(String strJson);
	
	/**
	 * Controller method for Redeeming the coupon.
	 * Method Type:POST.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.USERREDEEMCOUPON_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String userRedeemCouponJson(String xml);
	
	/**
	 * Controller method to display Coupon list for Location's.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.GETCOUPONSLOCATIONLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getCouponsLocationListJson(String strJson);
	
	/**
	 * Controller method to display coupons Offer list for map Location's map.
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.GETCOUPONSLOCATIONMAPLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getCouponsLocationMapListJson(String strJson);
	
	/**
	 * Controller method to display My Accounts
	 * 
	 * @param String strJson.
	 */
	@POST
	@Path(HubCitiURLPath.GETMYACCOUNTSLIST_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getMyAccountsJson(String strJson);
}
