package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.Filter;;

/**
 * Class to Sort Filter By Header.
 * 
 * @author Kumar D
 *
 */
public class SortFilterByHeader implements Comparator<Filter> {

	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortFilterByHeader.class.getName());

	public int compare(Filter o1, Filter o2) {
		
		String filterHeader1 = null;
		String filterHeader2 = null;
		
		int iFilterHeader = 0;
		try {
			filterHeader1 = o1.getfHeader();
			filterHeader2 = o2.getfHeader();
			/* Checking for "Sort" value. if Sort Header is find place at top. */
			if (filterHeader1.toLowerCase().startsWith("sor".toLowerCase())) {
				iFilterHeader = -1;
			} else if (filterHeader2.toLowerCase().startsWith("sor".toLowerCase())) {
					iFilterHeader = 1;
			} else {
					iFilterHeader = filterHeader1.compareTo(filterHeader2);
			}
		} catch (Exception e) {
			log.info("ParseException in SortFilterByHeader : Exception : " + e.getMessage());
		}
		return iFilterHeader;
	}

}
