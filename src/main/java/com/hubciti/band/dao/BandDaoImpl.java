package com.hubciti.band.dao;

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

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.BottomButton;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.GoogleCategory;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.MainCategory;
import com.hubciti.common.pojos.Menu;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.News;
import com.hubciti.common.pojos.RetailerCreatedPages;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.utility.Utility;

/**
 * @author kumar_dodda
 *
 */
@SuppressWarnings({"unchecked"})
public class BandDaoImpl implements BandDao {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BandDaoImpl.class);

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
	 * The DAOImpl method fetches Category Details. no Query parameter
	 * 
	 * @param instance
	 *            of MenuItem.
	 * @return List of Event Types.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public Categories getAllEventTypes(MenuItem objMenuItem) throws HubCitiException {

		LOG.info("Inside BandDaoImpl : getAllEventTypes");

		List<GoogleCategory> eventTypeList = null;
		List<BottomButton> bottomBtnList = null;
		Categories objCategories = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_FindNearByBandEvent");
			simpleJdbcCall.returningResultSet("eventTypeList", new BeanPropertyRowMapper<GoogleCategory>(GoogleCategory.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource eventTypesParams = new MapSqlParameterSource();
			eventTypesParams.addValue("UserID", objMenuItem.getUserId());
			eventTypesParams.addValue("HcMenuItemID", objMenuItem.getmItemId());
			eventTypesParams.addValue("HubCitiID", objMenuItem.getHubCitiId());
			eventTypesParams.addValue("Latitude", objMenuItem.getLatitude());
			eventTypesParams.addValue("Longitude", objMenuItem.getLongitude());
			eventTypesParams.addValue("PostalCode", objMenuItem.getPostalCode());
			eventTypesParams.addValue("Radius", objMenuItem.getRadius());
			eventTypesParams.addValue("MainMenuID", objMenuItem.getMainMenuId());

			final Map<String, Object> resFromProc = simpleJdbcCall.execute(eventTypesParams);

			if (null != resFromProc) {
				if (null == resFromProc.get(HubCitiConstants.ERRORNUMBER)) {

					eventTypeList = (List<GoogleCategory>) resFromProc.get("eventTypeList");
					bottomBtnList = (List<BottomButton>) resFromProc.get("bottomButtonDetails");

					objCategories = new Categories();
					objCategories.setNoRecordMsg(Utility.clobToString((Clob) resFromProc.get("NoRecordsMsg")));

					objCategories.setmColor((String) resFromProc.get("MenuColor"));
					objCategories.setmFontColor((String) resFromProc.get("MenuFontColor"));

					objCategories.setCatList(eventTypeList);

					if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
						objCategories.setBottomBtnList(bottomBtnList);
						objCategories.setBottomBtn(1);
					} else {
						objCategories.setBottomBtn(0);
					}

				} else {
					final Integer errorNum = (Integer) resFromProc.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resFromProc.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside BandDaoImpl : getAllEventTypes : usp_FindNearByBandEvent : " + errorNum + " and error message: " + errorMsg);
					throw new HubCitiException(errorMsg);
				}

			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getAllEventTypes : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getAllEventTypes");
		return objCategories;
	}

	/**
	 * The DAOImpl method fetches Band list details.
	 * 
	 * @param instance
	 *            of RetailerDetail.
	 * @return List of Bands.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public RetailersDetails getBandList(RetailerDetail objRetDet) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getBandList");

		RetailersDetails objBandsDetails = null;
		List<RetailerDetail> bandDetailslist = null;
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
			simpleJdbcCall.withProcedureName("usp_HcBandListPagination");
			simpleJdbcCall.returningResultSet("bandDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource findBandListParams = new MapSqlParameterSource();

			findBandListParams.addValue("HCHubCitiID", objRetDet.getHubCitiId());
			findBandListParams.addValue("LowerLimit", objRetDet.getLastVisitedNo());
			findBandListParams.addValue("ScreenName", HubCitiConstants.FINDSCREENNAME);
			findBandListParams.addValue("HcMenuItemId", objRetDet.getmItemId());
			findBandListParams.addValue("Radius", objRetDet.getRadius());
			findBandListParams.addValue("SearchKey", objRetDet.getSearchKey());
			findBandListParams.addValue("PostalCode", objRetDet.getPostalCode());
			findBandListParams.addValue("HcBottomButtonId", objRetDet.getBottomBtnId());
			findBandListParams.addValue("MainMenuID", objRetDet.getMainMenuId());
			findBandListParams.addValue("SortColumn", objRetDet.getSortColumn());
			findBandListParams.addValue("SortOrder", objRetDet.getSortOrder());
			findBandListParams.addValue("HcCityID", objRetDet.getCityIds());
			findBandListParams.addValue("groupBy", objRetDet.getGroupBy());
			findBandListParams.addValue("UserConfiguredPostalCode", objRetDet.getUserPostalCode());
			findBandListParams.addValue("BandSubCategoryID", objRetDet.getCatIds());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(findBandListParams);

			if (null == resultFromProcedure.get("ErrorNumber")) {
				bandDetailslist = (List<RetailerDetail>) resultFromProcedure.get("bandDetails");
				bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

				objBandsDetails = new RetailersDetails();

				if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
					objBandsDetails.setBottomBtnList(bottomBtnList);
					objBandsDetails.setBottomBtn(1);
				} else {
					objBandsDetails.setBottomBtn(0);
				}

				final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
				boolean nxtPage = (Boolean) resultFromProcedure.get("NxtPageFlag");
				Integer nextPage = 0;
				if (nxtPage == true) {
					nextPage = 1;
				}

				if (null != bandDetailslist && !bandDetailslist.isEmpty()) {
					objBandsDetails.setRetailerDetail(bandDetailslist);
					objBandsDetails.setMaxRowNum(bandDetailslist.get(bandDetailslist.size()-1).getRowNumber());
					objBandsDetails.setMaxCnt(maxCnt);
					objBandsDetails.setNextPage(nextPage);

				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getBandList : usp_HcBandListPagination  : errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getBandList : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getBandList");
		return objBandsDetails;
	}

	/**
	 * The DAOImpl method fetches Band details details.
	 * 
	 * @param objBandDetail
	 * @return Band information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<RetailerDetail> getBandSummary(RetailerDetail objBandDetail) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getBandSummary");

		List<RetailerDetail> bandSummaryList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcBandSummaryDetail");
			simpleJdbcCall.returningResultSet("bandSummaryDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource bandParams = new MapSqlParameterSource();

			bandParams.addValue("UserID", objBandDetail.getUserId());
			bandParams.addValue("BandID", objBandDetail.getBandID());
			bandParams.addValue("Latitude", objBandDetail.getLatitude());
			bandParams.addValue("Longitude", objBandDetail.getLongitude());
			bandParams.addValue("MainMenuID", objBandDetail.getMainMenuId());
			bandParams.addValue("PostalCode", objBandDetail.getPostalCode());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(bandParams);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				bandSummaryList = (List<RetailerDetail>) resultFromProcedure.get("bandSummaryDetails");
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside BandDaoImpl : getBandSummary : errorNum : " + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getBandSummary  " + dae.getMessage());
			throw new HubCitiException(dae);
		}

		LOG.info("Exit BandDaoImpl : getBandSummary");
		return bandSummaryList;
	}

	/**
	 * The DAOImpl method fetches Band Anything pages details.
	 * 
	 * @param objBandDetail
	 * @return List of RetailerCreatedPages. 
	 * @throws HubCitiException
	 */
	public List<RetailerCreatedPages> getBandCreatePages(RetailerDetail objBandDetail) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getBandCreatePages");

		List<RetailerCreatedPages> bandCreatedPagesList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcQRDisplayBandCreatedPages");

			simpleJdbcCall.returningResultSet("bandCreatedPages", new BeanPropertyRowMapper<RetailerCreatedPages>(RetailerCreatedPages.class));

			final MapSqlParameterSource bandCrtdPagesParam = new MapSqlParameterSource();
			bandCrtdPagesParam.addValue("UserID", objBandDetail.getUserId());
			bandCrtdPagesParam.addValue("BandID", objBandDetail.getBandID());
			bandCrtdPagesParam.addValue("HcHubCitiID", objBandDetail.getHubCitiId());
			// For User tracking
			bandCrtdPagesParam.addValue("MainMenuID", objBandDetail.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(bandCrtdPagesParam);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				bandCreatedPagesList = (List<RetailerCreatedPages>) resultFromProcedure.get("bandCreatedPages");

				if (null != bandCreatedPagesList && !bandCreatedPagesList.isEmpty()) {

					final Boolean bEventExist = (Boolean) resultFromProcedure.get("EventExists");
					final Boolean bFundExist = (Boolean) resultFromProcedure.get("FundRaisingExists");

					if (null != bEventExist) {
						if (bEventExist) {
							bandCreatedPagesList.get(0).setEventExist(1);
						} else {
							bandCreatedPagesList.get(0).setEventExist(0);
						}
					}

					if (null != bFundExist) {
						if (bFundExist) {
							bandCreatedPagesList.get(0).setFundExist(1);
						} else {
							bandCreatedPagesList.get(0).setFundExist(0);
						}
					}
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getBandCreatePages : usp_HcQRDisplayBandCreatedPages ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getBandCreatePages : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getBandCreatePages");
		return bandCreatedPagesList;
	}

	/**
	 * DAOImpl method to fetch event list from database.
	 * 
	 * @param instance
	 *            of MenuItem.
	 * @return instance of EventDetails.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public EventDetails getEventList(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getEventList");

		List<EventDetails> eventDetailsList = null;
		EventDetails eventDetails = null;
		Integer maxRowNum = 0;
		List<BottomButton> bottomBtnList = null;
		try {
			final MapSqlParameterSource eventDetailParams = new MapSqlParameterSource();

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcBandEventsDisplay");
			simpleJdbcCall.returningResultSet("eventList", new BeanPropertyRowMapper<EventDetails>(EventDetails.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));
			
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
			eventDetailParams.addValue("CityIDs",objMenuItem.getCityIds()); //hub region changes

			/* Event display param */
			eventDetailParams.addValue("HcMenuItemID", objMenuItem.getmItemId());
			eventDetailParams.addValue("HcBottomButtonID", objMenuItem.getBottomBtnId());

			/* For Retailer event display param. */
			eventDetailParams.addValue("RetailID", objMenuItem.getBandId());
			eventDetailParams.addValue("RetailLocationID", objMenuItem.getRetailLocationId());
			eventDetailParams.addValue("FundRaisingID", objMenuItem.getFundId());
			eventDetailParams.addValue("EventDate", objMenuItem.getEvtDate());
			eventDetailParams.addValue("BandEventTypeID", objMenuItem.getEvtTypeID());
			eventDetailParams.addValue("Radius", objMenuItem.getRadius());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(eventDetailParams);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

					final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
					final Boolean nextPage = (Boolean) resultFromProcedure.get("NxtPageFlag");

					eventDetailsList = (List<EventDetails>) resultFromProcedure.get("eventList");

					if (eventDetailsList != null && !eventDetailsList.isEmpty()) {
						
						// To get max row number
						maxRowNum = eventDetailsList.get(eventDetailsList.size() - 1).getRowNum();

						// looping inside list to change date format.
						for (EventDetails objEventDetails : eventDetailsList) {
							objEventDetails.setIsDateFormated(false);
							objEventDetails.setIsTimeFormated(false);
							objEventDetails.setStartDate(objEventDetails.getStartDate());
							objEventDetails.setEndDate(objEventDetails.getEndDate());
							objEventDetails.setIsDateFormated(null);
							objEventDetails.setIsTimeFormated(null);
						}
						bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

						eventDetails = new EventDetails();

						if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
							eventDetails.setBottomBtnList(bottomBtnList);
							eventDetails.setBottomBtn(1);
						} else {
							eventDetails.setBottomBtn(0);
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
					LOG.error("Inside AlertEventDaoImpl : getEventList " + HubCitiConstants.SCHEMANAME + " : usp_HcBandEventsDisplay : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getEventList " + HubCitiConstants.SCHEMANAME + " : usp_HcBandEventsDisplay : " + dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getEventList");
		return eventDetails;
	}




	/**
	 * DAOImpl method for displaying News BookMark and Non BookMark catagories.
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return  containing BookMark and Non BookMark catagories array list .
	 * @throws HubCitiException throws if exception occurs.
	 */

	public CategoryDetails getNewsBookMarkCategories(News objNews) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getNewsBookMarkCategories");

		CategoryDetails objNewsBkCatgories = null;
		List<CategoryDetails> newsBkCatgoriesList;

		String strBookMark = null;
		String strSection = null;
		String bannerImg=null;
		String weatherUrl=null;
		String homeImgPath=null;
		String bkImagePath=null;
		String titleBkGrdColor=null;
		String hamburgerImg=null;
		

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_DisplayNewsFirstTopNavigationBookMarkBar");
			simpleJdbcCall.returningResultSet("getNewsBookMarkCatagories", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));

			final MapSqlParameterSource newsBkCategoriesParams = new MapSqlParameterSource();
			newsBkCategoriesParams.addValue("HcUserID", objNews.getUserId());
			newsBkCategoriesParams.addValue("HubCitiID", objNews.getHubCitiId());
			newsBkCategoriesParams.addValue("LinkID", objNews.getLinkId());
			newsBkCategoriesParams.addValue("LevelID", objNews.getLevel());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(newsBkCategoriesParams);

			Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (iErrorNum == null) {
				newsBkCatgoriesList = (List<CategoryDetails>) resultFromProcedure.get("getNewsBookMarkCatagories");
				
				bannerImg = (String) resultFromProcedure.get("bannerImg");
				weatherUrl = (String) resultFromProcedure.get("weatherurl");
				homeImgPath = (String) resultFromProcedure.get("homeImgPath");
				bkImagePath = (String) resultFromProcedure.get("bkImgPath");
				titleBkGrdColor=(String) resultFromProcedure.get("titleBkGrdColor");
				hamburgerImg=(String) resultFromProcedure.get("HamburgerImagePath");
				
				if (null != newsBkCatgoriesList  && !newsBkCatgoriesList.isEmpty()) {
					strBookMark = (String) resultFromProcedure.get("BookMark");
					strSection = (String) resultFromProcedure.get("Section");
					
					
					objNewsBkCatgories = new CategoryDetails();
					objNewsBkCatgories.setHeadOne(strBookMark);
					objNewsBkCatgories.setHeadTwo(strSection);
					objNewsBkCatgories.setBannerImg(bannerImg);
					objNewsBkCatgories.setWeatherURL(weatherUrl);
					objNewsBkCatgories.setHomeImgPath(homeImgPath);
					objNewsBkCatgories.setBkImgPath(bkImagePath);
					objNewsBkCatgories.setTitleBkGrdColor(titleBkGrdColor);
					objNewsBkCatgories.setHamburgerImg(hamburgerImg);
					objNewsBkCatgories.setListCatDetails(newsBkCatgoriesList);
				}else{
					objNewsBkCatgories = new CategoryDetails();
					objNewsBkCatgories.setBannerImg(bannerImg);
					objNewsBkCatgories.setWeatherURL(weatherUrl);
					objNewsBkCatgories.setHomeImgPath(homeImgPath);
					objNewsBkCatgories.setBkImgPath(bkImagePath);
					objNewsBkCatgories.setTitleBkGrdColor(titleBkGrdColor);
					objNewsBkCatgories.setHamburgerImg(hamburgerImg);
				}

			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getNewsBookMarkCategories : usp_HcPreferredNewsCategoryDisplay : errorNum..." + iErrorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getNewsBookMarkCategories : "+ dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}
		return objNewsBkCatgories;
	}

	/**
	 * DAOImpl method for Updating News BookMark and HubCiti Functionality.
	 * 
	 * @param objNews
	 * @return  Success/Failure  .
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String updateNewsBookMarkCategories(News objNews) throws HubCitiException {

		LOG.info("Inside BandDaoImpl : updateNewsBookMarkCategories");
		String response = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_UpdateNewsFirstSideNavigationAndBookmark");

			final MapSqlParameterSource updateNewsBkMarkParams = new MapSqlParameterSource();
			updateNewsBkMarkParams.addValue("HcuserID", objNews.getUserId());
			updateNewsBkMarkParams.addValue("HubcitiID", objNews.getHubCitiId());
			updateNewsBkMarkParams.addValue("BookmarkOrder", objNews.getBkMarkOrder());
			updateNewsBkMarkParams.addValue("NavigationID", objNews.getNavigOrder());
			updateNewsBkMarkParams.addValue("HcTemplateID", objNews.getTemplateId());

			updateNewsBkMarkParams.addValue("LinkID", objNews.getLinkId());
			updateNewsBkMarkParams.addValue("LevelID", objNews.getLevel());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(updateNewsBkMarkParams);


			final Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (status == 0) {
				response = HubCitiConstants.SUCCESSCODE;
			}

		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : updateNewsBookMarkCategories : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		} 

		LOG.info("Exit BandDaoImpl  : updateNewsBookMarkCategories");
		return response;
	}


	/**
	 * DAOImpl method for get News detail from news ID.
	 * 
	 * @param itemID
	 * @return  Success/Failure  .
	 * @throws HubCitiException throws if exception occurs.
	 */
	public List<Item> getNewsDetail(Integer newsID) throws HubCitiException {

		LOG.info("Inside BandDaoImpl : getNewsDetail");
		List<Item> newsDetailsList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_NewsFirstDetails");
			simpleJdbcCall.returningResultSet("getNewsDetail", new BeanPropertyRowMapper<Item>(Item.class));
			final MapSqlParameterSource newsDetailParam = new MapSqlParameterSource();
			newsDetailParam.addValue("RssFeedNewsID", newsID);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(newsDetailParam);


			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					newsDetailsList = (List<Item>) resultFromProcedure.get("getNewsDetail");
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside AlertEventDaoImpl : getNewsDetail " + HubCitiConstants.SCHEMANAME+ " : usp_HcFundraisingDetails :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}

		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getNewsDetail ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		} 

		LOG.info("Exit BandDaoImpl  : getNewsDetail");
		return newsDetailsList;
	}


	/**
	 * DAOImpl method for displaying both HubCiti Functionality and News categories.
	 * 
	 * @param userId
	 * @param hubCitiId
	 * @return  containing both HubCiti Functionality and News categories array list .
	 * @throws HubCitiException throws if exception occurs.
	 */

	public CategoryDetails getNewsSideNavigationMenu(Menu objMenu) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getNewsSideNavigationMenu");

		String strBannerImg = null;
		String strAppDownLink = null;
		String strAndroidLink = null;
		Boolean bDepartFlag = null;
		Boolean bTypeFlag = null;
		String retGroupImg = null;
		String appIconImg = null;
		Integer noOfColumns = null;
		Integer retAffCount = null;
		Integer retAffId = null;
		String retAffName = null;
		String menuName = null;
		Boolean bRegionApp = null;
		Boolean bTempChanged = null;

		String tempBkGrdImg = null;
		Boolean bDisplayLabel = null;

		String strBtnBkgrdColor = null;
		String strBtnLblColor = null;

		String bkImgPath = null;
		String homeImgPath = null;
		String titleTxtColor = null;
		String titleBkGrdColor = null;
		
		final CategoryDetails objNewsSideNavigatnDetails =  new CategoryDetails();
		List<MainCategory> newsCategoriesList = null;
		List<MenuItem> hcMenuItemList = new ArrayList<MenuItem>();
		Menu objMenuDisp = new Menu();

		if ("".equals(Utility.checkNull(objMenu.getSortOrder()))) {
			objMenu.setSortOrder("None");
		}

		if ("".equals(Utility.checkNull(objMenu.getDateCreated()))) {
			objMenu.setDateCreated(null);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiAddNewsFirstMenuDisplay");
			simpleJdbcCall.returningResultSet("menuDisplayDetails", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));
			simpleJdbcCall.returningResultSet("getNewsCatagories", new BeanPropertyRowMapper<MainCategory>(MainCategory.class));
			final MapSqlParameterSource newsSideNavigatnParams = new MapSqlParameterSource();

			newsSideNavigatnParams.addValue("HubCitiID", objMenu.getHubCitiId());
			newsSideNavigatnParams.addValue("LevelID", objMenu.getLevel());
			newsSideNavigatnParams.addValue("LinkID", objMenu.getLinkId());
			newsSideNavigatnParams.addValue("SortOrder", objMenu.getSortOrder());
			newsSideNavigatnParams.addValue("DepartmentID", objMenu.getDepartmentId());
			newsSideNavigatnParams.addValue("TypeID", objMenu.getTypeId());
			newsSideNavigatnParams.addValue("HcCityID", objMenu.getCityIds());
			newsSideNavigatnParams.addValue("DateCheck", objMenu.getDateCreated());
			newsSideNavigatnParams.addValue("DeviceType", objMenu.getDevType());
			newsSideNavigatnParams.addValue("UserID", objMenu.getUserId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(newsSideNavigatnParams);

			Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (iErrorNum == null) {
				hcMenuItemList = (List<MenuItem>) resultFromProcedure.get("menuDisplayDetails");

				bTempChanged = (Boolean) resultFromProcedure.get("TemplateChanged");
				if (null != bTempChanged) {
					if (bTempChanged) {
						objMenuDisp.setIsTempChanged(1);
					} else {
						objMenuDisp.setIsTempChanged(0);
					}
				}

				newsCategoriesList = (List<MainCategory>) resultFromProcedure.get("getNewsCatagories");
				if (null != newsCategoriesList  && !newsCategoriesList.isEmpty()) {

					objNewsSideNavigatnDetails.setListMainCat(newsCategoriesList);
				}

				if (hcMenuItemList != null && !hcMenuItemList.isEmpty()) {

					strBannerImg = (String) resultFromProcedure.get("HCMenuBannerImage");
					strAppDownLink = (String) resultFromProcedure.get("DownLoadLinkIOS");
					strAndroidLink = (String) resultFromProcedure.get("DownLoadLinkAndroid");
					bDepartFlag = (Boolean) resultFromProcedure.get("HcDepartmentFlag");
					bTypeFlag = (Boolean) resultFromProcedure.get("HcTypeFlag");
					retGroupImg = (String) resultFromProcedure.get("RetailGroupButtonImagePath");
					appIconImg = (String) resultFromProcedure.get("AppIconImagePath");
					noOfColumns = (Integer) resultFromProcedure.get("NoOfColumns");
					retAffCount = (Integer) resultFromProcedure.get("FilterCount");
					retAffId = (Integer) resultFromProcedure.get("FilterID");
					retAffName = (String) resultFromProcedure.get("FilterName");
					menuName = (String) resultFromProcedure.get("MenuName");
					bRegionApp = (Boolean) resultFromProcedure.get("IsRegionApp");

					tempBkGrdImg = (String) resultFromProcedure.get("TempleteBackgroundImage");
					bDisplayLabel = (Boolean) resultFromProcedure.get("DisplayLabel");

					strBtnBkgrdColor = (String) resultFromProcedure.get("LabelBckGndColor");
					strBtnLblColor = (String) resultFromProcedure.get("LabelFontColor");

					homeImgPath = (String) resultFromProcedure.get("homeImgPath");
					bkImgPath = (String) resultFromProcedure.get("bkImgPath");
					titleBkGrdColor = (String) resultFromProcedure.get("titleBkGrdColor");
					titleTxtColor = (String) resultFromProcedure.get("titleTxtColor");

					objMenuDisp.setMenuId(hcMenuItemList.get(0).getMenuId());
					objMenuDisp.setHubCitiId(hcMenuItemList.get(0).getHubCitiId());
					objMenuDisp.setTemplateName(hcMenuItemList.get(0).getTemplateName());
					objMenuDisp.setLevel(hcMenuItemList.get(0).getLevel());
					objMenuDisp.setmBkgrdColor(hcMenuItemList.get(0).getmBkgrdColor());
					objMenuDisp.setmBkgrdImage(hcMenuItemList.get(0).getmBkgrdImage());
					objMenuDisp.setSmBkgrdColor(hcMenuItemList.get(0).getSmBkgrdColor());
					objMenuDisp.setSmBkgrdImage(hcMenuItemList.get(0).getSmBkgrdImage());
					objMenuDisp.setmFontColor(hcMenuItemList.get(0).getmFontColor());
					objMenuDisp.setSmFontColor(hcMenuItemList.get(0).getSmFontColor());
					objMenuDisp.setDateModified(hcMenuItemList.get(0).getDateModified());
					objMenuDisp.setTemplateBgColor(hcMenuItemList.get(0).getTemplateBgColor());

					objMenuDisp.setDownLoadLink(strAppDownLink);
					objMenuDisp.setRetGroupImg(retGroupImg);
					objMenuDisp.setAndroidLink(strAndroidLink);
					objMenuDisp.setAppIconImg(appIconImg);
					objMenuDisp.setNoOfColumns(noOfColumns);
					objMenuDisp.setRetAffCount(retAffCount);
					objMenuDisp.setRetAffId(retAffId);
					objMenuDisp.setRetAffName(retAffName);

					if (null != bRegionApp) {
						if (bRegionApp) {
							objMenuDisp.setIsRegApp(1);
						} else {
							objMenuDisp.setIsRegApp(0);
						}
					}

					if (!"".equals(Utility.checkNull(menuName))) {
						objMenuDisp.setMenuName(menuName);
					}

					for (int i = 0; i < hcMenuItemList.size(); i++) {
						hcMenuItemList.get(i).setMenuId(null);
						hcMenuItemList.get(i).setHubCitiId(null);
						hcMenuItemList.get(i).setTemplateName(null);
						hcMenuItemList.get(i).setLevel(null);
						hcMenuItemList.get(i).setSmBkgrdImage(null);
						hcMenuItemList.get(i).setSmBkgrdColor(null);
						hcMenuItemList.get(i).setmBkgrdImage(null);
						hcMenuItemList.get(i).setmBkgrdColor(null);
						hcMenuItemList.get(i).setmFontColor(null);
						hcMenuItemList.get(i).setSmFontColor(null);
						hcMenuItemList.get(i).setDateModified(null);
						hcMenuItemList.get(i).setTemplateBgColor(null);
					}

					objMenuDisp.setArMItemList(hcMenuItemList);

					List <Menu> menuList = new ArrayList<Menu>();
					menuList.add(objMenuDisp);

					objNewsSideNavigatnDetails.setMenuList(menuList);
					if (!"".equals(Utility.checkNull(strBannerImg))) {
						objMenuDisp.setmBannerImg(strBannerImg);
					}

					if (null != bDepartFlag) {
						if (bDepartFlag) {
							objMenuDisp.setDepartFlag(1);
						} else {
							objMenuDisp.setDepartFlag(0);
						}
					}
					if (null != bTypeFlag) {
						if (bTypeFlag) {
							objMenuDisp.setTypeFlag(1);
						} else {
							objMenuDisp.setTypeFlag(0);
						}
					}

					objMenuDisp.setTempBkgrdImg(tempBkGrdImg);
					objMenuDisp.setBtnBkgrdColor(strBtnBkgrdColor);
					objMenuDisp.setBtnLblColor(strBtnLblColor);

					objMenuDisp.setHomeImgPath(homeImgPath);
					objMenuDisp.setBkImgPath(bkImgPath);
					objMenuDisp.setTitleBkGrdColor(titleBkGrdColor);
					objMenuDisp.setTitleTxtColor(titleTxtColor);

					if (null != bDisplayLabel) {
						if (bDisplayLabel) {
							objMenuDisp.setIsDisplayLabel(1);
						} else {
							objMenuDisp.setIsDisplayLabel(0);
						}
					}

				} else {
					objMenuDisp.setNoRecordMsg(Utility.clobToString((Clob) resultFromProcedure.get("NoRecordsMsg")));
				}

			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getNewsSideNavigationMenu : usp_HcHubCitiAddNewsFirstMenuDisplay : errorNum..." + iErrorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getNewsSideNavigationMenu : "+ dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		} 
		return objNewsSideNavigatnDetails;
	}


	/**
	 * The DAOImpl method for fetching News List by Category's.
	 * 
	 * @param instance of objItem.
	 * @return Json News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public Item getNewsListByCategories(Item objItem) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getNewsListByCategories");

		Item objItems = null;
		List<Item> newsByCagatoriesList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_NewsFirstCategoryDisplay");
			simpleJdbcCall.returningResultSet("newsFeedList", new BeanPropertyRowMapper<Item>(Item.class));

			final MapSqlParameterSource newsFeedParams = new MapSqlParameterSource();

			newsFeedParams.addValue("NewsType", objItem.getCatName());
			newsFeedParams.addValue("HubCitiID", objItem.getHubCitiID());
			newsFeedParams.addValue("SearchKey", objItem.getSearchKey());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(newsFeedParams);

			if (null == resultFromProcedure.get("ErrorNumber")) {
				newsByCagatoriesList = (List<Item>) resultFromProcedure.get("newsFeedList");

				objItems = new Item();
				if (null != newsByCagatoriesList && !newsByCagatoriesList.isEmpty()) {
					objItems.setItems(newsByCagatoriesList);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getNewsListByCategories : usp_NewsFirstNewsDisplay  : errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getNewsListByCategories : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getNewsListByCategories");
		return objItems;
	}



	/**
	 * The DAOImpl method for fetching Combinational News  List by Category's information.
	 * 
	 * @param instance of objItem.
	 * @return Json Combination pattern News  List by Category's information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public Item getCombNewsListByCategories(Item objItem) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getCombNewsListByCategories");

		Item objCombNewsTemplate = null;
		List<Item> combNewsList = null;
		Integer iMaxCount = 0;
		boolean bNxtPageFlag = false;
		Integer iLowerLimitFlag = 0;
		String strNewBannerImg = null;
		Integer templateChanged;
		String templateName = null;
		String modifiedDate = null;
		String strWeatherURL = null;
		
		String bkImgPath = null;
		String homeImgPath = null;
		String titleTxtColor = null;
		String titleBkGrdColor = null;
		String hamburgerImg = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_NewsFirstCombinationalTempleteNewsDisplay");
			simpleJdbcCall.returningResultSet("combinationalTempleteNewsDisplay", new BeanPropertyRowMapper<Item>(Item.class));

			final MapSqlParameterSource comboNewsParams = new MapSqlParameterSource();

			comboNewsParams.addValue("HubCitiID", objItem.getHubCitiId());
			comboNewsParams.addValue("SearchKey", objItem.getSearchKey());
			comboNewsParams.addValue("LowerLimit", objItem.getLowerLimit());
			comboNewsParams.addValue("HcUserID", objItem.getUserId());
			comboNewsParams.addValue("DateCheck", objItem.getDateCreated());
			comboNewsParams.addValue("LinkID", objItem.getLinkId());
			comboNewsParams.addValue("level", objItem.getLevel());
			comboNewsParams.addValue("ScreenName", HubCitiConstants.COMB_NEWS_FIRST_SCREENNAME);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(comboNewsParams);

			templateChanged = (Integer) resultFromProcedure.get("TemplateChanged");
			templateName = (String) resultFromProcedure.get("TemplateName");
			modifiedDate = (String) resultFromProcedure.get("ModifiedDate");
			strWeatherURL = (String) resultFromProcedure.get("weatherurl");
			
			homeImgPath = (String) resultFromProcedure.get("homeImgPath");
			bkImgPath = (String) resultFromProcedure.get("bkImgPath");
			titleBkGrdColor = (String) resultFromProcedure.get("titleBkGrdColor");
			titleTxtColor = (String) resultFromProcedure.get("titleTxtColor");
			hamburgerImg = (String) resultFromProcedure.get("HamburgerImagePath");


			if (null == resultFromProcedure.get("ErrorNumber")) {
				combNewsList = (List<Item>) resultFromProcedure.get("combinationalTempleteNewsDisplay");

				objCombNewsTemplate = new Item();
				objCombNewsTemplate.setTemplateChanged(templateChanged);
				objCombNewsTemplate.setNewtempName(templateName);
				objCombNewsTemplate.setModifiedDate(modifiedDate);
				objCombNewsTemplate.setWeatherURL(strWeatherURL);
				objCombNewsTemplate.setHomeImgPath(homeImgPath);
				objCombNewsTemplate.setBkImgPath(bkImgPath);
				objCombNewsTemplate.setTitleBkGrdColor(titleBkGrdColor);
				objCombNewsTemplate.setTitleTxtColor(titleTxtColor);
				objCombNewsTemplate.setHamburgerImg(hamburgerImg);
				
				iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
				if(resultFromProcedure.get("NxtPageFlag") != null)
					bNxtPageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");
				iLowerLimitFlag = (Integer) resultFromProcedure.get("LowerLimitFlag");
				strNewBannerImg = (String) resultFromProcedure.get("bannerImg");

				if (null != combNewsList && !combNewsList.isEmpty()) {

					objCombNewsTemplate.setItems(combNewsList);
					objCombNewsTemplate.setMaxCnt(iMaxCount);

					if (bNxtPageFlag == false) {
						objCombNewsTemplate.setNextPage(0);
					} else {
						objCombNewsTemplate.setNextPage(1);
					}

					objCombNewsTemplate.setLowerLimitFlag(iLowerLimitFlag);
					objCombNewsTemplate.setBannerImg(strNewBannerImg);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getCombNewsListByCategories : usp_NewsFirstCombinationalTempleteNewsDisplay  : errorNum : " + errorNum + "errorMsg : " + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getCombNewsListByCategories : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getCombNewsListByCategories");
		return objCombNewsTemplate;
	}


	/**
	 * The DAOImpl method for fetching Scrolling News List by Category's information.
	 * 
	 * @param instance of objItem.
	 * @return Json Scrolling pattern News  List by Category's information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public Item getScrollingNewsListByCategories(Item objItem) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getScrollingNewsListByCategories");

		Item objScrollingNewsTemplate = null;
		List<Item> scrolNewsList = null;
		Integer iMaxCount = 0;
		Boolean bNxtPageFlag = null; 
		String strNewBannerImg = null;
		String templateName = null;
		String strSubPageName = null;
		Integer templateChanged = null;
		String modifiedDate = null;
		String strWeatherURL = null;

		String bkImgPath = null;
		String homeImgPath = null;
		String titleTxtColor = null;
		String titleBkGrdColor = null;
		String hamburgerImg = null;
		String backButtonColor = null;
		
		if (null == objItem.getIsSideBar()) {
			objItem.setIsSideBar(0);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_NewsFirstScrollingNewsDisplay");
			simpleJdbcCall.returningResultSet("scrollingTempleteNewsDisplay", new BeanPropertyRowMapper<Item>(Item.class));

			final MapSqlParameterSource scrolNewsParams = new MapSqlParameterSource();

			scrolNewsParams.addValue("CategoryType", objItem.getCatName());
			scrolNewsParams.addValue("HubCitiID", objItem.getHubCitiId());
			scrolNewsParams.addValue("SearchKey", objItem.getSearchKey());
			scrolNewsParams.addValue("LowerLimit", objItem.getLowerLimit());
			scrolNewsParams.addValue("HcUserID", objItem.getUserId());
			scrolNewsParams.addValue("isSideNavigation", objItem.getIsSideBar());
			scrolNewsParams.addValue("DateCheck", objItem.getDateCreated());
			scrolNewsParams.addValue("LinkID", objItem.getLinkId());
			scrolNewsParams.addValue("level", objItem.getLevel());
			scrolNewsParams.addValue("ScreenName", HubCitiConstants.SCROL_NEWS_FIRST_SCREENNAME);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scrolNewsParams);

			templateChanged = (Integer) resultFromProcedure.get("TemplateChanged");
			templateName = (String) resultFromProcedure.get("TemplateName");
			modifiedDate = (String) resultFromProcedure.get("ModifiedDate");
			strWeatherURL = (String) resultFromProcedure.get("weatherurl");
			homeImgPath = (String) resultFromProcedure.get("homeImgPath");
			bkImgPath = (String) resultFromProcedure.get("bkImgPath");
			titleBkGrdColor = (String) resultFromProcedure.get("titleBkGrdColor");
			titleTxtColor = (String) resultFromProcedure.get("titleTxtColor");
			hamburgerImg = (String) resultFromProcedure.get("HamburgerImagePath");
			backButtonColor = (String) resultFromProcedure.get("backButtonColor");

			if (null == resultFromProcedure.get("ErrorNumber")) {
				scrolNewsList = (List<Item>) resultFromProcedure.get("scrollingTempleteNewsDisplay");

				objScrollingNewsTemplate = new Item();
				objScrollingNewsTemplate.setTemplateChanged(templateChanged);
				objScrollingNewsTemplate.setNewtempName(templateName);
				objScrollingNewsTemplate.setModifiedDate(modifiedDate);
				objScrollingNewsTemplate.setWeatherURL(strWeatherURL);
				
				objScrollingNewsTemplate.setHomeImgPath(homeImgPath);
				objScrollingNewsTemplate.setBkImgPath(bkImgPath);
				objScrollingNewsTemplate.setTitleBkGrdColor(titleBkGrdColor);
				objScrollingNewsTemplate.setTitleTxtColor(titleTxtColor);
				objScrollingNewsTemplate.setHamburgerImg(hamburgerImg);
				objScrollingNewsTemplate.setBackButtonColor(backButtonColor);
				iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
				if (null != scrolNewsList && !scrolNewsList.isEmpty()) {

					objScrollingNewsTemplate.setItems(scrolNewsList);

					
					bNxtPageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");
					strNewBannerImg = (String) resultFromProcedure.get("bannerImg");
					strSubPageName = (String) resultFromProcedure.get("SubPage");
					
					objScrollingNewsTemplate.setMaxCnt(iMaxCount);

					if (bNxtPageFlag != null && bNxtPageFlag == true) {
						objScrollingNewsTemplate.setNextPage(1);
					} else {
						objScrollingNewsTemplate.setNextPage(0);
					}

					objScrollingNewsTemplate.setLowerLimitFlag(scrolNewsList.get(scrolNewsList.size() - 1).getRowNum());
					objScrollingNewsTemplate.setBannerImg(strNewBannerImg);
					objScrollingNewsTemplate.setSubPageName(strSubPageName);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getScrollingNewsListByCategories : usp_NewsFirstScrollingNewsDisplay  : errorNum : " + errorNum + "errorMsg : " + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getScrollingNewsListByCategories : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getScrollingNewsListByCategories");
		return objScrollingNewsTemplate;
	}


	/**
	 * The DAOImpl method for displaying all News  Category's.
	 * 
	 * @param instance of News.
	 * @return Json News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public CategoryDetails getAllNewsCategories(News objNews) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getAllNewsCategories");

		List<CategoryDetails> newsCatList = null;
		CategoryDetails objCatDetails =  null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_NewsFirstCategoryDisplay");
			simpleJdbcCall.returningResultSet("getAllNewsCatagories", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));

			final MapSqlParameterSource newsFeedParams = new MapSqlParameterSource();

			newsFeedParams.addValue("HcUserID", objNews.getUserId());
			newsFeedParams.addValue("HubCitiID", objNews.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(newsFeedParams);

			if (null == resultFromProcedure.get("ErrorNumber")) {
				newsCatList = (List<CategoryDetails>) resultFromProcedure.get("getAllNewsCatagories");;

				if (null != newsCatList  && !newsCatList.isEmpty()) {
					objCatDetails = new CategoryDetails();
					objCatDetails.setListCatDetails(newsCatList);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getAllNewsCategories : usp_NewsFirstCategoryDisplay  : errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getAllNewsCategories : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getAllNewsCategories");
		return objCatDetails;
	}


	/**
	 * DAOImpl method for displaying News BookMark and HubCiti Functionality.
	 * 
	 * @param instance of News.
	 * @return  containing News BookMark and HubCiti Functionality list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public CategoryDetails getNewsBookMarkAndHubCitiFunctn(News objNews) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getNewsBookMarkAndHubCitiFunctn");

		CategoryDetails objNewsBkMkAndHubCitiMenu = null;
		List<CategoryDetails> newsCategoryHubCitiFunctnlist;

		String strBookMark = null;
		String strSection = null;
		Integer iErrorNum = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			
			if (null == objNews.getSideNaviPersonalizatn() || !objNews.getSideNaviPersonalizatn()) {
				simpleJdbcCall.withProcedureName("usp_DisplayNewsFirstSideNavigation");
			} else {
				simpleJdbcCall.withProcedureName("usp_DisplaySingleNewsFirstSideNavigation");
			}
			simpleJdbcCall.returningResultSet("getNewsBookMarkandHubCitiFunctn", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));
			final MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
			sqlParameters.addValue("HcUserID", objNews.getUserId());
			sqlParameters.addValue("HubCitiID", objNews.getHubCitiId());
			sqlParameters.addValue("LinkID", objNews.getLinkId());
			sqlParameters.addValue("LevelID", objNews.getLevel());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(sqlParameters);

			iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (iErrorNum == null) {
				
				newsCategoryHubCitiFunctnlist = (List<CategoryDetails>) resultFromProcedure.get("getNewsBookMarkandHubCitiFunctn");
				
				if (null != newsCategoryHubCitiFunctnlist  && !newsCategoryHubCitiFunctnlist.isEmpty()) {
					strBookMark = (String) resultFromProcedure.get("BookMark");
					strSection = (String) resultFromProcedure.get("Section");

					objNewsBkMkAndHubCitiMenu = new CategoryDetails();
					objNewsBkMkAndHubCitiMenu.setHeadOne(strBookMark);
					objNewsBkMkAndHubCitiMenu.setHeadTwo(strSection);
					objNewsBkMkAndHubCitiMenu.setListCatDetails(newsCategoryHubCitiFunctnlist);
				}

			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getNewsBookMarkAndHubCitiFunctn : usp_DisplayNewsFirstSideNavigation : errorNum..." + iErrorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getNewsBookMarkAndHubCitiFunctn : "+ dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		} 
		return objNewsBkMkAndHubCitiMenu;
	}


	/**
	 * DAOImpl method for displaying All News Sub categories.
	 * 
	 * @param userId
	 * @param categoryID
	 * @return  containing All News Sub categories arrayList .
	 * @throws HubCitiException throws if exception occurs.
	 */
	public List<CategoryDetails> getAllNewsSubCategoriesByCatID(Integer userId, String catName) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getAllNewsSubCategoriesByCatID");

		List<CategoryDetails> newsSubCatgoriesList;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiNewsFirstUserCategoryDetails");
			simpleJdbcCall.returningResultSet("getAllNewsSubCategoriesByCatID", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));

			final MapSqlParameterSource newsBkCategoriesParams = new MapSqlParameterSource();
			newsBkCategoriesParams.addValue("UserID", userId);
			newsBkCategoriesParams.addValue("CategoryName", catName);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(newsBkCategoriesParams);

			Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (iErrorNum == null) {
				newsSubCatgoriesList = (List<CategoryDetails>) resultFromProcedure.get("getAllNewsSubCategoriesByCatID");
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getAllNewsSubCategoriesByCatID : usp_HcHubCitiNewsFirstUserCategoryDetails : errorNum..." + iErrorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getAllNewsSubCategoriesByCatID : "+ dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		} 
		return newsSubCatgoriesList;
	}


	/**
	 * The DAOImpl method for fetching Block News List by Category's information.
	 * 
	 * @param instance of objItem.
	 * @return Json Block Pattern News  List by Category's information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public Item getBlockNewsListByCategories(Item objItem) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getBlockNewsListByCategories");

		Item objBlockNewsTemplate = null;
		List<Item> blockNewsList = null;

		String templateName = null;
		Integer templateChanged = null;
		String modifiedDate = null;
		String strNewBannerImg = null;
		String strWeatherURL = null;
  		String bkImgPath = null;
		String homeImgPath = null;
		String titleTxtColor = null;
		String titleBkGrdColor = null;
		String hamburgerImg = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_NewsFirstBlockTempleteNewsDisplay");
			simpleJdbcCall.returningResultSet("blockTempleteNewsDisplay", new BeanPropertyRowMapper<Item>(Item.class));

			final MapSqlParameterSource blockNewsParams = new MapSqlParameterSource();

			blockNewsParams.addValue("HubcitiID", objItem.getHubCitiId());
			blockNewsParams.addValue("LowerLimit", objItem.getLowerLimit());
			blockNewsParams.addValue("HcuserID", objItem.getUserId());
			blockNewsParams.addValue("DateCheck", objItem.getDateCreated());
			blockNewsParams.addValue("LinkID", objItem.getLinkId());
			blockNewsParams.addValue("level", objItem.getLevel());
			blockNewsParams.addValue("ScreenName", HubCitiConstants.BLOCK_NEWS_FIRST_SCREENNAME);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(blockNewsParams);

			templateChanged = (Integer) resultFromProcedure.get("TemplateChanged");
			templateName = (String) resultFromProcedure.get("TemplateName");
			modifiedDate = (String) resultFromProcedure.get("ModifiedDate");
			strNewBannerImg = (String) resultFromProcedure.get("bannerImg");
			strWeatherURL = (String) resultFromProcedure.get("weatherurl");
			homeImgPath = (String) resultFromProcedure.get("homeImgPath");
			bkImgPath = (String) resultFromProcedure.get("bkImgPath");
			titleBkGrdColor = (String) resultFromProcedure.get("titleBkGrdColor");
			titleTxtColor = (String) resultFromProcedure.get("titleTxtColor");
			hamburgerImg = (String) resultFromProcedure.get("HamburgerImagePath");

			if (null == resultFromProcedure.get("ErrorNumber")) {
				blockNewsList = (List<Item>) resultFromProcedure.get("blockTempleteNewsDisplay");

				objBlockNewsTemplate = new Item();
				objBlockNewsTemplate.setTemplateChanged(templateChanged);
				objBlockNewsTemplate.setNewtempName(templateName);
				objBlockNewsTemplate.setModifiedDate(modifiedDate);
				objBlockNewsTemplate.setBannerImg(strNewBannerImg);
				objBlockNewsTemplate.setWeatherURL(strWeatherURL);
				
				objBlockNewsTemplate.setHomeImgPath(homeImgPath);
				objBlockNewsTemplate.setBkImgPath(bkImgPath);
				objBlockNewsTemplate.setTitleBkGrdColor(titleBkGrdColor);
				objBlockNewsTemplate.setTitleTxtColor(titleTxtColor);
				objBlockNewsTemplate.setHamburgerImg(hamburgerImg);
				
				if (null != blockNewsList && !blockNewsList.isEmpty()) {
					objBlockNewsTemplate.setItems(blockNewsList);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getBlockNewsListByCategories : usp_NewsFirstBlockTempleteNewsDisplay  : errorNum : " + errorNum + "errorMsg : " + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getBlockNewsListByCategories : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getBlockNewsListByCategories");
		return objBlockNewsTemplate;
	}
	
	
	/**
	 * The DAOImpl method is for fetching Event Band List Information details.
	 * 
	 * @param instance of RetailerDetail.
	 * @return List of Band.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public RetailersDetails getEventBandsList(RetailerDetail objRetDet) throws HubCitiException {
		LOG.info("Inside BandDaoImpl : getEventBandsList");

		RetailersDetails objBandsDetails = null;
		List<RetailerDetail> bandDetailslist = null;
		List<BottomButton> bottomBtnList = null;

		if (null == objRetDet.getLowerLimit()) {
			objRetDet.setLowerLimit(0);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcBandEventsAssociationDisplay");
			simpleJdbcCall.returningResultSet("eventBandDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource findBandListParams = new MapSqlParameterSource();

			findBandListParams.addValue("HubCitiID", objRetDet.getHubCitiId());
			findBandListParams.addValue("HCHubCitiID", objRetDet.getHubCitiId());
			findBandListParams.addValue("LowerLimit", objRetDet.getLowerLimit());
			findBandListParams.addValue("ScreenName", HubCitiConstants.EVENTBANDS_SCREENNAME);
			findBandListParams.addValue("BandEventID", objRetDet.getbEvtId());
			findBandListParams.addValue("HcuserID", objRetDet.getUserId());
			findBandListParams.addValue("UserID", objRetDet.getUserId());
			
			/*findBandListParams.addValue("Radius", objRetDet.getRadius());
			findBandListParams.addValue("SearchKey", objRetDet.getSearchKey());
			findBandListParams.addValue("PostalCode", objRetDet.getPostalCode());
			findBandListParams.addValue("HcBottomButtonId", objRetDet.getBottomBtnId());
			findBandListParams.addValue("MainMenuID", objRetDet.getMainMenuId());
			findBandListParams.addValue("SortColumn", objRetDet.getSortColumn());
			findBandListParams.addValue("SortOrder", objRetDet.getSortOrder());
			findBandListParams.addValue("HcCityID", objRetDet.getCityIds());
			findBandListParams.addValue("groupBy", objRetDet.getGroupBy());
			findBandListParams.addValue("UserConfiguredPostalCode", objRetDet.getUserPostalCode());
			findBandListParams.addValue("BandSubCategoryID", objRetDet.getCatIds());*/

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(findBandListParams);

			if (null == resultFromProcedure.get("ErrorNumber")) {
				bandDetailslist = (List<RetailerDetail>) resultFromProcedure.get("eventBandDetails");
				bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

				objBandsDetails = new RetailersDetails();

				if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
					objBandsDetails.setBottomBtnList(bottomBtnList);
					objBandsDetails.setBottomBtn(1);
				} else {
					objBandsDetails.setBottomBtn(0);
				}

				final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
				boolean nxtPage = (Boolean) resultFromProcedure.get("NxtPageFlag");
				Integer nextPage = 0;
				if (nxtPage == true) {
					nextPage = 1;
				}

				if (null != bandDetailslist && !bandDetailslist.isEmpty()) {
					objBandsDetails.setRetailerDetail(bandDetailslist);
					objBandsDetails.setMaxRowNum(bandDetailslist.get(bandDetailslist.size()-1).getRowNum());
					objBandsDetails.setMaxCnt(maxCnt);
					objBandsDetails.setNextPage(nextPage);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside BandDaoImpl : getEventBandsList : usp_HcEventBandListPagination  : errorNum..." + errorNum + "errorMsg.." + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException dae) {
			LOG.error("Inside BandDaoImpl : getEventBandsList : ", dae.getMessage());
			throw new HubCitiException(dae.getMessage());
		}

		LOG.info("Exit BandDaoImpl : getEventBandsList");
		return objBandsDetails;
	}
}
