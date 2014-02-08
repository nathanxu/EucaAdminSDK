package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;
import com.amazonaws.AmazonClientException;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.BooleanStaxUnmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.eucalyptus.admin.model.*;

public class DeregisterComponentResultStaxUnmarshaller implements
		Unmarshaller<DeregisterComponentResult, StaxUnmarshallerContext> {

	public DeregisterComponentResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		DeregisterComponentResult deregisterComponentResult = new DeregisterComponentResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;
		while (true) {

			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return deregisterComponentResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("ConfigurationMessage/_return", targetDepth)) {
					deregisterComponentResult.setResult(BooleanStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return deregisterComponentResult;
				}
			}
		}
	}

	private static DeregisterComponentResultStaxUnmarshaller instance;

	public static DeregisterComponentResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new DeregisterComponentResultStaxUnmarshaller();
		return instance;
	}

}
