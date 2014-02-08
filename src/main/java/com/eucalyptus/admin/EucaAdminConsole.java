package com.eucalyptus.admin;

import java.io.Serializable;
import java.util.ArrayList;

import com.eucalyptus.admin.console.EucaConsoleMessage;
import com.eucalyptus.admin.console.JSONMessage;
import java.util.Date;

public class EucaAdminConsole {
	
	public static final String CLCServiceType = "eucalyptus";
	public static final String SCServiceType = "storage";
	public static final String CCServiceType = "cluster";
	public static final String WalrusServiceType = "walrus";
	public static final String VMBServiceType = "vmbroker";
	public static final String NodeServiceType = "node";
	
	private EucaAdminConsoleConf configuration;
	private boolean usemock = false;
	private String clcIp;
	private Integer clcPort;
	private String accessKey;
	private String secretKey;
	
	private EucaAdminConsoleApi executer; 
	
	public EucaAdminConsole() {
		this.executer = new EucaAdminConsoleImpl();
	}
    
	public void setConfiguration(EucaAdminConsoleConf config) {
		this.configuration = config;
		this.executer.setup(this.configuration);
	}
	
	public EucaAdminConsoleConf getConfiguration() {
		return this.configuration ;
	}

	/**
	 * Set this value to True if you wish to use mock data instead of talking to the CLC.
	 * @param value
	 */
	public void setUsemock(boolean value) {
		this.usemock = value;
	}
	
	
	/**
	 * return the value of mock data setting
	 * @return
	 */
	public boolean getUsemock() {
		return this.usemock;
	}
	
	/**
     * login into the CLC for user authentication 
     * @param account
     * @param user
     * @param password
     * @return eucaConsoleMessage
     */
	public EucaConsoleMessage login(String account, String user, String password) {
		
		if (usemock)
			return EucaAdminConsoleMockdata.login(account, user, password);
		return executer.login(account, user, password);
		/*
		EucaConsoleMessage ret = new EucaConsoleMessage();
		//todo		
		return ret; */
	}
	
	/**
	 * configure the adminConsole API client
	 * @param Ip
	 * @param port
	 */
	public void configure(String Ip,Integer port, String accessKey, String secretKey) {
		this.clcIp = Ip;
		this.clcPort = port;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.executer.configure(Ip, port, accessKey, secretKey);
	}
	
	/**
	 * query CloudProperties
	 * @param properties
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage describeProperties(ArrayList<String> properties) {
		if (usemock)
			return EucaAdminConsoleMockdata.describeProperties(properties);
		return executer.describeProperties(properties);
	}
	
	/**
	 * modify the value of a sepecify cloud property
	 * @param property
	 * @param value
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage modifyProperty(String property, String value) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyProperty(property, value);
		return this.executer.modifyProperty(property, value);
	}
	

	/**
	 * query the component & services status
	 * @param byServiceType
	 * @param byHost
	 * @param byState
	 * @param byPartition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage describeServices(String byServiceType,String byHost,String byState,String byPartition) {
		if (usemock)
			return EucaAdminConsoleMockdata.describeServices(byServiceType, byHost, byState, byPartition);
		return this.executer.describeServices(byServiceType, byHost, byState, byPartition);
	}
	
	/**
	 * register cloud controller component
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerCLC(String host, String name,String user, String password) {
		if (usemock)
			return EucaAdminConsoleMockdata.registerCLC(host, name,user,password);
		return this.executer.registerCLC(host, name,user,password);
	}
	/**
	 * de-register cloud controller component
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterCLC(String name) {
		if (usemock)
			return EucaAdminConsoleMockdata.deregisterCLC(name);
		return this.executer.deregisterCLC(name);
	}
	/**
	 * Register Wlarus component
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerWalrus(String host, String name,String user, String password) {
		if (usemock)
			return EucaAdminConsoleMockdata.registerWalrus(host, name,user,password);
		return this.executer.registerWalrus(host, name,user,password);
	}
	/**
	 * deregister walrus component
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterWalrus(String name) {
		if (usemock)
			return EucaAdminConsoleMockdata.deregisterWalrus(name);
		return this.executer.deregisterWalrus(name);
	}
	/**
	 * register cloud cluster component
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerCC(String host, String name,String partition,String user, String password) {
		if (usemock)
			return EucaAdminConsoleMockdata.registerCC(host, name, partition,user,password);
		return this.executer.registerCC(host, name, partition,user,password);
	}
	/**
	 * de-register cloud cluster component
	 * @param name
	 * @param partition
	 * @return cloud controller
	 */
	public EucaConsoleMessage deregisterCC(String name,String partition) {
		if (usemock)
			return EucaAdminConsoleMockdata.deregisterCC(name, partition);
		return this.executer.deregisterCC(name, partition);
	}
	
