package com.eucalyptus.admin.model;

import java.io.Serializable;
import com.amazonaws.AmazonWebServiceRequest;

public class GetAccountPolicyResult extends AmazonWebServiceRequest implements Serializable {
	
	String policyName;
	String policyBody;
	
	public String getPolicyName() {
		return this.policyName;
	}
	
	public String getPolicyBody() {
		return this.policyBody;
	}
	
	public void setPolicyName(String name) {
		this.policyName = name;
	}
	
	public void setPolicyBody(String body) {
		this.policyBody = body;
	}
}
