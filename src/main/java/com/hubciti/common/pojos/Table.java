package com.hubciti.common.pojos;

import java.util.List;

import net.webqa.WebQAServiceRequestType;

import com.hubciti.common.utility.Utility;
import net.webqa.WebQACategory;

/**
 * The POJO class for Table.
 * 
 * @author Kumar D
 */
public class Table {
	private String responseCode;
	private String responseText;
	private Integer id;
	private String  description;
	private String  table_id;
	private String  notes;
	private Integer rowOrder;
	private String groupBy;
	private String sortBy;
	private List<Table> Table;
	private List<TableGroup> tableGroup;

	private List<WebQAServiceRequestType> arWebQAServiceReqTypeList;

	private List<WebQAServiceReqType> arServiceReqTypeList;

	private List<WebQACategory> arWebQACategory;

	private String email;
	private String createDate;
	private String referNum;
	private String status;
	private String statusId;
	private String statusDesc;
	private String deptName;
	private String fldName;
	private String fldValue;
	private String issueId;

	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipCode;

	private String closeDate;


	/**
	 * @return the description
	 */
	 public String getDescription() {
		return description;
	}
	/**
	 * @return the table_id
	 */
	 public String getTable_id() {
		 return table_id;
	 }
	 /**
	  * @return the notes
	  */
	 public String getNotes() {
		 return notes;
	 }

	 /**
	  * @param description the description to set
	  */
	 public void setDescription(String description) {
		 this.description = description;
	 }
	 /**
	  * @param table_id the table_id to set
	  */
	 public void setTable_id(String table_id) {
		 this.table_id = table_id;
	 }
	 /**
	  * @param notes the notes to set
	  */
	 public void setNotes(String notes) {
		 this.notes = notes;
	 }
	 /**
	  * @param rowOrder the rowOrder to set
	  */
	 public void setRowOrder(int rowOrder) {
		 this.rowOrder = rowOrder;
	 }

