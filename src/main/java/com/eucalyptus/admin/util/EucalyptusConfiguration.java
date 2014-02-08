/**
 * 
 */
package com.eucalyptus.admin.util;

import java.util.*;
import java.io.*;


public class EucalyptusConfiguration {
	private Map<String,String> properties;
	private String file = null;
	private ArrayList<String> lines;
	private Map<String,Integer> positionIndex;
	
	public EucalyptusConfiguration() {
		properties = null;
		file = null;
		lines = null;
		positionIndex = null;
	}

	public EucalyptusConfiguration(String file) {
	
		load(file);
	}

	public void load(String file) {

		try {
			
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			lines = new ArrayList<String>() ;
			positionIndex = new HashMap<String,Integer>();
			properties = new HashMap<String,String>();	
			String line;
			int lineIndex = 0;
			while ((line=br.readLine())!=null) {
	            lines.add(line);
	            //get property line
	            if (!line.startsWith("#") && line.indexOf("=") > 0) {
	            	int keyLen = line.indexOf("=");
	            	String key = line.substring(0,keyLen).trim().toUpperCase();
	            	String value ="";
	            	if (line.length()> (keyLen +1)) {
	            		value = line.substring(keyLen + 1);
	            	}
	            	positionIndex.put(key, lineIndex);
	            	properties.put(key, value);
	            } 
	            // get commented line
	            if (line.startsWith("#") && line.indexOf("=") > 1) {
	            	int keyLen = line.indexOf("=");
	            	String key = line.substring(1,keyLen).trim().toUpperCase();
	            	//System.out.println("key:" + key + " value:" + value);
	            	if (!positionIndex.containsKey(key))
	            		positionIndex.put(key, lineIndex);
	            } 
	            
	            lineIndex +=1;
	        }
			//System.out.println(lineIndex);
			this.file = file;
	        br.close();
	        fr.close();
		} catch (FileNotFoundException ex) {
			properties = null;
			this.file = null;
		} catch (IOException ex) {
			properties = null;
			this.file = null;
		}

	}

	public String getValue(String key) {

		if (properties!= null && properties.containsKey(key.toUpperCase())) {
			String oldvalue = properties.get(key.toUpperCase());
			//delete \"
			String value = oldvalue.trim();
			if (value.charAt(0) =='\"' && value.charAt(value.length()-1) == '\"') {
				return value.substring(1, value.length()-1);
			} else {
				return value;
			}
		} else {
			return "";
		}
	}

	public void lear() {
		if ( properties!= null )
			properties.clear();
	}
	
	public void remove(String key) {
		
		String keyUpper = key.toUpperCase();
		if (properties!= null ) 
			properties.remove(keyUpper);
		
		if(positionIndex.containsKey(keyUpper)){ //replace the old value
			int index = positionIndex.get(keyUpper);
			String newValue = "#" + lines.get(index);
			lines.set(index,newValue);	
		}
	}

	public void setValue(String key, String value,String comments) {
		
		String keyUpper = key.toUpperCase();
		String newValue = keyUpper +"=\""+value + "\"";
		if (properties!= null ) 
			properties.put(keyUpper, value);
		
		if(positionIndex.containsKey(keyUpper)){ //replace the old value
			lines.set(positionIndex.get(keyUpper),newValue);	
		} else  {
			if ( comments != null )
				lines.add("#" + comments);
			lines.add(newValue);
			positionIndex.put(keyUpper, lines.size() -1);
		} 
		
		
	}

	public void save() throws FileNotFoundException, IOException {
		if (file==null)
			return;
		//FileOutputStream outputFile = new FileOutputStream(this.file);
		FileWriter fw=new FileWriter(new File(this.file));
        BufferedWriter  bw=new BufferedWriter(fw);
        for(String line:this.lines) {
        	bw.write(line +"\n");
        }
        fw.close();        
	}
	
	public void save(String file) throws FileNotFoundException, IOException {
		if (file==null)
			return;
		FileWriter fw=new FileWriter(new File(file));
        BufferedWriter  bw=new BufferedWriter(fw);
        for(String line:this.lines) {
        	bw.write(line +"\n");
        }
        bw.close();
        fw.close();        
	}
}
