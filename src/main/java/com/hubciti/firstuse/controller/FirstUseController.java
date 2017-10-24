package com.hubciti.firstuse.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.hubciti.common.constants.HubCitiURLPath;

/**
 * Class for First Use
 * 
 * @author dhruvanath_mm
 */
@Path(HubCitiURLPath.FIRSTUSE)
public interface FirstUseController {

	/**
	 * Method to provide login service.
	 * 
	 * @param XML
	 *            containing user credentials.
	 * @return String XML containing login status with user details.
	 */
	@POST
	@Path(HubCitiURLPath.LOGIN)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String userLogin(String xml);

	/**
	 * Method to provide login service implemented for improving performance.
	 * 
	 * @param XML
	 *            containing user credentials.
	 * @return String XML containing login status with user details.
	 */
	@POST
	@Path(HubCitiURLPath.GET_USER_LOGIN)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String getUserLogin(String xml);

	/**
	 * Method for User Sign Up.
	 * 
	 * @param XML
	 *            containing registration details.
	 * @return String XML containing userId.
	 */
	@POST
	@Path(HubCitiURLPath.SIGNUP)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String userRegistration(String xml);

	/**
	 * Method to get Configuration in user login/signUp flow.
	 * 
	 * @param XML
	 *            containing hubCitiId and pageType.
	 * @return String XML containing user screen configuration.
	 */
	@POST
	@Path(HubCitiURLPath.LOGINFLOW)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String userLoginFlow(String xml);

	/**
	 * Method to get user prefered categories.
	 * 
	 * @param XML
	 *            containing userId.
	 * @return String XML containing user prefered categories details.
	 */
	@POST
	@Path(HubCitiURLPath.GETUSERCAT)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String getUserPreferedCategories(String xml);

	/**
	 * Method to update user prefered categories.
	 * 
	 * @param XML
	 *            containing userId and categorys ids.
	 * @return String XML containing SUCCESS or FAILURE response.
	 */
	@POST
	@Path(HubCitiURLPath.SETUSERCAT)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String setUserPreferedCategories(String xml);

	/**
	 * Method to get user information.
	 * 
	 * @param xml
	 *            containing user ID
	 * @return String xml containing user information
	 */
	@POST
	@Path(HubCitiURLPath.GETUSERINFO)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String getUserInfo(String xml);

