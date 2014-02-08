package com.eucalyptus.admin.console.model;

public class DashBoardData {
	//Account/User
	Integer accounts;
	Integer users;
	Integer groups;
	
	//Usage/Capacity
	Integer totalDisk;
	Integer availableDisk;
	
	Integer totalMem;
	Integer availableMem;
	
	Integer totalCpu;
	Integer availableCpu;
	
    //Instance
	Integer runInstances;
	Integer stoppedInstances;
	Integer penddingInstances;
	
	//public Ip
	Integer totalIp;
	Integer availableIp;
	
	//ebs
	Integer createdVolumes;
	Integer attachedVolumes;
	
	public Integer getAccounts(){
		return this.accounts;
	}
	
	public Integer getUsers(){
		return this.users;
	}
	
	public Integer getGroups(){
		return this.groups;
	}
	
	public Integer getTotalDisk(){
		return this.totalDisk;
	}
	
	public Integer getAvailableDisk(){
		return this.availableDisk;
	}
	
	public Integer getTotalCpu(){
		return this.totalCpu;
	}
	
	public Integer getAvailableCpu(){
		return this.availableCpu;
	}
	
	public Integer getTotalMem(){
		return this.totalMem;
	}
	
	public Integer getAvailableMem(){
		return this.availableMem;
	}
	
	public Integer getRunInstances(){
		return this.runInstances;
	}
	
	public Integer getStoppedInstances(){
		return this.stoppedInstances;
	}
	
	public Integer getPenddingInstances(){
		return this.penddingInstances;
	}
	
	public Integer getTotalIp(){
		return this.totalIp;
	}
	
	public Integer getAvailableIp(){
		return this.availableIp;
	}
	
	public Integer getCreatedVolumes(){
		return this.createdVolumes;
	}
	
	public Integer getAttachedVolumes(){
		return this.attachedVolumes;
	}
	
	public void setAccounts(Integer accounts){
		this.accounts = accounts;
	}
	
	public void setUsers(Integer users){
		this.users = users;
	}
	
	public void setGroups(Integer groups){
		this.groups = groups;
	}
	
	public void setTotalDisk(Integer totalDisk){
		this.totalDisk = totalDisk;
	}
	
	public void setAvailableDisk(Integer availableDisk){
		this.availableDisk = availableDisk;
	}
	
	public void setTotalCpu(Integer totalCpu){
		this.totalCpu = totalCpu;
	}
	
	public void setAvailableCpu(Integer availableCpu){
		this.availableCpu = availableCpu;
	}
	
	public void setTotalMem(Integer totalMem){
		this.totalMem = totalMem;
	}
	
	public void setAvailableMem(Integer availableMem){
		this.availableMem = availableMem;
	}
	
	public void setRunInstances(Integer runInstances){
		this.runInstances = runInstances;
	}
	
	public void setStoppedInstances(Integer stoppedInstances){
		this.stoppedInstances = stoppedInstances;
	}
	
	public void setPenddingInstances(Integer penddingInstances){
		this.penddingInstances = penddingInstances;
	}
	
	public void setTotalIp(Integer totalIp){
		this.totalIp = totalIp;
	}
	
	public void setAvailableIp(Integer availableIp){
		this.availableIp = availableIp;
	}
	
	public void setCreatedVolumes(Integer createdVolumes){
		this.createdVolumes = createdVolumes;
	}
	
	public void setAttachedVolumes(Integer attachedVolumes){
		this.attachedVolumes = attachedVolumes;
	}
}
