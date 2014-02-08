package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;

import com.eucalyptus.admin.model.*;

public class DeleteAccountRequestMarshaller implements
		Marshaller<Request<DeleteAccountRequest>, DeleteAccountRequest> {

	public Request<DeleteAccountRequest> marshall(
			DeleteAccountRequest deleteAccountRequest) {

		if (deleteAccountRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}

		if (deleteAccountRequest.getAccountName() == null) {
			throw new AmazonClientException("No account name specified");
		}

		Request<DeleteAccountRequest> request = new DefaultRequest<DeleteAccountRequest>(deleteAccountRequest, "EucaAdmin");
		request.addParameter("Action", "DeleteAccount");
		request.addParameter("Version", "2010-05-08");

		request.addParameter("AccountName",
				StringUtils.fromString(deleteAccountRequest.getAccountName()));
		request.addParameter("Recursive",StringUtils.fromBoolean(deleteAccountRequest.getRecursive()));
		return request;
	}
}
