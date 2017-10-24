/**
* @author Kumar D
* @version 0.1
*
* This class demonstrates SortFundraiserByName.
*/
package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.Fundraiser;

public class SortFundraiserByName implements Comparator<Fundraiser> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortFundraiserByName.class.getName());

	public int compare(Fundraiser o1, Fundraiser o2) {
		String strCategory1 = null;
		String strCategory2 = null;
		int iCategory = 0;
		try {
			strCategory1 = o1.getFundName();
			strCategory2 = o2.getFundName();

			if (strCategory1 != null && !"".equals(strCategory1)) {
				strCategory1 = strCategory1.toUpperCase();
			}

			if (strCategory2 != null && !"".equals(strCategory2)) {
				strCategory2 = strCategory2.toUpperCase();
			}

			iCategory = strCategory1.compareTo(strCategory2);
			
		} catch (Exception e) {
			LOG.error("ParseException in SortFundraiserByName : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}