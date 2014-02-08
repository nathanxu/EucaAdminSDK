package com.eucalyptus.admin.model.transform;

import javax.xml.stream.events.XMLEvent;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.IntegerStaxUnmarshaller;
import com.amazonaws.transform.SimpleTypeStaxUnmarshallers.StringStaxUnmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
import com.eucalyptus.admin.model.*;

public class ModifyInstanceTypeResultStaxUnmarshaller implements
		Unmarshaller<ModifyInstanceTypeResult, StaxUnmarshallerContext> {

	public ModifyInstanceTypeResult unmarshall(StaxUnmarshallerContext context)
			throws Exception {
		ModifyInstanceTypeResult modifyInstanceTypeResult = new ModifyInstanceTypeResult();
		int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;
		while (true) {

			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return modifyInstanceTypeResult;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("instanceType", targetDepth)) {
					modifyInstanceTypeResult.setInstanceType(VmTypeDetailUnmarshall(context));
					continue;
				}
				
				if (context.testExpression("previousInstanceType", targetDepth)) {
					modifyInstanceTypeResult.setOldInstanceType(VmTypeDetailUnmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return modifyInstanceTypeResult;
				}
			}
		}
	}
	
    private VmTypeDetail VmTypeDetailUnmarshall(StaxUnmarshallerContext context) throws Exception {
    	VmTypeDetail vmType = new VmTypeDetail();
    	int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return vmType;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("name", targetDepth)) {
					vmType.setName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("cpu", targetDepth)) {
					vmType.setCpu(IntegerStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				if (context.testExpression("disk", targetDepth)) {
					vmType.setDisk(IntegerStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				if (context.testExpression("memory", targetDepth)) {
					vmType.setMemory(IntegerStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				if (context.testExpression("availability/item", targetDepth)) {
					vmType.getAvailability().add(ZoneStatusMarshall(context));
					
					continue;
				}

			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return vmType;
				}
			}
		}
	}
    
    private VmTypeZoneStatus ZoneStatusMarshall(StaxUnmarshallerContext context) throws Exception {
    	VmTypeZoneStatus zoneStatus = new VmTypeZoneStatus();
    	int originalDepth = context.getCurrentDepth();
		int targetDepth = originalDepth + 1;

		if (context.isStartOfDocument())
			targetDepth += 1;

		while (true) {
			XMLEvent xmlEvent = context.nextEvent();
			if (xmlEvent.isEndDocument())
				return zoneStatus;

			if (xmlEvent.isAttribute() || xmlEvent.isStartElement()) {
				if (context.testExpression("name", targetDepth)) {
					zoneStatus.setName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				if (context.testExpression("zoneName", targetDepth)) {
					zoneStatus.setZoneName(StringStaxUnmarshaller.getInstance()
							.unmarshall(context));
					continue;
				}
				
				if (context.testExpression("max", targetDepth)) {
					zoneStatus.setMax(IntegerStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				if (context.testExpression("available", targetDepth)) {
					zoneStatus.setAvailable(IntegerStaxUnmarshaller.getInstance().unmarshall(context));
					continue;
				}
				
			} else if (xmlEvent.isEndElement()) {
				if (context.getCurrentDepth() < originalDepth) {
					return zoneStatus;
				}
			}
		}
	}
    
	private static ModifyInstanceTypeResultStaxUnmarshaller instance;
	public static ModifyInstanceTypeResultStaxUnmarshaller getInstance() {
		if (instance == null)
			instance = new ModifyInstanceTypeResultStaxUnmarshaller();
		return instance;
	}

}
