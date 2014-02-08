package com.eucalyptus.admin.model.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.ec2.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class DescribeComponentsRequestMarshaller implements
		Marshaller<Request<DescribeComponentsRequest>, DescribeComponentsRequest> {

	public Request<DescribeComponentsRequest> marshall(
			DescribeComponentsRequest describeComponentsRequest) {

		if (describeComponentsRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}
		if (describeComponentsRequest.getAction() == null) {
			throw new AmazonClientException("No specific action specified");
		}
		Request<DescribeComponentsRequest> request = new DefaultRequest<DescribeComponentsRequest>(
				describeComponentsRequest, "EucaAdmin");
		request.addParameter("Action", StringUtils.fromString(describeComponentsRequest.getAction()));
		request.addParameter("Version", "eucalyptus");

		return request;
	}
}
