package com.hubciti.common.pojos;

public class RetailerTrack {

	private String retailerId;
	private String retailLocationId;
	private String catName;
	private Integer mainMenuId;
	
	public Integer getMainMenuId() {
		return mainMenuId;
	}
	public void setMainMenuId(Integer mainMenuId) {
		this.mainMenuId = mainMenuId;
	}
	public String getRetailerId() {
		return retailerId;
	}
	public void setRetailerId(String retailerId) {
		this.retailerId = retailerId;
	}
	public String getRetailLocationId() {
		return retailLocationId;
	}
	public void setRetailLocationId(String retailLocationId) {
		this.retailLocationId = retailLocationId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
}
