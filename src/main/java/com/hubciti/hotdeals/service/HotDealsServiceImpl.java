package com.hubciti.hotdeals.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.helper.XstreamParserHelper;
import com.hubciti.common.pojos.Data;
import com.hubciti.common.pojos.Deal;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.HotDealAPIResultSet;
import com.hubciti.common.pojos.HotDealsCategoryInfo;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.HotDealsListRequest;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.HotDealsResultSet;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.RetailerTrack;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.dao.FirstUseDao;
import com.hubciti.hotdeals.dao.HotDealsDao;

public class HotDealsServiceImpl implements HotDealsService {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(HotDealsServiceImpl.class);

	/**
	 * Instance variable for HotDealsDao.
	 */
	public HotDealsDao hotDealsDao;

	/**
	 * Instance variable for FirstUseDAO.
	 */
	public FirstUseDao firstUseDao;

	/**
	 * Setter method for HotDealsDao.
	 * 
	 * @param hotDealsDao
	 *            Instance of type HotDealsDao.
	 */
	public void setHotDealsDao(HotDealsDao hotDealsDao) {
		this.hotDealsDao = hotDealsDao;
	}

	/**
	 * Setter method for FirstUseDAO.
	 * 
	 * @param firstUseDao
	 *            Instance of type FirstUseDAO.
	 */
	public void setFirstUseDao(FirstUseDao firstUseDao) {
		this.firstUseDao = firstUseDao;
	}

