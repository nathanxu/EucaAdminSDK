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

public class ModifyPropertyRequestMarshaller implements Marshaller<Request<ModifyPropertyRequest>, ModifyPropertyRequest> {
	
	public Request<ModifyPropertyRequest> marshall(ModifyPropertyRequest modifyPropertyRequest) {

        if (modifyPropertyRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}
        if (modifyPropertyRequest.getName() == null) {
		    throw new AmazonClientException("No property name specified");
		}
        

        Request<ModifyPropertyRequest> request = new DefaultRequest<ModifyPropertyRequest>(modifyPropertyRequest, "EucaAdmin");
        request.addParameter("Action", "ModifyPropertyValue");
        request.addParameter("Version", "eucalyptus");

        
        request.addParameter("Name", StringUtils.fromString(modifyPropertyRequest.getName()));
        if (modifyPropertyRequest.getValue()!=null) {
        	request.addParameter("Value", StringUtils.fromString(modifyPropertyRequest.getValue().toString()));
        }
        
        if (modifyPropertyRequest.getReset()) {
        	request.addParameter("Reset", StringUtils.fromBoolean(modifyPropertyRequest.getReset()));
        }
        
 
        
        return request;
    }
}
