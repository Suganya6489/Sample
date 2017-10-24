package com.hubciti.scannow.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.scannow.service.ScanNowService;

/**
 * The ScanNowRestEasy has methods to accept ScanNowRestEasy requests.
 * 
 * @author Kumar D
 */
public class ScanNowControllerImpl implements ScanNowController {
	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ScanNowControllerImpl.class.getName());

	/**
	 * This ControllerImpl method for get product information.
	 * 
	 * @param xml
	 *            input request contains bar code,user id.
	 * @return returns response XML based on Success or Error.
	 */
	public String scanProductForBarCode(String xml) {
		LOG.info("Inside ScanNowControllerImpl : scanProductForBarCode");
		String responseXML = null;
		final ScanNowService scanNowService = ServiceFactory.getScanNowService();
		try {
			responseXML = scanNowService.scanBarCodeProduct(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ScanNowControllerImpl : scanProductForBarCode: exception : " + exception);
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * This ControllerImpl method for displaying smart search product list
	 * information based on search key and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getSmartSearchProdlist(String xml) {
		LOG.info("Inside ScanNowControllerImpl : getSmartSearchProdlst");
		String responseXml = null;
		final ScanNowService scanNowService = ServiceFactory.getScanNowService();
		try {
			responseXml = scanNowService.getSmartSearchProdlist(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ScanNowControllerImpl : getSmartSearchProdlst : exception : " + exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * This ControllerImpl method for fetching Smart Search product count
	 * information based on search key and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getSmartSearchCount(String xml) {
		LOG.info("Inside ScanNowControllerImpl : getSmartSearchCount");
		String responseXml = null;
		final ScanNowService scanNowService = ServiceFactory.getScanNowService();
		try {
			responseXml = scanNowService.getSmartSearchCount(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : getSmartSearchCount : exception : " + exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * This ControllerImpl method for fetching MainMenuID Method Type:GET
	 * 
	 * @param No
	 *            parameter
	 * @return XML containing mainMenuId.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String userTrackingGetMainMenuID(String xml) {
		LOG.info("Inside ScanNowControllerImpl : userTrackingGetMainMenuID");
		String responseXml = null;
		final ScanNowService scanNowService = ServiceFactory.getScanNowService();
		try {
			responseXml = scanNowService.userTrackingGetMainMenuID(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : userTrackingGetMainMenuID : exception : " + exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * This ControllerImpl method for get product information. Calls method in
	 * service layer. accepts a POST request, MIME type is text/xml.
	 * 
	 * @param xml
	 *            input request contains search key and user id.
	 * @return returns response XML based on Success or Error.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String smartSearchProduct(String xml) {
		LOG.info("Inside ScanNowControllerImpl : smartSearchProduct");
		String responseXml = null;
		final ScanNowService scanNowService = ServiceFactory.getScanNowService();
		try {
			responseXml = scanNowService.smartSearchProducts(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside ThisLocationControllerImpl : smartSearchProduct : exception : " + exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * This is a RestEasyImplementation WebService Method for fetching product
	 * information for the given user id. Method Type:GET
	 * 
	 * @param userId
	 *            as request parameter in which scan history to be fetched.
	 * @param lastVisitedRecord
	 *            as request parameter for pagination.
	 * @return XML containing scan history information in the response.
	 */
	public String getScanHistory(Integer userId, Integer lastVisitedRecord, Integer hubCitiId) {
		final String methodName = "getScanHistory";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		if (LOG.isDebugEnabled()) {
			LOG.debug("client Requested :" + "userID" + userId + "lastVisitedRecord" + lastVisitedRecord);
		}
		final ScanNowService scanNowService = ServiceFactory.getScanNowService();
		try {
			responseXml = scanNowService.getScanHistory(userId, lastVisitedRecord, hubCitiId);
		} catch (HubCitiException exception) {
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMCODE);
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returned Response:" + responseXml);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * This is a RestEasy WebService Method to delete scan history items.
	 * 
	 * @param xml
	 *            for which wish list Product to be delete.
	 * @return XML based on success or failure
	 */
	public String deleteScanHistoryItem(String xml) {
		final String methodName = "deleteScanHistoryItem";
		String responseXml = null;
		LOG.info(HubCitiConstants.METHODSTART + methodName);

		if (LOG.isDebugEnabled()) {
			LOG.debug("Recieved Request XML" + xml);
		}

		final ScanNowService scanNowService = ServiceFactory.getScanNowService();

		try {
			responseXml = scanNowService.deleteScanHistoryItem(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXml;
	}

	/**
	 * The RestEasyImpl method for get product information. Calls method in
	 * service layer. accepts a POST request, MIME type is text/xml.
	 * 
	 * @param xml
	 *            input request contains search key and user id.
	 * @return returns response XML based on Success or Error.
	 */
	public String searchAddProductForName(String xml) {
		final String methodName = "scanProductForName";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		if (LOG.isDebugEnabled()) {
			LOG.debug("Request from Client:" + xml);
		}
		final ScanNowService scanNowService = ServiceFactory.getScanNowService();
		try {
			responseXml = scanNowService.seacrhAddForProdName(xml);
		} catch (HubCitiException exception) {
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Returned Response to Client is :" + responseXml);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}
}
