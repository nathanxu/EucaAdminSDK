package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;


public class ModifyInstanceTypeRequestMarshaller implements Marshaller<Request<ModifyInstanceTypeRequest>, ModifyInstanceTypeRequest> {

    public Request<ModifyInstanceTypeRequest> marshall(ModifyInstanceTypeRequest modifyInstanceTypeRequest) {

        if (modifyInstanceTypeRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}
        
        if (modifyInstanceTypeRequest.getName()==null) {
        	throw new AmazonClientException("now name of instance type specificed");
        }
        
        Request<ModifyInstanceTypeRequest> request = new DefaultRequest<ModifyInstanceTypeRequest>(modifyInstanceTypeRequest, "EucaAdmin");
        request.addParameter("Action", "ModifyInstanceTypeAttribute");
        request.addParameter("Version", "eucalyptus");

        request.addParameter("Name", StringUtils.fromString(modifyInstanceTypeRequest.getName()));
        if (modifyInstanceTypeRequest.getReset()) {
        	request.addParameter("Reset", StringUtils.fromBoolean(modifyInstanceTypeRequest.getReset()));
        	return request;
        
        } else {
        	request.addParameter("Reset", StringUtils.fromBoolean(modifyInstanceTypeRequest.getReset()));
        }
        request.addParameter("Cpu", StringUtils.fromInteger(modifyInstanceTypeRequest.getCpu()));
        request.addParameter("Disk", StringUtils.fromInteger(modifyInstanceTypeRequest.getDisk()));
        request.addParameter("Memory", StringUtils.fromInteger(modifyInstanceTypeRequest.getMemory()));
        return request;
    }
}

