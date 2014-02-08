package com.eucalyptus.admin.console.model;
import java.util.Collection;
import com.eucalyptus.admin.model.*;

public class ComponentsStatus {
	
	Collection<ComponentInfo> components;
	
	public Collection<ComponentInfo> getComponents() {
		if (this.components == null) {
			this.components = new java.util.ArrayList<ComponentInfo>();
		}
		return this.components;
	}
	
	public void setComponents(Collection<ComponentInfo> components) {
		if (components == null) {
			this.components = null;
			return;
		}
		Collection<ComponentInfo> copy = new java.util.ArrayList<ComponentInfo>(components.size());
        copy.addAll(components);
        this.components = copy;
	}
}
