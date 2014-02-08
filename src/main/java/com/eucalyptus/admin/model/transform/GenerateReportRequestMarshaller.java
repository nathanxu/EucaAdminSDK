package com.eucalyptus.admin.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class GenerateReportRequestMarshaller implements
		Marshaller<Request<GenerateReportRequest>, GenerateReportRequest> {

	public Request<GenerateReportRequest> marshall(
			GenerateReportRequest generateReportRequest) {

		if (generateReportRequest == null) {
			throw new AmazonClientException(
					"Invalid argument passed to marshall(...)");
		}
		if (generateReportRequest.getReportType() == null) {
			throw new AmazonClientException("must specify report type");
		}
		
		if (generateReportRequest.getReportFormat() == null) {
			throw new AmazonClientException("must specify report format (csv,html)");
		}
		
		Request<GenerateReportRequest> request = new DefaultRequest<GenerateReportRequest>(
				generateReportRequest, "EucaAdmin");
		request.addParameter("Action","GenerateReport");
		request.addParameter("Version", "2012-08-24");
		request.addParameter("Type",StringUtils.fromString(generateReportRequest.getReportType()));
		request.addParameter("Format",StringUtils.fromString(generateReportRequest.getReportFormat()));

		return request;
	}

}
