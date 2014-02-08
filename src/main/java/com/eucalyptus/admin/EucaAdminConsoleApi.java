package com.eucalyptus.admin;

import java.util.ArrayList;
import com.eucalyptus.admin.console.EucaConsoleMessage;
import java.util.Date;

public interface EucaAdminConsoleApi {

	/**
	 * login into the CLC for user authentication
	 * 
	 * @param account
	 * @param user
	 * @param password
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage login(String account, String user, String password);
	
	/**
	 * configure the adminConsole API client
	 * @param configure
	 */
	public void setup(EucaAdminConsoleConf configure); 
	
	/**
	 * configure the adminConsole API client
	 * 
	 * @param Ip
	 * @param port
	 */
	public void configure(String Ip, Integer port, String accessKey,
			String secretKey);

	/**
	 * query CloudProperties
	 * 
	 * @param properties
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage describeProperties(ArrayList<String> properties);

	/**
	 * modify the value of a sepecify cloud property
	 * 
	 * @param property
	 * @param value
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage modifyProperty(String property, String value);

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
			String byHost, String byState, String byPartition);

	/**
	 * register cloud controller component
	 * 
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerCLC(String host, String name,String user, String password);

	/**
	 * de-register cloud controller component
	 * 
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterCLC(String name);

	/**
	 * Register Wlarus component
	 * 
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerWalrus(String host, String name,String user, String password);

	/**
	 * deregister walrus component
	 * 
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterWalrus(String name);

	/**
	 * register cloud cluster component
	 * 
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerCC(String host, String name,
			String partition,String user,String password);

	/**
	 * de-register cloud cluster component
	 * 
	 * @param name
	 * @param partition
	 * @return cloud controller
	 */
	public EucaConsoleMessage deregisterCC(String name, String partition);

	/**
	 * register storage controller component
	 * 
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerSC(String host, String name,
			String partition,String user, String password);

	/**
	 * de-register storage controller component
	 * 
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterSC(String name, String partition);

	/**
	 * register vmware broker
	 * 
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerVMB(String host, String name,
			String partition);

	/**
	 * de-register vmware broker
	 * 
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterVMB(String name, String partition);

	/**
	 * register node controller
	 * 
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage registerNode(String host, String partition,String user, String password);

	/**
	 * de-register node controller
	 * 
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterNode(String host, String partition);

	/**
	 * enable a service component
	 * 
	 * @param name
	 *            - name of the component
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage enableService(String name);

	/**
	 * disable service component
	 * 
	 * @param name
	 *            - name of the component
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage disableService(String name);

	/**
	 * get the all VM types
	 * 
	 * @param instanceType
	 *            - null stand for all instance type
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getInstanceTypes(ArrayList<String> instanceType);

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
			int mem, int disk);

	/**
	 * return a block manager of a cluster
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage getBlockManager(String cluster);

	/**
	 * modify the block manager of cluster
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage modifyBlockManager(String cluster, String manager);

	/**
	 * return a block manager of a cluster
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage getBlockManagerProperties(String cluster,
			String manager);

	/**
	 * modify the parameters of block manager
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage modifyBlockManagerProperty(String cluster,
			String para, String value);

	public EucaConsoleMessage getNetworkMode();

	/**
	 * return the parameters for a network mode
	 * 
	 * @param mode
	 * @return
	 */
	public EucaConsoleMessage getNetworkModeProperties(String mode);

	/**
	 * modify the network mode
	 * 
	 * @param cluster
	 * @return
	 */
	public EucaConsoleMessage modifyNetworkMode(String mode);

	/**
	 * modify property of network mode
	 * 
	 * @param mode
	 * @param para
	 * @param value
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage modifyNetwrokModeProperty(String mode,
			String para, String value);

	/**
	 * return the properties of EBS services (one cluster)
	 * 
	 * @param cluster
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage getEbsServiceProperties(String cluster);

	/**
	 * Modify the property of EBS service
	 * 
	 * @param cluster
	 * @param para
	 * @param value
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage modifyEbsServiceProperty(String cluster,
			String para, String value);

	/**
	 * return the DNS service properties
	 * 
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage getDNSServiceProperties();

	/**
	 * Modify the property of DNS service
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	public EucaConsoleMessage modifyDNSServiceProperty(String property,
			String value);

	/**
	 * return the properties of S3
	 * 
	 * @return
	 */
	public EucaConsoleMessage getS3ServiceProperties();

	/**
	 * Modify the property of S3 service
	 * 
	 * @param property
	 * @param value
	 * @return
	 */

	public EucaConsoleMessage modifyS3ServiceProperty(String property,
			String value);

	/**
	 * modify image of the load balancer service
	 * 
	 * @param image
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage modifyLoadBalancerImage(String image);

	/**
	 * get the image of load balancer
	 * 
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage getLoadBalancerImage();

	/**
	 * Install the load balancer
	 * 
	 * @return EucaConsoleMessage
	 */
	public EucaConsoleMessage installBalancerImage();

	/**
	 * query images
	 * 
	 * @param images
	 *            - null stand for all images
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getImages(ArrayList<String> images);

	/**
	 * deregister images
	 * 
	 * @param image
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage deregisterImage(String image);

	/**
	 * set the Image as public or private image
	 * 
	 * @param image
	 * @param ispublic
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage modifyImage(String image, boolean ispublic);

	/**
	 * Create account
	 * 
	 * @param name
	 *            - name of the account
	 * @return eucaConsoleMessage (account id & name)
	 */
	public EucaConsoleMessage createAccount(String name);

