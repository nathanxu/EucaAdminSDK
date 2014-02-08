package com.eucalyptus.admin.model;

import java.io.Serializable;

import com.amazonaws.AmazonWebServiceRequest;
public class AddAccountPolicyRequest extends AmazonWebServiceRequest implements Serializable {
	private String account;
	private String policyName;
	private String policyBody;
	
	public String getAccount() {
		return this.account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
    
	
	public String getPolicyName() {
		return this.policyName;
	}
	
	public void setPolicyName(String name) {
		this.policyName = name;
	}
    
	public String getPolicyBody() {
		return this.policyBody;
	}
	
	public void setPolicyBody(String body) {
		this.policyBody = body;
	}
}
