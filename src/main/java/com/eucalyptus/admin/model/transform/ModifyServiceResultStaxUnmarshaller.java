package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.BooleanStaxUnmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.model.*;

public class ModifyServiceResultStaxUnmarshaller implements
		Unmarshaller<ModifyServiceResult, StaxUnmarshallerContext> {

	public ModifyServiceResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		ModifyServiceResult modifyServiceResult = new ModifyServiceResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;
		// System.out.println(context.readText());
		while (true) {

			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return modifyServiceResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("EmpyreanMessage/_return", targetDepth)) {
					modifyServiceResult.setResult(BooleanStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return modifyServiceResult;
				}
			}
		}
	}

	private static ModifyServiceResultStaxUnmarshaller instance;

	public static ModifyServiceResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new ModifyServiceResultStaxUnmarshaller();
		return instance;
	}

}
