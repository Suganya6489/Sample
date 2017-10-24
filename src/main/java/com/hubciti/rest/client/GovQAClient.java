package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class GovQAClient {

	public static void main(String[] args) {

		// login();
		// createAccount();
//		 filterA();
		// filterB();
//		 faqs();
		 customerFields();
//		 customerInfo();
//		editAccount();

	}

	private static void editAccount() {
		final String logon = "http://66.228.143.28:8080/HubCiti2.3/govqa/editaccountinfo";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "{\"customerInfo\": {\"authKey\": \"ifF34jauK;\",\"loginEmail\":\"test1@gmail.com\",\"password\": \"234234\",\"firstName\": \"kiran\",\"lastName\": \"last\",\"phone\": \"8553887424\",\"groupAccessName\": \"\",\"custCFName1\": \"\",\"custCFValue1\": \"\",\"custCFName2\": \"\",\"custCFValue2\": \"\",\"custCFName3\": \"\",\"custCFValue3\": \"\",\"custCFName4\": \"\",\"custCFValue4\": \"\",\"custCFName5\": \"\",\"custCFValue5\": \"\"}}";
			
		

		request.accept(MediaType.APPLICATION_JSON).body(MediaType.APPLICATION_JSON, inputXML);

		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("Customr Fields URI : " + logon);
			System.out.println("Request in Customr Fields :" + inputXML);
			System.out.println("response in Customr Fields :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	private static void customerInfo() {
		final String logon = "http://66.228.143.28:8080/HubCiti2.3/govqa/customerinfo";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "{\"customer\":{\"authKey\":\"ifF34jauK;\",\"customerEmail\":\"pav@gmail.com\"}}";

		request.accept(MediaType.APPLICATION_JSON).body(MediaType.APPLICATION_JSON, inputXML);

		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("Customer Information URI : " + logon);
			System.out.println("Request in Customer Information :" + inputXML);
			System.out.println("response in Customer Information :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	private static void customerFields() {

		final String logon = "http://localhost:9990/HubCiti2.4/govqa/servicereqcustomfields";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "{\"govqa\":{\"authKey\":\"ifF34jauK;\",\"typeNo\":55}}";

		request.accept(MediaType.APPLICATION_JSON).body(MediaType.APPLICATION_JSON, inputXML);

		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("customerFields URI : " + logon);
			System.out.println("Request in customerFields :" + inputXML);
			System.out.println("response in customerFields :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	private static void filterB() {

		final String logon = "http://localhost:8080/HubCiti2.3/govqa/filterb";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "{\"authKey\" : \"ifF34jauK;\",\"parentID\" : 43}";

		request.accept(MediaType.APPLICATION_JSON).body(MediaType.APPLICATION_JSON, inputXML);

		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("filterB URI : " + logon);
			System.out.println("Request in filterB :" + inputXML);
			System.out.println("response in filterB :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	private static void faqs() {
		final String logon = "http://localhost:8080/HubCiti2.3/govqa/faqs";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "{\"authKey\":\"ifF34jauK;\",\"totalToReturn\":100}";

		request.accept(MediaType.APPLICATION_JSON).body(MediaType.APPLICATION_JSON, inputXML);

		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("faqs URI : " + logon);
			System.out.println("Request in faqs :" + inputXML);
			System.out.println("response in faqs :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	private static void login() {
		final String logon = "http://localhost:8080/HubCiti2.3/govqa/login";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "{  \"customer\": {    \"authKey\": \"ifF34jauK;\",    \"customerEmail\": \"sagar.byali@spanservices.com\",    \"password\": \"sagar\"  }}";

		request.accept(MediaType.APPLICATION_JSON).body(MediaType.APPLICATION_JSON, inputXML);

		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getFilterListDisplay URI : " + logon);
			System.out.println("Request in getOptionListDisplay :" + inputXML);
			System.out.println("response in getOptionListDisplay :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

	}

	/**
	 * Find information Category Listing
	 */
	private static void filterA() {
		final String logon = "http://localhost:9990/HubCiti2.4/govqa/filtera";

		final ClientRequest request = new ClientRequest(logon);

		String inputXML = "{\"authKey\":\"ifF34jauK;\"}";

		request.accept(MediaType.APPLICATION_JSON).body(MediaType.APPLICATION_JSON, inputXML);

		request.getHeaders();

		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("filterA URI : " + logon);
			System.out.println("Request in filterA :" + inputXML);
			System.out.println("response in filterA :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

	}

	private static void createAccount() {

	}

}
