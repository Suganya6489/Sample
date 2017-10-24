
package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class RateReviewRestClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {
		// getProductRateReview();
		// getProdForBarCode();
		// testSaveUserProductRating();
		//		 testShareProductRetailerInfo();
		// shareCLRInfo();
//				 ShareProductInfo();
		// shareHotDealInfo();
		// shareHotdealthruEmailInfo();4174417441744174417441744174mobile app mobile app mobile app mobile app 
		// shareCouponthruEmailInfo();
		//		 shareAppSite();
//				 shareAppSiteByEmail();
//				 sharespecialoffByEmail();
		//		 sharespecialoffshare();
//		 shareEventByEmail();

		//		 shareEvent();
		//		 tracksharedetail();

		//shareEvent();
//				shareFundraiserByEmail();
		//		shareFundraiser();
//				senderShareTracking();
		//		receiverShareTracking();
		
//		shareCouponByEmail();
//		shareCoupon();
//		shareHotDeal();
//		shareHotDealByEmail();
//		shareAppLinkByEmail();
		
	//	shareBand();
	//	shareBandByEmail();
	//	shareBandEvent();
		shareBandEvetByEmail();
	}
	
	private static void shareFundraiserByEmail() {
		
		final String URL = "http://localhost:8080/HubCiti2.2/ratereview/sharefundraiseremail";
		
		final String inputXml = "<ShareProductInfo>"
									+"<platform>IOS</platform>"
									+"<fundraiserId>3080</fundraiserId>"
									+"<hubCitiId>82</hubCitiId>"
									+"<userId>17</userId>"
								+"</ShareProductInfo>";

		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("shareFundraiserByEmail URI : " + URL);
			System.out.println("Request in shareFundraiserByEmail" + inputXml);
			System.out.println("response in shareFundraiserByEmail : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in hubciti rest easy client..");
		}
		
	}

	// new client request for share event details by email
	private static void shareEventByEmail() {
		
		final String URL = "http://localhost:8080/HubCiti2.1/ratereview/shareeventemail";
		
		final String inputXml = "<ShareProductInfo>"
											+"<platform>IOS</platform>"
											+"<eventId>197</eventId>"
											+"<hubCitiId>82</hubCitiId>"
											+"<userId>14</userId>"
										+"</ShareProductInfo>";

		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("testShareProductRetailerInfo URI : " + URL);
			System.out.println("Request in testShareProductRetailerInfo" + inputXml);
			System.out.println("response in shareEventByEmail : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in hubciti rest easy client..");
		}
		
	}

	static void getProductRateReview() {
		final String getProducts = "http://localhost:8080/HubCiti2.2/ratereview/fetchuserrating";
		final ClientRequest request = new ClientRequest(getProducts);
		final String getProductsXML2 = "<ProductDetailsRequest>" + "<userId>3</userId>" + "<productId>931343</productId>"
				+ "<hubCitiId>32</hubCitiId>" + "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getProductsXML2);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getProductRateReview : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}


	static void testSaveUserProductRating() {
		String URL = "http://localhost:8080/HubCiti1.0/ratereview/saveuserrating";
		String SaveUserProductRating = "<UserRatingInfo>" + "<userId>2</userId>" + "<productId>2</productId>" + "<currentRating>5</currentRating>"
				+ "</UserRatingInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml").body(MediaType.TEXT_XML, SaveUserProductRating);
		request.getHeaders();
		try {
			String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void testShareProductRetailerInfo() {
		String URL = "http://localhost:8080/HubCiti1.7/ratereview/shareproductretailerinfo";

		String inputXml = "<ShareProductInfo>"
				//				+ "<isFromThisLocation>1</isFromThisLocation>"
				+ "<productId>2590758</productId>" + "<userId>12148</userId>" +
				//				"<retailerId>20</retailerId>" + "<retailerLocationId>3872</retailerLocationId>" +
				"<hubCitiId>90</hubCitiId>" +
				"</ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("testShareProductRetailerInfo URI : " + URL);
			System.out.println("Request in testShareProductRetailerInfo" + inputXml);
			System.out.println("response in testShareProductRetailerInfo" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void shareCLRInfo() {
		final String getHdProdInfo = "http://localhost:8080/HubCiti1.0/ratereview/shareclrinfo?couponid=7&hubCitiId=70";
		final ClientRequest request = new ClientRequest(getHdProdInfo);
		try {
			final String responseObj = request.getTarget(String.class);
			System.out.println("response in getCouponInfo" + responseObj);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void shareHotDealInfo() {
		final String getHdProdInfo = "http://localhost:8080/HubCiti1.0/ratereview/sharehotdealinfo?hotdealId=6600357&hubCitiId=70";
		final ClientRequest request = new ClientRequest(getHdProdInfo);
		try {
			final String responseObj = request.getTarget(String.class);
			System.out.println("response in getCouponInfo" + responseObj);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void ShareProductInfo() {
		String URL = "http://localhost:8080/HubCiti2.2/ratereview/shareProductInfo";
		String inputXml = "<ShareProductInfo>" //+ "<toEmail>kumar_dodda@spanservices.com</toEmail>" 
				//+ "<isFromThisLocation>0</isFromThisLocation>"
				+ "<productId>1</productId>" + "<userId>14</userId>" +
				//"<retailerId>20</retailerId>" + "<retailerLocationId>3872</retailerLocationId>" +
				"<hubCitiId>70</hubCitiId> <platform>ANDROID</platform>" +
				"</ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("ShareProductInfo URI : " + URL);
			System.out.println("Request in ShareProductInfo" + inputXml);
			System.out.println("response in ShareProductInfo" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}


	static void shareAppSiteByEmail() {
		String URL = "http://localhost:8080/HubCiti2.1/ratereview/shareappsiteemail";

		String inputXml = "<ShareProductInfo>" /*+ "<toEmail>kumar_dodda@spanservices.com</toEmail>"*/
				//		+"<shareType>email</shareType>"
				+ "<retailerId>31</retailerId>" + "<retailerLocationId>6155</retailerLocationId>" + "<hubCitiId>31</hubCitiId>"
				+ "<userId>14</userId> <platform>IOS</platform>" + "</ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareAppSiteByEmail URI : " + URL);
			System.out.println("Request in shareAppSiteByEmail" + inputXml);
			System.out.println("response in shareAppSiteByEmail" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void shareAppSite() {
		String URL = "http://66.228.143.28:8080/HubCiti1.7/ratereview/shareappsite";
		String inputXml = "<ShareProductInfo><userId>1663</userId><retailerId>108110</retailerId><retailerLocationId>2082936</retailerLocationId><hubCitiId>8</hubCitiId></ShareProductInfo>";
		/*"<ShareProductInfo>" + "<userId>2999</userId><retailerId>796</retailerId>"
//				"<shareType>Facebook</shareType>"
				+ "<retailerLocationId>92034</retailerLocationId>" + "<hubCitiId>90</hubCitiId>" + "</ShareProductInfo>";*/

		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();

		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareAppSite URI : " + URL);
			System.out.println("Request in shareAppSite" + inputXml);
			System.out.println("response in shareAppSite" + response);
		} catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void shareAppLinkByEmail() {
		//String URL = "http://10.11.202.76:8080/HubCiti1.0/ratereview/shareapplink";
		String URL = "http://localhost:8080/HubCiti2.1/ratereview/shareapplink";
		// String URL =
		// "http://199.36.142.83:8080/HubCiti1.0/ratereview/shareapplink";
		/*String inputXml = "<ShareProductInfo><shareType>email</shareType>"
				+ "<hubCitiId>91</hubCitiId><userId>5</userId><toEmail>dhruvanath_mm@spanservices.com</toEmail><platform>IOS</platform></ShareProductInfo>";*/
		String inputXml = "<ShareProductInfo><shareType>email</shareType>"
							+"<hubCitiId>17</hubCitiId><userId>5</userId><platform>ANDROID</platform></ShareProductInfo>"; 
		
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareAppLinkByEmail URI : " + URL);
			System.out.println("Request in shareAppLinkByEmail" + inputXml);
			System.out.println("response in shareAppLinkByEmail" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}


	static void sharespecialoffByEmail() {
		String URL = "http://localhost:8080/HubCiti2.2/ratereview/sharespecialoffemail";
		String inputXml = "<ShareProductInfo>" + "<retailerId>234</retailerId><pageId>234</pageId>"
				+ "<userId>234</userId><hubCitiId>234</hubCitiId><platform>IOS</platform></ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("sharespecialoffByEmail URI : " + URL);
			System.out.println("Request in sharespecialoffByEmail" + inputXml);
			System.out.println("response in sharespecialoffByEmail" + response);
		} catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}


	static void sharespecialoffshare() {
		String URL = "http://localhost:8080/HubCiti1.7/ratereview/sharespecialoff";
		/*String inputXml = "<ShareProductInfo>" + "<retailerId>1232</retailerId><pageId>41</pageId>"
				+ "<userId>2999</userId><hubCitiId>90</hubCitiId></ShareProductInfo>";*/
		String inputXml = "<UserTrackingData><mainMenuId>22808</mainMenuId><shrTypNam>Email</shrTypNam><aPageId>6705</aPageId><tarAddr>keerthi.chinivar@spanservices.com;</tarAddr><retId>1109478</retId><retLocId>2086135</retLocId></UserTrackingData>";
//		String inputXml = "<ShareProductInfo><retailerId>1109478</retailerId><pageId>12924</pageId><userId>6819</userId><hubCitiId>2070</hubCitiId></ShareProductInfo>";

		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("sharespecialoffshare URI : " + URL);
			System.out.println("Request in sharespecialoffshare" + inputXml);
			System.out.println("response in sharespecialoffshare" + response);
		} catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}


	static void tracksharedetail() {
		String URL = "http://localhost:8080/HubCiti1.7/ratereview/sharetracking";
		String inputXml = "<ShareProductInfo><pageId>50</pageId>"
				+ "<userId>2015</userId><qrTypeCode>2200</qrTypeCode></ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("tracksharedetail URI : " + URL);
			System.out.println("Request in tracksharedetail" + inputXml);
			System.out.println("response in tracksharedetail" + response);
		} catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}



	static void senderShareTracking()	{

		String URL = "http://localhost:8080/HubCiti1.7/ratereview/sendersharetrack";
//		String URL = "http://localhost:8080/HubCiti1.7/ratereview/sendersharetrack";
		String inputXml = "<UserTrackingData><mainMenuId>22808</mainMenuId><shrTypNam>Email</shrTypNam><aPageId>6705</aPageId><tarAddr>keerthi.chinivar@spanservices.com;</tarAddr><retId>1109478</retId><retLocId>2086135</retLocId></UserTrackingData>";
		/*String inputXml = "<UserTrackingData>"
				+ "<mainMenuId>22768</mainMenuId>"
				+ "<shrTypNam>Email</shrTypNam>"
				+ "<tarAddr>keshavakarthik.s@spanservices.com</tarAddr>"
				+ "<sPageId>6711</sPageId>"*/
//				+ "<aPageId>3135</aPageId>"
//				+ "<eventId>2171</eventId>"
				//							+ "<prodId>1</prodId>"
				/*		+ "<coupId>1</coupId>"
							+ "<retLocId>92955</retLocId>"
							+ "<hotDealId>1</hotDealId>"
							+ "<sPageId>6711</sPageId>"
							+ "<aPageId>2</aPageId>"
							+ "<retId>2</retId>"
							+ "<retLocId>2</retLocId>"


							+ "<fundId>6</fundId>" */
							/*+ "</UserTrackingData>";*/

		//		String shareTypes = "Facebook,Twitter,Email,Text";

		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();

		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("senderShareTracking URI : " + URL);
			System.out.println("senderShareTracking in shareEvent  " + inputXml);
			System.out.println("senderShareTracking in shareEvent  " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}

	}

	static void receiverShareTracking()	{

		String URL = "http://localhost:8080/HubCiti1.7/ratereview/receiversharetrack";

		String inputXml = "<ShareProductInfo>" + "<qrTypeCode>1000</qrTypeCode> <pageId>2</pageId>" + 
				"<hubCitiId>17</hubCitiId>"	+ "<userId>11</userId>" + "</ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();

		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("receiverShareTracking URI : " + URL);
			System.out.println("Request in receiverShareTracking" + inputXml);
			System.out.println("response in receiverShareTracking" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}

	}


	static void shareHotDealByEmail() {                           
		String URL = "http://localhost:8080/HubCiti2.1/ratereview/sharehotdealbyemail";

		String inputXml = "<ShareProductInfo>"+"<hotdealId>1201010</hotdealId> <platform>Android</platform>" + 
				"<hubCitiId>13</hubCitiId>"	+ "<userId>14</userId>" + "</ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareHotDealByEmail URI : " + URL);
			System.out.println("Request in shareHotDealByEmail" + inputXml);
			System.out.println("response in shareHotDealByEmail" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void shareHotDeal() {
		String URL = "http://localhost:8080/HubCiti1.7/ratereview/sharehotdeal";

		String inputXml = "<ShareProductInfo>" + "<hotdealId>1436923</hotdealId>" + 
				"<hubCitiId>90</hubCitiId>"	+ "<userId>4</userId>" + "</ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareHotDeal URI : " + URL);
			System.out.println("Request in shareHotDeal" + inputXml);
			System.out.println("response in shareHotDeal" + response);
		} catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}
	
	
	static void shareCouponByEmail() {
		//String URL = "http://localhost:8080/HubCiti2.2/ratereview/sharecouponbyemail";
		String URL ="http://localhost:8080/HubCiti2.2/ratereview/sharecouponbyemail";
/*		String inputXml = "<ShareProductInfo>" + "<toEmail>kumar_dodda@spanservices.com</toEmail>" + "<couponId>65999</couponId>" + 
				"<hubCitiId>90</hubCitiId>"	+ "<userId>4</userId>" + "</ShareProductInfo>";*/
		
		/*String inputXml = "<ShareProductInfo>" + "<couponId>65999</couponId>" + 
				"<hubCitiId>90</hubCitiId>"	+ "<userId>4</userId>" + "</ShareProductInfo>";*/
		
		String inputXml = "<ShareProductInfo><couponId>1143</couponId><hubCitiId>70</hubCitiId><userId>11</userId><platform>IOS</platform></ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareCouponByEmail URI : " + URL);
			System.out.println("Request in shareCouponByEmail" + inputXml);
			System.out.println("response in shareCouponByEmail" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void shareCoupon() {
		String URL = "http://localhost:8080/HubCiti1.7/ratereview/sharecoupon";

		String inputXml = "<ShareProductInfo>" + "<couponId>65999</couponId>" + 
				"<hubCitiId>90</hubCitiId>"	+ "<userId>4</userId>" + "</ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareCoupon URI : " + URL);
			System.out.println("Request in shareCoupon : " + inputXml);
			System.out.println("response in shareCoupon : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}
	
	static void shareBandByEmail() {
		String URL = "http://localhost:8080/HubCiti2.6/ratereview/sharebandemail";

		String inputXml = "<ShareProductInfo><userId>18130</userId><bandId>2</bandId><hubCitiId>10266</hubCitiId><platform>ANDROID</platform></ShareProductInfo>";
		
		ClientRequest request = new ClientRequest(URL);
		
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareBandByEmail URI : " + URL);
			System.out.println("Request in shareBandByEmail" + inputXml);
			System.out.println("response in shareBandByEmail" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}
	
	static void shareBand() {
		String URL = "http://localhost:8080/HubCiti2.6/ratereview/shareband";
		String inputXml = "<ShareProductInfo><userId>18130</userId><bandId>2</bandId><hubCitiId>10266</hubCitiId><platform>ANDROID</platform></ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareBand URI : " + URL);
			System.out.println("Request in shareBand" + inputXml);
			System.out.println("response in shareBand" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}
	
	static void shareBandEvent() {
		String URL = "http://localhost:8080/HubCiti2.6/ratereview/sharebandevent";
		String inputXml = "<ShareProductInfo><platform>IOS</platform><eventId>9</eventId><hubCitiId>82</hubCitiId><userId>1</userId></ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareBand URI : " + URL);
			System.out.println("Request in shareBand" + inputXml);
			System.out.println("response in shareBand" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}
	
	static void shareBandEvetByEmail() {
		String URL = "http://localhost:8080/HubCiti2.6/ratereview/sharebandeventemail";
		String inputXml = "<ShareProductInfo><platform>IOS</platform><eventId>9</eventId><hubCitiId>82</hubCitiId><userId>1</userId></ShareProductInfo>";
		ClientRequest request = new ClientRequest(URL);
		
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("shareBandEvetByEmail URI : " + URL);
			System.out.println("Request in shareBandEvetByEmail" + inputXml);
			System.out.println("response in shareBandEvetByEmail" + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}
	

}
