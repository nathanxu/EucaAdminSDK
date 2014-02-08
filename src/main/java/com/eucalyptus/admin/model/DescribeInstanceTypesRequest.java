package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;

public class DescribeInstanceTypesRequest  extends AmazonWebServiceRequest implements Serializable {
	
	private ArrayList<String> instanceTypes;
	private boolean availability;
	
	public DescribeInstanceTypesRequest() {
		this.availability = false;
	}
	public ArrayList<String> getInstanceTypes() {

		if (instanceTypes == null) {
			instanceTypes = new java.util.ArrayList<String>();
		}
		return instanceTypes;
	}

	public void setInstanceTypes(ArrayList<String> instanceTypes) {
		if (instanceTypes== null) {
			this.instanceTypes = null;
			return;
		}
		java.util.ArrayList<String> argCopy = new java.util.ArrayList<String>(
				instanceTypes.size());
		argCopy.addAll(instanceTypes);
		this.instanceTypes = argCopy;
	}
	
	public boolean getAvailability() {
		return this.availability;
	}
	
	public void setAvailability(boolean arg) {
		this.availability = arg;
	}
}
