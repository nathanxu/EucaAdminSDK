package com.eucalyptus.admin.console.model;

import java.util.*;

public class InstanceReport {
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
	Collection<InstanceReportData> reports;
	
	public Date getStartDay() {
		return this.startDay;
	}
    
	public Date getEndDay() {
		return this.endDay;
	}
	
	public Collection<InstanceReportData> getReports() {
		if (this.reports == null) {
			this.reports = new ArrayList<InstanceReportData> ();
		}
		return this.reports;
	}
	
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
    
	public void setEndDay(Date endDay) {
		this.endDay =endDay;
	}
	
	public void setReports(Collection<InstanceReportData> reports) {
		if (reports == null) {
			this.reports = null;
		}
		Collection<InstanceReportData> copys = new ArrayList<InstanceReportData>(reports.size());
		copys.addAll(reports);
		this.reports = copys;
	}
}
