package com.eucalyptus.admin.test;

import java.util.ArrayList;

import com.eucalyptus.admin.*;
import com.eucalyptus.admin.console.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import com.eucalyptus.admin.util.*; 

public class consoleExample {
	
	private static Map<String,String[]> commands = new HashMap<String,String[]>();
	
	static {
		commands.put("login", new String[] {"login","login example"});
		commands.put("components", new String[] {"describeComponents","show all components"});
		
		commands.put("cloud", new String[] {"describeCloudControllers","show all cloud controllers"});
		commands.put("registerCloud",new String[] {"registerCLC","register a cloud controller component"});
		commands.put("deregisterCloud", new String[] {"deregisterCLC","deregister a cloud controller"});
		commands.put("enableCloud", new String[] {"enableCLC","enable a cloud controller"});
		commands.put("disableCloud", new String[] {"disableCLC","disable a cloud controller"});
		
		commands.put("cluster", new String[] {"describeClusters","show all cluster controller"});
		commands.put("registerCC", new String[] {"registerCC","register a cluster controller"});
		commands.put("deregisterCC", new String[] {"deregisterCC","deregister a cluster controller"});
		commands.put("enableCC", new String[] {"enableCC","enable a cluster controller"});
		commands.put("disableCC", new String[] {"disableCC","disable a cluster controller"});
		
		commands.put("walrus", new String[] {"describeWalruses","show all walrus components"});
		commands.put("registerWalrus", new String[] {"registerWalrus","register a walrus"});
		commands.put("deregisterWalrus", new String[] {"deregisterWalrus","deregister a walrus"});
		commands.put("enableWalrus", new String[] {"enableWalrus","enable a walrus"});
		commands.put("disableWalrus", new String[] {"disableWalrus","diable a walrus"});
		
		commands.put("storage", new String[] {"describeStorageControllers","show all storage controller"});
		commands.put("registerSC", new String[] {"registerSC","register a storage controller"});
		commands.put("deregisterSC", new String[] {"deregisterSC","deregister a storage controller"});
		commands.put("enableSC", new String[] {"enableSC","enable storage controller"});
		commands.put("disableSC", new String[] {"disableSC","disable storage controller"});
		
		commands.put("vmwarebroker", new String[] {"describeVmwareBrokers","show all vmware brokers"});
		commands.put("registerVMB", new String[] {"registerVMB","register a vmware broker"});
		commands.put("deregisterVMB", new String[] {"deregisterVMB","deregister a vmware broker"});
		commands.put("enableVMB", new String[] {"enableVMB","enable a vmware broker"});
		commands.put("disableVMB", new String[] {"disableVMB","disable a vmware broker"});
		
		commands.put("node", new String[] {"describeNodes","show all node controllers"});
		commands.put("registerNode", new String[] {"registerNode","register a node controller"});
		commands.put("deregisterNode", new String[] {"deregisterNode","deregister a node controller"});
		commands.put("enableNode", new String[] {"enableNode","enable a node controller"});
		commands.put("disableNode", new String[] {"disableNode","disable a node controller"});
		
		commands.put("blockmanager", new String[] {"getBlockManager","show the block manager of a cluster"});
		commands.put("blockmanagerParameter", new String[] {"getBlockManagerDetails","show all properties of a block manager"});
		commands.put("modifyBlockmanager", new String[] {"modifyBlockManager","modify the block manager of a cluster"});
		commands.put("modifyBlockmanagerParameters", new String[] {"modifyBlockManagerProperties","modify the properties of block manager "});
		
		commands.put("networkmode", new String[] {"getNetworkMode","show the network mode of cloud"});
		commands.put("networkmodeParameter", new String[] {"getNetworkModeProperties","show all properties of network mode"});
		commands.put("modifyNetworkmode", new String[] {"modifyNetworkMode","modify the network mode of cloud"});
		commands.put("modifyNetworkmodeParameters", new String[] {"modifyNetworkModeProperties","modify the properties of network mode "});
		
		commands.put("vmType", new String[] {"describeInstanceTypes","show all vm types"});
		commands.put("modifyVmType", new String[] {"modifyInstanceType","modify a vm type"});
		
		commands.put("EBS", new String[] {"getEbsServiceProperties","show all ebs service properties"});
		commands.put("modifyEBS", new String[] {"modifyEbsServiceProperty","modify a ebs service property"});
		
		commands.put("DNS", new String[] {"getDNSServiceProperties","show all dns service properties"});
		commands.put("modifyDNS", new String[] {"modifyDNSServiceProperty","modify a dns service property"});
		
		commands.put("S3", new String[] {"getS3ServiceProperties","show all s3 service properties"});
		commands.put("modifyS3", new String[] {"modifyS3ServiceProperty","modify a s3 service property"});
		
		commands.put("balancer", new String[] {"getLoadBalancerImage","show load balancer image"});
		commands.put("modifyBalancer", new String[] {"modifyLoadBalancerImage","modify load balancer image"});
		commands.put("installBalancer", new String[] {"installBalancerImage","install load balancer image"});
		
		commands.put("properties", new String[] {"getCloudProperties","show all cloud properties"});
		commands.put("modifyProperty", new String[] {"modifyCloudProperty","modify a coud property"});
		
		//image
		commands.put("image", new String[] {"getImages","query the image"});
		commands.put("modifyImage", new String[] {"modifyImages","modify a image"});
		commands.put("deregisterImage", new String[] {"deregisterImages","deregister a image"});

        //manage access
		commands.put("createAccount", new String[] {"createAccount","create a account"});
		commands.put("deleteAccount", new String[] {"deleteAccount","delete a account"});
		commands.put("getAccountList", new String[] {"getAccountList","list account"});
		commands.put("addAccountPolicy", new String[] {"addAccountPolicy","add policy for account"});
		commands.put("deleteAccountPolicy", new String[] {"delAccountPolicy","delete policy for account"});
		commands.put("getAccountPolicyList", new String[] {"getAccountPolicyList","list account policy"});
	
		commands.put("createGroup", new String[] {"createGroup","create a group"});
		commands.put("deleteGroup", new String[] {"deleteGroup","delete a group"});
		commands.put("getGroupList", new String[] {"getGroupList","list groups"});
		commands.put("addGroupPolicy", new String[] {"addGroupPolicy","add policy for group"});
		commands.put("deleteGroupPolicy", new String[] {"delGroupPolicy","delete policy for group"});
		commands.put("getGroupPolicyList", new String[] {"getGroupPolicyList","list group policy"});
		commands.put("addGroupUser", new String[] {"addGroupUser","add user to group"});
		commands.put("deleteGroupUser", new String[] {"delGroupUser","delete user from group"});
		commands.put("getGroupUserList", new String[] {"getGroupUserList","list users of group"});
		
		commands.put("createUser", new String[] {"createUser","create a user"});
		commands.put("deleteUser", new String[] {"deleteUser","delete a user"});
		commands.put("getUserList", new String[] {"getUserList","list users"});
		commands.put("addUserPolicy", new String[] {"addUserPolicy","add policy for group"});
		commands.put("deleteUserPolicy", new String[] {"delUserPolicy","delete policy for user"});
		commands.put("getUserPolicyList", new String[] {"getUserPolicyList","list user policy"});
		commands.put("getUserGroupList", new String[] {"getUserGroupList","list user groups"});
		commands.put("getUserCerts", new String[] {"getUserCerts","list certificates of user"});
		commands.put("delUserCert", new String[] {"delUserCert","delete a user certificate"});
		commands.put("getUserKeys", new String[] {"getUserKeys","list keys of user"});
		commands.put("delUserKeys", new String[] {"delUserKeys","delete a user key"});
		commands.put("addUserKeys", new String[] {"addUserKeys","delete a user key"});
		
		commands.put("modifyPassword", new String[] {"modifyUserPassword","modify user password"});
		commands.put("addPassword", new String[] {"addUserPassword","add password for new user"});
		commands.put("downloadUserCred", new String[] {"downloadUserCreds","download the user credentials"});
		
		//reporting
		commands.put("instanceReport", new String[] {"genInstanceReport","generate intance report"});
		commands.put("capacityReport", new String[] {"genCapacityReport","generate capacity report"});
		commands.put("s3Report", new String[] {"genS3Report","generate object storage usage report"});
		commands.put("ipReport", new String[] {"genIpReport","generate public Ip isage report"});
		commands.put("volumeReport", new String[] {"genVolumeReport","generate EBS volume usage report"});
		
		//monitor component
		commands.put("monitorComponent", new String[] {"monitorComponent","monitor the components"});
		
		//dashboard
		commands.put("dashBoard", new String[] {"dashBoard","display the dashboard"});
		
		commands.put("sshclient", new String[] {"testSshClient","ssh client"});
		commands.put("eucalyptusconfig", new String[] {"eucalytusConfiguration","change eucalyptus conf"});
		
	}
	
