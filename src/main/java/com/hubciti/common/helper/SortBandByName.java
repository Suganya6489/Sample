package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.RetailerDetail;

/**
 * Class for grouping and sorting in find
 * 
 * @author Kumar D
 */
public class SortBandByName implements Comparator<RetailerDetail> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortBandByName.class.getName());

	public int compare(RetailerDetail objRet1, RetailerDetail objRet2) {
		String strBandName1 = null;
		String strBandName2 = null;
		int iBandName = 0;
		try {
			strBandName1 = objRet1.getBandName();
			strBandName2 = objRet2.getBandName();

			if (strBandName1 != null && !"".equals(strBandName1)) {
				strBandName1 = strBandName1.toUpperCase();
			}

			if (strBandName2 != null && !"".equals(strBandName2)) {
				strBandName2 = strBandName2.toUpperCase();
			}
			iBandName = strBandName1.compareTo(strBandName2);
		} catch (Exception e) {
			LOG.error("ParseException in SortBandByName : Exception : " + e.getMessage());
		}
		return iBandName;
	}

}
