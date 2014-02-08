package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;
import com.eucalyptus.admin.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.*;

public class DescribeComponentsResultStaxUnmarshaller implements
		Unmarshaller<DescribeComponentsResult, StaxUnmarshallerContext> {

	public DescribeComponentsResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		DescribeComponentsResult describeComponentsResult = new DescribeComponentsResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return describeComponentsResult;
			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				//System.out.println(xmlEvent.toString());
				if (context.testExpression("registered/item")) {
					//System.out.println("xx");		
					describeComponentsResult.getComponents().add(
							ComponentInfoUnmarshall(context)); 
					continue;
				}
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return describeComponentsResult;
				}
			}
		}
	}

	private static DescribeComponentsResultStaxUnmarshaller instance;

	public static DescribeComponentsResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new DescribeComponentsResultStaxUnmarshaller();
		return instance;
	}

	private ComponentInfo ComponentInfoUnmarshall(
			StaxUnmarshallerContext context) throws Exception {

		ComponentInfo componentInfo = new ComponentInfo();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return componentInfo;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("name", targetDepth)) {
					componentInfo.setName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("type", targetDepth)) {
					componentInfo.setType(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("hostName", targetDepth)) {
					componentInfo.setHostName(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}
				if (context.testExpression("fullName", targetDepth)) {
					componentInfo.setFullName(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}
				if (context.testExpression("state", targetDepth)) {
					componentInfo.setState(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("detail", targetDepth)) {
					componentInfo.setDetail(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}
				
				if (context.testExpression("partition", targetDepth)) {
					componentInfo.setPartition(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}

			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return componentInfo;
				}
			}
		}
	}

}
