package com.hubciti.hotdeals.dao;

import java.text.ParseException;
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
import com.hubciti.common.pojos.Data;
import com.hubciti.common.pojos.Deal;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.HotDealsCategoryInfo;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.HotDealsListRequest;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.HotDealsResultSet;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.RetailerTrack;
import com.hubciti.common.utility.Utility;
@SuppressWarnings({"unchecked" })
public class HotDealsDaoImpl implements HotDealsDao {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(HotDealsDaoImpl.class);

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
	 * This DAO Implementation method fetches each hot deals information.
	 * 
	 * @param userId
	 *            as query parameter
	 * @param hotDealId
	 *            as query parameter
	 * @return HotDealsListResponse
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public HotDealsCategoryInfo getHotDealDetail(HotDealsDetails objHotDealsDetails) throws HubCitiException {
		LOG.info("Inside HotDealsDaoImpl : getHotDealDetail ");
		
		final List<HotDealsDetails> hotDealsListResponselst;
		
		HotDealsCategoryInfo hotDealsCategoryInfolst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHotDealDetails");
			simpleJdbcCall.returningResultSet("HotDealDetails", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));

			final MapSqlParameterSource fetchProductDetailsParameters = new MapSqlParameterSource();
			fetchProductDetailsParameters.addValue("UserID", objHotDealsDetails.getUserId());
			fetchProductDetailsParameters.addValue("HotDealID", objHotDealsDetails.getHotDealId());
			fetchProductDetailsParameters.addValue("HubCitiID", objHotDealsDetails.getHubCitiId());
			// For user tracking
			fetchProductDetailsParameters.addValue("HotDealListID", objHotDealsDetails.getHotDealListId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchProductDetailsParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					hotDealsListResponselst = (List<HotDealsDetails>) resultFromProcedure.get("HotDealDetails");
					if (null != hotDealsListResponselst && !hotDealsListResponselst.isEmpty()) {
						hotDealsCategoryInfolst = new HotDealsCategoryInfo();
						hotDealsCategoryInfolst.setHotDealsDetailsArrayLst((ArrayList<HotDealsDetails>) hotDealsListResponselst);
					} else {
						return hotDealsCategoryInfolst;
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside HotDealsDaoImpl : getHotDealDetail : " + errorNum + " and error message: " + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside HotDealsDaoImpl : getHotDealDetail :  ", exception);
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit HotDealsDaoImpl : getHotDealDetail ");
		return hotDealsCategoryInfolst;
	}

