package com.hubciti.band.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hubciti.band.dao.BandDao;
import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.exception.HubCitiException;
import com.hubciti.common.pojos.Categories;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.MainCategory;
import com.hubciti.common.pojos.Menu;
import com.hubciti.common.pojos.MenuItem;
import com.hubciti.common.pojos.News;
import com.hubciti.common.pojos.RetailerCreatedPages;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.pojos.SubCategory;
import com.hubciti.common.utility.Utility;
import com.hubciti.firstuse.dao.FirstUseDao;

public class BandServiceImpl implements BandService {

	/**
	 * Getting the logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BandServiceImpl.class);

	/**
	 * Instance variable for BandDao.
	 */
	public BandDao bandDao;
	/**
	 * @param bandDao
	 */
	public void setBandDao(BandDao bandDao) {
		this.bandDao = bandDao;
	}

	/**
	 * Instance variable for FirstUseDAO.
	 */
	public FirstUseDao firstUseDao;

	/**
	 * Setter method for FirstUseDao.
	 * 
	 * @param firstUseDao
	 *            Instance of type FirstUseDao.
	 */
	public void setFirstUseDao(FirstUseDao firstUseDao) {
		this.firstUseDao = firstUseDao;
	}

	/**
	 * The ServiceImpl method fetches Event Type List.
	 * @param strJSON as request
	 * @return Json Event ArrayList.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getAllEventTypes(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getAllEventTypes");

		String strJsonResponse = null;

		try {

			final Gson gson = new Gson();
			final MenuItem objMenuItem = gson.fromJson(strJson, MenuItem.class);

			/*if (null == objMenuItem.getHubCitiId() || null == objMenuItem.getUserId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else if (null == objMenuItem.getLatitude() || null == objMenuItem.getLongitude() || null == objMenuItem.getPostalCode()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			}  else if (null == objMenuItem.getRadius()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {*/

			// For user tracking
			Integer mainMenuId = null;
			if (null == objMenuItem.getMainMenuId()) {
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = objMenuItem.getMainMenuId();
			}

			Categories evtTypeList = bandDao.getAllEventTypes(objMenuItem);