	/**
	 * Method to get user information.
	 * 
	 * @param json
	 *            containing user ID
	 * @return String json containing user information
	 */
	@POST
	@Path(HubCitiURLPath.GETUSERINFO_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserInfo_json(String json);

	/**
	 * Method to update user information
	 * 
	 * @param xml
	 *            containing user details
	 * @return String xnl containing SUCCESS or FAILURE
	 */
	@POST
	@Path(HubCitiURLPath.UPDATEUSERINFO)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String updateUserInfo(String xml);

	/**
	 * Method to update user information
	 * 
	 * @param JSON
	 *            containing user details
	 * @return String JSON containing SUCCESS or FAILURE
	 */
	@POST
	@Path(HubCitiURLPath.UPDATEUSERINFO_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateUserInfo_json(String xml);

	/**
	 * Controller method is to display main menu details. Method Type: POST
	 * 
	 * @param String
	 *            type of containing Menu details.
	 * @return String type of Menu xml details.
	 */
	@POST
	@Path(HubCitiURLPath.MENUDISPLAY)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String menuDisplay(String xml);

	/**
	 * Controller method is to send password to user EmailId. Method Type: GET
	 * 
	 * @param userName
	 *            of String type.
	 * @return XML containing password in the response.
	 */
	@GET
	@Path(HubCitiURLPath.FORGOTPWD)
	@Produces("text/xml")
	public String forgotPassword(@QueryParam("username") String userName, @QueryParam("hubCitiId") Integer hubCitiId);

	/**
	 * Controller method is to change User Password. Method Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return the XML in response based on Success or failure code.
	 */
	@POST
	@Path(HubCitiURLPath.CHANGEPASSWORD)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String changePassword(String inputXML);

	/**
	 * This is a RestEasy WebService Method is get started images information.
	 * Method Type:GET.
	 * 
	 * @param userId
	 *            , as request parameter.
	 * @return XML containing started images information.
	 */
	@GET
	@Path(HubCitiURLPath.GETSTARTEDIMAGES)
	@Produces("text/xml;charset=UTF-8")
	public String getStartedImageInfo(@QueryParam("userId") Integer iUserId);

	/**
	 * This is a RestEasy WebService Method is get mainMenuId.
	 * 
	 * @param xml
	 * @return xml containing mainmenuId.
	 */
	@POST
	@Path(HubCitiURLPath.GETMAINMENUMID)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String getMainMenuId(String xml);

	/**
	 * This is a RestEasy WebService Method is get mainMenuId.
	 * 
	 * @param xml
	 * @return xml containing mainmenuId.
	 */
	@POST
	@Path(HubCitiURLPath.GETUTMAINMENUMID)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String getNewMainMenuId(String xml);

	/**
	 * 
	 * @param iUserId
	 * @param ihubCitiId
	 * @param mItemId
	 * @param bottomBtnId
	 * @param platform
	 * @return Response message
	 */
	@GET
	@Path(HubCitiURLPath.GETMAINMENUMID)
	@Produces("text/xml;charset=UTF-8")
	public String getMainMenuId(@QueryParam("userId") Integer iUserId, @QueryParam("hubCitiId") Integer ihubCitiId,
			@QueryParam("mItemId") Integer mItemId, @QueryParam("bottomBtnId") Integer bottomBtnId, @QueryParam("platform") String platform);
	/**
	 * This is a RestEasy WebService Method To get department Name, ID,
	 * menuItemType Name, ID.
	 * 
	 * @param xml
	 * @return xml containing mainmenuId.
	 */
	@POST
	@Path(HubCitiURLPath.DEPTANDMENUTYPE)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String getDepartmentAndMenuTypeDetails(String xml);

	/**
	 * Controller method for enabling or disabling PushNotification Alert and to
	 * set wish list radius Method Type:POST
	 * 
	 * @param xml
	 *            as request
	 * @return XML containing the success or failure in the response.
	 */
	@POST
	@Path(HubCitiURLPath.SETUSERSETTINGS)
	@Produces("text/xml")
	@Consumes("text/xml")
	String setUserSettings(String xml);

	/**
	 * Controller method for get PushNotification Alert on/off and to get wish
	 * list radius Method Type:POST
	 * 
	 * @param userId
	 *            as request
	 * @return XML containing push notification alert on/off and wish list
	 *         radius.
	 */
	@POST
	@Path(HubCitiURLPath.GETUSERSETTINGS)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getUserSettings(String xml);

	/**
	 * Controller method for saving user email id state. Method Type:GET.
	 * 
	 * @param userId
	 *            for which the email to be stored.
	 * @param emailId
	 *            to be stored for user.
	 * @return the XML in response based on Success or failure code.
	 */
	@GET
	@Path(HubCitiURLPath.SAVEUSEREMID)
	@Produces("text/xml")
	String saveUserEmailId(@QueryParam("userid") Integer userId, @QueryParam("emaiid") String emailId);

	/**
	 * The RestEasy WebService method for saving user email id state.Method
	 * Type:GET.
	 * 
	 * @param userId
	 *            for which the email to be stored.
	 * @param emailId
	 *            to be stored for user.
	 * @return the XML in response based on Success or failure code.
	 */
	@GET
	@Path(HubCitiURLPath.FETCHUNIVSTATES)
	@Produces("text/xml")
	String fetchUniversityStates(@QueryParam("hubCitiId") Integer hubCitiId);

	/**
	 * The RestEasy WebService method for saving user email id state.Method
	 * Type:GET.
	 * 
	 * @param userId
	 *            for which the email to be stored.
	 * @param emailId
	 *            to be stored for user.
	 * @return the XML in response based on Success or failure code.
	 */
	@GET
	@Path(HubCitiURLPath.FETCHUNIVERSITIES)
	@Produces("text/xml;charset=UTF-8")
	String fetchUniversities(@QueryParam("hubCitiId") Integer hubCitiId, @QueryParam("stateab") String stateAbv);

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
	@POST
	@Path(HubCitiURLPath.RANGECHECK)
	@Produces("text/xml")
	@Consumes("text/xml")
	String hubCitiUserRangeCheck(String xml);

	/**
	 * Controller method is to get particular FAQ Details. Method Type: POST
	 * 
	 * @param xml
	 *            as request containing hubCitiId, faqId as input parameter.
	 * @return XML containing particular FAQ Details in the response.
	 */
	@POST
	@Path(HubCitiURLPath.FAQDETAILS)
	@Produces("text/xml")
	@Consumes("text/xml")
	String fetchFaqDetails(String xml);

	/**
	 * Controller method is to get All FAQ Display or based on searchkey. Method
	 * Type: POST
	 * 
	 * @param xml
	 *            as request.
	 * @return XML containing response with list FAQ Display.
	 */
	@POST
	@Path(HubCitiURLPath.FAQDISPLAY)
	@Produces("text/xml")
	@Consumes("text/xml")
	String fetchFaqDisplay(String xml);

	/**
	 * Controller method to get categories for FAQ's Method Method Type : GET.
	 * 
	 * @param hubCitiId
	 *            , lowerLimit.
	 * @return xml containing categories.
	 */
	@GET
	@Path(HubCitiURLPath.FAQCATEGORY)
	@Produces("text/xml")
	String getFAQsCategories(@QueryParam("hubCitiId") Integer hubCitiId, @QueryParam("lowerLimit") Integer lowerLimit,
			@QueryParam("userId") Integer userId, @QueryParam("platform") String platform, @QueryParam("searchKey") String searchKey);


	/**
	 * Controller method to get City Preferences. Method Type : POST.
	 * 
	 * @param XML
	 *            .
	 * @return String XML containing City Preferences details.
	 */
	@POST
	@Path(HubCitiURLPath.GETCITYPREFERENCES)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getCityPreferences(String xml);

	/**
	 * Controller method to update City Preferences. Method Type : POST.
	 * 
	 * @param XML
	 *            .
	 * @return String XML containing SUCCESS or FAILURE response.
	 */
	@POST
	@Path(HubCitiURLPath.UPDATECITYPREFERENCES)
	@Produces("text/xml")
	@Consumes("text/xml")
	String setCityPreferences(String xml);

	/**
	 * Controller method to get updated City Preferences. Method Type : POST.
	 * 
	 * @param XML
	 *            .
	 * @return String XML containing City Preferences details.
	 */
	@POST
	@Path(HubCitiURLPath.GETUSERCITYPREFERENCES)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getUserCityPreferences(String xml);

	/**
	 * Controller method to get FAQ Setting bit flag. Method Type:POST.
	 * 
	 * @param XML
	 *            .
	 * @return String XML containing FAQ Setting bit flag.
	 */
	@POST
	@Path(HubCitiURLPath.FAQSETTING)
	@Produces("text/xml")
	@Consumes("text/xml")
	String getFaqSetting(String xml);


	/**
	 * Controller method for Register device Id and token id for push
	 * notification Method Type:POST.
	 * 
	 * @param xml
	 *            .
	 * @return XML containing the success or failure in the response.
	 */

	@POST
	@Path(HubCitiURLPath.REGISTERPUSHNOTIFICATION)
	@Produces("text/xml")
	@Consumes("text/xml")
	String registerPushNotification(String xml);

	/**
	 * Method to get user preferred location categories.
	 * 
	 * @param XML
	 *            containing userId.
	 * @return String XML containing user preferred categories details.
	 */
	@GET
	@Path(HubCitiURLPath.GETUSERLOCCAT)
	@Produces("text/xml;charset=UTF-8")

	public String getUserPreferedLocationCategories(@QueryParam("userId")Integer userId,  @QueryParam("hubCitiId") Integer hubCitiId);

	/**
	 * Method to get user preferred location categories.
	 * 
	 * @param XML
	 *            containing userId.
	 * @return String XML containing user preferred categories details.
	 */
	@POST
	@Path(HubCitiURLPath.SETUSERLOCCAT)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String updateUserPreferedLocationCategories(String xml);

	/**
	 * Controller Method to find pushNotify Back Ground details. Method Type :
	 * GET.
	 * 
	 * @param hubCitiId
	 * @return XML containing the List of pushNotify details.
	 */
	@GET
	@Path(HubCitiURLPath.BACKGRDPUSHNOTIFICATION)
	@Produces("application/json;charset=UTF-8")
	public String pushNotifyBackGrd(@QueryParam("hubCitiId") Integer hubCitiId);

	/**
	 * Controller Method to find pushNotify Back Ground details. Method Type :
	 * GET.
	 * 
	 * @param hubCitiId
	 * @return XML containing the List of pushNotify details.
	 */
	@GET
	@Path(HubCitiURLPath.WEB_BACKGRDPUSHNOTIFICATION)
	@Produces("application/json;charset=UTF-8")
	public String webPushNotifyBackGrd(@QueryParam("hubCitiId") Integer hubCitiId);

	/**
	 * Controller method is to cache HubCiti MenuDisplay information.
	 * Method Type: POST
	 * 
	 * @param String
	 *            type of containing Menu details.
	 * @return String type of Menu details.
	 */
	@POST
	@Path(HubCitiURLPath.HUBMENUDISPLAY)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String HubMenuDisplay(String strJson);


	/**
	 * Controller method is remove HubCiti MenuDisplay information from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_HUB_MENUDISPLAY)
	@Produces(MediaType.APPLICATION_JSON)
	public String rmHubCitiMenuDisplay();


	/**
	 * Controller method is to update device details for menu. 
	 * Method Type: POST
	 * 
	 * @param String
	 *            type of containing device details.
	 * @return String type of response as json.
	 */
	@POST
	@Path(HubCitiURLPath.UPDATE_DEVICE_DETAILS)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateDeviceDetailsForMenu(String strJson);


	/**
	 * Controller method is remove Hub Region MenuDisplay information from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_HUBREGION_MENUDISPLAY)
	@Produces(MediaType.APPLICATION_JSON)
	public String rmHubRegionMenuDisplay();


	/**
	 * Controller method is to cache Hub Region MenuDisplay information.
	 * Method Type: POST
	 * 
	 * @param String
	 *            type of containing Menu details.
	 * @return String type of Menu JSON details.
	 */
	@POST
	@Path(HubCitiURLPath.HUBREGION_MENUDISPLAY)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	String menuDisplayForHubRegion(String strJson);


	/**
	 * Controller method is remove Hub Region MenuDisplay information from cached. 
	 * Method Type:GET.
	 * 
	 * @return JSON containing success or failure information.
	 */
	@GET
	@Path(HubCitiURLPath.REMOVE_NEW_HUBREGION_MENUDISPLAY)
	@Produces(MediaType.APPLICATION_JSON)
	String rmNewHubRegionMenuDisplay();
	
	/**
	 * 
	 * Web service for HubCiti IOS Push Notification user Instance Badge Value  reset.
	 * 
	 * Note: UserID used for the future Implementation, currently we are not written based on the userId
	 * 
	 * @param hubCitiId 
	 * @param deviceId
	 * @param userId
	 * @return jsonRespons
	 */
	@GET
	@Path(HubCitiURLPath.RESET_BADGE)
	@Produces("application/json;charset=utf-8")
	String resetBadge(@QueryParam("hubCitiId") Integer hubCitiId,@QueryParam("deviceId") String deviceId,@QueryParam("userId") Integer userId);
	
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
	@POST
	@Path( HubCitiURLPath.V2 + HubCitiURLPath.FORGOTPWD)
	@Produces("text/xml")
	@Consumes("text/xml")
	
	public String forgotPassword_v2(String request);
	/**
	 * VERSION 2.0
	 * 
	 * Method to provide login service implemented for improving performance.
	 * 
	 * @param XML
	 *            containing user credentials.
	 * @return String XML containing login status with user details.
	 */
	@POST
	@Path(HubCitiURLPath.V2+HubCitiURLPath.GET_USER_LOGIN)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String getUserLogin_V2(String xml);
	
	/**
	 * 
	 * VERSION 2.0
	 * 
	 * Method for User Sign Up.
	 * 
	 * @param XML
	 *            containing registration details.
	 * @return String XML containing userId.
	 */
	@POST
	@Path(HubCitiURLPath.V2+HubCitiURLPath.SIGNUP)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String userRegistration_V2(String xml);
	
	/**
	 * VERSION 2.0
	 * Controller method is to change User Password. Method Type:POST
	 * 
	 * @param inputXML
	 *            in the request.
	 * @return the XML in response based on Success or failure code.
	 */
	@POST
	@Path(HubCitiURLPath.V2+HubCitiURLPath.RESETPASSWORD)
	@Produces("text/xml")
	@Consumes("text/xml")
	public String resetPassword(String inputXML);
	
	/**
	 * VERSION 2.0
	 * 
	 * Method to update user information
	 * 
	 * @param JSON
	 *            containing user details
	 * @return String JSON containing SUCCESS or FAILURE
	 */
	@POST
	@Path(HubCitiURLPath.V2+HubCitiURLPath.UPDATEUSERINFO_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String updateUserInfo_json_V2(String xml);
	
	
	/**
     * VERSION 1.0
     * 
     * Method To get Tutorial Images.
     * 
     * @param hubCitikey
     * 
     * @return String JSON containing slide images or FAILURE
     */
	
	@GET
	@Path(HubCitiURLPath.GETFIRSTLOGINTUTORIAL)
	@Produces(MediaType.APPLICATION_JSON)
	public String getTutorialImages(@QueryParam("hubCitiKey") String hubCitikey);
	
	
	/**
	 * Controller method is to display HubCiti side Menu Display information.
	 * Method Type: POST
	 * 
	 * @param String
	 *            type of containing Menu details.
	 */
	@POST
	@Path(HubCitiURLPath.HUB_SIDE_MENUDISPLAY)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	public String hubSideMenuDisplay(String strJson);
	

	/**
	 * Controller method to get categories for FAQ's 
	 * Method Type : GET.
	 * 
	 * @param hubCitiId,lowerLimit,platform,searchKey.
	 */
	@GET
	@Path(HubCitiURLPath.FAQCATEGORY_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	String getFAQsCategoriesJson(@QueryParam("hubCitiId") Integer hubCitiId, @QueryParam("lowerLimit") Integer lowerLimit,
			@QueryParam("userId") Integer userId, @QueryParam("platform") String platform, @QueryParam("searchKey") String searchKey);
	
	/**
	 * Controller method is to display HubCiti side Menu Display information.
	 * Method Type: POST
	 * 
	 * @param String
	 *            type of containing Menu details.
	 */
	@POST
	@Path(HubCitiURLPath.V2+HubCitiURLPath.HUB_SIDE_MENUDISPLAY)
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON+"; charset=utf-8")
	public String hubSideMenuDisplay_V2(String strJson);
}
