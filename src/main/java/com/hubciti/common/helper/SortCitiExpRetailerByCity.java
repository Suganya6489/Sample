package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.Retailer;
import com.hubciti.common.utility.Utility;

/**
 * Class to sort retailer by city.
 * 
 * @author Kumar D
 *
 */
public class SortCitiExpRetailerByCity implements Comparator<Retailer> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortCitiExpRetailerByCity.class.getName());
	
	public int compare(Retailer o1, Retailer o2) {
		
		String strCity1 = null;
		String strCity2 = null;
		int iCityName = 0;
		
		try {
			strCity1 = o1.getCity();
			strCity2 = o2.getCity();

			if (!"".equals(Utility.checkNull(strCity1))) {
				strCity1 = strCity1.toUpperCase();
			}

			if (!"".equals(Utility.checkNull(strCity2))) {
				strCity2 = strCity2.toUpperCase();
			}
			
			iCityName = strCity1.compareTo(strCity2);
			
		} catch (Exception e) {
			LOG.error("ParseException in SortCitiExpRetailerByCity : Exception : " + e.getMessage());
		}
		return iCityName;
	}
}
