package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.TableGroup;

/**
* This class demonstrates SortTableGroup.
* @author Kumar D
* @version 1.0  
*/
public class SortTableGroup implements Comparator<TableGroup> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortTableGroup.class.getName());

	public int compare(TableGroup o1, TableGroup o2) {
		String strAlphabet1 = null;
		String strAlphabet2 = null;

		int iAtoz = 0;

		try {

			strAlphabet1 = o1.getGroupContent();
			strAlphabet2 = o2.getGroupContent();

			if (strAlphabet1.equalsIgnoreCase(strAlphabet2)) {
				iAtoz = -1;
			} else if (strAlphabet2.equalsIgnoreCase(strAlphabet1)) {
				iAtoz = 1;
			}  else {
				iAtoz = strAlphabet1.compareTo(strAlphabet2);
			}

		} catch (Exception e) {
			LOG.error("Inside SortTableGroup : Exception : " + e.getMessage());
		}

		return iAtoz;
	}
}
