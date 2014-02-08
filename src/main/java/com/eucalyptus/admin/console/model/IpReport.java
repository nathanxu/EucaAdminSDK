package com.eucalyptus.admin.console.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class IpReport {
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
	Collection<IpReportData> reports;
	
	public Date getStartDay() {
		return this.startDay;
	}
    
	public Date getEndDay() {
		return this.endDay;
	}
	
	public Collection<IpReportData> getReports() {
		if (this.reports == null) {
			this.reports = new ArrayList<IpReportData> ();
		}
		return this.reports;
	}
	
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
    
	public void setEndDay(Date endDay) {
		this.endDay =endDay;
	}
	
	public void setReports(Collection<IpReportData> reports) {
		if (reports == null) {
			this.reports = null;
			return;
		}
		Collection<IpReportData> copys = new ArrayList<IpReportData>(reports.size());
		copys.addAll(reports);
		this.reports = copys;
	}
}
