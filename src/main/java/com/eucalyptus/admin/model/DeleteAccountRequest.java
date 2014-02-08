package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;

public class DeleteAccountRequest extends AmazonWebServiceRequest implements Serializable {

	private boolean recursive = false;
	private String  accountName;
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public boolean getRecursive() {
		return this.recursive;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public void setRecursive(boolean value) {
		this.recursive = value;
	}
}
