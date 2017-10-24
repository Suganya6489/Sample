package com.hubciti.ratereview.dao;

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
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.ProductReview;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ShareProductInfo;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.pojos.UserRatingInfo;
import com.hubciti.common.pojos.UserTrackingData;
import com.hubciti.common.utility.Utility;
import com.hubciti.ratereview.query.RateReviewQueries;

@SuppressWarnings({"unchecked" })
public class RateReviewDaoImpl implements RateReviewDao {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(RateReviewDaoImpl.class);

	/**
	 * For JDBC connection.
	 */
	private JdbcTemplate jdbcTemplate;

	/**
	 * To call stored procedue.
	 */
	private SimpleJdbcCall simpleJdbcCall;

	/**
	 * To set ParameterizedBeanPropertyRowMapper to map POJO.
	 */
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
	 * This DAOImpl method for fetches user product rating information.
	 * 
	 * @param ProductDetailsRequest
	 *            as productDetReq.
	 * @return UserRatingInfo details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public UserRatingInfo fetchUserProductRating(ProductDetailsRequest productDetReq) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : fecthUserProductRating");
		UserRatingInfo userRatingInfo = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGetUserRatings");
			simpleJdbcCall.returningResultSet("userRatingInfo_one", new BeanPropertyRowMapper<UserRatingInfo>(UserRatingInfo.class));
			simpleJdbcCall.returningResultSet("userRatingInfo_two", new BeanPropertyRowMapper<UserRatingInfo>(UserRatingInfo.class));

			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("prProductID", productDetReq.getProductId());
			ratingParameters.addValue("prUserID", productDetReq.getUserId());
			ratingParameters.addValue("HcHubCitiID", productDetReq.getHubCitiId());
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					userRatingInfo = new UserRatingInfo();
					final List<UserRatingInfo> userRatingInfoList_One = (List<UserRatingInfo>) resultFromProcedure.get("userRatingInfo_one");
					final List<UserRatingInfo> userRatingInfoList_Two = (List<UserRatingInfo>) resultFromProcedure.get("userRatingInfo_two");
					if (null != userRatingInfoList_One && !userRatingInfoList_One.isEmpty() || null != userRatingInfoList_Two
							&& !userRatingInfoList_Two.isEmpty()) {
						userRatingInfo.setResponseCode(HubCitiConstants.SUCCESSCODE);
						userRatingInfo.setResponseText(HubCitiConstants.SUCCESSTEXT);
						for (int i = 0; i < userRatingInfoList_One.size(); i++) {
							userRatingInfoList_One.get(i).getProductId();
							if ("".equals(Utility.checkNull(userRatingInfoList_One.get(i).getCurrentRating()))) {
								userRatingInfoList_One.get(i).setCurrentRating("0");
							}
							if ("".equals(Utility.checkNull(userRatingInfoList_One.get(i).getAvgRating()))) {
								userRatingInfoList_One.get(i).setAvgRating("0");
							}
							if ("".equals(Utility.checkNull(userRatingInfoList_One.get(i).getNoOfUsersRated()))) {
								userRatingInfoList_One.get(i).setNoOfUsersRated("0");
							}
							userRatingInfo.setArUserRatingInfo(userRatingInfoList_One);
						}
						for (int i = 0; i < userRatingInfoList_Two.size(); i++) {
							userRatingInfoList_Two.get(i).getProductId();
							if ("".equals(Utility.checkNull(userRatingInfoList_Two.get(i).getCurrentRating()))) {
								userRatingInfoList_Two.get(i).setCurrentRating("0");
							}
							if ("".equals(Utility.checkNull(userRatingInfoList_Two.get(i).getAvgRating()))) {
								userRatingInfoList_Two.get(i).setAvgRating("0");
							}
							if ("".equals(Utility.checkNull(userRatingInfoList_Two.get(i).getNoOfUsersRated()))) {
								userRatingInfoList_Two.get(i).setNoOfUsersRated("0");
							}
							userRatingInfo.setUserRatingInfo(userRatingInfoList_Two);
						}
					} else {
						userRatingInfo.setCurrentRating("0");
						userRatingInfo.setAvgRating("0");
						userRatingInfo.setNoOfUsersRated("0");
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : fecthUserProductRating : Store Procedure error : usp_HcAnythingPageDetails ", errorNum,
							errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : fecthUserProductRating : Store Procedure error : usp_HcAnythingPageDetails : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return userRatingInfo;
	}

	

	/**
	 * The DAO method for fetching product reviews.
	 * 
	 * @param userId
	 *            as a request parameter
	 * @param productId
	 *            as a request parameter
	 * @return ProductReview list.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public ArrayList<ProductReview> getProductReviews(Integer userId, Integer productId) throws HubCitiException {
		final String methodName = "getProductReviews in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		ArrayList<ProductReview> productReviewslist = null;

		try {
			simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
			productReviewslist = (ArrayList<ProductReview>) simpleJdbcTemplate.query(RateReviewQueries.GETPRODUCTREVIEWS,
					new BeanPropertyRowMapper<ProductReview>(ProductReview.class), productId);
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return productReviewslist;
	}

	/**
	 * This method fetches User product ratings.
	 * 
	 * @param userId
	 *            as query parameter
	 * @param productId
	 *            as query parameter
	 * @return UserRatingInfo object
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public UserRatingInfo fecthUserProductRating(Integer userId, Integer productId, Integer hubCitiId) throws HubCitiException {
		final String methodName = "fecthUserProductRating";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		UserRatingInfo userRatingInfo = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGetUserRatings");
			simpleJdbcCall.returningResultSet("userRatingInfo", new BeanPropertyRowMapper<UserRatingInfo>(UserRatingInfo.class));

			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("prProductID", productId);
			ratingParameters.addValue("prUserID", userId);
			ratingParameters.addValue("HcHubCitiID", hubCitiId);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					final List<UserRatingInfo> userRatingInfoList = (List<UserRatingInfo>) resultFromProcedure.get("userRatingInfo");
					if (null != userRatingInfoList && !userRatingInfoList.isEmpty()) {
						userRatingInfo = userRatingInfoList.get(0);
						final String userRating = userRatingInfo.getCurrentRating();
						final String avgRating = userRatingInfo.getAvgRating();
						final String noOfUserrated = userRatingInfo.getNoOfUsersRated();
						if (null == userRating) {
							userRatingInfo.setCurrentRating("0");
						}
						if (null == avgRating) {
							userRatingInfo.setAvgRating("0");
						}
						if (null == noOfUserrated) {
							userRatingInfo.setNoOfUsersRated("0");
						}
					} else {
						userRatingInfo = new UserRatingInfo();
						userRatingInfo.setCurrentRating("0");
						userRatingInfo.setAvgRating("0");
						userRatingInfo.setNoOfUsersRated("0");
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_GetUserRatings Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return userRatingInfo;
	}

	/**
	 * This method fetches User product ratings.
	 * 
	 * @param userRatingInfo
	 *            as request parameter containing userId,produtId and rating
	 *            information
	 * @return xml containing success or failure
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String saveUserProductRating(UserRatingInfo userRatingInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : saveUserProductRating");
		String response = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSaveUserRatings");
			simpleJdbcCall.returningResultSet("saveuserratings", new BeanPropertyRowMapper<UserRatingInfo>(UserRatingInfo.class));
			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("ProductID", userRatingInfo.getProductId());
			ratingParameters.addValue("UserID", userRatingInfo.getUserId());
			ratingParameters.addValue("Rating", userRatingInfo.getCurrentRating());
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					response = HubCitiConstants.SUCCESS;
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : saveUserProductRating : Store Procedure error : HcSaveUserRatings : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : saveUserProductRating : Store Procedure error : HcSaveUserRatings : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return response;
	}

	/**
	 * The DAOImpl method to fetching product information.
	 * 
	 * @param shareProductInfo
	 *            as parameter ShareProductInfo.
	 * @return ProductDetail object.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ProductDetail getProductInfo(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getProductInfo");
		
		ProductDetail productDetail = null;
	/*	String productKey = null;
		String shareURL = null;
		
		ArrayList<AppConfiguration> shareUrlInfoList = null;
		final String productId = shareProductInfo.getProductId().toString();
		final String decryptedUserId = shareProductInfo.getUserId().toString();*/
		
