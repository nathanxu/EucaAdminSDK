package com.eucalyptus.admin.model.transform;


import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class ModifyServiceRequestMarshaller implements Marshaller<Request<ModifyServiceRequest>, ModifyServiceRequest> {

    public Request<ModifyServiceRequest> marshall(ModifyServiceRequest modifyServiceRequest) {

        if (modifyServiceRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}
        
        if (modifyServiceRequest.getServiceName() == null || modifyServiceRequest.getState() == null) {
		    throw new AmazonClientException("No service name or state specified");
		}

        Request<ModifyServiceRequest> request = new DefaultRequest<ModifyServiceRequest>(modifyServiceRequest, "EucaAdmin");
        request.addParameter("Action", "ModifyService");
        request.addParameter("Version", "eucalyptus");

        request.addParameter("Name",StringUtils.fromString(modifyServiceRequest.getServiceName()));
        request.addParameter("State",StringUtils.fromString(modifyServiceRequest.getState()));
        
        return request;
    }

}
