package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class FirstUseRestClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {

		// userLogin();
		// userRegistration();
//		 userLoginFlow();
		// getUserPreferedCategories();
		// setUserPreferedCategories();
		// getUserInfo();
		// userLogin();
		// userRegistration();
		// userLoginFlow();
		// getUserPreferedCategories();
		// setUserPreferedCategories();
		// getUserInfo();
		// getUserInfo_json();
		// updateUserInfo();
		// updateUserInfo_json();
		// forgotPassword();
		

		// ChangePassword();
		// getStartedImageInfo();
		// getMainMenuId();
		// getGetMethodMainMenuId();
		// getDepartmentAndMenuTypeDetails();
		// setuserSettings();
		// getUserSettings();
		// saveUserEmailId();
		// fetchUniversityStates();
		// fetchUniversities();
		// hubCitiUserRangeCheck();

		// faqDetails();
		// getFaqDisplay();
		// getFAQsCategories();

		// getCityPreferences();
		// setCityPreferences();
		// getUserCityPreferences();
		// faqSetting();
		// regPushNotification();
		// getUserPreferedLocationCategories();
		// setUserPreferedLocationCategories();
		// pushNotifyBackGrd();

		// webPushNotifyBackGrd();

		
		// deleteHubCitiMenuDisplay();
		// updateDeviceDetailsForMenu();

		// deleteHubRegionMenuDisplay();

		// getUserLogin();

		// faqDetails();
		// getFaqDisplay();
		// getFAQsCategories();

		// getCityPreferences();
		// setCityPreferences();
		// getUserCityPreferences();
		// faqSetting();
		// regPushNotification();
		// getUserPreferedLocationCategories();
		// setUserPreferedLocationCategories();
		// pushNotifyBackGrd();

		// webPushNotifyBackGrd();
		// updateDeviceDetailsForMenu();

		// faqDetails();
		// getFaqDisplay();
		// getFAQsCategories();

		// getCityPreferences();
		// setCityPreferences();
		// getUserCityPreferences();
		// faqSetting();
		// regPushNotification();
		// getUserPreferedLocationCategories();
		// setUserPreferedLocationCategories();
		// pushNotifyBackGrd();

		// webPushNotifyBackGrd();
		// updateDeviceDetailsForMenu();
		// getMenu();
		// getUserLogin();
//		   badgereset();
//		 hubCitiMenuDisplay();
		menuDisplayForHubRegion();
		// menuDisplay();
	}

	public static void userLogin() {
		final String logon = "http://localhost:8080/HubCiti_Cache/firstuse/login";
		// final String logon =
		// "http://localhost:8080/HubCiti1.6/firstuse/login";
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.0/firstuse/login";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.6/firstuse/login";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails><userName><![CDATA[WelcomeScanSeeGuest]]></userName><password><![CDATA[:::We@Love!!ScanSee?{People}]]></password><appVersion>2.1</appVersion><deviceId>23F9568C-0956-4BE1-AF10-09478D12B892</deviceId><hubCitiKey>Tyler19</hubCitiKey><platform>IOS</platform></UserDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("userLogin URI : " + logon);
			System.out.println("Request in userLogin" + inputXML);
			System.out.println("response in userLogin" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getUserLogin() {
		final String logon = "http://localhost:8080/HubCiti2.3.3/firstuse/getuserlogin";
		// final String logon =
		// "http://localhost:8080/HubCiti1.6/firstuse/login";
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.0/firstuse/login";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.6/firstuse/login";
		final ClientRequest request = new ClientRequest(logon);

		/*
		 * String inputXML = "<UserDetails>" + "<userName>ra123456</userName>" +
		 * "<password>123456</password>" + "<appVersion>1.0</appVersion>" +
		 * "<deviceId>F8254055-6ECE-43C6-AE0D-804486714434</deviceId>" +
		 * "<hubCitiKey>tyler19</hubCitiKey>" // +
		 * "<postalCode>573201</postalCode>" // + "<latitude></latitude>" // +
		 * "<longitude></longitude>" + "<platform>IOS</platform>" +
		 * "</UserDetails>";
		 */

		String inputXML = "<UserDetails><userName><![CDATA[tester]]></userName><password><![CDATA[123456]]></password><appVersion>2.1</appVersion><deviceId>E45529B0-19DB-4F59-A341-FE3A0A448001</deviceId><hubCitiKey>Tyler19</hubCitiKey><platform>IOS</platform></UserDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getuserLogin URI : " + logon);
			System.out.println("Request in getuserLogin" + inputXML);
			System.out.println("response in getuserLogin" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void userRegistration() {
		final String logon = "http://66.228.143.28:8080/HubCiti1.6/firstuse/signup";
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/firstuse/signup";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/signup";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" + "<userName>test_service11</userName>" + "<password>123456</password>" + "<appVersion>1.4</appVersion>"
				+ "<deviceId>cfd6cf8b66791302</deviceId>" + "<hubCitiKey>RegionCityExphubCitiApp1143</hubCitiKey>"
				// + "<postalCode>00501</postalCode>"
				// + "<latitude></latitude>"
				// + "<longitude></longitude>"
				+ "</UserDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in userRegistration" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void userLoginFlow() {
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/loginflow";
		final String logon = "http://66.228.143.28:8080/HubCiti2.1/firstuse/loginflow";
		// final String logon =
		// "https://app.scansee.net/HubCiti1.0/firstuse/loginflow";
		final ClientRequest request = new ClientRequest(logon);

		/*
		 * String inputXML = "<LoginFlowDetails>" +
		 * "<pageType>Login Page</pageType>" +
		 * "<hubCitiKey>spanqa.hubciti68</hubCitiKey>" + "</LoginFlowDetails>";
		 */
		String inputXML = "<LoginFlowDetails><pageType><![CDATA[Login  Page]]></pageType><hubCitiKey><![CDATA[spanqa.regionsapp2070]]></hubCitiKey></LoginFlowDetails>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in userLoginFlow" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getUserPreferedCategories() {
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.0/firstuse/getusercat";
		// final String logon =
		// "https://app.scansee.net/HubCiti1.0/firstuse/getusercat";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/getusercat";
		// final String logon =
		// "http://localhost:8080/HubCiti1.7/firstuse/getusercat";
		final String logon = "http://66.228.143.28:8080/HubCiti2.0/firstuse/getusercat";
		// final String logon =
		// "https://app.scansee.net/HubCiti1.0/firstuse/getusercat";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" + "<userId>1982</userId>" + "</UserDetails>";
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("getUserPreferedCategories URI : " + logon);
			System.out.println("Request in getUserPreferedCategories" + inputXML);
			System.out.println("response in getUserPreferedCategories" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void setUserPreferedCategories() {
		final String logon = "http://66.228.143.28:8080/HubCiti1.8/firstuse/setusercat";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<CategoryDetails><userId>10029</userId><catIds></catIds></CategoryDetails>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in setUserPreferedCategories" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getUserInfo() {
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/firstuse/getuserinfo";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/getuserinfo";
		final String logon = "http://localhost:8080/HubCiti2.3.3/firstuse/getuserinfo";
		// final String logon =
		// "https://app.scansee.net/HubCiti1.0/firstuse/getuserinfo";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" /* + "<userId>17075</userId>" */+ "hubCtiId>9257</hubCitiId>" // 77
				+ "</UserDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getUserInfo" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getUserInfo_json() {
		final String logon = "http://localhost:8080/HubCiti2.3.3/firstuse/getuserinfo_json";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/getuserinfo";
		// final String logon =
		// "http://localhost:8080/HubCiti2.3.3/firstuse/getuserinfo";
		// final String logon =
		// "https://app.scansee.net/HubCiti1.0/firstuse/getuserinfo";

		final ClientRequest request = new ClientRequest(logon);

		String inputJSON = "{\"userId\":17075,\"hubCitiId\":9257}";

		request.accept("application/json").body(MediaType.APPLICATION_JSON, inputJSON);

		request.getHeaders();

		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getUserInfoJSON" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void updateUserInfo() {
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/firstuse/updateuserinfo";
		final String logon = "http://199.36.142.83:8080/HubCiti1.3/firstuse/updateuserinfo";
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/firstuse/updateuserinfo";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/updateuserinfo";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" + "<userId>2674</userId>" + "<firstName>Kumar</firstName>" + "<lastName>Dodda</lastName>" + "<postalCode>78655</postalCode>"
				+ "<gender>1</gender>"
				// + "<dob>2014-12-30</dob>"
				+ "<dob>jan 13, 2014</dob>" + "<phoneNum>5555555555</phoneNum>" + "<deviceId>65b7b0bcc5d919f5</deviceId>" + "<email>kumar@spantest.com</email>"
				// // + "<univId>1</univId>"
				// + "<incomeRangeId>0</incomeRangeId>"
				// + "<martialStatusId>0</martialStatusId>"
				// + "<educatonLevelId>0</educatonLevelId>"
				// // + "<latitude></latitude>"
				// // + "<longitude></longitude>"
				// + "<hubCitiId>8</hubCitiId>"
				+ "</UserDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in updateUserInfo" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void updateUserInfo_json() {
		final String logon = "http://localhost:8080/HubCiti2.3.3/firstuse/updateuserinfo_json";
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.3/firstuse/updateuserinfo";
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/firstuse/updateuserinfo";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/updateuserinfo";
		final ClientRequest request = new ClientRequest(logon);

		String inputJSON = "{\"userId\": 28,\"firstName\": \"name1\",\"lastName\": \"ss\",\"postalCode\": \"78729\",\"gender\": \"1\",\"dob\": \"2012-12-30\", \"phoneNum\": \"9966338855\",\"deviceId\": \"abcd1\",\"univId\": \"1\",\"email\": \"aa@span.com\",\"incomeRangeId\": 1,\"martialStatusId\": 1,\"educatonLevelId\": 3}";

		request.accept("application/json").body(MediaType.APPLICATION_JSON, inputJSON);

		request.getHeaders();

		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in updateUserInfo" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void menuDisplay() {
		// final String strMenuDisplaylogon
		// ="https://app.scansee.net/HubCiti1.6/firstuse/menudisplay";
		// final String strMenuDisplaylogon
		// ="http://66.228.143.28:8080/HubCiti_Perf/firstuse/menudisplay";

		final String strMenuDisplaylogon = "http://localhost:9990/HubCiti_Cache/firstuse/menudisplay";
		// final String strMenuDisplaylogon
		// ="http://66.228.143.28:8080/HubCiti2.2/firstuse/menudisplay";

		// final String strMenuDisplaylogon =
		// "http://10.11.202.76:8080/HubCiti1.4/firstuse/menudisplay";
		// final String strMenuDisplaylogon =
		// "http://66.228.143.28:8080/HubCiti2.3.1/firstuse/menudisplay";
		// final String strMenuDisplaylogon =
		// "https://app.scansee.net/HubCiti1.0/firstuse/menudisplay";

		/*
		 * String inputXML = "<Menu>" + "<linkId>0</linkId>"// 39 +
		 * "<userId>6819</userId>" + "<hubCitiId>2070</hubCitiId>" +
		 * "<level>1</level>"//1 + "<platform>IOS</platform>" +
		 * "<deviceId>CA402537-6904-4B9D-B89F-3C63D16B4ECC</deviceId>" +
		 * "<sortOrder>NONE</sortOrder>" + "<osVersion>8.3</osVersion>" +
		 * "<departmentId>0</departmentId>" + "<typeId>0</typeId>" // +
		 * "<cityIds>696</cityIds>"
		 * +"<dateCreated>2015-04-02 05:36:13.8</dateCreated>"
		 * 
		 * 
		 * String inputJSON =
		 * "{\"userId\":\"6819\",\"hubCitiId\":2070,\"level\":1,\"sortOrder\":\"None\",\"departmentId\":0,\"typeId\":0,\"deviceId\":\"000000000000000\",\"linkId\":0,\"platform\":\"Android\",\"dateCreated\":\"\"}"
		 * ; // String inputJSON =
		 * "{\"userId\": \"3\", \"hubCitiId\": \"82\", \"level\": \"1\", \"sortOrder\": \"NONE\", \"departmentId\": \"0\", \"typeId\": \"0\", \"deviceId\": \"DEE458EE-E4A3-44CA-9337-AE3E1902FB4E\", \"osVersion\": \"8.3\", \"linkId\": \"0\", \"platform\": \"IOS\", \"cityIds\": \"696\"}"
		 * ;
		 * 
		 * final ClientRequest request = new ClientRequest(strMenuDisplaylogon);
		 * request.accept("application/json").body(MediaType.APPLICATION_JSON,
		 * inputJSON); request.getHeaders(); try {
		 * 
		 * long strtTime = new Date().getTime(); final String response =
		 * request.postTarget(String.class); long endTime = new
		 * Date().getTime(); System.out.println(endTime - strtTime);
		 * System.out.println("menuDisplay URI : " + strMenuDisplaylogon);
		 * System.out.println("Request in menuDisplay" + inputJSON);
		 * System.out.println("response in menuDisplay" + response); } catch
		 * (Exception e) { System.out.println("Exception :  " + e); } }
		 * 
		 * public static void getMenu() { // final String strMenuDisplaylogon
		 * ="https://app.scansee.net/HubCiti1.6/firstuse/menudisplay"; // final
		 * String strMenuDisplaylogon
		 * ="http://66.228.143.28:8080/HubCiti_Perf/firstuse/menudisplay";
		 * 
		 * final String strMenuDisplaylogon
		 * ="http://localhost:8080/HubCiti2.3.3/firstuse/getmenu"; // final
		 * String strMenuDisplaylogon
		 * ="http://66.228.143.28:8080/HubCiti2.2/firstuse/menudisplay";
		 * 
		 * // final String strMenuDisplaylogon =
		 * "http://10.11.202.76:8080/HubCiti1.4/firstuse/menudisplay"; // final
		 * String strMenuDisplaylogon =
		 * "http://66.228.143.28:8080/HubCiti2.3.1/firstuse/menudisplay"; //
		 * final String strMenuDisplaylogon =
		 * "https://app.scansee.net/HubCiti1.0/firstuse/menudisplay";
		 * 
		 * /* String inputXML = "<Menu>" + "<linkId>0</linkId>"// 39 +
		 * "<userId>6819</userId>" + "<hubCitiId>2070</hubCitiId>" +
		 * "<level>1</level>"//1 + "<platform>IOS</platform>" +
		 * "<deviceId>CA402537-6904-4B9D-B89F-3C63D16B4ECC</deviceId>" +
		 * "<sortOrder>NONE</sortOrder>" + "<osVersion>8.3</osVersion>" +
		 * "<departmentId>0</departmentId>" + "<typeId>0</typeId>" // +
		 * "<cityIds>696</cityIds>"
		 * +"<dateCreated>2015-04-02 05:36:13.8</dateCreated>"
		 */

		// String inputJSON
		// ="{\"departmentId\" :\"0\", \"hubCitiId\":\"52\",\"level\": \"1\", \"linkId\":\"0\",\"osVersion\" : \"9.1\",\"platform\": \"IOS\",\"sortOrder\":\"NONE\",\"typeId\" : \"0\", \"userId\":\"17182\"}";
		// String inputJSON
		// ="{\"userId\": \"2723\",\"departmentId\" :\"0\", \"hubCitiId\":\"82\",\"level\": \"1\", \"linkId\":\"0\",\"osVersion\" : \"9.1\",\"sortOrder\":\"NONE\",\"typeId\" : \"0\",\"cityIds\":\"7372,9994,12511,17062\"}";
		// String inputJSON =
		// "{\"userId\": \"2723\", \"hubCitiId\": \"82\", \"level\": \"1\", \"sortOrder\": \"NONE\", \"departmentId\": \"0\", \"typeId\": \"0\", \"deviceId\": \"5119EA08-8DB2-4A7B-BF32-149DE27E20CB\", \"osVersion\": \"8.3\", \"linkId\": \"0\", \"platform\": \"IOS\", \"cityIds\": \"696\"}";

		String inputJSON = "{\"userId\": \"2723\", \"hubCitiId\": \"82\", \"level\": \"1\", \"sortOrder\": \"NONE\", \"departmentId\": \"0\", \"typeId\": \"0\", \"deviceId\": \"5119EA08-8DB2-4A7B-BF32-149DE27E20CB\", \"osVersion\": \"8.3\", \"linkId\": \"0\", \"platform\": \"IOS\"}";

		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);
		request.accept("application/json").body(MediaType.APPLICATION_JSON, inputJSON);
		request.getHeaders();
		try {

			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("menuDisplay URI : " + strMenuDisplaylogon);
			System.out.println("Request in menuDisplay" + inputJSON);
			System.out.println("response in menuDisplay" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void forgotPassword() {
		// final String strFGotPwd =
		// "http://localhost:8080/HubCiti1.0/firstuse/forgotpwd?username=gunasindhu_s@spanservices.com";
		// final String strFGotPwd =
		// "http://199.36.142.83:8080/HubCiti1.0/firstuse/forgotpwd?username=kumar_dodda@spanservices.com&hubCitiId=44";
		final String strFGotPwd = "http://10.11.202.76:8080/HubCiti1.0/firstuse/forgotpwd?username=kumar_dodda@spanservices.com&hubCitiId=91";
		// final String strFGotPwd =
		// "https://app.scansee.net/HubCiti1.0/firstuse/forgotpwd?username=chaya_bs@spanservices.com&hubCitiId=9";
		final ClientRequest objClRequest = new ClientRequest(strFGotPwd);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in forgotPassword --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void ChangePassword() {
		final String insertUserInfo = "http://10.11.202.76:8080/HubCiti1.0/firstuse/changepassword";
		final ClientRequest request = new ClientRequest(insertUserInfo);
		final String saveUserInfo = "<UserRegistrationInfo><userId>74</userId>" + "<password>12345678</password>" + "</UserRegistrationInfo>";
		request.accept("text/xml").body(MediaType.TEXT_XML, saveUserInfo);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response for ChangePassword-------->" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getStartedImageInfo() {
		// final String strImages =
		// "https://app.scansee.net/HubCiti2.3.1/firstuse/getstartedimages?userId=1";
		final String strImages = "https://app.scansee.net/HubCiti2.3.1/firstuse/getstartedimages?userId=1";
		// final String strImages =
		// "https://app.scansee.net/HubCiti1.6/firstuse/getstartedimages?userId=1";
		// final String strImages =
		// "http://localhost:8080/HubCiti2.3/firstuse/getstartedimages?userId=1";
		final ClientRequest objClRequest = new ClientRequest(strImages);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in getStartedImageInfo --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getMainMenuId() {
		// final String strMenuDisplaylogon =
		// "http://localhost:8080/HubCiti1.0/firstuse/utgetmainmenuid";

		final String strMenuDisplaylogon = "https://app.scansee.net/HubCiti2.0/firstuse/utgetmainmenuid";
		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);

		String inputXML = "<MenuItem>" + "<userId>11181</userId>" + "<hubCitiId>19</hubCitiId>" + "<platform>IOS</platform>" /*
																															 * +
																															 * "<mItemId>700</mItemId>"
																															 */
				+ "<bottomBtnId>291</bottomBtnId>" + "</MenuItem>";
		// request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8",
		// inputXML);
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response for getMainMenuId" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getGetMethodMainMenuId() {
		final String strMenuDisplaylogon = "http://localhost:8080/HubCiti2.1/firstuse/utgetmainmenuid?userId=261&hubCitiId=55&mItemId=700&bottomBtnId=1&platform=IOS";
		// final String strMenuDisplaylogon =
		// "https://app.scansee.net/HubCiti2.0/firstuse/utgetmainmenuid";

		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);

		// request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8",
		// inputXML);

		try {
			final String response = request.getTarget(String.class);
			System.out.println("getMainMenuId URL :  " + strMenuDisplaylogon);
			System.out.println("response for getMainMenuId" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

		/*
		 * final ClientRequest objClRequest = new ClientRequest(strImages); try
		 * { final String strResponse = objClRequest.getTarget(String.class);
		 * System.out.println("response in getStartedImageInfo --> " +
		 * strResponse); } catch (Exception e) {
		 * System.out.println("Exception :  " + e); }
		 */
	}

	public static void getDepartmentAndMenuTypeDetails() {
		// final String strMenuDisplaylogon =
		// "http://199.36.142.83:8080/HubCiti1.0/firstuse/deptandmenutype";
		final String strMenuDisplaylogon = "http://localhost:8080/HubCiti2.1/firstuse/deptandmenutype";
		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);

		String inputXML = "<MenuItem>" + "<userId>2</userId>" + "<hubCitiId>75</hubCitiId>" + "<menuId>1275</menuId><fName>type</fName> " + "</MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("response for menuDisplay" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void setuserSettings() {
		final String insertUserInfo = "http://localhost:8080/HubCiti1.6/firstuse/setusersettings";
		final ClientRequest request = new ClientRequest(insertUserInfo);
		final String saveUserInfo = "<UserSettings>" + "<userId>3</userId>" + "<localeRadius>22</localeRadius>"
		// + "<pushNotify>true</pushNotify>"
				+ "<deviceId>8eeb6587fbab3fcaeac0abaa67d16d13dfd33ddcdc7d</deviceId>" + "</UserSettings>";
		request.accept("text/xml").body(MediaType.TEXT_XML, saveUserInfo);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("response for setuserSettings" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getUserSettings() {
		String URL = "https://app.scansee.net/HubCiti1.6/firstuse/getusersettings";
		ClientRequest request = new ClientRequest(URL);

		final String inputXml = "<AuthenticateUser>" + "<userId>94</userId>" + "<deviceID>9A8E31DA-EFCC-46FE-8D0A-9D5DCE447247</deviceID><hubCitiId>10</hubCitiId>"
				+ "</AuthenticateUser>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("getUserSettings URI : " + URL);
			System.out.println("Request in getUserSettings" + inputXml);
			System.out.println("response in getUserSettings" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void saveUserEmailId() {
		final String strUserEmailID = "http://localhost:8080/HubCiti1.0/firstuse/saveuseremid?userid=3&emaiid=kumar_dodda@span.com";
		final ClientRequest objClRequest = new ClientRequest(strUserEmailID);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in saveUserEmailId --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void fetchUniversityStates() {
		String URL = "http://localhost:8080/HubCiti1.0/firstuse/getunivstates?hubCitiId=25";
		final ClientRequest objClRequest = new ClientRequest(URL);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in saveUserEmailId --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void fetchUniversities() {
		String URL = "http://localhost:8080/HubCiti1.0/firstuse/getuniversities?hubCitiId=25&stateab=NY";
		final ClientRequest objClRequest = new ClientRequest(URL);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in saveUserEmailId --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void hubCitiUserRangeCheck() {
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.0/firstuse/login";
		final String logon = "http://localhost:8080/HubCiti1.0/firstuse/rangecheck";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" + "<userId>369</userId>" + "<hubCitiId>70</hubCitiId>"
		// + "<postalCode>78725</postalCode>"
		// + "<latitude></latitude>"
		// + "<longitude></longitude>"
				+ "</UserDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in userLogin" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void faqDetails() {

		// final String strFaqDetails =
		// "http://199.36.142.83:8080/HubCiti1.6/firstuse/faqdetails";
		final String strFaqDetails = "http://localhost:8080/HubCiti2.2/firstuse/faqdetails";

		ClientRequest request = new ClientRequest(strFaqDetails);

		final String strFaqDetail = "<FAQDetails>" + "<hubCitiId>8</hubCitiId>" + "<faqId>181</faqId>" + "<platform>Android</platform>" + "</FAQDetails>";

		request.accept("text/xml").body(MediaType.TEXT_XML, strFaqDetail);

		request.getHeaders();

		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("faqDetails URI : " + strFaqDetails);
			System.out.println("Request in faqDetails" + strFaqDetail);
			System.out.println("response for faqDetails" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

	}

	public static void getFaqDisplay() {
		/*
		 * final String strFaqDetails =
		 * "http://localhost:8080/HubCiti1.6/firstuse/faqdisplay";
		 */
		final String strFaqDetails = "http://localhost:8080/HubCiti2.2/firstuse/faqdisplay";

		ClientRequest request = new ClientRequest(strFaqDetails);

		/*
		 * final String strFaqDetail = "<FAQDetails>" +
		 * "<hubCitiId>82</hubCitiId>" + "<categoryId>42</categoryId>" +
		 * "<lowerLimit>0</lowerLimit>" // + "<searchKey></searchKey>" // +
		 * "<mainMenuId>10</mainMenuId>" //need to carry from //
		 * CategoryFAQDisplay + "</FAQDetails>";
		 */

		final String strFaqDetail = "<FAQDetails><hubCitiId>1143</hubCitiId><categoryId>12172</categoryId><platform>Android</platform></FAQDetails>";

		request.accept("text/xml").body(MediaType.TEXT_XML, strFaqDetail);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("URL : " + strFaqDetails);
			System.out.println("Request for getFaqDisplay" + strFaqDetail);
			System.out.println("response for getFaqDisplay" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getFAQsCategories() {

		final String faqCat = "http://localhost:8080/HubCiti2.2/firstuse/faqcategory?hubCitiId=8&lowerLimit=0&userId=1&searchKey=";
		// final String faqCat =
		// "https://app.scansee.net/HubCiti1.6/firstuse/faqcategory?hubCitiId=8&lowerLimit=0&userId=1";
		// final String faqCat =
		// "http://199.36.142.83:8080/HubCiti2.0/firstuse/faqcategory?hubCitiId=8&lowerLimit=0";
		// final String faqCat =
		// "http://localhost:8080/HubCiti1.3/firstuse/faqcategory?hubCitiId=136&lowerLimit=0&userId=2831";
		// final String faqCat =
		// "http://10.11.202.76:8080/HubCiti1.2/firstuse/faqcategory?hubCitiId=90";
		final ClientRequest objClRequest = new ClientRequest(faqCat);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in getFAQsCategories --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getCityPreferences() {
		final String logon = "http://localhost:8080/HubCiti1.6/firstuse/getcitypreferences";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" + "<userId>3</userId>" + "<hubCitiId>140</hubCitiId>" + "</UserDetails>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("getCityPreferences URI : " + logon);
			System.out.println("Request in getCityPreferences" + inputXML);
			System.out.println("response in getCityPreferences" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void setCityPreferences() {
		final String logon = "http://localhost:8080/HubCiti1.6/firstuse/updatecitypreferences";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" + "<userId>4416</userId>" + "<hubCitiId>1143</hubCitiId>" + "<cityIds>696,3922,9996</cityIds>" + "</UserDetails>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("setCityPreferences URI : " + logon);
			System.out.println("Request in setCityPreferences : " + inputXML);
			System.out.println("response in setCityPreferences : " + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getUserCityPreferences() {
		// final String logon =
		// "https://app.scansee.net/HubCiti1.6/firstuse/getusercitypref";
		final String logon = "http://localhost:8080/HubCiti2.1/firstuse/getusercitypref";
		final ClientRequest request = new ClientRequest(logon);
		String inputXML = "<UserDetails><hubCitiId>2070</hubCitiId><userId>6819</userId><module>CitiEXP</module><mItemId>39708</mItemId><postalCode><![CDATA[75087]]></postalCode></UserDetails>";
		/*
		 * String inputXML = "<UserDetails>" + "<userId>10054</userId>" +
		 * "<hubCitiId>2070</hubCitiId>"+
		 * 
		 * //"<module>Citi Exp</module>"+ //Find All, Find Single, Citi Exp,
		 * Events "<citiExpId>2070</citiExpId>"+
		 * 
		 * //"<module>Events</module>"+ //Find All, Find Single, Citi Exp,
		 * Events "<mItemId>35757</mItemId>"+
		 * //"<bottomBtnId>2133</bottomBtnId>"+
		 * 
		 * "<module>CitiEXP</module>"+ //Find All, Find Single, CitiExp, Events,
		 * SubMenu, Fund // "<linkId>9081</linkId>"+ // "<typeId>0</typeId>"+ //
		 * "<departmentId>0</departmentId>"+ // "<level>0</level>"+ //
		 * "<catName>Arts & Culture</catName>"+ // "<mItemId>27698</mItemId>"+
		 * //"<bottomBtnId>2133</bottomBtnId>"+ // "<subCatId>0</subCatId>"+
		 * "<radius>100</radius>"+ "<latitude>30.422401</latitude>"+
		 * "<longitude>-97.796676</longitude>"+
		 * 
		 * 
		 * // "<retailId>1046</retailId>"+ //
		 * "<retailLocationId></retailLocationId>"+
		 * 
		 * //"<srchKey>140</srchKey>"+"<catIds>140</catIds>"+ "</UserDetails>";
		 */
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("getUserCityPreferences URI : " + logon);
			System.out.println("Request in getUserCityPreferences" + inputXML);
			System.out.println("response in getUserCityPreferences" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void faqSetting() {
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/firstuse/updateuserinfo";
		final String logon = "http://localhost:8080/HubCiti1.6/firstuse/getfaqsetting";
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/firstuse/updateuserinfo";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/firstuse/updateuserinfo";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<UserDetails>" + "<userId>3</userId>" + "<hubCitiId>90</hubCitiId>" + "</UserDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("faqSetting URI : " + logon);
			System.out.println("Request in faqSetting" + inputXML);
			System.out.println("response in faqSetting" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void regPushNotification() {
		final String logon = "http://localhost:8080/HubCiti1.6/firstuse/registerpushnotify";
		final ClientRequest request = new ClientRequest(logon);

		final String inputXml = "<UserRegistrationInfo>" + "<userTokenID><![CDATA[9d89db670643d29701e719ec08daf80610998a29b8e380f6125fbedb1bd5b1e8]]></userTokenID>"
				+ "<deviceID>F8254055-6ECE-43C6-AE0D-804486714434</deviceID>" + "<platform>IOS</platform>" + "  <hcKey>RegionCityExphubCitiApp1143</hcKey>"

				+ "</UserRegistrationInfo>";
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);

		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("regPushNotification URI : " + logon);
			System.out.println("Request in regPushNotification" + inputXml);
			System.out.println("response in regPushNotification" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getUserPreferedLocationCategories() {
		String URL = "http://localhost:8080/HubCiti1.6/firstuse/getuserloccat?userId=7517&hubCitiId=4173";
		final ClientRequest objClRequest = new ClientRequest(URL);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in getUserPreferedLocationCategories --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void setUserPreferedLocationCategories() {
		final String logon = "http://66.228.143.28:8080/HubCiti1.8/firstuse/setuserloccat";
		// final String logon =
		// "http://localhost:8080/HubCiti1.6/firstuse/setuserloccat";
		final ClientRequest request = new ClientRequest(logon);
		final String inputXml = "<CategoryDetails><userId>10029</userId><hubCitiId>10</hubCitiId></CategoryDetails>";
		/*
		 * final String inputXml = "<CategoryDetails>" + "<userId>7517</userId>"
		 * + "<hubCitiId>4173</hubCitiId>" + "<catIds>1,2,3,11</catIds>" +
		 * "<subCatIds>111,112|NULL|NULL|2,3,4</subCatIds>" +
		 * "</CategoryDetails>";
		 */
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);

		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("Request in setUserPreferedLocationCategories" + inputXml);
			System.out.println("response in setUserPreferedLocationCategories" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void pushNotifyBackGrd() {
		// String URL =
		// "https://app.scansee.net/HubCiti1.6/firstuse/pushnotifybackgrd?hubCitiId=19";
		String URL = "http://66.228.143.28:8080/HubCiti1.6/firstuse/pushnotifybackgrd?hubCitiId=2070";
		// String URL =
		// "http://localhost:8080/HubCiti1.6/firstuse/pushnotifybackgrd?hubCitiId=2070";
		final ClientRequest objClRequest = new ClientRequest(URL);

		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("pushNotifyBackGrd URI : " + URL);
			System.out.println("response in pushNotifyBackGrd" + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void webPushNotifyBackGrd() {
		// String URL =
		// "https://app.scansee.net/HubCiti1.6/firstuse/pushnotifybackgrd?hubCitiId=19";
		String URL = "http://localhost:8080/HubCiti2.3.2/firstuse/webpushnotifybackgrd?hubCitiId=1";
		// String URL =
		// "http://localhost:8080/HubCiti1.6/firstuse/pushnotifybackgrd?hubCitiId=2070";
		final ClientRequest objClRequest = new ClientRequest(URL);

		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("webPushNotifyBackGrd URI : " + URL);
			System.out.println("response in webPushNotifyBackGrd" + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void hubCitiMenuDisplay() {
		// final String strMenuDisplaylogon
		// ="https://app.scansee.net/HubCiti1.6/firstuse/menudisplay";
		// final String strMenuDisplaylogon
		// ="http://66.228.143.28:8080/HubCiti_Perf/firstuse/menudisplay";

		// final String strMenuDisplaylogon
		// ="http://localhost:9990/HubCiti_Cache/firstuse/hubmenudisplay";

		final String strMenuDisplaylogon = "http://localhost:9990/HubCiti2.4.3/firstuse/hubmenudisplay";

		// String inputJSON
		// ="{\"hubCitiId\":2113,\"level\":1,\"sortOrder\":\"None\",\"linkId\":0}";
		String inputJSON = "{\"userId\": \"3\", \"hubCitiId\": \"82\", \"level\": \"1\", \"sortOrder\": \"NONE\", \"departmentId\": \"0\", \"typeId\": \"0\", \"deviceId\": \"DEE458EE-E4A3-44CA-9337-AE3E1902FB4E\", \"osVersion\": \"8.3\", \"linkId\": \"0\", \"platform\": \"IOS\", \"cityIds\": \"696\"}";

		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);
		request.accept("application/json").body(MediaType.APPLICATION_JSON, inputJSON);
		request.getHeaders();
		try {

			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("hubCitiMenuDisplay URI : " + strMenuDisplaylogon);
			System.out.println("Request in hubCitiMenuDisplay" + inputJSON);
			System.out.println("response in hubCitiMenuDisplay" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void updateDeviceDetailsForMenu() {

		final String strMenuDisplaylogon = "http://localhost:9990/HubCiti_Cache/firstuse/updatedevicedetails";

		String inputJSON = "{\"userId\":\"10654\",\"hubCitiId\":2070,\"deviceId\":\"000000000000000\",}";

		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);
		request.accept("application/json").body(MediaType.APPLICATION_JSON, inputJSON);
		request.getHeaders();
		try {

			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("menuDisplay URI : " + strMenuDisplaylogon);
			System.out.println("Request in menuDisplay" + inputJSON);
			System.out.println("response in menuDisplay" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void deleteHubCitiMenuDisplay() {
		final String strMobileService = "http://localhost:9990/HubCiti_Cache/firstuse/rmhcmenudisplay";
		final ClientRequest objClRequest = new ClientRequest(strMobileService);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in deleteHubCitiMenuDisplay --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void deleteHubRegionMenuDisplay() {
		final String strMobileService = "http://localhost:9990/HubCiti_Cache/firstuse/rmhubregmenudisplaycache";
		final ClientRequest objClRequest = new ClientRequest(strMobileService);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in deleteHubRegionMenuDisplay --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void menuDisplayForHubRegion() {

		final String strMenuDisplaylogon = "http://localhost:9990/HubCiti2.4.3/firstuse/hubregionmenudisplay";

		// String inputJSON =
		// "{\"hubCitiId\": \"2067\", \"level\": \"1\", \"sortOrder\": \"NONE\", \"departmentId\": \"0\", \"typeId\": \"0\", \"linkId\": \"0\",  \"cityIds\": \"7372,9994,12511,17062\"}";
		// String inputJSON =
		// "{\"hubCitiId\": \"2067\", \"level\": \"1\", \"sortOrder\": \"NONE\",\"linkId\": \"0\",  \"cityIds\": \"7372,9994,12511,17062\", \"dateCreated\": \" \",\"devType\": \" \"}";
//		String inputJSON = "{\"hubCitiId\":\"2119\",\"level\":\"2\",\"sortOrder\":\"None\",\"departmentId\":\"1\",\"typeId\":\"0\",\"linkId\":\"0\"}";
		
		String inputJSON = "{\"hubCitiId\":\"82\",\"level\":\"1\",\"sortOrder\":\"None\",\"departmentId\":\"1\",\"typeId\":\"0\",\"linkId\":\"16426\", \"dateCreated\": \"2016-03-08\"}";
		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);
		request.accept("application/json").body(MediaType.APPLICATION_JSON, inputJSON);
		request.getHeaders();
		try {

			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("hubCitiMenuDisplay URI : " + strMenuDisplaylogon);
			System.out.println("Request in hubCitiMenuDisplay" + inputJSON);
			System.out.println("response in hubCitiMenuDisplay" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	/*
	 * public static void getUserLogin() { final String logon =
	 * "http://localhost:9990/HubCiti_Cache/firstuse/getuserlogin"; // final
	 * String logon = "http://localhost:8080/HubCiti1.6/firstuse/login"; //
	 * final String logon =
	 * "http://199.36.142.83:8080/HubCiti1.0/firstuse/login"; //final String
	 * logon = "http://10.11.202.76:8080/HubCiti1.6/firstuse/login"; final
	 * ClientRequest request = new ClientRequest(logon);
	 * 
	 * String inputXML =
	 * "<UserDetails><userName><![CDATA[tester]]></userName><password><![CDATA[123456]]></password><appVersion>2.1</appVersion><deviceId>E45529B0-19DB-4F59-A341-FE3A0A448001</deviceId><hubCitiKey>Tyler19</hubCitiKey><platform>IOS</platform></UserDetails>"
	 * ; request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
	 * request.getHeaders(); try { long strtTime = new Date().getTime(); final
	 * String response = request.postTarget(String.class); long endTime = new
	 * Date().getTime(); System.out.println(endTime - strtTime);
	 * System.out.println("userLogin URI : " + logon);
	 * System.out.println("getUserLogin" + inputXML);
	 * System.out.println("getUserLogin" + response); } catch (Exception e) {
	 * System.out.println("Exception :  " + e); } }
	 */
	public static void badgereset() {

		final String strURL = "http://66.228.143.28:8080/HubCiti2.4.3/firstuse/resetbadge?hubCitiId=10&deviceId=1dd0&userId=10";

		final ClientRequest objClRequest = new ClientRequest(strURL);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("Badge reset URL: " + strURL);
			System.out.println("response in badgereset --> " + strResponse);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
}
