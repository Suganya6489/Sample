package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.pojos.CategoryInfo;

public class SortAlertEventByCategory implements Comparator<CategoryInfo> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortAlertEventByCategory.class.getName());

	public int compare(CategoryInfo o1, CategoryInfo o2) {
		String strCategory1 = null;
		String strCategory2 = null;
		int iCategory = 0;
		try {
			strCategory1 = o1.getCategoryName();
			// Group content contains category name.
			if (strCategory1 == null) {
				strCategory1 = o1.getGroupContent();
			}
			strCategory2 = o2.getCategoryName();
			// Group content contains category name.
			if (strCategory2 == null) {
				strCategory2 = o2.getGroupContent();
			}

			if (strCategory1 != null && !"".equals(strCategory1)) {
				strCategory1 = strCategory1.toUpperCase();
			}
			
			if (strCategory2 != null && !"".equals(strCategory2)) {
				strCategory2 = strCategory2.toUpperCase();
			}

			if (strCategory1.equalsIgnoreCase(HubCitiConstants.SIG_EVENTS)) {
				iCategory = -1;
			} else if (strCategory2.equalsIgnoreCase(HubCitiConstants.SIG_EVENTS)) {
				iCategory = 1;
			} else if (strCategory1.equalsIgnoreCase(HubCitiConstants.ONGOINGEVENTS)) {
				iCategory = -1;
			} else if (strCategory2.equalsIgnoreCase(HubCitiConstants.ONGOINGEVENTS)) {
				iCategory = 1;
			} else {
				iCategory = strCategory1.compareTo(strCategory2);
			}
			
		} catch (Exception e) {
			LOG.error("Inside SortAlertEventByCategory  : compare : " + e.getMessage());
		}
		
		return iCategory;
	}
}
