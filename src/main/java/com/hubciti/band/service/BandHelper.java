package com.hubciti.band.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.helper.BandSortByDate;
import com.hubciti.common.helper.SortBandByCategory;
import com.hubciti.common.helper.SortBandByName;
import com.hubciti.common.pojos.CategoryDetails;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.pojos.Item;
import com.hubciti.common.pojos.MainCategory;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.utility.Utility;

/**
 * Class for grouping and sorting in find
 * 
 * @author Kumar D
 */
public class BandHelper {

	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(BandHelper.class);

	public static RetailersDetails groupAndSortBands(RetailersDetails bandDetails) {
		LOG.info("Inside  BandHelper : groupAndSortBands ");

		String strKey = null;
		CategoryInfo objCategoryInfo = null;
		List<RetailerDetail> retailerDetailList = null;

		final HashMap<String, CategoryInfo> catMap = new HashMap<String, CategoryInfo>();
		final RetailersDetails objRetailersDetails = new RetailersDetails();

		final String strGroupBy = bandDetails.getGroupBy();

		for (RetailerDetail retDetail : bandDetails.getRetailerDetail()) {
			/*
			 	Select key for grouping. 
			 	1. If key=type -> Grouping will be done by category.
			 	1. If key=Name -> Grouping will be done by category.
			 */
			if ("type".equalsIgnoreCase(strGroupBy)) {
				strKey = retDetail.getCatName();
			} else {
				strKey = HubCitiConstants.NOTAPPLICABLE;
			}

			if (!"".equals(Utility.checkNull(strKey))) {

				if (catMap.containsKey(strKey)) {

					objCategoryInfo = catMap.get(strKey);
					retailerDetailList = objCategoryInfo.getRetDetList();

					if (retailerDetailList != null && !retailerDetailList.isEmpty()) {

						final RetailerDetail bandDetail = new RetailerDetail();

						bandDetail.setCatIds(retDetail.getCatIds());
						bandDetail.setCatName(retDetail.getCatName());
						bandDetail.setBandName(retDetail.getBandName());
						bandDetail.setBandID(retDetail.getBandID());
						bandDetail.setBandImgPath(retDetail.getBandImgPath());
						bandDetail.setRowNumber(retDetail.getRowNumber());

						retailerDetailList.add(bandDetail);
						objCategoryInfo.setRetDetList(retailerDetailList);
					}
				} else {
					objCategoryInfo = new CategoryInfo();

					/*
						Setting groupContent for grouping. 
					 	1. type -> Grouping will be done by category.
					 	2. Name(atoz) -> Grouping will be done by BandName.
					 */
					if ("type".equalsIgnoreCase(strGroupBy)) {
						objCategoryInfo.setGroupContent(retDetail.getCatName());
					} else {
						objCategoryInfo.setGroupContent(HubCitiConstants.NOTAPPLICABLE);
					}

					retailerDetailList = new ArrayList<RetailerDetail>();

					final RetailerDetail bandDetail = new RetailerDetail();

					bandDetail.setCatIds(retDetail.getCatIds());
					bandDetail.setCatName(retDetail.getCatName());
					bandDetail.setBandName(retDetail.getBandName());
					bandDetail.setBandID(retDetail.getBandID());
					bandDetail.setBandImgPath(retDetail.getBandImgPath());
					bandDetail.setRowNumber(retDetail.getRowNumber());

					retailerDetailList.add(bandDetail);
					objCategoryInfo.setRetDetList(retailerDetailList);
				}
				catMap.put(strKey, objCategoryInfo);
			}
		}

		final Set<Map.Entry<String, CategoryInfo>> set = catMap.entrySet();
		final ArrayList<CategoryInfo> catInfoList = new ArrayList<CategoryInfo>();

		for (Map.Entry<String, CategoryInfo> entry : set) {
			catInfoList.add(entry.getValue());
		}

		SortBandByCategory sortByCategory = new SortBandByCategory();
		Collections.sort(catInfoList, sortByCategory);

		for (CategoryInfo objCatInfo : catInfoList) {
			SortBandByName sortBandByName = new SortBandByName();
			Collections.sort(objCatInfo.getRetDetList(), sortBandByName);
		} 

		objRetailersDetails.setCatList(catInfoList);

		LOG.info("Exit  BandHelper : groupAndSortBands ");
		return objRetailersDetails;
	}


