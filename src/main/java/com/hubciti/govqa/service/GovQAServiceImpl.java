package com.hubciti.govqa.service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.rpc.ServiceException;

import net.webqa.CreateCustomer;
import net.webqa.GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult;
import net.webqa.GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult;
import org.apache.commons.lang.ArrayUtils;
import net.webqa.WebQACategory;
import net.webqa.WebQACustomer;
import net.webqa.WebQACustomer2;
import net.webqa.WebQAServiceRequest2;
import net.webqa.WebQAServiceRequestType;
import net.webqa.__wslib;
import net.webqa.__wslibLocator;
import net.webqa.__wslibSoap;

import org.apache.axis.message.MessageElement;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.SortBySummary;
import com.hubciti.common.helper.SortGovQACompletedByDate;
import com.hubciti.common.helper.SortTableByReferNum;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.CustomerInfo;
import com.hubciti.common.pojos.Faq;
import com.hubciti.common.pojos.GovQA;
import com.hubciti.common.pojos.Response;
import com.hubciti.common.pojos.ServiceRequest;
import com.hubciti.common.pojos.Table;
import com.hubciti.common.pojos.TableGroup;
import com.hubciti.common.utility.PropertiesReader;
import com.hubciti.common.utility.Utility;
import com.net.webqa.WebQACustomField;
import com.net.webqa.WebQAFilterItem;
import com.net.webqa.WebQAWS;
import com.net.webqa.WebQAWSLocator;
import com.net.webqa.WebQAWSSoap;

/**
 * @author kumar_dodda
 *
 */
public class GovQAServiceImpl implements GovQAService {

	Logger LOG = Logger.getLogger(GovQAServiceImpl.class);


	/**
	 * Variable strFieldAlarm declared as constant string.
	 */
	private final String strAlarm_Field = "alarm holder";

	/**
	 * Variable strAlarmHold_Field declared as constant string.
	 */
	private final String strAlarmFinal_Field = "alarm holder's contact information";

	/**
	 * Variable selected_Filter_Empty declared as constant string.
	 */
	private final String selected_Filter_Empty = "Currently there are no requests for the selected filter.";


	/**
	 * 
	 * This service layer for customer Authentication.
	 * 
	 * @param string
	 * @return sessionId or Message
	 * @throws HubCitiException
	 */
	public String authenticateuser(String string) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : authenticateuser");
		String response = null;
		Response responseRef = new Response();

