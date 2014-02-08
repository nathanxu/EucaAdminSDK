package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;

import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;
import com.eucalyptus.admin.model.*;

public class ListAccountPoliciesResultStaxUnmarshaller implements
		Unmarshaller<ListAccountPoliciesResult, StaxUnmarshallerContext> {

	public ListAccountPoliciesResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		ListAccountPoliciesResult listAccountPoliciesResult = new ListAccountPoliciesResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return listAccountPoliciesResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("PolicyNames/member")) {
					listAccountPoliciesResult.getPolicies().add(
							 StringStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return listAccountPoliciesResult;
				}
			}
		}
	}

	private static ListAccountPoliciesResultStaxUnmarshaller instance;

	public static ListAccountPoliciesResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new ListAccountPoliciesResultStaxUnmarshaller();
		return instance;
	}

	private String policyUnmarshall(StaxUnmarshallerContext context)
			throws Exception {


		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;
        String policy = null;
		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return policy;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("AccountName", targetDepth)) {
					policy = StringStaxUnmarshaller.getInstance().unmarshall(context);
					continue;
				}

			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return policy;
				}
			}
		}
	}


}
