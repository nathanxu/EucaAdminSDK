package com.eucalyptus.admin.model.transform;


import javax.xml.stream.events.XMLEvent;

import com.eucalyptus.admin.model.*;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.*;

public class ModifyPropertyResultStaxUnmarshaller implements
		Unmarshaller<ModifyPropertyResult, StaxUnmarshallerContext> {

	public ModifyPropertyResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		ModifyPropertyResult modifyPropertyResult = new ModifyPropertyResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;
        //System.out.println(context.readText());
		while (true) {
			
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return modifyPropertyResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				//System.out.println(xmlEvent.toString());
				if (context.testExpression("name", targetDepth)) {
					modifyPropertyResult.setName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					
					continue;
				}
				if (context.testExpression("value", targetDepth)) {
					modifyPropertyResult.setValue(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("oldValue", targetDepth)) {
					modifyPropertyResult.setOldValue(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return modifyPropertyResult;
				}
			}
		}
	}

	private static ModifyPropertyResultStaxUnmarshaller instance;

	public static ModifyPropertyResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new ModifyPropertyResultStaxUnmarshaller();
		return instance;
	}
}
