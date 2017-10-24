package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.WebQAServiceReqType;

public class SortGovQARequestByName implements Comparator<WebQAServiceReqType> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortGovQARequestByName.class.getName());

	public int compare(WebQAServiceReqType w1, WebQAServiceReqType w2) {
		
		String strReqName1 = null;
		String strReqName2 = null;
		
		int iName = 0;
		try {
			strReqName1 = w1.getName();
			strReqName2 = w2.getName();

			if (strReqName1.equalsIgnoreCase(strReqName2)) {
				iName = -1;
			} else if (strReqName2.equalsIgnoreCase(strReqName1)) {
				iName = 1;
			}  else {
				iName = strReqName1.compareTo(strReqName2);
			}
		} catch (Exception e) {
			LOG.error("Inside  SortGovQARequestByName : Exception " + e.getMessage());
		}
		return iName;
	}
}