	/**
	 * Method to sort an group Combinational Template pattern. 
	 * 
	 * @param instance of itemDetails.
	 * @return Item object.
	 */
	public static Item groupByPosition(Item itemDetails) {
		LOG.info("Inside  BandHelper : groupByPosition ");

		CategoryInfo objCategoryInfo = null;
		String strNewsCategory = null;
		ArrayList<Item> itemList = null;

		final LinkedHashMap <String, CategoryInfo> itemMap = new LinkedHashMap<String, CategoryInfo>();
		Item objItem = new Item();

		for (Item objItemDetails : itemDetails.getItems()) {

			strNewsCategory = objItemDetails.getCatName();

			if (!"".equals(Utility.checkNull(strNewsCategory))) {
				if (itemMap.containsKey(strNewsCategory)) {

					objCategoryInfo = itemMap.get(strNewsCategory);
					itemList = (ArrayList<Item>) objCategoryInfo.getItemList();

					if (itemList != null && !itemList.isEmpty()) {

						final Item objItemDetail = new Item();

						objItemDetail.setItemID(objItemDetails.getItemID());
						objItemDetail.setCatName(objItemDetails.getCatName());
						objItemDetail.setTitle(objItemDetails.getTitle());
						objItemDetail.setImage(objItemDetails.getImage());
						if (!"".equals(Utility.checkNull(objItemDetails.getsDesc()))) {
							objItemDetail.setsDesc(Jsoup.parse(objItemDetails.getsDesc()).text());
						}
						objItemDetail.setlDesc(objItemDetails.getlDesc());
						objItemDetail.setLink(objItemDetails.getLink());
						objItemDetail.setDate(objItemDetails.getDate());
						objItemDetail.setAuthor(objItemDetails.getAuthor());
						objItemDetail.setTime(objItemDetails.getTime());
						objItemDetail.setVideoLink(objItemDetails.getVideoLink());
						objItemDetail.setDisplayType(objItemDetails.getDisplayType());
						objItemDetail.setRowNum(objItemDetails.getRowNum());

						itemList.add(objItemDetail);
						objCategoryInfo.setItemList(itemList);
					}

				} else {
					objCategoryInfo = new CategoryInfo();

					objCategoryInfo.setGroupContent(objItemDetails.getDisplayType());
					objCategoryInfo.setCatName(objItemDetails.getCatName());
					objCategoryInfo.setCatColor(objItemDetails.getCatColor());
					objCategoryInfo.setCatTxtColor(objItemDetails.getCatTxtColor());
					objCategoryInfo.setNonfeedlink(objItemDetails.getNonfeedlink());
					objCategoryInfo.setBackButtonColor(objItemDetails.getBackButtonColor());
					itemList = new ArrayList<Item>();

					final Item objItemDetail = new Item();

					objItemDetail.setItemID(objItemDetails.getItemID());
					objItemDetail.setCatName(objItemDetails.getCatName());
					objItemDetail.setTitle(objItemDetails.getTitle());
					objItemDetail.setImage(objItemDetails.getImage());
					if (!"".equals(Utility.checkNull(objItemDetails.getsDesc()))) {
						objItemDetail.setsDesc(Jsoup.parse(objItemDetails.getsDesc()).text());
					}
					objItemDetail.setlDesc(objItemDetails.getlDesc());
					objItemDetail.setLink(objItemDetails.getLink());
					objItemDetail.setDate(objItemDetails.getDate());
					objItemDetail.setAuthor(objItemDetails.getAuthor());
					objItemDetail.setTime(objItemDetails.getTime());
					objItemDetail.setVideoLink(objItemDetails.getVideoLink());
					objItemDetail.setDisplayType(objItemDetails.getDisplayType());
					objItemDetail.setRowNum(objItemDetails.getRowNum());
					itemList.add(objItemDetail);
					objCategoryInfo.setItemList(itemList);
				}
				itemMap.put(strNewsCategory, objCategoryInfo);
			}
		}


		final Set<Map.Entry<String, CategoryInfo>> set = itemMap.entrySet();

		final ArrayList<CategoryInfo> catInfoList = new ArrayList<CategoryInfo>();

		for (Map.Entry<String, CategoryInfo> entry : set) {
			catInfoList.add(entry.getValue());
		}	

		objItem.setCategoryList(catInfoList);
		LOG.info("Exit BandHelper : groupByPosition");
		return objItem;
	}


