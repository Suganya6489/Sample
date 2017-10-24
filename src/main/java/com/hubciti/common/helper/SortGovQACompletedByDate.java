package com.hubciti.common.helper;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.Table;
import com.hubciti.common.utility.Utility;

/**
 * Class to Sort GovQA Completed By Date.
 * @author Kumar D
 *
 */
public class SortGovQACompletedByDate implements Comparator<Table> {

	/**
	 * getting logger instance
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortGovQACompletedByDate.class.getName());

	
	public int compare(Table o1, Table o2) {
		
		final SimpleDateFormat FORMATTER = new SimpleDateFormat("MM/dd/yyyy");
		String strClosedDate1 = null;
		String strClosedDate2 = null;
		
		Date date1;
		Date date2;
		
		int iClosedDate = 0;

		try {

			strClosedDate1 = o1.getCloseDate();
			strClosedDate2 = o2.getCloseDate();

			if ("".equals(Utility.checkNull(strClosedDate1))) {
				iClosedDate = 1;
			} else if ("".equals(Utility.checkNull(strClosedDate2))) {
				iClosedDate = -1;
			}  else {
				date1 = FORMATTER.parse(o1.getCloseDate());
				date2 = FORMATTER.parse(o2.getCloseDate());
				
				iClosedDate = date2.compareTo(date1);
			}
		} catch (Exception e) {
			LOG.error("ParseException in SortGovQACompletedByDate : Exception : " + e.getMessage());
		}
		return iClosedDate;
	}

}
