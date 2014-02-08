package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class ListAccountPoliciesRequestMarshaller implements
		Marshaller<Request<ListAccountPoliciesRequest>, ListAccountPoliciesRequest> {

	public Request<ListAccountPoliciesRequest> marshall(
			ListAccountPoliciesRequest listAccountPoliciesRequest) {
		
		if (listAccountPoliciesRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}
		if (listAccountPoliciesRequest.getAccount() == null) {
			throw new AmazonClientException("must specifiy a account");
		}
		Request<ListAccountPoliciesRequest> request = new DefaultRequest<ListAccountPoliciesRequest>(
				listAccountPoliciesRequest, "EucaAdmin");
		request.addParameter("Action", "ListAccountPolicies");
		request.addParameter("Version", "2010-05-08");
		request.addParameter("AccountName",
				StringUtils.fromString(listAccountPoliciesRequest.getAccount()));
		return request;
	}

}
