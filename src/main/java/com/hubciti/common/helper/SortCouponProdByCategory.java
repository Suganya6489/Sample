package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.CategoryInfo;

/**
 * This is for sorting Coupons by category
 * 
 * @author Kumar D
 */
public class SortCouponProdByCategory implements Comparator<CategoryInfo> {
	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortCouponProdByCategory.class.getName());

	public int compare(CategoryInfo objCatInfo1, CategoryInfo objCatInfo2) {
		String strCategory1 = null;
		String strCategory2 = null;
		int iCategory = 0;
		try {
			strCategory1 = objCatInfo1.getCategoryName();
			strCategory2 = objCatInfo2.getCategoryName();

			if (null == strCategory1 && null == strCategory2) {
				strCategory1 = objCatInfo1.getBusCatName();
				strCategory2 = objCatInfo2.getBusCatName();
			}
			iCategory = strCategory1.compareTo(strCategory2);
		} catch (Exception e) {
			log.info("ParseException in SortCouponProdByCategory : Exception : " + e.getMessage());
		}
		return iCategory;
	}

}
