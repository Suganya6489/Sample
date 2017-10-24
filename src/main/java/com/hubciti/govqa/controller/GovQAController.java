package com.hubciti.govqa.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import com.hubciti.common.constants.HubCitiURLPath;

/**
 * @author kumar_dodda
 *
 */
@Path(HubCitiURLPath.GOVQA)
public interface GovQAController {

	
	/**
	 * 
	 * This controller for Authernticating GOVQA Customer
	 * @param json
	 * @return SessionId else valid message
	 * 
	 */
	@POST
	@Path(HubCitiURLPath.AUTHENTICATEUSER)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String authenticateuser(String json);
	
	/**
	 * This controller for creating new customer information
	 * @param json contains Customer information
	 * @return message
	 */
	@POST
	@Path(HubCitiURLPath.CREATEACCOUNT)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String createAccount(String json);
	
	
	@Path(HubCitiURLPath.REQUESTCUSTOMFIELDS)
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String getServiceRequestCustomFields(String inputJson);
	
	/**
	 * This Controller method to get sessionId after user login to govqa.
	 * 
	 * @param JSON contains information of login information.
	 * @return response JSON as String containing sessionId.
	 */
	@POST
	@Path(HubCitiURLPath.AUTH_PORT_USER)	
	@Produces(MediaType.APPLICATION_JSON)
	String authenticatePortalUser(String strJSON);	
	
	
	/**
	 * This Controller method to get list of service request.
	 * 
	 * @param JSON contains information of group and sort input parameter.
	 * @return response JSON as String containing list of service request.
	 */
	@POST
	@Path(HubCitiURLPath.MAKE_REQUEST)	
	@Produces(MediaType.APPLICATION_JSON)
	String getServiceRequestTypesByCategory(String strJSON);
	
	
	/**
	 * This Controller method to create service request note.
	 * 
	 * @param JSON contains information of service request.
	 * @return response JSON as String containing success.
	 */
	@POST
	@Path(HubCitiURLPath.ADD_SERVICE_REQ_NOTE)	
	@Produces(MediaType.APPLICATION_JSON)
	String addServiceRequestNote(String strJSON);	
	
	/**
	 * This Controller method to create new service request .
	 * 
	 * @param JSON contains information of service request.
	 * @return response JSON as String containing service request reference no.
	 */
	@Path(HubCitiURLPath.CREATESERVICEREQ)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	String createServiceRequest(String json);
	
	/**
	 * This Controller method to get list of service request categories.
	 * 
	 * @return response JSON as String containing list of service request categories.
	 */
	@GET
	@Path(HubCitiURLPath.GETSERVICEREQ_CATEGORIES)	
	@Produces(MediaType.APPLICATION_JSON)
	String getServiceRequestCategories();
	
	/**
	 * 
	 * @param request contains authkey value & customer email Id
	 * @return the customer Information
	 * 
	 */
	@POST
	@Path(HubCitiURLPath.CUSTOMER_INFORMATION)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String customerInformation(String request);
	
	/**
	 * This Controller method to update customer information.
	 * 
	 * @param request contains Customer Account modified information.
	 * @return response 
	 */
	@POST
	@Path(HubCitiURLPath.EDITACCOUNTINFO)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String editAccountInfo(String request);
	
	/**
	 * This controller method get Find Information Category List.
	 * @param request contains authKey.
	 * @return returns category List.
	 */
	@POST
	@Path(HubCitiURLPath.FILTER_A)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String filterA(String request);
	
	
	/**
	 * This controller method get Find Information  Sub Category List.
	 * @param request contains authKey. 
	 * @return returns category List.
	 */
	@POST
	@Path(HubCitiURLPath.FILTER_B)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String filterB(String request);
	
	/**
	 * This controller method get FAQs based on  searchKey word & Filters.
	 * @param request contains authKey,filterA Name, FilterB name, FilterC Name & searchKey. 
	 * @return returns category List.
	 */
	@POST
	@Path(HubCitiURLPath.FAQS)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	String faqs(String request);
	
	/**
	 * This Controller method to get list of service request created by customer.
	 * 
	 * @param JSON
	 *            contains information of customer EmailId input parameter.
	 * @return response JSON as String containing service request created by customer.
	 */
	@POST
	@Path(HubCitiURLPath.ALL_REQUEST_BY_CUSTMR)	
	@Produces(MediaType.APPLICATION_JSON)
	String getServiceRequestsByCustomer(String strJSON);
	

	/**
	 * This Controller method to update custom field value.
	 * 
	 * @param JSON contains information of custom field request.
	 * @return response JSON as String containing success.
	 */
	@POST
	@Path(HubCitiURLPath.UPDATE_REQ_CUSTOMFIELD)	
	@Produces(MediaType.APPLICATION_JSON)
	String updateRequestCustomField(String strJSON);
	
	
	/**
	 * This Controller method to get service requests with Reference Numbers. 
	 * 
	 * @param JSON
	 *            contains information of Reference Numbers  input parameter.
	 * @return response JSON as String containing retrieve service request detail.
	 */
	@POST
	@Path(HubCitiURLPath.GET_REQBY_REFERN0)	
	@Produces(MediaType.APPLICATION_JSON)
	String getServiceRequestByReferenceNo(String strJSON);
	
	/**
	 * This is a RestEasy WebService Method is remove cache from ServiceRequestsByCustomer information.
	 * Method Type:GET.
	 * 
	 * @param userId
	 *            , as request parameter.
	 * @return XML containing started images information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_REQ_BY_CUSTOMER)
	@Produces("text/xml;charset=UTF-8")
	public String rmServiceRequestsByCustomer();
	
	/**
	 * This is a RestEasy WebService Method is remove cache from ServiceRequestTypesByCategory information.
	 * Method Type:GET.
	 * 
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_REQ_BY_CATEGORY)
	@Produces("text/xml;charset=UTF-8")
	public String rmServiceRequestTypesByCategory();
	
}
