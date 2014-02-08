package com.eucalyptus.admin.model.transform;


import javax.xml.stream.events.XMLEvent;
import com.amazonaws.AmazonClientException;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.BooleanStaxUnmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.eucalyptus.admin.model.*;

public class RegisterComponentResultStaxUnmarshaller implements
		Unmarshaller<RegisterComponentResult, StaxUnmarshallerContext> {

	public RegisterComponentResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		RegisterComponentResult registerComponentResult = new RegisterComponentResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;
		while (true) {

			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return registerComponentResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("ConfigurationMessage/_return", targetDepth)) {
					registerComponentResult.setResult(BooleanStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return registerComponentResult;
				}
			}
		}
	}

	private static RegisterComponentResultStaxUnmarshaller instance;

	public static RegisterComponentResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new RegisterComponentResultStaxUnmarshaller();
		return instance;
	}

}
