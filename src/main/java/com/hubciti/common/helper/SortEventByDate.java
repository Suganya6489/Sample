package com.hubciti.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.EventDetails;

public class SortEventByDate implements Comparator<EventDetails> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortEventByDate.class.getName());

	public int compare(EventDetails o1, EventDetails o2) {
		Date date1;
		Date date2;
		Integer dateCompare = 0;
		final SimpleDateFormat FORMATTER = new SimpleDateFormat("MM-dd-yyyy");

		try {
			date1 = FORMATTER.parse(o1.getStartDate());
			date2 = FORMATTER.parse(o2.getStartDate());

			/* All Ongoing will be listed on top labeled as Ongoing. */
			if (o1.getIsOnGoing() == 1 && o2.getIsOnGoing() == 0) {
				dateCompare = -1;
			} else
				if (o1.getIsOnGoing() == 0 && o2.getIsOnGoing() == 1) {
					dateCompare = 1;
				} else {
					dateCompare = date1.compareTo(date2);
				}
		} catch (ParseException e) {
			LOG.error("ParseException in SortEventByDate : Exception : " + e.getMessage());
		}
		return dateCompare;
	}
}
