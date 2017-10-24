package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.RetailersDetails;

/**
 * This is for sorting Coupons by retailers
 * 
 * @author dhruvanath_mm
 */
public class SortCouponLocByRetailer implements Comparator<RetailersDetails> {

	/**
	 * Logger instance.
	 */
	private static final Logger log = LoggerFactory.getLogger(SortCouponLocByRetailer.class.getName());

	public int compare(RetailersDetails o1, RetailersDetails o2) {
		String strRet1 = null;
		String strRet2 = null;
		int iCategory = 0;
		try {
			strRet1 = o1.getRetName();
			strRet2 = o2.getRetName();
			iCategory = strRet1.compareTo(strRet2);
		} catch (Exception e) {
			log.info("ParseException in SortCouponLocByRetailer : Exception : " + e.getMessage());
		}
		return iCategory;
	}
}
