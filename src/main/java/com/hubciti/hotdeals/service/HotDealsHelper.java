package com.hubciti.hotdeals.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.helper.SortFundCategoryByDate;
import com.hubciti.common.helper.SortFundraiserByCategory;
import com.hubciti.common.helper.SortFundraiserByDate;
import com.hubciti.common.helper.SortFundraiserByName;
import com.hubciti.common.helper.SortHotDealByAPIName;
import com.hubciti.common.helper.SortHotDealByCategory;
import com.hubciti.common.helper.SortHotDealByDealType;
import com.hubciti.common.helper.SortReverseFundByDate;
import com.hubciti.common.helper.SortReverseFundByName;
import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.pojos.Fundraiser;
import com.hubciti.common.pojos.HotDealAPIResultSet;
import com.hubciti.common.pojos.HotDealsCategoryInfo;
import com.hubciti.common.pojos.HotDealsDetails;
import com.hubciti.common.pojos.HotDealsListResultSet;
import com.hubciti.common.pojos.HotDealsResultSet;
import com.hubciti.common.utility.Utility;

/**
 * This class for implementing hot deals list for displaying category wise.
 * 
 * @author shyamsundara_hm
 */
public class HotDealsHelper {

	/**
	 * Getting the Logger Instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(HotDealsHelper.class);

	/**
	 * for HotDealsHelper constructor.
	 */
	protected HotDealsHelper() {
		LOG.info("Inside HotDealsHelper class");
	}

