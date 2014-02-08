package com.eucalyptus.admin.test;

import java.util.*;
import java.text.SimpleDateFormat;

public class reportDataParse {

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
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d hh:mm:ss z yyyy");
			//Thu Nov 07 00:00:00 HKT 2013
			int index = lines[1].indexOf(":");
			if (index> 0) {
				startDay = sdf.parse(lines[1].substring(index+1));
			}

			index = lines[2].indexOf(":");
			if (index >0 ) {
				endDay = sdf.parse(lines[2].substring(index+1));
			}
			
			data = new ArrayList<String>();
			if (lines.length < 6 )
				return;
			
			for(int i=5;i<lines.length;i++) {
				data.add(lines[i]);
			}
			
		} catch (Exception e) {
		}

	}
	
	public void parseVolumeReport(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("Volume Report"))
			return;
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
			System.out.println("zone:" + zone +" account:" + account + " user: " + user +" vol:" + volId +" size:" + size + " GB_days:" +GBdays );
		}
	}
	
	public void parseS3Report(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("S3 Report"))
			return;

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
			System.out.println("account:" + account + " user: " + user +" bucket:" + bucket +" #:" + count +" total size:" + totalSize + " GB_days:" +GBdays );
		}
	}
	
	public void parseIPReport(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("Elastic IP Report"))
			return;

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
			System.out.println("account:" + account + " user: " + user +" ip:" + ip +" instanceId:" + instanceId  + " days:" +days );
		}
	}
	
	public void parseInstanceReport(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("Instance Report"))
			return;

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
			
			System.out.println("zone:" + zone +" account:" + account + " user: " + user +" instanceId:" + instanceId +" days:" + days + " diskTimeWrite:" +diskTimeWrite );
		}
	}
	
	public void parseCapacityReport(String input) {
		
		parseReportData(input);
		if (!this.reportType.endsWith("Capacity Report"))
			return;
		for (String line:this.data) {
			String[] sets = line.split(",");  
			if (sets.length != 5 ) 
				continue;
			String resource = sets[2];
			int total = Integer.parseInt(sets[3]);
			int available = Integer.parseInt(sets[4]);
			System.out.println("resource:" + resource + " total: " + total +" avail:" + available );
		}
	}
	
	public static void main(final String[] args) throws Exception {
		reportDataParse parser = new reportDataParse();
		
		//parser.parseReportData(args[0]);
		parser.parseS3Report(args[0]);
		parser.parseCapacityReport(args[0]);
		parser.parseInstanceReport(args[0]);
		parser.parseVolumeReport(args[0]);
		parser.parseIPReport(args[0]);
		System.out.println(parser.getStartDay());
		System.out.println(parser.getEndDay());
		//System.out.println(parser.getReportType());

	}
}
