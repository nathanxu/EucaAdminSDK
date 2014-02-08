package com.eucalyptus.admin.model;
import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;


public class DescribeComponentsRequest extends AmazonWebServiceRequest implements Serializable {
	private String action;
	
	public String getAction(){
		return this.action;
	}
	
	public void setAction(String action){
		this.action = action;
	}
}
