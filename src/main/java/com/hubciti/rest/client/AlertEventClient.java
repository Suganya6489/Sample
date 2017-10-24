package com.hubciti.rest.client;

import java.util.Date;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.client.ClientRequest;

public class AlertEventClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {

//		 getAlertsList();
//		 getDiningRetailersInfo();
//		 getEventList();
//		 getEventCategories();
// 		 getDiningCategories();
//       userTrackingAlertClick();
//		 getEventDetails();
//       userTrackingAlertClick();
//		 getEventHotelDisplay();
//		 getEventHotelDetail();
//		 getEventappsiteloc();
//		getFundraiserDetails();
//		getFundraiserList();
//	    getDepartmentList();
//		 getEventLogistics();
//		 getFundraiserAppSiteLocationList();
		
		remEventList();
	}

	private static void getFundraiserAppSiteLocationList() {
	
		final String logon = "http://localhost:8080/HubCiti2.1/alertevent/getfundAppSiteloc";
		
		final ClientRequest request = new ClientRequest(logon);

		/*String inputXML = 		"<Fundraiser>"
									+"<userId>14</userId>"
									+"<hubCitiId>17</hubCitiId>"
									+"<fundId>3110</fundId>"
									+"<postalCode>78654</postalCode>"
									
								+"</Fundraiser>";
		*/
		String 	inputXML =		"<Fundraiser>"
											+"<userId>14</userId>"
											+"<hubCitiId>17</hubCitiId>"
											+"<fundId>2</fundId>"
											+"<postalCode>78654</postalCode>"
											+"<latitude>10</latitude>"
											+"<longitude>234</longitude>"
									+"</Fundraiser>";
								

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("request URL : " + logon);
			System.out.println("Request :  " + inputXML);
			System.out.println("response in getFundraiserAppSiteLocationList" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
		
	}

static void getEventDateList() {
		
		final String logon = "http://localhost:8080/HubCiti1.9/alertevent/getevtdatelist";
		
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = 		"<EventDetails>"
									+"<userId>11</userId>"
									+"<hubCitiId>140</hubCitiId>"
									+"<eventCatId>0</eventCatId>"
									+"<mItemId>22754</mItemId>"
									//+"<bottomBtnId></bottomBtnId>"
									//+"<retailId></retailId>"
									//+"<retailLocationId></retailLocationId>"
									//+"<fundRaisId></fundRaisId>"
								+"</EventDetails>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getevtdatelist" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
		
		
	}

	static void getAlertsList() {
//		final String logon = "http://10.11.202.76:8080/HubCiti1.6/alertevent/alertlist";
		// final String logon =
		// "http://66.228.143.28:8080/HubCiti1.6/alertevent/alertlist";
		 final String logon = "http://localhost:8080/HubCiti1.6/alertevent/alertlist";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.6/alertevent/alertlist";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<MenuItem>" + "<userId>1658</userId>" + "<hubCitiId>10</hubCitiId>" + "<mItemId>10</mItemId>"
		// + "<bottomBtnId>1859</bottomBtnId>"
				+ "<platform>ios</platform>"
				// + "<mainMenuId>10</mainMenuId>"
				+ "<lowerLimit>0</lowerLimit>" + "<categoryId>10</categoryId>"
				// + "<searchKey>alert Category1</searchKey>"
				+ "</MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getCategory" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getDiningRetailersInfo() {
		final String getRetailersInfo = "https://app.scansee.net/HubCiti1.2/alertevent/getdiningretinfo";
		final ClientRequest request = new ClientRequest(getRetailersInfo);
		// If GPS is off..<lat>18.00529</lat><lng>-66.61231</lng>
		final String getRetailersInfo1Xml = "<ThisLocationRequest>" 
				+ "<userId>3</userId>" 
				+ "<cuisineTypeId>27</cuisineTypeId>"
				+ "<lastVisitedRecord>0</lastVisitedRecord>"
				// + "<preferredRadius>100</preferredRadius>"
				+ "<mainMenuId>1</mainMenuId>"
				// + "<mItemId>70</mItemId>"
				// + "<bottomBtnId>70</bottomBtnId>"
				// + "<platform>IOS</platform>"
//				+ "<latitude>12.9465362</latitude>" 
//				+ "<longitude>77.5762703</longitude>"
				+ "<postalCode>78654</postalCode>"
				// + "<preferredRadius>100</preferredRadius>"
				// + "<sortOrder>Asc</sortOrder>" //Asc, Desc
				// + "<sortColumn>Distance</sortColumn>" //RetailName, Distance
				// + "<searchKey></searchKey>"
				+ "<hubCitiId>10</hubCitiId>" + "</ThisLocationRequest>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getRetailersInfo1Xml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("response in getRetailersInfo: " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}

	}

	static void userTrackingAlertClick() {
		final String logon = "http://10.11.202.220:8080/HubCiti1.6/alertevent/utalertclick?alertListId=233";
		final ClientRequest request = new ClientRequest(logon);

		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in getCategory" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getDiningCategories() {
		final String logon = "http://10.11.202.76:8080/HubCiti1.6/alertevent/fetcheventdetail?eventId=2191&eventsListId=34925";
//		final String logon = "http://localhost:8080/HubCiti1.6/alertevent/diningcategory?userId=218&hubCitiId=70";
		final ClientRequest request = new ClientRequest(logon);

		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in getCategory" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getEventList() {
//		final String logon = "https://app.scansee.net/HubCiti1.5/alertevent/eventlist";
//		final String logon = "http://localhost:8080/HubCiti2.1/alertevent/eventlist";
//		 final String logon = "http://10.11.202.76:8080/HubCiti1.6/alertevent/eventlist";
//		 final String logon ="http://10.11.202.76:8080/HubCiti1.6/alertevent/eventlist";
		 final String logon ="http://localhost:8080/HubCiti_Cache/alertevent/eventlist";
		// final String logon =
		// "http://localhost:8080/HubCiti1.6/alertevent/eventlist";
		final ClientRequest request = new ClientRequest(logon);
		//String inputXML = "<MenuItem><userId>6819</userId><platform>ios</platform><mainMenuId>27959</mainMenuId><mItemId>39709</mItemId><postalCode><![CDATA[75087]]></postalCode><hubCitiId>2070</hubCitiId><lowerLimit>0</lowerLimit><sortBy>atoz</sortBy><isRegApp>1</isRegApp></MenuItem>"; 
		String inputXML = "<MenuItem>" 
				+ "<userId>1658</userId>" 
				+ "<hubCitiId>10</hubCitiId>" 
				+ "<mainMenuId>24206</mainMenuId>"
				+ "<mItemId>39074</mItemId>"
				+ "<bottomBtnId>188</bottomBtnId>"
				+ "<platform>ios</platform>" 
				+ "<lowerLimit>0</lowerLimit>"
				+ "<latitude>37.785800</latitude>"
				+ "<longitude>122.406400</longitude>"
				+ "<postalCode>78654</postalCode>" 
				+ "<sortOrder>Desc</sortOrder>" // Desc,Asc
				+ "<groupBy>date</groupBy>" // date, type, atoz, N/A
				+ "<sortBy>date</sortBy>" // date, name, distance, city
				+ "<searchKey></searchKey>"
				+ "<catIds>0</catIds>" 
				+ "<retailId>1246</retailId>"
				+ "<retailLocationId>93146</retailLocationId>"
				+ "<cityIds>1,2,3</cityIds>" //HubCiti level value will be null.
				+ "<isRegApp>0</isRegApp>" //default value is 0 (HubCiti) and 1(Region App) .
				+"<fundId>1014</fundId>"
				+"<evtDate>1014</evtDate>"
				+ "</MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getEventList URI : " + logon);
			System.out.println("Request in getEventList" + inputXML);
			System.out.println("response in getEventList" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getEventDetails() {
	//	final String getEventDetails = "http://localhost:8080/HubCiti1.6/alertevent/fetcheventdetail?eventId=5264&eventsListId=82";
		
//		final String getEventDetails = "http://localhost:8080/HubCiti2.3.2/alertevent/fetcheventdetail?eventId=5264&hubCitiId=82";
		final String getEventDetails = "http://localhost:8080/HubCiti2.3.2/alertevent/fetcheventdetail?eventId=3530&eventsListId=4&hubCitiId=2113";
//	final String getEventDetails = "http://localhost:8080/HubCiti1.6/alertevent/fetcheventdetail?eventId=2191&eventsListId=34925";
//		final String getEventDetails = "https://app.scansee.net/HubCiti1.6/alertevent/fetcheventdetail?eventId=28&eventsListId=1";
		final ClientRequest request = new ClientRequest(getEventDetails);
		try {
			final String responseObj = request.getTarget(String.class);
			System.out.println("response in getEventDetails" + responseObj);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getEventHotelDisplay() {

//		final String getRetailersInfo = "http://10.11.202.76:8080/HubCiti1.6/alertevent/geteventhoteldisplay";
		 final String getRetailersInfo = "http://66.228.143.28:8080/HubCiti1.6/alertevent/geteventhoteldisplay";
		// final String getRetailersInfo = "http://localhost:8080/HubCiti1.6/alertevent/geteventhoteldisplay";

		final ClientRequest request = new ClientRequest(getRetailersInfo);
		final String getRetailersInfo1Xml = "<ThisLocationRequest>"
											+ "<userId>528</userId>" 
											+ "<eventId>36</eventId>"
											+ "<lastVisitedRecord>0</lastVisitedRecord>"
//		 									+ "<latitude>18.00529</latitude>"
//		 									+ "<longitude>-66.61231</longitude>"
				 							+ "<postalCode>78654</postalCode>"
											+ "<hubCitiId>10</hubCitiId>" 
											+ "<mainMenuId>1</mainMenuId>"
										+ "</ThisLocationRequest>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getRetailersInfo1Xml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getEventHotelDisplay: " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getEventCategories() {
//		final String logon = "http://10.11.202.220:8080/HubCiti1.6/alertevent/eventcategory?userId=2&hubCitiId=70";
		final String logon = "http://localhost:8080/HubCiti1.6/alertevent/eventcategory";
		final ClientRequest request = new ClientRequest(logon);
		final String requestXML = "<MenuItem>"
				+ "<userId>3</userId>" 
				+ "<hubCitiId>80</hubCitiId>" 
				+ "<mItemId>10352</mItemId>"
//				+ "<bottomBtnId></bottomBtnId>"
			+ "</MenuItem>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, requestXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getEventCategories" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getEventHotelDetail() {
		final String getRetailersInfo = "http://66.228.143.28:8080/HubCiti1.5/alertevent/fetcheventhoteldetail";
		final ClientRequest request = new ClientRequest(getRetailersInfo);
		final String getRetailersInfo1Xml = "<Retailer>" + "<userId>2</userId>" + "<eventId>4</eventId>" + "<hubCitiId>47</hubCitiId>"
				+ "<retListId>0</retListId>" + "<retailLocationId>1303938</retailLocationId>" + "</Retailer>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getRetailersInfo1Xml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getRetailersInfo: " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}
	
	
	static void getEventappsiteloc() {
		final String getRetailersInfo = "http://localhost:8080/HubCiti1.6/alertevent/eventappsiteloc";
//		final String getRetailersInfo = "http://66.228.143.28:8080/HubCiti1.6/alertevent/eventappsiteloc";
		final ClientRequest request = new ClientRequest(getRetailersInfo);
		final String getRetailersInfo1Xml = "<Retailer>" + "<userId>2400</userId>" + "<eventId>8</eventId>" + "<hubCitiId>70</hubCitiId>"
				//+ "<latitude>30.1474</latitude>" + "<longitude>95.5086</longitude>" 
				+ "</Retailer>";
			//	+ "</Retailer>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getRetailersInfo1Xml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getRetailersInfo: " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}
	
	
	
	public static void getDepartmentList() {
		final String strMenuDisplaylogon = "http://localhost:8080/HubCiti1.8/alertevent/fundrserdeptlist";
		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);

		String inputXML = "<MenuItem>" + "<userId>10019</userId>" + "<hubCitiId>2070</hubCitiId>" 
		/*+ "<mItemId>20745</mItemId>" 
		//+ "<bottomBtnId>15</bottomBtnId>"
		+"<retailId>20742</retailId>"
		+"<retailLocationId>20742</retailLocationId>"*/
		+"</MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getDepartmentList URI : " + strMenuDisplaylogon);
			System.out.println("Request in getDepartmentList" + inputXML);
			System.out.println("response in getDepartmentList" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	public static void getFundraiserDetails() {

//		final String strMenuDisplaylogon = "http://sdw2107:8080/HubCiti1.6/alertevent/getfundrsdetail";
		final String strMenuDisplaylogon = "http://localhost:8080/HubCiti1.6/alertevent/getfundrsdetail";
		final ClientRequest request = new ClientRequest(strMenuDisplaylogon);

		String inputXML = "<MenuItem>" 
				 + "<fundListId>0</fundListId>" 
		         + "<hubCitiId>8</hubCitiId>" 
				 + "<fundId>55</fundId>" 
				 + "<platform>Android</platform>"
				 +"</MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getFundraiserDetails URI : " + strMenuDisplaylogon);
			System.out.println("Request in getFundraiserDetails" + inputXML);
			System.out.println("response in getFundraiserDetails" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

	}
	
	
	public static void getFundraiserList() {
//		final String logon = "https://app.scansee.net/HubCiti1.6/alertevent/fundrserlist";
//		 final String logon = "http://localhost:8080/HubCiti/alertevent/fundrserlist";
//		 final String logon = "http://10.11.202.76:8080/HubCiti1.6/alertevent/fundrserlist";
		 final String logon =" http://66.228.143.28:8080/HubCiti1.7/alertevent/fundrserlist";
//		 final String logon ="http://localhost:8080/HubCiti1.6/alertevent/fundrserlist";
		// final String logon = "http://localhost:8080/HubCiti1.6/alertevent/eventlist";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<MenuItem>" 
				+ "<userId>6819</userId>" 
				+ "<hubCitiId>2070</hubCitiId>" 
				+ "<mainMenuId>22053</mainMenuId>"
				+ "<mItemId>36444</mItemId>"
//				+ "<bottomBtnId>188</bottomBtnId>"
				+ "<platform>ios</platform>" 
				+ "<lowerLimit>0</lowerLimit>"
				+ "<latitude>37.787400</latitude>"
				+ "<longitude>122.408200</longitude>"
//				+ "<postalCode>78654</postalCode>" 
//				+ "<sortOrder>Desc</sortOrder>" // Desc,Asc
				+ "<groupBy>date</groupBy>" // date, type, atoz,
				+ "<sortBy>date</sortBy>" // date, name 
//				+ "<searchKey></searchKey>"
				+ "<catId>0</catId>" 
				+ "<retailId>1109478</retailId>"
				+ "<retailLocationId>2086121</retailLocationId>"
//				+ "<cityIds>1,2,3</cityIds>" //HubCiti level value will be null.
//				+ "<isRegApp>1</isRegApp>" //default value is 0 (HubCiti) and 1(Region App).
//				+"<departmentId>1</departmentId>"
				+ "</MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			
			System.out.println(endTime - strtTime);
			
			System.out.println("getFundraiserList URI : " + logon);
			System.out.println("Request in getFundraiserList" + inputXML);
			System.out.println("response in getFundraiserList" + response);
			
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	static void getEventLogistics() {
//		final String logon = "https://app.scansee.net/HubCiti1.5/alertevent/eventlist";
		 final String logon = "http://localhost:8080/HubCiti1.7/alertevent/eventlogistics";
//		 final String logon = "http://10.11.202.76:8080/HubCiti1.6/alertevent/eventlist";
//		 final String logon ="http://10.11.202.76:8080/HubCiti1.6/alertevent/eventlist";
//		 final String logon ="http://66.228.143.28:8080/HubCiti1.7/alertevent/eventlist";
		// final String logon =
		// "http://localhost:8080/HubCiti1.6/alertevent/eventlist";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<MenuItem>" 
				+ "<userId>11</userId>" 
				+ "<hubCitiId>2070</hubCitiId>" 
				+ "<eventId>3249</eventId>"
				+ "</MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getEventLogistics URI : " + logon);
			System.out.println("Request in getEventLogistics" + inputXML);
			System.out.println("response in getEventLogistics" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	

	static void remEventList() {											
		final String strMobileService = "http://localhost:9990/HubCiti_Cache/alertevent/rmeventlistcache";
		final ClientRequest objClRequest = new ClientRequest(strMobileService);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in remEventList --> " + strResponse);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
}
