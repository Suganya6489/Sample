package com.hubciti.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.pojos.CategoryInfo;

public class SortAlertEventByDate implements Comparator<CategoryInfo> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortAlertEventByDate.class.getName());

	public int compare(CategoryInfo o1, CategoryInfo o2) {
		// LOG.info("inside SortAlertEventByDate class");
		Date date1 = null;
		Date date2 = null;
		Integer dateCompare = 0;
		Boolean compareDateRequired = true;
		final SimpleDateFormat FORMATTER = new SimpleDateFormat("MM-dd-yyyy");

		try {


			if (o1.getGroupContent() != null && !o1.getGroupContent().equalsIgnoreCase(HubCitiConstants.SIG_EVENTS)) {
				date1 = FORMATTER.parse(o1.getGroupContent());
			} else {
				dateCompare = -1;
				compareDateRequired = false;
			}

			if (o2.getGroupContent() != null  && !o2.getGroupContent().equalsIgnoreCase(HubCitiConstants.SIG_EVENTS)) {
				date2 = FORMATTER.parse(o2.getGroupContent());
			} else {
				dateCompare = 1;
				compareDateRequired = false;
			}
			
			if (o1.getGroupContent() != null && !o1.getGroupContent().equalsIgnoreCase(HubCitiConstants.ONGOINGEVENTS)  && !o1.getGroupContent().equalsIgnoreCase(HubCitiConstants.SIG_EVENTS)) {
				date1 = FORMATTER.parse(o1.getGroupContent());
			} else {
				dateCompare = -1;
				compareDateRequired = false;
			}

			if (o2.getGroupContent() != null && !o2.getGroupContent().equalsIgnoreCase(HubCitiConstants.ONGOINGEVENTS) && !o2.getGroupContent().equalsIgnoreCase(HubCitiConstants.SIG_EVENTS)) {
				date2 = FORMATTER.parse(o2.getGroupContent());
			} else {
				dateCompare = 1;
				compareDateRequired = false;
			}

			if (compareDateRequired) {
				dateCompare = date1.compareTo(date2);
			}
		} catch (ParseException e) {
			LOG.error("ParseException in SortAlertEventByDate : Exception : " + e.getMessage());
		}
		return dateCompare;
	}

}
