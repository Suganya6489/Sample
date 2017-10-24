package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.pojos.Retailer;

/**
 * Class to sort by distance.
 * @author dhruvanath_mm
 *
 */
public class SortCitiExpRetailerByDistance implements Comparator<Retailer> {

	/**
	 * getting logger instance
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortCitiExpRetailerByDistance.class.getName());

	public int compare(Retailer o1, Retailer o2) {
		String strDistance1 = null;
		String strDistance2 = null;
		float value;
		int iCategory = 0;
		try {
			strDistance1 = o1.getDistance();
			strDistance2 = o2.getDistance();

			if (strDistance1.equalsIgnoreCase(HubCitiConstants.NOTAPPLICABLE)) {
				iCategory = 1;
			} else
				if (strDistance2.equalsIgnoreCase(HubCitiConstants.NOTAPPLICABLE)) {
					iCategory = -1;
				} else {
					strDistance1 = strDistance1.replaceAll(" mi", "");
					strDistance2 = strDistance2.replaceAll(" mi", "");
					float dist1 = Float.parseFloat(strDistance1);
					float dist2 = Float.parseFloat(strDistance2);
					value = dist1 - dist2;
					if (value > 0) {
						iCategory = 1;
					} else
						if (value < 0) {
							iCategory = -1;
						} else {
							iCategory = 0;
						}
				}
		} catch (Exception e) {
			LOG.error("ParseException in SortCitiExpRetailerByDistance : Exception : " + e.getMessage());
		}
		return iCategory;
	}

}
