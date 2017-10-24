package com.hubciti.alertevent.dao;

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

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.AlertDetails;
import com.hubciti.common.pojos.BottomButton;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.Retailer;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;
import com.hubciti.common.utility.Utility;

/**
 * Dao method for alerts/events.
 * 
 * @author dhruvanath_mm
 */
@SuppressWarnings({"unchecked" })
public class AlertEventDaoImpl implements AlertEventDao {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(AlertEventDaoImpl.class);

	/**
	 * For JDBC connection.
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * To call stored procedue.
	 */
	private SimpleJdbcCall simpleJdbcCall;

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
	 * Method to fetch alert list from database.
	 * 
	 * @param objMenuItem
	 * @return {@link AlertDetails} list.
	 * @throws HubCitiException
	 */
	public AlertDetails getAlertsList(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getAlertsList");

		List<AlertDetails> alertDetailsList = null;
		AlertDetails objAlertDetails = null;
		// String strGrey = null;
		String strGreen = null;
		String strYellow = null;
		String strRed = null;
		Integer maxRowNum = 0;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcHubCitiAlertsDisplay");
			simpleJdbcCall.returningResultSet("alertList", new BeanPropertyRowMapper<AlertDetails>(AlertDetails.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", objMenuItem.getUserId());
			scanQueryParams.addValue("HubCitiID", objMenuItem.getHubCitiId());
			scanQueryParams.addValue("LowerLimit", objMenuItem.getLowerLimit());
			scanQueryParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			scanQueryParams.addValue("MainMenuID", objMenuItem.getMainMenuId());

			scanQueryParams.addValue("SearchParameter", objMenuItem.getSearchKey());
			scanQueryParams.addValue("CategoryID", objMenuItem.getCategoryId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == errorNum) {

					final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
					final Boolean nextPage = (Boolean) resultFromProcedure.get("NxtPageFlag");

					alertDetailsList = (List<AlertDetails>) resultFromProcedure.get("alertList");

					if (alertDetailsList != null && !alertDetailsList.isEmpty()) {

						strGreen = (String) resultFromProcedure.get("AlertImageGreen");
						strYellow = (String) resultFromProcedure.get("AlertImageYellow");
						strRed = (String) resultFromProcedure.get("AlertImageRed");
						// To get max row number
						maxRowNum = alertDetailsList.get(alertDetailsList.size() - 1).getRowNum();

						// looping inside list to change date format.
						for (AlertDetails objAlert : alertDetailsList) {
							objAlert.setIsDateFormated(false);
							objAlert.setIsTimeFormated(false);
							objAlert.setStartDate(objAlert.getStartDate());
							objAlert.setEndDate(objAlert.getEndDate());
							objAlert.setStartTime(objAlert.getStartTime());
							objAlert.setEndTime(objAlert.getEndTime());
							objAlert.setIsDateFormated(null);
							objAlert.setIsTimeFormated(null);
						}

						objAlertDetails = new AlertDetails();
						objAlertDetails.setAlertList(alertDetailsList);
						objAlertDetails.setMaxCount(maxCnt);
						objAlertDetails.setMaxRowNum(maxRowNum);
						// objAlertDetails.setGrey(strGrey);
						objAlertDetails.setGreen(strGreen);
						objAlertDetails.setYellow(strYellow);
						objAlertDetails.setRed(strRed);

						if (nextPage != null && nextPage == true) {
							objAlertDetails.setNextPage(1);
						} else {
							objAlertDetails.setNextPage(0);
						}

					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getAlertsList " + HubCitiConstants.SCHEMANAME+ " : usp_HcHubCitiAlertsDisplay :  ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getAlertsList " + HubCitiConstants.SCHEMANAME+ " : usp_HcHubCitiAlertsDisplay :   " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside AlertEventDaoImpl : getAlertsList " + HubCitiConstants.SCHEMANAME+ " : usp_HcHubCitiAlertsDisplay :   " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit AlertEventDaoImpl : getAlertsList");
		return objAlertDetails;
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
	public RetailersDetails getDiningRetInfoForLocation(ThisLocationRequest thisLocationRequest) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getDiningRetInfoForLocation");
		List<RetailerDetail> retailerList = null;
		RetailersDetails details = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcDiningRetailersDisplay");
			simpleJdbcCall.returningResultSet("retailers", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource fetchRetailerDetailsParameters = new MapSqlParameterSource();
			fetchRetailerDetailsParameters.addValue("UserID", thisLocationRequest.getUserId());
			fetchRetailerDetailsParameters.addValue("CuisineTypeID", thisLocationRequest.getCuisineTypeId());
			fetchRetailerDetailsParameters.addValue("HubCitiID", thisLocationRequest.getHubCitiId());
			fetchRetailerDetailsParameters.addValue("LowerLimit", thisLocationRequest.getLastVisitedRecord());
			fetchRetailerDetailsParameters.addValue("Radius", thisLocationRequest.getPreferredRadius());
			fetchRetailerDetailsParameters.addValue("Latitude", thisLocationRequest.getLatitude());
			fetchRetailerDetailsParameters.addValue("Longitude", thisLocationRequest.getLongitude());
			fetchRetailerDetailsParameters.addValue("PostalCode", thisLocationRequest.getPostalCode());
			fetchRetailerDetailsParameters.addValue("SortOrder", thisLocationRequest.getSortOrder());
			fetchRetailerDetailsParameters.addValue("SortColumn", thisLocationRequest.getSortColumn());
			fetchRetailerDetailsParameters.addValue("SearchParameter", thisLocationRequest.getSearchKey());
			fetchRetailerDetailsParameters.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);

			// For user tracking
			fetchRetailerDetailsParameters.addValue("MainMenuID", thisLocationRequest.getMainMenuId());

			// below param are output params from SP.
			fetchRetailerDetailsParameters.addValue("ErrorNumber", null);
			fetchRetailerDetailsParameters.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetailerDetailsParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
					final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");

					retailerList = (List<RetailerDetail>) resultFromProcedure.get("retailers");
					details = new RetailersDetails();
					if (null != retailerList && !retailerList.isEmpty()) {
						final Integer maxRowNum = retailerList.get(retailerList.size() - 1).getRowNumber();
						details.setMaxRowNum(maxRowNum);
						details.setRetailerDetail(retailerList);

						if (nextpage != null) {
							if (nextpage) {
								details.setNextPage(1);
							} else {
								details.setNextPage(0);
							}
						}
						if (null != maxCnt) {
							details.setMaxCnt(maxCnt);
						}
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Inside AlertEventDaoImpl : getDiningRetInfoForLocation " + HubCitiConstants.SCHEMANAME+ " : usp_HcFetchRetailerListPagination ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getDiningRetInfoForLocation " + HubCitiConstants.SCHEMANAME+ " : usp_HcFetchRetailerListPagination : "
					+ e.getMessage());
			throw new HubCitiException(e);
		}
		return details;
	}

	/**
	 * Method to capture click on Alert List.
	 * 
	 * @param xml
	 * @return String xml containing alert details.
	 * @throws HubCitiException.
	 */
	public String userTrackingAlertClick(Integer alertListId) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : userTrackingAlertClick");

		String response = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcUserTrackingAlertsClick");

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("AlertListID", alertListId);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null == errorNum) {
				response = HubCitiConstants.SUCCESSTEXT;
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside AlertEventDaoImpl : userTrackingAlertClick " + HubCitiConstants.SCHEMANAME+ " : usp_HcUserTrackingAlertsClick ", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : userTrackingAlertClick " + HubCitiConstants.SCHEMANAME+ " : usp_HcUserTrackingAlertsClick " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside AlertEventDaoImpl : userTrackingAlertClick " + HubCitiConstants.SCHEMANAME+ " : usp_HcUserTrackingAlertsClick " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit AlertEventDaoImpl : userTrackingAlertClick");
		return response;
	}

	/**
	 * Method for getting dining categories.
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return {@link CategoryDetails} object.
	 * @throws HubCitiException
	 */
	public CategoryDetails getDiningCategories(Integer userId, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getDiningCategories");

		// String response = null;
		List<CategoryDetails> catDetailsList = null;
		CategoryDetails objCategoryDetails = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcDiningCategoriesDisplay");
			simpleJdbcCall.returningResultSet("catList", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("HubCitiID", hubCitiId);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			catDetailsList = (List<CategoryDetails>) resultFromProcedure.get("catList");
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null == errorNum) {
				objCategoryDetails = new CategoryDetails();
				objCategoryDetails.setListCatDetails(catDetailsList);
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside AlertEventDaoImpl : getDiningCategories " + HubCitiConstants.SCHEMANAME+ " : usp_HcDiningCategoriesDisplay : ", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getDiningCategories " + HubCitiConstants.SCHEMANAME+ " : usp_HcDiningCategoriesDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside AlertEventDaoImpl : getDiningCategories " + HubCitiConstants.SCHEMANAME+ " : usp_HcDiningCategoriesDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit AlertEventDaoImpl : getDiningCategories ");
		return objCategoryDetails;
	}

	/**
	 * DAOImpl method to fetch event list from database.
	 * 
	 * @param instance of MenuItem.
	 * @return instance of EventDetails.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public EventDetails getEventList(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getEventList");

		List<EventDetails> eventDetailsList = null;
		List<BottomButton> bottomBtnList = null;
		EventDetails eventDetails = null;
		Integer maxRowNum = 0;

		try {
			final MapSqlParameterSource eventDetailParams = new MapSqlParameterSource();

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			/*
			   To display events based on the RegionApp i.e nothing but Events 
			    w.r.t RegionApp along with the HubCities associated to that RegionApp.
			 */
			
			if  ("Band".equals(Utility.checkNull(objMenuItem.getListType()))) {
				simpleJdbcCall.withProcedureName("usp_HcBandEventsDisplay");
				
			} else {
				if (1 == objMenuItem.getIsRegApp()) {
					
					simpleJdbcCall.withProcedureName("usp_HcHubRegionEventsDisplay");
					
				} else {
					simpleJdbcCall.withProcedureName("usp_HcEventsDisplay");
				}
			}

			simpleJdbcCall.returningResultSet("eventList", new BeanPropertyRowMapper<EventDetails>(EventDetails.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			//Region/Area App 
			if ("".equals(Utility.checkNull(objMenuItem.getCityIds()))) {
				objMenuItem.setCityIds(null);
			}
			eventDetailParams.addValue("CityIDs", objMenuItem.getCityIds());

			eventDetailParams.addValue("UserID", objMenuItem.getUserId());
			eventDetailParams.addValue("HubCitiID", objMenuItem.getHubCitiId());
			eventDetailParams.addValue("LowerLimit", objMenuItem.getLowerLimit());
			eventDetailParams.addValue("CategoryID", objMenuItem.getCatIds());
			eventDetailParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			eventDetailParams.addValue("SortColumn", objMenuItem.getSortColumn());
			eventDetailParams.addValue("SortOrder", objMenuItem.getSortOrder());
			eventDetailParams.addValue("Latitude", objMenuItem.getLatitude());
			eventDetailParams.addValue("Longitude", objMenuItem.getLongitude());
			eventDetailParams.addValue("Postalcode", objMenuItem.getPostalCode());
			eventDetailParams.addValue("SearchParameter", objMenuItem.getSearchKey());
			eventDetailParams.addValue("MainMenuID", objMenuItem.getMainMenuId());
			eventDetailParams.addValue("GroupColumn", objMenuItem.getGroupBy());

			/*Event display param*/
			eventDetailParams.addValue("HcMenuItemID", objMenuItem.getmItemId());
			eventDetailParams.addValue("HcBottomButtonID", objMenuItem.getBottomBtnId());

			/*For Retailer event display param.*/
			eventDetailParams.addValue("RetailID", objMenuItem.getRetailId());
			eventDetailParams.addValue("RetailLocationID", objMenuItem.getRetailLocationId());

			eventDetailParams.addValue("FundRaisingID", objMenuItem.getFundId());
			eventDetailParams.addValue("EventDate", objMenuItem.getEvtDate());
			
			eventDetailParams.addValue("Radius", objMenuItem.getRadius());
			eventDetailParams.addValue("BandEventTypeID", objMenuItem.getEvtTypeID());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(eventDetailParams);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
					final Boolean nextPage = (Boolean) resultFromProcedure.get("NxtPageFlag");

					eventDetailsList = (List<EventDetails>) resultFromProcedure.get("eventList");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

					eventDetails = new EventDetails();

					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						eventDetails.setBottomBtnList(bottomBtnList);
						eventDetails.setBottomBtn(1);
					} else {
						eventDetails.setBottomBtn(0);
					}

					if (eventDetailsList != null && !eventDetailsList.isEmpty()) {

						// To get max row number
						maxRowNum = eventDetailsList.get(eventDetailsList.size() - 1).getRowNum();

						// looping inside list to change date fromat.
						for (EventDetails objEventDetails : eventDetailsList) {
							objEventDetails.setIsDateFormated(false);
							objEventDetails.setIsTimeFormated(false);
							objEventDetails.setStartDate(objEventDetails.getStartDate());
							objEventDetails.setEndDate(objEventDetails.getEndDate());
							objEventDetails.setStartTime(objEventDetails.getStartTime());
							objEventDetails.setEndTime(objEventDetails.getEndTime());
							objEventDetails.setIsDateFormated(null);
							objEventDetails.setIsTimeFormated(null);
						}

						eventDetails.setEventList(eventDetailsList);
						eventDetails.setMaxCount(maxCnt);
						eventDetails.setMaxRowNum(maxRowNum);

						if (nextPage != null && nextPage == true) {
							eventDetails.setNextPage(1);
						} else {
							eventDetails.setNextPage(0);
						}

					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getEventList " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsDisplay : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getEventList " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside AlertEventDaoImpl : getEventList " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit AlertEventDaoImpl : getEventList");
		return eventDetails;
	}

	/**
	 * The DAOImpl method fetches event details details.
	 * 
	 * @param eventId
	 *            as request parameter
	 * @return xml containing event information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<EventDetails> getEventDetails(Integer eventId, Integer eventsListId, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getEventDetails");

		List<EventDetails> arEventDetailslist = null;
		String strEvtLgSSQRPath = null;

		try {
			final MapSqlParameterSource eventDetailsParam = new MapSqlParameterSource();
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			if (null == eventsListId ) {
				simpleJdbcCall.withProcedureName("usp_HcBandEventDetail");
			} else {
				simpleJdbcCall.withProcedureName("usp_HcEventDetails");
				eventDetailsParam.addValue("EventsListID", eventsListId);
			}

			simpleJdbcCall.returningResultSet("eventDetailInfo", new BeanPropertyRowMapper<EventDetails>(EventDetails.class));

			eventDetailsParam.addValue("HcEventID", eventId);
			eventDetailsParam.addValue("HcHubCitiID", hubCitiId);

			eventDetailsParam.addValue(HubCitiConstants.ERRORNUMBER, null);
			eventDetailsParam.addValue(HubCitiConstants.ERRORNUMBER, null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(eventDetailsParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					final Boolean appSiteFlag = (Boolean) resultFromProcedure.get("AppSiteFlag");
					strEvtLgSSQRPath = (String) resultFromProcedure.get("EventLogisticSSQRPath");

					arEventDetailslist = (List<EventDetails>) resultFromProcedure.get("eventDetailInfo");

					if (null != arEventDetailslist && !arEventDetailslist.isEmpty()) {
						if (appSiteFlag) {
							arEventDetailslist.get(0).setIsAppSiteFlag(1);
						} else {
							arEventDetailslist.get(0).setIsAppSiteFlag(0);
						}
						arEventDetailslist.get(0).setEventLgSSQRPath(strEvtLgSSQRPath);
					}

				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getEventDetails " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventDetails :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside AlertEventDaoImpl : getEventDetails " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventDetails : " + exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit RateReviewDaoImpl : getEventDetails");
		return arEventDetailslist;
	}

	/**
	 * This DAOImpl method for fetch the hotel from the database for the given
	 * search parameters(Zipcode or Latitude and longitude).
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
	public RetailersDetails getEventHotelDisplay(ThisLocationRequest thisLocationRequest) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getEventHotelDisplay");

		List<RetailerDetail> retailerList = null;
		RetailersDetails details = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcEventsHotelsDisplay");
			simpleJdbcCall.returningResultSet("retailers", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource fetchRetailerDetailsParameters = new MapSqlParameterSource();
			fetchRetailerDetailsParameters.addValue("UserID", thisLocationRequest.getUserId());
			fetchRetailerDetailsParameters.addValue("HcEventID", thisLocationRequest.getEventId());
			fetchRetailerDetailsParameters.addValue("HubCitiID", thisLocationRequest.getHubCitiId());
			fetchRetailerDetailsParameters.addValue("LowerLimit", thisLocationRequest.getLastVisitedRecord());
			fetchRetailerDetailsParameters.addValue("Latitude", thisLocationRequest.getLatitude());
			fetchRetailerDetailsParameters.addValue("Longitude", thisLocationRequest.getLongitude());
			fetchRetailerDetailsParameters.addValue("PostalCode", thisLocationRequest.getPostalCode());
			fetchRetailerDetailsParameters.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			fetchRetailerDetailsParameters.addValue("MainMenuID", thisLocationRequest.getMainMenuId());

			// below param are output params from SP.
			fetchRetailerDetailsParameters.addValue("ErrorNumber", null);
			fetchRetailerDetailsParameters.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetailerDetailsParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
					final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");

					retailerList = (List<RetailerDetail>) resultFromProcedure.get("retailers");
					details = new RetailersDetails();

					if (null != retailerList && !retailerList.isEmpty()) {
						details.setRetailerDetail(retailerList);

						if (nextpage != null) {
							if (nextpage) {
								details.setNextPage(1);
							} else {
								details.setNextPage(0);
							}
						}
						if (null != maxCnt) {
							details.setMaxCnt(maxCnt);
						}
						details.setMaxRowNum(retailerList.get(retailerList.size() - 1).getRowNum());
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.error("Inside AlertEventDaoImpl : getEventHotelDisplay " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsHotelsDisplay ", errorNum,
							errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getEventHotelDisplay " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsHotelsDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit AlertEventDaoImpl : getEventHotelDisplay");
		return details;
	}

	/**
	 * Method for getting Event categories.
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return {@link CategoryDetails} object.
	 * @throws HubCitiException
	 */
	public CategoryDetails getEventCategories(MenuItem menuItemObj) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getEventCategories");

		List<CategoryDetails> catDetailsList = null;
		CategoryDetails objCategoryDetails = null;
		List<BottomButton> arBottomBtnList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcEventsCategoryDisplay");
			simpleJdbcCall.returningResultSet("catList", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("HubCitiID", menuItemObj.getHubCitiId());
			scanQueryParams.addValue("UserID", menuItemObj.getUserId());
			scanQueryParams.addValue("HcMenuItemID", menuItemObj.getmItemId());
			scanQueryParams.addValue("HcBottomButtonID", menuItemObj.getBottomBtnId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			catDetailsList = (List<CategoryDetails>) resultFromProcedure.get("catList");
			arBottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null == errorNum) {
				objCategoryDetails = new CategoryDetails();
				objCategoryDetails.setListCatDetails(catDetailsList);
				objCategoryDetails.setBottomBtnList(arBottomBtnList);
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside AlertEventDaoImpl : getEventCategories " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsCategoryDisplay :  ", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getEventCategories " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsCategoryDisplay :  " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside AlertEventDaoImpl : getEventCategories " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsCategoryDisplay :  " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit AlertEventDaoImpl : getEventCategories");
		return objCategoryDetails;
	}

	/**
	 * The DAOImpl method fetches event hotel details details.
	 * 
	 * @param eventId
	 *            as request parameter
	 * @return xml containing event information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<EventDetails> getEventHotelDetails(Retailer objRetailer) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getEventHotelDetails");

		List<EventDetails> arEventDetailslist = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcEventsHotelsDetails");
			simpleJdbcCall.returningResultSet("eventHotelDetail", new BeanPropertyRowMapper<EventDetails>(EventDetails.class));

			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("HcEventID", objRetailer.getEventId());
			ratingParameters.addValue("UserID", objRetailer.getUserId());
			ratingParameters.addValue("HubCitiID", objRetailer.getHubCitiId());
			ratingParameters.addValue("RetailLocationID", objRetailer.getRetailLocationId());
			ratingParameters.addValue("RetailerListID", objRetailer.getRetListId());

			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arEventDetailslist = (List<EventDetails>) resultFromProcedure.get("eventHotelDetail");
					/*if (null != arEventDetailslist && !arEventDetailslist.isEmpty()) {
					}*/
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getEventHotelDetails " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsHotelsDetails :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside AlertEventDaoImpl : getEventHotelDetails " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventsHotelsDetails : "
					+ exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit AlertEventDaoImpl : getEventHotelDetails");
		return arEventDetailslist;
	}

	/**
	 * DAO method to fetch locations (App Sites) associated to events.
	 * 
	 * @param objRetailer
	 * @return App site list
	 * @throws HubCitiException
	 */
	public List<Retailer> getEventAppSiteLocation(Retailer objRetailer) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getEventAppSiteLocation");

		List<Retailer> retailerList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcEventAppSiteLocationDisplay");

			simpleJdbcCall.returningResultSet("appSiteList", new BeanPropertyRowMapper<Retailer>(Retailer.class));

			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("HcEventID", objRetailer.getEventId());
			ratingParameters.addValue("UserID", objRetailer.getUserId());
			ratingParameters.addValue("HubCitiID", objRetailer.getHubCitiId());
			ratingParameters.addValue("Latitude", objRetailer.getLatitude());
			ratingParameters.addValue("Longitude", objRetailer.getLongitude());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);

