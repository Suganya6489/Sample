package com.hubciti.find.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.google.gson.Gson;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.externalapi.ExternalAPIManager;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.CLRDetails;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.Filter;
import com.hubciti.common.pojos.FindNearByIntactResponse;
import com.hubciti.common.pojos.GoogleCategory;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.ProductRatingReview;
import com.hubciti.common.pojos.ProductReview;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.UpdateUserInfo;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.pojos.UserRatingInfo;
import com.hubciti.common.utility.Utility;
import com.hubciti.find.dao.FindDao;
import com.hubciti.firstuse.dao.FirstUseDao;
import com.hubciti.ratereview.dao.RateReviewDao;
import com.scansee.externalapi.common.constants.ApplicationConstants;
import com.scansee.externalapi.common.findnearby.pojos.FindNearByRequest;
import com.scansee.externalapi.common.pojos.ExternalAPIErrorLog;
import com.scansee.externalapi.common.pojos.ExternalAPIInformation;
import com.scansee.externalapi.findNearBy.FindNearByService;
import com.scansee.externalapi.findNearBy.FindNearByServiceImpl;
import com.scansee.externalapi.findplace.FindPlaceService;
import com.scansee.externalapi.findplace.FindPlaceserviceImpl;
import com.scansee.externalapi.google.pojos.ServiceSearchResponse;
import com.scansee.externalapi.google.pojos.ServiceSerachRequest;
import com.scansee.externalapi.onlinestores.OnlineStoresService;
import com.scansee.externalapi.onlinestores.OnlineStoresServiceImpl;
import com.scansee.externalapi.shopzilla.pojos.OnlineStores;
import com.scansee.externalapi.shopzilla.pojos.OnlineStoresMetaData;
import com.scansee.externalapi.shopzilla.pojos.OnlineStoresRequest;

