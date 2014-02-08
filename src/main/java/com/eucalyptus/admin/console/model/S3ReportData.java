package com.eucalyptus.admin.console.model;

public class S3ReportData {
	/**
	 * the account
	 */
	String account;
	
	/**
	 * the user;
	 */
	String user;
	
	/**
	 * bucket
	 */
	String bucket;
	/**
	 * total No. of object in the bucket
	 */
	Integer objects;
	/**
	 * total size of objects (GB)
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
	
	public String getBucket() {
		return bucket;
	}
	
	public Integer getObjects() {
		return objects;
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
	
	public void setBucket(String bucket) {
		this.bucket =bucket;
	}
	
	public void setObjects(Integer objects) {
		this.objects = objects;
	}
	
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}
	
	public void setGBDays(Integer GBDays) {
		this.GBDays = GBDays;
	}
}
