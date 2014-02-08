package com.eucalyptus.admin.model;

import java.io.Serializable;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;

public class DescribeServicesResult  extends AmazonWebServiceRequest implements Serializable {
	
	private java.util.ArrayList<ServiceStatus> services;
    
    public java.util.ArrayList<ServiceStatus> getServices() {
        
        if (services == null) {
        	services = new java.util.ArrayList<ServiceStatus>();
        }
        return services;
    }
    
    public void setServices(java.util.Collection<ServiceStatus> services) {
    	if (services == null) {
            this.services = null;
            return;
        }
        java.util.ArrayList<ServiceStatus> servicesCopy = new java.util.ArrayList<ServiceStatus>(services.size());
        servicesCopy.addAll(services);
        this.services = servicesCopy;
    }
    
   
    
    public DescribeServicesResult withServices(java.util.Collection<ServiceStatus> services) {
    	if (services == null) {
            this.services = null;
        } else {
            java.util.ArrayList<ServiceStatus> servicesCopy = new java.util.ArrayList<ServiceStatus>(services.size());
            servicesCopy.addAll(services);
            this.services = servicesCopy;
        }
        return this;

    }
}
