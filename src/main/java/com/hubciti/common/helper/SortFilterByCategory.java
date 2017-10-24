package com.hubciti.common.helper;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hubciti.common.pojos.CategoryInfo;

public class SortFilterByCategory implements Comparator<CategoryInfo> {

	/**
	 * Logger instance.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(SortFilterByCategory.class.getName());

	public int compare(CategoryInfo o1, CategoryInfo o2) {

		int iCategory = 0;
		
		try {
			
			iCategory = o1.getCategoryId().compareTo(o2.getCategoryId());
		} catch (Exception e) {
			LOG.error("ParseException in SortFilterByCategory : Exception : " + e.getMessage());
		}
		
		return iCategory;
	}
}
