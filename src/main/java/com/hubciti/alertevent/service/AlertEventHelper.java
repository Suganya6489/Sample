package com.hubciti.alertevent.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.helper.SortAlertEventByCategory;
import com.hubciti.common.helper.SortAlertEventByDate;
import com.hubciti.common.helper.SortEventByCity;
import com.hubciti.common.helper.SortEventByDate;
import com.hubciti.common.helper.SortEventByName;
import com.hubciti.common.helper.SortEventsByDisance;
import com.hubciti.common.helper.SortFundCategoryByDate;
import com.hubciti.common.helper.SortFundraiserByCategory;
import com.hubciti.common.helper.SortFundraiserByDate;
import com.hubciti.common.helper.SortFundraiserByName;
import com.hubciti.common.helper.SortReverseEventByCity;
import com.hubciti.common.helper.SortReverseEventByDate;
import com.hubciti.common.helper.SortReverseEventByName;
import com.hubciti.common.helper.SortReverseEventsByDistance;
import com.hubciti.common.helper.SortReverseFundByDate;
import com.hubciti.common.helper.SortReverseFundByName;
import com.hubciti.common.pojos.AlertDetails;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.utility.Utility;

/**
 * Class used for sorting.
 * 
 * @author dhruvanath_mm
 */
public class AlertEventHelper {

	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AlertEventHelper.class);

	/**
	 * Method to sort and group alertDetails list by category
	 * 
	 * @param alertDetailsList
	 * @return
	 */
	public static AlertDetails sortAlertListByCategory(List<AlertDetails> alertDetailsList) {
		String methodName = "sortAlertListByCategory() in Class AlertEventHelper.";
		LOG.info("Inside Method: " + methodName);
		final HashMap<String, CategoryInfo> alertCategoryMap = new HashMap<String, CategoryInfo>();
		CategoryInfo objCategoryInfo = null;
		AlertDetails alertDetailsObj = new AlertDetails();
		String key = null;
		ArrayList<AlertDetails> alertList = null;

		for (AlertDetails objAlertDetails : alertDetailsList) {
			key = objAlertDetails.getAlertCatName();
			if (key != null && !"".equals(key)) {
				if (alertCategoryMap.containsKey(key)) {
					objCategoryInfo = alertCategoryMap.get(key);
					alertList = (ArrayList<AlertDetails>) objCategoryInfo.getAlertList();
					if (alertList != null && !alertList.isEmpty()) {
						final AlertDetails objAlert = new AlertDetails();
						objAlert.setAlertId(objAlertDetails.getAlertId());
						objAlert.setAlertListId(objAlertDetails.getAlertListId());
						objAlert.setAlertName(objAlertDetails.getAlertName());
						objAlert.setStartDate(objAlertDetails.getStartDate());
						objAlert.setEndDate(objAlertDetails.getEndDate());
						objAlert.setSeverityId(objAlertDetails.getSeverityId());
						objAlert.setShortDes(objAlertDetails.getDescription());
						objAlert.setHubCitiId(objAlertDetails.getHubCitiId());
						objAlert.setStartTime(objAlertDetails.getStartTime());
						objAlert.setEndTime(objAlertDetails.getEndTime());
						objAlert.setSevImgPath(objAlertDetails.getSevImgPath());
						alertList.add(objAlert);
						objCategoryInfo.setAlertList(alertList);
					}
				} else {
					objCategoryInfo = new CategoryInfo();
					objCategoryInfo.setCategoryId(objAlertDetails.getAlertCatId());
					objCategoryInfo.setCategoryName(objAlertDetails.getAlertCatName());
					alertList = new ArrayList<AlertDetails>();
					final AlertDetails objAlert = new AlertDetails();
					objAlert.setAlertId(objAlertDetails.getAlertId());
					objAlert.setAlertListId(objAlertDetails.getAlertListId());
					objAlert.setAlertName(objAlertDetails.getAlertName());
					objAlert.setStartDate(objAlertDetails.getStartDate());
					objAlert.setEndDate(objAlertDetails.getEndDate());
					objAlert.setSeverityId(objAlertDetails.getSeverityId());
					objAlert.setShortDes(objAlertDetails.getDescription());
					objAlert.setHubCitiId(objAlertDetails.getHubCitiId());
					objAlert.setStartTime(objAlertDetails.getStartTime());
					objAlert.setEndTime(objAlertDetails.getEndTime());
					objAlert.setSevImgPath(objAlertDetails.getSevImgPath());
					alertList.add(objAlert);
					objCategoryInfo.setAlertList(alertList);
				}
				alertCategoryMap.put(key, objCategoryInfo);
			}
		}
		final Set<Map.Entry<String, CategoryInfo>> set = alertCategoryMap.entrySet();
		final ArrayList<CategoryInfo> catInfoList = new ArrayList<CategoryInfo>();
		for (Map.Entry<String, CategoryInfo> entry : set) {
			catInfoList.add(entry.getValue());
		}
		SortAlertEventByCategory objSortAlertEventByCategory = new SortAlertEventByCategory();
		Collections.sort(catInfoList, objSortAlertEventByCategory);
		alertDetailsObj.setCategoryList(catInfoList);
		return alertDetailsObj;
	}

	/**
	 * Method to sort an group events. Grouping by - type (Category) - atoz
	 * (Alphabetical) - date (Event Date, i.e Start Date) Sorting by - name
	 * (Event Name) - date (Event Date, i.e Start Date)
	 * 
	 * @param eventDetailsList
	 * @return eventdetails object.
	 */
	public static EventDetails sortEventList(EventDetails eventDetailsFromService) {
		LOG.info("Inside AlertEventHelper : sortEventList");

		CategoryInfo objCategoryInfo = null;

		String key = null;
		String strSignCatID = null;

		ArrayList<EventDetails> eventList = null;
		List<EventDetails> onGoingEventList = null;
		List<EventDetails> nonOnGoingEventList = null;
		List<EventDetails> signEventList = null;

		CategoryInfo objCatOnGoingEvents = null;
		CategoryInfo objCatSigEvents = null;

		final HashMap<String, CategoryInfo> eventCategoryMap = new HashMap<String, CategoryInfo>();
		EventDetails eventDetailsObj = new EventDetails();

		final String sortOrder = eventDetailsFromService.getSortOrder();
		final String groupBy = eventDetailsFromService.getGroupBy();
		final String sortBy = eventDetailsFromService.getSortBy();


		// Separating OnGoing and Non-OnGoing events and putting in different lists
		onGoingEventList = new ArrayList<EventDetails>();
		nonOnGoingEventList = new ArrayList<EventDetails>();
		signEventList = new ArrayList<EventDetails>();

		for (EventDetails objEvent : eventDetailsFromService.getEventList()) {
			
			if (null == objEvent.getIsOngoing() || "0".equalsIgnoreCase(objEvent.getIsOngoing())) {
				objEvent.setIsOnGoing(0);
			} else {
				objEvent.setIsOnGoing(1);
			}
			if (HubCitiConstants.SIG_EVENTS.equalsIgnoreCase(objEvent.getEventCatName())) {
				signEventList.add(objEvent);
				if ("".equals(Utility.checkNull(strSignCatID))) {
					strSignCatID = objEvent.getEventCatId().toString();
				}
			} else if (objEvent.getIsOnGoing() == 1 ) {
				onGoingEventList.add(objEvent);
			} else {
				nonOnGoingEventList.add(objEvent);
			}

		}

		/**
		 * Looping Non-OnGoing event list for grouping. Grouping by - type
		 * (Category) - atoz (Alphabetical) - date (Event Date, i.e Start Date)
		 */
		for (EventDetails objEventDetails : nonOnGoingEventList) {

			if ("type".equalsIgnoreCase(groupBy)) {
				key = objEventDetails.getEventCatName();
			} else if ("atoz".equalsIgnoreCase(groupBy)) {
				key = Character.toString(objEventDetails.getEventName().charAt(0)).toUpperCase();
			} else {
				key = objEventDetails.getStartDate();
			}

			if (key != null && !"".equals(key)) {
				if (eventCategoryMap.containsKey(key)) {

					objCategoryInfo = eventCategoryMap.get(key);
					eventList = (ArrayList<EventDetails>) objCategoryInfo.getEventList();

					if (eventList != null && !eventList.isEmpty()) {
						final EventDetails objEvent = new EventDetails();
						objEvent.setEventId(objEventDetails.getEventId());
						objEvent.setEventName(objEventDetails.getEventName());
						objEvent.setEventListId(objEventDetails.getEventListId());
						objEvent.setImgPath(objEventDetails.getImgPath());
						objEvent.setStartDate(objEventDetails.getStartDate());
						objEvent.setEndDate(objEventDetails.getEndDate());
						objEvent.setStartTime(objEventDetails.getStartTime());
						objEvent.setEndTime(objEventDetails.getEndTime());
						objEvent.setDistance(objEventDetails.getDistance());
						objEvent.setEventCatId(objEventDetails.getEventCatId());
						objEvent.setEventCatName(objEventDetails.getEventCatName());
						objEvent.setIsOnGoing(objEventDetails.getIsOnGoing());
						objEvent.setCityName(objEventDetails.getCityName());

						/*objEvent.setBandID(objEventDetails.getBandID());
						objEvent.setBandName(objEventDetails.getBandName());*/
						
						eventList.add(objEvent);
						objCategoryInfo.setEventList(eventList);
					}

				} else {

					objCategoryInfo = new CategoryInfo();

					if ("type".equalsIgnoreCase(groupBy)) {
						objCategoryInfo.setGroupContent(objEventDetails.getEventCatName());
						objCategoryInfo.setCategoryId(objEventDetails.getEventCatId());
					} else if ("atoz".equalsIgnoreCase(groupBy)) {
						objCategoryInfo.setGroupContent(Character.toString(objEventDetails.getEventName().charAt(0)).toUpperCase());
						objCategoryInfo.setCategoryId(-2);
					} else {
						objCategoryInfo.setGroupContent(objEventDetails.getStartDate());
						objCategoryInfo.setCategoryId(-1);
					}

					eventList = new ArrayList<EventDetails>();

					final EventDetails objEvent = new EventDetails();

					objEvent.setEventId(objEventDetails.getEventId());
					objEvent.setEventName(objEventDetails.getEventName());
					objEvent.setEventListId(objEventDetails.getEventListId());
					objEvent.setImgPath(objEventDetails.getImgPath());
					objEvent.setStartDate(objEventDetails.getStartDate());
					objEvent.setEndDate(objEventDetails.getEndDate());
					objEvent.setStartTime(objEventDetails.getStartTime());
					objEvent.setEndTime(objEventDetails.getEndTime());
					objEvent.setDistance(objEventDetails.getDistance());
					objEvent.setEventCatId(objEventDetails.getEventCatId());
					objEvent.setEventCatName(objEventDetails.getEventCatName());
					objEvent.setIsOnGoing(objEventDetails.getIsOnGoing());
					objEvent.setCityName(objEventDetails.getCityName());
					/*objEvent.setBandID(objEventDetails.getBandID());
					objEvent.setBandName(objEventDetails.getBandName());*/
					eventList.add(objEvent);
					objCategoryInfo.setEventList(eventList);
				}

				eventCategoryMap.put(key, objCategoryInfo);
			}
		}
		final Set<Map.Entry<String, CategoryInfo>> set = eventCategoryMap.entrySet();

		final ArrayList<CategoryInfo> catInfoList = new ArrayList<CategoryInfo>();
		
		final ArrayList<CategoryInfo> catInfoListSO = new ArrayList<CategoryInfo>();
		
		// Adding Signature List Events.
		if (signEventList != null && !signEventList.isEmpty()) {
			objCatSigEvents = new CategoryInfo();
			objCatSigEvents.setEventList(signEventList);
			objCatSigEvents.setGroupContent(HubCitiConstants.SIG_EVENTS);
			objCatSigEvents.setCategoryId(Integer.parseInt(strSignCatID));
			/*catInfoList.add(objCatSigEvents);*/
			catInfoListSO.add(objCatSigEvents);
		}

		//		Adding OnGoing and Non-Ongoing Event to one single list.
		if (onGoingEventList != null && !onGoingEventList.isEmpty()) {
			objCatOnGoingEvents = new CategoryInfo();
			objCatOnGoingEvents.setEventList(onGoingEventList);
			objCatOnGoingEvents.setGroupContent(HubCitiConstants.ONGOINGEVENTS);
			objCatOnGoingEvents.setCategoryId(-3);
			/*catInfoList.add(objCatOnGoingEvents);*/
			catInfoListSO.add(objCatOnGoingEvents);
		}


		for (Map.Entry<String, CategoryInfo> entry : set) {
			catInfoList.add(entry.getValue());
		}

		/*
		 	Conditions to sort event list. 
		 	1. First if condition - 
		 		For grouped by type or atoz or city (i.e by category or alphabetical or city's), 
		 		Events sorted by event name or start date or distance. 
		 	2. Second else if condition - 
		 		For grouped by N/A  (single group), Events sorted by distance. 
		 	3. Third else condition -
		        For grouped by start date, EventSorted by name or date.
		 */

		if ("type".equalsIgnoreCase(groupBy) || "atoz".equalsIgnoreCase(groupBy)) {

			SortAlertEventByCategory sortByCategory = new SortAlertEventByCategory();

			Collections.sort(catInfoList, sortByCategory);
			if ("Desc".equalsIgnoreCase(sortOrder)) {
				Collections.reverse(catInfoList);
			}
			for (CategoryInfo objCatInfo : catInfoList) {

				/* To sort by event name, distance, city or else by event start date. */
				if ("name".equalsIgnoreCase(sortBy)) {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortReverseEventByName objReverseEventByName = new SortReverseEventByName();
						Collections.sort(objCatInfo.getEventList(), objReverseEventByName);
					} else {
						SortEventByName sortEventByName = new SortEventByName();
						Collections.sort(objCatInfo.getEventList(), sortEventByName);
					}

				} else if ("distance".equalsIgnoreCase(sortBy)) {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortEventsByDisance sortEventsByDisance = new SortEventsByDisance();
						Collections.sort(objCatInfo.getEventList(), sortEventsByDisance);
					} else {
						SortReverseEventsByDistance objReverseEventsByDist = new SortReverseEventsByDistance();
						Collections.sort(objCatInfo.getEventList(), objReverseEventsByDist);
					}

				} else if ("city".equalsIgnoreCase(sortBy)) {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortReverseEventByCity sortReverseEventByCity = new SortReverseEventByCity();
						Collections.sort(objCatInfo.getEventList(), sortReverseEventByCity);
					} else {
						SortEventByCity objSortEventByCity = new SortEventByCity();
						Collections.sort(objCatInfo.getEventList(), objSortEventByCity);
					}

				} else {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortReverseEventByDate objReverseEventByDate = new SortReverseEventByDate();
						Collections.sort(objCatInfo.getEventList(), objReverseEventByDate);
					} else {
						SortEventByDate sortEventByDate = new SortEventByDate();
						Collections.sort(objCatInfo.getEventList(), sortEventByDate);
					}

				}

				/* Looping to change date format. */
				for (EventDetails objEvent : objCatInfo.getEventList()) {
					objEvent.setStartDate(Utility.changeDateFormat1(objEvent.getStartDate()));
					objEvent.setStartTime(Utility.convertDBtimeAmPm(objEvent.getStartTime()));
				}
			}
		} else {

			SortAlertEventByDate sortbyDate = new SortAlertEventByDate();
			Collections.sort(catInfoList, sortbyDate);

			if ("Desc".equalsIgnoreCase(sortOrder)) {
				Collections.reverse(catInfoList);
			}

			for (CategoryInfo objCatInfo : catInfoList) {

				if (objCatInfo.getGroupContent() != null && !objCatInfo.getGroupContent().equalsIgnoreCase(HubCitiConstants.ONGOINGEVENTS)  && !objCatInfo.getGroupContent().equalsIgnoreCase(HubCitiConstants.SIG_EVENTS)) {
					objCatInfo.setGroupContent(Utility.changeDateFormat1(objCatInfo.getGroupContent()));
				}

				/* To sort by event name, distance, city or else by event start date. */
				if ("name".equalsIgnoreCase(sortBy)) {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortReverseEventByName objReverseEventByName = new SortReverseEventByName();
						Collections.sort(objCatInfo.getEventList(), objReverseEventByName);
					} else {
						SortEventByName sortEventByName = new SortEventByName();
						Collections.sort(objCatInfo.getEventList(), sortEventByName);
					}

				} else if ("distance".equalsIgnoreCase(sortBy)) {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortEventsByDisance sortEventsByDisance = new SortEventsByDisance();
						Collections.sort(objCatInfo.getEventList(), sortEventsByDisance);
					} else {
						SortReverseEventsByDistance objReverseEventsByDist = new SortReverseEventsByDistance();
						Collections.sort(objCatInfo.getEventList(), objReverseEventsByDist);
					}

				} else if ("city".equalsIgnoreCase(sortBy)) {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortReverseEventByCity sortReverseEventByCity = new SortReverseEventByCity();
						Collections.sort(objCatInfo.getEventList(), sortReverseEventByCity);
					} else {
						SortEventByCity objSortEventByCity = new SortEventByCity();
						Collections.sort(objCatInfo.getEventList(), objSortEventByCity);
					}

				} else {

					if ("Desc".equalsIgnoreCase(sortOrder)) {
						SortReverseEventByDate objReverseEventByDate = new SortReverseEventByDate();
						Collections.sort(objCatInfo.getEventList(), objReverseEventByDate);
					} else {
						SortEventByDate sortEventByDate = new SortEventByDate();
						Collections.sort(objCatInfo.getEventList(), sortEventByDate);
					}
				}

				/* Looping to change date format. */
				for (EventDetails objEvent : objCatInfo.getEventList()) {
					objEvent.setStartDate(Utility.changeDateFormat1(objEvent.getStartDate()));
					objEvent.setStartTime(Utility.convertDBtimeAmPm(objEvent.getStartTime()));
				}
			}
		}
		
		for (CategoryInfo objCatInfoRef : catInfoListSO) { 
			
			for (EventDetails objEvent : objCatInfoRef.getEventList()) {
				objEvent.setStartDate(Utility.changeDateFormat1(objEvent.getStartDate()));
				objEvent.setStartTime(Utility.convertDBtimeAmPm(objEvent.getStartTime()));
			}
		}
		catInfoListSO.addAll(catInfoList);
		
		eventDetailsObj.setCategoryList(catInfoListSO);

		LOG.info("Exit AlertEventHelper : sortEventList");
		return eventDetailsObj;
	}

	/**
	 * Method to Group Fundraiser. 
	 * 
	 * Grouping by - date (default), Alphabetical (atoz), category, department.
	 * @param  list of fundraiser.
	 * @return Fundraiser object
	 */
	public static Fundraiser sortFundraiserList(Fundraiser fundraiserList) {
		LOG.info("Inside AlertEventHelper : sortFundraiserList");

		final HashMap<String, CategoryInfo> fundCategoryMap = new HashMap<String, CategoryInfo>();

		CategoryInfo objCategoryInfo = null;
		Fundraiser objFundraiser = new Fundraiser();
		String strKey = null;

		final String strSortOrder = fundraiserList.getSortOrder();
		final String strGroupBy = fundraiserList.getGroupBy();
		final String strSortBy = fundraiserList.getSortBy();


		ArrayList<Fundraiser> arFundraiserList = null;


		//Grouping By - Date (default), Alphabetical (atoz), Category(type).
		for (Fundraiser fundraiser : fundraiserList.getFundraiserList()) {

			if ("type".equalsIgnoreCase(strGroupBy)) {
				strKey = fundraiser.getFundCatName();
			} /*else if ("department".equalsIgnoreCase(strGroupBy)) {
				if (!"".equals(Utility.checkNull(fundraiser.getDepartment()))) {
					strKey = fundraiser.getDepartment();
				} else {
					strKey = HubCitiConstants.NOTAPPLICABLE;
				}
			}*/ else if ("atoz".equalsIgnoreCase(strGroupBy)) {
				strKey = Character.toString(fundraiser.getFundName().charAt(0)).toUpperCase();
			} else {
				strKey = fundraiser.getStartDate();
			}

			if (!"".equals(Utility.checkNull(strKey))) {

				if (fundCategoryMap.containsKey(strKey)) {

					objCategoryInfo = fundCategoryMap.get(strKey);
					arFundraiserList = (ArrayList<Fundraiser>) objCategoryInfo.getFundraiserList();

					if (arFundraiserList != null && !arFundraiserList.isEmpty()) {

						final Fundraiser fundrsr = new Fundraiser();

						fundrsr.setFundraiserId(fundraiser.getFundId());
						fundrsr.setFundName(fundraiser.getFundName());
						fundrsr.setsDescription(fundraiser.getsDescription());
						fundrsr.setlDescription(fundraiser.getlDescription());
						fundrsr.setImgPath(fundraiser.getImgPath());
						fundrsr.setStartDate(fundraiser.getStartDate());
						fundrsr.setFundCatId(fundraiser.getFundCatId());
						fundrsr.setFundCatName(fundraiser.getFundCatName());
						fundrsr.setFundListId(fundraiser.getFundListId());

						arFundraiserList.add(fundrsr);

						objCategoryInfo.setFundraiserList(arFundraiserList);
					}

				} else {

					objCategoryInfo = new CategoryInfo();

					if ("type".equalsIgnoreCase(strGroupBy)) {
						objCategoryInfo.setGroupContent(fundraiser.getFundCatName());
						objCategoryInfo.setCategoryId(fundraiser.getFundCatId());
					} else if ("atoz".equalsIgnoreCase(strGroupBy)) {
						objCategoryInfo.setGroupContent(Character.toString(fundraiser.getFundName().charAt(0)).toUpperCase());
						objCategoryInfo.setCategoryId(-2);
					} else {
						objCategoryInfo.setGroupContent(fundraiser.getStartDate());
						objCategoryInfo.setCategoryId(-1);
					}

					arFundraiserList = new ArrayList<Fundraiser>();

					final Fundraiser fundrser = new Fundraiser();

					fundrser.setFundraiserId(fundraiser.getFundId());
					fundrser.setFundName(fundraiser.getFundName());
					fundrser.setsDescription(fundraiser.getsDescription());
					fundrser.setlDescription(fundraiser.getlDescription());
					fundrser.setImgPath(fundraiser.getImgPath());
					fundrser.setStartDate(fundraiser.getStartDate());
					fundrser.setFundCatId(fundraiser.getFundCatId());
					fundrser.setFundCatName(fundraiser.getFundCatName());
					fundrser.setFundListId(fundraiser.getFundListId());

					arFundraiserList.add(fundrser);

					objCategoryInfo.setFundraiserList(arFundraiserList);
				}

				fundCategoryMap.put(strKey, objCategoryInfo);
			} 
		}


		final Set<Map.Entry<String, CategoryInfo>> setList = fundCategoryMap.entrySet();
		final ArrayList<CategoryInfo> arCategoryList = new ArrayList<CategoryInfo>();

		for (Map.Entry<String, CategoryInfo> entry : setList) {
			arCategoryList.add(entry.getValue());
		}


		/*
		   Conditions Fundraiser list. 
		   Grouping By - date (default), Alphabetical (atoz), category,.
		   Sort BY  - date (default), Name
		   SortOrder  - Asc (default), Desc
		 */

		if ("type".equalsIgnoreCase(strGroupBy) || "atoz".equalsIgnoreCase(strGroupBy)) {

			SortFundraiserByCategory sortByCategory = new SortFundraiserByCategory();
			Collections.sort(arCategoryList, sortByCategory);

			if ("Desc".equalsIgnoreCase(strSortOrder)) {
				Collections.reverse(arCategoryList);
			}


			for (CategoryInfo objCatInfo : arCategoryList) {
				/* To sort by Fundraiser name,  or else by Fundraiser start date. */
				if ("name".equalsIgnoreCase(strSortBy)) {
					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						SortReverseFundByName objReverseFundByName = new SortReverseFundByName();
						Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByName);
					} else {
						SortFundraiserByName sortFundByName = new SortFundraiserByName();
						Collections.sort(objCatInfo.getFundraiserList(), sortFundByName);
					}
				} else {
					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						SortReverseFundByDate objReverseFundByDate = new SortReverseFundByDate();
						Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByDate);
					} else {
						SortFundraiserByDate sortFundByDate = new SortFundraiserByDate();
						Collections.sort(objCatInfo.getFundraiserList(), sortFundByDate);
					}
				}
				/* Looping to change date format. */
				for (Fundraiser fundraiser : objCatInfo.getFundraiserList()) {
					fundraiser.setStartDate(Utility.changeDateFormat1(fundraiser.getStartDate()));
				}
			}

		} else {

			SortFundCategoryByDate sortFundCatbyDate = new SortFundCategoryByDate();

			Collections.sort(arCategoryList, sortFundCatbyDate);
			if ("Desc".equalsIgnoreCase(strSortOrder)) {
				Collections.reverse(arCategoryList);
			}

			for (CategoryInfo objCatInfo : arCategoryList) { 

				if (null != objCatInfo.getGroupContent()) {
					objCatInfo.setGroupContent(Utility.changeDateFormat1(objCatInfo.getGroupContent()));
				}


				/* To sort by Fundraiser name,  or else by Fundraiser start date. */
				if ("name".equalsIgnoreCase(strSortBy)) {
					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						SortReverseFundByName objReverseFundByName = new SortReverseFundByName();
						Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByName);
					} else {
						SortFundraiserByName sortFundByName = new SortFundraiserByName();
						Collections.sort(objCatInfo.getFundraiserList(), sortFundByName);
					}
				} else {
					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						SortReverseFundByDate objReverseFundByDate = new SortReverseFundByDate();
						Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByDate);
					} else {
						SortFundraiserByDate sortFundByDate = new SortFundraiserByDate();
						Collections.sort(objCatInfo.getFundraiserList(), sortFundByDate);
					}
				}

				/* Looping to change date format. */
				for (Fundraiser fundraiser : objCatInfo.getFundraiserList()) {
					fundraiser.setStartDate(Utility.changeDateFormat1(fundraiser.getStartDate()));
				}

			}
		}

		LOG.info("Exit AlertEventHelper : sortFundraiserList");

		objFundraiser.setCategoryList(arCategoryList);
		return objFundraiser;
	}
}
