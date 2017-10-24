package com.hubciti.firstuse.service;

import com.hubciti.common.exception.HubCitiException;

public interface FirstUseService {

	/**
	 * Method for user login. Calls the XStreams helper class methods for
	 * parsing xml.
	 * 
	 * @param xml
	 *            containing login credentials.
	 * @return String xml containing user details.
	 * @throws HubCitiException.
	 */
	public String userLogin(String xml) throws HubCitiException;


	/**
	 * Method for user login. Calls the XStreams helper class methods for
	 * parsing xml.
	 * 
	 * @param xml
	 *            containing login credentials.
	 * @return String xml containing user details.
	 * @throws HubCitiException.
	 */
	public String getUserLogin(String xml) throws HubCitiException;

	/**
	 * Method for User Sign Up. Calls the XStreams helper class methods for
	 * parsing XML.
	 * 
	 * @param xml
	 *            contining user credentials.
	 * @return String xml containing userId.
	 * @throws HubCitiException.
	 */
	public String userRegistration(String xml) throws HubCitiException;

	/**
	 * Method to get Configuration in user login/signUp flow.
	 * 
	 * @param xml
	 * @return String xml.
	 * @throws HubCitiException.
	 */
	public String userLoginFlow(String xml) throws HubCitiException;

	/**
	 * Method to get user prefered categories.
	 * 
	 * @param xml
	 * @return string xml.
	 * @throws HubCitiException
	 */
	public String getUserPreferedCategories(String xml) throws HubCitiException;

	/**
	 * Method to update user prefered categories.
	 * 
	 * @param xml
	 * @return
	 * @throws HubCitiException
	 */
	public String setUserPreferedCategories(String xml) throws HubCitiException;

	/**
	 * Method to get user information.
	 * 
	 * @param xml
	 * @return string xml.
	 * @throws HubCitiException
	 */
	public String getUserInfo(String xml) throws HubCitiException;

	/**
	 * Method to get user information.
	 * 
	 * @param json
	 * @return string json.
	 * @throws HubCitiException
	 */
	public String getUserInfo_json(String json) throws HubCitiException;

	/**
	 * Method to update user information
	 * 
	 * @param xml
	 * @return string xml.
	 * @throws HubCitiException
	 */
	public String updateUserInfo(String xml) throws HubCitiException;

	/**
	 * Method to update user information
	 * 
	 * @param JSON
	 * @return string JSON.
	 * @throws HubCitiException
	 */
	public String updateUserInfo_json(String json) throws HubCitiException;


	/**
	 * Service method is to display main menu details
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu xml details.
	 */
	public String menuDisplay(String xml) throws HubCitiException;

	/**
	 * Service method is to display main menu details
	 * 
	 * @param userName
	 *            of String type.
	 * @return XML containing password in the response.
	 */
	public String forgotPassword(String strUsername, Integer hubCitiId) throws HubCitiException;

	/**
	 * Service method is takes the user new password and encrypt it.
	 * 
	 * @param inputXML
	 *            in the User Request.
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 */
	public String changePassword(String inputXML) throws HubCitiException;

	/**
	 * Service method to get started images information.
	 * 
	 * @param userId
	 *            as request parameter.
	 * @return XML containing user information in the response.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	public String getStartedImageInfo(Integer userId) throws HubCitiException;

	/**
	 * Service method to get main menu id.
	 * 
	 * @param xml
	 * @return xml containing mainMenuId
	 * @throws HubCitiException.
	 */
	public String getMainMenuId(String xml) throws HubCitiException;

	/**
	 * 
	 * @param iUserId 
	 * @param ihubCitiId
	 * @param mItemId
	 * @param bottomBtnId
	 * @param platform
	 * @return response
	 * @throws HubCitiException.
	 */
	public String getMainMenuId(Integer iUserId, Integer ihubCitiId,
			Integer mItemId, Integer bottomBtnId, String platform) throws HubCitiException;
	/**
	 * This is a Service Method To get department Name, ID, menuItemType Name,
	 * ID.
	 * 
	 * @param xml
	 * @return
	 * @throws HubCitiException
	 */
	public String getDepartmentAndMenuTypeDetails(String xml) throws HubCitiException;

