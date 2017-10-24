package com.hubciti.govqa.service;

import com.hubciti.common.exception.HubCitiException;


/**
 * @author kumar_dodda
 *
 */
public interface GovQAService {

	/**
	 * 
	 * This service layer for customer Authentication.
	 * 
	 * @param string
	 * @return response object contains responsecode , responseText & sessionId
	 * @throws HubCitiException
	 */
	String authenticateuser(String string) throws HubCitiException;

	/**
	 * This service layer for creating new customer account
	 * 
	 * @param json
	 * @return
	 * @throws HubCitiException
	 */
	String createAccount(String json) throws HubCitiException;

	/**
	 * This Service method to get sessionId after user login to govqa.
	 * 
	 * @param JSON
	 *            contains information of login information.
	 * @return response JSON as String containing sessionId.
	 */
	String authenticatePortalUser(String strJSON) throws HubCitiException;

	/**
	 * This Service method to get list of service request.
	 * 
	 * @param JSON
	 *            contains information of group and sort input parameter.
	 * @return response JSON as String containing list of service request.
	 */
	String getServiceRequestTypesByCategory(String strJSON) throws HubCitiException;

	/**
	 * This Service method to create service request note.
	 * 
	 * @param JSON
	 *            contains information of service request.
	 * @return response JSON as String containing success.
	 */
	String addServiceRequestNote(String strJSON) throws HubCitiException;

	/**
	 * This ControllerImpl method to create new service request .
	 * 
	 * @param JSON
	 *            contains information of service request.
	 * @return response JSON as String containing service request reference no.
	 */
	String createServiceRequest(String strJSON) throws HubCitiException;

	/**
	 * This Service method to get list of categories.
	 * 
	 * @return response JSON as String containing list of service request
	 *         categories.
	 */
	String getServiceRequestCategories() throws HubCitiException;

	/**
	 * This service layer for getching service request type custom fields
	 * 
	 * @param inputData
	 * @return list of custom fields in JSOn format
	 * @throws HubCitiException
	 *             if any error occurres
	 */
	String getServiceRequestCustomFields(String inputJSON)
			throws HubCitiException;

	/**
	 * 
	 * This Service Layer written for fetching customer information
	 * 
	 * @param request
	 *            contains customer Mail Id
	 * @return
	 * @throws HubCitiException
	 */
	String customerInformation(String request) throws HubCitiException;

	/**
	 * 
	 * @param request
	 *            contains Customer Account modified information
	 * 
	 * @return response
	 * 
	 * @throws HubCitiException
	 */
	String editAccountInfo(String request) throws HubCitiException;

	
	/**
	 * This Service Layer written for fetching Category List
	 * @param request
	 * @return category List
	 * @throws HubCitiException
	 */
	String filterA(String request) throws HubCitiException;
	
	
	/**
	 * This Service Layer written for fetching  Sub Category  List
	 * 
	 * @param request
	 * @return category List
	 * @throws HubCitiException
	 * 
	 */
	String filterB(String request) throws HubCitiException;
	
	/**
	 * 
	 * @param request contains faq filtering & searching is working fine
	 * @return Number of faqs 
	 * @throws HubCitiException
	 * 
	 */
	String faqs(String request)throws HubCitiException;
	
	
	/**
	 * This Service method to get list of service request created by customer.
	 * 
	 * @param JSON
	 *            contains information of customer EmailId input parameter.
	 * @return response JSON as String containing service request created by customer.
	 */
	String getServiceRequestsByCustomer(String strJSON) throws HubCitiException;
	
	/**
	 * This Service method to update custom field value.
	 * 
	 * @param JSON contains information of custom field request.
	 * @return response JSON as String containing success.
	 */
	String updateRequestCustomField(String strJSON) throws HubCitiException;
	
	/**
	 * This Service method to get service requests with Reference Numbers 
	 * 
	 * @param JSON
	 *            contains information of Reference Numbers  input parameter.
	 * @return response JSON as String containing retrieve service request detail.
	 */
	String getServiceRequestByReferenceNo(String strJSON) throws HubCitiException;

	/**
	 * Service method to remove ServiceRequestsByCustomer information cached.
	 * 
	 * @param userId
	 *            as request parameter.
	 * @return XML containing user information in the response.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String rmServiceRequestsByCustomer() throws HubCitiException;
	
	/**
	 * Service method to remove ServiceRequestTypesByCategory information cached.
	 * 
	 * @param userId
	 *            as request parameter.
	 * @return XML containing user information in the response.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String rmServiceRequestTypesByCategory() throws HubCitiException;


}