	/**
	 * This DAO Implementation fir method fetches hotDeals list.
	 * 
	 * @param hotDealsListRequest
	 *            of HotDealsListRequest
	 * @param screenName
	 *            as query parameter.
	 * @return HotDealsListResultSet
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public HotDealsListResultSet getHotDealslist(HotDealsListRequest hotDealsListRequest, String screenName) throws HubCitiException {
		LOG.info("Inside HotDealsDaoImpl : getHotDealslist ");
		
		List<HotDealsResultSet> hotDealslist = null;
		//List<Category> categoryList = null;
		HotDealsListResultSet hotDealsListResultSet = null;
		List<BottomButton> bottomBtnList = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHotDealSearchPagination");
			
			simpleJdbcCall.returningResultSet("hotDealslst", new BeanPropertyRowMapper<HotDealsResultSet>(HotDealsResultSet.class));
			//simpleJdbcCall.returningResultSet("categoryList", new BeanPropertyRowMapper<Category>(Category.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));
			
			final MapSqlParameterSource fetchHotDealsParam = new MapSqlParameterSource();
			fetchHotDealsParam.addValue(HubCitiConstants.USERID, hotDealsListRequest.getUserId());
			fetchHotDealsParam.addValue("Search", hotDealsListRequest.getSearchItem());
			fetchHotDealsParam.addValue("ScreenName", screenName);
			fetchHotDealsParam.addValue("Category", hotDealsListRequest.getCategory());
			fetchHotDealsParam.addValue("LowerLimit", hotDealsListRequest.getLastVisitedProductNo());
			fetchHotDealsParam.addValue("HubCitiID", hotDealsListRequest.getHubCitiId());
			fetchHotDealsParam.addValue("Latitude", hotDealsListRequest.getLatitude());
			fetchHotDealsParam.addValue("Longitude", hotDealsListRequest.getLongitude());
			fetchHotDealsParam.addValue("PostalCode", hotDealsListRequest.getZipCode());
			fetchHotDealsParam.addValue(HubCitiConstants.ERRORNUMBER, null);
			fetchHotDealsParam.addValue(HubCitiConstants.ERRORMESSAGE, null);

			// For user tracking
			fetchHotDealsParam.addValue("MainMenuID", hotDealsListRequest.getMainMenuId());

			final Map<String, Object> resFromProcedure = simpleJdbcCall.execute(fetchHotDealsParam);
			
			if (null != resFromProcedure) {
				hotDealsListResultSet = new HotDealsListResultSet();
				
				if (null == resFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					
					hotDealslist = (List<HotDealsResultSet>) resFromProcedure.get("hotDealslst");
					//categoryList = (List<Category>) resFromProcedure.get("categoryList");
					bottomBtnList = (List<BottomButton>) resFromProcedure.get("bottomButtonDetails");
					
					final Boolean favCat = (Boolean) resFromProcedure.get("FavCatFlag");
					final Boolean categoryFlag = (Boolean) resFromProcedure.get("ByCategoryFlag");
					
					
					if (null != hotDealslist && !hotDealslist.isEmpty()) {
						hotDealsListResultSet.setHotDealsListResponselst(hotDealslist);
						//hotDealsListResultSet.setCategory(categoryList);
						
						final Boolean nextpage = (Boolean) resFromProcedure.get("NxtPageFlag");
						final Integer iMaxCnt = (Integer) resFromProcedure.get("MaxCnt");
						
						if (nextpage != null) {
							if (nextpage) {
								hotDealsListResultSet.setNextPage(1);
							} else {
								hotDealsListResultSet.setNextPage(0);
							}
						}
						
						if (null == iMaxCnt) {
							hotDealsListResultSet.setMaxCnt(0);
						} else {
							hotDealsListResultSet.setMaxCnt(iMaxCnt);
						}
						
						if (favCat != null) {
							if (favCat) {
								hotDealsListResultSet.setFavCat(1);
							} else {
								hotDealsListResultSet.setFavCat(0);
							}
						}
						// To get maximum row number.
						hotDealsListResultSet.setMaxRowNum(hotDealslist.get(hotDealslist.size() - 1).getRowNumber());
					}
					
					if (favCat != null) {
						if (favCat) {
							hotDealsListResultSet.setFavCat(1);
						} else {
							hotDealsListResultSet.setFavCat(0);
						}
					}
					
					if (categoryFlag != null) {
						if (categoryFlag) {
							hotDealsListResultSet.setCategoryFlag(1);
						} else {
							hotDealsListResultSet.setCategoryFlag(0);
						}
					}
					
					if (bottomBtnList != null && !bottomBtnList.isEmpty()) {
						hotDealsListResultSet.setBottomBtn(1);
						hotDealsListResultSet.setArBottomBtnList(bottomBtnList);
					} else {
						hotDealsListResultSet.setBottomBtn(0);
					}
					
					
				} else {
					final Integer errorNum = (Integer) resFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside HotDealsDaoImpl : getHotDealslist : usp_HcHotDealSearchPagination : " + errorNum + "  and error message: {}"
							+ errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside HotDealsDaoImpl : getHotDealslist : ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside HotDealsDaoImpl : getHotDealslist : ", exception);
			throw new HubCitiException(exception);
		}
		return hotDealsListResultSet;
	}

	/**
	 * This DAO Implementation method removes hot deals .
	 * 
	 * @param hotDealsListRequest
	 *            as query parameter
	 * @return response based on success or failure.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String removeHDProduct(HotDealsListRequest hotDealsListRequest) throws HubCitiException {
		final String methodName = "removeHDProduct in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName + "requested user id is -->" + hotDealsListRequest.getUserId());
		String responseFromProc = null;
		Integer fromProc = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHotDealInterest");
			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", hotDealsListRequest.getUserId());
			scanQueryParams.addValue("HotDealID", hotDealsListRequest.getHotDealId());
			scanQueryParams.addValue("Interested", hotDealsListRequest.gethDInterested());
			scanQueryParams.addValue("InterestDate", Utility.getFormattedDate());
			scanQueryParams.addValue("HcHubcitiID", hotDealsListRequest.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (fromProc == 0) {
				responseFromProc = HubCitiConstants.SUCCESS;
			} else {
				responseFromProc = HubCitiConstants.FAILURE;
			}
			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Error Occured in removeHDProduct method ..errorNum..." + errorNum + "errorMsg.." + errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in removeHDProduct", exception);
			throw new HubCitiException(exception);
		} catch (ParseException exception) {
			LOG.error("Exception occurred in removeHDProduct", exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseFromProc;
	}

	/**
	 * The DAO method to update hot deal click impression to database for user
	 * tracking
	 * 
	 * @param userId
	 *            as Query parameter
	 * @param hotDealId
	 *            as Query parameter
	 * @return String SUCCESS or FAILURE
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String userTrackingGetHotDealClick(Integer userId, Integer hotDealId, Integer hotDealListId) throws HubCitiException {
		final String methodName = "UserTrackingGetHotDealClick in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName + "Requested user id is -->" + userId);
		String response;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTrackingHotDealGetDealClick");
			simpleJdbcCall.returningResultSet("HotDealDetails", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));

			final MapSqlParameterSource fetchProductDetailsParameters = new MapSqlParameterSource();
			fetchProductDetailsParameters.addValue("UserID", userId);
			fetchProductDetailsParameters.addValue("HotDealID", hotDealId);
			fetchProductDetailsParameters.addValue("HotDealListID", hotDealListId);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchProductDetailsParameters);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				response = HubCitiConstants.SUCCESS;
			} else {
				response = HubCitiConstants.FAILURE;
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in getHotDealProductDetail ", exception);
			throw new HubCitiException(exception);
		}
		return response;
	}

	/**
	 * This DAO Method to claim hot deal. Method Type: POST
	 * 
	 * @param objHotDealListRequest
	 *            containing userId, hotDealId and mainMenuId
	 * @return XMl containing SUCCESS or FAILURE message
	 * @throws HubCitiException
	 *             is thrown
	 */
	public String hotDealClaim(HotDealsListRequest objHotDealListRequest) throws HubCitiException {
		final String methodName = "hotDealClaim in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGallaryHotDealClip");

