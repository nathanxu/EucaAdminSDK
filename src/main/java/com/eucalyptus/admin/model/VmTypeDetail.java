package com.eucalyptus.admin.model;

import java.util.ArrayList;

public class VmTypeDetail {
	String name;
	Integer cpu;
	Integer disk;
	Integer memory;
	ArrayList<VmTypeZoneStatus> availability;

	public VmTypeDetail() {
		this.cpu = 0;
		this.disk = 0;
		this.memory = 0;
		this.availability = new ArrayList<VmTypeZoneStatus>();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCpu() {
		return this.cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public Integer getDisk() {
		return this.disk;
	}

	public void setDisk(Integer disk) {
		this.disk = disk;
	}

	public Integer getMemory() {
		return this.memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public ArrayList<VmTypeZoneStatus> getAvailability() {
		if (availability == null) {
			availability = new ArrayList<VmTypeZoneStatus>();
		}
		return availability;
	}
	
	public void setAvailability(ArrayList<VmTypeZoneStatus> availability) {
		if (availability == null) {
			this.availability = null;
			return;
		}
		java.util.ArrayList<VmTypeZoneStatus> argCopy = new java.util.ArrayList<VmTypeZoneStatus>(
				availability.size());
		argCopy.addAll(availability);
		this.availability = argCopy;
	}

}