		List<ProductDetail> arProdDetailList = null;
		Map<String, Object> resultFromProcedure = null;
		
		/*shareUrlInfoList = getAppConfig(HubCitiConstants.CONFIGURATIONTYPESHAREURL);
		for (int i = 0; i < shareUrlInfoList.size(); i++) {
			if (shareUrlInfoList.get(i).getScreenName().equals(HubCitiConstants.EMAILSHAREURL)) {
				shareURL = shareUrlInfoList.get(i).getScreenContent();
			}
		}*/

		/*final String key = decryptedUserId + "/" + productId;
		EncryptDecryptPwd enryptDecryptpwd = null;*/
		
		try {
			
			/*enryptDecryptpwd = new EncryptDecryptPwd();
			productKey = enryptDecryptpwd.encrypt(key);
			shareURL = shareURL + "productKey=" + productKey;*/

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcProductShare");
			
			simpleJdbcCall.returningResultSet("getProductInfo", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));
			final MapSqlParameterSource prodInfoParam = new MapSqlParameterSource();
				prodInfoParam.addValue("ProductID", shareProductInfo.getProductId());
				prodInfoParam.addValue("HcHubcitiID", shareProductInfo.getHubCitiId());
				prodInfoParam.addValue("UserID", shareProductInfo.getUserId());

			resultFromProcedure = simpleJdbcCall.execute(prodInfoParam);
			arProdDetailList = (List<ProductDetail>) resultFromProcedure.get("getProductInfo");
			
			if (null != resultFromProcedure) {
				productDetail = new ProductDetail();
				
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					if (null != arProdDetailList && !arProdDetailList.isEmpty()) {
						productDetail = (ProductDetail) arProdDetailList.get(0);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getProductInfo : Store Procedure error : usp_HcProductShare : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : Store Procedure error : usp_HcProductShare  : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return productDetail;
	}

	/**
	 * The DAOImpl method to fetching retailer information with sale price.
	 * 
	 * @param retailerId
	 *            as request parameter
	 * @param retailerLocationId
	 *            as request parameter
	 * @param productId
	 *            as request parameter
	 * @return xml containing retailer details with sale price
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public RetailerDetail getRetailerInfoWithSalePrice(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getRetailerInfoWithSalePrice");
		
		RetailerDetail retailerDetail = null;
		List<RetailerDetail> arRetDetailList = null;
		Map<String, Object> resultFromProcedure = null;
		
		try {
			simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
			
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareProduct");
			
			simpleJdbcCall.returningResultSet("getProductShareInfo", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource objProdInfoParam = new MapSqlParameterSource();
			
			objProdInfoParam.addValue("UserID", shareProductInfo.getUserId());
			objProdInfoParam.addValue("ProductID", shareProductInfo.getProductId());
			objProdInfoParam.addValue("RetailID", shareProductInfo.getRetailerId());
			objProdInfoParam.addValue("RetailLocationID", shareProductInfo.getRetailerLocationId());

			resultFromProcedure = simpleJdbcCall.execute(objProdInfoParam);
			
			arRetDetailList = (List<RetailerDetail>) resultFromProcedure.get("getProductShareInfo");
			
			if (null != resultFromProcedure) {
				
				retailerDetail = new RetailerDetail();
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					
					if (null != arRetDetailList && !arRetDetailList.isEmpty()) {
						retailerDetail = (RetailerDetail) arRetDetailList.get(0);
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getRetailerInfoWithSalePrice : Store Procedure error : usp_HcProductShare : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
			
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : getRetailerInfoWithSalePrice : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		return retailerDetail;
	}

	/**
	 * The DAOImpl method Fetching App Configuration details.
	 * 
	 * @return ArrayList<AppConfiguration> .
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ArrayList<AppConfiguration> getAppConfig(String configType) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getAppConfig");
		
		ArrayList<AppConfiguration> appConfigurationList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_GetScreenContent");
			
			simpleJdbcCall.returningResultSet("AppConfigurationList", new BeanPropertyRowMapper<AppConfiguration>(AppConfiguration.class));
			
			final MapSqlParameterSource appConfigParams = new MapSqlParameterSource();
			appConfigParams.addValue("ConfigurationType", configType);
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(appConfigParams);
			if (null != resultFromProcedure) {

				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					appConfigurationList = (ArrayList<AppConfiguration>) resultFromProcedure.get("AppConfigurationList");

				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getAppConfig : Store Procedure error : usp_GetScreenContent :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : getAppConfig : " + e.getMessage());
			throw new HubCitiException(e);
		}
		
		LOG.info("Exit RateReviewDaoImpl : getAppConfig");
		return appConfigurationList;
	}
	

	/**
	 * The DAOImpl method fetches share hot deal details.
	 * 
	 * @param hotdealId
	 *            , hubCitiId as request parameter
	 * @return xml containing hot deal name and URL.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	/*public List<HotDealsDetails> getShareHotdealInfo(Integer hotdealId, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getShareHotdealInfo");
		List<HotDealsDetails> hotDealsDetailsLst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHotDealShare");
			simpleJdbcCall.returningResultSet("shareHotdealInfo", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));

			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("HotDealID", hotdealId);
			ratingParameters.addValue("HcHubcitiID", hubCitiId);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					hotDealsDetailsLst = (List<HotDealsDetails>) resultFromProcedure.get("shareHotdealInfo");
					if (null != hotDealsDetailsLst && !hotDealsDetailsLst.isEmpty()) {
						final Boolean bhotDealExpired = (Boolean) resultFromProcedure.get("HotDealExpired");
						if (null != bhotDealExpired) {
							if (bhotDealExpired) {
								hotDealsDetailsLst.get(0).setHotDealExpired(1);
							} else {
								hotDealsDetailsLst.get(0).setHotDealExpired(0);
							}
						}
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getAppConfig : Store Procedure error : usp_HcHotDealShare :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : getShareHotdealInfo : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return hotDealsDetailsLst;
	}*/

	/**
	 * The DAOImpl method fetches share coupon details.
	 * 
	 * @param couponId
	 *            ,hubCitiId as request parameter
	 * @return xml containing coupon name and URL.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<CouponDetails> getShareCouponInfo(Integer couponId, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getShareCouponInfo");
		List<CouponDetails> arCouponDetailslist = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponShare");
			simpleJdbcCall.returningResultSet("shareCouponInfo", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource ratingParameters = new MapSqlParameterSource();
			ratingParameters.addValue("CouponID", couponId);
			ratingParameters.addValue("HcHubcitiID", hubCitiId);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			ratingParameters.addValue(HubCitiConstants.ERRORNUMBER, null);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(ratingParameters);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arCouponDetailslist = (List<CouponDetails>) resultFromProcedure.get("shareCouponInfo");
					if (null != arCouponDetailslist && !arCouponDetailslist.isEmpty()) {
						final Boolean bCoupExpired = (Boolean) resultFromProcedure.get("CouponExpired");
						if (null != bCoupExpired) {
							if (bCoupExpired) {
								arCouponDetailslist.get(0).setCouponExpired(1);
							} else {
								arCouponDetailslist.get(0).setCouponExpired(0);
							}
						}
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getShareCouponInfo : Store Procedure error : usp_HcCouponShare :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : getShareCouponInfo : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		return arCouponDetailslist;
	}

	/**
	 * The DAOImpl method for fetching product information.
	 * 
	 * @param shareProductInfo
	 *            as request parameter.
	 * @param retailerDetail
	 *            as request parameter.
	 * @param userId
	 *            as request parameter.
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<ProductDetail> getProductInfo(ShareProductInfo shareProductInfo, RetailerDetail retailerDetail, int userId, String shareURL)
			throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getProductInfo");
		
		List<ProductDetail> productDetaillst = null;
		/*String response = null;*/
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareProductDetails");
			simpleJdbcCall.returningResultSet("productDetails", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));

