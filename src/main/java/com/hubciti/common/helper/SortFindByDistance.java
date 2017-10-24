package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.pojos.RetailerDetail;

/**
 * Class for grouping and sorting in find
 * 
 * @author Kumar D
 */
public class SortFindByDistance implements Comparator<RetailerDetail> {
	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortFindByDistance.class.getName());

	public int compare(RetailerDetail objRet1, RetailerDetail objRet2) {
		String strDistance1 = null;
		String strDistance2 = null;
		int iDistance = 0;
		try {
			strDistance1 = objRet1.getDistance();
			strDistance2 = objRet2.getDistance();
			
			if (strDistance1.equalsIgnoreCase(HubCitiConstants.NOTAPPLICABLE)) {
				iDistance = 1;
			} else if (strDistance2.equalsIgnoreCase(HubCitiConstants.NOTAPPLICABLE)) {
				iDistance = -1;
			} else {
					strDistance1 = strDistance1.replaceAll("mi", "");
					strDistance2 = strDistance2.replaceAll("mi", "");
					
					final Float dist1 = Float.parseFloat(strDistance1.trim());
					final Float dist2 = Float.parseFloat(strDistance2.trim());
					
					Float val = 0.0f;
//					iDistance = strDistance1.compareTo(strDistance2);
					
					val = dist1 - dist2;
					if(val < 0)	{
						iDistance = -1;
					} else if(val > 0)	{
						iDistance = 1;
					} else	{
						iDistance = 0;
					}
				}
		} catch (Exception e) {
			LOG.error("ParseException in SortFindByDistance : Exception : " + e.getMessage());
		}
		return iDistance;
	}
	
}
