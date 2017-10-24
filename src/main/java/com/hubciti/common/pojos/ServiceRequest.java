package com.hubciti.common.pojos;


/**
 * The POJO class for ServiceRequest.
 * 
 * @author Kumar D
 */
public class ServiceRequest {
	/**
	 * Variable email declared as String.
	 */
	private String email;
	/**
	 * Variable password declared as String.
	 */
	private String password;
	/**
	 * Variable note declared as String.
	 */
	private String note;
	/**
	 * Variable note declared as String.
	 */
	private Integer reqTypeId;
    private String createDate;
    
    private String filterName;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @return the reqTypeId
	 */
	public Integer getReqTypeId() {
		return reqTypeId;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @param reqTypeId the reqTypeId to set
	 */
	public void setReqTypeId(Integer reqTypeId) {
		this.reqTypeId = reqTypeId;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
}
