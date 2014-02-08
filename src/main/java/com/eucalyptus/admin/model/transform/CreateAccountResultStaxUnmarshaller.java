package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.eucalyptus.admin.model.*;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.*;

public class CreateAccountResultStaxUnmarshaller implements
		Unmarshaller<CreateAccountResult, StaxUnmarshallerContext> {

	public CreateAccountResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		CreateAccountResult createAccountResult = new CreateAccountResult();
		createAccountResult.setAccountStatus("confirmed");
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;
		while (true) {

			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return createAccountResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("AccountName")) {
					createAccountResult.setAccountName(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}
				if (context.testExpression("AccountId")) {
					createAccountResult.setAccountId(StringStaxUnmarshaller
							.getInstance().unmarshall(context));
					continue;
				}

			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return createAccountResult;
				}
			}
		}
	}

	private static CreateAccountResultStaxUnmarshaller instance;

	public static CreateAccountResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new CreateAccountResultStaxUnmarshaller();
		return instance;
	}

}
