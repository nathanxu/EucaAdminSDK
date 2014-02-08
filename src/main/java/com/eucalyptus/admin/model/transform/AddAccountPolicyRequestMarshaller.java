package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class AddAccountPolicyRequestMarshaller implements
		Marshaller<Request<AddAccountPolicyRequest>, AddAccountPolicyRequest> {

	public Request<AddAccountPolicyRequest> marshall(
			AddAccountPolicyRequest addAccountPolicyRequest) {

		if (addAccountPolicyRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}
		if (addAccountPolicyRequest.getAccount() == null) {
			throw new AmazonClientException("must specifiy a account");
		}
		
		if (addAccountPolicyRequest.getPolicyBody() == null) {
			throw new AmazonClientException("must specifiy a account");
		}

		if (addAccountPolicyRequest.getPolicyName() == null) {
			throw new AmazonClientException("must specifiy policy name");
		}

		Request<AddAccountPolicyRequest> request = new DefaultRequest<AddAccountPolicyRequest>(
				addAccountPolicyRequest, "EucaAdmin");
		request.addParameter("Action", "PutAccountPolicy");
		request.addParameter("Version", "2010-05-08");
		request.addParameter("AccountName",
				StringUtils.fromString(addAccountPolicyRequest.getAccount()));
		request.addParameter("PolicyName",
				StringUtils.fromString(addAccountPolicyRequest.getPolicyName()));
		request.addParameter("PolicyDocument",
				StringUtils.fromString(addAccountPolicyRequest.getPolicyBody()));
		return request;
	}

}
