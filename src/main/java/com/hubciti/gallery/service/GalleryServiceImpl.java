package com.hubciti.gallery.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.CLRDetails;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.Coupons;
import com.hubciti.common.pojos.CouponsDetails;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.Retailer;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.dao.FirstUseDao;
import com.hubciti.gallery.dao.GalleryDAO;

/**
 * The GalleryServiceImpl implementation class.
 * 
 * @author Kumar D
 */
public class GalleryServiceImpl implements GalleryService {
	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GalleryServiceImpl.class);

	/**
	 * Instance variable for my gallery DAO instance.
	 */
	private GalleryDAO galleryDao;

	/**
	 * Instance variable for FirstUseDAO.
	 */
	public FirstUseDao firstUseDao;
	
	/**
	 * Variable NOCOUPON_BY_LOC_MSG declared as constant string.
	 */
	private final String NOCOUPON_BY_LOC_MSG = "There are no coupons by location.\nTry search by Product.";
	/**
	 * Variable NOCOUPON_BY_PRODUCT_MSG declared as constant string.
	 */
	public final String NOCOUPON_BY_PRODUCT_MSG = "There are no coupons by Product.\nTry search by Location.";

	/**
	 * @param galleryDao
	 *            the galleryDao to set
	 */
	public void setGalleryDao(GalleryDAO galleryDao) {
		this.galleryDao = galleryDao;
	}

	/**
	 * Setter method for FirstUseDao.
	 * 
	 * @param firstUseDao
	 *            Instance of type FirstUseDao.
	 */
	public void setFirstUseDao(FirstUseDao firstUseDao) {
		this.firstUseDao = firstUseDao;
	}

	/**
	 * ServiceImpl is to fetching the Coupon All Location or Product Details.
	 * 
	 * @param xml
	 *            as request parameter.
	 * @return XML containing couponDetails information in the response.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */

	public String fetchCouponLocationProduct(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : fetchCouponLocationProduct");
		
		String response = null;
		
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final CLRDetails clrDetailsObj = (CLRDetails) streamHelper.parseXmlToObject(xml);
		
		CouponsDetails couponDetails = null;
		if (null == clrDetailsObj.getUserId() || null == clrDetailsObj.getCouponId()
				|| "".equalsIgnoreCase(Utility.checkNull(clrDetailsObj.getType())) || null == clrDetailsObj.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			couponDetails = galleryDao.fetchCouponLocationProduct(clrDetailsObj);
			if (null != couponDetails) {
				response = XstreamParserHelper.produceXmlFromObject(couponDetails);
				response = response.replaceAll("<productLst>", "");
				response = response.replaceAll("</productLst>", "");
				response = response.replaceAll("<retailDetailsList>", "");
				response = response.replaceAll("</retailDetailsList>", "");
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : fetchCouponLocationProduct");
		return response;
	}

	/**
	 * ServiceImpl Method to get all coupons for Product. Uses
	 * XstreamParserHelper class to get request parameter from XML to object
	 * conversion.
	 * 
	 * @param xml
	 *            as request parameter.
	 * @return xml containing coupon details
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getAllCouponsByProduct(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getAllCouponsByProduct");
		
		String response = null;
		CouponsDetails objCouponsDetails = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objCoupDetailsReq = (ProductDetailsRequest) parser.parseXmlToObject(xml);
		
		if (null == objCoupDetailsReq.getUserId() || null == objCoupDetailsReq.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			// For user tracking
			Integer mainMenuId = null;
			if (objCoupDetailsReq.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(objCoupDetailsReq.getUserId());
				objMenuItem.setHubCitiId(objCoupDetailsReq.getHubCitiId());
				objMenuItem.setmItemId(objCoupDetailsReq.getmItemId());
				objMenuItem.setBottomBtnId(objCoupDetailsReq.getBottomBtnId());
				objMenuItem.setPlatform(objCoupDetailsReq.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objCoupDetailsReq.getMainMenuId();
			}

			if ("".equals(Utility.checkNull(objCoupDetailsReq.getCatIds()))) {
				objCoupDetailsReq.setCatIds("0");
			}
			objCouponsDetails = galleryDao.getAllCouponsByProduct(objCoupDetailsReq);
			CouponsDetails objCouponDetail = new CouponsDetails();
			// To sort coupons by category
			if (null != objCouponsDetails &&  null != objCouponsDetails.getCouponDetail() && !objCouponsDetails.getCouponDetail().isEmpty() ) {
				
				objCouponDetail = MygalleryHelper.sortAllCouponsByCatForProduct(objCouponsDetails.getCouponDetail());
				objCouponDetail.setNextPageFlag(objCouponsDetails.getNextPageFlag());
				objCouponDetail.setMaxRowNum(objCouponsDetails.getMaxRowNum());
				objCouponDetail.setMaxCnt(objCouponsDetails.getMaxCnt());
				objCouponDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCouponDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
				
				
			} else {
				objCouponDetail.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				objCouponDetail.setResponseText(NOCOUPON_BY_PRODUCT_MSG);
			}
			
			if (1 == objCouponsDetails.getBottomBtn()) {
				objCouponDetail.setArBottomBtnList(objCouponsDetails.getArBottomBtnList());
			} 
			
			objCouponDetail.setMainMenuId(mainMenuId);
			objCouponDetail.setBottomBtn(objCouponsDetails.getBottomBtn());
			response = XstreamParserHelper.produceXmlFromObject(objCouponDetail);
		}
		
		LOG.info("Exit GalleryServiceImpl : getAllCouponsByProduct");
		return response;
	}

	/**
	 * ServiceImpl Method to get categories for coupons for products.
	 * 
	 * @param xml
	 *            as request.
	 * @return xml containing business categories
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getCoupProductCategory(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getCoupProductCategory");
		
		String response = null;
		CouponsDetails objCouponsDetails = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objProdDetailsReq = (ProductDetailsRequest) parser.parseXmlToObject(xml);
		
		if (null == objProdDetailsReq.getUserId() || null == objProdDetailsReq.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			objCouponsDetails = galleryDao.getCoupProductCategory(objProdDetailsReq);
			if (objCouponsDetails != null && !objCouponsDetails.getCouponDetail().isEmpty() && objCouponsDetails.getCouponDetail() != null) {
				objCouponsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCouponsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objCouponsDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : getCoupProductCategory");
		return response;
	}

	/**
	 * ServiceImpl Method to get retailers for selected business category.
	 * 
	 * @param xml
	 *            as request parameter.
	 * @return xml containing retailer list
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getRetailerForBusinessCategory(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getRetailerForBusinessCategory");
		
		String response = null;
		CouponsDetails objCouponsDetails = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objProdDetailsReq = (ProductDetailsRequest) parser.parseXmlToObject(xml);

		if (null == objProdDetailsReq.getUserId() || null == objProdDetailsReq.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			if (null == objProdDetailsReq.getBusCatIds()) {
				objProdDetailsReq.setBusCatIds("0");
			}
			objCouponsDetails = galleryDao.getRetailerForBusinessCategory(objProdDetailsReq);
			if (objCouponsDetails != null && !objCouponsDetails.getCouponDetail().isEmpty() && objCouponsDetails.getCouponDetail() != null) {
				objCouponsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCouponsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objCouponsDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : getRetailerForBusinessCategory");
		return response;
	}

	/**
	 * ServiceImpl to get all coupons for Location. Uses XstreamParserHelper
	 * class to get request parameter from XML to object conversion.
	 * 
	 * @param xml
	 *            as request.
	 * @return xml containing coupon details
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getAllCouponsByLocation(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getAllCouponsByLocation");
		
		String response = null;
		CouponsDetails objCouponsDetails = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objCoupDetailsReq = (ProductDetailsRequest) parser.parseXmlToObject(xml);
		
		if (null == objCoupDetailsReq.getUserId() || null == objCoupDetailsReq.getHubCitiId() || null == objCoupDetailsReq.getRetailId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			// For user tracking
			Integer mainMenuId = null;
			if (objCoupDetailsReq.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(objCoupDetailsReq.getUserId());
				objMenuItem.setHubCitiId(objCoupDetailsReq.getHubCitiId());
				objMenuItem.setmItemId(objCoupDetailsReq.getmItemId());
				objMenuItem.setBottomBtnId(objCoupDetailsReq.getBottomBtnId());
				objMenuItem.setPlatform(objCoupDetailsReq.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objCoupDetailsReq.getMainMenuId();
			}

			if ("".equals(Utility.checkNull(objCoupDetailsReq.getCatIds()))) {
				objCoupDetailsReq.setCatIds("0");
			}
			objCouponsDetails = galleryDao.getAllCouponsByLocation(objCoupDetailsReq);
			CouponsDetails objCouponsDetailsRetSort = new CouponsDetails();
			CouponsDetails objCouponDetailsSorted = new CouponsDetails();
			if (null != objCouponsDetails && null != objCouponsDetails.getCouponDetail() && !objCouponsDetails.getCouponDetail().isEmpty()) {
				
				// To sort coupons by retailers distance
				objCouponsDetailsRetSort = MygalleryHelper.sortAllCouponsRetailersByDistanceForLocation(objCouponsDetails.getCouponDetail());

				
				ArrayList<RetailersDetails> retDetailsList = new ArrayList<RetailersDetails>();
				RetailersDetails objRetDetails = null;
				CouponsDetails coupsDetails = null;

				// To sort retailer location by distance
				for (int i = 0; i < objCouponsDetailsRetSort.getRetDetailsList().size(); i++) {
					coupsDetails = new CouponsDetails();
					objRetDetails = new RetailersDetails();
					coupsDetails = MygalleryHelper.sortRetailerCoupByDist(objCouponsDetailsRetSort.getRetDetailsList().get(i).getCouponDetailsList());
					objRetDetails.setRetName(objCouponsDetailsRetSort.getRetDetailsList().get(i).getRetName());
					objRetDetails.setRetId(objCouponsDetailsRetSort.getRetDetailsList().get(i).getRetId());
					objRetDetails.setRetDetailsList(coupsDetails.getRetDetailsList());
					retDetailsList.add(objRetDetails);
				}
				objCouponDetailsSorted.setRetDetailsList(retDetailsList);
				
				objCouponDetailsSorted.setArBottomBtnList(objCouponsDetails.getArBottomBtnList());
				objCouponDetailsSorted.setBottomBtn(objCouponsDetails.getBottomBtn());
				
				objCouponDetailsSorted.setNextPageFlag(objCouponsDetails.getNextPageFlag());
				objCouponDetailsSorted.setMainMenuId(mainMenuId);
				objCouponDetailsSorted.setMaxCnt(objCouponsDetails.getMaxCnt());
				objCouponDetailsSorted.setMaxRowNum(objCouponsDetails.getMaxRowNum());
				objCouponDetailsSorted.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCouponDetailsSorted.setResponseText(HubCitiConstants.SUCCESSTEXT);

			} else {
				objCouponDetailsSorted.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				objCouponDetailsSorted.setResponseText(NOCOUPON_BY_LOC_MSG);
			}
			
			if (1 == objCouponsDetails.getBottomBtn()) {
				objCouponDetailsSorted.setArBottomBtnList(objCouponsDetails.getArBottomBtnList());
			} 
			
			objCouponDetailsSorted.setMainMenuId(mainMenuId);
			objCouponDetailsSorted.setBottomBtn(objCouponsDetails.getBottomBtn());
			response = XstreamParserHelper.produceXmlFromObject(objCouponDetailsSorted);
		}
		
		LOG.info("Exit GalleryServiceImpl : getAllCouponsByLocation");
		return response;
	}

	/**
	 * Method to get business categories for coupons for Locations.
	 * 
	 * @param xml
	 *            as request.
	 * @return xml containing business categories
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getCoupLocationBusinessCategory(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getCoupLocationBusinessCategory");
		
		String response = null;
		CouponsDetails objCouponsDetails = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objProdDetailsReq = (ProductDetailsRequest) parser.parseXmlToObject(xml);

		if (objProdDetailsReq.getUserId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			objCouponsDetails = galleryDao.getCoupLocationBusinessCategory(objProdDetailsReq);

			if (objCouponsDetails != null && !objCouponsDetails.getCouponDetail().isEmpty() && objCouponsDetails.getCouponDetail() != null) {
				objCouponsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCouponsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objCouponsDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : getCoupLocationBusinessCategory");
		return response;
	}

	/**
	 * ServiceImpl for the Adding a Coupon(to the Coupon Gallery).This method
	 * validates the input parameters and returns validation error if validation
	 * fails. calls DAO methods to add the coupons.
	 * 
	 * @param xml
	 *            contains coupon info.
	 * @return String XML with success or failure.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String couponAdd(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : couponAdd");
		
		String response = null;
		
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final CLRDetails clrAddRemove = (CLRDetails) streamHelper.parseXmlToObject(xml);
		
		if (null == clrAddRemove.getUserId() || null == clrAddRemove.getCouponId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = galleryDao.addCoupon(clrAddRemove);
			if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.ADDCOUPON);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : couponAdd");
		return response;
	}

	/**
	 * ServiceImpl for redeem coupon . This method validates the userId, if it
	 * is valid it will call the DAO method.
	 * 
	 * @param xml
	 *            for which has user id and type based on this it redeems coupon
	 * @return String containing success or failure in the response.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String userRedeemCoupon(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : userRedeemCoupon");
		
		String response = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final CouponDetails couponDetailsObj = (CouponDetails) parser.parseXmlToObject(xml);

		if (null == couponDetailsObj.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = galleryDao.userRedeemCoupon(couponDetailsObj);
			if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.COUPONREDEEMRESPONSETEXT);
			} else
				if (HubCitiConstants.CLRALLREADYREDEEMRESPONSETEXT.equalsIgnoreCase(response)) {
					response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.CLRALLREADYREDEEMRESPONSETEXT);
				} else {
					response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
				}
		}
		
		LOG.info("Exit GalleryServiceImpl : userRedeemCoupon");
		return response;
	}

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

	public String removeCoupon(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : removeCoupon");
		
		String response = null;
		
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final CLRDetails clrAddRemove = (CLRDetails) streamHelper.parseXmlToObject(xml);
		
		if (null == clrAddRemove.getUserId() || null == clrAddRemove.getCouponId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = galleryDao.removeCoupon(clrAddRemove);
			if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.REMOVE);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : removeCoupon");
		return response;
	}

	/**
	 * Method to get gallery HotDeals for All/Used/Expired in Location.
	 * 
	 * @param xml
	 *            as reqest.
	 * @return xml containing hotdeals sorted by retailers and category.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getGalleryHotDealByLocation(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getGalleryHotDealByLocation");
		
		String response = null;
		HotDealsListResultSet objHdDetails = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objCouponsRequest = (ProductDetailsRequest) parser.parseXmlToObject(xml);
		
		if (null == objCouponsRequest.getUserId() || null == objCouponsRequest.getType() || null == objCouponsRequest.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			Integer mainMenuId = null;
			if (objCouponsRequest.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(objCouponsRequest.getUserId());
				objMenuItem.setHubCitiId(objCouponsRequest.getHubCitiId());
				objMenuItem.setmItemId(objCouponsRequest.getmItemId());
				objMenuItem.setBottomBtnId(objCouponsRequest.getBottomBtnId());
				objMenuItem.setPlatform(objCouponsRequest.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objCouponsRequest.getMainMenuId();
			}

			objCouponsRequest.setMainMenuId(mainMenuId);

			objHdDetails = galleryDao.getGalleryHotDealByLocation(objCouponsRequest);

			if (objHdDetails != null && (!objHdDetails.getHdDetailsList().isEmpty() && objHdDetails.getHdDetailsList() != null)) {

				/**
				 * Sorting Hot deal by retailers and in each retailer is sorted
				 * by category for hot deals
				 */

				HotDealsListResultSet objHotDealResultRetSorted = new HotDealsListResultSet();
				objHotDealResultRetSorted = MygalleryHelper.sortHotDealsListByRetailer(objHdDetails.getHdDetailsList());
				HotDealsListResultSet objHotDealsSorted = new HotDealsListResultSet();
				ArrayList<RetailersDetails> retDetailsList = new ArrayList<RetailersDetails>();
				RetailersDetails objRetailerDetails = null;
				HotDealsListResultSet objHotDealsResult = null;

				/**
				 * For sorting HotDeals by distance for each retailer
				 */
				for (int i = 0; i < objHotDealResultRetSorted.getRetDetailsList().size(); i++) {
					objRetailerDetails = new RetailersDetails();
					objHotDealsResult = new HotDealsListResultSet();
					objHotDealsResult = MygalleryHelper.getHotDealsListByRetailerLoc(objHotDealResultRetSorted.getRetDetailsList().get(i)
							.gethDDetailsList());
					objRetailerDetails.setRetName(objHotDealResultRetSorted.getRetDetailsList().get(i).getRetName());
					objRetailerDetails.setRetId(objHotDealResultRetSorted.getRetDetailsList().get(i).getRetId());
					objRetailerDetails.setRetDetailsList((ArrayList<RetailersDetails>) objHotDealsResult.getRetDetailsList());
					retDetailsList.add(objRetailerDetails);
				}
				objHotDealsSorted.setRetDetailsList(retDetailsList);
				objHotDealsSorted.setMaxCnt(objHdDetails.getMaxCnt());
				objHotDealsSorted.setMainMenuId(mainMenuId);
				objHotDealsSorted.setNextPage(objHdDetails.getNextPage());
				objHotDealsSorted.setMaxRowNum(objHdDetails.getMaxRowNum());

				objHotDealsSorted.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objHotDealsSorted.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objHotDealsSorted);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT, "mainMenuId",
						mainMenuId.toString());
			}
		}

		LOG.info("Exit GalleryServiceImpl : getGalleryHotDealByLocation");
		return response;
	}

	/**
	 * Method to get gallery Hot deals for All/Used/Expired in Products.
	 * 
	 * @param xml
	 *            as reqest.
	 * @return xml containing hotdeals sorted by category
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getGalleryHotDealByProduct(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getGalleryHotDealByProduct");
		
		String response = null;
		HotDealsListResultSet objHdDetails = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objCouponsRequest = (ProductDetailsRequest) parser.parseXmlToObject(xml);
		
		if (objCouponsRequest.getUserId() == null || objCouponsRequest.getType() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			// To get mainMenuID from moduleID
			Integer mainMenuId = null;
			if (objCouponsRequest.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(objCouponsRequest.getUserId());
				objMenuItem.setHubCitiId(objCouponsRequest.getHubCitiId());
				objMenuItem.setmItemId(objCouponsRequest.getmItemId());
				objMenuItem.setBottomBtnId(objCouponsRequest.getBottomBtnId());
				objMenuItem.setPlatform(objCouponsRequest.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objCouponsRequest.getMainMenuId();
			}

			objCouponsRequest.setMainMenuId(mainMenuId);

			objHdDetails = galleryDao.getGalleryHotDealByProduct(objCouponsRequest);

			// To sort Hotdeals by category
			if (objHdDetails != null && (!objHdDetails.getHdDetailsList().isEmpty() && objHdDetails.getHdDetailsList() != null)) {
				HotDealsListResultSet objHotDealResultSetSorted = new HotDealsListResultSet();
				objHotDealResultSetSorted = MygalleryHelper.sortHotDealsListByCategory(objHdDetails.getHdDetailsList());
				objHotDealResultSetSorted.setMaxCnt(objHdDetails.getMaxCnt());
				objHotDealResultSetSorted.setMainMenuId(mainMenuId);
				objHotDealResultSetSorted.setNextPage(objHdDetails.getNextPage());
				objHotDealResultSetSorted.setMaxRowNum(objHdDetails.getMaxRowNum());
				objHotDealResultSetSorted.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objHotDealResultSetSorted.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objHotDealResultSetSorted);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT, "mainMenuId",
						mainMenuId.toString());
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : getGalleryHotDealByProduct");
		return response;
	}

	/**
	 * ServiceImpl method to get gallery coupons for All/Used/Expired in
	 * Location.
	 * 
	 * @param xml
	 *            as reqest.
	 * @return xml containing coupons
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getGalleryCouponsByLocation(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getGalleryCouponsByLocation");
		
		String response = null;
		CouponsDetails objCoupDetailsResponse = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objCouponsRequest = (ProductDetailsRequest) parser.parseXmlToObject(xml);
		
		if (null == objCouponsRequest.getUserId() || null == objCouponsRequest.getType() || null == objCouponsRequest.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			Integer mainMenuId = null;
			if (objCouponsRequest.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(objCouponsRequest.getUserId());
				objMenuItem.setHubCitiId(objCouponsRequest.getHubCitiId());
				objMenuItem.setmItemId(objCouponsRequest.getmItemId());
				objMenuItem.setBottomBtnId(objCouponsRequest.getBottomBtnId());
				objMenuItem.setPlatform(objCouponsRequest.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objCouponsRequest.getMainMenuId();
			}

			objCouponsRequest.setMainMenuId(mainMenuId);
			objCoupDetailsResponse = galleryDao.getGalleryCouponsByLocation(objCouponsRequest);
			if (objCoupDetailsResponse != null
					&& (!objCoupDetailsResponse.getCouponDetail().isEmpty() && objCoupDetailsResponse.getCouponDetail() != null)) {

				// Sorting retailers and in each retailers coupon are sorted by
				// category
				CouponsDetails objCouponsDetailsRetSort = new CouponsDetails();
				objCouponsDetailsRetSort = MygalleryHelper.sortAllCouponsRetailersByNameForLocation(objCoupDetailsResponse.getCouponDetail());
				CouponsDetails objCouponDetailsSorted = new CouponsDetails();
				ArrayList<RetailersDetails> retDetailsList = new ArrayList<RetailersDetails>();
				RetailersDetails objRetDetails = null;
				CouponsDetails coupsDetails = null;
				// For sorting coupons by distance for each retailer
				for (int i = 0; i < objCouponsDetailsRetSort.getRetDetailsList().size(); i++) {
					coupsDetails = new CouponsDetails();
					objRetDetails = new RetailersDetails();
					coupsDetails = MygalleryHelper.sortRetailerCoupByDist(objCouponsDetailsRetSort.getRetDetailsList().get(i).getCouponDetailsList());
					objRetDetails.setRetName(objCouponsDetailsRetSort.getRetDetailsList().get(i).getRetName());
					objRetDetails.setRetId(objCouponsDetailsRetSort.getRetDetailsList().get(i).getRetId());
					objRetDetails.setRetLocId(objCouponsDetailsRetSort.getRetDetailsList().get(i).getRetLocId());
					objRetDetails.setRetDetailsList(coupsDetails.getRetDetailsList());
					retDetailsList.add(objRetDetails);
				}
				objCouponDetailsSorted.setRetDetailsList(retDetailsList);
				objCouponDetailsSorted.setNextPageFlag(objCoupDetailsResponse.getNextPageFlag());
				objCouponDetailsSorted.setMainMenuId(mainMenuId);
				objCouponDetailsSorted.setMaxCnt(objCoupDetailsResponse.getMaxCnt());
				objCouponDetailsSorted.setMaxRowNum(objCoupDetailsResponse.getMaxRowNum());
				response = XstreamParserHelper.produceXmlFromObject(objCouponDetailsSorted);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT, "mainMenuId",
						mainMenuId.toString());
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : getGalleryCouponsByLocation");
		return response;
	}

	/**
	 * Method to get gallery coupons for All/Used/Expired in Products.
	 * 
	 * @param xml
	 *            as reqest.
	 * @return xml containing coupons sorted by category
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getGalleryCouponsByProduct(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getGalleryCouponsByProduct");
		
		String response = null;
		CouponsDetails objCoupDetailsResponse = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetailsRequest objCouponsRequest = (ProductDetailsRequest) parser.parseXmlToObject(xml);
		
		if (objCouponsRequest.getUserId() == null || objCouponsRequest.getType() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			Integer mainMenuId = null;
			if (objCouponsRequest.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(objCouponsRequest.getUserId());
				objMenuItem.setHubCitiId(objCouponsRequest.getHubCitiId());
				objMenuItem.setmItemId(objCouponsRequest.getmItemId());
				objMenuItem.setBottomBtnId(objCouponsRequest.getBottomBtnId());
				objMenuItem.setPlatform(objCouponsRequest.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objCouponsRequest.getMainMenuId();
			}
			objCouponsRequest.setMainMenuId(mainMenuId);
			objCoupDetailsResponse = galleryDao.getGalleryCouponsByProduct(objCouponsRequest);

			// To sort coupons by category
			if (objCoupDetailsResponse != null
					&& (!objCoupDetailsResponse.getCouponDetail().isEmpty() && objCoupDetailsResponse.getCouponDetail() != null)) {
				CouponsDetails objCouponDetails = new CouponsDetails();
				objCouponDetails = MygalleryHelper.sortAllCouponsByCatForProduct(objCoupDetailsResponse.getCouponDetail());
				objCouponDetails.setNextPageFlag(objCoupDetailsResponse.getNextPageFlag());
				objCouponDetails.setMainMenuId(mainMenuId);
				objCouponDetails.setMaxCnt(objCoupDetailsResponse.getMaxCnt());
				objCouponDetails.setMaxRowNum(objCoupDetailsResponse.getMaxRowNum());
				response = XstreamParserHelper.produceXmlFromObject(objCouponDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT, "mainMenuId",
						mainMenuId.toString());
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : getGalleryCouponsByProduct");
		return response;
	}

	/**
	 * This service method to get deals present in the HubCiti (Coupon, Special
	 * Offer, Hot Deal, Sale).
	 * 
	 * @param xml
	 * @return xml.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getHubCitiSpecialDeals(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getHubCitiSpecialDeals");
		
		String response = null;
		
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ProductDetailsRequest productDetailsRequest = (ProductDetailsRequest) streamHelper.parseXmlToObject(xml);

		if (null == productDetailsRequest.getUserId() || null == productDetailsRequest.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			/**
			 * retailer special offers list.
			 */
			ProductDetails productDetailsObj = null;
			productDetailsObj = galleryDao.getHubCitiSpecialDeals(productDetailsRequest);
			if (productDetailsObj != null) {
				productDetailsObj.setResponseCode(HubCitiConstants.SUCCESSCODE);
				productDetailsObj.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(productDetailsObj);

				response = response.replaceAll("<RetailerDetail>", "<SpecialOffer>");
				response = response.replaceAll("</RetailerDetail>", "</SpecialOffer>");
				response = response.replaceAll("<ProductDetails>", "<SpecialOffersList>");
				response = response.replaceAll("</ProductDetails>", "</SpecialOffersList>");
				response = response.replaceAll("<specialOfferlst>", "");
				response = response.replaceAll("</specialOfferlst>", "");

			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		LOG.info("Exit GalleryServiceImpl : getHubCitiSpecialDeals");
		return response;
	}

	/**
	 * ServiceImpl method is to get Special offer retailer display.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	public String getSpecialOffRetDisplay(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getSpecialOffRetDisplay");
		
		String response = null;
		String completeAddress;
		
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper.parseXmlToObject(xml);
		
		if (null == thisLocationRequest.getUserId() || null == thisLocationRequest.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}
		if (null == thisLocationRequest.getLowerLimit()) {
			thisLocationRequest.setLowerLimit(0);
		}
		final RetailersDetails retailsDetails = galleryDao.getSpecialOffRetDisplay(thisLocationRequest);
		List<Retailer> arSPecialOffRetDispList = null;
		if (null != retailsDetails) {
			final List<RetailerDetail> retailerDetail = retailsDetails.getRetailerDetail();
			if (!retailerDetail.isEmpty()) {
				arSPecialOffRetDispList = new ArrayList<Retailer>();
				for (int i = 0; i < retailerDetail.size(); i++) {
					final Retailer objSPecialOffRetDisp = new Retailer();
					completeAddress = new String();
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getRetaileraddress1()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getRetaileraddress1())) {
						completeAddress = retailerDetail.get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getCity()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getCity())) {
						completeAddress = completeAddress + retailerDetail.get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getPostalCode()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getPostalCode())) {
						completeAddress = completeAddress + retailerDetail.get(i).getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getState()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getState())) {
						completeAddress = completeAddress + retailerDetail.get(i).getState();
					}

					objSPecialOffRetDisp.setRowNumber(retailerDetail.get(i).getRowNumber());
					objSPecialOffRetDisp.setRetailerId(retailerDetail.get(i).getRetailerId());
					objSPecialOffRetDisp.setRetailerName(retailerDetail.get(i).getRetailerName());
					objSPecialOffRetDisp.setRetailLocationId(retailerDetail.get(i).getRetailLocationId());
					objSPecialOffRetDisp.setRetailAddress(completeAddress);
					objSPecialOffRetDisp.setDistance(retailerDetail.get(i).getDistance());
					objSPecialOffRetDisp.setRetailImgPath(retailerDetail.get(i).getRetailImgPath());
					arSPecialOffRetDispList.add(objSPecialOffRetDisp);
				}
			}
			retailsDetails.setRetailerDetail(null);
			retailsDetails.setRetailers(arSPecialOffRetDispList);
			retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
			retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			response = XstreamParserHelper.produceXmlFromObject(retailsDetails);
		} else {
			response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
		}
		
		LOG.info("Exit GalleryServiceImpl : getSpecialOffRetDisplay");
		return response;
	}

	/**
	 * ServiceImpl method is to get Sales retailer display.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	public String getSalesRetDisplay(String xml) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getSalesRetDisplay");
		
		String response = null;
		String completeAddress;
		
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper.parseXmlToObject(xml);
		
		if (thisLocationRequest.getUserId() == null || thisLocationRequest.getHubCitiId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}
		if (null == thisLocationRequest.getLowerLimit()) {
			thisLocationRequest.setLowerLimit(0);
		}
		final RetailersDetails retailsDetails = galleryDao.getSalesRetDisplay(thisLocationRequest);
		List<Retailer> arSPecialOffRetDispList = null;
		if (null != retailsDetails) {
			final List<RetailerDetail> retailerDetail = retailsDetails.getRetailerDetail();
			if (!retailerDetail.isEmpty()) {
				arSPecialOffRetDispList = new ArrayList<Retailer>();
				for (int i = 0; i < retailerDetail.size(); i++) {
					final Retailer objSPecialOffRetDisp = new Retailer();
					completeAddress = new String();
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getRetaileraddress1()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getRetaileraddress1())) {
						completeAddress = retailerDetail.get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getCity()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getCity())) {
						completeAddress = completeAddress + retailerDetail.get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getPostalCode()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getPostalCode())) {
						completeAddress = completeAddress + retailerDetail.get(i).getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getState()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getState())) {
						completeAddress = completeAddress + retailerDetail.get(i).getState();
					}

					objSPecialOffRetDisp.setRowNumber(retailerDetail.get(i).getRowNumber());
					objSPecialOffRetDisp.setRetailerId(retailerDetail.get(i).getRetailerId());
					objSPecialOffRetDisp.setRetailerName(retailerDetail.get(i).getRetailerName());
					objSPecialOffRetDisp.setRetailLocationId(retailerDetail.get(i).getRetailLocationId());
					objSPecialOffRetDisp.setRetailAddress(completeAddress);
					objSPecialOffRetDisp.setDistance(retailerDetail.get(i).getDistance());
					arSPecialOffRetDispList.add(objSPecialOffRetDisp);
				}
			}
			retailsDetails.setRetailerDetail(null);
			retailsDetails.setRetailers(arSPecialOffRetDispList);
			retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
			retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			response = XstreamParserHelper.produceXmlFromObject(retailsDetails);
		} else {
			response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
		}
		
		LOG.info("Exit GalleryServiceImpl : getSalesRetDisplay");
		return response;
	}
	

	/**
	 * ServiceImpl for the Adding a Coupon(to the Coupon Gallery).
	 * 
	 * @param strJsonReq
	 *            contains coupon info.
	 * @return String with success or failure.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String couponAddJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : couponAddJson");

		String strJsonResponse = null;

		try {
			
			final Gson gson = new Gson();
			final CLRDetails clrAddRemove = gson.fromJson(strJsonReq, CLRDetails.class);

			if (null == clrAddRemove.getUserId() || null == clrAddRemove.getCouponId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				strJsonResponse = galleryDao.addCoupon(clrAddRemove);
				if (HubCitiConstants.SUCCESS.equalsIgnoreCase(strJsonResponse)) {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.ADDCOUPON);
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
				}
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : couponAddJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit GalleryServiceImpl : couponAddJson");
		return strJsonResponse;
	}
	

	/**
	 * ServiceImpl for redeem coupon.
	 * 
	 * @param strJsonReq
	 *            contains coupon info.
	 * @return String with success or failure.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String userRedeemCouponJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : userRedeemCouponJson");

		String strJsonResponse = null;

		try {

			final Gson gson = new Gson();
			final CouponDetails objCouponDetail = gson.fromJson(strJsonReq, CouponDetails.class);

			if (null == objCouponDetail.getUserId() || null == objCouponDetail.getCouponId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				strJsonResponse = galleryDao.userRedeemCoupon(objCouponDetail);
				if (HubCitiConstants.CLRALLREADYREDEEMRESPONSETEXT.equalsIgnoreCase(strJsonResponse)) {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.CLRALLREADYREDEEMRESPONSETEXT);
				}else if (HubCitiConstants.COUPONREDEEMRESPONSETEXT.equalsIgnoreCase(strJsonResponse)){
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.COUPONREDEEMRESPONSETEXT);
				}
				else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
				}
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  GalleryServiceImpl : userRedeemCouponJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit GalleryServiceImpl : userRedeemCouponJson");
		return strJsonResponse;
	}
	
	/**
	 * The ServiceImpl method for displaying  special offer list for location's.
	 * 
	 * @param strJsonReq input
	 * @return JSON as containing Special offer location list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	
	public String getCouponsLocationListJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getCouponsLocationListJson");

		String strJsonResponse = null;

		try {
			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final RetailerDetail objRetDetail = gson.fromJson(strJsonReq, RetailerDetail.class);


			if (null == objRetDetail.getUserId() || null == objRetDetail.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				if ("".equals(Utility.checkNull(objRetDetail.getSortColumn()))) {
					objRetDetail.setSortColumn("Distance");
				}
				
				if ("".equals(Utility.checkNull(objRetDetail.getSortOrder())) || (!"DESC".equalsIgnoreCase(objRetDetail.getSortOrder()))) {
					objRetDetail.setSortOrder("ASC");
				}
				
				final RetailersDetails retailersDetails = galleryDao.getCouponsLocationListJson(objRetDetail);

				if (retailersDetails != null&&
						((null!=retailersDetails.getFeaturedCouponsList()&&!retailersDetails.getFeaturedCouponsList().isEmpty())||
						(null!=retailersDetails.getNonFeaturedCouponsList()&&!retailersDetails.getNonFeaturedCouponsList().isEmpty()))) {
					retailersDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailersDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					
					if(null!=objRetDetail.getIsFeatOrNonFeat()&&objRetDetail.getIsFeatOrNonFeat()==1)
						retailersDetails.setFeaturedCouponsList(null);
					else if(null!=objRetDetail.getIsFeatOrNonFeat()&&objRetDetail.getIsFeatOrNonFeat()==0){
						retailersDetails.setNonFeaturedCouponsList(null);
						retailersDetails.setMaxCnt(retailersDetails.getmRow());
						retailersDetails.setMaxRowNum(retailersDetails.getmRow());
						retailersDetails.setNextPage(0);
						
					}
					retailersDetails.setmRow(null);			
					strJsonResponse = gson.toJson(retailersDetails);
				} else if(retailersDetails != null){
					retailersDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					retailersDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					strJsonResponse = gson.toJson(retailersDetails);
				}else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  GalleryServiceImpl : getCouponsLocationListJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit GalleryServiceImpl : getCouponsLocationListJson");
		return strJsonResponse;
	}

	
	/**
	 * The ServiceImpl method for displaying  coupons list for map location's.
	 * 
	 * @param strJsonReq input
	 * @return JSON as containing coupons for map location's.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getCouponsLocationMapListJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getCouponsLocationMapListJson");

		String strJsonResponse = null;

		try {
			final Gson gson = new Gson();
			final RetailerDetail objRetDetail = gson.fromJson(strJsonReq, RetailerDetail.class);
			RetailersDetails retailersDetail = new RetailersDetails();
			
			if (null == objRetDetail.getUserId() || null == objRetDetail.getHubCitiId() || "".equals(Utility.checkNull(objRetDetail.getCouponIds()))) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				final RetailersDetails retailersDetails = galleryDao.getCouponsLocationMapListJson(objRetDetail);

				if (retailersDetails != null 
						 && null!=retailersDetails.getCouponMapLocs()
						 && !retailersDetails.getCouponMapLocs().isEmpty()) {
				
					retailersDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailersDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					retailersDetail.setCouponMapLocs(retailersDetails.getCouponMapLocs());
					strJsonResponse = gson.toJson(retailersDetail);
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  GalleryServiceImpl : getCouponsLocationMapListJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit GalleryServiceImpl : getCouponsLocationMapListJson");
		return strJsonResponse;
	}
	
	
	/**
	 * The ServiceImpl method for displaying  My Accounts list for location's.
	 * 
	 * @param strJsonReq input
	 * @return JSON as containing My Accounts list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getMyAccountsJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside GalleryServiceImpl : getMyAccountsJson");

		String strJsonResponse = null;

		try {
			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final RetailerDetail objRetDetail = gson.fromJson(strJsonReq, RetailerDetail.class);


			if (null == objRetDetail.getUserId() || null == objRetDetail.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				if ("".equals(Utility.checkNull(objRetDetail.getSortColumn()))) {
					objRetDetail.setSortColumn("Distance");
				}
				
				if ("".equals(Utility.checkNull(objRetDetail.getSortOrder())) || (!"DESC".equalsIgnoreCase(objRetDetail.getSortOrder()))) {
					objRetDetail.setSortOrder("ASC");
				}
				
				final RetailersDetails retailersDetails = galleryDao.getMyAccountsJson(objRetDetail);

				if (retailersDetails != null&&
						((null!=retailersDetails.getClaimedCouponsList()&&!retailersDetails.getClaimedCouponsList().isEmpty())||
						(null!=retailersDetails.getRedeemedCouponsList()&&!retailersDetails.getRedeemedCouponsList().isEmpty())||
						(null!=retailersDetails.getExpiredCouponsList()&&!retailersDetails.getExpiredCouponsList().isEmpty()))) {
					retailersDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailersDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					
					
					List<Coupons> couponsList=new ArrayList<Coupons>();
									
					
					if(null!=retailersDetails.getClaimedCouponsList()&&!retailersDetails.getClaimedCouponsList().isEmpty()){
						Coupons coupon=new Coupons();
						coupon.setType("Saved");
						coupon.setList(retailersDetails.getClaimedCouponsList());
						couponsList.add(coupon);
					}
					if(null!=retailersDetails.getRedeemedCouponsList()&&!retailersDetails.getRedeemedCouponsList().isEmpty()){
						Coupons coupon=new Coupons();
						coupon.setType("Redeemed");
						coupon.setList(retailersDetails.getRedeemedCouponsList());
						couponsList.add(coupon);
					}
					if(null!=retailersDetails.getExpiredCouponsList()&&!retailersDetails.getExpiredCouponsList().isEmpty()){
						Coupons coupon=new Coupons();
						coupon.setType("Expired");
						coupon.setList(retailersDetails.getExpiredCouponsList());
						couponsList.add(coupon);
					}
					retailersDetails.setCouponList(couponsList);
					retailersDetails.setClaimedCouponsList(null);
					retailersDetails.setRedeemedCouponsList(null);
					retailersDetails.setExpiredCouponsList(null);
					strJsonResponse = gson.toJson(retailersDetails);
				} else if(retailersDetails != null){
					retailersDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					retailersDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					strJsonResponse = gson.toJson(retailersDetails);
				}else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  GalleryServiceImpl : getMyAccountsJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit GalleryServiceImpl : getMyAccountsJson");
		return strJsonResponse;
	}

}
