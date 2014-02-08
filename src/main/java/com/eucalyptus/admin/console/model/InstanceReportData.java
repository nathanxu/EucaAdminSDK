package com.eucalyptus.admin.console.model;

public class InstanceReportData {
	
	/**
	 *  the zone of the instance
	 */
	String partition;
	
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
	 * total used days
	 */
	Integer days;
	
	Double cpuUsage;
	
	/**
	 *  All network incoming traffic (GB)
	 */
	Double netTotalIn;
	
	/**
	 * All network outgoing traffic (GB)
	 */
	Double netTotalOut;
	
	/**
	 * total public network incoming traffic (GB)
	 */
	Double netExternalIn;
	
	/**
	 *  total public network outgoing traffic (GB)
	 */
	Double netExternalOut;
	
	/**
	 *  total bytes of disk read (GB)
	 */
	Double diskRead;
	
	/**
	 *  total bytes of disk write (GB)
	 */
	Double diskWrite;
	
	/**
	 * IOPS of read (M)
	 */
	Double diskIOPSRead;
	/**
	 * IOPS of write (M)
	 */
	Double diskIOPSWrite;
	/**
	 * total disk read time (hr)
	 */
	Double diskTimeRead;
	
	/**
	 *  total disk write time (hr)
	 */
	Double diskTimeWrite;
	
	public String getPartition() {
		return partition;
	}
	
	public String getAccount() {
		return account;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getInstanceId() {
		return instanceId;
	}
	
	public Integer getDays() {
		return days;
	}
	
	public Double getCpuUsage() {
		return this.cpuUsage;
	}
	
	public Double getNetTotalIn() {
		return this.netTotalIn;
	}
	
	public Double getNetTotalOut() {
		return this.netTotalOut;
	}
	
	public Double getNetExternalIn() {
		return this.netExternalIn;
	}
	
	public Double getNetExternalOut() {
		return this.netExternalOut;
	}
	
	public Double getDiskRead() {
		return this.diskRead;
	}
	
	public Double getDiskWrite() {
		return this.diskWrite;
	}
	
	public Double getDiskIOPSWrite() {
		return this.diskIOPSWrite;
	}

	public Double getDiskIOPSRead() {
		return this.diskIOPSRead;
	}
	
	public Double getDiskTimeRead() {
		return this.diskTimeRead;
	}
	
	public Double getDiskTimeWrite() {
		return this.diskTimeWrite;
	}

	public void setDiskTimeWrite(Double diskTimeWrite) {
		this.diskTimeWrite = diskTimeWrite;
	}
	
	public void setDiskTimeRead(Double diskTimeRead) {
		this.diskTimeRead = diskTimeRead;
	}
	
	public void setDiskIOPSWrite(Double diskIOPSWrite) {
		this.diskIOPSWrite = diskIOPSWrite;
	}
	
	public void setDiskIOPSRead(Double diskIOPSRead) {
		this.diskIOPSRead = diskIOPSRead;
	}
	
	public void setDiskWrite(Double diskWrite) {
		this.diskWrite = diskWrite;
	}
	
	public void setDiskRead(Double diskRead) {
		this.diskRead = diskRead;
	}
	
	public void setNetTotalIn(Double netTotalIn) {
		this.netTotalIn = netTotalIn;
	}
	
	public void setNetTotalOut(Double netTotalOut) {
		this.netTotalOut = netTotalOut;
	}
	
	public void setNetExternalIn(Double netExternalIn) {
		this.netExternalIn = netExternalIn;
	}
	
	public void setNetExternalOut(Double netExternalOut) {
		this.netExternalOut = netExternalOut;
	}
	
	public void setCpuUsage (Double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	public void setPartition(String partition) {
		this.partition = partition;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
}