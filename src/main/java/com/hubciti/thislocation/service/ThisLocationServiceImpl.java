package com.hubciti.thislocation.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.email.EmailComponent;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.CitiExperience;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.CustomerInfo;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.Retailer;
import com.hubciti.common.pojos.RetailerCreatedPages;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;
import com.hubciti.common.pojos.UserTrackingData;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.dao.FirstUseDao;
import com.hubciti.thislocation.dao.ThisLocationDao;

/**
 * Class For ThisLocationServiceImpl
 * 
 * @author Kumar D
 */
public class ThisLocationServiceImpl implements ThisLocationService {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ThisLocationServiceImpl.class);

	/**
	 * The variable of type ThisLocationDAO.
	 */
	private ThisLocationDao thisLocationDao;

	/**
	 * Instance variable for FirstUseDAO.
	 */
	public FirstUseDao firstUseDao;

	/**
	 * Setter method for thisLocationDao.
	 * 
	 * @param thisLocationDao
	 *            the Variable of type ThisLocationDAO
	 */
	public void setThisLocationDao(ThisLocationDao thisLocationDao) {
		this.thisLocationDao = thisLocationDao;
	}

	/**
	 * Setter method for FirstUseDAO.
	 * 
	 * @param firstUseDao
	 *            Instance of type FirstUseDAO.
	 */
	public void setFirstUseDao(FirstUseDao firstUseDao) {
		this.firstUseDao = firstUseDao;
	}

	/**
	 * ServiceImpl method is to get Partners.
	 * 
	 * @param iCitiExpID
	 *            input parameters.
	 * @return response XML as a String containing partners list
	 * @throws HubCitiException
	 */
	public String getPartners(Integer iCitiExpId, Integer userId,Integer bottomBtnId,Integer menuItemId)
			throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getPartners");

		String response = null;
		CitiExperience partnerList = null;

		if (null == userId) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			partnerList = thisLocationDao.getPartners(iCitiExpId, userId,bottomBtnId,menuItemId);

			if (null != partnerList && null != partnerList.getPartnerList()
					&& !partnerList.getPartnerList().isEmpty()) {
				partnerList.setResponseCode(HubCitiConstants.SUCCESSCODE);
				partnerList.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				partnerList.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				partnerList
				.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			response = XstreamParserHelper.produceXmlFromObject(partnerList);
		}

		LOG.info("Exit ThisLocationServiceImpl : getPartners");
		return response;
	}

	/**
	 * Service method is to get partner retailers.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	public String getRetailersForPartner(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailersForPartner");

		String response = null;
		String completeAddress;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper
				.parseXmlToObject(xml);

		if (thisLocationRequest.getUserId() == null
				|| thisLocationRequest.getRetAffId() == null
				|| thisLocationRequest.getHubCitiId() == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		} else if (thisLocationRequest.getmItemId() == null
				&& thisLocationRequest.getBottomBtnId() == null
				&& thisLocationRequest.getMainMenuId() == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}

		Integer mainMenuId = null;
		if (thisLocationRequest.getMainMenuId() == null) {
			MenuItem objMenuItem = new MenuItem();
			objMenuItem.setUserId(thisLocationRequest.getUserId());
			objMenuItem.setHubCitiId(thisLocationRequest.getHubCitiId());
			objMenuItem.setmItemId(thisLocationRequest.getmItemId());
			objMenuItem.setBottomBtnId(thisLocationRequest.getBottomBtnId());
			objMenuItem.setPlatform(thisLocationRequest.getPlatform());
			mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
		} else {
			mainMenuId = thisLocationRequest.getMainMenuId();
		}

		if (null == thisLocationRequest.getLowerLimit()) {
			thisLocationRequest.setLowerLimit(0);
		}
		if (null == thisLocationRequest.getSortColumn()) {
			thisLocationRequest.setSortColumn("Distance");
		}
		thisLocationRequest.setMainMenuId(mainMenuId);
		final RetailersDetails retailsDetails = thisLocationDao
				.getRetailersForPartner(thisLocationRequest,
						HubCitiConstants.HUBCITIAPP);

		List<Retailer> thisLocationRetailerInfoList = null;

		final List<RetailerDetail> retailerDetail = retailsDetails
				.getRetailerDetail();
		if (null != retailerDetail && !retailerDetail.isEmpty()) {
			thisLocationRetailerInfoList = new ArrayList<Retailer>();
			for (int i = 0; i < retailerDetail.size(); i++) {
				final Retailer thisLocationRetailerInfo = new Retailer();
				completeAddress = new String();
				if (null != retailerDetail.get(i).getRetaileraddress1()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i)
								.getRetaileraddress1())
								&& !HubCitiConstants.EMPTYSTR.equals(retailerDetail
										.get(i).getRetaileraddress1())) {
					completeAddress = retailerDetail.get(i)
							.getRetaileraddress1()
							+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (null != retailerDetail.get(i).getCity()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i).getCity())
						&& !HubCitiConstants.EMPTYSTR.equals(retailerDetail
								.get(i).getCity())) {
					completeAddress = completeAddress
							+ retailerDetail.get(i).getCity()
							+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (null != retailerDetail.get(i).getState()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i).getState())
						&& !HubCitiConstants.EMPTYSTR.equals(retailerDetail
								.get(i).getState())) {
					completeAddress = completeAddress
							+ retailerDetail.get(i).getState()+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (null != retailerDetail.get(i).getPostalCode()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i).getPostalCode())
						&& !HubCitiConstants.EMPTYSTR.equals(retailerDetail
								.get(i).getPostalCode())) {
					completeAddress = completeAddress
							+ retailerDetail.get(i).getPostalCode();
				}


				thisLocationRetailerInfo.setRowNumber(retailerDetail.get(i).getRowNumber());
				thisLocationRetailerInfo.setRetailerId(retailerDetail.get(i).getRetailerId());
				thisLocationRetailerInfo.setRetailerName(retailerDetail.get(i).getRetailerName());
				thisLocationRetailerInfo.setRetailLocationId(retailerDetail.get(i).getRetailLocationId());

				//thisLocationRetailerInfo.setRetailAddress(completeAddress);

				thisLocationRetailerInfo.setCompleteAddress(completeAddress);
				thisLocationRetailerInfo.setRetailAddress1(retailerDetail.get(i).getRetaileraddress1());

				if(!"".equals(Utility.checkNull(retailerDetail.get(i).getRetaileraddress2()))&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i).getRetaileraddress2())){
					thisLocationRetailerInfo.setRetailAddress2(retailerDetail.get(i).getRetaileraddress2());
				}
				thisLocationRetailerInfo.setDistance(retailerDetail.get(i)
						.getDistance());
				thisLocationRetailerInfo.setLogoImagePath(retailerDetail.get(i)
						.getLogoImagePath());
				thisLocationRetailerInfo.setBannerAdImagePath(retailerDetail
						.get(i).getBannerAdImagePath());
				thisLocationRetailerInfo.setRibbonAdImagePath(retailerDetail
						.get(i).getRibbonAdImagePath());
				thisLocationRetailerInfo.setRibbonAdURL(retailerDetail.get(i)
						.getRibbonAdURL());
				thisLocationRetailerInfo.setSaleFlag(retailerDetail.get(i)
						.getSaleFlag());
				thisLocationRetailerInfo.setSplashAdId(retailerDetail.get(i)
						.getSplashAdId());
				thisLocationRetailerInfo.setRetListId(retailerDetail.get(i)
						.getRetListId());

				thisLocationRetailerInfo.setLatitude(retailerDetail.get(i)
						.getRetLatitude());
				thisLocationRetailerInfo.setLongitude(retailerDetail.get(i)
						.getRetLongitude());
				thisLocationRetailerInfo.setRetGroupImg(retailerDetail.get(i)
						.getRetGroupImg());
				thisLocationRetailerInfo.setCity(retailerDetail.get(i)
						.getCity());
				thisLocationRetailerInfo.setState(retailerDetail.get(i)
						.getState());
				thisLocationRetailerInfo.setPostalCode(retailerDetail.get(i)
						.getPostalCode());

				thisLocationRetailerInfo.setLocationOpen(retailerDetail.get(i).getLocationOpen());

				thisLocationRetailerInfoList.add(thisLocationRetailerInfo);


			}

			retailsDetails.setRetailerDetail(null);
			retailsDetails.setRetailers(thisLocationRetailerInfoList);
			retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
			retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
		} else {
			retailsDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
			retailsDetails.setResponseText(retailsDetails.getNoRecordMsg());
		}

		retailsDetails.setNoRecordMsg(null);
		retailsDetails.setMainMenuId(mainMenuId);
		response = XstreamParserHelper.produceXmlFromObject(retailsDetails);

		LOG.info("Exit ThisLocationServiceImpl : getRetailersForPartner");
		return response;
	}

	/**
	 * Service method is to get Citi Experience retailers.
	 * 
	 * @param xml
	 * @return response XML as a string containing retailers
	 * @throws HubCitiException
	 */
	public String getRetailersForCitiExpirence(String xml)
			throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailersForCitiExpirence");

		String response = null;
		String strCompleteAddress;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationReq = (ThisLocationRequest) streamHelper
				.parseXmlToObject(xml);

		if (null == thisLocationReq.getUserId()
				|| null == thisLocationReq.getCitiExpId()
				|| null == thisLocationReq.getHubCitiId()) {
			return response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} /*
		 * else if (null == thisLocationReq.getmItemId() && null ==
		 * thisLocationReq.getBottomBtnId() && null ==
		 * thisLocationReq.getMainMenuId()) { return response =
		 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
		 * HubCitiConstants.INSUFFICENTREQUESTTEXT); }
		 */

		if (null == thisLocationReq.getLowerLimit()) {
			thisLocationReq.setLowerLimit(0);
		}

		/*
		 * Checking the condition for grouping. atoz - group by alphabetical
		 * (default). type - group by category.
		 */
		if (!"atoz".equalsIgnoreCase(thisLocationReq.getGroupBy())
				&& !"type".equalsIgnoreCase(thisLocationReq.getGroupBy())
				|| "".equals(Utility.checkNull(thisLocationReq.getGroupBy()))) {
			thisLocationReq.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
		}

		/*
		 * Condition to select Sorting Type 1. If sortBy is null- sorting is
		 * done by retailer name. 2. If sortBy is name- sorting is done by
		 * retailer name. 3. If sortBy is distance a. Sorting is done by
		 * distance b. No grouping is done, hence groupBy is changed to N/A.
		 */
		/*
		 * if (thisLocationReq.getSortColumn() != null) { if
		 * ("name".equalsIgnoreCase(thisLocationReq.getSortColumn())) {
		 * thisLocationReq.setSortColumn("RetailerName"); } else if
		 * ("city".equalsIgnoreCase(thisLocationReq.getSortColumn())){
		 * thisLocationReq.setSortColumn("City"); } else {
		 * thisLocationReq.setSortColumn("Distance"); //
		 * thisLocationReq.setGroupBy(HubCitiConstants.NOTAPPLICABLE); } } else
		 * { thisLocationReq.setSortColumn("Distance"); //
		 * thisLocationReq.setGroupBy(HubCitiConstants.NOTAPPLICABLE); }
		 */

		if (null == thisLocationReq.getSortColumn()) {
			thisLocationReq.setSortColumn("Distance");
		}

		// checking the condition for sorting in ascending or descending order.
		if (thisLocationReq.getSortOrder() == null
				|| !"Desc".equalsIgnoreCase(thisLocationReq.getSortOrder())) {
			thisLocationReq.setSortOrder("Asc");
		}

		Integer mainMenuId = null;
		if (thisLocationReq.getMainMenuId() == null) {
			MenuItem objMenuItem = new MenuItem();
			objMenuItem.setUserId(thisLocationReq.getUserId());
			objMenuItem.setHubCitiId(thisLocationReq.getHubCitiId());
			objMenuItem.setmItemId(thisLocationReq.getmItemId());
			objMenuItem.setBottomBtnId(thisLocationReq.getBottomBtnId());
			objMenuItem.setPlatform(thisLocationReq.getPlatform());
			mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
		} else {
			mainMenuId = thisLocationReq.getMainMenuId();
		}

		thisLocationReq.setMainMenuId(mainMenuId);
		final RetailersDetails retailsDetails = thisLocationDao
				.getRetailersForCitiExpirence(thisLocationReq,
						HubCitiConstants.HUBCITIAPP);

		// List<Retailer> thisLocationRetailerInfoList = null;

		// final List<RetailerDetail> retailerDetail =
		// retailsDetails.getRetailerDetail();
		if (null != retailsDetails.getRetailerDetail()
				&& !retailsDetails.getRetailerDetail().isEmpty()) {

			strCompleteAddress = new String();

			for (int i = 0; i < retailsDetails.getRetailerDetail().size(); i++) {
				if (null != retailsDetails.getRetailerDetail().get(i)
						.getRetaileraddress1()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailsDetails.getRetailerDetail()
								.get(i).getRetaileraddress1())
								&& !HubCitiConstants.EMPTYSTR.equals(retailsDetails
										.getRetailerDetail().get(i)
										.getRetaileraddress1())) {
					strCompleteAddress = retailsDetails.getRetailerDetail()
							.get(i).getRetaileraddress1()
							+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (null != retailsDetails.getRetailerDetail().get(i).getCity()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailsDetails.getRetailerDetail()
								.get(i).getCity())
								&& !HubCitiConstants.EMPTYSTR.equals(retailsDetails
										.getRetailerDetail().get(i).getCity())) {
					strCompleteAddress = strCompleteAddress
							+ retailsDetails.getRetailerDetail().get(i)
							.getCity() + HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (null != retailsDetails.getRetailerDetail().get(i)
						.getState()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailsDetails.getRetailerDetail()
								.get(i).getState())
								&& !HubCitiConstants.EMPTYSTR.equals(retailsDetails
										.getRetailerDetail().get(i).getState())) {
					strCompleteAddress = strCompleteAddress
							+ retailsDetails.getRetailerDetail().get(i)
							.getState()
							+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (null != retailsDetails.getRetailerDetail().get(i)
						.getPostalCode()
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailsDetails.getRetailerDetail()
								.get(i).getPostalCode())
								&& !HubCitiConstants.EMPTYSTR.equals(retailsDetails
										.getRetailerDetail().get(i).getPostalCode())) {
					strCompleteAddress = strCompleteAddress
							+ retailsDetails.getRetailerDetail().get(i)
							.getPostalCode();
				}

				retailsDetails.getRetailerDetail().get(i)
				.setCompleteAddress(strCompleteAddress);



			}
			/*
			 * retailsDetails.setSortBy(thisLocationReq.getSortColumn());
			 * retailsDetails.setSortOrder(thisLocationReq.getSortOrder());
			 * retailsDetails.setGroupBy(thisLocationReq.getGroupBy());
			 * 
			 * RetailersDetails groupedRetDet =
			 * ThisLocationHelper.groupAndSortCitiExpRetailers(retailsDetails);
			 */
			/*
			 * thisLocationRetailerInfoList = new ArrayList<Retailer>(); for
			 * (int i = 0; i < retailerDetail.size(); i++) { final Retailer
			 * thisLocationRetailerInfo = new Retailer(); completeAddress = new
			 * String(); if (null != retailerDetail.get(i).getRetaileraddress1()
			 * && !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).
			 * getRetaileraddress1()) &&
			 * !HubCitiConstants.EMPTYSTR.equals(retailerDetail
			 * .get(i).getRetaileraddress1())) { completeAddress =
			 * retailerDetail.get(i).getRetaileraddress1() +
			 * HubCitiConstants.COMMA; } if (null !=
			 * retailerDetail.get(i).getCity() &&
			 * !HubCitiConstants.NOTAPPLICABLE
			 * .equals(retailerDetail.get(i).getCity()) &&
			 * !HubCitiConstants.EMPTYSTR
			 * .equals(retailerDetail.get(i).getCity())) { completeAddress =
			 * completeAddress + retailerDetail.get(i).getCity() +
			 * HubCitiConstants.COMMA; } if (null !=
			 * retailerDetail.get(i).getPostalCode() &&
			 * !HubCitiConstants.NOTAPPLICABLE
			 * .equals(retailerDetail.get(i).getPostalCode()) &&
			 * !HubCitiConstants
			 * .EMPTYSTR.equals(retailerDetail.get(i).getPostalCode())) {
			 * completeAddress = completeAddress +
			 * retailerDetail.get(i).getPostalCode() + HubCitiConstants.COMMA; }
			 * if (null != retailerDetail.get(i).getState() &&
			 * !HubCitiConstants.
			 * NOTAPPLICABLE.equals(retailerDetail.get(i).getState()) &&
			 * !HubCitiConstants
			 * .EMPTYSTR.equals(retailerDetail.get(i).getState())) {
			 * completeAddress = completeAddress +
			 * retailerDetail.get(i).getState(); }
			 * 
			 * thisLocationRetailerInfo.setRowNumber(retailerDetail.get(i).
			 * getRowNumber());
			 * thisLocationRetailerInfo.setRetailerId(retailerDetail
			 * .get(i).getRetailerId());
			 * thisLocationRetailerInfo.setRetailerName
			 * (retailerDetail.get(i).getRetailerName());
			 * thisLocationRetailerInfo
			 * .setRetailLocationId(retailerDetail.get(i)
			 * .getRetailLocationId());
			 * thisLocationRetailerInfo.setRetailAddress(completeAddress);
			 * thisLocationRetailerInfo
			 * .setDistance(retailerDetail.get(i).getDistance());
			 * thisLocationRetailerInfo
			 * .setLogoImagePath(retailerDetail.get(i).getLogoImagePath());
			 * thisLocationRetailerInfo
			 * .setBannerAdImagePath(retailerDetail.get(i
			 * ).getBannerAdImagePath());
			 * thisLocationRetailerInfo.setRibbonAdImagePath
			 * (retailerDetail.get(i).getRibbonAdImagePath());
			 * thisLocationRetailerInfo
			 * .setRibbonAdURL(retailerDetail.get(i).getRibbonAdURL());
			 * thisLocationRetailerInfo
			 * .setSaleFlag(retailerDetail.get(i).getSaleFlag());
			 * thisLocationRetailerInfo
			 * .setSplashAdId(retailerDetail.get(i).getSplashAdId());
			 * thisLocationRetailerInfo
			 * .setRetListId(retailerDetail.get(i).getRetListId());
			 * 
			 * thisLocationRetailerInfo.setLatitude(retailerDetail.get(i).
			 * getRetLatitude());
			 * thisLocationRetailerInfo.setLongitude(retailerDetail
			 * .get(i).getRetLongitude());
			 * thisLocationRetailerInfo.setRetGroupImg
			 * (retailerDetail.get(i).getRetGroupImg());
			 * thisLocationRetailerInfo
			 * .setCatId(retailerDetail.get(i).getCatId());
			 * thisLocationRetailerInfo
			 * .setCatName(retailerDetail.get(i).getCatName());
			 * 
			 * thisLocationRetailerInfoList.add(thisLocationRetailerInfo); }
			 */
			/*
			 * retailsDetails.setRetailerDetail(null);
			 * 
			 * retailsDetails.setCatList(groupedRetDet.getCatList());
			 */
			// retailsDetails.setRetailers(thisLocationRetailerInfoList);
			retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
			retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

			/*
			 * if
			 * (HubCitiConstants.NOTAPPLICABLE.equals(retailsDetails.getGroupBy
			 * ())) { retailsDetails.getCatList().get(0).setGroupContent(""); }
			 */

		} else {
			retailsDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
			retailsDetails.setResponseText(retailsDetails.getNoRecordMsg());
			retailsDetails.setNoRecordMsg(null);
		}

		retailsDetails.setSortBy(null);
		retailsDetails.setSortOrder(null);
		retailsDetails.setGroupBy(null);

		retailsDetails.setMainMenuId(mainMenuId);
		response = XstreamParserHelper.produceXmlFromObject(retailsDetails);

		LOG.info("Exit ThisLocationServiceImpl : getRetailersForCitiExpirence");
		return response;
	}

	public String getRetailerSummary(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailerSummary");

		String response = null;

		List<RetailerDetail> retailSummarylst = null;
		List<RetailerCreatedPages> retCreatedPageslist = null;
		RetailersDetails retailerSummaryObj = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail retailerDetailObj = (RetailerDetail) streamHelper.parseXmlToObject(xml);

		if (null == retailerDetailObj.getRetailerId()
				|| null == retailerDetailObj.getUserId()
				|| null == retailerDetailObj.getRetailLocationId()
				|| null == retailerDetailObj.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			retailSummarylst = (List<RetailerDetail>) thisLocationDao.getRetailerSummary(retailerDetailObj);

			if (retailSummarylst != null && !retailSummarylst.isEmpty()) {
				retailerSummaryObj = new RetailersDetails();

				retailerSummaryObj.setClaimExist(retailSummarylst.get(0).getClaimExist());
				retailerSummaryObj.setClaimTxtMsg(retailSummarylst.get(0).getClaimTxtMsg());
				retailerSummaryObj.setUserMailId(retailSummarylst.get(0).getMail());

				retailerSummaryObj.setRetailerDetail(retailSummarylst);

				retCreatedPageslist = thisLocationDao.getRetCreatePages(retailerDetailObj);

				if (retCreatedPageslist != null && !retCreatedPageslist.isEmpty()) {
					retailerSummaryObj.setEventExist(retCreatedPageslist.get(0).getEventExist());
					retailerSummaryObj.setFundExist(retCreatedPageslist.get(0).getFundExist());

					retCreatedPageslist.get(0).setEventExist(null);
					retCreatedPageslist.get(0).setFundExist(null);
					retailerSummaryObj.setRetailerCreatedPageList(retCreatedPageslist);
				} else {
					retailerSummaryObj.setEventExist(0);
					retailerSummaryObj.setFundExist(0);
				}
			}

			if (retailerSummaryObj != null) {
				retailerSummaryObj.getRetailerDetail().get(0).setClaimExist(null);
				retailerSummaryObj.getRetailerDetail().get(0).setMail(null);
				retailerSummaryObj.getRetailerDetail().get(0).setClaimTxtMsg(null);
				retailerSummaryObj.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailerSummaryObj.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(retailerSummaryObj);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getRetailerSummary");
		return response;
	}

	/**
	 * The service method to get categories for partner retailers. It also
	 * validates the required fields.
	 * 
	 * @param retAffID
	 * @return response XML as a string containing categories
	 * @throws HubCitiException
	 */
	public String getCategoriesForPartners(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getCategoriesForPartners");

		String response = null;
		List<CategoryInfo> categoryInfoList = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper
				.parseXmlToObject(xml);

		if (thisLocationRequest.getRetAffId() == null
				|| thisLocationRequest.getHubCitiId() == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		} else {
			categoryInfoList = thisLocationDao
					.getCategoriesForPartners(thisLocationRequest);
			if (categoryInfoList != null && !categoryInfoList.isEmpty()) {
				categoryInfoList.get(0).setResponseCode(
						HubCitiConstants.SUCCESSCODE);
				categoryInfoList.get(0).setResponseText(
						HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(categoryInfoList);
				response = response.replaceAll("<list>", "<Category>");
				response = response.replaceAll("</list>", "</Category>");
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NOCATEGORYFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getCategoriesForPartners");
		return response;
	}

	/**
	 * The service method to get categories for group retailers. It also
	 * validates the required fields.
	 * 
	 * @param retGroupID
	 * @return response XML as a string containing categories
	 * @throws HubCitiException
	 */
	public String getCategoriesForGroupRetailers(String xml)
			throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getCategoriesForGroupRetailers");

		String response = null;
		List<CategoryInfo> categoryInfoList = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper
				.parseXmlToObject(xml);

		if (thisLocationRequest.getRetGroupId() == null
				|| thisLocationRequest.getHubCitiId() == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		} else {
			categoryInfoList = thisLocationDao
					.getCategoriesForGroupRetailers(thisLocationRequest);
			if (categoryInfoList != null && !categoryInfoList.isEmpty()) {
				response = XstreamParserHelper
						.produceXmlFromObject(categoryInfoList);
				String responseCodeText = "<responseCode>"
						+ HubCitiConstants.SUCCESSCODE
						+ "</responseCode><responseText>"
						+ HubCitiConstants.SUCCESSTEXT + "</responseText>";
				response = response.replaceAll("<list>", "<Category>"
						+ responseCodeText);
				response = response.replaceAll("</list>", "</Category>");
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NOCATEGORYFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getCategoriesForGroupRetailers");
		return response;
	}

	/**
	 * This is restEasy webservice method to get retiler special deals.
	 * 
	 * @param xml
	 * @return xml.
	 */
	public String getRetailerSpecialDeals(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailerSpecialDeals");

		String response = null;
		ProductDetails productDetailsObj = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ProductDetailsRequest productDetailsRequest = (ProductDetailsRequest) streamHelper
				.parseXmlToObject(xml);

		if (null == productDetailsRequest.getRetailLocationId()
				|| null == productDetailsRequest.getRetailId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			productDetailsObj = thisLocationDao
					.getRetailerSpecialDeals(productDetailsRequest);
			if (productDetailsObj != null) {
				productDetailsObj.setResponseCode(HubCitiConstants.SUCCESSCODE);
				productDetailsObj.setResponseText(HubCitiConstants.SUCCESSTEXT);

				response = XstreamParserHelper
						.produceXmlFromObject(productDetailsObj);

				response = response.replaceAll("<RetailerDetail>",
						"<SpecialOffer>");
				response = response.replaceAll("</RetailerDetail>",
						"</SpecialOffer>");
				response = response.replaceAll("<ProductDetails>",
						"<SpecialOffersList>");
				response = response.replaceAll("</ProductDetails>",
						"</SpecialOffersList>");
				response = response.replaceAll("<specialOfferlst>", "");
				response = response.replaceAll("</specialOfferlst>", "");

			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUNDCODE,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getRetailerSpecialDeals");
		return response;
	}

	public String getRetailerHotDeals(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailerHotDeals");

		String response = null;
		HotDealsDetails objHdDetails = null;
		List<HotDealsDetails> hotDealsDetailsList = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail retailerDetailObj = (RetailerDetail) streamHelper
				.parseXmlToObject(xml);

		if (null == retailerDetailObj.getRetailerId()
				|| null == retailerDetailObj.getUserId()
				|| null == retailerDetailObj.getRetailLocationId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			hotDealsDetailsList = thisLocationDao
					.getRetailerHotDeals(retailerDetailObj);

			if (hotDealsDetailsList != null && !hotDealsDetailsList.isEmpty()) {
				objHdDetails = new HotDealsDetails();
				objHdDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objHdDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				objHdDetails.setHdDetails(hotDealsDetailsList);
				response = XstreamParserHelper
						.produceXmlFromObject(objHdDetails);
				// response = response.replaceAll("<list>",
				// "<RetailerHotDealslist>");
				// response = response.replaceAll("</list>",
				// "</RetailerHotDealslist>");
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getRetailerHotDeals");
		return response;
	}

	public String getRetailerLocationCoupon(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailerLocationCoupon");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail retailerDetailObj = (RetailerDetail) streamHelper.parseXmlToObject(xml);
		List<CouponDetails> coupList = null;
		CouponDetails objCoupDetails = null;

		if (null == retailerDetailObj.getRetailerId() || null == retailerDetailObj.getUserId()
				|| null == retailerDetailObj.getRetailLocationId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			coupList = thisLocationDao.getRetailerLocationCoupon(retailerDetailObj);
			String strCoupLongDesc = null;
			if (coupList != null && !coupList.isEmpty()) {
				objCoupDetails = new CouponDetails();
				
				for (int i = 0; i < coupList.size(); i++) {
					if (!"".equals(Utility.checkNull(coupList.get(i).getCouponLongDescription()))) {
						strCoupLongDesc = coupList.get(i).getCouponLongDescription().replace("<![CDATA[", "").replace("]]>", "");
						coupList.get(i).setCouponLongDescription(Jsoup.parse(strCoupLongDesc).text());
					}
				}
				objCoupDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCoupDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				objCoupDetails.setCoupDetails(coupList);
				response = XstreamParserHelper.produceXmlFromObject(objCoupDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getRetailerLocationCoupon");
		return response;
	}

	/**
	 * The Service method for fetching Products list for the given Category and
	 * Retailer location ID.
	 * 
	 * @param xml
	 *            the request xml containingCategory and Retailer location ID.
	 * @return response XML as String with Product list or No Product found
	 *         message.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCitiException defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */

	public String getProductsInfo(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getProductsInfo");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ProductDetailsRequest productDetailsRequest = (ProductDetailsRequest) streamHelper
				.parseXmlToObject(xml);

		if (null == productDetailsRequest.getRetailLocationId()
				|| null == productDetailsRequest.getRetailId()
				|| null == productDetailsRequest.getRetailLocationId()
				|| null == productDetailsRequest.getHubCitiId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final ProductDetails productDetails = thisLocationDao
					.fetchProductDetails(productDetailsRequest,
							HubCitiConstants.HUBCITIAPP);
			if (productDetails != null) {
				productDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				productDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(productDetails);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NOPRODUCTFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getProductsInfo");
		return response;

	}

	/**
	 * The ServiceImpl method for fetching retailer special offer information.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing retailer details or if
	 *         HubCitiException exception it will return error message XML.
	 */
	public String getRetailerSpecialOffDetails(String xml)
			throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailerSpecialOffDetails");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail specialOfferRequest = (RetailerDetail) streamHelper
				.parseXmlToObject(xml);

		if (null == specialOfferRequest.getRetailerId()
				|| null == specialOfferRequest.getUserId()
				|| null == specialOfferRequest.getRetailLocationId()
				|| null == specialOfferRequest.getHubCitiId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final RetailersDetails retailersDetails = thisLocationDao
					.fetchSpecialOffers(specialOfferRequest);
			if (retailersDetails != null) {
				retailersDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailersDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(retailersDetails);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getRetailerSpecialOffDetails");
		return response;
	}

	/**
	 * For user tracking retailer summary clicks. Called whenever user taps item
	 * in the Retailer summary screen. Calls the XStreams helper to parse the
	 * given request XML and validates the required fields Method Type: POST
	 * 
	 * @param xml
	 * @return xml containing SUCCESS or FAILURE response
	 * @throws HubCitiException
	 */
	public String userTrackingRetailerSummaryClick(String xml)
			throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : userTrackingRetailerSummaryClick");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserTrackingData userTrackingData = (UserTrackingData) streamHelper
				.parseXmlToObject(xml);

		if (userTrackingData.getRetListId() == null
				|| userTrackingData.getRetDetailsId() == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = thisLocationDao
					.userTrackingRetailerSummaryClick(userTrackingData);
			if (response == HubCitiConstants.SUCCESSTEXT) {
				response = Utility.formResponseXml(
						HubCitiConstants.SUCCESSCODE,
						HubCitiConstants.SUCCESSTEXT);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.TECHNICALPROBLEMCODE,
						HubCitiConstants.FAILURECODE);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : userTrackingRetailerSummaryClick");
		return response;
	}

	/**
	 * This ServiceImpl method for fetching retailer Special Offer list.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing retailer details or
	 *         HubCitiException exception it will return error message XML.
	 */
	public String fetchSpecialDealsDetails(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailerSpecialOffDetails");

		String response = null;
		List<RetailerDetail> specialOfferlst = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail specialOfferRequest = (RetailerDetail) streamHelper
				.parseXmlToObject(xml);

		if (null == specialOfferRequest.getRetailerId()
				|| null == specialOfferRequest.getPageId()
				|| null == specialOfferRequest.getRetailLocationId()
				|| null == specialOfferRequest.getHubCitiId()
				|| null == specialOfferRequest.getScanTypeId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			specialOfferlst = thisLocationDao
					.fetchSpecialDealsDetails(specialOfferRequest);
			if (specialOfferlst != null && !specialOfferlst.isEmpty()) {
				response = XstreamParserHelper
						.produceXmlFromObject(specialOfferlst);
				response = response.replaceAll("<list>", "");
				response = response.replaceAll("</list>", "");
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getRetailerSpecialOffDetails");
		return response;
	}

	/**
	 * This ServiceImpl method for display retailer summary anything page
	 * details.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing anything page or
	 *         HubCitiException exception it will return error message XML.
	 */
	public String getRetSumAnyThingDetails(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetSumAnyThingDetails");

		String response = null;
		List<RetailerDetail> specialOfferlst = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail specialOfferRequest = (RetailerDetail) streamHelper
				.parseXmlToObject(xml);

		if (null == specialOfferRequest.getRetailerId()
				|| null == specialOfferRequest.getPageId()
				|| null == specialOfferRequest.getRetailLocationId()
				|| null == specialOfferRequest.getHubCitiId()
				|| null == specialOfferRequest.getScanTypeId()
				|| null == specialOfferRequest.getUserId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			specialOfferlst = thisLocationDao
					.getRetSumAnyThingDetails(specialOfferRequest);
			if (specialOfferlst != null && !specialOfferlst.isEmpty()) {
				response = XstreamParserHelper
						.produceXmlFromObject(specialOfferlst);
				response = response.replaceAll("<list>", "");
				response = response.replaceAll("</list>", "");
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getRetSumAnyThingDetails");
		return response;
	}

	public String fetchCouponDetails(String xml) throws HubCitiException {
		LOG.info("Inside CLRGalleryServiceImpl : fetchCouponDetails");

		String response = null;
		CouponDetails couponDetails = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail objCouponDetail = (RetailerDetail) streamHelper
				.parseXmlToObject(xml);

		if (null == objCouponDetail.getUserId()
				|| null == objCouponDetail.getCouponId()
				|| null == objCouponDetail.getHubCitiId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			couponDetails = thisLocationDao.fetchCouponDetails(objCouponDetail);
			if (null != couponDetails) {
				response = XstreamParserHelper
						.produceXmlFromObject(couponDetails);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit CLRGalleryServiceImpl : fetchCouponDetails");
		return response;
	}

	/**
	 * This ServiceImpl method for display retailer summary anything page
	 * details.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing anything page or
	 *         HubCitiException exception it will return error message XML.
	 */
	public String getHubCitiAnyThingDetails(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getHubCitiAnyThingDetails");

		String response = null;
		List<RetailerDetail> specialOfferlst = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail specialOfferRequest = (RetailerDetail) streamHelper
				.parseXmlToObject(xml);

		if (null == specialOfferRequest.getPageId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} /*else if (specialOfferRequest.getMainMenuId() == null
				&& specialOfferRequest.getmItemId() == null
				&& specialOfferRequest.getBottomBtnId() == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTCODE);
		}*/ else {

			Integer mainMenuId = null;
			if (specialOfferRequest.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(specialOfferRequest.getUserId());
				objMenuItem.setHubCitiId(specialOfferRequest.getHubCitiId());
				objMenuItem.setmItemId(specialOfferRequest.getmItemId());
				objMenuItem
				.setBottomBtnId(specialOfferRequest.getBottomBtnId());
				objMenuItem.setPlatform(specialOfferRequest.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = specialOfferRequest.getMainMenuId();
			}

			specialOfferRequest.setMainMenuId(mainMenuId);
			specialOfferlst = thisLocationDao
					.getHubCitiAnyThingDetails(specialOfferRequest);
			if (specialOfferlst != null && !specialOfferlst.isEmpty()) {

				// For handling HTML tags for Android as JSON object is
				// used.
				if (specialOfferRequest.getPlatform() != null
						&& "Android".equalsIgnoreCase(specialOfferRequest
								.getPlatform())) {
					String longDes = specialOfferlst.get(0)
							.getLongDescription();
					String shortDes = specialOfferlst.get(0).getsDescription();
					longDes = longDes.replace("<![CDATA[", "");
					longDes = longDes.replace("]]>", "");
					longDes = longDes.replaceAll("<", "&#60;");
					longDes = longDes.replaceAll(">", "&#62;");

					shortDes = shortDes.replace("<![CDATA[", "");
					shortDes = shortDes.replace("]]>", "");
					shortDes = shortDes.replaceAll("<", "&#60;");
					shortDes = shortDes.replaceAll(">", "&#62;");
					specialOfferlst.get(0).setLongDescription(longDes);
					specialOfferlst.get(0).setsDescription(shortDes);
				}

				specialOfferlst.get(0).setMainMenuId(mainMenuId);
				response = XstreamParserHelper
						.produceXmlFromObject(specialOfferlst);
				response = response.replaceAll("<list>", "");
				response = response.replaceAll("</list>", "");
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getHubCitiAnyThingDetails");
		return response;
	}

	/**
	 * This is Rest Easy Webservice for getting Latitude and Longitude for the
	 * given Zipcode.
	 * 
	 * @param zipcode
	 *            as request parameter for Zipcode.
	 * @return returns response XML Containing User's Location attributes if
	 *         exception it will return error message XML.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String getLatLong(Long zipcode) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getLatLong");

		String response = null;

		if (null == zipcode) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final ThisLocationRequest thisLocationRequestObj = thisLocationDao
					.fetchLatLong(zipcode);

			if (null != thisLocationRequestObj) {
				final HashMap<String, String> latAndLongMap = new HashMap<String, String>();
				latAndLongMap.put(HubCitiConstants.RESPONSECODE,
						HubCitiConstants.SUCCESSCODE);
				latAndLongMap.put(HubCitiConstants.RESPONSETEXT,
						HubCitiConstants.SUCCESSTEXT);
				latAndLongMap.put("Latitude",
						String.valueOf(thisLocationRequestObj.getLatitude()));
				latAndLongMap.put("Longitude",
						String.valueOf(thisLocationRequestObj.getLongitude()));
				response = Utility.formResponseXml("response", latAndLongMap);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.LOCATEONMAPTEXT);
			}

		}

		LOG.info("Exit ThisLocationServiceImpl : getLatLong");
		return response;
	}

	/**
	 * The service method for searching retailers. Calls the XStreams helper to
	 * parse the given request XML and validates the required fields.if
	 * validation succeeds then it will call DAO.
	 * 
	 * @param xml
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response XML as a String containing Retailers list or No
	 *         retailers found message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getRetailersInfoForLocation(String xml)
			throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailersInfoForLocation");

		Boolean gpsEnabled = false;
		String response = null;
		String completeAddress;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper
				.parseXmlToObject(xml);

		if (null == thisLocationRequest.getUserId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}
		gpsEnabled = thisLocationRequest.getGpsEnabled();
		if (!gpsEnabled) {
			thisLocationRequest.setLatitude(null);
			thisLocationRequest.setLongitude(null);
		}
		if (null == thisLocationRequest.getLastVisitedRecord()) {
			// if this property is zero
			thisLocationRequest.setLastVisitedRecord(0);
		}

		// For user tracking
		Integer mainMenuId = null;
		if (thisLocationRequest.getMainMenuId() == null) {
			MenuItem objMenuItem = new MenuItem();
			objMenuItem.setUserId(thisLocationRequest.getUserId());
			objMenuItem.setHubCitiId(thisLocationRequest.getHubCitiId());
			objMenuItem.setmItemId(thisLocationRequest.getmItemId());
			objMenuItem.setBottomBtnId(thisLocationRequest.getBottomBtnId());
			objMenuItem.setPlatform(thisLocationRequest.getPlatform());
			mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
		} else {
			mainMenuId = thisLocationRequest.getMainMenuId();
		}
		if (null == thisLocationRequest.getSortColumn()) {
			thisLocationRequest.setSortColumn("Distance");
		}

		thisLocationRequest.setMainMenuId(mainMenuId);
		RetailersDetails retailsDetails = thisLocationDao.fetchRetailerDetails(
				thisLocationRequest, HubCitiConstants.HUBCITIAPP);
		List<Retailer> retailerList = null;

		final List<RetailerDetail> retailerDetail = retailsDetails
				.getRetailerDetail();
		if (retailerDetail != null && !retailerDetail.isEmpty()) {
			retailerList = new ArrayList<Retailer>();
			for (int i = 0; i < retailerDetail.size(); i++) {
				final Retailer retailer = new Retailer();
				completeAddress = new String();
				if (!"".equals(Utility.checkNull(retailerDetail.get(i)
						.getRetaileraddress1()))
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i)
								.getRetaileraddress1())) {
					completeAddress = retailerDetail.get(i)
							.getRetaileraddress1()
							+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (!"".equals(Utility.checkNull(retailerDetail.get(i)
						.getCity()))
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i).getCity())) {
					completeAddress = completeAddress
							+ retailerDetail.get(i).getCity()
							+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (!"".equals(Utility.checkNull(retailerDetail.get(i)
						.getState()))
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i).getState())) {
					completeAddress = completeAddress
							+ retailerDetail.get(i).getState()
							+ HubCitiConstants.COMMA
							+ HubCitiConstants.EMPTYSTRING;
				}
				if (!"".equals(Utility.checkNull(retailerDetail.get(i)
						.getPostalCode()))
						&& !HubCitiConstants.NOTAPPLICABLE
						.equals(retailerDetail.get(i).getPostalCode())) {
					completeAddress = completeAddress
							+ retailerDetail.get(i).getPostalCode();
				}


				retailer.setRowNumber(retailerDetail.get(i).getRowNumber());
				retailer.setRetailerId(retailerDetail.get(i).getRetailerId());
				retailer.setRetailerName(retailerDetail.get(i).getRetailerName());
				retailer.setRetailLocationId(retailerDetail.get(i).getRetailLocationId());

				//				retailer.setRetailAddress(completeAddress);

				retailer.setCompleteAddress(completeAddress);
				retailer.setRetailAddress1(retailerDetail.get(i).getRetaileraddress1());

				retailer.setLocationOpen(retailerDetail.get(i).getLocationOpen());
				retailer.setCity(retailerDetail.get(i).getCity());
				retailer.setState(retailerDetail.get(i).getState());
				retailer.setPostalCode(retailerDetail.get(i).getPostalCode());


				if(!"".equals(Utility.checkNull(retailerDetail.get(i).getRetaileraddress2()))&&
						!HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getRetaileraddress2())){
					retailer.setRetailAddress2(retailerDetail.get(i).getRetaileraddress2());
				}

				retailer.setDistance(retailerDetail.get(i).getDistance());
				retailer.setLogoImagePath(retailerDetail.get(i)
						.getLogoImagePath());
				retailer.setBannerAdImagePath(retailerDetail.get(i)
						.getBannerAdImagePath());
				retailer.setRibbonAdImagePath(retailerDetail.get(i)
						.getRibbonAdImagePath());
				retailer.setRibbonAdURL(retailerDetail.get(i).getRibbonAdURL());
				if (null != retailerDetail.get(i).getSaleFlag()) {
					if (retailerDetail.get(i).getSaleFlag()) {
						retailer.setSaleFlg(1);
					} else {
						retailer.setSaleFlg(0);
					}
				} else {
					retailer.setSaleFlg(0);
				}

				retailer.setSplashAdId(retailerDetail.get(i).getSplashAdId());
				retailer.setRetListId(retailerDetail.get(i).getRetListId());
				retailer.setLatitude(retailerDetail.get(i).getLatitude());
				retailer.setLongitude(retailerDetail.get(i).getLongitude());
				retailer.setRetGroupImg(retailerDetail.get(i).getRetGroupImg());

				retailerList.add(retailer);
			}

			retailsDetails.setRetailerDetail(null);
			retailsDetails.setRetailers(retailerList);
			retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
			retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
		} else {
			retailsDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
			retailsDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
		}
		retailsDetails.setMainMenuId(mainMenuId);
		response = XstreamParserHelper.produceXmlFromObject(retailsDetails);

		LOG.info("Exit ThisLocationServiceImpl : getRetailersInfoForLocation");
		return response;
	}

	public String getAppSiteDetails(String xml) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getAppSiteDetails");

		String response = null;

		List<RetailerDetail> retailAppsiteList = null;
		List<RetailerCreatedPages> retailCreatedPageslst = null;
		RetailersDetails objRetailAppsite = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final RetailerDetail retailerDetailObj = (RetailerDetail) streamHelper.parseXmlToObject(xml);

		if (retailerDetailObj.getUserId() == null || retailerDetailObj.getHubCitiId() == null
				|| retailerDetailObj.getLinkId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (retailerDetailObj.getmItemId() == null && retailerDetailObj.getMainMenuId() == null
				&& retailerDetailObj.getBottomBtnId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			Integer mainMenuId = null;
			if (retailerDetailObj.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(retailerDetailObj.getUserId());
				objMenuItem.setHubCitiId(retailerDetailObj.getHubCitiId());
				objMenuItem.setmItemId(retailerDetailObj.getmItemId());
				objMenuItem.setBottomBtnId(retailerDetailObj.getBottomBtnId());
				objMenuItem.setPlatform(retailerDetailObj.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = retailerDetailObj.getMainMenuId();
			}

			retailerDetailObj.setMainMenuId(mainMenuId);
			retailAppsiteList = (List<RetailerDetail>) thisLocationDao.getAppSiteDetails(retailerDetailObj);

			if (retailAppsiteList != null && !retailAppsiteList.isEmpty()) {
				objRetailAppsite = new RetailersDetails();

				objRetailAppsite.setClaimExist(retailAppsiteList.get(0).getClaimExist());
				objRetailAppsite.setClaimTxtMsg(retailAppsiteList.get(0).getClaimTxtMsg());
				objRetailAppsite.setUserMailId(retailAppsiteList.get(0).getMail());

				objRetailAppsite.setRetailerDetail(retailAppsiteList);

				retailCreatedPageslst = thisLocationDao.getRetCreatePages(retailerDetailObj);

				if (retailCreatedPageslst != null && !retailCreatedPageslst.isEmpty()) {
					objRetailAppsite.setEventExist(retailCreatedPageslst.get(0).getEventExist());
					objRetailAppsite.setFundExist(retailCreatedPageslst.get(0).getFundExist());
					retailCreatedPageslst.get(0).setEventExist(null);
					retailCreatedPageslst.get(0).setFundExist(null);
					objRetailAppsite.setRetailerCreatedPageList(retailCreatedPageslst);
				} else {
					objRetailAppsite.setEventExist(0);
					objRetailAppsite.setFundExist(0);
				}
			}

			if (objRetailAppsite != null) {
				objRetailAppsite.getRetailerDetail().get(0).setClaimExist(null);
				objRetailAppsite.getRetailerDetail().get(0).setMail(null);
				objRetailAppsite.getRetailerDetail().get(0).setClaimTxtMsg(null);
				objRetailAppsite.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objRetailAppsite.setResponseText(HubCitiConstants.SUCCESSTEXT);
				objRetailAppsite.setMainMenuId(mainMenuId);
				response = XstreamParserHelper.produceXmlFromObject(objRetailAppsite);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT, HubCitiConstants.MAINMENUID, mainMenuId.toString());
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getAppSiteDetails");
		return response;
	}

	/**
	 * The service method for fetching user's Latitude, Longitude info.This
	 * method validates user id and calls DAO method.
	 * 
	 * @param userID
	 *            whose location attributes need to be fetched.
	 * @return returns response XML Containing User's Location attributes or No
	 *         records found message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getUserLocationPoints(Integer userID) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getUserLocationPoints");

		String response = null;

		if (null == userID) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final ThisLocationRequest thisLocationRequestObj = thisLocationDao
					.fetchUserLocationDetails(userID);
			if (null != thisLocationRequestObj) {
				thisLocationRequestObj
				.setResponseCode(HubCitiConstants.SUCCESSCODE);
				thisLocationRequestObj
				.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(thisLocationRequestObj);
				response = response.replaceAll("<ThisLocationRequest>",
						"<UserLocationPoints>");
				response = response.replaceAll("</ThisLocationRequest>",
						"</UserLocationPoints>");
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.THISLOCATIONLATITUDELONGITUDE);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : getUserLocationPoints");
		return response;
	}

	/**
	 * The service method for update user zip code information .
	 * 
	 * @param userId
	 * @param zipcode
	 * @return String with success or failure response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String updateZipcode(Long userId, String zipcode, Integer hubCitiId)
			throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : updateZipcode");

		String response = null;

		if (null == userId || null == zipcode) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = thisLocationDao
					.updateZipcode(userId, zipcode, hubCitiId);
			if (!HubCitiConstants.INVALIDZIPCODE.equalsIgnoreCase(response)) {
				if (HubCitiConstants.ZIPCODEUPDATED.equals(response)) {
					response = Utility.formResponseXml(
							HubCitiConstants.SUCCESSCODE,
							HubCitiConstants.ZIPCODEUPDATED);
				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.SUCCESSCODE,
							HubCitiConstants.ZIPCODEUPDATED, "defPostalCode",
							response);
				}
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.INVALIDZIPCODE);
			}
		}

		LOG.info("Exit ThisLocationServiceImpl : updateZipcode");
		return response;
	}

	/**
	 * Method to get Special Offer retailer location List.
	 * 
	 * @param xml
	 *            input
	 * @return response code contains list else error Message
	 * @throws HubCitiException
	 *             throws if Exception Occurs
	 */
	public String getSpecialOfferRetLocList(String xml) throws HubCitiException {

		LOG.info(" Inside ThisLocationServiceImpl : getSpecialOfferRetLocList ");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();

		RetailerDetail requestRetailer = (RetailerDetail) streamHelper
				.parseXmlToObject(xml);

		if (null != requestRetailer.getUserId()
				&& null != requestRetailer.getPageId()
				&& null != requestRetailer.getRetailerId()
				&& null != requestRetailer.getHubCitiId()) {

			if (null == requestRetailer.getLowerLimit()) {
				requestRetailer.setLowerLimit(0);
			}
			RetailerDetail retailerDetail = thisLocationDao
					.getSpecialOfferRetLocList(requestRetailer);

			if (null != retailerDetail
					&& null != retailerDetail.getRetDetailList()
					&& !retailerDetail.getRetDetailList().isEmpty()) {

				retailerDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailerDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(retailerDetail);

			} else {

				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUNDCODE,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		} else {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		}

		LOG.info(" Exit ThisLocationServiceImpl : getSpecialOfferRetLocList ");

		return response;
	}

	/**
	 * Method to get Special Offer Details
	 * 
	 * @param xml
	 *            input
	 * @return response code contains list else error Message
	 * @throws HubCitiException
	 *             throws if Exception Occurs
	 */
	public String getSpecialOfferDetails(String xml) throws HubCitiException {

		LOG.info(" Inside ThisLocationServiceImpl : getSpecialOfferDetails ");

		String response = null;

		final XstreamParserHelper streamParser = new XstreamParserHelper();

		RetailerDetail responseSpecialOfferDetails = null;

		RetailerDetail requestretailerDetails = (RetailerDetail) streamParser
				.parseXmlToObject(xml);

		/*
		 * // && null != requestretailerDetails.getRetailLocationId() -> changes
		 * // done. 08_03_2015
		 */
		// Rollback done
		if (null != requestretailerDetails.getUserId()
				&& null != requestretailerDetails.getHubCitiId()
				&& null != requestretailerDetails.getRetailLocationId()
				&& null != requestretailerDetails.getRetailerId()
				&& null != requestretailerDetails.getPageId()) {

			responseSpecialOfferDetails = new RetailerDetail();

			responseSpecialOfferDetails = thisLocationDao
					.getSpecialOfferDetails(requestretailerDetails);

			if (null != responseSpecialOfferDetails
					&& null != responseSpecialOfferDetails.getRetDetailList()
					&& !responseSpecialOfferDetails.getRetDetailList()
					.isEmpty()) {

				if (null != requestretailerDetails.getPlatform()
						&& requestretailerDetails.getPlatform()
						.equalsIgnoreCase("ANDROID")) {

					for (RetailerDetail retailer : responseSpecialOfferDetails
							.getRetDetailList()) {

						if (null != retailer.getShortDesc()) {
							retailer.setShortDesc(retailer.getShortDesc()
									.replaceAll("<", "&#60;")
									.replace(">", "&#62;"));
						}
						if (null != retailer.getLongDesc()) {
							retailer.setLongDesc(retailer.getLongDesc()
									.replaceAll("<", "&#60;")
									.replace(">", "&#62;"));
						}

					}

				} else if (null != requestretailerDetails.getPlatform()
						&& requestretailerDetails.getPlatform()
						.equalsIgnoreCase("IOS")) {
					for (RetailerDetail retailer : responseSpecialOfferDetails
							.getRetDetailList()) {
						if (null != retailer.getShortDesc()) {
							retailer.setShortDesc("<![CDATA["
									+ retailer.getShortDesc() + "]]>");
						}
						if (null != retailer.getLongDesc()) {
							retailer.setLongDesc("<![CDATA["
									+ retailer.getLongDesc() + "]]>");
						}
					}
				}
				responseSpecialOfferDetails
				.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseSpecialOfferDetails
				.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(responseSpecialOfferDetails);

			} else if (null != responseSpecialOfferDetails
					&& null != responseSpecialOfferDetails.getExtLinkFlag()
					&& 1 == responseSpecialOfferDetails.getExtLinkFlag()) {

				responseSpecialOfferDetails
				.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseSpecialOfferDetails
				.setResponseText(HubCitiConstants.SUCCESSTEXT);
				responseSpecialOfferDetails.setRetDetailList(null);
				response = XstreamParserHelper
						.produceXmlFromObject(responseSpecialOfferDetails);

			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUNDCODE,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		} else {

			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		}

		LOG.info(" Exit ThisLocationServiceImpl : getSpecialOfferDetails ");

		return response;
	}



	/**
	 * 
	 * ServiceImpl gets user details and sends password to user email id.
	 * 
	 * @param strUsername
	 *            as request parameter
	 * @return The Account Information.
	 * @throws HubCitiException
	 * @throws MessagingException 
	 */
	public String claimYourBusiness(String strJsonReq) throws HubCitiException {
	
		LOG.info("Inside ThisLocationServiceImpl : claimYourBusiness");
	
		String strSubject = null;
		String strMsgBody = null;
		String[] toAddressList = null;
		String strJsonResponse = null;
		String smtpHost = null;
		String smtpPort = null;
		RetailerDetail objClaimDetail = null;
	
		ArrayList<AppConfiguration> arEmailConfigList = null;
		List<AppConfiguration> toAddrAndSubjectList = null;
		CustomerInfo objClaimDetailsDb = null;
	
		try {
	
			final Gson gson = new Gson();
			final CustomerInfo objCustomerInfo = gson.fromJson(strJsonReq, CustomerInfo.class);
	
			if (objCustomerInfo.getUserId()== null ||  objCustomerInfo.getHubCitiId()== null || objCustomerInfo.getRetLocationId() == null) {
				strJsonResponse = Utility.formResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return strJsonResponse;
			} 

			toAddrAndSubjectList = thisLocationDao.getClaimAppConfig(HubCitiConstants.CLAIM_BUSINESS, objCustomerInfo.getHubCitiId());
	
			if (toAddrAndSubjectList != null) {
				for (int i = 0; i < toAddrAndSubjectList.size(); i++) {
					if (HubCitiConstants.CLAIM_BUSINESS.equalsIgnoreCase(toAddrAndSubjectList.get(i).getConfigurationType())) {
						if (HubCitiConstants.TO_ADDRESS.equalsIgnoreCase(toAddrAndSubjectList.get(i).getScreenName())) {
							toAddressList = toAddrAndSubjectList.get(i).getScreenContent().split(",");
						}
						if (HubCitiConstants.CLAIM_SUBJECT.equalsIgnoreCase(toAddrAndSubjectList.get(i).getScreenName())) {
							strSubject = toAddrAndSubjectList.get(i).getScreenContent();
						}
					}
				}
				arEmailConfigList = firstUseDao.getAppConfig(HubCitiConstants.EMAILCONFIG);
	
				for (int j = 0; j < arEmailConfigList.size(); j++) {
					if (arEmailConfigList.get(j).getScreenName().equals(HubCitiConstants.SMTPHOST)) {
						smtpHost = arEmailConfigList.get(j).getScreenContent();
					}
					if (arEmailConfigList.get(j).getScreenName().equals(HubCitiConstants.SMTPPORT)) {
						smtpPort = arEmailConfigList.get(j).getScreenContent();
					}
				}
				objClaimDetailsDb = thisLocationDao.getClaimYourBusinessdetails(objCustomerInfo);
				
				strMsgBody = Utility.claimEmailTemplate(objCustomerInfo,objClaimDetailsDb.getCustomerInfoList().get(0));
				EmailComponent.multipleUsersmailingComponent(objCustomerInfo.getLoginEmail(), toAddressList, strSubject, strMsgBody, smtpHost, smtpPort);
	
				objClaimDetail = thisLocationDao.getClaimBusinessInfo(objCustomerInfo.getRetLocationId(), objCustomerInfo.getHubCitiId());
	
				if (objClaimDetail != null) {
					objClaimDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objClaimDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objClaimDetail);
					strJsonResponse = strJsonResponse.replace("\\u003c![CDATA[","");
					strJsonResponse = strJsonResponse.replace("]]\\u003e","");
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
	
			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
			}
	
		}  catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : claimYourBusiness :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (MessagingException e) {
			LOG.error("Inside  ThisLocationServiceImpl : claimYourBusiness :  MessagingException :" + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
	
		LOG.info("Exit ThisLocationServiceImpl : claimYourBusiness");
		return strJsonResponse;
	}



	/**
	 * 
	 * ServiceImpl is to get Claim Business details.
	 * 
	 * @param strUsername
	 *            as request parameter
	 * @return The Claim Information.
	 * @throws HubCitiException
	 */
	public String getClaimYourBusinessdetails(String strJsonReq) throws HubCitiException {

		LOG.info("Inside ThisLocationServiceImpl : getClaimYourBusinessdetails");

		String strJsonResponse = null;
		CustomerInfo objClaimDetail = null;

		try {

			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final CustomerInfo objCustomerInfo = gson.fromJson(strJsonReq, CustomerInfo.class);

				objClaimDetail = thisLocationDao.getClaimYourBusinessdetails(objCustomerInfo);

				if (null != objClaimDetail) {
					objClaimDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objClaimDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objClaimDetail);
					strJsonResponse = strJsonResponse.replace("\\u003c![CDATA[","");
					strJsonResponse = strJsonResponse.replace("]]\\u003e","");
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}

		}  catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getClaimYourBusinessdetails :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit ThisLocationServiceImpl : getClaimYourBusinessdetails");
		return strJsonResponse;
	}
	
	
	
	
	/**
	 * Service method is to get Citi Experience retailers.
	 * 
	 * @param strJSONInput
	 * @return response JSON as a string containing retailers
	 * @throws HubCitiException
	 */
	public String getRetailersForCitiExpirenceJson(String strJSONInput) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailersForCitiExpirenceJson");

		String strJsonResponse = null;
		String strCompleteAddress;

		try {

			final Gson gson = new Gson();
			final ThisLocationRequest thisLocationReq = gson.fromJson(strJSONInput, ThisLocationRequest.class);

			if (null == thisLocationReq.getUserId()
					|| null == thisLocationReq.getCitiExpId()
					|| null == thisLocationReq.getHubCitiId()) {
				return strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);
			}

			if (null == thisLocationReq.getLowerLimit()) {
				thisLocationReq.setLowerLimit(0);
			}

			/*
			 * Checking the condition for grouping. atoz - group by alphabetical
			 * (default). type - group by category.
			 */
			if (!"atoz".equalsIgnoreCase(thisLocationReq.getGroupBy())
					&& !"type".equalsIgnoreCase(thisLocationReq.getGroupBy())
					|| "".equals(Utility.checkNull(thisLocationReq.getGroupBy()))) {
				thisLocationReq.setGroupBy(HubCitiConstants.NOTAPPLICABLE);
			}


			if (null == thisLocationReq.getSortColumn()) {
				thisLocationReq.setSortColumn("Distance");
			}

			// checking the condition for sorting in ascending or descending order.
			if (thisLocationReq.getSortOrder() == null
					|| !"Desc".equalsIgnoreCase(thisLocationReq.getSortOrder())) {
				thisLocationReq.setSortOrder("Asc");
			}

			Integer mainMenuId = null;
			if (thisLocationReq.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(thisLocationReq.getUserId());
				objMenuItem.setHubCitiId(thisLocationReq.getHubCitiId());
				objMenuItem.setmItemId(thisLocationReq.getmItemId());
				objMenuItem.setBottomBtnId(thisLocationReq.getBottomBtnId());
				objMenuItem.setPlatform(thisLocationReq.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = thisLocationReq.getMainMenuId();
			}

			thisLocationReq.setMainMenuId(mainMenuId);
			final RetailersDetails retailsDetails = thisLocationDao.getRetailersForCitiExpirence(thisLocationReq,
					HubCitiConstants.HUBCITIAPP);


			if (null != retailsDetails.getRetailerDetail() && !retailsDetails.getRetailerDetail().isEmpty()) {

				strCompleteAddress = new String();

				for (int i = 0; i < retailsDetails.getRetailerDetail().size(); i++) {
					if (null != retailsDetails.getRetailerDetail().get(i).getRetaileraddress1() && !HubCitiConstants.NOTAPPLICABLE
							.equals(retailsDetails.getRetailerDetail().get(i).getRetaileraddress1()) && !HubCitiConstants.EMPTYSTR.equals(retailsDetails.getRetailerDetail().get(i).getRetaileraddress1())) {
						strCompleteAddress = retailsDetails.getRetailerDetail() .get(i).getRetaileraddress1() 
								+ HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retailsDetails.getRetailerDetail().get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals(retailsDetails.getRetailerDetail()
							.get(i).getCity()) && !HubCitiConstants.EMPTYSTR.equals(retailsDetails.getRetailerDetail().get(i).getCity())) {
						strCompleteAddress = strCompleteAddress + retailsDetails.getRetailerDetail().get(i).getCity() 
								+ HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retailsDetails.getRetailerDetail().get(i).getState() && !HubCitiConstants.NOTAPPLICABLE
							.equals(retailsDetails.getRetailerDetail().get(i).getState())
							&& !HubCitiConstants.EMPTYSTR.equals(retailsDetails.getRetailerDetail().get(i).getState())) {
						strCompleteAddress = strCompleteAddress+ retailsDetails.getRetailerDetail().get(i).getState()
								+ HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retailsDetails.getRetailerDetail().get(i).getPostalCode()&& !HubCitiConstants.NOTAPPLICABLE	.equals(retailsDetails.getRetailerDetail()
							.get(i).getPostalCode())&& !HubCitiConstants.EMPTYSTR.equals(retailsDetails.getRetailerDetail().get(i).getPostalCode())) {
						strCompleteAddress = strCompleteAddress + retailsDetails.getRetailerDetail().get(i).getPostalCode();
					}
					retailsDetails.getRetailerDetail().get(i).setCompleteAddress(strCompleteAddress);
				}

				retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				retailsDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				retailsDetails.setResponseText(retailsDetails.getNoRecordMsg());

			}
			retailsDetails.setNoRecordMsg(null);
			retailsDetails.setSortBy(null);
			retailsDetails.setSortOrder(null);
			retailsDetails.setGroupBy(null);

			retailsDetails.setMainMenuId(mainMenuId);
			strJsonResponse =  gson.toJson(retailsDetails);

		}  catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getRetailersForCitiExpirenceJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit ThisLocationServiceImpl : getRetailersForCitiExpirenceJson");
		return strJsonResponse;
	}
	
	
	

	/**
	 * The service method for searching retailers. 
	 * 
	 * @param strJsonReq
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response JSON as a String containing Retailers list or No
	 *         retailers found message.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getRetailersInfoForLocationJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailersInfoForLocationJson");

		Boolean gpsEnabled = false;
		String strJsonResponse = null;
		String completeAddress;

		try {

			final Gson gson = new Gson();
			final ThisLocationRequest thisLocationReq = gson.fromJson(strJsonReq, ThisLocationRequest.class);

			if (null == thisLocationReq.getUserId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return strJsonResponse;
			}
			gpsEnabled = thisLocationReq.getGpsEnabled();
			if (!gpsEnabled) {
				thisLocationReq.setLatitude(null);
				thisLocationReq.setLongitude(null);
			}
			if (null == thisLocationReq.getLastVisitedRecord()) {
				// if this property is zero
				thisLocationReq.setLastVisitedRecord(0);
			}

			// For user tracking
			Integer mainMenuId = null;
			if (thisLocationReq.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(thisLocationReq.getUserId());
				objMenuItem.setHubCitiId(thisLocationReq.getHubCitiId());
				objMenuItem.setmItemId(thisLocationReq.getmItemId());
				objMenuItem.setBottomBtnId(thisLocationReq.getBottomBtnId());
				objMenuItem.setPlatform(thisLocationReq.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = thisLocationReq.getMainMenuId();
			}
			
			if (null == thisLocationReq.getSortColumn()) {
				thisLocationReq.setSortColumn("Distance");
			}

			thisLocationReq.setMainMenuId(mainMenuId);
			RetailersDetails retailsDetails = thisLocationDao.fetchRetailerDetails(thisLocationReq, HubCitiConstants.HUBCITIAPP);
			List<Retailer> retailerList = null;

			final List<RetailerDetail> retailerDetail = retailsDetails.getRetailerDetail();
			if (retailerDetail != null && !retailerDetail.isEmpty()) {
				retailerList = new ArrayList<Retailer>();
				for (int i = 0; i < retailerDetail.size(); i++) {
					final Retailer retailer = new Retailer();
					completeAddress = new String();
					
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getRetaileraddress1()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getRetaileraddress1())) {
						completeAddress = retailerDetail.get(i).getRetaileraddress1()+ HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getCity()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getCity())) {
						completeAddress = completeAddress + retailerDetail.get(i).getCity() + HubCitiConstants.COMMA
								+ HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getState()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getState())) {
						completeAddress = completeAddress+ retailerDetail.get(i).getState()
								+ HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i).getPostalCode()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getPostalCode())) {
						completeAddress = completeAddress
								+ retailerDetail.get(i).getPostalCode();
					}


					retailer.setRowNumber(retailerDetail.get(i).getRowNumber());
					retailer.setRetailerId(retailerDetail.get(i).getRetailerId());
					retailer.setRetailerName(retailerDetail.get(i).getRetailerName());
					retailer.setRetailLocationId(retailerDetail.get(i).getRetailLocationId());

					//				retailer.setRetailAddress(completeAddress);

					retailer.setCompleteAddress(completeAddress);
					retailer.setRetailAddress1(retailerDetail.get(i).getRetaileraddress1());

					retailer.setLocationOpen(retailerDetail.get(i).getLocationOpen());
					retailer.setCity(retailerDetail.get(i).getCity());
					retailer.setState(retailerDetail.get(i).getState());
					retailer.setPostalCode(retailerDetail.get(i).getPostalCode());


					if(!"".equals(Utility.checkNull(retailerDetail.get(i).getRetaileraddress2()))&&
							!HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getRetaileraddress2())){
						retailer.setRetailAddress2(retailerDetail.get(i).getRetaileraddress2());
					}

					retailer.setDistance(retailerDetail.get(i).getDistance());
					retailer.setLogoImagePath(retailerDetail.get(i)
							.getLogoImagePath());
					retailer.setBannerAdImagePath(retailerDetail.get(i)
							.getBannerAdImagePath());
					retailer.setRibbonAdImagePath(retailerDetail.get(i)
							.getRibbonAdImagePath());
					retailer.setRibbonAdURL(retailerDetail.get(i).getRibbonAdURL());
					if (null != retailerDetail.get(i).getSaleFlag()) {
						if (retailerDetail.get(i).getSaleFlag()) {
							retailer.setSaleFlg(1);
						} else {
							retailer.setSaleFlg(0);
						}
					} else {
						retailer.setSaleFlg(0);
					}

					retailer.setSplashAdId(retailerDetail.get(i).getSplashAdId());
					retailer.setRetListId(retailerDetail.get(i).getRetListId());
					retailer.setLatitude(retailerDetail.get(i).getLatitude());
					retailer.setLongitude(retailerDetail.get(i).getLongitude());
					retailer.setRetGroupImg(retailerDetail.get(i).getRetGroupImg());

					retailerList.add(retailer);
				}

				retailsDetails.setRetailerDetail(null);
				retailsDetails.setRetailers(retailerList);
				retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				retailsDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				retailsDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}
			retailsDetails.setMainMenuId(mainMenuId);
			strJsonResponse =  gson.toJson(retailsDetails);

		}  catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getRetailersInfoForLocationJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit ThisLocationServiceImpl : getRetailersInfoForLocationJson");
		return strJsonResponse;
	}
	
	
	/**
	 * This ServiceImpl method to get Coupon details.
	 * 
	 * @param strJsonReq
	 *           
	 * @return String JSON with coupon information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	
	
	public String getCouponDetailsJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getCouponDetailsJson");

		String strJsonResponse = null;
		CouponDetails couponDetails = null;
		
		try {

			final Gson gson =  new GsonBuilder().disableHtmlEscaping().create();
			final RetailerDetail objCouponDetail = gson.fromJson(strJsonReq, RetailerDetail.class);

		if (null == objCouponDetail.getUserId() || null == objCouponDetail.getCouponId() 
				|| null == objCouponDetail.getHubCitiId()) {
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			
			couponDetails = thisLocationDao.fetchCouponDetails(objCouponDetail);
			
			if (null != couponDetails) {
				
				strJsonResponse =  gson.toJson(couponDetails).replace("<![CDATA[", "").replace("]]>", "");
			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		} catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getCouponDetailsJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit ThisLocationServiceImpl : getCouponDetailsJson");
		return strJsonResponse;
	}
	
	
	
	/**
	 * ServiceImpl method is to get partner retailers.
	 * 
	 * @param strJsonReq
	 * @return response JSON as a string containing retailers
	 * @throws HubCitiException
	 */
	public String getRetailersForPartnerJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailersForPartnerJson");

		String strJsonResponse = null;
		String completeAddress;

		try {
			final Gson gson = new Gson();
			final ThisLocationRequest thisLocationReq = gson.fromJson(strJsonReq, ThisLocationRequest.class);


			if (null == thisLocationReq.getUserId() || null == thisLocationReq.getRetAffId() || null == thisLocationReq.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return strJsonResponse;
			} else if (null == thisLocationReq.getmItemId() && null == thisLocationReq.getBottomBtnId() && null == thisLocationReq.getMainMenuId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return strJsonResponse;
			}

			Integer mainMenuId = null;
			if (thisLocationReq.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(thisLocationReq.getUserId());
				objMenuItem.setHubCitiId(thisLocationReq.getHubCitiId());
				objMenuItem.setmItemId(thisLocationReq.getmItemId());
				objMenuItem.setBottomBtnId(thisLocationReq.getBottomBtnId());
				objMenuItem.setPlatform(thisLocationReq.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = thisLocationReq.getMainMenuId();
			}

			if (null == thisLocationReq.getLowerLimit()) {
				thisLocationReq.setLowerLimit(0);
			}
			if (null == thisLocationReq.getSortColumn()) {
				thisLocationReq.setSortColumn("Distance");
			}
			thisLocationReq.setMainMenuId(mainMenuId);
			final RetailersDetails retailsDetails = thisLocationDao.getRetailersForPartner(thisLocationReq, HubCitiConstants.HUBCITIAPP);

			List<Retailer> thisLocationRetailerInfoList = null;

			final List<RetailerDetail> retailerDetail = retailsDetails.getRetailerDetail();

			if (null != retailerDetail && !retailerDetail.isEmpty()) {

				thisLocationRetailerInfoList = new ArrayList<Retailer>();

				for (int i = 0; i < retailerDetail.size(); i++) {
					final Retailer thisLocationRetailerInfo = new Retailer();

					completeAddress = new String();

					if (null != retailerDetail.get(i).getRetaileraddress1() && !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getRetaileraddress1())
							&& !HubCitiConstants.EMPTYSTR.equals(retailerDetail.get(i).getRetaileraddress1())) {
						completeAddress = retailerDetail.get(i).getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}

					if (null != retailerDetail.get(i).getCity() && !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getCity()) && !HubCitiConstants.EMPTYSTR.equals(retailerDetail.get(i).getCity())) {
						completeAddress = completeAddress + retailerDetail.get(i).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}

					if (null != retailerDetail.get(i).getState() && !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getState())
							&& !HubCitiConstants.EMPTYSTR.equals(retailerDetail.get(i).getState())) {
						completeAddress = completeAddress+ retailerDetail.get(i).getState()+ HubCitiConstants.COMMA+ HubCitiConstants.EMPTYSTRING;
					}

					if (null != retailerDetail.get(i).getPostalCode() && !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getPostalCode())
							&& !HubCitiConstants.EMPTYSTR.equals(retailerDetail.get(i).getPostalCode())) {
						completeAddress = completeAddress + retailerDetail.get(i).getPostalCode();
					}

					thisLocationRetailerInfo.setRowNumber(retailerDetail.get(i).getRowNumber());
					thisLocationRetailerInfo.setRetailerId(retailerDetail.get(i).getRetailerId());
					thisLocationRetailerInfo.setRetailerName(retailerDetail.get(i).getRetailerName());
					thisLocationRetailerInfo.setRetailLocationId(retailerDetail.get(i).getRetailLocationId());


					thisLocationRetailerInfo.setCompleteAddress(completeAddress);
					thisLocationRetailerInfo.setRetailAddress1(retailerDetail.get(i).getRetaileraddress1());

					if(!"".equals(Utility.checkNull(retailerDetail.get(i).getRetaileraddress2()))&& !HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail.get(i).getRetaileraddress2())){
						thisLocationRetailerInfo.setRetailAddress2(retailerDetail.get(i).getRetaileraddress2());
					}
					thisLocationRetailerInfo.setDistance(retailerDetail.get(i).getDistance());
					thisLocationRetailerInfo.setLogoImagePath(retailerDetail.get(i).getLogoImagePath());
					thisLocationRetailerInfo.setBannerAdImagePath(retailerDetail.get(i).getBannerAdImagePath());
					thisLocationRetailerInfo.setRibbonAdImagePath(retailerDetail.get(i).getRibbonAdImagePath());
					thisLocationRetailerInfo.setRibbonAdURL(retailerDetail.get(i).getRibbonAdURL());
					thisLocationRetailerInfo.setSaleFlag(retailerDetail.get(i).getSaleFlag());
					thisLocationRetailerInfo.setSplashAdId(retailerDetail.get(i).getSplashAdId());
					thisLocationRetailerInfo.setRetListId(retailerDetail.get(i).getRetListId());

					thisLocationRetailerInfo.setLatitude(retailerDetail.get(i).getRetLatitude());
					thisLocationRetailerInfo.setLongitude(retailerDetail.get(i).getRetLongitude());
					thisLocationRetailerInfo.setRetGroupImg(retailerDetail.get(i).getRetGroupImg());
					thisLocationRetailerInfo.setCity(retailerDetail.get(i).getCity());
					thisLocationRetailerInfo.setState(retailerDetail.get(i).getState());
					thisLocationRetailerInfo.setPostalCode(retailerDetail.get(i).getPostalCode());

					thisLocationRetailerInfo.setLocationOpen(retailerDetail.get(i).getLocationOpen());
					thisLocationRetailerInfoList.add(thisLocationRetailerInfo);
				}

				retailsDetails.setRetailerDetail(null);
				retailsDetails.setRetailers(thisLocationRetailerInfoList);
				retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				retailsDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				retailsDetails.setResponseText(retailsDetails.getNoRecordMsg());
			}

			retailsDetails.setNoRecordMsg(null);
			retailsDetails.setMainMenuId(mainMenuId);
			strJsonResponse =  gson.toJson(retailsDetails);
			strJsonResponse = strJsonResponse.replace("\\u003c![CDATA[", "").replace("]]\\u003e", "");

		}  catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getRetailersForPartnerJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit ThisLocationServiceImpl : getRetailersForPartnerJson");
		return strJsonResponse;
	}
	
	
	/**
	 * ServiceImpl method to get Special Offer Details
	 * 
	 * @param strJsonReq input
	 * @return response code contains Special Offer Details
	 * @throws HubCitiException
	 *             throws if Exception Occurs
	 */
	public String getSpecialOfferDetailJson(String strJsonReq) throws HubCitiException {
		LOG.info(" Inside ThisLocationServiceImpl : getSpecialOfferDetailJson ");

		String strJsonResponse = null;

		try {

			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final RetailerDetail objRetailerDetailParam = gson.fromJson(strJsonReq, RetailerDetail.class);
			RetailerDetail objRetailerDetail = null;

			if (null != objRetailerDetailParam.getUserId()
					&& null != objRetailerDetailParam.getHubCitiId()
					&& null != objRetailerDetailParam.getRetailLocationId()
					&& null != objRetailerDetailParam.getRetailerId()
					&& null != objRetailerDetailParam.getPageId()) {

				objRetailerDetail = new RetailerDetail();

				objRetailerDetail = thisLocationDao.getSpecialOfferDetails(objRetailerDetailParam);

				if (null != objRetailerDetail && null != objRetailerDetail.getRetDetailList()
						&& !objRetailerDetail.getRetDetailList().isEmpty()) {

					/*	if (null != requestretailerDetails.getPlatform() && requestretailerDetails.getPlatform().equalsIgnoreCase("ANDROID")) {

					for (RetailerDetail retailer : objRetailerDetail.getRetDetailList()) {

						if (null != retailer.getShortDesc()) {
							retailer.setShortDesc(retailer.getShortDesc().replaceAll("<", "&#60;").replace(">", "&#62;"));
						}
						if (null != retailer.getLongDesc()) {
							retailer.setLongDesc(retailer.getLongDesc().replaceAll("<", "&#60;").replace(">", "&#62;"));
						}

					}

				} else if (null != requestretailerDetails.getPlatform() && requestretailerDetails.getPlatform().equalsIgnoreCase("IOS")) {
					for (RetailerDetail retailer : objRetailerDetail.getRetDetailList()) {

						if (null != retailer.getShortDesc()) {
							retailer.setShortDesc("<![CDATA["+ retailer.getShortDesc() + "]]>");
						}

						if (null != retailer.getLongDesc()) {
							retailer.setLongDesc("<![CDATA["+ retailer.getLongDesc() + "]]>");
						}
					}
				}*/
					objRetailerDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objRetailerDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objRetailerDetail);

				} else if (null != objRetailerDetail && null != objRetailerDetail.getExtLinkFlag() && 1 == objRetailerDetail.getExtLinkFlag()) {

					objRetailerDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objRetailerDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					objRetailerDetail.setRetDetailList(null);
					strJsonResponse = gson.toJson(objRetailerDetail);

				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getSpecialOfferDetails :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info(" Exit ThisLocationServiceImpl : getSpecialOfferDetails ");
		return strJsonResponse;
	}
	
	
	
	
	/**
	 * This ServiceImpl method to display HubCiti anything page details.
	 * 
	 * @param strJsonReq input
	 * @return JSON as containing HubCiti anything page details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getHubCitiAnyThingDetailsJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getHubCitiAnyThingDetailsJson");


		String strJsonResponse = null;
		List<RetailerDetail> anythingPagelist = null;

		try {
			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final RetailerDetail objRetailerDetailParam = gson.fromJson(strJsonReq, RetailerDetail.class);


			if (null == objRetailerDetailParam.getPageId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			}  else {

				Integer mainMenuId = null;
				if (objRetailerDetailParam.getMainMenuId() == null) {
					MenuItem objMenuItem = new MenuItem();
					objMenuItem.setUserId(objRetailerDetailParam.getUserId());
					objMenuItem.setHubCitiId(objRetailerDetailParam.getHubCitiId());
					objMenuItem.setmItemId(objRetailerDetailParam.getmItemId());
					objMenuItem.setBottomBtnId(objRetailerDetailParam.getBottomBtnId());
					objMenuItem.setPlatform(objRetailerDetailParam.getPlatform());
					mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
				} else {
					mainMenuId = objRetailerDetailParam.getMainMenuId();
				}

				objRetailerDetailParam.setMainMenuId(mainMenuId);
				anythingPagelist = thisLocationDao.getHubCitiAnyThingDetails(objRetailerDetailParam);
				if (anythingPagelist != null && !anythingPagelist.isEmpty()) {

					// For handling HTML tags for Android as JSON object is used.
					/*if (objRetailerDetailParam.getPlatform() != null
							&& "Android".equalsIgnoreCase(objRetailerDetailParam.getPlatform())) {
						String longDes = anythingPagelist.get(0).getLongDescription();
						String shortDes = anythingPagelist.get(0).getsDescription();
						longDes = longDes.replace("<![CDATA[", "");
						longDes = longDes.replace("]]>", "");
						longDes = longDes.replaceAll("<", "&#60;");
						longDes = longDes.replaceAll(">", "&#62;");

						shortDes = shortDes.replace("<![CDATA[", "");
						shortDes = shortDes.replace("]]>", "");
						shortDes = shortDes.replaceAll("<", "&#60;");
						shortDes = shortDes.replaceAll(">", "&#62;");
						anythingPagelist.get(0).setLongDescription(longDes);
						anythingPagelist.get(0).setsDescription(shortDes);
					}*/

					anythingPagelist.get(0).setMainMenuId(mainMenuId);

					strJsonResponse = gson.toJson(anythingPagelist);
					strJsonResponse = strJsonResponse.replace("<![CDATA[", "").replace("]]>", "");
					strJsonResponse = strJsonResponse.substring(1, strJsonResponse.length()-1);

				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getHubCitiAnyThingDetailsJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit ThisLocationServiceImpl : getHubCitiAnyThingDetailsJson");
		return strJsonResponse;
	}
	
	
	
	/**
	 * The ServiceImpl method for fetching retailer special offer information.
	 * 
	 * @param xml
	 *            as the request XML containing user info and Retailer
	 *            id,retailer location id info.
	 * @return returns response XML As String Containing retailer details or if
	 *         HubCitiException exception it will return error message XML.
	 */
	public String getRetailerSpecialOffDetailsJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getRetailerSpecialOffDetailsJson");

		String strJsonResponse = null;

		try {
			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final RetailerDetail objRetDetail = gson.fromJson(strJsonReq, RetailerDetail.class);


			if (null == objRetDetail.getRetailerId()
					|| null == objRetDetail.getUserId()
					|| null == objRetDetail.getRetailLocationId()
					|| null == objRetDetail.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				if ("".equals(Utility.checkNull(objRetDetail.getSortColumn()))) {
					objRetDetail.setSortColumn("Distance");
				}
				
				if ("".equals(Utility.checkNull(objRetDetail.getSortOrder())) || (!"DESC".equalsIgnoreCase(objRetDetail.getSortOrder()))) {
					objRetDetail.setSortOrder("ASC");
				}
				
				final RetailersDetails retailersDetails = thisLocationDao.fetchSpecialOffers(objRetDetail);

				if (retailersDetails != null) {
					retailersDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailersDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(retailersDetails);
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getRetailerSpecialOffDetailsJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit ThisLocationServiceImpl : getRetailerSpecialOffDetailsJson");
		return strJsonResponse;
	}
	
	
	/**
	 * The ServiceImpl method for displaying  special offer list for location's.
	 * 
	 * @param strJsonReq input
	 * @return JSON as containing Special offer location list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getSpecialOfferLocationListJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getSpecialOfferLocationListJson");

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
				
				final RetailersDetails retailersDetails = thisLocationDao.getSpecialOfferLocationListJson(objRetDetail);

				if (retailersDetails != null) {
					retailersDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailersDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(retailersDetails);
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getSpecialOfferLocationListJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit ThisLocationServiceImpl : getSpecialOfferLocationListJson");
		return strJsonResponse;
	}
	
	
	/**
	 * The ServiceImpl method for displaying  special offer list for map location's.
	 * 
	 * @param strJsonReq input
	 * @return JSON as containing special offer list for map location's.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String getSpecialOfferLocationMapListJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside ThisLocationServiceImpl : getSpecialOfferLocationMapListJson");

		String strJsonResponse = null;

		List<RetailerDetail> arFeaturedSpecialList = new ArrayList<RetailerDetail>();
		List<RetailerDetail> arNonFeaturedSpecialList = new ArrayList<RetailerDetail>();
		try {
			final Gson gson = new Gson();
			final RetailerDetail objRetDetail = gson.fromJson(strJsonReq, RetailerDetail.class);
			RetailersDetails retailersDetail = new RetailersDetails();

			if (null == objRetDetail.getUserId() || null == objRetDetail.getHubCitiId() || "".equals(Utility.checkNull(objRetDetail.getPageIds()))) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				if ("".equals(Utility.checkNull(objRetDetail.getSortColumn()))) {
					objRetDetail.setSortColumn("Distance");
				}

				if ("".equals(Utility.checkNull(objRetDetail.getSortOrder())) || (!"DESC".equalsIgnoreCase(objRetDetail.getSortOrder()))) {
					objRetDetail.setSortOrder("ASC");
				}

				final RetailersDetails retailersDetails = thisLocationDao.getSpecialOfferLocationMapListJson(objRetDetail);

				if (retailersDetails != null) {

					for (int i = 0; i < retailersDetails.getFeaturedSpecialList().size(); i++) {

						if (retailersDetails.getFeaturedSpecialList().get(i).getIsFeatured()) {
							arFeaturedSpecialList.add(retailersDetails.getFeaturedSpecialList().get(i));
							retailersDetail.setFeaturedSpecialList(arFeaturedSpecialList);
						} else {
							arNonFeaturedSpecialList.add(retailersDetails.getFeaturedSpecialList().get(i));
							retailersDetail.setNonFeaturedSpecialList(arNonFeaturedSpecialList);
						}
					}

					retailersDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailersDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(retailersDetail);
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND,HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  ThisLocationServiceImpl : getSpecialOfferLocationMapListJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
		LOG.info("Exit ThisLocationServiceImpl : getSpecialOfferLocationMapListJson");
		return strJsonResponse;
	}
}
