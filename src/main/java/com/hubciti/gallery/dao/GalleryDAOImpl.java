package com.hubciti.gallery.dao;

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
import com.hubciti.common.pojos.CLRDetails;
import com.hubciti.common.pojos.CouponDetails;
import com.hubciti.common.pojos.CouponsDetails;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.ProductDetail;
import com.hubciti.common.pojos.ProductDetails;
import com.hubciti.common.pojos.ProductDetailsRequest;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.ThisLocationRequest;
import com.hubciti.common.utility.Utility;

/**
 * This class for fetching gallery coupons information
 * 
 * @author Kumar D
 */
@SuppressWarnings({"unchecked" })
public class GalleryDAOImpl implements GalleryDAO {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(GalleryDAOImpl.class.getName());

	/**
	 * for jdbcTemplate connection.
	 */
	private JdbcTemplate jdbcTemplate;
	/**
	 * To call stored procedure.
	 */

	private SimpleJdbcCall simpleJdbcCall;

	/**
	 * This method for to get data source Spring DI.
	 * 
	 * @param dataSource
	 *            for DB operations.
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * DAOImpl Method is to fetching the Coupon All Location or Product Details.
	 * 
	 * @param objClrDetails
	 *            as request parameter.
	 * @return CouponDetails that contains Coupon details
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public CouponsDetails fetchCouponLocationProduct(CLRDetails objClrDetails) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : fetchCouponLocationProduct");
		List<RetailerDetail> arRetLocationList = new ArrayList<RetailerDetail>();
		ArrayList<ProductDetail> arCoupProductList = new ArrayList<ProductDetail>();
		CouponsDetails objCouponsDetails = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			if ("location".equalsIgnoreCase(objClrDetails.getType())) {
				simpleJdbcCall.withProcedureName("usp_HcHubCitiGalleryCouponLocationDisplay");
				simpleJdbcCall.returningResultSet("LocationDetails", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			} else
				if ("product".equalsIgnoreCase(objClrDetails.getType())) {
					simpleJdbcCall.withProcedureName("usp_HcHubCitiGalleryCouponProductDisplay");
					simpleJdbcCall.returningResultSet("ProductDetail", new BeanPropertyRowMapper<ProductDetail>(ProductDetail.class));
				}

			final MapSqlParameterSource fetchCouponParam = new MapSqlParameterSource();
			fetchCouponParam.addValue("UserID", objClrDetails.getUserId());
			fetchCouponParam.addValue("CouponID", objClrDetails.getCouponId());
			fetchCouponParam.addValue("HcHubcitiID", objClrDetails.getHubCitiId());
			// for user tracking
			fetchCouponParam.addValue("CouponListID", objClrDetails.getCouponListId());
			fetchCouponParam.addValue("MainMenuID", objClrDetails.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCouponParam);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					objCouponsDetails = new CouponsDetails();
					if ("location".equalsIgnoreCase(objClrDetails.getType())) {
						arRetLocationList = (ArrayList<RetailerDetail>) resultFromProcedure.get("LocationDetails");
						if (null != arRetLocationList && !arRetLocationList.isEmpty()) {
							objCouponsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
							objCouponsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
							objCouponsDetails.setMaxRowNum(arRetLocationList.get(arRetLocationList.size() - 1).getRowNumber());
							objCouponsDetails.setRetailDetailsList(arRetLocationList);
						} else {
							objCouponsDetails = null;
						}
					} else
						if ("product".equalsIgnoreCase(objClrDetails.getType())) {
							arCoupProductList = (ArrayList<ProductDetail>) resultFromProcedure.get("ProductDetail");
							if (null != arCoupProductList && !arCoupProductList.isEmpty()) {
								objCouponsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
								objCouponsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
								objCouponsDetails.setMaxRowNum(arCoupProductList.get(arCoupProductList.size() - 1).getRowNumber());
								objCouponsDetails.setProductLst(arCoupProductList);
							} else {
								objCouponsDetails = null;
							}
						}
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.info("Inside GalleryDAOImpl : fetchCouponLocationProduct :  Store Procedure error :   : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : fetchCouponLocationProduct : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objCouponsDetails;
	}

	/**
	 * DAOImpl Method to fetch all coupons for Product form database.
	 * 
	 * @param objCoupDetReq
	 *            of ProductDetailsRequest object.
	 * @return CouponsDetails object containing coupon details
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public CouponsDetails getAllCouponsByProduct(ProductDetailsRequest objCoupDetReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getAllCouponsByProduct");
		
		List<CouponDetails> objCouponDetailsList = null;
		List<BottomButton> arBottomBtnList = null;
		CouponsDetails objCouponsDetails = null;
	
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			
			simpleJdbcCall.withProcedureName("usp_HcGalleryAllCouponsByProduct");
			
			simpleJdbcCall.returningResultSet("couponDetails", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue("HcUserID", objCoupDetReq.getUserId());
			fetchCoupDetails.addValue("SearchKey", objCoupDetReq.getSearchKey());
			fetchCoupDetails.addValue("CategoryIDs", objCoupDetReq.getCatIds());
			if (objCoupDetReq.getLatitude() != null && !"".equals(objCoupDetReq.getLatitude().trim())) {
				fetchCoupDetails.addValue("Latitude", objCoupDetReq.getLatitude());
			} else {
				fetchCoupDetails.addValue("Latitude", null);
			}
			if (objCoupDetReq.getLongitude() != null && !"".equals(objCoupDetReq.getLongitude().trim())) {
				fetchCoupDetails.addValue("Longitude", objCoupDetReq.getLongitude());
			} else {
				fetchCoupDetails.addValue("Longitude", null);
			}
			fetchCoupDetails.addValue("PostalCode", objCoupDetReq.getPostalcode());
			fetchCoupDetails.addValue("LowerLimit", objCoupDetReq.getLowerLimit());
			fetchCoupDetails.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			fetchCoupDetails.addValue("MainMenuID", objCoupDetReq.getMainMenuId());
			fetchCoupDetails.addValue("HcHubcitiID", objCoupDetReq.getHubCitiId());

			final Map<String, Object> resFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);
			
			if (null != resFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : getAllCouponsByProduct : usp_HcGalleryAllCouponsByProduct : ", errorNum,
						errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				
				objCouponDetailsList = (List<CouponDetails>) resFromProcedure.get("couponDetails");
				arBottomBtnList = (List<BottomButton>) resFromProcedure.get("bottomButtonDetails");
				
				objCouponsDetails = new CouponsDetails();
				
				if (objCouponDetailsList != null && !objCouponDetailsList.isEmpty()) {
					
					final Boolean nxtPageFlag = (Boolean) resFromProcedure.get("NxtPageFlag");
					final Integer maxCnt = (Integer) resFromProcedure.get("MaxCnt");
								
					objCouponsDetails.setCouponDetail(objCouponDetailsList);
					if (nxtPageFlag) {
						objCouponsDetails.setNextPageFlag(1);
					} else {
						objCouponsDetails.setNextPageFlag(0);
					}
					objCouponsDetails.setMaxCnt(maxCnt);
					objCouponsDetails.setMaxRowNum(objCouponDetailsList.get(objCouponDetailsList.size() - 1).getRowNumber());
				}
				
				
				if (arBottomBtnList != null && !arBottomBtnList.isEmpty()) {
					objCouponsDetails.setBottomBtn(1);
					objCouponsDetails.setArBottomBtnList(arBottomBtnList);
				} else {
					objCouponsDetails.setBottomBtn(0);
				}
			}
			
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getAllCouponsByProduct : Store Procedure error : usp_HcGalleryAllCouponsByProduct : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objCouponsDetails;
	}

	/**
	 * DAOImpl Method to get categories for coupons for products.
	 * 
	 * @param objProdDetailsReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing business categories
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public CouponsDetails getCoupProductCategory(ProductDetailsRequest objProdDetailsReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getCoupProductCategory");
		List<CouponDetails> objCouponDetailsList = null;
		CouponsDetails objCouponsDetails = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponProductCategoryDisplay");
			simpleJdbcCall.returningResultSet("couponPopulationCenters", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue("UserID", objProdDetailsReq.getUserId());
			fetchCoupDetails.addValue("Latitude", objProdDetailsReq.getLatitude());
			fetchCoupDetails.addValue("Longitude", objProdDetailsReq.getLongitude());
			fetchCoupDetails.addValue("PostalCode", objProdDetailsReq.getPostalcode());
			fetchCoupDetails.addValue("HcHubcitiID", objProdDetailsReq.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);

			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : getCoupProductCategory :  Store Procedure error : usp_HcCouponProductCategoryDisplay  : ",
						errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				objCouponDetailsList = (List<CouponDetails>) resultFromProcedure.get("couponPopulationCenters");
				if (objCouponDetailsList != null && !objCouponDetailsList.isEmpty()) {
					objCouponsDetails = new CouponsDetails();
					objCouponsDetails.setCouponDetail(objCouponDetailsList);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getAllCouponsByProduct : Store Procedure error : usp_HcCouponProductCategoryDisplay  : "
					+ e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objCouponsDetails;
	}

	/**
	 * DAOImpl Method to get retailers for selected business category.
	 * 
	 * @param objProdDetailsReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing retailer list
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public CouponsDetails getRetailerForBusinessCategory(ProductDetailsRequest objProdDetailsReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getRetailerForBusinessCategory  ");
		List<CouponDetails> objCouponDetailsList = null;
		CouponsDetails objCouponsDetails = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponRetailerDisplay");
			simpleJdbcCall.returningResultSet("couponPopulationCenters", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue("UserID", objProdDetailsReq.getUserId());
			fetchCoupDetails.addValue("Latitude", objProdDetailsReq.getLatitude());
			fetchCoupDetails.addValue("Longitude", objProdDetailsReq.getLongitude());
			fetchCoupDetails.addValue("PostalCode", objProdDetailsReq.getPostalcode());
			fetchCoupDetails.addValue("BusinessCategoryID", objProdDetailsReq.getBusCatIds());
			fetchCoupDetails.addValue("HcHubcitiID", objProdDetailsReq.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);
			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : getRetailerForBusinessCategory :  Store Procedure error : usp_HcCouponRetailerDisplay  : ",
						errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				objCouponDetailsList = (List<CouponDetails>) resultFromProcedure.get("couponPopulationCenters");
				if (objCouponDetailsList != null && !objCouponDetailsList.isEmpty()) {
					objCouponsDetails = new CouponsDetails();
					objCouponsDetails.setCouponDetail(objCouponDetailsList);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getRetailerForBusinessCategory :  Store Procedure error : usp_HcCouponRetailerDisplay  : "
					+ e.getMessage());
			throw new HubCitiException(e.getMessage());
		}

		return objCouponsDetails;
	}

	/**
	 * DAOImpl Method to fetch all coupons for Product from database.
	 * 
	 * @param objCoupDetReq
	 *            of ProductDetailsRequest object.
	 * @return CouponsDetails object containing coupon details
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public CouponsDetails getAllCouponsByLocation(ProductDetailsRequest objCoupDetReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getAllCouponsByLocation");
		
		List<CouponDetails> objCouponDetailsList = null;
		CouponsDetails objCouponsDetails = null;
		List<BottomButton> bottomBtnList = null;
		

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			
			simpleJdbcCall.withProcedureName("usp_HcGalleryAllCouponsByLocation");
			simpleJdbcCall.returningResultSet("couponDetails", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue("HcUserID", objCoupDetReq.getUserId());
			fetchCoupDetails.addValue("SearchKey", objCoupDetReq.getSearchKey());
			fetchCoupDetails.addValue("BusinessCategoryIDs", objCoupDetReq.getBusCatIds());
			fetchCoupDetails.addValue("RetailID", objCoupDetReq.getRetailId());
			if (objCoupDetReq.getLatitude() != null && !"".equals(objCoupDetReq.getLatitude().trim())) {
				fetchCoupDetails.addValue("Latitude", objCoupDetReq.getLatitude());
			} else {
				fetchCoupDetails.addValue("Latitude", null);
			}
			if (objCoupDetReq.getLongitude() != null && !"".equals(objCoupDetReq.getLongitude().trim())) {
				fetchCoupDetails.addValue("Longitude", objCoupDetReq.getLongitude());
			} else {
				fetchCoupDetails.addValue("Longitude", null);
			}
			fetchCoupDetails.addValue("PostalCode", objCoupDetReq.getPostalcode());
			fetchCoupDetails.addValue("LowerLimit", objCoupDetReq.getLowerLimit());
			fetchCoupDetails.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			fetchCoupDetails.addValue("MainMenuID", objCoupDetReq.getMainMenuId());
			fetchCoupDetails.addValue("HcHubcitiID", objCoupDetReq.getHubCitiId());
			final Map<String, Object> resFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);

			if (null != resFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : getAllCouponsByLocation :  Store Procedure error : usp_HcGalleryAllCouponsByLocation  : ",
						errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				
				objCouponDetailsList = (List<CouponDetails>) resFromProcedure.get("couponDetails");
				bottomBtnList = (List<BottomButton>) resFromProcedure.get("bottomButtonDetails");
				
				objCouponsDetails = new CouponsDetails();
				
				if (objCouponDetailsList != null && !objCouponDetailsList.isEmpty()) {
					final Boolean nxtPageFlag = (Boolean) resFromProcedure.get("NxtPageFlag");
					final Integer maxCnt = (Integer) resFromProcedure.get("MaxCnt");
					
					
					objCouponsDetails.setCouponDetail(objCouponDetailsList);
					objCouponsDetails.setMaxRowNum(objCouponDetailsList.get(objCouponDetailsList.size() - 1).getRowNumber());
					
					
					if (nxtPageFlag) {
						objCouponsDetails.setNextPageFlag(1);
					} else {
						objCouponsDetails.setNextPageFlag(0);
					}
					objCouponsDetails.setMaxCnt(maxCnt);
				}
				
				if (bottomBtnList != null && !bottomBtnList.isEmpty()) {
					objCouponsDetails.setBottomBtn(1);
					objCouponsDetails.setArBottomBtnList(bottomBtnList);
				} else {
					objCouponsDetails.setBottomBtn(0);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getRetailerForBusinessCategory :  Store Procedure error : usp_HcGalleryAllCouponsByLocation  : "
					+ e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		
		LOG.info("Exit GalleryDAOImpl : getAllCouponsByLocation");
		return objCouponsDetails;
	}

	/**
	 * Method to get business categories for coupons for Locations.
	 * 
	 * @param objProdDetailsReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing business categories
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public CouponsDetails getCoupLocationBusinessCategory(ProductDetailsRequest objProdDetailsReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getCoupLocationBusinessCategory");
		List<CouponDetails> objCouponDetailsList = null;
		CouponsDetails objCouponsDetails = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponBusinessCategoryDisplay");
			simpleJdbcCall.returningResultSet("couponPopulationCenters", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue(HubCitiConstants.USERID, objProdDetailsReq.getUserId());
			fetchCoupDetails.addValue("Latitude", objProdDetailsReq.getLatitude());
			fetchCoupDetails.addValue("Longitude", objProdDetailsReq.getLongitude());
			fetchCoupDetails.addValue("PostalCode", objProdDetailsReq.getPostalcode());
			fetchCoupDetails.addValue("HubCitiID", objProdDetailsReq.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);

			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(" Error Occured in SP usp_HcCouponBusinessCategoryDisplay ..errorNum..{} errorMsg {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				objCouponDetailsList = (List<CouponDetails>) resultFromProcedure.get("couponPopulationCenters");

				if (objCouponDetailsList != null && !objCouponDetailsList.isEmpty()) {
					objCouponsDetails = new CouponsDetails();
					objCouponsDetails.setCouponDetail(objCouponDetailsList);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getCoupLocationBusinessCategory :  Store Procedure error : usp_HcCouponBusinessCategoryDisplay  : "
					+ e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objCouponsDetails;
	}

	/**
	 * DAOImpl Method for adding Coupon to gallery.
	 * 
	 * @param couponAddRemove
	 *            xml with Coupon details.
	 * @return response xml with success or failure error code.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String addCoupon(CLRDetails couponAddRemove) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : addCoupon");
		String response;
		Integer fromProc = 0;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcMasterShoppingListAddCoupon");
			final MapSqlParameterSource addCoupon = new MapSqlParameterSource();
			addCoupon.addValue("CouponID", couponAddRemove.getCouponId());
			addCoupon.addValue("HcUserID", couponAddRemove.getUserId());
			addCoupon.addValue("UserCouponClaimTypeID", null);
			addCoupon.addValue("CouponPayoutMethod", null);
			addCoupon.addValue("RetailLocationID", null);
			addCoupon.addValue("CouponClaimDate", Utility.getFormattedDate());
			// Adding CouponListID for usertracking.
			addCoupon.addValue("CouponListID", couponAddRemove.getCouponListId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(addCoupon);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (fromProc == 0) {
				response = HubCitiConstants.SUCCESS;
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : addCoupon :  Store Procedure error : usp_HcMasterShoppingListAddCoupon  : ", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
				// response = HubCitiConstants.FAILURE;
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : addCoupon :  Store Procedure error : usp_HcMasterShoppingListAddCoupon  : " + e.getMessage());
			response = HubCitiConstants.FAILURE;
			throw new HubCitiException(e.getMessage());
		} catch (ParseException e) {
			LOG.error("Inside GalleryDAOImpl : addCoupon :  ParseException  : " + e.getMessage());
			response = HubCitiConstants.FAILURE;
			throw new HubCitiException(e.getMessage());
		}
		return response;
	}

	/**
	 * This DAOImpl method is used to redeem coupon information based on userId.
	 * 
	 * @param couponDetailsObj
	 *            contains user id,coupon id ..
	 * @return Success or failure based on operation.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 * @throws ParseException
	 */
	public String userRedeemCoupon(CouponDetails couponDetailsObj) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : userRedeemCoupon");
		Integer fromProc = null;
		String responseFromProc = null;
		Integer usedFlag = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGalleryRedeemCoupon");

