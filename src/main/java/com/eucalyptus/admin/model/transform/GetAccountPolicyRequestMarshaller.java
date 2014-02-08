package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class GetAccountPolicyRequestMarshaller implements
		Marshaller<Request<GetAccountPolicyRequest>, GetAccountPolicyRequest> {

	public Request<GetAccountPolicyRequest> marshall(
			GetAccountPolicyRequest getAccountPolicyRequest) {

		if (getAccountPolicyRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}
		if (getAccountPolicyRequest.getAccount() == null) {
			throw new AmazonClientException("must specifiy a account");
		}
		
		if (getAccountPolicyRequest.getPolicyName() == null) {
			throw new AmazonClientException("must specifiy policy name");
		}
		
		Request<GetAccountPolicyRequest> request = new DefaultRequest<GetAccountPolicyRequest>(
				getAccountPolicyRequest, "EucaAdmin");
		request.addParameter("Action", "GetAccountPolicy");
		request.addParameter("Version", "2010-05-08");
		request.addParameter("AccountName",
				StringUtils.fromString(getAccountPolicyRequest.getAccount()));
		request.addParameter("PolicyName",
				StringUtils.fromString(getAccountPolicyRequest.getPolicyName()));
		return request;
	}

}