	/**
	 * The service implementation method for getHotDeal information. Calls the
	 * XStreams helper class methods for parsing.
	 * 
	 * @param userId
	 *            the request .
	 * @param hotDealId
	 *            the request parameter.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String getHotDealDetail(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : getHotDealDetail");

		String response = null;
		String strLongDesc = null;
		String strTermDesc = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final HotDealsDetails objHotDealsDetails = (HotDealsDetails) streamHelper.parseXmlToObject(xml);

		if (objHotDealsDetails.getUserId() == null || objHotDealsDetails.getHotDealId() == null || objHotDealsDetails.getHubCitiId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			final HotDealsCategoryInfo hotDealsCategoryInfo = hotDealsDao.getHotDealDetail(objHotDealsDetails);
			if (null != hotDealsCategoryInfo) {
				// Changing the date format.
				if (hotDealsCategoryInfo.getHotDealsDetailsArrayLst() != null && !hotDealsCategoryInfo.getHotDealsDetailsArrayLst().isEmpty()) {
					for (HotDealsDetails hdDetails : hotDealsCategoryInfo.getHotDealsDetailsArrayLst()) {
						hdDetails.setIsDateFormated(false);
						hdDetails.sethDStartDate(hdDetails.gethDStartDate());
						hdDetails.sethDEndDate(hdDetails.gethDEndDate());
						hdDetails.sethDExpDate(hdDetails.gethDExpDate());

						strLongDesc = hdDetails.gethDLognDescription().replaceAll("\n", "<br>");
						strLongDesc = strLongDesc.replaceAll("\r", "<br>");
						strTermDesc = hdDetails.gethDTermsConditions().replaceAll("\r", "<br>");
						strTermDesc = strTermDesc.replaceAll("\r", "<br>");
						hdDetails.sethDLognDescription(strLongDesc);
						hdDetails.sethDTermsConditions(strTermDesc);
						// hdDetails.sethDLognDescription(Utility.removeSpecialForWebView(hdDetails.gethDLognDescription()));
						// hdDetails.sethDTermsConditions(Utility.removeSpecialForWebView(hdDetails.gethDTermsConditions()));
					}
				}
				hotDealsCategoryInfo.setResponseCode(HubCitiConstants.SUCCESSCODE);
				hotDealsCategoryInfo.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(hotDealsCategoryInfo);
				// response = Utility.removeSpecialForWebView(response);
				response = response.replaceAll("<HotDealsCategoryInfo>", "<HotDealsListResultSet>");
				response = response.replaceAll("</HotDealsCategoryInfo>", "</HotDealsListResultSet>");
			} else {
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}

		LOG.info("Exit HotDealsServiceImpl : getHotDealDetail");
		return response;
	}

	/**
	 * The service implementation method for fetching hot deals list. Calls the
	 * XStreams helper class methods for parsing.
	 * 
	 * @param xml
	 *            the request xml.
	 * @return response xml with success code or error code.
	 * @throws HubCitiException
	 *             The exceptions are caught and a HubCiti Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String getHotDealslist(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : getHotDealslist");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final HotDealsListRequest hotDealsListRequest = (HotDealsListRequest) streamHelper.parseXmlToObject(xml);

		if (null == hotDealsListRequest.getUserId() || null == hotDealsListRequest.getHubCitiId()) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (hotDealsListRequest.getmItemId() == null && hotDealsListRequest.getMainMenuId() == null
				&& hotDealsListRequest.getBottomBtnId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			ArrayList<HotDealsResultSet> hotDealsDetailsResultSet;

			// For user tracking
			Integer mainMenuId = null;
			if (null == hotDealsListRequest.getMainMenuId()) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setUserId(hotDealsListRequest.getUserId());
				objMenuItem.setHubCitiId(hotDealsListRequest.getHubCitiId());
				objMenuItem.setmItemId(hotDealsListRequest.getmItemId());
				objMenuItem.setBottomBtnId(hotDealsListRequest.getBottomBtnId());
				objMenuItem.setPlatform(hotDealsListRequest.getPlatform());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = hotDealsListRequest.getMainMenuId();
			}

			hotDealsListRequest.setMainMenuId(mainMenuId);

			HotDealsListResultSet dbDealsResult = hotDealsDao.getHotDealslist(hotDealsListRequest, HubCitiConstants.HUBCITIAPP);
			hotDealsDetailsResultSet = (ArrayList<HotDealsResultSet>) dbDealsResult.getHotDealsListResponselst();

			HotDealsListResultSet objHotDealResults = null;
			HotDealAPIResultSet apiResultSet = null;
			ArrayList<HotDealAPIResultSet> hdAPIList = null;
			ArrayList<HotDealsResultSet> dealTypeList = null;
			Integer dealTypeCount = 0;
			
			
			if (null != hotDealsDetailsResultSet && !hotDealsDetailsResultSet.isEmpty()) {
				
				dealTypeList = HotDealsHelper.groupHotDealbyDealType(hotDealsDetailsResultSet);
				
				HotDealsListResultSet objResultSet = null;
				
				List<HotDealsListResultSet> listResultSet = new ArrayList<HotDealsListResultSet>();
				objHotDealResults = new HotDealsListResultSet();
				
				for(HotDealsResultSet obj : dealTypeList)	{
					objResultSet = new HotDealsListResultSet();
					hdAPIList = new ArrayList<HotDealAPIResultSet>();
					hdAPIList = HotDealsHelper.sortHotdealsByAPINames(obj.getHdResultSetList());
					ArrayList<HotDealAPIResultSet> hdAPIResultSet1 = new ArrayList<HotDealAPIResultSet>();
					if (hdAPIList != null && !hdAPIList.isEmpty()) {
						HotDealsListResultSet hdCatInfo = null;
						for (int i = 0; i < hdAPIList.size(); i++) {
							apiResultSet = new HotDealAPIResultSet();
							hdCatInfo = new HotDealsListResultSet();
							apiResultSet.setApiPartnerId(hdAPIList.get(i).getApiPartnerId());
							apiResultSet.setApiPartnerName(hdAPIList.get(i).getApiPartnerName());
							hdCatInfo = HotDealsHelper.sortHotDealsListByCategory(hdAPIList.get(i).getHotDealsDetailslst());
							apiResultSet.setHdCatInfoList((ArrayList<HotDealsCategoryInfo>) hdCatInfo.getHotDealsCategoryInfo());
							hdAPIResultSet1.add(apiResultSet);
						}
						objResultSet.setHdAPIResult(hdAPIResultSet1);
						objResultSet.setDealType(obj.getDealType());
					}
					listResultSet.add(objResultSet);
					dealTypeCount++;
				}

				/**/

