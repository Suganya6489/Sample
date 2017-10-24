package com.hubciti.alertevent.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;

import com.google.gson.Gson;
import com.hubciti.alertevent.dao.AlertEventDao;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.AlertDetails;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.Menu;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.Retailer;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.dao.FirstUseDao;

/**
 * Service method for Alerts/Events.
 * 
 * @author dhruvanath_mm
 */
public class AlertEventServiceImpl implements AlertEventService {
	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(AlertEventServiceImpl.class);

	/**
	 * Instance variable for AlertEventDao.
	 */
	public AlertEventDao alertEventDao;

	/**
	 * Instance variable for FirstUseDAO.
	 */
	public FirstUseDao firstUseDao;

	/**
	 * Setter method for AlertEventDao.
	 * 
	 * @param alertEventDao
	 *            Instance of type AlertEventDao.
	 */
	public void setAlertEventDao(AlertEventDao alertEventDao) {
		this.alertEventDao = alertEventDao;
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

	public String getAlertsList(String xml) throws HubCitiException {
		LOG.info("Inside AlertEventServiceImpl : getAlertsList");

		String response = null;

		final XstreamParserHelper parser = new XstreamParserHelper();
		final MenuItem objMenuItem = (MenuItem) parser.parseXmlToObject(xml);

		if (objMenuItem.getUserId() == null
				|| objMenuItem.getHubCitiId() == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			Integer mainMenuId = null;
			if (objMenuItem.getMainMenuId() == null) {
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objMenuItem.getMainMenuId();
			}
			objMenuItem.setMainMenuId(mainMenuId);
			AlertDetails alertDetailsFromDao = alertEventDao
					.getAlertsList(objMenuItem);

			if (alertDetailsFromDao != null
					&& alertDetailsFromDao.getAlertList() != null
					&& !alertDetailsFromDao.getAlertList().isEmpty()) {
				AlertDetails objAlertDetails = new AlertDetails();
				objAlertDetails = AlertEventHelper
						.sortAlertListByCategory(alertDetailsFromDao
								.getAlertList());

				if (objAlertDetails != null
						&& objAlertDetails.getCategoryList() != null
						&& !objAlertDetails.getCategoryList().isEmpty()) {
					objAlertDetails
					.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objAlertDetails
					.setResponseText(HubCitiConstants.SUCCESSTEXT);
					objAlertDetails.setMainMenuId(mainMenuId);
					objAlertDetails.setMaxCount(alertDetailsFromDao
							.getMaxCount());
					objAlertDetails.setNextPage(alertDetailsFromDao
							.getNextPage());
					objAlertDetails.setMaxRowNum(alertDetailsFromDao
							.getMaxRowNum());
					objAlertDetails.setGrey(alertDetailsFromDao.getGrey());
					objAlertDetails.setGreen(alertDetailsFromDao.getGreen());
					objAlertDetails.setYellow(alertDetailsFromDao.getYellow());
					objAlertDetails.setRed(alertDetailsFromDao.getRed());
					response = XstreamParserHelper
							.produceXmlFromObject(objAlertDetails);
				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NOALERTSFOUND,
							HubCitiConstants.MAINMENUID, mainMenuId.toString());
				}
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NOALERTSFOUND,
						HubCitiConstants.MAINMENUID, mainMenuId.toString());
			}
		}

		LOG.info("Exit AlertEventServiceImpl : getAlertsList");
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

	public String getDiningRetInfoForLocation(String xml)
			throws HubCitiException {
		LOG.info("Inside AlertEventServiceImpl : getDiningRetInfoForLocation");

		String response = null;
		String completeAddress;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper
				.parseXmlToObject(xml);

		if (null == thisLocationRequest.getCuisineTypeId()
				|| null == thisLocationRequest.getHubCitiId()
				|| null == thisLocationRequest.getUserId()) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
			return response;
		}
		// if this property is zero
		if (null == thisLocationRequest.getLastVisitedRecord()) {
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

		final RetailersDetails retailsDetails = alertEventDao
				.getDiningRetInfoForLocation(thisLocationRequest);
		List<Retailer> thisLocationRetailerInfoList = null;
		if (null != retailsDetails) {
			final List<RetailerDetail> retailerDetail = retailsDetails
					.getRetailerDetail();
			if (retailerDetail != null && !retailerDetail.isEmpty()) {
				thisLocationRetailerInfoList = new ArrayList<Retailer>();
				for (int i = 0; i < retailerDetail.size(); i++) {
					final Retailer thisLocationRetailerInfo = new Retailer();
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
						// code review change
						completeAddress += retailerDetail.get(i).getCity()
								+ HubCitiConstants.COMMA
								+ HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i)
							.getPostalCode()))
							&& !HubCitiConstants.NOTAPPLICABLE
							.equals(retailerDetail.get(i)
									.getPostalCode())) {
						// code review changes
						completeAddress += retailerDetail.get(i)
								.getPostalCode()
								+ HubCitiConstants.COMMA
								+ HubCitiConstants.EMPTYSTRING;
					}
					if (!"".equals(Utility.checkNull(retailerDetail.get(i)
							.getState()))
							&& !HubCitiConstants.NOTAPPLICABLE
							.equals(retailerDetail.get(i).getState())) {
						// code review changes
						completeAddress += retailerDetail.get(i).getState();
					}

					thisLocationRetailerInfo.setRowNumber(retailerDetail.get(i)
							.getRowNumber());
					thisLocationRetailerInfo.setRetailerId(retailerDetail
							.get(i).getRetailerId());
					thisLocationRetailerInfo.setRetailerName(retailerDetail
							.get(i).getRetailerName());
					thisLocationRetailerInfo.setRetailLocationId(retailerDetail
							.get(i).getRetailLocationId());
					thisLocationRetailerInfo.setRetailAddress(completeAddress);
					thisLocationRetailerInfo.setDistance(retailerDetail.get(i)
							.getDistance());
					thisLocationRetailerInfo.setLogoImagePath(retailerDetail
							.get(i).getLogoImagePath());
					thisLocationRetailerInfo
					.setBannerAdImagePath(retailerDetail.get(i)
							.getBannerAdImagePath());
					thisLocationRetailerInfo
					.setRibbonAdImagePath(retailerDetail.get(i)
							.getRibbonAdImagePath());
					thisLocationRetailerInfo.setRibbonAdURL(retailerDetail.get(
							i).getRibbonAdURL());

