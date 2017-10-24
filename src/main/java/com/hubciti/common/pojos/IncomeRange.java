package com.hubciti.common.pojos;

/**
 * The class has getter and setter methods for IncomeRange.
 * 
 * @author Kumar D
 */
public class IncomeRange {
	/**
	 * Variable incomeRangeId declared as of type Integer.
	 */
	private Integer icRangeId;
	/**
	 * Variable incomeRange declared as of type String.
	 */
	private String icRangeName;

	/**
	 * @return the icRangeId
	 */
	public Integer getIcRangeId() {
		return icRangeId;
	}

	/**
	 * @param icRangeId
	 *            the icRangeId to set
	 */
	public void setIcRangeId(Integer icRangeId) {
		this.icRangeId = icRangeId;
	}

	/**
	 * @return the icRangeName
	 */
	public String getIcRangeName() {
		return icRangeName;
	}

	/**
	 * @param icRangeName
	 *            the icRangeName to set
	 */
	public void setIcRangeName(String icRangeName) {
		this.icRangeName = icRangeName;
	}
}
