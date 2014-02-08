package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class DeleteAccountPolicyRequestMarshaller implements
		Marshaller<Request<DeleteAccountPolicyRequest>, DeleteAccountPolicyRequest> {

	public Request<DeleteAccountPolicyRequest> marshall(
			DeleteAccountPolicyRequest delAccountPolicyRequest) {

		if (delAccountPolicyRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}
		if (delAccountPolicyRequest.getAccount() == null) {
			throw new AmazonClientException("must specifiy a account");
		}

		if (delAccountPolicyRequest.getPolicyName() == null) {
			throw new AmazonClientException("must specifiy policy name");
		}

		Request<DeleteAccountPolicyRequest> request = new DefaultRequest<DeleteAccountPolicyRequest>(
				delAccountPolicyRequest, "EucaAdmin");
		request.addParameter("Action", "DeleteAccountPolicy");
		request.addParameter("Version", "2010-05-08");
		request.addParameter("AccountName",
				StringUtils.fromString(delAccountPolicyRequest.getAccount()));
		request.addParameter("PolicyName",
				StringUtils.fromString(delAccountPolicyRequest.getPolicyName()));
		return request;
	}

}
