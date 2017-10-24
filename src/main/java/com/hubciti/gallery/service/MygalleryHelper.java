package com.hubciti.gallery.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.helper.SortCoupLocationByRetDist;
import com.hubciti.common.helper.SortCouponLocByRetailer;
import com.hubciti.common.helper.SortCouponProdByCategory;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.CouponsDetails;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.utility.Utility;

public class MygalleryHelper {
	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(MygalleryHelper.class);

	/**
	 * for MygalleryHelper constructor.
	 */
	protected MygalleryHelper() {
		LOG.info("Inside MygalleryHelper class");
	}

	/**
	 * Method to sort coupons by Retailer by distance.
	 * 
	 * @param couponDetailsResultList
	 *            as request.
	 * @return objCouponsDetails in response/
	 */
	public static CouponsDetails sortAllCouponsRetailersByDistanceForLocation(List<CouponDetails> couponDetailsResultList) {
		CouponsDetails objCouponsDetails = new CouponsDetails();
		String key = null;
		final HashMap<String, RetailersDetails> retDetailsInfoMap = new HashMap<String, RetailersDetails>();
		ArrayList<CouponDetails> couponList;
		RetailersDetails retDetails;

		for (CouponDetails coupDetailsList : couponDetailsResultList) {
			key = coupDetailsList.getRetName();
			if (!"".equals(Utility.checkNull(key))) {
				key = Character.toUpperCase(key.charAt(0)) + key.substring(1);
				if (retDetailsInfoMap.containsKey(key)) {
					retDetails = retDetailsInfoMap.get(key);
					couponList = retDetails.getCouponDetailsList();
					if (null != couponList) {
						final CouponDetails coupDet = new CouponDetails();
						coupDet.setCouponId(coupDetailsList.getCouponId());
						coupDet.setCouponName(coupDetailsList.getCouponName());
						coupDet.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
						coupDet.setIsDateFormated(true);
						coupDet.setCouponStartDate(coupDetailsList.getCouponStartDate());
						coupDet.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
						coupDet.setIsDateFormated(null);
						coupDet.setCouponImagePath(coupDetailsList.getCouponImagePath());
						coupDet.setCouponURL(coupDetailsList.getCouponURL());
						coupDet.setBusCatId(coupDetailsList.getBusCatId());
						coupDet.setBusCatName(coupDetailsList.getBusCatName());
						coupDet.setAddress(coupDetailsList.getAddress());
						coupDet.setRetLocId(coupDetailsList.getRetLocId());
						coupDet.setCity(coupDetailsList.getCity());
						coupDet.setPostalCode(coupDetailsList.getPostalCode());
						coupDet.setCoupDesc(coupDetailsList.getCoupDesc());
						coupDet.setDistance(coupDetailsList.getDistance());
						coupDet.setClaimFlag(coupDetailsList.getClaimFlag());
						coupDet.setRedeemFlag(coupDetailsList.getRedeemFlag());
						coupDet.setNewFlag(coupDetailsList.getNewFlag());
						coupDet.setUsed(coupDetailsList.getUsed());
						coupDet.setCoupToIssue(coupDetailsList.getCoupToIssue());
						coupDet.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
						coupDet.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
						coupDet.setCouponListId(coupDetailsList.getCouponListId());
						coupDet.setCouponURL(coupDetailsList.getCouponURL());
						// coupDet.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
						coupDet.setUsedFlag(coupDetailsList.getUsedFlag());
						couponList.add(coupDet);
						if (retDetails.getMinRetDist() > coupDetailsList.getDistance()) {
							retDetails.setMinRetDist(coupDetailsList.getDistance());
						}
						retDetails.setCouponDetailsList(couponList);
					}
				} else {
					retDetails = new RetailersDetails();
					retDetails.setRetId(coupDetailsList.getRetId());
					retDetails.setRetName(coupDetailsList.getRetName());
					retDetails.setMinRetDist(coupDetailsList.getDistance());
					couponList = new ArrayList<CouponDetails>();
					final CouponDetails coupDet = new CouponDetails();
					coupDet.setCouponId(coupDetailsList.getCouponId());
					coupDet.setCouponName(coupDetailsList.getCouponName());
					coupDet.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
					coupDet.setIsDateFormated(true);
					coupDet.setCouponStartDate(coupDetailsList.getCouponStartDate());
					coupDet.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
					coupDet.setIsDateFormated(null);
					coupDet.setCouponImagePath(coupDetailsList.getCouponImagePath());
					coupDet.setCouponURL(coupDetailsList.getCouponURL());
					coupDet.setBusCatId(coupDetailsList.getBusCatId());
					coupDet.setBusCatName(coupDetailsList.getBusCatName());
					coupDet.setAddress(coupDetailsList.getAddress());
					coupDet.setRetLocId(coupDetailsList.getRetLocId());
					coupDet.setCity(coupDetailsList.getCity());
					coupDet.setPostalCode(coupDetailsList.getPostalCode());
					coupDet.setCoupDesc(coupDetailsList.getCoupDesc());
					coupDet.setDistance(coupDetailsList.getDistance());
					coupDet.setClaimFlag(coupDetailsList.getClaimFlag());
					coupDet.setRedeemFlag(coupDetailsList.getRedeemFlag());
					coupDet.setNewFlag(coupDetailsList.getNewFlag());
					coupDet.setUsed(coupDetailsList.getUsed());
					coupDet.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
					coupDet.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
					coupDet.setCouponListId(coupDetailsList.getCouponListId());
					coupDet.setCouponURL(coupDetailsList.getCouponURL());
					// coupDet.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
					coupDet.setUsedFlag(coupDetailsList.getUsedFlag());
					coupDet.setCoupToIssue(coupDetailsList.getCoupToIssue());
					couponList.add(coupDet);
					retDetails.setCouponDetailsList(couponList);
				}
				retDetailsInfoMap.put(key, retDetails);
			}
		}
		final Set<Map.Entry<String, RetailersDetails>> set = retDetailsInfoMap.entrySet();
		final ArrayList<RetailersDetails> retDetLst = new ArrayList<RetailersDetails>();
		for (Map.Entry<String, RetailersDetails> entry : set) {
			retDetLst.add(entry.getValue());
		}
		SortCoupLocationByRetDist objSortCoupByRet = new SortCoupLocationByRetDist();
		Collections.sort(retDetLst, objSortCoupByRet);
		objCouponsDetails.setRetDetailsList(retDetLst);
		return objCouponsDetails;
	}

