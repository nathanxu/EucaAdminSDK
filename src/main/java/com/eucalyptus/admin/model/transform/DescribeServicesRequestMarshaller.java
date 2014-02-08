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

public class DescribeServicesRequestMarshaller implements Marshaller<Request<DescribeServicesRequest>, DescribeServicesRequest> {

    public Request<DescribeServicesRequest> marshall(DescribeServicesRequest describeServicesRequest) {

        if (describeServicesRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<DescribeServicesRequest> request = new DefaultRequest<DescribeServicesRequest>(describeServicesRequest, "EucaAdmin");
        request.addParameter("Action", "DescribeServices");
        request.addParameter("Version", "eucalyptus");


        java.util.ArrayList<String> serviceNames = describeServicesRequest.getServiceNames();
        int servicesListIndex = 1;

        for (String serviceName : serviceNames) {
            if (serviceName != null) {
                request.addParameter("ServiceName." + servicesListIndex, StringUtils.fromString(serviceName));
            }

            servicesListIndex++;
        }
        
        if (describeServicesRequest.getByHost() !=null ) {
        	request.addParameter("ByHost", StringUtils.fromString(describeServicesRequest.getByHost()));
        }
        if (describeServicesRequest.getByState() !=null ) {
        	request.addParameter("ByState", StringUtils.fromString(describeServicesRequest.getByState()));
        }
        
        if (describeServicesRequest.getByServiceType() !=null ) {
        	request.addParameter("ByServiceType", StringUtils.fromString(describeServicesRequest.getByServiceType()));
        }
        
        if (describeServicesRequest.getByPartition() !=null ) {
        	request.addParameter("ByParition", StringUtils.fromString(describeServicesRequest.getByPartition()));
        }
        
        request.addParameter("ListAll", StringUtils.fromBoolean(describeServicesRequest.getListAll()));
        request.addParameter("ListInternal", StringUtils.fromBoolean(describeServicesRequest.getListInternal()));
        request.addParameter("ListUserServices", StringUtils.fromBoolean(describeServicesRequest.getListUserServices()));
        request.addParameter("ShowEvents", StringUtils.fromBoolean(describeServicesRequest.getShowEvents()));
        request.addParameter("ShowEventStacks", StringUtils.fromBoolean(describeServicesRequest.getShowEventStacks()));
        return request;
    }
}
