package com.eucalyptus.admin.console.model;

public class IpReportData {

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
	 * public Ip
	 */
	String ip;
	
	/**
	 * total used days 
	 */
	Integer days;
	
	public String getAccount() {
		return account;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getInstanceId() {
		return instanceId;
	}
	
	public String getIp() {
		return ip;
	}
		
	
	public Integer getDays() {
		return days;
	}
	
	public void setAccount(String account) {
		this.account = account ;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setIp(String ip) {
		this.ip =ip;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
		
	public void setDays(Integer days) {
		this.days = days;
	}
}