	/**
	 * Method to sort coupons by category in product tab.
	 * 
	 * @param couponDetailsResultList
	 *            as request.
	 * @return objCouponsDetails in response/
	 */
	public static CouponsDetails sortAllCouponsByCatForProduct(List<CouponDetails> couponDetailsResultList) {
		CouponsDetails objCouponsDetails = new CouponsDetails();
		String key = null;
		final HashMap<String, CategoryInfo> couponCategoryInfoMap = new HashMap<String, CategoryInfo>();
		ArrayList<CouponDetails> couponList;
		CategoryInfo categoryInfo = null;

		for (CouponDetails coupDetailsList : couponDetailsResultList) {
			key = coupDetailsList.getCatName();
			if (!"".equals(Utility.checkNull(key))) {
				key = Character.toUpperCase(key.charAt(0)) + key.substring(1);
				if (couponCategoryInfoMap.containsKey(key)) {
					categoryInfo = couponCategoryInfoMap.get(key);
					couponList = categoryInfo.getCouponDetailsList();
					if (null != couponList) {
						final CouponDetails objCouponDetails = new CouponDetails();
						objCouponDetails.setCouponId(coupDetailsList.getCouponId());
						objCouponDetails.setCouponName(coupDetailsList.getCouponName());
						objCouponDetails.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
						objCouponDetails.setIsDateFormated(true);
						objCouponDetails.setCouponStartDate(coupDetailsList.getCouponStartDate());
						objCouponDetails.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
						objCouponDetails.setIsDateFormated(null);
						objCouponDetails.setCouponImagePath(coupDetailsList.getCouponImagePath());
						objCouponDetails.setCouponURL(coupDetailsList.getCouponURL());
						objCouponDetails.setCoupDesc(coupDetailsList.getCoupDesc());
						objCouponDetails.setDistance(coupDetailsList.getDistance());
						objCouponDetails.setClaimFlag(coupDetailsList.getClaimFlag());
						objCouponDetails.setRedeemFlag(coupDetailsList.getRedeemFlag());
						objCouponDetails.setNewFlag(coupDetailsList.getNewFlag());
						objCouponDetails.setUsed(coupDetailsList.getUsed());
						objCouponDetails.setCoupToIssue(coupDetailsList.getCoupToIssue());
						objCouponDetails.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
						objCouponDetails.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
						objCouponDetails.setCouponListId(coupDetailsList.getCouponListId());
						// objCouponDetails.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
						objCouponDetails.setUsedFlag(coupDetailsList.getUsedFlag());
						couponList.add(objCouponDetails);
						categoryInfo.setCouponDetailsList(couponList);
					}
				} else {
					categoryInfo = new CategoryInfo();
					categoryInfo.setCategoryId(coupDetailsList.getCatId());
					categoryInfo.setCategoryName(coupDetailsList.getCatName());
					couponList = new ArrayList<CouponDetails>();
					final CouponDetails objCouponDetails = new CouponDetails();
					objCouponDetails.setCouponId(coupDetailsList.getCouponId());
					objCouponDetails.setCouponName(coupDetailsList.getCouponName());
					objCouponDetails.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
					objCouponDetails.setIsDateFormated(true);
					objCouponDetails.setCouponStartDate(coupDetailsList.getCouponStartDate());
					objCouponDetails.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
					objCouponDetails.setIsDateFormated(null);
					objCouponDetails.setCouponImagePath(coupDetailsList.getCouponImagePath());
					objCouponDetails.setCouponURL(coupDetailsList.getCouponURL());
					objCouponDetails.setCoupDesc(coupDetailsList.getCoupDesc());
					objCouponDetails.setDistance(coupDetailsList.getDistance());
					objCouponDetails.setClaimFlag(coupDetailsList.getClaimFlag());
					objCouponDetails.setRedeemFlag(coupDetailsList.getRedeemFlag());
					objCouponDetails.setNewFlag(coupDetailsList.getNewFlag());
					objCouponDetails.setUsed(coupDetailsList.getUsed());
					objCouponDetails.setCoupToIssue(coupDetailsList.getCoupToIssue());
					objCouponDetails.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
					objCouponDetails.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
					objCouponDetails.setCouponListId(coupDetailsList.getCouponListId());
					// objCouponDetails.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
					objCouponDetails.setUsedFlag(coupDetailsList.getUsedFlag());
					couponList.add(objCouponDetails);
					categoryInfo.setCouponDetailsList(couponList);
				}
				couponCategoryInfoMap.put(key, categoryInfo);
			}
		}

		final Set<Map.Entry<String, CategoryInfo>> set = couponCategoryInfoMap.entrySet();
		final ArrayList<CategoryInfo> categoryInfolst = new ArrayList<CategoryInfo>();
		for (Map.Entry<String, CategoryInfo> entry : set) {
			categoryInfolst.add(entry.getValue());
		}
		SortCouponProdByCategory objSortCoupByCat = new SortCouponProdByCategory();
		Collections.sort(categoryInfolst, objSortCoupByCat);
		objCouponsDetails.setCategoryInfoList(categoryInfolst);
		return objCouponsDetails;
	}

