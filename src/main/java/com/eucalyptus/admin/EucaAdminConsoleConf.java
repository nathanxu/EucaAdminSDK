/**
 * 
 */
package com.eucalyptus.admin;

/**
 * @author Nathan
 *
 */
public class EucaAdminConsoleConf {
	
	private String clcIp;
	private Integer clcPort;
	private String accessKey;
	private String secretKey;
	
	private String clcUser;
	private String clcPassword;
	private String ccDefaultUser;
	private String ccDefaultUserPassword;
	
	public String getClcIp() {
		return this.clcIp;
	}
	
	public void setClcIp(String value) {
		this.clcIp = value;
	}
	
	public Integer getClcPort() {
		return this.clcPort;
	}
	
	public void setClcPort(Integer value) {
		this.clcPort = value;
	}
	
	public String getAccessKey() {
		return this.accessKey;
	}
	
	public void setAccessKey(String value) {
		this.accessKey = value;
	}
	
	public String getSecretKey() {
		return this.secretKey;
	}
	
	public void setSecretKey(String value) {
		this.secretKey = value;
	}
	
	public String getClcUser() {
		return this.clcUser;
	}
	
	public void setClcUser(String value) {
		this.clcUser = value;
	}
	
	public String getClcPassword() {
		return this.clcPassword;
	}
	
	public void setClcPassword(String value) {
		this.clcPassword = value;
	}
	
	public String getCcDefaultUser() {
		return this.ccDefaultUser;
	}
	
	public void setCcDefaultUser(String value) {
		this.ccDefaultUser = value;
	}
	
	public String getCcDefaultUserPassword() {
		return this.ccDefaultUserPassword;
	}
	
	public void setCcDefaultUserPassword(String value) {
		this.ccDefaultUserPassword = value;
	}
}
