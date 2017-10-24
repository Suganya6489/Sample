package com.hubciti.firstuse.dao;

import java.util.ArrayList;
import java.util.List;

import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.AppConfiguration;
import com.hubciti.common.pojos.AuthenticateUser;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.City;
import com.hubciti.common.pojos.CountryCode;
import com.hubciti.common.pojos.Data;
import com.hubciti.common.pojos.FAQDetails;
import com.hubciti.common.pojos.LoginFlowDetails;
import com.hubciti.common.pojos.Menu;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.TutorialMediaResultSet;
import com.hubciti.common.pojos.UpdateUserInfo;
import com.hubciti.common.pojos.UserDetails;
import com.hubciti.common.pojos.UserRegistrationInfo;
import com.hubciti.common.pojos.UserSettings;

public interface FirstUseDao {

	/**
	 * DAO method to login.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of {@link UserDetails}.
	 * @throws HubCitiException
	 */
	public UserDetails userLogin(UserDetails userDetailsReq) throws HubCitiException;


	/**
	 * DAO method to login.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of {@link UserDetails}.
	 * @throws HubCitiException
	 */
	public UserDetails getUserLogin(UserDetails userDetailsReq) throws HubCitiException;


	/**
	 * DAO method to register new user.
	 * 
	 * @param instance
	 *            of UserDetails.
	 * @return instance of {@link UserDetails}.
	 * @throws HubCitiException
	 */
	public UserDetails userRegistration(UserDetails userRegDetails) throws HubCitiException;

	/**
	 * DAO method to get Custom UI for login flow .
	 * 
	 * @param objLoginFlow
	 * @return
	 * @throws HubCitiException
	 */
	public List<LoginFlowDetails> userLoginFlow(LoginFlowDetails objLoginFlow) throws HubCitiException;

	/**
	 * DAO methos to get User Prefered Categories.
	 * 
	 * @param objUserDetails
	 * @return instance of {@link CategoryDetails}
	 * @throws HubCitiException
	 */
	public CategoryDetails getUserPreferedCategories(UserDetails objUserDetails) throws HubCitiException;

	/**
	 * DAO methos to set User Prefered Categories.
	 * 
	 * @param objUserDetails
	 * @return instance of {@link CategoryDetails}
	 * @throws HubCitiException
	 */
	public String setUserPreferedCategories(CategoryDetails objCategoryDetails) throws HubCitiException;

	/**
	 * DAO method to get user information.
	 * 
	 * @param userDetailsReq
	 * @return List containing user details {@link UserDetails}
	 * @throws HubCitiException
	 */
	public UserDetails getUserInfo(UserDetails userDetailsReq) throws HubCitiException;

	/**
	 * DAO method to update user information.
	 * 
	 * @param objUserDetailsReq
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 */
	public String updateUserInfo(UserDetails objUserDetailsReq) throws HubCitiException;

	/**
	 * DAO method is to display main menu details
	 * 
	 * @param instance
	 *            of Menu.
	 * @return List of Menu details.
	 */
	public Menu menuDisplay(Menu objMenu) throws HubCitiException;

	/**
	 * DAO method fetches user details for sending password to user email id.
	 * 
	 * @param strUserName
	 *            request parameter
	 * @return User Details
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public AuthenticateUser forgotPassword(String strUserName, Integer hubCitiId) throws HubCitiException;

	/**
	 * DAO method Fetching App Configuration details.
	 * 
	 * @return ArrayList.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */

	public ArrayList<AppConfiguration> getAppConfig(String configType) throws HubCitiException;

	/**
	 * The DAO method to saves user password.
	 * 
	 * @param userID
	 *            request parameter.
	 * @param password
	 *            request parameter.
	 * @return returns the an XML depending on SUCCESS or ERROR.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String changePassword(Integer userID, String password) throws HubCitiException;

	/**
	 * The DAO method to get started images information from the database.
	 * 
	 * @throws getStartedImageInfo
	 *             If any exception occurs ScanSeeException will be thrown.
	 * @return TutorialMediaResultSet object.
	 */
	public TutorialMediaResultSet getStartedImageInfo() throws HubCitiException;