					thisLocationRetailerInfo.setSplashAdId(retailerDetail
							.get(i).getSplashAdId());
					thisLocationRetailerInfo.setRetListId(retailerDetail.get(i)
							.getRetListId());
					thisLocationRetailerInfo.setAdvertisementId(retailerDetail
							.get(i).getAdvertisementId());
					thisLocationRetailerInfo.setRetGroupImg(retailerDetail.get(
							i).getRetGroupImg());
					thisLocationRetailerInfo.setDistance(retailerDetail.get(i)
							.getDistance());
					thisLocationRetailerInfoList.add(thisLocationRetailerInfo);
				}

				retailsDetails.setRetailerDetail(null);
				retailsDetails.setRetailers(thisLocationRetailerInfoList);
				retailsDetails.setMainMenuId(mainMenuId);
				retailsDetails.setMaxRowNum(retailsDetails.getMaxRowNum());
				retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(retailsDetails);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT,
						HubCitiConstants.MAINMENUID, mainMenuId.toString());
			}
		} else {
			response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,
					HubCitiConstants.NORECORDSFOUNDTEXT,
					HubCitiConstants.MAINMENUID, mainMenuId.toString());
		}

		LOG.info("Exit AlertEventServiceImpl : getDiningRetInfoForLocation");
		return response;
	}

	/**
	 * Method to capture click on Alert List.
	 * 
	 * @param xml
	 * @return String xml containing alert details.
	 * @throws HubCitiException.
	 */
	public String userTrackingAlertClick(Integer alertListId)
			throws HubCitiException {
		LOG.info("Inside AlertEventServiceImpl : userTrackingAlertClick");

		String response = null;
		if (alertListId == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = alertEventDao.userTrackingAlertClick(alertListId);

			if (response.equals(HubCitiConstants.SUCCESSTEXT)) {
				response = Utility.formResponseXml(
						HubCitiConstants.SUCCESSCODE,
						HubCitiConstants.SUCCESSTEXT);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.TECHNICALPROBLEMCODE,
						HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit AlertEventServiceImpl : userTrackingAlertClick");
		return response;
	}

	/**
	 * Method for getting dining categories.
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return category list.
	 * @throws HubCitiException
	 */
	public String getDiningCategories(Integer userId, Integer hubCitiId)
			throws HubCitiException {
		LOG.info("Inside AlertEventServiceImpl : getDiningCategories");

		String response = null;

		if (userId == null || hubCitiId == null) {
			response = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			CategoryDetails objCatDetails = alertEventDao.getDiningCategories(
					userId, hubCitiId);

			if (objCatDetails != null
					&& objCatDetails.getListCatDetails() != null
					&& !objCatDetails.getListCatDetails().isEmpty()) {
				objCatDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCatDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper
						.produceXmlFromObject(objCatDetails);
			} else {
				response = Utility.formResponseXml(
						HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit AlertEventServiceImpl : getDiningCategories");
		return response;
	}

	/**
	 * Service method to get event list, also convert request xml to object and
	 * response object to xml.
	 * 
	 * @param strJson
	 * @return xml string containing alerts list.
	 * @throws HubCitiException
	 */
	public String getEventList(String strJson) throws HubCitiException {
		LOG.info("Inside AlertEventServiceImpl : getEventList ");

		String strJsonResponse = null;
		try {

			final Gson gson = new Gson();
			final MenuItem objMenuItem = gson.fromJson(strJson, MenuItem.class);
			if  (!"Band".equals(objMenuItem.getListType())) {
				
				if (null == objMenuItem.getUserId() || null == objMenuItem.getHubCitiId()) {
					return strJsonResponse = Utility.validRespJSON(
							HubCitiConstants.INSUFFICENTREQUESTCODE,
							HubCitiConstants.INSUFFICENTREQUESTTEXT);
					
				} else if (null == objMenuItem.getmItemId()
						&& null == objMenuItem.getMainMenuId()
						&& null == objMenuItem.getBottomBtnId()
						&& null == objMenuItem.getPlatform()
						&& null == objMenuItem.getRetailId()
						&& null == objMenuItem.getRetailLocationId()) {
					
					return strJsonResponse = Utility.validRespJSON(
							HubCitiConstants.INSUFFICENTREQUESTCODE,
							HubCitiConstants.INSUFFICENTREQUESTTEXT);
				} 

			} 
				/*
				 * Checking the condition for grouping. atoz - group by
				 * alphabetical. type - group by category. date - group by start
				 * date (default).
				 */
				if (null != objMenuItem.getGroupBy()
						&& !"atoz".equalsIgnoreCase(objMenuItem.getGroupBy())
						&& !"type".equalsIgnoreCase(objMenuItem.getGroupBy())) {
					objMenuItem.setGroupBy("date");
				}

				/*
				 * Checking the condition for sorting in database result set. name -
				 * Sort by event name. date - Sort by event date. distance - Sort by
				 * event distance.
				 */
				/*
				 * if ( null != menuItem.getSortBy()) {
				 * menuItem.setSortColumn(menuItem.getSortBy()); } else {
				 * menuItem.setSortBy("Date"); menuItem.setSortColumn("Date"); }
				 */

				if ("name".equalsIgnoreCase(objMenuItem.getSortBy())) {
					objMenuItem.setSortColumn("HcEventName");
				} else if ("distance".equalsIgnoreCase(objMenuItem.getSortBy())) {
					objMenuItem.setSortColumn("Distance");
				} else if ("city".equalsIgnoreCase(objMenuItem.getSortBy())) {
					objMenuItem.setSortColumn("City");
				} else if ("atoz".equalsIgnoreCase(objMenuItem.getSortBy())) {
					objMenuItem.setSortColumn("atoz");
				} else {
					objMenuItem.setSortBy("date");
					objMenuItem.setSortColumn("Date");
				}

				// checking the condition for sorting in ascending or descending
				// order.
				if (null == objMenuItem.getSortOrder()
						|| !"Desc".equalsIgnoreCase(objMenuItem.getSortOrder())) {
					objMenuItem.setSortOrder("Asc");
				}

				Integer mainMenuId = null;
				if (null ==  objMenuItem.getMainMenuId()) {
					mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
				} else {
					mainMenuId = objMenuItem.getMainMenuId();
				}
				objMenuItem.setMainMenuId(mainMenuId);

				if (null == objMenuItem.getLowerLimit()) {
					objMenuItem.setLowerLimit(0);
				}

				if (null == objMenuItem.getIsRegApp()) {
					objMenuItem.setIsRegApp(0);
				}

				EventDetails eventDetails = alertEventDao.getEventList(objMenuItem);

				eventDetails.setSortOrder(objMenuItem.getSortOrder());
				eventDetails.setSortBy(objMenuItem.getSortBy());
				eventDetails.setGroupBy(objMenuItem.getGroupBy());

				final List<EventDetails> eventDetailsList = eventDetails.getEventList();
				EventDetails objEventDetails = new EventDetails();

				if (null != eventDetailsList && !eventDetailsList.isEmpty()) {

					/*
					 * Grouping on below parameter. atoz - group by alphabetical.
					 * type - group by category. date - group by start date
					 * (default).
					 * 
					 * Sorting in database result set. name - Sort by event name.
					 * date - Sort by event date.(default). distance - Sort by event
					 * distance. city - Sort by event city.
					 */
					objEventDetails = AlertEventHelper.sortEventList(eventDetails);

					if (null != objEventDetails 
							&& null != objEventDetails.getCategoryList()
							&& !objEventDetails.getCategoryList().isEmpty()) {

						objEventDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objEventDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

						objEventDetails.setMaxCount(eventDetails.getMaxCount());
						objEventDetails.setNextPage(eventDetails.getNextPage());
						objEventDetails.setMaxRowNum(eventDetails.getMaxRowNum());
					} else {
						objEventDetails
						.setResponseCode(HubCitiConstants.NORECORDSFOUND);
						objEventDetails
						.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					}

				} else {
					objEventDetails
					.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					objEventDetails
					.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

				if (1 == eventDetails.getBottomBtn()) {
					objEventDetails.setBottomBtnList(eventDetails.getBottomBtnList());
				}

				objEventDetails.setBottomBtn(eventDetails.getBottomBtn());
				objEventDetails.setMainMenuId(mainMenuId);

				strJsonResponse = gson.toJson(objEventDetails);
				strJsonResponse = strJsonResponse.replace("\\u003c![CDATA[", "").replace("]]\\u003e", "");
			
			
		}  catch (HubCitiException e) {
			LOG.error("Inside  AlertEventServiceImpl : getEventList :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
			LOG.info("Exit AlertEventServiceImpl : getEventList ");
			return strJsonResponse;
		}

		/**
		 * This ServiceImpl method for the input parameters and returns validation
		 * error if validation fails. This Method fetches event details information.
		 * 
		 * @param xml
		 *            contains information to fetch event details.
		 * @return String XML with event details.
		 * @throws HubCitiException
		 *             throws if exception occurs.
		 */
		public String getEventDetails(Integer eventId, Integer eventsListId,
				Integer hubCitiId, String platform) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getEventDetails");

			String response = null;
			String completeAddress = null;
			String completeAddress1 = null;
			List<EventDetails> evtDetailList = null;

//			if (null == eventId || null == eventsListId && null == hubCitiId) {
			if (null == eventId || null == hubCitiId) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null != eventId) {
				evtDetailList = alertEventDao.getEventDetails(eventId, eventsListId, hubCitiId);
				if (null != evtDetailList && !evtDetailList.isEmpty()) {


					evtDetailList = getONGoingEvent(evtDetailList);


					evtDetailList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
					evtDetailList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);

					completeAddress = new String();
					if (!"".equals(Utility.checkNull(evtDetailList.get(0).getAddress()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getAddress())) {
						completeAddress = evtDetailList.get(0).getAddress() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						evtDetailList.get(0).setAddress1(evtDetailList.get(0).getAddress());
						evtDetailList.get(0).setAddress(null);

					}

					if (!"".equals(Utility.checkNull(evtDetailList.get(0).getCity())) 
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getCity())) {

						completeAddress += evtDetailList.get(0).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						completeAddress1 = evtDetailList.get(0).getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						evtDetailList.get(0).setCity(null);
					}

					if (!"".equals(Utility.checkNull(evtDetailList.get(0) .getPostalCode()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getPostalCode())) {
						completeAddress += evtDetailList.get(0).getPostalCode()+ HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}

					if (!"".equals(Utility.checkNull(evtDetailList.get(0).getState()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getState())) {
						completeAddress += evtDetailList.get(0).getState();
					}

					if (!"".equals(Utility.checkNull(evtDetailList.get(0).getState()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getState())) {
						completeAddress1 += evtDetailList.get(0).getState()+ HubCitiConstants.EMPTYSTRING;
						evtDetailList.get(0).setState(null);
					}

					if (!"".equals(Utility.checkNull(evtDetailList.get(0).getPostalCode()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getPostalCode())) {
						completeAddress1 += evtDetailList.get(0).getPostalCode()+ HubCitiConstants.EMPTYSTRING;
						evtDetailList.get(0).setPostalCode(null);
					}

					evtDetailList.get(0).setAddress2(completeAddress1);
					evtDetailList.get(0).setAddress(completeAddress);
					/*evtDetailList.get(0).setIsDateFormated(false);
				evtDetailList.get(0).setIsTimeFormated(false);*/

					if (!"".equals(Utility.checkNull(evtDetailList.get(0).getStartDate()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getStartDate())) {
						evtDetailList.get(0).setStartDate(Utility.formattedDate(evtDetailList.get(0).getStartDate()));
					}

					if (!"".equals(Utility.checkNull(evtDetailList.get(0).getEndDate()))
							&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndDate())) {
						evtDetailList.get(0).setEndDate(Utility.formattedDate(evtDetailList.get(0).getEndDate()));
					}
					/*if (!"".equals(Utility.checkNull(evtDetailList.get(0).getStartTime()))
						&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getStartTime())) {
					evtDetailList.get(0).setStartTime(evtDetailList.get(0).getStartTime());
				}
				if (!"".equals(Utility.checkNull(evtDetailList.get(0).getEndTime()))
						&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndTime())) {
					evtDetailList.get(0).setEndTime(evtDetailList.get(0).getEndTime());
				}
				evtDetailList.get(0).setIsDateFormated(null);
				evtDetailList.get(0).setIsTimeFormated(null);*/

					if (null != platform && platform.equalsIgnoreCase("ANDROID")) {

						if (null != evtDetailList.get(0).getLongDes()) {

							evtDetailList.get(0).setFlag(true);

							evtDetailList.get(0).setLongDes(evtDetailList.get(0).getLongDes()
									.replace("<![CDATA[", "")
									.replace("]]>", "")
									.replaceAll("<", "&#60;")
									.replaceAll(">", "&#62;"));

							evtDetailList.get(0).setFlag(null);
						}

					}

					// eventDetailList.get(0).setStartDate(Utility.changeDateFormat1(eventDetailList.get(0).getStartDate()));
					evtDetailList.get(0).setStartTime(Utility.convertDBtimeAmPm(evtDetailList.get(0).getStartTime()));
					evtDetailList.get(0).setEndTime(Utility.convertDBtimeAmPm(evtDetailList.get(0).getEndTime()));

					response = XstreamParserHelper.produceXmlFromObject(evtDetailList);
					response = response.replaceAll("<list>", "");
					response = response.replaceAll("</list>", "");
					response = response.replaceAll("<string>", "");
					response = response.replaceAll("</string>", "");
				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

			LOG.info("Exit AlertEventServiceImpl : getEventDetails");
			return response;
		}

		/**
		 * The service method for fetch the hotel. Calls the XStreams helper to
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

		public String getEventHotelDisplay(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getEventHotelDisplay");

			String response = null;

			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final ThisLocationRequest thisLocationRequest = (ThisLocationRequest) streamHelper
					.parseXmlToObject(xml);

			if (null == thisLocationRequest.getEventId()
					|| null == thisLocationRequest.getHubCitiId()
					|| null == thisLocationRequest.getUserId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return response;
			}
			// if this property is zero
			if (null == thisLocationRequest.getLastVisitedRecord()) {
				thisLocationRequest.setLastVisitedRecord(0);
			}

			final RetailersDetails retailsDetails = alertEventDao
					.getEventHotelDisplay(thisLocationRequest);
			List<Retailer> arRetailerInfoList = null;
			if (null != retailsDetails) {
				final List<RetailerDetail> retailerDetail = retailsDetails
						.getRetailerDetail();
				if (null != retailerDetail && !retailerDetail.isEmpty()) {
					arRetailerInfoList = new ArrayList<Retailer>();
					for (int i = 0; i < retailerDetail.size(); i++) {
						final Retailer objRetailer = new Retailer();
						objRetailer.setRowNumber(retailerDetail.get(i).getRowNum());
						objRetailer.setRetailerName(retailerDetail.get(i)
								.getRetailerName());
						objRetailer.setRetailLocationId(retailerDetail.get(i)
								.getRetailLocationId());
						objRetailer.setRetListId(retailerDetail.get(i)
								.getRetListId());
						objRetailer.setRetailerId(retailerDetail.get(i)
								.getRetailerId());
						objRetailer
						.setDistance(retailerDetail.get(i).getDistance());
						objRetailer.setHotelPrice(retailerDetail.get(i).getPrice());
						objRetailer.setRating(retailerDetail.get(i).getRating());
						if (HubCitiConstants.NOTAPPLICABLE.equals(retailerDetail
								.get(i).getLogoImagePath())) {
							objRetailer
							.setLogoImagePath(HubCitiConstants.IMAGENOTFOUND);
						} else {
							objRetailer.setLogoImagePath(retailerDetail.get(i)
									.getLogoImagePath());
						}
						arRetailerInfoList.add(objRetailer);
					}
					retailsDetails.setMaxRowNum(retailsDetails.getMaxRowNum());
					retailsDetails.setRetailerDetail(null);
					retailsDetails.setRetailers(arRetailerInfoList);
					retailsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					retailsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper
							.produceXmlFromObject(retailsDetails);
				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			LOG.info("Exit AlertEventServiceImpl : getEventHotelDisplay");
			return response;
		}

		/**
		 * Method for getting Event categories.
		 * 
		 * @param userId
		 * @param hubCitiId
		 * @return category list.
		 * @throws HubCitiException
		 */
		public String getEventCategories(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getEventCategories");

			String response = null;

			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final MenuItem menuItemObj = (MenuItem) streamHelper
					.parseXmlToObject(xml);

			if (menuItemObj.getUserId() == null
					|| menuItemObj.getHubCitiId() == null) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				CategoryDetails objCatDetails = alertEventDao
						.getEventCategories(menuItemObj);

				if (objCatDetails != null
						&& objCatDetails.getListCatDetails() != null
						&& !objCatDetails.getListCatDetails().isEmpty()) {
					if (objCatDetails.getListCatDetails() != null
							&& !objCatDetails.getListCatDetails().isEmpty()) {
						objCatDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objCatDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					} else {
						objCatDetails
						.setResponseCode(HubCitiConstants.NORECORDSFOUND);
						objCatDetails
						.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					}
					response = XstreamParserHelper
							.produceXmlFromObject(objCatDetails);
				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

			LOG.info("Exit AlertEventServiceImpl : getEventCategories");
			return response;
		}

		/**
		 * This ServiceImpl method for the input parameters and returns validation
		 * error if validation fails. This Method fetches event hotel details
		 * information.
		 * 
		 * @param xml
		 *            contains information to fetch event hotel details.
		 * @return String XML with event details.
		 * @throws HubCitiException
		 *             throws if exception occurs.Retailer
		 */
		public String getEventHotelDetails(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getEventHotelDetails");

			String response = null;
			List<EventDetails> eventDetailList = null;

			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final Retailer objRetailer = (Retailer) streamHelper
					.parseXmlToObject(xml);

			if (null == objRetailer.getEventId()
					|| null == objRetailer.getRetListId()
					|| null == objRetailer.getHubCitiId()
					|| null == objRetailer.getUserId()
					|| null == objRetailer.getRetailLocationId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return response;
			} else {
				eventDetailList = alertEventDao.getEventHotelDetails(objRetailer);
				if (eventDetailList != null && !eventDetailList.isEmpty()) {
					eventDetailList.get(0).setResponseCode(
							HubCitiConstants.SUCCESSCODE);
					eventDetailList.get(0).setResponseText(
							HubCitiConstants.SUCCESSTEXT);

					response = XstreamParserHelper
							.produceXmlFromObject(eventDetailList);
					response = response.replaceAll("<list>", "");
					response = response.replaceAll("</list>", "");
				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

			LOG.info("Exit AlertEventServiceImpl : getEventHotelDetails");
			return response;
		}

		/**
		 * This Service method to to get locations (App Sites) associated to events.
		 * 
		 * @param xml
		 * @return app site list.
		 * @throws HubCitiException
		 */
		public String getEventAppSiteLocation(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getEventAppSiteLocation ");

			String response = null;
			List<Retailer> retailerList = null;
			RetailersDetails objDetails = null;

			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final Retailer objRetailer = (Retailer) streamHelper
					.parseXmlToObject(xml);

			if (null == objRetailer.getEventId() || null == objRetailer.getUserId()
					|| null == objRetailer.getHubCitiId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return response;
			} else {
				retailerList = alertEventDao.getEventAppSiteLocation(objRetailer);
				if (retailerList != null && !retailerList.isEmpty()) {
					String completeAddress = null;
					for (Retailer retObj : retailerList) {
						if (!"".equals(Utility.checkNull(retObj.getDistance()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getDistance())) {
							completeAddress = retObj.getDistance()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
						}
						if (!"".equals(Utility.checkNull(retObj.getAddress()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getAddress())) {
							// code review changes
							completeAddress += retObj.getAddress()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
							retObj.setAddress(null);
						}
						if (!"".equals(Utility.checkNull(retObj.getCity()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getCity())) {
							// code review changes
							completeAddress += retObj.getCity()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
							retObj.setCity(null);
						}
						if (!"".equals(Utility.checkNull(retObj.getState()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getState())) {
							// code review changes
							completeAddress += retObj.getState()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
							retObj.setState(null);
						}
						if (!"".equals(Utility.checkNull(retObj.getPostalCode()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getPostalCode())) {
							completeAddress += retObj.getPostalCode();
							retObj.setPostalCode(null);
						}
						// code review changes
						retObj.setAddress(completeAddress);
						/* retObj.setAddress(completeAddress.toString()); */
					}
					objDetails = new RetailersDetails();
					objDetails.setRetList(retailerList);
					objDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(objDetails);
				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

			LOG.info("Exit AlertEventServiceImpl : getEventAppSiteLocation ");
			return response;
		}

		/**
		 * ServiceImpl method to get fundraiser list, also convert request xml to
		 * object and response object to xml.
		 * 
		 * @param xml
		 * @return xml string containing fundraiser list.
		 * @throws HubCitiException
		 *             throws if exception occurs.
		 */
		public String getFundraiserList(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getFundraiserList ");

			String response = null;

			final XstreamParserHelper parser = new XstreamParserHelper();
			final MenuItem menuItem = (MenuItem) parser.parseXmlToObject(xml);

			if (null == menuItem.getUserId() || null == menuItem.getHubCitiId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == menuItem.getmItemId()
					&& null == menuItem.getMainMenuId()
					&& null == menuItem.getBottomBtnId()
					&& null == menuItem.getPlatform()
					&& null == menuItem.getRetailId()
					&& null == menuItem.getRetailLocationId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				if (menuItem.getLowerLimit() == null) {
					menuItem.setLowerLimit(0);
				}

				/*
				 * Checking the for grouping parameter date, atoz, type --> catagory
				 * To group Fundraiser. Node can have below values: 1. date – to
				 * group by date. 2. type – to group by category. 3. atoz – to group
				 * alphabetically. 4. If NULL (or other than above mentioned value)
				 * – by default ‘date’ is selected.
				 */
				if (!"atoz".equalsIgnoreCase(menuItem.getGroupBy())
						&& !"type".equalsIgnoreCase(menuItem.getGroupBy())
						&& !"".equalsIgnoreCase(Utility.checkNull(menuItem
								.getGroupBy()))) {
					menuItem.setGroupBy("date");
				}

				/*
				 * Checking the for sorting parameter. name - Sort by Fundraiser
				 * Name. date - Sort by Fundraiser StartDate.
				 */
				if ("name".equalsIgnoreCase(menuItem.getSortBy())) {
					menuItem.setSortColumn("fundName");
				} else {
					menuItem.setSortBy("date");
					menuItem.setSortColumn("StartDate");
				}

				// checking the condition for sorting in ascending or descending
				// order.
				if (menuItem.getSortOrder() == null
						|| !"Desc".equalsIgnoreCase(menuItem.getSortOrder())) {
					menuItem.setSortOrder("Asc");
				}

				Integer mainMenuId = null;
				if (menuItem.getMainMenuId() == null) {
					mainMenuId = firstUseDao.getMainMenuId(menuItem);
				} else {
					mainMenuId = menuItem.getMainMenuId();
				}

				menuItem.setMainMenuId(mainMenuId);

				Fundraiser fundraiser = alertEventDao.getFundraiserList(menuItem);

				fundraiser.setSortOrder(menuItem.getSortOrder());
				fundraiser.setSortBy(menuItem.getSortBy());
				fundraiser.setGroupBy(menuItem.getGroupBy());

				final List<Fundraiser> fundraiserList = fundraiser
						.getFundraiserList();
				Fundraiser objFundraiser = new Fundraiser();

				if (null != fundraiserList && !fundraiserList.isEmpty()) {

					objFundraiser = AlertEventHelper.sortFundraiserList(fundraiser);

					if (null != objFundraiser
							&& null != objFundraiser.getCategoryList()
							&& !objFundraiser.getCategoryList().isEmpty()) {
						objFundraiser.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objFundraiser.setResponseText(HubCitiConstants.SUCCESSTEXT);
						objFundraiser.setMaxCount(fundraiser.getMaxCount());
						objFundraiser.setNextPage(fundraiser.getNextPage());
						objFundraiser.setMaxRowNum(fundraiser.getMaxRowNum());
					} else {
						objFundraiser
						.setResponseCode(HubCitiConstants.NORECORDSFOUND);
						objFundraiser
						.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					}

				} else {
					objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					objFundraiser
					.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

				if (1 == fundraiser.getBottomBtn()) {
					objFundraiser.setBottomBtnList(fundraiser.getBottomBtnList());
				}

				objFundraiser.setBottomBtn(fundraiser.getBottomBtn());
				objFundraiser.setMainMenuId(mainMenuId);
				objFundraiser.setIsDeptFlag(fundraiser.getIsDeptFlag());

				response = XstreamParserHelper.produceXmlFromObject(objFundraiser);
			}

			LOG.info("Exit AlertEventServiceImpl : getFundraiserList ");
			return response;
		}

		/**
		 * ServiceImpl method to get list of fundraiser department list.
		 * 
		 * @return XML in the response containing DepartMent details.
		 * @throws HubCitiException
		 *             throws if exception occurs.
		 */
		public String getDepartmentList(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getDepartmentList ");

			String response = null;
			List<MenuItem> arMItemList = null;
			Menu objMenu = new Menu();

			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final MenuItem objMenuItem = (MenuItem) streamHelper
					.parseXmlToObject(xml);

			/*
			 * if (null == objMenuItem.getmItemId() && null ==
			 * objMenuItem.getBottomBtnId() && null == objMenuItem.getRetailId() &&
			 * null == objMenuItem.getRetailLocationId()) { response =
			 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
			 * HubCitiConstants.INSUFFICENTREQUESTTEXT); } else
			 */if (null == objMenuItem.getHubCitiId()
					 || null == objMenuItem.getUserId()) {
				 response = Utility.formResponseXml(
						 HubCitiConstants.INSUFFICENTREQUESTCODE,
						 HubCitiConstants.INSUFFICENTREQUESTTEXT);
			 } else {

				 arMItemList = alertEventDao.getDepartmentList(objMenuItem);

				 if (arMItemList != null && !arMItemList.isEmpty()) {

					 objMenu.setResponseCode(HubCitiConstants.SUCCESSCODE);
					 objMenu.setResponseText(HubCitiConstants.SUCCESSTEXT);

					 objMenu.setmItemList(arMItemList);

					 response = XstreamParserHelper.produceXmlFromObject(objMenu);
				 } else {
					 response = Utility.formResponseXml(
							 HubCitiConstants.NORECORDSFOUNDCODE,
							 HubCitiConstants.NORECORDSFOUNDTEXT);
				 }
			 }

			 LOG.info("Exit AlertEventServiceImpl : getDepartmentList ");
			 return response;
		}

		/**
		 * ServiceImpl method to to get locations (App Sites) associated to
		 * fundraiser.
		 * 
		 * @param xml
		 * @return app site list.
		 * @throws HubCitiException
		 *             throws if exception occurs.
		 */
		public String getFundAppSiteLocation(String xml) throws HubCitiException {
			LOG.info("Inside AlertfundraisererviceImpl : getFundAppSiteLocation ");

			String response = null;
			List<Retailer> retailerList = null;
			RetailersDetails objDetails = null;

			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final Retailer objRetailer = (Retailer) streamHelper
					.parseXmlToObject(xml);

			if (null == objRetailer.getEventId() || null == objRetailer.getUserId()
					|| null == objRetailer.getHubCitiId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
				return response;
			} else {

				retailerList = alertEventDao.getFundAppSiteLocation(objRetailer);

				if (retailerList != null && !retailerList.isEmpty()) {
					String completeAddress = null;

					for (Retailer retObj : retailerList) {
						if (!"".equals(Utility.checkNull(retObj.getDistance()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getDistance())) {
							completeAddress = retObj.getDistance()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
						}
						if (!"".equals(Utility.checkNull(retObj.getAddress()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getAddress())) {
							// code review chnages
							completeAddress += retObj.getAddress()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
							retObj.setAddress(null);
						}
						if (!"".equals(Utility.checkNull(retObj.getCity()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getCity())) {
							// code review chnages
							completeAddress += retObj.getCity()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
							retObj.setCity(null);
						}
						if (!"".equals(Utility.checkNull(retObj.getState()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getState())) {
							// code review chnages
							completeAddress += retObj.getState()
									+ HubCitiConstants.COMMA
									+ HubCitiConstants.EMPTYSTRING;
							retObj.setState(null);
						}
						if (!"".equals(Utility.checkNull(retObj.getPostalCode()))
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retObj
										.getPostalCode())) {
							// code review chnages
							completeAddress += retObj.getPostalCode();
							retObj.setPostalCode(null);
						}
						// code review chnages
						retObj.setAddress(completeAddress);
						/* retObj.setAddress(completeAddress.toString()); */
					}

					objDetails = new RetailersDetails();
					objDetails.setRetList(retailerList);

					objDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

					response = XstreamParserHelper.produceXmlFromObject(objDetails);

				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

			LOG.info("Exit AlertfundraisererviceImpl : getFundAppSiteLocation ");
			return response;
		}

		/**
		 * ServiceImpl method for displaying fundraiser details information.
		 * 
		 * @param xml
		 *            contains information to fetch fundraiser details.
		 * @return String XML with event details.
		 * @throws HubCitiException
		 *             throws if exception occurs.
		 */
		public String getFundraiserDetails(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getFundraiserDetails");

			String response = null;
			List<Fundraiser> fundrsrList = null;

			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final MenuItem objMenuItem = (MenuItem) streamHelper
					.parseXmlToObject(xml);

			if (null == objMenuItem.getFundId()
					&& null == objMenuItem.getFundListId()
					&& null == objMenuItem.getHubCitiId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
			}

			fundrsrList = alertEventDao.getFundraiserDetails(objMenuItem);

			if (null != fundrsrList && !fundrsrList.isEmpty()) {

				fundrsrList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
				fundrsrList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);

				if (!"".equals(Utility.checkNull(objMenuItem.getPlatform()))
						&& "Android".equalsIgnoreCase(objMenuItem.getPlatform())) {
					String strLongDesc = fundrsrList.get(0).getlDescription();

					strLongDesc = strLongDesc.replace("<![CDATA[", "");
					strLongDesc = strLongDesc.replace("]]>", "");
					strLongDesc = strLongDesc.replaceAll("<", "&#60;");
					strLongDesc = strLongDesc.replaceAll(">", "&#62;");

					fundrsrList.get(0).setlDescription(strLongDesc);
				}

				response = XstreamParserHelper.produceXmlFromObject(fundrsrList);

				response = response.replaceAll("<list>", "");
				response = response.replaceAll("</list>", "");

			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND,
						HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			LOG.info("Exit AlertEventServiceImpl : getFundraiserDetails");
			return response;
		}

		/**
		 * ServiceImpl method to get event logistics information.
		 * 
		 * @param contains
		 *            information event details.
		 * @return XML in the response containing event logistics information.
		 * @throws HubCitiException
		 *             throws if exception occurs.
		 */
		public String getEventLogistics(String xml) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getEventLogistics");

			String response = null;

			final XstreamParserHelper parser = new XstreamParserHelper();
			final MenuItem objMenuItem = (MenuItem) parser.parseXmlToObject(xml);

			if (null == objMenuItem.getUserId()
					|| null == objMenuItem.getHubCitiId()
					|| null == objMenuItem.getEventId()) {
				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				AlertDetails alertDetails = alertEventDao
						.getEventLogistics(objMenuItem);

				if (alertDetails != null && alertDetails.getAlertList() != null
						&& !alertDetails.getAlertList().isEmpty()) {

					alertDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					alertDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

					response = XstreamParserHelper
							.produceXmlFromObject(alertDetails);
				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

			LOG.info("Exit AlertEventServiceImpl : getEventLogistics");
			return response;
		}

		/**
		 * 
		 */
		public String getFundraiserLocationList(String xml) throws HubCitiException {

			LOG.info("Inside  AlertEventServiceImpl : getFundraiserLocationDisplay");
			Fundraiser fundraiserResponse = null;

			if (LOG.isDebugEnabled()) {
				LOG.info("Input Data " + xml);
			}

			String response = null;

			final XstreamParserHelper parser = new XstreamParserHelper();

			final Fundraiser fundraiserRequest = (Fundraiser) parser
					.parseXmlToObject(xml);

			if (null == fundraiserRequest.getUserId()
					|| null == fundraiserRequest.getHubCitiId()
					|| null == fundraiserRequest.getFundId()) {

				response = Utility.formResponseXml(
						HubCitiConstants.INSUFFICENTREQUESTCODE,
						HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {

				fundraiserResponse = alertEventDao
						.getFundraiserLocationList(fundraiserRequest);

				if (null != fundraiserResponse
						&& !fundraiserResponse.getFundraiserList().isEmpty()) {

					fundraiserResponse
					.setResponseCode(HubCitiConstants.SUCCESSCODE);
					fundraiserResponse
					.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper
							.produceXmlFromObject(fundraiserResponse);

				} else {
					response = Utility.formResponseXml(
							HubCitiConstants.NORECORDSFOUND,
							HubCitiConstants.NORECORDSFOUNDTEXT);
				}

			}
			return response;
		}



		/**
		 * This AlertEventServiceImpl method to gets Event onGoing information.
		 * 
		 * @param evtDetailList as a parameter.
		 * @return List<EventDetails> evtDetailList.
		 */
		@SuppressWarnings("deprecation")
		public List<EventDetails> getONGoingEvent(List<EventDetails> evtDetailList) {
			LOG.info("Inside AlertEventServiceImpl : getONGoingEventDetail");


			if (null == evtDetailList.get(0).getIsOngoing() || "0".equalsIgnoreCase(evtDetailList.get(0).getIsOngoing())) {
				evtDetailList.get(0).setIsOngoing("no");
				evtDetailList.get(0).setIsOnGoing(0);

				Format formatter = new SimpleDateFormat("MM/dd/yyyy");
				Date date = new Date();

				if (null != evtDetailList.get(0).getStartDate() && !"".equals(evtDetailList.get(0).getStartDate())) {
					String strtDate = Utility.formattedDate(evtDetailList.get(0).getStartDate());
					evtDetailList.get(0).setEventDate(strtDate);
					date = new Date(strtDate);
					evtDetailList.get(0).setEventSDate(strtDate);
				} else {
					evtDetailList.get(0).setEventSDate(formatter.format(date));
				}

				if (null != evtDetailList.get(0).getStartTime() && !"".equals(evtDetailList.get(0).getStartTime())) {
					String eventTime = evtDetailList.get(0).getStartTime();
					String[] tempTime = eventTime.split(":");
					evtDetailList.get(0).setEventSTimeHrs(tempTime[0]);
					evtDetailList.get(0).setEventSTimeMins(tempTime[1]);
				}

				if (null != evtDetailList.get(0).getEndDate() && !"".equals(evtDetailList.get(0).getEndDate())
						&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndDate())) {
					String endDate = Utility.formattedDate(evtDetailList.get(0).getEndDate());
					evtDetailList.get(0).setEventEDate(endDate);
				} else {
					evtDetailList.get(0).setEventEDate(formatter.format(date));
				}

				if (null != evtDetailList.get(0).getEndTime() && !"".equals(evtDetailList.get(0).getEndTime())
						&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndTime())) {
					String eventTime = evtDetailList.get(0).getEndTime();
					String[] tempTime = eventTime.split(":");
					evtDetailList.get(0).setEventETimeHrs(tempTime[0]);
					evtDetailList.get(0).setEventETimeMins(tempTime[1]);
				}

				// Daily Recurrence
				evtDetailList.get(0).setIsOngoingDaily("days");
				evtDetailList.get(0).setEveryWeekDay(1);

				// Weekly Recurrence
				evtDetailList.get(0).setEveryWeek(1);
				evtDetailList.get(0).setIsOngoingMonthly("date");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				Integer day = calendar.get(Calendar.DAY_OF_WEEK);
				evtDetailList.get(0).setDays(new String[]{day.toString()});

				// Monthly Recurrence
				evtDetailList.get(0).setDateOfMonth(calendar.get(Calendar.DATE));
				evtDetailList.get(0).setEveryMonth(1);
				Calendar tempCalendar = Calendar.getInstance();
				tempCalendar.setTime(date);
				tempCalendar.set(Calendar.DATE, 1);

				if (calendar.get(Calendar.DAY_OF_WEEK) < tempCalendar.get(Calendar.DAY_OF_WEEK)) {
					evtDetailList.get(0).setDayNumber((calendar.get(Calendar.WEEK_OF_MONTH)) - 1);
				} else {
					evtDetailList.get(0).setDayNumber(calendar.get(Calendar.WEEK_OF_MONTH));
				}

				day = calendar.get(Calendar.DAY_OF_WEEK);
				evtDetailList.get(0).setEveryWeekDayMonth(new String[]{day.toString()});
				evtDetailList.get(0).setEveryDayMonth(1);
				evtDetailList.get(0).setDayOfMonth(day.toString());

				// Range Of Recurrence
				evtDetailList.get(0).setOccurenceType("noEndDate");
				evtDetailList.get(0).setEndAfter(1);

			} else {

				evtDetailList.get(0).setIsOngoing("yes");
				evtDetailList.get(0).setIsOnGoing(1);

				if (null != evtDetailList.get(0).getStartDate() && !"".equals(evtDetailList.get(0).getStartDate())) {

					evtDetailList.get(0).setEventSDate(Utility.formattedDate(evtDetailList.get(0).getStartDate()));

				}

				if (null != evtDetailList.get(0).getEndDate() && !"".equals(evtDetailList.get(0).getEndDate())
						&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndDate())) {
					evtDetailList.get(0).setEventEDate(Utility.formattedDate(evtDetailList.get(0).getEndDate()));
				}

				if ("Daily".equalsIgnoreCase(evtDetailList.get(0).getRecurrencePatternName())) {
					if (evtDetailList.get(0).getIsWeekDay() == false) {
						evtDetailList.get(0).setIsOngoingDaily("days");
						evtDetailList.get(0).setEveryWeekDay(evtDetailList.get(0).getRecurrenceInterval());
					} else {
						evtDetailList.get(0).setIsOngoingDaily("weekDays");
						evtDetailList.get(0).setEveryWeekDay(1);
					}

					// Weekly Recurrence
					evtDetailList.get(0).setEveryWeek(1);
					Date date = new Date(evtDetailList.get(0).getEventSDate());

					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					Integer day = calendar.get(Calendar.DAY_OF_WEEK);
					evtDetailList.get(0).setDays(new String[]{day.toString()});

					// Monthly Recurrence
					evtDetailList.get(0).setIsOngoingMonthly("date");
					evtDetailList.get(0).setDateOfMonth(calendar.get(Calendar.DATE));
					evtDetailList.get(0).setEveryMonth(1);
					Calendar tempCalendar = Calendar.getInstance();

					tempCalendar.setTime(date);
					tempCalendar.set(Calendar.DATE, 1);

					if (calendar.get(Calendar.DAY_OF_WEEK) < tempCalendar.get(Calendar.DAY_OF_WEEK)) {
						evtDetailList.get(0).setDayNumber((calendar.get(Calendar.WEEK_OF_MONTH)) - 1);
					} else {
						evtDetailList.get(0).setDayNumber(calendar.get(Calendar.WEEK_OF_MONTH));
					}

					day = calendar.get(Calendar.DAY_OF_WEEK);
					evtDetailList.get(0).setEveryWeekDayMonth(new String[]{day.toString()});
					evtDetailList.get(0).setEveryDayMonth(1);
					evtDetailList.get(0).setDayOfMonth(day.toString());

				} else if ("Weekly".equalsIgnoreCase(evtDetailList.get(0).getRecurrencePatternName())) {
					evtDetailList.get(0).setEveryWeek(evtDetailList.get(0).getRecurrenceInterval());
					//				String[] tempDays = evtDetailList.get(0).getDays();

					// Daily Recurrence
					evtDetailList.get(0).setIsOngoingDaily("days");
					evtDetailList.get(0).setEveryWeekDay(1);

					// Monthly Recurrence
					Date date = new Date(evtDetailList.get(0).getEventSDate());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					Integer day = calendar.get(Calendar.DAY_OF_WEEK);
					evtDetailList.get(0).setIsOngoingMonthly("date");
					evtDetailList.get(0).setDateOfMonth(calendar.get(Calendar.DATE));
					evtDetailList.get(0).setEveryMonth(1);
					Calendar tempCalendar = Calendar.getInstance();
					tempCalendar.setTime(date);
					tempCalendar.set(Calendar.DATE, 1);

					if (calendar.get(Calendar.DAY_OF_WEEK) < tempCalendar.get(Calendar.DAY_OF_WEEK)) {
						evtDetailList.get(0).setDayNumber((calendar.get(Calendar.WEEK_OF_MONTH)) - 1);
					} else {
						evtDetailList.get(0).setDayNumber(calendar.get(Calendar.WEEK_OF_MONTH));
					}

					day = calendar.get(Calendar.DAY_OF_WEEK);
					evtDetailList.get(0).setEveryWeekDayMonth(new String[]{day.toString()});
					evtDetailList.get(0).setEveryDayMonth(1);
					evtDetailList.get(0).setDayOfMonth(day.toString());

				} else if ("Monthly".equalsIgnoreCase(evtDetailList.get(0).getRecurrencePatternName())) {
					Date date = new Date(evtDetailList.get(0).getEventSDate());
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					Integer day = calendar.get(Calendar.DAY_OF_WEEK);

					if (evtDetailList.get(0).getByDayNumber() == true) {
						evtDetailList.get(0).setIsOngoingMonthly("date");
						evtDetailList.get(0).setDateOfMonth(evtDetailList.get(0).getDayNumber());
						evtDetailList.get(0).setEveryMonth(evtDetailList.get(0).getRecurrenceInterval());

						Calendar tempCalendar = Calendar.getInstance();
						tempCalendar.setTime(date);
						tempCalendar.set(Calendar.DATE, 1);

						if (calendar.get(Calendar.DAY_OF_WEEK) < tempCalendar.get(Calendar.DAY_OF_WEEK)) {
							evtDetailList.get(0).setDayNumber((calendar.get(Calendar.WEEK_OF_MONTH)) - 1);
						} else {
							evtDetailList.get(0).setDayNumber(calendar.get(Calendar.WEEK_OF_MONTH));
						}
						day = calendar.get(Calendar.DAY_OF_WEEK);
						evtDetailList.get(0).setEveryWeekDayMonth(new String[]{day.toString()});
						evtDetailList.get(0).setEveryDayMonth(1);
					} else {
						evtDetailList.get(0).setIsOngoingMonthly("day");
						day = calendar.get(Calendar.DAY_OF_WEEK);
						/*String[] tempDays = evtDetailList.get(0).getDays();
					evtDetailList.get(0).setEveryWeekDayMonth(tempDays);*/

						evtDetailList.get(0).setEveryWeekDayMonth(evtDetailList.get(0).getDays());
						evtDetailList.get(0).setDays(null);

						evtDetailList.get(0).setEveryDayMonth(evtDetailList.get(0).getRecurrenceInterval());

						evtDetailList.get(0).setDateOfMonth(calendar.get(Calendar.DATE));
						evtDetailList.get(0).setEveryMonth(1);
					}

					// Weekly Recurrence
					evtDetailList.get(0).setEveryWeek(1);
					// eventDetails.setDays(new String[] {
					// day.toString() });
					// Daily Recurrence
					evtDetailList.get(0).setIsOngoingDaily("days");
					evtDetailList.get(0).setEveryWeekDay(1);
				}

				if (null == evtDetailList.get(0).getEndDate() || HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndDate())
						&& null == evtDetailList.get(0).getEndAfter() ) {
					Date date = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					Format formatter = new SimpleDateFormat("MM/dd/yyyy");
					evtDetailList.get(0).setOccurenceType("noEndDate");
					evtDetailList.get(0).setEndAfter(1);
					evtDetailList.get(0).setEventEDate(formatter.format(date));

				} else if (null != evtDetailList.get(0).getEndDate() && null != evtDetailList.get(0).getEndAfter()
						&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndDate())) {
					evtDetailList.get(0).setOccurenceType("endAfter");
				} else {
					evtDetailList.get(0).setOccurenceType("endBy");
					evtDetailList.get(0).setEndAfter(1);
				}

				if (null != evtDetailList.get(0).getStartTime() && !"".equals(evtDetailList.get(0).getStartTime())) {
					String eventTime = evtDetailList.get(0).getStartTime();
					String[] tempTime = eventTime.split(":");
					evtDetailList.get(0).setEventSTimeHrs(tempTime[0]);
					evtDetailList.get(0).setEventSTimeMins(tempTime[1]);
				}
				if (null != evtDetailList.get(0).getEndTime() && !"".equals(evtDetailList.get(0).getEndTime())
						&& !HubCitiConstants.NOTAPPLICABLE.equals(evtDetailList.get(0).getEndTime())) {
					String eventTime = evtDetailList.get(0).getEndTime();
					String[] tempTime = eventTime.split(":");
					evtDetailList.get(0).setEventETimeHrs(tempTime[0]);
					evtDetailList.get(0).setEventETimeMins(tempTime[1]);
				}
			}



			return evtDetailList;
		}


		/**
		 * ServiceImpl method is remove EventList information from cached. 
		 * 
		 * @return JSON containing success or failure information.
		 */
		@CacheEvict(value = "getEventList", allEntries = true)
		public String remEventListCache() throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : remEventListCache");
			final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			return response;
		}
		
		
		/**
		 * ServiceImpl method to get fundraiser list on tap on bottom button on menu.
		 * 
		 * @param strJsonReq
		 * @return  string containing fundraiser list.
		 * @throws HubCitiException
		 *             throws if exception occurs.
		 */
		public String getFundraiserListJson(String strJsonReq) throws HubCitiException {
			LOG.info("Inside AlertEventServiceImpl : getFundraiserListJson ");

			String strJsonResponse = null;
			try {

				final Gson gson = new Gson();
				final MenuItem menuItem = gson.fromJson(strJsonReq, MenuItem.class);

				if (null == menuItem.getUserId() || null == menuItem.getHubCitiId()) {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
				} else if (null == menuItem.getmItemId()
						&& null == menuItem.getMainMenuId()
						&& null == menuItem.getBottomBtnId()
						&& null == menuItem.getPlatform()
						&& null == menuItem.getRetailId()
						&& null == menuItem.getRetailLocationId()) {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
				} else {

					if (menuItem.getLowerLimit() == null) {
						menuItem.setLowerLimit(0);
					}

					/*
					 * Checking the for grouping parameter date, atoz, type --> catagory
					 * To group Fundraiser. Node can have below values: 1. date – to
					 * group by date. 2. type – to group by category. 3. atoz – to group
					 * alphabetically. 4. If NULL (or other than above mentioned value)
					 * – by default ‘date’ is selected.
					 */
					
					if (!"atoz".equalsIgnoreCase(menuItem.getGroupBy())
							&& !"type".equalsIgnoreCase(menuItem.getGroupBy())
							&& !"".equalsIgnoreCase(Utility.checkNull(menuItem.getGroupBy()))) {
						menuItem.setGroupBy("date");
					}

					/*
					 * Checking the for sorting parameter. name - Sort by Fundraiser Name. 
					 * date - Sort by Fundraiser StartDate.
					 */
					if ("name".equalsIgnoreCase(menuItem.getSortBy())) {
						menuItem.setSortColumn("fundName");
					} else {
						menuItem.setSortBy("date");
						menuItem.setSortColumn("StartDate");
					}

					// checking the condition for sorting in ascending or descending order.
					if (menuItem.getSortOrder() == null || !"Desc".equalsIgnoreCase(menuItem.getSortOrder())) {
						menuItem.setSortOrder("Asc");
					}

					Integer mainMenuId = null;
					if (menuItem.getMainMenuId() == null) {
						mainMenuId = firstUseDao.getMainMenuId(menuItem);
					} else {
						mainMenuId = menuItem.getMainMenuId();
					}

					menuItem.setMainMenuId(mainMenuId);

					Fundraiser fundraiser = alertEventDao.getFundraiserList(menuItem);

					fundraiser.setSortOrder(menuItem.getSortOrder());
					fundraiser.setSortBy(menuItem.getSortBy());
					fundraiser.setGroupBy(menuItem.getGroupBy());

					final List<Fundraiser> fundraiserList = fundraiser.getFundraiserList();
					Fundraiser objFundraiser = new Fundraiser();

					if (null != fundraiserList && !fundraiserList.isEmpty()) {

						objFundraiser = AlertEventHelper.sortFundraiserList(fundraiser);

						if (null != objFundraiser && null != objFundraiser.getCategoryList()
								&& !objFundraiser.getCategoryList().isEmpty()) {
							objFundraiser.setResponseCode(HubCitiConstants.SUCCESSCODE);
							objFundraiser.setResponseText(HubCitiConstants.SUCCESSTEXT);
							objFundraiser.setMaxCount(fundraiser.getMaxCount());
							objFundraiser.setNextPage(fundraiser.getNextPage());
							objFundraiser.setMaxRowNum(fundraiser.getMaxRowNum());
						} else {
							objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUND);
							objFundraiser.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
						}

					} else {
						objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUND);
						objFundraiser.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					}

					if (1 == fundraiser.getBottomBtn()) {
						objFundraiser.setBottomBtnList(fundraiser.getBottomBtnList());
					}

					objFundraiser.setBottomBtn(fundraiser.getBottomBtn());
					objFundraiser.setMainMenuId(mainMenuId);
					objFundraiser.setIsDeptFlag(fundraiser.getIsDeptFlag());

					strJsonResponse = gson.toJson(objFundraiser);
					strJsonResponse = strJsonResponse.replace("\\u003c![CDATA[", "").replace("]]\\u003e", "");
				}

			} catch (HubCitiException e) {
				LOG.error("Inside  AlertEventServiceImpl : getFundraiserListJson :  " + e.getMessage());
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			} 
				LOG.info("Exit AlertEventServiceImpl : getFundraiserListJson ");
				return strJsonResponse;
			}
}