				/*objHotDealResults = new HotDealsListResultSet();
				hdAPIList = new ArrayList<HotDealAPIResultSet>();
				hdAPIList = HotDealsHelper.sortHotdealsByAPINames(hotDealsDetailsResultSet);
				ArrayList<HotDealAPIResultSet> hdAPIResultSet = new ArrayList<HotDealAPIResultSet>();
				if (hdAPIList != null && !hdAPIList.isEmpty()) {
					HotDealsListResultSet hdCatInfo = null;
					for (int i = 0; i < hdAPIList.size(); i++) {
						apiResultSet = new HotDealAPIResultSet();
						hdCatInfo = new HotDealsListResultSet();
						apiResultSet.setApiPartnerId(hdAPIList.get(i).getApiPartnerId());
						apiResultSet.setApiPartnerName(hdAPIList.get(i).getApiPartnerName());
						hdCatInfo = HotDealsHelper.sortHotDealsListByCategory(hdAPIList.get(i).getHotDealsDetailslst());
						apiResultSet.setHdCatInfoList((ArrayList<HotDealsCategoryInfo>) hdCatInfo.getHotDealsCategoryInfo());
						hdAPIResultSet.add(apiResultSet);
					}
				}*/

				//objHotDealResults.setHdAPIResult(hdAPIResultSet);
				objHotDealResults.setDealTypeList(listResultSet);
				objHotDealResults.setNextPage(dbDealsResult.getNextPage());
				objHotDealResults.setFavCat(dbDealsResult.getFavCat());
				objHotDealResults.setCategoryFlag(dbDealsResult.getCategoryFlag());
				objHotDealResults.setCategory(dbDealsResult.getCategory());
				objHotDealResults.setMaxRowNum(dbDealsResult.getMaxRowNum());
				objHotDealResults.setMaxCnt(dbDealsResult.getMaxCnt());

