package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;

public class ListAccountPoliciesRequest extends AmazonWebServiceRequest implements Serializable {
	private String account;
	
	public String getAccount() {
		return this.account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
}