			if (null != resultFromProcedure) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				if (null == errorNum) {
					retailerList = (List<Retailer>) resultFromProcedure.get("appSiteList");
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getEventAppSiteLocation " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventAppSiteLocationDisplay :  "
							+ errorNum + " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside AlertEventDaoImpl : getEventAppSiteLocation " + HubCitiConstants.SCHEMANAME+ " : usp_HcEventAppSiteLocationDisplay : "
					+ exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit AlertEventDaoImpl : getEventAppSiteLocation");
		return retailerList;
	}


	/**
	 * DAOImpl method to fetch fundraiser list from database.
	 * 
	 * @param MenuItem object
	 * @return fundraiser object List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public Fundraiser getFundraiserList(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getFundraiserList");

		List<Fundraiser> fundraiserList = null;
		List<BottomButton> bottomBtnList = null;
		Fundraiser objFundraiser = null;
		Integer maxRowNum = 0;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcFundraisingDisplay");

			simpleJdbcCall.returningResultSet("fundraiserList", new BeanPropertyRowMapper<Fundraiser>(Fundraiser.class));

			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource fundraiserParams = new MapSqlParameterSource();
			fundraiserParams.addValue("UserID", objMenuItem.getUserId());
			fundraiserParams.addValue("HubCitiID", objMenuItem.getHubCitiId());
			fundraiserParams.addValue("LowerLimit", objMenuItem.getLowerLimit());
			fundraiserParams.addValue("CategoryID", objMenuItem.getCatId());
			fundraiserParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			fundraiserParams.addValue("SortColumn", objMenuItem.getSortColumn());
			fundraiserParams.addValue("SortOrder", objMenuItem.getSortOrder());
			fundraiserParams.addValue("Latitude", objMenuItem.getLatitude());
			fundraiserParams.addValue("Longitude", objMenuItem.getLongitude());
			fundraiserParams.addValue("Postalcode", objMenuItem.getPostalCode());
			fundraiserParams.addValue("SearchParameter", objMenuItem.getSearchKey());
			fundraiserParams.addValue("MainMenuID", objMenuItem.getMainMenuId());
			fundraiserParams.addValue("GroupColumn", objMenuItem.getGroupBy());
			fundraiserParams.addValue("HCDepartmentID", objMenuItem.getDepartmentId());

			/*Fundraiser display param*/
			fundraiserParams.addValue("HcMenuItemID", objMenuItem.getmItemId());
			fundraiserParams.addValue("HcBottomButtonID", objMenuItem.getBottomBtnId());

