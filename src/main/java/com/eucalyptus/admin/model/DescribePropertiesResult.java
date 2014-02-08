package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.Request;
import java.io.Serializable;


public class DescribePropertiesResult extends AmazonWebServiceRequest implements Serializable {

    private java.util.ArrayList<CloudProperty> properties;
    
    public java.util.ArrayList<CloudProperty> getProperties() {
        
        if (properties == null) {
        	properties = new java.util.ArrayList<CloudProperty>();
        }
        return properties;
    }
    
    public void setProperties(java.util.Collection<CloudProperty> Properties) {
    	if (Properties == null) {
            this.properties = null;
            return;
        }
        java.util.ArrayList<CloudProperty> PropertiesCopy = new java.util.ArrayList<CloudProperty>(Properties.size());
        PropertiesCopy.addAll(Properties);
        this.properties = PropertiesCopy;
    }
    
   
    
    public DescribePropertiesResult withProperties(java.util.Collection<CloudProperty> Properties) {
    	if (Properties == null) {
            this.properties = null;
        } else {
            java.util.ArrayList<CloudProperty> PropertiesCopy = new java.util.ArrayList<CloudProperty>(Properties.size());
            PropertiesCopy.addAll(Properties);
            this.properties = PropertiesCopy;
        }
        return this;

    }
    
   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getProperties() != null) sb.append("Properties: " + getProperties() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((getProperties() == null) ? 0 : getProperties().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof DescribePropertiesResult == false) return false;
        DescribePropertiesResult other = (DescribePropertiesResult)obj;
        
        if (other.getProperties() == null ^ this.getProperties() == null) return false;
        if (other.getProperties() != null && other.getProperties().equals(this.getProperties()) == false) return false; 
       
        return true;
    }
    
}
    
