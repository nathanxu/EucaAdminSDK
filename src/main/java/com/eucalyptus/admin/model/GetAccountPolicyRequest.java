package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;

public class GetAccountPolicyRequest extends AmazonWebServiceRequest implements Serializable {
	private String account;
	private String policyName;
	
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
}
