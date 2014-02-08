package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;

public class CreateAccountRequest extends AmazonWebServiceRequest implements Serializable {
	private String accountName;
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
}
