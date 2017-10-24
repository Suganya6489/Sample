package com.hubciti.common.pojos;

import java.util.List;

public class Coupons {

	private String type;
	
	private List<RetailerDetail> list;

	public List<RetailerDetail> getList() {
		return list;
	}

	public void setList(List<RetailerDetail> list) {
		this.list = list;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