	/**
	 * To sort deals by API name
	 * 
	 * @param hotDealResultInputList
	 * @return hotdeal List
	 */
	public static ArrayList<HotDealAPIResultSet> sortHotdealsByAPINames(ArrayList<HotDealsResultSet> hotDealResultInputList) {
		final HashMap<String, HotDealAPIResultSet> hotDealsAPIInfoMap = new HashMap<String, HotDealAPIResultSet>();
		HotDealAPIResultSet objHotDealAPIResultSet = null;
		ArrayList<HotDealsDetails> hotDealsDetailsList = null;
		String key = null;
		for (HotDealsResultSet hotDealsDetails : hotDealResultInputList) {
			key = Utility.nullCheck(hotDealsDetails.getApiPartnerName());
			if (hotDealsAPIInfoMap.containsKey(key)) {
				objHotDealAPIResultSet = hotDealsAPIInfoMap.get(key);
				hotDealsDetailsList = objHotDealAPIResultSet.getHotDealsDetailslst();
				if (hotDealsDetailsList != null) {
					HotDealsDetails objHdDetails = new HotDealsDetails();
					objHdDetails.setHotDealName(hotDealsDetails.getHotDealName());
					objHdDetails.setHotDealId(hotDealsDetails.getHotDealId());
					objHdDetails.setHotDealImagePath(hotDealsDetails.getHotDealImagePath());
					objHdDetails.sethDshortDescription(hotDealsDetails.gethDshortDescription());
					objHdDetails.sethDPrice(hotDealsDetails.gethDPrice());
					objHdDetails.sethDSalePrice(hotDealsDetails.gethDSalePrice());
					objHdDetails.sethDDiscountAmount(hotDealsDetails.gethDDiscountAmount());
					objHdDetails.sethDDiscountPct(hotDealsDetails.gethDDiscountPct());
					objHdDetails.setProductId(hotDealsDetails.getProductId());
					objHdDetails.setRowNumber(hotDealsDetails.getRowNumber());
					objHdDetails.setHdURL(hotDealsDetails.getHdURL());
					objHdDetails.setDistance(hotDealsDetails.getDistance());
					objHdDetails.setCity(hotDealsDetails.getCity());
					objHdDetails.setHotDealListId(hotDealsDetails.getHotDealListId());
					objHdDetails.setIsDateFormated(false);
					objHdDetails.sethDStartDate(hotDealsDetails.gethDStartDate());
					objHdDetails.sethDEndDate(hotDealsDetails.gethDEndDate());
					objHdDetails.setIsDateFormated(null);
					objHdDetails.setNewFlag(hotDealsDetails.getNewFlag());
					objHdDetails.setExtFlag(hotDealsDetails.getExtFlag());
					if (null != hotDealsDetails.getCategoryId()) {
						objHdDetails.setCatId(hotDealsDetails.getCategoryId());
					} else {
						objHdDetails.setCatId(0);
					}
					objHdDetails.setCatName(hotDealsDetails.getCategoryName());
					hotDealsDetailsList.add(objHdDetails);
					objHotDealAPIResultSet.setHotDealsDetailslst(hotDealsDetailsList);
				}
			} else {
				objHotDealAPIResultSet = new HotDealAPIResultSet();
				objHotDealAPIResultSet.setApiPartnerName(hotDealsDetails.getApiPartnerName());
				objHotDealAPIResultSet.setApiPartnerId(hotDealsDetails.getApiPartnerId());
				hotDealsDetailsList = new ArrayList<HotDealsDetails>();
				HotDealsDetails objHdDetails = new HotDealsDetails();
				objHdDetails.setHotDealName(hotDealsDetails.getHotDealName());
				objHdDetails.setHotDealId(hotDealsDetails.getHotDealId());
				objHdDetails.setHotDealImagePath(hotDealsDetails.getHotDealImagePath());
				objHdDetails.sethDshortDescription(hotDealsDetails.gethDshortDescription());
				objHdDetails.sethDPrice(hotDealsDetails.gethDPrice());
				objHdDetails.sethDSalePrice(hotDealsDetails.gethDSalePrice());
				objHdDetails.sethDDiscountAmount(hotDealsDetails.gethDDiscountAmount());
				objHdDetails.sethDDiscountPct(hotDealsDetails.gethDDiscountPct());
				objHdDetails.setProductId(hotDealsDetails.getProductId());
				objHdDetails.setRowNumber(hotDealsDetails.getRowNumber());
				objHdDetails.setHdURL(hotDealsDetails.getHdURL());
				objHdDetails.setDistance(hotDealsDetails.getDistance());
				objHdDetails.setCity(hotDealsDetails.getCity());
				objHdDetails.setHotDealListId(hotDealsDetails.getHotDealListId());
				objHdDetails.setIsDateFormated(false);
				objHdDetails.sethDStartDate(hotDealsDetails.gethDStartDate());
				objHdDetails.sethDEndDate(hotDealsDetails.gethDEndDate());
				objHdDetails.setIsDateFormated(null);
				objHdDetails.setNewFlag(hotDealsDetails.getNewFlag());
				objHdDetails.setExtFlag(hotDealsDetails.getExtFlag());
				if (null != hotDealsDetails.getCategoryId()) {
					objHdDetails.setCatId(hotDealsDetails.getCategoryId());
				} else {
					objHdDetails.setCatId(0);
				}
				objHdDetails.setCatName(hotDealsDetails.getCategoryName());
				hotDealsDetailsList.add(objHdDetails);
				objHotDealAPIResultSet.setHotDealsDetailslst(hotDealsDetailsList);
			}
			hotDealsAPIInfoMap.put(key, objHotDealAPIResultSet);
		}
		final Set<Map.Entry<String, HotDealAPIResultSet>> set = hotDealsAPIInfoMap.entrySet();

		final ArrayList<HotDealAPIResultSet> hotDealsAPIInfolst = new ArrayList<HotDealAPIResultSet>();

		for (Map.Entry<String, HotDealAPIResultSet> entry : set) {
			hotDealsAPIInfolst.add(entry.getValue());
		}

		SortHotDealByAPIName objSortHDbyAPIName = new SortHotDealByAPIName();
		Collections.sort(hotDealsAPIInfolst, objSortHDbyAPIName);

		return hotDealsAPIInfolst;
	}

