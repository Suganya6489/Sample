package com.hubciti.scannow.service;

import com.hubciti.common.exception.HubCitiException;

/**
 * This interface for ScanNow methods, which are implemented by ScanNow
 * implementation class.
 * 
 * @author Kumar D
 */
public interface ScanNowService {
	/**
	 * This Service method for fetching product information based on bar code or
	 * scan code.
	 * 
	 * @param xml
	 *            as request.
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String scanBarCodeProduct(String xml) throws HubCitiException;

	/**
	 * This Service method for displaying smart search product list information
	 * based on search key and user Id.
	 * 
	 * @param xml
	 *            as request.
	 * @return xml based on product List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getSmartSearchProdlist(String xml) throws HubCitiException;

	/**
	 * The service method For fetching Smart Search product count information
	 * based on search key and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key
	 * @return xml based on product Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getSmartSearchCount(String xml) throws HubCitiException;

	/**
	 * This Service method for fetching MainMenuID
	 * 
	 * @return XML containing MainMenuID
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String userTrackingGetMainMenuID(String xml) throws HubCitiException;

	/**
	 * This Service method for fetching product information based on search key
	 * and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key.
	 * @return xml based on product Details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String smartSearchProducts(String xml) throws HubCitiException;

	/**
	 * The service method For fetching scan history information based on user
	 * Id.
	 * 
	 * @param userId
	 *            request user
	 * @param lastVisitedRecord
	 *            for to fetch next set of records.
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	String getScanHistory(Integer userId, Integer lastVisitedRecord, Integer hubCitiId) throws HubCitiException;

	/**
	 * The service method For deleting scan history.
	 * 
	 * @param xml
	 *            the Input XML.
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	String deleteScanHistoryItem(String xml) throws HubCitiException;

	/**
	 * The service method For fetching product information based on search key
	 * and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	String seacrhAddForProdName(String xml) throws HubCitiException;
}
