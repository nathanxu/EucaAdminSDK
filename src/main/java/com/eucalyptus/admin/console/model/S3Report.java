package com.eucalyptus.admin.console.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class S3Report {
    /**
     * start reporting date
     */
	Date startDay;
	/**
	 * end reporting date
	 */
	Date endDay;
	/**
	 * report data in instance level
	 */
	Collection<S3ReportData> reports;
	
	public Date getStartDay() {
		return this.startDay;
	}
    
	public Date getEndDay() {
		return this.endDay;
	}
	
	public Collection<S3ReportData> getReports() {
		if (this.reports == null) {
			this.reports = new ArrayList<S3ReportData> ();
		}
		return this.reports;
	}
	
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
    
	public void setEndDay(Date endDay) {
		this.endDay =endDay;
	}
	
	public void setReports(Collection<S3ReportData> reports) {
		if (reports == null) {
			this.reports = null;
			return;
		}
		Collection<S3ReportData> copys = new ArrayList<S3ReportData>(reports.size());
		copys.addAll(reports);
		this.reports = copys;
	}
}
