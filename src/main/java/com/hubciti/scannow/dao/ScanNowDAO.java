package com.hubciti.scannow.dao;

import java.util.List;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;

/**
 * This interface is used for ScanNow DAO module.
 * 
 * @author Kumar D
 */
public interface ScanNowDAO {

	/**
	 * This DAO method fetches the product information by passing user id,bar
	 * code,scan type.
	 * 
	 * @param scanDetail
	 *            instance of ProductDetail
	 * @return ProductDetail information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ProductDetail scanBarCodeProduct(ProductDetail scanDetail) throws HubCitiException;

	/**
	 * The DAO method For displaying smart search product list information based
	 * on search key and user Id.
	 * 
	 * @param productDetail
	 *            instance of ProductDetail
	 * @return ProductDetail information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ProductDetails getSmartSearchProdlist(ProductDetail productDetail) throws HubCitiException;

	/**
	 * The DAO method For fetching Smart Search product count information based
	 * on search key and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key.
	 * @return ProductDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ProductDetails getSmartSearchCount(ProductDetail productDetail) throws HubCitiException;

	/**
	 * The dao method fetches product smart search ID form database.
	 * 
	 * @param xml
	 *            as request contains and ProdSearch and mainMenuID
	 * @return prodSmaSeaID
	 * @throws ScanSeeException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	Integer userTrackingProductSmartSearch(String prodSearch, Integer mainMenuID) throws HubCitiException;

	/**
	 * The DAO method For fetches the product information based on search key.
	 * 
	 * @param seacrhDetail
	 *            instance of ProductDetail.
	 * @return ProductDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	ProductDetails smartSearchProducts(ProductDetail seacrhDetail) throws HubCitiException;

	/**
	 * This DAO method returns the Scan history of the requested user.
	 * 
	 * @param userId
	 *            request user
	 * @param lastVisitedRecord
	 *            for to fetch next set of records.
	 * @return responseXml
	 * @throws ScanSeeException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	List<ProductDetail> getScanHistory(Integer userId, Integer lastVisitedRecord, Integer hubCitiId) throws HubCitiException;

	/**
	 * The method for deleting wish list item.
	 * 
	 * @param productDetail
	 *            request parameter
	 * @return success or failure depending upon the result of operation.
	 * @throws ScanSeeException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	String deleteScanHistoryItem(ProductDetail productDetail) throws HubCitiException;

	/**
	 * The DAO method fetches the product information based on search key.
	 * 
	 * @param seacrhDetail
	 *            Instance of ProductDetail.
	 * @return ProductDetails.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	ProductDetails seacrhAddForProductName(ProductDetail seacrhDetail) throws HubCitiException;
}