			final MapSqlParameterSource fetchHotDealClaim = new MapSqlParameterSource();
			fetchHotDealClaim.addValue("UserID", objHotDealListRequest.getUserId());
			fetchHotDealClaim.addValue("HotDealId", objHotDealListRequest.getHotDealId());
			fetchHotDealClaim.addValue("MainMenuId", objHotDealListRequest.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchHotDealClaim);
			final Integer errorNumber = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null == errorNumber) {
				response = HubCitiConstants.SUCCESSTEXT;
			} else {
				response = HubCitiConstants.FAILURE;
				final String errorMessage = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Exception occurred in usp_HcGallaryHotDealClip " + HubCitiConstants.ERRORNUMBER + ": " + errorNumber
						+ HubCitiConstants.ERRORMESSAGE + ": " + errorMessage);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in usp_HcGallaryHotDealClip ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in usp_HcGallaryHotDealClip ", exception);
			throw new HubCitiException(exception);
		}
		return response;
	}

	/**
	 * This DAO Method to redeem hot deal. Method Type: POST
	 * 
	 * @param objHotDealListRequest
	 *            containing userId, hotDealId and mainMenuId
	 * @return XMl containing SUCCESS or FAILURE message
	 * @throws HubCitiException
	 *             is thrown
	 */
	public String hotDealRedeem(HotDealsListRequest objHotDealListRequest) throws HubCitiException {
		final String methodName = "hotDealRedeem in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGallaryHotDealRedeem");

			final MapSqlParameterSource fetchHotDealClaim = new MapSqlParameterSource();
			fetchHotDealClaim.addValue(HubCitiConstants.USERID, objHotDealListRequest.getUserId());
			fetchHotDealClaim.addValue("HotDealId", objHotDealListRequest.getHotDealId());
			fetchHotDealClaim.addValue("MainMenuId", objHotDealListRequest.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchHotDealClaim);
			final Integer errorNumber = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null == errorNumber) {
				response = HubCitiConstants.SUCCESSTEXT;
			} else {
				response = HubCitiConstants.FAILURE;
				final String errorMessage = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Exception occurred in usp_HcGallaryHotDealRedeem " + HubCitiConstants.ERRORNUMBER + ": " + errorNumber
						+ HubCitiConstants.ERRORMESSAGE + ": " + errorMessage);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in usp_HcGallaryHotDealRedeem ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in usp_HcGallaryHotDealRedeem ", exception);
			throw new HubCitiException(exception);
		}
		return response;
	}


	/**
	 * DAO Method to get List of user deals.
	 * 
	 * @param objDeal Instance of Deal, 
	 * @return XML containing the List of user deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public Data userDealsDisplay(Deal objDeal) throws HubCitiException {
		LOG.info("Inside HotDealsDaoImpl : userDealsDisplay");


		List<Deal> dealList = null;
		/*List<Deal> stateOfDealList = null;*/
		List<BottomButton> arBottomBtnList = null;
		Boolean isNewExist = true;
		
		if (null == objDeal.getFlag()) {
			objDeal.setFlag(false);
		}
		
		Data objData = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserDealsDisplay");
		
			simpleJdbcCall.returningResultSet("dealList", new BeanPropertyRowMapper<Deal>(Deal.class));
			/*simpleJdbcCall.returningResultSet("stateOfDealList", new BeanPropertyRowMapper<Deal>(Deal.class));*/
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));
			
			final MapSqlParameterSource userDealParam = new MapSqlParameterSource();
				userDealParam.addValue("HcUserID", objDeal.getUserId());
				userDealParam.addValue("HubCitiID", objDeal.getHubCitiId());
				userDealParam.addValue("SubmenuFlag", objDeal.getFlag());
				userDealParam.addValue("Latitude", objDeal.getLatitude());
				userDealParam.addValue("Longitude", objDeal.getLongitude());
				userDealParam.addValue("PostalCode", objDeal.getZipCode());

			final Map<String, Object> resFromProcedure = simpleJdbcCall.execute(userDealParam);

			final Integer errorNum = (Integer) resFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == errorNum) {

				dealList = (List<Deal>) resFromProcedure.get("dealList");
				/*stateOfDealList = (List<Deal>) resFromProcedure.get("stateOfDealList");*/
				arBottomBtnList = (List<BottomButton>) resFromProcedure.get("bottomButtonDetails");

				if (arBottomBtnList != null && !arBottomBtnList.isEmpty()) {
					objData = new Data();
					isNewExist = false;
					objData.setBottomBtn(1);
					objData.setArBottomBtnList(arBottomBtnList);
				}

				if (dealList != null && !dealList.isEmpty()) {
					if (isNewExist) {
						objData = new Data();
						objData.setBottomBtn(0);
					}
					isNewExist = false;
					objData.setDealList(dealList);
				}

				/*if (stateOfDealList != null && !stateOfDealList.isEmpty()) {
					if (isNewExist) {
						objData = new Data();
						objData.setBottomBtn(0);
					}
					isNewExist = false;
					objData.setDealStateList(stateOfDealList);
				}*/
				
				if (!isNewExist) {
					objData.setmColor((String) resFromProcedure.get("MenuColor"));
					objData.setmFontColor((String) resFromProcedure.get("MenuFontColor"));
					objData.setSectionColor((String) resFromProcedure.get("SectionColor"));
					objData.setmBackGrdColor((String) resFromProcedure.get("MenuBckgrndColor"));
				}


			} else {
				final String errorMsg = (String) resFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside HotDealsDaoImpl : userDealsDisplay : " + errorNum + " and error message: " + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException e) {
			LOG.error("Inside HotDealsDaoImpl : userDealsDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit HotDealsDaoImpl : userDealsDisplay");
		return objData;
	}



	/**
	 * DAO Method to get List of User Gallery Deals.
	 * 
	 * @param objDeal Instance of Deal, 
	 * @return XML containing the List of User Gallery Deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public Data userGalleryDisplay(Deal objDeal) throws HubCitiException {
		LOG.info("Inside HotDealsDaoImpl : userGalleryDisplay");


		List<Deal> dealList = null;
		List<BottomButton> arBottomBtnList = null;
		Boolean isNewExist = true;
		
		Data objData = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserGalleryDisplay");
			
			simpleJdbcCall.returningResultSet("dealList", new BeanPropertyRowMapper<Deal>(Deal.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));
			
			final MapSqlParameterSource galleryParam = new MapSqlParameterSource();
				galleryParam.addValue("HcUserID", objDeal.getUserId());
				galleryParam.addValue("HubCitiID", objDeal.getHubCitiId());
				galleryParam.addValue("Type", objDeal.getType());
				galleryParam.addValue("SubmenuFlag", objDeal.getFlag());


			final Map<String, Object> resFromProcedure = simpleJdbcCall.execute(galleryParam);


			final Integer errorNum = (Integer) resFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == errorNum) {

				arBottomBtnList = (List<BottomButton>) resFromProcedure.get("bottomButtonDetails");
				dealList = (List<Deal>) resFromProcedure.get("dealList");
				
				if (arBottomBtnList != null && !arBottomBtnList.isEmpty()) {
					objData = new Data();
					isNewExist = false;
					objData.setBottomBtn(1);
					objData.setArBottomBtnList(arBottomBtnList);
				} 

				if (dealList != null && !dealList.isEmpty()) {
					if (isNewExist) {
						objData = new Data();
						objData.setBottomBtn(0);
					}
					isNewExist = false;
					objData.setDealList(dealList);
				}
				
				if (!isNewExist) {
					objData.setmColor((String) resFromProcedure.get("MenuColor"));
					objData.setmFontColor((String) resFromProcedure.get("MenuFontColor"));
					objData.setSectionColor((String) resFromProcedure.get("SectionColor"));
					objData.setmBackGrdColor((String) resFromProcedure.get("MenuBckgrndColor"));
				}

			} else {
				final String errorMsg = (String) resFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside HotDealsDaoImpl : userGalleryDisplay :  " + errorNum + " and error message: " + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException e) {
			LOG.error("Inside HotDealsDaoImpl : userGalleryDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit HotDealsDaoImpl : userGalleryDisplay");
		return objData;
	}

	/**
	 * DAO Method to list Fundraisers Deals
	 * 
	 * @param menuItem
	 *            instance of fundraisers
	 * @return XML containing list for fundraisers
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 * 
	 */
	public Fundraiser getFundraisersList(MenuItem menuItem) throws HubCitiException
	{
		LOG.info("Inside HotDealsDAOImpl : getFundraisersList ");
		
		List<Fundraiser> fundraiserList = null;
		List<BottomButton> bottomBtnList = null;
		Fundraiser objFundraiser = null;
		Integer maxRowNum = 0;		
		
		try	{
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcDealsFundraisingListDisplay");
			
			simpleJdbcCall.returningResultSet("fundraiserList",new BeanPropertyRowMapper<Fundraiser>(Fundraiser.class));
			
			// setup Bottom Buttons dynamicallyin HubcitiApp .
			simpleJdbcCall.returningResultSet("bottomButtonDetails",new BeanPropertyRowMapper<BottomButton>(BottomButton.class));
			
			final MapSqlParameterSource fundraiserParams = new MapSqlParameterSource();
			
			fundraiserParams.addValue("UserID", menuItem.getUserId());
			fundraiserParams.addValue("HubCitiID", menuItem.getHubCitiId());
			fundraiserParams.addValue("LowerLimit", menuItem.getLowerLimit());
			fundraiserParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			fundraiserParams.addValue("SortColumn", menuItem.getSortColumn());
			fundraiserParams.addValue("SortOrder", menuItem.getSortOrder());
			fundraiserParams.addValue("SearchParameter", menuItem.getSearchKey());
			fundraiserParams.addValue("MainMenuID", menuItem.getMainMenuId());
			fundraiserParams.addValue("GroupColumn", menuItem.getGroupBy());
			fundraiserParams.addValue("HCDepartmentID", menuItem.getDepartmentId());
			fundraiserParams.addValue("CityIDs",menuItem.getCityIds());
			
			/* fundraisers display param*/
			fundraiserParams.addValue("CityIds:",menuItem.getCityIds());
			
			final Map<String,Object> resultFromPreduce = simpleJdbcCall.execute(fundraiserParams);
			final Integer errorNumber = (Integer)resultFromPreduce.get(HubCitiConstants.ERRORNUMBER);
			
			if(null != resultFromPreduce){
				
				if(null == resultFromPreduce.get(HubCitiConstants.ERRORNUMBER)){
					
					final Integer maxCnt = (Integer) resultFromPreduce.get("MaxCnt");
					final Boolean nextPage = (Boolean) resultFromPreduce.get("NxtPageFlag");
					
					fundraiserList = (List<Fundraiser>) resultFromPreduce.get("fundraiserList");
					bottomBtnList = (List<BottomButton>) resultFromPreduce.get("bottomButtonDetails");
					
					objFundraiser = new Fundraiser();
					
					
					// To check bottom button List
					
					if(null != bottomBtnList && !bottomBtnList.isEmpty()){
						objFundraiser.setBottomBtnList(bottomBtnList);
						objFundraiser.setBottomBtn(1);
					} else {
						objFundraiser.setBottomBtn(0);
					}
					
					if(null != fundraiserList && !fundraiserList.isEmpty()){
						
						maxRowNum = fundraiserList.get(fundraiserList.size() - 1).getRowNum();
						
						
						//looping inside list to change date format
						/*for(Fundraiser fundraiser : fundraiserList){
							
							fundraiser.setIsDateFormated(false);
							fundraiser.setStartDate(fundraiser.getStartDate());
							fundraiser.setEndDate(fundraiser.getEndDate());
							fundraiser.setIsDateFormated(null);
							
						}
						*/
						
						objFundraiser.setFundraiserList(fundraiserList);
						objFundraiser.setMaxCount(maxCnt);
						objFundraiser.setMaxRowNum(maxRowNum);
						
						if( null != nextPage){
							if(nextPage){
								objFundraiser.setNextPage(1);
							} else {
								objFundraiser.setNextPage(0);
							}
						}
						
					}
					
				} else {
					final String errorMsg = (String) resultFromPreduce.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside HotDealsDaoImpl : getFundraiserList " + HubCitiConstants.SCHEMANAME + ": usp_HcDealsFundraisingListDisplay : ",errorNumber,errorMsg);
					throw new HubCitiException(errorMsg);
				
				}
			}
			
		} catch(DataAccessException e) {
			LOG.error("Inside HodDealsDaoImpl : getFundraiserList " + HubCitiConstants.SCHEMANAME +" : usp_HcDealsFundraisingListDisplay " + e.getMessage());
			throw new HubCitiException(e);
		} catch (Exception e) {
			LOG.error("Inside HodDealsDaoImpl : getFundraiserList " + HubCitiConstants.SCHEMANAME +" : usp_HcDealsFundraisingListDisplay " + e.getMessage());
			throw new HubCitiException(e);
		}		
		LOG.info("Exit HotDealsDaoImpl : getFundraiserList");
		return objFundraiser;
	}
	
	
	/**
	 * DAO Method to get retailer Clicks and impressions.
	 * 
	 * @param objRetailerTrack instance of RetailerTrack
	 * @throws HubCitiException
	 *             throws if execption occurs.
	 */
	public void retailerClickImpression(RetailerTrack objRetailerTrack) throws HubCitiException {
		LOG.info("Inside HotDealsDaoImpl : retailerClickImpression");
		
		Integer iStatusCode = null; 
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_FindRetailerListTracking");

			final MapSqlParameterSource retailerTrackParams = new MapSqlParameterSource();
			retailerTrackParams.addValue("MainMenuID", objRetailerTrack.getMainMenuId());
			retailerTrackParams.addValue("RetailID", objRetailerTrack.getRetailerId());
			retailerTrackParams.addValue("RetailLocationID", objRetailerTrack.getRetailLocationId());
			retailerTrackParams.addValue("CategoryName", objRetailerTrack.getCatName());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(retailerTrackParams);
			iStatusCode = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (iStatusCode == 0) {
				LOG.info("Inside HotDealsDaoImpl : retailerClickImpression : " + HubCitiConstants.SUCCESSTEXT);
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				final String errorNum = (String) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				LOG.error("Inside HotDealsDaoImpl : retailerClickImpression : ", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
			
		} catch (DataAccessException e) {
			LOG.error("Inside HotDealsDaoImpl : retailerClickImpression :  " + e.getMessage());
			throw new HubCitiException(e);
		} 
		LOG.info("Exit HotDealsDaoImpl : retailerClickImpression");
	}
}
