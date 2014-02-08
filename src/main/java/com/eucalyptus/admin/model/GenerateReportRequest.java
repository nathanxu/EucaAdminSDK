package com.eucalyptus.admin.model;

import java.io.Serializable;
import java.util.Date;
import com.amazonaws.AmazonWebServiceRequest;

public class GenerateReportRequest extends AmazonWebServiceRequest implements Serializable {
	
	private Date startDay;
	private Date endDay;
	private String reportType;
	private String reportFormat;
	
	public Date getStartDay() {
		return this.startDay;
	}
    
	public Date getEndDay() {
		return this.endDay;
	}
	
	public String getReportType() {
		return this.reportType;
	}
	
	public String getReportFormat() {
		return this.reportFormat;
	}
	
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
    
	public void setEndDay(Date endDay) {
		this.endDay =endDay;
	}
	
	public void setReportType(String type) {
		this.reportType = type;
	}
	
	public void setReportFormat(String format) {
		this.reportFormat = format;
	}
	
	
}
