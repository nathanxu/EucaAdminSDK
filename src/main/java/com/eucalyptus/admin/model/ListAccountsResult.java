package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;
import java.util.*;

public class ListAccountsResult extends AmazonWebServiceRequest implements Serializable {
	private ArrayList<Account> accounts;
	
	public java.util.ArrayList<Account> getAccounts() {
        
        if (accounts == null) {
        	accounts = new java.util.ArrayList<Account>();
        }
        return accounts;
    }
    
 
    public void setAccount(java.util.ArrayList<Account> accounts) {
        if (accounts == null) {
            this.accounts = null;
            return;
        }
        java.util.ArrayList<Account> accountsCopy = new java.util.ArrayList<Account>(accounts.size());
        accountsCopy.addAll(accounts);
        this.accounts = accountsCopy;
    }
}