			final MapSqlParameterSource productDetailsParam = new MapSqlParameterSource();
				productDetailsParam.addValue("ProductID", shareProductInfo.getProductId());
				productDetailsParam.addValue("HcHubcitiID", shareProductInfo.getHubCitiId());
				productDetailsParam.addValue("UserID", shareProductInfo.getUserId());
				
				productDetailsParam.addValue("ErrorNumber", null);
				productDetailsParam.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(productDetailsParam);

			if (null != resultFromProcedure) {
				
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					
					productDetaillst = (List<ProductDetail>) resultFromProcedure.get("productDetails");
					
					if (productDetaillst != null && !productDetaillst.isEmpty()) {
						productDetaillst.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
						//response = Utility.formProductInfoHTML(productDetaillst, retailerDetail, userId, shareURL);
					}
				}
			}
			
		} /*catch (InvalidKeyException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : InvalidKeyException : " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : NoSuchAlgorithmException : " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : NoSuchPaddingException : " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : InvalidAlgorithmParameterException : " + e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : InvalidKeySpecException : " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : IllegalBlockSizeException : " + e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : BadPaddingException : " + e.getMessage());
		}*/ catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : getProductInfo : Store Procedure error : usp_HcShareProductDetails : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit RateReviewDaoImpl : getProductInfo");
		return productDetaillst;
	}

	/**
	 * The DAOImpl method for fetching user information for sending mail to
	 * share product information.
	 * 
	 * @param shareProductInfo
	 *            as request parameter
	 * @return EmailID as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getUserInfo(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getUserInfo");
		String response = null;
		try {
			simpleJdbcTemplate = new SimpleJdbcTemplate(jdbcTemplate);
			response = this.jdbcTemplate.queryForObject(RateReviewQueries.FETCHUSEREMAILID, new Object[] { shareProductInfo.getUserId() },
					String.class);
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : getUserInfo : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit RateReviewDaoImpl : getUserInfo");
		return response;
	}


	/**
	 * The DAOImpl method for adding share app site by email details to
	 * database.
	 * 
	 * @param objShareProductInfo
	 *            as request parameter
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<RetailersDetails> shareAppSiteByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareAppSiteByEmail");
		
		/*String response = null;*/
		List<RetailersDetails> arRetailersDetailsList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareAppsite");
			
			simpleJdbcCall.returningResultSet("shareAppsiteByEmail", new BeanPropertyRowMapper<RetailersDetails>(RetailersDetails.class));
			final MapSqlParameterSource shareAppsiteEmail = new MapSqlParameterSource();
			
				shareAppsiteEmail.addValue("UserID", objShareProductInfo.getUserId());
				shareAppsiteEmail.addValue("RetailID", objShareProductInfo.getRetailerId());
				shareAppsiteEmail.addValue("RetailLocationID", objShareProductInfo.getRetailerLocationId());
