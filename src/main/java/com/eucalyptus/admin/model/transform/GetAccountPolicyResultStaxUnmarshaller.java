package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;

import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;
import com.eucalyptus.admin.model.*;

public class GetAccountPolicyResultStaxUnmarshaller implements
		Unmarshaller<GetAccountPolicyResult, StaxUnmarshallerContext> {

	public GetAccountPolicyResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		GetAccountPolicyResult getAccountPolicyResult = new GetAccountPolicyResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return getAccountPolicyResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("PolicyName")) {
					getAccountPolicyResult.setPolicyName(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}
				
				if (context.testExpression("PolicyDocument")) {
					getAccountPolicyResult.setPolicyBody(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return getAccountPolicyResult;
				}
			}
		}
	}

	private static ListAccountsResultStaxUnmarshaller instance;

	public static ListAccountsResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new ListAccountsResultStaxUnmarshaller();
		return instance;
	}

}