	public Integer getMainMenuId(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * The DAO method for fetching user information from the database for the
	 * given userID.
	 * 
	 * @param userID
	 *            - for which user information need to be fetched.
	 * @throws ScanSeeException
	 *             If any exception occures ScanSeeException will be thrown.
	 * @return UserInformation object.
	 */
	public UpdateUserInfo fetchUserInfo(int userId, boolean isCellFireRequest) throws HubCitiException;

	/**
	 * This is a DAO Method To get department Name, ID, menuItemType Name, ID.
	 * 
	 * @param xml
	 * @return
	 * @throws HubCitiException
	 */
	public List<MenuItem> getDepartmentAndMenuTypeDetails(MenuItem objMenuItem) throws HubCitiException;

	/**
	 * The DAO method to update database to enable or diable push notification
	 * alert for a specidfied user or to update wish list radius
	 * 
	 * @param userSettings
	 *            instance of UserSettings pojo class.
	 * @return success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String setUserSettings(UserSettings userSettings) throws HubCitiException;

	/**
	 * The DAO method to update database to enable or diable push notification
	 * alert for a specidfied user or to update wish list radius
	 * 
	 * @param userSettings
	 *            instance of UserSettings pojo class.
	 * @return success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	List<UserSettings> getUserSettings(AuthenticateUser objAuthenticateUser) throws HubCitiException;

	/**
	 * The DAO method for saving user email id state.
	 * 
	 * @param userID
	 *            , emailId - for which user information need to be fetched.
	 * @return success or failure.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	String saveUserEmailId(Integer userID, String emailId) throws HubCitiException;

	/**
	 * The DAO method for fetching user information from the database for the
	 * given userID.
	 * 
	 * @param userID
	 *            - for which user information need to be fetched.
	 * @throws ScanSeeException
	 *             If any exception occures ScanSeeException will be thrown.
	 * @return UserInformation object.
	 */
	ArrayList<CountryCode> fetchUnivStates(Integer hubCitiId) throws HubCitiException;

	/**
	 * The DAO method for fetching user information from the database for the
	 * given userID.
	 * 
	 * @param userID
	 *            - for which user information need to be fetched.
	 * @throws ScanSeeException
	 *             If any exception occures ScanSeeException will be thrown.
	 * @return UserInformation object.
	 */
	ArrayList<CountryCode> fetchUniversities(Integer hubCitiId, String stateAbv) throws HubCitiException;

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
	UserDetails hubCitiUserRangeCheck(UserDetails objUserDetailsRequest) throws HubCitiException;

	/**
	 * DAO method is to get particular FAQ Details.
	 * 
	 * @param FAQDetails
	 *            object as input parameter.
	 * @return FAQDetails object containing particular FAQ Details.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	FAQDetails fetchFaqDetails(FAQDetails objFaqDetails) throws HubCitiException;

	/**
	 * DAO method to get All FAQ Display or based on searchkey.
	 * 
	 * @param FAQDetails
	 *            object as input parameter.
	 * @return XML containing response with list FAQ Display.
	 * @throws HubCitiException
	 *             is thrown.
	 */
	FAQDetails fetchFaqDisplay(FAQDetails objFaqDetails) throws HubCitiException;

	/**
	 * Method to fetch FAQ's categories.
	 * 
	 * @param hubCitiId
	 * @param lowerLimit
	 * @return {@link Categories} object.
	 * @throws HubCitiException
	 */
	Categories getFAQsCategories(Integer hubCitiId, Integer lowerLimit, Integer userId,String searchKey) throws HubCitiException;

	/**
	 * DAO method to get city preferences.
	 * 
	 * @param instance of UserDetails.
	 * @return instance of City List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	City getCityPreferences(UserDetails objUserDetails) throws HubCitiException;

	/**
	 * DAO method to set city preferences.
	 * 
	 * @param instance of UserDetails.
	 * @return String containing SUCCESS or FAILURE.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String setCityPreferences(UserDetails objUserDetails) throws HubCitiException;

	/**
	 * DAO method to get user city preferences.
	 * 
	 * @param instance of UserDetails.
	 * @return instance of City List.
	 * @throws HubCitiException throws if exception occurs.
	 */
	City getUserCityPreferences(UserDetails objUserDetails) throws HubCitiException;

	/**
	 * DAO method  to get FAQ Setting bit flag.
	 * 
	 * @return instance of UserDetails.
	 * @throws HubCitiException throws if exception occurs.
	 */
	UserDetails getFaqSetting(UserDetails objUserDetails) throws HubCitiException;

	/**
	 * DAO method for Register device id and token id for push notification.
	 * 
	 * @param userRegInfo instance of UserRegistrationInfo.
	 * @return String XML containing SUCCESS or FAILURE response.
	 * @throws HubCitiException throws if exception occurs.
	 */

	String registerPushNotification(UserRegistrationInfo userRegInfo) throws HubCitiException;

	CategoryDetails getUserPreferedLocationCategories(Integer userId, Integer hubCitiId) throws HubCitiException;

	String updateUserPreferedLocationCategories(CategoryDetails objCategoryDetails) throws HubCitiException;

	/**
	 * DAO Method to find pushNotify Back Ground details.
	 * 
	 * @param hubCitiId.
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	Data pushNotifyBackGrd(Integer hubCitiId) throws HubCitiException;


	/**
	 * DAO Method to find pushNotify Back Ground details.
	 * 
	 * @param hubCitiId.
	 * @return XML containing the List of pushNotify details.
	 * @throws HubCitiException throws if exception occurs.
	 */
	Data webPushNotifyBackGrd(Integer hubCitiId) throws HubCitiException;
	/**
	 * DAO method is to update device details.
	 * 
	 * @param instance
	 *            of Menu.
	 * @return String as success.
	 * @throws HubCitiException throws if exception occurs.
	 */
	String updateDeviceDetailsForMenu(Menu objMenu) throws HubCitiException;


	/**
	 * DAO method is to display main menu details
	 * 
	 * @param instance
	 *            of Menu.
	 * @return List of Menu details.
	 */

	/**
	 * DAO method is to get HubCiti MenuDisplay information.
	 * 
	 * @param Menu
	 *            type of containing Menu details
	 * @return Menu type of Menu details.
	 */
	Menu HubMenuDisplay(Menu objMenu) throws HubCitiException;

	/**
	 * DAO method is to display main menu details
	 * 
	 * @param instance
	 *            of Menu.
	 * @return List of Menu details.
	 */
	Menu menuDisplayForHubRegion(Menu objMenu) throws HubCitiException;

	/**
	 * Web service for HubCiti IOS Push Notification user Instance Badge Value  reset.
	 * 
	 * @param hubCitiId
	 * @param deviceId
	 * @param userId
	 * @return if the status is 0
	 *             then success
	 *             else
	 *             		unable to reset badge value.
	 */
	public String resetBadge(Integer hubCitiId, String deviceId, Integer userId) throws HubCitiException;


	/**
	 * VERSION 2.0
	 * 
	 * DAO method fetches user details for sending password to user email Id.
	 * 
	 * @param strUserName
	 *            request parameter
	 * @return User Details
	 * @throws HubCitiException
	 *             is thrown.
	 */
	public AuthenticateUser forgotPassword_v2(String strUserName, Integer hubCitiId,String encryptedpassword) throws HubCitiException;


	public UserDetails getUserLogin_V2(UserDetails userDetailsReq) throws HubCitiException;

	public UserDetails userRegistration_V2(UserDetails userRegDetails) throws HubCitiException;

	public String resetPassword(Integer userID, String password,String hubcitiKey) throws HubCitiException;

	/**
	 * 
	 * VERSION 2.0
	 * DAO method to update user information.
	 * 
	 * @param objUserDetailsReq
	 * @return SUCCESS or FAILURE response.
	 * @throws HubCitiException
	 */
	public String updateUserInfo_V2(UserDetails objUserDetailsReq) throws HubCitiException;


	/**
	 * VERSION 1.0
	 * 
	 * @return TutorialMediaResultSet
	 * @param HubCitiId
	 * @throws HubCitiException
	 */
	public TutorialMediaResultSet getTutorialImages(String hubCitikey) throws HubCitiException;

	/**
	 * DAO method is to display HubCiti side Menu Display information.
	 * 
	 * @param Menu
	 *            type of containing Menu details
	 * @return Menu type of Menu details.
	 */
	CategoryDetails hubSideMenuDisplay(Menu objMenu) throws HubCitiException;
	
	/**
	 * DAO method is to display HubCiti side Menu Display information.
	 * 
	 * @param Menu
	 *            type of containing Menu details
	 * @return Menu type of Menu details.
	 */
	CategoryDetails hubSideMenuDisplay_V2(Menu objMenu) throws HubCitiException;

}
