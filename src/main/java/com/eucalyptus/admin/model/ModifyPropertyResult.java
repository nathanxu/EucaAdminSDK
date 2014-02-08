package com.eucalyptus.admin.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class ModifyPropertyResult extends AmazonWebServiceRequest implements Serializable  {
	
	private String name;
	private Object value;
	private Object oldValue;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Object getValue() {
        return this.value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }
    
    public Object getOldValue() {
        return this.oldValue;
    }
    
    public void setOldValue(Object value) {
        this.oldValue = value;
    }
    
    public ModifyPropertyResult withName(String name) {
        this.name = name;
        return this;

    }
    public ModifyPropertyResult withValue(Object value) {
        this.value = value;
        return this;

    }
    public ModifyPropertyResult withOldValue(Object value) {
        this.oldValue = value;
        return this;

    }
   
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (name != null) sb.append("name: " + this.name +",");
        if (value != null) sb.append("value: " + this.value +",");
        if (oldValue != null) sb.append("oldValue: " + this.oldValue);
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((name == null) ? 0 : name.hashCode()); 
        hashCode = prime * hashCode + ((value == null) ? 0 : value.hashCode()); 
        hashCode = prime * hashCode + ((oldValue == null) ? 0 : oldValue.hashCode()); 
        return hashCode;
    }
    
}
