package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.HotDealAPIResultSet;

public class SortHotDealByAPIName implements Comparator<HotDealAPIResultSet> {

	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortHotDealByCategory.class.getName());

	public int compare(HotDealAPIResultSet o1, HotDealAPIResultSet o2) {
		
		String strAPIName1 = null;
		String strAPIName2 = null;
		
		int iCategory = 0;
		try {
			strAPIName1 = o1.getApiPartnerName();
			strAPIName2 = o2.getApiPartnerName();
			// To push ScanSee name to 1st
			if (strAPIName1.equalsIgnoreCase("ScanSee")) {
				iCategory = -1;
			} else if (strAPIName2.equalsIgnoreCase("ScanSee")) {
					iCategory = 1;
			} else {
					iCategory = strAPIName1.compareTo(strAPIName2);
			}
		} catch (Exception e) {
			log.info("ParseException in SortHotDealByAPIName : Exception : " + e.getMessage());
		}
		return iCategory;
	}

}
