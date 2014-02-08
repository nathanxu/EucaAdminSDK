package com.eucalyptus.admin.console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.eucalyptus.admin.console.model.*;

public class ReportDataUnmarshaller {
	
	private Date startDay = null;
	private Date endDay = null;
	private String reportType = null;
	private ArrayList<String> data;
	
	public Date getStartDay() {
		return this.startDay;
	}
	
	public Date getEndDay() {
		return this.endDay;
	}
	
	public String getReportType() {
		return this.reportType;
	}
	
	private void parseReportData(String input) {
		//reportData = input;
		try {
			String[] lines = input.split("\n");
			if (lines.length < 5)
				return;
			this.reportType = lines[0];
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
			//Thu Nov 07 00:00:00 HKT 2013
			int index = lines[1].indexOf(":");
			if (index> 0) {
				this.startDay = sdf.parse(lines[1].substring(index+1));
			}

			index = lines[2].indexOf(":");
			if (index >0 ) {
				this.endDay = sdf.parse(lines[2].substring(index+1));
			}
			
			this.data = new ArrayList<String>();
			if (lines.length < 6 )
				return;
			
			for(int i=5;i<lines.length;i++) {
				this.data.add(lines[i]);
			}
			
		} catch (ParseException e) {
			System.out.println(e.getMessage());
	    } catch (Exception e) {
		}

	}
	
	public VolumeReport parseVolumeReport(String input) {
		
		parseReportData(input);
		VolumeReport report = new VolumeReport();
		report.setStartDay(this.startDay);
		report.setEndDay(this.endDay);
		if (!this.reportType.endsWith("Volume Report"))
			return null;
		
		String zone = null;
		String account = null;
		String user = null;
		
		for (String line:this.data) {
			String[] sets = line.split(",");  
			if (sets.length != 8 ) 
				continue;
			if (!sets[0].isEmpty()) {
				zone = sets[0].split(":")[1].trim();
				continue;
			}
			if (!sets[1].isEmpty()) {
				account = sets[1].split(":")[1].trim();
				continue;
			}
			if (!sets[2].isEmpty()) {
				user = sets[2].split(":")[1].trim();
				continue;
			}
			String volId = sets[4];
			int size = Integer.parseInt(sets[6]);
			int GBdays = Integer.parseInt(sets[7]);
			
			VolumeReportData reportData = new VolumeReportData();
			reportData.setAccount(account);
			reportData.setUser(user);
			reportData.setVolume(volId);
			reportData.setInstanceId(sets[3]);
			reportData.setTotalSize(size);
			reportData.setGBDays(GBdays);
			report.getReports().add(reportData);
			//System.out.println("zone:" + zone +" account:" + account + " user: " + user +" vol:" + volId +" size:" + size + " GB_days:" +GBdays );
		}
		return report;
	}
	
	public S3Report parseS3Report(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("S3 Report"))
			return null;

		S3Report reports = new S3Report();
		reports.setStartDay(this.startDay);
		reports.setEndDay(this.endDay);
		String account = null;
		String user = null;
		
