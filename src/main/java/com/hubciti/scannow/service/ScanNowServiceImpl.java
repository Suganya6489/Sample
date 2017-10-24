package com.hubciti.scannow.service;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.UserTrackingData;
import com.hubciti.common.pojos.WishListHistoryDetails;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.dao.FirstUseDao;
import com.hubciti.scannow.dao.ScanNowDAO;

/**
 * This class is implementation of ScanNowService..
 * 
 * @author Kumar D
 */

public class ScanNowServiceImpl implements ScanNowService {
	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ScanNowServiceImpl.class);

	/**
	 * Instance variable for scanNowDao instance.
	 */
	private ScanNowDAO scanNowDao;
	/**
	 * Variable type for FirstUse
	 */
	private FirstUseDao firstUseDao;

	/**
	 * sets the ScanNowDAO DAO.
	 * 
	 * @param scanNowDAO
	 *            The instance for ScanNowDAO
	 */

	public void setScanNowDao(ScanNowDAO scanNowDAO) {
		this.scanNowDao = scanNowDAO;
	}

	/**
	 * @param firstUseDao
	 *            the firstUseDao to set.
	 */
	public void setFirstUseDao(FirstUseDao firstUseDao) {
		this.firstUseDao = firstUseDao;
	}

	/**
	 * This ServiceImpl method for fetching product information by passing
	 * barcode.
	 * 
	 * @param xml
	 *            the request Xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String scanBarCodeProduct(String xml) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : scanBarCodeProduct");
		
		String response = null;
		ProductDetail productDetail = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetail scanDetail = (ProductDetail) parser.parseXmlToObject(xml);
		
		if (null == scanDetail.getBarCode() || null == scanDetail.getUserId() || null == scanDetail.getScanType()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			productDetail = scanNowDao.scanBarCodeProduct(scanDetail);
			if (null != productDetail) {
				response = XstreamParserHelper.produceXmlFromObject(productDetail);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.SCANPRODNOTFOUND);
			}
		}
		
		LOG.info("Exit ScanNowServiceImpl : scanBarCodeProduct");
		return response;
	}

	/**
	 * This ServiceImpl method For displaying smart search product list
	 * information based on search key and user Id.
	 * 
	 * @param xml
	 *            the request Xml.
	 * @return xml based on product List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getSmartSearchProdlist(String xml) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : getSmartSearchProdlst");
		
		String response = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetail seacrhDetail = (ProductDetail) parser.parseXmlToObject(xml);
		
		if (null == seacrhDetail.getParCatId() || "".equals(Utility.checkNull(seacrhDetail.getSearchkey()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final ProductDetails productDetails = scanNowDao.getSmartSearchProdlist(seacrhDetail);
			if (null != productDetails) {
				response = XstreamParserHelper.produceXmlFromObject(productDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		LOG.info("Exit ScanNowServiceImpl : getSmartSearchProdlst");
		return response;
	}

	/**
	 * The service method For fetching Smart Search product count information
	 * based on search key and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getSmartSearchCount(String xml) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : getSmartSearchCount");
		
		String response = null;
		Integer iProdSmartSeachID = null;
		String strProdSearch = null;
		Integer iMainMenuID = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetail objProdDetail = (ProductDetail) parser.parseXmlToObject(xml);
		
		if (null == objProdDetail.getParCatId() || "".equals(Utility.checkNull(objProdDetail.getSearchkey()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			// For user tracking
			strProdSearch = objProdDetail.getSearchkey();
			iMainMenuID = objProdDetail.getMainMenuId();
			iProdSmartSeachID = scanNowDao.userTrackingProductSmartSearch(strProdSearch, iMainMenuID);
			if (null != iProdSmartSeachID) {
				objProdDetail.setProdSmaSeaId(iProdSmartSeachID);
			}
			final ProductDetails productDetails = scanNowDao.getSmartSearchCount(objProdDetail);
			if (null != productDetails) {
				response = XstreamParserHelper.produceXmlFromObject(productDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NOPRODUCTFOUNDTEXT);
			}
		}
		
		LOG.info("Exit ScanNowServiceImpl : getSmartSearchCount");
		return response;
	}

	/**
	 * This ServiceImpl method for fetching scan type.
	 * 
	 * @param xml
	 *            the request Xml.
	 * @return XML containing MainMenuID
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String userTrackingGetMainMenuID(String xml) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : userTrackingGetMainMenuID");
		
		String response = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final MenuItem objMenuItem = (MenuItem) parser.parseXmlToObject(xml);
		
		if (null == objMenuItem.getUserId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else
			if (null == objMenuItem.getHubCitiId() || null == objMenuItem.getPlatform()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else
				if (objMenuItem.getBottomBtnId() == null && objMenuItem.getmItemId() == null) {
					response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTCODE);
				} else {
					Integer mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
					if (mainMenuId != null) {
						UserTrackingData objUserTracking = new UserTrackingData();
						objUserTracking.setMainMenuId(mainMenuId);
						objUserTracking.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objUserTracking.setResponseText(HubCitiConstants.SUCCESSTEXT);
						response = XstreamParserHelper.produceXmlFromObject(objUserTracking);
					} else {
						response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
					}
				}
		
		LOG.info("Exit ScanNowServiceImpl : userTrackingGetMainMenuID");
		return response;
	}

	/**
	 * This ServiceImpl method for fetching product information by passing
	 * search key.
	 * 
	 * @param xml
	 *            the request Xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String smartSearchProducts(String xml) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : smartSearchProducts");
		
		String response = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetail seacrhDetail = (ProductDetail) parser.parseXmlToObject(xml);
		
		if (null == seacrhDetail.getUserId() || "".equals(Utility.checkNull(seacrhDetail.getSearchkey()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final ProductDetails productDetails = scanNowDao.smartSearchProducts(seacrhDetail);
			if (null != productDetails) {
				response = XstreamParserHelper.produceXmlFromObject(productDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NOPRODUCTFOUNDTEXT);
			}
		}
		
		LOG.info("Exit ScanNowServiceImpl : smartSearchProducts");
		return response;
	}

	/**
	 * The service implementation method for fetching Scan History information
	 * by passing user id. Calls the XStreams helper class methods for parsing.
	 * 
	 * @param userId
	 *            request user
	 * @param lastVisitedRecord
	 *            for to fetch next set of records.
	 * @return response xml with success code or error code.
	 * @throws ScanSeeException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String getScanHistory(Integer userId, Integer lastVisitedRecord, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : getScanHistory");
		
		String response = null;
		List<ProductDetail> productDetailslst = null;
		WishListHistoryDetails scanNowHistoryDetailsObj = null;
		
		if (null == userId || hubCitiId == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			productDetailslst = scanNowDao.getScanHistory(userId, lastVisitedRecord, hubCitiId);
			if (null != productDetailslst && !productDetailslst.isEmpty()) {
				// final ScanNowHelper scanNowHelperObj = new ScanNowHelper();
				scanNowHistoryDetailsObj = ScanNowHelper.getScanNowHistory(productDetailslst);
				scanNowHistoryDetailsObj.setNextPage(productDetailslst.get(0).getNextPage());
				if (null != scanNowHistoryDetailsObj) {
					scanNowHistoryDetailsObj.setResponseCode(HubCitiConstants.SUCCESSCODE);
					scanNowHistoryDetailsObj.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(scanNowHistoryDetailsObj);
					response = response.replaceAll("<WishListResultSet>", "<ScanHistoryInfo>");
					response = response.replaceAll("</WishListResultSet>", "</ScanHistoryInfo>");
					response = response.replaceAll("<productDetail>", "<ProductDetails>");
					response = response.replaceAll("</productDetail>", "</ProductDetails>");
					response = response.replaceAll("<WishListHistoryDetails>", "<ScanHistory>");
					response = response.replaceAll("</WishListHistoryDetails>", "</ScanHistory>");
				}
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		
		LOG.info("Exit ScanNowServiceImpl : getScanHistory");
		return response;
	}

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
	public String deleteScanHistoryItem(String xml) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : deleteScanHistoryItem");
		
		String response;
		
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final ProductDetail productDetail = (ProductDetail) streamHelper.parseXmlToObject(xml);

		if (productDetail.getScanHistoryId() == null || productDetail.getUserId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = scanNowDao.deleteScanHistoryItem(productDetail);
			if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
				LOG.info("Wish List product deleted with the UserId:" + productDetail.getUserId() + "and UserProductId"
						+ productDetail.getUserProductId());
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, "DELETED");
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit ScanNowServiceImpl : deleteScanHistoryItem");
		return response;
	}

	/**
	 * The service implementation method for fetching product information by
	 * passing search key. Calls the XStreams helper class methods for parsing.
	 * 
	 * @param xml
	 *            the request Xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String seacrhAddForProdName(String xml) throws HubCitiException {
		LOG.info("Inside ScanNowServiceImpl : seacrhAddForProdName");
		
		String response = null;
		
		final XstreamParserHelper parser = new XstreamParserHelper();
		final ProductDetail seacrhDetail = (ProductDetail) parser.parseXmlToObject(xml);
		
		if (null == seacrhDetail.getUserId() || seacrhDetail.getSearchkey() == null || "".equals(seacrhDetail.getSearchkey())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final ProductDetails productDetails = scanNowDao.seacrhAddForProductName(seacrhDetail);
			if (null != productDetails) {
				productDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				productDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(productDetails);
				response = StringEscapeUtils.unescapeXml(response);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NOPRODUCTFOUNDTEXT);
			}
		}
		
		LOG.info("Exit ScanNowServiceImpl : seacrhAddForProdName");
		return response;
	}
}