	 /**
	  * @return the tableGroup
	  */
	 public List<TableGroup> getTableGroup() {
		 return tableGroup;
	 }
	 /**
	  * @return the groupBy
	  */
	 public String getGroupBy() {
		 return groupBy;
	 }
	 /**
	  * @return the sortBy
	  */
	 public String getSortBy() {
		 return sortBy;
	 }
	 /**
	  * @param groupBy the groupBy to set
	  */
	 public void setGroupBy(String groupBy) {
		 this.groupBy = groupBy;
	 }
	 /**
	  * @param sortBy the sortBy to set
	  */
	 public void setSortBy(String sortBy) {
		 this.sortBy = sortBy;
	 }
	 /**
	  * @param tableGroup the tableGroup to set
	  */
	 public void setTableGroup(List<TableGroup> tableGroup) {
		 this.tableGroup = tableGroup;
	 }
	 /**
	  * @return the table
	  */
	 public List<Table> getTable() {
		 return Table;
	 }
	 /**
	  * @param table the table to set
	  */
	 public void setTable(List<Table> table) {
		 this.Table = table;
	 }
	 /**
	  * @return the arWebQAServiceReqTypeList
	  */
	 public List<WebQAServiceRequestType> getArWebQAServiceReqTypeList() {
		 return arWebQAServiceReqTypeList;
	 }
	 /**
	  * @param arWebQAServiceReqTypeList the arWebQAServiceReqTypeList to set
	  */
	 public void setArWebQAServiceReqTypeList(List<WebQAServiceRequestType> arWebQAServiceReqTypeList) {
		 this.arWebQAServiceReqTypeList = arWebQAServiceReqTypeList;
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
	 /**
	  * @return the email
	  */
	 public String getEmail() {
		 return email;
	 }
	 /**
	  * @return the createDate
	  */
	 public String getCreateDate() {
		 return createDate;
	 }
	 /**
	  * @return the referNum
	  */
	 public String getReferNum() {
		 return referNum;
	 }
	 /**
	  * @param email the email to set
	  */
	 public void setEmail(String email) {
		 this.email = email;
	 }
	 /**
	  * @param createDate the createDate to set
	  */
	 public void setCreateDate(String createDate) {

		 if (!"".equals(Utility.checkNull(createDate)) && createDate.contains("T")){
			 this.createDate = Utility.govQADateFormat(createDate);
		 } else {
			 this.createDate = createDate;
		 } 
	 }
	 /**
	  * @param referNum the referNum to set
	  */
	 public void setReferNum(String referNum) {
		 this.referNum = referNum;
	 }
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
	  * @return the id
	  */
	 public Integer getId() {
		 return id;
	 }
	 /**
	  * @return the rowOrder
	  */
	 public Integer getRowOrder() {
		 return rowOrder;
	 }
	 /**
	  * @param id the id to set
	  */
	 public void setId(Integer id) {
		 this.id = id;
	 }
	 /**
	  * @param rowOrder the rowOrder to set
	  */
	 public void setRowOrder(Integer rowOrder) {
		 this.rowOrder = rowOrder;
	 }
	 /**
	  * @return the status
	  */
	 public String getStatus() {
		 return status;
	 }
	 /**
	  * @param status the status to set
	  */
	 public void setStatus(String status) {
		 this.status = status;
	 }
	 /**
	  * @return the fldName
	  */
	 public String getFldName() {
		 return fldName;
	 }
	 /**
	  * @return the fldValue
	  */
	 public String getFldValue() {
		 return fldValue;
	 }
	 /**
	  * @param fldName the fldName to set
	  */
	 public void setFldName(String fldName) {
		 this.fldName = fldName;
	 }
	 /**
	  * @param fldValue the fldValue to set
	  */
	 public void setFldValue(String fldValue) {
		 this.fldValue = fldValue;
	 }
	 /**
	  * @return the arWebQACategory
	  */
	 public List<WebQACategory> getArWebQACategory() {
		 return arWebQACategory;
	 }
	 /**
	  * @param arWebQACategoryList the arWebQACategory to set
	  */
	 public void setArWebQACategory(List<WebQACategory> arWebQACategoryList) {
		 this.arWebQACategory = arWebQACategoryList;
	 }
	 /**
	  * @return the statusId
	  */
	 public String getStatusId() {
		 return statusId;
	 }
	 /**
	  * @param statusId the statusId to set
	  */
	 public void setStatusId(String statusId) {
		 this.statusId = statusId;
	 }
	 /**
	  * @return the statusDesc
	  */
	 public String getStatusDesc() {
		 return statusDesc;
	 }
	 /**
	  * @param statusDesc the statusDesc to set
	  */
	 public void setStatusDesc(String statusDesc) {
		 this.statusDesc = statusDesc;
	 }
	 /**
	  * @return the issueId
	  */
	 public String getIssueId() {
		 return issueId;
	 }
	 /**
	  * @param issueId the issueId to set
	  */
	 public void setIssueId(String issueId) {
		 this.issueId = issueId;
	 }
	 /**
	  * @return the deptName
	  */
	 public String getDeptName() {
		 return deptName;
	 }
	 /**
	  * @param deptName the deptName to set
	  */
	 public void setDeptName(String deptName) {
		 this.deptName = deptName;
	 }
	 /**
	  * @return the address1
	  */
	 public String getAddress1() {
		 return address1;
	 }
	 /**
	  * @return the address2
	  */
	 public String getAddress2() {
		 return address2;
	 }
	 /**
	  * @return the city
	  */
	 public String getCity() {
		 return city;
	 }
	 /**
	  * @return the state
	  */
	 public String getState() {
		 return state;
	 }
	 /**
	  * @return the zipCode
	  */
	 public String getZipCode() {
		 return zipCode;
	 }
	 /**
	  * @param address1 the address1 to set
	  */
	 public void setAddress1(String address1) {
		 this.address1 = address1;
	 }
	 /**
	  * @param address2 the address2 to set
	  */
	 public void setAddress2(String address2) {
		 this.address2 = address2;
	 }
	 /**
	  * @param city the city to set
	  */
	 public void setCity(String city) {
		 this.city = city;
	 }

	 /**
	  * @param state the state to set
	  */
	 public void setState(String state) {
		 this.state = state;
	 }
	 /**
	  * @param zipCode the zipCode to set
	  */
	 public void setZipCode(String zipCode) {
		 this.zipCode = zipCode;
	 }

	 /**
	  * @param closeDate the closeDate to set
	  */
	 public String getCloseDate() {
		 return closeDate;
	 }

	 /**
	  * @param closeDate the closeDate to set
	  */
	 public void setCloseDate(String closeDate) {
		 if (!"".equals(Utility.checkNull(closeDate)) && closeDate.contains("T")){
			 this.closeDate = Utility.govQADateFormat(closeDate);
		 } else {
			 this.closeDate = closeDate;
		 } 
	 }
}


