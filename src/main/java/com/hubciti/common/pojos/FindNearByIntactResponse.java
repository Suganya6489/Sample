package com.hubciti.common.pojos;

import java.util.ArrayList;

/**
 * pojo for FindNearByIntactResponse class.
 * 
 * @author pradip_k
 */
public class FindNearByIntactResponse {

	/**
	 * variable declare as findNearByMetaData.
	 */
	private FindNearByPartialResponse findNearByMetaData;

	/**
	 * variable declare as findNearByDetails.
	 */
	private ArrayList<FindNearByResponse> findNearByDetails;
	/**
	 * for findNearby.
	 */
	private boolean findNearBy;

	/**
	 * for getting findNearBy.
	 * 
	 * @return the findNearBy
	 */
	public boolean isFindNearBy() {
		return findNearBy;
	}

	/**
	 * for setting findNearBy.
	 * 
	 * @param findNearBy
	 *            the findNearBy to set
	 */
	public void setFindNearBy(boolean findNearBy) {
		this.findNearBy = findNearBy;
	}

	/**
	 * for getting findNearByMetaData.
	 * 
	 * @return the findNearByMetaData
	 */
	public FindNearByPartialResponse getFindNearByMetaData() {
		return findNearByMetaData;
	}

	/**
	 * for setting findNearByMetaData.
	 * 
	 * @param findNearByMetaData
	 *            the findNearByMetaData to set
	 */
	public void setFindNearByMetaData(FindNearByPartialResponse findNearByMetaData) {
		this.findNearByMetaData = findNearByMetaData;
	}

	/**
	 * for getting findNearByDetails.
	 * 
	 * @return the findNearByDetails
	 */
	public ArrayList<FindNearByResponse> getFindNearByDetails() {
		return findNearByDetails;
	}

	/**
	 * for setting findNearByDetails.
	 * 
	 * @param findNearByDetails
	 *            the findNearByDetails to set
	 */
	public void setFindNearByDetails(ArrayList<FindNearByResponse> findNearByDetails) {
		this.findNearByDetails = findNearByDetails;
	}

}
