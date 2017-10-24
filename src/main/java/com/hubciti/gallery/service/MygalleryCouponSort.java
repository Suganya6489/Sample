package com.hubciti.gallery.service;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.RetailerDetail;

/**
 * This class for sorting wish list history based on date.
 * 
 * @author Kumar D
 */
public class MygalleryCouponSort implements Comparator<RetailerDetail> {

	private static final Logger log = LoggerFactory.getLogger(MygalleryCouponSort.class.getName());

	/**
	 * This method is to compare two products.
	 * 
	 * @param wishListResultSet1
	 *            -As parameter
	 * @param wishListResultSet2
	 *            -As parameter
	 * @return HubCiti category Compare.
	 */
	public int compare(RetailerDetail cLRDetailslist1, RetailerDetail cLRDetailslist2) {
		log.info("Inside MygalleryCouponSort : compare");
		String catName1;
		String catName2;
		int scanDateComp = 0;
		try {
			catName1 = cLRDetailslist1.getCateName();
			catName2 = cLRDetailslist2.getCateName();
			scanDateComp = catName1.compareTo(catName2);
		} catch (Exception exception) {
			log.error("Inside MygalleryCouponSort : compare : exception :  " + exception);
		}
		return scanDateComp;
	}
}
