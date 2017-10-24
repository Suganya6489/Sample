package com.hubciti.firstuse.dao;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.AuthenticateUser;
import com.hubciti.common.pojos.BottomButton;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.City;
import com.hubciti.common.pojos.CountryCode;
import com.hubciti.common.pojos.Data;
import com.hubciti.common.pojos.Deal;
import com.hubciti.common.pojos.EducationLevel;
import com.hubciti.common.pojos.FAQDetails;
import com.hubciti.common.pojos.IncomeRange;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.LoginFlowDetails;
import com.hubciti.common.pojos.MainCategory;
import com.hubciti.common.pojos.MaritalStatus;
import com.hubciti.common.pojos.Menu;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.RSSFeedMessage;
import com.hubciti.common.pojos.TutorialMedia;
import com.hubciti.common.pojos.TutorialMediaResultSet;
import com.hubciti.common.pojos.UpdateUserInfo;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.pojos.UserRegistrationInfo;
import com.hubciti.common.pojos.UserSettings;
import com.hubciti.common.utility.EncryptDecryptPwd;
import com.hubciti.common.utility.Utility;
import com.scansee.externalapi.common.constants.ApplicationConstants;

/**
 * @author kumar_dodda
 *
 */
@SuppressWarnings({"unchecked"})
public class FirstUseDaoImpl implements FirstUseDao {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FirstUseDaoImpl.class);

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
	 * DAO method to login.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of {@link UserDetails}.
	 * @throws HubCitiException
	 */
	public UserDetails userLogin(UserDetails userDetailsReq) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : userLogin");

		UserDetails objUserDetails = null;
		EncryptDecryptPwd objEncryptDecryptPwd;
		String encryptedPwd = null;

		try {
			objEncryptDecryptPwd = new EncryptDecryptPwd();
			encryptedPwd = objEncryptDecryptPwd.encrypt(userDetailsReq.getPassword());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Exception in Encryption of password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Exception in Encryption of user password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("user password Encryption Exception    ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Exception while Encryption  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Exception during Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Exception in user password Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Exception in Encryption ", e);
			throw new HubCitiException(e.getMessage());
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcUserLogin");

			final MapSqlParameterSource userRegParam = new MapSqlParameterSource();
			userRegParam.addValue("UserName", userDetailsReq.getUserName());
			userRegParam.addValue("Password", encryptedPwd);
			userRegParam.addValue("AppVersion", userDetailsReq.getAppVersion());
			userRegParam.addValue("DeviceID", userDetailsReq.getDeviceId());
			userRegParam.addValue("HubCitiKey", userDetailsReq.getHubCitiKey());
			userRegParam.addValue("Latitude", userDetailsReq.getLatitude());
			userRegParam.addValue("Longitude", userDetailsReq.getLongitude());
			userRegParam.addValue("PostalCode", userDetailsReq.getPostalCode());
			userRegParam.addValue("RequestPlatformType", userDetailsReq.getPlatform());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(userRegParam);

			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			if (status == 0) {
				Integer userId = (Integer) resultFromProcedure.get(HubCitiConstants.USERID);
				Integer loginFlag = (Integer) resultFromProcedure.get("Login");
				Boolean isHCActive = (Boolean) resultFromProcedure.get("HubCitiDeactivated");
				Integer isInvalidPasswd = (Integer) resultFromProcedure.get("InvalidPassword");
				Integer isPrefSet = (Integer) resultFromProcedure.get("Result");
				Boolean addDevToUser = (Boolean) resultFromProcedure.get("AddDeviceToUser");
				String hubCitiName = (String) resultFromProcedure.get("HubCitiName");
				Integer hubCitiId = (Integer) resultFromProcedure.get("HubCitiID");
				// Boolean userRangeFlag = (Boolean)
				// resultFromProcedure.get("UserOutOfRange");
				String defaultPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");

				objUserDetails = new UserDetails();
				objUserDetails.setUserId(userId);
				objUserDetails.setHubCitiName(hubCitiName);
				objUserDetails.setHubCitiId(hubCitiId);

				objUserDetails.setIsPrefSet(isPrefSet);

				if (loginFlag != null && loginFlag != -1) {
					objUserDetails.setLoginFlag(1);
				} else {
					objUserDetails.setLoginFlag(0);
				}

				if (isInvalidPasswd == 1) {
					objUserDetails.setPassword("0");
				} else {
					objUserDetails.setPassword("1");
				}

				if (isHCActive) {
					objUserDetails.setIsHCActive(0);
				} else {
					objUserDetails.setIsHCActive(1);
				}

				if (addDevToUser) {
					objUserDetails.setAddDevToUser(1);
				} else {
					objUserDetails.setAddDevToUser(0);
				}

				/*
				 * if(userRangeFlag) { objUserDetails.setIsUserOutOfCiti(1); }
				 * else { objUserDetails.setIsUserOutOfCiti(0); }
				 */

				if (defaultPostalCode != null) {
					objUserDetails.setDefPostalCode(defaultPostalCode);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserLogin Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : userLogin : ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : userLogin : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl : userLogin");
		return objUserDetails;
	}

	public UserDetails getUserLogin(UserDetails userDetailsReq) throws HubCitiException {

		LOG.info("Inside FirstUseDaoImpl : getUserLogin");

		UserDetails objUserDetails = null;
		EncryptDecryptPwd objEncryptDecryptPwd;
		String encryptedPwd = null;
		List<City> cityList = null;

		try {
			objEncryptDecryptPwd = new EncryptDecryptPwd();
			encryptedPwd = objEncryptDecryptPwd.encrypt(userDetailsReq.getPassword());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Exception in Encryption of password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Exception in Encryption of user password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("user password Encryption Exception    ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Exception while Encryption  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Exception during Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Exception in user password Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Exception in Encryption ", e);
			throw new HubCitiException(e.getMessage());
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcGetUserLogin");
			simpleJdbcCall.returningResultSet("cityList", new BeanPropertyRowMapper<City>(City.class));
			final MapSqlParameterSource userRegParam = new MapSqlParameterSource();
			userRegParam.addValue("UserName", userDetailsReq.getUserName());
			userRegParam.addValue("Password", encryptedPwd);
			userRegParam.addValue("AppVersion", userDetailsReq.getAppVersion());
			userRegParam.addValue("DeviceID", userDetailsReq.getDeviceId());
			userRegParam.addValue("HubCitiKey", userDetailsReq.getHubCitiKey());
			userRegParam.addValue("Latitude", userDetailsReq.getLatitude());
			userRegParam.addValue("Longitude", userDetailsReq.getLongitude());
			userRegParam.addValue("PostalCode", userDetailsReq.getPostalCode());
			userRegParam.addValue("RequestPlatformType", userDetailsReq.getPlatform());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(userRegParam);

			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			if (status == 0) {
				Integer userId = (Integer) resultFromProcedure.get(HubCitiConstants.USERID);
				Integer loginFlag = (Integer) resultFromProcedure.get("Login");
				Boolean isHCActive = (Boolean) resultFromProcedure.get("HubCitiDeactivated");
				Integer isInvalidPasswd = (Integer) resultFromProcedure.get("InvalidPassword");
				Integer isPrefSet = (Integer) resultFromProcedure.get("Result");
				Boolean addDevToUser = (Boolean) resultFromProcedure.get("AddDeviceToUser");
				String hubCitiName = (String) resultFromProcedure.get("HubCitiName");
				Integer hubCitiId = (Integer) resultFromProcedure.get("HubCitiID");
				// Boolean userRangeFlag = (Boolean)
				// resultFromProcedure.get("UserOutOfRange");
				String defaultPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");
				cityList = (List<City>) resultFromProcedure.get("cityList");
				objUserDetails = new UserDetails();
				objUserDetails.setUserId(userId);
				objUserDetails.setHubCitiName(hubCitiName);
				objUserDetails.setHubCitiId(hubCitiId);
				objUserDetails.setCityList(cityList);
				objUserDetails.setIsPrefSet(isPrefSet);

				if (loginFlag != null && loginFlag != -1) {
					objUserDetails.setLoginFlag(1);
				} else {
					objUserDetails.setLoginFlag(0);
				}

				if (isInvalidPasswd == 1) {
					objUserDetails.setPassword("0");
				} else {
					objUserDetails.setPassword("1");
				}

				if (isHCActive) {
					objUserDetails.setIsHCActive(0);
				} else {
					objUserDetails.setIsHCActive(1);
				}

				if (addDevToUser) {
					objUserDetails.setAddDevToUser(1);
				} else {
					objUserDetails.setAddDevToUser(0);
				}

				/*
				 * if(userRangeFlag) { objUserDetails.setIsUserOutOfCiti(1); }
				 * else { objUserDetails.setIsUserOutOfCiti(0); }
				 */

				if (defaultPostalCode != null) {
					objUserDetails.setDefPostalCode(defaultPostalCode);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcGetUserLogin Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : getUserLogin : ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : getUserLogin : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl : getUserLogin");
		return objUserDetails;
	}

	/**
	 * DAO method to register new user.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of {@link UserDetails}.
	 * @throws HubCitiException
	 */
	public UserDetails userRegistration(UserDetails userRegDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : userRegistration");

		UserDetails objUserDetails = null;
		EncryptDecryptPwd objEncryptDecryptPwd;
		String encryptedPwd = null;

		try {
			objEncryptDecryptPwd = new EncryptDecryptPwd();
			encryptedPwd = objEncryptDecryptPwd.encrypt(userRegDetails.getPassword());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Exception in Encryption of password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Exception in Encryption of user password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("user password Encryption Exception    ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Exception while Encryption  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Exception during Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Exception in user password Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Exception in Encryption ", e);
			throw new HubCitiException(e.getMessage());
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserRegistration");
			final MapSqlParameterSource userRegistrationParameter = new MapSqlParameterSource();
			userRegistrationParameter.addValue("UserName", userRegDetails.getUserName());
			userRegistrationParameter.addValue("Password", encryptedPwd);
			userRegistrationParameter.addValue("HubCitiKey", userRegDetails.getHubCitiKey());
			userRegistrationParameter.addValue("AppVersion", userRegDetails.getAppVersion());
			userRegistrationParameter.addValue("DeviceID", userRegDetails.getDeviceId());
			userRegistrationParameter.addValue("UserLongitude", userRegDetails.getLatitude());
			userRegistrationParameter.addValue("UserLatitude", userRegDetails.getLongitude());
			userRegistrationParameter.addValue("PostalCode", userRegDetails.getPostalCode());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(userRegistrationParameter);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			Integer userExist = (Integer) resultFromProcedure.get("Result");

			if (errorNum == null) {
				if (userExist != null && userExist < 0) {
					objUserDetails = new UserDetails();
					objUserDetails.setLoginFlag(userExist);
				} else {
					Integer userId = (Integer) resultFromProcedure.get("FirstUserID");
					Integer hubCitiId = (Integer) resultFromProcedure.get("HubCitiID");
					String hubCitiName = (String) resultFromProcedure.get("HubCitiName");
					String defaultPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");
					objUserDetails = new UserDetails();
					objUserDetails.setUserId(userId);
					objUserDetails.setHubCitiId(hubCitiId);
					objUserDetails.setHubCitiName(hubCitiName);
					if (defaultPostalCode != null) {
						objUserDetails.setDefPostalCode(defaultPostalCode);
					}
				}
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserRegistration Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : userRegistration : ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : userRegistration : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl : userRegistration");
		return objUserDetails;
	}

	/**
	 * DAO method to get Custom UI for login flow .
	 * 
	 * @param objLoginFlow
	 * @return
	 * @throws HubCitiException
	 */
	public List<LoginFlowDetails> userLoginFlow(LoginFlowDetails objLoginFlow) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : userLoginFlow");

		List<LoginFlowDetails> listLoginFlowDetails = null;
		final LoginFlowDetails objPageLink = new LoginFlowDetails();
		String bkImgPath = null;
		String homeImgPath = null;
		String titleTxtColor = null;
		String titleBkGrdColor = null;
		String hamburgerImg = null;
		String templateType = null;
		String domainName = null;
		Integer iMaxCount = 0;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcConfiguredPagesDisplay");
			simpleJdbcCall.returningResultSet("loginConfigDetails", new BeanPropertyRowMapper<LoginFlowDetails>(LoginFlowDetails.class));
			final MapSqlParameterSource loginFlowParameter = new MapSqlParameterSource();
			loginFlowParameter.addValue("PageType", objLoginFlow.getPageType());
			loginFlowParameter.addValue("HubCitiKey", objLoginFlow.getHubCitiKey());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(loginFlowParameter);

			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			if (status == 0) {
				listLoginFlowDetails = (List<LoginFlowDetails>) resultFromProcedure.get("loginConfigDetails");
				templateType = (String) resultFromProcedure.get("templatetype");
				iMaxCount = (Integer) resultFromProcedure.get("MaxCnt");
				hamburgerImg = (String) resultFromProcedure.get("HamburgerImagePath");

				if (null != listLoginFlowDetails && !listLoginFlowDetails.isEmpty()) {

					homeImgPath = (String) resultFromProcedure.get("homeImgPath");
					bkImgPath = (String) resultFromProcedure.get("bkImgPath");
					titleBkGrdColor = (String) resultFromProcedure.get("titleBkGrdColor");
					titleTxtColor = (String) resultFromProcedure.get("titleTxtColor");
					domainName = (String) resultFromProcedure.get("domainName");

					objPageLink.setHomeImgPath(homeImgPath);
					objPageLink.setBkImgPath(bkImgPath);
					objPageLink.setTitleBkGrdColor(titleBkGrdColor);
					objPageLink.setTitleTxtColor(titleTxtColor);
					objPageLink.setTemplateName(templateType);

					if (objLoginFlow.getPageType().equalsIgnoreCase("Privacy Policy") || objLoginFlow.getPageType().equalsIgnoreCase("About Us Page")) {

						String pageLink = listLoginFlowDetails.get(0).getPageLink();

						objPageLink.setPageLink(pageLink);
						objPageLink.setPageType(objLoginFlow.getPageType());
						listLoginFlowDetails.add(objPageLink);

					} else if (objLoginFlow.getPageType().equalsIgnoreCase("Splash Image")) {
						String splashImg = (String) resultFromProcedure.get("SplashImage");
						String smallLogo = (String) resultFromProcedure.get("SmallLogo");

						objPageLink.setSplashImg(splashImg);
						objPageLink.setPageType(objLoginFlow.getPageType());
						objPageLink.setSmallLogo(smallLogo);

						listLoginFlowDetails.add(objPageLink);
					} else {
						String poweredBy = (String) resultFromProcedure.get("ScanSeeLogo");
						listLoginFlowDetails.get(0).setPoweredBy(poweredBy);
						listLoginFlowDetails.get(0).setHomeImgPath(homeImgPath);
						listLoginFlowDetails.get(0).setBkImgPath(bkImgPath);
						listLoginFlowDetails.get(0).setTitleBkGrdColor(titleBkGrdColor);
						listLoginFlowDetails.get(0).setTitleTxtColor(titleTxtColor);

					}

					listLoginFlowDetails.get(0).setTemplateName(templateType);
					listLoginFlowDetails.get(0).setHamburgerImg(hamburgerImg);
					listLoginFlowDetails.get(0).setMaxCnt(iMaxCount);
					listLoginFlowDetails.get(0).setDomainName(domainName);
				} else {
					listLoginFlowDetails = new ArrayList<LoginFlowDetails>();
					LoginFlowDetails objLogin  =  new LoginFlowDetails();
					objLogin.setHamburgerImg(hamburgerImg);
					objLogin.setMaxCnt(iMaxCount);
					listLoginFlowDetails.add(objLogin);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserRegistration Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : userLoginFlow : ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : userLoginFlow : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl : userLoginFlow");
		return listLoginFlowDetails;
	}

	/**
	 * DAO methos to get User Prefered Categories.
	 * 
	 * @param objUserDetails
	 * @return instance of {@link CategoryDetails}
	 * @throws HubCitiException
	 */
	public CategoryDetails getUserPreferedCategories(UserDetails objUserDetails) throws HubCitiException {
		final String methodName = "getUserPreferedCategories in FirstUseDao";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		CategoryDetails objCatDetails = null;
		List<CategoryDetails> listCategoryDetails;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcPreferredCategoryDisplay");
			simpleJdbcCall.returningResultSet("getUserPreferences", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));
			final MapSqlParameterSource loginFlowParameter = new MapSqlParameterSource();
			loginFlowParameter.addValue(HubCitiConstants.USERID, objUserDetails.getUserId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(loginFlowParameter);

			Integer errorNumb = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (errorNumb == null) {
				listCategoryDetails = (List<CategoryDetails>) resultFromProcedure.get("getUserPreferences");
				objCatDetails = new CategoryDetails();
				objCatDetails.setListCatDetails(listCategoryDetails);
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserRegistration Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in  " + methodName + " ", HubCitiConstants.DATAACCESSEXCEPTIONCODE, exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in " + methodName + " ", exception);
			throw new HubCitiException(exception);
		}
		return objCatDetails;
	}

	/**
	 * DAO methos to set User Prefered Categories.
	 * 
	 * @param objUserDetails
	 * @return instance of {@link CategoryDetails}
	 * @throws HubCitiException
	 */
	public String setUserPreferedCategories(CategoryDetails objCategoryDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : setUserPreferedCategories");

		String response = null;

		if ("".equals(Utility.checkNull(objCategoryDetails.getCatIds()))) {
			objCategoryDetails.setCatIds(null);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserPreferredCategory");
			final MapSqlParameterSource setPreferences = new MapSqlParameterSource();
			setPreferences.addValue(HubCitiConstants.USERID, objCategoryDetails.getUserId());
			setPreferences.addValue("Category", objCategoryDetails.getCatIds());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(setPreferences);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (errorNum == null) {
				response = HubCitiConstants.SUCCESSCODE;
			} else {
				// final Integer errorNum = (Integer)
				// resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserRegistration Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : setUserPreferedCategories ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : setUserPreferedCategories ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl  : setUserPreferedCategories");
		return response;
	}

	/**
	 * DAO method to get user information.
	 * 
	 * @param userDetailsReq
	 * @return List containing user details {@link UserDetails}
	 * @throws HubCitiException
	 */
	/*
	 * public List<UserDetails> getUserInfo(UserDetails userDetailsReq) throws
	 * HubCitiException { final String methodName =
	 * "getUserPreferedCategories in FirstUseDao";
	 * LOG.info(HubCitiConstants.METHODSTART + methodName); UserDetails
	 * objUserDetails = null; List<UserDetails> listUserDetails; try {
	 * simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
	 * simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
	 * simpleJdbcCall.withProcedureName("usp_HcUserInfoDisplay");
	 * simpleJdbcCall.returningResultSet("getUserDetails", new
	 * BeanPropertyRowMapper<UserDetails>(UserDetails.class)); final
	 * MapSqlParameterSource loginFlowParameter = new MapSqlParameterSource();
	 * loginFlowParameter.addValue(HubCitiConstants.USERID,
	 * userDetailsReq.getUserId()); final Map<String, Object>
	 * resultFromProcedure = simpleJdbcCall.execute(loginFlowParameter); Integer
	 * status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
	 * if(status == 0) { listUserDetails = (List<UserDetails>)
	 * resultFromProcedure.get("getUserDetails"); objUserDetails = new
	 * UserDetails(); objUserDetails.setListUserDetails(listUserDetails); } else
	 * { final Integer errorNum = (Integer)
	 * resultFromProcedure.get(HubCitiConstants.ERRORNUMBER); final String
	 * errorMsg = (String)
	 * resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE); LOG.error(
	 * "Error occurred in usp_HcUserInfoDisplay Store Procedure error number: {} and error message: {}"
	 * , errorNum, errorMsg); throw new HubCitiException(errorMsg); } }
	 * catch(DataAccessException exception) {
	 * LOG.error("Exception occurred in  " + methodName + " ",
	 * HubCitiConstants.DATAACCESSEXCEPTIONCODE, exception); throw new
	 * HubCitiException(exception); } catch(Exception exception) {
	 * LOG.error("Exception occurred in  " + methodName + " ", exception); throw
	 * new HubCitiException(exception); } return listUserDetails; }
	 */

	/**
	 * DAO method to get user information.
	 * 
	 * @param userDetailsReq
	 * @return List containing user details {@link UserDetails}
	 * @throws HubCitiException
	 */
	public UserDetails getUserInfo(UserDetails userDetailsReq) throws HubCitiException {
		final String methodName = "getUserPreferedCategories in FirstUseDao";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		UserDetails objUserDetails = null;
		List<UserDetails> listUserDetails = null;
		List<EducationLevel> arEducationList = null;
		List<MaritalStatus> arMaritalList = null;
		List<IncomeRange> arIncomeRangeList = null;
		Boolean bEducatStatus = null;
		Boolean bMaritStatus = null;
		Boolean bIncomeStatus = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserSettingsPageDisplay");
			simpleJdbcCall.returningResultSet("getUserDetails", new BeanPropertyRowMapper<UserDetails>(UserDetails.class));
			final MapSqlParameterSource loginFlowParameter = new MapSqlParameterSource();
			loginFlowParameter.addValue(HubCitiConstants.USERID, userDetailsReq.getUserId());
			loginFlowParameter.addValue("HubCitiID", userDetailsReq.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(loginFlowParameter);
			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			// Integer status = (Integer)
			// resultFromProcedure.get(HubCitiConstants.STATUS);

			if (null != resultFromProcedure) {
				if (null == errorNum) {
					listUserDetails = (List<UserDetails>) resultFromProcedure.get("getUserDetails");
					bEducatStatus = (Boolean) resultFromProcedure.get("EducationLevel");
					bMaritStatus = (Boolean) resultFromProcedure.get("MarialStatus");
					bIncomeStatus = (Boolean) resultFromProcedure.get("IncomeRange");

					/*
					 * Implementation existing hubciti User setting in the new
					 * implementation, required field added
					 */
					if (null != listUserDetails && !listUserDetails.isEmpty()) {

						for (UserDetails userProp : listUserDetails) {

							if (userProp.getRequiredField() == null) {

								userProp.setRequiredField(false);

							}
						}
					}

					if (null != listUserDetails && !listUserDetails.isEmpty()) {
						objUserDetails = new UserDetails();
						for (int i = 0; i < listUserDetails.size(); i++) {
							if (("Education".equalsIgnoreCase(listUserDetails.get(i).getFieldName()) || "Marital Status".equalsIgnoreCase(listUserDetails.get(i).getFieldName()) || "Income Range"
									.equalsIgnoreCase(listUserDetails.get(i).getFieldName()))) {
								if ("".equals(Utility.checkNull(listUserDetails.get(i).getValue()))) {
									listUserDetails.get(i).setValue("Select");
								}
							}
						}
						objUserDetails.setListUserDetails(listUserDetails);
					}

					if (null != bEducatStatus && bEducatStatus == true) {
						simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
						simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
						simpleJdbcCall.withProcedureName("usp_HcEducationLevelDisplay");
						simpleJdbcCall.returningResultSet("EducationDetails", new BeanPropertyRowMapper<EducationLevel>(EducationLevel.class));
						final MapSqlParameterSource educatParam = new MapSqlParameterSource();
						final Map<String, Object> resEducationDetails = simpleJdbcCall.execute(educatParam);
						arEducationList = (List<EducationLevel>) resEducationDetails.get("EducationDetails");
						if (null != arEducationList && !arEducationList.isEmpty()) {
							objUserDetails.setArEducationList(arEducationList);
						}
					}

					if (null != bMaritStatus && bMaritStatus == true) {
						simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
						simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
						simpleJdbcCall.withProcedureName("usp_HcMaritalStatusDisplay");
						simpleJdbcCall.returningResultSet("MaritalDetails", new BeanPropertyRowMapper<MaritalStatus>(MaritalStatus.class));
						final MapSqlParameterSource maritalParam = new MapSqlParameterSource();
						final Map<String, Object> resMaritalDetails = simpleJdbcCall.execute(maritalParam);
						arMaritalList = (List<MaritalStatus>) resMaritalDetails.get("MaritalDetails");
						if (null != arMaritalList && !arMaritalList.isEmpty()) {
							objUserDetails.setArMaritalList(arMaritalList);
						}
					}

					if (null != bIncomeStatus && bIncomeStatus == true) {
						simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
						simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
						simpleJdbcCall.withProcedureName("usp_HcIncomeRangeDisplay");
						simpleJdbcCall.returningResultSet("IncomeDetails", new BeanPropertyRowMapper<IncomeRange>(IncomeRange.class));
						final MapSqlParameterSource incomeParam = new MapSqlParameterSource();
						final Map<String, Object> resIncomeDetails = simpleJdbcCall.execute(incomeParam);
						arIncomeRangeList = (List<IncomeRange>) resIncomeDetails.get("IncomeDetails");
						if (null != arIncomeRangeList && !arIncomeRangeList.isEmpty()) {
							objUserDetails.setArIncomeRangeList(arIncomeRangeList);
						}
					}
				} else {
					final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Error occurred in usp_HcUserSettingsPageDisplay Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
					throw new HubCitiException(errorMsg);
				}
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in  " + methodName + " ", HubCitiConstants.DATAACCESSEXCEPTIONCODE, exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in  " + methodName + " ", exception);
			throw new HubCitiException(exception);
		}
		return objUserDetails;
	}

	/**
	 * DAO method to update user information.
	 * 
	 * @param objUserDetailsReq
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 */
	// public UserDetails updateUserInfo(UserDetails objUserDetailsReq) throws
	// HubCitiException {
	public String updateUserInfo(UserDetails objUserDetailsReq) throws HubCitiException {
		final String methodName = "updateUserInfo in FirstUseDao";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		// UserDetails objUserDetailsResponse = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserInfo");
			final MapSqlParameterSource updateUserInfo = new MapSqlParameterSource();
			updateUserInfo.addValue(HubCitiConstants.USERID, objUserDetailsReq.getUserId());

			if (objUserDetailsReq.getFirstName() != null && !"".equals(objUserDetailsReq.getFirstName().trim())) {
				updateUserInfo.addValue("FirstName", objUserDetailsReq.getFirstName());
			} else {
				updateUserInfo.addValue("FirstName", null);
			}

			if (objUserDetailsReq.getLastName() != null && !"".equals(objUserDetailsReq.getLastName().trim())) {
				updateUserInfo.addValue("Lastname", objUserDetailsReq.getLastName());
			} else {
				updateUserInfo.addValue("Lastname", null);
			}

			if (objUserDetailsReq.getPostalCode() != null && !"".equals(objUserDetailsReq.getPostalCode().trim())) {
				updateUserInfo.addValue("PostalCode", objUserDetailsReq.getPostalCode());
			} else {
				updateUserInfo.addValue("PostalCode", null);
			}

			if (objUserDetailsReq.getGender() != null && !"".equals(objUserDetailsReq.getGender().trim())) {
				updateUserInfo.addValue("Gender", objUserDetailsReq.getGender());
			} else {
				updateUserInfo.addValue("Gender", null);
			}
			if (!"".equals(Utility.checkNull(objUserDetailsReq.getEmail()))) {
				updateUserInfo.addValue("Email", objUserDetailsReq.getEmail());
			} else {
				updateUserInfo.addValue("Email", null);
			}

			updateUserInfo.addValue("DOB", objUserDetailsReq.getDob());
			updateUserInfo.addValue("MobilePhone", objUserDetailsReq.getPhoneNum());
			updateUserInfo.addValue("DeviceID", objUserDetailsReq.getDeviceId());
			updateUserInfo.addValue("UniversityIDs", objUserDetailsReq.getUnivId());
			updateUserInfo.addValue("IncomeRangeID", objUserDetailsReq.getIncomeRangeId());
			updateUserInfo.addValue("MaritalStatusID", objUserDetailsReq.getMartialStatusId());
			updateUserInfo.addValue("EducationLevelID", objUserDetailsReq.getEducatonLevelId());
			updateUserInfo.addValue("Latitude", objUserDetailsReq.getLatitude());
			updateUserInfo.addValue("Longitude", objUserDetailsReq.getLongitude());
			updateUserInfo.addValue("HubCitiID", objUserDetailsReq.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(updateUserInfo);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (errorNum == null) {
				// objUserDetailsResponse = new UserDetails();
				/*
				 * Boolean userRangeFlag = (Boolean)
				 * resultFromProcedure.get("UserOutOfRange"); String
				 * defaultPostalCode = (String)
				 * resultFromProcedure.get("DefaultPostalCode"); if
				 * (userRangeFlag) {
				 * objUserDetailsResponse.setIsUserOutOfCiti(1); } else {
				 * objUserDetailsResponse.setIsUserOutOfCiti(0); } if
				 * (defaultPostalCode != null) {
				 * objUserDetailsResponse.setDefPostalCode(defaultPostalCode); }
				 */
				response = HubCitiConstants.SUCCESSTEXT;
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserInfo Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in  " + methodName + " ", HubCitiConstants.DATAACCESSEXCEPTIONCODE, exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in  " + methodName + " ", exception);
			throw new HubCitiException(exception);
		}
		return response;
	}

	/**
	 * DAOImpl method is to display main menu details
	 * 
	 * @param instance
	 *            of Menu.
	 * @return List of Menu details.
	 */
	public Menu menuDisplay(Menu objMenu) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : menuDisplay");
		List<MenuItem> arMenuItemList = new ArrayList<MenuItem>();
		List<BottomButton> arBottomBtnList = null;
		Menu objMenuDisp = new Menu();
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

		if ("".equals(Utility.checkNull(objMenu.getSortOrder()))) {
			objMenu.setSortOrder("None");
		}

		if ("".equals(Utility.checkNull(objMenu.getDateCreated()))) {
			objMenu.setDateCreated(null);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiMenuDisplay");
			simpleJdbcCall.returningResultSet("menuDisplayDetails", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource menuDisplayParam = new MapSqlParameterSource();

			menuDisplayParam.addValue("HubCitiID", objMenu.getHubCitiId());
			menuDisplayParam.addValue("LevelID", objMenu.getLevel());
			menuDisplayParam.addValue("UserID", objMenu.getUserId());
			menuDisplayParam.addValue("LinkID", objMenu.getLinkId());
			menuDisplayParam.addValue("RequestPlatformtype", objMenu.getPlatform());
			menuDisplayParam.addValue("SortOrder", objMenu.getSortOrder());
			menuDisplayParam.addValue("DepartmentID", objMenu.getDepartmentId());
			menuDisplayParam.addValue("TypeID", objMenu.getTypeId());
			menuDisplayParam.addValue("DeviceID", objMenu.getDeviceId());
			menuDisplayParam.addValue("PlatformOSVersion", objMenu.getOsVersion());
			menuDisplayParam.addValue("HcCityID", objMenu.getCityIds());
			menuDisplayParam.addValue("DateCheck", objMenu.getDateCreated());
			menuDisplayParam.addValue("DeviceType", objMenu.getDevType());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(menuDisplayParam);
			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (status == 0) {

				arMenuItemList = (List<MenuItem>) resultFromProcedure.get("menuDisplayDetails");

				bTempChanged = (Boolean) resultFromProcedure.get("TemplateChanged");
				if (null != bTempChanged) {
					if (bTempChanged) {
						objMenuDisp.setIsTempChanged(1);
					} else {
						objMenuDisp.setIsTempChanged(0);
					}
				}

				if (arMenuItemList != null && !arMenuItemList.isEmpty()) {

					strBannerImg = (String) resultFromProcedure.get("HCMenuBannerImage");
					strAppDownLink = (String) resultFromProcedure.get("DownLoadLinkIOS");
					strAndroidLink = (String) resultFromProcedure.get("DownLoadLinkAndroid");
					bDepartFlag = (Boolean) resultFromProcedure.get("HcDepartmentFlag");
					bTypeFlag = (Boolean) resultFromProcedure.get("HcTypeFlag");
					retGroupImg = (String) resultFromProcedure.get("RetailGroupButtonImagePath");
					arBottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
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

					objMenuDisp.setMenuId(arMenuItemList.get(0).getMenuId());
					objMenuDisp.setHubCitiId(arMenuItemList.get(0).getHubCitiId());
					objMenuDisp.setTemplateName(arMenuItemList.get(0).getTemplateName());
					objMenuDisp.setLevel(arMenuItemList.get(0).getLevel());
					objMenuDisp.setmBkgrdColor(arMenuItemList.get(0).getmBkgrdColor());
					objMenuDisp.setmBkgrdImage(arMenuItemList.get(0).getmBkgrdImage());
					objMenuDisp.setSmBkgrdColor(arMenuItemList.get(0).getSmBkgrdColor());
					objMenuDisp.setSmBkgrdImage(arMenuItemList.get(0).getSmBkgrdImage());
					objMenuDisp.setmFontColor(arMenuItemList.get(0).getmFontColor());
					objMenuDisp.setSmFontColor(arMenuItemList.get(0).getSmFontColor());
					objMenuDisp.setDateModified(arMenuItemList.get(0).getDateModified());
					objMenuDisp.setTemplateBgColor(arMenuItemList.get(0).getTemplateBgColor());

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

					for (int i = 0; i < arMenuItemList.size(); i++) {
						arMenuItemList.get(i).setMenuId(null);
						arMenuItemList.get(i).setHubCitiId(null);
						arMenuItemList.get(i).setTemplateName(null);
						arMenuItemList.get(i).setLevel(null);
						arMenuItemList.get(0).setSmBkgrdImage(null);
						arMenuItemList.get(0).setSmBkgrdColor(null);
						arMenuItemList.get(0).setmBkgrdImage(null);
						arMenuItemList.get(0).setmBkgrdColor(null);
						arMenuItemList.get(i).setmFontColor(null);
						arMenuItemList.get(i).setSmFontColor(null);
						arMenuItemList.get(i).setDateModified(null);
						arMenuItemList.get(i).setTemplateBgColor(null);
					}
					objMenuDisp.setArMItemList(arMenuItemList);
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

					if (arBottomBtnList != null && !arBottomBtnList.isEmpty()) {
						objMenuDisp.setArBottomBtnList(arBottomBtnList);
					}

					objMenuDisp.setTempBkgrdImg(tempBkGrdImg);
					objMenuDisp.setBtnBkgrdColor(strBtnBkgrdColor);
					objMenuDisp.setBtnLblColor(strBtnLblColor);

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
				final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : menuDisplay : Store Procedure error : usp_HcHubCitiMenuDisplay :  ", objErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : menuDisplay : Store Procedure error : usp_HcHubCitiMenuDisplay :  " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : menuDisplay");
		return objMenuDisp;
	}

	/**
	 * DAOImpl method fetches user details for sending password to user email
	 * id.
	 * 
	 * @param strUserName
	 *            request parameter
	 * @return User Details
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public AuthenticateUser forgotPassword(String strUserName, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : forgotPassword");
		AuthenticateUser objAuthentUser = new AuthenticateUser();
		List<AuthenticateUser> listUserDetails;
		Integer iStatus = 0;
		Integer iSuccess = 0;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiUserForgotPassword");
			simpleJdbcCall.returningResultSet("getUserDetails", new BeanPropertyRowMapper<AuthenticateUser>(AuthenticateUser.class));
			final MapSqlParameterSource authentUserParam = new MapSqlParameterSource();
			authentUserParam.addValue("UserName", strUserName);
			authentUserParam.addValue("HubCitiID", hubCitiId);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(authentUserParam);
			iStatus = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			iSuccess = (Integer) resultFromProcedure.get(HubCitiConstants.SUCCESSTEXT);

			if (iStatus == 0 && iSuccess == 0) {
				listUserDetails = (List<AuthenticateUser>) resultFromProcedure.get("getUserDetails");
				objAuthentUser.setEmail(listUserDetails.get(0).getEmail());
				objAuthentUser.setUserName(listUserDetails.get(0).getUserName());
				objAuthentUser.setPassword(listUserDetails.get(0).getPassword());
				objAuthentUser.setSuccess(iSuccess);
			} else if (iStatus == 0 && iSuccess == 1) {
				objAuthentUser.setSuccess(iSuccess);
			} else {
				final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : forgotPassword : Store Procedure error : usp_HcHubCitiUserForgotPassword:  ", objErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : forgotPassword : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return objAuthentUser;
	}

	/**
	 * DAOImpl method Fetching App Configuration details.
	 * 
	 * @return ArrayList<AppConfiguration> .
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public ArrayList<AppConfiguration> getAppConfig(String configType) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : getAppConfig");

		ArrayList<AppConfiguration> appConfigurationList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_GetScreenContent");
			simpleJdbcCall.returningResultSet("AppConfigurationList", new BeanPropertyRowMapper<AppConfiguration>(AppConfiguration.class));
			final MapSqlParameterSource appConfigParams = new MapSqlParameterSource();
			appConfigParams.addValue("ConfigurationType", configType);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(appConfigParams);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (null != resultFromProcedure) {
				if (null == errorNum) {
					appConfigurationList = (ArrayList<AppConfiguration>) resultFromProcedure.get("AppConfigurationList");
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Exception in FirstUseDaoImpl : getAppConfig : " + e.getMessage());
			throw new HubCitiException(e);
		}
		LOG.info("Exiting FirstUseDaoImpl : getAppConfig");
		return appConfigurationList;
	}

	/**
	 * The DAOImpl method to saves user password.
	 * 
	 * @param userID
	 *            request parameter.
	 * @param password
	 *            request parameter.
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String changePassword(Integer userID, String password) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : changePassword");
		Integer fromProc;
		Map<String, Object> resultFromProcedure = null;
		String strResponse = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcChangePassword");
			final MapSqlParameterSource changePwdParam = new MapSqlParameterSource();
			changePwdParam.addValue("userid", userID);
			changePwdParam.addValue("password", password);
			resultFromProcedure = simpleJdbcCall.execute(changePwdParam);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (fromProc == 0) {
				strResponse = HubCitiConstants.SUCCESS;
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside FirstUseDaoImpl : changePassword : Store Procedure error : usp_HcChangePassword : ", errorNum, strErrorMsg);
				strResponse = HubCitiConstants.FAILURE;
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : changePassword : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return strResponse;
	}

	/**
	 * The DAOImpl method to get started images information from the database.
	 * 
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 * @return TutorialMediaResultSet object.
	 */
	public TutorialMediaResultSet getStartedImageInfo() throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : getStartedImageInfo");
		TutorialMediaResultSet tutorialMediaResultSet = null;
		List<TutorialMedia> arTutorialMediaParam = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFetchTutorialImages");
			simpleJdbcCall.returningResultSet("fetchTutorialImagesInfo", new BeanPropertyRowMapper<TutorialMedia>(TutorialMedia.class));
			final MapSqlParameterSource fetchTutMedia = new MapSqlParameterSource();
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchTutMedia);
			arTutorialMediaParam = (List<TutorialMedia>) resultFromProcedure.get("fetchTutorialImagesInfo");
			if (null != arTutorialMediaParam && !arTutorialMediaParam.isEmpty()) {
				tutorialMediaResultSet = new TutorialMediaResultSet();
				tutorialMediaResultSet.setTutorialMedia(arTutorialMediaParam);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : getStartedImageInfo : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return tutorialMediaResultSet;
	}

	public Integer getMainMenuId(MenuItem objMenuItem) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : getMainMenuId");
		Integer mainMenuId = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTrackingMainMenuCreation");
			final MapSqlParameterSource fetchTutMedia = new MapSqlParameterSource();
			fetchTutMedia.addValue("UserID", objMenuItem.getUserId());
			fetchTutMedia.addValue("HubCitiID", objMenuItem.getHubCitiId());
			fetchTutMedia.addValue("MenuItemID", objMenuItem.getmItemId());
			fetchTutMedia.addValue("BottomButtonID", objMenuItem.getBottomBtnId());
			fetchTutMedia.addValue("RequestPlatformtype", objMenuItem.getPlatform());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchTutMedia);

			Integer fromProc = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (fromProc == null) {
				mainMenuId = (Integer) resultFromProcedure.get("MainMenuID");
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside FirstUseDaoImpl : getMainMenuId() : Store Procedure error : usp_HcUserTrackingMainMenuCreation : ", errorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : getMainMenuId() : " + e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		return mainMenuId;
	}

	/**
	 * The DAO method for fetching user information from the database for the
	 * given userID.
	 * 
	 * @param userID
	 *            for which user information need to be fetched.
	 * @throws HubCitiException
	 *             If any exception occures HubCitiException will be thrown.
	 * @return UserInformation object.
	 */
	public UpdateUserInfo fetchUserInfo(int userId, boolean isCellFireRequest) throws HubCitiException {
		final String methodName = "fetchUserInfo";
		LOG.info("In DAO...." + HubCitiConstants.METHODSTART + methodName);
		LOG.info("userID...." + userId);
		UpdateUserInfo updateUserInfo = null;
		List<UpdateUserInfo> updateUserInfoList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserInfoDisplay");
			simpleJdbcCall.returningResultSet("UserInfo", new BeanPropertyRowMapper<UpdateUserInfo>(UpdateUserInfo.class));

			final MapSqlParameterSource fetchUserInfoParameters = new MapSqlParameterSource();
			fetchUserInfoParameters.addValue("UserId", userId);
			fetchUserInfoParameters.addValue("IsCellFireRequest", isCellFireRequest);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchUserInfoParameters);
			updateUserInfoList = (ArrayList<UpdateUserInfo>) resultFromProcedure.get("UserInfo");
			if (null != updateUserInfoList && !updateUserInfoList.isEmpty()) {
				updateUserInfo = new UpdateUserInfo();
				updateUserInfo = updateUserInfoList.get(0);
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName + e);
			throw new HubCitiException(e);
		}

		if (null != updateUserInfo) {
			return updateUserInfo;
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return updateUserInfo;
	}

	/**
	 * This is a DAO Method To get department Name, ID, menuItemType Name, ID.
	 * 
	 * @param xml
	 * @return
	 * @throws HubCitiException
	 */
	public List<MenuItem> getDepartmentAndMenuTypeDetails(MenuItem objMenuItem) throws HubCitiException {
		final String methodName = "getDepartmentAndMenuTypeDetails() in FirstUseDao";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		List<MenuItem> menuItemList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcMenuDepartmentAndTypeDisplay");
			simpleJdbcCall.returningResultSet("getUserDetails", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));
			final MapSqlParameterSource deptMenuParam = new MapSqlParameterSource();
			deptMenuParam.addValue("MenuID", objMenuItem.getMenuId());
			deptMenuParam.addValue("HcHubCitiID", objMenuItem.getHubCitiId());
			deptMenuParam.addValue("FilterName", objMenuItem.getfName());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(deptMenuParam);

			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			if (status == 0) {
				menuItemList = (List<MenuItem>) resultFromProcedure.get("getUserDetails");
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcMenuDepartmentAndTypeDisplay Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in  " + methodName + " ", HubCitiConstants.DATAACCESSEXCEPTIONCODE, exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in  " + methodName + " ", exception);
			throw new HubCitiException(exception);
		}
		return menuItemList;
	}

	/**
	 * The DAOImpl method to update database to enable or disable push
	 * notification alert for a specified user or to update wish list radius
	 * 
	 * @param userSettings
	 *            instance of UserSettings pojo class.
	 * @return success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String setUserSettings(UserSettings userSettings) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : setUserSettings");
		String response = null;

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
		simpleJdbcCall.withProcedureName("usp_HcUpdateUserPreferences");

		if (null == userSettings.getPushNotify()) {
			userSettings.setPushNotify(false);
		}
		if (null == userSettings.getbLocService()) {
			userSettings.setbLocService(false);
		}

		final MapSqlParameterSource usrSettingsParam = new MapSqlParameterSource();
		usrSettingsParam.addValue("UserID", userSettings.getUserId());
		usrSettingsParam.addValue("PushNotify", userSettings.getPushNotify());
		usrSettingsParam.addValue("WishListDefaultRadius", userSettings.getLocaleRadius());
		usrSettingsParam.addValue("DeviceID", userSettings.getDeviceId());
		usrSettingsParam.addValue("HubCitiID", userSettings.getHubCitiId());
		usrSettingsParam.addValue("LocationService", userSettings.getbLocService());
		try {
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(usrSettingsParam);
			Integer result = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (result == null) {
				response = HubCitiConstants.SUCCESS;
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : setUserSettings : Store Procedure error : usp_HcUpdateUserPreferences :  " + errorNum + " and error message:" + errorMsg);
				response = HubCitiConstants.FAILURE;
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : setUserSettings : Store Procedure error : usp_HcUpdateUserPreferences : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return response;
	}

	/**
	 * The DAO method to get push notification alert for a specidfied user from
	 * database or to get wish list radius
	 * 
	 * @param userId
	 * @return userSettings object
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public List<UserSettings> getUserSettings(AuthenticateUser objAuthUser) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : getUserSettings");
		List<UserSettings> arUserSettingsList = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFetchUserPreferences");
			simpleJdbcCall.returningResultSet("usersettings", new BeanPropertyRowMapper<UserSettings>(UserSettings.class));

			final MapSqlParameterSource fetchUserSettings = new MapSqlParameterSource();
			fetchUserSettings.addValue("UserID", objAuthUser.getUserId());
			fetchUserSettings.addValue("DeviceID", objAuthUser.getDeviceID());
			fetchUserSettings.addValue("HubCitiID", objAuthUser.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchUserSettings);

			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					arUserSettingsList = (List<UserSettings>) resultFromProcedure.get("usersettings");
				}
			} else {
				LOG.error("Inside FirstUseDaoImpl : getUserSettings : Store Procedure error : usp_HcFetchUserPreferences : error message: ");
				throw new HubCitiException();
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : getUserSettings : Store Procedure error : usp_HcFetchUserPreferences : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return arUserSettingsList;
	}

	/**
	 * The DAOImpl method to update database to enable or disable push
	 * notification alert for a specified user or to update wish list radius
	 * 
	 * @param userID
	 *            , emailId for which user information need to be fetched.
	 * @return success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String saveUserEmailId(Integer userID, String emailId) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : saveUserEmailId");
		Integer fromProc = null;
		Map<String, Object> resultFromProcedure = null;
		String response = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserEmailUpdation");
			final MapSqlParameterSource changePasswordParameters = new MapSqlParameterSource();
			changePasswordParameters.addValue("UserID", userID);
			changePasswordParameters.addValue("Email", emailId);
			resultFromProcedure = simpleJdbcCall.execute(changePasswordParameters);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (fromProc == 0) {
				response = HubCitiConstants.SUCCESS;
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : saveUserEmailId : Store Procedure error : usp_HcUserEmailUpdation :  " + errorNum + " and error message:" + errorMsg);
				response = HubCitiConstants.FAILURE;
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : saveUserEmailId : Store Procedure error : usp_HcUserEmailUpdation : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return response;
	}

	public ArrayList<CountryCode> fetchUnivStates(Integer hubCitiId) throws HubCitiException {
		final String methodName = "fetchUnivStates";
		LOG.info("In DAO...." + HubCitiConstants.METHODSTART + methodName);

		ArrayList<CountryCode> univstateslst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetrieveState");
			simpleJdbcCall.returningResultSet("univstates", new BeanPropertyRowMapper<CountryCode>(CountryCode.class));

			final MapSqlParameterSource fetchUserInfoParameters = new MapSqlParameterSource();
			fetchUserInfoParameters.addValue("HubCitiID", hubCitiId);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchUserInfoParameters);
			univstateslst = (ArrayList<CountryCode>) resultFromProcedure.get("univstates");
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName + e);
			throw new HubCitiException(e);
		}
		return univstateslst;
	}

	public ArrayList<CountryCode> fetchUniversities(Integer hubCitiId, String stateAbv) throws HubCitiException {
		final String methodName = "fetchUniversities";
		LOG.info("In DAO...." + HubCitiConstants.METHODSTART + methodName);

		ArrayList<CountryCode> univstateslst = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcRetrieveUniversity");
			simpleJdbcCall.returningResultSet("universities", new BeanPropertyRowMapper<CountryCode>(CountryCode.class));

			final MapSqlParameterSource fetchUserInfoParameters = new MapSqlParameterSource();
			fetchUserInfoParameters.addValue("HubCitiID", hubCitiId);
			fetchUserInfoParameters.addValue("State", stateAbv);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(fetchUserInfoParameters);
			univstateslst = (ArrayList<CountryCode>) resultFromProcedure.get("universities");
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName + e);
			throw new HubCitiException(e);
		}
		return univstateslst;
	}

	/**
	 * Method to check is user is outside hubCiti or not by taking latitude,
	 * longitude or postal code. Method Type:POST
	 * 
	 * @param UserDetails
	 *            object containing userId, hubCitiId, latitude and longitude or
	 *            postal code.
	 * @return UserDetails object containing flag to check is user out of
	 *         hubCiti and postal code if user outside hubCiti.
	 */
	public UserDetails hubCitiUserRangeCheck(UserDetails objUserDetailsRequest) throws HubCitiException {
		final String methodName = "In FirstUseDaoImpl : hubCitiUserRangeCheck()";
		LOG.info("In DAO...." + HubCitiConstants.METHODSTART + methodName);
		UserDetails objUserDetailsResponse = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserHubCitiRangeCheck");

			final MapSqlParameterSource insertUserInfo = new MapSqlParameterSource();
			insertUserInfo.addValue("UserID", objUserDetailsRequest.getUserId());
			insertUserInfo.addValue("HubCitiID", objUserDetailsRequest.getHubCitiId());
			insertUserInfo.addValue("Latitude", objUserDetailsRequest.getLatitude());
			insertUserInfo.addValue("Longitude", objUserDetailsRequest.getLongitude());
			insertUserInfo.addValue("PostalCode", objUserDetailsRequest.getPostalCode());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(insertUserInfo);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == errorNum) {
				Boolean userRangeFlag = (Boolean) resultFromProcedure.get("UserOutOfRange");
				String defaultPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");
				objUserDetailsResponse = new UserDetails();

				if (userRangeFlag) {
					objUserDetailsResponse.setIsUserOutOfCiti(1);
				} else {
					objUserDetailsResponse.setIsUserOutOfCiti(0);
				}

				if (defaultPostalCode != null) {
					objUserDetailsResponse.setDefPostalCode(defaultPostalCode);
				}
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside " + methodName + " Store Procedure error : usp_HcUserHubCitiRangeCheck :  " + errorNum + " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName + e);
			throw new HubCitiException(e);
		}
		return objUserDetailsResponse;
	}

	/**
	 * DAOImpl method is to get particular FAQ Details.
	 * 
	 * @param hubCitiId
	 *            , faqId as input parameter.
	 * @return FAQDetails object containing particular FAQ Details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public FAQDetails fetchFaqDetails(FAQDetails objFaqDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : fetchFaqDetails");
		FAQDetails objFAQDetail = null;
		List<FAQDetails> listFAQDetails;
		Integer iStatus = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFAQDetails");
			simpleJdbcCall.returningResultSet("getFAQDetails", new BeanPropertyRowMapper<FAQDetails>(FAQDetails.class));
			final MapSqlParameterSource objFaqParam = new MapSqlParameterSource();
			objFaqParam.addValue("HcHubcitiID", objFaqDetails.getHubCitiId());
			objFaqParam.addValue("HcFAQID", objFaqDetails.getFaqId());
			objFaqParam.addValue("FAQListID", objFaqDetails.getFaqListId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(objFaqParam);
			iStatus = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			if (iStatus == 0) {
				listFAQDetails = (List<FAQDetails>) resultFromProcedure.get("getFAQDetails");
				if (null != listFAQDetails && !listFAQDetails.isEmpty()) {
					objFAQDetail = listFAQDetails.get(0);
				}
			} else {
				final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : fetchFaqDetails : Store Procedure error : usp_HcFAQDetails:  ", objErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : fetchFaqDetails : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return objFAQDetail;
	}

	/**
	 * DAOImpl method to get All FAQ Display or based on searchkey.
	 * 
	 * @param FAQDetails
	 *            object as input parameter.
	 * @return XML containing response with list FAQ Display.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public FAQDetails fetchFaqDisplay(FAQDetails objFaqDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : fetchFaqDetails");
		FAQDetails objFAQDetail = null;
		List<FAQDetails> listFAQDetails;
		Integer iMaxRowNum = 0;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFAQDisplay");
			simpleJdbcCall.returningResultSet("getFAQDisplay", new BeanPropertyRowMapper<FAQDetails>(FAQDetails.class));
			final MapSqlParameterSource objFaqParam = new MapSqlParameterSource();
			objFaqParam.addValue("HcHubcitiID", objFaqDetails.getHubCitiId());
			objFaqParam.addValue("CategoryID", objFaqDetails.getCategoryId());
			objFaqParam.addValue("SearchKey", objFaqDetails.getSearchKey());
			objFaqParam.addValue("LowerLimit", objFaqDetails.getLowerLimit());
			objFaqParam.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);
			objFaqParam.addValue("MainMenuID", objFaqDetails.getMainMenuId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(objFaqParam);
			if (null != resultFromProcedure) {
				if (null == resultFromProcedure.get("ErrorNumber")) {
					listFAQDetails = (List<FAQDetails>) resultFromProcedure.get("getFAQDisplay");
					if (null != listFAQDetails && !listFAQDetails.isEmpty()) {
						Integer iMaxCnt = (Integer) resultFromProcedure.get("MaxCnt");
						Boolean nextPage = (Boolean) resultFromProcedure.get("NxtPageFlag");
						objFAQDetail = new FAQDetails();
						objFAQDetail.setFaqDetail(listFAQDetails);
						// To get max row number
						iMaxRowNum = listFAQDetails.get(listFAQDetails.size() - 1).getRowNum();
						objFAQDetail.setMaxRowNum(iMaxRowNum);
						objFAQDetail.setMaxCount(iMaxCnt);
						if (nextPage != null && nextPage == true) {
							objFAQDetail.setNextPageFlag(1);
						} else {
							objFAQDetail.setNextPageFlag(0);
						}
					}
				} else {
					final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
					final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
					LOG.error("Inside FirstUseDaoImpl : fetchFaqDisplay : Store Procedure error : usp_HcFAQDisplay:  ", objErrorNum, strErrorMsg);
					throw new HubCitiException(strErrorMsg);
				}
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : fetchFaqDisplay : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return objFAQDetail;
	}

	/**
	 * Method to fetch FAQ's categories.
	 * 
	 * @param hubCitiId
	 * @param lowerLimit
	 * @return {@link Categories} object.
	 * @throws HubCitiException
	 */
	public Categories getFAQsCategories(Integer hubCitiId, Integer lowerLimit, Integer userId, String searchKey) throws HubCitiException {
		final String methodName = "In FirstUseDaoImpl : getFAQsCategories()";
		LOG.info("In DAO...." + HubCitiConstants.METHODSTART + methodName);
		List<FAQDetails> faqCategoryList = null;
		List<BottomButton> bottomBtnList = null;
		Categories categories = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcFAQCategoryDisplay");

			final MapSqlParameterSource insertUserInfo = new MapSqlParameterSource();
			insertUserInfo.addValue("HcHubCitiID", hubCitiId);
			insertUserInfo.addValue("LowerLimit", lowerLimit);
			insertUserInfo.addValue("SearchKey", searchKey);
			insertUserInfo.addValue("userID", userId);
			insertUserInfo.addValue("ScreenName", HubCitiConstants.HUBCITIAPP);

			simpleJdbcCall.returningResultSet("catDetails", new BeanPropertyRowMapper<FAQDetails>(FAQDetails.class));
			/* Setup Bottom Buttons dynamically in HubCitiApp. */
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(insertUserInfo);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == errorNum) {
				faqCategoryList = (List<FAQDetails>) resultFromProcedure.get("catDetails");
				bottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");

				categories = new Categories();
				if (null != bottomBtnList && !bottomBtnList.isEmpty()) {
					categories.setBottomBtnList(bottomBtnList);
					categories.setBottomBtn(1);
				} else {
					categories.setBottomBtn(0);
				}

				if (null != faqCategoryList && !faqCategoryList.isEmpty()) {
					Integer maxCnt = (Integer) resultFromProcedure.get("MaxCnt");
					Boolean nextPage = (Boolean) resultFromProcedure.get("NxtPageFlag");
					categories.setFaqCatList(faqCategoryList);
					categories.setMaxCnt(maxCnt);
					categories.setMaxRowNum(faqCategoryList.get(faqCategoryList.size() - 1).getRowNum());

					if (nextPage) {
						categories.setNextPage(1);
					} else {
						categories.setNextPage(0);
					}

					for (FAQDetails faqObj : faqCategoryList) {
						faqObj.setRowNum(null);
					}
				}
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside " + methodName + " Store Procedure error : usp_HcFAQCategoryDisplay :  " + errorNum + " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName + e);
			throw new HubCitiException(e);
		}
		return categories;
	}

	/**
	 * DAOImpl method to get City Preferences.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of City List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public City getCityPreferences(UserDetails objUserDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : getCityPreferences");

		City objCity = null;
		List<City> cityList = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcCityList");

			simpleJdbcCall.returningResultSet("getCityPreferences", new BeanPropertyRowMapper<City>(City.class));

			final MapSqlParameterSource getCityPreferenceParam = new MapSqlParameterSource();
			getCityPreferenceParam.addValue(HubCitiConstants.USERID, objUserDetails.getUserId());
			getCityPreferenceParam.addValue("HcHubcitiID", objUserDetails.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(getCityPreferenceParam);

			final Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == iErrorNum) {
				cityList = (List<City>) resultFromProcedure.get("getCityPreferences");
				if (null != cityList && !cityList.isEmpty()) {
					objCity = new City();

					/*
					 * Collections.sort(cityList, new Comparator<City>() {
					 * public int compare(City c1, City c2) { return
					 * c1.getCityName().compareToIgnoreCase(c2.getCityName()); }
					 * });
					 */

					objCity.setCityList(cityList);
				}
			} else {
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : getCityPreferences : usp_HcCityList ", iErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : getCityPreferences : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : getCityPreferences");
		return objCity;
	}

	/**
	 * DAOImpl method to set City Preferences.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return String containing SUCCESS or FAILURE.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String setCityPreferences(UserDetails objUserDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : setCityPreferences");

		String response = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserCityAssociation");

			final MapSqlParameterSource setCityPreferenceParam = new MapSqlParameterSource();
			setCityPreferenceParam.addValue(HubCitiConstants.USERID, objUserDetails.getUserId());
			setCityPreferenceParam.addValue("HcHubcitiID", objUserDetails.getHubCitiId());
			setCityPreferenceParam.addValue("CitiID", objUserDetails.getCityIds());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(setCityPreferenceParam);

			final Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (iErrorNum == null) {
				response = HubCitiConstants.SUCCESSCODE;
			} else {
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : setCityPreferences : usp_HcUserCityAssociation ", iErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : setCityPreferences : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : setCityPreferences");
		return response;
	}

	/**
	 * DAOImpl method to get user city preferences.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of City List.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public City getUserCityPreferences(UserDetails objUserDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : getUserCityPreferences");

		City objCity = null;
		List<City> cityList = null;

		try {

			final MapSqlParameterSource getUserCityPrefParam = new MapSqlParameterSource();

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			if ("CitiEXP".equals(objUserDetails.getModule())) {

				simpleJdbcCall.withProcedureName("usp_HcCityExperiencePreferredCityList");
				getUserCityPrefParam.addValue("CityExperienceID", objUserDetails.getCitiExpId());

			} else if ("Events".equalsIgnoreCase(objUserDetails.getModule()) || "Fund".equalsIgnoreCase(objUserDetails.getModule())) {
				if ("Events".equalsIgnoreCase(objUserDetails.getModule())) {
					simpleJdbcCall.withProcedureName("usp_HcEventsPreferredCityList");
				} else {
					simpleJdbcCall.withProcedureName("usp_HcFundRaisingPreferredCityList");
				}
				getUserCityPrefParam.addValue("RetailID", objUserDetails.getRetailId());
				getUserCityPrefParam.addValue("RetailLocationID", objUserDetails.getRetailLocationId());
				getUserCityPrefParam.addValue("FundRaisingID", objUserDetails.getFundId());

			} else if ("Find All".equalsIgnoreCase(objUserDetails.getModule())) {

				simpleJdbcCall.withProcedureName("usp_HcFindPreferredCityList");
			} else if ("Find Single".equalsIgnoreCase(objUserDetails.getModule())) {

				simpleJdbcCall.withProcedureName("usp_HcSingleFindPreferredCityList");
			} else if ("SubMenu".equalsIgnoreCase(objUserDetails.getModule())) {

				simpleJdbcCall.withProcedureName("usp_HcSubMenuPreferredCityList");
				getUserCityPrefParam.addValue("LinkID", objUserDetails.getLinkId());
				getUserCityPrefParam.addValue("LevelID", objUserDetails.getLevel());
				getUserCityPrefParam.addValue("TypeID", objUserDetails.getTypeId());
				getUserCityPrefParam.addValue("DepartmentID", objUserDetails.getDepartmentId());

			} else if ("thislocation".equalsIgnoreCase(objUserDetails.getModule())) {
				simpleJdbcCall.withProcedureName("usp_HcThisLocationPreferredCityList");
			} else if("bandevents".equalsIgnoreCase(objUserDetails.getModule())){
				simpleJdbcCall.withProcedureName("usp_HcBandEventsPreferredCityList");
				getUserCityPrefParam.addValue("RetailID", objUserDetails.getRetailId());


			} else if("SpecialOffers".equalsIgnoreCase(objUserDetails.getModule())){
				simpleJdbcCall.withProcedureName("usp_HcSpecialOffersCityList");
				getUserCityPrefParam.addValue("UserID", objUserDetails.getUserId());
			} else if("Coupons".equalsIgnoreCase(objUserDetails.getModule())){
				simpleJdbcCall.withProcedureName("usp_HcCouponsCityList");
				getUserCityPrefParam.addValue("HcUserID", objUserDetails.getUserId());
				getUserCityPrefParam.addValue("RetailID", objUserDetails.getRetailId());

			}else if("myaccounts".equalsIgnoreCase(objUserDetails.getModule())){
				simpleJdbcCall.withProcedureName("usp_HcCouponsGalleryCityList");
				getUserCityPrefParam.addValue("HcUserID", objUserDetails.getUserId());
				getUserCityPrefParam.addValue("RetailID", objUserDetails.getRetailId());
			}

			simpleJdbcCall.returningResultSet("getUserCityPreferences", new BeanPropertyRowMapper<City>(City.class));

			getUserCityPrefParam.addValue("CategoryID", objUserDetails.getCatIds());
			getUserCityPrefParam.addValue("HcMenuItemID", objUserDetails.getmItemId());
			getUserCityPrefParam.addValue("HcBottomButtonID", objUserDetails.getBottomBtnId());

			getUserCityPrefParam.addValue("Latitude", objUserDetails.getLatitude());
			getUserCityPrefParam.addValue("Longitude", objUserDetails.getLongitude());
			getUserCityPrefParam.addValue("CategoryName", objUserDetails.getCatName());
			getUserCityPrefParam.addValue("Radius", objUserDetails.getRadius());
			getUserCityPrefParam.addValue("BusinessSubCategoryID", objUserDetails.getSubCatId());

			getUserCityPrefParam.addValue(HubCitiConstants.USERID, objUserDetails.getUserId());
			getUserCityPrefParam.addValue("HcHubcitiID", objUserDetails.getHubCitiId());
			getUserCityPrefParam.addValue("HubcitiID", objUserDetails.getHubCitiId());
			getUserCityPrefParam.addValue("SearchKey", objUserDetails.getSrchKey());
			getUserCityPrefParam.addValue("Postalcode", objUserDetails.getPostalCode());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(getUserCityPrefParam);

			final Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == iErrorNum) {
				cityList = (List<City>) resultFromProcedure.get("getUserCityPreferences");
				if (null != cityList && !cityList.isEmpty()) {
					objCity = new City();

					/*
					/*
					 * Collections.sort(cityList, new Comparator<City>() {
					 * public int compare(City c1, City c2) { return
					 * c1.getCityName().compareToIgnoreCase(c2.getCityName()); }
					 * });
					 */					objCity.setCityList(cityList);
				}
			} else {
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : getUserCityPreferences : usp_HcUserPreferredCityList", iErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : getUserCityPreferences : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : getUserCityPreferences");
		return objCity;
	}

	/**
	 * DAOImpl method to get FAQ Setting bit flag.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of UserDetails.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public UserDetails getFaqSetting(UserDetails objUsrDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : getFaqSetting");

		UserDetails objUserDetails = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiSettings");

			final MapSqlParameterSource getFaqSettingParam = new MapSqlParameterSource();
			getFaqSettingParam.addValue("HcUserID", objUsrDetails.getUserId());
			getFaqSettingParam.addValue("HubCitiID", objUsrDetails.getHubCitiId());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(getFaqSettingParam);

			final Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			final Integer iFaqExist = (Integer) resultFromProcedure.get("FaqExists");

			if (iErrorNum == null) {
				objUserDetails = new UserDetails();
				if (null != iFaqExist) {
					objUserDetails.setIsFaqExist(iFaqExist);
				} else {
					objUserDetails.setIsFaqExist(0);
				}
			} else {
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : getFaqSetting : usp_HcHubCitiSettings ", iErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}

		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : getFaqSetting : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : getFaqSetting");
		return objUserDetails;
	}

	/**
	 * This method insert the user push notification details.
	 * 
	 * @param userRegInfo
	 *            instance of UserRegistrationInfo.
	 * @return success or failure depending upon the result of operation.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String registerPushNotification(UserRegistrationInfo userRegInfo) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : registerPushNotification");

		Map<String, Object> resultFromProcedure = null;
		String strResponse = null;
		// Integer iAlreadyExist = null;
		Integer iStatus = null;

		try {

			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);

			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserTokenInsertion");

			final MapSqlParameterSource userRegParam = new MapSqlParameterSource();
			userRegParam.addValue("DeviceID", userRegInfo.getDeviceID());
			userRegParam.addValue("UserToken", userRegInfo.getUserTokenID());
			userRegParam.addValue("RequestPlatformType", userRegInfo.getPlatform());
			userRegParam.addValue("FaceBookAuthenticatedUser", 0);
			userRegParam.addValue("HcHubCitiKey", userRegInfo.getHcKey());

			resultFromProcedure = simpleJdbcCall.execute(userRegParam);
			iStatus = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			final Integer iErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (iErrorNum == null) {
				if (iStatus == 0) {
					/*
					 * iAlreadyExist = (Integer)
					 * resultFromProcedure.get("Result"); if (iAlreadyExist !=
					 * null) { if (iAlreadyExist == -1) { strResponse =
					 * "Exists"; } else { strResponse =
					 * HubCitiConstants.SUCCESS; } }
					 */
					strResponse = HubCitiConstants.SUCCESS;
				} else {
					strResponse = HubCitiConstants.FAILURE;
				}

			} else {
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : registerPushNotification : usp_UserTokenInsertion ", iErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);

			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : registerPushNotification : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : registerPushNotification");
		return strResponse;
	}

	public CategoryDetails getUserPreferedLocationCategories(Integer userId, Integer hubCitiId) throws HubCitiException {
		final String methodName = "getUserPreferedCategories in FirstUseDao";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		CategoryDetails objCatDetails = null;
		List<CategoryDetails> listCategoryDetails;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcPreferredLocationCategoryDisplay");
			simpleJdbcCall.returningResultSet("getUserPrefCat", new BeanPropertyRowMapper<CategoryDetails>(CategoryDetails.class));
			final MapSqlParameterSource sqlParameters = new MapSqlParameterSource();
			sqlParameters.addValue("HcUserID", userId);
			sqlParameters.addValue("HubCitiID", hubCitiId);
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(sqlParameters);

			Integer errorNumb = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (errorNumb == null) {
				listCategoryDetails = (List<CategoryDetails>) resultFromProcedure.get("getUserPrefCat");
				objCatDetails = new CategoryDetails();
				objCatDetails.setListCatDetails(listCategoryDetails);
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcPreferredLocationCategoryDisplay Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in  " + methodName + " ", HubCitiConstants.DATAACCESSEXCEPTIONCODE, exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in " + methodName + " ", exception);
			throw new HubCitiException(exception);
		}
		return objCatDetails;
	}

	public String updateUserPreferedLocationCategories(CategoryDetails objCategoryDetails) throws HubCitiException {

		LOG.info("Inside FirstUseDaoImpl : updateUserPreferedLocationCategories");
		String response = null;

		if ("".equals(Utility.checkNull(objCategoryDetails.getCatIds()))) {
			objCategoryDetails.setCatIds(null);
		}

		if ("".equals(Utility.checkNull(objCategoryDetails.getSubCatIds()))) {
			objCategoryDetails.setSubCatIds(null);
		}
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcPreferredLocationCategoryUpdation");
			final MapSqlParameterSource setPreferences = new MapSqlParameterSource();
			setPreferences.addValue("HcUserID", objCategoryDetails.getUserId());
			setPreferences.addValue("HubCitiID", objCategoryDetails.getHubCitiId());
			setPreferences.addValue("BusinessCategoryIDs", objCategoryDetails.getCatIds());
			setPreferences.addValue("SubCategoryIDs", objCategoryDetails.getSubCatIds());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(setPreferences);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (errorNum == null) {
				response = HubCitiConstants.SUCCESSCODE;
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcPreferredLocationCategoryUpdation Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : updateUserPreferedLocationCategories ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : updateUserPreferedLocationCategories ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl  : updateUserPreferedLocationCategories");
		return response;
	}

	/**
	 * DAOImpl Method to find pushNotify Back Ground details.
	 * 
	 * @param hubCitiId
	 *            .
	 * @return json containing the List of pushNotify details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public Data pushNotifyBackGrd(Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : pushNotifyBackGrd");

		List<RSSFeedMessage> rssfeedMsgList = null;
		List<Deal> dealList = null;
		Map<String, RSSFeedMessage> rssFeedMap = null;
		RSSFeedMessage objMsgData = null;
		Integer rssMsgCount = 0;
		String strMessage = "";
		List<String> strNewsType = null;

		Data objData = null;
		Boolean isNewExist = true;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserPushNotifyBackground");

			final MapSqlParameterSource insertUserInfo = new MapSqlParameterSource();
			insertUserInfo.addValue("HubCitiID", hubCitiId);

			simpleJdbcCall.returningResultSet("rssNewsList", new BeanPropertyRowMapper<RSSFeedMessage>(RSSFeedMessage.class));
			simpleJdbcCall.returningResultSet("dealOfTheDay", new BeanPropertyRowMapper<Deal>(Deal.class));

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(insertUserInfo);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == errorNum) {

				rssfeedMsgList = (List<RSSFeedMessage>) resultFromProcedure.get("rssNewsList");
				dealList = (List<Deal>) resultFromProcedure.get("dealOfTheDay");

				if (null != rssfeedMsgList && !rssfeedMsgList.isEmpty()) {
					objData = new Data();
					strNewsType = new ArrayList<String>();
					rssFeedMap = new HashMap<String, RSSFeedMessage>();

					rssMsgCount = rssfeedMsgList.size();

					if (rssMsgCount == 1) {
						strMessage = "Here is the Top story of the day";
					} else {
						strMessage = "Here are top " + rssMsgCount + "  stories of the day";
					}

					for (RSSFeedMessage msg : rssfeedMsgList) {
						objMsgData = new RSSFeedMessage();

						objMsgData.setTitle(msg.getType());
						objMsgData.setLink(msg.getLink());

						String key = msg.getType();
						if (!rssFeedMap.containsKey(key)) {
							rssFeedMap.put(msg.getType(), objMsgData);
							strNewsType.add(key);
						}
					}

					List<RSSFeedMessage> newsTypeList = new ArrayList<RSSFeedMessage>();
					for (String s : strNewsType) {
						newsTypeList.add(rssFeedMap.get(s));
					}

					for (int i = 0; i < newsTypeList.size(); i++) {
						newsTypeList.get(i).setTitle(newsTypeList.get(i).getTitle());
						newsTypeList.get(i).setLink(newsTypeList.get(i).getLink());
					}
					objData.setRssFeedList(newsTypeList);

					objData.setMessage(strMessage);
					isNewExist = false;
				}

				if (null != dealList && !dealList.isEmpty()) {
					if (isNewExist) {
						strMessage = "Here is your daily deal";
						objData = new Data();
					} else {
						strMessage = strMessage + " and your daily deal";
					}

					objData.setMessage(strMessage);
					objData.setDealList(dealList);
				}

			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : pushNotifyBackGrd :  usp_HcUserPushNotifyBackground " + errorNum + " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : pushNotifyBackGrd : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : pushNotifyBackGrd");
		return objData;
	}

	/**
	 * DAOImpl Method to find pushNotify Back Ground details.
	 * 
	 * @param hubCitiId
	 *            .
	 * @return json containing the List of pushNotify details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public Data webPushNotifyBackGrd(Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : webPushNotifyBackGrd");

		List<Deal> dealList = null;
		Data objData = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_WebHcDealoftheDayPushNotify");

			final MapSqlParameterSource insertUserInfo = new MapSqlParameterSource();
			insertUserInfo.addValue("HcHubCitiID", hubCitiId);

			simpleJdbcCall.returningResultSet("dealOfTheDay", new BeanPropertyRowMapper<Deal>(Deal.class));
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(insertUserInfo);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			if (null == errorNum) {

				dealList = (List<Deal>) resultFromProcedure.get("dealOfTheDay");

				if (null != dealList && !dealList.isEmpty()) {
					objData = new Data();
					objData.setMessage("Here is your daily deal");
					objData.setDealList(dealList);
				}

			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : webPushNotifyBackGrd :  usp_HcUserPushNotifyBackground " + errorNum + " and error message: {}" + errorMsg);
				throw new HubCitiException(errorMsg);
			}

		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : webPushNotifyBackGrd : " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : webPushNotifyBackGrd");
		return objData;
	}

	/**
	 * DAOImpl method is to get HubCiti MenuDisplay information.
	 * 
	 * @param Menu
	 *            type of containing Menu details
	 * @return Menu type of Menu details.
	 */
	public Menu HubMenuDisplay(Menu objMenu) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : HubMenuDisplay");

		List<MenuItem> arMenuItemList = new ArrayList<MenuItem>();
		List<BottomButton> arBottomBtnList = null;
		List<Item> arItemList = null;
		Menu objMenuDisp = new Menu();
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
		String templateName = null;
		String dateModified = null;
		Boolean bNewsTicker = null;

		String strColorActive = null;
		String strColorInActive = null;
		String hamburgerImg = null;

		if ("".equals(Utility.checkNull(objMenu.getSortOrder()))) {
			objMenu.setSortOrder("None");
		}
		if ("".equals(Utility.checkNull(objMenu.getDateCreated()))) {
			objMenu.setDateCreated(null);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiGetMainMenu");
			simpleJdbcCall.returningResultSet("newsDetails", new BeanPropertyRowMapper<Item>(Item.class));
			simpleJdbcCall.returningResultSet("menuDisplayDetails", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource HubMenuDisplayParam = new MapSqlParameterSource();

			HubMenuDisplayParam.addValue("HubCitiID", objMenu.getHubCitiId());
			HubMenuDisplayParam.addValue("LevelID", objMenu.getLevel());
			HubMenuDisplayParam.addValue("UserID", objMenu.getUserId());
			HubMenuDisplayParam.addValue("LinkID", objMenu.getLinkId());
			HubMenuDisplayParam.addValue("SortOrder", objMenu.getSortOrder());
			HubMenuDisplayParam.addValue("DepartmentID", objMenu.getDepartmentId());
			HubMenuDisplayParam.addValue("TypeID", objMenu.getTypeId());

			HubMenuDisplayParam.addValue("DateCheck", objMenu.getDateCreated());
			HubMenuDisplayParam.addValue("DeviceType", objMenu.getDevType());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(HubMenuDisplayParam);
			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (status == 0) {

				arMenuItemList = (List<MenuItem>) resultFromProcedure.get("menuDisplayDetails");

				bTempChanged = (Boolean) resultFromProcedure.get("TemplateChanged");
				if (null != bTempChanged) {
					if (bTempChanged) {
						objMenuDisp.setIsTempChanged(1);
					} else {
						objMenuDisp.setIsTempChanged(0);
					}
				}

				hamburgerImg = (String) resultFromProcedure.get("HamburgerImagePath");
				objMenuDisp.setHamburgerImg(hamburgerImg);

				if (arMenuItemList != null && !arMenuItemList.isEmpty()) {

					strBannerImg = (String) resultFromProcedure.get("HCMenuBannerImage");
					strAppDownLink = (String) resultFromProcedure.get("DownLoadLinkIOS");
					strAndroidLink = (String) resultFromProcedure.get("DownLoadLinkAndroid");
					bDepartFlag = (Boolean) resultFromProcedure.get("HcDepartmentFlag");
					bTypeFlag = (Boolean) resultFromProcedure.get("HcTypeFlag");
					retGroupImg = (String) resultFromProcedure.get("RetailGroupButtonImagePath");
					arBottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
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
					strColorActive = (String) resultFromProcedure.get("PgntdColorActive");
					strColorInActive = (String) resultFromProcedure.get("PgntdColorInActive");

					objMenuDisp.setMenuId(arMenuItemList.get(0).getMenuId());
					objMenuDisp.setHubCitiId(arMenuItemList.get(0).getHubCitiId());
					objMenuDisp.setTemplateName(arMenuItemList.get(0).getTemplateName());
					objMenuDisp.setLevel(arMenuItemList.get(0).getLevel());
					objMenuDisp.setmBkgrdColor(arMenuItemList.get(0).getmBkgrdColor());
					objMenuDisp.setmBkgrdImage(arMenuItemList.get(0).getmBkgrdImage());
					objMenuDisp.setSmBkgrdColor(arMenuItemList.get(0).getSmBkgrdColor());
					objMenuDisp.setSmBkgrdImage(arMenuItemList.get(0).getSmBkgrdImage());
					objMenuDisp.setmFontColor(arMenuItemList.get(0).getmFontColor());
					objMenuDisp.setSmFontColor(arMenuItemList.get(0).getSmFontColor());
					objMenuDisp.setDateModified(arMenuItemList.get(0).getDateModified());
					objMenuDisp.setTemplateBgColor(arMenuItemList.get(0).getTemplateBgColor());

					arItemList = (List<Item>) resultFromProcedure.get("newsDetails");

					homeImgPath = (String) resultFromProcedure.get("homeImgPath");
					bkImgPath = (String) resultFromProcedure.get("bkImgPath");
					titleBkGrdColor = (String) resultFromProcedure.get("titleBkGrdColor");
					titleTxtColor = (String) resultFromProcedure.get("titleTxtColor");

					bNewsTicker = (Boolean) resultFromProcedure.get("IsNewsTicker");

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


					if (bNewsTicker) {
						objMenuDisp.setIsNewTicker(1);
						objMenuDisp.setTickerTxtColor((String) resultFromProcedure.get("NewsTextColor"));
						objMenuDisp.setTickerBkgrdColor((String) resultFromProcedure.get("TickerBckGrdColor"));
						objMenuDisp.setTickerDirection((String) resultFromProcedure.get("NewsDirection"));
						objMenuDisp.setTickerMode((String) resultFromProcedure.get("NewsTickerMode"));
					} else {
						objMenuDisp.setIsNewTicker(0);
					}



					if (!"".equals(Utility.checkNull(menuName))) {
						objMenuDisp.setMenuName(menuName);
					}

					for (int i = 0; i < arMenuItemList.size(); i++) {
						arMenuItemList.get(i).setMenuId(null);
						arMenuItemList.get(i).setHubCitiId(null);
						arMenuItemList.get(i).setTemplateName(null);
						arMenuItemList.get(i).setLevel(null);
						arMenuItemList.get(i).setSmBkgrdImage(null);
						arMenuItemList.get(i).setSmBkgrdColor(null);
						arMenuItemList.get(i).setmBkgrdImage(null);
						arMenuItemList.get(i).setmBkgrdColor(null);
						arMenuItemList.get(i).setmFontColor(null);
						arMenuItemList.get(i).setSmFontColor(null);
						arMenuItemList.get(i).setDateModified(null);
						arMenuItemList.get(i).setTemplateBgColor(null);
					}
					objMenuDisp.setArMItemList(arMenuItemList);
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

					if (arBottomBtnList != null && !arBottomBtnList.isEmpty()) {
						objMenuDisp.setArBottomBtnList(arBottomBtnList);
					}

					if (arItemList != null && !arItemList.isEmpty()) {
						objMenuDisp.setArItemList(arItemList);
					}

					objMenuDisp.setTempBkgrdImg(tempBkGrdImg);
					objMenuDisp.setBtnBkgrdColor(strBtnBkgrdColor);
					objMenuDisp.setBtnLblColor(strBtnLblColor);

					objMenuDisp.setPgnColorActive(strColorActive);
					objMenuDisp.setPgnColorInActive(strColorInActive);

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
					if (objMenuDisp.getIsTempChanged() == 1) {
						templateName = (String) resultFromProcedure.get("TemplateName");
						dateModified = (String) resultFromProcedure.get("ModifiedDate");

						objMenuDisp.setTemplateName(templateName);
						objMenuDisp.setDateModified(dateModified);
					}
					objMenuDisp.setNoRecordMsg(Utility.clobToString((Clob) resultFromProcedure.get("NoRecordsMsg")));
				}
			} else {
				final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : HubMenuDisplay : Store Procedure error : usp_HcHubCitiHubMenuDisplay :  ", objErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : HubMenuDisplay : Store Procedure error : usp_HcHubCitiHubMenuDisplay :  " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : HubMenuDisplay");
		return objMenuDisp;
	}

	/**
	 * DAOImpl method is to display HubRegion main menu details
	 * 
	 * @param instance
	 *            of Menu.
	 * @return List of Menu details.
	 */
	public Menu menuDisplayForHubRegion(Menu objMenu) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : menuDisplayForHubRegion");

		List<MenuItem> arMenuItemList = new ArrayList<MenuItem>();
		List<BottomButton> arBottomBtnList = null;
		List<Item> arItemList = null;
		Menu objMenuDisp = new Menu();
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
		String templateName = null;
		String dateModified = null;
		String strColorActive = null;
		String strColorInActive = null;
		Boolean bNewsTicker = null;
		String hamburgerImg = null;

		if ("".equals(Utility.checkNull(objMenu.getSortOrder()))) {
			objMenu.setSortOrder("None");
		}

		if ("".equals(Utility.checkNull(objMenu.getDateCreated()))) {
			objMenu.setDateCreated(null);
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubRegionGetMainMenu");
			simpleJdbcCall.returningResultSet("newsDetails", new BeanPropertyRowMapper<Item>(Item.class));
			simpleJdbcCall.returningResultSet("menuDisplayForHubRegionDetails", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));
			simpleJdbcCall.returningResultSet("bottomButtonDetails", new BeanPropertyRowMapper<BottomButton>(BottomButton.class));

			final MapSqlParameterSource menuDisplayForHubRegionParam = new MapSqlParameterSource();

			menuDisplayForHubRegionParam.addValue("HubCitiID", objMenu.getHubCitiId());
			menuDisplayForHubRegionParam.addValue("LevelID", objMenu.getLevel());
			menuDisplayForHubRegionParam.addValue("LinkID", objMenu.getLinkId());
			menuDisplayForHubRegionParam.addValue("SortOrder", objMenu.getSortOrder());
			menuDisplayForHubRegionParam.addValue("DepartmentID", objMenu.getDepartmentId());
			menuDisplayForHubRegionParam.addValue("TypeID", objMenu.getTypeId());
			menuDisplayForHubRegionParam.addValue("HcCityID", objMenu.getCityIds());
			menuDisplayForHubRegionParam.addValue("DateCheck", objMenu.getDateCreated());
			menuDisplayForHubRegionParam.addValue("DeviceType", objMenu.getDevType());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(menuDisplayForHubRegionParam);
			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (status == 0) {

				arMenuItemList = (List<MenuItem>) resultFromProcedure.get("menuDisplayForHubRegionDetails");

				bTempChanged = (Boolean) resultFromProcedure.get("TemplateChanged");
				if (null != bTempChanged) {
					if (bTempChanged) {
						objMenuDisp.setIsTempChanged(1);

					} else {
						objMenuDisp.setIsTempChanged(0);
					}
				}
				hamburgerImg = (String) resultFromProcedure.get("HamburgerImagePath");
				objMenuDisp.setHamburgerImg(hamburgerImg);
				if (arMenuItemList != null && !arMenuItemList.isEmpty()) {

					strBannerImg = (String) resultFromProcedure.get("HCMenuBannerImage");
					strAppDownLink = (String) resultFromProcedure.get("DownLoadLinkIOS");
					strAndroidLink = (String) resultFromProcedure.get("DownLoadLinkAndroid");
					bDepartFlag = (Boolean) resultFromProcedure.get("HcDepartmentFlag");
					bTypeFlag = (Boolean) resultFromProcedure.get("HcTypeFlag");
					retGroupImg = (String) resultFromProcedure.get("RetailGroupButtonImagePath");
					arBottomBtnList = (List<BottomButton>) resultFromProcedure.get("bottomButtonDetails");
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
					strColorActive = (String) resultFromProcedure.get("PgntdColorActive");
					strColorInActive = (String) resultFromProcedure.get("PgntdColorInActive");

					homeImgPath = (String) resultFromProcedure.get("homeImgPath");
					bkImgPath = (String) resultFromProcedure.get("bkImgPath");
					titleBkGrdColor = (String) resultFromProcedure.get("titleBkGrdColor");
					titleTxtColor = (String) resultFromProcedure.get("titleTxtColor");
					arItemList = (List<Item>) resultFromProcedure.get("newsDetails");
					bNewsTicker = (Boolean) resultFromProcedure.get("IsNewsTicker");

					objMenuDisp.setMenuId(arMenuItemList.get(0).getMenuId());
					objMenuDisp.setHubCitiId(arMenuItemList.get(0).getHubCitiId());
					objMenuDisp.setTemplateName(arMenuItemList.get(0).getTemplateName());
					objMenuDisp.setLevel(arMenuItemList.get(0).getLevel());
					objMenuDisp.setmBkgrdColor(arMenuItemList.get(0).getmBkgrdColor());
					objMenuDisp.setmBkgrdImage(arMenuItemList.get(0).getmBkgrdImage());
					objMenuDisp.setSmBkgrdColor(arMenuItemList.get(0).getSmBkgrdColor());
					objMenuDisp.setSmBkgrdImage(arMenuItemList.get(0).getSmBkgrdImage());
					objMenuDisp.setmFontColor(arMenuItemList.get(0).getmFontColor());
					objMenuDisp.setSmFontColor(arMenuItemList.get(0).getSmFontColor());
					objMenuDisp.setDateModified(arMenuItemList.get(0).getDateModified());
					objMenuDisp.setTemplateBgColor(arMenuItemList.get(0).getTemplateBgColor());

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

					if (bNewsTicker) {
						objMenuDisp.setIsNewTicker(1);
						objMenuDisp.setTickerTxtColor((String) resultFromProcedure.get("NewsTextColor"));
						objMenuDisp.setTickerBkgrdColor((String) resultFromProcedure.get("TickerBckGrdColor"));
						objMenuDisp.setTickerDirection((String) resultFromProcedure.get("NewsDirection"));
						objMenuDisp.setTickerMode((String) resultFromProcedure.get("NewsTickerMode"));
					} else {
						objMenuDisp.setIsNewTicker(0);
					}

					if (!"".equals(Utility.checkNull(menuName))) {
						objMenuDisp.setMenuName(menuName);
					}

					for (int i = 0; i < arMenuItemList.size(); i++) {
						arMenuItemList.get(i).setMenuId(null);
						arMenuItemList.get(i).setHubCitiId(null);
						arMenuItemList.get(i).setTemplateName(null);
						arMenuItemList.get(i).setLevel(null);
						arMenuItemList.get(i).setSmBkgrdImage(null);
						arMenuItemList.get(i).setSmBkgrdColor(null);
						arMenuItemList.get(i).setmBkgrdImage(null);
						arMenuItemList.get(i).setmBkgrdColor(null);
						arMenuItemList.get(i).setmFontColor(null);
						arMenuItemList.get(i).setSmFontColor(null);
						arMenuItemList.get(i).setDateModified(null);
						arMenuItemList.get(i).setTemplateBgColor(null);
					}
					objMenuDisp.setArMItemList(arMenuItemList);
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

					if (arBottomBtnList != null && !arBottomBtnList.isEmpty()) {
						objMenuDisp.setArBottomBtnList(arBottomBtnList);
					}

					if (arItemList != null && !arItemList.isEmpty()) {
						objMenuDisp.setArItemList(arItemList);
					}

					objMenuDisp.setTempBkgrdImg(tempBkGrdImg);

					objMenuDisp.setBtnBkgrdColor(strBtnBkgrdColor);
					objMenuDisp.setBtnLblColor(strBtnLblColor);

					objMenuDisp.setPgnColorActive(strColorActive);
					objMenuDisp.setPgnColorInActive(strColorInActive);

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
					if (objMenuDisp.getIsTempChanged() == 1) {
						templateName = (String) resultFromProcedure.get("TemplateName");
						dateModified = (String) resultFromProcedure.get("ModifiedDate");

						objMenuDisp.setTemplateName(templateName);
						objMenuDisp.setDateModified(dateModified);
					}
					objMenuDisp.setNoRecordMsg(Utility.clobToString((Clob) resultFromProcedure.get("NoRecordsMsg")));

				}
			} else {
				final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : menuDisplayForHubRegion : Store Procedure error : usp_HcHubCitimenuDisplayForHubRegion :  ", objErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : menuDisplayForHubRegion : Store Procedure error : usp_HcHubCitimenuDisplayForHubRegion :  " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : menuDisplayForHubRegion");
		return objMenuDisp;
	}

	/**
	 * DAOImpl method is to update device details.
	 * 
	 * @param instance
	 *            of Menu.
	 * @return String as success.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String updateDeviceDetailsForMenu(Menu objMenu) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : updateDeviceDetailsForMenu");

		Integer status = null;
		String response = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiUpdateDeviceDetailsMenuDisplay");

			final MapSqlParameterSource menuDisplayParam = new MapSqlParameterSource();

			menuDisplayParam.addValue("HubCitiID", objMenu.getHubCitiId());
			menuDisplayParam.addValue("HcUserID", objMenu.getUserId());
			menuDisplayParam.addValue("RequestPlatformtype", objMenu.getPlatform());
			menuDisplayParam.addValue("DeviceID", objMenu.getDeviceId());
			menuDisplayParam.addValue("PlatformOSVersion", objMenu.getOsVersion());
			menuDisplayParam.addValue("AppVersion", objMenu.getAppVersion());
			menuDisplayParam.addValue("PrimaryDevice", objMenu.getPrimaryDevice());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(menuDisplayParam);
			status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);

			if (null != status && status == 0) {
				response = ApplicationConstants.SUCCESS;
			} else {
				final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : updateDeviceDetailsForMenu : Store Procedure error : usp_HcHubCitiUpdateDeviceDetailsMenuDisplay :  ", objErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : updateDeviceDetailsForMenu : Store Procedure error : usp_HcHubCitiUpdateDeviceDetailsMenuDisplay :  " + e.getMessage());
			throw new HubCitiException(e);
		}

		LOG.info("Exit FirstUseDaoImpl : updateDeviceDetailsForMenu");
		return response;
	}

	/**
	 * Web Services for reseting badge value for IOS Push Nptification based on
	 * the DeviceId and HubCitiId
	 * 
	 * @param hubCitiId
	 * @param deviceId
	 * @param userId
	 * @return if the status is 0 then success else unable to reset badge value.
	 */
	public String resetBadge(Integer hubCitiId, String deviceId, Integer userId) throws HubCitiException {

		LOG.info("Inside FirstUseDaoImpl : resetBadge");
		Integer fromProc;
		Map<String, Object> resultFromProcedure = null;
		String response = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			// SP name should be add
			simpleJdbcCall.withProcedureName("usp_PushNotifyBadgeReset");
			final MapSqlParameterSource resetBadge = new MapSqlParameterSource();

			// input params should be add
			resetBadge.addValue("HcHubCitiID", hubCitiId);
			resetBadge.addValue("DeviceID", deviceId);
			resetBadge.addValue("UserID", userId);

			resultFromProcedure = simpleJdbcCall.execute(resetBadge);
			fromProc = (Integer) resultFromProcedure.get("Status");
			// if fromProc is zero means successfully value reset done.
			if (fromProc == 0) {
				// Message name should be get it from database
				response = HubCitiConstants.SUCCESSTEXT;
			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside FirstUseDaoImpl : ResetBadge : ", errorNum, strErrorMsg);
				response = "Unable to reset badge value.";
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : resetBadge : " + e.getMessage());
			throw new HubCitiException(e);
		}
		return response;
	}

	/**
	 * DAOImpl method fetches user details for sending password to user email
	 * id.
	 * 
	 * @param strUserName
	 *            request parameter
	 * @return User Details
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public AuthenticateUser forgotPassword_v2(String strUserName, Integer hubCitiId, String encryptedpassword) throws HubCitiException {

		LOG.info("Inside FirstUseDaoImpl : forgotPassword_v2");

		AuthenticateUser objAuthentUser = new AuthenticateUser();
		Integer iStatus = 0;
		String responseMessage = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiUserForgotPassword_version2");
			simpleJdbcCall.returningResultSet("getUszerDetails", new BeanPropertyRowMapper<AuthenticateUser>(AuthenticateUser.class));
			final MapSqlParameterSource authentUserParam = new MapSqlParameterSource();
			authentUserParam.addValue("HcUserName", strUserName);
			authentUserParam.addValue("HubCitiID", hubCitiId);
			authentUserParam.addValue("TPassword", encryptedpassword);

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(authentUserParam);
			iStatus = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			responseMessage = (String) resultFromProcedure.get("message");

			if (iStatus == 0) {

				strUserName = (String) resultFromProcedure.get("UserName");
				String email = (String) resultFromProcedure.get("Email");
				if (null != email) {

					objAuthentUser.setEmail(email);
					objAuthentUser.setUserName(strUserName);
					objAuthentUser.setPassword(encryptedpassword);
					objAuthentUser.setSuccess(1);
					objAuthentUser.setMessage(responseMessage);
				} else {
					objAuthentUser.setSuccess(0);
					objAuthentUser.setMessage(responseMessage);
				}

			} else {
				final Integer objErrorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Inside FirstUseDaoImpl : forgotPassword_v2 : Store Procedure error : usp_HcHubCitiUserForgotPassword:  ", objErrorNum, strErrorMsg);
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : forgotPassword_v2 : " + e.getMessage());
			throw new HubCitiException(e);
		}
		LOG.info("Exit FirstUseDaoImpl : forgotPassword_v2");
		return objAuthentUser;
	}

	public UserDetails getUserLogin_V2(UserDetails userDetailsReq) throws HubCitiException {

		LOG.info("Inside FirstUseDaoImpl : getUserLogin_V2");

		UserDetails objUserDetails = null;
		EncryptDecryptPwd objEncryptDecryptPwd;
		String encryptedPwd = null;
		List<City> cityList = null;

		try {
			objEncryptDecryptPwd = new EncryptDecryptPwd();
			encryptedPwd = objEncryptDecryptPwd.encrypt(userDetailsReq.getPassword());

		} catch (NoSuchAlgorithmException e) {
		} catch (NoSuchPaddingException e) {
			LOG.error("Exception in Encryption of user password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("user password Encryption Exception    ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Exception while Encryption  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Exception during Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Exception in user password Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Exception in Encryption ", e);
			throw new HubCitiException(e.getMessage());
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);

			simpleJdbcCall.withProcedureName("usp_HcUserLogin_Version2");
			simpleJdbcCall.returningResultSet("cityList", new BeanPropertyRowMapper<City>(City.class));
			final MapSqlParameterSource userRegParam = new MapSqlParameterSource();
			userRegParam.addValue("UserName", userDetailsReq.getUserName());
			userRegParam.addValue("Password", encryptedPwd);
			userRegParam.addValue("AppVersion", userDetailsReq.getAppVersion());
			userRegParam.addValue("DeviceID", userDetailsReq.getDeviceId());
			userRegParam.addValue("HubCitiKey", userDetailsReq.getHubCitiKey());
			userRegParam.addValue("Latitude", userDetailsReq.getLatitude());
			userRegParam.addValue("Longitude", userDetailsReq.getLongitude());
			userRegParam.addValue("PostalCode", userDetailsReq.getPostalCode());
			userRegParam.addValue("RequestPlatformType", userDetailsReq.getPlatform());

			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(userRegParam);

			Integer status = (Integer) resultFromProcedure.get(HubCitiConstants.STATUS);
			if (status == 0) {
				Integer userId = (Integer) resultFromProcedure.get(HubCitiConstants.USERID);
				Integer loginFlag = (Integer) resultFromProcedure.get("Login");
				Boolean isHCActive = (Boolean) resultFromProcedure.get("HubCitiDeactivated");
				Integer isInvalidPasswd = (Integer) resultFromProcedure.get("InvalidPassword");
				Integer isPrefSet = (Integer) resultFromProcedure.get("Result");
				Boolean addDevToUser = (Boolean) resultFromProcedure.get("AddDeviceToUser");
				String hubCitiName = (String) resultFromProcedure.get("HubCitiName");
				Integer hubCitiId = (Integer) resultFromProcedure.get("HubCitiID");
				Boolean isTempUser = (Boolean) resultFromProcedure.get("IsTempUser");
				Boolean noEmailCount = (Boolean) resultFromProcedure.get("NoEmailCount");
				String postalCode = (String) resultFromProcedure.get("PostalCode");

				// Boolean userRangeFlag = (Boolean)
				// resultFromProcedure.get("UserOutOfRange");
				String defaultPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");
				cityList = (List<City>) resultFromProcedure.get("cityList");
				objUserDetails = new UserDetails();
				objUserDetails.setUserId(userId);
				objUserDetails.setHubCitiName(hubCitiName);
				objUserDetails.setHubCitiId(hubCitiId);
				objUserDetails.setCityList(cityList);
				objUserDetails.setIsPrefSet(isPrefSet);
				objUserDetails.setPostalCode(postalCode);

				if (isTempUser) {
					objUserDetails.setIsTempUser(1);
				} else {
					objUserDetails.setIsTempUser(0);
				}

				if (noEmailCount) {
					objUserDetails.setNoEmailCount(1);
				} else {
					objUserDetails.setNoEmailCount(0);
				}
				// objUserDetails.setNoEmailCount(noEmailCount);

				if (loginFlag != null && loginFlag == -2) {
					objUserDetails.setLoginFlag(-2);
				} else if (loginFlag != null && loginFlag != -1) {
					objUserDetails.setLoginFlag(1);
				} else {
					objUserDetails.setLoginFlag(0);
				}

				if (isInvalidPasswd == 1) {
					objUserDetails.setPassword("0");
				} else {
					objUserDetails.setPassword("1");
				}

				if (isHCActive) {
					objUserDetails.setIsHCActive(0);
				} else {
					objUserDetails.setIsHCActive(1);
				}

				if (addDevToUser) {
					objUserDetails.setAddDevToUser(1);
				} else {
					objUserDetails.setAddDevToUser(0);
				}

				/*
				 * if(userRangeFlag) { objUserDetails.setIsUserOutOfCiti(1); }
				 * else { objUserDetails.setIsUserOutOfCiti(0); }
				 */

				if (defaultPostalCode != null) {
					objUserDetails.setDefPostalCode(defaultPostalCode);
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcGetUserLogin Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : getUserLogin_v2 : ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : getUserLogin_v2 : ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl : getUserLogin_V2");
		return objUserDetails;
	}

	public UserDetails userRegistration_V2(UserDetails userRegDetails) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : userRegistration_v2");

		UserDetails objUserDetails = null;
		EncryptDecryptPwd objEncryptDecryptPwd;
		String encryptedPwd = null;

		try {
			objEncryptDecryptPwd = new EncryptDecryptPwd();
			encryptedPwd = objEncryptDecryptPwd.encrypt(userRegDetails.getPassword());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Exception in Encryption of password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Exception in Encryption of user password..", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("user password Encryption Exception    ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Exception while Encryption  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Exception during Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Exception in user password Encryption ", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Exception in Encryption ", e);
			throw new HubCitiException(e.getMessage());
		}

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserRegistration_Version2");
			final MapSqlParameterSource userRegistrationParameter = new MapSqlParameterSource();
			userRegistrationParameter.addValue("UserName", userRegDetails.getUserName());
			userRegistrationParameter.addValue("Password", encryptedPwd);
			userRegistrationParameter.addValue("Email", userRegDetails.getEmail());
			userRegistrationParameter.addValue("HubCitiKey", userRegDetails.getHubCitiKey());
			userRegistrationParameter.addValue("AppVersion", userRegDetails.getAppVersion());
			userRegistrationParameter.addValue("DeviceID", userRegDetails.getDeviceId());
			userRegistrationParameter.addValue("UserLongitude", userRegDetails.getLatitude());
			userRegistrationParameter.addValue("UserLatitude", userRegDetails.getLongitude());
			userRegistrationParameter.addValue("PostalCode", userRegDetails.getPostalCode());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(userRegistrationParameter);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
			Integer userExist = (Integer) resultFromProcedure.get("Result");

			if (errorNum == null) {
				if (userExist != null && userExist < 0) {
					objUserDetails = new UserDetails();
					objUserDetails.setLoginFlag(userExist);
				} else {
					Integer userId = (Integer) resultFromProcedure.get("FirstUserID");
					Integer hubCitiId = (Integer) resultFromProcedure.get("HubCitiID");
					String hubCitiName = (String) resultFromProcedure.get("HubCitiName");
					String defaultPostalCode = (String) resultFromProcedure.get("DefaultPostalCode");
					objUserDetails = new UserDetails();
					objUserDetails.setUserId(userId);
					objUserDetails.setHubCitiId(hubCitiId);
					objUserDetails.setHubCitiName(hubCitiName);
					if (defaultPostalCode != null) {
						objUserDetails.setDefPostalCode(defaultPostalCode);
					}
				}
			} else {
				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserRegistration Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Inside FirstUseDaoImpl : userRegistration_v2 : ", exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Inside FirstUseDaoImpl : userRegistration_v2: ", exception);
			throw new HubCitiException(exception);
		}

		LOG.info("Exit FirstUseDaoImpl : userRegistration_v2");
		return objUserDetails;
	}

	public String resetPassword(Integer userID, String password, String hubcitiKey) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : resetPassword");
		Integer fromProc;
		Map<String, Object> resultFromProcedure = null;
		String strResponse = null;
		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcChangePassword_version2");
			final MapSqlParameterSource changePwdParam = new MapSqlParameterSource();
			changePwdParam.addValue("userid", userID);
			changePwdParam.addValue("HubcitiKey", hubcitiKey);
			changePwdParam.addValue("password", password);
			resultFromProcedure = simpleJdbcCall.execute(changePwdParam);
			fromProc = (Integer) resultFromProcedure.get("Status");
			if (fromProc == 0) {
				Boolean isSucess = (Boolean) resultFromProcedure.get("IsSuccess");
				if( null != isSucess  && !isSucess){
					strResponse = HubCitiConstants.SUCCESS;
				}else{
					strResponse = "Unable to update Password.";
				}

			} else {
				final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);
				final String strErrorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.info("Inside FirstUseDaoImpl : resetPassword : Store Procedure error : usp_HcChangePassword : ", errorNum, strErrorMsg);
				strResponse = HubCitiConstants.FAILURE;
				throw new HubCitiException(strErrorMsg);
			}
		} catch (DataAccessException e) {
			LOG.error("Inside FirstUseDaoImpl : resetPassword : " + e.getMessage());
			throw new HubCitiException(e);
		}
		LOG.info("Inside FirstUseDaoImpl : resetPassword");
		return strResponse;
	}

	/**
	 * VERSION 2.0
	 * 
	 * DAO method to update user information.
	 * 
	 * @param objUserDetailsReq
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 */
	public String updateUserInfo_V2(UserDetails objUserDetailsReq) throws HubCitiException {
		final String methodName = " updateUserInfo_V2 : FirstUseDaoImpl";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String response = null;
		Boolean emailPresent=false;
		// UserDetails objUserDetailsResponse = null;

		try {
			simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcUserInfo_version2");
			final MapSqlParameterSource updateUserInfo = new MapSqlParameterSource();
			updateUserInfo.addValue(HubCitiConstants.USERID, objUserDetailsReq.getUserId());

			if (objUserDetailsReq.getFirstName() != null && !"".equals(objUserDetailsReq.getFirstName().trim())) {
				updateUserInfo.addValue("FirstName", objUserDetailsReq.getFirstName());
			} else {
				updateUserInfo.addValue("FirstName", null);
			}

			if (objUserDetailsReq.getLastName() != null && !"".equals(objUserDetailsReq.getLastName().trim())) {
				updateUserInfo.addValue("Lastname", objUserDetailsReq.getLastName());
			} else {
				updateUserInfo.addValue("Lastname", null);
			}

			if (objUserDetailsReq.getPostalCode() != null && !"".equals(objUserDetailsReq.getPostalCode().trim())) {
				updateUserInfo.addValue("PostalCode", objUserDetailsReq.getPostalCode());
			} else {
				updateUserInfo.addValue("PostalCode", null);
			}

			if (objUserDetailsReq.getGender() != null && !"".equals(objUserDetailsReq.getGender().trim())) {
				updateUserInfo.addValue("Gender", objUserDetailsReq.getGender());
			} else {
				updateUserInfo.addValue("Gender", null);
			}
			if (!"".equals(Utility.checkNull(objUserDetailsReq.getEmail()))) {
				updateUserInfo.addValue("Email", objUserDetailsReq.getEmail());
			} else {
				updateUserInfo.addValue("Email", null);
			}

			updateUserInfo.addValue("DOB", objUserDetailsReq.getDob());
			updateUserInfo.addValue("MobilePhone", objUserDetailsReq.getPhoneNum());
			updateUserInfo.addValue("DeviceID", objUserDetailsReq.getDeviceId());
			updateUserInfo.addValue("UniversityIDs", objUserDetailsReq.getUnivId());
			updateUserInfo.addValue("IncomeRangeID", objUserDetailsReq.getIncomeRangeId());
			updateUserInfo.addValue("MaritalStatusID", objUserDetailsReq.getMartialStatusId());
			updateUserInfo.addValue("EducationLevelID", objUserDetailsReq.getEducatonLevelId());
			updateUserInfo.addValue("Latitude", objUserDetailsReq.getLatitude());
			updateUserInfo.addValue("Longitude", objUserDetailsReq.getLongitude());
			updateUserInfo.addValue("HubCitiID", objUserDetailsReq.getHubCitiId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(updateUserInfo);

			final Integer errorNum = (Integer) resultFromProcedure.get(HubCitiConstants.ERRORNUMBER);

			if (errorNum == null) {

				emailPresent=(Boolean)resultFromProcedure.get("ExistsEmail");
				if( null == emailPresent){
					response = HubCitiConstants.FAILURECODE;
				}else if(emailPresent){
					response = HubCitiConstants.FAILURECODE;
				}else{
					response = HubCitiConstants.SUCCESSTEXT;
				}
			}else {

				final String errorMsg = (String) resultFromProcedure.get(HubCitiConstants.ERRORMESSAGE);
				LOG.error("Error occurred in usp_HcUserInfo Store Procedure error number: {} and error message: {}", errorNum, errorMsg);
				throw new HubCitiException(errorMsg);
			}
		} catch (DataAccessException exception) {
			LOG.error("Exception occurred in  " + methodName + " ", HubCitiConstants.DATAACCESSEXCEPTIONCODE, exception);
			throw new HubCitiException(exception);
		} catch (Exception exception) {
			LOG.error("Exception occurred in  " + methodName + " ", exception);
			throw new HubCitiException(exception);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return response;
	}



	public TutorialMediaResultSet getTutorialImages(String hubCitikey) throws HubCitiException{
		LOG.info("Inside FirstUseDaoImpl : getTutorialImages");

		List<TutorialMediaResultSet>  tutorialMediaResultSets=null;
		TutorialMediaResultSet tutorialMediaResultSet=null;
		//List<String> slideImageList=null;

		Integer status;
		String logoPath=null;


		try{
			simpleJdbcCall=new SimpleJdbcCall(jdbcTemplate);
			simpleJdbcCall.setSchemaName(HubCitiConstants.SCHEMANAME);
			simpleJdbcCall.withProcedureName("usp_HcHubCitiFirstUseImagesList");
			simpleJdbcCall.returningResultSet("tutorialImagesInfo", new BeanPropertyRowMapper<TutorialMediaResultSet>(TutorialMediaResultSet.class));
			final MapSqlParameterSource tutorialImagesinfo= new MapSqlParameterSource();
			tutorialImagesinfo.addValue("HcHubCitiKey",hubCitikey);

			final Map<String,Object> resultFromProcedure=simpleJdbcCall.execute(tutorialImagesinfo);

			status=(Integer)resultFromProcedure.get("Status");
			logoPath=(String)resultFromProcedure.get("LogoPath");

			tutorialMediaResultSets = (List<TutorialMediaResultSet>) resultFromProcedure.get("tutorialImagesInfo");
			if(status==0){

				tutorialMediaResultSet =new TutorialMediaResultSet();
				tutorialMediaResultSet.setLogoPath(logoPath);				
				tutorialMediaResultSet.setResponseCode(HubCitiConstants.SUCCESSCODE);
				tutorialMediaResultSet.setResponseText(HubCitiConstants.SUCCESSTEXT);

				if(null==tutorialMediaResultSets ||tutorialMediaResultSets.isEmpty()||tutorialMediaResultSets.get(0).getLink()==null){
					tutorialMediaResultSet.setTutorialMediaResultSets(null);
				}else{
					tutorialMediaResultSet.setTutorialMediaResultSets(tutorialMediaResultSets);
				}
			}

		} catch(DataAccessException dae){
			LOG.info("Inside FirstUseDaoImpl : getTutorialImages : " +dae);
			throw new HubCitiException(dae);
		}

		LOG.info("Exit FirstUseDaoImpl : getTutorialImages");

		return tutorialMediaResultSet;
	}

	/**
	 * DAOImpl method is to display HubCiti side Menu Display information.
	 * 
	 * @param Menu
	 *            type of containing Menu details
	 * @return CategoryDetails type of CategoryDetails details.
	 */
	public CategoryDetails hubSideMenuDisplay(Menu objMenu) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : hubSideMenuDisplay");

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


			if (null == objMenu.getSideNaviPersonalizatn() || !objMenu.getSideNaviPersonalizatn()) {
				simpleJdbcCall.withProcedureName("usp_HcHubcitiSideNavigationDisplay");
			} else {
				simpleJdbcCall.withProcedureName("usp_NewsFirstSingleSideNavigationDisplay");
			}
			simpleJdbcCall.returningResultSet("menuDisplayDetails", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));
			simpleJdbcCall.returningResultSet("getNewsCatagories", new BeanPropertyRowMapper<MainCategory>(MainCategory.class));
			final MapSqlParameterSource hubSideMenuDisplayParam = new MapSqlParameterSource();

			hubSideMenuDisplayParam.addValue("HubCitiID", objMenu.getHubCitiId());
			hubSideMenuDisplayParam.addValue("LevelID", objMenu.getLevel());
			hubSideMenuDisplayParam.addValue("LinkID", objMenu.getLinkId());
			hubSideMenuDisplayParam.addValue("UserID", objMenu.getUserId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(hubSideMenuDisplayParam);

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
	 * DAOImpl method is to display HubCiti side Menu Display information.
	 * 
	 * @param Menu
	 *            type of containing Menu details
	 * @return CategoryDetails type of CategoryDetails details.
	 */
	public CategoryDetails hubSideMenuDisplay_V2(Menu objMenu) throws HubCitiException {
		LOG.info("Inside FirstUseDaoImpl : hubSideMenuDisplay_V2");

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


			if (null == objMenu.getSideNaviPersonalizatn() || !objMenu.getSideNaviPersonalizatn()) {
				simpleJdbcCall.withProcedureName("usp_HcHubcitiSideNavigationDisplay_V2");
			} else {
				simpleJdbcCall.withProcedureName("usp_NewsFirstSingleSideNavigationDisplay_V2");
			}
			simpleJdbcCall.returningResultSet("menuDisplayDetails", new BeanPropertyRowMapper<MenuItem>(MenuItem.class));
			simpleJdbcCall.returningResultSet("getNewsCatagories", new BeanPropertyRowMapper<MainCategory>(MainCategory.class));
			final MapSqlParameterSource hubSideMenuDisplayParam = new MapSqlParameterSource();

			hubSideMenuDisplayParam.addValue("HubCitiID", objMenu.getHubCitiId());
			hubSideMenuDisplayParam.addValue("LevelID", objMenu.getLevel());
			hubSideMenuDisplayParam.addValue("LinkID", objMenu.getLinkId());
			hubSideMenuDisplayParam.addValue("UserID", objMenu.getUserId());
			final Map<String, Object> resultFromProcedure = simpleJdbcCall.execute(hubSideMenuDisplayParam);

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
}
