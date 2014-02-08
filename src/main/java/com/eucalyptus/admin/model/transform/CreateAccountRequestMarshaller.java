package com.eucalyptus.admin.model.transform;

import com.eucalyptus.admin.model.*;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;


public class CreateAccountRequestMarshaller implements
		Marshaller<Request<CreateAccountRequest>, CreateAccountRequest> {

	public Request<CreateAccountRequest> marshall(
			CreateAccountRequest createAccountRequest) {

		if (createAccountRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}

		if (createAccountRequest.getAccountName() == null) {
			throw new AmazonClientException("No account name specified");
		}

		Request<CreateAccountRequest> request = new DefaultRequest<CreateAccountRequest>(
				createAccountRequest, "EucaAdmin");
		request.addParameter("Action", "CreateAccount");
		request.addParameter("Version", "2010-05-08");

		request.addParameter("AccountName",
				StringUtils.fromString(createAccountRequest.getAccountName()));

		return request;
	}
}
