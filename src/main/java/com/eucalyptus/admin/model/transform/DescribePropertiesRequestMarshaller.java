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


public class DescribePropertiesRequestMarshaller implements Marshaller<Request<DescribePropertiesRequest>, DescribePropertiesRequest> {

    public Request<DescribePropertiesRequest> marshall(DescribePropertiesRequest describePropertiesRequest) {

        if (describePropertiesRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<DescribePropertiesRequest> request = new DefaultRequest<DescribePropertiesRequest>(describePropertiesRequest, "AmazonEC2");
        request.addParameter("Action", "DescribeProperties");
        request.addParameter("Version", "eucalyptus");


        java.util.ArrayList<String> propertiesList = describePropertiesRequest.getProperties();
        int propertiesListIndex = 1;

        for (String propertiesListValue : propertiesList) {
            if (propertiesListValue != null) {
                request.addParameter("Property." + propertiesListIndex, StringUtils.fromString(propertiesListValue));
            }

            propertiesListIndex++;
        }
        return request;
    }
}

