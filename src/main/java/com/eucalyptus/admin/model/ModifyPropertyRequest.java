package com.eucalyptus.admin.model;
import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class ModifyPropertyRequest extends AmazonWebServiceRequest implements Serializable {
	
	private String name;
	private Object value;
	private boolean reset = false;
	
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
    
    public boolean getReset() {
        return this.reset;
    }
    
    public void setReset(boolean value) {
        this.reset = value;
    }
    
    public ModifyPropertyRequest withName(String name) {
        this.name = name;
        return this;

    }
    public ModifyPropertyRequest withValue(Object value) {
        this.value = value;
        return this;

    }
    public ModifyPropertyRequest withReset(boolean value) {
        this.reset = value;
        return this;

    }
   
}
