package com.hubciti.common.pojos;

import java.util.List;

public class GoogleCategory {

	private String CatName;
	private String CatDisName;
	private String CatImgPth;
	private Integer catID;
	
	private String bandName;
	private String bandImgPth;
	private Integer bandID;
	
	private Integer evtTypeID;
	private String evtTypeName;
	
	private List<BottomButton> arBottomBtnList;

	/**
	 * @return the catName
	 */
	public String getCatName() {
		return CatName;
	}

	/**
	 * @param catName
	 *            the catName to set
	 */
	public void setCatName(String catName) {
		CatName = catName;
	}

	/**
	 * @return the catDisName
	 */
	public String getCatDisName() {
		return CatDisName;
	}

	/**
	 * @param catDisName
	 *            the catDisName to set
	 */
	public void setCatDisName(String catDisName) {
		CatDisName = catDisName;
	}

	/**
	 * @return the catImgPth
	 */
	public String getCatImgPth() {
		return CatImgPth;
	}

	/**
	 * @param catImgPth
	 *            the catImgPth to set
	 */
	public void setCatImgPth(String catImgPth) {
		CatImgPth = catImgPth;
	}

	public Integer getCatID() {
		return catID;
	}

	public void setCatID(Integer catID) {
		this.catID = catID;
	}

	public List<BottomButton> getArBottomBtnList() {
		return arBottomBtnList;
	}

	public void setArBottomBtnList(List<BottomButton> arBottomBtnList) {
		this.arBottomBtnList = arBottomBtnList;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getBandImgPth() {
		return bandImgPth;
	}

	public void setBandImgPth(String bandImgPth) {
		this.bandImgPth = bandImgPth;
	}

	public Integer getBandID() {
		return bandID;
	}

	public void setBandID(Integer bandID) {
		this.bandID = bandID;
	}

	public Integer getEvtTypeID() {
		return evtTypeID;
	}

	public void setEvtTypeID(Integer evtTypeID) {
		this.evtTypeID = evtTypeID;
	}

	public String getEvtTypeName() {
		return evtTypeName;
	}

	public void setEvtTypeName(String evtTypeName) {
		this.evtTypeName = evtTypeName;
	}
}
