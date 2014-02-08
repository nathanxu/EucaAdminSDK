package com.eucalyptus.admin.console.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class VolumeReport {
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
	Collection<VolumeReportData> reports;
	
	public Date getStartDay() {
		return this.startDay;
	}
    
	public Date getEndDay() {
		return this.endDay;
	}
	
	public Collection<VolumeReportData> getReports() {
		if (this.reports == null) {
			this.reports = new ArrayList<VolumeReportData> ();
		}
		return this.reports;
	}
	
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
    
	public void setEndDay(Date endDay) {
		this.endDay =endDay;
	}
	
	public void setReports(Collection<VolumeReportData> reports) {
		if (reports == null) {
			this.reports = null;
			return;
		}
		Collection<VolumeReportData> copys = new ArrayList<VolumeReportData>(reports.size());
		copys.addAll(reports);
		this.reports = copys;
	}
}
