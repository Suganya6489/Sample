package com.hubciti.common.pojos;

import java.util.ArrayList;
import java.util.List;

/**
 * pojo class for Hot deals API result set.
 * 
 * @author dileepa_cc
 */
public class HotDealAPIResultSet {

	/**
	 * Variable apiPartnerName declared as String.
	 */
	private String apiPartnerName;

	private Integer apiPartnerId;
	/**
	 * Variable hotDealsDetailslst list.
	 */
	private ArrayList<HotDealsDetails> hotDealsDetailslst;

	private ArrayList<HotDealsCategoryInfo> hdCatInfoList;

	private ArrayList<HotDealsListResultSet> hdListResultSet;
	
	private String dealType;

	/**
	 * To get apiPartnerName.
	 * 
	 * @return the apiPartnerName
	 */
	public String getApiPartnerName() {
		return apiPartnerName;
	}

	/**
	 * To set apiPartnerName.
	 * 
	 * @param apiPartnerName
	 *            the apiPartnerName to set
	 */
	public void setApiPartnerName(String apiPartnerName) {
		this.apiPartnerName = apiPartnerName;
	}

	/**
	 * To get hotDealsDetailslst.
	 * 
	 * @return the hotDealsDetailslst
	 */
	public ArrayList<HotDealsDetails> getHotDealsDetailslst() {
		return hotDealsDetailslst;
	}

	/**
	 * To set hotDealsDetailslst.
	 * 
	 * @param hotDealsDetailslst
	 *            the hotDealsDetailslst to set
	 */
	public void setHotDealsDetailslst(ArrayList<HotDealsDetails> hotDealsDetailslst) {
		this.hotDealsDetailslst = hotDealsDetailslst;
	}

	public Integer getApiPartnerId() {
		return apiPartnerId;
	}

	public void setApiPartnerId(Integer apiPartnerId) {
		this.apiPartnerId = apiPartnerId;
	}

	public ArrayList<HotDealsCategoryInfo> getHdCatInfoList() {
		return hdCatInfoList;
	}

	public void setHdCatInfoList(ArrayList<HotDealsCategoryInfo> hdCatInfoList) {
		this.hdCatInfoList = hdCatInfoList;
	}

	public ArrayList<HotDealsListResultSet> getHdListResultSet() {
		return hdListResultSet;
	}

	public void setHdListResultSet(ArrayList<HotDealsListResultSet> hdListResultSet) {
		this.hdListResultSet = hdListResultSet;
	}

	/**
	 * @return the dealType
	 */
	public String getDealType() {
		return dealType;
	}

	/**
	 * @param dealType the dealType to set
	 */
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

}
