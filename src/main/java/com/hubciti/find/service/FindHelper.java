package com.hubciti.find.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.helper.SortFilterByCategory;
import com.hubciti.common.helper.SortFilterByHeader;
import com.hubciti.common.helper.SortFindByCategory;
import com.hubciti.common.helper.SortFindByCity;
import com.hubciti.common.helper.SortFindByDistance;
import com.hubciti.common.helper.SortFindByName;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.Filter;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.utility.Utility;

/**
 * Class for grouping and sorting in find
 * 
 * @author Kumar D
 */
public class FindHelper {

	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(FindHelper.class);

	public static RetailersDetails groupAndSortRetailers(RetailersDetails retDetails) {
		LOG.info("Inside  FindHelper : groupAndSortRetailers ");

		String key = null;
		CategoryInfo objCategoryInfo = null;
		List<RetailerDetail> retailerDetailList = null;
		String strCompleteAddress;

		final HashMap<String, CategoryInfo> catMap = new HashMap<String, CategoryInfo>();
		final RetailersDetails objRetailersDetails = new RetailersDetails();

		final String strSortOrder = retDetails.getSortOrder();
		final String strGroupBy = retDetails.getGroupBy();
		final String strSortColumn = retDetails.getSortColumn();

		for (RetailerDetail retDetail : retDetails.getRetailerDetail()) {
			/*
			 	Select key for grouping. 
			 	1. If key=N/A -> No grouping i.e. only one group will be there with groupContent value N/A 
			 	2. If key=atoz -> Grouping will be done alphabetically. 
			 	3. If key=type -> Grouping will be done by category.
			 */
			if (HubCitiConstants.NOTAPPLICABLE.equalsIgnoreCase(strGroupBy)) {
				key = HubCitiConstants.NOTAPPLICABLE;
			} else if ("atoz".equalsIgnoreCase(strGroupBy)) {
				key = Character.toString(retDetail.getRetailerName().charAt(0)).toUpperCase();
			} else {
				key = retDetail.getSubCatName();
			}

			if (!"".equals(Utility.checkNull(key))) {

				if (catMap.containsKey(key)) {

					objCategoryInfo = catMap.get(key);
					retailerDetailList = objCategoryInfo.getRetDetList();

					if (retailerDetailList != null && !retailerDetailList.isEmpty()) {

						final RetailerDetail retailerDetail = new RetailerDetail();

						strCompleteAddress = new String();

						if (null != retDetail.getRetaileraddress1()
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getRetaileraddress1())
								&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getRetaileraddress1())) {
							strCompleteAddress = retDetail.getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null != retDetail.getCity() && !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getCity())
								&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getCity())) {
							strCompleteAddress = strCompleteAddress + retDetail.getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null != retDetail.getPostalCode()
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getPostalCode())
								&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getPostalCode())) {
							strCompleteAddress = strCompleteAddress + retDetail.getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null != retDetail.getState() && !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getState())
								&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getState())) {
							strCompleteAddress = strCompleteAddress + retDetail.getState();
						}

						retailerDetail.setRetListId(retDetail.getRetListId());
						retailerDetail.setRetailerId(retDetail.getRetailerId());
						retailerDetail.setRetailerName(retDetail.getRetailerName());
						retailerDetail.setRetailLocationId(retDetail.getRetailLocationId());
						retailerDetail.setCompleteAddress(strCompleteAddress);
						retailerDetail.setRetLatitude(retDetail.getRetLatitude());
						retailerDetail.setRetLongitude(retDetail.getRetLongitude());
						retailerDetail.setDistance(retDetail.getDistance());
						retailerDetail.setLogoImagePath(retDetail.getLogoImagePath());
						retailerDetail.setBannerAdImagePath(retDetail.getBannerAdImagePath());
						retailerDetail.setRibbonAdImagePath(retDetail.getRibbonAdImagePath());
						retailerDetail.setRibbonAdURL(retDetail.getRibbonAdURL());
						retailerDetail.setAdvertisementId(retDetail.getAdvertisementId());
						retailerDetail.setSplashAdId(retDetail.getSplashAdId());
						retailerDetail.setSaleFlag(retDetail.getSaleFlag());
						retailerDetail.setCity(retDetail.getCity());
						retailerDetail.setCatId(retDetail.getCatId());
						retailerDetail.setCatName(retDetail.getCatName());

						retailerDetailList.add(retailerDetail);
						objCategoryInfo.setRetDetList(retailerDetailList);
					}
				} else {
					objCategoryInfo = new CategoryInfo();

					/*
						Setting groupContent for grouping. 
						1. N/A -> No grouping i.e. only one group will be there with groupContent value N/A 
						2. atoz -> Grouping will be done alphabetically. 
					 	3. type -> Grouping will be done by sub category.
					 	4. city -> Grouping will be done by city.
					 */
					if (HubCitiConstants.NOTAPPLICABLE.equalsIgnoreCase(strGroupBy)) {
						objCategoryInfo.setGroupContent(HubCitiConstants.NOTAPPLICABLE);
					} else if ("atoz".equalsIgnoreCase(strGroupBy)) {
						objCategoryInfo.setGroupContent(Character.toString(retDetail.getRetailerName().charAt(0)).toUpperCase());
					}/* else if ("city".equalsIgnoreCase(groupBy)) {
						objCategoryInfo.setGroupContent(retDetail.getCity());
					} */else {
						objCategoryInfo.setGroupContent(retDetail.getSubCatName());
					}

					retailerDetailList = new ArrayList<RetailerDetail>();

					final RetailerDetail retailerDetail = new RetailerDetail();

					strCompleteAddress = new String();

					if (null != retDetail.getRetaileraddress1()
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getRetaileraddress1())
							&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getRetaileraddress1())) {
						strCompleteAddress = retDetail.getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retDetail.getCity() && !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getCity())
							&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getCity())) {
						strCompleteAddress = strCompleteAddress + retDetail.getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retDetail.getPostalCode()
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getPostalCode())
							&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getPostalCode())) {
						strCompleteAddress = strCompleteAddress + retDetail.getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retDetail.getState() && !HubCitiConstants.NOTAPPLICABLE.equals(retDetail.getState())
							&& !HubCitiConstants.EMPTYSTR.equals(retDetail.getState())) {
						strCompleteAddress = strCompleteAddress + retDetail.getState();
					}

					retailerDetail.setRetListId(retDetail.getRetListId());
					retailerDetail.setRetailerId(retDetail.getRetailerId());
					retailerDetail.setRetailerName(retDetail.getRetailerName());
					retailerDetail.setRetailLocationId(retDetail.getRetailLocationId());
					retailerDetail.setCompleteAddress(strCompleteAddress);
					retailerDetail.setRetLatitude(retDetail.getRetLatitude());
					retailerDetail.setRetLongitude(retDetail.getRetLongitude());
					retailerDetail.setDistance(retDetail.getDistance());
					retailerDetail.setLogoImagePath(retDetail.getLogoImagePath());
					retailerDetail.setBannerAdImagePath(retDetail.getBannerAdImagePath());
					retailerDetail.setRibbonAdImagePath(retDetail.getRibbonAdImagePath());
					retailerDetail.setRibbonAdURL(retDetail.getRibbonAdURL());
					retailerDetail.setAdvertisementId(retDetail.getAdvertisementId());
					retailerDetail.setSplashAdId(retDetail.getSplashAdId());
					retailerDetail.setSaleFlag(retDetail.getSaleFlag());
					retailerDetail.setCity(retDetail.getCity());
					retailerDetail.setCatId(retDetail.getCatId());
					retailerDetail.setCatName(retDetail.getCatName());

					retailerDetailList.add(retailerDetail);
					objCategoryInfo.setRetDetList(retailerDetailList);
				}
				catMap.put(key, objCategoryInfo);
			}
		}

		final Set<Map.Entry<String, CategoryInfo>> set = catMap.entrySet();
		final ArrayList<CategoryInfo> catInfoList = new ArrayList<CategoryInfo>();

		for (Map.Entry<String, CategoryInfo> entry : set) {
			catInfoList.add(entry.getValue());
		}

		if ("type".equalsIgnoreCase(strGroupBy) || "atoz".equalsIgnoreCase(strGroupBy)) {

			SortFindByCategory sortByCategory = new SortFindByCategory();
			Collections.sort(catInfoList, sortByCategory);

			if ("Desc".equalsIgnoreCase(strSortOrder)) {
				Collections.reverse(catInfoList);
			}

			for (CategoryInfo objCatInfo : catInfoList) {

				//To sort by name or distance or city.
				if ("name".equalsIgnoreCase(strSortColumn)) {

					SortFindByName sortFindByName = new SortFindByName();
					Collections.sort(objCatInfo.getRetDetList(), sortFindByName);

					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						Collections.reverse(objCatInfo.getRetDetList());
					}

				} else if ("distance".equalsIgnoreCase(strSortColumn)) {

					SortFindByDistance sortFindByDistance = new SortFindByDistance();
					Collections.sort(objCatInfo.getRetDetList(), sortFindByDistance);

					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						Collections.reverse(objCatInfo.getRetDetList());
					}
				} else if ("city".equalsIgnoreCase(strSortColumn)) {

					SortFindByCity sortFindByCity= new SortFindByCity();
					Collections.sort(objCatInfo.getRetDetList(), sortFindByCity);

					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						Collections.reverse(objCatInfo.getRetDetList());
					}
				}
			}
		}
		objRetailersDetails.setCatList(catInfoList);

		LOG.info("Exit  FindHelper : groupAndSortRetailers ");
		return objRetailersDetails;
	}



	/**
	 * Method to sort an group Filter. Grouping by
	 * 
	 * @param instance of Filter.
	 * @return Filter object.
	 */
	public static ArrayList<Filter> sortFilterHeaderList(List<Filter> filterList ) {
		LOG.info("Inside FindHelper : sortFilterList");

		Filter objFilter = null;
		String key = null;

		final HashMap<String, Filter> filterMap = new HashMap<String, Filter>();

		for (Filter objFilterDetails : filterList) {

			key = objFilterDetails.getfHeader();
			if (!"".equals(Utility.checkNull(key))) {
				
				if (filterMap.containsKey(key)) {
					objFilter = filterMap.get(key);
					filterList = (ArrayList<Filter>) objFilter.getFilterList();

					if (filterList != null && !filterList.isEmpty()) {
						
						final Filter objFilters = new Filter();
						objFilters.setFilterName(objFilterDetails.getFilterName());
						
						filterList.add(objFilters);
						objFilter.setFilterList(filterList);
					}
					
				} else {

					objFilter = new Filter();
					objFilter.setfHeader(objFilterDetails.getfHeader());
					filterList = new ArrayList<Filter>();
					final Filter objFilters = new Filter();
					objFilters.setFilterName(objFilterDetails.getFilterName());

					filterList.add(objFilters);
					objFilter.setFilterList(filterList);
				}
				
				filterMap.put(key, objFilter);
			}
		}


		final Set<Map.Entry<String, Filter>> set = filterMap.entrySet();

		final ArrayList<Filter> arFilterList = new ArrayList<Filter>();

		for (Map.Entry<String, Filter> entry : set) {
			arFilterList.add(entry.getValue());
		}
		
		SortFilterByHeader objSortFilterByHeader = new SortFilterByHeader();
		Collections.sort(arFilterList, objSortFilterByHeader);
		
	/*	filterDetailObj.setFilterList(arFilterList);

		LOG.info("Exit FindHelper : sortEventList");
		return filterDetailObj;*/
		return arFilterList;
	}
	
	
	
	
	
	/**
	 * Method to group Sort  &  Filter list. 
	 * 
	 * @param instance of Filter.
	 * @return Filter object.
	 */
	public static Filter sortFilterList(Filter objFilter) {
		LOG.info("Inside FindHelper : sortFilterList");

		CategoryInfo objCategoryInfo = null;
		String key = null;
		ArrayList<Filter> filterList = null;
		List<Filter> oneFilterFlagList = null;
		List<Filter> zeroFilterFlagList = null;

		final HashMap<String, CategoryInfo> filterMap = new HashMap<String, CategoryInfo>();
		Filter filterDetailObj = new Filter();


		// Separating Option Filter and Non-Option Filter and putting in different lists
		oneFilterFlagList = new ArrayList<Filter>();
		zeroFilterFlagList = new ArrayList<Filter>();

		for (Filter objFilters : objFilter.getFilterList()) {
			if (objFilters.getFilterFlag() == 1) {
				oneFilterFlagList.add(objFilters);
			} else {
				zeroFilterFlagList.add(objFilters);
			}
		}



		for (Filter objFilterDetails : oneFilterFlagList) {

			key = objFilterDetails.getFilterName();

			if (!"".equals(Utility.checkNull(key))) {
				if (filterMap.containsKey(key)) {

					objCategoryInfo = filterMap.get(key);
					filterList = (ArrayList<Filter>) objCategoryInfo.getFilterList();

					if (filterList != null && !filterList.isEmpty()) {

						final Filter objZeroFilter = new Filter();

						objZeroFilter.setfValueId(objFilterDetails.getfValueId());
						objZeroFilter.setfValueName(objFilterDetails.getfValueName());
						objZeroFilter.setFilterFlag(objFilterDetails.getFilterFlag());

						filterList.add(objZeroFilter);
						objCategoryInfo.setFilterList(filterList);
					}
					
				} else {

					objCategoryInfo = new CategoryInfo();

					objCategoryInfo.setGroupContent(objFilterDetails.getFilterName());
					objCategoryInfo.setCategoryId(objFilterDetails.getFilterId());

					filterList = new ArrayList<Filter>();

					final Filter objFlagOneFilter = new Filter();

					objFlagOneFilter.setfValueId(objFilterDetails.getfValueId());
					objFlagOneFilter.setfValueName(objFilterDetails.getfValueName());
					objFlagOneFilter.setFilterFlag(objFilterDetails.getFilterFlag());

					filterList.add(objFlagOneFilter);

					objCategoryInfo.setFilterList(filterList);
				}
				
				filterMap.put(key, objCategoryInfo);
			}
		}


		final Set<Map.Entry<String, CategoryInfo>> set = filterMap.entrySet();

		final ArrayList<CategoryInfo> catInfoList = new ArrayList<CategoryInfo>();

		for (Map.Entry<String, CategoryInfo> entry : set) {
			catInfoList.add(entry.getValue());
		}	

		SortFilterByCategory sortByCategory = new SortFilterByCategory();
		Collections.sort(catInfoList, sortByCategory);

		filterDetailObj.setCategoryList(catInfoList);

		LOG.info("Exit FindHelper : sortFilterList");
		return filterDetailObj;
	}


}