	/**
	 * Service method to enable or disable push notification alerts. Calls
	 * XStream helper class methods for parsing
	 * 
	 * @param xml
	 *            - the request xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String setUserSettings(String xml) throws HubCitiException;

	/**
	 * Service method get push notification alert on/off and wish list radius.
	 * Calls XStream helper class methods for parsing
	 * 
	 * @param xml
	 *            - the request xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String getUserSettings(String xml) throws HubCitiException;

	/**
	 * service method for saving user email id state.
	 * 
	 * @param userID
	 *            , emailId for which user information need to be fetched.
	 * @return XML containing user information in the response.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String saveUserEmailId(Integer userID, String emailId) throws HubCitiException;

	/**
	 * The Service method for Saving User Information. This method checks for
	 * mandatory fields, if any mandatory fields are not available returns
	 * Insufficient Data error message else calls the DAO method.
	 * 
	 * @param xml
	 *            containing user information.
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             If any exception occures HubCitiException will be thrown.
	 */
	String fetchUnivStates(Integer hubCitiId) throws HubCitiException;

	/**
	 * The Service method for Saving User Information. This method checks for
	 * mandatory fields, if any mandatory fields are not available returns
	 * Insufficient Data error message else calls the DAO method.
	 * 
	 * @param xml
	 *            containing user information.
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             If any exception occures HubCitiException will be thrown.
	 */
	String fetchUniversities(Integer hubCitiId, String stateAbv) throws HubCitiException;

	/**
	 * Method to check is user is outside hubCiti or not by taking latitude,
	 * longitude or postal code.
	 * 
	 * @param xml
	 *            as request containing userId, hubCitiId, latitude and
	 *            longitude or postal code.
	 * @return xml containing is user out of hubCiti and postal code if user
	 *         outside hubCiti.
	 */
	String hubCitiUserRangeCheck(String xml) throws HubCitiException;

	/**
	 * Service method to get particular FAQ Details.
	 * 
	 * @param xml
	 *            containing FAQ information.
	 * @return XML containing particular FAQ Details in the response.
	 * @throws HubCitiException
	 *             If any exception occures HubCitiException will be thrown.
	 */
	String fetchFaqDetails(String xml) throws HubCitiException;

	/**
	 * Service method to get All FAQ Display or based on searchkey. Method Type:
	 * POST
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing response with list FAQ Display.
	 * @throws HubCitiException
	 *             If any exception occures HubCitiException will be thrown.
	 */
	String fetchFaqDisplay(String xml) throws HubCitiException;

	/**
	 * Method to fetch FAQ's categories. Also converts response object to XML.
	 * 
	 * @param hubCitiId
	 *            , lowerLimit
	 * @return XML.
	 * @throws HubCitiException.
	 */
	String getFAQsCategories(Integer hubCitiId, Integer lowerLimit, Integer userId,String platform,String searchKey) throws HubCitiException;

