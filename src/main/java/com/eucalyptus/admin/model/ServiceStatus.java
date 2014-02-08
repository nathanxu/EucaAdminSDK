package com.eucalyptus.admin.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ServiceStatus {
    
	//-- ServiceId
	private String uuid;
	private String partition;
	private String name;
	private String type;
	private String fullName;
	private ArrayList<String> uris;
	private String uri;
    // localState
	private String localState;
	private int	   localEpoch;
	
	private ArrayList<String> details;
	//statusDetails
	private ArrayList<ServiceStatusDetail> statusDetails;

	
	public String getUuid() {
		return this.uuid;
	}
	
	public String getPartition() {
		return this.partition;
	}
	
	public String getName() {
		return this.name;
	}
	public String getType() {
		return this.type;
	}
	public String getFullName() {
		return this.fullName;
	}
	public String getUri() {
		return this.uri;
	}
	
	public ArrayList<String> getUris() {
		if (this.uris == null) {
        	 this.uris= new java.util.ArrayList<String>();
        }
		return this.uris;
	}
	
	public ArrayList<String> getDetails() {
		if (this.details == null) {
        	 this.details= new java.util.ArrayList<String>();
        }
		return this.details;
	}
	
	public String getLocalState() {
		return this.localState;
	}
	
	public int getLocalEpoch() {
		return this.localEpoch;
	}
	public ArrayList<ServiceStatusDetail> getStatusDetails(){
		if (this.statusDetails == null) {
			this.statusDetails= new java.util.ArrayList<ServiceStatusDetail>();
        }
		return this.statusDetails;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public void setPartition(String partition) {
		this.partition = partition;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setUris(ArrayList<String> uris) {
		if (uris == null) {
            this.uris = null;
            return;
        }
        java.util.ArrayList<String> urisCopy = new java.util.ArrayList<String>(uris.size());
        urisCopy.addAll(uris);
        this.uris = urisCopy;
	}
	
	public void setLocalState( String localState) {
		this.localState = localState;
	}
	
	public void setLocalEpoch(int localEpoch) {
		this.localEpoch = localEpoch;
	}
	
	public void setDetails(ArrayList<String> details) {
		if (details == null) {
            this.details = null;
            return;
        }
        java.util.ArrayList<String> urisCopy = new java.util.ArrayList<String>(details.size());
        urisCopy.addAll(details);
        this.details = urisCopy;
	}
	
	public void setStatusDetails(ArrayList<ServiceStatusDetail> statusDetails){
		if (statusDetails == null) {
            this.statusDetails = null;
            return;
        }
        java.util.ArrayList<ServiceStatusDetail> statusDetailsCopy = new java.util.ArrayList<ServiceStatusDetail>(statusDetails.size());
        statusDetailsCopy.addAll(statusDetails);
        this.statusDetails = statusDetailsCopy;
	}


}