	/**
	 * register storage controller component
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerSC(String host, String name,String partition,String user, String password) {
		if (usemock)
			return EucaAdminConsoleMockdata.registerSC(host, name, partition,user,password);
		return this.executer.registerSC(host, name, partition,user,password);
	}
	
	/**
	 * de-register storage controller component
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterSC(String name,String partition) {
		if (usemock)
			return EucaAdminConsoleMockdata.deregisterSC(name, partition);
		return this.executer.deregisterSC(name, partition);
	}
	
	/**
	 * register vmware broker
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerVMB(String host, String name,String partition) {
		if (usemock)
			return EucaAdminConsoleMockdata.registerVMB(host, name, partition);
		return this.executer.registerVMB(host, name, partition);
	}
	/**
	 * de-register vmware broker
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterVMB(String name,String partition) {
		if (usemock)
			return EucaAdminConsoleMockdata.deregisterVMB(name, partition);
		return this.executer.deregisterVMB(name, partition);
	}
	
	/**
	 * register node controller
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerNode(String host,String partition,String user, String password) {
		if (usemock)
			return EucaAdminConsoleMockdata.registerNode(host, partition,user,password);
		return this.executer.registerNode(host, partition,user,password);
	}
	/**
	 * de-register node controller
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterNode(String host,String partition) {
		if (usemock)
			return EucaAdminConsoleMockdata.deregisterNode(host, partition);
		return this.executer.deregisterNode(host, partition);
	}
	/**
	 * enable a service component
	 * @param name - name of the component
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage enableService(String name) {
		if (usemock)
			return EucaAdminConsoleMockdata.enableService(name);
		return this.executer.enableService(name);
	}
	
	/**
	 * disable service component
	 * @param name - name of the component
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage disableService( String name) {
		if (usemock)
			return EucaAdminConsoleMockdata.disableService(name);
		return this.executer.disableService(name);
	}
	
	/**
	 * get the all VM types
	 * @param instanceType  - null stand for all instance type
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getInstanceTypes(ArrayList<String> instanceType) {
		if (usemock)
			return EucaAdminConsoleMockdata.getInstanceTypes(instanceType);
		return this.executer.getInstanceTypes(instanceType);
	}
	
    /**
     * modify a instance type
     * @param instanceType
     * @param cpu  - count of vcp
     * @param mem  - memory MB
     * @param disk - disk GB 
     * @return eucaConsoleMessage
     */
	public EucaConsoleMessage modifyInstanceTypes(String instanceType,int cpu,int mem,int disk) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyInstanceTypes(instanceType, cpu, mem, disk);
		return this.executer.modifyInstanceTypes(instanceType, cpu, mem, disk);
	}
	
    /**
     * return a block manager of a cluster
     * @param cluster
     * @return
     */
	public EucaConsoleMessage getBlockManager(String cluster) {
		ArrayList<String> clusteres = new ArrayList<String>();
		//the name of block manager: clustername.storage..blockstoragemanager
		clusteres.add("cluster01.storage.blockstoragemanager");
		if (usemock)
			return EucaAdminConsoleMockdata.describeProperties(clusteres);
		return this.executer.getBlockManager(cluster);
	}
	
	/**
     * modify the block manager of cluster
     * @param cluster
     * @return
     */
	public EucaConsoleMessage modifyBlockManager(String cluster, String manager) {
		String property = cluster +".storage.blockstoragemanager";
		
		if (usemock)
			return EucaAdminConsoleMockdata.modifyProperty(property, manager);
		return this.executer.modifyBlockManager(cluster, manager);
	}
	
	/**
     * return a block manager of a cluster
     * @param cluster
     * @return
     */
	public EucaConsoleMessage getBlockManagerProperties(String cluster, String manager) {

		if (usemock)
			return EucaAdminConsoleMockdata.getBlockManagerProperties(cluster, manager);
		return this.executer.getBlockManagerProperties(cluster, manager);
	}
	
	/**
     * modify the parameters of block manager
     * @param cluster
     * @return
     */
	public EucaConsoleMessage modifyBlockManagerProperty(String cluster, String para, String value) {
		String property = cluster +".storage." + para;
		if (usemock)
			return EucaAdminConsoleMockdata.modifyProperty(property, value);
		return this.executer.modifyBlockManagerProperty(cluster, para, value);
	}
	
	public EucaConsoleMessage getNetworkMode() {
		ArrayList<String> mode = new ArrayList<String>();
		mode.add("networkmode");	
		if (usemock)
			return EucaAdminConsoleMockdata.describeProperties(mode);
		return this.executer.getNetworkMode();
	}
	
	/**
	 * return the parameters for a network mode
	 * @param mode
	 * @return
	 */
	public EucaConsoleMessage getNetworkModeProperties(String mode) {
		if (usemock)
			return EucaAdminConsoleMockdata.getNetworkModeProperties(mode);
		return this.executer.getNetworkModeProperties(mode);
	}
	
	/**
     * modify the network mode
     * @param cluster
     * @return
     */
	public EucaConsoleMessage modifyNetworkMode(String mode) {
		
		if (usemock)
			return EucaAdminConsoleMockdata.modifyProperty("networkmode", mode);
		return this.executer.modifyNetworkMode(mode);
	}
	/**
	 * modify property of network mode
	 * @param mode
	 * @param para
	 * @param value
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage modifyNetwrokModeProperty(String mode, String para, String value) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyProperty(para, value);
		return this.executer.modifyNetwrokModeProperty(mode, para, value);
	}
	
	/**
	 * return the properties of EBS services (one cluster)
	 * @param cluster
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage getEbsServiceProperties(String cluster) {
		if (usemock)
			return EucaAdminConsoleMockdata.getEbsServiceProperties(cluster);
		return this.executer.getEbsServiceProperties(cluster);
	}
	/**
	 * Modify the property of EBS service
	 * @param cluster
	 * @param para
	 * @param value
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage modifyEbsServiceProperty(String cluster,String para,String value) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyEbsServiceProperties(cluster, para, value);
		return this.executer.modifyEbsServiceProperty(cluster, para, value);
	}
	
	/**
	 * return the DNS service properties
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage getDNSServiceProperties() {
		if (usemock)
			return EucaAdminConsoleMockdata.getDNSServiceProperties();
		return this.executer.getDNSServiceProperties();
	}
	
    /**
     * Modify the property of DNS service
     * @param property
     * @param value
     * @return
     */
	public EucaConsoleMessage modifyDNSServiceProperty(String property, String value) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyDNSServiceProperty(property, value);
		return this.executer.modifyDNSServiceProperty(property, value);
	}
	
	/**
	 * return the properties of S3
	 * @return
	 */
	public EucaConsoleMessage getS3ServiceProperties() {
		if (usemock)
			return EucaAdminConsoleMockdata.getS3ServiceProperties();
		return this.executer.getS3ServiceProperties();
	}
	
	/**
     * Modify the property of S3 service
     * @param property
     * @param value
     * @return
     */
	
	public EucaConsoleMessage modifyS3ServiceProperty(String property, String value) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyS3ServiceProperty(property, value);
		return this.executer.modifyS3ServiceProperty(property, value);
	}
	
	/**
	 * modify image of the load balancer service
	 * @param image
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage modifyLoadBalancerImage(String image) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyLoadBalancerImage(image);
		return this.executer.modifyLoadBalancerImage(image);
		
	}
    /**
     * get the image of load balancer
     * @return EucaConsoleMessage
     */
    public EucaConsoleMessage getLoadBalancerImage() {
    	if (usemock)
			return EucaAdminConsoleMockdata.getLoadBalancerImage();
    	return this.executer.getLoadBalancerImage();
	}
    /**
     * Install the load balancer
     * @return EucaConsoleMessage
     */
    public EucaConsoleMessage installBalancerImage() {
		
    	if (usemock)
			return EucaAdminConsoleMockdata.installBalancerImage();
    	return this.executer.installBalancerImage();
	}
    
	
	/**
	 * query images
	 * @param images - null stand for all images
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getImages(ArrayList<String> images) {
		if (usemock)
			return EucaAdminConsoleMockdata.getImages(images);
		return this.executer.getImages(images);
	}
	
	/**
	 * deregister images
	 * @param image
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterImage(String image) {
		if (usemock)
			return EucaAdminConsoleMockdata.deregisterImage(image);
		return this.executer.deregisterImage(image);
	}
	
	/**
	 * set the Image as public or private image 
	 * @param image
	 * @param ispublic
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage modifyImage(String image, boolean ispublic) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyImage(image, ispublic);
		return this.executer.modifyImage(image, ispublic);
	}
	
	/**
	 * Create account
	 * @param name - name of the account
	 * @return eucaConsoleMessage (account id & name)
	 */
	public EucaConsoleMessage createAccount(String name) {
		if (usemock)
			return EucaAdminConsoleMockdata.createAccount(name);
		return this.executer.createAccount(name);
	}
	
	/**
	 * delete a account
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delAccount(String name) {
		if (usemock)
			return EucaAdminConsoleMockdata.delAccount(name);
		return this.executer.delAccount(name);
	}
	/**
	 * get account list
	 * @return eucaConsoleMessage (account id & name)
	 */
	public EucaConsoleMessage getAccountList() {
		if (usemock)
			return EucaAdminConsoleMockdata.getAccountList();
		return this.executer.getAccountList();
	}
	
    /**
     * return the all policy list of account
     * @param account
     * @return
     */
	public EucaConsoleMessage getAccountPolicyList(String account) {
		if (usemock)
			return EucaAdminConsoleMockdata.getAccountPolicyList(account);
		return this.executer.getAccountPolicyList(account);
	}
	
	/**
     * return the policy info of a account
     * @param account
     * @param policy  - name of the policy
     * @return eucaConsoleMessage
     */
	public EucaConsoleMessage getAccountPolicy(String account, String policy) {
		if (usemock)
			return EucaAdminConsoleMockdata.getAccountPolicy(account, policy);
		return this.executer.getAccountPolicy(account, policy);
	}
	
	/**
	 * add a policy to a account
	 * @param account
	 * @param policy		- name of the policy
	 * @param policy_conent - content of the policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addAccountPolicy(String account, String policy, String policy_conent) {
		if (usemock)
			return EucaAdminConsoleMockdata.addAccountPolicy(account, policy, policy_conent);
		return this.executer.addAccountPolicy(account, policy, policy_conent);
	}
	
	/**
	 * delete a policy of a account
	 * @param account
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delAccountPolicy(String account, String policy) {
		if (usemock)
			return EucaAdminConsoleMockdata.delAccountPolicy(account, policy);
		return this.executer.delAccountPolicy(account, policy);
	}
	
	/**
	 * add a user group
	 * @param account  - name of account
	 * @param group    - name of group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage createGroup(String account, String group) {
		if (usemock)
			return EucaAdminConsoleMockdata.createGroup(account, group);
		return this.executer.createGroup(account, group);
	}
	
	/**
	 * delete a user group
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delGroup(String account, String group) {
		if (usemock)
			return EucaAdminConsoleMockdata.delGroup(account, group);
		return this.executer.delGroup(account, group);
	}
	
	/**
	 * get list of user group
	 * @param account
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupList(String account) {
		if (usemock)
			return EucaAdminConsoleMockdata.getGroupList(account);
		return this.executer.getGroupList(account);
	}
	
	/**
	 * add a existing user to a existing group
	 * @param account
	 * @param group
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addGroupUser(String account,String group,String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.addGroupUser(account, group, user);
		return this.executer.addGroupUser(account, group, user);
	}
	
	/**
	 * delete a user from existing user group
	 * @param account
	 * @param group
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage delGroupUser(String account,String group,String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.delGroupUser(account, group, user);
		return this.executer.delGroupUser(account, group, user);
	}
	
	/**
	 * get user list of a group
	 * @param account
	 * @param group
	 * @return
	 */
	public EucaConsoleMessage getGroupUserList(String account,String group) {
		if (usemock)
			return EucaAdminConsoleMockdata.getGroupUserList(account, group);
		return this.executer.getGroupUserList(account, group);
	}
	/**
	 * Update the group name
	 * @param account
	 * @param old_group
	 * @param new_group
	 * @return
	 */
	public EucaConsoleMessage modifyGroup(String account,String old_group, String new_group) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyGroup(account, old_group, new_group);
		return this.executer.modifyGroup(account, old_group, new_group);
	}
	
	/**
	 * get all policy list of a group
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupPolicyList(String account,String group) {
		if (usemock)
			return EucaAdminConsoleMockdata.getGroupPolicyList(account, group);
		return this.executer.getGroupPolicyList(account, group);
	}
	
	/**
	 * Get group policy content/info 
	 * @param account
	 * @param group
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupPolicy(String account, String group, String policy) {
		if (usemock)
			return EucaAdminConsoleMockdata.getGroupPolicy(account, group, policy);
		return this.executer.getGroupPolicy(account, group, policy);
	}
	
	/**
	 * add policy to a group
	 * @param account
	 * @param group
	 * @param policy
	 * @param policy_conent
	 * @return
	 */
	public EucaConsoleMessage addGroupPolicy(String account,String group, String policy, String policy_conent) {
		if (usemock)
			return EucaAdminConsoleMockdata.addGroupPolicy(account, group, policy, policy_conent);
		return this.executer.addGroupPolicy(account, group, policy, policy_conent);
	}
	
	/**
	 * delete a policy of group
	 * @param account
	 * @param group
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delGroupPolicy(String account,String group, String policy) {
		if (usemock)
			return EucaAdminConsoleMockdata.delGroupPolicy(account, group, policy);
		return this.executer.delGroupPolicy(account, group, policy);
	}
	
	/**
	 * create a new user 
	 * @param account
	 * @param group  - null stand for no user group
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage createUser(String account,String group, String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.createUser(account, group, user);
		return this.executer.createUser(account, group, user);
	}
	/**
	 * delete a user
	 * @param account
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage delUser(String account, String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.delUser(account, user);
		return this.executer.delUser(account, user);
	}
	
	/**
	 * return user list of a account 
	 * @param account
	 * @return
	 */
	public EucaConsoleMessage getUserList(String account) {
		if (usemock)
			return EucaAdminConsoleMockdata.getUserList(account);
		return this.executer.getUserList(account);
	}
	/**
	 * return group list of a user
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserGroupList(String account,String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.getUserGroupList(account, user);
		return this.executer.getUserGroupList(account, user);
	}
	
	/**
	 * get all policy list of a user
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserPolicyList(String account,String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.getUserPolicyList(account, user);
		return this.executer.getUserPolicyList(account, user);
	}
	
	/**
	 * Get user policy content/info 
	 * @param account
	 * @param user
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserPolicy(String account, String user, String policy) {
		if (usemock)
			return EucaAdminConsoleMockdata.getUserPolicy(account, user, policy);
		return this.executer.getUserPolicy(account, user, policy);
	}
	
	/**
	 * add policy to a user
	 * @param account
	 * @param user
	 * @param policy
	 * @param policy_conent
	 * @return
	 */
	public EucaConsoleMessage addUserPolicy(String account,String user, String policy, String policy_conent) {
		if (usemock)
			return EucaAdminConsoleMockdata.addUserPolicy(account, user, policy, policy_conent);
		return this.executer.addUserPolicy(account, user, policy, policy_conent);
	}
	
	/**
	 * delete a policy of user
	 * @param account
	 * @param user
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delUserPolicy(String account,String user, String policy) {
		if (usemock)
			return EucaAdminConsoleMockdata.delUserPolicy(account, user, policy);
		return this.executer.delUserPolicy(account, user, policy);
	}
	
    /**
     * return the x509 certs of a user 
     * @param account
     * @param user
     * @return eucaConsoleMessage
     */
	public EucaConsoleMessage getUserCerts(String account,String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.getUserCerts(account, user);
		return this.executer.getUserCerts(account, user);
	}
	
	/**
     * add new x509 certs of a user 
     * @param account
     * @param user
     * @return eucaConsoleMessage
     */
	public EucaConsoleMessage addUserCerts(String account,String user,String certBody) {
		if (usemock)
			return EucaAdminConsoleMockdata.addUserCerts(account, user);
		return this.executer.addUserCerts(account, user, certBody);
	}
	
	/**
	 * Delete a user cert
	 * @param account
	 * @param user
	 * @param cert
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delUserCerts(String account,String user,String cert) {
		if (usemock)
			return EucaAdminConsoleMockdata.delUserCerts(account, user, cert);
		return this.executer.delUserCerts(account, user, cert);
	}
	
	/**
	 * return the keys of a user
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserKeys(String account,String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.getUserKeys(account, user);
		return this.executer.getUserKeys(account, user);
	}
	/**
	 * add a new user key
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addUserKeys(String account,String user) {
		if (usemock)
			return EucaAdminConsoleMockdata.addUserKeys(account, user);
		return this.executer.addUserKeys(account, user);
	}
	/**
	 * delete a user key
	 * @param account
	 * @param user
	 * @param key
	 * @return
	 */
	public EucaConsoleMessage delUserKeys(String account,String user,String key) {
		if (usemock)
			return EucaAdminConsoleMockdata.delUserKeys(account, user, key);
		return this.executer.delUserKeys(account, user, key);
	}
	
	/**
	 * add the user's login password for new user
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public EucaConsoleMessage addUserLogin(String account,String user,String password) {
		if (usemock)
			return EucaAdminConsoleMockdata.addUserLogin(account, user, password);
		return this.executer.addUserLogin(account, user, password);
	}
	
	/**
	 * modify the user's login password
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public EucaConsoleMessage modifyUserLogin(String account,String user,String password) {
		if (usemock)
			return EucaAdminConsoleMockdata.modifyUserLogin(account, user, password);
		return this.executer.modifyUserLogin(account, user, password);
	}
	/**
	 * download the credentials
	 * @param account
	 * @param user
	 * @param filePath
	 * @return
	 */
	public EucaConsoleMessage downloadUserCredentials(String account,String user, String filePath) {
		if (usemock)
			return EucaAdminConsoleMockdata.downloadUserCredentials(account, user, filePath);
		return this.executer.downloadUserCredentials(account, user, filePath);
	}
	/**
	 * generate instance report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genInstanceReport(Date startDay, Date endDay) {
		if (usemock)
			return EucaAdminConsoleMockdata.genInstanceReport(startDay, endDay);
		return this.executer.genInstanceReport(startDay, endDay);
	}
	
	/**
	 * generate capacity report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genCapacityReport(Date startDay, Date endDay) {
		if (usemock)
			return EucaAdminConsoleMockdata.genCapacityReport(startDay, endDay);
		return this.executer.genCapacityReport(startDay, endDay);
	}
	
	/**
	 * generate S3 report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genS3Report(Date startDay, Date endDay) {
		if (usemock)
			return EucaAdminConsoleMockdata.genS3Report(startDay, endDay);
		return this.executer.genS3Report(startDay, endDay);
	}
	
	/**
	 * generate Volume report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genVolumeReport(Date startDay, Date endDay) {
		if (usemock)
			return EucaAdminConsoleMockdata.genVolumeReport(startDay, endDay);
		return this.executer.genVolumeReport(startDay, endDay);
	}
	
	/**
	 * generate Ip report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genIpReport(Date startDay, Date endDay) {
		if (usemock)
			return EucaAdminConsoleMockdata.genIpReport(startDay, endDay);
		return this.executer.genIpReport(startDay, endDay);
	}
	
	/**
	 * monitor the Component, return the component status and event
	 * @return
	 */
	public EucaConsoleMessage monitorComponent() {
		if (usemock)
			return EucaAdminConsoleMockdata.monitorComponent();
		return this.executer.monitorComponent();
	}
	
	/**
	 * retrieve the dashboard data
	 * @return
	 */
	public EucaConsoleMessage getDashBoardData() {
		if (usemock)
			return EucaAdminConsoleMockdata.getDashBoardData();
		return this.executer.getDashBoardData();
	}
}