		try {

			final __wslib service = new __wslibLocator();
			final __wslibSoap soap;

			soap = service.get__wslibSoap();
			final XstreamParserHelper streamHelper = new XstreamParserHelper();

			CreateCustomer customer = (CreateCustomer) streamHelper.parseJsonToObject(string);

			if (null == customer.getAuthKey() || customer.getAuthKey().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.AUTHKEY_REQUIRED));

			} else if (null == customer.getCustomerEmail() || customer.getCustomerEmail().isEmpty() || null == customer.getPassword()
					|| customer.getPassword().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.EMAIL_PASSWORD_REQUIRED));

			} else {

				response = soap.authenticatePortalUserV2(customer.getAuthKey(), customer.getCustomerEmail(), customer.getPassword());

				if (!response.contains("-")) {
					responseRef.setResponseCode(HubCitiConstants.FAILURECODE);
					responseRef.setResponseText(response);

				} else {

					responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
					responseRef.setSessionId(response);
				}
			}

			response = XstreamParserHelper.produceJsonFromObject(responseRef);

		} catch (ServiceException e) {
			LOG.error("GOVQA inside authenticateuser error occured ");
			throw new HubCitiException(e);

		} catch (RemoteException e) {
			LOG.error("GOVQA inside authenticateuser error occured ");
			throw new HubCitiException(e);
		}

		return response;
	}

	/**
	 * This service layer for creating new user account
	 * 
	 * @param json
	 * @return
	 * @throws HubCitiException
	 */
	public String createAccount(String string) throws HubCitiException {

		LOG.info("Inside GovQAServiceImpl : createAccount ");

		String response = null;

		Response responseRef = new Response();

		try {

			final __wslib service = new __wslibLocator();
			final __wslibSoap soap;

			soap = service.get__wslibSoap();

			final XstreamParserHelper streamHelper = new XstreamParserHelper();

			CreateCustomer customer = (CreateCustomer) streamHelper.parseJsonToObject(string);
			if (null == customer.getAuthKey() || customer.getAuthKey().isEmpty()) {
				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.AUTHKEY_REQUIRED));

			} else if (null == customer.getCustomerEmail() || customer.getCustomerEmail().isEmpty()) {
				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.EMAILID_REQUIRED));

			} else if (null == customer.getPassword() || customer.getPassword().isEmpty()) {
				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.PASSWORD_REQUIRED));

			} else if (null == customer.getCustomerFirstName() || customer.getCustomerFirstName().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.FIRSTNAME_REQUIRED));

			} else if (null == customer.getCustomerLastName() || customer.getCustomerLastName().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.LASTNAME_REQUIRED));

			} else if (null == customer.getPhone() || customer.getPhone().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.PHONENUMBER_REQUIRED));

			} else {

				response = soap.createCustomer(customer.getAuthKey(), customer.getCustomerEmail(), customer.getCustomerFirstName(),
						customer.getCustomerLastName(), customer.getPhone(), customer.getAddress1(), customer.getAddress2(), customer.getCity(),
						customer.getCity(), customer.getZip(), "", customer.getPassword(), true, "");

				if (null != response && response.equals("SUCCESS")) {

					responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseRef.setResponseText(response);

				} else {

					responseRef.setResponseCode(HubCitiConstants.FAILURECODE);
					responseRef.setResponseText(response);

				}
			}

			response = XstreamParserHelper.produceJsonFromObject(responseRef);

		} catch (ServiceException e) {
			LOG.error("GOVQA inside createAccount error occured ");
			throw new HubCitiException(e);

		} catch (RemoteException e) {
			LOG.error("GOVQA inside createAccount error occured ");
			throw new HubCitiException(e);
		}

		return response;
	}

	/**
	 * This ServiceImpl method to get sessionId after user login to GOVQA.
	 * 
	 * @param strJSON
	 *            contains information of login information.
	 * @return response JSON as String containing sessionId.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String authenticatePortalUser(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : authenticatePortalUser ");

		String strResponse = null;
		String strSessionID = null;

		try {

			final Gson gson = new Gson();
			final ServiceRequest serviceReq = gson.fromJson(strJSON, ServiceRequest.class);
			final __wslib wsLocator = new __wslibLocator();

			if ("".equals(Utility.checkNull(serviceReq.getEmail())) || "".equals(Utility.checkNull(serviceReq.getPassword()))) {
				strResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.EMAIL_PASSWORD_EMPTY);
			} else {

				final __wslibSoap wsSoap = wsLocator.get__wslibSoap();
				strSessionID = wsSoap.authenticatePortalUser(PropertiesReader.getPropertyValue(HubCitiConstants.AUTH_KEY), serviceReq.getEmail(),
						serviceReq.getPassword());

				if (!strSessionID.contains("Invalid")) {
					strResponse = Utility.validationResponseJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT, "SessionID",
							strSessionID);
				} else {
					strResponse = Utility.validRespJSON(HubCitiConstants.FAILURECODE, strSessionID);
				}
			}

		} catch (RemoteException re) {
			LOG.error("Inside  GovQAServiceImpl : authenticatePortalUser :  " + re);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (ServiceException se) {
			LOG.error("Inside  GovQAServiceImpl : authenticatePortalUser :  " + se);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAServiceImpl : authenticatePortalUser ");
		return strResponse;
	}



	/**
	 * This ServiceImpl method to create service request note.
	 * 
	 * @param strJSON
	 *            contains information of service request.
	 * @return response JSON as String containing success.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String addServiceRequestNote(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : addServiceRequestNote ");

		final Gson gson = new Gson();
		String strResponse = null;

		final String strAuthKey = PropertiesReader.getPropertyValue("auth_key");

		try {

			final ServiceRequest serviceReq = gson.fromJson(strJSON, ServiceRequest.class);
			final __wslib wsLocator = new __wslibLocator();

			if (null == serviceReq.getReqTypeId()) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.SERVICE_REQUEST_TYPE_ID_EMPTY);
			} else if ("".equals(Utility.checkNull(serviceReq.getNote()))) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.SERVICE_REQ_NOTE);
			} else {

				final __wslibSoap wsSoap = wsLocator.get__wslibSoap();

				strResponse = wsSoap.addServiceRequestNote(strAuthKey, serviceReq.getReqTypeId(), serviceReq.getNote());

				if (!"".equals(Utility.checkNull(strResponse)) && "OK".equals(Utility.checkNull(strResponse))) {
					strResponse = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
				} else {
					strResponse = Utility.validRespJSON(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
				}

			}

		} catch (RemoteException re) {
			LOG.error("Inside  GovQAServiceImpl : addServiceRequestNote :  " + re);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);

		} catch (ServiceException se) {
			LOG.error("Inside  GovQAServiceImpl : addServiceRequestNote :  " + se);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAServiceImpl : addServiceRequestNote ");
		return strResponse;
	}





	/**
	 * This ControllerImpl method to create new service request .
	 * 
	 * @param strJSON
	 *            contains information of service request.
	 * @return response JSON as String containing created service request
	 *         reference no.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String createServiceRequest_Old(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : createServiceRequest ");

		String strResponse = null;
		final String strAuthKey = PropertiesReader.getPropertyValue("auth_key");
		Calendar calendar = Calendar.getInstance();
		final String strCurrentDate = Utility.ISODateTimeFormat();

		try {

			final __wslib wsLocator = new __wslibLocator();

			final Gson gson = new Gson();
			final WebQACustomer objWebQACust = gson.fromJson(strJSON, WebQACustomer.class);

			if (0 == objWebQACust.getSrTypeId()) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.SERVICE_REQUEST_TYPE_ID_EMPTY);
			} else if ("".equals(Utility.checkNull(objWebQACust.getEmail()))) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOEMAILID);
			} else if (0 == objWebQACust.getCustomerId()) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.CUSTOMER_ID);
			} else if ("".equals(Utility.checkNull(objWebQACust.getlAddress()))) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.ADDRESS_EMPTY);
			} else {

				final __wslibSoap wsSoap = wsLocator.get__wslibSoap();

				strResponse = wsSoap.createServiceRequestExtended(strAuthKey, objWebQACust.getSrTypeId(), objWebQACust.getCustomerId(),
						objWebQACust.getEmail(), objWebQACust.getCustomFieldData(), objWebQACust.getFirst(), objWebQACust.getLast(),
						objWebQACust.getPhone(), calendar, objWebQACust.getAddressOne(), objWebQACust.getAddressTwo(), objWebQACust.getCity(),
						objWebQACust.getState(), objWebQACust.getZip(), strCurrentDate, objWebQACust.getId(), objWebQACust.getlAddress());

				if (!"".equals(Utility.checkNull(strResponse))) {
					strResponse = Utility.validationResponseJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT, "reference_no",
							strResponse);
				} else {
					strResponse = Utility.validRespJSON(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
				}
			}

		} catch (RemoteException re) {
			strResponse = Utility.validationResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error("Inside GovQAServiceImpl : createServiceRequest :  " + re);
		} catch (ServiceException se) {
			strResponse = Utility.validationResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error("Inside GovQAServiceImpl : createServiceRequest :  " + se);
		}

		LOG.info("Exit GovQAServiceImpl : createServiceRequest ");
		return strResponse;
	}


	/**
	 * This ServiceImpl method to get list of categories.
	 * 
	 * @return response JSON as String containing list of service request
	 *         categories.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getServiceRequestCategories() throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : getServiceRequestCategories ");

		WebQACategory[] arWebQACategory = null;
		String strResponse = null;

		final List<WebQACategory> arWebQACategoryList = new ArrayList<WebQACategory>();
		final Gson gson = new Gson();
		final String strAuthKey = PropertiesReader.getPropertyValue("auth_key");
		final Table objTable = new Table();

		try {

			final __wslib wsLocator = new __wslibLocator();
			final __wslibSoap wsSoap = wsLocator.get__wslibSoap();
			arWebQACategory = wsSoap.getServiceRequestCategories(strAuthKey);

			if (null != arWebQACategory) {

				for (int j = 0; j < arWebQACategory.length; j++) {
					arWebQACategoryList.add(arWebQACategory[j]);
				}

				objTable.setArWebQACategory(arWebQACategoryList);
				objTable.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objTable.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strResponse = gson.toJson(objTable);

			} else {
				objTable.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
				objTable.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		} catch (ServiceException se) {
			LOG.error("Inside GovQAServiceImpl : getServiceRequestCategories : " + se);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (RemoteException re) {
			LOG.error("Inside GovQAServiceImpl : getServiceRequestCategories : " + re);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAServiceImpl : getServiceRequestCategories ");
		return strResponse;
	}

	/**
	 * This service layer for getching service request type custom fields
	 * 
	 * @param inputData
	 * @return list of custom fields in JSOn format
	 * @throws HubCitiException
	 *             if any error occurres
	 */
	
	
	public static void main(String[] args) throws HubCitiException {
		
		getServiceRequestCustomFields1();
	}
	
	
	public static String getServiceRequestCustomFields1() throws HubCitiException {
//		LOG.info("Inside GovQAServiceImpl : getServiceRequestCustomFields ");

		String response = null;
		Response responseRef = new Response();
		int iIndex = 0;

		try {

			/*final XstreamParserHelper streamHelper = new XstreamParserHelper();

			GovQA request = (GovQA) streamHelper.parseJsonToObject(inputJSON);
			if ((null == request.getAuthKey() || request.getAuthKey().equalsIgnoreCase("")) || null == request.getTypeNo()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {*/

				final WebQAWS service = new WebQAWSLocator();
				final WebQAWSSoap soap = service.getWebQAWSSoap();
				WebQACustomField[] fields = soap.getRequestTypeCustomFields("ifF34jauK;", 55);

				/*if (null != fields && fields.length > 0) {

				responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
				responseRef.setCustomFieldList(fields);
			} else {

				responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
			}*/

				responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
				int iRequestNo = 55;
				if (null != fields && fields.length > 0) {
					if (iRequestNo == 55) {
						for (int i = 0; i < fields.length; i++) {

							if (fields[i].getFldNo() == 79){
								iIndex = i;
							}
						}
						fields = (WebQACustomField[]) ArrayUtils.remove(fields, iIndex);
					}

					responseRef.setCustomFieldList(fields);
				} 

			response = XstreamParserHelper.produceJsonFromObject(responseRef);

			System.out.println("response : " + response);
		} catch (ServiceException exception) {

//			LOG.error("GOVQA inside GovQAServiceImpl : getServiceRequestCustomFields  error occured " + exception);
			throw new HubCitiException(exception.fillInStackTrace());
		} catch (RemoteException exception) {

//			LOG.error("GOVQA inside GovQAServiceImpl : getServiceRequestCustomFields  error occured ");
			throw new HubCitiException(exception.fillInStackTrace());
		} catch (Exception exception) {

//			LOG.error("GOVQA inside GovQAServiceImpl : getServiceRequestCustomFields  error occured " + exception);
			throw new HubCitiException(exception.fillInStackTrace());
		}
		return response;
	}
	
	
	public String getServiceRequestCustomFields(String inputJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : getServiceRequestCustomFields ");

		String response = null;
		Response responseRef = new Response();
		int iIndex = 0;

		try {

			final XstreamParserHelper streamHelper = new XstreamParserHelper();

			GovQA request = (GovQA) streamHelper.parseJsonToObject(inputJSON);
			if ((null == request.getAuthKey() || request.getAuthKey().equalsIgnoreCase("")) || null == request.getTypeNo()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {

				final WebQAWS service = new WebQAWSLocator();
				final WebQAWSSoap soap = service.getWebQAWSSoap();
				WebQACustomField[] fields = soap.getRequestTypeCustomFields(request.getAuthKey(), request.getTypeNo());

				/*if (null != fields && fields.length > 0) {

				responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
				responseRef.setCustomFieldList(fields);
			} else {

				responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
			}*/

				responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
				responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);

				if (null != fields && fields.length > 0) {
					if (request.getTypeNo() == 55) {
						for (int i = 0; i < fields.length; i++) {

							if (fields[i].getFldNo() == 79){
								iIndex = i;
							}
						}
						fields = (WebQACustomField[]) ArrayUtils.remove(fields, iIndex);
					}

					responseRef.setCustomFieldList(fields);
				} 
			}

			response = XstreamParserHelper.produceJsonFromObject(responseRef);

		} catch (ServiceException exception) {

			LOG.error("GOVQA inside GovQAServiceImpl : getServiceRequestCustomFields  error occured " + exception);
			throw new HubCitiException(exception.fillInStackTrace());
		} catch (RemoteException exception) {

			LOG.error("GOVQA inside GovQAServiceImpl : getServiceRequestCustomFields  error occured ");
			throw new HubCitiException(exception.fillInStackTrace());
		} catch (Exception exception) {

			LOG.error("GOVQA inside GovQAServiceImpl : getServiceRequestCustomFields  error occured " + exception);
			throw new HubCitiException(exception.fillInStackTrace());
		}
		return response;
	}

	public String customerInformation(String request) throws HubCitiException {

		LOG.info("Inside GovQAServiceImpl : customerInformation");
		String response = null;
		Response responseRef = new Response();

		CreateCustomer customerRequest;
		WebQACustomer customerResponse = null;

		try {

			final __wslib service = new __wslibLocator();
			final __wslibSoap soap = service.get__wslibSoap();
			final XstreamParserHelper streamHelper = new XstreamParserHelper();

			customerRequest = (CreateCustomer) streamHelper.parseJsonToObject(request);

			if (null == customerRequest.getAuthKey() || customerRequest.getAuthKey().isEmpty() || null == customerRequest.getCustomerEmail()
					|| customerRequest.getCustomerEmail().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {

				customerResponse = soap.getCustomerInfoObject(customerRequest.getAuthKey(), customerRequest.getCustomerEmail());

				if (null != customerResponse) {

					responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
					responseRef.setCustomer(customerResponse);

				} else {

					responseRef.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					responseRef.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					responseRef.setSessionId(response);

				}
			}

			response = XstreamParserHelper.produceJsonFromObject(responseRef);

		} catch (ServiceException e) {
			LOG.error("GOVQA inside customerInformation error occured ");
			throw new HubCitiException(e);

		} catch (RemoteException e) {
			LOG.error("GOVQA inside customerInformation error occured ");
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("GOVQA inside customerInformation error occured ");
			throw new HubCitiException(e);
		}
		return response;

	}

	/**
	 * 
	 * @param request
	 *            contains Customer Account modified information
	 * 
	 * @return response
	 * 
	 * @throws HubCitiException
	 */
	public String editAccountInfo(String request) throws HubCitiException {

		LOG.info("Inside GovQAServiceImpl : editAccountInfo");

		String response = null;

		Response responseRef = new Response();

		CustomerInfo customer;
		try {

			__wslib service = new __wslibLocator();

			__wslibSoap soap = service.get__wslibSoap();

			final XstreamParserHelper streamHelper = new XstreamParserHelper();

			customer = (CustomerInfo) streamHelper.parseJsonToObject(request);

			if (null == customer.getAuthKey() || customer.getAuthKey().isEmpty()) {
				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.AUTHKEY_REQUIRED));

			} else if (null == customer.getLoginEmail() || customer.getLoginEmail().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.EMAILID_REQUIRED));

			} else if (null == customer.getPassword() || customer.getPassword().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.PASSWORD_REQUIRED));

			} else if (null == customer.getFirstName() || customer.getFirstName().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.FIRSTNAME_REQUIRED));

			} else if (null == customer.getLastName() || customer.getLastName().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.LASTNAME_REQUIRED));

			} else if (null == customer.getPhone() || customer.getPhone().isEmpty()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.PHONENUMBER_REQUIRED));

			} else {

				if (null == customer.getGroupAccessName()) {
					customer.setGroupAccessName("");
				}
				response = soap.updateClientUser(customer.getAuthKey(), customer.getLoginEmail(), customer.getPassword(),
						customer.getGroupAccessName(), customer.getFirstName(), customer.getLastName(), customer.getPhone(),
						customer.getCustCFName1(), customer.getCustCFValue1(), customer.getCustCFName2(), customer.getCustCFValue2(),
						customer.getCustCFName3(), customer.getCustCFValue3(), customer.getCustCFName4(), customer.getCustCFValue4(),
						customer.getCustCFName5(), customer.getCustCFValue5());

				if (null != response && response.contains(PropertiesReader.getPropertyValue(HubCitiConstants.CUSTOMER_UPDATED))) {

					responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseRef.setResponseText(response);

				} else {

					responseRef.setResponseCode(HubCitiConstants.FAILURECODE);
					responseRef.setResponseText(response);

				}
			}

			response = XstreamParserHelper.produceJsonFromObject(responseRef);

		} catch (ServiceException e) {

			LOG.error("GOVQA inside editAccountInfo error occured ");
			throw new HubCitiException(e);

		} catch (RemoteException e) {

			LOG.error("GOVQA inside editAccountInfo error occured ");
			throw new HubCitiException(e);

		} catch (Exception e) {

			LOG.error("GOVQA inside editAccountInfo error occured ");
			throw new HubCitiException(e);

		}

		LOG.info("Exit GovQAServiceImpl : editAccountInfo");

		return response;
	}

	/**
	 * This Service Layer written for fetching Category Information
	 * 
	 * @param request
	 * @return category List
	 * @throws HubCitiException
	 */
	public String filterA(String request) throws HubCitiException {

		LOG.info("Inside GovQAServiceImpl : filterA ");
		final Gson gson = new Gson();

		String response = null;

		Response responseRef = new Response();

		try {
			// using google json parser
			Faq faq = gson.fromJson(request, Faq.class);

			if (null != faq.getAuthKey()) {

				final WebQAWS service = new WebQAWSLocator();

				final WebQAWSSoap soap = service.getWebQAWSSoap();

				WebQAFilterItem[] categories = soap.getFilterAItems(faq.getAuthKey());

				if (null != categories && categories.length != 0) {

					responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
					responseRef.setCategories(categories);

				} else {

					responseRef.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					responseRef.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

			} else {
				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.AUTHKEY_REQUIRED));
			}

			response = gson.toJson(responseRef);

		} catch (ServiceException e) {

			LOG.error("GOVQA inside filterA error occured ");
			throw new HubCitiException(e);

		} catch (RemoteException e) {

			LOG.error("GOVQA inside filterA error occured ");
			throw new HubCitiException(e);

		} catch (Exception e) {

			LOG.error("GOVQA inside filterA error occured ");
			throw new HubCitiException(e);

		}
		LOG.info("GOVQA Exit filterA error occured ");
		return response;
	}

	/**
	 * This Service Layer written for fetching Sub Category Information List
	 * 
	 * @param request
	 * @return category List
	 * @throws HubCitiException
	 * 
	 */
	public String filterB(String request) throws HubCitiException {

		LOG.info("Inside GovQAServiceImpl : filterB ");
		final Gson gson = new Gson();

		String response = null;

		Response responseRef = new Response();

		try {
			// using google json parser
			Faq faq = gson.fromJson(request, Faq.class);

			if (null == faq.getAuthKey() || null == faq.getParentID()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {

				final WebQAWS service = new WebQAWSLocator();

				final WebQAWSSoap soap = service.getWebQAWSSoap();

				if (null == faq.getParentID()) {

					faq.setParentID(-1);

				}
				WebQAFilterItem[] categories = soap.getFilterBItems(faq.getAuthKey(), faq.getParentID());

				if (null != categories) {

					responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
					responseRef.setCategories(categories);

				} else {

					responseRef.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					responseRef.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
			response = gson.toJson(responseRef);
		} catch (ServiceException e) {
			LOG.error("GOVQA inside filterB error occured ");
			throw new HubCitiException(e);

		} catch (RemoteException e) {

			LOG.error("GOVQA inside filterB error occured ");
			throw new HubCitiException(e);

		} catch (Exception e) {
			LOG.error("GOVQA inside filterB error occured ");
			throw new HubCitiException(e);
		}
		LOG.info("GOVQA Exit filterB");
		return response;
	}

	/**
	 * 
	 * @param request
	 *            contains faq filtering & searching is working fine
	 * @return Number of faqs
	 * @throws HubCitiException
	 * 
	 */

	public String faqs(String request) throws HubCitiException {

		LOG.info("Inside GovQAServiceImpl : faqs ");
		final Gson gson = new Gson();

		String response = null;

		Response responseRef = new Response();

		try {

			// using Google json parser
			Faq faq = gson.fromJson(request, Faq.class);

			if (null == faq.getAuthKey()) {

				responseRef.setResponseCode(HubCitiConstants.INSUFFICENTREQUESTCODE);
				responseRef.setResponseText(HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {

				if (null == faq.getKeywords()) {
					faq.setKeywords("");
				}

				if (null == faq.getFilterA()) {
					faq.setFilterA("");
				}
				if (null == faq.getFilterB()) {
					faq.setFilterB("");
				}
				if (null == faq.getFilterC()) {
					faq.setFilterC("");
				}
				if (null == faq.getTotalToReturn()) {
					faq.setTotalToReturn(0);
				}
				if (null == faq.getVisibilityID()) {
					faq.setVisibilityID(1);
				}

				WebQAWS service = new WebQAWSLocator();

				WebQAWSSoap soap = service.getWebQAWSSoap();

				com.net.webqa.WebQAIssue[] faqs = soap.getIssueSearchSimple(faq.getAuthKey(), faq.getKeywords(), faq.getFilterA(), faq.getFilterB(),
						faq.getFilterC(), faq.getVisibilityID(), faq.getTotalToReturn());

				if (null != faqs) {

					List<com.net.webqa.WebQAIssue> faqList = Arrays.asList(faqs);

					if (null != faq.getSortBy()
							&& faq.getSortBy().equalsIgnoreCase(PropertiesReader.getPropertyValue(HubCitiConstants.SORT_BY).trim())) {

						Collections.sort(faqList, new SortBySummary());

					}

					if (null != faqList && faqList.size() == faq.getTotalToReturn()) {

						responseRef.setIsNext(1);

					} else {

						responseRef.setIsNext(0);

					}
					for (com.net.webqa.WebQAIssue faq1 : faqList) {

						faq1.setQuestion(faq1.getQuestion());
						faq1.setSolution(faq1.getSolution());
					}
					responseRef.setResponseCode(HubCitiConstants.SUCCESSCODE);
					responseRef.setResponseText(HubCitiConstants.SUCCESSTEXT);
					responseRef.setFaqs(faqList);

				} else {

					responseRef.setResponseCode(HubCitiConstants.FAILURECODE);
					responseRef.setResponseText(PropertiesReader.getPropertyValue(HubCitiConstants.INVALID_AUTHKEY));

				}
			}
			response = gson.toJson(responseRef);
			response = response.replaceAll("[^\\x00-\\x7F]", "");

		} catch (ServiceException e) {
			LOG.error("GOVQA inside faqs error occured ");
			throw new HubCitiException(e);
		} catch (RemoteException e) {
			LOG.error("GOVQA inside faqs error occured ");
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("GOVQA inside faqs error occured ");
			throw new HubCitiException(e);
		}

		LOG.info("GOVQA Exit faqs ");
		return response;
	}






	/**
	 * This ServiceImpl method to update custom field value.
	 * 
	 * @param JSON
	 *            contains information of custom field request.
	 * @return response JSON as String containing success.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String updateRequestCustomField(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : updateRequestCustomField ");
		final Gson gson = new Gson();
		String strResponse = null;
		Boolean bFldUpdated = false;
		final String strAuthKey = PropertiesReader.getPropertyValue("auth_key");

		try {

			final Table objTable = gson.fromJson(strJSON, Table.class);
			final __wslib wsLocator = new __wslibLocator();

			if ("".equals(Utility.checkNull(objTable.getReferNum()))) {
				strResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.REFER_NUMBER_EMPTY);
			} else {

				if ( objTable.getFldName().startsWith(strAlarm_Field)) {
					objTable.setFldName(strAlarmFinal_Field);
				}

				final __wslibSoap wsSoap = wsLocator.get__wslibSoap();

				bFldUpdated = wsSoap.updateRequestCustomField(strAuthKey, objTable.getReferNum(), objTable.getFldName(), objTable.getFldValue());

				if (bFldUpdated) {
					strResponse = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
				} else {
					strResponse = Utility.validRespJSON(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
				}
			}
		} catch (RemoteException re) {
			LOG.error("Inside  GovQAServiceImpl : updateRequestCustomField :  " + re);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);

		} catch (ServiceException se) {
			LOG.error("Inside  GovQAServiceImpl : updateRequestCustomField :  " + se);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAServiceImpl : updateRequestCustomField");
		return strResponse;
	}

	/**
	 * This ServiceImpl method to get service requests with Reference Numbers
	 * 
	 * @param JSON
	 *            contains information of Reference Numbers input parameter.
	 * @return response JSON as String containing retrieve service request
	 *         detail.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getServiceRequestByReferenceNo(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : getServiceRequestByReferenceNo ");

		Table objTable = null;
		String strResponse = null;
		GetServiceRequestByReferenceNumberResponseGetServiceRequestByReferenceNumberResult objGetReqByReferNo = null;

		final int iStartCnt = Integer.parseInt(PropertiesReader.getPropertyValue("make_a_req_start").toString());
		final int iEndCnt = Integer.parseInt(PropertiesReader.getPropertyValue("make_a_req_end").toString());
		final int iGrtrCnt = Integer.parseInt(PropertiesReader.getPropertyValue("make_a_req_grtr").toString());
		final String strAuthKey = PropertiesReader.getPropertyValue("auth_key");

		int iCount = 0;

		try {

			final Gson gson = new Gson();
			final Table table = gson.fromJson(strJSON, Table.class);

			if ("".equals(Utility.checkNull(table.getReferNum()))) {
				strResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.REFER_NUMBER_EMPTY);

			} else {

				final __wslib wsLocator = new __wslibLocator();
				final __wslibSoap wsSoap = wsLocator.get__wslibSoap();

				objGetReqByReferNo = wsSoap.getServiceRequestByReferenceNumber(strAuthKey, table.getReferNum());

				if (null != objGetReqByReferNo) {
					MessageElement[] elements = objGetReqByReferNo.get_any();

					for (MessageElement element : elements) {
						++iCount;

						if (iCount == 2 && element.getAsString().length() > iGrtrCnt) {
							strResponse = element.getAsString();
							strResponse = strResponse.substring(iStartCnt, strResponse.length() - iEndCnt);

							objTable = Utility.DOMParseToObject(strResponse);

							if (null != objTable) {
								objTable.setResponseCode(HubCitiConstants.SUCCESSCODE);
								objTable.setResponseText(HubCitiConstants.SUCCESSTEXT);
								strResponse = gson.toJson(objTable);
							} else {
								strResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
							}

						} else if (iCount == 2) {
							strResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
						}
					}

				} else {
					strResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (RemoteException re) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestByReferenceNo :  " + re);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (ServiceException se) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestByReferenceNo :  " + se);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (Exception e) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestByReferenceNo :  " + e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAServiceImpl : getServiceRequestByReferenceNo ");

		return strResponse;
	}



	/**
	 * This ControllerImpl method to create new service request .
	 * 
	 * @param strJSON
	 *            contains information of service request.
	 * @return response JSON as String containing created service request
	 *         reference no.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String createServiceRequest(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : createServiceRequest ");

		String strResponse = null;
		final String strAuthKey = PropertiesReader.getPropertyValue("auth_key");
		final Calendar calendar = Calendar.getInstance();

		try {

			final __wslib wsLocator = new __wslibLocator();

			final Gson gson = new Gson();
			final WebQACustomer2 objWebQACust = new WebQACustomer2();
			WebQAServiceRequest2  objWebQAReq = gson.fromJson(strJSON, WebQAServiceRequest2.class);

			objWebQAReq.setCreateDate(calendar);
			objWebQAReq.setUpdateDate(calendar);
			objWebQAReq.setStatusId(4);
			objWebQACust.setEmail(objWebQAReq.getContactEmail());
			objWebQAReq.setRequestOrigin(11);

			if (0 == objWebQAReq.getId()) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.SERVICE_REQUEST_TYPE_ID_EMPTY);
			} else if ("".equals(Utility.checkNull(objWebQAReq.getContactEmail()))) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOEMAILID);
			} else if ("".equals(Utility.checkNull(objWebQAReq.getAddressOne()))) {
				strResponse = Utility.validationResponseJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.ADDRESS_EMPTY);
			} else {

				final __wslibSoap wsSoap = wsLocator.get__wslibSoap();

				strResponse = wsSoap.createServiceRequestFull(strAuthKey, objWebQACust, objWebQAReq);

				if (!"".equals(Utility.checkNull(strResponse))) {
					strResponse = Utility.validationResponseJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT, "reference_no",
							strResponse);
				} else {
					strResponse = Utility.validRespJSON(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
				}
			}

		} catch (RemoteException re) {
			strResponse = Utility.validationResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error("Inside GovQAServiceImpl : createServiceRequest :  " + re);
		} catch (ServiceException se) {
			strResponse = Utility.validationResponseJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error("Inside GovQAServiceImpl : createServiceRequest :  " + se);
		}

		LOG.info("Exit GovQAServiceImpl : createServiceRequest ");
		return strResponse;
	}



	/**
	 * This ServiceImpl method to get list of service request.
	 * 
	 * @param JSON
	 *            contains information of group and sort input parameter.
	 * @return response JSON as String containing list of service request.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

		@Cacheable(value="getServiceRequestTypesByCategory", key="#strJSON")
	public String getServiceRequestTypesByCategory(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : getServiceRequestTypesByCategory ");

		String strServiceRequestTypesByCategory = null;
		final String[] strCatIdskey = PropertiesReader.getPropertyValue("make_a_req_category_ids").split(",");

		Table objTable = new Table();
		TableGroup objTableGrp = new TableGroup();
		final Gson gson = new Gson();

		final String strAuthKey = PropertiesReader.getPropertyValue("auth_key");

		WebQAServiceRequestType[] arWebQAServiceReqType = null;

		List<WebQAServiceRequestType> arWebQAServiceReqTypeList = new ArrayList<WebQAServiceRequestType>();

		try {

			final Table objTableReq = gson.fromJson(strJSON, Table.class);
			final __wslib wsLocator = new __wslibLocator();
			final __wslibSoap wsSoap = wsLocator.get__wslibSoap();

			int[] ints = new int[strCatIdskey.length];
			int i;

			for (i = 0; i < strCatIdskey.length; i++) {

				ints[i] = Integer.parseInt(strCatIdskey[i]);

				arWebQAServiceReqType = wsSoap.getServiceRequestTypesByCategory(strAuthKey, ints[i], "");
				if (null != arWebQAServiceReqType && arWebQAServiceReqType.length > 0) {

					for (int j = 0; j < arWebQAServiceReqType.length; j++) {
						arWebQAServiceReqTypeList.add(arWebQAServiceReqType[j]);
					}
				}

				objTable.setArWebQAServiceReqTypeList(arWebQAServiceReqTypeList);
			}

			if (!"".equals(Utility.checkNull(objTableReq.getGroupBy())) && !"atoz".equalsIgnoreCase(objTableReq.getGroupBy())
					&& !"type".equalsIgnoreCase(objTableReq.getGroupBy())) {
				objTable.setGroupBy("atoz");
			} else {
				objTable.setGroupBy(objTableReq.getGroupBy());
			}

			if (!"".equals(Utility.checkNull(objTableReq.getSortBy())) && !"name".equalsIgnoreCase(objTableReq.getSortBy())) {
				objTable.setSortBy("name");
			} else {
				objTable.setSortBy(objTableReq.getSortBy());
			}

			if (null != objTable && null != objTable.getArWebQAServiceReqTypeList() && !objTable.getArWebQAServiceReqTypeList().isEmpty()) {
				objTableGrp = GovQAHelper.sortTableGroup(objTable);

				if (null != objTableGrp && null != objTableGrp.getTableGroup() && !objTableGrp.getTableGroup().isEmpty()) {
					objTableGrp.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objTableGrp.setResponseText(HubCitiConstants.SUCCESSTEXT);

				} else {
					objTableGrp.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					objTableGrp.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

			} else {
				objTableGrp.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
				objTableGrp.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			strServiceRequestTypesByCategory = gson.toJson(objTableGrp);

		} catch (RemoteException re) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestTypesByCategory :  " + re);
			strServiceRequestTypesByCategory = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (ServiceException se) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestTypesByCategory :  " + se);
			strServiceRequestTypesByCategory = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (Exception e) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestTypesByCategory :  " + e);
			strServiceRequestTypesByCategory = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAServiceImpl : getServiceRequestTypesByCategory ");
		return strServiceRequestTypesByCategory;
	}


	/**
	 * This ServiceImpl method to get list of service request created by
	 * customer.
	 * 
	 * @param JSON
	 *            contains information of customer EmailId input parameter.
	 * @return response JSON as String containing service request created by
	 *         customer.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
//	@Cacheable(value="getServiceRequestsByCustomer", key="#strJSON")
	public String getServiceRequestsByCustomer(String strJSON) throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : getServiceRequestsByCustomer ");

		String strResponse = null;
		GetServiceRequestsByCustomerResponseGetServiceRequestsByCustomerResult objGetReqByCustomer = null;
		Table objTable = new Table();
		String strCrteDate = null;
		String strClosedDate = null;

		final int iStartCnt = Integer.parseInt(PropertiesReader.getPropertyValue(HubCitiConstants.MAKE_A_REQ_START).toString());
		final int iEndCnt = Integer.parseInt(PropertiesReader.getPropertyValue(HubCitiConstants.MAKE_A_REQ_END).toString());
		final int iGrtrCnt = Integer.parseInt(PropertiesReader.getPropertyValue(HubCitiConstants.MAKE_A_REQ_GRTR).toString());
		final String strAuthKey = PropertiesReader.getPropertyValue(HubCitiConstants.AUTH_KEY);

		int iCount = 0;

		try {

			final Gson gson = new Gson();
			final ServiceRequest serviceReq = gson.fromJson(strJSON, ServiceRequest.class);

			if ("".equals(Utility.checkNull(serviceReq.getEmail()))) {
				strResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.NOEMAILID);
			} else {

				final __wslib wsLocator = new __wslibLocator();
				final __wslibSoap wsSoap = wsLocator.get__wslibSoap();

				objGetReqByCustomer = wsSoap.getServiceRequestsByCustomer(strAuthKey, serviceReq.getEmail(), serviceReq.getCreateDate());

				if (null != objGetReqByCustomer) {
					MessageElement[] elements = objGetReqByCustomer.get_any();

					for (MessageElement element : elements) {
						++iCount;

						if (iCount == 2 && element.getAsString().length() > iGrtrCnt) {
							strResponse = element.getAsString();
							strResponse = strResponse.substring(iStartCnt, strResponse.length() - iEndCnt);
							strResponse = strResponse.replaceAll("diffgr:id", "table_id").replaceAll("msdata:rowOrder", "rowOrder")
									.replaceAll("request_description", "description").replaceAll("status_description", "status")
									.replaceAll("contact_email", "email").replaceAll("create_date", "createDate")
									.replaceAll("sr_address_one", "address1").replaceAll("sr_address_two", "address2")
									.replaceAll("sr_city", "city").replaceAll("sr_state", "state")
									.replaceAll("sr_zip_code", "zipCode").replaceAll("reference_no", "referNum")
									.replaceAll("close_date", "closeDate")
									.replaceAll("xml:space=\"preserve\"", "");

							JSONObject xmlJSONObj = XML.toJSONObject(strResponse);
							strResponse = xmlJSONObj.toString();
							strResponse = strResponse.replace(HubCitiConstants.TABLE_STRUCTURE, HubCitiConstants.TABLE_STRUCTURE_REPLACE).replace(
									HubCitiConstants.TABLE_ENDTAG, HubCitiConstants.TABLE_ENDTAG_REPLACE);
							objTable = gson.fromJson(strResponse, Table.class);

							if (!"".equals(Utility.checkNull(serviceReq.getFilterName())) && !"NONE".equalsIgnoreCase(serviceReq.getFilterName())) {
								Iterator<Table> iterator = objTable.getTable().iterator();

								while (iterator.hasNext()) {
									Table table = iterator.next();
									if (!table.getStatus().equalsIgnoreCase(serviceReq.getFilterName())) {
										iterator.remove();
									}
								}
							}

							if (null != objTable.getTable() && !objTable.getTable().isEmpty()) {
								objTable.setResponseCode(HubCitiConstants.SUCCESSCODE);
								objTable.setResponseText(HubCitiConstants.SUCCESSTEXT);

								for (int i = 0; i < objTable.getTable().size(); i++) {
									strCrteDate = objTable.getTable().get(i).getCreateDate();
									strClosedDate = objTable.getTable().get(i).getCloseDate();

									if (!"".equals(Utility.checkNull(strCrteDate))) {
										objTable.getTable().get(i).setCreateDate(strCrteDate);
									}

									if (!"".equals(Utility.checkNull(strClosedDate))) {
										objTable.getTable().get(i).setCloseDate(strClosedDate);
									}
								}

								if ("Completed".equals(serviceReq.getFilterName())) {
									SortGovQACompletedByDate sortCompletedByDate = new SortGovQACompletedByDate();
									Collections.sort(objTable.getTable(), sortCompletedByDate);
								} else {
									SortTableByReferNum sortReqByRefer = new SortTableByReferNum();
									Collections.sort(objTable.getTable(), sortReqByRefer);
								}

							} else {
								objTable.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
								objTable.setResponseText(selected_Filter_Empty);
								objTable.setTable(null);
							}


						} else if (iCount == 2) {
							objTable.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
							objTable.setResponseText(HubCitiConstants.VIEWMYREQUEST_EMPTYRECORD);
						}
					}

				} else {
					objTable.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					objTable.setResponseText(HubCitiConstants.VIEWMYREQUEST_EMPTYRECORD);
				}

			}

			strResponse = gson.toJson(objTable);

		} catch (RemoteException re) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestsByCustomer :  " + re);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (ServiceException se) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestsByCustomer :  " + se);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} catch (Exception e) {
			LOG.error("Inside  GovQAServiceImpl : getServiceRequestsByCustomer :  " + e);
			strResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit GovQAServiceImpl : getServiceRequestsByCustomer ");
		return strResponse;

	}


	/**
	 * This ServiceImpl method to clear the cache for list of service request created by
	 * customer.
	 * 
	 * @param JSON
	 *            contains information of customer EmailId input parameter.
	 * @return response JSON as String containing service request created by
	 *         customer.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	@CacheEvict(value = "getServiceRequestsByCustomer", allEntries = true)
	public String rmServiceRequestsByCustomer() throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : rmServiceRequestsByCustomer ");

		final Gson gson = new Gson();
		Table objTable = new Table();

		objTable.setResponseCode(HubCitiConstants.SUCCESSCODE);
		objTable.setResponseText(HubCitiConstants.SUCCESSTEXT);

		return gson.toJson(objTable);
	}


	/**
	 * This ServiceImpl method to clear the cache for list of service request created by
	 * customer.
	 * 
	 * @param JSON
	 *            contains information of customer EmailId input parameter.
	 * @return response JSON as String containing service request created by
	 *         customer.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	@CacheEvict(value = "getServiceRequestTypesByCategory", allEntries = true)
	public String rmServiceRequestTypesByCategory() throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : rmServiceRequestTypesByCategory ");

		final Gson gson = new Gson();
		Table objTable = new Table();

		objTable.setResponseCode(HubCitiConstants.SUCCESSCODE);
		objTable.setResponseText(HubCitiConstants.SUCCESSTEXT);

		return gson.toJson(objTable);
	}
	
	
/*	@Caching(evict = {
			@CacheEvict(value="referenceData", allEntries=true),
			@CacheEvict(value="podcasts", allEntries=true),
			@CacheEvict(value="searchResults", allEntries=true),
			@CacheEvict(value="newestAndRecommendedPodcasts", allEntries=true),
			@CacheEvict(value="randomAndTopRatedPodcasts", allEntries=true)		    
		})*/
	
//	@CacheEvict(value = {"getServiceRequestTypesByCategory", "getServiceRequestsByCustomer"}, allEntries = true)
	
	/*@CacheEvict(value = "getServiceRequestTypesByCategory", allEntries = true)
	public String rmServiceRequestTypesByCategory() throws HubCitiException {
		LOG.info("Inside GovQAServiceImpl : rmServiceRequestTypesByCategory ");

		final Gson gson = new Gson();
		Table objTable = new Table();

		objTable.setResponseCode(HubCitiConstants.SUCCESSCODE);
		objTable.setResponseText(HubCitiConstants.SUCCESSTEXT);

		return gson.toJson(objTable);
	}*/

}