	/**
	 * Method to sort Coupons by distance and arrange by retailers.
	 * 
	 * @param couponDetailsResultList
	 *            as request
	 * @return objCouponsDetails CouponsDetails object
	 */
	public static CouponsDetails sortRetailerCoupByDist(List<CouponDetails> couponDetailsResultList) {
		final CouponsDetails objCouponsDetails = new CouponsDetails();
		String key = null;
		final HashMap<String, RetailersDetails> retDetailsInfoMap = new HashMap<String, RetailersDetails>();
		ArrayList<CouponDetails> couponList;
		RetailersDetails retDetails;

		for (CouponDetails coupDetailsList : couponDetailsResultList) {
			key = coupDetailsList.getRetLocId().toString();
			if (!"".equals(Utility.isNull(key))) {
				if (retDetailsInfoMap.containsKey(key)) {
					retDetails = retDetailsInfoMap.get(key);
					couponList = retDetails.getCouponDetailsList();
					if (null != couponList) {
						final CouponDetails coupDet = new CouponDetails();
						coupDet.setCouponId(coupDetailsList.getCouponId());
						coupDet.setCouponName(coupDetailsList.getCouponName());
						coupDet.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
						coupDet.setIsDateFormated(true);
						coupDet.setCouponStartDate(coupDetailsList.getCouponStartDate());
						coupDet.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
						coupDet.setIsDateFormated(null);
						coupDet.setCouponImagePath(coupDetailsList.getCouponImagePath());
						coupDet.setCouponURL(coupDetailsList.getCouponURL());
						coupDet.setCoupDesc(coupDetailsList.getCoupDesc());
						coupDet.setClaimFlag(coupDetailsList.getClaimFlag());
						coupDet.setRedeemFlag(coupDetailsList.getRedeemFlag());
						coupDet.setNewFlag(coupDetailsList.getNewFlag());
						coupDet.setUsed(coupDetailsList.getUsed());
						coupDet.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
						coupDet.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
						coupDet.setCouponListId(coupDetailsList.getCouponListId());
						coupDet.setCouponURL(coupDetailsList.getCouponURL());
						coupDet.setCoupToIssue(coupDetailsList.getCoupToIssue());
						coupDet.setDistance(coupDetailsList.getDistance());
						// coupDet.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
						coupDet.setUsedFlag(coupDetailsList.getUsedFlag());
						couponList.add(coupDet);
						retDetails.setCouponDetailsList(couponList);
					}
				} else {
					retDetails = new RetailersDetails();
					retDetails.setRetLocId(coupDetailsList.getRetLocId());
					retDetails.setRetailerAddress(coupDetailsList.getAddress());
					retDetails.setCity(coupDetailsList.getCity());
					retDetails.setState(coupDetailsList.getState());
					retDetails.setPostalCode(coupDetailsList.getPostalCode());
					retDetails.setMinRetDist(coupDetailsList.getDistance());
					couponList = new ArrayList<CouponDetails>();
					final CouponDetails coupDet = new CouponDetails();
					coupDet.setCouponId(coupDetailsList.getCouponId());
					coupDet.setCouponName(coupDetailsList.getCouponName());
					coupDet.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
					coupDet.setIsDateFormated(true);
					coupDet.setCouponStartDate(coupDetailsList.getCouponStartDate());
					coupDet.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
					coupDet.setIsDateFormated(null);
					coupDet.setCouponImagePath(coupDetailsList.getCouponImagePath());
					coupDet.setCouponURL(coupDetailsList.getCouponURL());
					coupDet.setCoupDesc(coupDetailsList.getCoupDesc());
					coupDet.setClaimFlag(coupDetailsList.getClaimFlag());
					coupDet.setRedeemFlag(coupDetailsList.getRedeemFlag());
					coupDet.setNewFlag(coupDetailsList.getNewFlag());
					coupDet.setUsed(coupDetailsList.getUsed());
					coupDet.setCoupToIssue(coupDetailsList.getCoupToIssue());
					coupDet.setDistance(coupDetailsList.getDistance());
					coupDet.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
					coupDet.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
					coupDet.setCouponListId(coupDetailsList.getCouponListId());
					coupDet.setCouponURL(coupDetailsList.getCouponURL());
					// coupDet.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
					coupDet.setUsedFlag(coupDetailsList.getUsedFlag());
					couponList.add(coupDet);
					retDetails.setCouponDetailsList(couponList);
				}
				retDetailsInfoMap.put(key, retDetails);
			}
		}
		final Set<Map.Entry<String, RetailersDetails>> set = retDetailsInfoMap.entrySet();
		final ArrayList<RetailersDetails> retDetLst = new ArrayList<RetailersDetails>();
		for (Map.Entry<String, RetailersDetails> entry : set) {
			retDetLst.add(entry.getValue());
		}
		SortCoupLocationByRetDist objSortCoupByRet = new SortCoupLocationByRetDist();
		Collections.sort(retDetLst, objSortCoupByRet);
		objCouponsDetails.setRetDetailsList(retDetLst);
		return objCouponsDetails;
	}