	/**
	 * To sort hot deal by category
	 * 
	 * @param hotDealsResultSet
	 * @return hot deal List
	 */
	public static HotDealsListResultSet sortHotDealsListByCategory(ArrayList<HotDealsDetails> hotDealsResultSet) {
		final HotDealsListResultSet hotDealsListResultSet = new HotDealsListResultSet();
		String key = null;
		final HashMap<String, HotDealsCategoryInfo> hotDealsCategoryInfoMap = new HashMap<String, HotDealsCategoryInfo>();

		ArrayList<HotDealsDetails> hotDealsDetailslst;
		HotDealsCategoryInfo hotDealsCategoryInfo = null;
		for (HotDealsDetails hotDealsDetailslist : hotDealsResultSet) {
			key = hotDealsDetailslist.getCatName();
			if (!"".equals(Utility.isNull(key))) {
				key = key.toUpperCase();
				if (hotDealsCategoryInfoMap.containsKey(key)) {
					hotDealsCategoryInfo = hotDealsCategoryInfoMap.get(key);
					hotDealsDetailslst = hotDealsCategoryInfo.getHotDealsDetailsArrayLst();
					if (null != hotDealsDetailslst) {
						final HotDealsDetails hotDealsDetailsObj = new HotDealsDetails();
						// hotDealsDetailsObj.setApiPartnerId(hotDealsDetailslist.getApiPartnerId());
						// hotDealsDetailsObj.setApiPartnerName(hotDealsDetailslist.getApiPartnerName());
						hotDealsDetailsObj.setHotDealName(hotDealsDetailslist.getHotDealName());
						hotDealsDetailsObj.setHotDealId(hotDealsDetailslist.getHotDealId());
						hotDealsDetailsObj.setHotDealImagePath(hotDealsDetailslist.getHotDealImagePath());
						hotDealsDetailsObj.sethDshortDescription(hotDealsDetailslist.gethDshortDescription());
						hotDealsDetailsObj.sethDPrice(hotDealsDetailslist.gethDPrice());
						hotDealsDetailsObj.sethDSalePrice(hotDealsDetailslist.gethDSalePrice());
						hotDealsDetailsObj.sethDDiscountAmount(hotDealsDetailslist.gethDDiscountAmount());
						hotDealsDetailsObj.sethDDiscountPct(hotDealsDetailslist.gethDDiscountPct());
						hotDealsDetailsObj.setProductId(hotDealsDetailslist.getProductId());
						hotDealsDetailsObj.setRowNumber(hotDealsDetailslist.getRowNumber());
						hotDealsDetailsObj.setHdURL(hotDealsDetailslist.getHdURL());
						hotDealsDetailsObj.setDistance(hotDealsDetailslist.getDistance());
						hotDealsDetailsObj.setCity(hotDealsDetailslist.getCity());
						hotDealsDetailsObj.setHotDealListId(hotDealsDetailslist.getHotDealListId());
						hotDealsDetailsObj.sethDStartDate(hotDealsDetailslist.gethDStartDate());
						hotDealsDetailsObj.sethDEndDate(hotDealsDetailslist.gethDEndDate());
						hotDealsDetailsObj.setNewFlag(hotDealsDetailslist.getNewFlag());
						hotDealsDetailsObj.setExtFlag(hotDealsDetailslist.getExtFlag());
						hotDealsDetailslst.add(hotDealsDetailsObj);
						hotDealsCategoryInfo.setHotDealsDetailsArrayLst(hotDealsDetailslst);
					}
				} else {
					hotDealsCategoryInfo = new HotDealsCategoryInfo();
					hotDealsCategoryInfo.setCategoryId(hotDealsDetailslist.getCatId());
					hotDealsCategoryInfo.setCategoryName(hotDealsDetailslist.getCatName());
					hotDealsDetailslst = new ArrayList<HotDealsDetails>();
					final HotDealsDetails hotDealsDetailsObj = new HotDealsDetails();
					// hotDealsDetailsObj.setApiPartnerId(hotDealsDetailslist.getApiPartnerId());
					// hotDealsDetailsObj.setApiPartnerName(hotDealsDetailslist.getApiPartnerName());
					hotDealsDetailsObj.setHotDealName(hotDealsDetailslist.getHotDealName());
					hotDealsDetailsObj.setHotDealId(hotDealsDetailslist.getHotDealId());
					hotDealsDetailsObj.setHotDealImagePath(hotDealsDetailslist.getHotDealImagePath());
					hotDealsDetailsObj.sethDshortDescription(hotDealsDetailslist.gethDshortDescription());
					hotDealsDetailsObj.sethDPrice(hotDealsDetailslist.gethDPrice());
					hotDealsDetailsObj.sethDSalePrice(hotDealsDetailslist.gethDSalePrice());
					hotDealsDetailsObj.sethDDiscountAmount(hotDealsDetailslist.gethDDiscountAmount());
					hotDealsDetailsObj.sethDDiscountPct(hotDealsDetailslist.gethDDiscountPct());
					hotDealsDetailsObj.setProductId(hotDealsDetailslist.getProductId());
					hotDealsDetailsObj.setRowNumber(hotDealsDetailslist.getRowNumber());
					hotDealsDetailsObj.setHdURL(hotDealsDetailslist.getHdURL());
					hotDealsDetailsObj.setDistance(hotDealsDetailslist.getDistance());
					hotDealsDetailsObj.setCity(hotDealsDetailslist.getCity());
					hotDealsDetailsObj.setHotDealListId(hotDealsDetailslist.getHotDealListId());
					hotDealsDetailsObj.sethDStartDate(hotDealsDetailslist.gethDStartDate());
					hotDealsDetailsObj.sethDEndDate(hotDealsDetailslist.gethDEndDate());
					hotDealsDetailsObj.setNewFlag(hotDealsDetailslist.getNewFlag());
					hotDealsDetailsObj.setExtFlag(hotDealsDetailslist.getExtFlag());
					hotDealsDetailslst.add(hotDealsDetailsObj);
					hotDealsCategoryInfo.setHotDealsDetailsArrayLst(hotDealsDetailslst);
				}
				hotDealsCategoryInfoMap.put(key, hotDealsCategoryInfo);
			}

		}
		final Set<Map.Entry<String, HotDealsCategoryInfo>> set = hotDealsCategoryInfoMap.entrySet();

		final ArrayList<HotDealsCategoryInfo> hotDealsCategoryInfolst = new ArrayList<HotDealsCategoryInfo>();

		for (Map.Entry<String, HotDealsCategoryInfo> entry : set) {
			hotDealsCategoryInfolst.add(entry.getValue());
		}

		SortHotDealByCategory objSortHDbyCategory = new SortHotDealByCategory();
		Collections.sort(hotDealsCategoryInfolst, objSortHDbyCategory);
		hotDealsListResultSet.setHotDealsCategoryInfo(hotDealsCategoryInfolst);
		return hotDealsListResultSet;
	}
	
