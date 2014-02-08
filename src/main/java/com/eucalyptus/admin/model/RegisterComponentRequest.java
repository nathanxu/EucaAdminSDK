package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class RegisterComponentRequest extends AmazonWebServiceRequest implements Serializable {

	private String action;
	private String partition;
	private String name;
	private String host;
	private int port;
	
	public RegisterComponentRequest() {
		this.port = -1;
	}
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
	public String getHost() {
		return this.host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return this.port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
}