			if (evtTypeList != null && evtTypeList.getCatList() != null && !evtTypeList.getCatList().isEmpty()) {
				evtTypeList.setResponseCode(HubCitiConstants.SUCCESSCODE);
				evtTypeList.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				evtTypeList.setCatList(null);
				evtTypeList.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				evtTypeList.setResponseText(evtTypeList.getNoRecordMsg());
			}
			evtTypeList.setNoRecordMsg(null);
			evtTypeList.setMainMenuId(mainMenuId);
			strJsonResponse = gson.toJson(evtTypeList);
			/*}*/

		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getAllEventTypes :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandServiceImpl : getAllEventTypes");
		return strJsonResponse;
	}



	/**
	 * The ServiceImpl method fetches Band List.
	 * @param strJSON as request
	 * @return Json Band ArrayList.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getBandList(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getBandList");

		String strJsonResponse = null;

		try {
			final Gson gson = new Gson();
			final RetailerDetail retailDetail = gson.fromJson(strJson, RetailerDetail.class);

			/*if (retailDetail.getHubCitiId() == null || retailDetail.getCatName() == null || "".equals(retailDetail.getCatName())) {
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else if (retailDetail.getmItemId() == null && retailDetail.getBottomBtnId() == null && retailDetail.getMainMenuId() == null) {
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTCODE);
		} else {*/
			RetailersDetails bandDetails = null;

			Integer mainMenuId = null;
			if (retailDetail.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setmItemId(retailDetail.getmItemId());
				objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = retailDetail.getMainMenuId();
			}
			retailDetail.setMainMenuId(mainMenuId);

			/*
			 	Condition  
			 	1. If groupBy is type(Category Name "Genre"), Name(BandName "AtoZ")- grouping is done accordingly. 
			    2. If sortBy is name- sorting is done by Band name. 
			    3. sortOrder by Ascending.
			 */
			retailDetail.setSortColumn("BandName");
			retailDetail.setSortOrder("ASC");

			bandDetails = bandDao.getBandList(retailDetail);

			RetailersDetails bandGrpSort = new RetailersDetails();

			if (bandDetails != null && bandDetails.getRetailerDetail() != null
					&& !bandDetails.getRetailerDetail().isEmpty()) {

				bandDetails.setGroupBy(retailDetail.getGroupBy());
				bandDetails.setSortColumn(retailDetail.getSortColumn());
				bandDetails.setSortOrder(retailDetail.getSortOrder());
				/*
			 	Grouping on below parameter. 
			 		type - group by category. 

			    Sorting in database result set.
					name - Sort by BandName. 
				 */
				bandGrpSort = BandHelper.groupAndSortBands(bandDetails);

				bandDetails.setRetailerDetail(null);
				bandDetails.setCatList(bandGrpSort.getCatList());

				bandDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				bandDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				bandDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				bandDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			bandDetails.setGroupBy(null);
			bandDetails.setSortColumn(null);
			bandDetails.setSortOrder(null);

			bandDetails.setMainMenuId(mainMenuId);

			strJsonResponse = gson.toJson(bandDetails);
			/*}*/

		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getBandList :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getBandList ");
		return strJsonResponse;
	}



	/**
	 * This ServiceImpl method fetches Band information.
	 * @param bandID
	 * @param hubCitiId
	 * @return String Json with Band details.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getBandSummary(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getBandSummary");

		String strJsonResponse = null;

		List<RetailerDetail> bandSummaryList = null;
		List<RetailerCreatedPages> bandCreatedPagesList = null;
		RetailersDetails objBandSummary = null;

		try {

			final Gson gson = new Gson();
			final RetailerDetail objRetDetail = gson.fromJson(strJson, RetailerDetail.class);

			/*	if (null == objRetDetail.getBandID()) {
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE,HubCitiConstants.INSUFFICENTREQUESTTEXT);
		} else {*/
			bandSummaryList = (List<RetailerDetail>) bandDao.getBandSummary(objRetDetail);

			if (bandSummaryList != null && !bandSummaryList.isEmpty()) {
				objBandSummary = new RetailersDetails();
				if (!"".equals(Utility.checkNull(bandSummaryList.get(0).getContactPhone()))) {
					bandSummaryList.get(0).setContactPhone(Utility.getPhoneFormate(bandSummaryList.get(0).getContactPhone().trim()));
				}

				objBandSummary.setRetailerDetail(bandSummaryList);
				bandCreatedPagesList = bandDao.getBandCreatePages(objRetDetail);

				if (bandCreatedPagesList != null && !bandCreatedPagesList.isEmpty()) {
					objBandSummary.setEventExist(bandCreatedPagesList.get(0).getEventExist());
					objBandSummary.setFundExist(bandCreatedPagesList.get(0).getFundExist());

					bandCreatedPagesList.get(0).setEventExist(null);
					bandCreatedPagesList.get(0).setFundExist(null);
					objBandSummary.setRetailerCreatedPageList(bandCreatedPagesList);
				} else {
					objBandSummary.setEventExist(0);
					objBandSummary.setFundExist(0);
				}
			}

			if (objBandSummary != null) {
				objBandSummary.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objBandSummary.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strJsonResponse = gson.toJson(objBandSummary);
			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}
			/*	}*/

		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getBandSummary :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandServiceImpl : getBandSummary");
		return strJsonResponse;
	}


	/**
	 * ServiceImpl method to get event list, also convert request xml to object and
	 * response object to xml.
	 * 
	 * @param strJson
	 * @return xml string containing alerts list.
	 * @throws HubCitiException
	 */
	public String getEventList(String strJson) throws HubCitiException {

		LOG.info("Inside BandServiceImpl : getEventList ");
		EventDetails eventDetails = null;
		String strJsonResponse = null;
		EventDetails objEventDetails = null;

		try {

			final Gson gson = new Gson();
			final MenuItem objMenuItem = gson.fromJson(strJson, MenuItem.class);

			if (null == objMenuItem.getUserId() || null == objMenuItem.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {

				objMenuItem.setGroupBy("date");

				if (null == objMenuItem.getLowerLimit()) {
					objMenuItem.setLowerLimit(0);
				}

				if( null != objMenuItem.getSortBy()){
					objMenuItem.setSortColumn(objMenuItem.getSortBy());
				}else{
					objMenuItem.setSortBy("date");
					objMenuItem.setSortColumn("Date");
				}

				if (null == objMenuItem.getSortOrder()
						|| !"Desc".equalsIgnoreCase(objMenuItem.getSortOrder())) {
					objMenuItem.setSortOrder("Asc");
				}

				Integer mainMenuId = null;
				if (null ==  objMenuItem.getMainMenuId()) {
					mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
				} else {
					mainMenuId = objMenuItem.getMainMenuId();
				}
				objMenuItem.setMainMenuId(mainMenuId);
				eventDetails = bandDao.getEventList(objMenuItem);



				if (null != eventDetails && null != eventDetails.getEventList() && !eventDetails.getEventList().isEmpty()) {

					eventDetails.setSortOrder(objMenuItem.getSortOrder());
					eventDetails.setSortBy(objMenuItem.getSortBy());
					eventDetails.setGroupBy(objMenuItem.getGroupBy());

					Map<String, List<EventDetails>> groupedList = BandHelper.sortEventList(eventDetails.getEventList());

					Set<String> keys = groupedList.keySet();
					List<EventDetails> evtlist = new ArrayList<EventDetails>();

					if( null != keys){
						for( String key : keys ){
							EventDetails singleEvents = new EventDetails(); 
							singleEvents.setGroupContent(Utility.convertmmFormattoMMMFormat(key));
							singleEvents.setEventList(groupedList.get(key));
							evtlist.add(singleEvents);
						}
					}
					eventDetails.setEventGroupList(evtlist);
					eventDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					eventDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

					eventDetails.setSortBy(null);
					eventDetails.setGroupBy(null);
					eventDetails.setSortOrder(null);
					eventDetails.setEventList(null);
					objEventDetails = eventDetails;

				} else {
					objEventDetails = new EventDetails();
					objEventDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
					objEventDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}

			} 
			strJsonResponse = gson.toJson(objEventDetails);
			if( null != strJsonResponse){
				strJsonResponse = strJsonResponse.replace("\\u003c![CDATA[","");
				strJsonResponse = strJsonResponse.replace("]]\\u003e","");
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getEventList :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getEventList ");
		return strJsonResponse;
	}	




	/**
	 * ServiceImpl method for displaying News BookMark and Non BookMark catagories.
	 * 
	 * @param strJSON as request
	 * @return JSON containing BookMark and Non BookMark catagories array list response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public  String getNewsBookMarkCategories(String strJSON) throws HubCitiException {
		LOG.info("Inside  BandServiceImpl : getNewsBookMarkCategories()");
		String strJsonResponse = null;
		CategoryDetails objNewsBkMkCategories = null;

		final Gson gson = new Gson();
		final News objNews = gson.fromJson(strJSON, News.class);

		try {
			if (null == objNews.getUserId() || null == objNews.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {

				objNewsBkMkCategories = bandDao.getNewsBookMarkCategories(objNews);
				if (null != objNewsBkMkCategories && null != objNewsBkMkCategories.getListCatDetails() && !objNewsBkMkCategories.getListCatDetails().isEmpty()) {

					if (null == objNews.getIsBkMark()) {
						objNews.setIsBkMark(0);
					}

					CategoryDetails objBkMkCategories =  new CategoryDetails();
					CategoryDetails objNormalCategories = new CategoryDetails();

					List <CategoryDetails> markCategoryList =  new ArrayList<CategoryDetails>();
					List <CategoryDetails> UnMarkCategoryList = new ArrayList<CategoryDetails>();

					for (CategoryDetails objMarkCatagory : objNewsBkMkCategories.getListCatDetails()) {

						if (1 == objMarkCatagory.getIsAdded()) {
							markCategoryList.add(objMarkCatagory);
						} else if (0 == objNews.getIsBkMark() && 0 == objMarkCatagory.getIsAdded()){
							UnMarkCategoryList.add(objMarkCatagory);
						}
					}

					List<CategoryDetails> arBkMarkCatList = new ArrayList<CategoryDetails>();

					if (null != markCategoryList && !markCategoryList.isEmpty() ) {
						objBkMkCategories.setListCatDetails(BandHelper.getNewsBookMarkGrpByCategories(markCategoryList));
						objBkMkCategories.setHeader(objNewsBkMkCategories.getHeadOne());
						arBkMarkCatList.add(objBkMkCategories);
					}

					if (null != UnMarkCategoryList && !UnMarkCategoryList.isEmpty() ) {
						objNormalCategories.setListCatDetails(BandHelper.getNewsBookMarkGrpByCategories(UnMarkCategoryList));
						objNormalCategories.setHeader(objNewsBkMkCategories.getHeadTwo());
						arBkMarkCatList.add(objNormalCategories);
					}
					
					CategoryDetails objNewsBkMkCategoriesnew = new CategoryDetails();
					objNewsBkMkCategoriesnew.setBookMarkList(arBkMarkCatList);
					
					objNewsBkMkCategoriesnew.setBannerImg(objNewsBkMkCategories.getBannerImg());
					objNewsBkMkCategoriesnew.setBkImgPath(objNewsBkMkCategories.getBkImgPath());
					objNewsBkMkCategoriesnew.setHomeImgPath(objNewsBkMkCategories.getHomeImgPath());
					objNewsBkMkCategoriesnew.setWeatherURL(objNewsBkMkCategories.getWeatherURL());
					objNewsBkMkCategoriesnew.setTitleBkGrdColor(objNewsBkMkCategories.getTitleBkGrdColor());
					objNewsBkMkCategoriesnew.setHamburgerImg(objNewsBkMkCategories.getHamburgerImg());
					
					objNewsBkMkCategoriesnew.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objNewsBkMkCategoriesnew.setResponseText(HubCitiConstants.SUCCESSTEXT);

					strJsonResponse = gson.toJson(objNewsBkMkCategoriesnew);

				} else if(null != objNewsBkMkCategories){
					CategoryDetails objNewsBkMkCategoriesnew = new CategoryDetails();
					objNewsBkMkCategoriesnew.setBannerImg(objNewsBkMkCategories.getBannerImg());
					objNewsBkMkCategoriesnew.setBkImgPath(objNewsBkMkCategories.getBkImgPath());
					objNewsBkMkCategoriesnew.setHomeImgPath(objNewsBkMkCategories.getHomeImgPath());
					objNewsBkMkCategoriesnew.setWeatherURL(objNewsBkMkCategories.getWeatherURL());
					objNewsBkMkCategoriesnew.setTitleBkGrdColor(objNewsBkMkCategories.getTitleBkGrdColor());
					objNewsBkMkCategoriesnew.setHamburgerImg(objNewsBkMkCategories.getHamburgerImg());
					objNewsBkMkCategoriesnew.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					objNewsBkMkCategoriesnew.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
					strJsonResponse = gson.toJson(objNewsBkMkCategoriesnew);
					
				}else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getNewsBookMarkCategories :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandServiceImpl : getNewsBookMarkCategories ");
		return strJsonResponse;
	}



	/**
	 * ServiceImpl method for Updating News BookMark and HubCiti Functionality.
	 * 
	 * @param strJSON as request
	 * @return JSON containing BookMark and Non BookMark catagories array list response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public  String updateNewsBookMarkCategories(String strJSON) throws HubCitiException {
		LOG.info("Inside  BandServiceImpl : updateNewsBookMarkCategories ");
		String strJsonResponse = null;

		final Gson gson = new Gson();
		final News objNews = gson.fromJson(strJSON, News.class);

		try {
			if (null==objNews.getUserId()||null ==objNews.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTCODE, HubCitiConstants.INSUFFICENTREQUESTTEXT);
			} else {
				strJsonResponse = bandDao.updateNewsBookMarkCategories(objNews);
				if (strJsonResponse == HubCitiConstants.SUCCESSCODE) {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.SUCCESSCODE, HubCitiConstants.SUCCESSTEXT);
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.FAILURETEXT);
				}
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : updateNewsBookMarkCategories :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : updateNewsBookMarkCategories ");
		return strJsonResponse;
	}



	/**
	 * ServiceImpl method for get News detail from news ID.
	 * 
	 * @param  newsID as request
	 * @return JSON containing  News detail information.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public  String getNewsDetail(Integer newsID) throws HubCitiException {
		LOG.info("Inside  BandServiceImpl : getNewsDetail");

		String strJsonResponse = null;
		List<Item> itemList = null;

		final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		final Item objItem = new Item();

		try {

			if (null == newsID) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {

				itemList = bandDao.getNewsDetail(newsID);
				if (null != itemList && !itemList.isEmpty()) {
					objItem.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objItem.setResponseText(HubCitiConstants.SUCCESSTEXT);

					if (!"".equals(Utility.checkNull(itemList.get(0).getlDesc()))) {
						itemList.get(0).setsDesc(Jsoup.parse(itemList.get(0).getlDesc()).text());
						
						if (itemList.get(0).getsDesc().length() > 150) {
							itemList.get(0).setsDesc(itemList.get(0).getsDesc().substring(0, 150) + "...") ;
						}
					}
					
					objItem.setItems(itemList);
				} else {
					objItem.setResponseCode(HubCitiConstants.NORECORDSFOUNDCODE);
					objItem.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
				}
				strJsonResponse = gson.toJson(objItem);
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getNewsDetail :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getNewsDetail");
		return strJsonResponse;
	}



	/**
	 * ServiceImpl method for displaying both HubCiti Functionality and News categories.
	 * 
	 * @param strJSON as request
	 * @return JSON containing both HubCiti Functionality and News categories array list response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public  String getNewsSideNavigationMenu(String strJSON) throws HubCitiException {
		LOG.info("Inside  BandServiceImpl : getNewsSideNavigationMenu ");
		String strJsonResponse = null;
		CategoryDetails objNewsCatDetail = null;

		try {

			final Gson gson = new Gson();
			final Menu objMenu =  (Menu) gson.fromJson(strJSON, Menu.class);

			objNewsCatDetail = bandDao.getNewsSideNavigationMenu(objMenu);
			if ((null != objNewsCatDetail.getListMainCat() && !objNewsCatDetail.getListMainCat().isEmpty())
					|| (null != objNewsCatDetail.getMenuList() && !objNewsCatDetail.getMenuList().isEmpty())) {

				List<MainCategory> mainCatList = new ArrayList<MainCategory>();
				List<CategoryDetails> finalCategoryList = new ArrayList<CategoryDetails>();
				if (null != objNewsCatDetail.getListMainCat() && !objNewsCatDetail.getListMainCat().isEmpty()) {
					mainCatList = BandHelper.getGrpByNewsSubCategoryList(objNewsCatDetail.getListMainCat());

					CategoryDetails objNewsCategory =  new CategoryDetails();
					objNewsCategory.setListMainCat(mainCatList);
					finalCategoryList.add(objNewsCategory);
				}

				if (null != objNewsCatDetail.getMenuList() && !objNewsCatDetail.getMenuList().isEmpty()) {
					CategoryDetails objMenuFunctnty =  new CategoryDetails();
					objMenuFunctnty.setMenuList(objNewsCatDetail.getMenuList());
					finalCategoryList.add(objMenuFunctnty);
				}

				objNewsCatDetail = new CategoryDetails();
				objNewsCatDetail.setListCatDetails(finalCategoryList);

				objNewsCatDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objNewsCatDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strJsonResponse = gson.toJson(objNewsCatDetail);

			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getNewsSideNavigationMenu :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandServiceImpl : getNewsSideNavigationMenu ");
		return strJsonResponse;
	}


	/**
	 * The ServiceImpl method for fetching News List by Category's. 
	 * @param strJSON as request
	 * @return Json News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getNewsListByCategories(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getNewsListByCategories");

		String strJsonResponse = null;

		try {
			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final Item itemDetail = gson.fromJson(strJson, Item.class);

			Item objNewsDetails = null;
			objNewsDetails = bandDao.getNewsListByCategories(itemDetail);

			if (objNewsDetails != null && objNewsDetails.getItems() != null
					&& !objNewsDetails.getItems().isEmpty()) {

				
				for (int i = 0; i < objNewsDetails.getItems().size(); i++) {
					if (!"".equals(Utility.checkNull(objNewsDetails.getItems().get(i).getlDesc()))) {
						objNewsDetails.getItems().get(i).setsDesc(Jsoup.parse(objNewsDetails.getItems().get(i).getlDesc()).text());
						if (objNewsDetails.getItems().get(i).getsDesc().length() > 150) {
							objNewsDetails.getItems().get(i).setsDesc(objNewsDetails.getItems().get(i).getsDesc().substring(0, 150) + "...") ;
						} 
					}
				}

				objNewsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objNewsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);

				strJsonResponse = gson.toJson(objNewsDetails);
			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getNewsListByCategories :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getNewsListByCategories ");
		return strJsonResponse;
	}


	/**
	 * The ServiceImpl method for fetching Combinational News  List by Category's information. 
	 * @param strJSON as request
	 * @return Json Combination pattern News  List by Category's information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getCombNewsListByCategories(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getCombNewsListByCategories");

		String strJsonResponse = null;
		Item objCombNewsDetail = null;

		try {
			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final Item itemDetail = gson.fromJson(strJson, Item.class);

			if (null == itemDetail.getLevel()) {
				itemDetail.setLevel(1);
			}

			if (null == itemDetail.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {

				if (null == itemDetail.getLowerLimit()) {
					itemDetail.setLowerLimit(0);
				}

				Item objCombNewsDetails = bandDao.getCombNewsListByCategories(itemDetail);
				int iFlag = 0;

				if (objCombNewsDetails != null && objCombNewsDetails.getItems() != null
						&& !objCombNewsDetails.getItems().isEmpty()) {

					iFlag = 1;
					objCombNewsDetail = BandHelper.groupByPosition(objCombNewsDetails);
					objCombNewsDetail.setMaxCnt(objCombNewsDetails.getMaxCnt());
					objCombNewsDetail.setLowerLimitFlag(objCombNewsDetails.getLowerLimitFlag());
					objCombNewsDetail.setNextPage(objCombNewsDetails.getNextPage());
					objCombNewsDetail.setBannerImg(objCombNewsDetails.getBannerImg());
				} else if (objCombNewsDetails.getTemplateChanged() == 1) {
					iFlag = 2;
				} else {
					strJsonResponse = Utility.validationResponseJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT, "hamburgerImg", objCombNewsDetails.getHamburgerImg());
				}


				if (0 < iFlag) {
					if (null == objCombNewsDetail ) {
						objCombNewsDetail = new Item();
					}
					objCombNewsDetail.setTemplateChanged(objCombNewsDetails.getTemplateChanged());
					objCombNewsDetail.setNewtempName(objCombNewsDetails.getNewtempName());
					objCombNewsDetail.setModifiedDate(objCombNewsDetails.getModifiedDate());
					objCombNewsDetail.setHomeImgPath(objCombNewsDetails.getHomeImgPath());
					objCombNewsDetail.setBkImgPath(objCombNewsDetails.getBkImgPath());
					objCombNewsDetail.setTitleBkGrdColor(objCombNewsDetails.getTitleBkGrdColor());
					objCombNewsDetail.setTitleTxtColor(objCombNewsDetails.getTitleTxtColor());
					objCombNewsDetail.setWeatherURL(objCombNewsDetails.getWeatherURL());
					objCombNewsDetail.setHamburgerImg(objCombNewsDetails.getHamburgerImg());
					objCombNewsDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objCombNewsDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objCombNewsDetail);
				}
			}

		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getCombNewsListByCategories :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getCombNewsListByCategories ");
		return strJsonResponse;
	}

	


	/**
	 * The ServiceImpl method for fetching Scrolling News List by Category's information. 
	 * @param strJSON as request
	 * @return Json Scrolling pattern News List by Category's information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getScrollingNewsListByCategories(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getScrollingNewsListByCategories");

		String strJsonResponse = null;

		try {
			final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
			final Item itemDetail = gson.fromJson(strJson, Item.class);

			if (null == itemDetail.getLevel()) {
				itemDetail.setLevel(1);
			}

			if(null == itemDetail.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			}else {

				if (null == itemDetail.getLowerLimit()) {
					itemDetail.setLowerLimit(0);
				}

				Item objScrolNewsDetails = bandDao.getScrollingNewsListByCategories(itemDetail);
				if (objScrolNewsDetails != null && objScrolNewsDetails.getItems() != null
						&& !objScrolNewsDetails.getItems().isEmpty()) {

					for (int i = 0; i < objScrolNewsDetails.getItems().size(); i++) {
						if (!"".equals(Utility.checkNull(objScrolNewsDetails.getItems().get(i).getlDesc()))) {
							objScrolNewsDetails.getItems().get(i).setsDesc(Jsoup.parse(objScrolNewsDetails.getItems().get(i).getlDesc()).text());
							if (objScrolNewsDetails.getItems().get(i).getsDesc().length() > 150) {
								objScrolNewsDetails.getItems().get(i).setsDesc(objScrolNewsDetails.getItems().get(i).getsDesc().substring(0, 150) + "...") ;
							} 
						}
					}
					
					objScrolNewsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objScrolNewsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objScrolNewsDetails);
				} else if(objScrolNewsDetails.getTemplateChanged() == 1) {

					objScrolNewsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objScrolNewsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objScrolNewsDetails);
				} else {
					strJsonResponse = Utility.validationResponseJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT,"hamburgerImg", objScrolNewsDetails.getHamburgerImg(),"backButtonColor",objScrolNewsDetails.getBackButtonColor());	
				}
			}
		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getScrollingNewsListByCategories :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getScrollingNewsListByCategories ");
		return strJsonResponse;
	}



	/**
	 * The ServiceImpl method for displaying all News  Category's. 
	 * @param strJSON as request
	 * @return Json News List by Category's.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getAllNewsCategories(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getAllNewsCategories");

		String strJsonResponse = null;
		CategoryDetails objNewsCatDetail = null;
		try {
			final Gson gson = new Gson();
			final News objNews = gson.fromJson(strJson, News.class);


			objNewsCatDetail = bandDao.getAllNewsCategories(objNews);

			if (objNewsCatDetail != null && objNewsCatDetail.getListCatDetails() != null
					&& !objNewsCatDetail.getListCatDetails().isEmpty()) {

				List<MainCategory> mainCatList = new ArrayList<MainCategory>();
				mainCatList = getMainCategory(objNewsCatDetail);

				CategoryDetails objCatDetails =  new CategoryDetails();
				objCatDetails.setListMainCat(mainCatList);

				objNewsCatDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
				objNewsCatDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);
				strJsonResponse = gson.toJson(objNewsCatDetail);
			} else {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
			}

		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getAllNewsCategories :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getAllNewsCategories ");
		return strJsonResponse;
	}

	public  List<MainCategory>  getMainCategory(CategoryDetails objNewsCatDetail) {

		Integer currentCatId = null;
		Integer prevCatId = 0;
		Boolean firstEntry = true;
		MainCategory objMainCat = null;
		SubCategory objSubCat = null;
		List<MainCategory> mainCatList = new ArrayList<MainCategory>();
		List<SubCategory> subCatList = null;
		int i = 0;
		for (CategoryDetails obj : objNewsCatDetail.getListCatDetails()) {
			i++;
			currentCatId = obj.getCatId();
			if (prevCatId == currentCatId) {
				objSubCat = new SubCategory();
				objSubCat.setSubCatId(obj.getSubCatId());
				objSubCat.setSubCatName(obj.getSubCatName());
				objSubCat.setIsAdded(obj.getIsAdded());
				subCatList.add(objSubCat);

			} else {

				if (!firstEntry) {
					objMainCat.setListSubCat(subCatList);
					mainCatList.add(objMainCat);
				}

				objMainCat = new MainCategory();
				objMainCat.setParCatId(obj.getCatId());
				objMainCat.setParCatName(obj.getCatName());
				objMainCat.setIsAdded(obj.getIsAdded());
				subCatList = new ArrayList<SubCategory>();
				objSubCat = new SubCategory();
				objSubCat.setSubCatId(obj.getSubCatId());
				objSubCat.setSubCatName(obj.getSubCatName());
				objSubCat.setIsAdded(obj.getIsAdded());
				subCatList.add(objSubCat);
			}

			firstEntry = false;
			prevCatId = currentCatId;

			if (i == objNewsCatDetail.getListCatDetails().size()) {
				objMainCat.setListSubCat(subCatList);
				mainCatList.add(objMainCat);
			}
		}
		return mainCatList;
	}




	/**
	 * ServiceImpl method for displaying News BookMark and HubCiti Functionality.
	 * 
	 * @param strJSON as request
	 * @return JSON containing News BookMark and HubCiti Functionality list.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public  String getNewsBookMarkAndHubCitiFunctn(String strJSON) throws HubCitiException {
		LOG.info("Inside  BandServiceImpl : getNewsBookMarkAndHubCitiFunctn()");

		String strJsonResponse = null;
		CategoryDetails objNewsCatDetail = null;

		final Gson gson = new Gson();
		final News objNews = gson.fromJson(strJSON, News.class);

		try {
			if (null == objNews.getUserId() || null == objNews.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {

				objNewsCatDetail = bandDao.getNewsBookMarkAndHubCitiFunctn(objNews);
				if (null != objNewsCatDetail && null != objNewsCatDetail.getListCatDetails() && !objNewsCatDetail.getListCatDetails().isEmpty()) {

					if (null == objNews.getIsBkMark()) {
						objNews.setIsBkMark(0);
					}

					final CategoryDetails objBookMarkCat =  new CategoryDetails();
					final CategoryDetails objNormalCat = new CategoryDetails();

					final List <CategoryDetails> markCategoryList =  new ArrayList<CategoryDetails>();
					final List <CategoryDetails> UnMarkCategoryList = new ArrayList<CategoryDetails>();

					for (CategoryDetails objMarkCatagory : objNewsCatDetail.getListCatDetails()) {

						if (1 == objMarkCatagory.getIsAdded()) {
							markCategoryList.add(objMarkCatagory);
						} else if (0 == objNews.getIsBkMark() && 0 == objMarkCatagory.getIsAdded()){
							UnMarkCategoryList.add(objMarkCatagory);
						}
					}

					final List<CategoryDetails> finalCategoryList = new ArrayList<CategoryDetails>();

					if (null != markCategoryList && !markCategoryList.isEmpty() ) {

						objBookMarkCat.setListCatDetails(BandHelper.getGrpByNewsSubCategoryBkMkandHubCitiFunctn(markCategoryList));
						objBookMarkCat.setHeader(objNewsCatDetail.getHeadOne());
						finalCategoryList.add(objBookMarkCat);
					}

					if (null != UnMarkCategoryList && !UnMarkCategoryList.isEmpty() ) {
						objNormalCat.setListCatDetails(BandHelper.getGrpByNewsSubCategoryBkMkandHubCitiFunctn(UnMarkCategoryList));
						objNormalCat.setHeader(objNewsCatDetail.getHeadTwo());
						finalCategoryList.add(objNormalCat);
					}

					objNewsCatDetail = new CategoryDetails();

					objNewsCatDetail.setBookMarkList(finalCategoryList);
					objNewsCatDetail.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objNewsCatDetail.setResponseText(HubCitiConstants.SUCCESSTEXT);

					strJsonResponse = gson.toJson(objNewsCatDetail);

				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}

		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getNewsBookMarkAndHubCitiFunctn :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		} 

		LOG.info("Exit BandServiceImpl : getNewsBookMarkAndHubCitiFunctn");
		return strJsonResponse;
	}



	/**
	 * ServiceImpl method for displaying All News Sub categories.
	 * 
	 * @param strJSON as request
	 * @return JSON containing All News Sub categories arrayList response.
	 * @throws HubCitiException throws if exception occurs.
	 */
	public  String getAllNewsSubCategoriesByCatID(String strJSON) throws HubCitiException {
		LOG.info("Inside  BandServiceImpl : getAllNewsSubCategoriesByCatID");

		String strJsonResponse = null;
		List<CategoryDetails> newsSubCatList = null;

		final Gson gson = new Gson();
		final News objNews = gson.fromJson(strJSON, News.class);

		try {

			if (null == objNews.getUserId() || null == objNews.getCatName()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {

				newsSubCatList = bandDao.getAllNewsSubCategoriesByCatID(objNews.getUserId(), objNews.getCatName());
				if (null != newsSubCatList && !newsSubCatList.isEmpty()) {

					final CategoryDetails objSubCategories =  new CategoryDetails();
					objSubCategories.setListCatDetails(BandHelper.getAllNewsSubCategoriesByCatID(newsSubCatList));
					objSubCategories.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objSubCategories.setResponseText(HubCitiConstants.SUCCESSTEXT);

					strJsonResponse = gson.toJson(objSubCategories);

				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUNDCODE, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		} catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getAllNewsSubCategoriesByCatID :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}

		LOG.info("Exit BandServiceImpl : getAllNewsSubCategoriesByCatID ");
		return strJsonResponse;
	}




	/**
	 * The ServiceImpl method for fetching Block News List by Category's information. 
	 * @param strJSON as request
	 * @return Json Block Pattern News  List by Category's information.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getBlockNewsListByCategories(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getBlockNewsListByCategories");

		String strJsonResponse = null;

		try {
			final Gson gson = new Gson();
			final Item itemDetail = gson.fromJson(strJson, Item.class);

			if (null == itemDetail.getLevel()) {
				itemDetail.setLevel(1);
			}

			if(null == itemDetail.getHubCitiId()) {
				strJsonResponse = Utility.validRespJSON(HubCitiConstants.INSUFFICENTREQUESTTEXT, HubCitiConstants.INSUFFICENTREQUESTCODE);
			} else {

				if (null == itemDetail.getLowerLimit()) {
					itemDetail.setLowerLimit(0);
				}
				Item objBlockNewsDetails = bandDao.getBlockNewsListByCategories(itemDetail);

				if (objBlockNewsDetails != null && objBlockNewsDetails.getItems() != null
						&& !objBlockNewsDetails.getItems().isEmpty()) {

					objBlockNewsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objBlockNewsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objBlockNewsDetails);
				} else if(objBlockNewsDetails.getTemplateChanged()==1){
					objBlockNewsDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
					objBlockNewsDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
					strJsonResponse = gson.toJson(objBlockNewsDetails);
				} else {
					strJsonResponse = Utility.validRespJSON(HubCitiConstants.NORECORDSFOUND, HubCitiConstants.NORECORDSFOUNDTEXT);
				}
			}
		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getBlockNewsListByCategories :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getBlockNewsListByCategories ");
		return strJsonResponse;
	}




	/**
	 * The ServiceImpl method is for fetching Event Band List Information.
	 * @param strJSON as request
	 * @return Json Band ArrayList.
	 * @throws HubCitiException
	 *             throws if exception occurs.
	 */
	public String getEventBandsList(String strJson) throws HubCitiException {
		LOG.info("Inside BandServiceImpl : getEventBandsList");

		String strJsonResponse = null;

		try {

			final Gson gson = new Gson();
			final RetailerDetail retailDetail = gson.fromJson(strJson, RetailerDetail.class);

			RetailersDetails bandDetails = null;

			Integer mainMenuId = null;
			if (retailDetail.getMainMenuId() == null) {
				MenuItem objMenuItem = new MenuItem();
				objMenuItem.setmItemId(retailDetail.getmItemId());
				objMenuItem.setBottomBtnId(retailDetail.getBottomBtnId());
				mainMenuId = firstUseDao.getMainMenuId(objMenuItem);
			} else {
				mainMenuId = retailDetail.getMainMenuId();
			}
			retailDetail.setMainMenuId(mainMenuId);
			bandDetails = bandDao.getEventBandsList(retailDetail);


			if (bandDetails != null && bandDetails.getRetailerDetail() != null
					&& !bandDetails.getRetailerDetail().isEmpty()) {

				bandDetails.setResponseCode(HubCitiConstants.SUCCESSCODE);
				bandDetails.setResponseText(HubCitiConstants.SUCCESSTEXT);
			} else {
				bandDetails.setResponseCode(HubCitiConstants.NORECORDSFOUND);
				bandDetails.setResponseText(HubCitiConstants.NORECORDSFOUNDTEXT);
			}

			bandDetails.setGroupBy(null);
			bandDetails.setSortColumn(null);
			bandDetails.setSortOrder(null);

			bandDetails.setMainMenuId(mainMenuId);

			strJsonResponse = gson.toJson(bandDetails);

		}  catch (HubCitiException e) {
			LOG.error("Inside  BandServiceImpl : getEventBandsList :  " + e.getMessage());
			strJsonResponse = Utility.validRespJSON(HubCitiConstants.TECHNICALPROBLEMCODE, HubCitiConstants.TECHNICALPROBLEMTEXT);
		}
		LOG.info("Exit BandServiceImpl : getEventBandsList ");
		return strJsonResponse;
	}

}


