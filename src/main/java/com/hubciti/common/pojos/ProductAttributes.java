package com.hubciti.common.pojos;

import com.hubciti.common.constants.HubCitiConstants;
import com.hubciti.common.utility.Utility;

/**
 * The ProductAttributes contains setter and getter methods for product
 * attributes fields.
 * 
 * @author dileepa_cc
 */
public class ProductAttributes {

	/**
	 * for product attributeName.
	 */

	private String attributeName;

	/**
	 * Variable displayValue declared as String.
	 */
	private String displayValue;

	/**
	 * To get attributeName value.
	 * 
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * To set attributeName value.
	 * 
	 * @param attributeName
	 *            the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * To get displayValue value.
	 * 
	 * @return the displayValue
	 */
	public String getDisplayValue() {
		return displayValue;
	}

	/**
	 * To set displayValue.
	 * 
	 * @param displayValue
	 *            the displayValue to set
	 */
	public void setDisplayValue(String displayValue) {
		if (null == displayValue || displayValue.equals("")) {
			this.displayValue = HubCitiConstants.NOTAPPLICABLE;
		} else {
			this.displayValue = Utility.removeHTMLTags(displayValue);
		}
	}
}
