package com.hubciti.common.pojos;

/**
 * The class has getter and setter methods for IncomeRange.
 * 
 * @author Kumar D
 */
public class EducationLevel {
	/**
	 * Variable incomeRangeId declared as of type Integer.
	 */
	protected Integer educatLevelId;
	/**
	 * Variable incomeRange declared as of type String.
	 */
	protected String educatLevelName;

	/**
	 * @return the educatLevelId
	 */
	public Integer getEducatLevelId() {
		return educatLevelId;
	}

	/**
	 * @param educatLevelId
	 *            the educatLevelId to set
	 */
	public void setEducatLevelId(Integer educatLevelId) {
		this.educatLevelId = educatLevelId;
	}

	/**
	 * @return the educatLevelName
	 */
	public String getEducatLevelName() {
		return educatLevelName;
	}

	/**
	 * @param educatLevelName
	 *            the educatLevelName to set
	 */
	public void setEducatLevelName(String educatLevelName) {
		this.educatLevelName = educatLevelName;
	}
}