	/**
	 * Method to group News Category and SubCategory.
	 * 
	 * @param instance of mainCatList.
	 * @return Filter object.
	 */
	public static List<MainCategory> getGrpByNewsSubCategoryList(List<MainCategory> newsCategoryList) {
		LOG.info("Inside BandHelper : getGrpByNewsSubCategoryList");

		MainCategory objNewsCategory = null;
		String strNewsCategory = null;

		final LinkedHashMap<String, MainCategory> newsCategoryMap = new LinkedHashMap<String, MainCategory>();

		for (MainCategory objCategoryDetails : newsCategoryList) {

			strNewsCategory = objCategoryDetails.getCatName();
			if (!"".equals(Utility.checkNull(strNewsCategory))) {

				if (newsCategoryMap.containsKey(strNewsCategory)) {
					objNewsCategory = newsCategoryMap.get(strNewsCategory);
					newsCategoryList = (ArrayList<MainCategory>) objNewsCategory.getMainCatList();

					if (newsCategoryList != null && !newsCategoryList.isEmpty()) {

						final MainCategory objNewsCate = new MainCategory();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						newsCategoryList.add(objNewsCate);
						objNewsCategory.setMainCatList(newsCategoryList);
					}  else {
						List<MainCategory> newsMainCategoryList = new ArrayList<MainCategory>();

						final MainCategory objNewsCate = new MainCategory();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						newsMainCategoryList.add(objNewsCate);
						objNewsCategory.setMainCatList(newsMainCategoryList);
					}

				} else {

					objNewsCategory = new MainCategory();
					objNewsCategory.setCatName(objCategoryDetails.getCatName());
					objNewsCategory.setCatId(objCategoryDetails.getCatId());
					objNewsCategory.setSortOrder(objCategoryDetails.getSortOrder());
					objNewsCategory.setFlag(objCategoryDetails.getFlag());
					objNewsCategory.setCatColor(objCategoryDetails.getCatColor());
					objNewsCategory.setCatTxtColor(objCategoryDetails.getCatTxtColor());
					objNewsCategory.setIsSubCategory(objCategoryDetails.getIsSubCategory());

					newsCategoryList = new ArrayList<MainCategory>();

					if (!"".equals(Utility.checkNull(objCategoryDetails.getSubCatName())) && !"All".equals(objCategoryDetails.getSubCatName())) {
						final MainCategory objNewsCate = new MainCategory();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						newsCategoryList.add(objNewsCate);
						objNewsCategory.setMainCatList(newsCategoryList);
					}
				}

				newsCategoryMap.put(strNewsCategory, objNewsCategory);
			}
		}


		final Set<Map.Entry<String, MainCategory>> set = newsCategoryMap.entrySet();

		final ArrayList<MainCategory> arCategoryDetailList = new ArrayList<MainCategory>();

		for (Map.Entry<String, MainCategory> entry : set) {
			arCategoryDetailList.add(entry.getValue());
		}

		LOG.info("Exit BandHelper : getGrpByNewsSubCategoryList");
		return arCategoryDetailList;
	}




