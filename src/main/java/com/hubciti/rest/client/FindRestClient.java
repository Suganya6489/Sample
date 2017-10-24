package com.hubciti.rest.client;

import java.util.Date;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;

public class FindRestClient {

	public static void main(String[] args) {
		executeServices();
	}

	public static void executeServices() {
//		getCategory();
//		getCategoryJson();
		
		//	 findScanSeeCategorySearch();
		// findGoogleRetSearch();
		//		 findGoogleCategorySearch();
		//getProductSummary();
		//		 getMediaInfo();
		//		 userTrackingProdMediaClick();
		// getProductAttributes();
		// fetchMSLCLRInfo();
		// userTrackingOnlineStoreClick();
		//		 getSubCategories();
		//		findSingleCategoryRetailers();
		/*for(int i = 0; i < 10; i++)	{
			new Thread()	{
				public void run()	{
					findSingleCategoryRetailers();
				}
			}.start();			
		}*/

		//		 getFilterList();

		//getSortFilterList();
//			 getCategoryList();
		//		 getOptionList();
		//		 getInterestList();
		
//		remGetCategoryCache();
//		findSingleCategoryRetailers();
//		findSingleCategoryRetailersJson();
		
//		 findScanSeeRetSearch();
//		findScanSeeRetSearchJson();
		
//		 findScanSeeCategorySearch();
	 findScanSeeCategorySearchJson();
	}


	static void getInterestList() {

		final String logon = "http://localhost:9990/HubCiti2.4.2/find/getcategory";
		/*final String logon = "http://localhost:8080/HubCiti2.1/find/getinterestlist";*/
		final ClientRequest request = new ClientRequest(logon);

		/*//single find option Filter List
		String inputXML = "<Filter>"
								+ "<userId>3</userId>"
								+ "<hubCitiId>4173</hubCitiId>"
								+ "<fCategoryName>Dining</fCategoryName>"
							//	+ "<latitude>234</latitude>"
							//	+ "<longitude>234</longitude>"
								+ "<mItemId>23770</mItemId>"
							//	+ "<bottomBtnId></bottomBtnId>"
								+ "<spType>singlefind</spType>"
						  + "</Filter>";

		 */
		/*//find option Filter List
		String inputXML = "<Filter>"
				+ "<userId>3</userId>"
				+ "<hubCitiId>4173</hubCitiId>"
				+ "<fCategoryName>Dining</fCategoryName>"
				//+ "<latitude>2323</latitude>"
				//+ "<longitude>2323</longitude>"
				+ "<mItemId>20023</mItemId>"
			//	+ "<bottomBtnId></bottomBtnId>"
				+ "<spType>find</spType>"
		  + "</Filter>";
		 */


		// City Experience Interest List
		String inputXML = "<Filter><hubCitiId>2070</hubCitiId><userId>6819</userId><spType>CitiEXP</spType><citiExpId>2070</citiExpId></Filter>";
		/*	String inputXML = "<Filter>"
				+ "<userId>6819</userId>"
				+ "<hubCitiId>2070</hubCitiId>"
				+ "<cityExpId>2070</cityExpId>"
			 //   + "<categoryId></categoryId>"
			//	+ "<searchKey></searchKey>"
			//	+ "<latitude>2323</latitude>"
			//	+ "<longitude>2323</longitude>"
//				+ "<lowerLimit>0</lowerLimit>"
			//	+ "<cityId>0</cityId>"
				+ "<spType>CitiEXP</spType>"
		  + "</Filter>";
		 */


		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getFilterListDisplay URI : " + logon);
			System.out.println("Request in getFilterListDisplay :" + inputXML);
			System.out.println("response in getFilterListDisplay :" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}


	}

	static void getOptionList() {
		final String logon = "http://localhost:8080/HubCiti2.1/find/getoptionlist";
		final ClientRequest request = new ClientRequest(logon);

		// Get retailer Filter List
		String inputXML = "<Filter>"
				+ "<userId>66</userId>"
				+"<hubCitiId>93</hubCitiId>"
				//					+"<cityId></cityId>"
				+"<latitude>30.57228</latitude>"
				+"<longitude>-98.306992</longitude>"
				+"<mItemId>23769</mItemId>"
				//					+"<bottomBtnId></bottomBtnId>"
				+"<radius>100</radius>"
				//					+"<searchKey></searchKey>"    
				+"<businessId>11</businessId>"     
				+"<spType>options</spType>"
				+"</Filter>";

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getFilterListDisplay URI : " + logon);
			System.out.println("Request in getOptionListDisplay :" + inputXML);
			System.out.println("response in getOptionListDisplay :" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}

	}

