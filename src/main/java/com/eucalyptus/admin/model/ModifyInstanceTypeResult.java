package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;


public class ModifyInstanceTypeResult extends AmazonWebServiceRequest implements Serializable  {
	private VmTypeDetail instanceType;
	private VmTypeDetail oldInstanceType;
	
	public VmTypeDetail getInstanceType() {
		return this.instanceType;
	}
	
	public void setInstanceType(VmTypeDetail instanceType) {
		this.instanceType = instanceType;
	}
	
	public VmTypeDetail getOldInstanceType() {
		return this.instanceType;
	}
	
	public void setOldInstanceType(VmTypeDetail instanceType) {
		this.instanceType = instanceType;
	}
}
