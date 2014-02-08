package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;
import java.util.*;

public class ListAccountPoliciesResult extends AmazonWebServiceRequest implements Serializable {
	ArrayList<String> policies;
	
	public ArrayList<String> getPolicies() {
		if (this.policies == null) {
			this.policies = new ArrayList<String>();
		}
		return this.policies;
	}
	
	public void setPolicies(ArrayList<String> policies) {
		if (policies == null) {
			this.policies = null;
			return;
		}
		ArrayList<String> copy = new ArrayList<String>(policies.size());
		copy.addAll(policies);
		this.policies = copy;
	}
}
