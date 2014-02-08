package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;

import com.eucalyptus.admin.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.*;
import com.eucalyptus.admin.model.ServiceStatus;

public class DescribeServicesResultStaxUnmarshaller implements
		Unmarshaller<DescribeServicesResult, StaxUnmarshallerContext> {

	public DescribeServicesResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		DescribeServicesResult describeServicesResult = new DescribeServicesResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return describeServicesResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("serviceStatuses/item", targetDepth)) {
					describeServicesResult.getServices().add(
							ServiceStatusUnmarshall(context));
					continue;
				}
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return describeServicesResult;
				}
			}
		}
	}

	private static DescribeServicesResultStaxUnmarshaller instance;

	public static DescribeServicesResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new DescribeServicesResultStaxUnmarshaller();
		return instance;
	}

	private ServiceStatus ServiceStatusUnmarshall(StaxUnmarshallerContext context)
			throws Exception {

		ServiceStatus service = new ServiceStatus();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return service;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("serviceId", targetDepth)) {
					ServiceIdUnmarshall(context,service);
					continue;
				}
				
				if (context.testExpression("localState", targetDepth)) {
					service.setLocalState(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
				if (context.testExpression("localEpoch", targetDepth)) {
					service.setLocalEpoch((IntegerStaxUnmarshaller.getInstance()
							.unmarshall(context).intValue()));
					continue;
				}
				
				if (context.testExpression("details/item", targetDepth)) {
					DetailUnmarshaller(context,service);
					continue;
				}
				
				if (context.testExpression("statusDetails/item", targetDepth)) {
					service.getStatusDetails().add(StatusDetailUnmarshaller(context));
					continue;
				}

			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return service;
				}
			}

		}

	}
	private void ServiceIdUnmarshall(StaxUnmarshallerContext context, ServiceStatus service)
			throws Exception {

		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("uuid", targetDepth)) {
					service.setUuid(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
				if (context.testExpression("partition", targetDepth)) {
					service.setPartition(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("name", targetDepth)) {
					service.setName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("fullName", targetDepth)) {
					service.setFullName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("uri", targetDepth)) {
					service.setUri(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("type", targetDepth)) {
					service.setType(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
				if (context.testExpression("uris/item", targetDepth)) {
					UrisUnmarshaller(context,service);
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return;
				}
			}

		}

	}
    private void UrisUnmarshaller(StaxUnmarshallerContext context, ServiceStatus service)
			throws Exception {
    	
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("entry", targetDepth)) {
					service.getUris().add(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return;
				}
			}

		}

	}
    private void DetailUnmarshaller(StaxUnmarshallerContext context, ServiceStatus service)
			throws Exception {
    	
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("entry", targetDepth)) {
					service.getDetails().add(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return;
				}
			}

		}

	}
    private ServiceStatusDetail StatusDetailUnmarshaller(StaxUnmarshallerContext context)
			throws Exception {
    	
    	ServiceStatusDetail statusDetail = new ServiceStatusDetail();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return statusDetail;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("severity", targetDepth)) {
					statusDetail.severity = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				if (context.testExpression("message", targetDepth)) {
					statusDetail.message = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				if (context.testExpression("serviceHost", targetDepth)) {
					statusDetail.serviceHost = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				if (context.testExpression("serviceName", targetDepth)) {
					statusDetail.serviceName = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				if (context.testExpression("serviceFullName", targetDepth)) {
					statusDetail.serviceFullName = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				if (context.testExpression("stackTrace", targetDepth)) {
					statusDetail.stackTrace = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				if (context.testExpression("timestamp", targetDepth)) {
					statusDetail.timestamp = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				if (context.testExpression("uuid", targetDepth)) {
					statusDetail.uuid = StringStaxUnmarshaller.getInstance()
							.unmarshall(context);
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return statusDetail;
				}
			}

		}

	}
    
}
