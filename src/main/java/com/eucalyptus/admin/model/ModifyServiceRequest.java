package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class ModifyServiceRequest extends AmazonWebServiceRequest implements Serializable {

	private String serviceName;
	private String state;
	
	public String getServiceName(){
		return this.serviceName;
	}
	public String getState(){
		return this.state;
	}
	public void setServiceName(String name){
		this.serviceName = name;
	}
	
	public void setState(String state){
		this.state = state;
	}
}
