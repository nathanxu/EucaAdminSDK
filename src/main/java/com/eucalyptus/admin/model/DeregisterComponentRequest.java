package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class DeregisterComponentRequest extends AmazonWebServiceRequest implements Serializable {

	private String action;
	private String partition;
	private String name;
	
	public String getAction() {
		return this.action;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getPartition() {
		return this.partition;
	}
	
	public void setPartition(String partition) {
		this.partition = partition;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
