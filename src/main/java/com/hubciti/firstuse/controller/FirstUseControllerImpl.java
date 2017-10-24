package com.hubciti.firstuse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.servicefactory.ServiceFactory;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.service.FirstUseService;

/**
 * Class For First Use
 * 
 * @author dhruvanath_mm
 */
public class FirstUseControllerImpl implements FirstUseController {

	/**
	 * Getting the logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FirstUseControllerImpl.class);

	/**
	 * debugger flag for logging.
	 */
	private static final boolean ISDEBUGENABLED = LOG.isDebugEnabled();

	/**
	 * The FirstUseControllerImpl Constructor.
	 */
	public FirstUseControllerImpl() {

	}

	/**
	 * Method to provide login service.
	 * 
	 * @param XML
	 *            containing user credentials.
	 * @return String XML containing login status with user details.
	 */
	public String userLogin(String xml) {
		final String methodName = "userLogin";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.userLogin(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		return responseXml;
	}

	/**
	 * Method to provide login service.
	 * 
	 * @param XML
	 *            containing user credentials.
	 * @return String XML containing login status with user details.
	 */
	public String getUserLogin(String xml) {
		final String methodName = "getUserLogin";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.getUserLogin(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * Method for User Sign Up.
	 * 
	 * @param XML
	 *            containing registration details.
	 * @return String XML containing userId.
	 */
	public String userRegistration(String xml) {
		final String methodName = "userRegistration";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.userRegistration(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		return responseXml;
	}

	/**
	 * Method to get Configuration in user login/signUp flow.
	 * 
	 * @param XML
	 *            containing hubCitiId and pageType.
	 * @return String XML containing user screen configuration.
	 */
	public String userLoginFlow(String xml) {
		final String methodName = "userLoginFlow";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {

			responseXml = firstUseService.userLoginFlow(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		return responseXml;
	}

	/**
	 * Method to get user prefered categories.
	 * 
	 * @param XML
	 *            containing userId.
	 * @return String XML containing user prefered categories details.
	 */
	public String getUserPreferedCategories(String xml) {
		final String methodName = "getUserPreferedCategories";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.getUserPreferedCategories(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		return responseXml;
	}

	/**
	 * Method to update user prefered categories.
	 * 
	 * @param XML
	 *            containing userId and categorys ids.
	 * @return String XML containing SUCCESS or FAILURE response.
	 */
	public String setUserPreferedCategories(String xml) {
		final String methodName = "setUserPreferedCategories";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.setUserPreferedCategories(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		return responseXml;
	}

	/**
	 * Method to get user information.
	 * 
	 * @param xml
	 *            containing user ID
	 * @return String xml containing user information
	 */
	public String getUserInfo(String xml) {
		final String methodName = "getUserInfo";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.getUserInfo(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		return responseXml;
	}

	/**
	 * Method to get user information.
	 * 
	 * @param json
	 *            containing user ID
	 * @return String xml containing user information
	 */
	public String getUserInfo_json(String json) {
		final String methodName = "getUserInfo_json";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved JSON :" + json);
		}
		String responseJSON = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseJSON = firstUseService.getUserInfo_json(json);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.ERROROCCURRED + " FirstUseControllerImpl getUserInfo_json " + exception.toString());
			responseJSON = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseJSON);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseJSON;
	}

	/**
	 * Method to update user information
	 * 
	 * @param xml
	 *            containing user details
	 * @return String xnl containing SUCCESS or FAILURE
	 */
	public String updateUserInfo(String xml) {
		final String methodName = "updateUserInfo";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.updateUserInfo(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		return responseXml;
	}

	/**
	 * Method to update user information
	 * 
	 * @param JSON
	 *            containing user details
	 * @return String JSON containing SUCCESS or FAILURE
	 */
	public String updateUserInfo_json(String json) {
		final String methodName = "updateUserInfo_json";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved JSON :" + json);
		}
		String responseJSON = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseJSON = firstUseService.updateUserInfo_json(json);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseJSON = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseJSON);
		}
		return responseJSON;
	}

	/**
	 * ControllerImpl method is to display main menu details
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu xml details.
	 */
	public String menuDisplay(String xml) {
		LOG.info("Inside FirstUseControllerImpl : menuDisplay");
		String strResponseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXml = firstUseService.menuDisplay(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : menuDisplay : " + exception.toString());
			strResponseXml = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXml;
	}

	/**
	 * ControllerImpl method is to send password to user EmailId.
	 * 
	 * @param userName
	 *            of String type.
	 * @return XML containing password in the response.
	 */
	public String forgotPassword(String strUsername, Integer hubCitiId) {
		LOG.info("Inside FirstUseControllerImpl : forgotPassword");
		String strResponseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXml = firstUseService.forgotPassword(strUsername, hubCitiId);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : forgotPassword : " + exception.toString());
			strResponseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXml;
	}

	/**
	 * ControllerImpl method is to change User Password.Method Type:POST.
	 * 
	 * @param inputXML
	 *            in the request
	 * @return the XML in response based on Success or failure code.
	 */

	public String changePassword(String inputXML) {
		LOG.info("Inside FirstUseControllerImpl : changePassword");
		String strResponseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXml = firstUseService.changePassword(inputXML);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : changePassword : " + exception.toString());
			strResponseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXml;
	}

	/**
	 * ControllerImpl method is get started images information. Method Type:GET.
	 * 
	 * @param userId
	 *            , as request parameter.
	 * @return XML containing started images information.
	 */
	public String getStartedImageInfo(Integer iUserId) {
		LOG.info("Inside FirstUseControllerImpl : getStartedImageInfo");
		String strResponseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXML = firstUseService.getStartedImageInfo(iUserId);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : getStartedImageInfo : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This is a RestEasy WebService Method is get mainMenuId.
	 * 
	 * @param xml
	 * @return xml containing mainmenuId.
	 */
	public String getMainMenuId(String xml) {
		LOG.info("Inside FirstUseControllerImpl : getMainMenuID");
		String strResponseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXML = firstUseService.getMainMenuId(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : getMainMenuID : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * This is a RestEasy WebService Method is get mainMenuId.
	 * 
	 * @param xml
	 * @return xml containing mainmenuId.
	 */
	public String getNewMainMenuId(String xml) {
		LOG.info("Inside FirstUseControllerImpl : getNewMainMenuId");
		String strResponseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXML = firstUseService.getMainMenuId(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : getNewMainMenuId : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	// New Web service for getMainmenuID using GET method
	/**
	 * 
	 * @param iUserId
	 * @param ihubCitiId
	 * @param mItemId
	 * @param bottomBtnId
	 * @param platform
	 * @return Response message
	 */
	public String getMainMenuId(Integer iUserId, Integer ihubCitiId, Integer mItemId, Integer bottomBtnId, String platform) {
		LOG.info("Inside FirstUseControllerImpl : getMainMenuID");
		String strResponseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXML = firstUseService.getMainMenuId(iUserId, ihubCitiId, mItemId, bottomBtnId, platform);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : getMainMenuID : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;

	}

	/**
	 * ControllerImpl method To get department Name, ID, menuItemType Name, ID.
	 * 
	 * @param xml
	 * @return xml containing mainmenuId.
	 */
	public String getDepartmentAndMenuTypeDetails(String xml) {
		LOG.info("Inside FirstUseControllerImpl : getDepartmentAndMenuTypeDetails ");
		String strResponseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXML = firstUseService.getDepartmentAndMenuTypeDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : getDepartmentAndMenuTypeDetails : " + exception.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXML;
	}

	/**
	 * ControllerImpl method for enabling or disabling PushNotification Alert
	 * and to set wish list radius
	 * 
	 * @param xml
	 *            as request
	 * @return XML containing the success or failure in the response.
	 */
	public String setUserSettings(String xml) {
		LOG.info("Inside FirstUseControllerImpl : setUserSettings");
		String responseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXML = firstUseService.setUserSettings(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : setUserSettings : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * ControllerImpl method to get PushNotification Alert on/off and to get
	 * wish list radius
	 * 
	 * @param userId
	 *            as request
	 * @return XML containing push notification alert on/off and wish list
	 *         radius.
	 */
	public String getUserSettings(String xml) {
		LOG.info("Inside FirstUseControllerImpl : getUserSettings");
		String responseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXML = firstUseService.getUserSettings(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : getUserSettings : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	/**
	 * ControllerImpl method for saving user email id state.
	 * 
	 * @param userId
	 *            , emailId as request
	 * @return XML in response based on Success or failure code.
	 */
	public String saveUserEmailId(Integer userId, String emailId) {
		LOG.info("Inside FirstUseControllerImpl : saveUserEmailId");
		String responseXML = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXML = firstUseService.saveUserEmailId(userId, emailId);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : saveUserEmailId : " + exception.toString());
			responseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return responseXML;
	}

	public String fetchUniversityStates(Integer hubCitiId) {
		final String methodName = "fetchUniversityStates in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		if (ISDEBUGENABLED) {
			LOG.debug("No input parameter");
		}
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.fetchUnivStates(hubCitiId);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	public String fetchUniversities(Integer hubCitiId, String stateAbv) {
		final String methodName = "fetchUniversities in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		if (ISDEBUGENABLED) {
			LOG.debug(" input parameter stateAbv:" + stateAbv);
		}
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.fetchUniversities(hubCitiId, stateAbv);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * Controller method to check is user is outside hubCiti or not by taking
	 * latitude, longitude or postal code. Method Type:POST
	 * 
	 * @param xml
	 *            as request containing userId, hubCitiId, latitude and
	 *            longitude or postal code.
	 * @return xml containing is user out of hubCiti and postal code if user
	 *         outside hubCiti.
	 */
	public String hubCitiUserRangeCheck(String xml) {
		final String methodName = "hubCitiUserRangeCheck in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		if (ISDEBUGENABLED) {
			LOG.debug(" input parameter stateAbv:" + xml);
		}
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.hubCitiUserRangeCheck(xml);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * ControllerImpl method to get particular FAQ Details. Method Type: POST
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing particular FAQ Details in the response.
	 * @throws HubCitiException
	 *             If any exception occures HubCitiException will be thrown.
	 */
	public String fetchFaqDetails(String xml) {
		LOG.info("Inside FirstUseControllerImpl : fetchFaqDetails");
		String strResponseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXml = firstUseService.fetchFaqDetails(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : fetchFaqDetails : " + exception.toString());
			strResponseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXml;
	}

	/**
	 * ControllerImpl method to get All FAQ Display or based on searchkey.
	 * Method Type: POST
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing response with list FAQ Display.
	 * @throws HubCitiException
	 *             If any exception occures HubCitiException will be thrown.
	 */
	public String fetchFaqDisplay(String xml) {
		LOG.info("Inside FirstUseControllerImpl : fetchFaqDisplay");
		String strResponseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXml = firstUseService.fetchFaqDisplay(xml);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : fetchFaqDisplay : " + exception.toString());
			strResponseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseXml;
	}

	/**
	 * Controller method to get categories for FAQ's Method Type:POST
	 * 
	 * @param hubCitiId
	 *            , lowerLimit.
	 * @return xml containing categories.
	 */
	public String getFAQsCategories(Integer hubCitiId, Integer lowerLimit, Integer userId, String platform, String searchKey) {
		final String methodName = "getFAQsCategories() in RestEasy Layer";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (LOG.isDebugEnabled()) {
			LOG.info("Input seacrh key " + searchKey);
		}
		String responseXml = null;
		if (ISDEBUGENABLED) {
			LOG.debug(" input parameter hubCitiId:" + hubCitiId + ", lowerLimit:" + lowerLimit);
		}
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.getFAQsCategories(hubCitiId, lowerLimit, userId, platform, searchKey);
		} catch (HubCitiException exception) {
			LOG.error(HubCitiConstants.EXCEPTIONOCCURED + methodName, exception);
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * ControllerImpl method to get City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing City Preferences details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getCityPreferences(String xml) {
		LOG.info("Inside FirstUseControllerImpl : getCityPreferences");

		String responseXml = null;

		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();

		try {
			responseXml = firstUseService.getCityPreferences(xml);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : getCityPreferences : " + e.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseControllerImpl : getCityPreferences");
		return responseXml;
	}

	/**
	 * ControllerImpl method to update City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String setCityPreferences(String xml) {
		LOG.info("Inside FirstUseControllerImpl : setCityPreferences");

		String responseXml = null;

		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();

		try {
			responseXml = firstUseService.setCityPreferences(xml);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : setCityPreferences : " + e.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseControllerImpl : setCityPreferences");
		return responseXml;
	}

	/**
	 * ControllerImpl method to get updated City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing City Preferences details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getUserCityPreferences(String xml) {
		LOG.info("Inside FirstUseControllerImpl : getUserCityPreferences");

		String responseXml = null;

		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();

		try {
			xml = xml.replace("<catName>", "<catName><![CDATA[");
			xml = xml.replace("</catName>", "]]></catName>");
			responseXml = firstUseService.getUserCityPreferences(xml);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : getUpdatedCityPreferences : " + e.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseControllerImpl : getUserCityPreferences");
		return responseXml;
	}

	/**
	 * ControllerImpl method get FAQ Setting bit flag.
	 * 
	 * @param XML
	 * @return String XML containing get FAQ Setting bit flag.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFaqSetting(String xml) {
		LOG.info("Inside FirstUseControllerImpl : getFaqSetting");

		String responseXml = null;

		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();

		try {
			responseXml = firstUseService.getFaqSetting(xml);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : getFaqSetting : " + e.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseControllerImpl : getFaqSetting");
		return responseXml;
	}

	/**
	 * ControllerImpl method for Register device id and token id for push
	 * notification. Method Type:POST.
	 * 
	 * @param xml
	 *            as request
	 * @return XML containing the success or failure in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public String registerPushNotification(String xml) {
		LOG.info("Inside FirstUseControllerImpl : registerPushNotification");

		String strResponseXML = null;

		final FirstUseService firstUse = ServiceFactory.getFirstUseService();
		try {
			strResponseXML = firstUse.registerPushNotification(xml);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : registerPushNotification : " + e.toString());
			strResponseXML = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseControllerImpl : registerPushNotification");
		return strResponseXML;
	}

	public String getUserPreferedLocationCategories(Integer userId, Integer hubCitiId) {
		final String methodName = "getUserPreferedLocationCategories";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.getUserPreferedLocationCategories(userId, hubCitiId);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	public String updateUserPreferedLocationCategories(String xml) {
		final String methodName = "updateUserPreferedLocationCategories";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.updateUserPreferedLocationCategories(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	/**
	 * ControllerImpl Method to find pushNotify Back Ground details.
	 * 
	 * @param hubCitiId
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String pushNotifyBackGrd(Integer hubCitiId) {
		LOG.info("Inside FirstUseControllerImpl : pushNotifyBackGrd");

		String responseXml = null;

		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.pushNotifyBackGrd(hubCitiId);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : pushNotifyBackGrd : " + e.toString());
			responseXml = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseControllerImpl : pushNotifyBackGrd");
		return responseXml;
	}

	/**
	 * ControllerImpl Method to find web pushNotify Back Ground details.
	 * 
	 * @param hubCitiId
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String webPushNotifyBackGrd(Integer hubCitiId) {
		LOG.info("Inside FirstUseControllerImpl : webPushNotifyBackGrd");

		String strResponseJson = null;

		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.webPushNotifyBackGrd(hubCitiId);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : webPushNotifyBackGrd : " + e.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit FirstUseControllerImpl : webPushNotifyBackGrd");
		return strResponseJson;
	}

	/**
	 * ControllerImpl method is to cache HubCiti MenuDisplay information.
	 * 
	 * @param strJSON
	 *            type of containing Menu details
	 * @return JSON type of Menu details.
	 */
	public String HubMenuDisplay(String strJSON) {
		LOG.info("Inside FirstUseControllerImpl : HubMenuDisplay");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.HubMenuDisplay(strJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : HubMenuDisplay : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * ControllerImpl method is remove HubCiti MenuDisplay information from
	 * cached. Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	public String rmHubCitiMenuDisplay() {
		LOG.info("Inside GovQAControllerImpl : rmHubCitiMenuDisplay");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.rmHubCitiMenuDisplay();
		} catch (HubCitiException exception) {
			LOG.error("Inside GovQAControllerImpl : rmHubCitiMenuDisplay : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * ControllerImpl method is to update device details
	 * 
	 * @param strJson
	 *            type of containing device details
	 * @return String as json.
	 */
	public String updateDeviceDetailsForMenu(String strJson) {
		LOG.info("Inside FirstUseControllerImpl : updateDeviceDetailsForMenu");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.updateDeviceDetailsForMenu(strJson);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : updateDeviceDetailsForMenu : " + exception.toString());
			strResponseJson = Utility.generateErrRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * ControllerImpl method is to remove Hub Region MenuDisplay information
	 * from cached. Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	public String rmHubRegionMenuDisplay() {
		LOG.info("Inside GovQAControllerImpl : rmHubRegionMenuDisplay");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.rmHubRegionMenuDisplay();
		} catch (HubCitiException exception) {
			LOG.error("Inside GovQAControllerImpl : rmHubRegionMenuDisplay : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * ControllerImpl method is to cache Hub Region MenuDisplay information.
	 * 
	 * @param strMenuJSON
	 *            type of containing Menu details
	 * @return String type of Menu JSON details.
	 */
	public String menuDisplayForHubRegion(String strMenuJSON) {
		LOG.info("Inside FirstUseControllerImpl : menuDisplayForHubRegion");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.menuDisplayForHubRegion(strMenuJSON);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : menuDisplayForHubRegion : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * ControllerImpl method is remove Hub Region MenuDisplay information from
	 * cached. Method Type:GET.
	 * 
	 * @return XML containing success or failure information.
	 */
	public String rmNewHubRegionMenuDisplay() {
		LOG.info("Inside GovQAControllerImpl : rmNewHubRegionMenuDisplay");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.rmNewHubRegionMenuDisplay();
		} catch (HubCitiException exception) {
			LOG.error("Inside GovQAControllerImpl : rmNewHubRegionMenuDisplay : " + exception.toString());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		return strResponseJson;
	}

	/**
	 * 
	 * Web service for HubCiti IOS Push Notification user Instance Badge Value
	 * reset.
	 * 
	 * @param hubCitiId
	 * @param deviceId
	 * @param userId
	 * @return jsonResponse
	 */
	public String resetBadge(Integer hubCitiId, String deviceId, Integer userId) {
		LOG.info(HubCitiConstants.METHODSTART + "resetBadge");
		String response = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			response = firstUseService.resetBadge(hubCitiId, deviceId, userId);
		} catch (HubCitiException e) {
			LOG.error(HubCitiConstants.ERROROCCURRED + "resetBadge" + e.getMessage());
			response = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Response returned is " + response);
		}
		LOG.info(HubCitiConstants.METHODEND + "resetBadge");
		return response;
	}

	/**
	 * 
	 * VERSION 2.0
	 * 
	 * ControllerImpl method is to send temporary password to user EmailId.
	 * 
	 * @param userName
	 *            of String type.
	 * @return XML containing password in the response.
	 */
	public String forgotPassword_v2(String requestXML) {
		LOG.info("Inside FirstUseControllerImpl : forgotPassword_v2");
		String strResponseJSON = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJSON = firstUseService.forgotPassword_v2(requestXML);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : forgotPassword_v2 : " + exception.toString());
			strResponseJSON = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit FirstUseControllerImpl : forgotPassword_v2");
		return strResponseJSON;
	}

	public String getUserLogin_V2(String xml) {
		final String methodName = "getUserLogin_V2";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved XML :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.getUserLogin_V2(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseXml;
	}

	public String userRegistration_V2(String xml) {
		final String methodName = "userRegistration_V2";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved input :" + xml);
		}
		String responseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseXml = firstUseService.userRegistration_V2(xml);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseXml);
		}
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		return responseXml;
	}


	public String resetPassword(String inputXML) {
		LOG.info("Inside FirstUseControllerImpl : resetPassword");
		String strResponseXml = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseXml = firstUseService.resetPassword(inputXML);
		} catch (HubCitiException exception) {
			LOG.error("Inside FirstUseControllerImpl : resetPassword : " + exception.toString());
			strResponseXml = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit FirstUseControllerImpl : resetPassword");
		return strResponseXml;
	}

	/**
	 * VERSION 2.0
	 * 
	 * Method to update user information
	 * 
	 * @param JSON
	 *            containing user details
	 * @return String JSON containing SUCCESS or FAILURE
	 */
	public String updateUserInfo_json_V2(String json) {
		final String methodName = "updateUserInfo_json_V2";
		LOG.info(HubCitiConstants.METHODSTART + methodName);
		if (ISDEBUGENABLED) {
			LOG.debug("Recieved JSON :" + json);
		}
		String responseJSON = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			responseJSON = firstUseService.updateUserInfo_json_V2(json);
		} catch (HubCitiException exception) {
			LOG.error(exception.toString());
			responseJSON = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		if (ISDEBUGENABLED) {
			LOG.debug("Returned Response is:" + responseJSON);
		}
		LOG.info(HubCitiConstants.METHODEND + methodName);
		return responseJSON;
	}


	/**
	 * VERSION 1.0
	 * 
	 * @param hubCitikey
	 * 
	 * @return String JSON containing slide images or FAILURE
	 */
	public String getTutorialImages(String hubCitikey){
		LOG.info("Inside FirstUseControllerImpl : getTutorialImages");
		String responseJSON=null;
		final FirstUseService firstUseService=ServiceFactory.getFirstUseService();
		try{
			responseJSON=firstUseService.getTutorialImages(hubCitikey);
		}catch(HubCitiException e){
			LOG.error("Inside FirstUseControllerImpl : getTutorialImages : " + e.getMessage());
			responseJSON=Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit FirstUseControllerImpl : hubSideMenuDisplay");
		return responseJSON;
	}


	/**
	 * ControllerImpl method is to display HubCiti side Menu Display information.
	 * 
	 * @param strJSON
	 *            type of containing Menu details
	 * @return JSON type of Menu details.
	 */
	public String hubSideMenuDisplay(String strJSON) {
		LOG.info("Inside FirstUseControllerImpl : hubSideMenuDisplay");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.hubSideMenuDisplay(strJSON);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : hubSideMenuDisplay : " + e.getMessage());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit FirstUseControllerImpl : hubSideMenuDisplay");
		return strResponseJson;
	}


	/**
	 * ControllerImpl method to get categories for FAQ's
	 * 
	 * @param hubCitiId,lowerLimit,platform,searchKey.
	 * @return JSON type of categories.
	 */
	public String getFAQsCategoriesJson(Integer hubCitiId, Integer lowerLimit, Integer userId, String platform, String searchKey) {
		LOG.info("Inside FirstUseControllerImpl : getFAQsCategoriesJson");

		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.getFAQsCategoriesJson(hubCitiId, lowerLimit, userId, platform, searchKey);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : getFAQsCategoriesJson : " + e.getMessage());
			strResponseJson = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit FirstUseControllerImpl : getFAQsCategoriesJson");
		return strResponseJson;
	}


	/**
	 * ControllerImpl method is to display HubCiti side Menu Display information.
	 * 
	 * @param strJSON
	 *            type of containing Menu details
	 * @return JSON type of Menu details.
	 */
	public String hubSideMenuDisplay_V2(String strJSON) {
		LOG.info("Inside FirstUseControllerImpl : hubSideMenuDisplay_V2");
		String strResponseJson = null;
		final FirstUseService firstUseService = ServiceFactory.getFirstUseService();
		try {
			strResponseJson = firstUseService.hubSideMenuDisplay_V2(strJSON);
		} catch (HubCitiException e) {
			LOG.error("Inside FirstUseControllerImpl : hubSideMenuDisplay_V2 : " + e.getMessage());
			strResponseJson = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit FirstUseControllerImpl : hubSideMenuDisplay_V2");
		return strResponseJson;
	}
}
