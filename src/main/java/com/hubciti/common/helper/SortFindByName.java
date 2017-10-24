package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.RetailerDetail;

/**
 * Class for grouping and sorting in find
 * 
 * @author Kumar D
 */
public class SortFindByName implements Comparator<RetailerDetail> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortFindByName.class.getName());

	public int compare(RetailerDetail objRet1, RetailerDetail objRet2) {
		String strRetailer1 = null;
		String strRetailer2 = null;
		int iRetailName = 0;
		try {
			strRetailer1 = objRet1.getRetailerName();
			strRetailer2 = objRet2.getRetailerName();

			if (strRetailer1 != null && !"".equals(strRetailer1)) {
				strRetailer1 = strRetailer1.toUpperCase();
			}

			if (strRetailer2 != null && !"".equals(strRetailer2)) {
				strRetailer2 = strRetailer2.toUpperCase();
			}
			iRetailName = strRetailer1.compareTo(strRetailer2);
		} catch (Exception e) {
			LOG.error("ParseException in SortFindByName : Exception : " + e.getMessage());
		}
		return iRetailName;
	}

}