		for (String line:this.data) {
			String[] sets = line.split(",");  
			if (sets.length != 6 ) 
				continue;
			if (!sets[0].isEmpty()) {
				account = sets[0].split(":")[1].trim();
				continue;
			}
			if (!sets[1].isEmpty()) {
				user = sets[1].split(":")[1].trim();
				continue;
			}
	
			String bucket = sets[2];
			int count = Integer.parseInt(sets[3]);
			int totalSize = Integer.parseInt(sets[4]);
			int GBdays = Integer.parseInt(sets[5]);
			S3ReportData rData = new S3ReportData();
			rData.setAccount(account);
			rData.setUser(user);
			rData.setBucket(bucket);
			rData.setObjects(count);
			rData.setTotalSize(totalSize);
			rData.setGBDays(GBdays);
			reports.getReports().add(rData);
			//System.out.println("account:" + account + " user: " + user +" bucket:" + bucket +" #:" + count +" total size:" + totalSize + " GB_days:" +GBdays );
		}
		return reports;
	}
	
	public IpReport parseIPReport(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("Elastic IP Report"))
			return null;
		IpReport reports = new IpReport();
		reports.setStartDay(this.startDay);
		reports.setEndDay(this.endDay);
		
		String account = null;
		String user = null;
		
		for (String line:this.data) {
			String[] sets = line.split(",");  
			if (sets.length != 6 ) 
				continue;
			if (!sets[0].isEmpty()) {
				account = sets[0].split(":")[1].trim();
				continue;
			}
			if (!sets[1].isEmpty()) {
				user = sets[1].split(":")[1].trim();
				continue;
			}
	
			String ip = sets[2];
			String instanceId = sets[3];
			int days = Integer.parseInt(sets[5]);
			IpReportData rData = new IpReportData();
			rData.setAccount(account);
			rData.setUser(user);
			rData.setInstanceId(instanceId);
			rData.setIp(ip);
			rData.setDays(days);
			reports.getReports().add(rData);
			
			//System.out.println("account:" + account + " user: " + user +" ip:" + ip +" instanceId:" + instanceId  + " days:" +days );
		}
		return reports;
	}
	
	public InstanceReport parseInstanceReport(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("Instance Report"))
			return null;

		InstanceReport reports = new InstanceReport();
		reports.setStartDay(this.startDay);
		reports.setEndDay(this.endDay);
		
		String zone = null;
		String account = null;
		String user = null;
		
		for (String line:this.data) {
			String[] sets = line.split(",");  
			if (sets.length != 18 ) 
				continue;
			if (!sets[0].isEmpty()) {
				zone = sets[0].split(":")[1].trim();
				continue;
			}
			if (!sets[1].isEmpty()) {
				account = sets[1].split(":")[1].trim();
				continue;
			}
			if (!sets[2].isEmpty()) {
				user = sets[2].split(":")[1].trim();
				continue;
			}
			if (sets[3].equals("InstanceId")) //ignore the subtitle line
				continue;
			
			String instanceId = sets[3];
			int days = Integer.parseInt(sets[6]);
			Double cpuUsage = Double.parseDouble(sets[7]);
			Double netTotalIn= Double.parseDouble(sets[8]);
			Double netTotalOut = Double.parseDouble(sets[9]);
			Double netExternalIn = Double.parseDouble(sets[10]);
			Double netExternalOut = Double.parseDouble(sets[11]);
			Double diskRead = Double.parseDouble(sets[12]);
			Double diskWrite = Double.parseDouble(sets[13]);
			Double diskIOPSRead = Double.parseDouble(sets[14]);
			Double diskIOPSWrite = Double.parseDouble(sets[15]);
			Double diskTimeRead = Double.parseDouble(sets[16]);
			Double diskTimeWrite = Double.parseDouble(sets[17]); 
			InstanceReportData rData = new InstanceReportData();
			rData.setAccount(account);
			rData.setUser(user);
			rData.setInstanceId(instanceId);
			rData.setDays(days);
			rData.setCpuUsage(cpuUsage);
			rData.setNetTotalIn(netTotalIn);
			rData.setNetTotalOut(netTotalOut);
			rData.setNetExternalIn(netExternalIn);
			rData.setNetExternalOut(netExternalOut);
			rData.setDiskRead(diskRead);
			rData.setDiskWrite(diskWrite);
			rData.setDiskIOPSRead(diskIOPSRead);
			rData.setDiskIOPSWrite(diskIOPSWrite);
			rData.setDiskTimeRead(diskTimeRead);
			rData.setDiskTimeWrite(diskTimeWrite);
			reports.getReports().add(rData);
			//System.out.println("zone:" + zone +" account:" + account + " user: " + user +" instanceId:" + instanceId +" days:" + days + " diskTimeWrite:" +diskTimeWrite );
		}
		return reports;
	}
	
	public CapacityReport parseCapacityReport(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("Capacity Report"))
			return null;
		
		CapacityReport reports = new CapacityReport();
		reports.setStartDay(this.startDay);
		reports.setEndDay(this.endDay);
		
		for (String line:this.data) {
			String[] sets = line.split(",");  
			if (sets.length != 5 ) 
				continue;
			String resource = sets[2];
			int total = Integer.parseInt(sets[4]);
			int available = Integer.parseInt(sets[3]);
			CapacityReportData rData = new CapacityReportData();
			rData.setResource(resource);
			rData.setTotal(total);
			rData.setAvailable(available);
			reports.getReports().add(rData);
			//System.out.println("resource:" + resource + " total: " + total +" avail:" + available );
		}
		return reports;
	}
}