			final MapSqlParameterSource fetchCouponParameters = new MapSqlParameterSource();
			fetchCouponParameters.addValue("HcUserID", couponDetailsObj.getUserId());
			fetchCouponParameters.addValue("CouponID", couponDetailsObj.getCouponId());
			fetchCouponParameters.addValue("UserCouponClaimTypeID", couponDetailsObj.getUserCouponClaimTypeId());
			fetchCouponParameters.addValue("CouponPayoutMethod", couponDetailsObj.getCouponPayoutMethod());
			fetchCouponParameters.addValue("RetailLocationID", couponDetailsObj.getRetailLocationId());
			fetchCouponParameters.addValue("CouponClaimDate", Utility.getFormattedDate());

			// For user tracking
			fetchCouponParameters.addValue("CouponListID", couponDetailsObj.getCouponListId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCouponParameters);
			if (null != resultFromProcedure) {
				if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.info("Inside GalleryDAOImpl : userRedeemCoupon :  Store Procedure error : usp_HcGalleryRedeemCoupon  : ", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				} else {
					fromProc = (Integer) resultFromProcedure.get("Status");
					if (fromProc == 0) {
						responseFromProc = HubCitiConstants.SUCCESS;
						usedFlag = (Integer) resultFromProcedure.get("UsedFlag");
						if (usedFlag != null) {
							if (usedFlag == 1) {
								responseFromProc = HubCitiConstants.CLRALLREADYREDEEMRESPONSETEXT;
							}else{
								responseFromProc = HubCitiConstants.COUPONREDEEMRESPONSETEXT;
							}
						}
					} else {
						responseFromProc = HubCitiConstants.FAILURE;
					}
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : userRedeemCoupon :  Store Procedure error : usp_HcGalleryRedeemCoupon  : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (ParseException e) {
			LOG.error("Inside GalleryDAOImpl : userRedeemCoupon :  ParseException  : " + e.getMessage());
			responseFromProc = HubCitiConstants.FAILURE;
			throw new HubCitiException(e.getMessage());
		}
		return responseFromProc;
	}

	/**
	 * Method for removing Coupon from gallery.
	 * 
	 * @param couponAddRemove
	 *            xml with remove Coupon request.
	 * @return response xml with success or failure error code.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String removeCoupon(CLRDetails couponAddRemove) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : removeCoupon");
		String responseFromProc = null;
		Integer fromProc = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcMasterShoppingListDeleteCoupon");
			final MapSqlParameterSource removeCoupon = new MapSqlParameterSource().addValue("CouponID", couponAddRemove.getCouponId()).addValue(
					"HcUserID", couponAddRemove.getUserId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(removeCoupon);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (fromProc == 0) {
				responseFromProc = HubCitiConstants.SUCCESS;
			} else {
				responseFromProc = HubCitiConstants.FAILURE;
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : removeCoupon :  Store Procedure error : usp_HcGalleryRedeemCoupon  : ", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : removeCoupon :  Store Procedure error : usp_HcGalleryRedeemCoupon  : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return responseFromProc;
	}

	/**
	 * Method to get gallery HotDeals for All/Used/Expired in Location.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object
	 * @return HotDealsListResultSet object containing HotDeals sorted by
	 *         retailers and category.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public HotDealsListResultSet getGalleryHotDealByLocation(ProductDetailsRequest objCoupDetReq) throws HubCitiException {
		final String methodName = "getGalleryHotDealByLocation in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		List<HotDealsDetails> hdDetailsList = null;
		HotDealsListResultSet objHdDetails = null;

		String storeProcedureName = null;

		if (objCoupDetReq.getType().equalsIgnoreCase("Used")) {
			storeProcedureName = "usp_HcGalleryUsedHotDealsByLocation";
		} else
			if (objCoupDetReq.getType().equalsIgnoreCase("Expired")) {
				storeProcedureName = "usp_HcGalleryExpiredHotDealsByLocation";
			} else {
				// For "Claimed"
				storeProcedureName = "usp_HcGalleryHotDealsByLocation";
			}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName(storeProcedureName);
			simpleJdbcCall.returningResultSet("couponPopulationCenters", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue(HubCitiConstants.USERID, objCoupDetReq.getUserId());
			fetchCoupDetails.addValue("SearchKey", objCoupDetReq.getSearchKey());
			fetchCoupDetails.addValue("LowerLimit", objCoupDetReq.getLowerLimit());
			fetchCoupDetails.addValue("ScreenName", HubCitiConstants.ALLCOUPSCREENNAME);
			fetchCoupDetails.addValue(HubCitiConstants.MAINMENUID, objCoupDetReq.getMainMenuId());

			/* Default Postal Code changes for the SPs of Deals Module */
			fetchCoupDetails.addValue("HCHubCitiID", objCoupDetReq.getHubCitiId());
			fetchCoupDetails.addValue("Latitude", objCoupDetReq.getLatitude());
			fetchCoupDetails.addValue("Longitude", objCoupDetReq.getLongitude());
			fetchCoupDetails.addValue("PostalCode", objCoupDetReq.getPostalcode());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);

			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(" Error Occured in SP " + storeProcedureName + " ..errorNum..{} errorMsg {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				hdDetailsList = (List<HotDealsDetails>) resultFromProcedure.get("couponPopulationCenters");

				final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
				final Boolean nxtPageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");

				if (hdDetailsList != null && !hdDetailsList.isEmpty()) {
					objHdDetails = new HotDealsListResultSet();
					objHdDetails.setHdDetailsList(hdDetailsList);
					objHdDetails.setMaxCnt(maxCnt);
					if (nxtPageFlag) {
						objHdDetails.setNextPage(1);
					} else {
						objHdDetails.setNextPage(0);
					}
					int maxRowNum = hdDetailsList.get(0).getRowNumber();
					int num1;
					for (int i = 1; i < hdDetailsList.size(); i++) {
						num1 = hdDetailsList.get(i).getRowNumber();
						if (maxRowNum < num1) {
							maxRowNum = num1;
						}
					}
					objHdDetails.setMaxRowNum(maxRowNum);
				}
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, e);
			throw new HubCitiException(e);
		}
		return objHdDetails;
	}

	/**
	 * Method to get gallery Hot deals for All/Used/Expired in Products.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object
	 * @return HotDealsListResultSet object containing hotdeals sorted by
	 *         category
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public HotDealsListResultSet getGalleryHotDealByProduct(ProductDetailsRequest objCoupDetReq) throws HubCitiException {
		final String methodName = "getGalleryHotDealByProduct in DAO layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		List<HotDealsDetails> hdDetailsList = null;
		HotDealsListResultSet objHdDetails = null;

		String storeProcedureName = null;

		if (objCoupDetReq.getType().equalsIgnoreCase("Used")) {
			storeProcedureName = "usp_HcGalleryUsedHotDealsByProduct";
		} else
			if (objCoupDetReq.getType().equalsIgnoreCase("Expired")) {
				storeProcedureName = "usp_HcGalleryExpiredHotDealsByProduct";
			} else {
				// For "Claimed"
				storeProcedureName = "usp_HcGalleryHotDealsByProduct";
			}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName(storeProcedureName);
			simpleJdbcCall.returningResultSet("couponPopulationCenters", new BeanPropertyRowMapper<HotDealsDetails>(HotDealsDetails.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue(HubCitiConstants.USERID, objCoupDetReq.getUserId());
			fetchCoupDetails.addValue("SearchKey", objCoupDetReq.getSearchKey());
			fetchCoupDetails.addValue("LowerLimit", objCoupDetReq.getLowerLimit());
			fetchCoupDetails.addValue("ScreenName", HubCitiConstants.ALLCOUPSCREENNAME);
			fetchCoupDetails.addValue(HubCitiConstants.MAINMENUID, objCoupDetReq.getMainMenuId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);

			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info(" Error Occured in SP " + storeProcedureName + " ..errorNum..{} errorMsg {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				hdDetailsList = (List<HotDealsDetails>) resultFromProcedure.get("couponPopulationCenters");
				final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
				final Boolean nxtPageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");

				if (hdDetailsList != null && !hdDetailsList.isEmpty()) {
					objHdDetails = new HotDealsListResultSet();
					objHdDetails.setHdDetailsList(hdDetailsList);
					objHdDetails.setMaxCnt(maxCnt);
					if (nxtPageFlag) {
						objHdDetails.setNextPage(1);
					} else {
						objHdDetails.setNextPage(0);
					}
					int maxRowNum = hdDetailsList.get(0).getRowNumber();
					int num1;
					for (int i = 1; i < hdDetailsList.size(); i++) {
						num1 = hdDetailsList.get(i).getRowNumber();
						if (maxRowNum < num1) {
							maxRowNum = num1;
						}
					}
					objHdDetails.setMaxRowNum(maxRowNum);
				}
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, e);
			throw new HubCitiException(e);
		}
		return objHdDetails;
	}

	/**
	 * Method to get gallery coupons for All/Used/Expired in Location.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing coupons
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public CouponsDetails getGalleryCouponsByLocation(ProductDetailsRequest objCoupDetReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getGalleryCouponsByLocation");
		List<CouponDetails> objCouponDetailsList = null;
		CouponsDetails objCouponsDetails = null;
		String storeProcedureName = null;
		if (objCoupDetReq.getType().equalsIgnoreCase("Claimed")) {
			storeProcedureName = "usp_HcGalleryCouponsByLocation";
		} else
			if (objCoupDetReq.getType().equalsIgnoreCase("Expired")) {
				storeProcedureName = "usp_HcGalleryExpiredCouponsByLocation";
			} else
				if (objCoupDetReq.getType().equalsIgnoreCase("Used")) {
					storeProcedureName = "usp_HcGalleryUsedCouponsByLocation";
				}
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName(storeProcedureName);
			simpleJdbcCall.returningResultSet("couponPopulationCenters", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue("UserID", objCoupDetReq.getUserId());
			fetchCoupDetails.addValue("SearchKey", objCoupDetReq.getSearchKey());
			fetchCoupDetails.addValue("LowerLimit", objCoupDetReq.getLowerLimit());
			fetchCoupDetails.addValue("ScreenName", HubCitiConstants.ALLCOUPSCREENNAME);
			fetchCoupDetails.addValue("MainMenuID", objCoupDetReq.getMainMenuId());

			/* Default Postal Code changes for the SPs of Deals Module */
			fetchCoupDetails.addValue("Latitude", objCoupDetReq.getLatitude());
			fetchCoupDetails.addValue("Longitude", objCoupDetReq.getLongitude());
			fetchCoupDetails.addValue("PostalCode", objCoupDetReq.getPostalcode());
			fetchCoupDetails.addValue("HubCitiID", objCoupDetReq.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);
			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : getGalleryCouponsByLocation :  Store Procedure error : " + storeProcedureName + " :  ", errorNum,
						errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				objCouponDetailsList = (List<CouponDetails>) resultFromProcedure.get("couponPopulationCenters");
				final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
				final Boolean nextpageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");

				if (objCouponDetailsList != null && !objCouponDetailsList.isEmpty()) {
					objCouponsDetails = new CouponsDetails();
					objCouponsDetails.setCouponDetail(objCouponDetailsList);
					objCouponsDetails.setMaxCnt(maxCnt);
					if (nextpageFlag) {
						objCouponsDetails.setNextPageFlag(1);
					} else {
						objCouponsDetails.setNextPageFlag(0);
					}
					int maxRowNum = objCouponDetailsList.get(0).getRowNumber();
					int num1;
					for (int i = 1; i < objCouponDetailsList.size(); i++) {
						num1 = objCouponDetailsList.get(i).getRowNumber();
						if (maxRowNum < num1) {
							maxRowNum = num1;
						}
					}
					objCouponsDetails.setMaxRowNum(maxRowNum);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getGalleryCouponsByLocation :  Store Procedure error : " + storeProcedureName + " : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objCouponsDetails;
	}

	/**
	 * Method to get gallery coupons for All/Used/Expired in Products.
	 * 
	 * @param objCoupDetReq
	 *            ProductDetailsRequest object
	 * @return CouponsDetails object containing coupons sorted by category
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public CouponsDetails getGalleryCouponsByProduct(ProductDetailsRequest objCoupDetReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getGalleryCouponsByProduct");
		List<CouponDetails> objCouponDetailsList = null;
		CouponsDetails objCouponsDetails = null;
		String storeProcedureName = null;
		if (objCoupDetReq.getType().equalsIgnoreCase("Claimed")) {
			storeProcedureName = "usp_HcGalleryCouponsByProduct";
		} else
			if (objCoupDetReq.getType().equalsIgnoreCase("Expired")) {
				storeProcedureName = "usp_HcGalleryExpiredCouponsByProduct";
			} else
				if (objCoupDetReq.getType().equalsIgnoreCase("Used")) {
					storeProcedureName = "usp_HcGalleryUsedCouponsByProduct";
				}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName(storeProcedureName);
			simpleJdbcCall.returningResultSet("couponPopulationCenters", new BeanPropertyRowMapper<CouponDetails>(CouponDetails.class));

			final MapSqlParameterSource fetchCoupDetails = new MapSqlParameterSource();
			fetchCoupDetails.addValue("UserID", objCoupDetReq.getUserId());
			fetchCoupDetails.addValue("SearchKey", objCoupDetReq.getSearchKey());
			fetchCoupDetails.addValue("LowerLimit", objCoupDetReq.getLowerLimit());
			fetchCoupDetails.addValue("ScreenName", HubCitiConstants.ALLCOUPSCREENNAME);
			fetchCoupDetails.addValue("MainMenuID", objCoupDetReq.getMainMenuId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchCoupDetails);
			if (null != resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : getGalleryCouponsByProduct :  Store Procedure error : " + storeProcedureName + "  : ", errorNum,
						errorMsg);
				throw new HubCitiException(errorMsg);
			} else {
				objCouponDetailsList = (List<CouponDetails>) resultFromProcedure.get("couponPopulationCenters");
				if (objCouponDetailsList != null && !objCouponDetailsList.isEmpty()) {
					objCouponsDetails = new CouponsDetails();
					final Boolean nextpageFlag = (Boolean) resultFromProcedure.get("NxtPageFlag");
					final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");

					int maxRowNum = objCouponDetailsList.get(0).getRowNumber();
					int num1;
					for (int i = 1; i < objCouponDetailsList.size(); i++) {
						num1 = objCouponDetailsList.get(i).getRowNumber();
						if (maxRowNum < num1) {
							maxRowNum = num1;
						}
					}

					objCouponsDetails.setCouponDetail(objCouponDetailsList);
					if (nextpageFlag) {
						objCouponsDetails.setNextPageFlag(1);
					} else {
						objCouponsDetails.setNextPageFlag(0);
					}
					objCouponsDetails.setMaxCnt(maxCnt);
					objCouponsDetails.setMaxRowNum(maxRowNum);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getGalleryCouponsByProduct :  Store Procedure error : " + storeProcedureName + "  : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objCouponsDetails;
	}

	/**
	 * DAO method to fetch deals present in specified HubCiti (Coupon, Special
	 * Offer, Hot Deal, Sale).
	 * 
	 * @param productDetailsRequest
	 * @return {@link ProductDetails} object.
	 * @throws HubCitiException. If
	 *             any exception occurs HubCitiException will be thrown.
	 */
	public ProductDetails getHubCitiSpecialDeals(ProductDetailsRequest productDetailsRequest) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getHubCitiSpecialDeals");

		ProductDetails productDetailsObj = null;
		List<RetailerDetail> specialOfferlst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSpecialDealsDisplay");
			simpleJdbcCall.returningResultSet("specialofferdiscounts", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			final MapSqlParameterSource scanQueryParams = new MapSqlParameterSource();
			scanQueryParams.addValue("UserID", productDetailsRequest.getUserId());
			scanQueryParams.addValue("HubCitiID", productDetailsRequest.getHubCitiId());
			scanQueryParams.addValue("Latitude", productDetailsRequest.getLatitude());
			scanQueryParams.addValue("Longitude", productDetailsRequest.getLongitude());
			scanQueryParams.addValue("PostalCode", productDetailsRequest.getPostalcode());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(scanQueryParams);

			specialOfferlst = (List<RetailerDetail>) resultFromProcedure.get("specialofferdiscounts");
			if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					if (null != specialOfferlst && !specialOfferlst.isEmpty()) {
						productDetailsObj = new ProductDetails();
						productDetailsObj.setSpecialOfferlst(specialOfferlst);
					}
				}
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside GalleryDAOImpl : getHubCitiSpecialDeals : usp_HcSpecialDealsDisplay : errorNum : " + errorNum + " : errorMsg : "
						+ errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside GalleryDAOImpl : getHubCitiSpecialDeals : usp_HcSpecialDealsDisplay : ", exception);
			throw new HubCitiException(exception);
		}
		return productDetailsObj;
	}

	/**
	 * DAO method to fetches the Special offer retailer display.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	public RetailersDetails getSpecialOffRetDisplay(ThisLocationRequest thisLocationReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getSpecialOffRetDisplay");
		List<RetailerDetail> retailerList = null;
		RetailersDetails objRetailerDetails = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSpecialOffersRetailersDisplay");
			simpleJdbcCall.returningResultSet("SpecialOffersRetailers", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource fetchRetailerDetailsParameters = new MapSqlParameterSource();

			fetchRetailerDetailsParameters.addValue("UserID", thisLocationReq.getUserId());
			fetchRetailerDetailsParameters.addValue("HcHubCitiID", thisLocationReq.getHubCitiId());
			fetchRetailerDetailsParameters.addValue("Longitude", thisLocationReq.getLongitude());
			fetchRetailerDetailsParameters.addValue("Latitude", thisLocationReq.getLatitude());
			fetchRetailerDetailsParameters.addValue("LowerLimit", thisLocationReq.getLowerLimit());
			fetchRetailerDetailsParameters.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);

			/* Default Postal Code changes for the SPs of Deals Module */
			fetchRetailerDetailsParameters.addValue("PostalCode", thisLocationReq.getPostalCode());

			// below param are output params from SP.
			fetchRetailerDetailsParameters.addValue("ErrorNumber", null);
			fetchRetailerDetailsParameters.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetailerDetailsParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					retailerList = (List<RetailerDetail>) resultFromProcedure.get("SpecialOffersRetailers");
					if (null != retailerList && !retailerList.isEmpty()) {
						objRetailerDetails = new RetailersDetails();
						objRetailerDetails.setRetailerDetail(retailerList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						if (nextpage != null) {
							if (nextpage) {
								objRetailerDetails.setNextPage(1);
							} else {
								objRetailerDetails.setNextPage(0);
							}
						}
						objRetailerDetails.setMaxCnt(maxCnt);
						objRetailerDetails.setMaxRowNum(retailerList.get(retailerList.size() - 1).getRowNumber());
						objRetailerDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objRetailerDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					} else {
						LOG.info("No Retailers found");
						return objRetailerDetails;
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.info("Inside GalleryDAOImpl : getSpecialOffRetDisplay :  Store Procedure error : usp_HcSpecialOffersRetailersDisplay   : ",
							errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getSpecialOffRetDisplay :  Store Procedure error : usp_HcSpecialOffersRetailersDisplay  : "
					+ e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objRetailerDetails;
	}

	/**
	 * DAO method to fetches the Sales retailer display.
	 * 
	 * @param ThisLocationRequest
	 *            object
	 * @return returns RetailersDetails object containing List of retailers.
	 * @throws HubCitiException
	 */
	public RetailersDetails getSalesRetDisplay(ThisLocationRequest thisLocationReq) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getSalesRetDisplay");
		List<RetailerDetail> retailerList = null;
		RetailersDetails objRetailerDetails = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcSalesRetailersDisplay");
			simpleJdbcCall.returningResultSet("salesRetailersDisplay", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));

			final MapSqlParameterSource fetchRetailerDetailsParameters = new MapSqlParameterSource();

			fetchRetailerDetailsParameters.addValue("UserID", thisLocationReq.getUserId());
			fetchRetailerDetailsParameters.addValue("HubCitiID", thisLocationReq.getHubCitiId());
			fetchRetailerDetailsParameters.addValue("Longitude", thisLocationReq.getLongitude());
			fetchRetailerDetailsParameters.addValue("Latitude", thisLocationReq.getLatitude());
			fetchRetailerDetailsParameters.addValue("LowerLimit", thisLocationReq.getLowerLimit());
			fetchRetailerDetailsParameters.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			fetchRetailerDetailsParameters.addValue("PostalCode", thisLocationReq.getPostalCode());
			// below param are output params from SP.
			fetchRetailerDetailsParameters.addValue("ErrorNumber", null);
			fetchRetailerDetailsParameters.addValue("ErrorMessage", null);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchRetailerDetailsParameters);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					retailerList = (List<RetailerDetail>) resultFromProcedure.get("salesRetailersDisplay");
					if (null != retailerList && !retailerList.isEmpty()) {
						objRetailerDetails = new RetailersDetails();
						objRetailerDetails.setRetailerDetail(retailerList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						final Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						if (nextpage != null) {
							if (nextpage) {
								objRetailerDetails.setNextPage(1);
							} else {
								objRetailerDetails.setNextPage(0);
							}
						}
						objRetailerDetails.setMaxCnt(maxCnt);
						objRetailerDetails.setMaxRowNum(retailerList.get(retailerList.size() - 1).getRowNumber());
						objRetailerDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objRetailerDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					} else {
						LOG.info("No Retailers found");
						return objRetailerDetails;
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					final String errorNum = Integer.toString((Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER));
					LOG.info("Inside GalleryDAOImpl : getSalesRetDisplay :  Store Procedure error : usp_HcSalesRetailersDisplay   : ", errorNum,
							errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getSalesRetDisplay :  Store Procedure error : usp_HcSalesRetailersDisplay  : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return objRetailerDetails;
	}
	
	/**
	 * This DAOImpl method for display Coupons for Location's.
	 * 
	 * @param objRetDetail
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public RetailersDetails getCouponsLocationListJson(RetailerDetail objRetDetail) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getCouponsLocationListJson");


		boolean ifeatured = false;
		RetailersDetails couponsDetails = null;
		List<RetailerDetail> arFeaturedCouponsList = null;
		List<RetailerDetail> arNonFeaturedCouponsList = null;
		List<BottomButton> bottomBtnList=null;
		Integer iMaxCount = null;
		String label=null;
		
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcGalleryAllCouponsByLocation");
			simpleJdbcCall.returningResultSet("FeaturedCouponsLocationList", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			simpleJdbcCall.returningResultSet("NonFeaturedCouponsLocationList", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource couponsParams = new MapSqlParameterSource();
			couponsParams.addValue("HcUserID", objRetDetail.getUserId());
			if (null == objRetDetail.getLastVisitedNo()) {
				objRetDetail.setLastVisitedNo(0);
			}
			couponsParams.addValue("LowerLimit", objRetDetail.getLastVisitedNo());
			couponsParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			couponsParams.addValue("HcHubcitiID", objRetDetail.getHubCitiId());
			couponsParams.addValue("Longitude", objRetDetail.getLongitude());
			couponsParams.addValue("Latitude", objRetDetail.getLatitude());
			couponsParams.addValue("PostalCode", objRetDetail.getPostalCode());
			couponsParams.addValue("SearchKey", objRetDetail.getSearchKey());
			couponsParams.addValue("SortColumn", objRetDetail.getSortColumn());
			couponsParams.addValue("HcCityID", objRetDetail.getCityIds());
			couponsParams.addValue("BusinessCategoryIDs", objRetDetail.getCatIds());
			couponsParams.addValue("SortOrder", objRetDetail.getSortOrder());
			couponsParams.addValue("RetailID", objRetDetail.getRetailId());
			couponsParams.addValue("MainMenuID", objRetDetail.getMainMenuId());
			
					             
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(couponsParams);


			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arFeaturedCouponsList = (List<RetailerDetail>) resultFromProcedure.get("FeaturedCouponsLocationList");
					arNonFeaturedCouponsList = (List<RetailerDetail>) resultFromProcedure.get("NonFeaturedCouponsLocationList");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
					
					if(null==objRetDetail.getIsFeatOrNonFeat())
						objRetDetail.setIsFeatOrNonFeat(2);
					
					if (null != arFeaturedCouponsList && !arFeaturedCouponsList.isEmpty()&&objRetDetail.getIsFeatOrNonFeat()!=1) {
						ifeatured = true;
						couponsDetails = new RetailersDetails();
						couponsDetails.setFeaturedCouponsList(arFeaturedCouponsList);
						couponsDetails.setmRow(arFeaturedCouponsList.get(arFeaturedCouponsList.size() - 1).getRowNum());
					}
					
					if (null != arNonFeaturedCouponsList && !arNonFeaturedCouponsList.isEmpty()&&objRetDetail.getIsFeatOrNonFeat()!=0) {
						if (!ifeatured) {
							ifeatured = true;
							couponsDetails = new RetailersDetails();
						}

						couponsDetails.setNonFeaturedCouponsList(arNonFeaturedCouponsList);
						final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
						label=(String)resultFromProcedure.get("Label");
						
						couponsDetails.setMaxCnt(iMaxCount);
						couponsDetails.setLabel(label);
						if (nextpage != null) {
							if (nextpage) {
								couponsDetails.setNextPage(1);
							} else {
								couponsDetails.setNextPage(0);
							}
						}
						couponsDetails.setMaxRowNum(arNonFeaturedCouponsList.get(arNonFeaturedCouponsList.size() - 1).getRowNum());
					}
					
					if(!ifeatured){
						couponsDetails = new RetailersDetails();
					}	
					if (bottomBtnList != null && !bottomBtnList.isEmpty()) {
						couponsDetails.setBottomBtn(1);
						couponsDetails.setBottomBtnList(bottomBtnList);
					} else {
						couponsDetails.setBottomBtn(0);
					}
					
					
				
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside GalleryDAOImpl : getCouponsLocationListJson : Store Procedure error : usp_HcGalleryAllCouponsByLocation ",errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getCouponsLocationListJson : Store Procedure error : usp_HcGalleryAllCouponsByLocation : "+ e.getMessage());
			throw new HubCitiException(e);
		}
		return couponsDetails;
	}
	
	
	/**
	 * This DAOImpl method for display Coupons details for Location's.
	 * 
	 * @param objRetDetail
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public RetailersDetails getCouponsLocationMapListJson(RetailerDetail objRetDetail) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getCouponsLocationMapListJson");


		RetailersDetails couponMapDetails = null;
		List<RetailerDetail> couponsMaplist = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponsMapDisplay");
			simpleJdbcCall.returningResultSet("CouponsLocationforMap", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			
			final MapSqlParameterSource specialOffersParams = new MapSqlParameterSource();
			specialOffersParams.addValue("HcUserID", objRetDetail.getUserId());
			specialOffersParams.addValue("HcHubcitiID", objRetDetail.getHubCitiId());
			specialOffersParams.addValue("Longitude", objRetDetail.getLongitude());
			specialOffersParams.addValue("Latitude", objRetDetail.getLatitude());
			specialOffersParams.addValue("PostalCode", objRetDetail.getPostalCode());
			specialOffersParams.addValue("CouponIDs", objRetDetail.getCouponIds());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(specialOffersParams);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					couponsMaplist = (List<RetailerDetail>) resultFromProcedure.get("CouponsLocationforMap");

					if (null != couponsMaplist && !couponsMaplist.isEmpty()) {
						couponMapDetails = new RetailersDetails();
						couponMapDetails.setCouponMapLocs(couponsMaplist);
					}

				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside GalleryDAOImpl : getCouponsLocationMapListJson : Store Procedure error : usp_HcCouponsMapDisplay ",errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getCouponsLocationMapListJson : Store Procedure error : usp_HcCouponsMapDisplay : "+ e.getMessage());
			throw new HubCitiException(e);
		}
		return couponMapDetails;
	}

	
	/**
	 * This DAOImpl method for display My Account
	 * 
	 * @param objRetDetail
	 *            instance of RetailerDetail.
	 * @return RetailersDetails details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public RetailersDetails getMyAccountsJson(RetailerDetail objRetDetail) throws HubCitiException {
		LOG.info("Inside GalleryDAOImpl : getMyAccountJson");
		
		boolean ifeatured = false;
		RetailersDetails couponsDetails = null;
		List<RetailerDetail> arClaimedCouponsList = null;
		List<RetailerDetail> arRedeemedCouponsList = null;
		List<RetailerDetail> arExpiredCouponsList=null;
		
		List<BottomButton> bottomBtnList=null;
		Integer iMaxCount = null;
		
		
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCouponsByLocationGallery");
			simpleJdbcCall.returningResultSet("ClaimedCouponsLocationList", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			simpleJdbcCall.returningResultSet("RedeemedCouponsLocationList", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			simpleJdbcCall.returningResultSet("ExpiredCouponsLocationList", new BeanPropertyRowMapper<RetailerDetail>(RetailerDetail.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource couponsParams = new MapSqlParameterSource();
			couponsParams.addValue("HcUserID", objRetDetail.getUserId());
			if (null == objRetDetail.getLastVisitedNo()) {
				objRetDetail.setLastVisitedNo(0);
			}
			couponsParams.addValue("LowerLimit", objRetDetail.getLastVisitedNo());
			couponsParams.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			couponsParams.addValue("HcHubcitiID", objRetDetail.getHubCitiId());
			couponsParams.addValue("Longitude", objRetDetail.getLongitude());
			couponsParams.addValue("Latitude", objRetDetail.getLatitude());
			couponsParams.addValue("PostalCode", objRetDetail.getPostalCode());
			couponsParams.addValue("SearchKey", objRetDetail.getSearchKey());
			couponsParams.addValue("SortColumn", objRetDetail.getSortColumn());
			couponsParams.addValue("HcCityID", objRetDetail.getCityIds());
			couponsParams.addValue("BusinessCategoryIDs", objRetDetail.getCatIds());
			couponsParams.addValue("SortOrder", objRetDetail.getSortOrder());
			couponsParams.addValue("RetailID", objRetDetail.getRetailId());
			couponsParams.addValue("Type",objRetDetail.getType());
			couponsParams.addValue("MainMenuID", objRetDetail.getMainMenuId());
			
					             
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(couponsParams);


			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get(HubCitiConstants.ERRORNUMBER)) {
					arClaimedCouponsList = (List<RetailerDetail>) resultFromProcedure.get("ClaimedCouponsLocationList");
					arRedeemedCouponsList = (List<RetailerDetail>) resultFromProcedure.get("RedeemedCouponsLocationList");
					arExpiredCouponsList= (List<RetailerDetail>) resultFromProcedure.get("ExpiredCouponsLocationList");
					bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
					
					final Boolean nextpage = (Boolean) resultFromProcedure.get("NxtPageFlag");
					iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
							
								
					
					if (null != arClaimedCouponsList && !arClaimedCouponsList.isEmpty()) {
						ifeatured = true;
						couponsDetails = new RetailersDetails();	
						couponsDetails.setClaimedCouponsList(arClaimedCouponsList);
						couponsDetails.setMaxRowNum(arClaimedCouponsList.get(arClaimedCouponsList.size() - 1).getRowNum());
					}
					
					if (null != arRedeemedCouponsList && !arRedeemedCouponsList.isEmpty() ) {
						if (!ifeatured) {
							ifeatured = true;
							couponsDetails = new RetailersDetails();
						}
						
						couponsDetails.setRedeemedCouponsList(arRedeemedCouponsList);
						couponsDetails.setMaxRowNum(arRedeemedCouponsList.get(arRedeemedCouponsList.size() - 1).getRowNum());
					}
										
					
					if (null != arExpiredCouponsList && !arExpiredCouponsList.isEmpty()) {
						if (!ifeatured) {
							ifeatured = true;
							couponsDetails = new RetailersDetails();
						}
						couponsDetails.setExpiredCouponsList(arExpiredCouponsList);
						couponsDetails.setMaxRowNum(arExpiredCouponsList.get(arExpiredCouponsList.size() - 1).getRowNum());
					}
					
					if (!ifeatured) {
						couponsDetails = new RetailersDetails();
					}
							if (nextpage != null) {
								if (nextpage) {
								couponsDetails.setNextPage(1);
								} else {
								couponsDetails.setNextPage(0);
							}
						}
						couponsDetails.setMaxCnt(iMaxCount);
					
						if (bottomBtnList != null && !bottomBtnList.isEmpty()) {
							couponsDetails.setBottomBtn(1);
							couponsDetails.setBottomBtnList(bottomBtnList);
						} else {
							couponsDetails.setBottomBtn(0);
						}
					
				} else {
					final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside GalleryDAOImpl : getMyAccountJson : Store Procedure error : usp_HcGalleryAllCouponsByLocation",errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside GalleryDAOImpl : getMyAccountJson : Store Procedure error : usp_HcGalleryAllCouponsByLocation : "+ e.getMessage());
			throw new HubCitiException(e);
		}
		return couponsDetails;
		
	}
}
