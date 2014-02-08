package com.eucalyptus.admin.console;
import java.util.*;
import net.sf.json.*;

public class EucaConsoleMessage {
	
	private String   api;
	private boolean  status;
	private Integer  errCode;
	private String	 errMessage;
	private Object   data;
	
	/**
	 * get JSON content of this object
	 * @return
	 */
	public JSONObject toJson() {
		
		JSONObject tranObj = new JSONObject();
		
		if(this.api !=null)
			tranObj.put("api", this.api);
		else
			tranObj.put("api", "null");
		
		tranObj.put("status", this.status);
		
		if(this.errCode !=null)
			tranObj.put("errCode", this.errCode);
		else
			tranObj.put("errCode", "null");
		
		if(this.errMessage !=null)
			tranObj.put("errMessage", this.errMessage);
		else
			tranObj.put("errMessage", "null");
		
		if(this.data !=null)
			tranObj.put("data", this.data);
		else
			tranObj.put("data", "null");
		return tranObj;
	}
	/**
	 * print the JSON content to system output
	 */
	public void printMessage() {
		System.out.print(toJson().toString());
	}
	
	/**
	 * get the json object the command output
	 * @return
	 */
	public Object getData(){
		return data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public Integer getErrCode() {
		return this.errCode;
	}
	
	public String getErrMessage() {
		return this.errMessage;
	}
	public String getApi() {
		return this.api;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setErrCode(Integer errCode) {
		this.errCode = errCode;
	}
	
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
	public void setApi(String api) {
		this.api = api;
	}
	
}
