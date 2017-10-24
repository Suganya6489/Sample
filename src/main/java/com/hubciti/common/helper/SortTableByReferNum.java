package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.Table;

/**
* This class demonstrates SortTableByReferNum.
* @author Kumar D
* @version 1.0  
*/
public class SortTableByReferNum implements Comparator<Table> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortTableByReferNum.class.getName());

	public int compare(Table o1, Table o2) {
		String strAlphabet1 = null;
		String strAlphabet2 = null;

		int iRefNum = 0;

		try {

			strAlphabet1 = o1.getReferNum();
			strAlphabet2 = o2.getReferNum();

			if (strAlphabet2.equalsIgnoreCase(strAlphabet1)) {
				iRefNum = -1;
			} else if (strAlphabet1.equalsIgnoreCase(strAlphabet2)) {
				iRefNum = 1;
			}  else {
				iRefNum = strAlphabet2.compareTo(strAlphabet1);
			}

		} catch (Exception e) {
			LOG.error("Inside SortTableByReferNum : Exception : " + e.getMessage());
		}

		return iRefNum;
	}
}
