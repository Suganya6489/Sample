package com.hubciti.common.pojos;

import java.util.List;

import com.hubciti.common.pojos.WebQAServiceReqType;



public class TableGroup {
	private String responseCode;

	private String responseText;
	private String groupContent;
	private Integer groupId;
	
	private List<Table> tableList;
	private List<TableGroup> tableGroup;
    private List<WebQAServiceReqType> arServiceReqTypeList;


	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @return the responseText
	 */
	public String getResponseText() {
		return responseText;
	}

	/**
	 * @return the groupContent
	 */
	public String getGroupContent() {
		return groupContent;
	}

	/**
	 * @return the tableList
	 */
	public List<Table> getTableList() {
		return tableList;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @param responseText the responseText to set
	 */
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	/**
	 * @param groupContent the groupContent to set
	 */
	public void setGroupContent(String groupContent) {
		this.groupContent = groupContent;
	}

	/**
	 * @param tableList the tableList to set
	 */
	public void setTableList(List<Table> tableList) {
		this.tableList = tableList;
	}

	/**
	 * @return the groupId
	 */
	public Integer getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the tableGroup
	 */
	public List<TableGroup> getTableGroup() {
		return tableGroup;
	}

	/**
	 * @param tableGroup the tableGroup to set
	 */
	public void setTableGroup(List<TableGroup> tableGroup) {
		this.tableGroup = tableGroup;
	}

	/**
	 * @return the arServiceReqTypeList
	 */
	public List<WebQAServiceReqType> getArServiceReqTypeList() {
		return arServiceReqTypeList;
	}

	/**
	 * @param arServiceReqTypeList the arServiceReqTypeList to set
	 */
	public void setArServiceReqTypeList(List<WebQAServiceReqType> arServiceReqTypeList) {
		this.arServiceReqTypeList = arServiceReqTypeList;
	}

}
