package com.eucalyptus.admin.console.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CapacityReport {
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
	Collection<CapacityReportData> reports;
	
	public Date getStartDay() {
		return this.startDay;
	}
    
	public Date getEndDay() {
		return this.endDay;
	}
	
	public Collection<CapacityReportData> getReports() {
		if (this.reports == null) {
			this.reports = new ArrayList<CapacityReportData> ();
		}
		return this.reports;
	}
	
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
    
	public void setEndDay(Date endDay) {
		this.endDay =endDay;
	}
	
	public void setReports(Collection<CapacityReportData> reports) {
		if (reports == null) {
			this.reports = null;
			return;
		}
		Collection<CapacityReportData> copys = new ArrayList<CapacityReportData>(reports.size());
		copys.addAll(reports);
		this.reports = copys;
	}
}
