package com.eucalyptus.admin.model;

public class VmTypeZoneStatus {
	String name;
	String zoneName;
	Integer max;
	Integer available;
	
	public VmTypeZoneStatus() {
		this.max = 0;
		this.available = 0;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getZoneName() {
		return this.zoneName;
	}

	public void setZoneName(String name) {
		this.zoneName = name;
	}

	public Integer getMax() {
		return this.max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
	
	public Integer getAvailable() {
		return this.available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

}
