package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class ModifyServiceResult extends AmazonWebServiceRequest implements Serializable{

	private boolean result;
	
	public boolean getResult() {
		return this.result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
