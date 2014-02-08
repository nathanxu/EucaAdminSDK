package com.eucalyptus.admin.model.transform;

import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.eucalyptus.admin.model.*;

public class ListAccountsRequestMarshaller implements
		Marshaller<Request<ListAccountsRequest>, ListAccountsRequest> {

	public Request<ListAccountsRequest> marshall(ListAccountsRequest listAccountsRequest) {
		
		
		Request<ListAccountsRequest> request = new DefaultRequest<ListAccountsRequest>(
				listAccountsRequest, "EucaAdmin");
		request.addParameter("Action", "ListAccounts");
		request.addParameter("Version", "2010-05-08");
		return request;
	}

}
