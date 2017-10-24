package com.hubciti.scannow.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
/*import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;*/

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.UserTrackingData;
import com.hubciti.common.utility.Utility;

/**
 * This is implementation class for ScanNowDAO.
 * 
 * @author Kumar D
 */
@SuppressWarnings({"unchecked" })
public class ScanNowDAOImpl implements ScanNowDAO {
	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ScanNowDAOImpl.class);
	/**
	 * for Jdbc connection.
	 */
	private JdbcTemplate jdbcTemplate;
	/**
	 * To call stored procedure.
	 */
	/*@SuppressWarnings("unused")
	private SimpleJdbcTemplate simpleJdbcTemplate;*/

	/**
	 * To call stored procedure.
	 */

	private SimpleJdbcCall simpleJdbcCall;

	/**
	 * To get the data source from xml..
	 * 
	 * @param dataSource
	 *            used for DB access.
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * This DAOImpl method is used to fetch product information based on Bar
	 * code ,scan type.
	 * 
	 * @param scanDetail
	 *            instance of ProductDetail
	 * @return productDetails product information based on bar code.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ProductDetail scanBarCodeProduct(ProductDetail scanDetail) throws HubCitiException {
		LOG.info("Inside ScanNowDAOImpl : scanBarCodeProduct");
		List<ProductDetail> productDetailList;
		ProductDetail productDetails = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcScanProduct");
			simpleJdbcCall.returningResultSet("productslist", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserId", scanDetail.getUserId());
			scanQueryParams.addValue("ScanCode", scanDetail.getBarCode());
			scanQueryParams.addValue("DeviceID", scanDetail.getDeviceId());
			scanQueryParams.addValue("ScanLongitude", scanDetail.getScanLatitude());
			scanQueryParams.addValue("ScanLatitude", scanDetail.getScanLongitude());
			scanQueryParams.addValue("ScanType", scanDetail.getScanType());
			scanQueryParams.addValue("FieldAgentRequest", 0);
			scanQueryParams.addValue("Date", Utility.getFormattedDate());
			scanQueryParams.addValue("HcHubCitiID", scanDetail.getHubCitiId());
			// For user tracking
			scanQueryParams.addValue("MainMenuID", scanDetail.getMainMenuId());
			scanQueryParams.addValue("ScanTypesID", scanDetail.getScanTypeId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					productDetailList = (List<ProductDetail>) resultFromProcedure.get("productslist");
					if (null != productDetailList && !productDetailList.isEmpty()) {
						productDetails = productDetailList.get(0);
						productDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						productDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ScanNowDAOImpl : scanBarCodeProduct : Store Procedure error : usp_HcScanProduct ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : fecthUserProductRating : Store Procedure error : usp_HcScanProduct : " + e.getMessage());
			throw new HubCitiException(e);
		} catch (ParseException e) {
			LOG.error("Inside RateReviewDaoImpl : fecthUserProductRating : ParseException : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return productDetails;
	}

	/**
	 * This DAOImpl method is used to displaying smart search product list
	 * information based on search key and user Id.
	 * 
	 * @param productDetail
	 *            instance of ProductDetail
	 * @return productDetails list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ProductDetails getSmartSearchProdlist(ProductDetail productDetail) throws HubCitiException {
		LOG.info("Inside ScanNowDAOImpl : getSmartSearchProdlst");
		List<ProductDetail> productDetailList;
		ProductDetails productDetails = null;
		try {
			if (null == productDetail.getLastVistedProductNo()) {
				productDetail.setLastVistedProductNo(0);
			}
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcProductSmartSearchProductList");
			simpleJdbcCall.returningResultSet("smartsearchproductlist", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("ProdSearch", productDetail.getSearchkey());
			scanQueryParams.addValue("ParentCategoryID", productDetail.getParCatId());
			scanQueryParams.addValue("LowerLimit", productDetail.getLastVistedProductNo());
			scanQueryParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			scanQueryParams.addValue("HcHubCitiID", productDetail.getHubCitiId());
			scanQueryParams.addValue("UserID", productDetail.getUserId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			productDetailList = (List<ProductDetail>) resultFromProcedure.get("smartsearchproductlist");
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					if (null != productDetailList && !productDetailList.isEmpty()) {
						productDetails = new ProductDetails();
						productDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						productDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
						productDetails.setProductDetail(productDetailList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						if (nextpage != null) {
							if (nextpage) {
								productDetails.setNextPage(1);
							} else {
								productDetails.setNextPage(0);
							}
						}
						if (maxCnt != null) {
							productDetails.setMaxCount(maxCnt);
						}
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ScanNowDAOImpl : getSmartSearchProdlst : Store Procedure error : usp_HcProductSmartSearchProductList ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ScanNowDAOImpl : getSmartSearchProdlst : Store Procedure error : usp_HcProductSmartSearchProductList : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return productDetails;
	}

	/**
	 * This DAOImpl method for fetching Smart Search product count information
	 * based on search key and user Id.
	 * 
	 * @param xml
	 *            as request contains user Id and search key
	 * @return productDetails list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ProductDetails getSmartSearchCount(ProductDetail productDetail) throws HubCitiException {
		LOG.info("Inside ScanNowDAOImpl : getSmartSearchCount");
		List<ProductDetail> productDetailList;
		ProductDetails productDetails = null;
		try {
			if (null == productDetail.getLastVistedProductNo()) {
				productDetail.setLastVistedProductNo(0);
			}
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcProductSmartSearchResults");
			simpleJdbcCall.returningResultSet("smartsearchresults", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("ProdSearch", productDetail.getSearchkey());
			scanQueryParams.addValue("ParentCategoryID", productDetail.getParCatId());
			scanQueryParams.addValue("LowerLimit", productDetail.getLastVistedProductNo());
			scanQueryParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			scanQueryParams.addValue("HcHubCitiID", productDetail.getHubCitiId());
			// for user tracking
			scanQueryParams.addValue("MainMenuID", productDetail.getMainMenuId());
			scanQueryParams.addValue("ProductSmartSearchID", productDetail.getProdSmaSeaId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			productDetailList = (List<ProductDetail>) resultFromProcedure.get("smartsearchresults");
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					if (null != productDetailList && !productDetailList.isEmpty()) {
						productDetails = new ProductDetails();
						productDetails.setProductDetail(productDetailList);
						productDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						productDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						if (null != nextpage) {
							if (nextpage) {
								productDetails.setNextPage(1);
							} else {
								productDetails.setNextPage(0);
							}
						}
						if (null != maxCnt) {
							productDetails.setMaxCount(maxCnt);
						}
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ScanNowDAOImpl : getSmartSearchCount : Store Procedure error : usp_HcProductSmartSearchResults ", errorNum,
							errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ScanNowDAOImpl : getSmartSearchCount : Store Procedure error : usp_HcProductSmartSearchResults : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return productDetails;
	}

	/**
	 * This DAOImpl method For adding smart search data to database for User
	 * tracking
	 * 
	 * @param request
	 *            contains mainMenuID and search key
	 * @return productSmartSearchID
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public Integer userTrackingProductSmartSearch(String prodSearch, Integer mainMenuID) throws HubCitiException {
		LOG.info("Inside ScanNowDAOImpl : userTrackingProductSmartSearch");
		Integer prodSmaSeaID = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTrackingProductSmartSearch");
			simpleJdbcCall.returningResultSet("smartsearchcount", new BeanPropertyRowMapper<UserTrackingData>(UserTrackingData.class));

			final MapSqlParameterSource productSearch = new MapSqlParameterSource();
			productSearch.addValue("ProdSearch", prodSearch);
			productSearch.addValue("MainMenuID", mainMenuID);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(productSearch);
			if (null == resultFromProcedure.get("ErrorNumber")) {
				prodSmaSeaID = (Integer) resultFromProcedure.get("ProductSmartSearchID");
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside ScanNowDAOImpl : userTrackingProductSmartSearch : Store Procedure error : usp_HcUserTrackingProductSmartSearch ",
						errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ScanNowDAOImpl : userTrackingProductSmartSearch : Store Procedure error : usp_HcUserTrackingProductSmartSearch : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return prodSmaSeaID;
	}

	/**
	 * This DAOImpl method is used to fetch product information based on search
	 * key.
	 * 
	 * @param searchDetail
	 *            contains search key,user id.
	 * @return productDetails product information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ProductDetails smartSearchProducts(ProductDetail searchDetail) throws HubCitiException {
		LOG.info("Inside ScanNowDAOImpl : smartSearchProducts");
		List<ProductDetail> productDetailList;
		ProductDetails productDetails = null;
		try {
			if (null == searchDetail.getLastVistedProductNo()) {
				searchDetail.setLastVistedProductNo(0);
			}
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcProductSmartSearch");
			simpleJdbcCall.returningResultSet("smartsearchproducts", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("ProdSearch", searchDetail.getSearchkey());
			scanQueryParams.addValue("HcHubCitiID", searchDetail.getHubCitiId());
			scanQueryParams.addValue("UserID", searchDetail.getUserId());

			// for user tracking
			scanQueryParams.addValue("MainMenuID", searchDetail.getMainMenuId());

			long strtTime = new Date().getTime();
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			long endTime = new Date().getTime();
			LOG.info("Time take by database to respond: " + (endTime - strtTime));
			productDetailList = (List<ProductDetail>) resultFromProcedure.get("smartsearchproducts");
			Double timeTakenBySP = (Double) resultFromProcedure.get("TimeTaken");
			LOG.info("Time take by database to respond (from database output value): " + timeTakenBySP);
			Integer prodSmaSeaID = (Integer) resultFromProcedure.get("ProductSmartSearchID");
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					if (null != productDetailList && !productDetailList.isEmpty()) {
						productDetails = new ProductDetails();
						productDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						productDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
						productDetails.setProductDetail(productDetailList);
						productDetails.setProdSmaSeaId(prodSmaSeaID);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ScanNowDAOImpl : smartSearchProducts : Store Procedure error : usp_HcProductSmartSearch ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside ScanNowDAOImpl : smartSearchProducts : Store Procedure error : usp_HcProductSmartSearch : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		return productDetails;
	}

	/**
	 * This DAOImpl method is used to fetch Scan History information.
	 * 
	 * @param userId
	 *            as request parameter.
	 * @param lastVisitedRecord
	 *            as request parameter. for to fetch next set of records.
	 * @return Xml contains scan history information
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application will be thrown which is caught in the
	 *             Controller layer
	 */
	public List<ProductDetail> getScanHistory(Integer userId, Integer lastVisitedRecord, Integer hubCitiId) throws HubCitiException {
		final String methodName = "getScanHistory";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ArrayList<ProductDetail> productDetailList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcScanHistoryDisplayPagination");
			simpleJdbcCall.returningResultSet("scanhistory", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", userId);
			scanQueryParams.addValue("LowerLimit", lastVisitedRecord);
			scanQueryParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			scanQueryParams.addValue("HcHubcitiID", hubCitiId);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					productDetailList = (ArrayList<ProductDetail>) resultFromProcedure.get("scanhistory");
					if (!productDetailList.isEmpty() && productDetailList != null) {
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						// This code for Pagination. Set the NextPage flag to
						// first row of list
						if (nextpage != null) {
							if (nextpage) {
								productDetailList.get(0).setNextPage(1);
							} else {
								productDetailList.get(0).setNextPage(0);
							}
						}
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_ScanHistoryDisplayPagination Store Procedure error number: {} and error message: {}", errorNum,
							errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (EmptyResultDataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		} catch (DataAccessException exception) {
			LOG.error("Exception Occurred in getScanHistory", exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return productDetailList;
	}

	/**
	 * The method for deleting wish list item.
	 * 
	 * @param productDetail
	 *            request parameter
	 * @return success or failure depending upon the result of operation.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String deleteScanHistoryItem(ProductDetail productDetail) throws HubCitiException {
		final String methodName = "deleteScanHistoryItem";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		Integer result = 1;

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
		simpleJdbcCall.withProcedureName("usp_HcScanHistorytDelete");
		MapSqlParameterSource wishListDeleteParameters;
		try {
			wishListDeleteParameters = new MapSqlParameterSource();
			wishListDeleteParameters.addValue("ScanHistoryID", productDetail.getScanHistoryId());
			wishListDeleteParameters.addValue(HubCitiConstants.USERID, productDetail.getUserId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(wishListDeleteParameters);
			result = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			if (result == 0) {
				response = HubCitiConstants.SUCCESS;
			} else {
				response = HubCitiConstants.FAILURE;
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsgis = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "in usp_ScanHistorytDelete" + errorNum + "errorMsgis.." + errorMsgis);
				throw new HubCitiException(errorMsgis);
			}
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}

		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}

	/**
	 * This DAOImpl method is used to fetch product information based on search
	 * key.
	 * 
	 * @param searchDetail
	 *            contains search key,user id.
	 * @return productDetails product information.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application will be thrown which is caught in the
	 *             Controller layer
	 */
	public ProductDetails seacrhAddForProductName(ProductDetail searchDetail) throws HubCitiException {

		final String methodName = "scanForProductName";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		List<ProductDetail> productDetailList;
		ProductDetails productDetails = null;

		try {
			if (null == searchDetail.getLastVistedProductNo()) {
				searchDetail.setLastVistedProductNo(0);
			}
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSearchProductPagination");
			simpleJdbcCall.returningResultSet("products", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserId", searchDetail.getUserId());
			scanQueryParams.addValue("ProdSearch", searchDetail.getSearchkey());
			scanQueryParams.addValue("LowerLimit", searchDetail.getLastVistedProductNo());
			scanQueryParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			scanQueryParams.addValue("HcHubCitiID", searchDetail.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			productDetailList = (List<ProductDetail>) resultFromProcedure.get("products");
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					if (null != productDetailList && !productDetailList.isEmpty()) {
						LOG.info("Products found for the search");
						productDetails = new ProductDetails();
						productDetails.setProductDetail(productDetailList);

						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");

						// This code for Pagination. Set the NextPage flag to
						// first row of list
						if (nextpage != null) {
							if (nextpage) {
								productDetails.setNextPage(1);
							} else {
								productDetails.setNextPage(0);
							}
						}
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_SearchProduct Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in scanForProductName", exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return productDetails;
	}
}
