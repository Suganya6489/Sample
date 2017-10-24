package com.hubciti.rest.client;

import java.util.Date;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.client.ClientRequest;

public class BandClient {

	public static void main(String[] args) {
		getNewsListByCategories();
	}

	private static void getNewsListByCategories() {
		final String logon = "http://localhost:9990/HubCiti2.5/band/getnewsbycategory";
		final ClientRequest request = new ClientRequest(logon);
		String strJsonReq = "{\"hubCitiID\":\"10\",\"feedType\":\"business\"}";
		request.accept("application/json;charset=utf-8").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getNewsListByCategories Fields URI : " + logon);
			System.out.println("Request in getNewsListByCategories :" + strJsonReq);
			System.out.println("response in getNewsListByCategories :" + response);

		} catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}


}