	/**
	 * Method to sort HotDeal list by retailer.
	 * 
	 * @param hotDealDetailsList
	 *            as request
	 * @return objHotDeals HotDealsListResultSet object.
	 */
	public static HotDealsListResultSet sortHotDealsListByRetailer(List<HotDealsDetails> hotDealDetailsList) {
		HotDealsListResultSet objHotDeals = new HotDealsListResultSet();
		String key = null;
		final HashMap<String, RetailersDetails> hDInfoMap = new HashMap<String, RetailersDetails>();
		ArrayList<HotDealsDetails> hdDetailsList;
		RetailersDetails objRetailersDetails = null;

		for (HotDealsDetails hDDetails : hotDealDetailsList) {
			key = hDDetails.getRetName();
			if (!"".equals(Utility.isNull(key))) {
				if (hDInfoMap.containsKey(key)) {
					objRetailersDetails = hDInfoMap.get(key);
					hdDetailsList = objRetailersDetails.gethDDetailsList();
					if (null != hdDetailsList) {
						final HotDealsDetails objHDDetails = new HotDealsDetails();
						objHDDetails.setHotDealName(hDDetails.getHotDealName());
						objHDDetails.setHotDealId(hDDetails.getHotDealId());
						objHDDetails.setHotDealImagePath(hDDetails.getHotDealImagePath());
						objHDDetails.setHdURL(hDDetails.getHdURL());
						objHDDetails.setIsDateFormated(false);
						objHDDetails.sethDStartDate(hDDetails.gethDStartDate());
						objHDDetails.sethDEndDate(hDDetails.gethDEndDate());
						objHDDetails.sethDExpDate(hDDetails.gethDExpDate());
						objHDDetails.setIsDateFormated(null);
						objHDDetails.sethDDiscountAmount(hDDetails.gethDDiscountAmount());
						objHDDetails.sethDDiscountPct(hDDetails.gethDDiscountPct());
						objHDDetails.sethDGallId(hDDetails.gethDGallId());
						objHDDetails.sethDDesc(hDDetails.gethDDesc());
						objHDDetails.setUsedFlag(hDDetails.getUsedFlag());
						objHDDetails.setAddress(hDDetails.getAddress());
						objHDDetails.setRetLocId(hDDetails.getRetLocId());
						hdDetailsList.add(objHDDetails);
						objRetailersDetails.sethDDetailsList(hdDetailsList);
					}
				} else {
					objRetailersDetails = new RetailersDetails();
					objRetailersDetails.setRetId(hDDetails.getRetId());
					objRetailersDetails.setRetName(hDDetails.getRetName());
					// objRetailersDetails.setRetLocId(hDDetails.getRetLocId());
					hdDetailsList = new ArrayList<HotDealsDetails>();
					final HotDealsDetails objHDDetails = new HotDealsDetails();
					objHDDetails.setHotDealName(hDDetails.getHotDealName());
					objHDDetails.setHotDealId(hDDetails.getHotDealId());
					objHDDetails.setHotDealImagePath(hDDetails.getHotDealImagePath());
					objHDDetails.setHdURL(hDDetails.getHdURL());
					objHDDetails.sethDStartDate(hDDetails.gethDStartDate());
					objHDDetails.sethDEndDate(hDDetails.gethDEndDate());
					objHDDetails.sethDDiscountAmount(hDDetails.gethDDiscountAmount());
					objHDDetails.sethDDiscountPct(hDDetails.gethDDiscountPct());
					objHDDetails.sethDExpDate(hDDetails.gethDExpDate());
					objHDDetails.sethDGallId(hDDetails.gethDGallId());
					objHDDetails.sethDDesc(hDDetails.gethDDesc());
					objHDDetails.setUsedFlag(hDDetails.getUsedFlag());
					objHDDetails.setAddress(hDDetails.getAddress());
					objHDDetails.setRetLocId(hDDetails.getRetLocId());
					hdDetailsList.add(objHDDetails);
					objRetailersDetails.sethDDetailsList(hdDetailsList);
				}
				hDInfoMap.put(key, objRetailersDetails);
			}
		}
		final Set<Map.Entry<String, RetailersDetails>> set = hDInfoMap.entrySet();
		final ArrayList<RetailersDetails> retailerDetailsList = new ArrayList<RetailersDetails>();
		for (Map.Entry<String, RetailersDetails> entry : set) {
			retailerDetailsList.add(entry.getValue());
		}
		SortCouponLocByRetailer objSortCoupByRet = new SortCouponLocByRetailer();
		Collections.sort(retailerDetailsList, objSortCoupByRet);
		objHotDeals.setRetDetailsList(retailerDetailsList);
		return objHotDeals;
	}

