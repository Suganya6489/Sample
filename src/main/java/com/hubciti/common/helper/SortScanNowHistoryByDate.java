package com.hubciti.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.WishListResultSet;

/**
 * This class for sorting scan now history based on date.
 * 
 * @author team
 */
public class SortScanNowHistoryByDate implements Comparator<WishListResultSet> {
	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortScanNowHistoryByDate.class.getName());

	/**
	 * This method is to compare two products.
	 * 
	 * @param wishListResultSet1
	 *            -As parameter
	 * @param wishListResultSet2
	 *            -As parameter
	 * @return scanDateComp
	 */
	public int compare(WishListResultSet wishListResultSet1, WishListResultSet wishListResultSet2) {
		Date date1;
		Date date2;
		int scanDateComp = 0;
		final SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

		try {
			date1 = formatter.parse(wishListResultSet1.getDateScanned());
			date2 = formatter.parse(wishListResultSet2.getDateScanned());
			scanDateComp = date1.compareTo(date2);
		} catch (ParseException e) {
			log.error("ParseException in SortScanNowHistoryByDate : Exception : " + e.getMessage());
		}
		return scanDateComp;
	}
}
