package com.eucalyptus.admin.console.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;


public class EucaGetSessionTokenRequest extends AmazonWebServiceRequest implements Serializable {

    /**
     * The duration, in seconds, that the credentials should remain valid.
     * Acceptable durations for IAM user sessions range from 900 seconds (15
     * minutes) to 129600 seconds (36 hours), with 43200 seconds (12 hours)
     * as the default. Sessions for AWS account owners are restricted to a
     * maximum of 3600 seconds (one hour). If the duration is longer than one
     * hour, the session for AWS account owners defaults to one hour.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>900 - 129600<br/>
     */
    private Integer durationSeconds;
    
    /**
     *  The user
     */
    private String  user;
    /**
     * the account
     */
    private String  account;
    
    /**
     * the password, the user/admin console login profile 
     */
    private String  password;
    
    private String  newPassword;

    /**
     * Default constructor for a new GetSessionTokenRequest object.  Callers should use the
     * setter or fluent setter (with...) methods to initialize this object after creating it.
     */
    public EucaGetSessionTokenRequest() {}
    
    /**
     * The duration, in seconds, that the credentials should remain valid.
     * Acceptable durations for IAM user sessions range from 900 seconds (15
     * minutes) to 129600 seconds (36 hours), with 43200 seconds (12 hours)
     * as the default. Sessions for AWS account owners are restricted to a
     * maximum of 3600 seconds (one hour). If the duration is longer than one
     * hour, the session for AWS account owners defaults to one hour.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>900 - 129600<br/>
     *
     * @return The duration, in seconds, that the credentials should remain valid.
     *         Acceptable durations for IAM user sessions range from 900 seconds (15
     *         minutes) to 129600 seconds (36 hours), with 43200 seconds (12 hours)
     *         as the default. Sessions for AWS account owners are restricted to a
     *         maximum of 3600 seconds (one hour). If the duration is longer than one
     *         hour, the session for AWS account owners defaults to one hour.
     */
    public Integer getDurationSeconds() {
        return durationSeconds;
    }
    
    /**
     * The duration, in seconds, that the credentials should remain valid.
     * Acceptable durations for IAM user sessions range from 900 seconds (15
     * minutes) to 129600 seconds (36 hours), with 43200 seconds (12 hours)
     * as the default. Sessions for AWS account owners are restricted to a
     * maximum of 3600 seconds (one hour). If the duration is longer than one
     * hour, the session for AWS account owners defaults to one hour.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>900 - 129600<br/>
     *
     * @param durationSeconds The duration, in seconds, that the credentials should remain valid.
     *         Acceptable durations for IAM user sessions range from 900 seconds (15
     *         minutes) to 129600 seconds (36 hours), with 43200 seconds (12 hours)
     *         as the default. Sessions for AWS account owners are restricted to a
     *         maximum of 3600 seconds (one hour). If the duration is longer than one
     *         hour, the session for AWS account owners defaults to one hour.
     */
    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }
    
    /**
     * The duration, in seconds, that the credentials should remain valid.
     * Acceptable durations for IAM user sessions range from 900 seconds (15
     * minutes) to 129600 seconds (36 hours), with 43200 seconds (12 hours)
     * as the default. Sessions for AWS account owners are restricted to a
     * maximum of 3600 seconds (one hour). If the duration is longer than one
     * hour, the session for AWS account owners defaults to one hour.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Range: </b>900 - 129600<br/>
     *
     * @param durationSeconds The duration, in seconds, that the credentials should remain valid.
     *         Acceptable durations for IAM user sessions range from 900 seconds (15
     *         minutes) to 129600 seconds (36 hours), with 43200 seconds (12 hours)
     *         as the default. Sessions for AWS account owners are restricted to a
     *         maximum of 3600 seconds (one hour). If the duration is longer than one
     *         hour, the session for AWS account owners defaults to one hour.
     *
     * @return A reference to this updated object so that method calls can be chained 
     *         together.
     */
    public EucaGetSessionTokenRequest withDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
        return this;
    }
    
    public String getUser(){
    	return this.user;
    }
    
    public void setUser(String user) {
    	this.user = user;
    }
    
    public String getAccount() {
    	return this.account;
    }
    
    public void setAccount(String account) {
    	this.account = account;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getNewPassword() {
    	return this.newPassword;
    }
    
    public void setNewPassword(String password) {
    	this.newPassword = password;
    }
    
    
}
    