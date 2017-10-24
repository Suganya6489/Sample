package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class WebQAClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {

//		authenticatePortalUser();
//		getMobileServiceRequestTypes();
//		addServiceRequestNote();
		
		createServiceRequestTypeID();
//		getServiceRequestCategories();
//		getServiceRequestsByCustomer();
//		updateRequestCustomField();
//		getServiceRequestByReferenceNo();
//		deleteServiceRequestsByCustomer();
//		deleteMobileServiceRequestTypes();
		
	}

	
	static void authenticatePortalUser() {
		final String strURI = "http://localhost:8080/HubCiti_Cache/govqa/authPortalUser";
//		final String strInputReq = "<UserRegistrationInfo><email>sivaramprasad_k@spanservices.com</email><password>kumar</password> <hcKey>ifF34jauK;</hcKey></UserRegistrationInfo>";
		final String strJsonReq = "{ \"email\": \"sivaramprasad_k@spanservices.com\", \"password\": \"kuma\"}";
//		final String strJsonReq = "{ \"password\": \"kumar\", \"hcKey\": \"ifF34jauK;\" }";
		final ClientRequest request = new ClientRequest(strURI);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			final String strResponse = request.postTarget(String.class);
			System.out.println("URI in authenticatePortalUser --> " + strURI);
			System.out.println("Request in authenticatePortalUser --> " + strJsonReq);
			System.out.println("response in authenticatePortalUser --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
    }  
	
	
	
	static void addServiceRequestNote() {
		final String strURI = "http://localhost:8080/HubCiti_Cache/govqa/addservicereqnote";
		final String strJsonReq = "{ \"reqTypeId\": 31, \"note\": \"sivaramprasad_k@spanservices.com\" }";
		final ClientRequest request = new ClientRequest(strURI);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			final String strResponse = request.postTarget(String.class);
			System.out.println("URI in addServiceRequestNote --> " + strURI);
			System.out.println("Request in addServiceRequestNote --> " + strJsonReq);
			System.out.println("response in addServiceRequestNote --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
    }
	
	
	static void createServiceRequestTypeID() {
		final String strURI = "http://localhost:9990/HubCiti2.4.2/govqa/createservicereq";
//		final String strURI = "http://66.228.143.28:8080/HubCiti_Perf/govqa/createservicereq";
	/*	final String strJsonReq = "{\"srTypeId\": 52,"
			                         +"\"email\": \"sivaramprasad_k@spanservices.com\","
			                         +"\"customerId\": 1421,"
//			                         +"\"customFieldData\": [ "
									+"[ "
			                         +"{\"fldNo\" : 55, \"name\" : \"Location of Car\", \"prompt\" : \"Location of Car\", \"required\" : true , \"fldValue\" :\"Location of Car\" },"
									 +"{\"fldNo\" : 56 , \"name\" : \"Type of car\", \"prompt\" : \"Type of car\", \"required\" : true, \"fldValue\" :\"Type of car\" },"
			                         +"{\"fldNo\" : 57, \"name\" : \"Color of car\", \"prompt\" : \"Color of car\", \"required\" : true, \"fldValue\" :\"Color of car\" },"
									 +"{\"fldNo\" : 58, \"name\" : \"License plate number\", \"prompt\" : \"License plate number\", \"required\" : true, \"fldValue\" :\"License plate number\" }"
			                        + "]"
			                         + "}";*/
		
		/*final String strJsonReq = "{\"srTypeId\": 52,\"email\": \"sivaramprasad_k@spanservices.com\",\"customerId\": 1421,"
                +"\"customFieldData\": [ "
				+"{\"fldNo\" : \"55\", \"name\" : \"Location of Car\", \"prompt\" : \"Location of Car\", \"required\" : true, \"fldValue\" :\"Location of Car\" },"
				 +"{\"fldNo\" : \"56\", \"name\" : \"Type of car\", \"prompt\" : \"Type of car\", \"required\" : true, \"fldValue\" :\"Type of car\" },"
                +"{\"fldNo\" : \"57\", \"name\" : \"Color of car\", \"prompt\" : \"Color of car\", \"required\" : true, \"fldValue\" :\"Color of car\" },"
				 +"{\"fldNo\" :\"58\", \"name\" : \"License plate number\", \"prompt\" : \"License plate number\", \"required\" : true, \"fldValue\" :\"License plate number\" }"
               + "]"
                + "}";*/
		
		/*by TypeID*/
		/*final String strJsonReq = "{\"srTypeId\": 52,\"email\": \"bindu.m@spanservices.com\",\"first\": \"sivaramprasad_k\",\"last\": \"sivaramprasad\",\"phone\": \"1234567890\",\"addressOne\": \"4811 Arapaho Road\",\"addressTwo\": \"4811 Arapaho Road\",\"city\": \"city\",\"state\": \"state\",\"zip\": \"75244\",\"srType\": \"Illegal Parking\",\"Id\": 0,\"customerId\":1440,"
                +"\"customFieldData\": [ "
				+"{\"fldNo\" : \"55\", \"name\" : \"Location of Car\", \"prompt\" : \"Location of Car\", \"required\" : true, \"fldValue\" :\"Location of Car\" },"
				 +"{\"fldNo\" : \"56\", \"name\" : \"Type of car\", \"prompt\" : \"Type of car\", \"required\" : true, \"fldValue\" :\"Type of car\" },"
                +"{\"fldNo\" : \"57\", \"name\" : \"Color of car\", \"prompt\" : \"Color of car\", \"required\" : true, \"fldValue\" :\"Color of car\" },"
				 +"{\"fldNo\" :\"58\", \"name\" : \"License plate number\", \"prompt\" : \"License plate number\", \"required\" : true, \"fldValue\" :\"License plate number\" }"
               + "]"
                + "}";*/
		/*by TypeName*/
		/*final String strJsonReq = "{\"srType\": \"Illegal Parking\",\"email\": \"sivaramprasad_k@spanservices.com\",\"customerId\": 1421,"
                +"\"customFieldData\": [ "
				+"{\"fldNo\" : \"55\", \"name\" : \"Location of Car\", \"prompt\" : \"Location of Car\", \"required\" : true, \"fldValue\" :\"Location of Car\" },"
				 +"{\"fldNo\" : \"56\", \"name\" : \"Type of car\", \"prompt\" : \"Type of car\", \"required\" : true, \"fldValue\" :\"Type of car\" },"
                +"{\"fldNo\" : \"57\", \"name\" : \"Color of car\", \"prompt\" : \"Color of car\", \"required\" : true, \"fldValue\" :\"Color of car\" },"
				 +"{\"fldNo\" :\"58\", \"name\" : \"License plate number\", \"prompt\" : \"License plate number\", \"required\" : true, \"fldValue\" :\"License plate number\" }"
               + "]"
                + "}";*/
		
		final String strJsonReq = "{\"id\": 52,\"lAddress\": \"Service address 15\",\"contactEmail\": \"shernandez@scansee.com\",\"first\": \"Sergio\",\"last\": \"Sergio\",\"phone\": \"1234567890\",\"addressOne\": \"customer address Constant\",\"addressTwo\": \"customer address Constant address2\",\"city\": \"city\",\"state\": \"state\",\"zip\": \"75244\",\"zipCode\": \"75244\",\"srType\": \"Illegal Parking\",\"customerId\":1606,"
			    +"\"customFieldData\": [ "
				+"{\"fldNo\" : \"55\", \"name\" : \"Location of Car\", \"prompt\" : \"Location of Car\", \"required\" : true, \"fldValue\" :\"Location of Car\" },"
				 +"{\"fldNo\" : \"56\", \"name\" : \"Type of car\", \"prompt\" : \"Type of car\", \"required\" : true, \"fldValue\" :\"Type of car\" },"
			    +"{\"fldNo\" : \"57\", \"name\" : \"Color of car\", \"prompt\" : \"Color of car\", \"required\" : true, \"fldValue\" :\"Color of car\" },"
				 +"{\"fldNo\" :\"58\", \"name\" : \"License plate number\", \"prompt\" : \"License plate number\", \"required\" : true, \"fldValue\" :\"License plate number\" }"
			   + "]"
			    + "}";
		
//		final String strJsonReq ="{"id": 51,"name": "Alarm Permitting","title": "Alarm Permitting","contactEmail": "sample@gmail.com","addressOne": "Service Address 1","addressTwo": "Service Address 2","city": "scity","state": "TX","zipCode": "75246","customFieldData": [ {"fldNo" : "51", "name" : "alarm holder's contact information", "prompt" : "contact information", "required" : true, "fldValue" :"contact information"},{"fldNo" : "52", "name" : "description of premises", "prompt" : "description of premises", "required" : true, "fldValue" :"description of premises" },{"fldNo" : "53", "name" : "emergency contact information", "prompt" : "emergency contact information", "required" : true, "fldValue" :"emergency contact information"},{"fldNo" :"54", "name" : "alarm company contact information", "prompt" : "alarm company contact information", "required" : true, "fldValue" :"alarm company contact information"}]}"
				
				
				final String strJsonReq2 = "{\"id\": 51," +
						"\"name\": \"Animal Registration\"," +
						"\"title\": \"Animal Registration\"," +
						"\"contactEmail\": \"sample@gmail.com\"," +
						"\"addressOne\": \"Service Address 1\"," +
						"\"addressTwo\": \"Service Address 2\"," +
						"\"city\": \"scity\"," +
						"\"state\": \"TX\"," + //sTX  --> will not insert to the GovQA System.
						"\"zipCode\": \"75246\","+

			        "\"customFieldData\": [ "
					+"{\"fldNo\" : \"51\", \"name\" : \"alarm holder's contact information\", \"prompt\" : \"contact information\", \"required\" : true, \"fldValue\" :\"contact information\"},"
					 +"{\"fldNo\" : \"52\", \"name\" : \"description of premises\", \"prompt\" : \"description of premises\", \"required\" : true, \"fldValue\" :\"description of premises\" },"
			        +"{\"fldNo\" : \"53\", \"name\" : \"emergency contact information\", \"prompt\" : \"emergency contact information\", \"required\" : true, \"fldValue\" :\"emergency contact information\"},"
					 +"{\"fldNo\" :\"54\", \"name\" : \"alarm company contact information\", \"prompt\" : \"alarm company contact information\", \"required\" : true, \"fldValue\" :\"alarm company contact information\"}"
			       + "]"+
				
				
			        "}";
		
		final ClientRequest request = new ClientRequest(strURI);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		
		try {
			final String strResponse = request.postTarget(String.class);
			System.out.println("URI in createServiceRequestTypeID --> " + strURI);
			System.out.println("Request in createServiceRequestTypeID --> " + strJsonReq);
			System.out.println("response in createServiceRequestTypeID --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
    }
	
	
	static void getServiceRequestCategories() {

		final String strURI = "http://localhost:9990/HubCiti2.4/govqa/getservicereqcategories";
		final ClientRequest request = new ClientRequest(strURI);

		try {
			long strtTime = new Date().getTime();
			final String strResponse = request.getTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("getServiceRequestCategories  URI :  " + strURI);
			System.out.println("response in getServiceRequestCategories " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	
	static void updateRequestCustomField() {
		final String strURI = "http://66.228.143.28:8080/HubCiti_Cache/govqa/updatereqcustfield";
		final String strJsonReq = "{ \"referNum\": \"W004425-112015\", \"fldName\": \"alarm holder's contact information\" , \"fldValue\": \"alarm holder's contact information\" }";
		final ClientRequest request = new ClientRequest(strURI);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			final String strResponse = request.postTarget(String.class);
			System.out.println("URI in addServiceRequestNote --> " + strURI);
			System.out.println("Request in addServiceRequestNote --> " + strJsonReq);
			System.out.println("response in addServiceRequestNote --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
    }
	
	static void getServiceRequestByReferenceNo() {
		final String strURI = "http://localhost:9990/HubCiti2.4/govqa/getreqbyreferno";
		final String strJsonReq = "{ \"referNum\": \"W004022-020816\"}";
		final ClientRequest request = new ClientRequest(strURI);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			final String strResponse = request.postTarget(String.class);
			System.out.println("URI in getServiceRequestByReferenceNo --> " + strURI);
			System.out.println("Request in getServiceRequestByReferenceNo --> " + strJsonReq);
			System.out.println("response in getServiceRequestByReferenceNo --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
    }
	
	
	
	static void getServiceRequestsByCustomer() {
//		String strJsonReq = "{ \"email\": \"Npham@addisontx.gov\", \"filterName\":\"Completed\"}";	
		String strJsonReq = "{ \"email\": \"shernandez@scansee.com\"}";
		String strURI = "http://localhost:9990/HubCiti2.4/govqa/getallrequestsbycustmr";
		
		ClientRequest request = new ClientRequest(strURI);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();

		try {

			long strtTime = new Date().getTime();
			final String strResponse = request.postTarget(String.class);
			long endTime = new Date().getTime();
			
			System.out.println(endTime - strtTime);
			
			System.out.println("URI in getServiceRequestsByCustomer --> " + strURI);
			System.out.println("Request in getServiceRequestsByCustomer --> " + strJsonReq);
			System.out.println("response in getServiceRequestsByCustomer --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	
	public static void deleteServiceRequestsByCustomer() {
		final String strServiceReqByCust= "http://localhost:9990/HubCiti_Cache/govqa/remallrequestsbycustmrcache";
		final ClientRequest objClRequest = new ClientRequest(strServiceReqByCust);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in deleteServiceRequestsByCustomer --> " + strResponse);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	
	
	static void getMobileServiceRequestTypes() {
		
		final String strURI = "http://localhost:9990/HubCiti_Cache/govqa/makerequest";
//		final String strURI = "https://app.scansee.net/HubCiti2.2/govqa/makerequest";
		final String strJsonReq = "{ \"groupBy\": \"type\", \"sortBy\": \"name\"}";
//		final String strJsonReq = "{ \"groupBy\": \"type\"}";
		final ClientRequest request = new ClientRequest(strURI);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			
			long strtTime = new Date().getTime();
			final String strResponse = request.postTarget(String.class);
			long endTime = new Date().getTime();
			
			System.out.println(endTime - strtTime);
			
			System.out.println("URI in getMobileServiceRequestTypes --> " + strURI);
			System.out.println("Request in getMobileServiceRequestTypes --> " + strJsonReq);
			System.out.println("response in getMobileServiceRequestTypes --> " + strResponse);
		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	public static void deleteMobileServiceRequestTypes() {
		final String strMobileService = "http://localhost:9990/HubCiti_Cache/govqa/remgovqamakerequestcache";
		final ClientRequest objClRequest = new ClientRequest(strMobileService);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in deleteMobileServiceRequestTypes --> " + strResponse);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
}

