package com.hubciti.gallery.dao;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.CLRDetails;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.CouponsDetails;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;

/**
 * The Interface for GalleryDAO.
 * 
 * @author Kumar D
 */
public interface GalleryDAO {
	/**
	 * DAO Method is to fetching the Coupon All Location or Product Details.
	 * 
	 * @param objClrDetails
	 *            as request parameter.
	 * @return CouponDetails that contains Coupon details
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CouponsDetails fetchCouponLocationProduct(CLRDetails objClrDetails) throws HubCitiException;

	/**
	 * DAO Method to fetch all coupons for Product form database.
	 * 
	 * @param objCoupDetReq
	 *            of ProductDetailsRequest object.
	 * @return CouponsDetails object containing coupon details
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CouponsDetails getAllCouponsByProduct(ProductDetailsRequest objCoupDetReq) throws HubCitiException;

	/**
	 * DAO Method to get categories for coupons for products.
	 * 
	 * @param objProdDetailsReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing business categories
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CouponsDetails getCoupProductCategory(ProductDetailsRequest objProdDetailsReq) throws HubCitiException;

	/**
	 * DAO Method to get retailers for selected business category.
	 * 
	 * @param objProdDetailsReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing retailer list
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CouponsDetails getRetailerForBusinessCategory(ProductDetailsRequest objProdDetailsReq) throws HubCitiException;

	/**
	 * DAO Method to fetch all coupons for Product from database.
	 * 
	 * @param objCoupDetReq
	 *            of ProductDetailsRequest object.
	 * @return CouponsDetails object containing coupon details
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	CouponsDetails getAllCouponsByLocation(ProductDetailsRequest objCoupDetReq) throws HubCitiException;

	/**
	 * Method to get business categories for coupons for Locations.
	 * 
	 * @param objProdDetailsReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing business categories
	 * @throws ScanSeeException
	 *             If any exception occurs ScanSeeException will be thrown.
	 */
	CouponsDetails getCoupLocationBusinessCategory(ProductDetailsRequest objProdDetailsReq) throws HubCitiException;

	/**
	 * DAO Method to add the coupons to database for the given user.
	 * 
	 * @param couponAddRemove
	 *            contains coupon info.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	String addCoupon(CLRDetails couponAddRemove) throws HubCitiException;

	/**
	 * DAO Method validates the input parameters and returns validation error if
	 * validation fails. Calls DAO methods based on user id and coupon id.
	 * 
	 * @param couponDetailsObj
	 *            request parameter.
	 * @return String XML with my gallery details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String userRedeemCoupon(CouponDetails couponDetailsObj) throws HubCitiException;

	/**
	 * DAO Method to remove the coupon to database for the given user.
	 * 
	 * @param couponAddRemove
	 *            contains coupon info.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String removeCoupon(CLRDetails couponAddRemove) throws HubCitiException;

	/**
	 * DAO Method to get gallery HotDeals for All/Used/Expired in Location.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object
	 * @return HotDealsListResultSet object containing hotdeals sorted by
	 *         retailers and category.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	HotDealsListResultSet getGalleryHotDealByLocation(ProductDetailsRequest objCoupDetReq) throws HubCitiException;

	/**
	 * DAO Method to get gallery Hot deals for All/Used/Expired in Products.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object
	 * @return HotDealsListResultSet object containing hotdeals sorted by
	 *         category
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	HotDealsListResultSet getGalleryHotDealByProduct(ProductDetailsRequest objCoupDetReq) throws HubCitiException;

	/**
	 * DAO Method to get gallery coupons for All/Used/Expired in Location.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object.
	 * @return CouponsDetails object containing coupons
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	CouponsDetails getGalleryCouponsByLocation(ProductDetailsRequest objCoupDetReq) throws HubCitiException;

	/**
	 * DAO Method to get gallery coupons for All/Used/Expired in Products.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object.
	 * @return CouponsDetails object containing coupons sorted by category
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	CouponsDetails getGalleryCouponsByProduct(ProductDetailsRequest objCoupDetReq) throws HubCitiException;

	/**
	 * DAO method to fetch deals present in specified HubCiti (Coupon, Special
	 * Offer, Hot Deal, Sale).
	 * 
	 * @param productDetailsRequest
	 * @return {@link ProductDetails} object.
	 * @throws HubCitiException. If
	 *             any exception occurs HubCitiException will be thrown.
	 */
	ProductDetails getHubCitiSpecialDeals(ProductDetailsRequest productDetailsRequest) throws HubCitiException;

	/**
	 * DAO method to fetches Special offer retailer display.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	RetailersDetails getSpecialOffRetDisplay(ThisLocationRequest thisLocationReq) throws HubCitiException;

	/**
	 * DAO method to fetches Sales retailer display.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	RetailersDetails getSalesRetDisplay(ThisLocationRequest thisLocationReq) throws HubCitiException;
	
	/**
	 * The ServiceImpl method for displaying  Coupons list for location's.
	 * 
	 * @param objRetDetail input
	 */
	RetailersDetails getCouponsLocationListJson (RetailerDetail objRetDetail) throws HubCitiException;
	
	/**
	 * The ServiceImpl method for displaying  Coupons for map location's.
	 * 
	 * @param objRetDetail input
	 */
	RetailersDetails getCouponsLocationMapListJson (RetailerDetail objRetDetail) throws HubCitiException;
	
	/**
	 * The ServiceImpl method for displaying  My Accounts
	 * 
	 * @param objRetDetail input
	 */
	RetailersDetails getMyAccountsJson (RetailerDetail objRetDetail) throws HubCitiException;
}