	/**
	 * Service method to get City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing City Preferences details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String getCityPreferences(String xml) throws HubCitiException;

	/**
	 * Service method to update City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing SUCCESS or FAILURE response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String setCityPreferences(String xml) throws HubCitiException;

	/**
	 * Service method to get user City Preferences.
	 * 
	 * @param XML
	 * @return String XML containing City Preferences details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String getUserCityPreferences(String xml) throws HubCitiException;

	/**
	 * Service method  to get FAQ Setting bit flag.
	 * 
	 * @param XML
	 * @return String XML containing  to get FAQ Setting bit flag.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String getFaqSetting(String xml) throws HubCitiException;

	/**
	 * Service method for Register device id and token id for push notification.
	 * 
	 * @param XML
	 * @return String XML containing SUCCESS or FAILURE response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String registerPushNotification(String inputXml) throws HubCitiException;

	String getUserPreferedLocationCategories(Integer userId, Integer hubCitiId) throws HubCitiException;

	String updateUserPreferedLocationCategories(String xml) throws HubCitiException;

	/**
	 * Service Method to find pushNotify Back Ground details.
	 * 
	 * @param hubCitiId.
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String pushNotifyBackGrd(Integer hubCitiId) throws HubCitiException;


	/**
	 * Service Method to find web pushNotify Back Ground details.
	 * 
	 * @param hubCitiId.
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String webPushNotifyBackGrd(Integer hubCitiId) throws HubCitiException;
	/**
	 * Service method is to cache HubCiti MenuDisplay information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu JSON details.
	 */
	String HubMenuDisplay(String strJson) throws HubCitiException;

	/**
	 * Service  method to clear the cache for HubCiti MenuDisplay information.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String rmHubCitiMenuDisplay() throws HubCitiException;

	/**
	 * Service method is to update device details
	 * 
	 * @param String type of containing device details
	 * @return String as json.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String updateDeviceDetailsForMenu(String json) throws HubCitiException;

	/**
	 * Service method is to remove Hub Region MenuDisplay information from cached. 
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String rmHubRegionMenuDisplay() throws HubCitiException;


	/**
	 * Service method is to cache Hub Region MenuDisplay information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 * @return String type of Menu JSON details.
	 */
	String menuDisplayForHubRegion(String strMenuJSON) throws HubCitiException;


	/**
	 * Service method to remove Hub Region MenuDisplay information from cached.
	 * 
	 * @return JSON containing success or failure information.
	 * @throws HubCitiException
	 *             If any exception occurs HubCitiException will be thrown.
	 */
	String rmNewHubRegionMenuDisplay() throws HubCitiException;

	/**
	 * Web service for HubCiti IOS Push Notification user Instance Badge Value  reset.
	 * Business Logic: Checking only Required Information.
	 *   
	 * @param hubCitiId
	 * @param deviceId
	 * @param userId
	 * @return
	 */
	public String resetBadge(Integer hubCitiId, String deviceId, Integer userId) throws HubCitiException;


	/**
	 * 
	 * Version 2.0
	 * 
	 * Service method is to display main menu details
	 * 
	 * @param userName
	 *            of String type.
	 * @return XML containing password in the response.
	 */
	public String forgotPassword_v2(String forgotPassword_v2) throws HubCitiException;

	public String getUserLogin_V2(String xml) throws HubCitiException;

	public String userRegistration_V2(String xml) throws HubCitiException;

	public String resetPassword(String inputXML) throws HubCitiException;

	/**
	 * VERSION 2.0
	 * Method to update user information
	 * 
	 * @param JSON
	 * @return string JSON.
	 * @throws HubCitiException
	 */
	public String updateUserInfo_json_V2(String json) throws HubCitiException;

	/**
	 * Version 1.0
	 * 
	 * @param hubCitiId
	 * @return String
	 * @throws HubCitiException
	 */

	public String getTutorialImages(String hubCitikey)throws HubCitiException;

	/**
	 * Service method is to display HubCiti side Menu Display information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 */
	String hubSideMenuDisplay(String strJson) throws HubCitiException;
	
	/**
	 * Service method to get categories for FAQ's 
	 * 
	 * @param hubCitiId, lowerLimit, platform, searchKey.
	 */
	String getFAQsCategoriesJson(Integer hubCitiId, Integer lowerLimit, Integer userId, String platform, String searchKey) throws HubCitiException;
	
	/**
	 * Service method is to display HubCiti side Menu Display information.
	 * 
	 * @param String
	 *            type of containing Menu details
	 */
	String hubSideMenuDisplay_V2(String strJson) throws HubCitiException;
	
}
