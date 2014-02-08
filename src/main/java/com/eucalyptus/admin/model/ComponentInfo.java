package com.eucalyptus.admin.model;

public class ComponentInfo {
	
	private String type;
	private String partition;
	private String name;
	private String hostName;
	private String fullName;
	private String state;
	private String detail;
		
	
	public String getType(){
		return this.type;
	}
	public void setType(String type){
		this.type = type;
	}
	
	public String getPartition(){
		return this.partition;
	}
	public void setPartition(String partition){
		this.partition = partition;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getHostName(){
		return this.hostName;
	}
	
	public void setHostName(String hostName){
		this.hostName = hostName;
	}
	
	public String getFullName(){
		return this.fullName;
	}
	public void setFullName(String fullName){
		this.fullName = fullName;
	}
	
	public String getState(){
		return this.state;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getDetail(){
		return this.detail;
	}
	public void setDetail(String detail){
		this.detail = detail;
	}
	
}
