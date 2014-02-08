package com.eucalyptus.admin.console.model;

public class VolumeReportData {
	/**
	 * the account
	 */
	String account;
	
	/**
	 * the user;
	 */
	String user;
	/**
	 * the instance ID 
	 */
	String  instanceId;
	/**
	 * volume Id
	 */
	String volume;
	/**
	 * total No. of object in the bucket
	 */
	Integer totalSize;
	
	/**
	 * total size * Days 
	 */
	Integer GBDays;
	
	public String getAccount() {
		return account;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getInstanceId() {
		return instanceId;
	}
	
	public String getVolume() {
		return volume;
	}
		
	public Integer getTotalSize() {
		return totalSize;
	}
	
	public Integer getGBDays() {
		return GBDays;
	}
	
	public void setAccount(String account) {
		this.account = account ;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setVolume(String volume) {
		this.volume =volume;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
		
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	
	public void setGBDays(Integer GBDays) {
		this.GBDays = GBDays;
	}
}