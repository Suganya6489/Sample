package com.hubciti.find.dao;

import java.sql.Clob;
import java.util.ArrayList;
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
import org.springframework.jdbc.core.simple.*;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.BottomButton;
import com.hubciti.common.pojos.CLRDetails;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.Filter;
import com.hubciti.common.pojos.FindNearByIntactResponse;
import com.hubciti.common.pojos.FindNearByPartialResponse;
import com.hubciti.common.pojos.FindNearByResponse;
import com.hubciti.common.pojos.GoogleCategory;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.ProductAttributes;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.utility.Utility;
import com.hubciti.find.query.FindQueries;
import com.scansee.externalapi.common.constants.ApplicationConstants;
import com.scansee.externalapi.common.pojos.ExternalAPISearchParameters;
import com.scansee.externalapi.common.pojos.ExternalAPIVendor;


/**
 * Class for Find Dao Implementation
 * 
 * @author dhruvanath_mm
 */
@SuppressWarnings({"unchecked" })
public class FindDaoImpl implements FindDao {

	/**
	 * Getting the LOGger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FindDaoImpl.class);

	/**
	 * For JDBC connection
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * calling stored procedure.
	 */
	private SimpleJdbcCall simpleJdbcCall;

	/*
	 * To set ParameterizedBeanPropertyRowMapper to map POJO.
	 */
	@Deprecated
	private SimpleJdbcTemplate simpleJdbcTemplate;

	/**
	 * To get the datasource.
	 * 
	 * @param dataSource
	 *            The data source for Spring JDBC connection.
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * The DAO method fetches Category Details. no Query parameter
	 * 
	 * @return HotDealsCategoryInfo.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */

