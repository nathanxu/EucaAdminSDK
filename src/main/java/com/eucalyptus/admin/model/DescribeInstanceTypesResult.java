package com.eucalyptus.admin.model;

import java.io.Serializable;
import java.util.ArrayList;
import com.amazonaws.AmazonWebServiceRequest;

public class DescribeInstanceTypesResult extends AmazonWebServiceRequest
		implements Serializable {

	private ArrayList<VmTypeDetail> instanceTypeDetails;

	public java.util.ArrayList<VmTypeDetail> getInstanceTypeDetails() {

		if (instanceTypeDetails == null) {
			instanceTypeDetails = new java.util.ArrayList<VmTypeDetail>();
		}
		return instanceTypeDetails;
	}

	public void setInstanceTypeDetails(ArrayList<VmTypeDetail> instanceTypes) {
		if (instanceTypeDetails == null) {
			this.instanceTypeDetails = null;
			return;
		}
		java.util.ArrayList<VmTypeDetail> argCopy = new java.util.ArrayList<VmTypeDetail>(
				instanceTypes.size());
		argCopy.addAll(instanceTypes);
		this.instanceTypeDetails = argCopy;
	}

	public DescribeInstanceTypesResult withInstanceTypeDetails(
			java.util.Collection<VmTypeDetail> instanceTypes) {
		if (instanceTypeDetails == null) {
			this.instanceTypeDetails = null;
			return null;
		}
		java.util.ArrayList<VmTypeDetail> argCopy = new java.util.ArrayList<VmTypeDetail>(
				instanceTypes.size());
		argCopy.addAll(instanceTypes);
		this.instanceTypeDetails = argCopy;
		return this;

	}
}