	/**
	 * Method to get HotDeal List by reailers.
	 * 
	 * @param hotDealDetailsList
	 *            as request
	 * @return objHotDeals HotDealsListResultSet object
	 */
	public static HotDealsListResultSet getHotDealsListByRetailerLoc(List<HotDealsDetails> hotDealDetailsList) {
		final HotDealsListResultSet objHotDeals = new HotDealsListResultSet();
		String key = null;
		final HashMap<String, RetailersDetails> hDInfoMap = new HashMap<String, RetailersDetails>();
		ArrayList<HotDealsDetails> hdDetailsList;
		RetailersDetails objRetInfo = null;

		for (HotDealsDetails hDDetails : hotDealDetailsList) {
			key = hDDetails.getRetLocId().toString();
			if (!"".equals(Utility.isNull(key))) {
				if (hDInfoMap.containsKey(key)) {
					objRetInfo = hDInfoMap.get(key);
					hdDetailsList = objRetInfo.gethDDetailsList();
					if (null != hdDetailsList) {
						final HotDealsDetails objHDDetails = new HotDealsDetails();
						objHDDetails.setHotDealName(hDDetails.getHotDealName());
						objHDDetails.setHotDealId(hDDetails.getHotDealId());
						objHDDetails.setHotDealImagePath(hDDetails.getHotDealImagePath());
						objHDDetails.setHdURL(hDDetails.getHdURL());
						objHDDetails.setIsDateFormated(false);
						objHDDetails.sethDStartDate(hDDetails.gethDStartDate());
						objHDDetails.sethDEndDate(hDDetails.gethDEndDate());
						objHDDetails.setIsDateFormated(null);
						objHDDetails.sethDDiscountAmount(hDDetails.gethDDiscountAmount());
						objHDDetails.sethDDiscountPct(hDDetails.gethDDiscountPct());
						objHDDetails.sethDExpDate(hDDetails.gethDExpDate());
						objHDDetails.sethDGallId(hDDetails.gethDGallId());
						objHDDetails.sethDDesc(hDDetails.gethDDesc());
						objHDDetails.setUsedFlag(hDDetails.getUsedFlag());
						hdDetailsList.add(objHDDetails);
						objRetInfo.sethDDetailsList(hdDetailsList);
					}
				} else {
					objRetInfo = new RetailersDetails();
					objRetInfo.setRetLocId(hDDetails.getRetLocId());
					objRetInfo.setRetailerAddress(hDDetails.getAddress());
					hdDetailsList = new ArrayList<HotDealsDetails>();
					final HotDealsDetails objHDDetails = new HotDealsDetails();
					objHDDetails.setHotDealName(hDDetails.getHotDealName());
					objHDDetails.setHotDealId(hDDetails.getHotDealId());
					objHDDetails.setHotDealImagePath(hDDetails.getHotDealImagePath());
					objHDDetails.setHdURL(hDDetails.getHdURL());
					objHDDetails.setIsDateFormated(false);
					objHDDetails.sethDStartDate(hDDetails.gethDStartDate());
					objHDDetails.sethDEndDate(hDDetails.gethDEndDate());
					objHDDetails.setIsDateFormated(null);
					objHDDetails.sethDDiscountAmount(hDDetails.gethDDiscountAmount());
					objHDDetails.sethDDiscountPct(hDDetails.gethDDiscountPct());
					objHDDetails.sethDExpDate(hDDetails.gethDExpDate());
					objHDDetails.sethDGallId(hDDetails.gethDGallId());
					objHDDetails.sethDDesc(hDDetails.gethDDesc());
					objHDDetails.setUsedFlag(hDDetails.getUsedFlag());
					hdDetailsList.add(objHDDetails);
					objRetInfo.sethDDetailsList(hdDetailsList);
				}
				hDInfoMap.put(key, objRetInfo);
			}
		}
		final Set<Map.Entry<String, RetailersDetails>> set = hDInfoMap.entrySet();
		final ArrayList<RetailersDetails> retDetailsLst = new ArrayList<RetailersDetails>();
		for (Map.Entry<String, RetailersDetails> entry : set) {
			retDetailsLst.add(entry.getValue());
		}
		// SortCouponProdByCategory objSortCoupByRet = new
		// SortCouponProdByCategory();
		// Collections.sort(categoryLst, objSortCoupByRet);
		objHotDeals.setRetDetailsList(retDetailsLst);
		return objHotDeals;
	}

