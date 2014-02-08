package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;

import com.eucalyptus.admin.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.*;

public class DescribePropertiesResultStaxUnmarshaller implements
		Unmarshaller<DescribePropertiesResult, StaxUnmarshallerContext> {

	public DescribePropertiesResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		DescribePropertiesResult describePropertiesResult = new DescribePropertiesResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return describePropertiesResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("properties/item", targetDepth)) {
					describePropertiesResult.getProperties().add(propertyUnmarshall(context));
					continue;
				}
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return describePropertiesResult;
				}
			}
		}
	}

	private static DescribePropertiesResultStaxUnmarshaller instance;

	public static DescribePropertiesResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new DescribePropertiesResultStaxUnmarshaller();
		return instance;
	}

	private CloudProperty propertyUnmarshall(StaxUnmarshallerContext context)
			throws Exception {

		CloudProperty property = new CloudProperty();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return property;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("name", targetDepth)) {
					property.setName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("value", targetDepth)) {
					property.setValue(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("description", targetDepth)) {
					property.setDescription(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}

			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return property;
				}
			}
		}
	}

}