	public static void getCategoryList() {

		final String logon = "http://localhost:8080/HubCiti2.1/find/getcategorylist";
		final ClientRequest request = new ClientRequest(logon);

		/*// Get City Experience  category  List
		String inputXML = "<Filter>"
							+ "<userId>66</userId>"
							+"<hubCitiId>93</hubCitiId>"
							+ "<cityExpId>2133</cityExpId>"
		//					+"<searchKey></searchKey>"
		//					+"<latitude></latitude>"
		//					+"<longitude></longitude>"
		//					+"<cityId></cityId>"							    
		 					+"<moduleName>cityexp</moduleName>"
		 				+"</Filter>";
		 */

		// Get Filter  category List
		String inputXML = "<MenuItem><userId>15931</userId><bottomBtnId>13</bottomBtnId><platform>IOS</platform><levelFlag>false</levelFlag><radius>50</radius><hubCitiId>10</hubCitiId></MenuItem>";
		//				"<latitude></latitude><longitude></longitude><bottomBtnId>304</bottomBtnId><searchKey></searchKey></Filter>";
		/*	String inputXML = "<Filter>"
									+ "<userId>6</userId>"
									+"<hubCitiId>1143</hubCitiId>"
									+"<filterId>1130</filterId>"
									+"<zipCode><zipCode>"
									+"<searchKey></searchKey>"
									+"<latitude></latitude>"
									+"<longitude></longitude>"
				 					+"<moduleName>filter</moduleName>"
				 				+"</Filter>";*/



		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getFilterListDisplay URI : " + logon);
			System.out.println("Request in getCategoryList Display :" + inputXML);
			System.out.println("response in getCategoryList Display :" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getCategory() {

		final String logon = "http://localhost:9990/HubCiti2.4.2/find/getcategory";
		final ClientRequest request = new ClientRequest(logon);
	/*	String inputXML = "<MenuItem><userId>9981</userId><bottomBtnId>11803</bottomBtnId><platform>IOS</platform>" +
				         "<levelFlag>false</levelFlag><hubCitiId>2070</hubCitiId><gpsEnabled>0</gpsEnabled></MenuItem>"; 
*/
		String inputXML = "<MenuItem><userId>15931</userId><bottomBtnId>13</bottomBtnId><platform>IOS</platform><levelFlag>false</levelFlag><radius>50</radius><hubCitiId>10</hubCitiId><hubCitiId>10</hubCitiId></MenuItem>";
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			String response = null;
			long strtTime = new Date().getTime();
			response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("getCategory  URI :  " + logon);
			System.out.println("Request in getCategory " + inputXML);
			System.out.println("response in getCategory " + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void findScanSeeRetSearch() {
		final String logon ="http://localhost:8080/HubCiti2.3.2/find/ssretsearch";
		//		final String logon = "https://app.scansee.net/HubCiti1.5/find/ssretsearch";
		// final String logon =
		// "http://199.36.142.83:8080/HubCiti1.0/find/ssretsearch";
		//		 final String logon = "http://199.36.142.83:8080/HubCiti1.3.1/find/ssretsearch";
		//		final String logon ="http://localhost:8080/HubCiti2.1/find/ssretsearch";

		//		 final String logon = "http://66.228.143.28:8080/HubCiti1.3.1/find/ssretsearch";
		final ClientRequest request = new ClientRequest(logon);

		//		String inputXml = "<RetailerDetail><userId>6819</userId><searchKey>span</searchKey><lastVisitedNo>0</lastVisitedNo><postalCode>75087" +
		//				"</postalCode><mainMenuId>27597</mainMenuId><sortOrder>ASC</sortOrder><sortColumn>distance</sortColumn><hubCitiId>2070</hubCitiId></RetailerDetail>" ;
		String inputXml = "<RetailerDetail><userId>10462</userId><searchKey>adult</searchKey><lastVisitedNo>0</lastVisitedNo><postalCode>78654</postalCode><mainMenuId>42571</mainMenuId><mItemId>44706</mItemId><platform>IOS</platform><sortOrder>ASC</sortOrder><sortColumn>distance</sortColumn><hubCitiId>2113</hubCitiId></RetailerDetail>";
		//				"<locSpecials>0</locSpecials><interests>1,2</interests></RetailerDetail>";
		/*String inputXml = "<RetailerDetail>" + 
				"<userId>8101</userId>" + 
				"<hubCitiId>19</hubCitiId>" 
				+ "<searchKey>Dining</searchKey>"
				+ "<lastVisitedNo>50</lastVisitedNo>" 
				+ "<longitude>-95.292179</longitude>" 
				+ "<latitude>32.325366</latitude>"
//				+"<sortOrder>ASC</sortOrder>" // Values --> ASC,DESC  .Default is by Ascending.
//				+"<sortColumn>Distance</sortColumn>" //Values --> Distance, name .Default is by Distance.
//				+"<postalCode>78654</postalCode>"
//				+"<mItemId>12701</mItemId>"
				+ "<mainMenuId>21968</mainMenuId>" 
//				+"<cityIds>696,9996</cityIds>"
//				+"<groupBy>atoz</groupBy>"// atoz, type
				+ "</RetailerDetail>";*/

		request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("findScanSeeRetSearch URI : " + logon);
			System.out.println("Request in findScanSeeRetSearch" + inputXml);
			System.out.println("response in findScanSeeRetSearch" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void findScanSeeCategorySearch() {
		//		 final String logon = "http://199.36.142.83:8080/HubCiti1.5/find/sscatsearch";
		//		 final String logon = "http://10.11.202.76:8080/HubCiti1.6/find/sscatsearch";
		final String logon = "https://app.scansee.net/HubCiti2.0/find/sscatsearch";
		//		final String logon = "http://localhost:8080/HubCiti2.1/find/sscatsearch";
		//		final String logon = "http://66.228.143.28:8080/HubCiti2.1/find/sscatsearch";
		final ClientRequest request = new ClientRequest(logon);
		//String inputXml = "<RetailerDetail><userId>6819</userId><catName>Dining</catName><lastVisitedNo>0</lastVisitedNo><mItemId>39689</mItemId><sortColumn>Distance</sortColumn><platform>Android</platform><hubCitiId>2070</hubCitiId></RetailerDetail>" ;

		/*	String inputXml ="<RetailerDetail><userId>10091</userId><catName>Dining</catName><lastVisitedNo>0</lastVisitedNo><mainMenuId>33867</mainMenuId><bottomBtnId>0</bottomBtnId><platform>IOS</platform><sortOrder>ASC</sortOrder><sortColumn>distance</sortColumn><locSpecials>0</locSpecials><hubCitiId>10</hubCitiId></RetailerDetail>";*/
		String inputXml = "<RetailerDetail><userId>11180</userId><catName>Home Décor/Interior Design</catName><lastVisitedNo>0</lastVisitedNo><bottomBtnId>209</bottomBtnId><sortColumn>Distance</sortColumn><platform>Android</platform><hubCitiId>19</hubCitiId></RetailerDetail>";

		/*String inputXml = "<RetailerDetail>" 
							+ "<userId>3</userId>" 
							+ "<catName>dining</catName>" 
							+ "<lastVisitedNo>0</lastVisitedNo>"
							+ "<hubCitiId>4173</hubCitiId>"
//							+ "<mainMenuId>45621</mainMenuId>"
							+ "<mItemId>20023</mItemId>"
//							+ "<searchKey>tyler</searchKey>"
//							+ "<latitude>32.325366</latitude>"
//							+ "<longitude>-95.292179</longitude>"
//							+ "<bottomBtnId>209</bottomBtnId>" 
							+ "<platform>IOS</platform>"
//							+ "<postalcode>78753</postalcode>"
//							+ "<subCatId>0</subCatId>"
							+"<sortOrder>ASC</sortOrder>" // Values --> ASC,DESC  .Default is by Ascending.
							+"<sortColumn>Distance</sortColumn>" //Values --> Distance, name,city .Default is by Distance.
//							+"<groupBy>atoz</groupBy>"// atoz, type
							//+"<cityIds>696,9996</cityIds>"
							+"<locSpecials>0</locSpecials>" //0- SaleFlag All will display else 1 -SaleFlag with 1 will display
//							+"<interests>1,2</interests>"
						+ "</RetailerDetail>";*/
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("findScanSeeCategorySearch URI : " + logon);
			System.out.println("Request in findScanSeeCategorySearch" + inputXml);
			System.out.println("response in findScanSeeCategorySearch" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void findGoogleRetSearch() {

		String logon = "http://localhost:8080/HubCiti/find/googleretsearch";
		// String logon =
		// "http://localhost:8080/ScanSee2.4/find/findgoogleretsearch";

		final ClientRequest request = new ClientRequest(logon);

		/*
		 * String inputXml = "<ServiceSerachRequest>" + "<userId>1</userId>" +
		 * "<lat>32.910347</lat>" + "<lng>-96.728472</lng>" +
		 * "<keyword>pizza</keyword>" // +
		 * "<pagetoken>ClRQAAAAKeIjjsLel-y-Ovtxgof5Bi8le5hgoI-LPASEAIRG9SQKksCyc5iC_UTmbentRuM_eEx7MjfPkJnE7mS3ILa-b04dpp05GHtz71XFQoqwF6YSEKnUJ2d5wHE8vIIGWVQOrNoaFFJ-W7YvTLUryVtEy2CmdK8jSHSM</pagetoken>"
		 * + "</ServiceSerachRequest>";
		 */

		String inputXml = "<ServiceSerachRequest>" + "<userId>1</userId>" + "<lat>32.910347</lat>" + "<lng>-96.728472</lng>"
				+ "<keyword>pizza</keyword>"
				// +"<pagetoken>ClRQAAAAKeIjjsLel-y-Ovtxgof5Bi8le5hgoI-LPASEAIRG9SQKksCyc5iC_UTmbentRuM_eEx7MjfPkJnE7mS3ILa-b04dpp05GHtz71XFQoqwF6YSEKnUJ2d5wHE8vIIGWVQOrNoaFFJ-W7YvTLUryVtEy2CmdK8jSHSM</pagetoken>"
				+ "</ServiceSerachRequest>";

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);

		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in save findGoogleRetSearch:" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void findGoogleCategorySearch() {
		final String logon = "http://localhost:8080/HubCiti/find/googlecatsearch";
		final ClientRequest request = new ClientRequest(logon);
		String inputXml = "<ServiceSerachRequest>" + "<userId>7693</userId>" + "<lat>30.331562</lat>" + "<lng>-97.700394</lng>"
				+ "<type>Hotels/Motels/Resorts</type>"
				// +"<pagetoken>CmRZAAAANFNfx33VvCaGy0wrUARVfORQ70IVrn8oA01qAhvfFJYVX6Ly-Zuub_SPV-jaKHjPj_0SvVpGtVL-8_z3X3lc7gTYrKkURsKD96edpsRvXapdxVke2tvoNATDJL-LfAOfEhAsmB8S7lZ9IHZez0ir1DbqGhROFud2AaZIXhESythsHURkBpzDxw</pagetoken>"
				+ "</ServiceSerachRequest>";
		// request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8",
		// inputXml);
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in save user media" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getProductSummary() {
		// final String logon =
		// "http://localhost:8080/HubCitiVM/find/getproductsummary";
		final String logon = "http://localhost:8080/HubCiti2.1/find/getproductsummary";
		final ClientRequest request = new ClientRequest(logon);
		String inputXml = "<ProductDetailsRequest>" + "<userId>7096</userId>"
				+ "<productId>3682011</productId>"// 843575, 273302, 273298,
				// 9191739
				+ "<postalcode>78654</postalcode>"
				// + "<retailId>1004</retailId>"
				+ "<saleListId>2699</saleListId>"
				//				+ "<prodListId>6924</prodListId>" 
				+ "<mainMenuId>11719</mainMenuId>" + "<scanTypeId>0</scanTypeId>" + "<hubCitiId>91</hubCitiId>"
				+ "</ProductDetailsRequest>";

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);

		// request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getProductSummary" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	/*
	 * public static void getProductSummary() { // final String logon =
	 * "http://199.36.142.83:8080/HubCiti1.0/find/getproductsummary"; final
	 * String logon = "http://localhost:8080/HubCitiVQA/find/getproductsummary";
	 * final ClientRequest request = new ClientRequest(logon); String inputXml =
	 * "<ProductDetailsRequest>" + "<userId>308</userId>" +
	 * "<productId>2863451</productId>"//843575, 273302, 273298, 9191739 // +
	 * "<postalcode>78753</postalcode>" //+ "<retailId>1004</retailId>" // +
	 * "<saleListId>1986</saleListId>" + "<prodListId>4910</prodListId>" +
	 * "<mainMenuId>1</mainMenuId>" + "<scanTypeId>0</scanTypeId>" +
	 * "<hubCitiId>47</hubCitiId>" + "</ProductDetailsRequest>";
	 * request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8",
	 * inputXml); request.getHeaders(); try { final String response =
	 * request.postTarget(String.class);
	 * System.out.println("response in save user media" + response); } catch
	 * (Exception e) { System.out.println("Exception :  " + e); } }
	 */

	public static void getMediaInfo() {
		final String logon = "http://199.36.142.83:8080/HubCiti1.2/find/mediainfo";
		final ClientRequest request = new ClientRequest(logon);
		String inputXml = "<ProductDetailsRequest>" + "<userId>1615</userId>" + "<productId>26932654</productId>" + "<mediaType>video</mediaType>" // audio,
				// video,
				// others
				+ "<prodListId>1</prodListId>" + "<hubCitiId>8</hubCitiId>" + "</ProductDetailsRequest>";

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in save user media" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void userTrackingProdMediaClick() {
		final String logon = "http://199.36.142.83:8080/HubCiti1.2/find/utpmclick?pmListId=1";
		final ClientRequest request = new ClientRequest(logon);
		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in save user media" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void getProductAttributes() {
		final String logon = "http://10.11.202.220:8080/HubCiti1.0/find/fetchproudctattributes?userId=2&productId=647369";
		final ClientRequest request = new ClientRequest(logon);

		try {
			final String response = request.getTarget(String.class);
			System.out.println("response in save user media" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void fetchMSLCLRInfo() {
		final String logon = "http://localhost:8080/HubCiti1.0/find/fetchproductclrinfo";
		final ClientRequest request = new ClientRequest(logon);
		String inputXml = "<CLRDetails>" + "<userId>3</userId>" + "<productId>2324</productId>" + "<retailerId>733</retailerId>"
				+ "<lowerLimit>0</lowerLimit>" + "<hubCitiId>70</hubCitiId>" + "<mainMenuId>1</mainMenuId>" + "</CLRDetails>";

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", inputXml);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in save user media" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void userTrackingOnlineStoreClick() {
		String URL = "http://localhost:8080/HubCiti1.0/find/utonstoclick";
		String addslhistoryproductxml = "<CLRDetails>" + "<productId>2</productId>" + "</CLRDetails>";
		ClientRequest request = new ClientRequest(URL);
		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", addslhistoryproductxml);
		request.getHeaders();
		try {
			String response = request.postTarget(String.class);
			System.out.println("response " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	static void getSubCategories() {
		final String logon = "http://199.36.142.83:8080/HubCiti1.5/find/getsubcategory";
		final ClientRequest request = new ClientRequest(logon);

		final String subCategories = "<UserDetails>" +
				"<userId>6788</userId>" +
				"<hubCitiId>2067</hubCitiId>" +
				"<catId>11</catId>"+
				//			"<mItemId>38834</mItemId>"+
				"<postalCode><![CDATA[78654]]></postalCode>"
				+			"<bottomBtnId>7225</bottomBtnId>"+
				"</UserDetails>";

		request.accept("text/xml;charset=UTF-8").body("text/xml;charset=UTF-8", subCategories);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("response in getCategory" + response);

			System.out.println("getSubCategories  URI :  " + logon);
			System.out.println("Request in getSubCategories " + subCategories);
			System.out.println("response in getSubCategories " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

	public static void findSingleCategoryRetailers() {
		//		final String logon = "http://66.228.143.28:8080/HubCiti2.0/find/singlecatretailers";
		//		 final String logon = "http://66.228.143.28:8080/HubCiti1.5/find/singlecatretailers";
		//final String logon = "https://app.scansee.net/HubCiti1.3.1/find/singlecatretailers";
		final String logon = "http://localhost:8080/HubCiti2.3/find/singlecatretailers";

		final ClientRequest request = new ClientRequest(logon);

		final String inputXml = "<RetailerDetail>" 
				+ "<userId>3</userId>" 
				+ "<catName>Dining</catName>"
				+ "<hubCitiId>1143</hubCitiId>"
				//	+"<bottomBtnId>6615</bottomBtnId>"
				+ "<sortColumn>distance</sortColumn>"
				+ "<sortOrder>ASC</sortOrder>" 
				+"<locSpecials>0</locSpecials>"
				+ "</RetailerDetail>";

		//final String inputXml = "<RetailerDetail><userId>681</userId><hubCitiId>2070</hubCitiId><subCatId>0</subCatId><lastVisitedNo>0</lastVisitedNo><mainMenuId>26951</mainMenuId><mItemId>39694</mItemId><sortOrder>ASC</sortOrder><sortColumn>distance</sortColumn><catName>Dining</catName></RetailerDetail>"; 
		/*final String inputXml = "<RetailerDetail>" 
				+ "<userId>3</userId>" 
				+ "<catName>Dining</catName>"
				+ "<lastVisitedNo>0</lastVisitedNo>"
				+ "<hubCitiId>93</hubCitiId>"
//				 + "<mainMenuId>235</mainMenuId>"
				+ "<mItemId>15458</mItemId>"
				// + "<searchKey>test1 Location</searchKey>"
				+ "<latitude>30.57228</latitude>"
				+ "<longitude>-97.796676</longitude>"
				+ "<sortColumn>distance</sortColumn>"// name, distance, city
//				+ "<groupBy>atoz</groupBy>" // atoz, type
				+ "<sortOrder>ASC</sortOrder>" 
//				+ "<subCatId>0</subCatId>"
//				+ "<cityIds>9996</cityIds>"
				+ "<radius>100</radius>"
				+"<locSpecials>0</locSpecials>" //0- SaleFlag All will display else 1 -SaleFlag with 1 will display
				//+"<interests>1,2</interests>"
				+ "</RetailerDetail>";*/
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXml);
		request.getHeaders();
		try {
			String response = null;
			long strtTime = new Date().getTime();
			response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("findSingleCategoryRetailers  URI :  " + logon);
			System.out.println("Request in findSingleCategoryRetailers " + inputXml);
			System.out.println("response in findSingleCategoryRetailers " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}


	static void getFilterList() {
		final String logon = "http://66.228.143.28:8080/HubCiti2.1/find/getfilterlist";
		final ClientRequest request = new ClientRequest(logon);
		String inputXML = "<MenuItem><hubCitiId>2070</hubCitiId><userId>68191</userId><mItemId>396941</mItemId><catId>11</catId><fName>DDD1</fName></MenuItem>"; 
		/*String inputXML = "<MenuItem>" 
				+ "<userId>8914</userId>" 
				+ "<hubCitiId>2070</hubCitiId>" 
//			+ "<latitude>30.57228</latitude>"
//				+ "<longitude>-98.306992</longitude>"
				+ "<catId>11</catId>" 
//				+ "<cityIds>1,2,3</cityIds>" //HubCiti level value will be null.
//				+"<radius>100</radius>"
				+ "<mItemId>37633</mItemId>"
//				+ "<bottomBtnId>304</bottomBtnId>"// 1859
				+ "</MenuItem>";*/
		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			System.out.println("getFilterList URI : " + logon);
			System.out.println("Request in getFilterList" + inputXML);
			System.out.println("response in getFilterList" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}



	static void getSortFilterList() {
		final String logon = "http://localhost:8080/HubCiti2.2/find/getsortfilterlist";
		final ClientRequest request = new ClientRequest(logon);

		/*String inputXML = "<UserDetails>" + "<userId>10018</userId>" + "<hubCitiId>2070</hubCitiId>"+
			"<module>Events</module>"+	//Find All -23769, Find Single -23770, Citi Exp, Events
			"<mItemId>39331</mItemId>"+
//			"<bottomBtnId>2133</bottomBtnId>"+
//			"<latitude>30.422401</latitude>"+
//			"<longitude>-97.796676</longitude>"+
//			"<catName>dining</catName>"+
			"<subCatId>0</subCatId>"+
			<postalCode>75244</postalCode>
			 "</UserDetails>";*/

		/*String inputXML = "<UserDetails>" + "<userId>6</userId>" + "<hubCitiId>1143</hubCitiId>"+
					"<module>CitiExp</module>"+	//Find All -23769, Find Single -23770, Citi Exp, Events
					"<latitude>30.422401</latitude>"+
					"<longitude>-97.796676</longitude>"+

					"<citiExpId>2133</citiExpId>"+	
//					 "<lowerLimit>0</lowerLimit>" +
//					 "<cityIds>1,2</cityIds>" +
//					 "<srchKey>something_Testing</srchKey>" +
					 "</UserDetails>";*/

		/*String inputXML = "<UserDetails>" + "<userId>6</userId>" + "<hubCitiId>1143</hubCitiId>"+
					"<module>CitiExp</module>"+	//Find All -23769, Find Single -23770, Citi Exp, Events
					"<latitude>30.422401</latitude>"+
					"<longitude>-97.796676</longitude>"+

					"<citiExpId>2133</citiExpId>"+	
//					 "<lowerLimit>0</lowerLimit>" +
//					 "<cityIds>1,2</cityIds>" +
//					 "<srchKey>something_Testing</srchKey>" +
					 "</UserDetails>";*/

		String inputXML = "<UserDetails>" + "<userId>10052</userId>" + "<hubCitiId>2070</hubCitiId>"+
				"<module>filter</module>"+	//Find All -23769, Find Single -23770, Citi Exp, Events, Filter

					 "<filterId>2070</filterId>" +
					 "<latitude>30.422401</latitude>"+
					 "<longitude>-97.796676</longitude>"+
					 "<postalCode>75244</postalCode>"+	
					 //					"<menuId>1275</menuId>"+	
					 //					 "<lowerLimit>0</lowerLimit>" +
					 //					 "<cityIds>1,2</cityIds>" +
					 "<srchKey>something_Testing</srchKey>" +
					 "</UserDetails>";


		request.accept("text/xml").body(MediaType.TEXT_XML, inputXML);
		request.getHeaders();
		try {
			final String response = request.postTarget(String.class);
			System.out.println("getSortFilterList URI : " + logon);
			System.out.println("Request in getSortFilterList" + inputXML);
			System.out.println("response in getSortFilterList" + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}



	static void remGetCategoryCache() {											
		final String strMobileService = "http://localhost:9990/HubCiti_Cache/find/rmfindcategorycache";
		final ClientRequest objClRequest = new ClientRequest(strMobileService);
		try {
			final String strResponse = objClRequest.getTarget(String.class);
			System.out.println("response in remGetCategoryCache --> " + strResponse);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	
	public static void getCategoryJson() {

		final String logon = "http://localhost:9990/HubCiti2.5/find/getcategoryjson";

		/*final String strJsonReq = "{ \"userId\": \"15931\", \"bottomBtnId\": \"13\", \"platform\": \"IOS\",\"levelFlag\": \"false\", \"radius\": \"50\"," +
				" \"hubCitiId\": \"10\",\"longitude\": \"77.572200\",\"latitude\": \"12.921400\", \"postalCode\": \"75244\", \"mItemId\": \"64623\"}";*/
		
		final String strJsonReq = "{\"userId\":\"6788\",\"platform\":\"IOS\",\"postalCode\":\"75567\", \"radius\":\"99\",\"platform\": \"IOS\",\"levelFlag\": \"false\", \"radius\": \"50\", \"mainMenuId\":\"54047\",\"hubCitiId\": \"2067\",\"mItemId\": \"72733\", \"cityIds\":\"2,271,566,654,696,925,1452,1845,3640,3920,4061,4608,5670,7372,7620,9994,12511,14047,14063,14274,14450,16402,17062,17659\"}";
		  
		final ClientRequest request = new ClientRequest(logon);
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();

		try {
			long strtTime = new Date().getTime();
			final String response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);
			
			System.out.println("getCategoryJson URI : " + logon);
			System.out.println("Request in getCategoryJson Display :" + strJsonReq);
			System.out.println("response in getCategoryJson Display :" + response);

		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	
	public static void findSingleCategoryRetailersJson() {
		final String logon = "http://localhost:9990/HubCiti2.4.2/find/singlecatretailersjson";
		final ClientRequest request = new ClientRequest(logon);

		final String strJsonReq = "{ \"userId\": \"3\", \"catName\": \"Dining\", \"lastVisitedNo\": \"0\", \"hubCitiId\":" +
				" \"93\", \"mainMenuId\": \"235\",\"mItemId\": \"15458\", \"searchKey\": \"test1 Location\", \"latitude\": \"30.57228\"," +
				"\"longitude\": \"-97.796676\",  \"sortColumn\": \"distance\",\"groupBy\": \"atoz\",\"sortOrder\": \"ASC\","+
				"\"subCatId\": \"0\",  \"cityIds\": \"9996\", \"radius\": \"100\", \"locSpecials\": \"0\", \"interests\": \"1,2\" }";
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			String response = null;
			long strtTime = new Date().getTime();
			response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("findSingleCategoryRetailersJson  URI :  " + logon);
			System.out.println("Request in findSingleCategoryRetailersJson " + strJsonReq);
			System.out.println("response in findSingleCategoryRetailersJson " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	public static void findScanSeeRetSearchJson() {
		final String logon = "http://localhost:9990/HubCiti2.4.2/find/ssretsearchjson";
		final ClientRequest request = new ClientRequest(logon);

		final String strJsonReq = "{ \"userId\": \"3\", \"catName\": \"Dining\", \"lastVisitedNo\": \"0\", \"hubCitiId\":" +
				" \"93\", \"mainMenuId\": \"235\",\"mItemId\": \"15458\", \"searchKey\": \"test1 Location\", \"latitude\": \"30.57228\"," +
				"\"longitude\": \"-97.796676\",  \"sortColumn\": \"distance\",\"groupBy\": \"atoz\",\"sortOrder\": \"ASC\","+
				"\"subCatId\": \"0\",  \"cityIds\": \"9996\", \"radius\": \"100\", \"locSpecials\": \"0\", \"interests\": \"1,2\" }";
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			String response = null;
			long strtTime = new Date().getTime();
			response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("FindScanSeeRetSearchJson  URI :  " + logon);
			System.out.println("Request in findScanSeeRetSearchJson " + strJsonReq);
			System.out.println("Response in findScanSeeRetSearchJson " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}
	
	
	
	public static void findScanSeeCategorySearchJson() {
		final String logon = "http://localhost:8080/HubCiti2.5/find/sscatsearchjson";
		final ClientRequest request = new ClientRequest(logon);

		final String strJsonReq = "{ \"userId\": \"3\", \"catName\": \"Dining\", \"lastVisitedNo\": \"0\", \"hubCitiId\":" +
				" \"93\", \"mainMenuId\": \"235\",\"mItemId\": \"15458\", \"searchKey\": \"test1 Location\", \"latitude\": \"30.57228\"," +
				"\"longitude\": \"-97.796676\",  \"sortColumn\": \"distance\",\"groupBy\": \"atoz\",\"sortOrder\": \"ASC\","+
				"\"subCatId\": \"0\",  \"cityIds\": \"9996\", \"radius\": \"100\", \"locSpecials\": \"0\", \"interests\": \"1,2\" }";
		
		request.accept("application/json").body(MediaType.APPLICATION_JSON, strJsonReq);
		request.getHeaders();
		try {
			String response = null;
			long strtTime = new Date().getTime();
			response = request.postTarget(String.class);
			long endTime = new Date().getTime();
			System.out.println(endTime - strtTime);

			System.out.println("findScanSeeCategorySearchJson  URI :  " + logon);
			System.out.println("Request in findScanSeeCategorySearchJson " + strJsonReq);
			System.out.println("Response in findScanSeeCategorySearchJson " + response);
		}
		catch (Exception e) {
			System.out.println("Exception :  " + e);
		}
	}

}