	/**
	 * Method to group News Category and SubCategory.
	 * 
	 * @param instance of mainCatList.
	 * @return CategoryDetail object.
	 */
	public static List<CategoryDetails> getGrpByNewsSubCategoryBkMkandHubCitiFunctn(List<CategoryDetails> newsCategoryandHubFunctnList) {
		LOG.info("Inside BandHelper : getGrpByNewsSubCategoryBkMkandHubCitiFunctn");

		CategoryDetails objNewsCategory = null;
		String strNewsCategory = null;

		final LinkedHashMap<String, CategoryDetails> newsCategoryMap = new LinkedHashMap<String, CategoryDetails>();

		for (CategoryDetails objCategoryDetails : newsCategoryandHubFunctnList) {

			strNewsCategory = objCategoryDetails.getParCatName();
			if (!"".equals(Utility.checkNull(strNewsCategory))) {

				if (newsCategoryMap.containsKey(strNewsCategory)) {
					objNewsCategory = newsCategoryMap.get(strNewsCategory);
					newsCategoryandHubFunctnList = (ArrayList<CategoryDetails>) objNewsCategory.getListCatDetails();

					if (newsCategoryandHubFunctnList != null && !newsCategoryandHubFunctnList.isEmpty()) {

						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setIsAdded(objCategoryDetails.getIsAdded());
						objNewsCate.setIsBkMark(objCategoryDetails.getIsBkMark());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						objNewsCate.setBackButtonColor(objCategoryDetails.getBackButtonColor());
						objNewsCate.setNonfeedlink(objCategoryDetails.getNonfeedlink());

						newsCategoryandHubFunctnList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsCategoryandHubFunctnList);
					} else {
						List<CategoryDetails> newsSubCategoryandHubFunctnList = new ArrayList<CategoryDetails>();

						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setIsAdded(objCategoryDetails.getIsAdded());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						objNewsCate.setIsBkMark(objCategoryDetails.getIsBkMark());
						objNewsCate.setBackButtonColor(objCategoryDetails.getBackButtonColor());
						objNewsCate.setNonfeedlink(objCategoryDetails.getNonfeedlink());
						newsSubCategoryandHubFunctnList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsSubCategoryandHubFunctnList);
					}

				} else {

					objNewsCategory = new CategoryDetails();
					objNewsCategory.setParCatName(objCategoryDetails.getParCatName());
					objNewsCategory.setParCatId(objCategoryDetails.getParCatId());
					objNewsCategory.setSortOrder(objCategoryDetails.getSortOrder());
					objNewsCategory.setIsBkMark(objCategoryDetails.getIsBkMark());
					objNewsCategory.setIsAdded(objCategoryDetails.getIsAdded());
					objNewsCategory.setIsHubFunctn(objCategoryDetails.getIsHubFunctn());
					objNewsCategory.setCatColor(objCategoryDetails.getCatColor());
					objNewsCategory.setCatTxtColor(objCategoryDetails.getCatTxtColor());
					objNewsCategory.setIsSubCategory(objCategoryDetails.getIsSubCategory());
					objNewsCategory.setNonfeedlink(objCategoryDetails.getNonfeedlink());
					objNewsCategory.setBackButtonColor(objCategoryDetails.getBackButtonColor());
					newsCategoryandHubFunctnList = new ArrayList<CategoryDetails>();

					if (!"".equals(Utility.checkNull(objCategoryDetails.getSubCatName())) && !"All".equals(objCategoryDetails.getSubCatName())) {
						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						objNewsCate.setIsBkMark(objCategoryDetails.getIsBkMark());
						objNewsCate.setIsAdded(objCategoryDetails.getIsAdded());
						objNewsCate.setBackButtonColor(objCategoryDetails.getBackButtonColor());
						objNewsCate.setNonfeedlink(objCategoryDetails.getNonfeedlink());
						newsCategoryandHubFunctnList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsCategoryandHubFunctnList);
					}
				}
				newsCategoryMap.put(strNewsCategory, objNewsCategory);
			}
		}

		final Set<Map.Entry<String, CategoryDetails>> set = newsCategoryMap.entrySet();

		final ArrayList<CategoryDetails> arCategoryDetailList = new ArrayList<CategoryDetails>();

		for (Map.Entry<String, CategoryDetails> entry : set) {
			arCategoryDetailList.add(entry.getValue());
		}

		LOG.info("Exit BandHelper : getGrpByNewsSubCategoryList");
		return arCategoryDetailList;
	}



	public static List<CategoryDetails> getNewsBookMarkGrpByCategories(List<CategoryDetails> newsBkMkGrpByCategoriesList) {
		LOG.info("Inside BandHelper : getNewsBookMarkGrpByCategories");

		CategoryDetails objNewsCategory = null;
		String strNewsCategory = null;

		final LinkedHashMap<String, CategoryDetails> newsCategoryMap = new LinkedHashMap<String, CategoryDetails>();

		for (CategoryDetails objCategoryDetails : newsBkMkGrpByCategoriesList) {

			strNewsCategory = objCategoryDetails.getParCatName();
			if (!"".equals(Utility.checkNull(strNewsCategory))) {

				if (newsCategoryMap.containsKey(strNewsCategory)) {
					objNewsCategory = newsCategoryMap.get(strNewsCategory);
					newsBkMkGrpByCategoriesList = (ArrayList<CategoryDetails>) objNewsCategory.getListCatDetails();

					if (newsBkMkGrpByCategoriesList != null && !newsBkMkGrpByCategoriesList.isEmpty()) {

						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setIsAdded(objCategoryDetails.getIsAdded());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						objNewsCate.setIsBkMark(objCategoryDetails.getIsBkMark());
						objNewsCate.setNonfeedlink(objCategoryDetails.getNonfeedlink());
						objNewsCate.setBackButtonColor(objCategoryDetails.getBackButtonColor());
						newsBkMkGrpByCategoriesList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsBkMkGrpByCategoriesList);
					} else {
						List<CategoryDetails> newBkMkGrpByCategoriesList = new ArrayList<CategoryDetails>();

						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setIsAdded(objCategoryDetails.getIsAdded());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						objNewsCate.setIsBkMark(objCategoryDetails.getIsBkMark());
						objNewsCate.setNonfeedlink(objCategoryDetails.getNonfeedlink());
						objNewsCate.setBackButtonColor(objCategoryDetails.getBackButtonColor());
						newBkMkGrpByCategoriesList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newBkMkGrpByCategoriesList);
					}

				} else {

					objNewsCategory = new CategoryDetails();
					objNewsCategory.setParCatName(objCategoryDetails.getParCatName());
					objNewsCategory.setParCatId(objCategoryDetails.getParCatId());
					objNewsCategory.setSortOrder(objCategoryDetails.getSortOrder());
					objNewsCategory.setIsBkMark(objCategoryDetails.getIsBkMark());
					objNewsCategory.setIsAdded(objCategoryDetails.getIsAdded());
					objNewsCategory.setIsHubFunctn(objCategoryDetails.getIsHubFunctn());
					objNewsCategory.setCatColor(objCategoryDetails.getCatColor());
					objNewsCategory.setCatTxtColor(objCategoryDetails.getCatTxtColor());
					objNewsCategory.setNonfeedlink(objCategoryDetails.getNonfeedlink());
					objNewsCategory.setBackButtonColor(objCategoryDetails.getBackButtonColor());
					objNewsCategory.setIsSubCategory(objCategoryDetails.getIsSubCategory());

					newsBkMkGrpByCategoriesList = new ArrayList<CategoryDetails>();

					if (!"".equals(Utility.checkNull(objCategoryDetails.getSubCatName())) && !"All".equals(objCategoryDetails.getSubCatName())) {
						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						objNewsCate.setIsAdded(objCategoryDetails.getIsAdded());
						objNewsCate.setSortOrder(objCategoryDetails.getSortOrder());
						objNewsCate.setIsBkMark(objCategoryDetails.getIsBkMark());
						objNewsCate.setNonfeedlink(objCategoryDetails.getNonfeedlink());
						objNewsCate.setBackButtonColor(objCategoryDetails.getBackButtonColor());
						newsBkMkGrpByCategoriesList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsBkMkGrpByCategoriesList);
					}

				}

				newsCategoryMap.put(strNewsCategory, objNewsCategory);
			}
		}


		final Set<Map.Entry<String, CategoryDetails>> set = newsCategoryMap.entrySet();

		final ArrayList<CategoryDetails> arCategoryDetailList = new ArrayList<CategoryDetails>();

		for (Map.Entry<String, CategoryDetails> entry : set) {
			arCategoryDetailList.add(entry.getValue());
		}

		LOG.info("Exit BandHelper : getNewsBookMarkGrpByCategories");
		return arCategoryDetailList;
	}




	public static List<CategoryDetails> getAllNewsSubCategoriesByCatID(List<CategoryDetails> newsSubCategoriesList) {
		LOG.info("Inside BandHelper : getAllNewsSubCategoriesByCatID");

		CategoryDetails objNewsCategory = null;
		String strNewsCategory = null;

		final LinkedHashMap<String, CategoryDetails> newsSubCategoriesMap = new LinkedHashMap<String, CategoryDetails>();

		for (CategoryDetails objCategoryDetails : newsSubCategoriesList) {

			strNewsCategory = objCategoryDetails.getParCatName();
			if (!"".equals(Utility.checkNull(strNewsCategory))) {

				if (newsSubCategoriesMap.containsKey(strNewsCategory)) {
					objNewsCategory = newsSubCategoriesMap.get(strNewsCategory);
					newsSubCategoriesList = (ArrayList<CategoryDetails>) objNewsCategory.getListCatDetails();

					if (newsSubCategoriesList != null && !newsSubCategoriesList.isEmpty()) {

						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());

						newsSubCategoriesList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsSubCategoriesList);
					} else {
						List<CategoryDetails> newSubCategoriesList = new ArrayList<CategoryDetails>();

						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						newSubCategoriesList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsSubCategoriesList);
					}

				} else {

					objNewsCategory = new CategoryDetails();
					objNewsCategory.setParCatName(objCategoryDetails.getParCatName());
					objNewsCategory.setParCatId(objCategoryDetails.getParCatId());
					objNewsCategory.setCatColor(objCategoryDetails.getCatColor());
					objNewsCategory.setIsSubCategory(objCategoryDetails.getIsSubCategory());

					newsSubCategoriesList = new ArrayList<CategoryDetails>();

					if (!"".equals(Utility.checkNull(objCategoryDetails.getSubCatName())) && !"All".equals(objCategoryDetails.getSubCatName())) {
						final CategoryDetails objNewsCate = new CategoryDetails();
						objNewsCate.setSubCatName(objCategoryDetails.getSubCatName());
						objNewsCate.setSubCatId(objCategoryDetails.getSubCatId());
						objNewsCate.setIsSubCategory(objCategoryDetails.getIsSubCategory());
						newsSubCategoriesList.add(objNewsCate);
						objNewsCategory.setListCatDetails(newsSubCategoriesList);
					}
				}
				newsSubCategoriesMap.put(strNewsCategory, objNewsCategory);
			}
		}


		final Set<Map.Entry<String, CategoryDetails>> set = newsSubCategoriesMap.entrySet();

		final ArrayList<CategoryDetails> arCategoryDetailList = new ArrayList<CategoryDetails>();

		for (Map.Entry<String, CategoryDetails> entry : set) {
			arCategoryDetailList.add(entry.getValue());
		}

		LOG.info("Exit BandHelper : getAllNewsSubCategoriesByCatID");
		return arCategoryDetailList;
	}

	/**
	 * Group By Date & sort the group based on sort order
	 * @param eventDetailsList
	 * @return eventdetails object.
	 */
	public static Map<String, List<EventDetails>> sortEventList(List<EventDetails> eventDetailsFromService) {

		Map<String, List<EventDetails>> groupedList = new TreeMap<String, List<EventDetails>>(new BandSortByDate());

		for (EventDetails event : eventDetailsFromService) {
			if (groupedList.containsKey(event.getStartDate())) {
				groupedList.get(event.getStartDate()).add(event);
			} else {
				List<EventDetails> detailList = new ArrayList<EventDetails>();
				detailList.add(event);
				groupedList.put(event.getStartDate(), detailList);
			}
		}		

		return groupedList;
	}
	
}