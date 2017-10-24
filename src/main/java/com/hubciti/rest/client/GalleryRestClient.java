package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class GalleryRestClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {
		// CouponProductLocationDetails();
		// getAllCouponsByProduct();
//		 CouponProductLocationDetails();
		 getAllCouponsByProduct();
		// getCoupProductCategory();
//		 getAllCouponsByLocation();
		// getRetailerForBusinessCategory();
		// getCoupLocationBusinessCategory();
		// redeemCouponDetails();
		// addCoupon();
		// removeCoupon();
//		 getGalleryHotDealByLocation();
		// getGalleryHotDealByProduct();
//		getGalleryCouponsByLocation();
		// getGalleryCouponsByProduct();
//		 getHubCitiSpecialDeals();
//		 getSpecialOffRetDisplay();
		// getSalesRetDisplay();
	}

	static void CouponProductLocationDetails() {
		final String getGalleryInfo = "https://app.scansee.net/HubCiti2.0/gallery/getcouponproductorlocation";
		// final String getGalleryInfo =
		// "http://199.36.142.83:8080/HubCiti1.0/gallery/getcouponproductorlocation;
		final ClientRequest request = new ClientRequest(getGalleryInfo);
		final String getMyGalInfXml = "<CLRDetails>"
				+ "<userId>10019</userId>" // 218
				+ "<couponId>73923</couponId>" + "<couponListId>20</couponListId>" + "<mainMenuId>1</mainMenuId>" + "<hubCitiId>2070</hubCitiId>"
				+ "<type>location</type>" // product/location
				+ "</CLRDetails>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("CouponProductLocationDetails URI : " + getGalleryInfo );
			System.out.println("Request in CouponProductLocationDetails : " + getMyGalInfXml);
			System.out.println("Response in CouponProductLocationDetails : " + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getAllCouponsByProduct() {
		 final String getMyGalleryInfo =  "http://localhost:8080/HubCiti2.1/gallery/getallcoupbyprod";
//		final String getMyGalleryInfo = "https://app.scansee.net/HubCiti2.0/gallery/getallcoupbyprod";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);
		
		final String getMyGalInfXml = "<ProductDetailsRequest><userId>834</userId><lowerLimit>0</lowerLimit><postalcode>78654</postalcode><retailId>0</retailId>"+
		"<hubCitiId>10</hubCitiId><mainMenuId>93282</mainMenuId><busCatIds>0</busCatIds><platform>Android</platform></ProductDetailsRequest>";
		/*final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>834</userId>"
		// + "<searchKey></searchKey>"
		// + "<postalcode>78752</postalcode>"
				+ "<postalcode>78654</postalcode><retailId>0</retailId>"
				// + "<latitude>32.910347</latitude>"
				// + "<longitude>-96.728472</longitude>"
				+ "<catIds>0</catIds>" + "<lowerLimit>0</lowerLimit>" + "<hubCitiId>10</hubCitiId>" + "<mainMenuId>93282</mainMenuId>"
				// + "<mItemId>540</mItemId>"
				// + "<bottomBtnId>70</bottomBtnId>"
				+ "<platform>Android</platform>" + "</ProductDetailsRequest>";*/
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getAllCouponsByProduct URI : " + getMyGalleryInfo );
			System.out.println("Request in getAllCouponsByProduct : " + getMyGalInfXml);
			System.out.println("Response in getAllCouponsByProduct : " + response);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

	}

	static void getCoupProductCategory() {
		final String getMyGalleryInfo = "http://localhost:8080/HubCiti1.0/gallery/coupprodcat";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);

		final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>6</userId>"
		// + "<postalcode>75243</postalcode>"
		// + "<latitude>32.910347</latitude>"
		// + "<longitude>-96.728472</longitude>"
				+ "<hubCitiId>70</hubCitiId>" + "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response getCoupProductCategory" + response);
		}
		catch (Exception e) {
			System.out.println("exception in getCoupProductCategory : ");
		}
	}

	static void getRetailerForBusinessCategory() {
		// final String getMyGalleryInfo =
		// "http://localhost:8080/HubCiti1.0/gallery/retforbuscat";
		final String getMyGalleryInfo = "http://10.11.202.76:8080/HubCiti1.0/gallery/retforbuscat";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);

		final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>3</userId>" + "<postalcode>78728</postalcode>"
		// + "<latitude>32.910347</latitude>"
		// + "<longitude>-96.728472</longitude>"
				+ "<hubCitiId>91</hubCitiId>"
				// + "<busCatIDs>0</busCatIDs>"
				+ "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response getRetailerForBusinessCategory " + response);
		}
		catch (Exception e) {
			System.out.println("exception in getRetailerForBusinessCategory : ");
		}
	}

	static void getAllCouponsByLocation() {
		final String getMyGalleryInfo = "http://localhost:8080/HubCiti2.1/gallery/getallcoupbyloc";
		// final String getMyGalleryInfo =
		// "http://199.36.142.83:8080/HubCiti1.0/gallery/getallcoupbyloc";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);
		final String getMyGalInfXml = "<ProductDetailsRequest>"
				+ "<userId>6819</userId>"
				// + "<searchKey>vaishnavi</searchKey>"
				+ "<postalcode>75032</postalcode>"
//				+ "<latitude>30.57228</latitude>"
//				+ "<longitude>-98.306992</longitude>"
				+ "<retailId>0</retailId>" + "<busCatIds>0</busCatIds>" + "<lowerLimit>0</lowerLimit>" + "<hubCitiId>2070</hubCitiId>"
				+ "<mainMenuId>24713</mainMenuId>"
				// + "<mItemId>70</mItemId>"
				// + "<bottomBtnId>70</bottomBtnId>"
				+ "<platform>Android</platform>" + "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getAllCouponsByLocation URI : " + getMyGalleryInfo);
			System.out.println("Request in getAllCouponsByLocation : " + getMyGalInfXml);
			System.out.println("response in getAllCouponsByLocation : " + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getCoupLocationBusinessCategory() {
		// final String getMyGalleryInfo =
		// "http://localhost:8080/HubCiti1.0/gallery/couplocbuscat";
		final String getMyGalleryInfo = "http://10.11.202.76:8080/HubCiti1.0/gallery/couplocbuscat";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);

		final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>3</userId>" + "<postalcode>78728</postalcode>"
		// + "<latitude>32.910347</latitude>"
		// + "<longitude>-96.728472</longitude>"
				+ "<hubCitiId>91</hubCitiId>" + "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void addCoupon() {
		String URL = "http://localhost:8080/HubCiti1.0/gallery/addCoupon";
		String inputXml = "<CLRDetails>" + "<couponId>1</couponId>" + "<userId>3</userId>" + "</CLRDetails>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void redeemCouponDetails() {
		final String getMyGalleryInfo = "http://localhost:8080/HubCiti1.0/gallery/userredeemcoupon";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);
		final String getMyGalInfXml = "<CouponDetails>" + "<userId>3</userId>" + "<couponId>1</couponId>" + "</CouponDetails>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void removeCoupon() {
		String URL = "http://localhost:8080/HubCiti1.0/gallery/removeCoupon";
		String inputXml = "<CLRDetails>" + "<couponId>1</couponId>" + "<userId>3</userId>" + "</CLRDetails>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getGalleryHotDealByLocation() {

		final String getMyGalleryInfo = "http://localhost:8080/HubCiti1.8/gallery/gallhdbyloc";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);
		final String getMyGalInfXml = "<ProductDetailsRequest><userId>834</userId><mainMenuId>87535</mainMenuId><postalcode><![CDATA[78654]]></postalcode><hubCitiId>10</hubCitiId><lowerLimit>0</lowerLimit><type>Expired</type></ProductDetailsRequest>";
		/*final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>1919</userId>"
		// + "<searchKey></searchKey>"
				+ "<lowerLimit>0</lowerLimit>" + "<mainMenuId>1</mainMenuId>" + "<type>Used</type>" + "<hubCitiId>87</hubCitiId>"
				// +"   <latitude></latitude>"
				// +"  <longitude></longitude>"
				// +"   <postalcode></postalcode>"
				+ "</ProductDetailsRequest>";*/
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}

	}

	static void getGalleryHotDealByProduct() {
		final String getMyGalleryInfo = "http://localhost:8080/HubCitiVQA/gallery/gallhdbyprod";
		// final String getMyGalleryInfo =
		// "http://199.36.142.83:8080/HubCiti1.0/gallery/gallhdbyprod";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);
		final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>651</userId>"
		// + "<searchKey></searchKey>"
				+ "<lowerLimit>0</lowerLimit>" + "<mainMenuId>1</mainMenuId>" + "<type>Claimed</type>" + "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void getGalleryCouponsByLocation() {
		// 199.36.142.83
		// final String getMyGalleryInfo =
		// "http://199.36.142.83:8080/HubCiti1.0/gallery/gallcoupbyloc";
		final String getMyGalleryInfo = "http://10.11.202.76:8080/HubCiti1.0/gallery/gallcoupbyloc";
		// final String getMyGalleryInfo =
		// "http://localhost:8080/HubCiti1.0/gallery/gallcoupbyloc";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);
		final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>399</userId>"
		// + "<searchKey>Coupon</searchKey>"
				+ "<lowerLimit>0</lowerLimit>"
				// + "<mainMenuId>344</mainMenuId>"
				+ "<type>Expired</type>"// Claimed/Used/Expired
				+ "  <hubCitiId>91</hubCitiId>"
				// + "  <latitude></latitude>"
				// + "  <longitude></longitude>"
				+ "  <postalode>78654</postalcode>" + "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	static void getGalleryCouponsByProduct() {
		// final String getMyGalleryInfo =
		// "http://10.11.202.76:8080/HubCiti1.0/gallery/gallcoupbyprod";
		final String getMyGalleryInfo = "http://localhost:8080/HubCiti1.0/gallery/gallcoupbyprod";
		final ClientRequest request = new ClientRequest(getMyGalleryInfo);
		final String getMyGalInfXml = "<ProductDetailsRequest>" + "<userId>3</userId>"
		// + "<searchKey>coupon</searchKey>"
				+ "<lowerLimit>0</lowerLimit>" + "<type>Used</type>" // Claimed/Used/Expired
				+ "<mainMenuId>45</mainMenuId>" + "</ProductDetailsRequest>";
		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getMyGalInfXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in scansee rest easy client..");
		}
	}

	public static void getHubCitiSpecialDeals() {
		// final String logon =
		// "http://localhost:8080/HubCiti1.0/gallery/hubcitispedeals";
		final String logon = "http://localhost:8080/HubCiti1.2/gallery/hubcitispedeals";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<ProductDetailsRequest>" + "<userId>1817</userId>" + "<hubCitiId>90</hubCitiId>" 
		//+ " <postalcode>78728</postalcode>"
		+"<latitude>72.856200</latitude>"  
		+"<longitude>19.017600</longitude>"
		+ "</ProductDetailsRequest>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getHubCitiSpecialDeals :" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getSpecialOffRetDisplay() {
		//final String logon = "http://10.11.202.76:8080/HubCiti1.0/gallery/getspecialofferretdisp";
		final String logon = "http://localhost:8080/HubCiti1.7/gallery/getspecialofferretdisp";
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.0/gallery/getspecialofferretdisp";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<ThisLocationRequest>" + "<userId>4416</userId>" + "<hubCitiId>1143</hubCitiId>" + "<lowerLimit>0</lowerLimit>" +
		// "<latitude>30.36485</latitude>" +
		// "<longitude>-97.682658</longitude>" +
//				"<postalCode>78654</postalCode>" + 
		"</ThisLocationRequest>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getSpecialOffRetDisplay URI : " + logon );
			System.out.println("Request in getSpecialOffRetDisplay" + inputXML);
			System.out.println("response in getSpecialOffRetDisplay" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getSalesRetDisplay() {
		final String logon = "http://localhost:8080/HubCiti1.0/gallery/getsalesretdisplay";
		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "<ThisLocationRequest>" + "<userId>6</userId>" + "<hubCitiId>70</hubCitiId>" + "<lowerLimit>0</lowerLimit>" +
		// + "<latitude></latitude>"
		// + "<longitude></longitude>"
				"</ThisLocationRequest>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getSalesRetDisplay" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
}