	/**
	 * delete a account
	 * 
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delAccount(String name);

	/**
	 * get account list
	 * 
	 * @return eucaConsoleMessage (account id & name)
	 */
	public EucaConsoleMessage getAccountList();

	/**
	 * return the all policy list of account
	 * 
	 * @param account
	 * @return
	 */
	public EucaConsoleMessage getAccountPolicyList(String account);

	/**
	 * return the policy info of a account
	 * 
	 * @param account
	 * @param policy
	 *            - name of the policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getAccountPolicy(String account, String policy);

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
			String policy_conent);

	/**
	 * delete a policy of a account
	 * 
	 * @param account
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delAccountPolicy(String account, String policy);

	/**
	 * add a user group
	 * 
	 * @param account
	 *            - name of account
	 * @param group
	 *            - name of group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage createGroup(String account, String group);

	/**
	 * delete a user group
	 * 
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delGroup(String account, String group);

	/**
	 * get list of user group
	 * 
	 * @param account
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupList(String account);

	/**
	 * add a existing user to a existing group
	 * 
	 * @param account
	 * @param group
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addGroupUser(String account, String group,
			String user);

	/**
	 * delete a user from existing user group
	 * 
	 * @param account
	 * @param group
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage delGroupUser(String account, String group,
			String user);

	/**
	 * get user list of a group
	 * 
	 * @param account
	 * @param group
	 * @return
	 */
	public EucaConsoleMessage getGroupUserList(String account, String group);

	/**
	 * Update the group name
	 * 
	 * @param account
	 * @param old_group
	 * @param new_group
	 * @return
	 */
	public EucaConsoleMessage modifyGroup(String account, String old_group,
			String new_group);

	/**
	 * get all policy list of a group
	 * 
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupPolicyList(String account, String group);

	/**
	 * Get group policy content/info
	 * 
	 * @param account
	 * @param group
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getGroupPolicy(String account, String group,
			String policy);

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
			String policy, String policy_conent);

	/**
	 * delete a policy of group
	 * 
	 * @param account
	 * @param group
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delGroupPolicy(String account, String group,
			String policy);

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
			String user);

	/**
	 * delete a user
	 * 
	 * @param account
	 * @param user
	 * @return
	 */
	public EucaConsoleMessage delUser(String account, String user);

	/**
	 * return user list of a account
	 * 
	 * @param account
	 * @return
	 */
	public EucaConsoleMessage getUserList(String account);

	/**
	 * return group list of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserGroupList(String account, String user);

	/**
	 * get all policy list of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserPolicyList(String account, String user);

	/**
	 * Get user policy content/info
	 * 
	 * @param account
	 * @param user
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserPolicy(String account, String user,
			String policy);

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
			String policy, String policy_conent);

	/**
	 * delete a policy of user
	 * 
	 * @param account
	 * @param user
	 * @param policy
	 * @return
	 */
	public EucaConsoleMessage delUserPolicy(String account, String user,
			String policy);

	/**
	 * return the x509 certs of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserCerts(String account, String user);

	/**
	 * add new x509 certs of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addUserCerts(String account, String user,String certBody);

	/**
	 * Delete a user cert
	 * 
	 * @param account
	 * @param user
	 * @param cert
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage delUserCerts(String account, String user,
			String cert);

	/**
	 * return the keys of a user
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage getUserKeys(String account, String user);

	/**
	 * add a new user key
	 * 
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public EucaConsoleMessage addUserKeys(String account, String user);

	/**
	 * delete a user key
	 * 
	 * @param account
	 * @param user
	 * @param key
	 * @return
	 */
	public EucaConsoleMessage delUserKeys(String account, String user,
			String key);

	/**
	 * add the user's login password for new user
	 * 
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public EucaConsoleMessage addUserLogin(String account, String user,
			String password);

	/**
	 * modify the user's login password
	 * 
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public EucaConsoleMessage modifyUserLogin(String account, String user,
			String password);

	/**
	 * download the credentials
	 * 
	 * @param account
	 * @param user
	 * @param filePath
	 * @return
	 */
	public EucaConsoleMessage downloadUserCredentials(String account,
			String user, String filePath);

	/**
	 * generate instance report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genInstanceReport(Date startDay, Date endDay);

	/**
	 * generate capacity report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genCapacityReport(Date startDay, Date endDay);

	/**
	 * generate S3 report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genS3Report(Date startDay, Date endDay);

	/**
	 * generate Volume report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genVolumeReport(Date startDay, Date endDay);

	/**
	 * generate Ip report
	 * 
	 * @param startDay
	 *            - reporting start day
	 * @param endDay
	 *            - reporting end day
	 * @return
	 */
	public EucaConsoleMessage genIpReport(Date startDay, Date endDay);

	/**
	 * monitor the Component, return the component status and event
	 * 
	 * @return
	 */
	public EucaConsoleMessage monitorComponent();

	/**
	 * retrieve the dashboard data
	 * 
	 * @return
	 */
	public EucaConsoleMessage getDashBoardData();

}
