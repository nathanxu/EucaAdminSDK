package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;

import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;


public class GenerateReportResultStaxUnmarshaller implements
		Unmarshaller<String, StaxUnmarshallerContext> {

	public String unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		
		String result = null;
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return result;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("Data")) {
					result = StringStaxUnmarshaller.getInstance().unmarshall(context);
					continue;
				}

			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return result;
				}
			}
		}
	}

	private static GenerateReportResultStaxUnmarshaller instance;

	public static GenerateReportResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new GenerateReportResultStaxUnmarshaller();
		return instance;
	}
}
