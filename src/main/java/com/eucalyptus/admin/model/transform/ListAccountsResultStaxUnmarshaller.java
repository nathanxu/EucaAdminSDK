package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;
import com.eucalyptus.admin.model.*;

public class ListAccountsResultStaxUnmarshaller implements
		Unmarshaller<ListAccountsResult, StaxUnmarshallerContext> {

	public ListAccountsResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		ListAccountsResult listAccountsResult = new ListAccountsResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return listAccountsResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("Accounts/member")) {
					listAccountsResult.getAccounts().add(
							accountUnmarshall(context));
					continue;
				}
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return listAccountsResult;
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

	private Account accountUnmarshall(StaxUnmarshallerContext context)
			throws Exception {

		Account account = new Account();
		account.setAccountStatus("confirmed");
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return account;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("AccountName", targetDepth)) {
					account.setAccountName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("AccountId", targetDepth)) {
					account.setAccountId(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return account;
				}
			}
		}
	}

}
