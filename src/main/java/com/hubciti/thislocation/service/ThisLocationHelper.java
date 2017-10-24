package com.hubciti.thislocation.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.helper.SortCitiExpReailerByName;
import com.hubciti.common.helper.SortCitiExpRetailerByCity;
import com.hubciti.common.helper.SortCitiExpRetailerByDistance;
import com.hubciti.common.helper.SortFindByCategory;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.Retailer;
import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.pojos.RetailersDetails;
import com.hubciti.common.utility.Utility;

/**
 * Helper class to Grouping and sorting in Citi Experience.
 * 
 * @author Kumar D
 *
 */
public class ThisLocationHelper {

	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ThisLocationHelper.class);
	
	/**
	 * Method to group and sort City Experience Retailers.
	 * @param retailersDetails
	 * @return {@link RetailersDetails} object
	 */
	public static RetailersDetails groupAndSortCitiExpRetailers(RetailersDetails retailersDetails)	{
		LOG.info("Inside ThisLocationHelper : groupAndSortCitiExpRetailers");
		
		RetailersDetails objRetailersDetails = new RetailersDetails();
		final HashMap<String, CategoryInfo> retCategoryMap = new HashMap<String, CategoryInfo>();
		
		String strKey = null;
		List<Retailer> arRetailerList = null;
		CategoryInfo objCategoryInfo = null;
		String completeAddress;
		
		final String strGroupBy = retailersDetails.getGroupBy();
		final String strSortByCol = retailersDetails.getSortBy();
		final String strSortOrder = retailersDetails.getSortOrder();
		
		// For Grouping, atoz (alphabetic) and type(category).
		for(RetailerDetail retDet : retailersDetails.getRetailerDetail())	{


			if ("atoz".equalsIgnoreCase(strGroupBy)) {
				strKey = Character.toString(retDet.getRetailerName().charAt(0)).toUpperCase();
			} else if("type".equalsIgnoreCase(strGroupBy))	{
				strKey = retDet.getCatName();
			} else {
				strKey = HubCitiConstants.NOTAPPLICABLE;
			}


			if(!"".equals(Utility.checkNull(strKey)))	{

				if(retCategoryMap.containsKey(strKey))	{

					objCategoryInfo = retCategoryMap.get(strKey);
					arRetailerList = objCategoryInfo.getRetailerList();

					if(null != arRetailerList && !arRetailerList.isEmpty())	{

						final Retailer retailer = new Retailer();
						completeAddress = new String();

						if (null != retDet.getRetaileraddress1()
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getRetaileraddress1())
								&& !HubCitiConstants.EMPTYSTR.equals(retDet.getRetaileraddress1())) {
							completeAddress = retDet.getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null != retDet.getCity() && !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getCity())
								&& !HubCitiConstants.EMPTYSTR.equals(retDet.getCity())) {
							completeAddress = completeAddress + retDet.getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null != retDet.getPostalCode()
								&& !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getPostalCode())
								&& !HubCitiConstants.EMPTYSTR.equals(retDet.getPostalCode())) {
							completeAddress = completeAddress + retDet.getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
						}
						if (null != retDet.getState() && !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getState())
								&& !HubCitiConstants.EMPTYSTR.equals(retDet.getState())) {
							completeAddress = completeAddress + retDet.getState();
						}

						retailer.setRowNumber(retDet.getRowNumber());
						retailer.setRetailerId(retDet.getRetailerId());
						retailer.setRetailerName(retDet.getRetailerName());
						retailer.setRetailLocationId(retDet.getRetailLocationId());
						retailer.setRetailAddress(completeAddress);
						retailer.setDistance(retDet.getDistance());
						retailer.setLogoImagePath(retDet.getLogoImagePath());
						retailer.setBannerAdImagePath(retDet.getBannerAdImagePath());
						retailer.setRibbonAdImagePath(retDet.getRibbonAdImagePath());
						retailer.setRibbonAdURL(retDet.getRibbonAdURL());
						retailer.setSaleFlag(retDet.getSaleFlag());
						retailer.setSplashAdId(retDet.getSplashAdId());
						retailer.setRetListId(retDet.getRetListId());
						retailer.setLatitude(retDet.getRetLatitude());
						retailer.setLongitude(retDet.getRetLongitude());
						retailer.setRetGroupImg(retDet.getRetGroupImg());
						retailer.setCity(retDet.getCity());
						retailer.setCatId(retDet.getCateId());
						retailer.setCatName(retDet.getCatName());

						arRetailerList.add(retailer);
						objCategoryInfo.setRetailerList(arRetailerList);
					}
				} else {

					objCategoryInfo = new CategoryInfo();
					//objCategoryInfo.setGroupContent(key);


					/*if(HubCitiConstants.NOTAPPLICABLE.equals(key))	{
						objCategoryInfo.setCategoryId(0);
					} else	{
						objCategoryInfo.setCategoryId(retDet.getCatId());
					}*/

					/*
					  Setting groupContent for grouping. 
						2. atoz -> Grouping will be done alphabetically. 
					 	3. type -> Grouping will be done by category.
					 	4. city -> Grouping will be done by city.
					 */


					if ("atoz".equalsIgnoreCase(strGroupBy)) {
						objCategoryInfo.setGroupContent(Character.toString(retDet.getRetailerName().charAt(0)).toUpperCase());
					} else if("type".equalsIgnoreCase(strGroupBy))	{
						objCategoryInfo.setGroupContent(retDet.getCatName());
					} else {
						objCategoryInfo.setGroupContent(HubCitiConstants.NOTAPPLICABLE);
					}


					arRetailerList = new ArrayList<Retailer>();
					final Retailer retailer = new Retailer();

					completeAddress = new String();

					if (null != retDet.getRetaileraddress1()
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getRetaileraddress1())
							&& !HubCitiConstants.EMPTYSTR.equals(retDet.getRetaileraddress1())) {
						completeAddress = retDet.getRetaileraddress1() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retDet.getCity() && !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getCity())
							&& !HubCitiConstants.EMPTYSTR.equals(retDet.getCity())) {
						completeAddress = completeAddress + retDet.getCity() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retDet.getPostalCode()
							&& !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getPostalCode())
							&& !HubCitiConstants.EMPTYSTR.equals(retDet.getPostalCode())) {
						completeAddress = completeAddress + retDet.getPostalCode() + HubCitiConstants.COMMA + HubCitiConstants.EMPTYSTRING;
					}
					if (null != retDet.getState() && !HubCitiConstants.NOTAPPLICABLE.equals(retDet.getState())
							&& !HubCitiConstants.EMPTYSTR.equals(retDet.getState())) {
						completeAddress = completeAddress + retDet.getState();
					}

					retailer.setRowNumber(retDet.getRowNumber());
					retailer.setRetailerId(retDet.getRetailerId());
					retailer.setRetailerName(retDet.getRetailerName());
					retailer.setRetailLocationId(retDet.getRetailLocationId());
					retailer.setRetailAddress(completeAddress);
					retailer.setDistance(retDet.getDistance());
					retailer.setLogoImagePath(retDet.getLogoImagePath());
					retailer.setBannerAdImagePath(retDet.getBannerAdImagePath());
					retailer.setRibbonAdImagePath(retDet.getRibbonAdImagePath());
					retailer.setRibbonAdURL(retDet.getRibbonAdURL());
					retailer.setSaleFlag(retDet.getSaleFlag());
					retailer.setSplashAdId(retDet.getSplashAdId());
					retailer.setRetListId(retDet.getRetListId());
					retailer.setLatitude(retDet.getRetLatitude());
					retailer.setLongitude(retDet.getRetLongitude());
					retailer.setRetGroupImg(retDet.getRetGroupImg());
					retailer.setCity(retDet.getCity());
					retailer.setCatId(retDet.getCateId());
					retailer.setCatName(retDet.getCatName());
					arRetailerList.add(retailer);
					objCategoryInfo.setRetailerList(arRetailerList);
				}
				retCategoryMap.put(strKey, objCategoryInfo);
			}
		}
		
		
		final Set<Map.Entry<String, CategoryInfo>> set = retCategoryMap.entrySet();
		final ArrayList<CategoryInfo> catInfoList = new ArrayList<CategoryInfo>();
		
		for (Map.Entry<String, CategoryInfo> entry : set) {
			catInfoList.add(entry.getValue());
		}
		
		
		if ("type".equalsIgnoreCase(strGroupBy) || "atoz".equalsIgnoreCase(strGroupBy)) {

			//Sorting Category.
			SortFindByCategory sortCategory = new SortFindByCategory();
			Collections.sort(catInfoList, sortCategory);

			if ("Desc".equalsIgnoreCase(strSortOrder)) {
				Collections.reverse(catInfoList);
			}
			
			//Sorting retailers inside category.
			for(CategoryInfo categoryInfo : catInfoList)	{

				if("RetailerName".equalsIgnoreCase(strSortByCol))	{ 
					
					SortCitiExpReailerByName sortByRetName = new SortCitiExpReailerByName();
					Collections.sort(categoryInfo.getRetailerList(), sortByRetName);
				} else if("city".equalsIgnoreCase(strSortByCol)) { 
					
					SortCitiExpRetailerByCity sortByCity = new SortCitiExpRetailerByCity();
					Collections.sort(categoryInfo.getRetailerList(), sortByCity);
				} else {
					
					SortCitiExpRetailerByDistance sortByRetDist = new SortCitiExpRetailerByDistance();
					Collections.sort(categoryInfo.getRetailerList(), sortByRetDist);
				}
			}
		} 
		
		objRetailersDetails.setCatList(catInfoList);
		return objRetailersDetails;
	}
}