	/**
	 * Method to Group Fundraiser. 
	 * 
	 * Grouping by - date (default), Alphabetical (atoz), category, department.
	 * @param  list of fundraiser.
	 * @return Fundraiser object
	 */
	public static Fundraiser sortFundraiserList(Fundraiser fundraiserList) {
		LOG.info("Inside HotDealsHelper : sortFundraiserList");
		
		final HashMap<String, CategoryInfo> fundCategoryMap = new HashMap<String, CategoryInfo>();
		
		CategoryInfo objCategoryInfo = null;
		Fundraiser objFundraiser = new Fundraiser();
		String strKey = null;
		
		final String strSortOrder = fundraiserList.getSortOrder();
		final String strGroupBy = fundraiserList.getGroupBy();
		final String strSortBy = fundraiserList.getSortBy();
		
		
		ArrayList<Fundraiser> arFundraiserList = null;
		
		
		 //Grouping By - Date (default), Alphabetical (atoz), Category(type).
		for (Fundraiser fundraiser : fundraiserList.getFundraiserList()) {
			
			if ("type".equalsIgnoreCase(strGroupBy)) {
				strKey = fundraiser.getFundCatName();
			} 
			else if ("atoz".equalsIgnoreCase(strGroupBy)) {
				strKey = Character.toString(fundraiser.getFundName().charAt(0)).toUpperCase();
			} else {
				strKey = fundraiser.getStartDate();
			}
			
				if (!"".equals(Utility.checkNull(strKey))) {

					if (fundCategoryMap.containsKey(strKey)) {

						objCategoryInfo = fundCategoryMap.get(strKey);
						arFundraiserList = (ArrayList<Fundraiser>) objCategoryInfo.getFundraiserList();

						if (arFundraiserList != null && !arFundraiserList.isEmpty()) {

							final Fundraiser fundrsr = new Fundraiser();

							fundrsr.setFundraiserId(fundraiser.getFundId());
							fundrsr.setFundName(fundraiser.getFundName());
							fundrsr.setsDescription(fundraiser.getsDescription());
							fundrsr.setlDescription(fundraiser.getlDescription());
							fundrsr.setImgPath(fundraiser.getImgPath());
							fundrsr.setStartDate(fundraiser.getStartDate());
							fundrsr.setFundCatId(fundraiser.getFundCatId());
							fundrsr.setFundCatName(fundraiser.getFundCatName());
							fundrsr.setFundListId(fundraiser.getFundListId());
							
							arFundraiserList.add(fundrsr);
							
							objCategoryInfo.setFundraiserList(arFundraiserList);
						}

					} else {

						objCategoryInfo = new CategoryInfo();

						if ("type".equalsIgnoreCase(strGroupBy)) {
							objCategoryInfo.setGroupContent(fundraiser.getFundCatName());
							objCategoryInfo.setCategoryId(fundraiser.getFundCatId());
						} else if ("atoz".equalsIgnoreCase(strGroupBy)) {
							objCategoryInfo.setGroupContent(Character.toString(fundraiser.getFundName().charAt(0)).toUpperCase());
							objCategoryInfo.setCategoryId(-2);
						} else {
							objCategoryInfo.setGroupContent(fundraiser.getStartDate());
							objCategoryInfo.setCategoryId(-1);
						}

						arFundraiserList = new ArrayList<Fundraiser>();

						final Fundraiser fundrser = new Fundraiser();

						fundrser.setFundraiserId(fundraiser.getFundId());
						fundrser.setFundName(fundraiser.getFundName());
						fundrser.setsDescription(fundraiser.getsDescription());
						fundrser.setlDescription(fundraiser.getlDescription());
						fundrser.setImgPath(fundraiser.getImgPath());
						fundrser.setStartDate(fundraiser.getStartDate());
						fundrser.setFundCatId(fundraiser.getFundCatId());
						fundrser.setFundCatName(fundraiser.getFundCatName());
						fundrser.setFundListId(fundraiser.getFundListId());
						
						arFundraiserList.add(fundrser);
						
						objCategoryInfo.setFundraiserList(arFundraiserList);
					}
					
					fundCategoryMap.put(strKey, objCategoryInfo);
				} 
			}

		
		final Set<Map.Entry<String, CategoryInfo>> setList = fundCategoryMap.entrySet();
		final ArrayList<CategoryInfo> arCategoryList = new ArrayList<CategoryInfo>();
		
		for (Map.Entry<String, CategoryInfo> entry : setList) {
			arCategoryList.add(entry.getValue());
		}
		
		
		/*
		   Conditions Fundraiser list. 
		   Grouping By - date (default), Alphabetical (atoz), category,.
		   Sort BY  - date (default), Name
		   SortOrder  - Asc (default), Desc
		 */
		
		if ("type".equalsIgnoreCase(strGroupBy) || "atoz".equalsIgnoreCase(strGroupBy)) {
			
			SortFundraiserByCategory sortByCategory = new SortFundraiserByCategory();
			Collections.sort(arCategoryList, sortByCategory);
			
			if ("Desc".equalsIgnoreCase(strSortOrder)) {
				Collections.reverse(arCategoryList);
			}
			
			
			for (CategoryInfo objCatInfo : arCategoryList) {
				/* To sort by Fundraiser name,  or else by Fundraiser start date. */
				if ("name".equalsIgnoreCase(strSortBy)) {
					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						SortReverseFundByName objReverseFundByName = new SortReverseFundByName();
						Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByName);
					} else {
						SortFundraiserByName sortFundByName = new SortFundraiserByName();
						Collections.sort(objCatInfo.getFundraiserList(), sortFundByName);
					}
				} else {
						if ("Desc".equalsIgnoreCase(strSortOrder)) {
							SortReverseFundByDate objReverseFundByDate = new SortReverseFundByDate();
							Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByDate);
						} else {
							SortFundraiserByDate sortFundByDate = new SortFundraiserByDate();
							Collections.sort(objCatInfo.getFundraiserList(), sortFundByDate);
						}
					}
				/* Looping to change date format. */
				for (Fundraiser fundraiser : objCatInfo.getFundraiserList()) {
					fundraiser.setStartDate(Utility.convertDBdate(fundraiser.getStartDate()));
				}
			}
			
		} else {
			
			SortFundCategoryByDate sortFundCatbyDate = new SortFundCategoryByDate();

			Collections.sort(arCategoryList, sortFundCatbyDate);
			if ("Desc".equalsIgnoreCase(strSortOrder)) {
				Collections.reverse(arCategoryList);
			}

			for (CategoryInfo objCatInfo : arCategoryList) { 
				
				if (null != objCatInfo.getGroupContent()) {
					objCatInfo.setGroupContent(Utility.convertDBdate(objCatInfo.getGroupContent()));
				}
				
			
				/* To sort by Fundraiser name,  or else by Fundraiser start date. */
				if ("name".equalsIgnoreCase(strSortBy)) {
					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						SortReverseFundByName objReverseFundByName = new SortReverseFundByName();
						Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByName);
					} else {
						SortFundraiserByName sortFundByName = new SortFundraiserByName();
						Collections.sort(objCatInfo.getFundraiserList(), sortFundByName);
					}
				} else {
					if ("Desc".equalsIgnoreCase(strSortOrder)) {
						SortReverseFundByDate objReverseFundByDate = new SortReverseFundByDate();
						Collections.sort(objCatInfo.getFundraiserList(), objReverseFundByDate);
					} else {
						SortFundraiserByDate sortFundByDate = new SortFundraiserByDate();
						Collections.sort(objCatInfo.getFundraiserList(), sortFundByDate);
					}
				}
				
				/* Looping to change date format. */
				for (Fundraiser fundraiser : objCatInfo.getFundraiserList()) {
					fundraiser.setStartDate(Utility.convertDBdate(fundraiser.getStartDate()));
				}
				
			}
		}
		
		LOG.info("Exit HotDealsHelper : sortFundraiserList");
		
		objFundraiser.setCategoryList(arCategoryList);
		return objFundraiser;
		}
	
	/*public static ArrayList<HotDealAPIResultSet> groupHotDealbyDealType(ArrayList<HotDealsResultSet> hotDealResultInputList) {
		final HashMap<String, HotDealAPIResultSet> hotDealsAPIInfoMap = new HashMap<String, HotDealAPIResultSet>();
		HotDealAPIResultSet objHotDealAPIResultSet = null;
		ArrayList<HotDealsDetails> hotDealsDetailsList = null;
		String key = null;
		for (HotDealsResultSet hotDealsDetails : hotDealResultInputList) {
			key = Utility.nullCheck(hotDealsDetails.getDealType());
			if (hotDealsAPIInfoMap.containsKey(key)) {
				objHotDealAPIResultSet = hotDealsAPIInfoMap.get(key);
				hotDealsDetailsList = objHotDealAPIResultSet.getHotDealsDetailslst();
				if (hotDealsDetailsList != null) {
					HotDealsDetails objHdDetails = new HotDealsDetails();
					objHdDetails.setHotDealName(hotDealsDetails.getHotDealName());
					objHdDetails.setHotDealId(hotDealsDetails.getHotDealId());
					objHdDetails.setHotDealImagePath(hotDealsDetails.getHotDealImagePath());
					objHdDetails.sethDshortDescription(hotDealsDetails.gethDshortDescription());
					objHdDetails.sethDPrice(hotDealsDetails.gethDPrice());
					objHdDetails.sethDSalePrice(hotDealsDetails.gethDSalePrice());
					objHdDetails.sethDDiscountAmount(hotDealsDetails.gethDDiscountAmount());
					objHdDetails.sethDDiscountPct(hotDealsDetails.gethDDiscountPct());
					objHdDetails.setProductId(hotDealsDetails.getProductId());
					objHdDetails.setRowNumber(hotDealsDetails.getRowNumber());
					objHdDetails.setHdURL(hotDealsDetails.getHdURL());
					objHdDetails.setDistance(hotDealsDetails.getDistance());
					objHdDetails.setCity(hotDealsDetails.getCity());
					objHdDetails.setHotDealListId(hotDealsDetails.getHotDealListId());
					objHdDetails.setIsDateFormated(false);
					objHdDetails.sethDStartDate(hotDealsDetails.gethDStartDate());
					objHdDetails.sethDEndDate(hotDealsDetails.gethDEndDate());
					objHdDetails.setIsDateFormated(null);
					objHdDetails.setNewFlag(hotDealsDetails.getNewFlag());
					objHdDetails.setExtFlag(hotDealsDetails.getExtFlag());
					if (null != hotDealsDetails.getCategoryId()) {
						objHdDetails.setCatId(hotDealsDetails.getCategoryId());
					} else {
						objHdDetails.setCatId(0);
					}
					objHdDetails.setCatName(hotDealsDetails.getCategoryName());
					hotDealsDetailsList.add(objHdDetails);
					objHotDealAPIResultSet.setHotDealsDetailslst(hotDealsDetailsList);
				}
			} else {
				objHotDealAPIResultSet = new HotDealAPIResultSet();
				objHotDealAPIResultSet.setDealType(hotDealsDetails.getDealType());
				hotDealsDetailsList = new ArrayList<HotDealsDetails>();
				HotDealsDetails objHdDetails = new HotDealsDetails();
				objHdDetails.setHotDealName(hotDealsDetails.getHotDealName());
				objHdDetails.setHotDealId(hotDealsDetails.getHotDealId());
				objHdDetails.setHotDealImagePath(hotDealsDetails.getHotDealImagePath());
				objHdDetails.sethDshortDescription(hotDealsDetails.gethDshortDescription());
				objHdDetails.sethDPrice(hotDealsDetails.gethDPrice());
				objHdDetails.sethDSalePrice(hotDealsDetails.gethDSalePrice());
				objHdDetails.sethDDiscountAmount(hotDealsDetails.gethDDiscountAmount());
				objHdDetails.sethDDiscountPct(hotDealsDetails.gethDDiscountPct());
				objHdDetails.setProductId(hotDealsDetails.getProductId());
				objHdDetails.setRowNumber(hotDealsDetails.getRowNumber());
				objHdDetails.setHdURL(hotDealsDetails.getHdURL());
				objHdDetails.setDistance(hotDealsDetails.getDistance());
				objHdDetails.setCity(hotDealsDetails.getCity());
				objHdDetails.setHotDealListId(hotDealsDetails.getHotDealListId());
				objHdDetails.setIsDateFormated(false);
				objHdDetails.sethDStartDate(hotDealsDetails.gethDStartDate());
				objHdDetails.sethDEndDate(hotDealsDetails.gethDEndDate());
				objHdDetails.setIsDateFormated(null);
				objHdDetails.setNewFlag(hotDealsDetails.getNewFlag());
				objHdDetails.setExtFlag(hotDealsDetails.getExtFlag());
				if (null != hotDealsDetails.getCategoryId()) {
					objHdDetails.setCatId(hotDealsDetails.getCategoryId());
				} else {
					objHdDetails.setCatId(0);
				}
				objHdDetails.setCatName(hotDealsDetails.getCategoryName());
				hotDealsDetailsList.add(objHdDetails);
				objHotDealAPIResultSet.setHotDealsDetailslst(hotDealsDetailsList);
			}
			hotDealsAPIInfoMap.put(key, objHotDealAPIResultSet);
		}
		final Set<Map.Entry<String, HotDealAPIResultSet>> set = hotDealsAPIInfoMap.entrySet();

		final ArrayList<HotDealAPIResultSet> hotDealsAPIInfolst = new ArrayList<HotDealAPIResultSet>();

		for (Map.Entry<String, HotDealAPIResultSet> entry : set) {
			hotDealsAPIInfolst.add(entry.getValue());
		}

		//SortHotDealByAPIName objSortHDbyAPIName = new SortHotDealByAPIName();
		//Collections.sort(hotDealsAPIInfolst, objSortHDbyAPIName);

		return hotDealsAPIInfolst;
	}*/
	
	public static ArrayList<HotDealsResultSet> groupHotDealbyDealType(ArrayList<HotDealsResultSet> hotDealResultInputList) {
		final HashMap<String, HotDealsResultSet> hotDealsAPIInfoMap = new HashMap<String, HotDealsResultSet>();
		HotDealsResultSet objHotDealAPIResultSet = null;
		ArrayList<HotDealsResultSet> hotDealsDetailsList = null;
		String key = null;
		for (HotDealsResultSet hotDealsDetails : hotDealResultInputList) {
			key = Utility.nullCheck(hotDealsDetails.getDealType());
			if (hotDealsAPIInfoMap.containsKey(key)) {
				objHotDealAPIResultSet = hotDealsAPIInfoMap.get(key);
				hotDealsDetailsList = objHotDealAPIResultSet.getHdResultSetList();
				if (hotDealsDetailsList != null) {
					HotDealsResultSet objHdDetails = new HotDealsResultSet();
					objHdDetails.setHotDealName(hotDealsDetails.getHotDealName());
					objHdDetails.setHotDealId(hotDealsDetails.getHotDealId());
					objHdDetails.setHotDealImagePath(hotDealsDetails.getHotDealImagePath());
					objHdDetails.sethDshortDescription(hotDealsDetails.gethDshortDescription());
					objHdDetails.sethDPrice(hotDealsDetails.gethDPrice());
					objHdDetails.sethDSalePrice(hotDealsDetails.gethDSalePrice());
					objHdDetails.sethDDiscountAmount(hotDealsDetails.gethDDiscountAmount());
					objHdDetails.sethDDiscountPct(hotDealsDetails.gethDDiscountPct());
					objHdDetails.setProductId(hotDealsDetails.getProductId());
					objHdDetails.setRowNumber(hotDealsDetails.getRowNumber());
					objHdDetails.setHdURL(hotDealsDetails.getHdURL());
					objHdDetails.setDistance(hotDealsDetails.getDistance());
					objHdDetails.setCity(hotDealsDetails.getCity());
					objHdDetails.setHotDealListID(hotDealsDetails.getHotDealListId());
					//objHdDetails.setIsDateFormated(false);
					objHdDetails.sethDStartDate(hotDealsDetails.gethDStartDate());
					objHdDetails.sethDEndDate(hotDealsDetails.gethDEndDate());
					//objHdDetails.setIsDateFormated(null);
					objHdDetails.setNewFlag(hotDealsDetails.getNewFlag());
					objHdDetails.setExtFlag(hotDealsDetails.getExtFlag());
					if (null != hotDealsDetails.getCategoryId()) {
						objHdDetails.setCategoryId(hotDealsDetails.getCategoryId());
					} else {
						objHdDetails.setCategoryId(0);
					}
					objHdDetails.setCategoryName(hotDealsDetails.getCategoryName());
					objHdDetails.setApiPartnerName(hotDealsDetails.getApiPartnerName());
					objHdDetails.setApiPartnerId(hotDealsDetails.getApiPartnerId());
					hotDealsDetailsList.add(objHdDetails);
					objHotDealAPIResultSet.setHdResultSetList(hotDealsDetailsList);
				}
			} else {
				objHotDealAPIResultSet = new HotDealsResultSet();
				objHotDealAPIResultSet.setDealType(hotDealsDetails.getDealType());
				hotDealsDetailsList = new ArrayList<HotDealsResultSet>();
				HotDealsResultSet objHdDetails = new HotDealsResultSet();
				objHdDetails.setHotDealName(hotDealsDetails.getHotDealName());
				objHdDetails.setHotDealId(hotDealsDetails.getHotDealId());
				objHdDetails.setHotDealImagePath(hotDealsDetails.getHotDealImagePath());
				objHdDetails.sethDshortDescription(hotDealsDetails.gethDshortDescription());
				objHdDetails.sethDPrice(hotDealsDetails.gethDPrice());
				objHdDetails.sethDSalePrice(hotDealsDetails.gethDSalePrice());
				objHdDetails.sethDDiscountAmount(hotDealsDetails.gethDDiscountAmount());
				objHdDetails.sethDDiscountPct(hotDealsDetails.gethDDiscountPct());
				objHdDetails.setProductId(hotDealsDetails.getProductId());
				objHdDetails.setRowNumber(hotDealsDetails.getRowNumber());
				objHdDetails.setHdURL(hotDealsDetails.getHdURL());
				objHdDetails.setDistance(hotDealsDetails.getDistance());
				objHdDetails.setCity(hotDealsDetails.getCity());
				objHdDetails.setHotDealListID(hotDealsDetails.getHotDealListId());
//				objHdDetails.setIsDateFormated(false);
				objHdDetails.sethDStartDate(hotDealsDetails.gethDStartDate());
				objHdDetails.sethDEndDate(hotDealsDetails.gethDEndDate());
//				objHdDetails.setIsDateFormated(null);
				objHdDetails.setNewFlag(hotDealsDetails.getNewFlag());
				objHdDetails.setExtFlag(hotDealsDetails.getExtFlag());
				if (null != hotDealsDetails.getCategoryId()) {
					objHdDetails.setCategoryId(hotDealsDetails.getCategoryId());
				} else {
					objHdDetails.setCategoryId(0);
				}
				objHdDetails.setCategoryName(hotDealsDetails.getCategoryName());
				objHdDetails.setApiPartnerName(hotDealsDetails.getApiPartnerName());
				objHdDetails.setApiPartnerId(hotDealsDetails.getApiPartnerId());
				hotDealsDetailsList.add(objHdDetails);
				objHotDealAPIResultSet.setHdResultSetList(hotDealsDetailsList);
			}
			hotDealsAPIInfoMap.put(key, objHotDealAPIResultSet);
		}
		final Set<Map.Entry<String, HotDealsResultSet>> set = hotDealsAPIInfoMap.entrySet();

		final ArrayList<HotDealsResultSet> hdResultSetList = new ArrayList<HotDealsResultSet>();

		for (Map.Entry<String, HotDealsResultSet> entry : set) {
			hdResultSetList.add(entry.getValue());
		}

		SortHotDealByDealType objSortHDbyDealType = new SortHotDealByDealType();
		Collections.sort(hdResultSetList, objSortHDbyDealType);

		return hdResultSetList;
	}

}