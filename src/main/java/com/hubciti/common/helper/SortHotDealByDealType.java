package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.HotDealsResultSet;

/**
 * Class to Sort HotDeal By DealType
 * @author Kumar D
 *
 */
public class SortHotDealByDealType implements Comparator<HotDealsResultSet> {

	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortHotDealByDealType.class.getName());

	public int compare(HotDealsResultSet o1, HotDealsResultSet o2) {
		
		String strDealType1 = null;
		String strDealType2 = null;
		
		int iCategory = 0;
		try {
			strDealType1 = o1.getDealType();
			strDealType2 = o2.getDealType();
			
			if (strDealType1.equalsIgnoreCase("Local")) {
				iCategory = -1;
			} else if (strDealType2.equalsIgnoreCase("Local")) {
					iCategory = 1;
			} else {
					iCategory = strDealType1.compareTo(strDealType2);
			}
		} catch (Exception e) {
			log.info("ParseException in SortHotDealByDealType : Exception : " + e.getMessage());
		}
		return iCategory;
	}

}
