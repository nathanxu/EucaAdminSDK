package com.eucalyptus.admin;

import java.util.*;

import com.eucalyptus.admin.console.JSONMessage;
import com.eucalyptus.admin.console.EucaConsoleMessage;
import com.eucalyptus.admin.console.ReportDataUnmarshaller;
import com.eucalyptus.admin.util.*;

import com.eucalyptus.admin.model.*;
import com.eucalyptus.admin.console.model.*;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;


import com.amazonaws.services.identitymanagement.model.*;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EucaAdminConsoleImpl implements EucaAdminConsoleApi {
	private String clcIp;
	private Integer port;
	private String accessKey;
	private String secretKey;
	
	private String clcUser;
	private String clcPassword;
	private String ccDefaultUser;
	private String ccDefaultUserPassword;

	private BasicAWSCredentials creds;

	
	private String cloudUri(final String servicePath) {
		return URI.create("http://" + this.clcIp + ":" + this.port + "/")
				.resolve(servicePath).toString();
	}

	private EucaAdminClient getEucaAdminClient() {

		final EucaAdminClient euca = new EucaAdminClient(this.creds);
		// EucaAdminClient euca = new EucaAdminClient(provider);
		euca.setEndpoint(cloudUri("/services/Properties/"));
		return euca;
	}

	private AmazonEC2 getEc2Client() {
		final AmazonEC2 ec2 = new AmazonEC2Client(this.creds);
		ec2.setEndpoint(cloudUri("/services/Eucalyptus/"));
		return ec2;
	}

	private EucaIAMClient getIAMClient() {
		EucaIAMClient iam = new EucaIAMClient(this.creds);
		iam.setEndpoint(cloudUri("/services/Euare/"));
		return iam;
	}
	
	private Map<String, String> getAllCluster(boolean status) {
		// get the active CC hosts
		Map<String, String> clusters = new HashMap<String, String>();
		String clusterHost = null;
		EucaAdminClient adminClient = getEucaAdminClient();
		adminClient.setEndpoint(cloudUri("/services/Empyrean/"));
		DescribeServicesRequest request = new DescribeServicesRequest();
		request.setByServiceType("cluster");
		if(status)
			request.setByState("enabled");
		DescribeServicesResult res = adminClient.describeServices(request);
		ArrayList<ServiceStatus> services = res.getServices();
		for (ServiceStatus service : services) {
			String url = service.getUri();
			if (url.startsWith("http://")) {
				clusterHost = url.substring(7, url.indexOf(":", 7));
			} else {
				clusterHost = url;
			}
			clusters.put(service.getPartition(), clusterHost);
		}
		return clusters;

	}
	
	private void syncKeyFiles(String registerType,String partition, String registerHost,String user,String password) throws IOException {
		
		Ssh2Client clcClient = new Ssh2Client(clcIp,clcUser,clcPassword);
		boolean ret = clcClient.connect();
		if (!ret) {
			throw new IOException("Can't connect to clc server to sync key files");
		}
		
		String tempDir = "/tmp/" + Tools.getRandomString(18) + "/";
		
		File dir = new File(tempDir);
		if(dir != null)
			dir.mkdir();
		else
			throw new IOException("Can't make temporary directory to donwload key files from clc server");
		
		String[] remoteFiles = null;
		String keyDir = "/var/lib/eucalyptus/keys/";
		
		if(registerType.equals("cloud")) {
			remoteFiles = new String[]{keyDir + "euca.p12", keyDir +"cloud-cert.pem", keyDir +"cloud-pk.pem"};
		}
		String clusterKeyDir = keyDir + partition +"/";
		if(registerType.equals("cluster")) {
			remoteFiles = new String[]{clusterKeyDir + "node-cert.pem", clusterKeyDir +"cluster-cert.pem", clusterKeyDir +"cluster-pk.pem" 
					                  ,clusterKeyDir + "vtunpass", clusterKeyDir + "node-pk.pem"};
		}
		if(registerType.equals("storage") || registerType.equals("walrus")) {
			remoteFiles = new String[]{keyDir + "euca.p12" };
		}
		
		if(registerType.equals("node") ) {
			remoteFiles = new String[]{clusterKeyDir + "node-cert.pem", clusterKeyDir +"cluster-cert.pem", clusterKeyDir +"cluster-pk.pem" 
	                  , clusterKeyDir + "node-pk.pem"};
		}

		Collection<String> donwloadFiles;
		try {
		donwloadFiles= clcClient.downLoadFiles(remoteFiles, tempDir);
		} catch (IOException e) {
			clcClient.close();
			Tools.deleteDir(dir);
			throw new IOException("Can't download key files from clc:" + e.toString());
		}
		
		if ( donwloadFiles == null  || donwloadFiles.size()==0) {
			clcClient.close();
			Tools.deleteDir(dir);
			throw new IOException("Can't download key files from clc");
		}
		clcClient.close();
		//upload the key files to register server
		Ssh2Client registerClient = new Ssh2Client(registerHost,user,password);
		ret = registerClient.connect();
		if (!ret) {
			throw new IOException("Can't connect to register server to sync key files");
		}
		try {
			registerClient.upLoadFiles(donwloadFiles.toArray(new String[0]), keyDir);
		} catch (IOException e) {
			registerClient.close();
			Tools.deleteDir(dir);
			throw new IOException("Can't upload key files to register server:" + e.toString());
		}
		registerClient.close();
		Tools.deleteDir(dir);
	}
	
	private void setRemoteConfiguration(Map<String,String[]> properties,String server,String user,String password) throws IOException {
		RemoteEucalyptusConfiguration conf = new RemoteEucalyptusConfiguration();
		conf.load(server, user, password);
		Set<String> key = properties.keySet();
        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            String k = (String) it.next();
            String action = properties.get(k)[0];
            String v = properties.get(k)[1];
            if (action.equals("r")) //replace
            	conf.setValue(k, v, null);
            if (action.equals("a")) {//append
            	String oldv = conf.getValue(k);
            	if ( oldv.charAt(0) =='\"' && oldv.charAt(oldv.length() -1) == '\"') 
            		conf.setValue(k, oldv.substring(1,oldv.length() -1) + v, null);
            	else
            		conf.setValue(k, oldv + v, null);	
            	
            }
            if (action.equals("d")) {//delete;
            	String oldv = conf.getValue(k);
            	int i = oldv.indexOf(v);
            	int li = v.length();
            	if(i>-1) {
            		conf.setValue(k,oldv.substring(0,i) + oldv.substring(i+li),null);
            	}
            }
        }
		conf.save(server, user, password);
	}
	/**
	 * query CloudProperties
	 * 
	 * @param properties
	 * @return eucaConsoleMessage
	 */
	private DescribePropertiesResult getProperties(ArrayList<String> properties) {
		
		DescribePropertiesResult result;
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			
			if (properties != null && properties.size() > 0) {
				DescribePropertiesRequest request = new DescribePropertiesRequest();
				request.setProperties(properties);
				result = adminClient.describeProperties(request);
			} else {
				result = adminClient.describeProperties();
			}
		} catch (AmazonServiceException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
		return result;
	}
	public void setup(EucaAdminConsoleConf config) {
		this.clcIp = config.getClcIp();
		this.port = config.getClcPort();
		this.accessKey = config.getAccessKey();
		this.secretKey = config.getSecretKey();
		this.creds = new BasicAWSCredentials(this.accessKey, this.secretKey);
		
		this.clcUser = config.getClcUser();
		this.clcPassword = config.getClcPassword();
		this.ccDefaultUser = config.getCcDefaultUser();
		this.ccDefaultUserPassword = config.getCcDefaultUserPassword();
	}
	public void configure(String Ip, Integer port, String accessKey,
			String secretKey) {
		this.clcIp = Ip;
		this.port = port;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.creds = new BasicAWSCredentials(this.accessKey, this.secretKey);

	}

	public EucaConsoleMessage login(String account, String user, String password) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("login",
				true, null, 0);
		// Date now = ;
		try {
			Calendar laterNow = Calendar.getInstance();
			laterNow.setTime(new Date());
			laterNow.add(Calendar.SECOND, 5);

			EucaSessionTokenClient euca = new EucaSessionTokenClient();
			String servicePath = "/services/Tokens/";
			String endpoint = URI
					.create("http://" + this.clcIp + ":" + this.port + "/")
					.resolve(servicePath).toString();
			euca.setEndpoint(endpoint);
			EucaGetSessionTokenRequest request = new EucaGetSessionTokenRequest();
			request.setDurationSeconds(5);
			request.setAccount(account);
			request.setUser(user);
			request.setPassword(password);
			GetSessionTokenResult result = euca.getSessionToken(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.getMessage());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		return ret;

	}

	/**
	 * query CloudProperties
	 * 
	 * @param properties
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage describeProperties(ArrayList<String> properties) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"describeProperties", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			DescribePropertiesResult result;
			if (properties != null && properties.size() > 0) {
				DescribePropertiesRequest request = new DescribePropertiesRequest();
				request.setProperties(properties);
				result = adminClient.describeProperties(request);
			} else {
				result = adminClient.describeProperties();
			}
			ret.setData(JSONMessage.assembleDescribeProperties(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.getMessage());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * modify the value of a sepcific cloud property
	 * 
	 * @param property
	 * @param value
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage modifyProperty(String property, String value) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"describeProperties", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			ModifyPropertyResult result;
			ModifyPropertyRequest request = new ModifyPropertyRequest();
			request.setName(property);
			request.setValue(value);
			result = adminClient.modifyProperty(request);
			if (!value.equals(result.getValue().toString())){
				ret.setStatus(false);
				ret.setErrCode(400);
				ret.setErrMessage("the new value of cloud property (" + value +") is invalid");
			}
			ret.setData(JSONMessage.assembleModifyProperties(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.getMessage());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		return ret;
	}

	/**
	 * query the component & services status
	 * 
	 * @param byServiceType
	 * @param byHost
	 * @param byState
	 * @param byPartition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage describeServices(String byServiceType,
			String byHost, String byState, String byPartition) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"describeServices", true, null, 0);
		DescribeComponentsResult result = new DescribeComponentsResult();

		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Configuration/"));

			DescribeComponentsResult res = new DescribeComponentsResult();
			DescribeComponentsRequest request = new DescribeComponentsRequest();
			java.util.ArrayList<ComponentInfo> components;

			if (byServiceType == null || byServiceType.equals("eucalyptus")) {
				request.setAction("DescribeEucalyptus");
				res = adminClient.describeComponents(request);
				components = res.getComponents();
				for (ComponentInfo component : components) {
					result.getComponents().add(component);
				}
			}
			if (byServiceType == null || byServiceType.equals("walrus")) {
				request.setAction("DescribeWalruses");
				res = adminClient.describeComponents(request);
				components = res.getComponents();
				for (ComponentInfo component : components) {
					result.getComponents().add(component);
				}
			}
			if (byServiceType == null || byServiceType.equals("cluster")) {
				request.setAction("DescribeClusters");
				res = adminClient.describeComponents(request);
				components = res.getComponents();
				for (ComponentInfo component : components) {
					result.getComponents().add(component);
				}
			}
			if (byServiceType == null || byServiceType.equals("storage")) {
				request.setAction("DescribeStorageControllers");
				res = adminClient.describeComponents(request);
				components = res.getComponents();
				for (ComponentInfo component : components) {
					result.getComponents().add(component);
				}
			}
			if (byServiceType == null || byServiceType.equals("vmwarebroker")) {
				try {
					request.setAction("DescribeVMwareBrokers");
					res = adminClient.describeComponents(request);
					components = res.getComponents();
					for (ComponentInfo component : components) {
						result.getComponents().add(component);
					}
				} catch (Exception e) {
				}
			}

			if (byServiceType == null || byServiceType.equals("node")) {
				adminClient.setEndpoint(cloudUri("/services/Empyrean/"));
				DescribeServicesRequest srvRequest = new DescribeServicesRequest();
				srvRequest.setByServiceType("node");
				srvRequest.setListAll(true);
				if (byPartition != null) {
					srvRequest.setByPartition(byPartition);
				}

				if (byHost != null) {
					srvRequest.setByHost(byHost);
				}

				if (byState != null) {
					srvRequest.setByState(byState);
				}

				DescribeServicesResult srvRes = new DescribeServicesResult();
				srvRes = adminClient.describeServices(srvRequest);
				ArrayList<ServiceStatus> services = srvRes.getServices();
				for (ServiceStatus service : services) {
					ComponentInfo node = new ComponentInfo();
					node.setName(service.getName());
					node.setHostName(service.getName());
					node.setPartition(service.getPartition());
					node.setFullName(service.getFullName());
					node.setState(service.getLocalState());
					node.setType("node");
					node.setDetail("");
					result.getComponents().add(node);
				}
			}
			ret.setData(JSONMessage.assembleDescribeComponents(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		return ret;

	}

	private EucaConsoleMessage registerComponent(String Action,
			String partition, String name, String host) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Configuration/"));
			RegisterComponentRequest request = new RegisterComponentRequest();
			request.setAction(Action);
			request.setHost(host);
			request.setName(name);
			request.setPartition(partition);
			RegisterComponentResult result = adminClient
					.registerComponent(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		ret.setData(null);
		return ret;
	}

	private EucaConsoleMessage deregisterComponent(String action, String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Configuration/"));
			DeregisterComponentRequest request = new DeregisterComponentRequest();
			request.setAction(action);
			request.setName(name);
			DeregisterComponentResult result = adminClient
					.deregisterComponent(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * register cloud controller component
	 * 
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerCLC(String host, String name,String user, String password) {
		
		Ssh2Client sshClient = new Ssh2Client(host, user, password);
		try {
			sshClient.testConnectivity();
		} catch (IOException e) {
			return JSONMessage.getEucaConsoleMessage("RegisterComponent",
					false, "Can't ssh to the register server:" + e.toString(),
					255);

		}
		EucaConsoleMessage ret = registerComponent("RegisterEucalyptus", "eucalyptus", name, host);
		try {
			syncKeyFiles("cloud","eucalyptus",host,user,password);
		} catch (IOException e) {
			ret.setErrCode(255);
			ret.setStatus(false);
			ret.setErrMessage(e.toString() + ", Please sync the keys in clc server by command line!");
		}
		return ret;
	}

	/**
	 * de-register cloud controller component
	 * 
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterCLC(String name) {
		EucaConsoleMessage consoleMessage = this.describeServices(
				"eucalyptus", null, null, null);
		JSONArray results =  (JSONArray) consoleMessage.getData();
		for (int i=0; i< results.size();i++) {
			JSONObject obj = results.getJSONObject(i);
			if( obj.get("name").equals(name) && obj.getString("state").toLowerCase().equals("enabled")) {
				return JSONMessage.getEucaConsoleMessage("DeregisterComponent", false, "this is primary CLC now, can't be deregistered", 400);
			}
				
		}
		//return JSONMessage.getEucaConsoleMessage("DeregisterComponent", true, null, 0);
		return deregisterComponent("DeregisterEucalyptus", name);
	}

	/**
	 * Register Wlarus component
	 * 
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerWalrus(String host, String name,String user, String password) {
		Ssh2Client sshClient = new Ssh2Client(host, user, password);
		try {
			sshClient.testConnectivity();
		} catch (IOException e) {
			return JSONMessage.getEucaConsoleMessage("RegisterComponent",
					false, "Can't ssh to the register server:" + e.toString(),
					255);

		}
		EucaConsoleMessage ret = registerComponent("RegisterWalrus", "walrus", name, host);
		try {
			syncKeyFiles("cluster","walrus",host,user,password);
		} catch (IOException e) {
			ret.setErrCode(255);
			ret.setStatus(false);
			ret.setErrMessage(e.toString() + ", Please sync the keys in clc server by command line!");
		}
		return ret;
	}

	/**
	 * deregister walrus component
	 * 
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterWalrus(String name) {
		return deregisterComponent("DeregisterWalrus", name);
	}

	/**
	 * register cloud cluster component
	 * 
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerCC(String host, String name,
			String partition, String user, String password) {
		// test the server connectivity
		Ssh2Client sshClient = new Ssh2Client(host, user, password);
		try {
			sshClient.testConnectivity();
		} catch (IOException e) {
			return JSONMessage.getEucaConsoleMessage("RegisterComponent",
					false, "Can't ssh to the register server:" + e.toString(),
					255);

		}
		EucaConsoleMessage ret =registerComponent("RegisterCluster", partition, name, host);
		try {
			syncKeyFiles("cluster",partition,host,user,password);
		} catch (IOException e) {
			ret.setErrCode(255);
			ret.setStatus(false);
			ret.setErrMessage(e.toString() + ", Please sync the keys in clc server by command line!");
		}
		return ret;
	}

	/**
	 * de-register cloud cluster component
	 * 
	 * @param name
	 * @param partition
	 * @return cloud controller
	 */
	public EucaConsoleMessage deregisterCC(String name, String partition) {
		return deregisterComponent("DeregisterCluster", name);
	}

	/**
	 * register storage controller component
	 * 
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerSC(String host, String name,
			String partition,String user, String password) {
		
		//test the server connectivity
		Ssh2Client sshClient = new Ssh2Client(host, user, password);
		try {
			sshClient.testConnectivity();
		} catch (IOException e) {
			return JSONMessage.getEucaConsoleMessage("RegisterComponent",
					false, "Can't ssh to the register server:" + e.toString(),
					255);

		}
		EucaConsoleMessage ret = registerComponent("RegisterStorageController", partition, name,
				host);
		try {
			syncKeyFiles("storage",partition,host,user,password);
		} catch (IOException e) {
			ret.setErrCode(255);
			ret.setStatus(false);
			ret.setErrMessage(e.toString() + ", Please sync the keys in clc server by command line!");
		}
		return ret;
	}

	/**
	 * de-register storage controller component
	 * 
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterSC(String name, String partition) {
		return deregisterComponent("DeregisterStorageController", name);
	}

	/**
	 * register vmware broker
	 * 
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerVMB(String host, String name,
			String partition) {
		return registerComponent("RegisterVMwareBroker", partition, name, host);
	}

	/**
	 * de-register vmware broker
	 * 
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterVMB(String name, String partition) {
		return deregisterComponent("DeregisterVMwareBroker", name);
	}

	/**
	 * register node controller
	 * 
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerNode(String host, String partition,String user, String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		
		// test the server connenctivity
		Ssh2Client sshClient = new Ssh2Client(host,user,password);
		try {
			sshClient.testConnectivity();
		} catch (IOException e) {
			ret.setData(null);
			ret.setStatus(false);
			ret.setErrMessage("Can't ssh to the register server:" + e.toString());
			return ret;
		}
		//get the active CC host
		String clusterHost = null;
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Empyrean/"));
			DescribeServicesRequest request = new DescribeServicesRequest();
			request.setByPartition(partition);
			request.setByServiceType("cluster");
			request.setByState("enabled");
			DescribeServicesResult res = adminClient.describeServices(request);
			ServiceStatus service = res.getServices().get(0);
			String url = service.getUri();
			if (url.startsWith("http://")) {
				clusterHost = url.substring(7, url.indexOf(":", 7));
			}
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage("Can't get the active cluster server:"+e.toString());
			return ret;
		}
		if (clusterHost == null) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage("Can't get the active cluster server");
			return ret;
		}
		//change configuration of cluster
		try {
			Map<String,String[]> properties = new HashMap<String,String[]>();
			properties.put("NODES", new String[]{"a",host});
			this.setRemoteConfiguration(properties, clusterHost, ccDefaultUser, ccDefaultUserPassword);
		} catch (IOException e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage("Can't get the active cluster server");
			return ret;
		}
		try {
			syncKeyFiles("node",partition,host,user,password);
		} catch (IOException e) {
			ret.setErrCode(255);
			ret.setStatus(false);
			ret.setErrMessage(e.toString() + ", Please sync the keys in clc server by command line!");
		}
		return ret;
	}

	/**
	 * de-register node controller
	 * 
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterNode(String host, String partition) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		
		//get the active CC host
				String clusterHost = null;
				try {
					EucaAdminClient adminClient = getEucaAdminClient();
					adminClient.setEndpoint(cloudUri("/services/Empyrean/"));
					DescribeServicesRequest request = new DescribeServicesRequest();
					request.setByPartition(partition);
					request.setByServiceType("cluster");
					request.setByState("enabled");
					DescribeServicesResult res = adminClient.describeServices(request);
					ServiceStatus service = res.getServices().get(0);
					String url = service.getUri();
					if (url.startsWith("http://")) {
						clusterHost = url.substring(7, url.indexOf(":", 7));
					}
				} catch (AmazonServiceException e) {
					ret.setStatus(false);
					ret.setErrCode(e.getStatusCode());
					ret.setErrMessage("Can't get the active cluster server:"+e.toString());
					return ret;
				}
				if (clusterHost == null) {
					ret.setStatus(false);
					ret.setErrCode(255);
					ret.setErrMessage("Can't get the active cluster server");
					return ret;
				}
				//change configuration of cluster
				try {
					Map<String,String[]> properties = new HashMap<String,String[]>();
					properties.put("NODES", new String[]{"d",host});
					this.setRemoteConfiguration(properties, clusterHost, ccDefaultUser, ccDefaultUserPassword);
				} catch (IOException e) {
					ret.setStatus(false);
					ret.setErrCode(255);
					ret.setErrMessage("Can't get the active cluster server");
					return ret;
				}
				
				return ret;
		
		
	}

	/**
	 * enable a service component
	 * 
	 * @param name
	 *            -name of the component
	 */
	public EucaConsoleMessage enableService(String name) {
		
		return JSONMessage.getEucaConsoleMessage(
				"ModifyService", false, "This operation can't be supported by the cloud controller", 400);
		/*
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"ModifyService", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Empyrean/"));
			ModifyServiceRequest request = new ModifyServiceRequest();
			request.setServiceName(name);
			request.setState("enable");
			ModifyServiceResult result = adminClient.modifyServices(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		ret.setData(null);
		return ret; */

	}

	/**
	 * disable service component
	 * 
	 * @param name
	 *            - name of the component
	 */
	public EucaConsoleMessage disableService(String name) {
		
		//don't suppport in this version
		return JSONMessage.getEucaConsoleMessage(
				"ModifyService", false, "This operation can't be supported by the cloud controller", 400);
		/*
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"ModifyService", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Empyrean/"));
			ModifyServiceRequest request = new ModifyServiceRequest();
			request.setServiceName(name);
			request.setState("disable");
			ModifyServiceResult result = adminClient.modifyServices(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		ret.setData(null);
		return ret; */
	}

	/**
	 * get the all VM types
	 * 
	 * @param instanceType
	 *            - null stand for all instance type
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getInstanceTypes(ArrayList<String> instanceType) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"discribeInstanceTypes", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Eucalyptus/"));
			DescribeInstanceTypesRequest request = new DescribeInstanceTypesRequest();
			request.setInstanceTypes(instanceType);
			request.setAvailability(true);
			final DescribeInstanceTypesResult result = adminClient
					.describeVmTypes(request);
			ret.setData(JSONMessage.assembleDescribeInstanceTypes(result));

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * modify a instance type
	 * 
	 * @param instanceType
	 * @param cpu
	 *            - count of vcp
	 * @param mem
	 *            - memory MB
	 * @param disk
	 *            - disk GB
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage modifyInstanceTypes(String instanceType, int cpu,
			int mem, int disk) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"ModifyInstanceTypes", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Eucalyptus/"));

			ModifyInstanceTypeRequest request = new ModifyInstanceTypeRequest();
			request.setName(instanceType);
			request.setCpu(cpu);
			request.setMemory(mem);
			request.setDisk(disk);
			ModifyInstanceTypeResult result = adminClient
					.modifyVmTypes(request);
			ret.setData(JSONMessage.assembleModifyInstanceType(result));

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * query images
	 * 
	 * @param images
	 *            - null stand for all images
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getImages(ArrayList<String> images) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"discribeImages", true, null, 0);
		try {
			AmazonEC2 ec2Client = getEc2Client();
			DescribeImagesRequest request = new DescribeImagesRequest();
			if (images != null && images.size() > 0) {
				request.setImageIds(images);
			}
			DescribeImagesResult result = ec2Client.describeImages();
			ret.setData(JSONMessage.assembleDescribeImagesResult(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * deregister images
	 * 
	 * @param image
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterImage(String image) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterImage", true, null, 0);

		try {
			AmazonEC2 ec2Client = getEc2Client();
			DeregisterImageRequest request = new DeregisterImageRequest();
			request.setImageId(image);
			ec2Client.deregisterImage(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * set the Image as public or private image
	 * 
	 * @param image
	 * @param ispublic
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage modifyImage(String image, boolean ispublic) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"ModifyImageAttributes", true, null, 0);

		try {
			AmazonEC2 ec2Client = getEc2Client();
			ModifyImageAttributeRequest request = new ModifyImageAttributeRequest();
			request.setImageId(image);
			LaunchPermissionModifications launchPermission = new LaunchPermissionModifications();
			ArrayList<LaunchPermission> permissions = new ArrayList<LaunchPermission>();
			LaunchPermission permission = new LaunchPermission();
			permission.setGroup("all");
			permissions.add(permission);
			if (ispublic) {
				launchPermission.setAdd(permissions);
			} else {
				launchPermission.setRemove(permissions);
			}
			request.setLaunchPermission(launchPermission);
			ec2Client.modifyImageAttribute(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * Create account
	 * 
	 * @param name
	 *            - name of the account
	 * @return eucaConsoleMessage (account id & name)
	 */
	public EucaConsoleMessage createAccount(String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"createAccount", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			CreateAccountRequest request = new CreateAccountRequest();
			request.setAccountName(name);
			CreateAccountResult result = iamClient.CreateAccount(request);
			Account account = new Account();
			account.setAccountId(result.getAccountId());
			account.setAccountName(result.getAccountName());
			account.setAccountStatus("confirmed");
			ret.setData(JSONMessage.assembleAccountType(account));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * delete a account
	 * 
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delAccount(String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteAccount", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			DeleteAccountRequest request = new DeleteAccountRequest();
			request.setAccountName(name);
			request.setRecursive(true);
			iamClient.DeleteAccount(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * get account list
	 * 
	 * @return eucaConsoleMessage (account id & name)
	 */
	public EucaConsoleMessage getAccountList() {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getAccountList", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			ListAccountsResult result = iamClient.ListAccounts();
			ret.setData(JSONMessage.assembleAccountListsType(result
					.getAccounts()));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * return the all policy list of account
	 * 
	 * @param account
	 * @return
	 */
	public EucaConsoleMessage getAccountPolicyList(String account) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getAccountPolicyList", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			ListAccountPoliciesRequest request = new ListAccountPoliciesRequest();
			request.setAccount(account);
			ListAccountPoliciesResult result = iamClient
					.ListAccountPolicies(request);
			ArrayList<String> policyNames = result.getPolicies();
			java.util.Collection<EucaPolicy> policies = new java.util.ArrayList<EucaPolicy>();

			for (String policyName : policyNames) {
				EucaPolicy policy = getAccountPolicyContent(account, policyName);
				policies.add(policy);
			}
			ret.setData(JSONMessage.assemblePolicyListsType(policies, true));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * return the policy info of a account
	 * 
	 * @param account
	 * @param policy
	 *            - name of the policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getAccountPolicy(String account, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getAccountPolicy", true, null, 0);
		try {
			EucaPolicy eucaPolicy = getAccountPolicyContent(account, policy);
			ret.setData(JSONMessage.assemblePolicyType(eucaPolicy, true));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * add a policy to a account
	 * 
	 * @param account
	 * @param policy
	 *            - name of the policy
	 * @param policy_conent
	 *            - content of the policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addAccountPolicy(String account, String policy,
			String policy_conent) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"addAccountPolicy", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();

			AddAccountPolicyRequest request = new AddAccountPolicyRequest();
			request.setAccount(account);
			request.setPolicyName(policy);
			request.setPolicyBody(policy_conent);
			iamClient.AddAccountPolicy(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * delete a policy of a account
	 * 
	 * @param account
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delAccountPolicy(String account, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteAccountPolicy", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			DeleteAccountPolicyRequest request = new DeleteAccountPolicyRequest();
			request.setAccount(account);
			request.setPolicyName(policy);
			iamClient.DeleteAccountPolicy(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * add a user group
	 * 
	 * @param account
	 *            - name of account
	 * @param group
	 *            - name of group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage createGroup(String account, String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"createGroup", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			CreateGroupRequest request = new CreateGroupRequest();
			request.setGroupName(group);
			request.setPath("/");
			CreateGroupResult result = iamClient.createGroup(request);
			EucaGroup eucaGroup = new EucaGroup();
			eucaGroup.setGroupId(result.getGroup().getGroupId());
			eucaGroup.setGroupName(result.getGroup().getGroupName());
			eucaGroup.setAccount(account);
			eucaGroup.setPath(result.getGroup().getPath());
			ret.setData(JSONMessage.assembleGroupType(eucaGroup));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * delete a user group
	 * 
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delGroup(String account, String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteGroup", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			DeleteGroupRequest request = new DeleteGroupRequest();
			request.setGroupName(group);
			iamClient.deleteGroup(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * get list of user group
	 * 
	 * @param account
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupList(String account) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getGroupList", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			ListGroupsResult result = iamClient.listGroups();
			java.util.Collection<EucaGroup> groups = new java.util.ArrayList<EucaGroup>();
			java.util.List<Group> iamGroups = result.getGroups();
			for (Group group : iamGroups) {
				EucaGroup eucaGroup = new EucaGroup();
				eucaGroup.setAccount(account);
				eucaGroup.setGroupId(group.getGroupId());
				eucaGroup.setGroupName(group.getGroupName());
				eucaGroup.setPath(group.getPath());
				groups.add(eucaGroup);
			}
			ret.setData(JSONMessage.assembleGroupListsType(groups));

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * add a existing user to a existing group
	 * 
	 * @param account
	 * @param group
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addGroupUser(String account, String group,
			String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"addUserToGroup", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			AddUserToGroupRequest request = new AddUserToGroupRequest();
			request.setGroupName(group);
			request.setUserName(user);
			iamClient.addUserToGroup(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * delete a user from existing user group
	 * 
	 * @param account
	 * @param group
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage delGroupUser(String account, String group,
			String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteUserFromGroup", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			RemoveUserFromGroupRequest request = new RemoveUserFromGroupRequest();
			request.setGroupName(group);
			request.setUserName(user);
			iamClient.removeUserFromGroup(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * get user list of a group
	 * 
	 * @param account
	 * @param group
	 * @return
	 */
	public EucaConsoleMessage getGroupUserList(String account, String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getUserListForGroup", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			GetGroupRequest request = new GetGroupRequest();
			request.setGroupName(group);
			GetGroupResult result = iamClient.getGroup(request);
			java.util.List<User> users = result.getUsers();
			java.util.Collection<EucaUser> eucaUsers = new java.util.ArrayList<EucaUser>();
			for (User user : users) {
				EucaUser eucaUser = new EucaUser();
				eucaUser.setAccount(account);
				eucaUser.setPath(user.getPath());
				eucaUser.setUserId(user.getUserId());
				eucaUser.setUserName(user.getUserName());
				ArrayList<EucaGroup> groups = new ArrayList<EucaGroup>();
				EucaGroup eucaGroup = new EucaGroup();
				eucaGroup.setGroupId(result.getGroup().getGroupId());
				eucaGroup.setGroupName(result.getGroup().getGroupName());
				groups.add(eucaGroup);
				eucaUser.setGroups(groups);
				eucaUsers.add(eucaUser);
			}
			ret.setData(JSONMessage.assembleUserListsType(eucaUsers));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * Update the group name
	 * 
	 * @param account
	 * @param old_group
	 * @param new_group
	 * @return
	 */
	public EucaConsoleMessage modifyGroup(String account, String old_group,
			String new_group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"moifyGroup", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			UpdateGroupRequest request = new UpdateGroupRequest();
			request.setGroupName(old_group);
			request.setNewGroupName(new_group);
			iamClient.updateGroup(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;

	}

	private EucaPolicy getAccountPolicyContent(String account, String policyName) {
		EucaIAMClient iamClient = getIAMClient();
		GetAccountPolicyRequest request = new GetAccountPolicyRequest();
		request.setAccount(account);
		request.setPolicyName(policyName);
		GetAccountPolicyResult result = iamClient.GetAccountPolicy(request);
		EucaPolicy policy = new EucaPolicy();
		policy.setAccountName(account);
		policy.setUserName("");
		policy.setPolicyName(policyName);
		policy.setGroupName("");
		policy.setPolicyDocument(result.getPolicyBody());
		return policy;

	}

	private EucaPolicy getUserPolicyContent(String account, String user,
			String policyName) {
		EucaIAMClient iamClient = getIAMClient();
		if (!account.equals("eucalyptus")) {
			iamClient.setDelegateAccount(account);
		}
		GetUserPolicyRequest request = new GetUserPolicyRequest();
		request.setUserName(user);
		request.setPolicyName(policyName);
		GetUserPolicyResult result = iamClient.getUserPolicy(request);
		EucaPolicy policy = new EucaPolicy();
		policy.setAccountName(account);
		policy.setUserName(user);
		policy.setPolicyName(policyName);
		policy.setGroupName("");
		policy.setPolicyDocument(result.getPolicyDocument());
		return policy;

	}

	private EucaPolicy getGroupPolicyContent(String account, String group,
			String policyName) {
		EucaIAMClient iamClient = getIAMClient();
		if (!account.equals("eucalyptus")) {
			iamClient.setDelegateAccount(account);
		}
		GetGroupPolicyRequest request = new GetGroupPolicyRequest();
		request.setGroupName(group);
		request.setPolicyName(policyName);
		GetGroupPolicyResult result = iamClient.getGroupPolicy(request);
		EucaPolicy policy = new EucaPolicy();
		policy.setAccountName(account);
		policy.setUserName("");
		policy.setPolicyName(policyName);
		policy.setGroupName(group);
		policy.setPolicyDocument(result.getPolicyDocument());
		return policy;

	}

	/**
	 * get all policy list of a group
	 * 
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupPolicyList(String account, String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getUserPolicyList", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}

			ListGroupPoliciesRequest request = new ListGroupPoliciesRequest();
			request.setGroupName(group);
			ListGroupPoliciesResult result = iamClient
					.listGroupPolicies(request);
			List<String> names = result.getPolicyNames();
			java.util.Collection<EucaPolicy> policies = new java.util.ArrayList<EucaPolicy>();
			for (String name : names) {
				EucaPolicy policy = getGroupPolicyContent(account, group, name);
				policies.add(policy);
			}
			ret.setData(JSONMessage.assemblePolicyListsType(policies, true));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * Get group policy content/info
	 * 
	 * @param account
	 * @param group
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupPolicy(String account, String group,
			String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getGroupPolicy", true, null, 0);
		try {
			EucaPolicy eucaPolicy = getGroupPolicyContent(account, group,
					policy);
			ret.setData(JSONMessage.assemblePolicyType(eucaPolicy, true));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * add policy to a group
	 * 
	 * @param account
	 * @param group
	 * @param policy
	 * @param policy_conent
	 * @return
	 */
	public EucaConsoleMessage addGroupPolicy(String account, String group,
			String policy, String policy_conent) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"putGroupPolicy", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			PutGroupPolicyRequest request = new PutGroupPolicyRequest();
			request.setGroupName(group);
			request.setPolicyName(policy);
			request.setPolicyDocument(policy_conent);
			iamClient.putGroupPolicy(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * delete a policy of group
	 * 
	 * @param account
	 * @param group
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delGroupPolicy(String account, String group,
			String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteGroupPolicy", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			DeleteGroupPolicyRequest request = new DeleteGroupPolicyRequest();
			request.setGroupName(group);
			request.setPolicyName(policy);
			iamClient.deleteGroupPolicy(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * create a new user
	 * 
	 * @param account
	 * @param group
	 *            - null stand for no user group
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage createUser(String account, String group,
			String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"createUser", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			CreateUserRequest request = new CreateUserRequest();
			request.setUserName(user);
			request.setPath("/");
			CreateUserResult result = iamClient.createUser(request);
			EucaUser eucaUser = new EucaUser();
			eucaUser.setAccount(account);
			eucaUser.setPath(result.getUser().getPath());
			eucaUser.setUserId(result.getUser().getUserId());
			eucaUser.setUserName(user);
			java.util.Collection<EucaGroup> groups = new java.util.ArrayList<EucaGroup>();
			eucaUser.setGroups(groups);
			ret.setData(JSONMessage.assembleUserType(eucaUser));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * delete a user
	 * 
	 * @param account
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage delUser(String account, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteUser", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			DeleteUserRequest request = new DeleteUserRequest();
			request.setUserName(user);
			iamClient.deleteUser(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * return user list of a account
	 * 
	 * @param account
	 * @return
	 */
	public EucaConsoleMessage getUserList(String account) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getUserList", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			ListUsersRequest request = new ListUsersRequest();
			ListUsersResult result = iamClient.listUsers(request);
			java.util.List<User> users = result.getUsers();
			java.util.Collection<EucaUser> eucaUsers = new java.util.ArrayList<EucaUser>();
			for (User user : users) {
				EucaUser eucaUser = new EucaUser();
				eucaUser.setAccount(account);
				eucaUser.setPath(user.getPath());
				eucaUser.setUserId(user.getUserId());
				eucaUser.setUserName(user.getUserName());
				List<EucaGroup> groups = getUserGroups(account,
						user.getUserName());
				eucaUser.setGroups(groups);
				eucaUsers.add(eucaUser);
			}
			ret.setData(JSONMessage.assembleUserListsType(eucaUsers));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	private List<EucaGroup> getUserGroups(String account, String user) {
		List<EucaGroup> groups = new ArrayList<EucaGroup>();
		EucaIAMClient iamClient = getIAMClient();
		if (!account.equals("eucalyptus")) {
			iamClient.setDelegateAccount(account);
		}

		ListGroupsForUserRequest request = new ListGroupsForUserRequest();
		request.setUserName(user);
		ListGroupsForUserResult result = iamClient.listGroupsForUser(request);
		List<Group> iamGroups = result.getGroups();
		for (Group group : iamGroups) {
			EucaGroup eucaGroup = new EucaGroup();
			eucaGroup.setAccount(account);
			eucaGroup.setGroupName(group.getGroupName());
			eucaGroup.setGroupId(group.getGroupId());
			eucaGroup.setPath(group.getPath());
			groups.add(eucaGroup);
		}
		return groups;
	}

	/**
	 * return group list of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserGroupList(String account, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getUserGroupList", true, null, 0);
		try {
			java.util.Collection<EucaGroup> groups = getUserGroups(account,
					user);
			ret.setData(JSONMessage.assembleGroupListsType(groups));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * get all policy list of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserPolicyList(String account, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getUserPolicyList", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}

			ListUserPoliciesRequest request = new ListUserPoliciesRequest();
			request.setUserName(user);
			ListUserPoliciesResult result = iamClient.listUserPolicies(request);
			List<String> names = result.getPolicyNames();
			java.util.Collection<EucaPolicy> policies = new java.util.ArrayList<EucaPolicy>();
			for (String name : names) {
				EucaPolicy policy = getUserPolicyContent(account, user, name);
				policies.add(policy);
			}
			ret.setData(JSONMessage.assemblePolicyListsType(policies, true));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * Get user policy content/info
	 * 
	 * @param account
	 * @param user
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserPolicy(String account, String user,
			String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getUserPolicy", true, null, 0);
		try {
			EucaPolicy eucaPolicy = getUserPolicyContent(account, user, policy);
			ret.setData(JSONMessage.assemblePolicyType(eucaPolicy, true));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * add policy to a user
	 * 
	 * @param account
	 * @param user
	 * @param policy
	 * @param policy_conent
	 * @return
	 */
	public EucaConsoleMessage addUserPolicy(String account, String user,
			String policy, String policy_conent) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"putUserPolicy", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			PutUserPolicyRequest request = new PutUserPolicyRequest();
			request.setUserName(user);
			request.setPolicyName(policy);
			request.setPolicyDocument(policy_conent);
			iamClient.putUserPolicy(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * delete a policy of user
	 * 
	 * @param account
	 * @param user
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delUserPolicy(String account, String user,
			String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteUserPolicy", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			DeleteUserPolicyRequest request = new DeleteUserPolicyRequest();
			request.setUserName(user);
			request.setPolicyName(policy);
			iamClient.deleteUserPolicy(request);

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * return the x509 certs of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserCerts(String account, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getUserCerts", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			ListSigningCertificatesRequest request = new ListSigningCertificatesRequest();
			request.setUserName(user);
			ListSigningCertificatesResult result = iamClient
					.listSigningCertificates(request);
			ret.setData(JSONMessage.assembleListCertificatesResult(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * add new x509 certs of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addUserCerts(String account, String user,
			String certsBody) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"loadUserCertificate", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			UploadSigningCertificateRequest request = new UploadSigningCertificateRequest();
			request.setUserName(user);
			request.setCertificateBody(certsBody);
			UploadSigningCertificateResult result = iamClient
					.uploadSigningCertificate(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * Delete a user cert
	 * 
	 * @param account
	 * @param user
	 * @param cert
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delUserCerts(String account, String user,
			String cert) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteUserCertificate", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			DeleteSigningCertificateRequest request = new DeleteSigningCertificateRequest();
			request.setUserName(user);
			request.setCertificateId(cert);
			iamClient.deleteSigningCertificate(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * return the keys of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserKeys(String account, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"listUserAccessKeys", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			ListAccessKeysRequest request = new ListAccessKeysRequest();
			request.setUserName(user);
			ListAccessKeysResult result = iamClient.listAccessKeys(request);
			ret.setData(JSONMessage.assembleListAccessKeysResult(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;

	}

	/**
	 * add a new user key
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addUserKeys(String account, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"CreateAccessKeys", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			CreateAccessKeyRequest request = new CreateAccessKeyRequest();
			request.setUserName(user);
			CreateAccessKeyResult result = iamClient.createAccessKey(request);
			ret.setData(JSONMessage.assembleCreateAccessKeyResult(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;

	}

	/**
	 * delete a user key
	 * 
	 * @param account
	 * @param user
	 * @param key
	 * @return
	 */
	public EucaConsoleMessage delUserKeys(String account, String user,
			String key) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"deleteAccessKey", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			DeleteAccessKeyRequest request = new DeleteAccessKeyRequest();
			request.setUserName(user);
			request.setAccessKeyId(key);
			iamClient.deleteAccessKey(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * add the user's login password for new user
	 * 
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public EucaConsoleMessage addUserLogin(String account, String user,
			String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"addUserLoginProfile", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			CreateLoginProfileRequest request = new CreateLoginProfileRequest();
			request.setUserName(user);
			request.setPassword(password);
			iamClient.createLoginProfile(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * modify the user's login password
	 * 
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public EucaConsoleMessage modifyUserLogin(String account, String user,
			String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"updateUserLoginProfile", true, null, 0);
		try {
			EucaIAMClient iamClient = getIAMClient();
			if (!account.equals("eucalyptus")) {
				iamClient.setDelegateAccount(account);
			}
			UpdateLoginProfileRequest request = new UpdateLoginProfileRequest();
			request.setUserName(user);
			request.setPassword(password);
			iamClient.updateLoginProfile(request);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		ret.setData(null);
		return ret;
	}

	/**
	 * Download the credentials file
	 * 
	 * @param account
	 * @param user
	 * @param filePath
	 * @return
	 */
	public EucaConsoleMessage downloadUserCredentials(String account,
			String user, String filePath) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"downloadCredentials", true, null, 0);
		try {
			File creds = new File(filePath);
			if (creds.exists()) {
				creds.delete();
				creds.createNewFile();
			} else {
				File dir = creds.getParentFile();
				if (!dir.exists())
					dir.mkdirs();
				creds.createNewFile();
			}
			ret.setData(JSONMessage.assembleDownloadCredsResult(filePath));
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(1);
			ret.setErrMessage(e.toString());
		}

		return ret;
	}

	/**
	 * return a block manager of a cluster
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage getBlockManager(String cluster) {
		ArrayList<String> clusteres = new ArrayList<String>();
		// the name of block manager: clustername.storage..blockstoragemanager
		clusteres.add(cluster + ".storage.blockstoragemanager");
		return describeProperties(clusteres);

	}

	/**
	 * modify the block manager of cluster
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage modifyBlockManager(String cluster, String manager) {

		String property = cluster + ".storage.blockstoragemanager";
		ArrayList<String> props = new ArrayList<String>();
		props.add(property);
		DescribePropertiesResult old = this.getProperties(props);
		if(old !=null && old.getProperties().size() > 0) {
			CloudProperty p = old.getProperties().get(0);
			if (p.getValue() != null) {
				if(!p.toString().toLowerCase().equals("unset") && !p.toString().isEmpty()){
					return JSONMessage.getEucaConsoleMessage(
							"ModifyProperty", false, "This cluster already setup the blockmanager, not allow to change", 400); 
				}
			}
		}
		return modifyProperty(property, manager);
	}

	/**
	 * return a block manager of a cluster
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage getBlockManagerProperties(String cluster,
			String manager) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DescribeProperties", true, null, 0);

		ArrayList<String> properties = new ArrayList<String>();
		if (manager.equals("clvm")) {
			properties.add(cluster + ".storage.sharedevice");
		}

		if (manager.equals("das")) {
			properties.add(cluster + ".storage.dasdevice");
		}
		/*
		 * if (manager.equals("clvm") ) {
		 * properties.add(cluster+".storage.dasdevice"); }
		 */
		if (manager.equals("overlay")) {
			ret.setData(null);
			return ret;
		}

		if (manager.equals("equallogic")) {
			properties.add(cluster + ".storage.sanhost");
			properties.add(cluster + ".storage.sanuser");
			properties.add(cluster + ".storage.sanpassword");
			properties.add(cluster + ".storage.ncpaths");
			properties.add(cluster + ".storage.scpaths");
		}

		if (manager.equals("netapp")) {
			properties.add(cluster + ".storage.sanhost");
			properties.add(cluster + ".storage.sanuser");
			properties.add(cluster + ".storage.sanpassword");
			properties.add(cluster + ".storage.chapuser");
			properties.add(cluster + ".storage.vservername");
			properties.add(cluster + ".storage.ncpaths");
			properties.add(cluster + ".storage.scpaths");
		}

		if (manager.equals("emc-vnx")) {
			properties.add(cluster + ".storage.sanhost");
			properties.add(cluster + ".storage.sanuser");
			properties.add(cluster + ".storage.sanpassword");
			properties.add(cluster + ".storage.chapuser");
			properties.add(cluster + ".storage.storagepool");
			properties.add(cluster + ".storage.loginscope");
			properties.add(cluster + ".storage.ncpaths");
			properties.add(cluster + ".storage.scpaths");
		}
		return describeProperties(properties);
	}

	/**
	 * modify the parameters of block manager
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage modifyBlockManagerProperty(String cluster,
			String para, String value) {
		String property = cluster + ".storage." + para;
		return modifyProperty(property, value);
	}

	/**
	 * get network mode
	 */
	public EucaConsoleMessage getNetworkMode() {
		
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"descirbeNetwrokMode", true, null, 0);
		try {
			Map<String, String> clusters = getAllCluster(false);
			Set<String> partitions = clusters.keySet();
			String partition = partitions.iterator().next();
			if (partition == null) {
				ret.setStatus(false);
				ret.setErrCode(255);
				ret.setErrMessage("There is no cluster registered, network not setuped yet");
				ret.setData(null);
				return ret;
			}
			ArrayList<String> mode = new ArrayList<String>();
			mode.add(partition + ".cluster.networkmode");
			DescribePropertiesResult result = this.getProperties(mode);
			DescribePropertiesResult convertResult = new DescribePropertiesResult();
			CloudProperty networkmode = new CloudProperty();
			CloudProperty old = result.getProperties().get(0);
			networkmode.setName("networkmode");
			networkmode.setValue(old.getValue());
			networkmode.setDescription(old.getDescription());
			convertResult.getProperties().add(networkmode);
			ret.setData(JSONMessage.assembleDescribeProperties(convertResult));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
		//
		//return describeProperties(mode);
	}

	/**
	 * modify the network mode
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage modifyNetworkMode(String mode) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"ModifyNetwrokMode", true, null, 0);
		try {
			
			Map<String, String> clusters = getAllCluster(false);
			Set<String> partitions = clusters.keySet();
			for(String partition:partitions) {
				
				String property = partition + ".cluster.networkmode";
				ArrayList<String> props = new ArrayList<String>();
				props.add(property);
				DescribePropertiesResult old = this.getProperties(props);
				if(old !=null && old.getProperties().size() > 0) {
					CloudProperty p = old.getProperties().get(0);
					if (p.getValue() != null) {
						if(!p.toString().toLowerCase().equals("unset") && !p.toString().isEmpty()){
							return JSONMessage.getEucaConsoleMessage(
									"ModifyProperty", false, "network mode already be set, not allow to change", 400); 
						}
					}
				}
				
				Map<String,String[]> properties = new HashMap<String,String[]>();
				properties.put("VNET_MODE", new String[]{"r",mode});
				this.setRemoteConfiguration(properties, clusters.get(partition), ccDefaultUser, ccDefaultUserPassword);
			}
			ModifyPropertyResult result = new ModifyPropertyResult();
			result.setName("networkmode");
			result.setValue(mode);
			result.setOldValue(mode);
			ret.setData(JSONMessage.assembleModifyProperties(result));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (IOException e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
	    } catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
		
		//return modifyProperty("networkmode", mode);
	}

	public EucaConsoleMessage getNetworkModeProperties(String mode) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DescribeProperties", true, null, 0);

		ArrayList<String> properties = new ArrayList<String>();
		
		if (mode.toLowerCase().equals("system")) {
			ret.setData(null);
			return ret;
		}
		CloudProperty vnet_subnet;
		CloudProperty vnet_mask;
		CloudProperty vnet_dns;
		CloudProperty vnet_publicips;
		CloudProperty vnet_broadcast;
		CloudProperty vnet_router;
		CloudProperty vnet_macmap;
		CloudProperty vnet_addrspernet;
		CloudProperty vnet_privinterface = new CloudProperty();
		CloudProperty vnet_pubinterface = new CloudProperty();
		CloudProperty vlan_range;
		
		vnet_privinterface.setDescription("--");
		vnet_privinterface.setName("vnet_privinterface");
		vnet_privinterface.setValue("--");
		
		vnet_pubinterface.setDescription("--");
		vnet_pubinterface.setName("vnet_pubinterface");
		vnet_pubinterface.setValue("--");
		try {
			Map<String, String> clusters = getAllCluster(false);
			Set<String> partitions = clusters.keySet();
			String partition = partitions.iterator().next();
			if (partition == null) {
				ret.setStatus(false);
				ret.setErrCode(255);
				ret.setErrMessage("There is no cluster registered, network not setuped yet");
				ret.setData(null);
				return ret;
			}
			
			properties.add(partition + "cluster.minnetworktag");
			properties.add(partition + "cluster.maxnetworktag");
			
			DescribePropertiesResult tags = this.getProperties(properties);
			String mintag = "2",maxtag = "4094";
			for(CloudProperty property:tags.getProperties())
			{
				if ( property.getName().indexOf("minnetworktag") >0 )
					mintag = (String)property.getValue();
				if ( property.getName().indexOf("maxnetworktag") >0 )
					mintag = (String)property.getValue();
			};
			vlan_range = new CloudProperty();
			vlan_range.setName("vlan_range");
			vlan_range.setValue(mintag + "-" + maxtag);
			vlan_range.setDescription("--");
			
			String clcHost =  clusters.get(partition);
			RemoteEucalyptusConfiguration conf =new RemoteEucalyptusConfiguration();
			conf.load(clcHost, ccDefaultUser, ccDefaultUserPassword);
			
			vnet_subnet = new CloudProperty();
			vnet_subnet.setName("vnet_subnet");
			vnet_subnet.setValue(conf.getValue("vnet_subnet"));
			vnet_subnet.setDescription("--");
			
			vnet_mask = new CloudProperty();
			vnet_mask.setName("vnet_netmask");
			vnet_mask.setValue(conf.getValue("vnet_netmask"));
			vnet_mask.setDescription("--");
			
			vnet_dns = new CloudProperty();
			vnet_dns.setName("vnet_dns");
			vnet_dns.setValue(conf.getValue("vnet_dns"));
			vnet_dns.setDescription("--");
			
			vnet_addrspernet = new CloudProperty();
			vnet_addrspernet.setName("vnet_addrspernet");
			vnet_addrspernet.setValue(conf.getValue("vnet_addrspernet"));
			vnet_addrspernet.setDescription("--");
			
			vnet_publicips = new CloudProperty();
			vnet_publicips.setName("vnet_publicips");
			vnet_publicips.setValue(conf.getValue("vnet_publicips"));
			vnet_publicips.setDescription("--");
			
			vnet_broadcast = new CloudProperty();
			vnet_broadcast.setName("vnet_broadcast");
			vnet_broadcast.setValue(conf.getValue("vnet_broadcast"));
			vnet_broadcast.setDescription("--");
			
			vnet_router = new CloudProperty();
			vnet_router.setName("vnet_router");
			vnet_router.setValue(conf.getValue("vnet_router"));
			vnet_router.setDescription("--");
			
			vnet_macmap = new CloudProperty();
			vnet_router.setName("vnet_macmap");
			vnet_router.setValue(conf.getValue("vnet_macmap"));
			vnet_router.setDescription("--");
			
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
			return ret;
		} catch (IOException e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
			return ret;
	    } catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
			return ret;
		}
		DescribePropertiesResult result = new DescribePropertiesResult();
		if (mode.toLowerCase().equals("managed")) {
			
			result.getProperties().add(vnet_subnet);
			result.getProperties().add(vnet_mask);
			result.getProperties().add(vnet_dns);
			result.getProperties().add(vnet_addrspernet);
			result.getProperties().add(vnet_publicips);
			result.getProperties().add(vnet_privinterface);
			result.getProperties().add(vnet_pubinterface);
			result.getProperties().add(vlan_range);
		}
		if (mode.toLowerCase().equals("managed-novlan")) {
			result.getProperties().add(vnet_subnet);
			result.getProperties().add(vnet_mask);
			result.getProperties().add(vnet_dns);
			result.getProperties().add(vnet_addrspernet);
			result.getProperties().add(vnet_publicips);
			result.getProperties().add(vnet_privinterface);
			result.getProperties().add(vnet_pubinterface);
		}
		

		if (mode.toLowerCase().equals("static")) {
			result.getProperties().add(vnet_subnet);
			result.getProperties().add(vnet_mask);
			result.getProperties().add(vnet_dns);
			result.getProperties().add(vnet_broadcast);
			result.getProperties().add(vnet_router);
			result.getProperties().add(vnet_privinterface);
			result.getProperties().add(vnet_macmap);

		}
		ret.setData(JSONMessage.assembleDescribeProperties(result));
		return ret;
	}

	/**
	 * modify property of network mode
	 * 
	 * @param mode
	 * @param para
	 * @param value
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage modifyNetwrokModeProperty(String mode,
			String para, String value) {
		return modifyProperty(para, value);
	}

	public EucaConsoleMessage getEbsServiceProperties(String cluster) {

		ArrayList<String> properties = new ArrayList<String>();
		properties.add(cluster + ".storage.maxtotalvolumesizeingb");
		properties.add(cluster + ".storage.maxvolumesizeingb");
		return describeProperties(properties);
	}

	public EucaConsoleMessage modifyEbsServiceProperty(String cluster,
			String para, String value) {
		//String prop = cluster + ".storage." + para;
		return modifyProperty(para, value);
	}

	public EucaConsoleMessage getDNSServiceProperties() {

		ArrayList<String> properties = new ArrayList<String>();
		properties.add("system.dns.dnsdomain");
		properties.add("bootstrap.webservices.use_dns_delegation");
		properties.add("bootstrap.webservices.use_instance_dns");
		properties.add("cloud.vmstate.instance_subdomain");

		return describeProperties(properties);
	}

	public EucaConsoleMessage modifyDNSServiceProperty(String para, String value) {
		String prop = para;
		switch (para) {
		case "dnsdoman":
			prop = "system.dns.dnsdomain";
			break;
		case "use_dns_delegation":
			prop = "bootstrap.webservices.use_dns_delegation";
			break;
		case "use_instance_dns":
			prop = "bootstrap.webservices.use_instance_dns";
			break;
		case "instance_subdomain":
			prop = "cloud.vmstate.instance_subdomain";
			break;
		default:
			/*
			return JSONMessage.getEucaConsoleMessage("modifyProperties", false,
					"Invalid property name", 400); */
		}

		return modifyProperty(prop, value);
	}

	public EucaConsoleMessage getS3ServiceProperties() {

		ArrayList<String> properties = new ArrayList<String>();
		properties.add("walrus.storagemaxbucketsizeinmb");
		properties.add("walrus.storagemaxbucketsperaccount");
		properties.add("walrus.storagemaxcachesizeinmb");
		properties.add("walrus.storagemaxtotalcapacity");
		properties.add("walrus.storagemaxtotalsnapshotsizeingb");
		return describeProperties(properties);
	}

	public EucaConsoleMessage modifyS3ServiceProperty(String para, String value) {

		//String prop = "walrus." + para;
		return modifyProperty(para, value);
	}

	public EucaConsoleMessage modifyLoadBalancerImage(String image) {
		return modifyProperty("loadbalancing.loadbalancer_emi", image);
	}

	public EucaConsoleMessage getLoadBalancerImage() {

		ArrayList<String> properties = new ArrayList<String>();
		properties.add("loadbalancing.loadbalancer_emi");
		return describeProperties(properties);
	}

	public EucaConsoleMessage installBalancerImage() {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"installBalancerImage", true, null, 0);
		ret.setData(null);
		return ret;
	}

	/**
	 * generate instance report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genInstanceReport(Date startDay, Date endDay) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"genInstanceReport", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Reporting/"));
			GenerateReportRequest request = new GenerateReportRequest();
			request.setStartDay(startDay);
			request.setEndDay(endDay);
			request.setReportType("instance");
			request.setReportFormat("csv");
			String result = adminClient.generateReport(request);
			ReportDataUnmarshaller parser = new ReportDataUnmarshaller();
			InstanceReport reports = parser.parseInstanceReport(result);
			ret.setData(JSONMessage.assmebileInstanceReport(reports));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * generate capacity report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genCapacityReport(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"genCapacityReport", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Reporting/"));
			GenerateReportRequest request = new GenerateReportRequest();
			request.setStartDay(startDay);
			request.setEndDay(endDay);
			request.setReportType("capacity");
			request.setReportFormat("csv");
			String result = adminClient.generateReport(request);
			ReportDataUnmarshaller parser = new ReportDataUnmarshaller();
			CapacityReport reports = parser.parseCapacityReport(result);
			ret.setData(JSONMessage.assmebileCapacityReport(reports));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * generate S3 report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genS3Report(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"genS3Report", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Reporting/"));
			GenerateReportRequest request = new GenerateReportRequest();
			request.setStartDay(startDay);
			request.setEndDay(endDay);
			request.setReportType("s3");
			request.setReportFormat("csv");
			String result = adminClient.generateReport(request);
			ReportDataUnmarshaller parser = new ReportDataUnmarshaller();
			S3Report reports = parser.parseS3Report(result);
			ret.setData(JSONMessage.assmebileS3Report(reports));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * generate Ip report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genIpReport(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"genIpReport", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Reporting/"));
			GenerateReportRequest request = new GenerateReportRequest();
			request.setStartDay(startDay);
			request.setEndDay(endDay);
			request.setReportType("elastic-ip");
			request.setReportFormat("csv");
			String result = adminClient.generateReport(request);
			ReportDataUnmarshaller parser = new ReportDataUnmarshaller();
			IpReport reports = parser.parseIPReport(result);
			ret.setData(JSONMessage.assmebileIpReport(reports));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * generate Volume report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genVolumeReport(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"genVolumeReport", true, null, 0);
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Reporting/"));
			GenerateReportRequest request = new GenerateReportRequest();
			request.setStartDay(startDay);
			request.setEndDay(endDay);
			request.setReportType("volume");
			request.setReportFormat("csv");
			String result = adminClient.generateReport(request);
			ReportDataUnmarshaller parser = new ReportDataUnmarshaller();
			VolumeReport reports = parser.parseVolumeReport(result);
			ret.setData(JSONMessage.assmebileVolumeReport(reports));
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * monitor the Component, return the component status and event
	 * 
	 * @return
	 */
	public EucaConsoleMessage monitorComponent() {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"monitoringComponents", true, null, 0);
		ComponentsStatus result = new ComponentsStatus();
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Empyrean/"));
			DescribeServicesRequest request = new DescribeServicesRequest();
			request.setShowEvents(true);
			request.setListAll(true);
			DescribeServicesResult res = adminClient.describeServices(request);
			ArrayList<ServiceStatus> services = res.getServices();
			for (ServiceStatus service : services) {
				String type = service.getType();
				if (type.equals("eucalyptus") || type.equals("cluster")
						|| type.equals("node") || type.equals("walrus")
						|| type.equals("storage")
						|| type.equals("vmwarebroker")) {
					ComponentInfo component = new ComponentInfo();
					component.setName(service.getName());
					String url = service.getUri();
					if (url.startsWith("http://")) {
						component.setHostName(url.substring(7,
								url.indexOf(":", 7)));
					} else {
						component.setHostName(service.getUri());
					}
					component.setPartition(service.getPartition());
					component.setFullName(service.getFullName());
					component.setState(service.getLocalState());
					component.setType(type);
					String event = "";
					if (service.getStatusDetails() != null
							&& service.getStatusDetails().size() > 0) {
						event = service.getStatusDetails().get(0).message;
					}
					component.setDetail(event);
					result.getComponents().add(component);
				}

			}
			ret.setData(JSONMessage.assembleComponentsStatus(result));

		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		return ret;
	}

	/**
	 * retrieve the dashboard data
	 * 
	 * @return
	 */
	public EucaConsoleMessage getDashBoardData() {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"getDashBoard", true, null, 0);
		
		/*EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"genCapacityReport", true, null, 0); */
		java.util.Date startDay = new java.util.Date();
		java.util.Date endDay = new java.util.Date();
		DashBoardData result = new DashBoardData();
		try {
			EucaAdminClient adminClient = getEucaAdminClient();
			adminClient.setEndpoint(cloudUri("/services/Reporting/"));
			GenerateReportRequest request = new GenerateReportRequest();
			request.setStartDay(startDay);
			request.setEndDay(endDay);
			request.setReportType("capacity");
			request.setReportFormat("csv");
			String reportResult = adminClient.generateReport(request);
			ReportDataUnmarshaller parser = new ReportDataUnmarshaller();
			CapacityReport reports = parser.parseCapacityReport(reportResult);
			for (CapacityReportData data:reports.getReports()) {
				
				if (data.getResource().equals("EC2 Disk")) {
					result.setTotalDisk(data.getTotal());
					result.setAvailableDisk(data.getAvailable());
				}
				
				if (data.getResource().equals("EC2 Memory")) {
					result.setTotalMem(data.getTotal());
					result.setAvailableMem(data.getAvailable());
				}
				if (data.getResource().equals("EC2 Compute")) {
					result.setTotalCpu(data.getTotal());
					result.setAvailableCpu(data.getAvailable());
				}
				if (data.getResource().equals("Elastic IP")) {
					result.setTotalIp(data.getTotal());
					result.setAvailableIp(data.getAvailable());
				}
				
				if (data.getResource().equals("t1.micro")) {
					result.setRunInstances(data.getTotal());
					result.setStoppedInstances(data.getAvailable());
				}
				
				if (data.getResource().equals("EBS Storage")) {
					result.setCreatedVolumes(data.getTotal());
					result.setAttachedVolumes(data.getAvailable());
				}
				
			}
			EucaIAMClient iamClient = getIAMClient();
			ListAccountsResult accounts = iamClient.ListAccounts();
			result.setAccounts(accounts.getAccounts().size());
			int groupCount = 0;
			int userCount = 0;
			for(Account account:accounts.getAccounts()) {
				if (!account.equals("eucalyptus")) {
					iamClient.setDelegateAccount(account.getAccountName());
				}
				ListGroupsResult groups = iamClient.listGroups();
				groupCount += groups.getGroups().size();
				ListUsersResult users = iamClient.listUsers();
				userCount += users.getUsers().size();
			}
			result.setUsers(userCount);
			result.setGroups(groupCount);
		} catch (AmazonServiceException e) {
			ret.setStatus(false);
			ret.setErrCode(e.getStatusCode());
			ret.setErrMessage(e.toString());
			ret.setData(null);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			ret.setStatus(false);
			ret.setErrCode(255);
			ret.setErrMessage(e.toString());
			ret.setData(null);
		}
		
		//result.setAccounts(2);
		//result.setUsers(4);
		//result.setGroups(2);
		//result.setTotalCpu(16);
		//result.setTotalDisk(500);
		//result.setTotalMem(40960);
		//result.setTotalIp(100);
		//result.setAvailableCpu(14);
		//result.setAvailableIp(98);
		//result.setAvailableMem(8192);
		//result.setAvailableDisk(490);

		//result.setRunInstances(2);
		//result.setStoppedInstances(1);
		//result.setPenddingInstances(0);

		//result.setCreatedVolumes(4);
		//result.setAttachedVolumes(2);

		ret.setData(JSONMessage.assembleDashBoardData(result));
		return ret;
	}
	/*
	 * private String getRandomString(int length) { String base =
	 * "abcdefghijklmnopqrstuvwxyz0123456789"; java.util.Random random = new
	 * java.util.Random(); StringBuffer sb = new StringBuffer(); for (int i = 0;
	 * i < length; i++) { int number = random.nextInt(base.length());
	 * sb.append(base.charAt(number)); } return sb.toString(); }
	 */
}
