package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.EventDetails;

public class SortReverseEventByCity implements Comparator<EventDetails> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortReverseEventByCity.class.getName());

	public int compare(EventDetails o1, EventDetails o2) {
		
		String strCity1 = null;
		String strCity2 = null;
		
		int iCityName = 0;
		try {
			strCity1 = o1.getCityName();
			strCity2 = o2.getCityName();

			if (strCity1 != null && !"".equals(strCity1)) {
				strCity1 = strCity1.toUpperCase();
			}

			if (strCity2 != null && !"".equals(strCity2)) {
				strCity2 = strCity2.toUpperCase();
			}

			/* All Ongoing will be listed on top labeled as Ongoing. */
			if (o1.getIsOnGoing() == 1 && o2.getIsOnGoing() == 0) {
				iCityName = -1;
			} else
				if (o1.getIsOnGoing() == 0 && o2.getIsOnGoing() == 1) {
					iCityName = 1;
				} else {
					iCityName = strCity2.compareTo(strCity1);
				}
		} catch (Exception e) {
			LOG.error("ParseException in SortReverseEventByCity : Exception : " + e.getMessage());
		}
		return iCityName;
	}
}