public class FindServiceImpl implements FindService {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FindServiceImpl.class);

	/**
	 * Instance variable for FindDao.
	 */
	public FindDao findDao;

	/**
	 * Instance variable for firstUse DAO instance.
	 */
	private FirstUseDao firstUseDao;

	/**
	 * Instance variable for RateReview DAO instance.
	 */
	private RateReviewDao rateReviewDao;

	/**
	 * holds the number of online Stores values;
	 */

	private String totalOnlineStores = null;

	/**
	 * holds minimum price
	 */
	private String minimumPrice = null;

	/**
	 * Setter method for FindDAO.
	 * 
	 * @param firstUseDao
	 *            Instance of type FirstUseDAO.
	 */
	public void setFindDao(FindDao findDao) {
		this.findDao = findDao;
	}

	/**
	 * sets the FirstUseDAO
	 * 
	 * @param firstUseDao
	 *            The instance for FirstUseDAO
	 */
	public void setFirstUseDao(FirstUseDao firstUseDao) {
		this.firstUseDao = firstUseDao;
	}

	/**
	 * sets the RateReviewDao
	 * 
	 * @param firstUseDao
	 *            The instance for FirstUseDAO
	 */
	public void setRateReviewDao(RateReviewDao rateReviewDao) {
		this.rateReviewDao = rateReviewDao;
	}



	/**
	 * This is a RestEasyImpl WebService Method to find retailer for ScanSee
	 * data.
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing the ScanSee data search details in the response.
	 */
	public String findScanSeeRetSearch(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findScanSeeRetSearch");

		String strResponse = null;
		String strCompleteAddress = null;
		RetailersDetails retailerDetails = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail retailDetail = (RetailerDetail) streamHelper.parseXmlToObject(xml);

		if (null == retailDetail.getUserId()) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERIDNOTFOUND);
		} else if (null == retailDetail.getHubCitiId()) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (null == retailDetail.getmItemId() && null == retailDetail.getBottomBtnId() && null == retailDetail.getMainMenuId()) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if ( null != retailDetail.getSearchKey() && "".equals(Utility.checkNull(retailDetail.getSearchKey()))) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Please enter valid search key");
		} else {

			Integer mainMenuId = null;
			if (retailDetail.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setmItemId(retailDetail.getmItemId());
				objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = retailDetail.getMainMenuId();
			}
			retailDetail.setMainMenuId(mainMenuId);

			/*
			 	Condition to select Grouping Type 
			 	1. If groupBy is atoz or type- grouping is done accordingly. 
			 	2. If groupBy is unknown value and null- no grouping is done, ie N/A is named initially
			 	   and in response groupContent will have empty string.
			 */
			if (!"atoz".equalsIgnoreCase(retailDetail.getGroupBy()) && !"type".equalsIgnoreCase(retailDetail.getGroupBy())
					|| "".equals(Utility.checkNull(retailDetail.getGroupBy()))) {
				retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			} 

			/*
			   Condition to select Sorting Type 
			    1. If sortBy is null- sorting is done by retailer name. 
			    2. If sortBy is name- sorting is done by retailer name. 
			    3. If sortBy is distance 
			         a. Sorting is done by distance 
			         b. No grouping is done, hence groupBy is changed to N/A.
			 */

			if ( null == retailDetail.getSortColumn()) {

				retailDetail.setSortColumn("Distance");
			}


			//			After filter implementation

			/*if (retailDetail.getSortColumn() != null) {
				if ("name".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("RetailerName");
				} else if ("city".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("city");
				} else {
					retailDetail.setSortColumn("Distance");
//					retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				}
			} else {
				retailDetail.setSortColumn("Distance");
//				retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			}*/

			//For sorting in ascending or descending order.
			if (retailDetail.getSortOrder() == null || (!"DESC".equalsIgnoreCase(retailDetail.getSortOrder()))) {
				retailDetail.setSortOrder("ASC");
			}

			retailerDetails = findDao.findScanSeeRetSearch(retailDetail);

			//			RetailersDetails retailerGrpSort = new RetailersDetails();

			if (retailerDetails != null && retailerDetails.getRetailerDetail() != null
					&& !retailerDetails.getRetailerDetail().isEmpty()) {

				strCompleteAddress = new String();

				for ( int i = 0; i< retailerDetails.getRetailerDetail().size(); i++ ) {
					if (null !=  retailerDetails.getRetailerDetail().get(i).getRetaileraddress1()
							&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getRetaileraddress1())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getRetaileraddress1())) {
						strCompleteAddress = retailerDetails.getRetailerDetail().get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailerDetails.getRetailerDetail().get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getCity())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getCity())) {
						strCompleteAddress = strCompleteAddress +  retailerDetails.getRetailerDetail().get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailerDetails.getRetailerDetail().get(i).getPostalCode()
							&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getPostalCode())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getPostalCode())) {
						strCompleteAddress = strCompleteAddress +  retailerDetails.getRetailerDetail().get(i).getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailerDetails.getRetailerDetail().get(i).getState() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getState())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getState())) {
						strCompleteAddress = strCompleteAddress +  retailerDetails.getRetailerDetail().get(i).getState();
					}
					retailerDetails.getRetailerDetail().get(i).setCompleteAddress(strCompleteAddress.trim());
				}
				/*retailerDetails.setGroupBy(retailDetail.getGroupBy());
				retailerDetails.setSortColumn(retailDetail.getSortColumn());
				retailerDetails.setSortOrder(retailDetail.getSortOrder());

				retailerGrpSort = FindHelper.groupAndSortRetailers(retailerDetails);

				retailerDetails.setRetailerDetail(null);
				retailerDetails.setCatList(retailerGrpSort.getCatList());*/

				/*if (HubCitiConstants.NOTAPPLICABLE.equals(retailerDetails.getGroupBy())) {
					retailerDetails.getCatList().get(0).setGroupContent("");
				}*/

				retailerDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailerDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				retailerDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				retailerDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}


			/*retailerDetails.setGroupBy(null);
			retailerDetails.setSortColumn(null);
			retailerDetails.setSortOrder(null);*/

			retailerDetails.setMainMenuId(mainMenuId);

			strResponse = XstreamParserHelper.produceXmlFromObject(retailerDetails);
		}

		LOG.info("Exit FindServiceImpl : findScanSeeRetSearch");
		return strResponse;
	}

	/**
	 * This is a RestEasyImpl WebService Method to find retailer category for
	 * ScanSee data. retailers from ScanSee given coordinates.
	 * 
	 * @param xml
	 * @return xml containing retailer list
	 */
	public String findScanSeeCategorySearch(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findScanSeeCategorySearch");

		String response = null;
		String strCompleteAddress;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail retailDetail = (RetailerDetail) streamHelper.parseXmlToObject(xml);

		if (retailDetail.getHubCitiId() == null || retailDetail.getCatName() == null || "".equals(retailDetail.getCatName())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (retailDetail.getmItemId() == null && retailDetail.getBottomBtnId() == null && retailDetail.getMainMenuId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTCODE);
		} else {
			RetailersDetails retailersDetails = null;

			Integer mainMenuId = null;
			if (retailDetail.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setmItemId(retailDetail.getmItemId());
				objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = retailDetail.getMainMenuId();
			}
			retailDetail.setMainMenuId(mainMenuId);


			/*
			 	Condition to select Grouping Type 
			 	1. If groupBy is atoz or type- grouping is done accordingly. 
			 	2. If groupBy is unknown value or null - no grouping is done, ie N/A is named initially
			 	   and in response groupContent will have empty string.
			 */
			if (!"atoz".equalsIgnoreCase(retailDetail.getGroupBy()) && !"type".equalsIgnoreCase(retailDetail.getGroupBy())
					|| "".equals(Utility.checkNull(retailDetail.getGroupBy()))) {
				retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			} 

			/*
			   Condition to select Sorting Type 
			    1. If sortBy is null- sorting is done by retailer name. 
			    2. If sortBy is name- sorting is done by retailer name. 
			    3. If sortBy is distance 
			         a. Sorting is done by distance 
			         b. No grouping is done, hence groupBy is changed to N/A.
			 */
			if ( null == retailDetail.getSortColumn()) {
				retailDetail.setSortColumn("Distance");
				retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			}

			//After filter changes   
			/*
			if (retailDetail.getSortColumn() != null) {
				if ("name".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("RetailerName");
				} else if ("city".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("city");
				} else {
					retailDetail.setSortColumn("Distance");
					//retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				}
			} else {
				retailDetail.setSortColumn("Distance");
				//retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			}
			 */			

			//For sorting in ascending or descending order.
			if (retailDetail.getSortOrder() == null || (!"DESC".equalsIgnoreCase(retailDetail.getSortOrder()))) {
				retailDetail.setSortOrder("ASC");
			}

			retailersDetails = findDao.findScanSeeCategorySearch(retailDetail);

			//			RetailersDetails retailGrpSort = new RetailersDetails();

			if (retailersDetails != null && retailersDetails.getRetailerDetail() != null
					&& !retailersDetails.getRetailerDetail().isEmpty()) {

				/*retailersDetails.setGroupBy(retailDetail.getGroupBy());
				retailersDetails.setSortColumn(retailDetail.getSortColumn());
				retailersDetails.setSortOrder(retailDetail.getSortOrder());*/
				/*
			 	Grouping on below parameter. 
			 		atoz - group by alphabetical.
			 		type - group by category. 

			    Sorting in database result set.
					name - Sort by RetailerName. 
			    	distance - Sort by Retailer distance.
			    	city - Sort by city.
				 */
				/*	retailGrpSort = FindHelper.groupAndSortRetailers(retailersDetails);

				retailersDetails.setRetailerDetail(null);
				retailersDetails.setCatList(retailGrpSort.getCatList());

				if (HubCitiConstants.NOTAPPLICABLE.equals(retailersDetails.getGroupBy())) {
					retailersDetails.getCatList().get(0).setGroupContent("");
				}*/

				strCompleteAddress = new String();

				for ( int i = 0; i< retailersDetails.getRetailerDetail().size(); i++ ) {
					if (null !=  retailersDetails.getRetailerDetail().get(i).getRetaileraddress1()
							&& !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getRetaileraddress1())
							&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getRetaileraddress1())) {
						strCompleteAddress = retailersDetails.getRetailerDetail().get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailersDetails.getRetailerDetail().get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getCity())
							&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getCity())) {
						strCompleteAddress = strCompleteAddress +  retailersDetails.getRetailerDetail().get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailersDetails.getRetailerDetail().get(i).getPostalCode()
							&& !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getPostalCode())
							&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getPostalCode())) {
						strCompleteAddress = strCompleteAddress +  retailersDetails.getRetailerDetail().get(i).getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailersDetails.getRetailerDetail().get(i).getState() && !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getState())
							&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getState())) {
						strCompleteAddress = strCompleteAddress +  retailersDetails.getRetailerDetail().get(i).getState();
					}
					retailersDetails.getRetailerDetail().get(i).setCompleteAddress(strCompleteAddress);
				}

				retailersDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailersDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				retailersDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				retailersDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			retailersDetails.setGroupBy(null);
			retailersDetails.setSortColumn(null);
			retailersDetails.setSortOrder(null);

			retailersDetails.setMainMenuId(mainMenuId);

			response = XstreamParserHelper.produceXmlFromObject(retailersDetails);
		}

		LOG.info("Exit FindServiceImpl : findScanSeeCategorySearch");
		return response;
	}

	/**
	 * The service method for searching retailer by name or keyword to fetch
	 * location details for given the coordinates and keyword.
	 * 
	 * @param strInputXml
	 *            as request.
	 * @return response based on success or failure.
	 * @throws HubCitiException
	 *             for exception.
	 */
	public String findGoogleRetSearch(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findGoogleRetSearch");

		String strResponse = null;
		double oneMileInMeter = 1609.34;
		Integer idefaultRadius = null;
		ArrayList<ServiceSearchResponse> arServiceSearchResplist = null;
		ExternalAPIInformation externalAPIInformation = new ExternalAPIInformation();

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ServiceSerachRequest objServiceSearchReq = (ServiceSerachRequest) streamHelper.parseXmlToObject(xml);

		if (null == objServiceSearchReq.getUserId() || "".equals(objServiceSearchReq.getLat()) || "".equals(objServiceSearchReq.getLng())
				|| "".equals(objServiceSearchReq.getKeyword())) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			idefaultRadius = findDao.getUserRadius(Integer.valueOf(objServiceSearchReq.getUserId()), HubCitiConstants.FINDMODULENAME);
			externalAPIInformation = getExternalAPIInformation(HubCitiConstants.FINDMODULENAME);
			if (null != externalAPIInformation.getSerchParameters() && null != externalAPIInformation.getVendorList()) {
				final ServiceSerachRequest objSearchParam = new ServiceSerachRequest();
				objSearchParam.setLocation(objServiceSearchReq.getLat() + "," + objServiceSearchReq.getLng());
				objSearchParam.setLat(objServiceSearchReq.getLat());
				objSearchParam.setLng(objServiceSearchReq.getLng());
				objSearchParam.setRadius(String.valueOf(idefaultRadius * oneMileInMeter));
				objSearchParam.setKeyword(objServiceSearchReq.getKeyword());
				objSearchParam.setPagetoken(objServiceSearchReq.getPagetoken());
				objSearchParam.setValues();
				arServiceSearchResplist = getGoogleAPIData(externalAPIInformation, objSearchParam);
			}
			if (arServiceSearchResplist != null && !arServiceSearchResplist.isEmpty()) {
				arServiceSearchResplist.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
				arServiceSearchResplist.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
				strResponse = XstreamParserHelper.produceXmlFromObject(arServiceSearchResplist);
				strResponse = strResponse.replaceAll("<list>", "");
				strResponse = strResponse.replaceAll("</list>", "");
			} else {
				strResponse = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FindServiceImpl : findScanSeeCategorySearch");
		return strResponse;
	}

	/**
	 * The service method to find retailers from Google APIs given the
	 * coordinates.
	 * 
	 * @param xml
	 *            as request
	 * @return xml containing retailer list
	 * @throws HubCitiException
	 *             for exception
	 */
	public String findGoogleCategorySearch(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findGoogleCategorySearch");

		String response = null;
		double onemileinmetere = 1609.34;
		StringBuffer catType = new StringBuffer();
		String type = null;
		Integer defaultRedius = null;
		ArrayList<ServiceSearchResponse> serviceSearchResponselst = null;

		ExternalAPIInformation externalAPIInformation = new ExternalAPIInformation();

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ServiceSerachRequest placeSearchRequest = (ServiceSerachRequest) streamHelper.parseXmlToObject(xml);

		if (null == placeSearchRequest.getUserId() || "".equals(placeSearchRequest.getLat()) || "".equals(placeSearchRequest.getLng())
				|| "".equals(placeSearchRequest.getType())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			defaultRedius = findDao.getUserRadius(Integer.valueOf(placeSearchRequest.getUserId()), HubCitiConstants.FINDMODULENAME);
			externalAPIInformation = getExternalAPIInformation(HubCitiConstants.FINDMODULENAME);
			if (null != externalAPIInformation.getSerchParameters() && null != externalAPIInformation.getVendorList()) {
				List<GoogleCategory> categorylst = findDao.getAllCategories(placeSearchRequest.getType());

				if (!categorylst.isEmpty()) {
					for (GoogleCategory googleCategory : categorylst) {
						catType.append(googleCategory.getCatName());
						catType.append("|");
					}
					type = catType.toString();
					type = type.substring(0, type.length() - 1);
				}

				final ServiceSerachRequest searchParams = new ServiceSerachRequest();
				searchParams.setLocation(placeSearchRequest.getLat() + "," + placeSearchRequest.getLng());
				searchParams.setLat(placeSearchRequest.getLat());
				searchParams.setLng(placeSearchRequest.getLng());
				searchParams.setRadius(String.valueOf(defaultRedius * onemileinmetere));
				searchParams.setType(type);
				searchParams.setPagetoken(placeSearchRequest.getPagetoken());
				searchParams.setValues();
				serviceSearchResponselst = getGoogleAPIData(externalAPIInformation, searchParams);
			}
			if (serviceSearchResponselst != null && !serviceSearchResponselst.isEmpty()) {
				serviceSearchResponselst.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
				serviceSearchResponselst.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(serviceSearchResponselst);
				response = response.replaceAll("<list>", "");
				response = response.replaceAll("</list>", "");
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FindServiceImpl : findGoogleCategorySearch");
		return response;
	}

	/**
	 * This is a Service Method for displaying audio,video and other
	 * information.
	 * 
	 * @param userId
	 *            , productId, mediaType, hubCitiId
	 * @return XML containing media information as in the response.
	 * @throws HubCitiException.
	 */
	public String getMediaInfo(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getMediaInfo");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ProductDetailsRequest objProdDetail = (ProductDetailsRequest) streamHelper.parseXmlToObject(xml);

		if (null == objProdDetail.getProductId() || "".equals(objProdDetail.getProductId())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERIDNOTFOUND);
		} else {
			ProductDetails productDetails = null;

			productDetails = findDao.getMediaInfo(objProdDetail);
			if (productDetails != null) {
				productDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				productDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(productDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FindServiceImpl : getMediaInfo");
		return response;
	}

	public String userTrackingProdMediaClick(Integer pmListId) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : userTrackingProdMediaClick");

		String response = null;

		if (null == pmListId) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			response = findDao.userTrackingProdMediaClick(pmListId);
			if (response != null) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit FindServiceImpl : userTrackingProdMediaClick");
		return response;
	}

	/**
	 * This method is used to get external API information.
	 * 
	 * @param moduleName
	 *            -Requested module name.
	 * @return ExternalAPIInformation
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public ExternalAPIInformation getExternalAPIInformation(String moduleName) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getExternalAPIInformation");

		final ExternalAPIManager externalAPIManager = new ExternalAPIManager(moduleName, findDao);
		final ExternalAPIInformation externalAPIInformation = externalAPIManager.getExternalAPIInformation();

		LOG.info("Exit FindServiceImpl : getExternalAPIInformation");
		return externalAPIInformation;
	}

	public ArrayList<ServiceSearchResponse> getGoogleAPIData(ExternalAPIInformation externalAPIInformation, ServiceSerachRequest searchParams) {
		LOG.info("Inside FindServiceImpl : getGoogleAPIData");

		ServiceSearchResponse serviceSearchResponseObj = null;
		ArrayList<ServiceSearchResponse> serviceSearchResponselst = new ArrayList<ServiceSearchResponse>();
		FindPlaceService findPlaceService = new FindPlaceserviceImpl();

		serviceSearchResponseObj = findPlaceService.processFindRequest(externalAPIInformation, searchParams);

		if (serviceSearchResponseObj != null && serviceSearchResponseObj.getPlaceSearchlst().size() != 0) {
			serviceSearchResponseObj.setPoweredby(HubCitiConstants.POWEREDBY);
			serviceSearchResponselst.add(serviceSearchResponseObj);
			String pageTokenToCheck = null;
			int pageTokenLenth = 0;

			if (serviceSearchResponseObj != null) {
				pageTokenToCheck = serviceSearchResponseObj.getPagetoken();
			}
			if (pageTokenToCheck != null) {
				pageTokenLenth = pageTokenToCheck.length();
			}

			if (serviceSearchResponseObj != null && pageTokenLenth > 1000) {
				if (!serviceSearchResponselst.isEmpty() && serviceSearchResponselst != null) {
					serviceSearchResponselst.get(0).setPagetoken(null);
				}
			} else
				if (serviceSearchResponseObj != null && pageTokenLenth > 300) {
					searchParams.setPagetoken(pageTokenToCheck);
					searchParams.setValues();
					ServiceSearchResponse serviceSearchResponseObj1 = findPlaceService.processFindRequest(externalAPIInformation, searchParams);
					if (serviceSearchResponseObj1 == null) {
						if (!serviceSearchResponselst.isEmpty() && serviceSearchResponselst != null) {
							serviceSearchResponselst.get(0).setPagetoken(null);
						}
					}
				}
			searchParams.setValues();
		}

		LOG.info("Exit FindServiceImpl : getGoogleAPIData");
		return serviceSearchResponselst;
	}

	/**
	 * This is a service Method for fetching product summary for the given input
	 * as XML. This method validates the input XML, if it is valid it will call
	 * the DAO method.
	 * 
	 * @param xml
	 *            containing input information need to be fetched the product
	 *            summary
	 * @return XML containing product summary containing product summary in the
	 *         response.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String getProductSummary(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getProductSummary");

		String localStoreResponse = null;
		String successResponseCode = "<responseCode>" + HubCitiConstants.SUCCESSCODE + "</responseCode><responseText>" + HubCitiConstants.SUCCESSTEXT
				+ "</responseText>";
		final StringBuilder finalResponse = new StringBuilder("<ProductSummary>" + successResponseCode);
		String response = null;
		final long methodStartTime = new Date().getTime();

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ProductDetailsRequest productDetailsRequest = (ProductDetailsRequest) streamHelper.parseXmlToObject(xml);

		final Integer userId = productDetailsRequest.getUserId();
		Integer retailId = productDetailsRequest.getRetailId();
		final Integer productId = productDetailsRequest.getProductId();
		final String postalcode = productDetailsRequest.getPostalcode();
		int radius = 0;

		String lat = productDetailsRequest.getLatitude();
		String lng = productDetailsRequest.getLongitude();

		Double latitude = null;
		Double longitude = null;

		if (lat != null && !"".equals(lat)) {
			latitude = Double.parseDouble(lat);
		}
		if (lng != null && !"".equals(lng)) {
			longitude = Double.parseDouble(lng);
		}
		// for user tracking
		final Integer prodListId = productDetailsRequest.getProdListId();
		final Integer mainMenuId = productDetailsRequest.getMainMenuId();
		// For user tracking
		Integer saleListId = productDetailsRequest.getSaleListId();
		//Integer scanId = productDetailsRequest.getScanId();
		Integer scanTypeId = productDetailsRequest.getScanTypeId();
		Integer hubCitiId = productDetailsRequest.getHubCitiId();

		ArrayList<AppConfiguration> radiuslst = null;
		if (null == userId || null == productId) {
			LOG.info("Request does not contain UserId/ productId ");
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}
		// Fetching user radius from App configuration table.
		radiuslst = firstUseDao.getAppConfig(HubCitiConstants.DEFAULTRADIUS);

		for (int i = 0; i < radiuslst.size(); i++) {
			if (radiuslst.get(i).getScreenName().equals(HubCitiConstants.DEFAULTRADIUS)) {
				if (radiuslst.get(i).getScreenContent() != null) {
					radius = Integer.parseInt(radiuslst.get(i).getScreenContent());
				}
			}
		}
		if (null == retailId) {
			retailId = 0;
		}
		// Get the configuration images and headers;
		final ArrayList<AppConfiguration> stockInfoList = firstUseDao.getAppConfig(HubCitiConstants.CONFIGURATIONTYPESTOCK);
		final Map<String, String> map = new HashMap<String, String>();

		for (AppConfiguration i : stockInfoList) {
			map.put(i.getScreenName(), i.getScreenContent());
		}

		// Get the product information rating,Coupons etc.
		final long locaStoreStrt11 = new Date().getTime();
		final ProductDetail productDetail = findDao.getProductDetail(userId, productId, retailId, saleListId, prodListId, mainMenuId, scanTypeId,
				hubCitiId);

		if (null != productDetail) {
			final long locaStoreEnd11 = new Date().getTime();
			LOG.info("Total Time taken for fetching product details{}", (locaStoreEnd11 - locaStoreStrt11) / 1000);
			String productInfoRespone = XstreamParserHelper.produceXmlFromObject(productDetail);
			if (productInfoRespone != null) {
				finalResponse.append(productInfoRespone);
			}

			// First get the LocalStore information from database.
			final long locaStoreStrt1 = new Date().getTime();
			// final FindNearByIntactResponse findNearByDetailsResultSet =
			// findDao.fetchNearByLowestPrice(userId, latitude, productId,
			// longitude, postalcode, radius, mainMenuId, hubCitiId);
			final FindNearByIntactResponse findNearByDetailsResultSet = findRetailersLocalDataBase(userId, latitude, productId, longitude,
					postalcode, radius, mainMenuId, hubCitiId);
			final long locaStoreEnd1 = new Date().getTime();
			LOG.info("Total Time taken for the Local Store for local database{}", (locaStoreEnd1 - locaStoreStrt1) / 1000);

			if (null != findNearByDetailsResultSet.getFindNearByDetails() && null != findNearByDetailsResultSet.getFindNearByMetaData()) {
				localStoreResponse = XstreamParserHelper.produceXmlFromObject(findNearByDetailsResultSet);
				// LOG.info("Inside IF: 101, Response: localStoreResponse is " +
				// localStoreResponse);
			} else {
				final long locaStoreStrt = new Date().getTime();
				localStoreResponse = findRetailersExternalAPI(userId, productId, latitude, longitude, radius, 0, postalcode);
				final long locaStoreEnd = new Date().getTime();
				LOG.info("Total Time taken for the Local Store {}", (locaStoreEnd - locaStoreStrt) / 1000);
				// LOG.info("Inside ELSE: 101, Response: localStoreResponse is "
				// + localStoreResponse);
			}

			if (localStoreResponse != null) {
				localStoreResponse = localStoreResponse.replaceAll(
						"<FindNearByDetailsResultSet>",
						"<FindNearByDetailsResultSet><stockImagePath>" + map.get(HubCitiConstants.NEARBYIMAGE) + "</stockImagePath><stockHeader>"
								+ map.get(HubCitiConstants.NEARBYHEADER) + "</stockHeader>" + "<stockImgPathDir>"
								+ map.get(HubCitiConstants.GETDIRIMAGE) + "</stockImgPathDir><stockHeaderDir>"
								+ map.get(HubCitiConstants.GETDIRHEADER) + "</stockHeaderDir>" + "<stockImgPathWebSite>"
								+ map.get(HubCitiConstants.BROWSEWEBSITEIMAGE) + "</stockImgPathWebSite><stockHeaderWebSite>"
								+ map.get(HubCitiConstants.BROWSEWEBSITEHEADER) + "</stockHeaderWebSite>");
				finalResponse.append(localStoreResponse);
				// LOG.info("Inside IF: 102, Response: localStoreResponse is " +
				// localStoreResponse);
			}
			LOG.info("Response returned by LocalStore API \n {}", localStoreResponse);

			// Request for Call to Online Stores External API.
			final OnlineStoresRequest onlineStoresRequest = new OnlineStoresRequest();
			onlineStoresRequest.setProductId(productId);
			onlineStoresRequest.setUserId(String.valueOf(userId));
			onlineStoresRequest.setPageStart(String.valueOf(0));
			final long locaStoreStrt = new Date().getTime();

			// Call External API for Online Stores.
			String onLineResponse = getOnlineStoreData(onlineStoresRequest);
			// onLineResponse = Utility.removeSpecialChars(onLineResponse);
			final long locaStoreEnd = new Date().getTime();
			LOG.debug("Total Time taken for the Online Store {}", (locaStoreEnd - locaStoreStrt) / 1000);
			LOG.debug("Response returned by Online API \n {}", onLineResponse);
			OnlineStoresMetaData onlineStoresMetaData = new OnlineStoresMetaData();
			OnlineStores onlineStoreInfoObj = null;

			if (null != onLineResponse) {
				onlineStoreInfoObj = (OnlineStores) streamHelper.parseXmlToObject(Utility.removeSpecialCharsReverse(onLineResponse));
				onLineResponse = onLineResponse.replaceAll("<OnlineStores>", "<OnlineStores><stockImagePath>" + map.get(HubCitiConstants.ONLINEIMAGE)
						+ "</stockImagePath><stockHeader>" + map.get(HubCitiConstants.ONLINEHEADER) + "</stockHeader>");
			}

			/**
			 * For commission junction data implementation
			 */
			ArrayList<RetailerDetail> onlineRetailerlst = null;
			ArrayList<Double> salePricelst = new ArrayList<Double>();
			String onlineRetailerRespone = null;
			onlineRetailerlst = findDao.fetchCommissionJunctionData(userId, productId, prodListId, mainMenuId, hubCitiId);

			if (null != onlineRetailerlst && !onlineRetailerlst.isEmpty()) {
				for (int i = 0; i < onlineRetailerlst.size(); i++) {
					if (onlineRetailerlst.get(i).getSalePrice() != null
							&& !(onlineRetailerlst.get(i).getSalePrice().equalsIgnoreCase(HubCitiConstants.NOTAPPLICABLE))) {
						String retSalePrice;
						retSalePrice = onlineRetailerlst.get(i).getSalePrice().substring(1, onlineRetailerlst.get(i).getSalePrice().length());
						salePricelst.add(Double.parseDouble(retSalePrice));
					}
				}
				Double onlineMinPrice = null;
				if (salePricelst != null && !salePricelst.isEmpty()) {
					onlineMinPrice = Collections.min(salePricelst);
				}
				Double shopMinPrice = null;
				if (minimumPrice != null) {
					//int dollerindex = minimumPrice.indexOf('$');
					minimumPrice = minimumPrice.substring(1, minimumPrice.length());
					shopMinPrice = Double.parseDouble(minimumPrice);
				}

				if (shopMinPrice != null && !shopMinPrice.equals(HubCitiConstants.NOTAPPLICABLE) && onlineMinPrice != null
						&& !shopMinPrice.equals(HubCitiConstants.NOTAPPLICABLE)) {
					if (onlineMinPrice < shopMinPrice) {
						minimumPrice = String.valueOf(onlineMinPrice);
						minimumPrice = "$" + Utility.formatDecimalValue(minimumPrice);
					} else {
						minimumPrice = String.valueOf(shopMinPrice);
						minimumPrice = "$" + Utility.formatDecimalValue(minimumPrice);
					}
				} else {
					if (onlineMinPrice != null && !onlineMinPrice.equals(HubCitiConstants.NOTAPPLICABLE)) {
						minimumPrice = String.valueOf(onlineMinPrice);
						minimumPrice = "$" + Utility.formatDecimalValue(minimumPrice);
					}
				}
				if (totalOnlineStores != null) {
					int total = Integer.parseInt(totalOnlineStores);
					totalOnlineStores = String.valueOf(total + onlineRetailerlst.size());
				} else {
					totalOnlineStores = String.valueOf(onlineRetailerlst.size());
				}
				onlineRetailerRespone = XstreamParserHelper.produceXmlFromObject(onlineRetailerlst);

			}

			if (onlineRetailerRespone != null) {
				onlineRetailerRespone = onlineRetailerRespone.replaceAll("<list>", "<CommissionJunctionData>");
				onlineRetailerRespone = onlineRetailerRespone.replaceAll("</list>", "</CommissionJunctionData>");
				if (minimumPrice == null) {
					minimumPrice = " ";
				}

				if (onLineResponse == null) {
					onlineRetailerRespone = onlineRetailerRespone.replace(
							"<CommissionJunctionData>",
							"<OnlineStores><onlineStoresMetaData><minPrice>" + minimumPrice + "</minPrice><totalStores>" + totalOnlineStores
							+ "</totalStores> <nextPage>0</nextPage>" + "</onlineStoresMetaData><stockImagePath>"
							+ map.get(HubCitiConstants.ONLINEIMAGE) + "</stockImagePath><stockHeader>"
							+ map.get(HubCitiConstants.ONLINEHEADER) + "</stockHeader><CommissionJunctionData>");
					onlineRetailerRespone = onlineRetailerRespone.replaceAll("</CommissionJunctionData>", "</CommissionJunctionData></OnlineStores>");
					onLineResponse = onlineRetailerRespone;
				} else {
					onlineStoresMetaData.setMinPrice(minimumPrice);
					onlineStoresMetaData.setTotalStores(totalOnlineStores);
					onlineStoresMetaData.setApiURL(onlineStoreInfoObj.getOnlineStoresMetaData().getApiURL());
					onlineStoresMetaData.setPageStart(onlineStoreInfoObj.getOnlineStoresMetaData().getPageStart());
					onlineStoresMetaData.setPageSize(onlineStoreInfoObj.getOnlineStoresMetaData().getPageSize());
					onlineStoresMetaData.setVendorName(onlineStoreInfoObj.getOnlineStoresMetaData().getVendorName());
					onlineStoresMetaData.setNextPage(onlineStoreInfoObj.getOnlineStoresMetaData().getNextPage());
					onlineStoreInfoObj.setOnlineStoresMetaData(onlineStoresMetaData);

					onLineResponse = XstreamParserHelper.produceXmlFromObject(onlineStoreInfoObj);

					if (null != onLineResponse) {
						onLineResponse = onLineResponse.replaceAll(
								"<OnlineStores>",
								"<OnlineStores><stockImagePath>" + map.get(HubCitiConstants.ONLINEIMAGE) + "</stockImagePath><stockHeader>"
										+ map.get(HubCitiConstants.ONLINEHEADER) + "</stockHeader>");
					}

					onlineRetailerRespone = onlineRetailerRespone.replaceAll("</CommissionJunctionData>", "</CommissionJunctionData></OnlineStores>");
					onLineResponse = onLineResponse.replace("</OnlineStores>", onlineRetailerRespone);
				}
			}

			if (null != onLineResponse) {
				finalResponse.append(onLineResponse);
			}

			/**
			 * Calling rate review dao method to get review and rating
			 * information. It includes reviews of the product and user ratings.
			 */
			String rateReviewresponse = null;
			ProductRatingReview productRatingReview = null;
			final ArrayList<ProductReview> productReviewslist = rateReviewDao.getProductReviews(userId, productId);
			UserRatingInfo userRatingInfo = rateReviewDao.fecthUserProductRating(userId, productId, hubCitiId);

			productRatingReview = new ProductRatingReview();
			int reviewSize = 0;

			if (null == productReviewslist || productReviewslist.isEmpty()) {
				reviewSize = 0;
				productRatingReview.setTotalReviews("0");
				// LOG.info("Inside IF: 201: ProductReviewList:  is NULL/EMPTY");
			} else {
				reviewSize = productReviewslist.size();
				productRatingReview.setProductReviews(productReviewslist);
				productRatingReview.setTotalReviews(String.valueOf(productReviewslist.size()));
				// LOG.info("Inside ELSE: 201: ProductReviewList: SIZE: " +
				// productReviewslist.size());
			}

			if (null != userRatingInfo) {
				/*Integer currentRating = null;
				Integer averageRating = null;*/
				if (userRatingInfo.getCurrentRating() != null && !userRatingInfo.getCurrentRating().equals("")) {
					//					currentRating = Integer.valueOf(userRatingInfo.getCurrentRating());
				}
				if (userRatingInfo.getAvgRating() != null && !userRatingInfo.getCurrentRating().equals("")) {
					//					averageRating = Integer.valueOf(userRatingInfo.getAvgRating());
				}
				productRatingReview.setUserRatingInfo(userRatingInfo);
			} else {
				userRatingInfo = new UserRatingInfo();
				userRatingInfo.setCurrentRating("0");
				userRatingInfo.setAvgRating("0");
				productRatingReview.setUserRatingInfo(userRatingInfo);
			}

			if (productRatingReview != null) {
				// Check size of The Reviews and Check the Rat
				String avgRating = productRatingReview.getUserRatingInfo().getAvgRating();
				String currRating = productRatingReview.getUserRatingInfo().getCurrentRating();
				if (reviewSize != 0 || !avgRating.equals("0") || !currRating.equals("0")) {
					productRatingReview.setStockImagePath(map.get(HubCitiConstants.REVIEWIMAGE));
					productRatingReview.setStockHeader(map.get(HubCitiConstants.REVIEWHEADER));
					rateReviewresponse = XstreamParserHelper.produceXmlFromObject(productRatingReview);
					finalResponse.append(rateReviewresponse);
				}
			}

			// Append CLR information to final response
			final String couponStatus = productDetail.getCoupon_Status();
			final String loyaltyStatus = productDetail.getLoyalty_Status();
			final String rebateStatus = productDetail.getRebate_Status();
			if (!"Grey".equalsIgnoreCase(couponStatus) || !"Grey".equalsIgnoreCase(loyaltyStatus) || !"Grey".equalsIgnoreCase(rebateStatus)) {
				final Map<String, String> clrMap = new HashMap<String, String>();
				clrMap.put("stockImagePath", map.get(HubCitiConstants.DISCOUNTIMAGE));
				clrMap.put("stockHeader", map.get(HubCitiConstants.DISCOUNTHEADER));
				clrMap.put("couponFlag", productDetail.getCoupon_Status());
				clrMap.put("loyaltyFlag", productDetail.getLoyalty_Status());
				clrMap.put("rebateFlag", productDetail.getRebate_Status());
				final String productClrIngfo = Utility.formResponseXml("CLRInfo", clrMap);
				finalResponse.append(productClrIngfo);
			}

			// Close Root tag.
			finalResponse.append("</ProductSummary>");
			LOG.info("Exit FindServiceImpl : getProductSummary");

			totalOnlineStores = null;
			minimumPrice = null;
			// LOG.info("Final Product Summary response \n {}", finalResponse);

			final long methodEndTime = new Date().getTime();
			LOG.info("Total Time taken by the Method \n {}", (methodEndTime - methodStartTime) / 1000);
		} else {
			response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			return response;
		}

		LOG.info("Exit FindServiceImpl : getProductSummary");
		return Utility.removeSpecialChars(finalResponse.toString());
	}

	/**
	 * This is Service method for find near by retailers based on lowest
	 * price.This method validates the input parameters and returns validation
	 * error if validation fails. calls DAO methods to get the number of
	 * Retailers and lowest price of the products searching for.
	 * 
	 * @param userID
	 *            requested user.
	 * @param latitude
	 *            current location of user.
	 * @param productId
	 *            products to be searched for.
	 * @param longitude
	 *            current location of user.
	 * @param postalcode
	 *            current location of user.
	 * @param radius
	 *            search distance.
	 * @return String XMl list of retailers.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String findRetailersExternalAPI(Integer userId, Integer productId, Double latitude, Double longitude, Integer radius, Integer page,
			String postalcode) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findRetailersExternalAPI");

		if (null == userId || null == productId || "".equals(postalcode)) {
			LOG.info("Validation failed because UserId/ProductId not available or Empty string validation");
			return Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		}

		String response = null;
		final FindNearByRequest findNearByRequest = new FindNearByRequest();
		ProductDetail productInfo = null;
		ExternalAPIInformation externalAPIInformation = new ExternalAPIInformation();
		UpdateUserInfo updateUserInfo = null;
		String externalAPIURL;
		@SuppressWarnings("unused")
		ExternalAPIErrorLog externalAPIErrorLog = null;
		productInfo = findDao.getProductInfoforExternalAPI(productId);
		updateUserInfo = firstUseDao.fetchUserInfo(userId, false);

		if (null != productInfo) {
			if (null != productInfo.getBarCode() && !productInfo.getBarCode().equals(HubCitiConstants.NOTAPPLICABLE)) {
				findNearByRequest.setUpcCode(productInfo.getBarCode());
			} else
				if (null != productInfo.getSkuCode() && !productInfo.getSkuCode().equals(HubCitiConstants.NOTAPPLICABLE)) {
					findNearByRequest.setSkuCode(productInfo.getSkuCode());
				} else
					if (null != productInfo.getProductName() && !productInfo.getProductName().equals(HubCitiConstants.NOTAPPLICABLE)) {
						findNearByRequest.setKeywords(productInfo.getProductName());
					} else {
						LOG.info("No product Information is available to invoke external API");
						return null;
					}

			if (null != latitude && null != longitude) {
				findNearByRequest.setLatitude(String.valueOf(latitude));
				findNearByRequest.setLongiTude(String.valueOf(longitude));
			} else {
				if (null == postalcode) {
					if (null != updateUserInfo) {
						if (null != updateUserInfo.getPostalCode()) {
							findNearByRequest.setZipCode(updateUserInfo.getPostalCode());
						}
					} else {
						LOG.info("Either Latitude and Longitude or ZipCode Available to invoke External API");
						return null;
					}
				} else {
					findNearByRequest.setZipCode(postalcode);
				}
			}

			findNearByRequest.setRadius(String.valueOf(radius));
			findNearByRequest.setFormat(HubCitiConstants.EXTERNALAPIFORMAT);
			findNearByRequest.setPage(String.valueOf(page));
			findNearByRequest.setUserId(userId);
			findNearByRequest.setValues();

			final FindNearByService findNearByService = new FindNearByServiceImpl();
			externalAPIInformation = getExternalAPIInformation(HubCitiConstants.FINDNEARBYMODULENAME);

			if (null != externalAPIInformation.getSerchParameters() && null != externalAPIInformation.getVendorList()) {
				LOG.info("Invoking External API for Module::::::::" + HubCitiConstants.FINDNEARBYMODULENAME + " With Parameters:Product ID:"
						+ productId + " Product Name:" + productInfo.getProductName() + " Latitude::" + latitude + " Logitude::" + longitude
						+ " Radius::" + radius);

				response = findNearByService.processFindNearByRequest(externalAPIInformation, findNearByRequest);
				externalAPIURL = findNearByService.getApiURL();
				if (null != externalAPIURL) {
					findDao.insertExternalAPIURL(externalAPIURL, userId);
				}

				if (findNearByService.getResponsecode() != 11000) {
					response = null;
				}
				if (HubCitiConstants.EXTERNALAPIFAILURE == findNearByService.getResponsecode()) {
					externalAPIErrorLog = findNearByService.getExternalAPIErrors();
				}
				LOG.info("XML Response from External API:::::" + response);
			}
		}

		LOG.info("Exit FindServiceImpl : findRetailersExternalAPI");
		return response;
	}

	/**
	 * This service method is used to get online store informations.
	 * 
	 * @param onlineStoresRequest
	 *            -Request object
	 * @return response as success or failure
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	private String getOnlineStoreData(OnlineStoresRequest onlineStoresRequest) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getOnlineStoreData");

		ProductDetail productInfo = null;
		String moduleName = "FindOnlineStores";
		String response = null;

		@SuppressWarnings("unused")
		ExternalAPIErrorLog externalAPIErrorLog = null;
		ExternalAPIInformation externalAPIInformation = new ExternalAPIInformation();
		final OnlineStoresService onlineStoresService = new OnlineStoresServiceImpl();
		if (null == onlineStoresRequest.getApiURL()) {
			productInfo = findDao.getProductInfoforExternalAPI(onlineStoresRequest.getProductId());
			if (null != productInfo) {
				if (productInfo.getBarCode() != HubCitiConstants.NOTAPPLICABLE) {
					final OnlineStoresRequest onlineStores = new OnlineStoresRequest();
					onlineStores.setPageStart(onlineStoresRequest.getPageStart());
					onlineStores.setUpcCode(productInfo.getBarCode());
					onlineStores.setUserId(onlineStoresRequest.getUserId());
					onlineStores.setValues();
					externalAPIInformation = getExternalAPIInformation(HubCitiConstants.FINDONLINESTOESMODULENAME);

					if (null != externalAPIInformation.getSerchParameters() && null != externalAPIInformation.getVendorList()) {
						LOG.info("Invoking External API for Module::::::::" + moduleName + " With Parameters:Product UPC Code:"
								+ onlineStoresRequest.getUpcCode() + " Product Name:" + productInfo.getProductName());
						response = onlineStoresService.onlineStoresInfo(externalAPIInformation, onlineStores);

						if (HubCitiConstants.EXTERNALAPIFAILURE == onlineStoresService.getResponseCode()) {
							externalAPIErrorLog = onlineStoresService.getExternalAPIErrors();
						}

						if (onlineStoresService.getResponseCode() != 11000) {
							response = null;
						}
						totalOnlineStores = onlineStoresService.getNumberOfStores();
						minimumPrice = onlineStoresService.getMinimumPrice();
					}

				} else {
					LOG.info("Product Barcode is not available to Invoke External External API");
				}
			}
		} else {
			LOG.info("Invoking ShopZilla For Pagination");
			response = onlineStoresService.onlineStoresInfo(null, onlineStoresRequest);
			if (onlineStoresService.getResponseCode() != 11000) {
				response = null;
			}
			LOG.info("Response XML from External API:::::" + response);
		}

		LOG.info("Exit FindServiceImpl : getOnlineStoreData");
		return response;
	}

	/**
	 * This is Service method for finding retailers local database.This method
	 * validates the input parameters and returns validation error if validation
	 * fails. calls DAO methods to get the number of Retailers and lowest price
	 * of the products searching for.
	 * 
	 * @param userID
	 *            requested user.
	 * @param latitude
	 *            current location of user.
	 * @param productId
	 *            products to be searched for.
	 * @param longitude
	 *            current location of user.
	 * @param postalcode
	 *            current location of user.
	 * @param radius
	 *            search distance.
	 * @return String XMl list of retailers.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public FindNearByIntactResponse findRetailersLocalDataBase(Integer userID, Double latitude, Integer productId, Double longitude,
			String postalcode, int radius, Integer mainMenuId, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findRetailersLocalDataBase");

		final FindNearByIntactResponse findNearByDetailsResultSet = findDao.fetchNearByLowestPrice(userID, latitude, productId, longitude,
				postalcode, radius, mainMenuId, hubCitiId);

		LOG.info("Exit FindServiceImpl : findRetailersLocalDataBase");
		return findNearByDetailsResultSet;
	}

	/**
	 * This method fetches product attributes information.
	 * 
	 * @param userId
	 *            as the request.
	 * @param productId
	 *            as request parameter
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String fetchProductAttributes(Integer userId, Integer productId) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : fetchProductAttributes");

		String response = null;
		ProductDetail productDetailsObj = null;

		if (userId == null || productId == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			productDetailsObj = findDao.fetchProductAttributes(userId, productId);
			if (null != productDetailsObj) {
				productDetailsObj.setResponseCode(HubCitiConstants.SUCCESSCODE);
				productDetailsObj.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(productDetailsObj);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FindServiceImpl : fetchProductAttributes");
		return response;
	}

	/**
	 * The method calls JAXB Helper class method and parses the XML. The result
	 * is passed to FindDao method.
	 * 
	 * @param xml
	 *            contains userId,productId,retailerId,lowerLimit which are
	 *            needed
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String fetchMSLCLRDetails(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : fetchMSLCLRDetails");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final CLRDetails clrDetailsObj = (CLRDetails) streamHelper.parseXmlToObject(xml);

		if (clrDetailsObj.getClrC() == null) {
			clrDetailsObj.setClrC(0);
		}
		if (clrDetailsObj.getLowerLimit() == null) {
			clrDetailsObj.setLowerLimit(0);
		}
		if (clrDetailsObj.getUserId() == null) {
			LOG.info("Request does not contain proper inforamtion");
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final CLRDetails cLRDetailsObj = findDao.fetchMasterSLCLRDetails(clrDetailsObj);
			if (null != cLRDetailsObj) {
				cLRDetailsObj.setResponseCode(HubCitiConstants.SUCCESSCODE);
				cLRDetailsObj.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(cLRDetailsObj);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FindServiceImpl : fetchMSLCLRDetails");
		return response;
	}

	/**
	 * This ServiceImpl method for capturing Online Product Click
	 * 
	 * @param xml
	 *            - the request xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.Retailer
	 */
	public String userTrackingOnlineStoreClick(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : userTrackingOnlineStoreClick");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final CLRDetails clrDetailsObj = (CLRDetails) streamHelper.parseXmlToObject(xml);

		if (null == clrDetailsObj.getProductId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = findDao.userTrackingOnlineStoreClick(clrDetailsObj);
		}

		LOG.info("Exit FindServiceImpl : userTrackingOnlineStoreClick");
		return response;
	}

	public String findSingleCategoryRetailers(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findSingleCategoryRetailers");

		String response = null;
		String strCompleteAddress;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail retailDetail = (RetailerDetail) streamHelper.parseXmlToObject(xml);

		if (null == retailDetail.getHubCitiId() || "".equals(Utility.checkNull(retailDetail.getCatName()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (null == retailDetail.getmItemId() && null == retailDetail.getBottomBtnId() && null == retailDetail.getMainMenuId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			RetailersDetails retailerDetail = new RetailersDetails();

			Integer mainMenuId = null;
			if (null == retailDetail.getMainMenuId()) {

				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setmItemId(retailDetail.getmItemId());
				objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());

				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = retailDetail.getMainMenuId();
			}
			retailDetail.setMainMenuId(mainMenuId);

			/*
					 	Condition to select Grouping Type 
					 	1. If groupBy is atoz or type- grouping is done accordingly. 
					 	2. If groupBy is unknown value or null  - no grouping is done, ie N/A is named initially
					 	   and in response groupContent will have empty string.
			 */
			if (!"atoz".equalsIgnoreCase(retailDetail.getGroupBy()) && !"type".equalsIgnoreCase(retailDetail.getGroupBy())
					|| "".equals(Utility.checkNull(retailDetail.getGroupBy()))) {
				retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			} 

			/*
					   Condition to select Sorting Type 
					    1. If sortBy is null- sorting is done by retailer name. 
					    2. If sortBy is name- sorting is done by retailer name. 
					    3. If sortBy is distance 
					         a. Sorting is done by distance 
					         b. No grouping is done, hence groupBy is changed to N/A.
			 */
			/*if (null != retailDetail.getSortColumn()) {
						if ("name".equalsIgnoreCase(retailDetail.getSortColumn())) {
							retailDetail.setSortColumn("RetailerName");
						}  else if ("city".equalsIgnoreCase(retailDetail.getSortColumn())){
							retailDetail.setSortColumn("City");
						}  else {
							retailDetail.setSortColumn("Distance");
//							retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
						}
					} else {
						retailDetail.setSortColumn("Distance");
//						retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
					}*/

			// after making sort by  as dynamic list we are verifying only null if it contains values we consider as valid sortcolumn  
			if (null == retailDetail.getSortColumn()) {
				retailDetail.setSortColumn("Distance");
			} 


			//For sorting in ascending or descending order.
			if (retailDetail.getSortOrder() == null || (!"DESC".equalsIgnoreCase(retailDetail.getSortOrder()))) {
				retailDetail.setSortOrder("ASC");
			}

			retailerDetail = findDao.findSingleCategoryRetailers(retailDetail);
			//					RetailersDetails retsDetails = new RetailersDetails();

			if (retailerDetail != null && retailerDetail.getRetailerDetail() != null
					&& !retailerDetail.getRetailerDetail().isEmpty()) {

				strCompleteAddress = new String();
				/*retailerDetail.setGroupBy(retailDetail.getGroupBy());
						retailerDetail.setSortColumn(retailDetail.getSortColumn());
						retailerDetail.setSortOrder(retailDetail.getSortOrder());

						retsDetails = FindHelper.groupAndSortRetailers(retailerDetail);*/

				retailerDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailerDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
				/*retsDetails.setPoweredby(retailerDetail.getPoweredby());
						retsDetails.setMaxCnt(retailerDetail.getMaxCnt());
						retsDetails.setNextPage(retailerDetail.getNextPage());
						retsDetails.setMaxRowNum(retailerDetail.getMaxRowNum());

						retsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						retsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

						if (HubCitiConstants.NOTAPPLICABLE.equals(retailDetail.getGroupBy())) {
							retsDetails.getCatList().get(0).setGroupContent("");
						}*/

				for ( int i = 0; i< retailerDetail.getRetailerDetail().size(); i++ ) {
					if (null !=  retailerDetail.getRetailerDetail().get(i).getRetaileraddress1()
							&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getRetaileraddress1())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getRetaileraddress1())) {
						strCompleteAddress = retailerDetail.getRetailerDetail().get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailerDetail.getRetailerDetail().get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getCity())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getCity())) {
						strCompleteAddress = strCompleteAddress +  retailerDetail.getRetailerDetail().get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailerDetail.getRetailerDetail().get(i).getPostalCode()
							&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getPostalCode())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getPostalCode())) {
						strCompleteAddress = strCompleteAddress +  retailerDetail.getRetailerDetail().get(i).getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null !=  retailerDetail.getRetailerDetail().get(i).getState() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getState())
							&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getState())) {
						strCompleteAddress = strCompleteAddress +  retailerDetail.getRetailerDetail().get(i).getState();
					}
					retailerDetail.getRetailerDetail().get(i).setCompleteAddress(strCompleteAddress);
				}

			} else {
				retailerDetail.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				retailerDetail.setResponseText(retailerDetail.getNoRecordMsg());
				retailerDetail.setNoRecordMsg(null);
			}

			/*if (1 == retailerDetail.getBottomBtn()) {
						retsDetails.setBottomBtnList(retailerDetail.getBottomBtnList());
					}

					retailerDetail.setGroupBy(null);
					retailerDetail.setSortColumn(null);
					retailerDetail.setSortOrder(null);

					retsDetails.setBottomBtn(retailerDetail.getBottomBtn());*/

			response = XstreamParserHelper.produceXmlFromObject(retailerDetail);
		}

		LOG.info("Exit FindServiceImpl : findSingleCategoryRetailers");
		return response;
	}

	/**
	 * Method for getting sub-categories for find single category
	 * 
	 * @return category list.
	 * @throws HubCitiException
	 */
	public String getSubCategories(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getSubCategories");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if ( null == objUserDetails.getUserId() || null == objUserDetails.getHubCitiId() || null == objUserDetails.getCatId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (null == objUserDetails.getmItemId() &&  null == objUserDetails.getBottomBtnId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			CategoryDetails objCatDetails = findDao.getSubCategories(objUserDetails);

			if (objCatDetails != null && objCatDetails.getListCatDetails() != null && !objCatDetails.getListCatDetails().isEmpty()) {
				objCatDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCatDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objCatDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FindServiceImpl : getSubCategories");
		return response;
	}

	/**
	 * ServiceImpl method to get filter list, also convert request xml to object and
	 * response object to xml.
	 * 
	 * @param xml
	 * @return xml string containing filter list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getFilterList(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getFilterList ");

		String response = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final MenuItem menuItem = (MenuItem) parser.parseXmlToObject(xml);

		if (null == menuItem.getUserId() || null == menuItem.getHubCitiId() ||  null == menuItem.getCatId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		}  else {

			Filter filter = findDao.getFilterList(menuItem);

			//				final List<Filter> filterList = filter.getFilterList();
			Filter objFilter = new Filter();

			if (null != filter &&  null != filter.getFilterList() && !filter.getFilterList().isEmpty()) {

				/*	objFilter = FindHelper.sortFilterList(filter);

					if ( null != objFilter && null != objFilter.getCategoryList() && !objFilter.getCategoryList().isEmpty()) {
						objFilter.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objFilter.setResponseText(HubCitiConstants.SUCCESSTEXT);

					} else {
						objFilter.setResponseCode(HubCitiConstants.NORECORDSFOUND);
						objFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					}*/

				objFilter.setFilterList(filter.getFilterList());
				objFilter.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objFilter.setResponseText(HubCitiConstants.SUCCESSTEXT);

			} else {
				objFilter.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				objFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			response = XstreamParserHelper.produceXmlFromObject(objFilter);
		}

		LOG.info("Exit FindServiceImpl : getFilterList ");
		return response;
	}



	/**
	 * ServiceImpl method to get Sort and Filter List. 
	 * 
	 * @param xml
	 * @return xml string containing filter list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getSortFilterList(String xml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getSortFilterList ");

		String response = null;
		List<Filter> filterList = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) parser.parseXmlToObject(xml);

	/*	if (null == objUserDetails.getUserId() || null == objUserDetails.getHubCitiId() ||  null == objUserDetails.getModule()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (null == objUserDetails.getmItemId() &&  null == objUserDetails.getBottomBtnId() 
				&& !"CitiExp".equals(objUserDetails.getModule()) && !"SubMenu".equals(objUserDetails.getModule())  && !"Filter".equalsIgnoreCase(objUserDetails.getModule())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.MENUITEMORBOTTOMBUTTON);
		} else if (("CitiExp".equals(objUserDetails.getModule())) && (null == objUserDetails.getCitiExpId())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if ("Find Single".equals(objUserDetails.getModule()) && (null == objUserDetails.getCatName())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {*/


			filterList = findDao.getSortFilterList(objUserDetails);
			Filter objFilter = new Filter();
			if (null != filterList && !filterList.isEmpty()) {
				List<Filter> sortfilterList = new ArrayList<Filter>();
				sortfilterList = FindHelper.sortFilterHeaderList(filterList);

				if ( null != sortfilterList && !sortfilterList.isEmpty()) {
					objFilter.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objFilter.setResponseText(HubCitiConstants.SUCCESSTEXT);
					objFilter.setFilterList(sortfilterList);

				} else {
					objFilter.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					objFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

			} else {
				objFilter.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				objFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			response = XstreamParserHelper.produceXmlFromObject(objFilter);
		/*}*/

		LOG.info("Exit FindServiceImpl : getSortFilterList ");
		return response;
	}

	/**
	 * ServiceImpl method to get filter list based on specified type field value
	 * 
	 * @param xml
	 * @return xml string containing filter list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getInterestList(String xml) throws HubCitiException {
		LOG.info(" Inside FindServiceImpl : getInterestList");

		String response = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final Filter requestFilter = (Filter) parser.parseXmlToObject(xml);

		// filter response instance
		Filter responseFilter = new Filter();

		if (null == requestFilter.getUserId() || null == requestFilter.getHubCitiId()
				|| null == requestFilter.getSpType()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			Filter filter = findDao.getInterestList(requestFilter);
			List<Filter> filterList = filter.getFilterList();

			if (null != filterList && !filterList.isEmpty()) {
				responseFilter.setFilterList(filterList);
				if (null != responseFilter && !responseFilter.getFilterList().isEmpty()) {
					responseFilter.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseFilter.setResponseText(HubCitiConstants.SUCCESSTEXT);
				} else {
					responseFilter.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					responseFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			} else {
				responseFilter.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				responseFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			response = XstreamParserHelper.produceXmlFromObject(responseFilter);
		}

		LOG.info(ApplicationConstants.METHODEND + " Inside FindServiceImpl  "
				+ "getInterestList");
		return response;
	}

	/**
	 * Service method to get Option Filter list (option changes)
	 * 
	 * @param xml
	 *            string containing filter changes
	 * @return
	 * @throws HubCitiException
	 */
	public String getOptionList(String xml) throws HubCitiException {


		LOG.info(ApplicationConstants.METHODSTART + " Inside FindServiceImpl "
				+ "getOptionList");

		String response = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final Filter requestFilter = (Filter) parser.parseXmlToObject(xml);

		// filter response instance
		Filter responseFilter = new Filter();

		if (null == requestFilter.getUserId()
				|| null == requestFilter.getHubCitiId()
				|| null == requestFilter.getSpType()) {

			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);

		} else {
			Filter filter = findDao.getOptionList(requestFilter);
			List<Filter> filterList = filter.getFilterList();

			if (null != filterList && !filterList.isEmpty()) {
				responseFilter.setFilterList(filterList);
				if (null != responseFilter && !responseFilter.getFilterList().isEmpty()) {

					responseFilter.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseFilter.setResponseText(HubCitiConstants.SUCCESSTEXT);
				} else {
					responseFilter.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					responseFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			} else {
				responseFilter.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				responseFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			response = XstreamParserHelper.produceXmlFromObject(responseFilter);
		}

		LOG.info(ApplicationConstants.METHODEND + " Inside FindServiceImpl  "
				+ "getOptionList");
		return response;
	}

	/**
	 * Service method to get City Experience Category List
	 * 
	 * @param xml
	 *            string containing City Experience Category Information
	 * @return
	 * @throws HubCitiException
	 */
	public String getCategoryList(String xml) throws HubCitiException {
	LOG.info("Inside FindServiceImpl : getCategoryList");
		String response = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final Filter requestFilter = (Filter) parser.parseXmlToObject(xml);

		// filter response instance
		Filter responseFilter = new Filter();

		if (null == requestFilter.getUserId() || null == requestFilter.getHubCitiId()) {
			response = Utility.formResponseXml( HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			responseFilter = findDao.getCategoryList(requestFilter);
			if ( null != responseFilter && null != responseFilter.getCategoryList() && !responseFilter.getCategoryList().isEmpty()) { 
				responseFilter.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseFilter.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				responseFilter.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
				responseFilter.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		} 
		response = XstreamParserHelper.produceXmlFromObject(responseFilter);
		LOG.info("Exit FindServiceImpl : getCategoryList");
		return response;
	}


	/**
	 * The ServiceImpl method fetches Category Details.
	 * 
	 * @return Category List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getCategoryInfo(String strXml) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getCategoryInfo");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final MenuItem objMenuItem = (MenuItem) streamHelper.parseXmlToObject(strXml);

		if (null == objMenuItem.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (null == objMenuItem.getmItemId() && null == objMenuItem.getBottomBtnId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (null == objMenuItem.getRadius()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			// For user tracking
			Integer mainMenuId = null;
			if (null == objMenuItem.getMainMenuId()) {
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objMenuItem.getMainMenuId();
			}

			Categories categories = findDao.getCategoryDetail(objMenuItem);

			if (categories != null && categories.getCatList() != null && !categories.getCatList().isEmpty()) {
				categories.setResponseCode(HubCitiConstants.SUCCESSCODE);
				categories.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				categories.setCatList(null);
				categories.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				categories.setResponseText(categories.getNoRecordMsg());
			}
			categories.setNoRecordMsg(null);
			categories.setMainMenuId(mainMenuId);
			response = XstreamParserHelper.produceXmlFromObject(categories);
		}

		LOG.info("Exit FindServiceImpl : getCategoryInfo");
		return response;
	}


	/**
	 * The ServiceImpl method fetches Category Details.
	 * 
	 * @return Category List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	@Cacheable(value="categoryJsonInfo",  key="#strJson")
	public String getCategoryJsonInfo(String strJson) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getCategoryJsonInfo");

		String strJsonResponse = null;

		try {

			final Gson gson = new Gson();
			final MenuItem objMenuItem = gson.fromJson(strJson, MenuItem.class);

			if (null == objMenuItem.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == objMenuItem.getmItemId() && null == objMenuItem.getBottomBtnId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == objMenuItem.getRadius()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				// For user tracking
				Integer mainMenuId = null;
				if (null == objMenuItem.getMainMenuId()) {
					mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
				} else {
					mainMenuId = objMenuItem.getMainMenuId();
				}

				Categories categories = findDao.getCategoryDetail(objMenuItem);

				if (categories != null && categories.getCatList() != null && !categories.getCatList().isEmpty()) {
					categories.setResponseCode(HubCitiConstants.SUCCESSCODE);
					categories.setResponseText(HubCitiConstants.SUCCESSTEXT);
				} else {
					categories.setCatList(null);
					categories.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					categories.setResponseText(categories.getNoRecordMsg());
				}
				categories.setNoRecordMsg(null);
				categories.setMainMenuId(mainMenuId);
				strJsonResponse = gson.toJson(categories);
			}

		}  catch (HubCitiException e) {
			LOG.error("Inside  FindServiceImpl : getCategoryJsonInfo :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindServiceImpl : getCategoryJsonInfo");
		return strJsonResponse;
	}
	
	
	/**
	 * ServiceImpl method is remove Category information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 */
	@CacheEvict(value = "categoryJsonInfo", allEntries = true)
	public String delCategoryJson() throws HubCitiException {
		LOG.info("Inside FindServiceImpl : getCategoryRemCache");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}

	/**
	 * ServiceImpl method for category search for retailer.
	 * 
	 * @return JSON string with retailer information list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	@Cacheable(value="singleCategoryRetailersJson",  key="#strSingleCatRetJson")
	public String findSingleCategoryRetailersJson(String strSingleCatRetJson) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findSingleCategoryRetailersJson");

		String strJsonResponse = null;
		String strCompleteAddress;

		try {

			final Gson gson = new Gson();
			final RetailerDetail retailDetail = gson.fromJson(strSingleCatRetJson, RetailerDetail.class);

			if (null == retailDetail.getHubCitiId() || "".equals(Utility.checkNull(retailDetail.getCatName()))) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == retailDetail.getmItemId() && null == retailDetail.getBottomBtnId() && null == retailDetail.getMainMenuId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				RetailersDetails retailerDetail = new RetailersDetails();

				Integer mainMenuId = null;
				if (null == retailDetail.getMainMenuId()) {

					MenuItem objMenuItem = new MenuItem();
					objMenuItem.setmItemId(retailDetail.getmItemId());
					objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());

					mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
				} else {
					mainMenuId = retailDetail.getMainMenuId();
				}
				retailDetail.setMainMenuId(mainMenuId);

				if (!"atoz".equalsIgnoreCase(retailDetail.getGroupBy()) && !"type".equalsIgnoreCase(retailDetail.getGroupBy())
						|| "".equals(Utility.checkNull(retailDetail.getGroupBy()))) {
					retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				} 


				// after making sort by  as dynamic list we are verifying only null if it contains values we consider as valid sortcolumn  
				if (null == retailDetail.getSortColumn()) {
					retailDetail.setSortColumn("Distance");
				} 


				if (retailDetail.getSortOrder() == null || (!"DESC".equalsIgnoreCase(retailDetail.getSortOrder()))) {
					retailDetail.setSortOrder("ASC");
				}

				retailerDetail = findDao.findSingleCategoryRetailers(retailDetail);

				if (retailerDetail != null && retailerDetail.getRetailerDetail() != null
						&& !retailerDetail.getRetailerDetail().isEmpty()) {

					strCompleteAddress = new String();

					retailerDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailerDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);


					for ( int i = 0; i< retailerDetail.getRetailerDetail().size(); i++ ) {
						if (null !=  retailerDetail.getRetailerDetail().get(i).getRetaileraddress1()
								&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getRetaileraddress1())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getRetaileraddress1())) {
							strCompleteAddress = retailerDetail.getRetailerDetail().get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null !=  retailerDetail.getRetailerDetail().get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getCity())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getCity())) {
							strCompleteAddress = strCompleteAddress +  retailerDetail.getRetailerDetail().get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						
						if (null !=  retailerDetail.getRetailerDetail().get(i).getState() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getState())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getState())) {
							strCompleteAddress = strCompleteAddress +  retailerDetail.getRetailerDetail().get(i).getState() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						
						if (null !=  retailerDetail.getRetailerDetail().get(i).getPostalCode()
								&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetail.getRetailerDetail().get(i).getPostalCode())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetail.getRetailerDetail().get(i).getPostalCode())) {
							strCompleteAddress = strCompleteAddress +  retailerDetail.getRetailerDetail().get(i).getPostalCode() ;
						}
						
						retailerDetail.getRetailerDetail().get(i).setCompleteAddress(strCompleteAddress);
					}

				} else {
					retailerDetail.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					retailerDetail.setResponseText(retailerDetail.getNoRecordMsg());
					retailerDetail.setNoRecordMsg(null);
				}
				strJsonResponse = gson.toJson(retailerDetail);
			}
		}  catch (HubCitiException e) {
			LOG.error("Inside  FindServiceImpl : findSingleCategoryRetailers :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FindServiceImpl : findSingleCategoryRetailers");
		return strJsonResponse;
	}


	/**
	 * ServiceImpl method is remove Single Category Retailer information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 */
	@CacheEvict(value = "singleCategoryRetailersJson", allEntries = true)
	public String delSingleCategoryRetailersJson() throws HubCitiException {
		LOG.info("Inside FindServiceImpl : delSingleCategoryRetailersJson");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}

	/**
	 * ServiceImpl method to find retailer for ScanSee data.
	 * 
	 * @param strJSON as request.
	 * @return JSON containing the ScanSee data search details in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String findScanSeeRetSearchJson(String strRetSearchJson) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findScanSeeRetSearchJson");

		String strJsonResponse = null;
		String strCompleteAddress = null;
		RetailersDetails retailerDetails = null;

		try {
			final Gson gson = new Gson();
			final RetailerDetail retailDetail = gson.fromJson(strRetSearchJson, RetailerDetail.class);

			if (null == retailDetail.getUserId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERIDNOTFOUND);
			} else if (null == retailDetail.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == retailDetail.getmItemId() && null == retailDetail.getBottomBtnId() && null == retailDetail.getMainMenuId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if ( null != retailDetail.getSearchKey() && "".equals(Utility.checkNull(retailDetail.getSearchKey()))) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, "Please enter valid search key");
			} else {

				Integer mainMenuId = null;
				if (retailDetail.getMainMenuId() == null) {
					MenuItem objMenuItem = new MenuItem();
					objMenuItem.setmItemId(retailDetail.getmItemId());
					objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());
					mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
				} else {
					mainMenuId = retailDetail.getMainMenuId();
				}
				retailDetail.setMainMenuId(mainMenuId);

				/*
			 	Condition to select Grouping Type 
			 	1. If groupBy is atoz or type- grouping is done accordingly. 
			 	2. If groupBy is unknown value and null- no grouping is done, ie N/A is named initially
			 	   and in response groupContent will have empty string.
				 */
				if (!"atoz".equalsIgnoreCase(retailDetail.getGroupBy()) && !"type".equalsIgnoreCase(retailDetail.getGroupBy())
						|| "".equals(Utility.checkNull(retailDetail.getGroupBy()))) {
					retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				} 

				/*
			   Condition to select Sorting Type 
			    1. If sortBy is null- sorting is done by retailer name. 
			    2. If sortBy is name- sorting is done by retailer name. 
			    3. If sortBy is distance 
			         a. Sorting is done by distance 
			         b. No grouping is done, hence groupBy is changed to N/A.
				 */

				if ( null == retailDetail.getSortColumn()) {
					retailDetail.setSortColumn("Distance");
				}


				//After filter implementation

				/*if (retailDetail.getSortColumn() != null) {
				if ("name".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("RetailerName");
				} else if ("city".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("city");
				} else {
					retailDetail.setSortColumn("Distance");
//					retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				}
			} else {
				retailDetail.setSortColumn("Distance");
//				retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			}*/

				//For sorting in ascending or descending order.
				if (retailDetail.getSortOrder() == null || (!"DESC".equalsIgnoreCase(retailDetail.getSortOrder()))) {
					retailDetail.setSortOrder("ASC");
				}

				retailerDetails = findDao.findScanSeeRetSearch(retailDetail);

				//RetailersDetails retailerGrpSort = new RetailersDetails();

				if (retailerDetails != null && retailerDetails.getRetailerDetail() != null
						&& !retailerDetails.getRetailerDetail().isEmpty()) {

					strCompleteAddress = new String();

					for ( int i = 0; i< retailerDetails.getRetailerDetail().size(); i++ ) {
						if (null !=  retailerDetails.getRetailerDetail().get(i).getRetaileraddress1()
								&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getRetaileraddress1())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getRetaileraddress1())) {
							strCompleteAddress = retailerDetails.getRetailerDetail().get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null !=  retailerDetails.getRetailerDetail().get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getCity())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getCity())) {
							strCompleteAddress = strCompleteAddress +  retailerDetails.getRetailerDetail().get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null !=  retailerDetails.getRetailerDetail().get(i).getState() && !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getState())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getState())) {
							strCompleteAddress = strCompleteAddress +  retailerDetails.getRetailerDetail().get(i).getState() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null !=  retailerDetails.getRetailerDetail().get(i).getPostalCode()
								&& !HubCitiConstants.NOTAPPLICABLE.equals( retailerDetails.getRetailerDetail().get(i).getPostalCode())
								&& !HubCitiConstants.EMPTYSTR.equals( retailerDetails.getRetailerDetail().get(i).getPostalCode())) {
							strCompleteAddress = strCompleteAddress +  retailerDetails.getRetailerDetail().get(i).getPostalCode() ;
						}
						
						retailerDetails.getRetailerDetail().get(i).setCompleteAddress(strCompleteAddress.trim());
					}
					/*retailerDetails.setGroupBy(retailDetail.getGroupBy());
				retailerDetails.setSortColumn(retailDetail.getSortColumn());
				retailerDetails.setSortOrder(retailDetail.getSortOrder());

				retailerGrpSort = FindHelper.groupAndSortRetailers(retailerDetails);

				retailerDetails.setRetailerDetail(null);
				retailerDetails.setCatList(retailerGrpSort.getCatList());*/

					/*if (HubCitiConstants.NOTAPPLICABLE.equals(retailerDetails.getGroupBy())) {
					retailerDetails.getCatList().get(0).setGroupContent("");
				}*/

					retailerDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailerDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				} else {
					retailerDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					retailerDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

				retailerDetails.setMainMenuId(mainMenuId);
				strJsonResponse = gson.toJson(retailerDetails);
			}

		}  catch (HubCitiException e) {
			LOG.error("Inside  FindServiceImpl : findScanSeeRetSearchJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit FindServiceImpl : findScanSeeRetSearchJson");
		return strJsonResponse;
	}


	/**
	 * ServiceImpl method is remove Retailer search information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 */
	@CacheEvict(value = "retailerSearchJson", allEntries = true)
	public String delScanSeeRetSearchJson() throws HubCitiException {
		LOG.info("Inside FindServiceImpl : delSingleCategoryRetailersJson");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}

	/**
	 * ServiceImpl method to find retailer category for ScanSee data.
	 * 
	 * @param strJSON as request.
	 * @return JSON containing retailer list in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	@Cacheable(value="categorySearchJson",  key="#strCatSearchJson")
	public String findScanSeeCategorySearchJson(String strCatSearchJson) throws HubCitiException {
		LOG.info("Inside FindServiceImpl : findScanSeeCategorySearch");

		String strJsonResponse = null;
		String strCompleteAddress;

		try {

			final Gson gson = new Gson();
			final RetailerDetail retailDetail = gson.fromJson(strCatSearchJson, RetailerDetail.class);

			if (retailDetail.getHubCitiId() == null || retailDetail.getCatName() == null || "".equals(retailDetail.getCatName())) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (retailDetail.getmItemId() == null && retailDetail.getBottomBtnId() == null && retailDetail.getMainMenuId() == null) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {
				RetailersDetails retailersDetails = null;

				Integer mainMenuId = null;
				if (retailDetail.getMainMenuId() == null) {
					MenuItem objMenuItem = new MenuItem();
					objMenuItem.setmItemId(retailDetail.getmItemId());
					objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());
					mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
				} else {
					mainMenuId = retailDetail.getMainMenuId();
				}
				retailDetail.setMainMenuId(mainMenuId);


				/*
			 	Condition to select Grouping Type 
			 	1. If groupBy is atoz or type- grouping is done accordingly. 
			 	2. If groupBy is unknown value or null - no grouping is done, ie N/A is named initially
			 	   and in response groupContent will have empty string.
				 */
				if (!"atoz".equalsIgnoreCase(retailDetail.getGroupBy()) && !"type".equalsIgnoreCase(retailDetail.getGroupBy())
						|| "".equals(Utility.checkNull(retailDetail.getGroupBy()))) {
					retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				} 

				/*
			   Condition to select Sorting Type 
			    1. If sortBy is null- sorting is done by retailer name. 
			    2. If sortBy is name- sorting is done by retailer name. 
			    3. If sortBy is distance 
			         a. Sorting is done by distance 
			         b. No grouping is done, hence groupBy is changed to N/A.
				 */
				if ( null == retailDetail.getSortColumn()) {
					retailDetail.setSortColumn("Distance");
					retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				}

				//After filter changes   
				/*
			if (retailDetail.getSortColumn() != null) {
				if ("name".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("RetailerName");
				} else if ("city".equalsIgnoreCase(retailDetail.getSortColumn())) {
					retailDetail.setSortColumn("city");
				} else {
					retailDetail.setSortColumn("Distance");
					//retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
				}
			} else {
				retailDetail.setSortColumn("Distance");
				//retailDetail.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			}
				 */			

				//For sorting in ascending or descending order.
				if (retailDetail.getSortOrder() == null || (!"DESC".equalsIgnoreCase(retailDetail.getSortOrder()))) {
					retailDetail.setSortOrder("ASC");
				}

				retailersDetails = findDao.findScanSeeCategorySearch(retailDetail);

				//			RetailersDetails retailGrpSort = new RetailersDetails();

				if (retailersDetails != null && retailersDetails.getRetailerDetail() != null
						&& !retailersDetails.getRetailerDetail().isEmpty()) {

					/*retailersDetails.setGroupBy(retailDetail.getGroupBy());
				retailersDetails.setSortColumn(retailDetail.getSortColumn());
				retailersDetails.setSortOrder(retailDetail.getSortOrder());*/
					/*
			 	Grouping on below parameter. 
			 		atoz - group by alphabetical.
			 		type - group by category. 

			    Sorting in database result set.
					name - Sort by RetailerName. 
			    	distance - Sort by Retailer distance.
			    	city - Sort by city.
					 */
					/*	retailGrpSort = FindHelper.groupAndSortRetailers(retailersDetails);

				retailersDetails.setRetailerDetail(null);
				retailersDetails.setCatList(retailGrpSort.getCatList());

				if (HubCitiConstants.NOTAPPLICABLE.equals(retailersDetails.getGroupBy())) {
					retailersDetails.getCatList().get(0).setGroupContent("");
				}*/

					strCompleteAddress = new String();

					for ( int i = 0; i< retailersDetails.getRetailerDetail().size(); i++ ) {
						if (null !=  retailersDetails.getRetailerDetail().get(i).getRetaileraddress1()
								&& !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getRetaileraddress1())
								&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getRetaileraddress1())) {
							strCompleteAddress = retailersDetails.getRetailerDetail().get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null !=  retailersDetails.getRetailerDetail().get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getCity())
								&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getCity())) {
							strCompleteAddress = strCompleteAddress +  retailersDetails.getRetailerDetail().get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						
						if (null !=  retailersDetails.getRetailerDetail().get(i).getState() && !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getState())
								&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getState())) {
							strCompleteAddress = strCompleteAddress +  retailersDetails.getRetailerDetail().get(i).getState() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						
						if (null !=  retailersDetails.getRetailerDetail().get(i).getPostalCode()
								&& !HubCitiConstants.NOTAPPLICABLE.equals( retailersDetails.getRetailerDetail().get(i).getPostalCode())
								&& !HubCitiConstants.EMPTYSTR.equals( retailersDetails.getRetailerDetail().get(i).getPostalCode())) {
							strCompleteAddress = strCompleteAddress +  retailersDetails.getRetailerDetail().get(i).getPostalCode() ;
						}
						
						retailersDetails.getRetailerDetail().get(i).setCompleteAddress(strCompleteAddress);
					}

					retailersDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailersDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				} else {
					retailersDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					retailersDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

				retailersDetails.setGroupBy(null);
				retailersDetails.setSortColumn(null);
				retailersDetails.setSortOrder(null);

				retailersDetails.setMainMenuId(mainMenuId);
				strJsonResponse = gson.toJson(retailersDetails);
			}

		}  catch (HubCitiException e) {
			LOG.error("Inside  FindServiceImpl : findSingleCategoryRetailers :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit FindServiceImpl : findScanSeeCategorySearch");
		return strJsonResponse;
	}
	
	
	/**
	 * ServiceImpl method is remove Category Search information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 */
	@CacheEvict(value = "categorySearchJson", allEntries = true)
	public String delScanSeeCategorySearchJson() throws HubCitiException {
		LOG.info("Inside FindServiceImpl : delScanSeeCategorySearchJson");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}
	
	/**
	 * ServiceImpl method is remove Find All Cache from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 */
	@CacheEvict(value = { "categoryJsonInfo", "singleCategoryRetailersJson", "categorySearchJson" }, allEntries = true)
	public String delFindAllCacheJson() throws HubCitiException {
		LOG.info("Inside FindServiceImpl : delFindAllCacheJson");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}
}