	/**
	 * Method to sort HotDealList by category.
	 * 
	 * @param hotDealDetailsList
	 * @return
	 */
	public static HotDealsListResultSet sortHotDealsListByCategory(List<HotDealsDetails> hotDealDetailsList) {
		HotDealsListResultSet objHotDeals = new HotDealsListResultSet();
		String key = null;
		final HashMap<String, CategoryInfo> hDInfoMap = new HashMap<String, CategoryInfo>();
		ArrayList<HotDealsDetails> hdDetailsList;
		CategoryInfo categoryInfo = null;

		for (HotDealsDetails hDDetails : hotDealDetailsList) {
			key = hDDetails.getCatName();
			if (!"".equals(Utility.isNull(key))) {
				if (hDInfoMap.containsKey(key)) {
					categoryInfo = hDInfoMap.get(key);
					hdDetailsList = categoryInfo.gethDDetailsList();
					if (null != hdDetailsList) {
						final HotDealsDetails objHDDetails = new HotDealsDetails();
						objHDDetails.setHotDealName(hDDetails.getHotDealName());
						objHDDetails.setHotDealId(hDDetails.getHotDealId());
						objHDDetails.setHotDealImagePath(hDDetails.getHotDealImagePath());
						objHDDetails.setHdURL(hDDetails.getHdURL());
						objHDDetails.setIsDateFormated(false);
						objHDDetails.sethDStartDate(hDDetails.gethDStartDate());
						objHDDetails.sethDEndDate(hDDetails.gethDEndDate());
						objHDDetails.sethDExpDate(hDDetails.gethDExpDate());
						objHDDetails.setIsDateFormated(null);
						objHDDetails.sethDDiscountAmount(hDDetails.gethDDiscountAmount());
						objHDDetails.sethDDiscountPct(hDDetails.gethDDiscountPct());
						objHDDetails.sethDGallId(hDDetails.gethDGallId());
						objHDDetails.sethDDesc(hDDetails.gethDDesc());
						objHDDetails.setUsedFlag(hDDetails.getUsedFlag());

						objHDDetails.setAddress(hDDetails.getAddress());
						objHDDetails.setRetId(hDDetails.getRetId());
						objHDDetails.setRetName(hDDetails.getRetName());
						objHDDetails.setRetLocId(hDDetails.getRetLocId());

						hdDetailsList.add(objHDDetails);
						categoryInfo.sethDDetailsList(hdDetailsList);
					}
				} else {
					categoryInfo = new CategoryInfo();
					if (null != hDDetails.getCatId()) {
						categoryInfo.setCategoryId(hDDetails.getCatId());
					} else {
						categoryInfo.setCategoryId(0);
					}
					categoryInfo.setCategoryName(hDDetails.getCatName());
					hdDetailsList = new ArrayList<HotDealsDetails>();
					final HotDealsDetails objHDDetails = new HotDealsDetails();
					objHDDetails.setHotDealName(hDDetails.getHotDealName());
					objHDDetails.setHotDealId(hDDetails.getHotDealId());
					objHDDetails.setHotDealImagePath(hDDetails.getHotDealImagePath());
					objHDDetails.setHdURL(hDDetails.getHdURL());
					objHDDetails.setIsDateFormated(false);
					objHDDetails.sethDStartDate(hDDetails.gethDStartDate());
					objHDDetails.sethDEndDate(hDDetails.gethDEndDate());
					objHDDetails.sethDExpDate(hDDetails.gethDExpDate());
					objHDDetails.setIsDateFormated(null);
					objHDDetails.sethDDiscountAmount(hDDetails.gethDDiscountAmount());
					objHDDetails.sethDDiscountPct(hDDetails.gethDDiscountPct());
					objHDDetails.sethDGallId(hDDetails.gethDGallId());
					objHDDetails.sethDDesc(hDDetails.gethDDesc());
					objHDDetails.setUsedFlag(hDDetails.getUsedFlag());
					objHDDetails.setAddress(hDDetails.getAddress());
					objHDDetails.setRetId(hDDetails.getRetId());
					objHDDetails.setRetName(hDDetails.getRetName());
					objHDDetails.setRetLocId(hDDetails.getRetLocId());

					hdDetailsList.add(objHDDetails);
					categoryInfo.sethDDetailsList(hdDetailsList);
				}
				hDInfoMap.put(key, categoryInfo);
			}
		}
		final Set<Map.Entry<String, CategoryInfo>> set = hDInfoMap.entrySet();
		final ArrayList<CategoryInfo> categoryLst = new ArrayList<CategoryInfo>();
		for (Map.Entry<String, CategoryInfo> entry : set) {
			categoryLst.add(entry.getValue());
		}
		SortCouponProdByCategory objSortCoupByRet = new SortCouponProdByCategory();
		Collections.sort(categoryLst, objSortCoupByRet);
		objHotDeals.setCategoryInfoList(categoryLst);
		return objHotDeals;
	}