	public void dashBoard(EucaAdminConsole console) {

		EucaConsoleMessage result = console.getDashBoardData();
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void monitorComponent(EucaAdminConsole console) {

		EucaConsoleMessage result = console.monitorComponent();
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void genS3Report(EucaAdminConsole console) {
		java.util.Date startDay = new java.util.Date();
		java.util.Date endDay = new java.util.Date();
		EucaConsoleMessage result = console.genS3Report(startDay, endDay);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void genIpReport(EucaAdminConsole console) {
		java.util.Date startDay = new java.util.Date();
		java.util.Date endDay = new java.util.Date();
		EucaConsoleMessage result = console.genIpReport(startDay, endDay);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void genVolumeReport(EucaAdminConsole console) {
		java.util.Date startDay = new java.util.Date();
		java.util.Date endDay = new java.util.Date();
		EucaConsoleMessage result = console.genVolumeReport(startDay, endDay);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void genCapacityReport(EucaAdminConsole console) {
		java.util.Date startDay = new java.util.Date();
		java.util.Date endDay = new java.util.Date();
		EucaConsoleMessage result = console.genCapacityReport(startDay, endDay);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void genInstanceReport(EucaAdminConsole console) {
		java.util.Date startDay = new java.util.Date();
		java.util.Date endDay = new java.util.Date();
		EucaConsoleMessage result = console.genInstanceReport(startDay, endDay);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void downloadUserCreds(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.downloadUserCredentials("demotest", "admin", "/tmp/xxx/admin.zip");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void addUserPassword(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.addUserLogin("demotest","demo","password");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void modifyUserPassword(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.modifyUserLogin("demotest","demo","password");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void addUserKeys(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.addUserKeys("demotest","demo");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void delUserKeys(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delUserKeys("demotest","demo","NFLWDQGJ4VAXFKYPE4ZKG");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void getUserKeys(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getUserKeys("demotest", "admin");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void delUserCert(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delUserCerts("demotest","demo","X12345678940");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void getUserCerts(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getUserCerts("demotest", "admin");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void getUserGroupList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getUserGroupList("demotest","demo");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void delUserPolicy(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delUserPolicy("demotest","demo", "fullPermission");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
    public void addUserPolicy(EucaAdminConsole console) {
		String policyContent = "{\"Version\":\"2013-10-11\",\"Statement\":[{\"Sid\":\"1\",\"Effect\":\"Allow\",\"Action\":\"*\",\"Resource\":\"*\"}]}";
		EucaConsoleMessage result = console.addUserPolicy("demotest","demo", "fullPermission", policyContent);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
    public void getUserPolicyList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getUserPolicyList("demotest","demo");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void getUserList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getUserList("demotest");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void deleteUser(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delUser("demotest","demo");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void createUser(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.createUser("demotest","dev", "demo");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	//group
	public void delGroupUser(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delGroupUser("demotest", "dev", "demo");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void addGroupUser(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.addGroupUser("demotest", "dev", "demo");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void getGroupUserList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getGroupUserList("demotest", "dev");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void delGroupPolicy(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delGroupPolicy("demotest","dev", "fullPermission");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
    public void addGroupPolicy(EucaAdminConsole console) {
		String policyContent = "{\"Version\":\"2013-10-11\",\"Statement\":[{\"Sid\":\"1\",\"Effect\":\"Allow\",\"Action\":\"*\",\"Resource\":\"*\"}]}";
		EucaConsoleMessage result = console.addGroupPolicy("demotest","dev", "fullPermission", policyContent);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
    public void getGroupPolicyList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getGroupPolicyList("demotest","dev");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void getGroupList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getGroupList("demotest");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void deleteGroup(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delGroup("demotest","dev");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void createGroup(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.createGroup("demotest", "dev");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	//--acccount
	public void delAccountPolicy(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delAccountPolicy("demotest", "test");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
    public void addAccountPolicy(EucaAdminConsole console) {
		String policyContent = "{\"Version\":\"2013-10-11\",\"Statement\":[{\"Sid\":\"1\",\"Effect\":\"Limit\",\"Action\":\"*\",\"Resource\":\"*\"}]}";
		EucaConsoleMessage result = console.addAccountPolicy("demotest", "test", policyContent);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
    public void getAccountPolicyList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getAccountPolicyList("demotest");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void getAccountList(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getAccountList();
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	public void deleteAccount(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.delAccount("demotest");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	public void createAccount(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.createAccount("demotest");
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	/**
	 * example to user login authentication
	 * @param console
	 */
	public void login(EucaAdminConsole console) {
		EucaConsoleMessage result = console.login("eucalyptus","admin","password");
		
		System.out.println(result.toJson().toString());
		result = console.login("eucalyptus","admin","test@demo");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to show all components;
	 * @param console
	 */
	public void describeComponents(EucaAdminConsole console) {
		EucaConsoleMessage result = console.describeServices(null, null, null, null);
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to only show cloud controller
	 * @param console
	 */
	public void describeCloudControllers(EucaAdminConsole console) {
		EucaConsoleMessage result = console.describeServices("cluster", null, null, null);
		System.out.println(result.toJson().toString());
		//result.printMessage();
	}
	
	/**
	 * example to only show walrus
	 * @param console
	 */
	public void describeWalruses(EucaAdminConsole console) {
		EucaConsoleMessage result = console.describeServices("walrus", null, null, null);
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to only show cluster controller
	 * @param console
	 */
	public void describeClusters(EucaAdminConsole console) {
		EucaConsoleMessage result = console.describeServices("cluster", null, null, null);
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to only show storage controller
	 * @param console
	 */
	public void describeStorageControllers(EucaAdminConsole console) {
		EucaConsoleMessage result = console.describeServices("storage", null, null, null);
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	

	/**
	 * example to only show node controllers
	 * @param console
	 */
	public void describeNodes(EucaAdminConsole console) {
		EucaConsoleMessage result = console.describeServices("node", null, null, null);
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to only show vmbroker
	 * @param console
	 */
	public void describeVmwareBrokers(EucaAdminConsole console) {
		EucaConsoleMessage result = console.describeServices("vmwarebroker", null, null, null);
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to register Cloud Controller
	 * @param console
	 */
	public void registerCLC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.registerCLC("192.168.1.100", "clc_02","hostUser","password");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to deregister Cloud Controller
	 * @param console
	 */
	public void deregisterCLC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.deregisterCLC("192.168.1.100");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to enable Cloud Controller
	 * @param console
	 */
	public void enableCLC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.enableService("clc_02");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to disable Cloud Controller
	 * @param console
	 */
	public void disableCLC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.disableService("clc_02");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	
	/**
	 * example to register Walrus
	 * @param console
	 */
	public void registerWalrus(EucaAdminConsole console) {
		EucaConsoleMessage result = console.registerWalrus("192.168.1.100", "walrus_01","hostUser","password");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to deregister Walrus
	 * @param console
	 */
	public void deregisterWalrus(EucaAdminConsole console) {
		EucaConsoleMessage result = console.deregisterWalrus("walrus_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to enable walrus
	 * @param console
	 */
	public void enableWalrus(EucaAdminConsole console) {
		EucaConsoleMessage result = console.enableService("walrus_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to disable walrus
	 * @param console
	 */
	public void disableWalrus(EucaAdminConsole console) {
		EucaConsoleMessage result = console.disableService("walrus_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	
	/**
	 * example to register Cluster controller
	 * @param console
	 */
	public void registerCC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.registerCC("192.168.1.100", "cc_01", "cluster01","hostUser","password");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to deregister Cluster Controller
	 * @param console
	 */
	public void deregisterCC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.deregisterCC("cc_01", "cluster01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to enable Cluster Controller
	 * @param console
	 */
	public void enableCC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.enableService("cc_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to disable Cluster Controller
	 * @param console
	 */
	public void disableCC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.disableService("cc_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to register storage controller
	 * @param console
	 */
	public void registerSC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.registerSC("192.168.1.100", "sc_01", "cluster01","hostUser","password");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to deregister storage Controller
	 * @param console
	 */
	public void deregisterSC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.deregisterSC("sc_01", "cluster01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to enable storage Controller
	 * @param console
	 */
	public void enableSC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.enableService("sc_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to disable storage Controller
	 * @param console
	 */
	public void disableSC(EucaAdminConsole console) {
		EucaConsoleMessage result = console.disableService("sc_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to register vmware broker
	 * @param console
	 */
	public void registerVMB(EucaAdminConsole console) {
		EucaConsoleMessage result = console.registerVMB("192.168.1.100", "vmb_01", "cluster01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to deregister vmware broker
	 * @param console
	 */
	public void deregisterVMB(EucaAdminConsole console) {
		EucaConsoleMessage result = console.deregisterVMB("vmb_01", "cluster01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to enable vmware broker
	 * @param console
	 */
	public void enableVMB(EucaAdminConsole console) {
		EucaConsoleMessage result = console.enableService("vmb_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to disable vmware broker
	 * @param console
	 */
	public void disableVMB(EucaAdminConsole console) {
		EucaConsoleMessage result = console.disableService("vmb_01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to register node controller
	 * @param console
	 */
	public void registerNode(EucaAdminConsole console) {
		EucaConsoleMessage result = console.registerNode("192.168.1.101","cluster01","hostUser","password");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to deregister node controller
	 * @param console
	 */
	public void deregisterNode(EucaAdminConsole console) {
		EucaConsoleMessage result = console.deregisterNode("192.168.1.101","cluster01");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to enable node controller
	 * @param console
	 */
	public void enableNode(EucaAdminConsole console) {
		EucaConsoleMessage result = console.enableService("192.168.1.101");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to disable node controller
	 * @param console
	 */
	public void disableNode(EucaAdminConsole console) {
		EucaConsoleMessage result = console.disableService("192.168.1.101");
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to show one and all instance Type
	 * @param console
	 */
	public void describeInstanceTypes(EucaAdminConsole console) {
		// show a one instance type
		ArrayList<String> types = new ArrayList<String>();
		types.add("m1.small");
		EucaConsoleMessage result = console.getInstanceTypes(types);
		System.out.println(result.toJson().toString());
		
		result = console.getInstanceTypes(null);
		//result.printMessage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * example to modify instance Type
	 * @param console
	 */
	public void modifyInstanceType(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.modifyInstanceTypes("m1.small", 2, 1024, 20);
		System.out.println(result.toJson().toString());
		
	}
	
	/**
	 * example to all of show block manager of one cloud cluster.
	 * @param console
	 */
	public void getBlockManager(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.getBlockManager("cluster01");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 *  example to show parameter of a block manager
	 */
	public void getBlockManagerDetails(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.getBlockManagerProperties("cluster01", "netapp");
		System.out.println(result.toJson().toString());
		
		result = console.getBlockManagerProperties("cluster01", "equallogic");
		System.out.println(result.toJson().toString());
		
		result = console.getBlockManagerProperties("cluster01", "clvm");
		System.out.println(result.toJson().toString());
		
		result = console.getBlockManagerProperties("cluster01", "emc-vnx");
		System.out.println(result.toJson().toString());
		
		result = console.getBlockManagerProperties("cluster01", "das");
		System.out.println(result.toJson().toString());
		
		result = console.getBlockManagerProperties("cluster01", "overlay");
		System.out.println(result.toJson().toString());
		
	}
	
	/**
	 * modify the block manager of a cluster
	 * @param console
	 */
	public void modifyBlockManager(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.modifyBlockManager("cluster01","clvm");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * modify properties of the block manager
	 * @param console
	 */
	public void modifyBlockManagerProperties(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.modifyBlockManagerProperty("cluster01", "sanhost", "192.168.1.124");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * get the network mode of cloud
	 * @param console
	 */
	public void getNetworkMode(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.getNetworkMode();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * get the network mode of cloud
	 * @param console
	 */
	public void getNetworkModeProperties(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.getNetworkModeProperties("MANAGED-NOVLAN");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 *  modify the network mode of cloud
	 * @param console
	 */
	public void modifyNetworkMode(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.modifyNetworkMode("manage");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 *  modify the network mode properties
	 * @param console
	 */
	public void modifyNetworkModeProperties(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.modifyNetwrokModeProperty("manage", "vnet_publicips", "192.168.1.0/24");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * get cloud properties
	 * @param console
	 */
	public static void getCloudProperties(EucaAdminConsole console) {
		
		//all properties
		EucaConsoleMessage ret = console.describeProperties(null);
		System.out.println(ret.toJson().toString());
		
		//one or more properties
		ArrayList<String> properties = new ArrayList<String>();
		properties.add("cloud.cluster.disabledinterval");
		ret = console.describeProperties(properties);
		System.out.println(ret.toJson().toString());
	}
	
	/**
	 * show how to modify cloud properties
	 * @param console
	 */
	public static void modifyCloudProperty(EucaAdminConsole console) {
		
		//all properties
		EucaConsoleMessage ret = console.modifyProperty("cloud.cluster.disabledinterval ", "15");
		System.out.println(ret.toJson().toString());
	}
	
	/**
	 * show all ebs services properties
	 * @param console
	 */
	public void getEbsServiceProperties(EucaAdminConsole console) {
		// show a one instance type
		EucaConsoleMessage result = console.getEbsServiceProperties("cluster001");
		System.out.println(result.toJson().toString());
	}
	/**
	 * show how to modify a ebs service property
	 * @param console
	 */
	public void modifyEbsServiceProperty(EucaAdminConsole console) {
		EucaConsoleMessage result = console.modifyEbsServiceProperty("cluster001", "maxtotalvolumesizeingb", "100");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * show all S3 services properties
	 * @param console
	 */
	public void getS3ServiceProperties(EucaAdminConsole console) {

		EucaConsoleMessage result = console.getS3ServiceProperties();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * show how to modify a S3 service property
	 * @param console
	 */
	public void modifyS3ServiceProperty(EucaAdminConsole console) {

		EucaConsoleMessage result = console.modifyS3ServiceProperty("storagemaxbucketsizeinmb", "5000");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * show all DNS services properties
	 * @param console
	 */
	public void getDNSServiceProperties(EucaAdminConsole console) {

		EucaConsoleMessage result = console.getDNSServiceProperties();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * show how to modify DNS service property
	 * @param console
	 */
	public void modifyDNSServiceProperty(EucaAdminConsole console) {

		EucaConsoleMessage result = console.modifyS3ServiceProperty("use_instance_dns", "true");
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * show load balancer image
	 * @param console
	 */
	public void getLoadBalancerImage(EucaAdminConsole console) {
		EucaConsoleMessage result = console.getLoadBalancerImage();
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * show how to modify image of load balancer
	 * @param console
	 */
	public void modifyLoadBalancerImage(EucaAdminConsole console) {
		EucaConsoleMessage result = console.modifyLoadBalancerImage("emi-12345678");
		System.out.println(result.toJson().toString());
	}
	/**
	 * show how to install load balancer image
	 * @param console
	 */
	public void installBalancerImage(EucaAdminConsole console) {
		EucaConsoleMessage result = console.installBalancerImage();
		System.out.println(result.toJson().toString());
	}
	/**
	 * get image information
	 * @param console
	 */
	public void getImages(EucaAdminConsole console) {
		// show all images;
		EucaConsoleMessage result = console.getImages(null);
		System.out.println(result.toJson().toString());
		
		//show one image
		ArrayList<String> images = new ArrayList<String>();
		images.add("emi-437D397A");
		result = console.getImages(images);
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * modify image attributes
	 * @param console
	 */
	public void modifyImages(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.modifyImage("emi-437D397A", true);
		System.out.println(result.toJson().toString());
	}
	
	/**
	 * deregister a image
	 * @param console
	 */
	public void deregisterImages(EucaAdminConsole console) {
		
		EucaConsoleMessage result = console.deregisterImage("emi-437D397A");
		System.out.println(result.toJson().toString());
	}
	
	public static void usage() {
		
		System.out.println("usage: consoleExample command");
		System.out.println("       command:");
		java.util.Set<String> keys = consoleExample.commands.keySet();
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            String key = (String) it.next();
            String[] values = consoleExample.commands.get(key);
            System.out.println("  "+ String.format("%1$-30s", key) + "  - " + values[1]);
        }
		
		System.exit(1);
	}
	
	public void testSshClient(EucaAdminConsole console) {
		try {
			Ssh2Client client = new Ssh2Client("192.168.1.100", "root",
					"xu951231");
			System.out.println(client.testConnectivity());
			String ret = client.downLoadFile(
					"/var/lib/eucalyptus/keys/cloud-pk.pem", "/tmp/");
			if (ret == null)
				System.out.println("can't donwload file");
			ret = client.upLoadFile("/tmp/cloud-pk.pem", "/opt/");
			if (ret == null)
				System.out.println("can't upload file");
		} catch (Exception e) {
			System.out.println(e.toString() + e.getStackTrace());
		}
	}
	
	public void eucalytusConfiguration(EucaAdminConsole console) {
		try {
			
			EucalyptusConfiguration conf = new EucalyptusConfiguration();
			conf.load("/opt/eucalyptus.conf");
			conf.setValue("NODES", "192.168.1.1",null);
			conf.remove("VNET_MODE");
			conf.setValue("NATHAN", "haha","test by add new item");
			conf.setValue("NATHAN2", "haha",null);
			conf.setValue("DISABLE_TUNNELING", "Y", null);
			conf.save("/opt/eucalyptus.conf.new");
			
			RemoteEucalyptusConfiguration rconf = new RemoteEucalyptusConfiguration();
			rconf.load("192.168.1.100", "root", "xu951231");
			System.out.println(rconf.getValue("NODES"));
			rconf.setValue("NODES", "192.168.1.101 192.168.1.102", null);
			rconf.save("192.168.1.100", "root", "xu951231");
			
			
		} catch (Exception e) {
			System.out.println(e.toString() + e.getStackTrace());
		}
	}
    /**
     * 	
     * @param args
     * @throws Exception
     */
	public static void main(final String[] args) throws Exception {
		
		//consoleExample.commands.
		
		if (args.length !=1 || args[0].equals("help") ) {
			consoleExample.usage();
		}
		EucaAdminConsole console = new EucaAdminConsole();
		console.setUsemock(false);
		//console.configure("10.10.1.1",8773,"1234567fgsdfgj","xvdfakjdafdkjfksajfsadjdfsafkd");
		EucaAdminConsoleConf config = new EucaAdminConsoleConf();
		config.setClcIp("192.168.1.100");
		config.setClcPort(8773);
		config.setAccessKey("IU5NTJEYZJ1DMBB84UKAF");
		config.setSecretKey("8Ek1e2tHO0D6gwaCNodKMSd3qUdWJ9wm3p2iqvEp");
		config.setClcUser("root");
		config.setClcPassword("xu951231");
		config.setCcDefaultUser("root");
		config.setCcDefaultUserPassword("xu951231");
		
		//console.configure("192.168.1.100",8773,"IU5NTJEYZJ1DMBB84UKAF","8Ek1e2tHO0D6gwaCNodKMSd3qUdWJ9wm3p2iqvEp");
		console.setConfiguration(config);
		//consoleExample example = new consoleExample();
		
		Class<consoleExample> c = consoleExample.class;
		Object obj=c.newInstance();
		java.lang.reflect.Method func = c.getMethod(consoleExample.commands.get(args[0])[0],new Class[]{EucaAdminConsole.class});
		Object[] arguments = {console};
		func.invoke(obj, arguments);
		
	}
}
