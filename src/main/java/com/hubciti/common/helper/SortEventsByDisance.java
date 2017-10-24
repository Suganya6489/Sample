package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.pojos.EventDetails;

public class SortEventsByDisance implements Comparator<EventDetails> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortEventsByDisance.class.getName());

	public int compare(EventDetails o1, EventDetails o2) {
		String strDistance1 = null;
		String strDistance2 = null;
		float value;
		int iCategory = 0;
		try {
			strDistance1 = o1.getDistance();
			strDistance2 = o2.getDistance();

			if (strDistance1.equalsIgnoreCase(HubCitiConstants.NOTAPPLICABLE)) {
				iCategory = 1;
			} else
				if (strDistance2.equalsIgnoreCase(HubCitiConstants.NOTAPPLICABLE)) {
					iCategory = -1;
				} else {
					// iCategory = strDistance1.compareTo(strDistance2);
					strDistance1 = strDistance1.replaceAll(" mi", "");
					strDistance2 = strDistance2.replaceAll(" mi", "");
					float dist1 = Float.parseFloat(strDistance1);
					float dist2 = Float.parseFloat(strDistance2);
					value = dist2 - dist1;
					/*
					 * if (value > 0) { iCategory = 1; } else if (value < 0) {
					 * iCategory = -1; } else { iCategory = 0; }
					 */

					/* All Ongoing will be listed on top labeled as Ongoing. */
					if (o1.getIsOnGoing() == 1 && o2.getIsOnGoing() == 0) {
						iCategory = -1;
					} else
						if (o1.getIsOnGoing() == 0 && o2.getIsOnGoing() == 1) {
							iCategory = 1;
						} else {
							iCategory = 0;
						}
				}
		} catch (Exception e) {
			LOG.error("ParseException in SortEventsByDisance : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}
