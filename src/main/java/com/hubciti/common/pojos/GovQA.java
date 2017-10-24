package com.hubciti.common.pojos;

/*
 * Class for handling GovQA Requests
 * 
 */
public class GovQA {

	/**
	 * Variable for authKey
	 */
	private String authKey;
	
	/**
	 * Variable for typeNo
	 */
	private Integer typeNo;

	/**
	 * 
	 * @return the authKey
	 */
	public String getAuthKey() {
		return authKey;
	}
	/**
	 * 
	 * @param authKey to set
	 */
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	/**
	 * 
	 * @return the typeNo
	 * 
	 */
	public Integer getTypeNo() {
		return typeNo;
	}
	
	/**
	 * 
	 * @param typeNo to set
	 * 
	 */
	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}

}
