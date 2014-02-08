package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;

public class CreateAccountResult extends AmazonWebServiceRequest implements Serializable {
	
	private String accountId;
	
	private String accountName;
	
	private String accountStatus;
	
	
	public String getAccountName() {
		return this.accountName;
	}
	
	public String getAccountId() {
		return this.accountId;
	}
	
	public String getAccountStatus() {
		return this.accountStatus;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public void setAccountStatus(String status) {
		this.accountStatus = status;
	}
	
}