				objHotDealResults.setArBottomBtnList(dbDealsResult.getArBottomBtnList());
				objHotDealResults.setBottomBtn(dbDealsResult.getBottomBtn());
				objHotDealResults.setDealTypeCount(dealTypeCount);
			}

			if (objHotDealResults != null) {
				objHotDealResults.setMainMenuId(mainMenuId);
				objHotDealResults.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objHotDealResults.setResponseText(HubCitiConstants.SUCCESSTEXT);
				response = XstreamParserHelper.produceXmlFromObject(objHotDealResults);
			} else {
				String mmID = null;
				if (null != mainMenuId) {
					mmID = mainMenuId.toString();
				} else {
					mmID = "0";
				}
				Integer favFlag = dbDealsResult.getFavCat();
				Integer categoryFlag = dbDealsResult.getCategoryFlag();
				response = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NONEAVAILABLE, "FavCatFlag",
						String.valueOf(favFlag), "categoryFlag", String.valueOf(categoryFlag), "mainMenuId", mmID);
			}
		}

		LOG.info("Exit HotDealsServiceImpl : getHotDealslist");
		return response;
	}

	/**
	 * The service implementation method for remove Hot deals. Calls the XStream
	 * helper class methods for parsing.
	 * 
	 * @param xml
	 *            the request .
	 * @return response string with success code or error code.
	 * @throws HubCitiException
	 *             The exceptions are caught and a ScanSee Exception defined for
	 *             the application is thrown which is caught in the Controller
	 *             layer.
	 */
	public String removeHDProd(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : removeHDProd");

		String response = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final HotDealsListRequest hotDealsListRequest = (HotDealsListRequest) streamHelper.parseXmlToObject(xml);

		if (hotDealsListRequest.getUserId() == null || hotDealsListRequest.gethDInterested() == null || hotDealsListRequest.getHotDealId() == null
				|| hotDealsListRequest.getHubCitiId() == null) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = hotDealsDao.removeHDProduct(hotDealsListRequest);
			if (null != response) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.HOTDEALSUPDATETEXT);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit HotDealsServiceImpl : removeHDProd");
		return response;
	}

	/**
	 * The service method For capturing hot deal click.
	 * 
	 * @param userId
	 *            as request
	 * @param hotDealId
	 *            as request
	 * @param hotDealListId
	 *            as request
	 * @return xml based on success or failure
	 * @throws HubCitiException
	 *             for exception
	 */
	public String userTrackingGetHotDealClick(Integer userId, Integer hotDealId, Integer hotDealListId) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : userTrackingGetHotDealClick");

		String response = null;

		if (userId == null || "".equals(userId) || hotDealId == null || "".equals(hotDealId) || hotDealListId == null || "".equals(hotDealListId)) {
			response = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			response = hotDealsDao.userTrackingGetHotDealClick(userId, hotDealId, hotDealListId);
			if (HubCitiConstants.SUCCESS.equalsIgnoreCase(response)) {
				response = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			} else {
				response = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
			}
		}

		LOG.info("Exit HotDealsServiceImpl : userTrackingGetHotDealClick");
		return response;
	}

	/**
	 * This Service Implementation Method to claim hot deal. Method Type: POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 */
	public String hotDealClaim(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : hotDealClaim");

		String strResponse = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final HotDealsListRequest objHdListRequest = (HotDealsListRequest) streamHelper.parseXmlToObject(xml);

		if (objHdListRequest.getUserId() == null || objHdListRequest.getHotDealId() == null) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			strResponse = hotDealsDao.hotDealClaim(objHdListRequest);
			if (HubCitiConstants.SUCCESSTEXT.equalsIgnoreCase(strResponse)) {
				strResponse = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			} else {
				strResponse = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURE);
			}
		}

		LOG.info("Exit HotDealsServiceImpl : hotDealClaim");
		return strResponse;
	}

	/**
	 * This Service Implementation Method to redeem hot deal. Method Type: POST
	 * 
	 * @param xml
	 *            containing userId, hotDealId and mainMenuID
	 * @return XMl containing SUCCESS or FAILURE message
	 * @throws HubCitiException
	 *             is thrown
	 */
	public String hotDealRedeem(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : hotDealRedeem");

		String strResponse = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final HotDealsListRequest objHdListRequest = (HotDealsListRequest) streamHelper.parseXmlToObject(xml);

		if (objHdListRequest.getUserId() == null || objHdListRequest.getHotDealId() == null) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			strResponse = hotDealsDao.hotDealRedeem(objHdListRequest);
			if (HubCitiConstants.SUCCESSTEXT.equalsIgnoreCase(strResponse)) {
				strResponse = Utility.formResponseXml(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
			} else {
				strResponse = Utility.formResponseXml(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURE);
			}
		}

		LOG.info("Exit HotDealsServiceImpl : hotDealRedeem");
		return strResponse;
	}


	/**
	 * ServiceImpl Method to get List of user deals.
	 * 
	 * @param xml, 
	 * @return XML containing the List of user deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String userDealsDisplay(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : userDealsDisplay");

		String strResponse = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final Deal objDeal = (Deal) streamHelper.parseXmlToObject(xml);

		if (null == objDeal.getUserId() || null == objDeal.getHubCitiId() || null == objDeal.getFlag()) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {

			Data objData= hotDealsDao.userDealsDisplay(objDeal);

			
			if(null != objData && ((null!=objData.getDealList() &&! objData.getDealList().isEmpty())||
					(null!=objData.getDealStateList()&&!objData.getDealStateList().isEmpty()))){
				objData.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objData.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strResponse = XstreamParserHelper.produceXmlFromObject(objData);
			}else if(null != objData){
				objData.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
				objData.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				strResponse = XstreamParserHelper.produceXmlFromObject(objData);
			}else{
				strResponse = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
			
			/*if(null == objData)	{
				strResponse = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			} else {
				objData.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objData.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strResponse = XstreamParserHelper.produceXmlFromObject(objData);
			}*/
		}

		LOG.info("Exit HotDealsServiceImpl : userDealsDisplay");
		return strResponse;
	}

	/**
	 * ServiceImpl Method to get List of User Gallery Deals.
	 * 
	 * @param xml, 
	 * @return XML containing the List of User Gallery Deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String userGalleryDisplay(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : userGalleryDisplay");

		String strResponse = null;

		final XstreamParserHelper streamHelper = new XstreamParserHelper();
		final Deal objDeal = (Deal) streamHelper.parseXmlToObject(xml);

		if (null == objDeal.getUserId() || null == objDeal.getHubCitiId() || null == objDeal.getFlag() || null == objDeal.getType()) {
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {
			Data objData= hotDealsDao.userGalleryDisplay(objDeal);

			if(null == objData)	{
				strResponse = Utility.formResponseXml(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			} else {
				objData.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objData.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strResponse = XstreamParserHelper.produceXmlFromObject(objData);
			}
		}

		LOG.info("Exit HotDealsServiceImpl : userGalleryDisplay");
		return strResponse;
	}
	
	
	/**
	 * serviceImpl Method to get List of Fundraisers.
	 * 
	 * @param xml
	 *            ,
	 * @return XML containing the List of Fundraisers.
	 * @throws HubCitiException
	 *             throws if execption occurs
	 * 
	 */

	public String getFundraisersList(String xml) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : getFundraisersList ");
	   
		String strResponse = null;

		final XstreamParserHelper parser = new XstreamParserHelper();

		final MenuItem menuItem = (MenuItem) parser.parseXmlToObject(xml);
       
		if (null == menuItem.getUserId() || null == menuItem.getHubCitiId()) {
			strResponse = Utility.formResponseXml(
					HubCitiConstants.INSUFFICENTREQUESTCODE,
					HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (null == menuItem.getMainMenuId() && null == menuItem.getPlatform()){
			strResponse = Utility.formResponseXml(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);

		} else
		{
			if (menuItem.getLowerLimit() == null) {
			menuItem.setLowerLimit(0);
		}
		/*
		 * Checking the for grouping parameter date, atoz, type --> catagory To
		 * group Fundraiser. Node can have below values: 
		 *  1. date – to group by date.
		 *  2. type – to group by category. 
		 *  3. atoz – to group alphabetically.
		 *  4. If NULL (or other than above mentioned value) – by default ‘date’ is selected.
		 */
		if (!"atoz".equalsIgnoreCase(menuItem.getGroupBy())
				&& !"type".equalsIgnoreCase(menuItem.getGroupBy())
				&& !"".equalsIgnoreCase(Utility.checkNull(menuItem.getGroupBy())))
		{
			menuItem.setGroupBy("date");
		}
		/*
		 	Checking for sorting parameter.
		 	name - Sort by Fundraiser Name. 
		 	date - Sort by Fundraiser StartDate.
	    */
		if("name".equalsIgnoreCase(menuItem.getSortBy())){
			menuItem.setSortBy("fundName");
		}else {
			menuItem.setSortOrder("date");
			menuItem.setSortColumn("StartDate");   
		}
		
		//checking the condition for sorting in Acsending or descending order.
		if(menuItem.getSortOrder() == null || !"Desc".equalsIgnoreCase(menuItem.getSortOrder())){
			menuItem.setSortOrder("Asc");
						
		}
		
		Integer mainMenuId = null;
		
		if(menuItem.getMainMenuId() == null ){
			
			mainMenuId = firstUseDao.getMainMenuId(menuItem);
			LOG.info("MainmenuId " + mainMenuId);
		}
		
		menuItem.setMainMenuId(mainMenuId);
		
		
		Fundraiser fundraiser = hotDealsDao.getFundraisersList(menuItem);
		
		fundraiser.setSortOrder(menuItem.getSortOrder());
		fundraiser.setSortBy(menuItem.getSortBy());
		fundraiser.setGroupBy(menuItem.getGroupBy());
		
		final List<Fundraiser> fundraiserList = fundraiser.getFundraiserList();
		
		Fundraiser objFundraiser = new Fundraiser();
		
		if(null != fundraiserList && !fundraiserList.isEmpty()){
			objFundraiser = HotDealsHelper.sortFundraiserList(fundraiser);
			
			
			if(null != objFundraiser && null != objFundraiser.getCategoryList() && !objFundraiser.getCategoryList().isEmpty()){
				
				objFundraiser.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objFundraiser.setResponseText(HubCitiConstants.SUCCESSTEXT);
				objFundraiser.setMainMenuId(fundraiser.getMainMenuId());
				objFundraiser.setMaxCount(fundraiser.getMaxCount());
				objFundraiser.setNextPage(fundraiser.getNextPage());
				objFundraiser.setMaxRowNum(fundraiser.getMaxRowNum());
				objFundraiser.setBottomBtn(fundraiser.getBottomBtn());
				objFundraiser.setMainMenuId(mainMenuId);
				objFundraiser.setIsDeptFlag(fundraiser.getIsDeptFlag());
				
			}
			else
			{
				objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUNDTEXT);
			}
		}
		else
		{
			objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUND);
			objFundraiser.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
		}
		
		if(1==fundraiser.getBottomBtn()){
			objFundraiser.setBottomBtnList(fundraiser.getBottomBtnList());
		}
		
		objFundraiser.setBottomBtn(fundraiser.getBottomBtn());
		objFundraiser.setMainMenuId(mainMenuId);
		objFundraiser.setIsDeptFlag(fundraiser.getIsDeptFlag());
		
		strResponse = XstreamParserHelper.produceXmlFromObject(objFundraiser);
		
		}
		LOG.info("Exit hotdealsServiceImpl : getFundraiserList");
		return strResponse;
	}
	
	/**
	 * Service Method to get retailer Clicks and impressions.
	 * 
	 * @param strJSON
	 * @throws HubCitiException
	 *             throws if execption occurs
	 */
	public void retailerClickImpression(String strJson) throws HubCitiException {
		LOG.info("Inside hotdealsServiceImpl : retailerClickImpression");

		final Gson gson = new Gson();
		final RetailerTrack objRetailerTrack = (RetailerTrack) gson.fromJson(strJson, RetailerTrack.class);

		hotDealsDao.retailerClickImpression(objRetailerTrack);
		
		LOG.info("Exit hotdealsServiceImpl : retailerClickImpression");
	}
	
	

	/**
	 * ServiceImpl method to get Dealsfundraiser list.
	 * 
	 * @param strJsonReq
	 * @return  string containing fundraiser list.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getFundraiserListJson(String strJsonReq) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : getFundraiserListJson ");

		String strJsonResponse = null;
		try {

			final Gson gson = new Gson();
			final MenuItem menuItem = gson.fromJson(strJsonReq, MenuItem.class);

			if (null == menuItem.getUserId() || null == menuItem.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == menuItem.getMainMenuId() && null == menuItem.getPlatform()){
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);

			} else {
				if (menuItem.getLowerLimit() == null) {
					menuItem.setLowerLimit(0);
				}
				/*
				 * Checking the for grouping parameter date, atoz, type --> catagory To
				 * group Fundraiser. Node can have below values: 
				 *  1. date – to group by date.
				 *  2. type – to group by category. 
				 *  3. atoz – to group alphabetically.
				 *  4. If NULL (or other than above mentioned value) – by default ‘date’ is selected.
				 */
				if (!"atoz".equalsIgnoreCase(menuItem.getGroupBy())
						&& !"type".equalsIgnoreCase(menuItem.getGroupBy())
						&& !"".equalsIgnoreCase(Utility.checkNull(menuItem.getGroupBy())))
				{
					menuItem.setGroupBy("date");
				}
				
				/*
	 				Checking for sorting parameter.
	 				name - Sort by Fundraiser Name. 
	 				date - Sort by Fundraiser StartDate.
				 */
				
				if("name".equalsIgnoreCase(menuItem.getSortBy())){
					menuItem.setSortBy("fundName");
				} else {
					menuItem.setSortOrder("date");
					menuItem.setSortColumn("StartDate");   
				}

				//checking the condition for sorting in Acsending or descending order.
				if(menuItem.getSortOrder() == null || !"Desc".equalsIgnoreCase(menuItem.getSortOrder())){
					menuItem.setSortOrder("Asc");
				}

				Integer mainMenuId = null;

				if(menuItem.getMainMenuId() == null ){
					mainMenuId = firstUseDao.getMainMenuId(menuItem);
					LOG.info("MainmenuId " + mainMenuId);
				}

				menuItem.setMainMenuId(mainMenuId);

				Fundraiser fundraiser = hotDealsDao.getFundraisersList(menuItem);

				fundraiser.setSortOrder(menuItem.getSortOrder());
				fundraiser.setSortBy(menuItem.getSortBy());
				fundraiser.setGroupBy(menuItem.getGroupBy());

				final List<Fundraiser> fundraiserList = fundraiser.getFundraiserList();

				Fundraiser objFundraiser = new Fundraiser();

				if(null != fundraiserList && !fundraiserList.isEmpty()){
					objFundraiser = HotDealsHelper.sortFundraiserList(fundraiser);

					if(null != objFundraiser && null != objFundraiser.getCategoryList() && !objFundraiser.getCategoryList().isEmpty()){

						objFundraiser.setResponseCode(HubCitiConstants.SUCCESSCODE);
						objFundraiser.setResponseText(HubCitiConstants.SUCCESSTEXT);
						objFundraiser.setMainMenuId(fundraiser.getMainMenuId());
						objFundraiser.setMaxCount(fundraiser.getMaxCount());
						objFundraiser.setNextPage(fundraiser.getNextPage());
						objFundraiser.setMaxRowNum(fundraiser.getMaxRowNum());
						objFundraiser.setBottomBtn(fundraiser.getBottomBtn());
						objFundraiser.setMainMenuId(mainMenuId);
						objFundraiser.setIsDeptFlag(fundraiser.getIsDeptFlag());

					} else {
						objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUND);
						objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUNDTEXT);
					}
				} else {
					objFundraiser.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					objFundraiser.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

				if(1==fundraiser.getBottomBtn()){
					objFundraiser.setBottomBtnList(fundraiser.getBottomBtnList());
				}

				objFundraiser.setBottomBtn(fundraiser.getBottomBtn());
				objFundraiser.setMainMenuId(mainMenuId);
				objFundraiser.setIsDeptFlag(fundraiser.getIsDeptFlag());

				strJsonResponse = gson.toJson(objFundraiser);
				strJsonResponse = strJsonResponse.replace("\\u003c![CDATA[", "").replace("]]\\u003e", "");

			}
			
		} catch (HubCitiException e) {
			LOG.error("Inside  hotdealsServiceImpl : getFundraiserListJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 
			LOG.info("Exit hotdealsServiceImpl : getFundraiserListJson");
			return strJsonResponse;
		}


	/**
	 * ServiceImpl Method to get List of user deals.
	 * 
	 * @param strJson, 
	 * @return strJson containing the List of user deals.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public String userDealsDisplayJson(String strJson) throws HubCitiException {
		LOG.info("Inside HotDealsServiceImpl : userDealsDisplayJson");

		final Gson gson = new Gson();
		String strJsonResponse = null;

		try {

			final Deal objDeal = gson.fromJson(strJson, Deal.class);
			if (null == objDeal.getUserId() || null == objDeal.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				Data objData= hotDealsDao.userDealsDisplay(objDeal);

				if(null == objData)	{
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				} else {
					objData.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objData.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objData);
				}
			}

		}  catch (HubCitiException e) {
			LOG.error("Inside  HotDealsServiceImpl : userDealsDisplayJson :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit HotDealsServiceImpl : userDealsDisplayJson");
		return strJsonResponse;
	}
	
}
