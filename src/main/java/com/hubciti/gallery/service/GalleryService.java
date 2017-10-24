package com.hubciti.gallery.service;

import com.hubciti.common.exception.HubCitiException;

/**
 * The GalleryService interface.
 * 
 * @author Kumar D
 */
public interface GalleryService {

	/**
	 * Service method is to fetching the Coupon All Location or Product Details.
	 * 
	 * @param xml
	 *            as request parameter.
	 * @return XML containing couponDetails information in the response.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String fetchCouponLocationProduct(String xml) throws HubCitiException;

	/**
	 * Service method to get all coupons for Product.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupon details
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String getAllCouponsByProduct(String xml) throws HubCitiException;

	/**
	 * Service method to get categories for coupons for products.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing business categories
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String getCoupProductCategory(String xml) throws HubCitiException;

	/**
	 * Service method to get retailers for selected business category.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing retailer list
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String getRetailerForBusinessCategory(String xml) throws HubCitiException;

	/**
	 * Service method to get all coupons for Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupon details
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String getAllCouponsByLocation(String xml) throws HubCitiException;

	/**
	 * Method to get business categories for coupons for Locations.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing business categories
	 * @throws HubCitiException
	 *             is thrown
	 */
	String getCoupLocationBusinessCategory(String xml) throws HubCitiException;

	/**
	 * Service method for the Adding a Coupon(to the Coupon Gallery).This method
	 * validates the input parameters and returns validation error if validation
	 * fails. calls DAO methods to add the coupons.
	 * 
	 * @param xml
	 *            contains coupon info.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String couponAdd(String xml) throws HubCitiException;

	/**
	 * Service method validates the input parameters and returns validation
	 * error if validation fails. Calls DAO methods based on user id and coupon
	 * id.
	 * 
	 * @param xml
	 *            request parameter.
	 * @return String XML with my gallery details.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String userRedeemCoupon(String xml) throws HubCitiException;

	/**
	 * This is Service method for the removing a Coupon(from the Coupon
	 * Gallery).This method validates the input parameters and returns
	 * validation error if validation fails. calls DAO methods to remove the
	 * coupons.
	 * 
	 * @param xml
	 *            contains coupon info.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String removeCoupon(String xml) throws HubCitiException;

	/**
	 * Method to get gallery HotDeals for All/Used/Expired in Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing hotdeals sorted by retailers and category.
	 * @throws HubCitiException
	 *             is thrown
	 */
	String getGalleryHotDealByLocation(String xml) throws HubCitiException;

	/**
	 * Method to get gallery Hot deals for All/Used/Expired in Products.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing hotdeals sorted by category
	 * @throws HubCitiException
	 *             is thrown
	 */
	String getGalleryHotDealByProduct(String xml) throws HubCitiException;

	/**
	 * This is Service method to get gallery coupons for All/Used/Expired in
	 * Location.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupons
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String getGalleryCouponsByLocation(String xml) throws HubCitiException;

	/**
	 * This is Service method to get gallery coupons for All/Used/Expired in
	 * Products.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing coupons sorted by category
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String getGalleryCouponsByProduct(String xml) throws HubCitiException;

	/**
	 * This service method to get deals present in the HubCiti (Coupon, Special
	 * Offer, Hot Deal, Sale).
	 * 
	 * @param xml
	 * @return xml.
	 */
	String getHubCitiSpecialDeals(String xml) throws HubCitiException;

	/**
	 * Service method is to Special offer retailer display.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	String getSpecialOffRetDisplay(String xml) throws HubCitiException;

	/**
	 * Service method is to Sales retailer display.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	String getSalesRetDisplay(String xml) throws HubCitiException;
	
	/**
	 * Service method for the Adding a Coupon(to the Coupon Gallery).
	 * 
	 * @param strJson contains coupon info.
	 */
	String couponAddJson(String strJson) throws HubCitiException;
	
	/**
	 * Service method for Redeeming Coupon.
	 * 
	 * @param strJson contains coupon info.
	 */
	String userRedeemCouponJson(String strJson) throws HubCitiException;
	
	/**
	 * service method to display  Coupons Offer list for Location's.
	 * 
	 * @param strJson
	 */
	String getCouponsLocationListJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to display  Coupons  for map Location's.
	 * 
	 * @param strJson
	 */
	String getCouponsLocationMapListJson(String strJSON) throws HubCitiException;
	
	/**
	 * service method to display  My Account.
	 * 
	 * @param strJson
	 */
	String getMyAccountsJson(String strJSON) throws HubCitiException;
}
