package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.EventDetails;
import com.hubciti.common.utility.Utility;

public class SortEventByCategory implements Comparator<EventDetails> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortEventByCategory.class.getName());

	public int compare(EventDetails o1, EventDetails o2) {
		
		String strEventCategory1 = null;
		String strEventCategory2 = null;
		
		int iCategory = 0;
		try {
			strEventCategory1 = o1.getEventCatName();
			strEventCategory2 = o2.getEventCatName();

			if (!"".equals(Utility.checkNull(strEventCategory1))) {
				strEventCategory1 = strEventCategory1.toUpperCase();
			}

			if (!"".equals(Utility.checkNull(strEventCategory2))) {
				strEventCategory2 = strEventCategory2.toUpperCase();
			}

			/* All Ongoing will be listed on top labeled as Ongoing. */
			if (o1.getIsOnGoing() == 1 && o2.getIsOnGoing() == 0) {
				iCategory = -1;
			} else if (o1.getIsOnGoing() == 0 && o2.getIsOnGoing() == 1) {
				iCategory = 1;
			} else {
				iCategory = strEventCategory1.compareTo(strEventCategory2);
			}
		} catch (Exception e) {
			LOG.error("ParseException in SortEventByCategory : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}
