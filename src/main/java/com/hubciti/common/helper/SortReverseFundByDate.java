package com.hubciti.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.Fundraiser;
/**
* This class demonstrates SortReverseFundByDate.
* @author Kumar D
* @version 1.0  
*/
public class SortReverseFundByDate implements Comparator<Fundraiser> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortEventByDate.class.getName());

	public int compare(Fundraiser o1, Fundraiser o2) {
		
		Date date1;
		Date date2;
		Integer dateCompare = 0;
		
		final SimpleDateFormat FORMATTER = new SimpleDateFormat("MM-dd-yyyy");

		try {
			date1 = FORMATTER.parse(o1.getStartDate());
			date2 = FORMATTER.parse(o2.getStartDate());

			dateCompare = date2.compareTo(date1);
			
		} catch (ParseException e) {
			LOG.error("ParseException in SortReverseFundByDate : Exception : " + e.getMessage());
		}
		return dateCompare;
	}
}