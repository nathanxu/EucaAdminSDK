package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class DeregisterComponentRequestMarshaller implements
		Marshaller<Request<DeregisterComponentRequest>, DeregisterComponentRequest> {

	public Request<DeregisterComponentRequest> marshall(
			DeregisterComponentRequest deregisterComponentRequest) {

		if (deregisterComponentRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}
		
		if (deregisterComponentRequest.getAction() == null) {
			throw new AmazonClientException(
					"No action specified");
		}
		
		if (deregisterComponentRequest.getName() == null) {
			throw new AmazonClientException("No component name specified");
		}

		Request<DeregisterComponentRequest> request = new DefaultRequest<DeregisterComponentRequest>(
				deregisterComponentRequest, "EucaAdmin");
		request.addParameter("Action", StringUtils.fromString(deregisterComponentRequest.getAction()));
		request.addParameter("Version", "eucalyptus");

		request.addParameter("Name",
				StringUtils.fromString(deregisterComponentRequest.getName()));
        if (deregisterComponentRequest.getPartition()!=null) {
        	request.addParameter("Partition",
    				StringUtils.fromString(deregisterComponentRequest.getPartition()));
        }
       
		return request;
	}
}
