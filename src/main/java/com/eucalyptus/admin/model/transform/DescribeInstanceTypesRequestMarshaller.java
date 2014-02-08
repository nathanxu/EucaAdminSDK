package com.eucalyptus.admin.model.transform;


import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;


public class DescribeInstanceTypesRequestMarshaller implements Marshaller<Request<DescribeInstanceTypesRequest>, DescribeInstanceTypesRequest> {

    public Request<DescribeInstanceTypesRequest> marshall(DescribeInstanceTypesRequest describeInstanceTypesRequest) {

        if (describeInstanceTypesRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<DescribeInstanceTypesRequest> request = new DefaultRequest<DescribeInstanceTypesRequest>(describeInstanceTypesRequest, "EucaAdmin");
        request.addParameter("Action", "DescribeInstanceTypes");
        request.addParameter("Version", "eucalyptus");


        java.util.ArrayList<String> typeList = describeInstanceTypesRequest.getInstanceTypes();
        int typeListIndex = 1;

        for (String type : typeList) {
            if (type != null) {
                request.addParameter("InstanceType." + typeListIndex, StringUtils.fromString(type));
            }

            typeListIndex++;
        }
        request.addParameter("Availability", StringUtils.fromBoolean(describeInstanceTypesRequest.getAvailability()));
        return request;
    }
}

