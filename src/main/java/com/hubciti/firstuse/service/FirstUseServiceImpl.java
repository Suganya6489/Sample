package com.hubciti.firstuse.service;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hubciti.band.service.BandHelper;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.email.EmailComponent;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.AuthenticateUser;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.City;
import com.hubciti.common.pojos.CountryCode;
import com.hubciti.common.pojos.Data;
import com.hubciti.common.pojos.Deal;
import com.hubciti.common.pojos.FAQDetails;
import com.hubciti.common.pojos.LoginFlowDetails;
import com.hubciti.common.pojos.MainCategory;
import com.hubciti.common.pojos.Menu;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.RSSFeedMessage;
import com.hubciti.common.pojos.SubCategory;
import com.hubciti.common.pojos.TutorialMediaResultSet;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.pojos.UserRegistrationInfo;
import com.hubciti.common.pojos.UserSettings;
import com.hubciti.common.pojos.UserTrackingData;
import com.hubciti.common.utility.EncryptDecryptPwd;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.dao.FirstUseDao;
import com.scansee.externalapi.common.constants.ApplicationConstants;

public class FirstUseServiceImpl implements FirstUseService {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FirstUseServiceImpl.class);

	/**
	 * Instance variable for FirstUseDAO.
	 */
	public FirstUseDao firstUseDao;

	/**
	 * Setter method for FirstUseDAO.
	 * 
	 * @param firstUseDao
	 *            Instance of type FirstUseDAO.
	 */
	public void setFirstUseDao(FirstUseDao firstUseDao) {
		this.firstUseDao = firstUseDao;
	}

	/**
	 * Method for user login. Calls the XStreams helper class methods for
	 * parsing xml. Method uses Xstream parser for XML handling.
	 * 
	 * @param xml
	 *            containing login credentials.
	 * @return String xml containing user details.
	 * @throws HubCitiException.
	 */
	public String userLogin(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : userLogin");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (objUserDetails.getUserName() == null || objUserDetails.getUserName().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "User Name Required");
		} else if (objUserDetails.getPassword() == null || objUserDetails.getPassword().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Password Required");
		} else if (objUserDetails.getHubCitiKey() == null || "".equals(objUserDetails.getHubCitiKey())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "HubCiti Key required");
		} else if (objUserDetails.getDeviceId() == null || objUserDetails.getDeviceId().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Device ID Required");
		} else if ("".equals(Utility.checkNull(objUserDetails.getPlatform()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_EMPTY);
		} else {

			UserDetails userDetails = new UserDetails();
			userDetails = firstUseDao.userLogin(objUserDetails);

			if (userDetails != null) {

				if (userDetails.getLoginFlag() == 0 && userDetails.getPassword().equals("1")) {
					response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "User Name Not Found");
				} else if (userDetails.getPassword().equals("0")) {
					response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "Invalid Password");
				} else {

					userDetails.setPassword(null);
					userDetails.setLoginFlag(null);
					userDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					userDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(userDetails);
				}

			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : userLogin");
		return response;
	}

	/**
	 * Method for user login. Calls the XStreams helper class methods for
	 * parsing xml. Method uses Xstream parser for XML handling.
	 * 
	 * @param xml
	 *            containing login credentials.
	 * @return String xml containing user details.
	 * @throws HubCitiException.
	 */
	public String getUserLogin(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getUserLogin");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (objUserDetails.getUserName() == null || objUserDetails.getUserName().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "User Name Required");
		} else if (objUserDetails.getPassword() == null || objUserDetails.getPassword().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Password Required");
		} else if (objUserDetails.getHubCitiKey() == null || "".equals(objUserDetails.getHubCitiKey())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "HubCiti Key required");
		} else if (objUserDetails.getDeviceId() == null || objUserDetails.getDeviceId().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Device ID Required");
		} else if ("".equals(Utility.checkNull(objUserDetails.getPlatform()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_EMPTY);
		} else {

			UserDetails userDetails = new UserDetails();
			userDetails = firstUseDao.getUserLogin(objUserDetails);

			if (userDetails != null) {

				if (userDetails.getLoginFlag() == 0 && userDetails.getPassword().equals("1")) {
					response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "User Name Not Found");
				} else if (userDetails.getPassword().equals("0")) {
					response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "Invalid Password");
				} else {

					userDetails.setPassword(null);
					userDetails.setLoginFlag(null);
					userDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					userDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(userDetails);
				}

			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getUserLogin");
		return response;
	}

	/**
	 * Method for User Sign Up. Calls the XStreams helper class methods for
	 * parsing XML.
	 * 
	 * @param xml
	 *            contining user credentials.
	 * @return String xml containing userId.
	 * @throws HubCitiException.
	 */
	public String userRegistration(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : userRegistration");

		String response = null;
		List<LoginFlowDetails> listLoginFlow = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (objUserDetails.getUserName() == null || objUserDetails.getUserName().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "User Name Required");
		} else if (objUserDetails.getPassword() == null || objUserDetails.getPassword().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Password Required");
		} else if (objUserDetails.getDeviceId() == null || objUserDetails.getDeviceId().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Device ID Required");
		} else if (objUserDetails.getAppVersion() == null || objUserDetails.getAppVersion().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "App Version Required");
		} else if (objUserDetails.getHubCitiKey() == null || objUserDetails.getHubCitiKey().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "HubCiti Key Required");
		} else {
			UserDetails userRegResponse = new UserDetails();
			userRegResponse = firstUseDao.userRegistration(objUserDetails);

			if (userRegResponse != null) {
				if (userRegResponse.getLoginFlag() != null && userRegResponse.getLoginFlag() < 0) {
					if (userRegResponse.getLoginFlag() == -1) {
						response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "User Name Exists Already");
					} else if (userRegResponse.getLoginFlag() == -2) {
						response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "Invalid HubCiti Key");
					} else {
						response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
					}
				} else {
					LoginFlowDetails objLoginFlow = new LoginFlowDetails();
					objLoginFlow.setPageType("Registration Page");
					objLoginFlow.setHubCitiKey(objUserDetails.getHubCitiKey());
					listLoginFlow = firstUseDao.userLoginFlow(objLoginFlow);

					if (listLoginFlow != null && !listLoginFlow.isEmpty() && listLoginFlow.get(0) != null) {
						userRegResponse.setRegSuccessUI(listLoginFlow.get(0));
					}

					userRegResponse.setResponseCode(HubCitiConstants.SUCCESSCODE);
					userRegResponse.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(userRegResponse);
				}
			} else {
				response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : userRegistration");
		return response;
	}

	/**
	 * Method to get Configuration in user login/signUp flow.
	 * 
	 * @param xml
	 * @return String xml.
	 * @throws HubCitiException.
	 */
	public String userLoginFlow(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : userLoginFlow");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final LoginFlowDetails objLoginFlowDetails = (LoginFlowDetails) streamHelper.parseXmlToObject(xml);

		if (objLoginFlowDetails.getPageType() == null || objLoginFlowDetails.getPageType().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (objLoginFlowDetails.getHubCitiKey() == null || objLoginFlowDetails.getHubCitiKey().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			List<LoginFlowDetails> objLoginFlowList = new ArrayList<LoginFlowDetails>();
			objLoginFlowList = firstUseDao.userLoginFlow(objLoginFlowDetails);

			if (objLoginFlowList != null && !objLoginFlowList.isEmpty() && objLoginFlowList.get(0).getMaxCnt() == 1) {
				objLoginFlowList.get(0).setResponseCode(HubCitiConstants.SUCCESSCODE);
				objLoginFlowList.get(0).setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objLoginFlowList);
				response = response.replaceAll("<list>", "");
				response = response.replaceAll("</list>", "");
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT, "hamburgerImg", objLoginFlowList.get(0).getHamburgerImg());
			}
		}

		LOG.info("Exit FirstUseServiceImpl : userLoginFlow");
		return response;
	}

	/**
	 * Method to get user prefered categories.
	 * 
	 * @param xml
	 * @return string xml.
	 * @throws HubCitiException
	 */
	public String getUserPreferedCategories(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getUserPreferedCategories");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (objUserDetails.getUserId() == null || objUserDetails.getUserId().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERIDNOTFOUND);
		} else {
			CategoryDetails objCategoryDetails = new CategoryDetails();
			objCategoryDetails = firstUseDao.getUserPreferedCategories(objUserDetails);

			/*
			 * For sorting category and its sub category
			 */
			if (objCategoryDetails != null && objCategoryDetails.getListCatDetails() != null && !objCategoryDetails.getListCatDetails().isEmpty()) {
				CategoryDetails objCatDetails = null;
				List<SubCategory> subCatList = null;
				List<MainCategory> mainCatList = new ArrayList<MainCategory>();
				MainCategory objMainCat = null;
				SubCategory objSubCat = null;
				/*int prevPatCatId = 0;
				int currentParCatId = 0;*/
				Integer prevPatCatId = 0;
				Integer currentParCatId = 0;
				int sum = 0;
				for (CategoryDetails catDetails : objCategoryDetails.getListCatDetails()) {
					++sum;
					currentParCatId = catDetails.getParCatId();
					if (prevPatCatId == 0) {					
						prevPatCatId = catDetails.getParCatId();
						objMainCat = new MainCategory();
						objMainCat.setParCatId(catDetails.getParCatId());
						objMainCat.setParCatName(catDetails.getParCatName());
						subCatList = new ArrayList<SubCategory>();
					}

					/*if (currentParCatId == prevPatCatId) {
					 */					if (currentParCatId.equals(prevPatCatId)) {
						 objSubCat = new SubCategory();
						 objSubCat.setSubCatId(catDetails.getSubCatId());
						 objSubCat.setSubCatName(catDetails.getSubCatName());
						 objSubCat.setCatId(catDetails.getCatId());
						 objSubCat.setIsAdded(catDetails.getIsAdded());
						 subCatList.add(objSubCat);
						 if (sum == objCategoryDetails.getListCatDetails().size() - 1) {
							 objMainCat.setListSubCat(subCatList);
							 mainCatList.add(objMainCat);
						 }
					 } else {
						 objMainCat.setListSubCat(subCatList);
						 mainCatList.add(objMainCat);
						 prevPatCatId = catDetails.getParCatId();
						 objMainCat = new MainCategory();
						 objMainCat.setParCatId(catDetails.getParCatId());
						 objMainCat.setParCatName(catDetails.getParCatName());
						 subCatList = new ArrayList<SubCategory>();

						 objSubCat = new SubCategory();
						 objSubCat.setSubCatId(catDetails.getSubCatId());
						 objSubCat.setSubCatName(catDetails.getSubCatName());
						 objSubCat.setCatId(catDetails.getCatId());
						 objSubCat.setIsAdded(catDetails.getIsAdded());
						 subCatList.add(objSubCat);
					 }
				}
				if (mainCatList != null && !mainCatList.isEmpty()) {
					objCatDetails = new CategoryDetails();
					objCatDetails.setListMainCat(mainCatList);
				}
				objCatDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCatDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objCatDetails);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getUserPreferedCategories");
		return response;
	}

	/**
	 * Method to update user prefered categories.
	 * 
	 * @param xml
	 * @return
	 * @throws HubCitiException
	 */
	public String setUserPreferedCategories(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : setUserPreferedCategories");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final CategoryDetails objCategoryDetails = (CategoryDetails) streamHelper.parseXmlToObject(xml);

		if (objCategoryDetails.getUserId() == null || objCategoryDetails.getUserId().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERIDNOTFOUND);
		} else {
			response = firstUseDao.setUserPreferedCategories(objCategoryDetails);
			if (response == HubCitiConstants.SUCCESSCODE) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : setUserPreferedCategories");
		return response;
	}

	/**
	 * Method to get user information.
	 * 
	 * @param xml
	 * @return string xml.
	 * @throws HubCitiException
	 */
	public String getUserInfo(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getUserInfo");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetailsReq = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (objUserDetailsReq.getUserId() == null || objUserDetailsReq.getUserId().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			// List<UserDetails> objUserDetailsList = new
			// ArrayList<UserDetails>();
			UserDetails objUserDetails = null;
			objUserDetails = firstUseDao.getUserInfo(objUserDetailsReq);
			if (null != objUserDetails) {
				// UserDetails usrDet = new UserDetails();
				// usrDet.setListUserDetails(objUserDetailsList);
				objUserDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objUserDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objUserDetails);
				// response = response.replaceAll("<list>", "");
				// response = response.replaceAll("</list>", "");
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getUserInfo");
		return response;
	}

	/**
	 * Method to get user information.
	 * 
	 * @param json
	 * @return string json.
	 * @throws HubCitiException
	 */
	public String getUserInfo_json(String json) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getUserInfo");
		String response = null;

		try {
			final Gson gson = new Gson();
			final UserDetails objUserDetailsReq = (UserDetails) gson.fromJson(json, UserDetails.class);

			if (objUserDetailsReq.getUserId() == null || objUserDetailsReq.getUserId().equals("")) {
				response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				UserDetails objUserDetails = null;
				objUserDetails = firstUseDao.getUserInfo(objUserDetailsReq);
				if (null != objUserDetails) {
					objUserDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objUserDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = gson.toJson(objUserDetails);
				} else {
					response = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (Exception exception) {
			LOG.error("Error FirstUseServiceImpl : getUserInfo " + exception.getMessage());
			throw new HubCitiException(exception.getMessage());
		}

		LOG.info("Exit FirstUseServiceImpl : getUserInfo");
		return response;
	}

	/**
	 * Method to update user information
	 * 
	 * @param xml
	 * @return string xml.
	 * @throws HubCitiException
	 */
	public String updateUserInfo(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : updateUserInfo");

		String response = null;
		// UserDetails objUserDetailsResponse = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetailsReq = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (objUserDetailsReq.getUserId() == null || objUserDetailsReq.getUserId().equals("")) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			// objUserDetailsResponse =
			// firstUseDao.updateUserInfo(objUserDetailsReq);
			response = firstUseDao.updateUserInfo(objUserDetailsReq);
			if (null != response && HubCitiConstants.SUCCESSTEXT.equalsIgnoreCase(response)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
				/*
				 * if (null != objUserDetailsResponse.getDefPostalCode()) {
				 * response =
				 * Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
				 * HubCitiConstants.SUCCESSTEXT, "defPostalCode",
				 * objUserDetailsResponse.getDefPostalCode()); } else { response
				 * = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE,
				 * HubCitiConstants.SUCCESSTEXT); }
				 */
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : updateUserInfo");
		return response;
	}

	/**
	 * Method to update user information
	 * 
	 * @param JSON
	 * @return string JSON.
	 * @throws HubCitiException
	 */
	public String updateUserInfo_json(String json) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : updateUserInfo");

		String response = null;

		try {

			final Gson gson = new Gson();
			final UserDetails objUserDetailsReq = (UserDetails) gson.fromJson(json, UserDetails.class);

			if (objUserDetailsReq.getUserId() == null || objUserDetailsReq.getUserId().equals("")) {

				response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {
				response = firstUseDao.updateUserInfo(objUserDetailsReq);

				if (null != response && HubCitiConstants.SUCCESSTEXT.equalsIgnoreCase(response)) {

					response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);

				} else {
					response = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
				}
			}

		} catch (Exception exception) {
			LOG.error("Error Inside FirstUseServiceImpl : updateUserInfo" + exception.getMessage());
			throw new HubCitiException(exception.getMessage());
		}
		LOG.info("Exit FirstUseServiceImpl : updateUserInfo");
		return response;
	}

	/**
	 * ServiceImpl method is to display main menu details
	 * 
	 * @param String
	 *            type of xml containing Menu details
	 * @return String type of Menu xml details.
	 */
	/*
	 * public String menuDisplay(String xml) throws HubCitiException {
	 * LOG.info("Inside FirstUseServiceImpl : menuDisplay");
	 * 
	 * String response = null; Menu objMenuDisp = null;
	 * 
	 * String strNoMsg = null;
	 * 
	 * final XstreamParserHelper streamHelper = new XstreamParserHelper(); final
	 * Menu objMenu = (Menu) streamHelper.parseXmlToObject(xml);
	 * 
	 * if (null == objMenu.getUserId()) { response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.USERID_EMPTY); } else if (null ==
	 * objMenu.getHubCitiId()) { response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.HUBCITID_EMPTY); } else if (null == objMenu.getLevel())
	 * { response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.LEVELID_EMPTY); } else if (null ==
	 * objMenu.getPlatform()) { response =
	 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
	 * HubCitiConstants.PLATFORM_EMPTY); } else { objMenuDisp =
	 * firstUseDao.menuDisplay(objMenu);
	 * 
	 * if (null != objMenuDisp.getArMItemList() &&
	 * !objMenuDisp.getArMItemList().isEmpty()) {
	 * 
	 * objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
	 * objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
	 * objMenuDisp.setNoRecordMsg(null); response =
	 * XstreamParserHelper.produceXmlFromObject(objMenuDisp); } else if (null ==
	 * objMenuDisp.getArMItemList() && objMenuDisp.getIsTempChanged() == 0) {
	 * 
	 * objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
	 * objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
	 * objMenuDisp.setNoRecordMsg(null); response =
	 * XstreamParserHelper.produceXmlFromObject(objMenuDisp); } else { response
	 * = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE,
	 * objMenuDisp.getNoRecordMsg()); } }
	 * 
	 * LOG.info("Exit FirstUseServiceImpl : menuDisplay"); return response; }
	 */

	public String menuDisplay(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : menuDisplay");

		String response = null;
		Menu objMenuDisp = null;

		final Gson gson = new Gson();
		final Menu objMenu = (Menu) gson.fromJson(xml, Menu.class);

		if (null == objMenu.getUserId()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (null == objMenu.getHubCitiId()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (null == objMenu.getLevel()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.LEVELID_EMPTY);
		} else if (null == objMenu.getPlatform()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_EMPTY);
		} else {
			objMenuDisp = firstUseDao.menuDisplay(objMenu);

			if (null != objMenuDisp.getArMItemList() && !objMenuDisp.getArMItemList().isEmpty()) {

				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
				objMenuDisp.setNoRecordMsg(null);
				response = gson.toJson(objMenuDisp);
			} else if (null == objMenuDisp.getArMItemList() && objMenuDisp.getIsTempChanged() == 0) {

				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
				objMenuDisp.setNoRecordMsg(null);
				response = gson.toJson(objMenuDisp);
			} else {
				response = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, objMenuDisp.getNoRecordMsg());
			}
		}

		LOG.info("Exit FirstUseServiceImpl : menuDisplay");
		return response;
	}

	/**
	 * ServiceImpl gets user details and sends password to user email id.
	 * 
	 * @param strUsername
	 *            as request parameter
	 * @return The Account Information.
	 * @throws HubCitiException
	 */
	public String forgotPassword(String strUsername, Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : forgotPassword");

		String toAddress = null;
		String subject = null;
		String msgBody = null;
		String fromAddress = null;
		String responseXML = null;
		String smtpHost = null;
		String smtpPort = null;
		String decryptedPassword = null;

		EncryptDecryptPwd enryptDecryptpwd;
		AuthenticateUser objAuthentUser = null;

		ArrayList<AppConfiguration> arEmailConfigList = null;
		List<AppConfiguration> frmAddressAndSubjectList = null;

		if ("".equals(Utility.checkNull(strUsername))) {
			responseXML = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			objAuthentUser = firstUseDao.forgotPassword(strUsername, hubCitiId);
			if (null != objAuthentUser) {
				if ("".equals(Utility.checkNull(objAuthentUser.getEmail())) && objAuthentUser.getSuccess() == 0) {
					responseXML = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USEREMAILIDNOTEXIST);
					return responseXML;
				} else if ("".equals(Utility.checkNull(objAuthentUser.getEmail())) && objAuthentUser.getSuccess() == 1) {
					responseXML = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERNAME_INVALID);
					return responseXML;
				}
				frmAddressAndSubjectList = firstUseDao.getAppConfig(HubCitiConstants.FORGOTPASSWORD);
				try {
					enryptDecryptpwd = new EncryptDecryptPwd();
					decryptedPassword = enryptDecryptpwd.decrypt(objAuthentUser.getPassword());
					toAddress = objAuthentUser.getEmail();
					if (frmAddressAndSubjectList != null) {
						for (int i = 0; i < frmAddressAndSubjectList.size(); i++) {
							if (HubCitiConstants.FORGOTPASSWORD.equalsIgnoreCase(frmAddressAndSubjectList.get(i).getConfigurationType())) {
								if ("FromAddress".equalsIgnoreCase(frmAddressAndSubjectList.get(i).getScreenName())) {
									fromAddress = frmAddressAndSubjectList.get(i).getScreenContent();
								}
								if ("Subject".equalsIgnoreCase(frmAddressAndSubjectList.get(i).getScreenName())) {
									subject = frmAddressAndSubjectList.get(i).getScreenContent();
								}
							}
						}
						arEmailConfigList = firstUseDao.getAppConfig(HubCitiConstants.EMAILCONFIG);
						for (int j = 0; j < arEmailConfigList.size(); j++) {
							if (arEmailConfigList.get(j).getScreenName().equals(HubCitiConstants.SMTPHOST)) {
								smtpHost = arEmailConfigList.get(j).getScreenContent();
							}
							if (arEmailConfigList.get(j).getScreenName().equals(HubCitiConstants.SMTPPORT)) {
								smtpPort = arEmailConfigList.get(j).getScreenContent();
							}
						}
						msgBody = Utility.forgotPasswordEmailTemplate(decryptedPassword);
						if (null != smtpHost || null != smtpPort) {
							EmailComponent.mailingComponent(fromAddress, toAddress, subject, msgBody, smtpHost, smtpPort);
						}
						responseXML = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.FORGOTPASSWORD_RESPONSE);
					}
				} catch (InvalidObjectException e) {
					LOG.error("Password Decryption Exception  ", e);
					throw new HubCitiException(e.getMessage());
				} catch (InvalidAlgorithmParameterException e) {
					LOG.error(" Decryption  Password Exception ", e);
					throw new HubCitiException(e.getMessage());
				} catch (InvalidKeySpecException e) {
					LOG.error("user Password Exception in Decryption ", e);
					throw new HubCitiException(e.getMessage());
				} catch (IllegalBlockSizeException e) {
					LOG.error("exception Decryption Password   ", e);
					throw new HubCitiException(e.getMessage());
				} catch (BadPaddingException e) {
					LOG.error("user password decrypted exception ", e);
					throw new HubCitiException(e.getMessage());
				} catch (IOException e) {
					LOG.error("Exception in Decryption ", e);
					throw new HubCitiException(e.getMessage());
				} catch (MessagingException e) {
					LOG.error("Inside FirstUseServiceImpl : forgotPassword : ", e.getMessage());
					responseXML = Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS, HubCitiConstants.INVALIDEMAILADDRESSTEXT);
				} catch (NoSuchAlgorithmException e) {
					LOG.error("Inside FirstUseServiceImpl : forgotPassword : Password Decryption Exception : ", e.getMessage());
					throw new HubCitiException(e.getMessage());
				} catch (NoSuchPaddingException e) {
					LOG.error("Inside FirstUseServiceImpl : forgotPassword : Password Decryption Exception : ", e.getMessage());
					throw new HubCitiException(e.getMessage());
				} catch (InvalidKeyException e) {
					LOG.error("Inside FirstUseServiceImpl : forgotPassword : Password Decryption Exception : ", e.getMessage());
					throw new HubCitiException(e.getMessage());
				}
			} else {
				responseXML = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : forgotPassword");
		return responseXML;
	}

	/**
	 * ServiceImpl method is takes the user new password and encrypt it.
	 * 
	 * @param inputXML
	 *            in the User Request.
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 */
	public String changePassword(String inputXML) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : changePassword");

		String response = null;
		String encryptedpwd = null;

		EncryptDecryptPwd encryptDecryptPwd;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserRegistrationInfo objUserRegistInfo = (UserRegistrationInfo) streamHelper.parseXmlToObject(inputXML);

		try {
			if (null == objUserRegistInfo.getUserId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
			} else if ("".equals(Utility.checkNull(objUserRegistInfo.getPassword()))) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PASSWORD_EMPTY);
			}
			encryptDecryptPwd = new EncryptDecryptPwd();
			encryptedpwd = encryptDecryptPwd.encrypt(objUserRegistInfo.getPassword());
			if ("".equals(Utility.checkNull(encryptedpwd))) {
				return Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			}
		} catch (NoSuchElementException e) {
			LOG.error("Exception in Encryption of password", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Exception in Encryption of user password", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("user password Encryption Exception", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Exception while Encryption  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Exception during Encryption", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Exception in user password Encryption", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Exception in Encryption", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Exception in Encryption", e);
			throw new HubCitiException(e.getMessage());
		}
		response = firstUseDao.changePassword(objUserRegistInfo.getUserId(), encryptedpwd);
		if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
			response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.PASSWORDUPDATED);
		} else {
			response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseServiceImpl : changePassword");
		return response;
	}

	/**
	 * ServiceImpl method to get started images information.
	 * 
	 * @param iUserId
	 *            as request parameter.
	 * @return XML containing user information in the response.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */

	public String getStartedImageInfo(Integer iUserId) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getStartedImageInfo");

		String strResponse = null;
		TutorialMediaResultSet objTutMediaResultSet = null;

		if (null == iUserId) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			objTutMediaResultSet = firstUseDao.getStartedImageInfo();
			if (null != objTutMediaResultSet) {
				objTutMediaResultSet.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objTutMediaResultSet.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strResponse = XstreamParserHelper.produceXmlFromObject(objTutMediaResultSet);
			} else {
				strResponse = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getStartedImageInfo");
		return strResponse;
	}

	/**
	 * Service method to get main menu id.
	 * 
	 * @param xml
	 * @return xml containing mainMenuId
	 * @throws HubCitiException.
	 */
	public String getMainMenuId(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getMainMenuId");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final MenuItem objMenuItem = (MenuItem) streamHelper.parseXmlToObject(xml);

		if (objMenuItem.getUserId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objMenuItem.getHubCitiId() == null || objMenuItem.getPlatform() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "hubCitiId and platform required");
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

		LOG.info("Exit FirstUseServiceImpl : getMainMenuId");
		return response;
	}

	public String getMainMenuId(Integer iUserId, Integer ihubCitiId, Integer imItemId, Integer ibottomBtnId, String splatform) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getMainMenuId");

		String response = null;

		// create instance
		final MenuItem objMenuItem = new MenuItem();

		/**
		 * Assign value to variable
		 */
		objMenuItem.setUserId(iUserId);
		objMenuItem.setHubCitiId(ihubCitiId);
		objMenuItem.setmItemId(imItemId);
		objMenuItem.setBottomBtnId(ibottomBtnId);
		objMenuItem.setPlatform(splatform);

		if (objMenuItem.getUserId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (objMenuItem.getHubCitiId() == null || objMenuItem.getPlatform() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "hubCitiId and platform required");
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

		LOG.info("Exit FirstUseServiceImpl : getMainMenuId");
		return response;
	}

	/**
	 * This is a Service Method To get department Name, ID, menuItemType Name,
	 * ID.
	 * 
	 * @param xml
	 * @return
	 * @throws HubCitiException
	 */
	public String getDepartmentAndMenuTypeDetails(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getDepartmentAndMenuTypeDetails");

		String response = null;

		List<MenuItem> menuItemList = null;
		Menu objMenu = new Menu();

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final MenuItem objMenuItem = (MenuItem) streamHelper.parseXmlToObject(xml);

		if (null == objMenuItem.getMenuId() || null == objMenuItem.getHubCitiId() || null == objMenuItem.getfName()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			menuItemList = firstUseDao.getDepartmentAndMenuTypeDetails(objMenuItem);
			if (menuItemList != null && !menuItemList.isEmpty()) {
				objMenu.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenu.setResponseText(HubCitiConstants.SUCCESSTEXT);
				objMenu.setmItemList(menuItemList);
				response = XstreamParserHelper.produceXmlFromObject(objMenu);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getDepartmentAndMenuTypeDetails");
		return response;
	}

	/**
	 * This ServiceImpl method to enable or disable push notification alerts.
	 * Calls XStream helper class methods for parsing
	 * 
	 * @param xml
	 *            - the request xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.Retailer
	 */
	public String setUserSettings(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : setUserSettings");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserSettings userSettings = (UserSettings) streamHelper.parseXmlToObject(xml);

		// (null == userSettings.getPushNotify()
		if ((null == userSettings.getUserId()) || (null == userSettings.getLocaleRadius())) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = firstUseDao.setUserSettings(userSettings);
			if (null != response && !response.equals(HubCitiConstants.FAILURE)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.USERSETTINGSUPDATETEXT);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : setUserSettings");
		return response;
	}

	/**
	 * This ServiceImpl method get push notification alert on/off and wish list
	 * radius.Calls XStream helper class methods for parsing
	 * 
	 * @param xml
	 *            - the request xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.Retailer
	 */
	public String getUserSettings(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getUserSettings");

		String response = null;
		UserSettings userSettings = null;
		List<UserSettings> userSettingsList = null;

		final XstreamParserHelper helper = new XstreamParserHelper();
		final AuthenticateUser objAuthenticateUser = (AuthenticateUser) helper.parseXmlToObject(xml);

		// (null == userSettings.getPushNotify()
		if (null == objAuthenticateUser.getUserId() || null == objAuthenticateUser.getDeviceID()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			userSettingsList = firstUseDao.getUserSettings(objAuthenticateUser);
			if (null != userSettingsList && !userSettingsList.isEmpty()) {
				userSettings = userSettingsList.get(0);
				userSettings.setResponseCode(HubCitiConstants.SUCCESSCODE);
				userSettings.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(userSettings);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getUserSettings");
		return response;
	}

	/**
	 * This ServiceImpl method for saving user email id state.
	 * 
	 * @param userID
	 *            , emailId for which user information need to be fetched.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.Retailer
	 */
	public String saveUserEmailId(Integer userID, String emailId) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : saveUserEmailId");

		String response = null;

		if (null == userID && null == emailId) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			boolean isValid = Utility.validateEmailId(emailId);
			if (!isValid) {
				return Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INVALIDEMAILADDRESSTEXT);
			}
			response = firstUseDao.saveUserEmailId(userID, emailId);
			if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.USEREMAILUPDATE);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : saveUserEmailId");
		return response;
	}

	public String fetchUnivStates(Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : fetchUnivStates");

		String response = null;
		ArrayList<CountryCode> stateCityList;

		stateCityList = firstUseDao.fetchUnivStates(hubCitiId);
		// ArrayList<CountryCodes> countryCodesList;
		if (null != stateCityList && !stateCityList.isEmpty()) {
			String successCodeText = "<responseCode>" + HubCitiConstants.SUCCESSCODE + "</responseCode><responseText>" + HubCitiConstants.SUCCESSTEXT + "</responseText>";
			response = XstreamParserHelper.produceXmlFromObject(stateCityList);
			response = response.replaceAll("<list>", "<UniversityStates>" + successCodeText);
			response = response.replaceAll("</list>", "</UniversityStates>");
		} else {
			response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
		}

		LOG.info("Exit FirstUseServiceImpl : fetchUnivStates");
		return response;
	}

	public String fetchUniversities(Integer hubCitiId, String stateAbv) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : fetchUniversities");

		String response = null;
		ArrayList<CountryCode> stateCityList;

		stateCityList = firstUseDao.fetchUniversities(hubCitiId, stateAbv);

		if (null != stateCityList && !stateCityList.isEmpty()) {
			String successCodeText = "<responseCode>" + HubCitiConstants.SUCCESSCODE + "</responseCode><responseText>" + HubCitiConstants.SUCCESSTEXT + "</responseText>";
			response = XstreamParserHelper.produceXmlFromObject(stateCityList);
			response = response.replaceAll("<list>", "<UniversityInfo>" + successCodeText);
			response = response.replaceAll("</list>", "</UniversityInfo>");
			response = StringEscapeUtils.unescapeXml(response);
		} else {
			response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
		}

		LOG.info("Exit FirstUseServiceImpl : fetchUniversities");
		return response;
	}

	/**
	 * Method to check is user is outside hubCiti or not by taking latitude,
	 * longitude or postal code. Method Type:POST
	 * 
	 * @param xml
	 *            as request containing userId, hubCitiId, latitude and
	 *            longitude or postal code.
	 * @return xml containing is user out of hubCiti and postal code if user
	 *         outside hubCiti.
	 */
	public String hubCitiUserRangeCheck(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : hubCitiUserRangeCheck");

		String response = null;

		final XstreamParserHelper helper = new XstreamParserHelper();
		final UserDetails objUserDetailsRequest = (UserDetails) helper.parseXmlToObject(xml);

		if (null == objUserDetailsRequest.getUserId() || null == objUserDetailsRequest.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			UserDetails objUserDetailsResponse = new UserDetails();
			objUserDetailsResponse = firstUseDao.hubCitiUserRangeCheck(objUserDetailsRequest);
			if (objUserDetailsResponse.getIsUserOutOfCiti() != null) {
				objUserDetailsResponse.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objUserDetailsResponse.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objUserDetailsResponse);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : hubCitiUserRangeCheck");
		return response;
	}

	/**
	 * Method to fetch FAQ's categories. Also converts input XML to object and
	 * response object to XML.
	 * 
	 * @param inputXml
	 * @return XML.
	 * @throws HubCitiException.
	 */
	public String getFAQsCategories(Integer hubCitiId, Integer lowerLimit, Integer userId, String platform, String searchKey) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getFAQsCategories");

		String response = null;

		Categories categories = null;
		try {

			if (null == hubCitiId || null == userId) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				categories = firstUseDao.getFAQsCategories(hubCitiId, lowerLimit, userId, searchKey);

				if (null != categories && null != categories.getFaqCatList() && !categories.getFaqCatList().isEmpty()) {

					for (FAQDetails faqDetail : categories.getFaqCatList()) {

						if (null != faqDetail.getCategoryName() && null != platform && "android".equalsIgnoreCase(platform)) {

							faqDetail.setCategoryName(URLEncoder.encode(faqDetail.getCategoryName(), "UTF-8"));

						} else {
							faqDetail.setCategoryName("<![CDATA[" + faqDetail.getCategoryName() + "]]>");
						}
					}

					categories.setResponseCode(HubCitiConstants.SUCCESSCODE);
					categories.setResponseText(HubCitiConstants.SUCCESSTEXT);
				} else {
					categories.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					categories.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}
				response = XstreamParserHelper.produceXmlFromObject(categories);
			}
		} catch (UnsupportedEncodingException usee) {

			LOG.error("Inside FirstUseServiceImpl getFAQsCategories " + usee);
			throw new HubCitiException(usee);

		}
		LOG.info("Exit FirstUseServiceImpl : getFAQsCategories");
		return response;
	}

	/**
	 * ServiceImpl to get particular FAQ Details.
	 * 
	 * @param xml
	 *            containing FAQ information.
	 * @return XML containing particular FAQ Details in the response.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public String fetchFaqDetails(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : fetchFaqDetails");

		FAQDetails objFAQDetails = null;
		String response = null;

		final XstreamParserHelper helper = new XstreamParserHelper();
		final FAQDetails objFaqReq = (FAQDetails) helper.parseXmlToObject(xml);

		try {

			if (null == objFaqReq.getHubCitiId() || null == objFaqReq.getFaqId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				objFAQDetails = firstUseDao.fetchFaqDetails(objFaqReq);
				if (null != objFAQDetails) {
					objFAQDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objFAQDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					/**
					 * Changes for android only as xml are converted to json.
					 */
					if (objFaqReq.getPlatform() != null && "Android".equalsIgnoreCase(objFaqReq.getPlatform())) {
						String answer = objFAQDetails.getAnswer();
						answer = answer.replace("<![CDATA[", "");
						answer = answer.replace("]]>", "");
						answer = answer.replaceAll("<", "&#60;");
						answer = answer.replaceAll(">", "&#62;");
						answer = URLEncoder.encode(answer, "UTF-8");
						objFAQDetails.setAnswer(answer);
					}
					response = XstreamParserHelper.produceXmlFromObject(objFAQDetails);
				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (UnsupportedEncodingException ue) {
			LOG.error("Inside FirstUseServiceImpl : fetchFaqDetails : " + ue.getMessage());
			throw new HubCitiException(ue);
		}
		LOG.info("Exit FirstUseServiceImpl : fetchFaqDetails");
		return response;
	}

	/**
	 * ServiceImpl to get All FAQ Display or based on searchkey. Method Type:
	 * POST
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing response with list FAQ Display.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public String fetchFaqDisplay(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : fetchFaqDisplay");

		FAQDetails objFAQDetails = null;
		String response = null;

		final XstreamParserHelper helper = new XstreamParserHelper();
		final FAQDetails objFaqReq = (FAQDetails) helper.parseXmlToObject(xml);

		try {

			if (null == objFaqReq.getHubCitiId() || null == objFaqReq.getCategoryId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				objFAQDetails = firstUseDao.fetchFaqDisplay(objFaqReq);
				if (null != objFAQDetails) {

					for (FAQDetails faq : objFAQDetails.getFaqDetail()) {

						if (null != faq.getQuestion()) {

							if (null != objFaqReq.getPlatform() && "Android".equalsIgnoreCase(objFaqReq.getPlatform())) {

								faq.setQuestion(URLEncoder.encode(faq.getQuestion(), "UTF-8"));

							} else {
								faq.setQuestion("<![CDATA[" + faq.getQuestion() + "]]>");
							}
						}

						if (null != faq.getAnswer()) {
							if (null != objFaqReq.getPlatform() && "Android".equalsIgnoreCase(objFaqReq.getPlatform())) {
								String answer = faq.getAnswer().replace("<![CDATA[", "").replace("]]>", "");
								faq.setAnswer(URLEncoder.encode(answer, "UTF-8"));
							}
						}
					}

					objFAQDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objFAQDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

					response = XstreamParserHelper.produceXmlFromObject(objFAQDetails);
				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

		} catch (UnsupportedEncodingException usee) {

		}
		LOG.info("Exit FirstUseServiceImpl : fetchFaqDisplay");
		return response;
	}

	/**
	 * ServiceImpl method to get City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing City Preferences details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getCityPreferences(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getCityPreferences");

		String response = null;
		City objCity = null;
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (null == objUserDetails.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else {

			objCity = firstUseDao.getCityPreferences(objUserDetails);

			if (null != objCity && null != objCity.getCityList() && !objCity.getCityList().isEmpty()) {
				objCity.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCity.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objCity);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getCityPreferences");
		return response;
	}

	/**
	 * ServiceImpl method to update City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String setCityPreferences(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : setCityPreferences");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (null == objUserDetails.getUserId() || null == objUserDetails.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} /*
		 * else if (null == objUserDetails.getCityIds()){ response =
		 * Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,
		 * HubCitiConstants.CITYIDS_EMPTY); }
		 */else {

			 response = firstUseDao.setCityPreferences(objUserDetails);

			 if (response == HubCitiConstants.SUCCESSCODE) {
				 response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			 } else {
				 response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			 }
		 }

		LOG.info("Exit FirstUseServiceImpl : setCityPreferences");
		return response;
	}

	/**
	 * ServiceImpl method to get user city preferences.
	 * 
	 * @param XML
	 * @return String XML containing City Preferences details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getUserCityPreferences(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getUserCityPreferences");

		String response = null;
		City objCity = null;
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (null == objUserDetails.getUserId() || null == objUserDetails.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			objCity = firstUseDao.getUserCityPreferences(objUserDetails);

			if (null != objCity && null != objCity.getCityList() && !objCity.getCityList().isEmpty()) {
				objCity.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objCity.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objCity);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : getUserCityPreferences");
		return response;
	}

	/**
	 * ServiceImpl method to get FAQ Setting bit flag.
	 * 
	 * @param XML
	 * @return String XML containing to get FAQ Setting bit flag.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFaqSetting(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getFaqSetting");

		String response = null;
		UserDetails userDetails = null;
		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

		if (null == objUserDetails.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else {

			userDetails = firstUseDao.getFaqSetting(objUserDetails);

			if (null != userDetails) {
				userDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				userDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				userDetails = new UserDetails();
				userDetails.setResponseCode(HubCitiConstants.FAILURECODE);
				userDetails.setResponseText(HubCitiConstants.FAILURETEXT);
			}
			response = XstreamParserHelper.produceXmlFromObject(userDetails);
		}

		LOG.info("Exit FirstUseServiceImpl : getFaqSetting");
		return response;
	}

	/**
	 * ServiceImpl method for Register device id and token id for push
	 * notification.
	 * 
	 * @param XML
	 * @return String XML containing SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String registerPushNotification(String inputXml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : registerPushNotification");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserRegistrationInfo userRegInfo = (UserRegistrationInfo) streamHelper.parseXmlToObject(inputXml);

		if ("".equals(Utility.checkNull(userRegInfo.getDeviceID()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.DEVICEID_EMPTY);
		} else if ("".equals(Utility.checkNull(userRegInfo.getUserTokenID()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERTOKENID_EMPTY);
		} else if ("".equals(Utility.checkNull(userRegInfo.getPlatform()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_EMPTY);
		} else if ("".equals(Utility.checkNull(userRegInfo.getHcKey()))) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "HubCiti Key Required.");
		} else {
			response = firstUseDao.registerPushNotification(userRegInfo);
			if (null != response && !response.equals(HubCitiConstants.FAILURE)) {
				if (response.equalsIgnoreCase("Exists")) {
					response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.PUSHNOTIFREGISTRATION);
				} else {
					response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
				}

			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			}
		}
		return response;
	}

	public String getUserPreferedLocationCategories(Integer userId, Integer hubCitiId) throws HubCitiException {
		LOG.info("Method Start: FirstUseServiceImpl : getUserPreferedLocationCategories()");
		String response = null;
		CategoryDetails objCatDetail = null;

		try {
			if (null == userId || "".equals(userId) || null == hubCitiId || "".equals(hubCitiId)) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {
				objCatDetail = firstUseDao.getUserPreferedLocationCategories(userId, hubCitiId);
				if (null != objCatDetail && null != objCatDetail.getListCatDetails() && !objCatDetail.getListCatDetails().isEmpty()) {
					Integer currentCatId = null;
					Integer prevCatId = 0;
					Boolean firstEntry = true;
					MainCategory objMainCat = null;
					SubCategory objSubCat = null;
					List<MainCategory> mainCatList = new ArrayList<MainCategory>();
					List<SubCategory> subCatList = null;
					int i = 0;
					for (CategoryDetails obj : objCatDetail.getListCatDetails()) {
						i++;
						if (i == 144) {
							int k = 122;
						}

						currentCatId = obj.getCatId();

						if (prevCatId.equals(currentCatId)) {
							objSubCat = new SubCategory();
							objSubCat.setSubCatId(obj.getSubCatId());
							// if(Utility.checkXMLIllegalCharacter(obj.getSubCatName())
							// &&
							// !obj.getSubCatName().contains(HubCitiConstants.CDATA_START))
							// {
							// objSubCat.setSubCatName(HubCitiConstants.CDATA_START
							// + obj.getSubCatName() +
							// HubCitiConstants.CDATA_END);
							// } else {
							objSubCat.setSubCatName(obj.getSubCatName());
							// }
							objSubCat.setIsAdded(obj.getIsAdded());
							subCatList.add(objSubCat);
						} else {
							if (!firstEntry) {
								objMainCat.setListSubCat(subCatList);
								mainCatList.add(objMainCat);
							}

							objMainCat = new MainCategory();
							objMainCat.setParCatId(obj.getCatId());
							// if(Utility.checkXMLIllegalCharacter(obj.getCatName())
							// &&
							// !obj.getCatName().contains(HubCitiConstants.CDATA_START))
							// {
							// objMainCat.setParCatName(HubCitiConstants.CDATA_START
							// + obj.getCatName() + HubCitiConstants.CDATA_END);
							// } else {
							objMainCat.setParCatName(obj.getCatName());
							// }

							subCatList = new ArrayList<SubCategory>();
							objSubCat = new SubCategory();
							objSubCat.setSubCatId(obj.getSubCatId());
							// if(Utility.checkXMLIllegalCharacter(obj.getSubCatName())
							// &&
							// !obj.getSubCatName().contains(HubCitiConstants.CDATA_START))
							// {
							// objSubCat.setSubCatName(HubCitiConstants.CDATA_START
							// + obj.getSubCatName() +
							// HubCitiConstants.CDATA_END);
							// } else {
							objSubCat.setSubCatName(obj.getSubCatName());
							// }

							objSubCat.setIsAdded(obj.getIsAdded());
							subCatList.add(objSubCat);
						}
						firstEntry = false;
						prevCatId = currentCatId;
					}
					objCatDetail = new CategoryDetails();
					objCatDetail.setListMainCat(mainCatList);
					objCatDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objCatDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					response = XstreamParserHelper.produceXmlFromObject(objCatDetail);
				} else {
					response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error(e.getMessage());
			throw new HubCitiException();
		}
		LOG.info("Method End: FirstUseServiceImpl : getUserPreferedLocationCategories()");
		return response;
	}

	public String updateUserPreferedLocationCategories(String xml) throws HubCitiException {
		LOG.info("Method Start: FirstUseServiceImpl : updateUserPreferedLocationCategories()");
		String responseXML = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final CategoryDetails objCategoryDetails = (CategoryDetails) streamHelper.parseXmlToObject(xml);

		if (objCategoryDetails.getUserId() == null || objCategoryDetails.getUserId().equals("")) {
			responseXML = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERIDNOTFOUND);
		} else {
			responseXML = firstUseDao.updateUserPreferedLocationCategories(objCategoryDetails);
			if (responseXML == HubCitiConstants.SUCCESSCODE) {
				responseXML = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			} else {
				responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
			}
		}

		LOG.info("Method End: FirstUseServiceImpl : updateUserPreferedLocationCategories()");
		return responseXML;
	}

	/**
	 * ServiceImpl Method to find pushNotify Back Ground details.
	 * 
	 * @param hubCitiId
	 *            .
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String pushNotifyBackGrd(Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : pushNotifyBackGrd");

		String response = null;

		if (null == hubCitiId) {

			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);

		} else {

			try {
				Data objData = firstUseDao.pushNotifyBackGrd(hubCitiId);

				if (null == objData) {
					LOG.info("No messages or deviceIds to send Push Notification");
					return response;
				} else {
					objData.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objData.setResponseText(HubCitiConstants.SUCCESSTEXT);
				}

				/*
				 * 
				 * Removing CDATA for dealName without effecting to existing
				 * implementation
				 */

				if (null != objData.getDealList() && !objData.getDealList().isEmpty()) {

					for (Deal dealref : objData.getDealList()) {
						if (null != dealref && null != dealref.getDealName()) {
							dealref.setIsPushNotification(true);
							dealref.setDealName(dealref.getDealName().replace("<![CDATA[", "").replace("]]>", ""));
							dealref.setIsPushNotification(null);
						}
					}
				}

				if (null != objData.getRssFeedList() && !objData.getRssFeedList().isEmpty()) {
					for (RSSFeedMessage feeds : objData.getRssFeedList()) {
						if (null != feeds) {
							feeds.setIsPushNotification(true);
							if (null != feeds.getTitle()) {
								feeds.setTitle(feeds.getTitle().replace("<![CDATA[", "").replace("]]>", ""));
							}
							if (null != feeds.getLink()) {
								feeds.setLink(feeds.getLink().replace("<![CDATA[", "").replace("]]>", ""));
							}
							feeds.setIsPushNotification(null);
						}
					}
				}

				final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				response = gson.toJson(objData);
			} catch (HubCitiException errorMsg) {
				LOG.error("Inside FirstUseServiceImpl : pushNotifyBackGrd : " + errorMsg.getMessage());
				throw new HubCitiException(errorMsg);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : pushNotifyBackGrd");
		return response;
	}

	/**
	 * ServiceImpl Method to find web pushNotify Back Ground details.
	 * 
	 * @param hubCitiId
	 *            .
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String webPushNotifyBackGrd(Integer hubCitiId) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : webPushNotifyBackGrd");

		String response = null;
		Data objData = null;
		final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

		if (null == hubCitiId) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else {

			objData = firstUseDao.webPushNotifyBackGrd(hubCitiId);
			if (null == objData) {
				response = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			} else {
				objData.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objData.setResponseText(HubCitiConstants.SUCCESSTEXT);

				if (null != objData.getDealList() && !objData.getDealList().isEmpty()) {
					for (Deal objDeal : objData.getDealList()) {
						if (null != objDeal && null != objDeal.getDealName()) {
							objDeal.setIsPushNotification(true);
							objDeal.setDealName(objDeal.getDealName().replace("<![CDATA[", "").replace("]]>", ""));
							objDeal.setIsPushNotification(null);
						}
					}
				}

				response = gson.toJson(objData);
			}
		}

		LOG.info("Exit FirstUseServiceImpl : webPushNotifyBackGrd");
		return response;
	}

	/**
	 * ServiceImpl method is to update device details
	 * 
	 * @param String
	 *            type of containing device details
	 * @return String as json.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String updateDeviceDetailsForMenu(String json) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : updateDeviceDetailsForMenu");

		String response = null;
		final Menu objMenuDisp = new Menu();

		final Gson gson = new Gson();
		final Menu objMenu = (Menu) gson.fromJson(json, Menu.class);

		if (null == objMenu.getUserId()) {
			response = Utility.generateErrRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
		} else if (null == objMenu.getHubCitiId()) {
			response = Utility.generateErrRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (null == objMenu.getPlatform()) {
			response = Utility.generateErrRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_EMPTY);
		} else if (null == objMenu.getDeviceId()) {
			response = Utility.generateErrRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.DEVICEID_EMPTY);
		} else {

			response = firstUseDao.updateDeviceDetailsForMenu(objMenu);

			if (null != response && ApplicationConstants.SUCCESS.equals(response)) {
				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				objMenuDisp.setResponseCode(HubCitiConstants.FAILURECODE);
				objMenuDisp.setResponseText(HubCitiConstants.FAILURETEXT);
			}

			response = gson.toJson(objMenuDisp);
		}

		LOG.info("Exit FirstUseServiceImpl : updateDeviceDetailsForMenu");
		return response;
	}

	/**
	 * ServiceImpl method is to cache HubCiti MenuDisplay information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu JSON details.
	 */

	@Cacheable(value = "getHubMenuDisplay", key = "#strJSON")
	public String HubMenuDisplay(String strJSON) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : HubMenuDisplay");

		String response = null;
		Menu objMenuDisp = null;

		final Gson gson = new Gson();
		final Menu objMenu = (Menu) gson.fromJson(strJSON, Menu.class);

		if (null == objMenu.getHubCitiId()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (null == objMenu.getLevel()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.LEVELID_EMPTY);
		} else {
			objMenuDisp = firstUseDao.HubMenuDisplay(objMenu);

			if (null != objMenuDisp.getArMItemList() && !objMenuDisp.getArMItemList().isEmpty()) {
				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else if (null == objMenuDisp.getArMItemList() && objMenuDisp.getIsTempChanged() == 0 && "".equals(Utility.checkNull(objMenuDisp.getNoRecordMsg()))) {
				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else if (null == objMenuDisp.getArMItemList() && objMenuDisp.getIsTempChanged() == 1 && "".equals(Utility.checkNull(objMenuDisp.getNoRecordMsg()))) {
				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				objMenuDisp.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
				objMenuDisp.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}
			objMenuDisp.setNoRecordMsg(null);
			response = gson.toJson(objMenuDisp);
		}

		LOG.info("Exit FirstUseServiceImpl : HubMenuDisplay");
		return response;
	}

	/**
	 * ServiceImpl method is to cache Hub Region MenuDisplay information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu JSON details.
	 */
	@Cacheable(value = "getmenuDisplayForHubRegion", key = "#strJSON")
	public String menuDisplayForHubRegion(String strJSON) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : menuDisplayForHubRegion");

		String response = null;
		Menu objMenuDisp = null;

		final Gson gson = new Gson();
		final Menu objMenu = (Menu) gson.fromJson(strJSON, Menu.class);

		if (null == objMenu.getHubCitiId()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITID_EMPTY);
		} else if (null == objMenu.getLevel()) {
			response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.LEVELID_EMPTY);
		} else {
			objMenuDisp = firstUseDao.menuDisplayForHubRegion(objMenu);

			if (null != objMenuDisp.getArMItemList() && !objMenuDisp.getArMItemList().isEmpty()) {

				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else if (null == objMenuDisp.getArMItemList() && objMenuDisp.getIsTempChanged() == 0 
					&& "".equals(Utility.checkNull(objMenuDisp.getNoRecordMsg()))) {

				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else if (null == objMenuDisp.getArMItemList() && objMenuDisp.getIsTempChanged() == 1 
					&& "".equals(Utility.checkNull(objMenuDisp.getNoRecordMsg()))) {
				objMenuDisp.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objMenuDisp.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				objMenuDisp.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
				objMenuDisp.setResponseText(objMenuDisp.getNoRecordMsg());
				objMenuDisp.setNoRecordMsg(null);
			}
			response = gson.toJson(objMenuDisp);
		}

		LOG.info("Exit FirstUseServiceImpl : menuDisplayForHubRegion");
		return response;
	}

	/**
	 * This ServiceImpl method to clear the cache for HubCiti MenuDisplay
	 * information.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	@CacheEvict(value = "getHubMenuDisplay", allEntries = true)
	public String rmHubCitiMenuDisplay() throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : rmHubCitiMenuDisplay ");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}

	/**
	 * This ServiceImpl method to remove Hub Region MenuDisplay information from
	 * cached.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	@CacheEvict(value = "getmenuDisplay", allEntries = true)
	public String rmHubRegionMenuDisplay() throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : rmHubRegionMenuDisplay ");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}

	/**
	 * This ServiceImpl method remove Hub Region MenuDisplay information from
	 * cached.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	@CacheEvict(value = "getmenuDisplayForHubRegion", allEntries = true)
	public String rmNewHubRegionMenuDisplay() throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : rmNewHubRegionMenuDisplay ");
		final String response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
		return response;
	}

	/**
	 * Web service for HubCiti IOS Push Notification user Instance Badge Value
	 * reset. Business Logic: Checking only Required Information.
	 * 
	 * @param hubCitiId
	 * @param deviceId
	 * @param userId
	 * @return response
	 */
	public String resetBadge(Integer hubCitiId, String deviceId, Integer userId) throws HubCitiException {

		LOG.info("Inside FirstUseServiceImpl : resetBadge");

		String response = null;

		try {
			if (null == hubCitiId || null == deviceId) {
				response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				// invoke DAO Layer
				response = firstUseDao.resetBadge(hubCitiId, deviceId, userId);
				response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, response);
			}

		} catch (Exception e) {
			LOG.error("Exit FirstUseServiceImpl : resetBadge");
			throw new HubCitiException(e.getMessage());
		}

		LOG.info("Exit FirstUseServiceImpl : resetBadge");

		return response;
	}

	/**
	 * Version 2.0
	 * 
	 * ServiceImpl gets user details and sends password to user email id.
	 * 
	 * @param strUsername
	 *            as request parameter
	 * @return The Account Information.
	 * @throws HubCitiException
	 */
	public String forgotPassword_v2(String requestXML) throws HubCitiException {

		LOG.info("Inside FirstUseServiceImpl : forgotPassword_v2");

		String toAddress = null;
		String subject = null;
		String msgBody = null;
		String fromAddress = null;
		String responseXML = null;
		String smtpHost = null;
		String smtpPort = null;
		String decryptedPassword = null;
		String autogenPassword = null;

		EncryptDecryptPwd enryptDecryptpwd;
		AuthenticateUser objAuthentUser = null;

		ArrayList<AppConfiguration> arEmailConfigList = null;
		List<AppConfiguration> frmAddressAndSubjectList = null;
		UserRegistrationInfo objUserRegistInfo = null;

		try {
			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			objUserRegistInfo = (UserRegistrationInfo) streamHelper.parseXmlToObject(requestXML);

			if (null == objUserRegistInfo.getUserName() || null == objUserRegistInfo.getHubCitiId()) {
				responseXML = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				// For generating auto generated password.
				autogenPassword = Utility.randomString(5);

				enryptDecryptpwd = new EncryptDecryptPwd();
				String encryptedPassword = enryptDecryptpwd.encrypt(autogenPassword);

				objAuthentUser = firstUseDao.forgotPassword_v2(objUserRegistInfo.getUserName(), objUserRegistInfo.getHubCitiId(), encryptedPassword);

				if (null != objAuthentUser) {

					if (objAuthentUser.getSuccess() == 0) {
						responseXML = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, objAuthentUser.getMessage());
						return responseXML;
					}

					frmAddressAndSubjectList = firstUseDao.getAppConfig(HubCitiConstants.FORGOTPASSWORD);

					enryptDecryptpwd = new EncryptDecryptPwd();
					decryptedPassword = enryptDecryptpwd.decrypt(objAuthentUser.getPassword());

					toAddress = objAuthentUser.getEmail();

					if (frmAddressAndSubjectList != null) {
						for (int i = 0; i < frmAddressAndSubjectList.size(); i++) {
							if (HubCitiConstants.FORGOTPASSWORD.equalsIgnoreCase(frmAddressAndSubjectList.get(i).getConfigurationType())) {
								if ("FromAddress".equalsIgnoreCase(frmAddressAndSubjectList.get(i).getScreenName())) {
									fromAddress = frmAddressAndSubjectList.get(i).getScreenContent();
								}
								if ("Subject".equalsIgnoreCase(frmAddressAndSubjectList.get(i).getScreenName())) {
									subject = frmAddressAndSubjectList.get(i).getScreenContent();
								}
							}
						}
						arEmailConfigList = firstUseDao.getAppConfig(HubCitiConstants.EMAILCONFIG);

						for (int j = 0; j < arEmailConfigList.size(); j++) {
							if (arEmailConfigList.get(j).getScreenName().equals(HubCitiConstants.SMTPHOST)) {
								smtpHost = arEmailConfigList.get(j).getScreenContent();
							}
							if (arEmailConfigList.get(j).getScreenName().equals(HubCitiConstants.SMTPPORT)) {
								smtpPort = arEmailConfigList.get(j).getScreenContent();
							}
						}

						msgBody = Utility.forgotPasswordEmailTemplate_v2(objAuthentUser.getUserName(), decryptedPassword);

						if (null != smtpHost || null != smtpPort) {
							EmailComponent.mailingComponent(fromAddress, toAddress, subject, msgBody, smtpHost, smtpPort);
						}
						responseXML = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, objAuthentUser.getMessage());
					}

				} else {
					responseXML = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (InvalidObjectException e) {
			LOG.error("Password Decryption Exception forgotPassword_v2  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error(" Decryption  Password Exception forgotPassword_v2 ", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("user Password Exception in Decryption forgotPassword_v2 ", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("exception Decryption Password forgotPassword_v2  ", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("user password decrypted exception forgotPassword_v2 ", e);
			throw new HubCitiException(e.getMessage());
		} catch (IOException e) {
			LOG.error("Exception in Decryption forgotPassword_v2 ", e);
			throw new HubCitiException(e.getMessage());
		} catch (MessagingException e) {
			LOG.error("Inside FirstUseServiceImpl : forgotPassword_v2 : ", e.getMessage());
			responseXML = Utility.formResponseXml(HubCitiConstants.INVALIDEMAILADDRESS, "Unable to send Email to : " + toAddress);
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Inside FirstUseServiceImpl : forgotPassword_v2 : Password Decryption Exception : ", e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Inside FirstUseServiceImpl : forgotPassword_v2 : Password Decryption Exception : ", e.getMessage());
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("Inside FirstUseServiceImpl : forgotPassword_v2 : Password Decryption Exception : ", e.getMessage());
			throw new HubCitiException(e.getMessage());
		}
		LOG.info("Exit FirstUseServiceImpl : forgotPassword_v2");
		return responseXML;
	}
	public String getUserLogin_V2(String xml) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getUserLogin_V2");

		String response = null;

		try {
			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

			if (objUserDetails.getUserName() == null || objUserDetails.getUserName().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "User Name/Email id Required");
			} else if (objUserDetails.getPassword() == null || objUserDetails.getPassword().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Password Required");
			} else if (objUserDetails.getHubCitiKey() == null || "".equals(objUserDetails.getHubCitiKey())) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "HubCiti Key required");
			} else if (objUserDetails.getDeviceId() == null || objUserDetails.getDeviceId().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Device ID Required");
			} else if ("".equals(Utility.checkNull(objUserDetails.getPlatform()))) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PLATFORM_EMPTY);
			} else {

				UserDetails userDetails = new UserDetails();
				userDetails = firstUseDao.getUserLogin_V2(objUserDetails);

				if (userDetails != null) {

					if (userDetails.getLoginFlag() == 0 && userDetails.getPassword().equals("1")) {
						response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "User Name/Email Not Found");
					} else if (userDetails.getLoginFlag() == -2) {
						response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "Temporary password Expired");
					} else if (userDetails.getPassword().equals("0")) {
						response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "Invalid Password");
					} else {

						userDetails.setPassword(null);
						userDetails.setLoginFlag(null);
						userDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
						userDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
						response = XstreamParserHelper.produceXmlFromObject(userDetails);
					}

				} else {
					response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception in getUserLogin_V2", e);
			throw new HubCitiException(e.getMessage());
		}

		LOG.info("Exit FirstUseServiceImpl : getUserLogin_V2");
		return response;
	}
	public String userRegistration_V2(String xml) throws HubCitiException {

		LOG.info("Inside FirstUseServiceImpl : userRegistration_V2");

		String response = null;
		List<LoginFlowDetails> listLoginFlow = null;

		try{
			final XstreamParserHelper streamHelper = new XstreamParserHelper();
			final UserDetails objUserDetails = (UserDetails) streamHelper.parseXmlToObject(xml);

			if (objUserDetails.getUserName() == null || objUserDetails.getUserName().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "User Name Required");
			} else if (objUserDetails.getPassword() == null || objUserDetails.getPassword().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Password Required");
			} else if (objUserDetails.getDeviceId() == null || objUserDetails.getDeviceId().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "Device ID Required");
			} else if (objUserDetails.getAppVersion() == null || objUserDetails.getAppVersion().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "App Version Required");
			} else if (objUserDetails.getHubCitiKey() == null || objUserDetails.getHubCitiKey().equals("")) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, "HubCiti Key Required");
			} else {
				UserDetails userRegResponse = new UserDetails();
				userRegResponse = firstUseDao.userRegistration_V2(objUserDetails);

				if (userRegResponse != null) {
					if (userRegResponse.getLoginFlag() != null && userRegResponse.getLoginFlag() < 0) {
						if (userRegResponse.getLoginFlag() == -1) {

							response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "User Name Exists Already");
						} else if (userRegResponse.getLoginFlag() == -2) {
							response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "EmailID Exists Already");
						} else if (userRegResponse.getLoginFlag() == -3) {
							response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "User Name and EmailID Exists Already");
						} else if (userRegResponse.getLoginFlag() == -4) {
							response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, "Invalid HubCiti Key");
						} else {
							response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
						}
					} else {
						LoginFlowDetails objLoginFlow = new LoginFlowDetails();
						objLoginFlow.setPageType("Registration Page");
						objLoginFlow.setHubCitiKey(objUserDetails.getHubCitiKey());
						listLoginFlow = firstUseDao.userLoginFlow(objLoginFlow);

						if (listLoginFlow != null && !listLoginFlow.isEmpty() && listLoginFlow.get(0) != null) {
							userRegResponse.setRegSuccessUI(listLoginFlow.get(0));
						}

						userRegResponse.setResponseCode(HubCitiConstants.SUCCESSCODE);
						userRegResponse.setResponseText(HubCitiConstants.SUCCESSTEXT);
						response = XstreamParserHelper.produceXmlFromObject(userRegResponse);
					}
				} else {
					response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, HubCitiConstants.FAILURETEXT);
				}
			}
		}catch (Exception e) {
			LOG.error("Exception in userRegistration_V2", e);
			throw new HubCitiException(e.getMessage());
		}
		LOG.info("Exit FirstUseServiceImpl : userRegistration_V2");
		return response;
	}

	public String resetPassword(String inputXML) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : resetPassword");

		String response = null;
		String encryptedpwd = null;

		EncryptDecryptPwd encryptDecryptPwd;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final UserRegistrationInfo objUserRegistInfo = (UserRegistrationInfo) streamHelper.parseXmlToObject(inputXML);

		try {
			if (null == objUserRegistInfo.getUserId()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.USERID_EMPTY);
			} else if (null == objUserRegistInfo.getHubCitiKey()) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.HUBCITKEY_EMPTY);
			} else if ("".equals(Utility.checkNull(objUserRegistInfo.getPassword()))) {
				response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.PASSWORD_EMPTY);
			} else {
				encryptDecryptPwd = new EncryptDecryptPwd();
				encryptedpwd = encryptDecryptPwd.encrypt(objUserRegistInfo.getPassword());
				if ("".equals(Utility.checkNull(encryptedpwd))) {
					return Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
				}

				response = firstUseDao.resetPassword(objUserRegistInfo.getUserId(), encryptedpwd, objUserRegistInfo.getHubCitiKey());
				if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
					response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.PASSWORDUPDATED);
				} else {
					response = Utility.formResponseXml(HubCitiConstants.FAILURECODE, response);
				}
			}

		} catch (NoSuchElementException e) {
			LOG.error("Exception in Encryption of password resetPassword", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchPaddingException e) {
			LOG.error("Exception in Encryption of user password resetPassword", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeyException e) {
			LOG.error("user password Encryption Exception resetPassword", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidAlgorithmParameterException e) {
			LOG.error("Exception while Encryption  resetPassword", e);
			throw new HubCitiException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			LOG.error("Exception during Encryption resetPassword", e);
			throw new HubCitiException(e.getMessage());
		} catch (IllegalBlockSizeException e) {
			LOG.error("Exception in user password Encryption resetPassword", e);
			throw new HubCitiException(e.getMessage());
		} catch (BadPaddingException e) {
			LOG.error("Exception in Encryption resetPassword", e);
			throw new HubCitiException(e.getMessage());
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Exception in Encryption resetPassword", e);
			throw new HubCitiException(e.getMessage());
		}

		LOG.info("Exit FirstUseServiceImpl : resetPassword");
		return response;
	}

	/**
	 * VERSION 2.0 
	 * 
	 * Method to update user information
	 * 
	 * @param JSON
	 * @return string JSON.
	 * @throws HubCitiException
	 */
	public String updateUserInfo_json_V2(String json) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : updateUserInfo_json_V2");

		String response = null;

		try {

			final Gson gson = new Gson();
			final UserDetails objUserDetailsReq = (UserDetails) gson.fromJson(json, UserDetails.class);

			if (objUserDetailsReq.getUserId() == null || objUserDetailsReq.getUserId().equals("")||objUserDetailsReq.getHubCitiId()==null) {

				response = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {
				response = firstUseDao.updateUserInfo_V2(objUserDetailsReq);

				if (null != response && HubCitiConstants.SUCCESSTEXT.equalsIgnoreCase(response)) {

					response = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);

				} else {
					response = Utility.validRespJSON(HubCitiConstants.FAILURECODE, "EmailId already Exist!");
				}
			}
		} catch (Exception exception) {
			LOG.error("Error Inside FirstUseServiceImpl : updateUserInfo_json_V2 " + exception.getMessage());
			throw new HubCitiException(exception.getMessage());
		}
		LOG.info("Exit FirstUseServiceImpl : updateUserInfo_json_V2");
		return response;
	}


	/**
	 * VERSION 1.0 
	 * 
	 * 
	 * 
	 * @param hubCitiId
	 * @return string JSON containing slide images or FAILURE
	 * @throws HubCitiException
	 */



	public String getTutorialImages(String hubCitikey)throws HubCitiException{
		LOG.info("Inside FirstUseServiceImpl : getTutorialImages");
		String responseJSON=null;

		try {
			final Gson gson=new Gson();

			if(null==hubCitikey||"".equals(hubCitikey)) {
				responseJSON=Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				TutorialMediaResultSet tutorials=firstUseDao.getTutorialImages(hubCitikey);
				if(null == tutorials.getLogoPath() && null == tutorials.getTutorialMediaResultSets()){
					responseJSON=Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				} else {
					responseJSON=gson.toJson(tutorials);
				}
			}
		} catch(Exception exception) {
			LOG.error("Error Inside FirstUseServiceImpl : getTutorialImages " + exception.getMessage());
			throw new HubCitiException(exception.getMessage());
		}

		LOG.info("Exit FirstUseServiceImpl : getTutorialImages");
		return responseJSON;
	}


	/**
	 * ServiceImpl method is to display HubCiti side Menu Display information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu JSON details.
	 */

	public String hubSideMenuDisplay(String strJSON) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : hubSideMenuDisplay");

		String strJsonResponse = null;
		CategoryDetails objNewsCatDetail = null;

		try {

			final Gson gson = new Gson();
			final Menu objMenu =  (Menu) gson.fromJson(strJSON, Menu.class);

			objNewsCatDetail = firstUseDao.hubSideMenuDisplay (objMenu);
			if ((null != objNewsCatDetail.getListMainCat() && !objNewsCatDetail.getListMainCat().isEmpty())
					|| (null != objNewsCatDetail.getMenuList() && !objNewsCatDetail.getMenuList().isEmpty())) {

				List<MainCategory> mainCatList = new ArrayList<MainCategory>();
				List<CategoryDetails> finalCategoryList = new ArrayList<CategoryDetails>();
				if (null != objNewsCatDetail.getListMainCat() && !objNewsCatDetail.getListMainCat().isEmpty()) {
					mainCatList = BandHelper.getGrpByNewsSubCategoryList(objNewsCatDetail.getListMainCat());

					CategoryDetails objNewsCategory =  new CategoryDetails();
					objNewsCategory.setListMainCat(mainCatList);
					finalCategoryList.add(objNewsCategory);
				}

				if (null != objNewsCatDetail.getMenuList() && !objNewsCatDetail.getMenuList().isEmpty()) {
					CategoryDetails objMenuFunctnty =  new CategoryDetails();
					objMenuFunctnty.setMenuList(objNewsCatDetail.getMenuList());
					finalCategoryList.add(objMenuFunctnty);
				}

				objNewsCatDetail = new CategoryDetails();
				objNewsCatDetail.setListCatDetails(finalCategoryList);

				objNewsCatDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objNewsCatDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strJsonResponse = gson.toJson(objNewsCatDetail);

			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getNewsSideNavigationMenu :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandServiceImpl : getNewsSideNavigationMenu ");
		return strJsonResponse;
	}



	/**
	 * Method to fetch FAQ's categories. Also converts input XML to object and
	 * response object to XML.
	 * 
	 * @param inputXml
	 * @return XML.
	 * @throws HubCitiException.
	 */
	public String getFAQsCategoriesJson(Integer hubCitiId, Integer lowerLimit, Integer userId, String platform, String searchKey) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : getFAQsCategoriesJson");

		String strJsonResponse = null;
		Categories categories = null;
		final Gson gson = new Gson();

		try {

			if (null == hubCitiId || null == userId) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				categories = firstUseDao.getFAQsCategories(hubCitiId, lowerLimit, userId, searchKey);

				if (null != categories && null != categories.getFaqCatList() && !categories.getFaqCatList().isEmpty()) {
					categories.setResponseCode(HubCitiConstants.SUCCESSCODE);
					categories.setResponseText(HubCitiConstants.SUCCESSTEXT);
				} else {
					categories.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					categories.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}
				strJsonResponse = gson.toJson(categories);
			}
		}  catch (HubCitiException e) {
			LOG.error("Inside  FirstUseServiceImpl : getFAQsCategoriesJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit FirstUseServiceImpl : getFAQsCategoriesJson");
		return strJsonResponse;
	}


	/**
	 * ServiceImpl method is to display HubCiti side Menu Display information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu JSON details.
	 */

	public String hubSideMenuDisplay_V2(String strJSON) throws HubCitiException {
		LOG.info("Inside FirstUseServiceImpl : hubSideMenuDisplay_V2");

		String strJsonResponse = null;
		CategoryDetails objNewsCatDetail = null;

		try {

			final Gson gson = new Gson();
			final Menu objMenu =  (Menu) gson.fromJson(strJSON, Menu.class);

			objNewsCatDetail = firstUseDao.hubSideMenuDisplay_V2 (objMenu);
			if ((null != objNewsCatDetail.getListMainCat() && !objNewsCatDetail.getListMainCat().isEmpty())
					|| (null != objNewsCatDetail.getMenuList() && !objNewsCatDetail.getMenuList().isEmpty())) {

				List<MainCategory> mainCatList = new ArrayList<MainCategory>();
				List<CategoryDetails> finalCategoryList = new ArrayList<CategoryDetails>();
				if (null != objNewsCatDetail.getListMainCat() && !objNewsCatDetail.getListMainCat().isEmpty()) {
					mainCatList = BandHelper.getGrpByNewsSubCategoryList(objNewsCatDetail.getListMainCat());

					CategoryDetails objNewsCategory =  new CategoryDetails();
					objNewsCategory.setListMainCat(mainCatList);
					finalCategoryList.add(objNewsCategory);
				}

				if (null != objNewsCatDetail.getMenuList() && !objNewsCatDetail.getMenuList().isEmpty()) {
					CategoryDetails objMenuFunctnty =  new CategoryDetails();
					objMenuFunctnty.setMenuList(objNewsCatDetail.getMenuList());
					finalCategoryList.add(objMenuFunctnty);
				}

				objNewsCatDetail = new CategoryDetails();
				objNewsCatDetail.setListCatDetails(finalCategoryList);

				objNewsCatDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objNewsCatDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strJsonResponse = gson.toJson(objNewsCatDetail);

			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getNewsSideNavigationMenu :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandServiceImpl : getNewsSideNavigationMenu ");
		return strJsonResponse;
	}
}