	/**
	 * Method to sort coupons by Retailer name.
	 * 
	 * @param couponDetailsResultList
	 *            as request.
	 * @return objCouponsDetails in response/
	 */
	public static CouponsDetails sortAllCouponsRetailersByNameForLocation(List<CouponDetails> couponDetailsResultList) {
		CouponsDetails objCouponsDetails = new CouponsDetails();
		String key = null;
		final HashMap<String, RetailersDetails> retDetailsInfoMap = new HashMap<String, RetailersDetails>();
		ArrayList<CouponDetails> couponList;
		RetailersDetails retDetails;

		for (CouponDetails coupDetailsList : couponDetailsResultList) {
			key = coupDetailsList.getRetName();
			if (!"".equals(Utility.isNull(key))) {
				key = Character.toUpperCase(key.charAt(0)) + key.substring(1);
				if (retDetailsInfoMap.containsKey(key)) {
					retDetails = retDetailsInfoMap.get(key);
					couponList = retDetails.getCouponDetailsList();
					if (null != couponList) {
						final CouponDetails coupDet = new CouponDetails();
						coupDet.setCouponId(coupDetailsList.getCouponId());
						coupDet.setCouponName(coupDetailsList.getCouponName());
						coupDet.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
						coupDet.setIsDateFormated(true);
						coupDet.setCouponStartDate(coupDetailsList.getCouponStartDate());
						coupDet.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
						coupDet.setIsDateFormated(null);
						coupDet.setCouponImagePath(coupDetailsList.getCouponImagePath());
						coupDet.setCouponURL(coupDetailsList.getCouponURL());
						coupDet.setBusCatId(coupDetailsList.getBusCatId());
						coupDet.setBusCatName(coupDetailsList.getBusCatName());
						coupDet.setAddress(coupDetailsList.getAddress());
						coupDet.setRetLocId(coupDetailsList.getRetLocId());
						coupDet.setCity(coupDetailsList.getCity());
						coupDet.setPostalCode(coupDetailsList.getPostalCode());
						coupDet.setCoupDesc(coupDetailsList.getCoupDesc());
						coupDet.setDistance(coupDetailsList.getDistance());
						coupDet.setClaimFlag(coupDetailsList.getClaimFlag());
						coupDet.setRedeemFlag(coupDetailsList.getRedeemFlag());
						coupDet.setNewFlag(coupDetailsList.getNewFlag());
						coupDet.setUsed(coupDetailsList.getUsed());
						coupDet.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
						coupDet.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
						coupDet.setCouponListId(coupDetailsList.getCouponListId());
						coupDet.setCouponURL(coupDetailsList.getCouponURL());
						coupDet.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
						coupDet.setUsedFlag(coupDetailsList.getUsedFlag());
						couponList.add(coupDet);
						retDetails.setCouponDetailsList(couponList);
					}
				} else {
					retDetails = new RetailersDetails();
					retDetails.setRetId(coupDetailsList.getRetId());
					retDetails.setRetName(coupDetailsList.getRetName());
					couponList = new ArrayList<CouponDetails>();
					final CouponDetails coupDet = new CouponDetails();
					coupDet.setCouponId(coupDetailsList.getCouponId());
					coupDet.setCouponName(coupDetailsList.getCouponName());
					coupDet.setUserCoupGallId(coupDetailsList.getUserCoupGallId());
					coupDet.setIsDateFormated(true);
					coupDet.setCouponStartDate(coupDetailsList.getCouponStartDate());
					coupDet.setCouponExpireDate(coupDetailsList.getCouponExpireDate());
					coupDet.setIsDateFormated(null);
					coupDet.setCouponImagePath(coupDetailsList.getCouponImagePath());
					coupDet.setCouponURL(coupDetailsList.getCouponURL());
					coupDet.setBusCatId(coupDetailsList.getBusCatId());
					coupDet.setBusCatName(coupDetailsList.getBusCatName());

					// Need to verify to remove this address
					coupDet.setAddress(coupDetailsList.getAddress());
					coupDet.setRetLocId(coupDetailsList.getRetLocId());

					coupDet.setCity(coupDetailsList.getCity());
					coupDet.setPostalCode(coupDetailsList.getPostalCode());
					coupDet.setCoupDesc(coupDetailsList.getCoupDesc());
					coupDet.setDistance(coupDetailsList.getDistance());
					coupDet.setClaimFlag(coupDetailsList.getClaimFlag());
					coupDet.setRedeemFlag(coupDetailsList.getRedeemFlag());
					coupDet.setNewFlag(coupDetailsList.getNewFlag());
					coupDet.setUsed(coupDetailsList.getUsed());
					coupDet.setCouponDiscountAmount(coupDetailsList.getCouponDiscountAmount());
					coupDet.setCouponDiscountPct(coupDetailsList.getCouponDiscountPct());
					coupDet.setCouponListId(coupDetailsList.getCouponListId());
					coupDet.setCouponURL(coupDetailsList.getCouponURL());
					coupDet.setViewableOnWeb(coupDetailsList.isViewableOnWeb());
					coupDet.setUsedFlag(coupDetailsList.getUsedFlag());
					couponList.add(coupDet);
					retDetails.setCouponDetailsList(couponList);
				}
				retDetailsInfoMap.put(key, retDetails);
			}
		}
		final Set<Map.Entry<String, RetailersDetails>> set = retDetailsInfoMap.entrySet();
		final ArrayList<RetailersDetails> retDetLst = new ArrayList<RetailersDetails>();
		for (Map.Entry<String, RetailersDetails> entry : set) {
			retDetLst.add(entry.getValue());
		}
		SortCouponLocByRetailer objSortCoupByRet = new SortCouponLocByRetailer();
		Collections.sort(retDetLst, objSortCoupByRet);
		objCouponsDetails.setRetDetailsList(retDetLst);
		return objCouponsDetails;
	}
}
