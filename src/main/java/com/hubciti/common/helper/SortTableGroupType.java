package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.pojos.TableGroup;
import com.hubciti.common.utility.PropertiesReader;

/**
* This class demonstrates SortTableGroupType.
* @author Kumar D
* @version 1.0  
*/
public class SortTableGroupType implements Comparator<TableGroup> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortTableGroupType.class.getName());

	final String strCatLabel = PropertiesReader.getPropertyValue(HubCitiConstants.CATEGORY_LABEL);
	public int compare(TableGroup o1, TableGroup o2) {
		String strCategory1 = null;
		String strCategory2 = null;

		int iCategory = 0;

		try {

			strCategory1 = o1.getGroupContent();
			strCategory2 = o2.getGroupContent();

			
			
			if (strCategory1.equalsIgnoreCase(strCatLabel)) {
				iCategory = -1;
			} else if (strCategory2.equalsIgnoreCase(strCatLabel)) {
				iCategory = 1;
			}  else {
				
				if (strCategory1.equalsIgnoreCase(strCategory2)) {
					iCategory = -1;
				} else if (strCategory2.equalsIgnoreCase(strCategory1)) {
					iCategory = 1;
				}  else {
					iCategory = strCategory1.compareTo(strCategory2);
				}
			}

		} catch (Exception e) {
			LOG.error("Inside SortTableGroupType : Exception : " + e.getMessage());
		}

		return iCategory;
	}
}
