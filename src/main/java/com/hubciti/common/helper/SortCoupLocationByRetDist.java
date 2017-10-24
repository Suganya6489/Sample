package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.RetailersDetails;

/**
 * To sort coupons by distance
 * 
 * @author Kumar D
 */
public class SortCoupLocationByRetDist implements Comparator<RetailersDetails> {
	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortCoupLocationByRetDist.class.getName());

	public int compare(RetailersDetails o1, RetailersDetails o2) {
		float dist1;
		float dist2;
		float value;
		int iCategory = 0;
		try {
			dist1 = o1.getMinRetDist();
			dist2 = o2.getMinRetDist();
			value = dist1 - dist2;
			if (value > 0) {
				iCategory = 1;
			} else
				if (value < 0) {
					iCategory = -1;
				}
		} catch (Exception e) {
			log.info("ParseException in SortCoupLocationByRetDist : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}
