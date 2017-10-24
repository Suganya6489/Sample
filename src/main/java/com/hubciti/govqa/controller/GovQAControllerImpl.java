package com.hubciti.govqa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.govqa.service.GovQAService;


/**
 * @author kumar_dodda
 *
 */
public class GovQAControllerImpl implements GovQAController {


	/**
	 * 
	 */
	Logger LOG = LoggerFactory.getLogger(GovQAControllerImpl.class);

	/**
	 * 
	 * This controller for Authernticating GOVQA Customer
	 * 
	 * @param json
	 * @return SessionId else valid message
	 * 
	 */
	public String authenticateuser(String json) {

		final String methodName = "GovQAControllerImpl inside authenticateuser";

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested JSON -->" + json);
		}
		String response = null;
		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.authenticateuser(json);

		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit authenticateuser");

		return response;
	}

	/**
	 * This controller for creating new customer information
	 * 
	 * @param json
	 *            contains Customer information
	 * @return message
	 */
	public String createAccount(String inputData) {

		final String methodName = "GovQAControllerImpl inside createAccount";

		if (LOG.isDebugEnabled()) {
			LOG.debug(" Requested JSON --> "  + inputData);
		}
		String response = null;
		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.createAccount(inputData);

		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			response = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit createAccount");

		return response;
	}

	/**
	 * This controller for getting the associated custom Fields.
	 * 
	 * @String response contains custom field objects
	 * 
	 */
	public String getServiceRequestCustomFields(String inputJson) {

		LOG.info("GovQAControllerImpl inside getServiceRequestCustomFields");

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested JSON :  " + inputJson);
		}
		String response = null;
		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.getServiceRequestCustomFields(inputJson);

		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + "GovQAControllerImpl inside getServiceRequestCustomFields  : " + " exception");

			response = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit getServiceRequestCustomFields");

		return response;

	}

	/**
	 * This ControllerImpl method to get sessionId after user login to govqa.
	 * 
	 * @param JSON
	 *            contains information of login information.
	 * @return response JSON as String containing sessionId.
	 */
	public String authenticatePortalUser(String strJSON) {
		LOG.info("Inside GovQAControllerImpl : authenticatePortalUser ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();
			strResponse = objWebQAService.authenticatePortalUser(strJSON);

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : authenticatePortalUser : " + e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAControllerImpl : authenticatePortalUser ");
		return strResponse;
	}

	/**
	 * This ControllerImpl method to get list of service request.
	 * 
	 * @param JSON
	 *            contains information of group and sort input parameter.
	 * @return response JSON as String containing list of service request.
	 */
	public String getServiceRequestTypesByCategory(String strJSON) {
		LOG.info("Inside GovQAControllerImpl : getServiceRequestTypesByCategory ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();
			strResponse = objWebQAService.getServiceRequestTypesByCategory(strJSON);

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : getServiceRequestTypesByCategory : " + e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAControllerImpl : getServiceRequestTypesByCategory ");
		return strResponse;

	}

	/**
	 * This ControllerImpl method to create service request note.
	 * 
	 * @param JSON
	 *            contains information of service request.
	 * @return response JSON as String containing success.
	 */
	public String addServiceRequestNote(String strJSON) {
		LOG.info("Inside GovQAControllerImpl : addServiceRequestNote ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();
			strResponse = objWebQAService.addServiceRequestNote(strJSON);

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : addServiceRequestNote : " + e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit GovQAControllerImpl : addServiceRequestNote ");
		return strResponse;
	}

	/**
	 * This ControllerImpl method to create new service request .
	 * 
	 * @param JSON
	 *            contains information of service request.
	 * @return response JSON as String containing service request reference no.
	 */
	public String createServiceRequest(String strJSON) {
		LOG.info("Inside GovQAControllerImpl : createServiceRequest ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();
			strResponse = objWebQAService.createServiceRequest(strJSON);

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : createServiceRequest : " + e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAControllerImpl : createServiceRequest ");
		return strResponse;
	}

	/**
	 * This ControllerImpl method to get list of service request categories.
	 * 
	 * @return response JSON as String containing list of service request
	 *         categories.
	 */
	public String getServiceRequestCategories() {
		LOG.info("Inside GovQAControllerImpl : getServiceRequestCategories ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();

			strResponse = objWebQAService.getServiceRequestCategories();

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : getServiceRequestCategories : " + e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAControllerImpl : getServiceRequestCategories ");
		return strResponse;

	}

	/**
	 * This cotrollerImpl method to invoke customer information b
	 * 
	 * @param contains
	 *            authkey & customerEmail
	 * @return customer information in JSON Format
	 * @exception if
	 *                any exception occurs we are sending techincal error
	 *                message
	 * 
	 */
	public String customerInformation(String request) {

		LOG.info("GovQAControllerImpl inside customerInformation ");

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested JSON -->" + request);
		}
		String response = null;

		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.customerInformation(request);

		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + " GovQAControllerImpl Exit customerInformation ", exception);
			response = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit customerInformation ");

		return response;
	}

	/**
	 * This cotrollerImpl method to edit Customer Account information
	 * 
	 * @param contains
	 *            modified Customer Account information
	 * 
	 * @return response
	 * @exception if
	 *                any exception occurs we are sending techincal error
	 *                message
	 * 
	 */
	public String editAccountInfo(String request) {

		LOG.info("GovQAControllerImpl inside editAccountInfo ");

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested JSON -->" + request);
		}
		String response = null;

		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.editAccountInfo(request);

		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + " GovQAControllerImpl Exit editAccountInfo ", exception);
			response = Utility.formResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit editAccountInfo ");

		return response;
	}

	/**
	 * This controller method get Find Information Category List
	 * 
	 * @param request
	 *            contains authKey
	 * @return returns category List
	 * 
	 */
	public String filterA(String request) {

		LOG.info("GovQAControllerImpl inside filterA ");

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested JSON -->" + request);
		}
		String response = null;

		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.filterA(request);

		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + " GovQAControllerImpl Exit filterA ", exception);
			response = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit filterA ");

		return response;
	}

	/**
	 * This controller method get Find Information Sub Category List
	 * 
	 * @param request
	 *            contains authKey
	 * @return returns category List
	 */
	public String filterB(String request) {

		LOG.info("GovQAControllerImpl inside filterB ");

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested JSON -->" + request);
		}
		String response = null;

		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.filterB(request);

		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + " GovQAControllerImpl Exit filterB ", exception);
			response = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit filterB ");

		return response;
	}

	/**
	 * This controller method get FAQs based on searchKey word & Filters
	 * 
	 * @param request
	 *            contains authKey,filterA Name, FilterB name, FilterC Name &
	 *            searchKey
	 * @return returns category List
	 */
	public String faqs(String request) {

		LOG.info("GovQAControllerImpl inside faqs ");

		if (LOG.isDebugEnabled()) {
			LOG.debug("Requested JSON -->" + request);
		}
		String response = null;

		final GovQAService govQAService = ServiceFactory.getGovQAService();

		try {

			response = govQAService.faqs(request);

		} catch (HubCitiException exception) {

			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + " GovQAControllerImpl Exit faqs ", exception);
			response = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);

		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("Response returned " + response);
		}

		LOG.info("GovQAControllerImpl Exit faqs ");

		return response;
	}
	
	

	/**
	 * This ControllerImpl method to get list of service request created by customer.
	 * 
	 * @param JSON
	 *            contains information of customer EmailId input parameter.
	 * @return response JSON as String containing service request created by customer.
	 */
	public String getServiceRequestsByCustomer(String strJSON) {
		LOG.info("Inside GovQAControllerImpl : GetServiceRequestsByCustomer ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();
			strResponse = objWebQAService.getServiceRequestsByCustomer(strJSON);

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : GetServiceRequestsByCustomer : "+ e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAControllerImpl : GetServiceRequestsByCustomer ");
		return strResponse;

	}
	
	
	/**
	 * This ControllerImpl method to update custom field value.
	 * 
	 * @param JSON contains information of custom field request.
	 * @return response JSON as String containing success.
	 */
	public String updateRequestCustomField(String strJSON) {
		LOG.info("Inside GovQAControllerImpl : updateRequestCustomField ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();
			strResponse = objWebQAService.updateRequestCustomField(strJSON);

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : updateRequestCustomField : "+ e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		
		LOG.info("Exit GovQAControllerImpl : updateRequestCustomField ");
		return strResponse;
	}
	
	
	/**
	 * This ControllerImpl method to get service requests with Reference Numbers 
	 * 
	 * @param JSON
	 *            contains information of Reference Numbers  input parameter.
	 * @return response JSON as String containing retrieve service request detail.
	 */
	public String getServiceRequestByReferenceNo(String strJSON) {
		LOG.info("Inside GovQAControllerImpl : getServiceRequestByReferenceNo ");

		String strResponse = null;
		try {

			final GovQAService objWebQAService = ServiceFactory.getGovQAService();
			strResponse = objWebQAService.getServiceRequestByReferenceNo(strJSON);

		} catch (HubCitiException e) {
			LOG.error("Inside GovQAControllerImpl : getServiceRequestByReferenceNo : "+ e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE,HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAControllerImpl : getServiceRequestByReferenceNo ");
		return strResponse;

	}
	
	/**
	 * ControllerImpl method is remove ServiceRequestsByCustomer information cached. 
	 * Method Type:GET.
	 * 
	 * @return XML containing success or failure information.
	 */
	public String rmServiceRequestsByCustomer() {
		LOG.info("Inside GovQAControllerImpl : rmServiceRequestsByCustomer");
		String strResponseXML = null;
		final GovQAService objWebQAService = ServiceFactory.getGovQAService();
		try {
			strResponseXML = objWebQAService.rmServiceRequestsByCustomer();
		} catch (HubCitiException exception) {
			LOG.error("Inside GovQAControllerImpl : rmServiceRequestsByCustomer : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
	
	/**
	 * ControllerImpl method is remove ServiceRequestsByCustomer information cached. 
	 * Method Type:GET.
	 * 
	 * @return XML containing success or failure information.
	 */
	public String rmServiceRequestTypesByCategory() {
		LOG.info("Inside GovQAControllerImpl : rmServiceRequestTypesByCategory");
		String strResponseXML = null;
		final GovQAService objWebQAService = ServiceFactory.getGovQAService();
		try {
			strResponseXML = objWebQAService.rmServiceRequestTypesByCategory();
		} catch (HubCitiException exception) {
			LOG.error("Inside GovQAControllerImpl : rmServiceRequestTypesByCategory : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}
}
