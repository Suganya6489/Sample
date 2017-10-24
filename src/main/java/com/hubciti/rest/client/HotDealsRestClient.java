package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class HotDealsRestClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {

//		 getHotDealsList();
		// removeHdProd();
		// userTrackingGetHotDealClick();
		// hotDealClaim();
		// hotDealRedeem();

//		 userGalleryDisplay();
//		userDealsDisplay();
//		fundraisersList();
		 retailerClickImpression();
		 
	}



	public static void getHotDealDetail() {
//		final String logon = "https://app.scansee.net/HubCiti1.7/hotdeals/hddetail";
//		final String logon = "http://localhost:8080/HubCiti2.1/hotdeals/hddetail";
		final String logon = "http://66.228.143.28:8080/HubCiti2.1/hotdeals/hddetail";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<HotDealsDetails><userId>14</userId><hubCitiId>10</hubCitiId><hotDealId>1472533</hotDealId><hotDealListId>1</hotDealListId></HotDealsDetails>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getHotDealDetail URI : " + logon);
			System.out.println("Request in getHotDealDetail" + inputXML);
			System.out.println("response in getHotDealDetail" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getHotDealsList() {
//		final String getHotDeallist2 = "https://app.scansee.net/HubCiti1.7/hotdeals/gethotdealprods";
//		final String getHotDeallist2 = "http://localhost:8080/HubCiti1.8/hotdeals/gethotdealprods";
		final String getHotDeallist2 = "http://66.228.143.28:8080/HubCiti1.8/hotdeals/gethotdealprods";
		final ClientRequest request = new ClientRequest(getHotDeallist2);

	/*	final String getHotDeallistXml2 = "<HotDealsListRequest>" + "<userId>6819</userId>" 
				+ "<category>0</category>"
//				+ "<searchItem><![CDATA[deal new]]></searchItem>"
				+ "<lastVisitedProductNo>0</lastVisitedProductNo>" 
//				+ "<mainMenuId>10</mainMenuId>" 
				+ "<hubCitiId>2070</hubCitiId>"
				+ "<mItemId>39057</mItemId>"
				// + "<bottomBtnId>60</bottomBtnId>"
				+ "<zipCode>78654</zipCode>"
				+ "<platform>IOS</platform>" + "</HotDealsListRequest>";*/
		
		final String getHotDeallistXml2 = "<HotDealsListRequest><zipCode>78654</zipCode><userId>3</userId><hubCitiId>2166</hubCitiId><category>0</category><mainMenuId>2</mainMenuId><lastVisitedProductNo>0</lastVisitedProductNo></HotDealsListRequest>";

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", getHotDeallistXml2);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getHotDealsList URI : " + getHotDeallist2 );
			System.out.println("Request in getHotDealsList : " + getHotDeallistXml2);
			System.out.println("Response in getHotDealsList : " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

		// int seconds = (int) (milliseconds / 1000) % 60 ;
		// int minutes = (int) ((milliseconds / (1000*60)) % 60);
		// int hours = (int) ((milliseconds / (1000*60*60)) % 24);
	}

	static void removeHdProd() {
		final String removeHdProd = "http://localhost:8080/HubCiti1.0/hotdeals/removehdprod";
		final ClientRequest request = new ClientRequest(removeHdProd);
		final String removeHdProdXml = "<HotDealsListRequest>" + "<userId>2</userId>" + "<hotDealId>6600357</hotDealId>"
				+ "<hDInterested>1</hDInterested>" + "<hubCitiId>70</hubCitiId>" + "</HotDealsListRequest>";

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", removeHdProdXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in removeHdProd" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void userTrackingGetHotDealClick() {
		final String addHotDealToWishList = "http://localhost:8080/HubCiti1.0/hotdeals/utgethotdealclick?userId=2&hotDealId=6600357&hotDealListId=1";
		final ClientRequest request = new ClientRequest(addHotDealToWishList);

		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in userTrackingGetHotDealClick" + response);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	static void hotDealClaim() {
		final String addHotDealToWishList = "http://localhost:8080/HubCiti1.0/hotdeals/hotdealclaim";
		final ClientRequest request = new ClientRequest(addHotDealToWishList);
		final String addHotDealToWishListXml = "<HotDealsListRequest>" + "<userId>3</userId>" + "<hotDealId>6600355</hotDealId>"
				+ "<mainMenuId>344</mainMenuId>" + "</HotDealsListRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, addHotDealToWishListXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in hotDealClaim" + response);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	static void hotDealRedeem() {
		final String addHotDealToWishList = "http://localhost:8080/HubCiti1.0/hotdeals/hotdealredeem";
		final ClientRequest request = new ClientRequest(addHotDealToWishList);
		final String addHotDealToWishListXml = "<HotDealsListRequest>" + "<hotDealId>6600355</hotDealId>" + "<userId>3</userId>"
				+ "<mainMenuId>345</mainMenuId>" + "</HotDealsListRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, addHotDealToWishListXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in hotDealRedeem" + response);
		}
		catch (Exception exception) {
			exception.printStackTrace();
		}
	}


	public static void userDealsDisplay() {
		//		final String logon = "http://localhost:8080/HubCiti1.0/hotdeals/dealdisplay";
		final String logon = "http://66.228.143.28:8080/HubCiti1.8/hotdeals/dealdisplay";
		//		final String logon = "http://localhost:8080/HubCiti1.0/hotdeals/dealdisplay";
		//		final String logon =  "http://10.11.202.76:8080/HubCiti1.0/hotdeals/dealdisplay";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<Deal>" 
				+ "<userId>10036</userId>" 
				+ "<hubCitiId>10</hubCitiId>" 
				+ "<flag>false</flag>"  // 0/1
			/*	+ "<latitude>90.00</latitude>"  // 0/1
				+ "<longitude>180.00</longitude>"  // 0/1
				+ "<zipCode>75244</zipCode>"  // 0/1*/
				+ "</Deal>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("userDealsDisplay URI : " + logon);
			System.out.println("Request in userDealsDisplay" + inputXML);
			System.out.println("Response in userDealsDisplay" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}



	public static void userGalleryDisplay() {
		//		final String logon = "http://localhost:8080/HubCiti1.0/hotdeals/gallerydisplay";
		final String logon = "http://66.228.143.28:8080/HubCiti1.8/hotdeals/gallerydisplay";
		//		final String logon = "http://localhost:8080/HubCiti1.0/hotdeals/gallerydisplay";
		//		final String logon =  "http://10.11.202.76:8080/HubCiti1.0/hotdeals/gallerydisplay";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<Deal>" 
				+ "<userId>10036</userId>" 
				+ "<hubCitiId>10</hubCitiId>" 
				+ "<type>Claimed</type>"  //'Claimed',  --Used, Expired
				+ "<flag>false</flag>" // 0/1
				+ "</Deal>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("userGalleryDisplay URI : " + logon);
			System.out.println("Request in userGalleryDisplay" + inputXML);
			System.out.println("Response in userGalleryDisplay" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	public static void fundraisersList() {
		//		final String logon = "http://localhost:8080/HubCiti1.0/hotdeals/gallerydisplay";
		final String logon = "http://66.228.143.28:8080/HubCiti1.8/hotdeals/fundraiserslist";
		//		final String logon = "http://localhost:8080/HubCiti1.0/hotdeals/gallerydisplay";
		//		final String logon =  "http://10.11.202.76:8080/HubCiti1.0/hotdeals/gallerydisplay";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<MenuItem><userId>6819</userId><platform>ios</platform>" +
				"<postalCode><![CDATA[78654]]></postalCode><hubCitiId>2070</hubCitiId>" +
				"<lowerLimit>0</lowerLimit>" +
				"<groupBy>date</groupBy><sortBy>date</sortBy></MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("fundraisersList URI : " + logon);
			System.out.println("Request in fundraisersList : " + inputXML);
			System.out.println("Response in fundraisersList : " + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	public static void retailerClickImpression() {

		final String strRetclickimpression = "http://localhost:8080/HubCiti2.4.3/hotdeals/retclickimpression";

		String inputJSON = "{\"mainMenuId\":\"138\",\"catName\":\"Dining\",\"retailerId\":\"1771,1001\",\"retailLocationId\":\"93913,92945\"}";

		final ClientRequest request = new ClientRequest(strRetclickimpression);
		request.accept("application/json").body(MediaType.APPLICATION_JSON, inputJSON);
		request.getHeaders();
		try {

			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("retailerClickImpression URI : " + strRetclickimpression);
			System.out.println("Request in retailerClickImpression" + inputJSON);
			System.out.println("response in retailerClickImpression" + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

}
