package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class RegisterComponentRequestMarshaller implements
		Marshaller<Request<RegisterComponentRequest>, RegisterComponentRequest> {

	public Request<RegisterComponentRequest> marshall(
			RegisterComponentRequest registerComponentRequest) {

		if (registerComponentRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}
		
		if (registerComponentRequest.getAction() == null) {
			throw new AmazonClientException(
					"No action specified");
		}
		
		if (registerComponentRequest.getName() == null) {
			throw new AmazonClientException("No component name specified");
		}

		Request<RegisterComponentRequest> request = new DefaultRequest<RegisterComponentRequest>(
				registerComponentRequest, "EucaAdmin");
		request.addParameter("Action", StringUtils.fromString(registerComponentRequest.getAction()));
		request.addParameter("Version", "eucalyptus");

		request.addParameter("Name",
				StringUtils.fromString(registerComponentRequest.getName()));
        if (registerComponentRequest.getHost()!=null) {
        	request.addParameter("Host",
    				StringUtils.fromString(registerComponentRequest.getHost()));
        }
        if (registerComponentRequest.getPartition()!=null) {
        	request.addParameter("Partition",
    				StringUtils.fromString(registerComponentRequest.getPartition()));
        }
        if (registerComponentRequest.getPort()!=-1) {
        	request.addParameter("Port",
    				StringUtils.fromInteger(new Integer(registerComponentRequest.getPort())));
        }
		return request;
	}
}
