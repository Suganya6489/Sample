package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

/**
 * This class for sending client request and response xmls.
 * 
 * @author Kumar D
 */
public class ScanNowRestEasyClient {
	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {
		// getProdForBarCode();
		// getSmartSearchProductlist();
		// userTrackingGetMainMenuID();
		// getSmartSearchCount();
//		getSmartSearchProducts();
		 getSmartSearchCount();
		// getscanhistory();
		// deletefromScanHistory();
		// searchAddProductForName();

	}

	/**
	 * This method for getting product for barcode.
	 */
	static void getProdForBarCode() {
		// final String getProdForBarCode =
		// "http://localhost:8080/HubCiti1.0/scannow/getprodforbarcode";
		final String getProdForBarCode = "http://199.36.142.83:8080/HubCiti1.0/scannow/getprodforbarcode";
		final ClientRequest request = new ClientRequest(getProdForBarCode);
		final String getProdForBarCodeXml = "<ProductDetail>" + "<deviceId>ef3a2b4de9f0c4cc32a5a4f5765d3c853ce654af</deviceId>"
				+ "<barCode><![CDATA[305213076003]]></barCode>" + "<scanType><![CDATA[upc-a]]></scanType>" + "<userId>22</userId>"
				+ "<mainMenuId>6</mainMenuId>" + "<scanTypeId>0</scanTypeId>" + "<hubCitiId>91</hubCitiId>"
				// + "<scanLatitude>0</scanLatitude>"
				// + "<scanLongitude>0</scanLongitude>"
				+ "</ProductDetail>";
		request.accept("text/xml").body(MediaType.TEXT_XML, getProdForBarCodeXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response  getProdForBarCode : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	/**
	 * This method for smart search for product name.
	 */
	static void getSmartSearchProductlist() {
		final String getProdForName = "http://199.36.142.83:8080/HubCiti1.0/scannow/getsmartsearchprodlist";
		// final String getProdForName =
		// "http://10.11.202.76:8080/HubCiti1.0/scannow/getsmartsearchprodlist";
		final ClientRequest request = new ClientRequest(getProdForName);
		final String getProdForBarCodeXml = "<ProductDetail>" + "<searchkey><![CDATA[baby food]]></searchkey>" + "<parCatId>0</parCatId>"
				+ "<lastVistedProductNo>0</lastVistedProductNo>" + "<hubCitiId>47</hubCitiId>" + "<userId>409</userId>" + "</ProductDetail>";
		request.accept("text/xml").body(MediaType.TEXT_XML, getProdForBarCodeXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getSmartSearchProducts() {
		// String getSmartSearchProducts =
		// "http://199.36.142.83:8080/HubCiti1.0/scannow/getsmartsearchprods";
		final String getSmartSearchProducts = "https://app.scansee.net/HubCiti1.0/scannow/getsmartsearchprods";
		// String getSmartSearchProducts =
		// "http://localhost:8080/HubCitiVQA/scannow/getsmartsearchprods";
		// String getSmartSearchProducts =
		// "http://199.36.142.83:8080/HubCiti1.0/scannow/getsmartsearchprods";
		ClientRequest request = new ClientRequest(getSmartSearchProducts);
		String inputXml = "<ProductDetail>" + "<userId>13</userId>" + "<searchkey><![CDATA[Baby Food]]></searchkey>" + "<mainMenuId>22</mainMenuId>"
				+ "<userId>25</userId>" + "<searchkey><![CDATA[harry potter]]></searchkey>" + "<mainMenuId>25</mainMenuId>"
				+ "<hubCitiId>10</hubCitiId>" + "</ProductDetail>";
		request.body(MediaType.TEXT_XML, inputXml);

		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getSmartSearchProducts : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void userTrackingGetMainMenuID() {
		String url = "http://localhost:8080/HubCiti1.0/scannow/utgetmainmenuid";
		ClientRequest request = new ClientRequest(url);
		String inputXml = "<UserTrackingData><userID>2</userID><moduleID>3</moduleID><postalCode><![CDATA[78752]]></postalCode></UserTrackingData>";

		request.body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("userTrackingGetMainMenuID : " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getSmartSearchCount() {
		 final String getProdForName = "http://10.11.202.76:8080/HubCiti1.5/scannow/getsmartsearchcount";
		// final String getProdForName =
		// "http://199.36.142.83:8080/HubCiti1.0/scannow/getsmartsearchcount";
//		final String getProdForName = "https://app.scansee.net/HubCiti1.0/scannow/getsmartsearchcount";
		final ClientRequest request = new ClientRequest(getProdForName);
		String strSearch = "mobile";
		// String keys [] = {"v", "i", "s", "i", "o", "n"};
		// StringBuilder strBu = new StringBuilder("tele");
		// for(int i = 0; i < keys.length; i++){
		// strBu.append(keys[i]);
		try {
			final String getProdForBarCodeXml = "<ProductDetail>" + "<searchkey><![CDATA[" + strSearch + "]]></searchkey>" + "<parCatId>0</parCatId>"
					+ "<lastVistedProductNo>0</lastVistedProductNo>" + "<mainMenuId>10735</mainMenuId>" + "<hubCitiId>90</hubCitiId>"
					// + "<prodSmaSeaID>1</prodSmaSeaID>"
					+ "</ProductDetail>";
			request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, getProdForBarCodeXml);
			request.getHeaders();
			long strtTime = new Date().getTime();
			String response = request.postTarget(String.class);
			long endtime = new Date().getTime();
			System.out.println((endtime - strtTime));
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("exception in HubCiti rest easy client..");
		}
	}

	static void getscanhistory() {
		final String getscanhistory = "http://localhost:8080/HubCiti1.0/scannow/getscanhistory?userId=2&lastVisitedRecord=0&hubCitiId=70";
		final ClientRequest request = new ClientRequest(getscanhistory);
		try {
			final String responseObj = request.getTarget(String.class);
			System.out.println("response " + responseObj);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void deletefromScanHistory() {
		String deleteWishList = "http://localhost:8080/HubCiti1.0/scannow/deletescanhistory";
		ClientRequest request = new ClientRequest(deleteWishList);
		String inputXml = "<ProductDetail><scanHistoryId>8</scanHistoryId><userId>2</userId></ProductDetail>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			String response = request.postTarget(String.class);
			System.out.println("Delete Wish List response : " + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	static void searchAddProductForName() {
		String deleteWishList = "http://localhost:8080/HubCiti1.0/scannow/getsearchprodforname";
		ClientRequest request = new ClientRequest(deleteWishList);
		String inputXml = "<ProductDetail>" + "<userId>2</userId>" + "<searchkey>baby food</searchkey>"
				+ "<lastVistedProductNo>0</lastVistedProductNo>" + "<hubCitiId>70</hubCitiId>" + "</ProductDetail>";

		request.accept("text/xml;charset=UTF-8").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			String response = request.postTarget(String.class);
			System.out.println("Delete Wish List response : " + response);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
