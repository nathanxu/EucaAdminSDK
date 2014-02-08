package com.eucalyptus.admin.console.model;

public class CapacityReportData {

	/**
	 * name of the resource
	 */
	String resource;
	
	/** 
	 * total amount
	 */
	Integer total;
	
	/**
	 * avaiable amount
	 */
	Integer avaiable;
	
	public String getResource() {
		return this.resource;
	}
	
	public Integer getTotal(){
		return this.total;
	}
	
	public Integer getAvailable(){
		return this.avaiable;
	}
	
	public void setResource(String resource) {
		this.resource = resource;
	}
	
	public void setTotal(Integer total){
		this.total = total;
	}
	
	public void setAvailable(Integer available){
		this.avaiable = available;
	}
}
