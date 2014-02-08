package com.eucalyptus.admin.model;

import java.io.Serializable;

public class CloudProperty implements Serializable {

	private String name;
	private Object value;
	private String description;
	
	public String getName() {
		return this.name;
	}
	
	public Object getValue() {
		return this.value;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setValue(Object value) {
		this.value=value;
	}
	
	public void setDescription(String desc) {
		this.description=desc;
	}
	
	public CloudProperty withName(String name) {
        this.name = name;
        return this;
    }
	
	public CloudProperty withValue(Object value) {
        this.value = value;
        return this;
    }
	public CloudProperty withDescription(String desc) {
        this.description = desc;
        return this;
    }
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if (getName() != null)
			sb.append("Name: " + getName() + ",");
		if (getValue() != null)
			sb.append("Value: " + getValue() + ",");
		
		sb.append("}");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hashCode = 1;

		hashCode = prime * hashCode
				+ ((getName() == null) ? 0 : getName().hashCode());
		hashCode = prime * hashCode
				+ ((getValue() == null) ? 0 : getValue().hashCode());
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (obj instanceof CloudProperty == false)
			return false;
		CloudProperty other = (CloudProperty) obj;

		if (other.getName() == null ^ this.getName() == null)
			return false;
		if (other.getName() != null
				&& other.getName().equals(this.getName()) == false)
			return false;
		if (other.getValue() == null ^ this.getValue() == null)
			return false;
		if (other.getValue() != null
				&& other.getValue().equals(this.getValue()) == false)
			return false;
		
		return true;
	}

}
