package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.HotDealsCategoryInfo;

/**
 * This class for sorting wish list history based on date.
 * 
 * @author Kumar D
 */
public class SortHotDealByCategory implements Comparator<HotDealsCategoryInfo> {
	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortHotDealByCategory.class.getName());

	/**
	 * This method is to compare two products.
	 * 
	 * @param objHDCategoryInfo1
	 *            -As parameter
	 * @param objHDCategoryInfo2
	 *            -As parameter
	 * @return iCategory
	 */
	public int compare(HotDealsCategoryInfo objHDCategoryInfo1, HotDealsCategoryInfo objHDCategoryInfo2) {
		String strCategory1 = null;
		String strCategory2 = null;
		int iCategory = 0;
		try {
			strCategory1 = objHDCategoryInfo1.getCategoryName();
			strCategory2 = objHDCategoryInfo2.getCategoryName();
			iCategory = strCategory1.compareTo(strCategory2);
		} catch (Exception e) {
			log.info("ParseException in SortHotDealByCategory : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}