			/*For Retailer event display param.*/
			fundraiserParams.addValue("RetailID", objMenuItem.getRetailId());
			fundraiserParams.addValue("RetailLocationID", objMenuItem.getRetailLocationId());

			fundraiserParams.addValue("CityIDs", objMenuItem.getCityIds());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fundraiserParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
					final Boolean nextPage = (Boolean) resultFromProcedure.get("NxtPageFlag");
					final Boolean bDepartFlag = (Boolean) resultFromProcedure.get("FundDepartmentFlag");

					fundraiserList = (List<Fundraiser>) resultFromProcedure.get("fundraiserList");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

					objFundraiser = new Fundraiser();

					// To check department list exist or not.
					if (null != bDepartFlag) {
						if (bDepartFlag) {
							objFundraiser.setIsDeptFlag(1);
						} else {
							objFundraiser.setIsDeptFlag(0);
						}
					}

					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						objFundraiser.setBottomBtnList(bottomBtnList);
						objFundraiser.setBottomBtn(1);
					} else {
						objFundraiser.setBottomBtn(0);
					}

					if ( null != fundraiserList && !fundraiserList.isEmpty()) {
						// To get max row number
						maxRowNum = fundraiserList.get(fundraiserList.size() - 1).getRowNum();

						// looping inside list to change date format.
						for (Fundraiser fundraiser : fundraiserList) {
							fundraiser.setIsDateFormated(false);
							fundraiser.setStartDate(fundraiser.getStartDate());
							fundraiser.setEndDate(fundraiser.getEndDate());
							fundraiser.setIsDateFormated(null);
						}

						objFundraiser.setFundraiserList(fundraiserList);
						objFundraiser.setMaxCount(maxCnt);
						objFundraiser.setMaxRowNum(maxRowNum);

						if (null != nextPage) {  
							if(nextPage) {
								objFundraiser.setNextPage(1);
							} else {
								objFundraiser.setNextPage(0);
							}
						}

					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getFundraiserList " + HubCitiConstants.SCHEMANAME+ " : usp_HcFundraisingDisplay : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getFundraiserList " + HubCitiConstants.SCHEMANAME+ " : usp_HcFundraisingDisplay  : " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside AlertEventDaoImpl : getFundraiserList " + HubCitiConstants.SCHEMANAME+ " :  usp_HcFundraisingDisplay  : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit AlertEventDaoImpl : getFundraiserList");
		return objFundraiser;
	}



	/**
	 * DAOImpl method to get list of fundraiser department list.
	 * 
	 * @param MenuItem object
	 * @return Department object List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public List<MenuItem> getDepartmentList(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getDepartmentList");

		List<MenuItem> menuItemList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcFundraisingDepartmentList");
			simpleJdbcCall.returningResultSet("getFundDepartmentList", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));

			final MapSqlParameterSource fundDepartParam = new MapSqlParameterSource();
			fundDepartParam.addValue("HcMenuItemID", objMenuItem.getmItemId());
			fundDepartParam.addValue("HubCitiID", objMenuItem.getHubCitiId());
			fundDepartParam.addValue("UserID", objMenuItem.getUserId());
			fundDepartParam.addValue("HcBottomButtonID", objMenuItem.getBottomBtnId());
			fundDepartParam.addValue("RetailID", objMenuItem.getRetailId());
			fundDepartParam.addValue("RetailLocationID", objMenuItem.getRetailLocationId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fundDepartParam);

			final Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (status == 0) {
				menuItemList = (List<MenuItem>) resultFromProcedure.get("getFundDepartmentList");
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside AlertEventDaoImpl : getDepartmentList : Store Procedure : usp_HcFundraisingDepartmentList : error number : " + errorNum + " and error message : "+ errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getDepartmentList " + HubCitiConstants.SCHEMANAME+ " : usp_HcFundraisingDepartmentList  : " + e.getMessage());
			throw new HubCitiException(e);
		} 

		LOG.info("Exit AlertEventDaoImpl : getDepartmentList");
		return menuItemList;
	}



	/**
	 * DAO method to fetch locations (App Sites) associated to fundraiser.
	 * 
	 * @param objRetailer
	 * @return App site list
	 * @throws HubCitiException
	 */
	public List<Retailer> getFundAppSiteLocation(Retailer objRetailer) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getFundAppSiteLocation");

		List<Retailer> retailerList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("----------------------------");
			simpleJdbcCall.returningResultSet("appSiteList", new BeanPropertyRowMapper<Retailer>(Retailer.class));

			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("HcEventID", objRetailer.getEventId());
			ratingParameters.addValue("UserID", objRetailer.getUserId());
			ratingParameters.addValue("HubCitiID", objRetailer.getHubCitiId());
			ratingParameters.addValue("Latitude", objRetailer.getLatitude());
			ratingParameters.addValue("Longitude", objRetailer.getLongitude());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);

			if (null != resultFromProcedure) {

				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

				if (null == errorNum) {
					retailerList = (List<Retailer>) resultFromProcedure.get("appSiteList");
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getFundAppSiteLocation() " + HubCitiConstants.SCHEMANAME+ " : --------------------------- :  "
							+ errorNum + " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}

			}
		} catch (DataAccessException exception) {
			LOG.error("Inside AlertEventDaoImpl : getFundAppSiteLocation() " + HubCitiConstants.SCHEMANAME+ " : -------------------- : "
					+ exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit AlertEventDaoImpl : getFundAppSiteLocation");
		return retailerList;
	}



	/**
	 * The DAOImpl method fetches fundraiser details.
	 * 
	 * @param fundId, hubCitiId, fundListId
	 *            as request parameter
	 * @return xml containing event information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<Fundraiser> getFundraiserDetails(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getFundraiserDetails");

		List<Fundraiser> fundraiserList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcFundraisingDetails");
			simpleJdbcCall.returningResultSet("fundDetailInfo", new BeanPropertyRowMapper<Fundraiser>(Fundraiser.class));

			final MapSqlParameterSource fundDetailsParam = new MapSqlParameterSource();
			fundDetailsParam.addValue("HcFundraisingID", objMenuItem.getFundId());
			fundDetailsParam.addValue("FundraisingListID", objMenuItem.getFundListId());
			fundDetailsParam.addValue("HcHubCitiID", objMenuItem.getHubCitiId());

			fundDetailsParam.addValue(HubCitiConstants.ERRORNUMBER, null);
			fundDetailsParam.addValue(HubCitiConstants.ERRORNUMBER, null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fundDetailsParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					fundraiserList = (List<Fundraiser>) resultFromProcedure.get("fundDetailInfo");

					if (null != fundraiserList && !fundraiserList.isEmpty()) {
						final Boolean appSiteFlag = (Boolean) resultFromProcedure.get("AppSiteFlag");

						if (appSiteFlag) {
							fundraiserList.get(0).setIsAppSiteFlag(1);
						} else {
							fundraiserList.get(0).setIsAppSiteFlag(0);
						}

					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getFundDetails " + HubCitiConstants.SCHEMANAME+ " : usp_HcFundraisingDetails :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside AlertEventDaoImpl : getFundraiserDetails : " + HubCitiConstants.SCHEMANAME+ " : usp_HcFundraisingDetails : " + exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit AlertEventDaoImpl : getFundraiserDetails");
		return fundraiserList;
	}



	/**
	 * The DAOImpl method method to get event logistics information.
	 * 
	 * @param  contains information event details.
	 * @return AlertDetails containing event logistics information.  
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public AlertDetails getEventLogistics(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside AlertEventDaoImpl : getEventLogistics");

		List<AlertDetails> alertDetailsList = null;
		AlertDetails objAlertDetails = null;


		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcHubCitiEventLogisticsDetails");
			simpleJdbcCall.returningResultSet("eventLogistics", new BeanPropertyRowMapper<AlertDetails>(AlertDetails.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", objMenuItem.getUserId());
			scanQueryParams.addValue("HubCitiID", objMenuItem.getHubCitiId());
			scanQueryParams.addValue("HcEventID", objMenuItem.getEventId());


			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == errorNum) {

					final Boolean overLayFlag = (Boolean) resultFromProcedure.get("EventsIsOverLayFlag");

					alertDetailsList = (List<AlertDetails>) resultFromProcedure.get("eventLogistics");

					if (alertDetailsList != null && !alertDetailsList.isEmpty()) {
						objAlertDetails = new AlertDetails();
						objAlertDetails.setEvtLogImg((String) resultFromProcedure.get("EventLogisticMapURL"));
						objAlertDetails.setEvtLogMapUrl((String) resultFromProcedure.get("EventLogisticImgPath"));

						objAlertDetails.setAlertList(alertDetailsList);

						if (overLayFlag != null && overLayFlag == true) {
							objAlertDetails.setOverLayFlag(1);
						} else {
							objAlertDetails.setOverLayFlag(0);
						}
					}

				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getEventLogistics " + HubCitiConstants.SCHEMANAME+ " : usp_HcHubCitiEventLogisticsDetails :  ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getEventLogistics " + HubCitiConstants.SCHEMANAME+ " : usp_HcHubCitiEventLogisticsDetails :   " + e.getMessage());
			throw new HubCitiException(e);
		} 

		LOG.info("Exit AlertEventDaoImpl : getEventLogistics");
		return objAlertDetails;
	}

	/**
	 * The DAO method to fetch Fundraiser  location List
	 * @param fundraiserRequest 
	 * @return list of appsite Location associated to Fundraisers
	 *  @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public Fundraiser getFundraiserLocationList(
			Fundraiser fundraiserRequest) throws HubCitiException{
		
		LOG.info("Inside AlertEventDaoImpl : getFundraiserAppSiteLocationList");
		
		List<Fundraiser> fundraiserList = null;
		Fundraiser fundraiserResponse = null ;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcFundraisingLocationDisplay");
			simpleJdbcCall.returningResultSet("fundraiserList", new BeanPropertyRowMapper<Fundraiser>(Fundraiser.class));

			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("FundraisingID", fundraiserRequest.getFundId());
			scanQueryParams.addValue("HubcitiID", fundraiserRequest.getHubCitiId());
			scanQueryParams.addValue("UserID", fundraiserRequest.getUserId());
			scanQueryParams.addValue("Latitude",fundraiserRequest.getLatitude());
			scanQueryParams.addValue("Longitude",fundraiserRequest.getLongitude());
			scanQueryParams.addValue("Postalcode",fundraiserRequest.getPostalCode());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == errorNum) {

					fundraiserList = (List<Fundraiser>) resultFromProcedure.get("fundraiserList");

					if (fundraiserList != null && !fundraiserList.isEmpty()) {
						
						fundraiserResponse = new Fundraiser();
						fundraiserResponse.setFundraiserList(fundraiserList);
						
						final Boolean HcAppSiteFlag = (Boolean)resultFromProcedure.get("HcAppSiteFlag");
						
						if ( null != HcAppSiteFlag  ) {
							
							if( HcAppSiteFlag == true){
								fundraiserResponse.setIsAppSiteFlag(1);
							}else{
								fundraiserResponse.setIsAppSiteFlag(0);
							}
							
						}
						
					}

				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getFundraiserAppSiteLocationList " + HubCitiConstants.SCHEMANAME+ " :  usp_HcFundraisingLocationDisplay:  ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside AlertEventDaoImpl : getFundraiserAppSiteLocationList " + HubCitiConstants.SCHEMANAME+ " : usp_HcFundraisingLocationDisplay :   " + e.getMessage());
			throw new HubCitiException(e);
		} 
		LOG.info("Exit AlertEventDaoImpl : getFundraiserAppSiteLocationList");
		return fundraiserResponse;
	}
}