//				shareAppsiteEmail.addValue("ShareType", objShareProductInfo.getShareType());
				shareAppsiteEmail.addValue("HubCitiId", objShareProductInfo.getHubCitiId());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareAppsiteEmail);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				
				arRetailersDetailsList = (List<RetailersDetails>) resultFromProcedure.get("shareAppsiteByEmail");
				
				if (arRetailersDetailsList != null && !arRetailersDetailsList.isEmpty()) {
//					arRetailersDetailsList.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
					arRetailersDetailsList.get(0).setShareText(HubCitiConstants.APPSITE_HEADING_1 + (String)resultFromProcedure.get("HubCitiName") + " mobile app and thought you might be interested:");
					//response = Utility.formShareAppInfoHTML(arRetailersDetailsList);
				}
				
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : Store Procedure error : usp_HcShareAppsite :  " + errorNum
						+ " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} /*catch (InvalidKeyException e) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : InvalidKeyException : " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : NoSuchAlgorithmException : " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : NoSuchPaddingException : " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : InvalidKeySpecException : " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : IllegalBlockSizeException : " + e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : BadPaddingException : " + e.getMessage());
		} */catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : Store Procedure error : usp_HcShareAppsite : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit RateReviewDaoImpl : shareAppSiteByEmail");
		return arRetailersDetailsList;
	}

	/**
	 * The DAOImpl method when the user clicks Share, they will be prompted to
	 * share via different options Text/Twitter/Facebook will send wording,
	 * retailer name, and link to the AppSite information.
	 * 
	 * @param objShareProduct
	 *            as request parameter.
	 * @return xml containing rebateId name and details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<RetailersDetails> getShareAppsite(ShareProductInfo objShareProduct) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getShareAppsite");
		
		List<RetailersDetails> arRetailersDetailsList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareAppsite");
			
			simpleJdbcCall.returningResultSet("shareAppsite", new BeanPropertyRowMapper<RetailersDetails>(RetailersDetails.class));

			final MapSqlParameterSource shareAppsiteParam = new MapSqlParameterSource();
			
			shareAppsiteParam.addValue("UserID", objShareProduct.getUserId());
			shareAppsiteParam.addValue("RetailID", objShareProduct.getRetailerId());
			shareAppsiteParam.addValue("RetailLocationID", objShareProduct.getRetailerLocationId());
//			shareAppsiteParam.addValue("ShareType", objShareProduct.getShareType());
			shareAppsiteParam.addValue("HubCitiId", objShareProduct.getHubCitiId());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareAppsiteParam);
			
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					arRetailersDetailsList = (List<RetailersDetails>) resultFromProcedure.get("shareAppsite");
					if (!arRetailersDetailsList.isEmpty() && arRetailersDetailsList != null) {
						arRetailersDetailsList.get(0).setShareText(HubCitiConstants.APPSITE_HEADING_1 + (String)resultFromProcedure.get("HubCitiName") + " mobile app and thought you might be interested:");
						arRetailersDetailsList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
						arRetailersDetailsList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getShareAppsite : Store Procedure error : usp_HcShareAppsite :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : getShareAppsite : Store Procedure error : usp_HcShareAppsite : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit RateReviewDaoImpl : getShareAppsite");
		return arRetailersDetailsList;
	}

	/**
	 * The DAOImpl method for fetching special offer information via through mail.
	 * 
	 * @param instance of ShareProductInfo.
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<RetailersDetails> shareSpecialOffByEmail(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareSpecialOffByEmail");
		
		List<RetailersDetails> specialOfflst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcDisplayRetailerCustomPageURL");
			
			simpleJdbcCall.returningResultSet("shareSpecialOffInfo", new BeanPropertyRowMapper<RetailersDetails>(RetailersDetails.class));

			final MapSqlParameterSource specialOffParam = new MapSqlParameterSource();
			
				specialOffParam.addValue("UserID", shareProductInfo.getUserId());
				specialOffParam.addValue("RetailID", shareProductInfo.getRetailerId());
				specialOffParam.addValue("PageID", shareProductInfo.getPageId());
				specialOffParam.addValue("HubCitiId", shareProductInfo.getHubCitiId());
				
				specialOffParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				specialOffParam.addValue(HubCitiConstants.ERRORNUMBER, null);
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specialOffParam);

			if (null != resultFromProcedure) {
				
				if (null == resultFromProcedure.get("ErrorNumber")) {
					
					specialOfflst = (List<RetailersDetails>) resultFromProcedure.get("shareSpecialOffInfo");
					if (!specialOfflst.isEmpty() && specialOfflst != null) {
						specialOfflst.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
//						specialOfflst.get(0).setAppIcon((String)resultFromProcedure.get("AppIcon"));
						//response = Utility.formSpecailOffInfoHtml(specialOfflst);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : Store Procedure error : usp_HcDisplayRetailerCustomPageURL :  "
							+ errorNum + " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		}/* catch (InvalidKeyException e) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : InvalidKeyException : " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : NoSuchAlgorithmException : " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : NoSuchPaddingException : " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : InvalidKeySpecException : " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : IllegalBlockSizeException : " + e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : BadPaddingException : " + e.getMessage());
		}*/ catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffByEmail : Store Procedure error : usp_HcDisplayRetailerCustomPageURL : " + exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit RateReviewDaoImpl : shareSpecialOffByEmail");
		return specialOfflst;
	}

	
	/**
	 * The DAOImpl method for fetching special offer information via through mail.
	 * 
	 * @param instance of ShareProductInfo.
	 * @return instance of RetailersDetails.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public RetailersDetails shareSpecialOffer(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareSpecialOffer");
		
		List<RetailersDetails> retDetailList = null;
		RetailersDetails objRetDetail = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcDisplayRetailerCustomPageURL");
			
			simpleJdbcCall.returningResultSet("shareSpecialOffInfo", new BeanPropertyRowMapper<RetailersDetails>(RetailersDetails.class));

			final MapSqlParameterSource specialOffParam = new MapSqlParameterSource();
			
				specialOffParam.addValue("UserID", shareProductInfo.getUserId());
				specialOffParam.addValue("RetailID", shareProductInfo.getRetailerId());
				specialOffParam.addValue("PageID", shareProductInfo.getPageId());
				specialOffParam.addValue("HubCitiId", shareProductInfo.getHubCitiId());
				
				specialOffParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				specialOffParam.addValue(HubCitiConstants.ERRORNUMBER, null);
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specialOffParam);

			if (null != resultFromProcedure) {
				
				if (null == resultFromProcedure.get("ErrorNumber")) {
					retDetailList = (List<RetailersDetails>) resultFromProcedure.get("shareSpecialOffInfo");
					
					if (!retDetailList.isEmpty() && retDetailList != null) {
						objRetDetail = new RetailersDetails();
//						retDetailList.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
//						retDetailList.get(0).setAppIcon((String)resultFromProcedure.get("AppIcon"));
						objRetDetail = (RetailersDetails)retDetailList.get(0);
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareSpecialOffer : Store Procedure error : usp_HcDisplayRetailerCustomPageURL :  "
							+ errorNum + " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		}  catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareSpecialOffer : Store Procedure error : usp_HcDisplayRetailerCustomPageURL : " + exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit RateReviewDaoImpl : shareSpecialOffer");
		return objRetDetail;
	}
	
	
	public UserDetails shareAppDownloadLink(ShareProductInfo objShareDetails) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareAppDownloadLink");
		
		List<UserDetails> userDetailsList = null;
		UserDetails objUserDetails = new UserDetails();
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareApp");
			
			simpleJdbcCall.returningResultSet("shareAppsite", new BeanPropertyRowMapper<UserDetails>(UserDetails.class));

			final MapSqlParameterSource shareParam = new MapSqlParameterSource();
			shareParam.addValue("UserID", objShareDetails.getUserId());
			shareParam.addValue("HcHubCitiID", objShareDetails.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareParam);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					userDetailsList = (List<UserDetails>) resultFromProcedure.get("shareAppsite");
					if (!userDetailsList.isEmpty() && userDetailsList != null) {
						String userName = (String) resultFromProcedure.get("UserName");
						String iTunesImg = (String) resultFromProcedure.get("ItunesImage");
						String androidImg = (String) resultFromProcedure.get("GooglePlayImage");
						objUserDetails = userDetailsList.get(0);
						objUserDetails.setUserName(userName);
						objUserDetails.setiTunesImg(iTunesImg);
						objUserDetails.setAndroidImg(androidImg);
					}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareAppDownloadLink : Store Procedure error : usp_HcShareApp :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareAppDownloadLink : Store Procedure error : usp_HcShareApp : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit RateReviewDaoImpl : shareAppDownloadLink");
		return objUserDetails;
	}
	
	

	
	/**
	 * The DAOImpl method for adding share event details to
	 * database.
	 * 
	 * @param objShareProductInfo
	 *            as request parameter
	 * @return EventDetails as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public EventDetails shareEventByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareEventByEmail");
		EventDetails eventDetails = null;
		String shareText = null;
		List<EventDetails> eventDetailsList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareEvent");
			simpleJdbcCall.returningResultSet("shareEventByEmail", new BeanPropertyRowMapper<EventDetails>(EventDetails.class));
			final MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("HcEventID", objShareProductInfo.getEventId());
			parameters.addValue("UserID", objShareProductInfo.getUserId());
			parameters.addValue("HubCitiId", objShareProductInfo.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(parameters);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				eventDetailsList = (List<EventDetails>) resultFromProcedure.get("shareEventByEmail");
				shareText = (String) resultFromProcedure.get("ShareText");
				
				if (eventDetailsList != null && !eventDetailsList.isEmpty()) {	
					eventDetails = new EventDetails();
					eventDetails = eventDetailsList.get(0);
					eventDetails.setShareText(shareText);
				}
				
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside RateReviewDaoImpl : shareEventByEmail : Store Procedure error : usp_HcShareEvent :  " + errorNum
						+ " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareEventByEmail : Store Procedure error : usp_HcShareEvent : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		return eventDetails;
	}
	
	/**
	 * The DAOImpl method for adding share fundraiser details to
	 * database.
	 * 
	 * @param objShareProductInfo
	 *            as request parameter
	 * @return EventDetails as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public Fundraiser shareFundraiserByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareFundraiserByEmail");
		Fundraiser fundraiserDetails = null;
		String shareText = null;
		List<Fundraiser> fundraiserDetailsList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareFundraiser");
			simpleJdbcCall.returningResultSet("shareFundraiserByEmail", new BeanPropertyRowMapper<Fundraiser>(Fundraiser.class));
			final MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("HcFundraisingID", objShareProductInfo.getFundraiserId());
			parameters.addValue("UserID", objShareProductInfo.getUserId());
			parameters.addValue("HubCitiId", objShareProductInfo.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(parameters);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				
				fundraiserDetailsList = (List<Fundraiser>) resultFromProcedure.get("shareFundraiserByEmail");
				shareText = (String) resultFromProcedure.get("ShareText");
				
				if (fundraiserDetailsList != null && !fundraiserDetailsList.isEmpty()) {	
					fundraiserDetails = new Fundraiser();
					fundraiserDetails = fundraiserDetailsList.get(0);
					fundraiserDetails.setShareText(shareText);
				}
				
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside RateReviewDaoImpl : shareFundraiserByEmail : Store Procedure error : usp_HcShareFundraiser :  " + errorNum
						+ " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareFundraiserByEmail : Store Procedure error : usp_HcShareFundraiser : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		return fundraiserDetails;
	}
	
	
	

	
	/**
	 * The DAOImpl method fetches share hot deal details via Email.
	 * 
	 * @param instance of ShareProductInfo.
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<HotDealsDetails> shareHotdealByEmail(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareHotdealByEmail");
		
		List<HotDealsDetails> arHDealsDetailList = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHotDealShare");
			
			simpleJdbcCall.returningResultSet("shareHotdealInfo", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));
			final MapSqlParameterSource shareHDParam = new MapSqlParameterSource();
				shareHDParam.addValue("HotDealID", shareProductInfo.getHotdealId());
				shareHDParam.addValue("HcHubcitiID", shareProductInfo.getHubCitiId());
				shareHDParam.addValue("UserID", shareProductInfo.getUserId());
				shareHDParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				shareHDParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareHDParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					
					arHDealsDetailList = (List<HotDealsDetails>) resultFromProcedure.get("shareHotdealInfo");
					
					if (null != arHDealsDetailList && !arHDealsDetailList.isEmpty()) {
						arHDealsDetailList.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
					//	response = Utility.formHotdealInfoHTML(arHDealsDetailList);
						
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : Store Procedure error : usp_HcHotDealShare :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		}/* catch (InvalidKeyException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : InvalidKeyException : " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : NoSuchAlgorithmException : " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : NoSuchPaddingException : " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : InvalidKeySpecException : " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : IllegalBlockSizeException : " + e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : BadPaddingException : " + e.getMessage());
		}*/ catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdealByEmail : Store Procedure error : usp_HcHotDealShare : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return arHDealsDetailList;
	}
	
	
	/**
	 * The DAOImpl method fetches share hot deal details information via Facebook, Text, Twitter.
	 * 
	 * @param instance of ShareProductInfo.
	 * @param instance of HotDealsDetails.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public HotDealsDetails shareHotdeal(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareHotdeal");
		
		List<HotDealsDetails> hotDealsDetailsList = null;
		HotDealsDetails hotDealsDetails = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHotDealShare");
			
			simpleJdbcCall.returningResultSet("shareHotdealInfo", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));

			final MapSqlParameterSource shareHDParam = new MapSqlParameterSource();
					shareHDParam.addValue("HotDealID", shareProductInfo.getHotdealId());
					shareHDParam.addValue("HcHubcitiID", shareProductInfo.getHubCitiId());
					shareHDParam.addValue("UserID", shareProductInfo.getUserId());
					shareHDParam.addValue(HubCitiConstants.ERRORNUMBER, null);
					shareHDParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareHDParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
						
					hotDealsDetailsList = (List<HotDealsDetails>) resultFromProcedure.get("shareHotdealInfo");
					
					if (null != hotDealsDetailsList && !hotDealsDetailsList.isEmpty()) {
						hotDealsDetails = new HotDealsDetails();
//						hotDealsDetailsList.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
						hotDealsDetails = (HotDealsDetails)hotDealsDetailsList.get(0);
						/*final Boolean bHDExpired = (Boolean) resultFromProcedure.get("HotDealExpired");*/
						
						/*if (null != bHDExpired) {
							if (bHDExpired) {
								hotDealsDetails.setHotDealExpired(1);
							} else {
								hotDealsDetails.setHotDealExpired(0);
							}
						}*/
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareHotdeal : Store Procedure error : usp_HcHotDealShare :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		}  catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : shareHotdeal : Store Procedure error : usp_HcHotDealShare : " + e.getMessage());
			throw new HubCitiException(e);
		}
		
		LOG.info("Exit RateReviewDaoImpl : getHotdealShare");
		return hotDealsDetails;
	}
	
	
	/**
	 * The DAOImpl method fetches share hot deal details information via Facebook, Text, Twitter.
	 * 
	 * @param instance of ShareProductInfo.
	 * @param instance of CouponDetails.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public CouponDetails shareCoupon(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareCoupon");
		
		List<CouponDetails> couponDetailsList = null;
		CouponDetails couponDetails = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponShare");
			
			simpleJdbcCall.returningResultSet("shareCouponInfo", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource coupShareParam = new MapSqlParameterSource();
				coupShareParam.addValue("CouponID", shareProductInfo.getCouponId());
				coupShareParam.addValue("HcHubcitiID", shareProductInfo.getHubCitiId());
				coupShareParam.addValue("UserID", shareProductInfo.getUserId());
				coupShareParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				coupShareParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(coupShareParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					
					couponDetailsList = (List<CouponDetails>) resultFromProcedure.get("shareCouponInfo");
					
					if (null != couponDetailsList && !couponDetailsList.isEmpty()) {
						couponDetails = new CouponDetails();
//						couponDetailsList.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
						couponDetails = (CouponDetails)couponDetailsList.get(0);
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareCoupon : Store Procedure error : usp_HcCouponShare :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		}  catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCoupon : Store Procedure error : usp_HcCouponShare : " + e.getMessage());
			throw new HubCitiException(e);
		}
		
		LOG.info("Exit RateReviewDaoImpl : shareCoupon");
		return couponDetails;
	}
	
	
	/**
	 * The DAOImpl method fetches share coupon details via Email.
	 * 
	 * @param couponId
	 *            ,hubCitiId as request parameter
	 * @return xml containing coupon name and URL.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<CouponDetails> shareCouponByEmail(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareCouponByEmail");
		
		List<CouponDetails> couponDetailslist = null;
		//String response = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponShare");
			simpleJdbcCall.returningResultSet("shareCouponInfo", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource coupShareParam = new MapSqlParameterSource();
				coupShareParam.addValue("CouponID", shareProductInfo.getCouponId());
				coupShareParam.addValue("HcHubcitiID", shareProductInfo.getHubCitiId());
				coupShareParam.addValue("UserID", shareProductInfo.getUserId());
				coupShareParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				coupShareParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(coupShareParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					
					couponDetailslist = (List<CouponDetails>) resultFromProcedure.get("shareCouponInfo");
					if (null != couponDetailslist && !couponDetailslist.isEmpty()) {
						couponDetailslist.get(0).setShareText((String)resultFromProcedure.get("ShareText"));
						//response = Utility.formCouponInfoHTML(couponDetailslist);
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : Store Procedure error : usp_HcCouponShare :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		}/* catch (InvalidKeyException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : InvalidKeyException : " + e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : NoSuchAlgorithmException : " + e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : NoSuchPaddingException : " + e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : InvalidAlgorithmParameterException : " + e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : InvalidKeySpecException : " + e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : IllegalBlockSizeException : " + e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : BadPaddingException : " + e.getMessage());
		}*/ catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareCouponByEmail : Store Procedure error : usp_HcCouponShare : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		return couponDetailslist;
	}

	
	/**
	 * The DAOImpl method to update sender share information type details to the database.
	 * 
	 * @param instance of UserTrackingData.
	 * @return SUCCESS or FAILURE string.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String updateReceiverShare(ShareProductInfo shareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : updateReceiverShare");
		
		String response = null;
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTrackingReceiverShare");
			
			final MapSqlParameterSource receiverShareParam = new MapSqlParameterSource();
				receiverShareParam.addValue("UserID", shareProductInfo.getUserId());
				receiverShareParam.addValue("QRTypeCode", shareProductInfo.getQrTypeCode());
				receiverShareParam.addValue("PageID", shareProductInfo.getPageId());
				receiverShareParam.addValue("HubCitiID", shareProductInfo.getHubCitiId());
				receiverShareParam.addValue("RetailID", shareProductInfo.getRetailerId());
				receiverShareParam.addValue("RetailLocationID", shareProductInfo.getRetailerLocationId());
				receiverShareParam.addValue(HubCitiConstants.ERRORNUMBER, null);
				receiverShareParam.addValue(HubCitiConstants.ERRORNUMBER, null);
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(receiverShareParam);
			
			final Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			
			if (status == 0 && null == errorNum) {
				response = HubCitiConstants.SUCCESSTEXT;
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside RateReviewDaoImpl : updateReceiverShare : Store Procedure error : usp_HcUserTrackingReceiverShare :  " + errorNum
						+ " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
			
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : updateReceiverShare : Store Procedure error : usp_HcUserTrackingReceiverShare : " + e.getMessage());
			throw new HubCitiException(e);
		} 
		
		LOG.info("Exit RateReviewDaoImpl : usp_HcUserTrackingReceiverShare");
		return response;
	}
	
	

	/**
	 * The DAOImpl method to fetch types of share.
	 * 
	 * @return list of share types name and its ID
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<UserTrackingData> getShareTypes() throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getShareTypes");
		
		List<UserTrackingData> objShareTypesList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTrackingShareTypeDisplay");
			
			simpleJdbcCall.returningResultSet("shareTypes", new BeanPropertyRowMapper<UserTrackingData>(UserTrackingData.class));
			
			final MapSqlParameterSource shareParam = new MapSqlParameterSource();
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					objShareTypesList = (List<UserTrackingData>) resultFromProcedure.get("shareTypes");
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getShareTypes : Store Procedure error : usp_HcUserTrackingShareTypeDisplay ", errorNum,
							errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : getShareTypes : Store Procedure error : usp_HcUserTrackingShareTypeDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		}
		
		LOG.info("Exit RateReviewDaoImpl : getShareTypes");
		return objShareTypesList;
	}
	
	/**
	 * The DAOImpl method to update sender share information type details to the database
	 * 
	 * @param instance of UserTrackingData.
	 * @return SUCCESS or FAILURE.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String updateSenderShare(UserTrackingData objUsrTracking) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : updateSenderShare");

		String response = null;
		String storeProcedure = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			final MapSqlParameterSource updateShareParam = new MapSqlParameterSource();

			if (null != objUsrTracking.getProdId()) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingProductShare");
				updateShareParam.addValue("ProductID", objUsrTracking.getProdId());
				storeProcedure = "usp_HcUserTrackingProductShare";

			} else if (objUsrTracking.getRetLocId() != null && objUsrTracking.getaPageId() == null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingShareAppsite");
				updateShareParam.addValue("RetailLocationID", objUsrTracking.getRetLocId());
				storeProcedure = "usp_HcUserTrackingShareAppsite";

			} else if (objUsrTracking.getHotDealId() != null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingHotDealShare");
				updateShareParam.addValue("ProductHotDealID", objUsrTracking.getHotDealId());
				storeProcedure = "usp_HcUserTrackingHotDealShare";

			} else if (objUsrTracking.getCoupId() != null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingCouponShare");
				updateShareParam.addValue("CouponID", objUsrTracking.getCoupId());
				storeProcedure = "usp_HcUserTrackingCouponShare";

			} else if (objUsrTracking.getEventId() != null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingEventShare");
				updateShareParam.addValue("EventID", objUsrTracking.getEventId());
				storeProcedure = "usp_HcUserTrackingEventShare";

			} else if (objUsrTracking.getFundId() != null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingFundraiserShare");
				updateShareParam.addValue("FundraiserID", objUsrTracking.getFundId());
				storeProcedure = "usp_HcUserTrackingFundraiserShare";

			} else if (objUsrTracking.getsPageId()!= null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingSpecialOfferShare");
				updateShareParam.addValue("SpecialPageID", objUsrTracking.getsPageId());
				storeProcedure = "usp_HcUserTrackingSpecialOfferShare";
				
			} else if (objUsrTracking.getaPageId()!= null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingAnythingPageShare");
				updateShareParam.addValue("AnythingPageID", objUsrTracking.getaPageId());
				updateShareParam.addValue("RetailID", objUsrTracking.getRetId());
				updateShareParam.addValue("RetailLocationID", objUsrTracking.getRetLocId());
				storeProcedure = "usp_HcUserTrackingAnythingPageShare";
			}else if (objUsrTracking.getBandId()!= null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingBandShare");
				updateShareParam.addValue("BandID", objUsrTracking.getBandId());
				storeProcedure = "usp_HcUserTrackingBandShare";
				
			}else if (objUsrTracking.getBandEventId()!= null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingBandEventsShare");
				updateShareParam.addValue("BandEventID", objUsrTracking.getBandEventId());
				storeProcedure = "usp_HcUserTrackingBandEventsShare";
				
			} else if (objUsrTracking.getaPageId()!= null) {
				simpleJdbcCall.withProcedureName("usp_HcUserTrackingAnythingPageShare");
				updateShareParam.addValue("AnythingPageID", objUsrTracking.getaPageId());
				updateShareParam.addValue("RetailID", objUsrTracking.getRetId());
				updateShareParam.addValue("RetailLocationID", objUsrTracking.getRetLocId());
				storeProcedure = "usp_HcUserTrackingAnythingPageShare";
			}

			updateShareParam.addValue("MainMenuID", objUsrTracking.getMainMenuId());
			updateShareParam.addValue("TargetAddress", objUsrTracking.getTarAddr());
			updateShareParam.addValue("ShareTypeID", objUsrTracking.getShrTypId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(updateShareParam);

			if (null != resultFromProcedure) {
				
				if (null == resultFromProcedure.get("ErrorNumber")) {
					response = HubCitiConstants.SUCCESSTEXT;
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : updateSenderShare : Store Procedure error : " + storeProcedure + " : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
					
				}
				
			}
		} catch (DataAccessException e) {
			LOG.error("Inside RateReviewDaoImpl : updateSenderShare : Store Procedure error :" + storeProcedure + " : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit RateReviewDaoImpl : updateSenderShare");
		return response;
	}
	
	/**
	 * 
	 * The DAOImpl method for adding share event details to
	 * database.
	 * 
	 * @param objShareProductInfo
	 *            as request parameter
	 * @return EventDetails as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 *    
	 */
	public EventDetails shareBandEventByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareBandEventByEmail");
		EventDetails eventDetails = null;
		String shareText = null;
		List<EventDetails> eventDetailsList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareBandEvent");
			simpleJdbcCall.returningResultSet("shareEventByEmail", new BeanPropertyRowMapper<EventDetails>(EventDetails.class));
			final MapSqlParameterSource parameters = new MapSqlParameterSource();
			parameters.addValue("HcEventID", objShareProductInfo.getEventId());
			parameters.addValue("UserID", objShareProductInfo.getUserId());
			parameters.addValue("HubCitiId", objShareProductInfo.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(parameters);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				eventDetailsList = (List<EventDetails>) resultFromProcedure.get("shareEventByEmail");
				shareText = (String) resultFromProcedure.get("ShareText");
				
				if (eventDetailsList != null && !eventDetailsList.isEmpty()) {	
					eventDetails = new EventDetails();
					eventDetails = eventDetailsList.get(0);
					eventDetails.setShareText(shareText);
				}
				
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside RateReviewDaoImpl : shareBandEventByEmail : Store Procedure error : usp_HcShareEvent :  " + errorNum
						+ " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareBandEventByEmail : Store Procedure error : usp_HcShareEvent : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		return eventDetails;
	}
	
	/**
	 * The DAOImpl method for adding share Band by email details to
	 * database.
	 * 
	 * @param objShareProductInfo
	 *            as request parameter
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<RetailersDetails> shareBandByEmail(ShareProductInfo objShareProductInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareAppSiteByEmail");
		
		/*String response = null;*/
		List<RetailersDetails> arRetailersDetailsList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareBandAppsite");
			
			simpleJdbcCall.returningResultSet("shareAppsiteByEmail", new BeanPropertyRowMapper<RetailersDetails>(RetailersDetails.class));
			final MapSqlParameterSource shareAppsiteEmail = new MapSqlParameterSource();
			
				shareAppsiteEmail.addValue("UserID", objShareProductInfo.getUserId());
				shareAppsiteEmail.addValue("BandID", objShareProductInfo.getBandId());
				shareAppsiteEmail.addValue("HubCitiId", objShareProductInfo.getHubCitiId());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareAppsiteEmail);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				
				arRetailersDetailsList = (List<RetailersDetails>) resultFromProcedure.get("shareAppsiteByEmail");
				
				if (arRetailersDetailsList != null && !arRetailersDetailsList.isEmpty()) {
					arRetailersDetailsList.get(0).setShareText(HubCitiConstants.BAND_HEADING_1 + (String)resultFromProcedure.get("HubCitiName") + " mobile app and thought you might be interested:");
				}
				
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : Store Procedure error : usp_HcShareAppsite :  " + errorNum
						+ " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		}catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareAppSiteByEmail : Store Procedure error : usp_HcShareAppsite : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit RateReviewDaoImpl : shareAppSiteByEmail");
		return arRetailersDetailsList;
	}
	
	/**
	 * The DAOImpl method when the user clicks Share, they will be prompted to
	 * share via different options Text/Twitter/Facebook will send wording,
	 * retailer name, and link to the AppSite information.
	 * 
	 * @param objShareProduct
	 *            as request parameter.
	 * @return xml containing Band name and details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<RetailersDetails> shareBand(ShareProductInfo objShareProduct) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : getShareAppsite");
		
		List<RetailersDetails> arRetailersDetailsList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareBandAppsite");
			
			simpleJdbcCall.returningResultSet("shareBand", new BeanPropertyRowMapper<RetailersDetails>(RetailersDetails.class));

			final MapSqlParameterSource shareAppsiteParam = new MapSqlParameterSource();
			
			shareAppsiteParam.addValue("UserID", objShareProduct.getUserId());
			shareAppsiteParam.addValue("BandID", objShareProduct.getBandId());
			shareAppsiteParam.addValue("HubCitiId", objShareProduct.getHubCitiId());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareAppsiteParam);
			
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					arRetailersDetailsList = (List<RetailersDetails>) resultFromProcedure.get("shareBand");
					if (!arRetailersDetailsList.isEmpty() && arRetailersDetailsList != null) {
						arRetailersDetailsList.get(0).setShareText(HubCitiConstants.BAND_HEADING_1 + (String)resultFromProcedure.get("HubCitiName") + " mobile app and thought you might be interested:");
						arRetailersDetailsList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
						arRetailersDetailsList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
					}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : getShareAppsite : Store Procedure error : usp_HcShareAppsite :  " + errorNum
							+ " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : getShareAppsite : Store Procedure error : usp_HcShareAppsite : " + exception.getMessage());
			throw new HubCitiException(exception);
		}
		
		LOG.info("Exit RateReviewDaoImpl : getShareAppsite");
		return arRetailersDetailsList;
	}
	

	/**
	 * The DAOImpl method for adding share News by email details to
	 * database.
	 * 
	 * @param shareNewsInfo
	 *            as request parameter
	 * @return The XML as the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<Item> shareNewsByEmail(ShareProductInfo shareNewsInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareNewsByEmail");

		/*String response = null;*/
		List<Item> arNewsDetailList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareNewsStory");

			simpleJdbcCall.returningResultSet("shareNewsByEmail", new BeanPropertyRowMapper<Item>(Item.class));
			final MapSqlParameterSource shareNewsIDParam = new MapSqlParameterSource();

			shareNewsIDParam.addValue("NewsID", shareNewsInfo.getNewsId());
			shareNewsIDParam.addValue("UserID", shareNewsInfo.getUserId());
			shareNewsIDParam.addValue("HubCitiId", shareNewsInfo.getHubCitiId());
			
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareNewsIDParam);

			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {

				arNewsDetailList = (List<Item>) resultFromProcedure.get("shareNewsByEmail");

				if (arNewsDetailList != null && !arNewsDetailList.isEmpty()) {
					arNewsDetailList.get(0).setShareText((String)resultFromProcedure.get("NewsShareText"));
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside RateReviewDaoImpl : shareNewsByEmail : " + errorNum + " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareNewsByEmail " + exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit RateReviewDaoImpl : shareNewsByEmail");
		return arNewsDetailList;
	}

	/**
	 * The DAOImpl method when the user clicks Share, they will be prompted to
	 * share via different options Text/Twitter/Facebook will send wording,
	 * retailer name, and link to the AppSite information.
	 * 
	 * @param shareNewsInfo
	 *            as request parameter.
	 * @return xml containing News name and details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<Item> shareNewsBySocailMedia(ShareProductInfo shareNewsInfo) throws HubCitiException {
		LOG.info("Inside RateReviewDaoImpl : shareNewsBySocailMedia");

		List<Item> arNewsDetailList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcShareNewsStory");

			simpleJdbcCall.returningResultSet("shareNewsBySocailMedia", new BeanPropertyRowMapper<Item>(Item.class));

			final MapSqlParameterSource shareNewsIDParam = new MapSqlParameterSource();

			shareNewsIDParam.addValue("NewsID", shareNewsInfo.getNewsId());
			shareNewsIDParam.addValue("UserID", shareNewsInfo.getUserId());
			shareNewsIDParam.addValue("HubCitiId", shareNewsInfo.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(shareNewsIDParam);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					arNewsDetailList = (List<Item>) resultFromProcedure.get("shareNewsBySocailMedia");
					if (!arNewsDetailList.isEmpty() && arNewsDetailList != null) {
						arNewsDetailList.get(0).setShareText((String)resultFromProcedure.get("NewsShareText"));
						arNewsDetailList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
						arNewsDetailList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
					}

				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside RateReviewDaoImpl : shareNewsBySocailMedia : " + errorNum + " and error message: {}" + errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside RateReviewDaoImpl : shareNewsBySocailMedia : " + exception.getMessage());
			throw new HubCitiException(exception);
		}

		LOG.info("Exit RateReviewDaoImpl : shareNewsBySocailMedia");
		return arNewsDetailList;
	}
}
