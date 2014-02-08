package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;

public class DescribeComponentsResult extends AmazonWebServiceRequest implements Serializable {
	private ArrayList<ComponentInfo> components;
	
	public java.util.ArrayList<ComponentInfo> getComponents() {
        
        if (components == null) {
        	components = new java.util.ArrayList<ComponentInfo>();
        }
        return components;
    }
    
 
    public void setComponents(java.util.ArrayList<ComponentInfo> components) {
        if (components == null) {
            this.components = null;
            return;
        }
        java.util.ArrayList<ComponentInfo> componentsCopy = new java.util.ArrayList<ComponentInfo>(components.size());
        componentsCopy.addAll(components);
        this.components = componentsCopy;
    }
}