package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.RetailerDetail;
import com.hubciti.common.utility.Utility;

/**
 * Class for grouping and sorting in find
 * 
 * @author Kumar D
 */
public class SortFindByCity implements Comparator<RetailerDetail> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortFindByCity.class.getName());

	public int compare(RetailerDetail objRet1, RetailerDetail objRet2) {
		
		String strCity1 = null;
		String strCity2 = null;
		int iCityName = 0;
		
		try {
			strCity1 = objRet1.getCity();
			strCity2 = objRet2.getCity();

			if (!"".equals(Utility.checkNull(strCity1))) {
				strCity1 = strCity1.toUpperCase();
			}

			if (!"".equals(Utility.checkNull(strCity2))) {
				strCity2 = strCity2.toUpperCase();
			}
			
			iCityName = strCity1.compareTo(strCity2);
			
		} catch (Exception e) {
			LOG.error("ParseException in SortFindByCity : Exception : " + e.getMessage());
		}
		return iCityName;
	}

}
