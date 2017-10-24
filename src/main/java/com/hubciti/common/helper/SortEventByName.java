package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.EventDetails;

public class SortEventByName implements Comparator<EventDetails> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortEventByName.class.getName());

	public int compare(EventDetails o1, EventDetails o2) {
		
		String strEventName1 = null;
		String strEventName2 = null;
		
		int iCategory = 0;
		try {
			strEventName1 = o1.getEventName();
			strEventName2 = o2.getEventName();

			if (strEventName1 != null && !"".equals(strEventName1)) {
				strEventName1 = strEventName1.toUpperCase();
			}

			if (strEventName2 != null && !"".equals(strEventName2)) {
				strEventName2 = strEventName2.toUpperCase();
			}

			/* All Ongoing will be listed on top labeled as Ongoing. */
			if (o1.getIsOnGoing() == 1 && o2.getIsOnGoing() == 0) {
				iCategory = -1;
			} else if (o1.getIsOnGoing() == 0 && o2.getIsOnGoing() == 1) {
				iCategory = 1;
			} else {
				iCategory = strEventName1.compareTo(strEventName2);
			}
		} catch (Exception e) {
			LOG.error("ParseException in SortEventByName : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}
