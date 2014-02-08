package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;


public class DescribeServicesRequest extends AmazonWebServiceRequest implements Serializable {
	
	private boolean listAll = false;
	private boolean listInternal =false;
	private boolean listUserServices = false;
	private boolean showEvents = false;
	private boolean showEventStacks = false;
	private String byHost;
	private String byServiceType;
	private String byState;
	private String byPartition;
	private ArrayList<String> serviceNames;
	
	public DescribeServicesRequest() {
		
		this.listAll = false;
		this.listInternal =false;
		this.listUserServices = false;
		this.showEvents = false;
		this.showEventStacks = false;
	}
	
	public boolean getListAll() {
		return this.listAll;
	}
	
	public boolean getListInternal() {
		return this.listInternal;
	}
	
	public boolean getListUserServices() {
		return this.listUserServices;
	}
	
	public boolean getShowEvents() {
		return this.showEvents;
	}
	public boolean getShowEventStacks() {
		return this.showEventStacks;
	}
	
	public String getByHost() {
		return this.byHost;
	}
	
	public String getByServiceType() {
		return this.byServiceType;
	}
	public String getByState() {
		return this.byState;
	}
	public String getByPartition() {
		return this.byPartition;
	}
	
	public ArrayList<String> getServiceNames(){
		if (serviceNames == null) {
			 this.serviceNames = new java.util.ArrayList<String>();
        }
		return this.serviceNames;
        
	}
	
	public void setServiceNames( ArrayList<String> serviceNames){
		if (serviceNames == null) {
			 this.serviceNames = null;
			 return;
        }
		java.util.ArrayList<String> argsCopy = new java.util.ArrayList<String>(serviceNames.size());
        argsCopy.addAll(serviceNames);
        this.serviceNames = argsCopy;
        
	}
	
	public void setListAll(boolean arg) {
		this.listAll = arg;
	}
	
	public void setListInternal(boolean arg) {
		this.listInternal = arg;
	}
	
	public void setLisUserServices(boolean arg) {
		this.listUserServices = arg;
	}
	
	public void setShowEvents(boolean arg) {
		this.showEvents = arg;
	}
	public void setShowEventStacks(boolean arg) {
		this.showEventStacks = arg;
	}
	
	public void setByHost(String arg) {
		this.byHost = arg;
	}
	
	public void setByServiceType(String arg) {
		this.byServiceType = arg;
	}
	public void setByState(String arg) {
		this.byState = arg;
	}
	public void setByPartition(String arg) {
		this.byPartition  = arg;
	}
	
}
