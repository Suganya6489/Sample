package com.hubciti.alertevent.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.alertevent.service.AlertEventService;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;

/**
 * Class containing controlled method related to alert/events.
 * 
 * @author dhruvanath_mm
 */
public class AlertEventControllerImpl implements AlertEventController {

	/**
	 * Getting the logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AlertEventControllerImpl.class);

	/**
	 * The FirstUseControllerImpl Constructor.
	 */
	public AlertEventControllerImpl() {

	}

	/**
	 * Method to get alert list on tap on Alert button on menu.
	 * 
	 * @param xml
	 * @return String xml containing alert list.
	 */
	public String getAlertsList(String xml) {
		String methodName = "getAlertList()";
		LOG.info("Inside AlertEventControllerImpl : " + methodName);
		String responseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			responseXML = alertEventService.getAlertsList(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : " + methodName + ": exception : " + exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * Method to capture click on Alert List.
	 * 
	 * @param xml
	 * @return String xml containing alert details.
	 */
	public void userTrackingAlertClick(Integer alertListId) {
		String methodName = "getAlertDetails()";
		LOG.info("Inside AlertEventControllerImpl : " + methodName);
		String responseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			responseXML = alertEventService.userTrackingAlertClick(alertListId);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : " + methodName + ": exception : " + exception);
			LOG.error(responseXML);
		}
	}

	/**
	 * This ControllerImpl method for searching the retailer for the given zip
	 * code or location attributes(Latitude or longitude). Method Type:POST.
	 * 
	 * @param xml
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response XML as String containing Retailers list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getDiningRetInfoForLocation(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getDiningRetInfoForLocation");
		String responseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			responseXML = alertEventService.getDiningRetInfoForLocation(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getDiningRetInfoForLocation : exception : ");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This Controller method for getting dining categories Method Type: GET.
	 * 
	 * @param input
	 *            userId, hubCitiId.
	 * @return response XML as String containing category list.
	 */
	public String getDiningCategories(Integer userId, Integer hubCitiId) {
		String methodName = "getDiningCategories()";
		LOG.info("Inside AlertEventControllerImpl : " + methodName);
		String responseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			responseXML = alertEventService.getDiningCategories(userId, hubCitiId);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : " + methodName + ": exception : " + exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	
	/**
	 * This ControllerImpl method to event details information Method Type: GET
	 * 
	 * @param xml
	 *            contains information about the eventId.
	 * @return XML in the response containing event details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getEventDetails(Integer eventId, Integer eventsListId, Integer hubCitiId,String platform) {
		LOG.info("Inside AlertEventControllerImpl : getEventDetails");

		String strResponseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			strResponseXML = alertEventService.getEventDetails(eventId, eventsListId, hubCitiId,platform);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getEventDetails : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This ControllerImpl method for fetch the hotel for the given zip code or
	 * location attributes(Latitude or longitude). Method Type:POST.
	 * 
	 * @param xml
	 *            the input request contains search parameters like zipcode or
	 *            location attributes.
	 * @return response XML as String containing Retailers list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getEventHotelDisplay(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getEventHotelDisplay");
		String responseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			responseXML = alertEventService.getEventHotelDisplay(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getEventHotelDisplay : exception : ");
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This Controller method for getting Event categories Method Type: GET.
	 * 
	 * @param input
	 *            userId, hubCitiId.
	 * @return response XML as String containing category list.
	 */
	public String getEventCategories(String xml) {
		String methodName = "getEventCategories()";
		LOG.info("Inside AlertEventControllerImpl : " + xml);
		String responseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			responseXML = alertEventService.getEventCategories(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : " + methodName + ": exception : " + exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This ControllerImpl method to event hotel details information Method
	 * Type: POST
	 * 
	 * @param xml
	 *            contains information about the HubCitiID, HcEventID,
	 *            RetailLocationID and RetailerListID .
	 * @return XML in the response containing event hotel details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getEventHotelDetails(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getEventHotelDetails");
		String strResponseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			strResponseXML = alertEventService.getEventHotelDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getEventHotelDetails : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This Controller method to to get locations (App Sites) associated to
	 * events.
	 * Type: POST
	 * @param xml
	 *            .
	 * @return XML in the response containing location details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getEventAppSiteLocation(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getEventAppSiteLocation");
		String strResponseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			strResponseXML = alertEventService.getEventAppSiteLocation(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getEventAppSiteLocation : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}



	/**
	 * This ControllerImpl method to get fundraiser list on tap on bottom button on menu.
	 * 
	 * @param xml
	 *            contains information about the MenuItem.
	 * @return XML in the response containing fundraiser details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFundraiserList(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getFundraiserList");

		String strResponseXML = null;

		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {

			strResponseXML = alertEventService.getFundraiserList(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getFundraiserList : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit AlertEventControllerImpl : getFundraiserList");
		return strResponseXML;
	}


	/**
	 * This ControllerImpl method to get list of fundraiser department list.
	 * @param xml 
	 * @return XML in the response containing DepartMent details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getDepartmentList(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getDepartmentList ");

		String strResponseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {

			strResponseXML = alertEventService.getDepartmentList(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getDepartmentList : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit AlertEventControllerImpl : getDepartmentList ");
		return strResponseXML;
	}


	/**
	 * This Controller method to to get locations (App Sites) associated to fundraiser.
	 * 
	 * @param xml.
	 * @return XML in the response containing location details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFundAppSiteLocation(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getFundAppSiteLocation");

		String strResponseXML = null;
		final AlertEventService alertfundraiserervice = ServiceFactory.getAlertEventService();
		try {

			strResponseXML = alertfundraiserervice.getFundAppSiteLocation(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getFundAppSiteLocation : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit AlertEventControllerImpl : getFundAppSiteLocation");
		return strResponseXML;
	}



	/**
	 * This ControllerImpl method to fundraiser details information.
	 * 
	 * @param xml
	 *            contains information about the fundId, fundListId, hubCitiId.
	 * @return XML in the response containing fundraiser details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getFundraiserDetails(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getFundraiserDetails");

		String strResponseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {

			strResponseXML = alertEventService.getFundraiserDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getFundraiserDetails : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit AlertEventControllerImpl : getFundraiserDetails");
		return strResponseXML;
	}



	/**
	 * This ControllerImpl method to get event logistics information.
	 * Method :POST
	 * @param  contains information event details.
	 * @return XML in the response containing event logistics information.  
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String getEventLogistics(String xml) {
		LOG.info("Inside AlertEventControllerImpl : getEventLogistics");

		String strResponseXML = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {

			strResponseXML = alertEventService.getEventLogistics(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getEventLogistics : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit AlertEventControllerImpl : getEventLogistics");
		return strResponseXML;
	}

	/**
	 *  This Controller method to get fundraiser Location display organization hosting or HubCiti AppSite or Retailer AppSite
	 * ; 
	 * Method :POST
	 * @param  contains information fundraiser information
	 * @return XML in the response containing fundraiser information.  
	 */
	public String getFundraiserLocationList(String xml) {

		LOG.info("Inside AlertEventControllerImpl : getFundraiserLocationDisplay");

		String strResponseXML = null;

		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();

		try{

			strResponseXML = alertEventService.getFundraiserLocationList(xml);

		}catch(HubCitiException exception){

			LOG.error("Inside AlertEventControllerImpl : getFundraiserLocationDisplay : exception : " + exception);
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		return strResponseXML;
	}


	/**
	 * Controller method is remove EventList information from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	public String remEventListCache() {
		LOG.info("Inside FindControllerImpl : remEventListCache");
		String strResponseJson = null;
		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			strResponseJson = alertEventService.remEventListCache();
		} catch (HubCitiException exception) {
			LOG.error("Inside FindControllerImpl : remEventListCache : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}
	
  	/**
	 * This controllerImpl method for Event List or Band Event List.
	 * 
	 * @param strJSON as request
	 * @return JSON containing Event List or Band Event array list response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getEventList(String strJSON) {
		LOG.info("Inside AlertEventControllerImpl : getEventList ");
		String strJsonResponse = null;

		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			strJsonResponse = alertEventService.getEventList(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getEventList : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit AlertEventControllerImpl : getEventList ");
		return strJsonResponse;
	}
	
	
  	/**
	 * This controllerImpl method to get fundraiser list on tap on bottom button on menu.
	 * 
	 * @param strJSON as request
	 * @return JSON containing fundraiser list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFundraiserListJson(String strJSON) {
		LOG.info("Inside AlertEventControllerImpl : getFundraiserListJson");
		String strJsonResponse = null;

		final AlertEventService alertEventService = ServiceFactory.getAlertEventService();
		try {
			strJsonResponse = alertEventService.getFundraiserListJson(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside AlertEventControllerImpl : getFundraiserListJson : " + exception);
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit AlertEventControllerImpl : getFundraiserListJson ");
		return strJsonResponse;
	}

}
