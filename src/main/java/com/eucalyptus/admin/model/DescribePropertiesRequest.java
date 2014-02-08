package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.Request;
import com.eucalyptus.admin.model.*;
import java.io.Serializable;
import com.amazonaws.internal.*;

public class DescribePropertiesRequest extends AmazonWebServiceRequest implements Serializable {
	
	private java.util.ArrayList<String> Properties;
	
    public java.util.ArrayList<String> getProperties() {
        
        if (Properties == null) {
        	Properties = new java.util.ArrayList<String>();
        }
        return Properties;
    }
    
 
    public void setProperties(java.util.Collection<String> Properties) {
        if (Properties == null) {
            this.Properties = null;
            return;
        }
        java.util.ArrayList<String> PropertiesCopy = new java.util.ArrayList<String>(Properties.size());
        PropertiesCopy.addAll(Properties);
        this.Properties = PropertiesCopy;
    }
    
    public DescribePropertiesRequest withProperties(String... Properties) {
        if (getProperties() == null) setProperties(new java.util.ArrayList<String>(Properties.length));
        for (String value : Properties) {
            getProperties().add(value);
        }
        return this;
    }
    
    public DescribePropertiesRequest withPublicIps(java.util.Collection<String> Properties) {
        if (Properties == null) {
            this.Properties = null;
        } else {
            java.util.ArrayList<String> PropertiesCopy = new java.util.ArrayList<String>(Properties.size());
            PropertiesCopy.addAll(Properties);
            this.Properties = PropertiesCopy;
        }

        return this;
    }
	
	
}
