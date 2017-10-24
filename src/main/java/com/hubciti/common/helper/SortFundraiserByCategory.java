/**
* @author Kumar D
* @version 0.1
*
* This class demonstrates SortFundraiserByCategory.
*/
package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.CategoryInfo;
import com.hubciti.common.utility.Utility;

public class SortFundraiserByCategory implements Comparator<CategoryInfo> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortFundraiserByCategory.class.getName());

	public int compare(CategoryInfo c1, CategoryInfo c2) {
		String strCategory1 = null;
		String strCategory2 = null;
		int iCategory = 0;
		try {
			strCategory1 = c1.getCategoryName();
			// Group content contains category name.
			if (strCategory1 == null) {
				strCategory1 = c1.getGroupContent();
			}
			strCategory2 = c2.getCategoryName();
			// Group content contains category name.
			if (strCategory2 == null) {
				strCategory2 = c2.getGroupContent();
			}

			if (!"".equals(Utility.checkNull(strCategory1))) {
				strCategory1 = strCategory1.toUpperCase();
			}

			if (!"".equals(Utility.checkNull(strCategory2))) {
				strCategory2 = strCategory2.toUpperCase();
			}

			iCategory = strCategory1.compareTo(strCategory2);
			
		} catch (Exception e) {
			LOG.error("ParseException in SortFundraiserByCategory : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}
