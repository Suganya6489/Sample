package com.hubciti.thislocation.dao;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.BottomButton;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.CitiExperience;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.CustomerInfo;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.RetailerCreatedPages;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;
import com.hubciti.common.pojos.UserTrackingData;
import com.hubciti.common.utility.Utility;
import com.hubciti.thislocation.query.ThisLocationQuery;


@SuppressWarnings({"unchecked" })
public class ThisLocationDaoImpl implements ThisLocationDao {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ThisLocationDaoImpl.class);

	/**
	 * For JDBC connection.
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * To call stored procedue.
	 */
	private SimpleJdbcCall simpleJdbcCall;

	/**
	 * To set ParameterizedBeanPropertyRowMapper to map POJOs.
	 */
	private SimpleJdbcTemplate simpleJdbcTemplate;

	/**
	 * To get the datasource .
	 * 
	 * @param dataSource
	 *            The data source for Spring JDBC connection.
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * DAOImpl method to fetches the partners from the database for the given
	 * location.
	 * 
	 * @param iCitiExpID
	 * @return returns ThisLocationRequest object container List of partners.
	 * @throws HubCitiException
	 */
	public CitiExperience getPartners(Integer iCitiExpID, Integer userId,Integer bottomBtnId,Integer menuItemId) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getPartners");

		List<CitiExperience> partnerList = null;
		CitiExperience partnerDetails = null;
		List<BottomButton> bottomBtnList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcFetchFilters");

			simpleJdbcCall.returningResultSet("partners", new BeanPropertyRowMapper<CitiExperience>(CitiExperience.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));
			final MapSqlParameterSource partnersParam = new MapSqlParameterSource();
			partnersParam.addValue("CitiExperienceID", iCitiExpID);
			partnersParam.addValue("userID", userId);
			partnersParam.addValue("HcBottomButtonID", bottomBtnId);
			partnersParam.addValue("HcMenuItemID", menuItemId);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(partnersParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					partnerList = (List<CitiExperience>) resultFromProcedure.get("partners");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

					partnerDetails = new CitiExperience();

					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						partnerDetails.setBottomBtnList(bottomBtnList);
						partnerDetails.setBottomBtn(1);
					} else {
						partnerDetails.setBottomBtn(0);
					}

					if (partnerList != null && !partnerList.isEmpty()) {
						partnerDetails.setPartnerList(partnerList);
						Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						partnerDetails.setMaxCnt(maxCnt);
						partnerDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						partnerDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Inside ThisLocationDaoImpl : getPartners : usp_HcFetchFilters : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getPartners : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return partnerDetails;
	}

	/**
	 * DAO method to fetches the partners from the database for the given
	 * location.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	public RetailersDetails getRetailersForPartner(ThisLocationRequest thisLocationReq, String screenName) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getRetailersForPartner");

		List<RetailerDetail> retailerList = null;
		RetailersDetails retailerDetails = null;
		List<BottomButton> bottomBtnList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFilterRetailerList");
			simpleJdbcCall.returningResultSet("retailers", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource fetchRetailerDetailsParameters = new MapSqlParameterSource();

			fetchRetailerDetailsParameters.addValue(HubCitiConstants.USERID, thisLocationReq.getUserId());
			fetchRetailerDetailsParameters.addValue("HCHubCitiID", thisLocationReq.getHubCitiId());
			fetchRetailerDetailsParameters.addValue("FilterID", thisLocationReq.getRetAffId());
			fetchRetailerDetailsParameters.addValue("Longitude", thisLocationReq.getLongitude());
			fetchRetailerDetailsParameters.addValue("Latitude", thisLocationReq.getLatitude());
			fetchRetailerDetailsParameters.addValue("ZipCode", thisLocationReq.getPostalCode());
			fetchRetailerDetailsParameters.addValue("LowerLimit", thisLocationReq.getLowerLimit());
			fetchRetailerDetailsParameters.addValue("ScreenName", screenName);
			fetchRetailerDetailsParameters.addValue("CategoryID", thisLocationReq.getCatIds());
			fetchRetailerDetailsParameters.addValue("SearchKey", thisLocationReq.getSearchKey());
			fetchRetailerDetailsParameters.addValue("requestedTime",thisLocationReq.getRequestedTime());
			// For user tracking
			fetchRetailerDetailsParameters.addValue("MAINMENUID", thisLocationReq.getMainMenuId());
			fetchRetailerDetailsParameters.addValue("SortColumn", thisLocationReq.getSortColumn());
			fetchRetailerDetailsParameters.addValue("SortOrder", thisLocationReq.getSortOrder());
			//Filter Implemenation HubCiti2.1
			fetchRetailerDetailsParameters.addValue("LocalSpecials", thisLocationReq.getLocSpecials());

			// below param are output params from SP.
			fetchRetailerDetailsParameters.addValue("ErrorNumber", null);
			fetchRetailerDetailsParameters.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetailerDetailsParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					retailerList = (List<RetailerDetail>) resultFromProcedure.get("retailers");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
					final String description = (String) resultFromProcedure.get("Description");

					retailerDetails = new RetailersDetails();

					/**
					 * Filter description added 
					 * If The description contains value added CDATA for handling special characters
					 *  
					 */
					if( null != description){
						retailerDetails.setsDescription("<![CDATA["+description+"]]>");
					}


					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						retailerDetails.setBottomBtnList(bottomBtnList);
						retailerDetails.setBottomBtn(1);
					} else {
						retailerDetails.setBottomBtn(0);
					}

					if (null != retailerList && !retailerList.isEmpty()) {
						retailerDetails.setRetailerDetail(retailerList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						final String retGroupImg = (String) resultFromProcedure.get("RetailGroupButtonImagePath");

						if (nextpage != null) {
							if (nextpage) {
								retailerDetails.setNextPage(1);
							} else {
								retailerDetails.setNextPage(0);
							}
						}
						retailerDetails.setMaxCnt(maxCnt);
						retailerDetails.setRetGroupImg(retGroupImg);


						retailerDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						retailerDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					} else {
						retailerDetails.setNoRecordMsg(Utility.clobToString((Clob)resultFromProcedure.get("NoRecordsMsg")));
						return retailerDetails;
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Inside ThisLocationDaoImpl : getRetailersForPartner : usp_HcFilterRetailerList :", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getRetailersForPartner : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return retailerDetails;
	}

	/**
	 * DAO method to fetches the City Experience retailers from the database for
	 * the given location.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	public RetailersDetails getRetailersForCitiExpirence(ThisLocationRequest thisLocationReq, String screenName) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getRetailersForCitiExpirence");

		List<RetailerDetail> retailerList = null;

		List<BottomButton> bottomBtnList = null;
		RetailersDetails retailerDetails = null;

		if ("".equals(Utility.checkNull(thisLocationReq.getCityIds()))) {
			thisLocationReq.setCityIds(null);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcCityExperienceRetailerList");

			simpleJdbcCall.returningResultSet("retailers", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource retailerDetailsParam = new MapSqlParameterSource();

			retailerDetailsParam.addValue(HubCitiConstants.USERID, thisLocationReq.getUserId());
			retailerDetailsParam.addValue("HcHubCitiID", thisLocationReq.getHubCitiId());
			retailerDetailsParam.addValue("CityExperienceID", thisLocationReq.getCitiExpId());
			retailerDetailsParam.addValue("Longitude", thisLocationReq.getLongitude());
			retailerDetailsParam.addValue("Latitude", thisLocationReq.getLatitude());
			retailerDetailsParam.addValue("ZipCode", thisLocationReq.getPostalCode());
			retailerDetailsParam.addValue("LowerLimit", thisLocationReq.getLowerLimit());
			retailerDetailsParam.addValue("ScreenName", screenName);
			retailerDetailsParam.addValue("CategoryID", thisLocationReq.getCatIds());
			retailerDetailsParam.addValue("SearchKey", thisLocationReq.getSearchKey());
			retailerDetailsParam.addValue("SortColumn", thisLocationReq.getSortColumn());
			retailerDetailsParam.addValue("SortOrder", thisLocationReq.getSortOrder());
			retailerDetailsParam.addValue("requestedTime",thisLocationReq.getRequestedTime());
			// For user tracking
			retailerDetailsParam.addValue("MainMenuId", thisLocationReq.getMainMenuId());
			//Region/Area App 
			retailerDetailsParam.addValue("CityID", thisLocationReq.getCityIds());
			retailerDetailsParam.addValue("GroupColumn", thisLocationReq.getGroupBy());

			retailerDetailsParam.addValue("LocalSpecials", thisLocationReq.getLocSpecials());
			retailerDetailsParam.addValue("Interests", thisLocationReq.getInterests());
			// below param are output params from SP.
			retailerDetailsParam.addValue("ErrorNumber", null);
			retailerDetailsParam.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(retailerDetailsParam);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					retailerList = (List<RetailerDetail>) resultFromProcedure.get("retailers");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

					retailerDetails = new RetailersDetails();

					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						retailerDetails.setBottomBtnList(bottomBtnList);
						retailerDetails.setBottomBtn(1);
					} else {
						retailerDetails.setBottomBtn(0);
					}

					if (null != retailerList && !retailerList.isEmpty()) {
						retailerDetails.setRetailerDetail(retailerList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						final String retGroupImg = (String) resultFromProcedure.get("RetailGroupButtonImagePath");
						final Integer retAffCount = (Integer) resultFromProcedure.get("RetailAffiliateCount");
						final Integer retAffId = (Integer) resultFromProcedure.get("RetailAffiliateID");
						final String retAffName = (String) resultFromProcedure.get("RetailAffiliateName");

						if (nextpage != null) {
							if (nextpage) {
								retailerDetails.setNextPage(1);
							} else {
								retailerDetails.setNextPage(0);
							}
						}
						retailerDetails.setMaxCnt(maxCnt);
						retailerDetails.setRetGroupImg(retGroupImg);
						retailerDetails.setRetAffCount(retAffCount);
						retailerDetails.setRetAffId(retAffId);
						retailerDetails.setRetAffName(retAffName);
						retailerDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						retailerDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
						retailerDetails.setLastRowNum(retailerList.get(retailerList.size() -1).getRowNumber());
					} else {
						retailerDetails.setNoRecordMsg(Utility.clobToString((Clob)resultFromProcedure.get("NoRecordsMsg")));
						return retailerDetails;
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Inside ThisLocationDaoImpl : getRetailersForCitiExpirence :  usp_HcCityExperienceRetailerList :  error number: {} and error message: {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getRetailersForCitiExpirence : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}

		LOG.info("Exit ThisLocationDaoImpl : getRetailersForCitiExpirence");
		return retailerDetails;
	}

	public List<RetailerDetail> getRetailerSummary(RetailerDetail objRetailerDetail) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getRetailerSummary");

		boolean bNxtPageFlag = false;
		List<RetailerDetail> retailSummarylst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcDisplayRetailLocationInfo");
			simpleJdbcCall.returningResultSet("retailerSummaryDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", objRetailerDetail.getUserId());
			scanQueryParams.addValue("RetailID", objRetailerDetail.getRetailerId());
			scanQueryParams.addValue("RetailLocationID", objRetailerDetail.getRetailLocationId());
			scanQueryParams.addValue("Latitude", objRetailerDetail.getLatitude());
			scanQueryParams.addValue("Longitude", objRetailerDetail.getLongitude());
			scanQueryParams.addValue("HcHubCitiID", objRetailerDetail.getHubCitiId());
			// For user tracking
			scanQueryParams.addValue("MainMenuID", objRetailerDetail.getMainMenuId());
			scanQueryParams.addValue("ScanTypeID", objRetailerDetail.getScanTypeId());
			scanQueryParams.addValue("RetailerListID", objRetailerDetail.getRetListId());

			/*
			 * User Default Postal Code usage when the user is outside the scope
			 * of the given Hub Citi.
			 */
			scanQueryParams.addValue("ZipCode", objRetailerDetail.getPostalCode());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				retailSummarylst = (List<RetailerDetail>) resultFromProcedure.get("retailerSummaryDetails");
				if (null != retailSummarylst && !retailSummarylst.isEmpty()) {

					bNxtPageFlag = (Boolean) resultFromProcedure.get("claimFlag");
					if (bNxtPageFlag) {
						retailSummarylst.get(0).setClaimExist(1);
					} else {
						retailSummarylst.get(0).setClaimExist(0);
					}
					retailSummarylst.get(0).setClaimTxtMsg((String) resultFromProcedure.get("claimBusiness"));
					retailSummarylst.get(0).setMail((String) resultFromProcedure.get("UserEmail"));
				} 
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "usp_DisplayRetailLocationInfo ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getRetailerSummary : " + e);
			throw new HubCitiException(e);
		}

		return retailSummarylst;
	}

	public List<RetailerCreatedPages> getRetCreatePages(RetailerDetail objRetailerDetail) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getRetCreatePages");

		List<RetailerCreatedPages> retCreatedPageslst = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcQRDisplayRetailerCreatedPages");

			simpleJdbcCall.returningResultSet("retCreatedPages", new BeanPropertyRowMapper<RetailerCreatedPages>(RetailerCreatedPages.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", objRetailerDetail.getUserId());
			scanQueryParams.addValue("RetailID", objRetailerDetail.getRetailerId());
			scanQueryParams.addValue("RetailLocationID", objRetailerDetail.getRetailLocationId());
			scanQueryParams.addValue("AppsiteID", objRetailerDetail.getLinkId());
			scanQueryParams.addValue("HcHubCitiID", objRetailerDetail.getHubCitiId());
			// For User tracking
			scanQueryParams.addValue("MainMenuID", objRetailerDetail.getMainMenuId());
			scanQueryParams.addValue("ScanTypeID", objRetailerDetail.getScanTypeId());
			scanQueryParams.addValue("RetailerListID", objRetailerDetail.getRetListId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);


			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

				retCreatedPageslst = (List<RetailerCreatedPages>) resultFromProcedure.get("retCreatedPages");

				if (null != retCreatedPageslst && !retCreatedPageslst.isEmpty()) {

					final Boolean bEventExist = (Boolean)resultFromProcedure.get("EventExists");
					final Boolean bFundExist = (Boolean)resultFromProcedure.get("FundRaisingExists");

					if (null != bEventExist) {
						if (bEventExist) {
							retCreatedPageslst.get(0).setEventExist(1);
						} else {
							retCreatedPageslst.get(0).setEventExist(0);
						}
					}

					if (null != bFundExist) {
						if (bFundExist) {
							retCreatedPageslst.get(0).setFundExist(1);
						} else {
							retCreatedPageslst.get(0).setFundExist(0);
						}
					}
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside ThisLocationDaoImpl : getRetCreatePage : usp_QRDisplayRetailerCreatedPages ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException exception) {
			LOG.error("Inside ThisLocationDaoImpl : getRetCreatePage : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit ThisLocationDaoImpl : getRetCreatePages");
		return retCreatedPageslst;
	}

	/**
	 * The method fetches the categories belonging to partner retailers from the
	 * database.
	 * 
	 * @param objThisLocationReq
	 *            .
	 * @return returns CategoryInfo object container List of categories.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCitiException defined for
	 *             the application will be thrown which is caught in the
	 *             Controller layer.
	 */
	public List<CategoryInfo> getCategoriesForPartners(ThisLocationRequest objThisLocationReq) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getCategoriesForPartners");
		List<CategoryInfo> arCategoryInfoList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFetchAffiliateRetailerCategories");
			simpleJdbcCall.returningResultSet("affiliateRetailers", new BeanPropertyRowMapper<CategoryInfo>(CategoryInfo.class));

			final MapSqlParameterSource fetchRetDetailsParam = new MapSqlParameterSource();
			fetchRetDetailsParam.addValue("RetailAffiliateID", objThisLocationReq.getRetAffId());
			fetchRetDetailsParam.addValue("HubCitiID", objThisLocationReq.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetDetailsParam);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arCategoryInfoList = (List<CategoryInfo>) resultFromProcedure.get("affiliateRetailers");
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Inside ThisLocationDaoImpl : getCategoriesForPartners : Store Procedure error : usp_FetchAffiliateRetailerCategories",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getCategoriesForPartners : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return arCategoryInfoList;
	}

	/**
	 * The method fetches the categories belonging to group retailers from the
	 * database.
	 * 
	 * @param objThisLocationReq
	 *            .
	 * @return returns CategoryInfo object container List of categories.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCitiException defined for
	 *             the application will be thrown which is caught in the
	 *             Controller layer.
	 */
	public List<CategoryInfo> getCategoriesForGroupRetailers(ThisLocationRequest objThisLocationReq) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getCategoriesForGroupRetailers");
		List<CategoryInfo> arCategoryInfoList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFetchGroupRetaierCategories");
			simpleJdbcCall.returningResultSet("affiliateRetailers", new BeanPropertyRowMapper<CategoryInfo>(CategoryInfo.class));

			final MapSqlParameterSource fetchRetDetailsParam = new MapSqlParameterSource();
			fetchRetDetailsParam.addValue("RetailGroupID", objThisLocationReq.getRetGroupId());
			fetchRetDetailsParam.addValue("HubCitiID", objThisLocationReq.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetDetailsParam);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arCategoryInfoList = (List<CategoryInfo>) resultFromProcedure.get("affiliateRetailers");
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error(
							"Inside ThisLocationDaoImpl : getCategoriesForGroupRetailers : Store Procedure error : usp_FetchGroupRetaierCategories ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getCategoriesForGroupRetailers : Store Procedure error : usp_FetchGroupRetaierCategories : "
					+ e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return arCategoryInfoList;
	}

	/**
	 * DAOImpl Method list all see current specials details .
	 * 
	 * @param productDetailsRequest instance of ProductDetailsRequest.
	 * @return ProductDetails details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public ProductDetails getRetailerSpecialDeals(ProductDetailsRequest productDetailsRequest) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getRetailerSpecialDeals");

		ProductDetails productDetailsObj = null;
		List<RetailerDetail> specialOfferlst = null;
		RetailerDetail retailDetail = new RetailerDetail();

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcRetailLocationSpecialDealsDisplay");
			simpleJdbcCall.returningResultSet("specialofferdiscounts", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", productDetailsRequest.getUserId());
			scanQueryParams.addValue("RetailID", productDetailsRequest.getRetailId());
			scanQueryParams.addValue("RetailLocationID", productDetailsRequest.getRetailLocationId());
			scanQueryParams.addValue("HcHubCitiID", productDetailsRequest.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			specialOfferlst = (List<RetailerDetail>) resultFromProcedure.get("specialofferdiscounts");

			specialOfferlst = (List<RetailerDetail>) resultFromProcedure.get("specialofferdiscounts");
			specialOfferlst = (List<RetailerDetail>) resultFromProcedure.get("specialofferdiscounts");


			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					if (null != specialOfferlst && !specialOfferlst.isEmpty()) {


						productDetailsObj = new ProductDetails();
						retailDetail = new RetailerDetail();
						String value = null;

						for (int i = 0; i < specialOfferlst.size(); i++) {
							if ("".equals(Utility.checkNull(value))) {
								value =  specialOfferlst.get(i).getSpecialNames() + HubCitiConstants.COMMA;
							} else {
								value = value + specialOfferlst.get(i).getSpecialNames() + HubCitiConstants.COMMA;
							}
						}

						specialOfferlst = new ArrayList<RetailerDetail>();

						retailDetail.setSpecialNames(value.substring(0, value.length() - 1));

						specialOfferlst.add(0, retailDetail);
						productDetailsObj.setSpecialOfferlst(specialOfferlst);
						productDetailsObj.setmColor((String)resultFromProcedure.get("MenuColor"));
						productDetailsObj.setmFontColor((String)resultFromProcedure.get("MenuFontColor"));
					}
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside ThisLocationDaoImpl : getRetailerSpecialDeals : usp_HcRetailLocationSpecialDealsDisplay : errorNum : " + errorNum + " : errorMsg  : " + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException exception) {
			LOG.error("Inside ThisLocationDaoImpl : getRetailerSpecialDeals : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit ThisLocationDaoImpl : getRetailerSpecialDeals");
		return productDetailsObj;
	}


	public List<HotDealsDetails> getRetailerHotDeals(RetailerDetail retailerDetailObj) throws HubCitiException {
		final String methodName = "getRetailerHotDeals";
		if (LOG.isDebugEnabled()) {
			LOG.debug(HubCitiConstants.METHODSTART + methodName + "requested user id is -->" + retailerDetailObj.getUserId());
		}
		List<HotDealsDetails> hotDealsDetailslst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetailLocationHotDealsDisplay");
			simpleJdbcCall.returningResultSet("hotDeals", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));
			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", retailerDetailObj.getUserId());
			scanQueryParams.addValue("RetailID", retailerDetailObj.getRetailerId());
			scanQueryParams.addValue("RetailLocationID", retailerDetailObj.getRetailLocationId());
			scanQueryParams.addValue("ScreenName", HubCitiConstants.RETAILERHOTDEALS);
			scanQueryParams.addValue("MainMenuID", retailerDetailObj.getMainMenuId());
			scanQueryParams.addValue("HcHubCitiID", retailerDetailObj.getHubCitiId());
			if (retailerDetailObj.getLastVisitedNo() == null) {
				retailerDetailObj.setLastVisitedNo(0);
			}

			scanQueryParams.addValue("LowerLimit", retailerDetailObj.getLastVisitedNo());
			// For user tracking
			/*
			 * scanQueryParams.addValue(HubCitiConstants.RETAILERLISTID,
			 * retailerDetailObj.getRetListId());
			 * scanQueryParams.addValue(HubCitiConstants.MAINMENUID,
			 * retailerDetailObj.getMainMenuId());
			 */

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			hotDealsDetailslst = (List<HotDealsDetails>) resultFromProcedure.get("hotDeals");

			if (null != resultFromProcedure.get("ErrorNumber")) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "usp_HcRetailLocationHotDealsDisplay ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in getRetailerHotDeals", exception);
			throw new HubCitiException(exception);
		}
		return hotDealsDetailslst;
	}

	public List<CouponDetails> getRetailerLocationCoupon(RetailerDetail retailerDetailObj) throws HubCitiException {
		final String methodName = "getRetailerHotDeals";
		if (LOG.isDebugEnabled()) {
			LOG.debug(HubCitiConstants.METHODSTART + methodName + "requested user id is -->" + retailerDetailObj.getUserId());
		}
		List<CouponDetails> couponList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetailLocationCouponsDisplay");
			simpleJdbcCall.returningResultSet("coupons", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));
			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", retailerDetailObj.getUserId());
			scanQueryParams.addValue("RetailID", retailerDetailObj.getRetailerId());
			scanQueryParams.addValue("RetailLocationID", retailerDetailObj.getRetailLocationId());
			scanQueryParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			scanQueryParams.addValue("MainMenuID", retailerDetailObj.getMainMenuId());
			scanQueryParams.addValue("RetailerListID", retailerDetailObj.getRetListId());
			scanQueryParams.addValue("HcHubCitiID", retailerDetailObj.getHubCitiId());
			if (retailerDetailObj.getLastVisitedNo() == null) {
				retailerDetailObj.setLastVisitedNo(0);
			}
			scanQueryParams.addValue("LowerLimit", retailerDetailObj.getLastVisitedNo());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			couponList = (List<CouponDetails>) resultFromProcedure.get("coupons");

			if (null != resultFromProcedure.get("ErrorNumber")) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "usp_HcRetailLocationCouponsDisplay ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in getRetailerLocationCoupon", exception);
			throw new HubCitiException(exception);
		}
		return couponList;
	}

	/**
	 * The method fetches the Product Details.
	 * 
	 * @param productDetailsRequest
	 *            instance of ProductDetailsRequest.
	 * @param screenName
	 *            as request parameter.
	 * @return ProductDetails returns list of Products.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public ProductDetails fetchProductDetails(ProductDetailsRequest productDetailsRequest, String screenName) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : fetchProductDetails");
		ProductDetails productDetails = null;
		List<ProductDetail> arProductDetailList = null;
		Integer iMaxCount = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetailLocationSalesDisplay");
			simpleJdbcCall.returningResultSet("productDetails", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));
			final MapSqlParameterSource fetchProductDetailsParameters = new MapSqlParameterSource();
			fetchProductDetailsParameters.addValue("UserID", productDetailsRequest.getUserId());
			fetchProductDetailsParameters.addValue("HubCitiID", productDetailsRequest.getHubCitiId());
			fetchProductDetailsParameters.addValue("RetailLocationID", productDetailsRequest.getRetailLocationId());
			fetchProductDetailsParameters.addValue("RetailerID", productDetailsRequest.getRetailId());
			// need to be implemented
			fetchProductDetailsParameters.addValue("ScreenName", screenName);
			if (null == productDetailsRequest.getLastVisitedProductNo()) {
				productDetailsRequest.setLastVisitedProductNo(0);
			}
			fetchProductDetailsParameters.addValue("LowerLimit", productDetailsRequest.getLastVisitedProductNo());
			// For user tracking
			fetchProductDetailsParameters.addValue("RetailerListID", productDetailsRequest.getRetListId());
			fetchProductDetailsParameters.addValue("MainMenuID", productDetailsRequest.getMainMenuId());
			// below param are output params from SP.
			fetchProductDetailsParameters.addValue("ErrorNumber", null);
			fetchProductDetailsParameters.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchProductDetailsParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arProductDetailList = (List<ProductDetail>) resultFromProcedure.get("productDetails");
					if (null != arProductDetailList && !arProductDetailList.isEmpty()) {
						productDetails = new ProductDetails();
						productDetails.setProductDetail(arProductDetailList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
						productDetails.setMaxCount(iMaxCount);
						if (nextpage != null) {
							if (nextpage) {
								productDetails.setNextPage(1);
							} else {
								productDetails.setNextPage(0);
							}
						}
					} else {
						return productDetails;
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error(
							"Inside ThisLocationDaoImpl : getCategoriesForGroupRetailers : Store Procedure error : usp_HcRetailLocationSalesDisplay ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : fetchProductDetails : Store Procedure error : usp_HcRetailLocationSalesDisplay : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return productDetails;
	}

	/**
	 * This DAOImpl method for fetching special offer details.
	 * 
	 * @param objRetDetail
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */

	public RetailersDetails fetchSpecialOffers(RetailerDetail objRetDetail) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : fetchSpecialOffers");
		RetailersDetails retailersDetails = null;
		List<RetailerDetail> arRetailerDetailList = null;
		
		Integer iMaxCount = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetailerLocationSpecialOffersDisplay");
			simpleJdbcCall.returningResultSet("retailerStoreDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource specialOffersParams = new MapSqlParameterSource();
			specialOffersParams.addValue("UserID", objRetDetail.getUserId());
			specialOffersParams.addValue("RetailID", objRetDetail.getRetailerId());
			specialOffersParams.addValue("RetailLocationID", objRetDetail.getRetailLocationId());
			if (null == objRetDetail.getLastVisitedNo()) {
				objRetDetail.setLastVisitedNo(0);
			}
			specialOffersParams.addValue("LowerLimit", objRetDetail.getLastVisitedNo());
			specialOffersParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			// For user tracking
			specialOffersParams.addValue("RetailerListID", objRetDetail.getRetListId());
			specialOffersParams.addValue("HcHubCitiID", objRetDetail.getHubCitiId());
			
			specialOffersParams.addValue("Longitude", objRetDetail.getLongitude());
			specialOffersParams.addValue("Latitude", objRetDetail.getLatitude());
			specialOffersParams.addValue("PostalCode", objRetDetail.getPostalCode());
			specialOffersParams.addValue("SearchKey", objRetDetail.getSearchKey());
			
			specialOffersParams.addValue("SortColumn", objRetDetail.getSortColumn());
			specialOffersParams.addValue("SortOrder", objRetDetail.getSortOrder());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specialOffersParams);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arRetailerDetailList = (List<RetailerDetail>) resultFromProcedure.get("retailerStoreDetails");
					if (null != arRetailerDetailList && !arRetailerDetailList.isEmpty()) {
						retailersDetails = new RetailersDetails();
						retailersDetails.setRetailerDetail(arRetailerDetailList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
						retailersDetails.setMaxCnt(iMaxCount);
						if (nextpage != null) {
							if (nextpage) {
								retailersDetails.setNextPage(1);
							} else {
								retailersDetails.setNextPage(0);
							}
						}
						retailersDetails.setMaxRowNum(arRetailerDetailList.get(arRetailerDetailList.size() - 1).getRowNum());
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error(
							"Inside ThisLocationDaoImpl : fetchSpecialOffers : Store Procedure error : usp_HcRetailerLocationSpecialOffersDisplay ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : fetchSpecialOffers : Store Procedure error : usp_HcRetailerLocationSpecialOffersDisplay : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return retailersDetails;
	}

	/**
	 * This DAOImpl method for fetching retailer Special Offer Details list.
	 * 
	 * @param specialOfferRequest
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public List<RetailerDetail> fetchSpecialDealsDetails(RetailerDetail specialOfferRequest) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : fetchSpecialOffers");
		List<RetailerDetail> arSpecialOfferList = null;
		RetailerDetail objRetailerDetail = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiRetailerLocationSpecialOffersDetails");
			simpleJdbcCall.returningResultSet("specialOfferDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource specOfferDetParams = new MapSqlParameterSource();
			specOfferDetParams.addValue("PageID", specialOfferRequest.getPageId());
			specOfferDetParams.addValue("RetailID", specialOfferRequest.getRetailerId());
			specOfferDetParams.addValue("RetailLocationID", specialOfferRequest.getRetailLocationId());
			specOfferDetParams.addValue("HcHubCitiID", specialOfferRequest.getHubCitiId());
			specOfferDetParams.addValue("ScanTypeID", specialOfferRequest.getScanTypeId());
			specOfferDetParams.addValue("MainMenuID", specialOfferRequest.getMainMenuId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specOfferDetParams);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arSpecialOfferList = (List<RetailerDetail>) resultFromProcedure.get("specialOfferDetails");
					final Boolean bExtLinkFlag = (Boolean) resultFromProcedure.get("ExternalLinkFlag");
					final String strExtLink = (String) resultFromProcedure.get("ExternalLink");
					if (null != arSpecialOfferList && !arSpecialOfferList.isEmpty()) {
						arSpecialOfferList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
						arSpecialOfferList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
						if (null != bExtLinkFlag) {
							if (bExtLinkFlag) {
								arSpecialOfferList.get(0).setExtLinkFlag(1);
								arSpecialOfferList.get(0).setExternalLink(strExtLink);
							} else {
								arSpecialOfferList.get(0).setExtLinkFlag(0);
							}
						}
					} else {
						arSpecialOfferList = new ArrayList<RetailerDetail>();
						objRetailerDetail = new RetailerDetail();
						objRetailerDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objRetailerDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
						if (bExtLinkFlag) {
							objRetailerDetail.setExtLinkFlag(1);
							objRetailerDetail.setExternalLink(strExtLink);
						} else {
							objRetailerDetail.setExtLinkFlag(0);
						}
						arSpecialOfferList.add(objRetailerDetail);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error(
							"Inside ThisLocationDaoImpl : fetchSpecialOffers : Store Procedure error : usp_HcHubCitiRetailerLocationSpecialOffersDetails ",
							errorNum, errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : fetchSpecialOffers : Store Procedure error : usp_HcHubCitiRetailerLocationSpecialOffersDetails : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return arSpecialOfferList;
	}

	/**
	 * This DAOImpl method for display retailer summary anything page details.
	 * 
	 * @param objAnyThing
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public List<RetailerDetail> getRetSumAnyThingDetails(RetailerDetail objAnyThing) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getRetSumAnyThingDetails");
		List<RetailerDetail> arSpecialOfferList = null;
		RetailerDetail objRetailerDetail = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HCRetailerAnythingPageDetails");
			simpleJdbcCall.returningResultSet("anythingPageDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource specOfferDetParams = new MapSqlParameterSource();
			specOfferDetParams.addValue("PageID", objAnyThing.getPageId());
			specOfferDetParams.addValue("UserID", objAnyThing.getUserId());
			specOfferDetParams.addValue("RetailID", objAnyThing.getRetailerId());
			specOfferDetParams.addValue("RetailLocationID", objAnyThing.getRetailLocationId());
			specOfferDetParams.addValue("HcHubCitiID", objAnyThing.getHubCitiId());
			specOfferDetParams.addValue("ScanTypeID", objAnyThing.getScanTypeId());
			specOfferDetParams.addValue("MainMenuID", objAnyThing.getMainMenuId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specOfferDetParams);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arSpecialOfferList = (List<RetailerDetail>) resultFromProcedure.get("anythingPageDetails");
					final Boolean bExtLinkFlag = (Boolean) resultFromProcedure.get("ExternalLinkFlag");
					final String strExtLink = (String) resultFromProcedure.get("ExternalLink");
					if (null != arSpecialOfferList && !arSpecialOfferList.isEmpty()) {
						arSpecialOfferList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
						arSpecialOfferList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
						if (null != bExtLinkFlag) {
							if (bExtLinkFlag) {
								arSpecialOfferList.get(0).setExtLinkFlag(1);
								arSpecialOfferList.get(0).setExternalLink(strExtLink);
							} else {
								arSpecialOfferList.get(0).setExtLinkFlag(0);
							}
						}
					} else {
						arSpecialOfferList = new ArrayList<RetailerDetail>();
						objRetailerDetail = new RetailerDetail();
						objRetailerDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objRetailerDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
						if (bExtLinkFlag) {
							objRetailerDetail.setExtLinkFlag(1);
							objRetailerDetail.setExternalLink(strExtLink);
						} else {
							objRetailerDetail.setExtLinkFlag(0);
						}
						arSpecialOfferList.add(objRetailerDetail);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ThisLocationDaoImpl : getRetSumAnyThingDetails : Store Procedure error : usp_HCRetailerAnythingPageDetails ",
							errorNum, errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getRetSumAnyThingDetails : Store Procedure error : usp_HCRetailerAnythingPageDetails : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return arSpecialOfferList;
	}

	/**
	 * For user tracking.
	 * 
	 * @param userTrackingData
	 * @return xml response containing SUCCESS or FAILURE.
	 * @throws HubCitiException
	 */
	public String userTrackingRetailerSummaryClick(UserTrackingData userTrackingData) throws HubCitiException {
		final String methodName = "userTrackingLocationDetails in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		Boolean bValue = false;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HCUserTrackingRetailerSummaryClicks");
			simpleJdbcCall.returningResultSet("retailers", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource userTrackLocationDetails = new MapSqlParameterSource();

			userTrackLocationDetails.addValue("RetailerDetailsID", userTrackingData.getRetDetailsId());
			userTrackLocationDetails.addValue("AnythingPageListID", userTrackingData.getAnythingPageListId());
			userTrackLocationDetails.addValue("RetailerListID", userTrackingData.getRetListId());
			if (userTrackingData.getBanADClick() == null) {
				userTrackingData.setBanADClick(bValue);
			}
			userTrackLocationDetails.addValue("BannerADClick", userTrackingData.getBanADClick());
			if (userTrackingData.getCallStoreClick() == null) {
				userTrackingData.setCallStoreClick(bValue);
			}
			userTrackLocationDetails.addValue("CallStoreClick", userTrackingData.getCallStoreClick());
			if (userTrackingData.getBrowseWebClick() == null) {
				userTrackingData.setBrowseWebClick(bValue);
			}
			userTrackLocationDetails.addValue("BrowseWebsiteClick", userTrackingData.getBrowseWebClick());
			if (userTrackingData.getGetDirClick() == null) {
				userTrackingData.setGetDirClick(bValue);
			}
			userTrackLocationDetails.addValue("GetDirectionsClick", userTrackingData.getGetDirClick());
			if (userTrackingData.getAnythingPageClick() == null) {
				userTrackingData.setAnythingPageClick(bValue);
			}
			userTrackLocationDetails.addValue("AnythingPageClick", userTrackingData.getAnythingPageClick());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(userTrackLocationDetails);

			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					if (status == 0) {
						response = HubCitiConstants.SUCCESSTEXT;
					} else {
						response = HubCitiConstants.FAILURE;
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Error occurred in usp_UserTrackingRetailerSummaryClicks Store Procedure error number: {} and error message: {}",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}

		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.ERROROCCURRED + methodName, e);
			throw new HubCitiException(e.getMessage());
		}

		LOG.info(HubCitiConstants.METHODEND + methodName);

		return response;
	}

	/**
	 * This DAOImpl method for display Coupon details.
	 * 
	 * @param objCouponDetail
	 *            instance of RetailerDetail.
	 * @return CouponsDetails details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public CouponDetails fetchCouponDetails(RetailerDetail objCouponDetail) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : fetchCouponDetails");
		List<CouponDetails> arCouponDetlist = null;
		CouponDetails couponInfo = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiGalleryCouponsDetails");
			simpleJdbcCall.returningResultSet("CouponDetails", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource fetchCouponParam = new MapSqlParameterSource();
			fetchCouponParam.addValue("Userid", objCouponDetail.getUserId());
			fetchCouponParam.addValue("Couponid", objCouponDetail.getCouponId());
			fetchCouponParam.addValue("HcHubCitiID", objCouponDetail.getHubCitiId());
			// for user tracking
			fetchCouponParam.addValue("CouponListID", objCouponDetail.getCouponListId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCouponParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arCouponDetlist = (List<CouponDetails>) resultFromProcedure.get("CouponDetails");
					if (null != arCouponDetlist && !arCouponDetlist.isEmpty()) {
						couponInfo = (CouponDetails) arCouponDetlist.get(0);
						couponInfo.setResponseCode(HubCitiConstants.SUCCESSCODE);
						couponInfo.setResponseText(HubCitiConstants.SUCCESSTEXT);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ThisLocationDaoImpl : fetchCouponDetails : Store Procedure error : usp_HcHubCitiGalleryCouponsDetails ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : fetchCouponDetails : Store Procedure error : usp_HcHubCitiGalleryCouponsDetails : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return couponInfo;
	}

	/**
	 * This DAOImpl method for display HubCiti anything page details.
	 * 
	 * @param objAnyThing
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public List<RetailerDetail> getHubCitiAnyThingDetails(RetailerDetail objAnyThing) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getHubCitiAnyThingDetails");
		List<RetailerDetail> arSpecialOfferList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcAnythingPageDetails");
			simpleJdbcCall.returningResultSet("anythingPageDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource specOfferDetParams = new MapSqlParameterSource();
			specOfferDetParams.addValue("AnythingPageID", objAnyThing.getPageId());
			specOfferDetParams.addValue("HcHubCitiID", objAnyThing.getHubCitiId());
			specOfferDetParams.addValue("MainMenuID", objAnyThing.getMainMenuId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specOfferDetParams);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arSpecialOfferList = (List<RetailerDetail>) resultFromProcedure.get("anythingPageDetails");
					if (null != arSpecialOfferList && !arSpecialOfferList.isEmpty()) {
						arSpecialOfferList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
						arSpecialOfferList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ThisLocationDaoImpl : getHubCitiAnyThingDetails : Store Procedure error : usp_HcAnythingPageDetails ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getHubCitiAnyThingDetails : Store Procedure error : usp_HcAnythingPageDetails : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return arSpecialOfferList;
	}

	/**
	 * This method is used to fetch the Latitude and Longitude for the given
	 * Zipcode.
	 * 
	 * @param zipcode
	 *            Zipcode for getting Lat and Long.
	 * @return ThisLocationRequest Container lat and long.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public ThisLocationRequest fetchLatLong(Long zipcode) throws HubCitiException {
		final String methodName = "fetchLatLong in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);

		List<ThisLocationRequest> thisLocationRequestLst = null;
		ThisLocationRequest thisLocationRequestObj = null;

		try {
			simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
			thisLocationRequestLst = simpleJdbcTemplate.query(ThisLocationQuery.FETCHLATLONG, new BeanPropertyRowMapper<ThisLocationRequest>(
					ThisLocationRequest.class), zipcode);

			if (!thisLocationRequestLst.isEmpty() && thisLocationRequestLst != null) {
				thisLocationRequestObj = new ThisLocationRequest();
				thisLocationRequestObj = thisLocationRequestLst.get(0);
			}
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.ERROROCCURRED + methodName, exception);
			throw new HubCitiException(exception.getMessage());
		}
		return thisLocationRequestObj;
	}

	/**
	 * This DAOImpl method fetches the Retailer Details from the database for
	 * the given search parameters(Zipcode or Latitude and longitude).
	 * 
	 * @param thisLocationRequest
	 *            instance of ThisLocationRequest.
	 * @param screenName
	 *            screenName to be used for Pagination size.
	 * @return RetailersDetails returns RetailerDetails object container List of
	 *         retailers.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public RetailersDetails fetchRetailerDetails(ThisLocationRequest thisLocationRequest, String screenName) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : fetchRetailerDetails");

		List<RetailerDetail> retailerList = null;
		RetailersDetails retailerDetails = null;
		List<BottomButton> bottomBtnList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFetchRetailerListPagination");
			simpleJdbcCall.returningResultSet("retailers", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource fetchRetailerDetailsParam = new MapSqlParameterSource();

			fetchRetailerDetailsParam.addValue("UserID", thisLocationRequest.getUserId());
			fetchRetailerDetailsParam.addValue("Radius", thisLocationRequest.getPreferredRadius());
			fetchRetailerDetailsParam.addValue("Longitude", thisLocationRequest.getLongitude());
			fetchRetailerDetailsParam.addValue("Latitude", thisLocationRequest.getLatitude());

			fetchRetailerDetailsParam.addValue("LowerLimit", thisLocationRequest.getLastVisitedRecord());
			fetchRetailerDetailsParam.addValue("ScreenName", screenName);
			fetchRetailerDetailsParam.addValue("HcHubcitiID", thisLocationRequest.getHubCitiId());
			// For user tracking
			fetchRetailerDetailsParam.addValue("Locateonmap", thisLocationRequest.getLocOnMap());
			fetchRetailerDetailsParam.addValue("MainMenuID", thisLocationRequest.getMainMenuId());
			fetchRetailerDetailsParam.addValue("requestedTime",thisLocationRequest.getRequestedTime());
			/*
			 * User Default Postal Code usage when the user is outside the scope
			 * of the given Hub Citi.
			 */
			fetchRetailerDetailsParam.addValue("ZipCode", thisLocationRequest.getZipcode());

			fetchRetailerDetailsParam.addValue("SortColumn", thisLocationRequest.getSortColumn());
			fetchRetailerDetailsParam.addValue("SortOrder", thisLocationRequest.getSortOrder());

			// below param are output params from SP.
			fetchRetailerDetailsParam.addValue("ErrorNumber", null);
			fetchRetailerDetailsParam.addValue("ErrorMessage", null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetailerDetailsParam);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					retailerList = (List<RetailerDetail>) resultFromProcedure.get("retailers");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

					retailerDetails = new RetailersDetails();

					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						retailerDetails.setBottomBtnList(bottomBtnList);
						retailerDetails.setBottomBtn(1);
					} else {
						retailerDetails.setBottomBtn(0);
					}

					if (null != retailerList && !retailerList.isEmpty()) {
						retailerDetails.setRetailerDetail(retailerList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						if (nextpage != null) {
							if (nextpage) {
								retailerDetails.setNextPage(1);
							} else {
								retailerDetails.setNextPage(0);
							}
						}
						if (null != maxCnt) {
							retailerDetails.setMaxCnt(maxCnt);
						}
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Inside ThisLocationDaoImpl : fetchRetailerDetails : Store Procedure error : usp_HcFetchRetailerListPagination ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : fetchRetailerDetails : Store Procedure error : usp_HcFetchRetailerListPagination : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return retailerDetails;
	}

	public List<RetailerDetail> getAppSiteDetails(RetailerDetail retailerDetailObj) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getAppSiteDetails");

		boolean bNxtPageFlag = false;
		List<RetailerDetail> retailAppsiteList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiAppSiteDetails");
			simpleJdbcCall.returningResultSet("appsiteDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("HcUserID", retailerDetailObj.getUserId());
			scanQueryParams.addValue("HubCitiID", retailerDetailObj.getHubCitiId());
			scanQueryParams.addValue("AppsiteID", retailerDetailObj.getLinkId());
			scanQueryParams.addValue("Latitude", retailerDetailObj.getLatitude());
			scanQueryParams.addValue("Longitude", retailerDetailObj.getLongitude());
			scanQueryParams.addValue("postalcode", retailerDetailObj.getPostalCode());
			scanQueryParams.addValue("MainMenuID", retailerDetailObj.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				
				retailAppsiteList = (List<RetailerDetail>) resultFromProcedure.get("appsiteDetails");
				
				if (null != retailAppsiteList && !retailAppsiteList.isEmpty()) {
					
					bNxtPageFlag = (Boolean) resultFromProcedure.get("claimFlag");
					if (bNxtPageFlag) {
						retailAppsiteList.get(0).setClaimExist(1);
					} else {
						retailAppsiteList.get(0).setClaimExist(0);
					}
					retailAppsiteList.get(0).setClaimTxtMsg((String) resultFromProcedure.get("claimBusiness"));
					retailAppsiteList.get(0).setMail((String) resultFromProcedure.get("UserEmail"));
				} 
				
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "usp_HcHubCitiAppSiteDetails ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside ThisLocationDaoImpl : getAppSiteDetails : ", exception);
			throw new HubCitiException(exception);
		}
		LOG.info("Inside ThisLocationDaoImpl : getAppSiteDetails");
		return retailAppsiteList;
	}

	/**
	 * This DAOImpl method fetches Location details from the database for the
	 * given user.
	 * 
	 * @param userID
	 *            , The userID in the request.
	 * @return ThisLocationRequest info contains Latitude and Longitude.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ThisLocationRequest fetchUserLocationDetails(Integer userID) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : fetchUserLocationDetails");
		List<ThisLocationRequest> thisLocationRequestLst = null;
		ThisLocationRequest thisLocationRequestObj = null;
		try {
			simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
			thisLocationRequestLst = simpleJdbcTemplate.query(ThisLocationQuery.FETCHUSERLOCATIONPOINTSQUERY,
					new BeanPropertyRowMapper<ThisLocationRequest>(ThisLocationRequest.class), userID);
			if (!thisLocationRequestLst.isEmpty() && null != thisLocationRequestLst) {
				thisLocationRequestObj = new ThisLocationRequest();
				thisLocationRequestObj.setLatitude(thisLocationRequestLst.get(0).getLatitude());
				thisLocationRequestObj.setLongitude(thisLocationRequestLst.get(0).getLongitude());
				thisLocationRequestObj.setPostalCode(thisLocationRequestLst.get(0).getPostalCode());
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : fetchUserLocationDetails : Query error : FETCHUSERLOCATIONPOINTSQUERY : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return thisLocationRequestObj;
	}

	/**
	 * This method for update user zip code information .
	 * 
	 * @param userId
	 * @param zipcode
	 * @return String with success or failure resposne.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String updateZipcode(Long userId, String zipcode, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : updateZipcode");
		Integer fromProc;
		Map<String, Object> resultFromProcedure = null;
		String response = null;
		Boolean validZipcode = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserPostalcodeUpdation");
			final MapSqlParameterSource postalCodeParam = new MapSqlParameterSource();
			postalCodeParam.addValue("HcUserID", userId);
			postalCodeParam.addValue("PostalCode", zipcode);
			postalCodeParam.addValue("HubCitiID", hubCitiId);
			postalCodeParam.addValue("Latitude", null);
			postalCodeParam.addValue("Longitude", null);
			resultFromProcedure = simpleJdbcCall.execute(postalCodeParam);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (fromProc == 0) {
				// response = ApplicationConstants.SUCCESS;
				validZipcode = (Boolean) resultFromProcedure.get("InvalidPostalCode");
				String defaultPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");
				if (validZipcode != null) {
					if (validZipcode) {
						response = HubCitiConstants.INVALIDZIPCODE;
					} else {
						if (defaultPostalCode != null) {
							response = defaultPostalCode;
						} else {
							response = HubCitiConstants.ZIPCODEUPDATED;
						}
					}
				}
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
				LOG.error("Inside ThisLocationDaoImpl : updateZipcode : Store Procedure error : usp_HcFetchRetailerListPagination ", errorNum,
						errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : fetchRetailerDetails : Store Procedure error : usp_HcUserPostalcodeUpdation : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return response;
	}


	/**
	 * The DAO method to get All Retailer Location List
	 * 
	 * @param requestRetailer
	 * @return returns retailer Location List
	 * @throws HubCitiException
	 * 			throws Exception
	 */
	public RetailerDetail getSpecialOfferRetLocList(RetailerDetail requestRetailer) throws HubCitiException {

		LOG.info("Inside ThisLocationDaoImpl : getSpecialOfferRetLocList");

		List<RetailerDetail>  retailerList = null;
		RetailerDetail retailerDetails =  null;

		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSpecialOfferRetailLocationsList");
			simpleJdbcCall.returningResultSet("retailerList",new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource queryParams = new MapSqlParameterSource();

			queryParams.addValue("HcUserID",requestRetailer.getUserId());
			queryParams.addValue("PageID",requestRetailer.getPageId());
			/*queryParams.addValue("Latitude", requestRetailer.getLatitude());
			queryParams.addValue("Longitude", requestRetailer.getLongitude());
			queryParams.addValue("PostalCode", requestRetailer.getPostalCode());
			 */queryParams.addValue("HcHubCitiID", requestRetailer.getHubCitiId());
			 queryParams.addValue("RetailID",requestRetailer.getRetailerId());
			 queryParams.addValue("LowerLimit", requestRetailer.getLowerLimit());
			 queryParams.addValue("ScreenName",HubCitiConstants.HUBCITIAPP);

			 final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(queryParams);

			 if(null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

				 retailerList = (List<RetailerDetail>) resultFromProcedure.get("retailerList");
				 Integer maxCount = (Integer)resultFromProcedure.get("MaxCnt");
				 Boolean nextPageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");

				 retailerDetails = new RetailerDetail();

				 retailerDetails.setRetDetailList(retailerList);
				 retailerDetails.setMaxCount(maxCount);
				 retailerDetails.setNextPageFlag(nextPageFlag);

			 }else {

				 final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				 final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				 LOG.info(HubCitiConstants.ERROROCCURRED + "usp_HcSpecialOfferRetailLocationsList ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				 throw new HubCitiException(errorMsg);
			 }

		}catch(DataAccessException dae) {
			LOG.error("Exception occurred in getSpecialOfferRetLocList  :", dae);
			throw new HubCitiException(dae);				
		}


		LOG.info("Exit ThisLocationDaoImpl : getSpecialOfferRetLocList");

		return retailerDetails;
	}


	/**
	 * The DAO method to get Special Offer Details
	 * 
	 * @param request includes retailer details
	 * @return returns special offer details
	 * @throws HubCitiException
	 * 			throws Exception
	 */
	public RetailerDetail getSpecialOfferDetails(RetailerDetail requestretailerDetails) throws HubCitiException {

		LOG.info("  Inside ThisLocationDaoImpl : getSpecialOfferDetails ");

		RetailerDetail specialOfferDetails = null;
		List<RetailerDetail> specialOfferList = null;
		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetailerLocationSpecialOffersDetails");
			simpleJdbcCall.returningResultSet("specialoffers",new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource queryParams = new MapSqlParameterSource();

			queryParams.addValue("HcUserID",requestretailerDetails.getUserId());
			queryParams.addValue("RetailID",requestretailerDetails.getRetailerId());
			queryParams.addValue("RetailLocationID",requestretailerDetails.getRetailLocationId());
			queryParams.addValue("PageID", requestretailerDetails.getPageId());
			queryParams.addValue("HubCitiID", requestretailerDetails.getHubCitiId());
			queryParams.addValue("ScanTypeID", requestretailerDetails.getScanTypeId());
			queryParams.addValue("MainMenuID", requestretailerDetails.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(queryParams);

			if(null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

				specialOfferDetails = new RetailerDetail();
				specialOfferList = (List<RetailerDetail>) resultFromProcedure.get("specialoffers");
				Boolean extLinkFlag = (Boolean)resultFromProcedure.get("ExternalLinkFlag");
				String externalLink = (String) resultFromProcedure.get("ExternalLink");

				if(null != extLinkFlag) {
					specialOfferDetails.setExtLinkFlag((extLinkFlag)?1:0);
				}
				specialOfferDetails.setExternalLink(externalLink);
				specialOfferDetails.setRetDetailList(specialOfferList);

			} else {

				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(HubCitiConstants.ERROROCCURRED + "getSpecialOfferDetails ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch(DataAccessException dae) {
			LOG.error("Inside ThisLocationDaoImpl : getSpecialOfferDetails :", dae);
			throw new HubCitiException(dae);				
		}

		LOG.info("Exit  ThisLocationDaoImpl : getSpecialOfferDetails ");
		return specialOfferDetails;

	}



	/**
	 * DAOImpl method Fetching App claim Configuration details.
	 * 
	 * @return SMTP details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ArrayList<AppConfiguration> getClaimAppConfig(String configType,  Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getClaimAppConfig");

		ArrayList<AppConfiguration> appConfigurationList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_GetClaimScreenContent");
			simpleJdbcCall.returningResultSet("AppClaimConfigurationList", new BeanPropertyRowMapper<AppConfiguration>(AppConfiguration.class));
			final MapSqlParameterSource appConfigParams = new MapSqlParameterSource();
			appConfigParams.addValue("ConfigurationType", configType);
			appConfigParams.addValue("HcHubCitiID", hubCitiId);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(appConfigParams);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == errorNum) {
					appConfigurationList = (ArrayList<AppConfiguration>) resultFromProcedure.get("AppClaimConfigurationList");
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Exit ThisLocationDaoImpl : getClaimAppConfig : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return appConfigurationList;
	}
	
	
	/**
	 * The DAO method fetches Claim Location details from the database for the given user.
	 * 
	 * @param retLocationId
	 *            The retLocationId in the request.
	 * @return RetailerDetail info contains Claim info.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public RetailerDetail getClaimBusinessInfo (Integer retLocationId,  Integer hubCitiId) throws HubCitiException {
	
		LOG.info("Inside ThisLocationDaoImpl : getClaimBusinessInfo");

		ArrayList<RetailerDetail> claimInfoList = null;
		RetailerDetail objClaimDetail = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_ClaimYourBusinessUpdate");
			simpleJdbcCall.returningResultSet("AppClaimBusinessInfo", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource claimParams = new MapSqlParameterSource();
			claimParams.addValue("RetailLocationID", retLocationId);
			claimParams.addValue("HcHubcitiID", hubCitiId);
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(claimParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == errorNum) {
					
					claimInfoList = (ArrayList<RetailerDetail>) resultFromProcedure.get("AppClaimBusinessInfo");
					if (null != claimInfoList && !claimInfoList.isEmpty()) {
						objClaimDetail = new RetailerDetail();
						objClaimDetail.setRetDetailList(claimInfoList);
					} 
					
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ThisLocationDaoImpl : " + "getClaimBusinessInfo" + " " + HubCitiConstants.SCHEMANAME);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Exit ThisLocationDaoImpl : getClaimBusinessInfo : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return objClaimDetail;
	}
	
	
	
	
	/**
 * The DAO method fetches Claim Location details from the database for the given user.
 * 
 * @param objCustomerInfo
 *            Instance of type CustomerInfo.
 * @return RetailerDetail info contains Claim info.
 * @throws HubCitiException
 *             throws if exception occurs.
 */
	public CustomerInfo getClaimYourBusinessdetails (CustomerInfo objCustomerInfo) throws HubCitiException {

		LOG.info("Inside ThisLocationDaoImpl : getClaimYourBusinessdetails");

		ArrayList<CustomerInfo> claimInfoList = null;
		CustomerInfo objClaimDetail = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_ClaimYourBusinessForm");
			simpleJdbcCall.returningResultSet("ClaimBusinessInfo", new BeanPropertyRowMapper<CustomerInfo>(CustomerInfo.class));
			final MapSqlParameterSource claimParams = new MapSqlParameterSource();
			claimParams.addValue("RetailLocationID", objCustomerInfo.getRetLocationId());
			claimParams.addValue("HcHubcitiID", objCustomerInfo.getHubCitiId());
			claimParams.addValue("HcuserID", objCustomerInfo.getUserId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(claimParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == errorNum) {

					claimInfoList = (ArrayList<CustomerInfo>) resultFromProcedure.get("ClaimBusinessInfo");
					if (null != claimInfoList && !claimInfoList.isEmpty()) {
						objClaimDetail = new CustomerInfo();
						objClaimDetail.setCustomerInfoList(claimInfoList);
					} 

				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ThisLocationDaoImpl : " + "getClaimYourBusinessdetails" + " " + HubCitiConstants.SCHEMANAME);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Exit ThisLocationDaoImpl : getClaimYourBusinessdetails : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return objClaimDetail;
}
	
	
	
	/**
	 * This DAOImpl method for display special offer details for Location's.
	 * 
	 * @param objRetDetail
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public RetailersDetails getSpecialOfferLocationListJson(RetailerDetail objRetDetail) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getSpecialOfferLocationListJson");


		boolean ifeatured = false;
		RetailersDetails specialDetails = null;
		List<RetailerDetail> arFeaturedSpecialList = null;
		List<RetailerDetail> arNonFeaturedSpecialList = null;

		Integer iMaxCount = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSpecialOffersDisplay");
			simpleJdbcCall.returningResultSet("FeaturedSpecialOffersLocationList", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			simpleJdbcCall.returningResultSet("NonFeaturedSpecialOffersLocationList", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource specialOffersParams = new MapSqlParameterSource();
			specialOffersParams.addValue("UserID", objRetDetail.getUserId());
			if (null == objRetDetail.getLastVisitedNo()) {
				objRetDetail.setLastVisitedNo(0);
			}
			specialOffersParams.addValue("LowerLimit", objRetDetail.getLastVisitedNo());
			specialOffersParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			specialOffersParams.addValue("HcHubCitiID", objRetDetail.getHubCitiId());

			specialOffersParams.addValue("Longitude", objRetDetail.getLongitude());
			specialOffersParams.addValue("Latitude", objRetDetail.getLatitude());
			specialOffersParams.addValue("PostalCode", objRetDetail.getPostalCode());
			specialOffersParams.addValue("SearchKey", objRetDetail.getSearchKey());
			specialOffersParams.addValue("SortColumn", objRetDetail.getSortColumn());
			specialOffersParams.addValue("CityIds", objRetDetail.getCityIds());
			specialOffersParams.addValue("CategoryIds", objRetDetail.getCatIds());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specialOffersParams);



			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arFeaturedSpecialList = (List<RetailerDetail>) resultFromProcedure.get("FeaturedSpecialOffersLocationList");
					arNonFeaturedSpecialList = (List<RetailerDetail>) resultFromProcedure.get("NonFeaturedSpecialOffersLocationList");

					if (null != arFeaturedSpecialList && !arFeaturedSpecialList.isEmpty() && (null == objRetDetail.getIsNonFeatured() || objRetDetail.getIsNonFeatured())) {
						ifeatured = true;
						specialDetails = new RetailersDetails();
						specialDetails.setFeaturedSpecialList(arFeaturedSpecialList);
					}

					if (null != arNonFeaturedSpecialList && !arNonFeaturedSpecialList.isEmpty() ) {
						if (!ifeatured) {
							specialDetails = new RetailersDetails();
						}

						specialDetails.setNonFeaturedSpecialList(arNonFeaturedSpecialList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
						specialDetails.setMaxCnt(iMaxCount);
						if (nextpage != null) {
							if (nextpage) {
								specialDetails.setNextPage(1);
							} else {
								specialDetails.setNextPage(0);
							}
						}
						specialDetails.setMaxRowNum(arNonFeaturedSpecialList.get(arNonFeaturedSpecialList.size() - 1).getRowNum());
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ThisLocationDaoImpl : getSpecialOfferLocationListJson : Store Procedure error : usp_HcSpecialOffersDisplay ",errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getSpecialOfferLocationListJson : Store Procedure error : usp_HcSpecialOffersDisplay : "+ e.getMessage());
			throw new HubCitiException(e);
		}
		return specialDetails;
	}
	
	
	/**
	 * This DAOImpl method for display special offer details for Location's.
	 * 
	 * @param objRetDetail
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public RetailersDetails getSpecialOfferLocationMapListJson(RetailerDetail objRetDetail) throws HubCitiException {
		LOG.info("Inside ThisLocationDaoImpl : getSpecialOfferLocationMapListJson");


		RetailersDetails specialDetails = null;
		List<RetailerDetail> arFeaturedSpecialList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSpecialOffersMapDisplay");
			simpleJdbcCall.returningResultSet("SpecialOffersLocationforMap", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			
			final MapSqlParameterSource specialOffersParams = new MapSqlParameterSource();
			specialOffersParams.addValue("UserID", objRetDetail.getUserId());
			specialOffersParams.addValue("HcHubCitiID", objRetDetail.getHubCitiId());
			specialOffersParams.addValue("Longitude", objRetDetail.getLongitude());
			specialOffersParams.addValue("Latitude", objRetDetail.getLatitude());
			specialOffersParams.addValue("PostalCode", objRetDetail.getPostalCode());
			specialOffersParams.addValue("PageID", objRetDetail.getPageIds());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specialOffersParams);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arFeaturedSpecialList = (List<RetailerDetail>) resultFromProcedure.get("SpecialOffersLocationforMap");

					if (null != arFeaturedSpecialList && !arFeaturedSpecialList.isEmpty()) {
						specialDetails = new RetailersDetails();
						specialDetails.setFeaturedSpecialList(arFeaturedSpecialList);
					}

				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside ThisLocationDaoImpl : getSpecialOfferLocationMapListJson : Store Procedure error : usp_HcSpecialOffersMapDisplay ",errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside ThisLocationDaoImpl : getSpecialOfferLocationMapListJson : Store Procedure error : usp_HcSpecialOffersMapDisplay : "+ e.getMessage());
			throw new HubCitiException(e);
		}
		return specialDetails;
	}

}
