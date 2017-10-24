package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.Retailer;

/**
 * Class to sort retailer by name to sort city experience retailers.
 * 
 * @author dhruvanath_mm
 *
 */
public class SortCitiExpReailerByName implements Comparator<Retailer> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortCitiExpReailerByName.class.getName());
	
	public int compare(Retailer o1, Retailer o2) {
		
		String strRetailer1 = null;
		String strRetailer2 = null;
		int iRetailName = 0;
		
		try {
			strRetailer1 = o1.getRetailerName();
			strRetailer2 = o2.getRetailerName();

			if (strRetailer1 != null && !"".equals(strRetailer1)) {
				strRetailer1 = strRetailer1.toUpperCase();
			}

			if (strRetailer2 != null && !"".equals(strRetailer2)) {
				strRetailer2 = strRetailer2.toUpperCase();
			}
			
			iRetailName = strRetailer1.compareTo(strRetailer2);
			
		} catch (Exception e) {
			LOG.error("ParseException in SortReailerByName : Exception : " + e.getMessage());
		}
		return iRetailName;
	}
}
