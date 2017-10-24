/**
* @author Kumar D
* @version 0.1
*
* This class demonstrates SortFundCategoryByDate.
*/

package com.hubciti.common.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.CategoryInfo;

public class SortFundCategoryByDate implements Comparator<CategoryInfo> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortFundCategoryByDate.class.getName());

	public int compare(CategoryInfo o1, CategoryInfo o2) {
		
		Date date1 = null;
		Date date2 = null;
		Integer dateCompare = 0;
		
		final SimpleDateFormat FORMATTER = new SimpleDateFormat("MM-dd-yyyy");

		try {
			
			date1 = FORMATTER.parse(o1.getGroupContent());
			date2 = FORMATTER.parse(o2.getGroupContent());
			
			dateCompare = date1.compareTo(date2);

		} catch (ParseException e) {
			LOG.error("ParseException in SortFundCategoryByDate : Exception : " + e.getMessage());
		}
		return dateCompare;
	}
}
