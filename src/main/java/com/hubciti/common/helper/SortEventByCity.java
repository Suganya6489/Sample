package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.utility.Utility;


/**
 * Class to sort retailer by city in Event List.
 * 
 * @author Kumar D
 *
 */
public class SortEventByCity implements Comparator<EventDetails> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortEventByCity.class.getName());

	public int compare(EventDetails o1, EventDetails o2) {
		
		String strCity1 = null;
		String strCity2 = null;
		
		int iCityName = 0;
		
		try {
			strCity1 = o1.getCityName();
			strCity2 = o2.getCityName();

			if (!"".equals(Utility.checkNull(strCity1))) {
				strCity1 = strCity1.toUpperCase();
			}

			if (!"".equals(Utility.checkNull(strCity2))) {
				strCity2 = strCity2.toUpperCase();
			}

			/* All Ongoing will be listed on top labeled as Ongoing. */
			if (o1.getIsOnGoing() == 1 && o2.getIsOnGoing() == 0) {
				iCityName = -1;
			} else if (o1.getIsOnGoing() == 0 && o2.getIsOnGoing() == 1) {
				iCityName = 1;
			} else {
				iCityName = strCity1.compareTo(strCity2);
			}
		} catch (Exception e) {
			LOG.error("ParseException in SortEventByCity : Exception : " + e.getMessage());
		}
		return iCityName;
	}
}
