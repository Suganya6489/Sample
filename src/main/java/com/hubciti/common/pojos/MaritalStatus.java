package com.hubciti.common.pojos;

/**
 * The class has getter and setter methods for IncomeRange.
 * 
 * @author Kumar D
 */
public class MaritalStatus {
	/**
	 * Variable incomeRangeId declared as of type Integer.
	 */
	private Integer mStatusId;
	/**
	 * Variable incomeRange declared as of type String.
	 */
	private String mStatusName;
	/**
	 * Variable incomeRange declared as of type String.
	 */
	private String mStatusDesc;

	/**
	 * @return the mStatusId
	 */
	public Integer getmStatusId() {
		return mStatusId;
	}

	/**
	 * @param mStatusId
	 *            the mStatusId to set
	 */
	public void setmStatusId(Integer mStatusId) {
		this.mStatusId = mStatusId;
	}

	/**
	 * @return the mStatusName
	 */
	public String getmStatusName() {
		return mStatusName;
	}

	/**
	 * @param mStatusName
	 *            the mStatusName to set
	 */
	public void setmStatusName(String mStatusName) {
		this.mStatusName = mStatusName;
	}

	/**
	 * @return the mStatusDesc
	 */
	public String getmStatusDesc() {
		return mStatusDesc;
	}

	/**
	 * @param mStatusDesc
	 *            the mStatusDesc to set
	 */
	public void setmStatusDesc(String mStatusDesc) {
		this.mStatusDesc = mStatusDesc;
	}
}
