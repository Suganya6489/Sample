package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class ThisLocationRestClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {
//		 getRetailersForPartner();
//		 getRetailersForCitiExpirence();
		getRetailerSummary();
		// getCategoriesForPartners();
		// getCategoriesForGroupRetailers();
//		 getRetailerSpecialDeals();
//		 getRetailerHotDeals();
		// getRetailerLocationCoupon();
		// getProducts();
//		 getretSpecialOfferDetails();
		// userTrackingRetailerSummaryClick();
		// getAnythingPageDetails();
//		 getCouponDetails();
//		 getHubCitiAnythingPageDetails();
		// getLatLong();
//		getRetailersInfo();
//		 getAppSiteDetails();
//		 fetchUserLocationPoints();
		// updateZipcode();
		// getPartner();
//		getretailerLocationList();
		//getSpecialOfferDetails();
	}
	
private static void getSpecialOfferDetails() {
		
		final String logon ="http://66.228.143.28:8080/HubCiti2.1/thislocation/getspecialofferdetails";	
		final ClientRequest request = new ClientRequest(logon);
		
		String inputXML ="<RetailerDetail>"
				+ "<userId>6819</userId>"
				+ "<retailerId>1109478</retailerId>"
				+ "<retailLocationId>2086135</retailLocationId>"
				+ "<pageId>6703</pageId>"
				+ "<hubCitiId>2070</hubCitiId>"
				+ "<mainMenuId>30092</mainMenuId>"
				+ "<platform>Android</platform>"
				+ "</RetailerDetail>";
		/*String inputXML = "<RetailerDetail>"
								+"<userId>6</userId>"  
								+"<retailerId>1046</retailerId>"   
								+"<retailLocationId>92466</retailLocationId>"   
								+"<pageId>248</pageId>"  
								+"<hubCitiId>82</hubCitiId>"   
								//+"<scanTypeId></scanTypeId>"
								//+"<mainMenuId></mainMenuId>"
								+"<platform>ANDROID</platform>"
							+"</RetailerDetail>";*/

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getSpecialOfferDetails URI : " + logon);
			System.out.println("Request in getSpecialOfferDetails  " + inputXML);
			System.out.println("response in getSpecialOfferDetails   " + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void getretailerLocationList() {
		
		//		 final String logon = "http://199.36.142.83:8080/HubCiti1.6/thislocation/getspecialoffloclist";
		//		final String logon = "http://10.11.202.76:8080/HubCiti1.6/thislocation/getspecialoffloclist";
		//		final String logon = "https://app.scansee.net/HubCiti1.6/thislocation/getspecialoffloclist";
		//		final String logon ="http://10.122.61.21:8080/HubCiti1.6/thislocation/getspecialoffloclist";
		
		final String logon ="http://localhost:8080/HubCiti2.1/thislocation/getspecialoffloclist";	
		final ClientRequest request = new ClientRequest(logon);
		

		String inputXML = "<RetailerDetail>" 
							+ "<userId>6</userId>"
							+"<hubCitiId>17</hubCitiId>"
							+ "<retailerId>1046</retailerId>"
							+"<pageId>288</pageId>"
							+"<lowerLimit>0</lowerLimit>"
				+ "</RetailerDetail>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getretailerLocationList URI : " + logon);
			System.out.println("Request in getretailerLocationList  " + inputXML);
			System.out.println("response in getretailerLocationList   " + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	static void getPartner() {
		final String strPartner = "http://199.36.142.83:8080/HubCiti1.0/thislocation/getpartners?citiExperId=40&userId=90";
		final ClientRequest request = new ClientRequest(strPartner);

		try {
			long strtTime = new Date().getTime();
			final String response = request.getTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("response in getPartner: " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	public static void getRetailersForPartner() {
//		final String logon = "http://199.36.142.83:8080/HubCiti1.5/thislocation/partnerret";
		final String logon = "http://localhost:8080/HubCiti2.1/thislocation/partnerret";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/thislocation/partnerret";
		final ClientRequest request = new ClientRequest(logon);
		/*
		 * String inputXML = "<ThisLocationRequest>" + "<userId>240</userId>" +
		 * "<hubCitiId>44</hubCitiId>" + "<retAffId>6</retAffId>" // +
		 * "<latitude></latitude>" // + "<longitude></longitude>" // +
		 * "<postalCode>78725</postalCode>" + "<catIds>0</catIds>" // +
		 * "<searchKey></searchKey>" + "<lowerLimit>0</lowerLimit>" // +
		 * "<mainMenuId>23</mainMenuId>" // + "<mItemId>700</mItemId>" //+
		 * "<bottomBtnId>1</bottomBtnId>" + "<platform>IOS</platform>" +
		 * "</ThisLocationRequest>";
		 */
		// String inputXML =
		// "<ThisLocationRequest><userId>240</userId><hubCitiId>44</hubCitiId><mItemId>1465</mItemId><lowerLimit>0</lowerLimit><retAffId>8</retAffId><catIds>0</catIds><platform>IOS</platform><postalCode><![CDATA[78753]]></postalCode></ThisLocationRequest>";

		String inputXML = "<ThisLocationRequest>" + "<userId>8096</userId>" + "<hubCitiId>3152</hubCitiId>"

				+ "<mainMenuId>11104</mainMenuId>" 
				+ "<lowerLimit>0</lowerLimit>" + "<retAffId>1066</retAffId>"
				 + "<catIds>1,2</catIds>"
				+ "<platform>IOS</platform>"
				 + "<postalCode><![CDATA[78654]]></postalCode>"
				 +"<sortColumn>name</sortColumn>" //Values --> Distance, RetailerName .Default is by Distance.
				 +" <sortOrder>Asc</sortOrder> "// Values --> ASC,DESC  .Default is by Ascending.
				  +"<locSpecials>1</locSpecials><searchKey></searchKey> <latitude></latitude><longitude></longitude>"// Values --> ASC,DESC  .Default is by Ascending.

				+ "</ThisLocationRequest>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getRetailersForPartner URI : " + logon);
			System.out.println("Request in getRetailersForPartner" + inputXML);
			System.out.println("response in getRetailersForPartner" + response);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getRetailersForCitiExpirence() {
//		final String logon = "http://199.36.142.83:8080/HubCiti1.5/thislocation/citiexpret";
//		final String logon = "https://app.scansee.net/HubCiti1.6/thislocation/citiexpret";
		final String logon = "http://localhost:8080/HubCiti2.1/thislocation/citiexpret";

		final ClientRequest request = new ClientRequest(logon);
		String inputXML = "<ThisLocationRequest>" 
				+ "<userId>6</userId>" 
				+ "<hubCitiId>1143</hubCitiId>" 
				+ "<citiExpId>2133</citiExpId>"
//				+ "<latitude>32.90456</latitude>"
//		 		+ "<longitude>-96.454497</longitude>"
//		 		+ "<postalCode>75701</postalCode>"
				+ "<catIds>10</catIds>"
//				+ "<searchKey>aa</searchKey>"
				+ "<lowerLimit>0</lowerLimit>" 
//				+ "<mainMenuId>46932</mainMenuId>"
//				+ "<mItemId>34069</mItemId>"
//				+ "<bottomBtnId>1</bottomBtnId>"
				+ "<platform>IOS</platform>"
//				+ "<sortColumn>City</sortColumn>" //Distance, name, city
				+ "<sortOrder>ASC</sortOrder>"
//				+"<groupBy>type</groupBy>"//atoz, type
//				+ "<cityIds>14278</cityIds>" //HubCiti level value will be null.
				+"<locSpecials>0</locSpecials>" //0- SaleFlag All will display else 1 -SaleFlag with 1 will display
//				+"<interests>1,2</interests>"
				+ "</ThisLocationRequest>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getRetailersForCitiExpirence URI : " + logon);
			System.out.println("Request in getRetailersForCitiExpirence" + inputXML);
			System.out.println("response in getRetailersForCitiExpirence" + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getRetailerSummary() {
//		 final String logon = "http://199.36.142.83:8080/HubCiti1.6/thislocation/retsummary";
//		final String logon = "http://10.11.202.76:8080/HubCiti1.6/thislocation/retsummary";
//		final String logon = "https://app.scansee.net/HubCiti2.1/thislocation/retsummary";
		final String logon = "http://66.228.143.28:8080/HubCiti2.2/thislocation/retsummary";
		// final String logon ="http://10.122.61.21:8080/HubCiti1.6/thislocation/retsummary";
		final ClientRequest request = new ClientRequest(logon);

		/*String inputXML = "<RetailerDetail>" + "<userId>3</userId>" + "<retailerId>2127</retailerId>" + "<retailLocationId>94410</retailLocationId>"
				//+ "<retListId>1517199</retListId>" 
		//+ "<mainMenuId>66110</mainMenuId>" + "<scanTypeId>0</scanTypeId>" 
				+ "<hubCitiId>1143</hubCitiId>"
//		+ "<postalCode>78750</postalCode>"
				 	//	+ "<latitude>30.422401</latitude>"
				 	//	+ "<longitude>-97.796676</longitude>"
				+ "</RetailerDetail>";*/
		
		
		//String inputXML = "<RetailerDetail>" + "<userId>10178</userId>" + "<retailerId>1108571</retailerId>" + "<retailLocationId>2083453</retailLocationId>"
				//+ "<retListId>1517199</retListId>" 
		//+ "<mainMenuId>66110</mainMenuId>" + "<scanTypeId>0</scanTypeId>" 
				//+ "<hubCitiId>10</hubCitiId>"
//		+ "<postalCode>78750</postalCode>"
				 	//	+ "<latitude>30.422401</latitude>"
				 	//	+ "<longitude>-97.796676</longitude>"
				/*+ "</RetailerDetail>";*/
				
		String inputXML ="<RetailerDetail><userId>10182</userId><retailerId>1108571</retailerId><retailLocationId>2083453</retailLocationId><retListId>1484832</retListId><mainMenuId>38480</mainMenuId><hubCitiId>8</hubCitiId><postalCode>78654</postalCode></RetailerDetail>";
		
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getRetailerSummary URI : " + logon);
			System.out.println("Request in getRetailerSummary" + inputXML);
			System.out.println("response in getRetailerSummary" + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getCategoriesForPartners() {
		final String logon = "http://199.36.142.83:8080/HubCiti1.0/thislocation/getcatforpartner";
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/thislocation/getcatforpartner";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<ThisLocationRequest>" + "<hubCitiId>75</hubCitiId>" + "<retAffId>4</retAffId>" + "</ThisLocationRequest>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("response in getCategoriesForPartners" + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getCategoriesForGroupRetailers() {
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.0/thislocation/getcatforgroup";
		// final String logon =
		// "http://10.11.202.76:8080/HubCiti1.0/thislocation/getcatforgroup";
		final String logon = "http://localhost:8080/HubCiti1.0/thislocation/getcatforgroup";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<ThisLocationRequest>" + "<hubCitiId>93</hubCitiId>" + "<retGroupId>62</retGroupId>" + "</ThisLocationRequest>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getCategoriesForGroupRetailers" + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getRetailerSpecialDeals() {
		final String logon = "http://localhost:8080/HubCiti1.8/thislocation/retspedealoff";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<ProductDetailsRequest>" + "<userId>3</userId>" + "<retailId>1046</retailId>"
				+ "<retailLocationId>94152</retailLocationId>" + "<hubCitiId>17</hubCitiId>" + "</ProductDetailsRequest>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("getRetailerSpecialDeals URI : " + logon);
			System.out.println("Request in getRetailerSpecialDeals" + inputXML);
			System.out.println("response in getRetailerSpecialDeals" + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getRetailerHotDeals() {
//		final String logon = "http://localhost:8080/HubCiti1.8/thislocation/getrethotdeals";
		final String logon = "http://66.228.143.28:8080/HubCiti1.8/thislocation/getrethotdeals";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<RetailerDetail>" + "<userId>11181</userId>" + "<retailerId>1112686</retailerId>" + "<retailLocationId>2087455</retailLocationId>"
				+ "<lastVisitedNo>0</lastVisitedNo>" + "<hubCitiId>19</hubCitiId>" + "<mainMenuId>1</mainMenuId>" + "</RetailerDetail>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("getRetailerHotDeals URI : " + logon);
			System.out.println("Request in getRetailerHotDeals" + inputXML);
			System.out.println("response in getRetailerHotDeals" + response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getRetailerLocationCoupon() {
		final String logon = "http://localhost:8080/HubCiti1.0/thislocation/retloccoup";
		final ClientRequest request = new ClientRequest(logon);
		String inputXML = "<RetailerDetail>" + "<userId>3</userId>" + "<retailerId>1046</retailerId>" + "<retailLocationId>93109</retailLocationId>"
				+ "<lastVisitedNo>0</lastVisitedNo>" + "<mainMenuId>1</mainMenuId>" + "<hubCitiId>70</hubCitiId>" + "<retListId>23</retListId>"
				+ "</RetailerDetail>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getRetailerLocationCoupon" + response);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void getProducts() {
		final String getProducts = "http://localhost:8080/HubCiti1.0/thislocation/getProducts";
		final ClientRequest request = new ClientRequest(getProducts);
		final String getProductsXML2 = "<ProductDetailsRequest>" + "<userId>3</userId>" + "<retailLocationId>93109</retailLocationId>"
				+ "<lastVisitedProductNo>0</lastVisitedProductNo>" + "<retailId>1046</retailId>" + "<hubCitiId>47</hubCitiId>"
				// + "<retListId>2</retListId>"
				// +"<mainMenuId>1</mainMenuId>"
				+ "</ProductDetailsRequest>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getProductsXML2);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getProducts : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getretSpecialOfferDetails() {
		final String strRetSpecOffDetails = "http://66.228.143.28:8080/HubCiti1.8/thislocation/getspecialofferlist";
//		final String strRetSpecOffDetails = "https://app.scansee.net/HubCiti1.5/thislocation/getspecialofferlist";
		final ClientRequest request = new ClientRequest(strRetSpecOffDetails);
/*		String getretstoreinfoxml = "<RetailerDetail>" + "<userId>11</userId>" + "<retailLocationId>78949</retailLocationId>"
				+ "<retailerId>135</retailerId>" + "<hubCitiId>19</hubCitiId>" + "<lastVisitedNo>0</lastVisitedNo>"
				// + "<retListId>2</retListId>"
				// +"<mainMenuId>1</mainMenuId>"
				+ "</RetailerDetail>";*/

		String getretstoreinfoxml = "<RetailerDetail><userId>1658</userId><retailLocationId>2086141</retailLocationId><lastVisitedNo>0</lastVisitedNo><retailerId>1067655</retailerId><mainMenuId>26089</mainMenuId><scanTypeId>0</scanTypeId><hubCitiId>10</hubCitiId></RetailerDetail>";
		request.accept("text/xml").body(MediaType.TEXT_XML, getretstoreinfoxml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getretSpecialOfferDetails URI : " + strRetSpecOffDetails);
			System.out.println("Request in getretSpecialOfferDetails" + getretstoreinfoxml);
			System.out.println("response in getretSpecialOfferDetails" + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getSpecialDealDetails() {
		final String getProducts = "http://localhost:8080/HubCiti1.0/thislocation/getspecialofferinfo";
		final ClientRequest request = new ClientRequest(getProducts);
		final String getProductsXML2 = "<RetailerDetail>" + "<pageId>289</pageId>" + "<retailerId>1046</retailerId>"
				+ "<retailLocationId>93109</retailLocationId>" + "<hubCitiId>70</hubCitiId>"
				// +"<mainMenuId>1</mainMenuId>"
				+ "<scanTypeId>1</scanTypeId>" + "</RetailerDetail>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getProductsXML2);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getSpecialDealDetails : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void userTrackingRetailerSummaryClick() {
		final String getProducts = "http://localhost:8080/HubCiti1.0/thislocation/utretsumclick";

		final ClientRequest request = new ClientRequest(getProducts);

		final String getProductsXML2 = "<UserTrackingData>" + "<retDetailsId>106</retDetailsId>" + "<anythingPageListId>75</anythingPageListId>"
				+ "<retListId>12430</retListId>"
				// + "<banADClick>false</banADClick>"
				+ "<callStoreClick>true</callStoreClick>"
				// + "<browseWebClick>false</browseWebClick>"
				// + "<getDirClick>false</getDirClick>"
				// + "<anythingPageClick>true</anythingPageClick>"
				+ "</UserTrackingData>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getProductsXML2);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}

	}

	static void getAnythingPageDetails() {
		final String getProducts = "http://localhost:8080/HubCiti1.0/thislocation/getretsumanythinginfo";
		final ClientRequest request = new ClientRequest(getProducts);
		final String getAnyThingXML = "<RetailerDetail>" + "<userId>3</userId>" + "<pageId>86</pageId>" + "<retailerId>1136</retailerId>"
				+ "<retailLocationId>93109</retailLocationId>" + "<hubCitiId>91</hubCitiId>"
				// +"<mainMenuId>1</mainMenuId>"
				+ "<scanTypeId>1</scanTypeId>" + "<platform>Android</platform>" + "</RetailerDetail>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getAnyThingXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getAnythingPageDetails : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getCouponDetails() {
		final String getProducts = "http://66.228.143.28:8080/HubCiti2.1/thislocation/getcoupondetails";
		// final String getProducts =
		// "http://localhost:8080/HubCiti1.0/thislocation/getcoupondetails";
		// final String getProducts =
		// "http://199.36.142.83:8080/HubCiti1.0/thislocation/getcoupondetails";
		final ClientRequest request = new ClientRequest(getProducts);
		final String getAnyThingXML = "<RetailerDetail><userId>8914</userId><couponId>73952</couponId><hubCitiId>2070</hubCitiId></RetailerDetail>";
		/*final String getAnyThingXML = "<RetailerDetail>" + "<userId>458</userId>" // 3
				+ "<hubCitiId>47</hubCitiId>"// 47
				+ "<couponId>68815</couponId>"// 66448
				// + "<couponListId>0</couponListId>"
				+ "</RetailerDetail>";*/
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getAnyThingXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getCouponDetails : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getHubCitiAnythingPageDetails() {
		final String getProducts = "http://localhost:8080/HubCiti1.7/thislocation/gethubcitianythinginfo";
		final ClientRequest request = new ClientRequest(getProducts);
		/*		final String getAnyThingXML = "<RetailerDetail>" + "<pageId>382</pageId>" + "<hubCitiId>77</hubCitiId>"
		// + "<mainMenuId>1</mainMenuId>"
				+ "<mItemId>2377</mItemId>" + "<platform>IOS</platform>" + "</RetailerDetail>";*/

		final String getAnyThingXML = "<RetailerDetail><pageId>3135</pageId><mItemId>38363</mItemId><platform>IOS</platform><hubCitiId>2070</hubCitiId></RetailerDetail>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getAnyThingXML);
		request.getHeaders();
		try {

			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getFilterList URI : " + getProducts);
			System.out.println("Request in getFilterList" + getAnyThingXML);
			System.out.println("response in getFilterList" + response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void getLatLong() {
		final String getProducts = "http://localhost:8080/HubCiti1.0/thislocation/fetchlatlong?zipcode=78729";
		final ClientRequest request = new ClientRequest(getProducts);

		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in getHubCitiAnythingPageDetails : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getRetailersInfo() {
		final String getRetailersInfo = "https://app.scansee.net/HubCiti1.6/thislocation/getretailersinfo";
//		final String getRetailersInfo = "https://app.scansee.net/HubCiti1.0/thislocation/getretailersinfo";
//		final String getRetailersInfo = "http://localhost:8080/HubCiti1.3/thislocation/getretailersinfo";
		final ClientRequest request = new ClientRequest(getRetailersInfo);
		/*final String getRetailersInfo1Xml = "<ThisLocationRequest>"
		// + "<preferredRadius>20</preferredRadius>"
				+ "<zipcode>78654</zipcode>"
//				 + "<latitude>30.422401</latitude>"
//				 + "<longitude>-97.796676</longitude>"
				+ "<userId>6807</userId>" 
				+ "<gpsEnabled>0</gpsEnabled>" 
				+ "<lastVisitedRecord>0</lastVisitedRecord>"
				//+ "<mainMenuId>1</mainMenuId>"
				// + "<mItemId>70</mItemId>"
				 + "<bottomBtnId>7247</bottomBtnId>"
				 + "<platform>IOS</platform>"
				+ "<hubCitiId>2070</hubCitiId>" 
				+ "<locOnMap>true</locOnMap>"
				+"<sortOrder>Asc</sortOrder>" // Values --> ASC,DESC  .Default is by Ascending.
				+"<sortColumn>distance</sortColumn>" //Values --> Distance, RetailerName .Default is by Distance.
				+ "</ThisLocationRequest>";
		*/
		final String getRetailersInfo1Xml = "<ThisLocationRequest><hubCitiId>19</hubCitiId><gpsEnabled>false</gpsEnabled><userId>11181</userId><locOnMap>true</locOnMap><zipcode>78654</zipcode><bottomBtnId>210</bottomBtnId><platform>IOS</platform><sortOrder>ASC</sortOrder><sortColumn>Distance</sortColumn><lastVisitedRecord>0</lastVisitedRecord></ThisLocationRequest>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getRetailersInfo1Xml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getRetailersInfo URI : " + getRetailersInfo);
			System.out.println("Request in getRetailersInfo" + getRetailersInfo1Xml);
			System.out.println("response in getRetailersInfo" + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getAppSiteDetails() {
		final String getRetailersInfo = "http://localhost:8080/HubCiti1.6/thislocation/appsitedetails";
		final ClientRequest request = new ClientRequest(getRetailersInfo);
		final String getRetailersInfo1Xml = "<RetailerDetail>" + "<userId>6786</userId>" + "<hubCitiId>2067</hubCitiId>" + "<linkId>1044</linkId>"
		// + "<latitude>0</latitude>"
		// + "<longitude>1</longitude>"
		// + "<postalCode></postalCode>"
		// + "<mainMenuId>45</mainMenuId>"
				+ "<mItemId>36101</mItemId>"
				// + "<bottomBtnId>1</bottomBtnId>"
				+ "<platform>IOS</platform>" + "</RetailerDetail>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getRetailersInfo1Xml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getRetailersInfo : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void fetchUserLocationPoints() {
		final String getProducts = "http://localhost:8080/HubCiti1.5/thislocation/fetchuserlocationpoints?userId=2999";
		final ClientRequest request = new ClientRequest(getProducts);

		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in fetchUserLocationPoints: " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void updateZipcode() {
		// final String getProducts =
		// "http://localhost:8080/HubCiti1.0/thislocation/updateusrzipcode?userId=370&zipcode=14564&hubCitiId=26";
		final String getProducts = "http://10.11.202.76:8080/HubCiti1.0/thislocation/updateusrzipcode?userId=1864&zipcode=78725&hubCitiId=90";

		final ClientRequest request = new ClientRequest(getProducts);
		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in updateZipcode: " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}
}
