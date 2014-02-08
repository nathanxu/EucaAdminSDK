package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class ModifyInstanceTypeRequest  extends AmazonWebServiceRequest implements Serializable {
	
	String name;
	Integer cpu;
	Integer disk;
	Integer memory;
	boolean reset;

	public ModifyInstanceTypeRequest() {
		this.cpu = -1;
		this.disk = -1;
		this.memory = -1;
		this.reset = false;
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
	
	public boolean getReset() {
		return this.reset;
	}
	
	public void setReset(boolean reset) {
		this.reset = reset;
	}

}