	public Categories getCategoryDetail(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : getCategoryDetail");

		List<GoogleCategory> categoryList = null;
		List<BottomButton> bottomBtnList = null;
		Categories objCategories = null;

		if (null == objMenuItem.getLevelFlag()) {
			objMenuItem.setLevelFlag(false);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFindCategoryDisplay");
			simpleJdbcCall.returningResultSet("Categorylist", new BeanPropertyRowMapper<GoogleCategory>(GoogleCategory.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource fetchCatParam = new MapSqlParameterSource();
//			fetchCatParam.addValue("UserID", objMenuItem.getUserId());
			fetchCatParam.addValue("HubCitiID", objMenuItem.getHubCitiId());
			fetchCatParam.addValue("HcMenuItemID", objMenuItem.getmItemId());
			fetchCatParam.addValue("HcBottmoButtonID", objMenuItem.getBottomBtnId());
			fetchCatParam.addValue("Latitude", objMenuItem.getLatitude());
			fetchCatParam.addValue("Longitude", objMenuItem.getLongitude());
			fetchCatParam.addValue("PostalCode", objMenuItem.getPostalCode());
			fetchCatParam.addValue("SubmenuFlag", objMenuItem.getLevelFlag());

			fetchCatParam.addValue("CityIDs", objMenuItem.getCityIds());
			fetchCatParam.addValue("Radius", objMenuItem.getRadius());
			fetchCatParam.addValue("UserConfiguredPostalCode", objMenuItem.getUserPostalCode());

			final Map<String, Object> resFromProc = simpleJdbcCall.execute(fetchCatParam);

			if (null != resFromProc) {
				if (null == resFromProc.get(HubCitiConstants.ERRORNUMBER)) {

					categoryList = (List<GoogleCategory>) resFromProc.get("Categorylist");
					bottomBtnList = (List<BottomButton>) resFromProc.get("bottomButtonDetails");

					objCategories = new Categories();
					objCategories.setNoRecordMsg(Utility.clobToString((Clob)resFromProc.get("NoRecordsMsg")));

					objCategories.setmColor((String) resFromProc.get("MenuColor"));
					objCategories.setmFontColor((String) resFromProc.get("MenuFontColor"));

					objCategories.setCatList(categoryList);

					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						objCategories.setBottomBtnList(bottomBtnList);
						objCategories.setBottomBtn(1);
					} else {
						objCategories.setBottomBtn(0);
					}

				} else {
					final Integer errorNum = (Integer) resFromProc.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resFromProc.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside FindDaoImpl : getCategoryDetail :  usp_HcFindCategoryDisplay : " + errorNum + " and error message: " + errorMsg);
					throw new HubCitiException(errorMsg);
				}

			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FindDaoImpl : getCategoryDetail : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FindDaoImpl : getCategoryDetail");
		return objCategories;
	}

	/**
	 * The DAO method fetches retailer details.
	 * 
	 * @param ServiceSerachRequest
	 *            object. .
	 * @return RetailersDetails object.
	 * @throws HubCitiException.
	 */
	public RetailersDetails findScanSeeRetSearch(RetailerDetail objRetDet) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : findScanSeeRetSearch");

		RetailersDetails retailerDetails = null;
		List<RetailerDetail> retailerDetailList = null;
		Integer findRetSeaId = null;
		Integer iMaxCount = 0;
		boolean bNxtPageFlag = false;
		List<BottomButton> bottomBtnList = null;

		if (objRetDet.getLastVisitedNo() == null) {
			objRetDet.setLastVisitedNo(0);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HCFindRetailerSearchPagination");

			simpleJdbcCall.returningResultSet("searchRetailerDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));
			final MapSqlParameterSource objRetSearchParam = new MapSqlParameterSource();
			objRetSearchParam.addValue(HubCitiConstants.USERID, objRetDet.getUserId());
			objRetSearchParam.addValue("HCHubCitiID", objRetDet.getHubCitiId());
			objRetSearchParam.addValue("SearchKey", objRetDet.getSearchKey());
			objRetSearchParam.addValue("Latitude", objRetDet.getLatitude());
			objRetSearchParam.addValue("Longitude", objRetDet.getLongitude());
			objRetSearchParam.addValue("Radius", objRetDet.getRadius());
			objRetSearchParam.addValue("LowerLimit", objRetDet.getLastVisitedNo());
			objRetSearchParam.addValue("ScreenName", HubCitiConstants.FINDSCREENNAME);
			// for user tracking
			objRetSearchParam.addValue("MainMenuID", objRetDet.getMainMenuId());
			objRetSearchParam.addValue("SortColumn", objRetDet.getSortColumn());
			objRetSearchParam.addValue("SortOrder", objRetDet.getSortOrder());
			//	objRetSearchParam.addValue("GroupColumn", objRetDet.getGroupBy());
			objRetSearchParam.addValue("HcCityID", objRetDet.getCityIds());

			objRetSearchParam.addValue("LocalSpecials", objRetDet.getLocSpecials());
			objRetSearchParam.addValue("Interests", objRetDet.getInterests());

			objRetSearchParam.addValue("FilterID", objRetDet.getFilterId());
			objRetSearchParam.addValue("FilterValuesID", objRetDet.getfValueId());

			objRetSearchParam.addValue("HcMenuItemID", objRetDet.getmItemId());
			objRetSearchParam.addValue("HcBottomButtonID", objRetDet.getBottomBtnId());
			objRetSearchParam.addValue("requestedTime",objRetDet.getRequestedTime());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(objRetSearchParam);

			if (null == resultFromProcedure.get("ErrorNumber")) {

				retailerDetailList = (List<RetailerDetail>) resultFromProcedure.get("searchRetailerDetails");
				bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
				retailerDetails = new RetailersDetails();

				if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
					retailerDetails.setBottomBtnList(bottomBtnList);
					retailerDetails.setBottomBtn(1);
				} else {
					retailerDetails.setBottomBtn(0);
				}

				findRetSeaId = (Integer) resultFromProcedure.get("FindRetailerSearchID");
				iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
				bNxtPageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");


				if (null != retailerDetailList && !retailerDetailList.isEmpty()) {
					retailerDetails.setPoweredby(HubCitiConstants.FINDAPIPATNERNAME);
					retailerDetails.setRetailerDetail(retailerDetailList);
					retailerDetails.setFindRetSeaId(findRetSeaId);
					retailerDetails.setMaxCnt(iMaxCount);
					if (bNxtPageFlag == false) {
						retailerDetails.setNextPage(0);
					} else {
						retailerDetails.setNextPage(1);
					}
					retailerDetails.setMaxRowNum(retailerDetailList.get(retailerDetailList.size() - 1).getRowNumber());
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "usp_HcFindRetailerSearchPagination ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in findScanSeeRetSearch", exception);
			throw new HubCitiException(exception);
		}
		LOG.info("Exit FindDaoImpl : findScanSeeRetSearch");
		return retailerDetails;
	}

	/**
	 * The DAO method fetches retailer details.
	 * 
	 * @param ServiceSerachRequest
	 *            object. .
	 * @return RetailersDetails object.
	 * @throws HubCitiException.
	 */
	public RetailersDetails findScanSeeCategorySearch(RetailerDetail objRetDet) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : findScanSeeCategorySearch");

		RetailersDetails retailersDetailsObj = null;
		List<RetailerDetail> retailStoreslst = null;
		List<BottomButton> bottomBtnList = null;

		if (null == objRetDet.getLastVisitedNo()) {
			objRetDet.setLastVisitedNo(0);
		}

		if ("".equals(Utility.checkNull(objRetDet.getCityIds()))) {
			objRetDet.setCityIds(null);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcFindRetailerListPagination");

			simpleJdbcCall.returningResultSet("retailerStoreDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource findRetListParams = new MapSqlParameterSource();
			/*Filter Changes HubCiti2.1 
			 findRetListParams.addValue("ApiPartnerName", HubCitiConstants.FINDAPIPATNERNAME);
			 findRetListParams.addValue("GroupColumn", objRetDet.getGroupBy());
			 */

			findRetListParams.addValue(HubCitiConstants.USERID, objRetDet.getUserId());
			findRetListParams.addValue("CategoryName", objRetDet.getCatName());
			findRetListParams.addValue("HCHubCitiID", objRetDet.getHubCitiId());
			findRetListParams.addValue("LowerLimit", objRetDet.getLastVisitedNo());
			findRetListParams.addValue("ScreenName", HubCitiConstants.FINDSCREENNAME);
			findRetListParams.addValue("HcMenuItemId", objRetDet.getmItemId());
			findRetListParams.addValue("Latitude", objRetDet.getLatitude());
			findRetListParams.addValue("Longitude", objRetDet.getLongitude());
			findRetListParams.addValue("Radius", objRetDet.getRadius());
			findRetListParams.addValue("SearchKey", objRetDet.getSearchKey());
			findRetListParams.addValue("PostalCode", objRetDet.getPostalCode());
			findRetListParams.addValue("HcBottomButtonId", objRetDet.getBottomBtnId());
			findRetListParams.addValue("MainMenuID", objRetDet.getMainMenuId());
			findRetListParams.addValue("BusinessSubCategoryID", objRetDet.getSubCatIds());
			findRetListParams.addValue("SortColumn",objRetDet.getSortColumn());
			findRetListParams.addValue("SortOrder", objRetDet.getSortOrder());
			findRetListParams.addValue("FilterID", objRetDet.getFilterId());
			findRetListParams.addValue("FilterValuesID", objRetDet.getfValueId());
			findRetListParams.addValue("requestedTime",objRetDet.getRequestedTime());
			// For user tracking
			//			findRetListParams.addValue("MainMenuID", objRetDet.getMainMenuId());
			//Region/Area App
			findRetListParams.addValue("HcCityID", objRetDet.getCityIds());


			findRetListParams.addValue("LocalSpecials", objRetDet.getLocSpecials());
			findRetListParams.addValue("Interests", objRetDet.getInterests());
			//			findRetListParams.addValue("SearchKey", objRetDet.getSearchKey());
			findRetListParams.addValue("UserConfiguredPostalCode", objRetDet.getUserPostalCode());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(findRetListParams);

			if (null == resultFromProcedure.get("ErrorNumber")) {
				retailStoreslst = (List<RetailerDetail>) resultFromProcedure.get("retailerStoreDetails");
				bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

				retailersDetailsObj = new RetailersDetails();

				if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
					retailersDetailsObj.setBottomBtnList(bottomBtnList);
					retailersDetailsObj.setBottomBtn(1);
				} else {
					retailersDetailsObj.setBottomBtn(0);
				}

				Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
				boolean nxtPage = (Boolean) resultFromProcedure.get("NxtPageFlag");
				Integer nextPage = 0;
				if (nxtPage == true) {
					nextPage = 1;
				}

				if (null != retailStoreslst && !retailStoreslst.isEmpty()) {
					retailersDetailsObj.setPoweredby(HubCitiConstants.FINDAPIPATNERNAME);
					retailersDetailsObj.setRetailerDetail(retailStoreslst);
					retailersDetailsObj.setMaxCnt(maxCnt);
					retailersDetailsObj.setNextPage(nextPage);
					retailersDetailsObj.setMaxRowNum(retailStoreslst.get(retailStoreslst.size() - 1).getRowNumber());
					LOG.info("Products found for the search");
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside FindDaoImpl : findScanSeeCategorySearch : usp_HcFindRetailerListPagination  : errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FindDaoImpl : findScanSeeCategorySearch : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FindDaoImpl : findScanSeeCategorySearch");
		return retailersDetailsObj;
	}

	/**
	 * Methods to get the products media info based on the media type from
	 * database. information.
	 * 
	 * @param RetailerDetail
	 *            object.
	 * @return ProductDetails onject.
	 * @throws HubCitiException.
	 */
	public ProductDetails getMediaInfo(ProductDetailsRequest objProdDetail) throws HubCitiException {
		final String methodName = "getMediaInfo in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		List<ProductDetail> productMediaDetaillst = null;
		ProductDetails productdetailsObj = null;
		String mediaType = objProdDetail.getMediaType();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiProductDetailsMediaDisplay");
			simpleJdbcCall.returningResultSet("productMediaInfo", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource mediaParameters = new MapSqlParameterSource();
			if ("audio".equalsIgnoreCase(mediaType)) {
				mediaType = "Audio Files";
			} else
				if ("video".equalsIgnoreCase(mediaType)) {
					mediaType = "Video Files";
				} else {
					mediaType = "Other Files";
				}
			mediaParameters.addValue("ProductID", objProdDetail.getProductId());
			mediaParameters.addValue("HcHubCitiID", objProdDetail.getHubCitiId());
			mediaParameters.addValue("MediaType", mediaType);
			// for user tracking
			mediaParameters.addValue("ProductListID", objProdDetail.getProdListId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(mediaParameters);

			if (null != resultFromProcedure) {
				if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Error occurred in getMediaInfo Store Procedure error number: {}and error message: {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				} else {
					productMediaDetaillst = (List<ProductDetail>) resultFromProcedure.get("productMediaInfo");
					if (productMediaDetaillst != null && !productMediaDetaillst.isEmpty()) {
						productdetailsObj = new ProductDetails();
						productdetailsObj.setMediaType(mediaType);
						productdetailsObj.setProductDetail(productMediaDetaillst);
					}
				}
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, e);
			throw new HubCitiException(e);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return productdetailsObj;
	}

	/**
	 * To update click on product media list to database for user tracking
	 * 
	 * @param pmListID
	 * @return success or failure string
	 * @throws HubCitiException
	 */
	public String userTrackingProdMediaClick(Integer pmListID) throws HubCitiException {
		final String methodName = "userTrackingProdMediaClick";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTrackingProductMediaClicks");

			final MapSqlParameterSource mediaClick = new MapSqlParameterSource();
			mediaClick.addValue("ProductMediaListID", pmListID);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(mediaClick);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					response = HubCitiConstants.SUCCESSTEXT;
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_HcUserTrackingProductMediaClicks Store Procedure error number: {} and error message: {}",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.info(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}
		return response;
	}

	/**
	 * The service method to fetch user configured radius from database.
	 * 
	 * @param userId
	 *            , module name as request parameter
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             for exception
	 */
	public Integer getUserRadius(Integer userId, String moduleName) throws HubCitiException {
		final String methodName = "getUserRadius in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		Integer usrRadius = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGetUserModuleRadius");

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", userId);
			scanQueryParams.addValue("ModuleName", moduleName);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					usrRadius = (Integer) resultFromProcedure.get("Radius");

				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_GetUserModuleRadius Store Procedure error number: {} " + errorNum + " and error message: {}"
							+ errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in getUserRadius", exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return usrRadius;
	}

	/**
	 * methods to get the external API List.
	 * 
	 * @param moduleName
	 *            requested moduleName.
	 * @return externalAPIList
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ArrayList<ExternalAPIVendor> getExternalAPIList(String moduleName) throws HubCitiException {
		final String methodName = "getExternalAPIList";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ArrayList<ExternalAPIVendor> externalAPIList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			if ("FindOnlineStores".equals(moduleName) || "Find".equals(moduleName)) {
				simpleJdbcCall.withProcedureName("usp_HcGetAPIListOnline");
			} else {
				simpleJdbcCall.withProcedureName("usp_HcGetAPIList");
			}
			simpleJdbcCall.returningResultSet("externalAPIListInfo", new BeanPropertyRowMapper<ExternalAPIVendor>(ExternalAPIVendor.class));
			final MapSqlParameterSource externalAPIListParameters = new MapSqlParameterSource();
			externalAPIListParameters.addValue("prSubModule", moduleName);
			externalAPIListParameters.addValue("HcHubcitiID", null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(externalAPIListParameters);
			externalAPIList = (ArrayList<ExternalAPIVendor>) resultFromProcedure.get("externalAPIListInfo");

			if (null != resultFromProcedure.get("ErrorNumber")) {
				final String errorMsg = (String) resultFromProcedure.get("ErrorMessage");
				final String errorNum = Integer.toString((Integer) resultFromProcedure.get("ErrorNumber"));
				LOG.error("Error occurred in usp_GetAPIList Store Procedure error number: {}" + errorNum + " and error message:{}");
				throw new HubCitiException(errorMsg);
			} else
				if (null != externalAPIList && !externalAPIList.isEmpty()) {
					return externalAPIList;
				}
		} catch (EmptyResultDataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception.getMessage());
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return externalAPIList;
	}

	/**
	 * methods to get the external API Input parameter List.
	 * 
	 * @param apiUsageID
	 *            requested APIUsageID.
	 * @param moduleName
	 *            requested moduleName.
	 * @return externalAPIInputParameters
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ArrayList<ExternalAPISearchParameters> getExternalAPIInputParameters(Integer apiUsageID, String moduleName) throws HubCitiException {
		final String methodName = "getExternalAPIInputParameters";
		LOG.info(HubCitiConstants.METHODSTART + methodName);

		ArrayList<ExternalAPISearchParameters> externalAPIInputParameters = null;
		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGetAPIInputParameters");
			simpleJdbcCall.returningResultSet("externalAPIInputParameters", new BeanPropertyRowMapper<ExternalAPISearchParameters>(
					ExternalAPISearchParameters.class));

			final MapSqlParameterSource externalAPIListParameters = new MapSqlParameterSource();
			externalAPIListParameters.addValue("prAPIUsageID", apiUsageID);
			externalAPIListParameters.addValue("PrAPISubModuleName", moduleName);
			externalAPIListParameters.addValue("HcHubcitiID", null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(externalAPIListParameters);
			externalAPIInputParameters = (ArrayList<ExternalAPISearchParameters>) resultFromProcedure.get("externalAPIInputParameters");

			if (null != resultFromProcedure.get("ErrorNumber")) {
				final String errorMsg = (String) resultFromProcedure.get("ErrorMessage");
				final String errorNum = Integer.toString((Integer) resultFromProcedure.get("ErrorNumber"));
				LOG.error("Error occurred in usp_GetAPIList Store Procedure error number: {}" + errorNum + " and error message -->: {}");
				throw new HubCitiException(errorMsg);
			} else
				if (null != externalAPIInputParameters && !externalAPIInputParameters.isEmpty()) {
					return externalAPIInputParameters;
				}
		} catch (EmptyResultDataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception.getMessage());
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception.getMessage());
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return externalAPIInputParameters;
	}

	/**
	 * This DAO Implementation method for getting find service categories.
	 * 
	 * @param mainCat
	 *            as request parameter.
	 * @return response based on success or failure.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public List<GoogleCategory> getAllCategories(String mainCat) throws HubCitiException {
		final String methodName = "getCategoryDetail in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		List<GoogleCategory> categorylst = null;

		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFindCategoryForAPI");
			simpleJdbcCall.returningResultSet("Categorylist", new BeanPropertyRowMapper<GoogleCategory>(GoogleCategory.class));

			final MapSqlParameterSource fetchCategoryParameters = new MapSqlParameterSource();
			fetchCategoryParameters.addValue("ApiPartnerName", "Google");
			fetchCategoryParameters.addValue("CategoryName", mainCat);
			fetchCategoryParameters.addValue("HcHubcitiID", null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCategoryParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					categorylst = (List<GoogleCategory>) resultFromProcedure.get("Categorylist");
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_FindCategoryDisplay Store Procedure error number: {} " + errorNum + " and error message: {}"
							+ errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}

		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in getCategoryDetail", exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return categorylst;
	}

	/**
	 * The method for fetching product details.
	 * 
	 * @param productId
	 *            request parameter
	 * @param userId
	 *            request parameter
	 * @param retailId
	 *            request parameter
	 * @return The ProductDetail as Product details.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public ProductDetail getProductDetail(int userId, int productId, int retailId, Integer saleListId, Integer prodListId, Integer mainMenuId,
			Integer scanId, Integer hubCitiId) throws HubCitiException {
		final String methodName = "getProductDetail";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ProductDetail productDetail = null;
		String retailerId = null;
		try {
			if (retailId != 0) {
				retailerId = String.valueOf(retailId);
			}
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcProductDetails");
			simpleJdbcCall.returningResultSet("shoppingProductDetail", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource shoppingProductParameters = new MapSqlParameterSource();
			shoppingProductParameters.addValue("ProductID", productId);
			shoppingProductParameters.addValue("UserID", userId);
			shoppingProductParameters.addValue("ScanLongitude", null);
			shoppingProductParameters.addValue("ScanLatitude", null);
			shoppingProductParameters.addValue("RetailID", retailerId);
			shoppingProductParameters.addValue("HcHubCitiID", hubCitiId);
			// For user tracking.
			shoppingProductParameters.addValue("SaleListID", saleListId);
			shoppingProductParameters.addValue("ProductListID", prodListId);
			shoppingProductParameters.addValue("MainMenuID", mainMenuId);
			shoppingProductParameters.addValue("ScanTypeID", scanId);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shoppingProductParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					final List<ProductDetail> productDetaillst = (List<ProductDetail>) resultFromProcedure.get("shoppingProductDetail");
					if (null != productDetaillst && !productDetaillst.isEmpty()) {
						productDetail = productDetaillst.get(0);
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get("ErrorMessage");
					final Integer errorNum = (Integer) resultFromProcedure.get("ErrorNumber");
					LOG.error("Error occurred in getProductDetail Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}

		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in getProductDetail", exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return productDetail;
	}

	/**
	 * The method for fetching Lowest price for the product and total retailers
	 * where product is available.
	 * 
	 * @param userID
	 *            The userId in the user request.
	 * @param latitude
	 *            The latitude value.
	 * @param longitude
	 *            The longitude value.
	 * @param productId
	 *            The product ID for which lowest price information is to be
	 * @param postalcode
	 *            The postal code for which lowest price information is to be
	 *            displayed.
	 * @param radius
	 *            The radius specified by the user.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application
	 * @return findNearByLowestPrice The xml with NearBy-Lowest Price Info.
	 */
	public FindNearByIntactResponse fetchNearByLowestPrice(int userID, Double latitude, int productId, Double longitude, String postalcode,
			double radius, Integer mainMenuId, Integer hubCitiId) throws HubCitiException {
		final String methodName = "fetchNearByLowestPrice in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		int findNearByCount;
		String findNearByLowest;

		final FindNearByIntactResponse findNearByDetailsResultSet = new FindNearByIntactResponse();
		FindNearByPartialResponse findNearByPartialResponse = null;
		ArrayList<FindNearByResponse> findNearByDetailList = null;
		Integer dbresult = 0;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFindNearByRetailer");
			simpleJdbcCall.returningResultSet("FindNearByInfo", new BeanPropertyRowMapper<FindNearByResponse>(FindNearByResponse.class));

			final MapSqlParameterSource fetchNearByParameters = new MapSqlParameterSource();
			fetchNearByParameters.addValue("UserID", userID);
			fetchNearByParameters.addValue("Latitude", latitude);
			fetchNearByParameters.addValue("Longitude", longitude);
			fetchNearByParameters.addValue("ProductId", productId);
			fetchNearByParameters.addValue("PostalCode", postalcode);
			fetchNearByParameters.addValue("Radius", radius);
			fetchNearByParameters.addValue("HcHubCitiID", hubCitiId);
			// For user tracking
			fetchNearByParameters.addValue("MainMenuID", mainMenuId);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchNearByParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					dbresult = (Integer) resultFromProcedure.get("Result");
					if (dbresult != null) {
						if (dbresult == -1) {
							findNearByDetailsResultSet.setFindNearBy(true);
							return findNearByDetailsResultSet;
						}
					}

					findNearByDetailList = (ArrayList<FindNearByResponse>) resultFromProcedure.get("FindNearByInfo");
					if (null != findNearByDetailList && !findNearByDetailList.isEmpty()) {
						findNearByCount = findNearByDetailList.size();
						findNearByLowest = findNearByDetailList.get(0).getProductPrice();
						findNearByPartialResponse = new FindNearByPartialResponse();
						findNearByPartialResponse.setLowestPrice(findNearByLowest);
						findNearByPartialResponse.setTotalLocations(String.valueOf(findNearByCount) + " Stores");
						findNearByPartialResponse.setImagePath(findNearByDetailList.get(0).getImagePath());

						findNearByDetailsResultSet.setFindNearByDetails(findNearByDetailList);
						findNearByDetailsResultSet.setFindNearByMetaData(findNearByPartialResponse);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_FindNearByRetailer Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, e);
			throw new HubCitiException(e);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return findNearByDetailsResultSet;
	}

	public ArrayList<RetailerDetail> fetchCommissionJunctionData(Integer userId, Integer productId, Integer productListId, Integer mainMenuId,
			Integer hubCitiId) throws HubCitiException {
		final String methodName = "fetchCommissionJunctionData";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ArrayList<RetailerDetail> onlineRetailerlst = null;
		try {
			LOG.info(" Request received from Userid ", userId);

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcOnlineRetailerInformation");
			simpleJdbcCall.returningResultSet("commissionjunction", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource fetchProductDetailsParameters = new MapSqlParameterSource();
			fetchProductDetailsParameters.addValue(HubCitiConstants.USERID, userId);
			fetchProductDetailsParameters.addValue("ProductID", productId);
			fetchProductDetailsParameters.addValue("HcHubCitiID", hubCitiId);
			// for user tracking
			fetchProductDetailsParameters.addValue("ProductListID", productListId);
			fetchProductDetailsParameters.addValue("MainMenuID", mainMenuId);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchProductDetailsParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					onlineRetailerlst = (ArrayList<RetailerDetail>) resultFromProcedure.get("commissionjunction");
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in fetchCommissionJunctionData Store Procedure error number: {} and error message: {}", errorNum,
							errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.info(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return onlineRetailerlst;
	}

	/**
	 * methods to get product info for external API.
	 * 
	 * @param productId
	 *            requested productId.
	 * @return ProductDetail object
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	@SuppressWarnings("deprecation")
	public ProductDetail getProductInfoforExternalAPI(Integer productId) throws HubCitiException {

		final String methodName = "getProductInfo";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ProductDetail productDetail = null;

		try {
			simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
			productDetail = simpleJdbcTemplate.queryForObject(FindQueries.PRODUCTINOQUERY, new BeanPropertyRowMapper<ProductDetail>(
					ProductDetail.class), productId);
			if (productDetail != null) {
				return productDetail;
			}
		} catch (EmptyResultDataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			return null;
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return productDetail;
	}

	/**
	 * This method is used to insert apiurls into FindNearByLog table.
	 * 
	 * @param apiURL
	 *            -As String parameter
	 * @param userId
	 *            -As int parameter
	 * @return response as failure or success
	 * @throws HubCitiException
	 *             throws if any exception.
	 */
	public String insertExternalAPIURL(String apiURL, int userId) throws HubCitiException {
		final String methodName = "insertBatchStatus";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		int result = 1;
		String response = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			result = this.jdbcTemplate.update(FindQueries.INSERTEXTERNALAPICALURL, apiURL, Utility.getFormattedCurrentDate(), userId);
			if (result == 0) {
				response = HubCitiConstants.SUCCESS;
			} else {
				response = HubCitiConstants.FAILURE;
			}
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}
		return response;
	}

	/**
	 * The method for getting product attributes information.
	 * 
	 * @param userId
	 *            are the request parameters.
	 * @param productId
	 *            are the request parameters.
	 * @return The XML with fetched information.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 * @throws HubCitiException
	 */
	public ProductDetail fetchProductAttributes(Integer userId, Integer productId) throws HubCitiException {
		final String methodName = "fetchProductAttributes";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ArrayList<ProductDetail> productDetailslst = null;
		ProductDetail productDetailObj = null;
		ProductDetails productImageObj = null;
		final List<ProductDetails> productImageslst = new ArrayList<ProductDetails>();
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcWishListProductAttributes");
			simpleJdbcCall.returningResultSet("productdetails", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("ProductID", productId);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);


			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				productDetailslst = (ArrayList<ProductDetail>) resultFromProcedure.get("productdetails");

				if (null != productDetailslst && !productDetailslst.isEmpty()) {
					productDetailObj = new ProductDetail();
					for (int i = 0; i < productDetailslst.size(); i++) {
						productImageObj = new ProductDetails();
						productImageObj.setProductMediaPath(productDetailslst.get(i).getProductMediaPath());
						productImageslst.add(productImageObj);
					}
					productDetailObj.setProductId(productDetailslst.get(0).getProductId());
					productDetailObj.setProductName(productDetailslst.get(0).getProductName());
					productDetailObj.setProductImagePath(productDetailslst.get(0).getProductImagePath());
					productDetailObj.setProductName(productDetailslst.get(0).getProductName());
					productDetailObj.setProductShortDescription(productDetailslst.get(0).getProductShortDescription());
					productDetailObj.setProductLongDescription(productDetailslst.get(0).getProductLongDescription());
					productDetailObj.setVideoFlag(productDetailslst.get(0).getVideoFlag());
					productDetailObj.setAudioFlag(productDetailslst.get(0).getAudioFlag());
					productDetailObj.setFileFlag(productDetailslst.get(0).getFileFlag());
					productDetailObj.setModelNumber(productDetailslst.get(0).getModelNumber());
					productDetailObj.setWarrantyServiceInfo(productDetailslst.get(0).getWarrantyServiceInfo());
					productDetailObj.setProductImageslst(productImageslst);
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "in usp_HcWishListProductAttributes" + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName + e);
			throw new HubCitiException(e.getMessage());
		}

		ProductAttributes productAttributesObj = null;
		final List<ProductAttributes> productAttributeslst = new ArrayList<ProductAttributes>();
		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcWishlistProductAttributeDisplay");
			simpleJdbcCall.returningResultSet("productAttributes", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("ProductID", productId);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);


			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				productDetailslst = (ArrayList<ProductDetail>) resultFromProcedure.get("productAttributes");

				if (null != productDetailslst && !productDetailslst.isEmpty()) {
					for (int i = 0; i < productDetailslst.size(); i++) {
						productAttributesObj = new ProductAttributes();
						productAttributesObj.setAttributeName(productDetailslst.get(i).getAttributeName());
						productAttributesObj.setDisplayValue(productDetailslst.get(i).getDisplayValue());
						productAttributeslst.add(productAttributesObj);
					}
					productDetailObj.setProductAttributeslst(productAttributeslst);
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "in usp_HcWishlistProductAttributeDisplay" + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName + e);
			throw new HubCitiException(e.getMessage());
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return productDetailObj;
	}

	/**
	 * Method for fetching Coupon Info. The method is called from the service
	 * layer.
	 * 
	 * @param userId
	 *            The user id in the request.
	 * @param retailerId
	 *            The retail ID in the request.
	 * @param productId
	 *            The product id in the request.
	 * @param lowerLimit
	 *            The lowerLimit in the request.
	 * @throws HubCitiException
	 *             The exception defined for the application.
	 * @return couponsDetails The xml with Coupon Details.
	 */
	public CLRDetails fetchMasterSLCLRDetails(CLRDetails clrDetailsObj) throws HubCitiException {
		final String methodName = "fetchCouponInfo in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		CLRDetails clrDetatilsObj = null;
		/**
		 * This variables needed to check nextPage is 1 or 0 for pagination.
		 */
		boolean isCouponNextPage = false;
		List<CouponDetails> couponDetailList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcMasterShoppingListCoupon");
			simpleJdbcCall.returningResultSet("CouponDetails", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource fetchCouponParameters = new MapSqlParameterSource();
			fetchCouponParameters.addValue("ProductID", clrDetailsObj.getProductId());
			fetchCouponParameters.addValue("RetailID", clrDetailsObj.getRetailerId());
			fetchCouponParameters.addValue("UserID", clrDetailsObj.getUserId());
			fetchCouponParameters.addValue("LowerLimit", clrDetailsObj.getLowerLimit());
			fetchCouponParameters.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			fetchCouponParameters.addValue("HcHubCitiID", clrDetailsObj.getHubCitiId());
			// For user tracking
			fetchCouponParameters.addValue(HubCitiConstants.MAINMENUID, clrDetailsObj.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCouponParameters);

			if (null != resultFromProcedure) {
				if (null != resultFromProcedure.get("ErrorNumber")) {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.info("Error Occured in usp_MasterShoppingListCoupon method ..errorNum.{} errorMsg {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				} else {
					couponDetailList = (List<CouponDetails>) resultFromProcedure.get("CouponDetails");
					if (null != couponDetailList && !couponDetailList.isEmpty()) {
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						if (nextpage != null) {
							if (nextpage) {
								isCouponNextPage = true;
							}
						}
					}
				}
			}
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}

		if (couponDetailList != null && !couponDetailList.isEmpty()) {
			clrDetatilsObj = new CLRDetails();
			if (isCouponNextPage == true) {
				clrDetatilsObj.setNextPageFlag(1);
			}
			if (isCouponNextPage == true) {
				clrDetatilsObj.setClrC(1);
			} else {
				clrDetatilsObj.setClrC(0);
			}
			if (couponDetailList != null && !couponDetailList.isEmpty()) {
				clrDetatilsObj.setIsCouponthere(true);
				clrDetatilsObj.setCouponDetails(couponDetailList);
			} else {
				clrDetatilsObj.setIsCouponthere(false);
			}
		}
		return clrDetatilsObj;
	}

	/**
	 * To update click on online products to database for user tracking
	 * 
	 * @param pmListID
	 * @return success or failure string
	 * @throws ScanSeeException
	 */
	/**
	 * The DAO method To update click on online products to database for user
	 * tracking.
	 * 
	 * @param objAddSLRequest
	 *            instance of CLRDetails pojo class.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String userTrackingOnlineStoreClick(CLRDetails objAddSLRequest) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : userTrackingOnlineStoreClick");
		String response = null;
		Integer fromProc = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTrackingOnlineStoreClick");

			final MapSqlParameterSource onlineClickParam = new MapSqlParameterSource();
			/*
			 * onlineClickParam.addValue("ProductID",
			 * objAddSLRequest.getProductId());
			 * onlineClickParam.addValue("MainMenuID",
			 * objAddSLRequest.getMainMenuId());
			 * onlineClickParam.addValue("RetailerName",
			 * objAddSLRequest.getRetName());
			 */
			onlineClickParam.addValue("OnlineProductDetailID", objAddSLRequest.getProductId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(onlineClickParam);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (null != resultFromProcedure) {
				if (fromProc == 0) {
					response = ApplicationConstants.SUCCESS;
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside FindDaoImpl : userTrackingOnlineStoreClick : Store Procedure error : usp_HcUserTrackingOnlineStoreClick :  "
							+ errorNum + " and error message:" + errorMsg);
					response = HubCitiConstants.FAILURE;
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FindDaoImpl : userTrackingOnlineStoreClick : Store Procedure error : usp_HcUserTrackingOnlineStoreClick : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return response;
	}

	public RetailersDetails findSingleCategoryRetailers(RetailerDetail objRetDet) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : findSingleCategoryRetailers");

		if (objRetDet.getLastVisitedNo() == null) {
			objRetDet.setLastVisitedNo(0);
		}

		if ("".equals(Utility.checkNull(objRetDet.getCityIds()))) {
			objRetDet.setCityIds(null);
		}


		RetailersDetails retailersDetails = null;
		List<RetailerDetail> retailDetailsList = null;
		List<BottomButton> bottomBtnList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcSingleFindRetailerListPagination");

			simpleJdbcCall.returningResultSet("retailerStoreDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource findRetListParams = new MapSqlParameterSource();
			/*Filter Changes HubCiti2.1 
				findRetListParams.addValue("ApiPartnerName", HubCitiConstants.FINDAPIPATNERNAME);
				findRetListParams.addValue("GroupColumn", objRetDet.getGroupBy());

			 */
//			findRetListParams.addValue(HubCitiConstants.USERID, objRetDet.getUserId());
			findRetListParams.addValue("CategoryName", objRetDet.getCatName());
			findRetListParams.addValue("HCHubCitiID", objRetDet.getHubCitiId());
			findRetListParams.addValue("LowerLimit", objRetDet.getLastVisitedNo());
			findRetListParams.addValue("ScreenName", HubCitiConstants.FINDSCREENNAME);
			findRetListParams.addValue("HcMenuItemId", objRetDet.getmItemId());
			findRetListParams.addValue("Latitude", objRetDet.getLatitude());
			findRetListParams.addValue("Longitude", objRetDet.getLongitude());
			findRetListParams.addValue("Radius", objRetDet.getRadius());
			findRetListParams.addValue("SearchKey", objRetDet.getSearchKey());
			findRetListParams.addValue("BusinessSubCategoryID", objRetDet.getSubCatIds());
			findRetListParams.addValue("SortColumn", objRetDet.getSortColumn());
			findRetListParams.addValue("SortOrder", objRetDet.getSortOrder());
			findRetListParams.addValue("PostalCode", objRetDet.getPostalCode());
			findRetListParams.addValue("requestedTime",objRetDet.getRequestedTime());
			// For user tracking
			findRetListParams.addValue("MainMenuID", objRetDet.getMainMenuId());
			//Region/Area App

			findRetListParams.addValue("HcCityID", objRetDet.getCityIds());

			findRetListParams.addValue("FilterID", objRetDet.getFilterId());
			findRetListParams.addValue("FilterValuesID", objRetDet.getfValueId());

			findRetListParams.addValue("LocalSpecials", objRetDet.getLocSpecials());
			findRetListParams.addValue("Interests", objRetDet.getInterests());
			findRetListParams.addValue("HcBottomButtonId", objRetDet.getBottomBtnId());
			findRetListParams.addValue("UserConfiguredPostalCode", objRetDet.getUserPostalCode());	

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(findRetListParams);

			if (null == resultFromProcedure.get("ErrorNumber")) {

				bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
				retailDetailsList = (List<RetailerDetail>) resultFromProcedure.get("retailerStoreDetails");

				retailersDetails = new RetailersDetails();

				if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
					retailersDetails.setBottomBtnList(bottomBtnList);
					retailersDetails.setBottomBtn(1);
				} else {
					retailersDetails.setBottomBtn(0);
				}

				Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
				boolean nxtPage = (Boolean) resultFromProcedure.get("NxtPageFlag");
				Integer nextPage = 0;
				if (nxtPage == true) {
					nextPage = 1;
				}

				if (null != retailDetailsList && !retailDetailsList.isEmpty()) {
					retailersDetails.setPoweredby(HubCitiConstants.FINDAPIPATNERNAME);
					retailersDetails.setRetailerDetail(retailDetailsList);
					retailersDetails.setMaxCnt(maxCnt);
					retailersDetails.setNextPage(nextPage);
					retailersDetails.setMaxRowNum(retailDetailsList.get(retailDetailsList.size() - 1).getRowNumber());
				} else {
					retailersDetails.setNoRecordMsg(Utility.clobToString((Clob)resultFromProcedure.get("NoRecordsMsg")));
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside FindDaoImpl : findSingleCategoryRetailers : HcSingleFindRetailerListPagination : errorNum : " + errorNum + " : errorMsg : " + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FindDaoImpl : findSingleCategoryRetailers : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FindDaoImpl : findSingleCategoryRetailers");
		return retailersDetails;
	}

	/**
	 * Method for getting sub-categories for find single category
	 * 
	 * @return category list.
	 * @throws HubCitiException
	 */
	public CategoryDetails getSubCategories(UserDetails objUserDetails) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : getSubCategories");

		List<CategoryDetails> catDetailsList = null;
		CategoryDetails objCategoryDetails = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetailerBusinessSubCategoriesDisplay");
			simpleJdbcCall.returningResultSet("catList", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));
			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("HubCitiID", objUserDetails.getHubCitiId());
			scanQueryParams.addValue("BusinessCatID", objUserDetails.getCatId());
			scanQueryParams.addValue("UserID", objUserDetails.getUserId());
			scanQueryParams.addValue("Latitude", objUserDetails.getLatitude());
			scanQueryParams.addValue("Longitude", objUserDetails.getLongitude());
			scanQueryParams.addValue("PostalCode", objUserDetails.getPostalCode());
			scanQueryParams.addValue("HcMenuItemID", objUserDetails.getmItemId());
			scanQueryParams.addValue("BottomButtonID", objUserDetails.getBottomBtnId());
			scanQueryParams.addValue("searchkey", objUserDetails.getSrchKey());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			catDetailsList = (List<CategoryDetails>) resultFromProcedure.get("catList");
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null == errorNum) {
				objCategoryDetails = new CategoryDetails();
				Boolean userOutOfRange = (Boolean) resultFromProcedure.get("UserOutOfRange");
				String defPostalCode = null;
				if (userOutOfRange != null && userOutOfRange) {
					defPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");
				}
				objCategoryDetails.setDefPostalCode(defPostalCode);
				objCategoryDetails.setListCatDetails(catDetailsList);
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FindDaoImpl : getSubCategories  : Store Procedure error :  usp_HcRetailerBusinessSubCategoriesDisplay : Exception",
						errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FindDaoImpl : getSubCategories : Store Procedure error :  usp_HcRetailerBusinessSubCategoriesDisplay  DataAccessException: "
					+ e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside FindDaoImpl : getSubCategories : Store Procedure error :  usp_HcRetailerBusinessSubCategoriesDisplay : Exception: "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		LOG.info("Exit FindDaoImpl : getSubCategories");
		return objCategoryDetails;
	}


	/**
	 * DAOImpl method to fetch filter list from database.
	 * 
	 * @param MenuItem object
	 * @return filter object List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public Filter getFilterList(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : getFilterList");

		List<Filter> filterList = null;
		Filter objFilter = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcRetailerFilterValueList");

			simpleJdbcCall.returningResultSet("filterList", new BeanPropertyRowMapper<Filter>(Filter.class));

			final MapSqlParameterSource filterListParams = new MapSqlParameterSource();
			filterListParams.addValue("UserID", objMenuItem.getUserId());
			filterListParams.addValue("HcHubCitiID", objMenuItem.getHubCitiId());
			filterListParams.addValue("BusinessCategoryID", objMenuItem.getCatId());
			filterListParams.addValue("Latitude", objMenuItem.getLatitude());
			filterListParams.addValue("Longitude", objMenuItem.getLongitude());
			filterListParams.addValue("Radius", objMenuItem.getRadius());
			filterListParams.addValue("HcCityID", objMenuItem.getCityIds());
			filterListParams.addValue("HcMenuItemId", objMenuItem.getmItemId());
			filterListParams.addValue("HcBottomButtonId", objMenuItem.getBottomBtnId());
			filterListParams.addValue("SearchKey", objMenuItem.getSearchKey());
			filterListParams.addValue("FilterValueName", objMenuItem.getfName());


			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(filterListParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					filterList = (List<Filter>) resultFromProcedure.get("filterList");
					objFilter = new Filter();

					if ( null != filterList && !filterList.isEmpty()) {
						objFilter.setFilterList(filterList);
					}

				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getFilterList " + HubCitiConstants.SCHEMANAME+ " : usp_HcRetailerFilterValueList : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FindDaoImpl : getFilterList " + HubCitiConstants.SCHEMANAME+ " : usp_HcRetailerFilterValueList  : " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside FindDaoImpl : getFilterList " + HubCitiConstants.SCHEMANAME+ " :  usp_HcRetailerFilterValueList  : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FindDaoImpl : getFilterList");
		return objFilter;
	}


	/**
	 * DAOImpl method to  get Sort and Filter List from database.
	 * 
	 * @param MenuItem object
	 * @return filter object List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public ArrayList<Filter> getSortFilterList(UserDetails objUserDetails) throws HubCitiException {
		LOG.info("Inside FindDaoImpl : getSortFilterList");

		ArrayList <Filter> arFilterList = null;

		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			/* Module Parameter : Find All, Find Single, Citi Exp, Events */
			final MapSqlParameterSource sortFilterParams = new MapSqlParameterSource();

			if (objUserDetails.getModule().equalsIgnoreCase("Find All")) {
				simpleJdbcCall.withProcedureName("usp_HcFindOptionsListDisplay");
				
			} else if (objUserDetails.getModule().equalsIgnoreCase("ThisLocation")) {
				simpleJdbcCall.withProcedureName("usp_HcThisLocationOptionsListDisplay");
				
			} else if(objUserDetails.getModule().equalsIgnoreCase("Find Single")) {
				simpleJdbcCall.withProcedureName("usp_HcSingleFindOptionsListDisplay");
				
			} else if(objUserDetails.getModule().equalsIgnoreCase("Events")) {
				simpleJdbcCall.withProcedureName("usp_HcEventsOptionListDisplay");
				//changes done 
				sortFilterParams.addValue("RetailID",objUserDetails.getRetailId());
				sortFilterParams.addValue("RetailLocationID",objUserDetails.getRetailLocationId());
				sortFilterParams.addValue("FundRaisingID", objUserDetails.getFundId());

			} else if(objUserDetails.getModule().equalsIgnoreCase("CitiExp")) {
				simpleJdbcCall.withProcedureName("usp_HcCityExperienceOptionList");

				sortFilterParams.addValue("CityExperienceID", objUserDetails.getCitiExpId());
				sortFilterParams.addValue("CityID", objUserDetails.getCityIds());

			} else if(objUserDetails.getModule().equalsIgnoreCase("SubMenu")) {
				simpleJdbcCall.withProcedureName("usp_HcSubMenuOptionsListDisplay");
				sortFilterParams.addValue("MenuID", objUserDetails.getMenuId());

			} else if (objUserDetails.getModule().equalsIgnoreCase("filter")) {
				simpleJdbcCall.withProcedureName("usp_HcFilterOptionList");
				sortFilterParams.addValue("FilterID", objUserDetails.getFilterId());
				sortFilterParams.addValue("ZipCode", objUserDetails.getPostalCode());
			} else if (objUserDetails.getModule().equalsIgnoreCase("band")) {
				simpleJdbcCall.withProcedureName("usp_HcBandOptionListDisplay");
			} else if (objUserDetails.getModule().equalsIgnoreCase("bandevents")) {
				simpleJdbcCall.withProcedureName("usp_HcBandEventsFilterOptionList");
			} else if (objUserDetails.getModule().equalsIgnoreCase("SpecialOffers")) {
				simpleJdbcCall.withProcedureName("usp_HcSpecialOffersOptionList");
			} else if (objUserDetails.getModule().equalsIgnoreCase("Coupons")){
				simpleJdbcCall.withProcedureName("usp_HcCouponSortFilterListDisplay");
			}else if (objUserDetails.getModule().equalsIgnoreCase("myaccounts")){
				simpleJdbcCall.withProcedureName("usp_HcCouponGallerySortFilterListDisplay");
			}
			

			/*switch (objUserDetails.getModule()) {

			case "Find All" :
				simpleJdbcCall.withProcedureName("usp_HcFindOptionsListDisplay");
				break;
			case "Find Single" :
				simpleJdbcCall.withProcedureName("usp_HcSingleFindOptionsListDisplay");
				break;
			case "Events" :
				simpleJdbcCall.withProcedureName("usp_HcEventsOptionListDisplay");
				sortFilterParams.addValue("Postalcode", objUserDetails.getPostalCode());
				break;
			case "CitiExp" :
				simpleJdbcCall.withProcedureName("usp_HcCityExperienceOptionList");

				sortFilterParams.addValue("CityExperienceID", objUserDetails.getCitiExpId());
				sortFilterParams.addValue("CityID", objUserDetails.getCityIds());

				break;

			case "SubMenu" :
				simpleJdbcCall.withProcedureName("usp_HcSubMenuOptionsListDisplay");
				sortFilterParams.addValue("MenuID", objUserDetails.getMenuId());
				break;
			}
			 */
			simpleJdbcCall.returningResultSet("sortAndFilterList", new BeanPropertyRowMapper<Filter>(Filter.class));


			sortFilterParams.addValue("UserID", objUserDetails.getUserId());
			sortFilterParams.addValue("HcHubCitiID", objUserDetails.getHubCitiId());
			sortFilterParams.addValue("Latitude", objUserDetails.getLatitude());
			sortFilterParams.addValue("Longitude", objUserDetails.getLongitude());
			sortFilterParams.addValue("HcMenuItemId", objUserDetails.getmItemId());
			sortFilterParams.addValue("HcBottomButtonId", objUserDetails.getBottomBtnId());
			sortFilterParams.addValue("CategoryName", objUserDetails.getCatName());
			sortFilterParams.addValue("SearchKey", objUserDetails.getSrchKey());
			sortFilterParams.addValue("Postalcode", objUserDetails.getPostalCode());
			sortFilterParams.addValue("Radius", objUserDetails.getRadius());
			sortFilterParams.addValue("RetailID", objUserDetails.getRetailId());
			
			sortFilterParams.addValue("BandEventTypeID",objUserDetails.getEvtTypeID());
			sortFilterParams.addValue("BandID",objUserDetails.getBandID());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(sortFilterParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arFilterList = (ArrayList<Filter>) resultFromProcedure.get("sortAndFilterList");
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getSortFilterList " + HubCitiConstants.SCHEMANAME+ " : usp_HcRetailerFilterValueList : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FindDaoImpl : getSortFilterList " + HubCitiConstants.SCHEMANAME +  " :  " + objUserDetails.getModule()  + " :  " + e.getMessage());
			throw new HubCitiException(e);
		} 

		LOG.info("Exit FindDaoImpl : getSortFilterList");
		return arFilterList;
	}

	/**
	 * This DAO method for getting filter List Display
	 * 
	 * @param requestFilter
	 * @return response XML as String containing Filter list.
	 * @throws HubCitiException
	 */
	public Filter getInterestList(Filter requestFilter) throws HubCitiException {
		LOG.info("Inside FindDAOImpl : getInterestList");

		List<Filter> filterList = null;
		Filter objFilter = null;
		String selectedProcedureName = null;

		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.returningResultSet("filterList",
					new BeanPropertyRowMapper<Filter>(Filter.class));

			final MapSqlParameterSource filterListparams = new MapSqlParameterSource();

			// variable for selecting particular Stored procedure call
			String spSelection = requestFilter.getSpType().trim();

			if(spSelection.equalsIgnoreCase("Find Single")) {

				selectedProcedureName = "usp_HcSingleFindOptionsInterestsListDisplay";

				filterListparams.addValue("CategoryName",requestFilter.getfCategoryName());
				filterListparams.addValue("HcMenuItemID",requestFilter.getmItemId());
				filterListparams.addValue("HcBottomButtonID",requestFilter.getBottomBtnId());

			} else if(spSelection.equalsIgnoreCase("Find All")) {

				selectedProcedureName = "usp_HcFindOptionsInterestsListDisplay";
				filterListparams.addValue("CategoryName",requestFilter.getfCategoryName());
				filterListparams.addValue("HcMenuItemID",requestFilter.getmItemId());
				filterListparams.addValue("HcBottomButtonID",requestFilter.getBottomBtnId());

			} else if(spSelection.equalsIgnoreCase("CitiExp")) {
				selectedProcedureName = "usp_HcCityExperienceInterestsList";
			} else if(spSelection.equalsIgnoreCase("thislocation")) {
				selectedProcedureName = "usp_HcThisLocationOptionsInterestsListDisplay";
				filterListparams.addValue("RequestedTime",requestFilter.getRequestedTime());
				filterListparams.addValue("Radius",requestFilter.getRadius());
			} else {
				throw new HubCitiException("Inside FindDAOImpl :"+ "getInterestList"+ "For the specified Type stored procedure is not available");
			}

			filterListparams.addValue("CityExperienceID",requestFilter.getCitiExpId());
			filterListparams.addValue("CategoryID",requestFilter.getCategoryId());
			filterListparams.addValue("CityID", requestFilter.getCityId());
			filterListparams.addValue("SearchKey",requestFilter.getSearchKey());
			filterListparams.addValue("UserID", requestFilter.getUserId());
			filterListparams.addValue("HcHubCitiID",requestFilter.getHubCitiId());
			filterListparams.addValue("Latitude", requestFilter.getLatitude());
			filterListparams.addValue("Longitude", requestFilter.getLongitude());
			filterListparams.addValue("PostalCode", requestFilter.getZipCode());

			simpleJdbcCall.withProcedureName(selectedProcedureName);

			final Map<String, Object> resultFromprocedure = simpleJdbcCall.execute(filterListparams);

			if (null == resultFromprocedure.get(HubCitiConstants.ERRORNUMBER)) {

				filterList = (List<Filter>) resultFromprocedure.get("filterList");
				objFilter = new Filter();

				if (null != filterList && !filterList.isEmpty()) {
					objFilter.setFilterList(filterList);
				}

			}  else {
				final String errorMsg = (String) resultFromprocedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FindDAOImpl : " + "getInterestList" + " " + HubCitiConstants.SCHEMANAME);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException dae) {

			LOG.error("Inside FindDAOImpl : " + "getInterestList" + " "+ HubCitiConstants.SCHEMANAME + "Stored procedure : "+ selectedProcedureName + "Message : " + dae.getMessage());
			throw new HubCitiException(dae);
		} catch (Exception e) {
			LOG.error("Inside FindDAOImpl : " + "getInterestList" + " "+ HubCitiConstants.SCHEMANAME + "Stored procedure : "+ selectedProcedureName + "Message : " + e.getMessage());
			throw new HubCitiException(e);
		}
		LOG.info(ApplicationConstants.METHODEND + " Inside FindDAOImpl "+ "getInterestList");
		return objFilter;
	}

	/**
	 * This DAO method for getting Option List Display
	 * 
	 * @param requestFilter
	 * @return response XML as String containing Option list.
	 * @throws HubCitiException
	 */
	public Filter getOptionList(Filter requestFilter) throws HubCitiException {


		LOG.info(ApplicationConstants.METHODSTART + " Inside FindDAOImpl  "
				+ "getOptionList");

		List<Filter> filterList = null;
		Filter objFilter = null;
		String selectedProcedureName = null;

		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.returningResultSet("filterList",
					new BeanPropertyRowMapper<Filter>(Filter.class));

			final MapSqlParameterSource filterListparams = new MapSqlParameterSource();

			// variable for selecting particular Stored procedure call
			String spSelection = requestFilter.getSpType().trim();

			if(spSelection.equalsIgnoreCase("options")) {

				selectedProcedureName ="usp_HcRetailerFilterList";

				filterListparams.addValue("HcCityID",requestFilter.getCityId());
				filterListparams.addValue("HcMenuItemID",requestFilter.getmItemId());
				filterListparams.addValue("HcBottomButtonID",requestFilter.getBottomBtnId());
				filterListparams.addValue("BusinessCategoryID",requestFilter.getBusinessId());
				filterListparams.addValue("Radius",requestFilter.getRadius());
				filterListparams.addValue("SearchKey",requestFilter.getSearchKey());

			} else {

				LOG.info(ApplicationConstants.EXCEPTIONOCCURRED + "Inside FindDAOImpl :" + "getOptionList" + "For the specified Type stored procedure is not available");
				throw new HubCitiException(ApplicationConstants.EXCEPTIONOCCURRED + "Inside FindDAOImpl :" + "getOptionList" + "For the specified Type stored procedure is not available");

			}
			filterListparams.addValue("UserID", requestFilter.getUserId());
			filterListparams.addValue("HcHubCitiID",requestFilter.getHubCitiId());
			filterListparams.addValue("Latitude", requestFilter.getLatitude());
			filterListparams.addValue("Longitude", requestFilter.getLongitude());

			simpleJdbcCall.withProcedureName(selectedProcedureName);

			final Map<String, Object> resultFromprocedure = simpleJdbcCall.execute(filterListparams);

			if (null == resultFromprocedure.get(HubCitiConstants.ERRORNUMBER)) {
				filterList = (List<Filter>) resultFromprocedure.get("filterList");
				objFilter = new Filter();

				if (null != filterList && !filterList.isEmpty()) {
					objFilter.setFilterList(filterList);
				}

			} else {
				final String errorMsg = (String) resultFromprocedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FindDAOImpl : getOptionList" +  errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException dae) {

			LOG.error("Inside FindDAOImpl : " + "getOptionList" + " "
					+ HubCitiConstants.SCHEMANAME + "Stored procedure : "
					+ selectedProcedureName + "Message : " + dae.getMessage());
			throw new HubCitiException(dae);
		} catch (Exception e) {
			LOG.error("Inside FindDAOImpl : " + "getOptionList" + " "
					+ HubCitiConstants.SCHEMANAME + "Stored procedure : "
					+ selectedProcedureName + "Message : " + e.getMessage());
			throw new HubCitiException(e);
		}
		LOG.info(ApplicationConstants.METHODEND + " Inside FindDAOImpl "
				+ "getOptionList");

		return objFilter;
	}

	/**
	 * This DAO method for getting City Experience Category List
	 * @param requestFilter
	 * @return response XML as String containing Category List
	 * @throws HubCitiException
	 */
	public Filter getCategoryList(Filter requestFilter) throws HubCitiException {
		LOG.info(" Inside FindDAOImpl  " + "getCategoryList");

		List<CategoryInfo> categoryList = null;
		Filter objFilter = null;

		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.returningResultSet("categoryList", new BeanPropertyRowMapper<CategoryInfo>(CategoryInfo.class));

			final MapSqlParameterSource categoryListparams = new MapSqlParameterSource();


			if(requestFilter.getModuleName().equalsIgnoreCase("CitiEXP")) {

				simpleJdbcCall.withProcedureName("usp_HcCityExperienceCategoryList");

				categoryListparams.addValue("CityExperienceID",requestFilter.getCitiExpId());
				categoryListparams.addValue("CityID",requestFilter.getCityId());
			} else if(requestFilter.getModuleName().equalsIgnoreCase("filter")) {

				simpleJdbcCall.withProcedureName("usp_HcFilterCategoryList");
				categoryListparams.addValue("FilterID",requestFilter.getFilterId());
				categoryListparams.addValue("ZipCode",requestFilter.getZipCode());
			} else if (requestFilter.getModuleName().equalsIgnoreCase("events")) {
				simpleJdbcCall.withProcedureName("usp_HcEventsOptionCategoryDisplay");

				categoryListparams.addValue("RetailID",requestFilter.getRetailId());
				categoryListparams.addValue("RetailLocationID",requestFilter.getRetailLocationId());
				categoryListparams.addValue("FundRaisingID",requestFilter.getFundId());
			} else if (requestFilter.getModuleName().equalsIgnoreCase("band")) {
				simpleJdbcCall.withProcedureName("usp_HcBandSubCategoriesDisplay");
			}  else if (requestFilter.getModuleName().equalsIgnoreCase("BandEvents")) {
				simpleJdbcCall.withProcedureName("usp_HcBandEventCategoriesDisplay");
			}  else if (requestFilter.getModuleName().equalsIgnoreCase("thislocation")) {
				simpleJdbcCall.withProcedureName("usp_HcThisLocationCategoriesDisplay");
			}  else if (requestFilter.getModuleName().equalsIgnoreCase("SpecialOffers")) {
				simpleJdbcCall.withProcedureName("usp_HcSpecialOffersBusinessCategoriesList");
			} else if (requestFilter.getModuleName().equalsIgnoreCase("Coupons")) {
				simpleJdbcCall.withProcedureName("usp_HcCouponsBusinessCategoryDisplay");
			} else if (requestFilter.getModuleName().equalsIgnoreCase("myaccounts")) {
				simpleJdbcCall.withProcedureName("usp_HcCouponsGalleryBusinessCategoryDisplay");
			} else {
				LOG.info("Inside FindDAOImpl :" + "getCategoryList : For the specified Type stored procedure is not available");
				throw new HubCitiException("Inside FindDAOImpl : getCategoryList : For the specified Type stored procedure is not available");
			}

			categoryListparams.addValue("UserID", requestFilter.getUserId());
			categoryListparams.addValue("HcHubCitiID",requestFilter.getHubCitiId());
			categoryListparams.addValue("Radius",requestFilter.getRadius());
			categoryListparams.addValue("SearchKey",requestFilter.getSearchKey());
			categoryListparams.addValue("Latitude", requestFilter.getLatitude());
			categoryListparams.addValue("Longitude", requestFilter.getLongitude());
			categoryListparams.addValue("HcMenuItemID", requestFilter.getmItemId());
			categoryListparams.addValue("HcBottomButtonID", requestFilter.getBottomBtnId());
			categoryListparams.addValue("Postalcode",requestFilter.getZipCode());
			categoryListparams.addValue("BandEventTypeID",requestFilter.getEvtTypeID());
			categoryListparams.addValue("BandID",requestFilter.getBandID());
			categoryListparams.addValue("RequestedTime",requestFilter.getRequestedTime());
			categoryListparams.addValue("RetailID",requestFilter.getRetailId());
		

			final Map<String, Object> resultFromprocedure = simpleJdbcCall.execute(categoryListparams);

			if (null == resultFromprocedure.get(HubCitiConstants.ERRORNUMBER)) {
				categoryList = (List<CategoryInfo>) resultFromprocedure.get("categoryList");
				objFilter = new Filter();

				if (null != categoryList && !categoryList.isEmpty()) {
					objFilter.setCategoryList(categoryList);
				}
			} else {
				final String errorMsg = (String) resultFromprocedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FindDAOImpl : " + "getCategoryList" + " " + HubCitiConstants.SCHEMANAME);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException dae) {
			LOG.error("Inside FindDAOImpl : " + "getCategoryList" + " " + HubCitiConstants.SCHEMANAME + "Stored procedure : "+ "usp_HcCityExperienceCategoryList" + "Message : " + dae.getMessage());
			throw new HubCitiException(dae);

		} catch (Exception e) {
			LOG.error("Inside FindDAOImpl : " + "getCategoryList" + " " + HubCitiConstants.SCHEMANAME + "Stored procedure : " + "usp_HcCityExperienceCategoryList" + "Message : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FindDAOImpl  : getCategoryList");
		return objFilter;
	}




}
