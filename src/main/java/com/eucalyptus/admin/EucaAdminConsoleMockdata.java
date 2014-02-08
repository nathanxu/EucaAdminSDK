package com.eucalyptus.admin;

import java.util.ArrayList;

import com.eucalyptus.admin.console.JSONMessage;
import com.eucalyptus.admin.console.EucaConsoleMessage;

//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
import com.eucalyptus.admin.model.*;
//import com.eucalyptus.admin.test.consoleExample;
import com.eucalyptus.admin.console.model.*;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.identitymanagement.model.*;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.File;

public class EucaAdminConsoleMockdata {
    private static Map<String,String> properties = new HashMap<String,String>();
    private static Image eri = new Image();
    private static Image eki = new Image();
    private static Image emi_ebs = new Image();
    private static Image emi_inst = new Image();
    private static EucaGroup demoGroup = new EucaGroup();
    private static EucaUser demoUser = new EucaUser();
    private static EucaUser adminUser = new EucaUser();
    private static EucaPolicy demoPolicy = new EucaPolicy();
    private static Account    adminAccount = new Account();
    private static Account    demoAccount = new Account();
    private static AccessKeyMetadata adminKey = new AccessKeyMetadata();
    private static AccessKeyMetadata demoKey = new AccessKeyMetadata();
    
    static {
    	properties.put("authentication.ldap_integration_configuration","{'sync':{'enable':'false'}}");
    	properties.put("authentication.websession_life_in_minutes","1440");
    	properties.put("autoscaling.activityexpiry","42d");
    	properties.put("autoscaling.activityinitialbackoff","9s");
    	properties.put("autoscaling.activitymaxbackoff","15m");
    	properties.put("autoscaling.activitytimeout","5m");
    	properties.put("autoscaling.maxlaunchincrement","20");
    	properties.put("autoscaling.maxregistrationretries","5");
    	properties.put("autoscaling.pendinginstancetimeout","15m");
    	properties.put("autoscaling.suspendedprocesses","{}");
    	properties.put("autoscaling.suspendedtasks","{}");
    	properties.put("autoscaling.suspensionlaunchattemptsthreshold","15");
    	properties.put("autoscaling.suspensiontimeout","1d");
    	properties.put("autoscaling.untrackedinstancetimeout","5m");
    	properties.put("autoscaling.zonefailurethreshold","5m");
    	properties.put("bootstrap.hosts.state_initialize_timeout","120000");
    	properties.put("bootstrap.hosts.state_transfer_timeout","10000");
    	properties.put("bootstrap.notifications.batch_delay_seconds","60");
    	properties.put("bootstrap.notifications.digest","false");
    	properties.put("bootstrap.notifications.digest_frequency_hours","24");
    	properties.put("bootstrap.notifications.digest_only_on_errors","true");
    	properties.put("bootstrap.notifications.email_from","notification@eucalyptus");
    	properties.put("bootstrap.notifications.email_from_name","Eucalyptus");
    	properties.put("bootstrap.notifications.email_subject_prefix","[eucalyptus-notifications]");
    	properties.put("bootstrap.notifications.email_to","{}");
    	properties.put("bootstrap.notifications.include_fault_stack","false");
    	properties.put("bootstrap.notifications.email.email_smtp_host","{}");
    	properties.put("bootstrap.notifications.email.email_smtp_port","25");
    	properties.put("bootstrap.servicebus.hup","0");
    	properties.put("bootstrap.servicebus.max_outstanding_messages","256");
    	properties.put("bootstrap.servicebus.workers_per_stage","16");
    	properties.put("bootstrap.timer.rate","10000");
    	properties.put("bootstrap.topology.coordinator_check_backoff_secs","10");
    	properties.put("bootstrap.topology.local_check_backoff_secs","10");
    	properties.put("bootstrap.tx.concurrent_update_retries","10");
    	properties.put("bootstrap.webservices.async_internal_operations","false");
    	properties.put("bootstrap.webservices.async_operations","false");
    	properties.put("bootstrap.webservices.async_pipeline","false");
    	properties.put("bootstrap.webservices.channel_connect_timeout","500");
    	properties.put("bootstrap.webservices.channel_keep_alive","true");
    	properties.put("bootstrap.webservices.channel_nodelay","true");
    	properties.put("bootstrap.webservices.channel_reuse_address","true");
    	properties.put("bootstrap.webservices.client_http_chunk_buffer_max","1048576000");
    	properties.put("bootstrap.webservices.client_idle_timeout_secs","30");
    	properties.put("bootstrap.webservices.client_internal_timeout_secs","60");
    	properties.put("bootstrap.webservices.client_pool_max_mem_per_conn","0");
    	properties.put("bootstrap.webservices.client_pool_max_threads","40");
    	properties.put("bootstrap.webservices.client_pool_timeout_millis","500");
    	properties.put("bootstrap.webservices.client_pool_total_mem","0");
    	properties.put("bootstrap.webservices.clock_skew_sec","20");
    	properties.put("bootstrap.webservices.cluster_connect_timeout_millis","2000");
    	properties.put("bootstrap.webservices.default_aws_sns_uri_scheme","http");
    	properties.put("bootstrap.webservices.default_ec2_uri_scheme","http");
    	properties.put("bootstrap.webservices.default_euare_uri_scheme","http");
    	properties.put("bootstrap.webservices.default_eustore_url","http://emis.eucalyptus.com/");
    	properties.put("bootstrap.webservices.default_https_enabled","false");
    	properties.put("bootstrap.webservices.default_s3_uri_scheme","http");
    	properties.put("bootstrap.webservices.http_max_chunk_bytes","102400");
    	properties.put("bootstrap.webservices.http_max_header_bytes","8192");
    	properties.put("bootstrap.webservices.http_max_initial_line_bytes","4096");
    	properties.put("bootstrap.webservices.oob_internal_operations","true");
    	properties.put("bootstrap.webservices.pipeline_read_timeout_seconds","20");
    	properties.put("bootstrap.webservices.pipeline_write_timeout_seconds","20");
    	properties.put("bootstrap.webservices.port","8773");
    	properties.put("bootstrap.webservices.replay_skew_window_sec","3");
    	properties.put("bootstrap.webservices.server_boss_pool_max_mem_per_conn","0");
    	properties.put("bootstrap.webservices.server_boss_pool_max_threads","128");
    	properties.put("bootstrap.webservices.server_boss_pool_timeout_millis","500");
    	properties.put("bootstrap.webservices.server_boss_pool_total_mem","0");
    	properties.put("bootstrap.webservices.server_channel_nodelay","true");
    	properties.put("bootstrap.webservices.server_channel_reuse_address","true");
    	properties.put("bootstrap.webservices.server_pool_max_mem_per_conn","0");
    	properties.put("bootstrap.webservices.server_pool_max_threads","128");
    	properties.put("bootstrap.webservices.server_pool_timeout_millis","500");
    	properties.put("bootstrap.webservices.server_pool_total_mem","0");
    	properties.put("bootstrap.webservices.statistics","false");
    	properties.put("bootstrap.webservices.use_dns_delegation","false");
    	properties.put("bootstrap.webservices.use_instance_dns","false");
    	properties.put("bootstrap.webservices.ssl.server_alias","eucalyptus");
    	properties.put("bootstrap.webservices.ssl.server_password","eucalyptus");
    	properties.put("bootstrap.webservices.ssl.server_ssl_ciphers","RSA:DSS:ECDSA:+RC4:+3DES:TLS_EMPTY_RENEGOTIATION_INFO_SCSV:!NULL:!EXPORT:!EXPORT1024:!MD5:!DES");
    	properties.put("cloud.db_check_poll_time","60000");
    	properties.put("cloud.db_check_threshold","2.0%");
    	properties.put("cloud.euca_log_level","INFO");
    	properties.put("cloud.log_file_disk_check_poll_time","5000");
    	properties.put("cloud.log_file_disk_check_threshold","2.0%");
    	properties.put("cloud.memory_check_poll_time","5000");
    	properties.put("cloud.memory_check_ratio","0.98");
    	properties.put("cloud.perm_gen_memory_check_poll_time","5000");
    	properties.put("cloud.perm_gen_memory_check_ratio","0.98");
    	properties.put("cloud.trigger_fault","{}");
    	properties.put("cloud.addresses.dodynamicpublicaddresses","true");
    	properties.put("cloud.addresses.maxkillorphans","360");
    	properties.put("cloud.addresses.orphangrace","360");
    	properties.put("cloud.addresses.systemreservedpublicaddresses","0");
    	properties.put("cloud.cluster.disabledinterval","15");
    	properties.put("cloud.cluster.enabledinterval","15");
    	properties.put("cloud.cluster.notreadyinterval","10");
    	properties.put("cloud.cluster.pendinginterval","3");
    	properties.put("cloud.cluster.requestworkers","16");
    	properties.put("cloud.cluster.startupsyncretries","10");
    	properties.put("cloud.images.defaultkernelid","eki-18E03EB1");
    	properties.put("cloud.images.defaultramdiskid","eri-44F43D04");
    	properties.put("cloud.images.defaultvisibility","false");
    	properties.put("cloud.monitor.default_poll_interval_mins","5");
    	properties.put("cloud.monitor.history_size","5");
    	properties.put("cloud.network.global_max_network_index","4096");
    	properties.put("cloud.network.global_max_network_tag","4096");
    	properties.put("cloud.network.global_min_network_index","2");
    	properties.put("cloud.network.global_min_network_tag","1");
    	properties.put("cloud.network.network_index_pending_timeout","35");
    	properties.put("cloud.network.network_tag_pending_timeout","35");
    	properties.put("cloud.vmstate.ebs_volume_creation_timeout","30");
    	properties.put("cloud.vmstate.instance_subdomain",".eucalyptus");
    	properties.put("cloud.vmstate.instance_timeout","720");
    	properties.put("cloud.vmstate.mac_prefix","d0:0d");
    	properties.put("cloud.vmstate.max_state_threads","16");
    	properties.put("cloud.vmstate.migration_refresh_time","60");
    	properties.put("cloud.vmstate.network_metadata_refresh_time","15");
    	properties.put("cloud.vmstate.shut_down_time","13");
    	properties.put("cloud.vmstate.stopping_time","10");
    	properties.put("cloud.vmstate.terminated_time","60");
    	properties.put("cloud.vmstate.tx_retries","10");
    	properties.put("cloud.vmstate.user_data_max_size_kb","16");
    	properties.put("cloud.vmstate.vm_initial_report_timeout","300");
    	properties.put("cloud.vmstate.vm_state_settle_time","40");
    	properties.put("cloud.vmstate.volatile_state_interval_sec","9223372036854775807");
    	properties.put("cloud.vmstate.volatile_state_timeout_sec","60");
    	properties.put("cloud.vmtypes.default_type_name","m1.small");
    	properties.put("cluster01.cluster.addressespernetwork","32");
    	properties.put("cluster01.cluster.maxnetworkindex","30");
    	properties.put("cluster01.cluster.maxnetworktag","15");
    	properties.put("cluster01.cluster.minnetworkindex","9");
    	properties.put("cluster01.cluster.minnetworktag","2");
    	properties.put("cluster01.cluster.networkmode","MANAGED-NOVLAN");
    	properties.put("cluster01.cluster.sourcehostname","192.168.1.100");
    	properties.put("cluster01.cluster.usenetworktags","true");
    	properties.put("cluster01.cluster.vnetnetmask","255.255.254.0");
    	properties.put("cluster01.cluster.vnetsubnet","172.31.254.0");
    	properties.put("cluster01.cluster.vnettype","ipv4");
    	properties.put("cluster01.storage.blockstoragemanager","clvm");
    	properties.put("cluster01.storage.dasdevice","<unset>");
    	properties.put("cluster01.storage.maxsnaptransferretries","50");
    	properties.put("cluster01.storage.maxtotalvolumesizeingb","100");
    	properties.put("cluster01.storage.maxvolumesizeingb","15");
    	properties.put("cluster01.storage.sharedevice","/dev/loop1");
    	properties.put("cluster01.storage.shouldtransfersnapshots","true");
    	properties.put("cluster01.storage.storeprefix","<unset>");
    	properties.put("cluster01.storage.tid","<unset>");
    	properties.put("cluster01.storage.timeoutinmillis","10000");
    	properties.put("cluster01.storage.volumesdir","//var/lib/eucalyptus/volumes");
    	properties.put("cluster01.storage.zerofillvolumes","false");
    	properties.put("experimental.dns.enabled","true");
    	properties.put("experimental.dns.recursive.enabled","true");
    	properties.put("experimental.dns.services.enabled","false");
    	properties.put("experimental.dns.split_horizon.enabled","true");
    	properties.put("loadbalancing.loadbalancer_dns_subdomain","lb");
    	properties.put("loadbalancing.loadbalancer_emi","NULL");
    	properties.put("loadbalancing.loadbalancer_instance_type","m1.small");
    	properties.put("loadbalancing.loadbalancer_num_vm","1");
    	properties.put("loadbalancing.loadbalancer_vm_keyname","{}");
    	properties.put("reporting.default_size_time_size_unit","GB");
    	properties.put("reporting.default_size_time_time_unit","DAYS");
    	properties.put("reporting.default_size_unit","GB");
    	properties.put("reporting.default_time_unit","DAYS");
    	properties.put("reporting.default_write_interval_mins","15");
    	properties.put("system.dns.dnsdomain","localhost");
    	properties.put("system.dns.nameserver","nshost.localhost");
    	properties.put("system.dns.nameserveraddress","127.0.0.1");
    	properties.put("system.dns.registrationid","fbbb004d-5e36-40f1-82b0-c3a73cb66202");
    	properties.put("system.exec.max_restricted_concurrent_ops","2");
    	properties.put("system.exec.restricted_concurrent_ops","dd,gunzip,tar");
    	properties.put("tagging.max_tags_per_resource","10");
    	properties.put("walrus.blockdevice","<unset>");
    	properties.put("walrus.resource","<unset>");
    	properties.put("walrus.storagedir","//var/lib/eucalyptus/bukkits");
    	properties.put("walrus.storagemaxbucketsizeinmb","5120");
    	properties.put("walrus.storagemaxbucketsperaccount","100");
    	properties.put("walrus.storagemaxcachesizeinmb","30720");
    	properties.put("walrus.storagemaxtotalcapacity","419");
    	properties.put("walrus.storagemaxtotalsnapshotsizeingb","50");
    	properties.put("www.http_port","8080");
    	properties.put("www.httpproxyhost","{}");
    	properties.put("www.httpproxyport","{}");
    	properties.put("www.https_ciphers","RSA:DSS:ECDSA:+RC4:+3DES:TLS_EMPTY_RENEGOTIATION_INFO_SCSV:!NULL:!EXPORT:!EXPORT1024:!MD5:!DES");
    	properties.put("www.https_port","8443");
    	
    	eri.setImageId("eri-44F43D04");
    	eri.setName(null);
    	eri.setImageLocation("centos6/initramfs-kexec.manifest.xml");
    	eri.setOwnerId("573394755474");
    	eri.setPublic(true);
    	eri.setArchitecture("x86_64");
    	eri.setImageType("ramdisk");
    	eri.setRootDeviceType("instance-store");
    	eri.setState("avaialbe");
    	
    	eki.setImageId("eki-18E03EB1");
    	eki.setName(null);
    	eki.setImageLocation("centos6/vmlinuz-kexec.manifest.xml");
    	eki.setOwnerId("573394755474");
    	eki.setPublic(true);
    	eki.setArchitecture("x86_64");
    	eki.setImageType("kernel");
    	eki.setRootDeviceType("instance-store");
    	eki.setState("avaialbe");
    	
    	emi_inst.setImageId("emi-437D397A");
    	emi_inst.setName("centos");
    	emi_inst.setImageLocation("centos6/ks-centos6-201308012043.img.manifest.xml");
    	emi_inst.setOwnerId("573394755474");
    	emi_inst.setPublic(false);
    	emi_inst.setArchitecture("x86_64");
    	emi_inst.setImageType("machine");
    	emi_inst.setRootDeviceType("instance-store");
    	emi_inst.setKernelId("eki-18E03EB1");
    	emi_inst.setRamdiskId("eri-44F43D04");
    	emi_inst.setDescription("centos 6 demo");
    	emi_inst.setState("avaialbe");
    	
    	emi_ebs.setImageId("emi-72C24156");
    	emi_ebs.setName("bosh-emi");
    	emi_ebs.setImageLocation("573394755474/micro-bosh-emi");
    	emi_ebs.setOwnerId("573394755474");
    	emi_ebs.setPublic(false);
    	emi_ebs.setArchitecture("x86_64");
    	emi_ebs.setImageType("machine");
    	emi_ebs.setRootDeviceType("ebs");
    	emi_ebs.setKernelId("eki-18E03EB1");
    	emi_ebs.setRamdiskId("eri-44F43D04");
    	emi_ebs.setDescription("ebs demo");
    	emi_ebs.setState("avaialbe");
    	
    	demoGroup.setAccount("eucalyptus");
    	demoGroup.setGroupId("LNOXY4FPNVU4SPGMWZAUF");
    	demoGroup.setGroupName("dev");
    
    	adminUser.setAccount("eucalyptus");
    	adminUser.setPath("/");
    	adminUser.setUserName("admin");
    	adminUser.setUserId("WCQSQWGZAZCAYHRW6T6BB");
    
    	demoUser.setAccount("eucalyptus");
    	demoUser.setPath("/");
    	demoUser.setUserName("demo");
    	demoUser.setUserId("O6HRI2ZT73MWKSWLMYXYA");
    	java.util.ArrayList<EucaGroup> groups = new java.util.ArrayList<EucaGroup>();
    	groups.add(demoGroup);
    	demoUser.setGroups(groups);
    	
    	demoPolicy.setAccountName("eucalyptus");
    	demoPolicy.setGroupName("dev");
    	demoPolicy.setUserName("demo");
    	demoPolicy.setPolicyName("fullPermission");
    	demoPolicy.setPolicyDocument("{\"Version\":\"2013-10-11\",\"Statement\":[{\"Sid\":\"1\",\"Effect\":\"Allow\",\"Action\":\"*\",\"Resource\":\"*\"}]}");
    	
    	adminAccount.setAccountId("573394755474");
    	adminAccount.setAccountName("eucalyptus");
    	adminAccount.setAccountStatus("confirmed");
    	
    	demoAccount.setAccountId("243304894100");
    	demoAccount.setAccountName("demo");
    	demoAccount.setAccountStatus("confirmed");
    	
    	adminKey.setAccessKeyId("NFLWDQGJ4VAXFKYPE4ZKG");
    	adminKey.setUserName("admin");
    	adminKey.setStatus("active");
    	
    	demoKey.setAccessKeyId("H99TCUK9FEZUVXTFUTDTE");
    	demoKey.setUserName("demo");
    	demoKey.setStatus("active");
    	
    }
    
    private static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";  
        java.util.Random random = new java.util.Random();  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            int number = random.nextInt(base.length());  
            sb.append(base.charAt(number));  
        }  
        return sb.toString();  
     }  
    
	public static EucaConsoleMessage login(String account, String user, String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("login", true, null, 0);
		return ret;
	}	
	
	/**
	 * query CloudProperties
	 * @param properties
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage describeProperties(ArrayList<String> properties) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("describeProperties", true, null, 0);
		DescribePropertiesResult result = new DescribePropertiesResult();
		if (properties!=null) {	
			//ArrayList<CloudProperty> cloudProps = new ArrayList<CloudProperty>;
			for(String name:properties) {
				CloudProperty property = new CloudProperty();
				property.setName(name);
				if (EucaAdminConsoleMockdata.properties.containsKey(name)) {
					property.setValue(EucaAdminConsoleMockdata.properties.get(name));
					property.setDescription("");	
				} else {
					property.setValue("mock data (name)");
					property.setDescription("mock data (description)");	
				}	
				result.getProperties().add(property);
			}
			
		} else {
			java.util.Set<String> keys = EucaAdminConsoleMockdata.properties.keySet();
	        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
	            String key = (String) it.next();
	            String value = EucaAdminConsoleMockdata.properties.get(key);
	            CloudProperty property= new CloudProperty();
	            property.setName(key);
				property.setValue(value);
				property.setDescription("");
				result.getProperties().add(property);
	        }
			
		}
		ret.setData(JSONMessage.assembleDescribeProperties(result));
		return ret;
	}
	
	/**
	 * modify the value of a sepecify cloud property
	 * @param property
	 * @param value
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage modifyProperty(String property, String value) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("describeProperties", true, null, 0);
		ModifyPropertyResult result = new ModifyPropertyResult();
		result.setName(property);
		result.setOldValue("mock data(value");
		result.setValue(value);	
		ret.setData(JSONMessage.assembleModifyProperties(result));
		return ret;
	}
	

	/**
	 * query the component & services status
	 * @param byServiceType
	 * @param byHost
	 * @param byState
	 * @param byPartition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage describeServices(String byServiceType,
			String byHost, String byState, String byPartition) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"describeServices", true, null, 0);
		DescribeComponentsResult result = new DescribeComponentsResult();

		if (byServiceType == null || byServiceType.equals("eucalyptus")) {
			ComponentInfo clc = new ComponentInfo();
			clc.setName("clc01");
			clc.setHostName("192.168.1.100");
			clc.setPartition("eucalyptus");
			clc.setFullName("arn:euca:eucalyptus:::192.168.1.100/");
			clc.setState("enable");
			clc.setType("eucalyptus");
			clc.setDetail("");
			result.getComponents().add(clc);
		}
		if (byServiceType == null || byServiceType.equals("walrus")) {
			ComponentInfo walrus = new ComponentInfo();
			walrus.setName("walrus01");
			walrus.setHostName("192.168.1.100");
			walrus.setPartition("walrus");
			walrus.setFullName("arn:euca:bootstrap:walrus:walrus:walrus01/");
			walrus.setState("enable");
			walrus.setType("walrus");
			walrus.setDetail("");
			result.getComponents().add(walrus);
		}
		if (byServiceType == null || byServiceType.equals("cluster")) {
			ComponentInfo cc = new ComponentInfo();
			cc.setName("cc_01");
			cc.setHostName("192.168.1.100");
			cc.setPartition("cluster01");
			cc.setFullName("arn:euca:eucalyptus:cluster01:cluster:cc_01/");
			cc.setState("enable");
			cc.setType("cluster");
			cc.setDetail("");
			result.getComponents().add(cc);
		}
		if (byServiceType == null || byServiceType.equals("storage")) {
			ComponentInfo sc = new ComponentInfo();
			sc.setName("sc_01");
			sc.setHostName("192.168.1.100");
			sc.setPartition("cluster01");
			sc.setFullName("arn:euca:eucalyptus:cluster01:storage:sc_01/");
			sc.setState("enable");
			sc.setType("storage");
			sc.setDetail("");
			result.getComponents().add(sc);
		}
		if (byServiceType == null || byServiceType.equals("node")) {
			ComponentInfo node = new ComponentInfo();
			node.setName("node_01");
			node.setHostName("192.168.1.101");
			node.setPartition("cluster01");
			node.setFullName("arn:euca:eucalyptus:cluster01:node:node_01/");
			node.setState("enable");
			node.setType("node");
			node.setDetail("");
			result.getComponents().add(node);
		}

		if (byServiceType == null || byServiceType.equals("vmwarebroker")) {
			ComponentInfo node = new ComponentInfo();
			node.setName("vmb_01");
			node.setHostName("192.168.1.100");
			node.setPartition("cluster01");
			node.setFullName("arn:euca:eucalyptus:cluster01:vmwarebroker:vmb_01/");
			node.setState("enable");
			node.setType("vmwarebroker");
			node.setDetail("");
			result.getComponents().add(node);
		}
		ret.setData(JSONMessage.assembleDescribeComponents(result));
		return ret;

	}
	
	/**
	 * register cloud controller component
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage registerCLC(String host, String name,String user,String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		RegisterComponentResult result = new RegisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	/**
	 * de-register cloud controller component
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage deregisterCLC(String name) {
		
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		DeregisterComponentResult result = new DeregisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	
	/**
	 * Register Wlarus component
	 * @param host
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage registerWalrus(String host, String name,String user,String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		RegisterComponentResult result = new RegisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	/**
	 * deregister walrus component
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage deregisterWalrus(String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		DeregisterComponentResult result = new DeregisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	/**
	 * register cloud cluster component
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage registerCC(String host, String name,String partition,String user,String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		RegisterComponentResult result = new RegisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	/**
	 * de-register cloud cluster component
	 * @param name
	 * @param partition
	 * @return cloud controller
	 */
	public static EucaConsoleMessage deregisterCC(String name,String partition) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		DeregisterComponentResult result = new DeregisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	
	/**
	 * register storage controller component
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage registerSC(String host, String name,String partition,String user,String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		RegisterComponentResult result = new RegisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	
	/**
	 * de-register storage controller component
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage deregisterSC(String name,String partition) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		DeregisterComponentResult result = new DeregisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	
	/**
	 * register vmware broker
	 * @param host
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage registerVMB(String host, String name,String partition) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		RegisterComponentResult result = new RegisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		ret.setErrCode(400);
		ret.setStatus(false);
		ret.setErrMessage("Failed to find corresponding class mapping for element: RegisterVMwareBroker in namespace: msgs_eucalyptus_com_3_3_0");
		return ret;
	}
	/**
	 * de-register vmware broker
	 * @param name
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage deregisterVMB(String name,String partition) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		DeregisterComponentResult result = new DeregisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		ret.setErrCode(400);
		ret.setStatus(false);
		ret.setErrMessage("Failed to find corresponding class mapping for element: RegisterVMwareBroker in namespace: msgs_eucalyptus_com_3_3_0");
		return ret;
	}
	
	/**
	 * register node controller
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage registerNode(String host,String partition,String user,String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"RegisterComponent", true, null, 0);
		RegisterComponentResult result = new RegisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	/**
	 * de-register node controller
	 * @param host
	 * @param partition
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage deregisterNode(String host,String partition) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterComponent", true, null, 0);
		DeregisterComponentResult result = new DeregisterComponentResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	/**
	 * enable a service component
	 * @param name -name of the component

	 */
	public static EucaConsoleMessage enableService(String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"enableService", true, null, 0);
		ModifyServiceResult result = new ModifyServiceResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
		
	}
	
	/**
	 * disable service component
	 * @param name - name of the component

	 */
	public static EucaConsoleMessage disableService(String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"disableService", true, null, 0);
		ModifyServiceResult result = new ModifyServiceResult();
		result.setResult(true);	
		ret.setData(null);
		return ret;
	}
	
	/**
	 * get the all VM types
	 * @param instanceType  - null stand for all instance type
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getInstanceTypes(ArrayList<String> instanceType) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"discribeInstanceTypes", true, null, 0);
		DescribeInstanceTypesResult result = new DescribeInstanceTypesResult();
		if ( instanceType == null || instanceType.contains("t1.micro") ) {
			VmTypeDetail t1_micro = new VmTypeDetail();
			t1_micro.setName("t1.micro");
			t1_micro.setCpu(1);
			t1_micro.setMemory(256);
			t1_micro.setDisk(5);
			result.getInstanceTypeDetails().add(t1_micro);
		}

		if (instanceType == null || instanceType.contains("m1.small") || instanceType == null) {
			VmTypeDetail m1_small = new VmTypeDetail();
			m1_small.setName("m1.small");
			m1_small.setCpu(1);
			m1_small.setMemory(512);
			m1_small.setDisk(5);
			result.getInstanceTypeDetails().add(m1_small);
		}

		if (instanceType == null || instanceType.contains("m1.medium") || instanceType == null) {
			VmTypeDetail m1_medium = new VmTypeDetail();
			m1_medium.setName("m1.medium");
			m1_medium.setCpu(1);
			m1_medium.setMemory(512);
			m1_medium.setDisk(10);
			result.getInstanceTypeDetails().add(m1_medium);
		}

		if (instanceType == null || instanceType.contains("c1.medium") || instanceType == null) {
			VmTypeDetail c1_medium = new VmTypeDetail();
			c1_medium.setName("c1.medium");
			c1_medium.setCpu(2);
			c1_medium.setMemory(512);
			c1_medium.setDisk(10);
			result.getInstanceTypeDetails().add(c1_medium);
		}

		if (instanceType == null || instanceType.contains("m1.large") || instanceType == null) {
			VmTypeDetail m1_large = new VmTypeDetail();
			m1_large.setName("m1.large");
			m1_large.setCpu(2);
			m1_large.setMemory(512);
			m1_large.setDisk(10);
			result.getInstanceTypeDetails().add(m1_large);
		}

		if (instanceType == null || instanceType.contains("m1.xlarge") || instanceType == null) {
			VmTypeDetail m1_xlarge = new VmTypeDetail();
			m1_xlarge.setName("m1.xlarge");
			m1_xlarge.setCpu(2);
			m1_xlarge.setMemory(1024);
			m1_xlarge.setDisk(10);
			result.getInstanceTypeDetails().add(m1_xlarge);
		}

		if (instanceType == null || instanceType.contains("c1.large") || instanceType == null) {
			VmTypeDetail c1_large = new VmTypeDetail();
			c1_large.setName("c1.large");
			c1_large.setCpu(2);
			c1_large.setMemory(2048);
			c1_large.setDisk(10);
			result.getInstanceTypeDetails().add(c1_large);
		}

		if (instanceType == null || instanceType.contains("c1.xlarge") || instanceType == null) {
			VmTypeDetail c1_xlarge = new VmTypeDetail();
			c1_xlarge.setName("c1.xlarge");
			c1_xlarge.setCpu(4);
			c1_xlarge.setMemory(4096);
			c1_xlarge.setDisk(30);
			result.getInstanceTypeDetails().add(c1_xlarge);
		}
		//EucaConsoleMessage ret = new EucaConsoleMessage();
		ret.setData(JSONMessage.assembleDescribeInstanceTypes(result));	
		return ret;
	}
	
    /**
     * modify a instance type
     * @param instanceType
     * @param cpu  - count of vcp
     * @param mem  - memory MB
     * @param disk - disk GB 
     * @return eucaConsoleMessage
     */
	public static EucaConsoleMessage modifyInstanceTypes(String instanceType,int cpu,int mem,int disk) {
		
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"discribeInstanceTypes", true, null, 0);
		ModifyInstanceTypeResult result = new ModifyInstanceTypeResult();
		VmTypeDetail nType = new VmTypeDetail();
		nType.setName(instanceType);
		nType.setCpu(cpu);
		nType.setDisk(disk);
		nType.setMemory(mem);
		result.setInstanceType(nType);
		ret.setData(JSONMessage.assembleModifyInstanceType(result));	
		return ret;
	}
	
	/**
	 * query images
	 * @param images - null stand for all images
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getImages(ArrayList<String> images) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"discribeImages", true, null, 0);
		DescribeImagesResult result = new DescribeImagesResult();
		if (images == null) {
			result.getImages().add(eri);
			result.getImages().add(eki);
			result.getImages().add(emi_ebs);
			result.getImages().add(emi_inst);
		} else {
			for(String image:images) {
				if (eri.getImageId().equals(image))
					result.getImages().add(eri);
				if (eki.getImageId().equals(image))
					result.getImages().add(eki);
				if (emi_inst.getImageId().equals(image))
					result.getImages().add(emi_inst);
				if (emi_ebs.getImageId().equals(image))
					result.getImages().add(emi_ebs);
			}
		}
		ret.setData(JSONMessage.assembleDescribeImagesResult(result));
		return ret;
	}
	
	/**
	 * deregister images
	 * @param image
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage deregisterImage(String image) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DeregisterImage", true, null, 0);
		ret.setData(null);
		return ret;
	}
	
	/**
	 * set the Image as public or private image 
	 * @param image
	 * @param ispublic
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage modifyImage(String image, boolean ispublic) {
		
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"ModifyImageAttributes", true, null, 0);
		ret.setData(null);
		return ret;
	}
	
	/**
	 * Create account
	 * @param name - name of the account
	 * @return eucaConsoleMessage (account id & name)
	 */
	public static EucaConsoleMessage createAccount(String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("createAccount", true, null, 0);		
		//CreateUserResult result = new CreateUserResult();
		Account account = new Account();
		account.setAccountId(getRandomString(15));
		account.setAccountName(name);
		account.setAccountStatus("confirmed");
		ret.setData(JSONMessage.assembleAccountType(account));
		return ret;	
	}
	
	/**
	 * delete a account
	 * @param name
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage delAccount(String name) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteAccount", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	/**
	 * get account list
	 * @return eucaConsoleMessage (account id & name)
	 */
	public static EucaConsoleMessage getAccountList() {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getAccountList", true, null, 0);
		java.util.Collection<Account> accounts = new java.util.ArrayList<Account>();
		accounts.add(demoAccount);
		accounts.add(adminAccount);
		ret.setData(JSONMessage.assembleAccountListsType(accounts));
		return ret;	
	}
	
    /**
     * return the all policy list of account
     * @param account
     * @return
     */
	public static EucaConsoleMessage getAccountPolicyList(String account) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getAccountPolicyList", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		java.util.Collection<EucaPolicy> policies = new java.util.ArrayList<EucaPolicy>();
		policies.add(demoPolicy);
		ret.setData(JSONMessage.assemblePolicyListsType(policies,true));
		return ret;
	}
	
	/**
     * return the policy info of a account
     * @param account
     * @param policy  - name of the policy
     * @return eucaConsoleMessage
     */
	public static EucaConsoleMessage getAccountPolicy(String account, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getAccountPolicy", true, null, 0);		
		ret.setData(JSONMessage.assemblePolicyType(demoPolicy, true));
		return ret;
	}
	
	/**
	 * add a policy to a account
	 * @param account
	 * @param policy		- name of the policy
	 * @param policy_conent - content of the policy
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage addAccountPolicy(String account, String policy, String policy_conent) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("addAccountPolicy", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * delete a policy of a account
	 * @param account
	 * @param policy
	 * @return
	 */
	public static EucaConsoleMessage delAccountPolicy(String account, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteAccountPolicy", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * add a user group
	 * @param account  - name of account
	 * @param group    - name of group
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage createGroup(String account, String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("createGroup", true, null, 0);		
		//CreateUserResult result = new CreateUserResult();
		EucaGroup eucaGroup = new EucaGroup();
		eucaGroup.setGroupId(getRandomString(21));
		eucaGroup.setGroupName(group);
		eucaGroup.setAccount(account);
		eucaGroup.setPath("/");
		
		ret.setData(JSONMessage.assembleGroupType(eucaGroup));
		return ret;	
	}
	
	/**
	 * delete a user group
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage delGroup(String account, String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteGroup", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * get list of user group
	 * @param account
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getGroupList(String account) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getGroupList", true, null, 0);
		java.util.Collection<EucaGroup> groups = new java.util.ArrayList<EucaGroup>();
		groups.add(demoGroup);
		ret.setData(JSONMessage.assembleGroupListsType(groups));
		return ret;	
	}
	
	/**
	 * add a existing user to a existing group
	 * @param account
	 * @param group
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage addGroupUser(String account,String group,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("addUserToGroup", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * delete a user from existing user group
	 * @param account
	 * @param group
	 * @param user
	 * @return
	 */
	public static EucaConsoleMessage delGroupUser(String account,String group,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteUserFromGroup", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * get user list of a group
	 * @param account
	 * @param group
	 * @return
	 */
	public static EucaConsoleMessage getGroupUserList(String account,String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getUserListForGroup", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		java.util.Collection<EucaUser> users = new java.util.ArrayList<EucaUser>();
		users.add(demoUser);
		//result.s
		ret.setData(JSONMessage.assembleUserListsType(users));
		return ret;	
	}
	/**
	 * Update the group name
	 * @param account
	 * @param old_group
	 * @param new_group
	 * @return
	 */
	public static EucaConsoleMessage modifyGroup(String account,String old_group, String new_group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("moifyGroup", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * get all policy list of a group
	 * @param account
	 * @param group
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getGroupPolicyList(String account,String group) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getUserPolicyList", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		java.util.Collection<EucaPolicy> policies = new java.util.ArrayList<EucaPolicy>();
		policies.add(demoPolicy);
		ret.setData(JSONMessage.assemblePolicyListsType(policies,true));
		return ret;
	}
	
	/**
	 * Get group policy content/info 
	 * @param account
	 * @param group
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getGroupPolicy(String account, String group, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getGroupPolicy", true, null, 0);		
		ret.setData(JSONMessage.assemblePolicyType(demoPolicy, true));
		return ret;
	}
	
	/**
	 * add policy to a group
	 * @param account
	 * @param group
	 * @param policy
	 * @param policy_conent
	 * @return
	 */
	public static EucaConsoleMessage addGroupPolicy(String account,String group, String policy, String policy_conent) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("putGroupPolicy", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * delete a policy of group
	 * @param account
	 * @param group
	 * @param policy
	 * @return
	 */
	public static EucaConsoleMessage delGroupPolicy(String account,String group, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteGroupPolicy", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * create a new user 
	 * @param account
	 * @param group  - null stand for no user group
	 * @param user
	 * @return
	 */
	public static EucaConsoleMessage createUser(String account,String group, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("createUser", true, null, 0);		
		//CreateUserResult result = new CreateUserResult();
		EucaUser eucaUser = new EucaUser();
		eucaUser.setUserId(getRandomString(21));
		eucaUser.setUserName(user);
		eucaUser.setPath("/");
		if (group !=null) {
			java.util.Collection<EucaGroup> groups = new java.util.ArrayList<EucaGroup> ();
			EucaGroup eucaGroup = new EucaGroup();
			eucaGroup.setGroupId(getRandomString(21));
			eucaGroup.setGroupName(group);
			groups.add(eucaGroup);
			eucaUser.setGroups(groups);
		}
		ret.setData(JSONMessage.assembleUserType(eucaUser));
		return ret;	
	}
	/**
	 * delete a user
	 * @param account
	 * @param user
	 * @return
	 */
	public static EucaConsoleMessage delUser(String account, String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteUser", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * return user list of a account 
	 * @param account
	 * @return
	 */
	public static EucaConsoleMessage getUserList(String account) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getUserList", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		java.util.Collection<EucaUser> users = new java.util.ArrayList<EucaUser>();
		users.add(adminUser);
		users.add(demoUser);
		//result.s
		ret.setData(JSONMessage.assembleUserListsType(users));
		return ret;	
	}
	/**
	 * return group list of a user
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getUserGroupList(String account,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getUserGroupList", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		java.util.Collection<EucaGroup> groups = new java.util.ArrayList<EucaGroup>();
		groups.add(demoGroup);
		ret.setData(JSONMessage.assembleGroupListsType(groups));
		return ret;	
	}
	
	/**
	 * get all policy list of a user
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getUserPolicyList(String account,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getUserPolicyList", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		java.util.Collection<EucaPolicy> policies = new java.util.ArrayList<EucaPolicy>();
		policies.add(demoPolicy);
		ret.setData(JSONMessage.assemblePolicyListsType(policies,true));
		return ret;	
	}
	
	/**
	 * Get user policy content/info 
	 * @param account
	 * @param user
	 * @param policy
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getUserPolicy(String account, String user, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getUserPolicy", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		ret.setData(JSONMessage.assemblePolicyType(demoPolicy,true));
		return ret;	
	}
	
	/**
	 * add policy to a user
	 * @param account
	 * @param user
	 * @param policy
	 * @param policy_conent
	 * @return
	 */
	public static EucaConsoleMessage addUserPolicy(String account,String user, String policy, String policy_conent) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("putUserPolicy", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * delete a policy of user
	 * @param account
	 * @param user
	 * @param policy
	 * @return
	 */
	public static EucaConsoleMessage delUserPolicy(String account,String user, String policy) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteUserPolicy", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		ret.setData(null);
		return ret;	
	}
	
    /**
     * return the x509 certs of a user 
     * @param account
     * @param user
     * @return eucaConsoleMessage
     */
	public static EucaConsoleMessage getUserCerts(String account,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getUserCerts", true, null, 0);
		//ListUsersResult result = new ListUsersResult();
		ListSigningCertificatesResult result = new ListSigningCertificatesResult();
		java.util.Collection<SigningCertificate> certificates = new java.util.ArrayList<SigningCertificate>();
		SigningCertificate cert = new SigningCertificate();
		cert.setStatus("active");
		cert.setUserName(user);
		cert.setCertificateId("VBEDUTILEBG1AC6QOI4QF");
		cert.setCertificateBody("-----BEGIN CERTIFICATE-----\n" + 
								"MIIDFjCCAf6gAwIBAgIHAT88i408hTANBgkqhkiG9w0BAQ0FADBBMQswCQYDVQQG\n" +
								"EwJVUzENMAsGA1UEChMEVXNlcjETMBEGA1UECxMKRXVjYWx5cHR1czEOMAwGA1UE\n" +
								"AxMFYWRtaW4wHhcNMTMwODE5MDczMDM1WhcNMTgwODE5MDczMDM1WjBBMQswCQYD\n" +
								"VQQGEwJVUzENMAsGA1UEChMEVXNlcjETMBEGA1UECxMKRXVjYWx5cHR1czEOMAwG\n" +
								"A1UEAxMFYWRtaW4wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCSKaQg\n" +
								"Aa9xdH0OelVpRElk9453dIyd9x/vkTLOsfYXH5VMS63rfh2UMslVKzRqAECJ5f2f\n" +
								"meDG9Y1aQ4TucEqmzGdiadj8weV4CeJiM1ucx5UtQyv02CFyJapR4mb096w0nH18\n" +
								"TYVTA9YC4KiZaKAN8MrQ9I6hWmXd7xmdctzEeKAkugaafgN90heeP4cNsGxmk/bL\n" +
								"yg0wN8IUt2YC7qEaRUpZULlrKt1NXFXqU5FWvpD3BZdsw8BV5vj2xA8SBbFVj2GR\n" +
								"o2i0tKrxdWaLCLqwLCCSDBZxz1xCkIBzwu2upVwb9shL3vYJQLIjkMykrPXrQ6Y9\n" +
								"Ofnz+2bOn63TJmqRAgMBAAGjEzARMA8GA1UdEwEB/wQFMAMBAf8wDQYJKoZIhvcN\n" +
								"AQENBQADggEBAC53XkbEajoxbhkRdsG1E+uNZS7NfK+lo6jHTlGt/LutCmmqNgME\n" +
								"otXl48wPCxYLYl2TP7mHHHtcfTKdNkr+E5qaAII9F5kxKUM1AQ2SxpdEEOkLWRD/\n" +
								"iajz4Eq9cE4/+4k6KqOePnc6qtxOtcCnJYwhW0tO9xjh1bfYhVuWR3UcJ5THc0ns\n" +
								"gdpuOA/zNmP2rOGhzMsqcH2EXCxsedH0UB3obQnsjXy8+cuyeAKdjrbR2BU9ZavT\n" +
								"4sSQ0woeKBXiG2tcTlg3lF9SJ5IfyvGRCRaMbuZaY7cq+BuF0lO4Rjh8M03C7rCf\n" +
								"J1ijwpx9TUQGQA8VXh6bqFCYK0d/sdvDiho=\n" +
								"-----END CERTIFICATE-----");
		certificates.add(cert);
		result.setCertificates(certificates);
		ret.setData(JSONMessage.assembleListCertificatesResult(result));
		return ret;	
	}
	
	/**
     * add new x509 certs of a user 
     * @param account
     * @param user
     * @return eucaConsoleMessage
     */
	public static EucaConsoleMessage addUserCerts(String account,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("loadUserCertificate", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
	
	/**
	 * Delete a user cert
	 * @param account
	 * @param user
	 * @param cert
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage delUserCerts(String account,String user,String cert) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteUserCertificate", true, null, 0);		
		ret.setData(null);
		return ret;
	}
	
	/**
	 * return the keys of a user
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage getUserKeys(String account,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("listUserAccessKeys", true, null, 0);		
		ListAccessKeysResult results = new ListAccessKeysResult();
		if ( user.equals("demo") ) {
			results.getAccessKeyMetadata().add(demoKey);
		}
		if ( user.equals("admin") ) {
			results.getAccessKeyMetadata().add(adminKey);
		}
		ret.setData(JSONMessage.assembleListAccessKeysResult(results));
		return ret;
		
	}
	/**
	 * add a new user key
	 * @param account
	 * @param user
	 * @return eucaConsoleMessage
	 */
	public static EucaConsoleMessage addUserKeys(String account,String user) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("CreateAccessKeys", true, null, 0);
		CreateAccessKeyResult result = new CreateAccessKeyResult();
		AccessKey key = new AccessKey();
		key.setUserName(user);
		key.setStatus("active");
		key.setAccessKeyId("BEQTZHRWOZ8STKAALCOFJ");
		key.setSecretAccessKey("BwNHPfpfdhXP1rtb3ooebIhvbr7PJO2RT9OO0LjA");
		result.setAccessKey(key);
		ret.setData(JSONMessage.assembleCreateAccessKeyResult(result));
		return ret;

	}
	/**
	 * delete a user key
	 * @param account
	 * @param user
	 * @param key
	 * @return
	 */
	public static EucaConsoleMessage delUserKeys(String account,String user,String key) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("deleteAccessKey", true, null, 0);		
		ret.setData(null);
		return ret;
	}
	
	/**
	 * add the user's login password for new user
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public static EucaConsoleMessage addUserLogin(String account,String user,String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("addUserLoginProfile", true, null, 0);		
		ret.setData(null);
		return ret;
	}
	
	/**
	 * modify the user's login password
	 * @param account
	 * @param user
	 * @param password
	 * @return
	 */
	public static EucaConsoleMessage modifyUserLogin(String account,String user,String password) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("updateUserLoginProfile", true, null, 0);		
		ret.setData(null);
		return ret;
	}
	
	/**
	 * Download the credentials file
	 * @param account
	 * @param user
	 * @param filePath
	 * @return
	 */
	public static EucaConsoleMessage downloadUserCredentials(String account,String user, String filePath) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("downloadCredentials", true, null, 0);		
		try {
			File creds = new File(filePath);
			if (creds.exists()) {
				creds.delete();
				creds.createNewFile();
			} else {
				File dir = creds.getParentFile();
				if (!dir.exists() )
					dir.mkdirs();
				creds.createNewFile();
			}
			ret.setData(JSONMessage.assembleDownloadCredsResult(filePath));
		} catch (Exception e) {
			ret.setErrCode(1);
			ret.setErrMessage(e.toString());
		}
		
		return ret;
	}
	
	/**
     * return a block manager of a cluster
     * @param cluster
     * @return
     */
	public static EucaConsoleMessage getBlockManagerProperties(String cluster, String manager) {

		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DescribeProperties", true, null, 0);
		
		ArrayList<String> properties = new ArrayList<String>();
		if (manager.equals("clvm") ) {
			properties.add(cluster+".storage.sharedevice");
		}
		
		if (manager.equals("das") ) {
			properties.add(cluster+".storage.sharedevice");
		}
		/*
		if (manager.equals("clvm") ) {
			properties.add(cluster+".storage.dasdevice");
		} */
		if (manager.equals("overlay") ) {
			ret.setData(null);
			return ret;	
		}
		
		if (manager.equals("equallogic") ) {
			properties.add(cluster+".storage.sanhost");
			properties.add(cluster+".storage.sanuser");
			properties.add(cluster+".storage.sanpassword");
			properties.add(cluster+".storage.ncpaths");
			properties.add(cluster+".storage.scpaths");
		}
		
		if (manager.equals("netapp") ) {
			properties.add(cluster+".storage.sanhost");
			properties.add(cluster+".storage.sanuser");
			properties.add(cluster+".storage.sanpassword");
			properties.add(cluster+".storage.chapuser");
			properties.add(cluster+".storage.vservername");
			properties.add(cluster+".storage.ncpaths");
			properties.add(cluster+".storage.scpaths");
		}
		
		if (manager.equals("emc-vnx") ) {
			properties.add(cluster+".storage.sanhost");
			properties.add(cluster+".storage.sanuser");
			properties.add(cluster+".storage.sanpassword");
			properties.add(cluster+".storage.chapuser");
			properties.add(cluster+".storage.storagepool");
			properties.add(cluster+".storage.loginscope");
			properties.add(cluster+".storage.ncpaths");
			properties.add(cluster+".storage.scpaths");
		}
		return EucaAdminConsoleMockdata.describeProperties(properties);
	}
	
	public static EucaConsoleMessage getNetworkModeProperties(String mode) {
		
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage(
				"DescribeProperties", true, null, 0);
		
		ArrayList<String> properties = new ArrayList<String>();
		if (mode.toLowerCase().equals("managed") ) {
			properties.add("vnet_subnet");
			properties.add("vnet_netmask");
			properties.add("vnet_dns");
			properties.add("vnet_addrspernet");
			properties.add("vnet_publicips");
			properties.add("vnet_privinterface");
			properties.add("vnet_pubinterface");
			properties.add("vlan_range");			
		}
		if (mode.toLowerCase().equals("managed-novlan") ) {
			properties.add("vnet_subnet");
			properties.add("vnet_netmask");
			properties.add("vnet_dns");
			properties.add("vnet_addrspernet");
			properties.add("vnet_publicips");
			properties.add("vnet_privinterface");
			properties.add("vnet_pubinterface");			
		}
		if (mode.toLowerCase().equals("system") ) {
			ret.setData(null);
			return ret;				
		}
		
		if (mode.toLowerCase().equals("static") ) {
			properties.add("vnet_subnet");
			properties.add("vnet_netmask");
			properties.add("vnet_dns");
			properties.add("vnet_broadcast");
			properties.add("vnet_router");
			properties.add("vnet_privinterface");
			properties.add("vnet_macmap");	
			
		}
		
		return EucaAdminConsoleMockdata.describeProperties(properties);
	}
	
	public static EucaConsoleMessage getEbsServiceProperties(String cluster) {
				
		ArrayList<String> properties = new ArrayList<String>();
	    properties.add(cluster + ".storage.maxtotalvolumesizeingb");
	    properties.add(cluster + ".storage.maxvolumesizeingb");
		return EucaAdminConsoleMockdata.describeProperties(properties);
	}
	
	public static EucaConsoleMessage modifyEbsServiceProperties(String cluster,String para, String value) {	
    	String prop = cluster +".storage." + para;
		return EucaAdminConsoleMockdata.modifyProperty(prop,value);		
	}
	
    public static EucaConsoleMessage getDNSServiceProperties() {
				
		ArrayList<String> properties = new ArrayList<String>();
	    properties.add("system.dns.dnsdomain");
	    properties.add("bootstrap.webservices.use_dns_delegation");
	    properties.add("bootstrap.webservices.use_instance_dns");
	    properties.add("cloud.vmstate.instance_subdomain");
	    
		return EucaAdminConsoleMockdata.describeProperties(properties);	
	}
    
    public static EucaConsoleMessage modifyDNSServiceProperty(String para, String value) {	
    	String prop;
    	switch(para) {
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
    			return JSONMessage.getEucaConsoleMessage("modifyProperties", false, "Invalid property name", 400);
    	}

		return EucaAdminConsoleMockdata.modifyProperty(prop,value);		
	}
	
    public static EucaConsoleMessage getS3ServiceProperties() {
		
		ArrayList<String> properties = new ArrayList<String>();
	    properties.add("walrus.storagemaxbucketsizeinmb");
	    properties.add("walrus.storagemaxbucketperaccount");
	    properties.add("walrus.storagemaxcachesizeinmb");
	    properties.add("walrus.storagemaxtotalcapacity");
	    properties.add("walrus.storagemaxtotalsnapshotsizeingb");
		return EucaAdminConsoleMockdata.describeProperties(properties);		
	}
    
    public static EucaConsoleMessage modifyS3ServiceProperty(String para, String value) {
		
		String prop = "walrus."+ para;
		return EucaAdminConsoleMockdata.modifyProperty(prop,value);		
	}

    public static EucaConsoleMessage modifyLoadBalancerImage(String image) {
		return EucaAdminConsoleMockdata.modifyProperty("loadbalancing.loadbalancer_emi", image);
	}
    
    public static EucaConsoleMessage getLoadBalancerImage() {
		
    	ArrayList<String> properties = new ArrayList<String>();
	    properties.add("loadbalancing.loadbalancer_emi");
		return EucaAdminConsoleMockdata.describeProperties(properties);		
	}
    
    public static EucaConsoleMessage installBalancerImage() {
		
    	EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("installBalancerImage", true, null, 0);		
		ret.setData(null);
		return ret;	
	}
    
    /**
	 * generate instance report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public static EucaConsoleMessage genInstanceReport(Date startDay, Date endDay) {
		
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("genInstanceReport", true, null, 0);		
		InstanceReport report = new InstanceReport();
		report.setStartDay(startDay);
		report.setEndDay(endDay);
		java.util.Collection<InstanceReportData> reports = new ArrayList<InstanceReportData>();
		
		InstanceReportData inst1 = new InstanceReportData();
		inst1.setPartition("cluster01");
		inst1.setAccount("eucalyptus");
		inst1.setUser("admin");
		inst1.setInstanceId("i-9A6143B3");
		inst1.setDays(2);
		inst1.setCpuUsage(80.0);
		inst1.setNetTotalIn(0.5);
		inst1.setNetTotalOut(0.8);
		inst1.setNetExternalIn(1.0);
		inst1.setNetExternalOut(3.2);
		inst1.setDiskRead(50.0);
		inst1.setDiskWrite(40.0);
		inst1.setDiskIOPSWrite(140.0);
		inst1.setDiskIOPSRead(200.0);
		inst1.setDiskTimeRead(5.0);
		inst1.setDiskTimeWrite(4.0);
		reports.add(inst1);
		
		InstanceReportData inst2 = new InstanceReportData();
		inst2.setPartition("cluster01");
		inst2.setAccount("eucalyptus");
		inst2.setUser("admin");
		inst2.setInstanceId("i-09E13EF2");
		inst2.setDays(2);
		inst2.setCpuUsage(40.0);
		inst2.setNetTotalIn(0.1);
		inst2.setNetTotalOut(0.2);
		inst2.setNetExternalIn(0.0);
		inst2.setNetExternalOut(0.2);
		inst2.setDiskRead(10.0);
		inst2.setDiskWrite(20.0);
		inst2.setDiskIOPSWrite(100.0);
		inst2.setDiskIOPSRead(80.0);
		inst2.setDiskTimeRead(1.0);
		inst2.setDiskTimeWrite(0.2);
		reports.add(inst2);
		
		report.setReports(reports);
		ret.setData(JSONMessage.assmebileInstanceReport(report));

		return ret;	
	}
	
	/**
	 * generate capacity report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public static EucaConsoleMessage genCapacityReport(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("genCapacityReport", true, null, 0);		
		CapacityReport report = new CapacityReport();
		report.setStartDay(startDay);
		report.setEndDay(endDay);
		java.util.Collection<CapacityReportData> reports = new ArrayList<CapacityReportData>();
		
		CapacityReportData s3 = new CapacityReportData();
		s3.setResource("S3");
		s3.setTotal(500);
		s3.setAvailable(50);
		reports.add(s3);
		
		CapacityReportData ip = new CapacityReportData();
		ip.setResource("Elastic IP");
		ip.setTotal(100);
		ip.setAvailable(1);
		reports.add(ip);
		
		CapacityReportData ebs = new CapacityReportData();
		ebs.setResource("EBS Storage");
		ebs.setTotal(500);
		ebs.setAvailable(10);
		reports.add(ebs);
		
		CapacityReportData cpu = new CapacityReportData();
		cpu.setResource("EC2 CPU");
		cpu.setTotal(500);
		cpu.setAvailable(10);
		reports.add(cpu);
		
		CapacityReportData disk = new CapacityReportData();
		disk.setResource("EC2 Disk");
		disk.setTotal(500);
		disk.setAvailable(10);
		reports.add(disk);
		
		CapacityReportData mem = new CapacityReportData();
		mem.setResource("EC2 Memory");
		mem.setTotal(40960);
		mem.setAvailable(4096);
		reports.add(mem);
		
		report.setReports(reports);
		ret.setData(JSONMessage.assmebileCapacityReport(report));
		return ret;	
	}
    
	/**
	 * generate S3 report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public static EucaConsoleMessage genS3Report(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("genS3Report", true, null, 0);		
		S3Report report = new S3Report();
		report.setStartDay(startDay);
		report.setEndDay(endDay);
		java.util.Collection<S3ReportData> reports = new ArrayList<S3ReportData>();
		
		S3ReportData s1 = new S3ReportData();
		s1.setAccount("eucalyptus");
		s1.setUser("admin");
		s1.setBucket("centos6");
		s1.setObjects(23);
		s1.setTotalSize(5);
		s1.setGBDays(10);
		reports.add(s1);
		
		S3ReportData s2 = new S3ReportData();
		s2.setAccount("eucalyptus");
		s2.setUser("admin");
		s2.setBucket("images");
		s2.setObjects(10);
		s2.setTotalSize(20);
		s2.setGBDays(40);
		reports.add(s2);
		
		S3ReportData s3 = new S3ReportData();
		s3.setAccount("eucalyptus");
		s3.setUser("admin");
		s3.setBucket("snapset-8b6e3655-dab6-4458-a527-bb7697146139");
		s3.setObjects(1);
		s3.setTotalSize(5);
		s3.setGBDays(10);
		reports.add(s3);
		
		S3ReportData s4 = new S3ReportData();
		s4.setAccount("eucalyptus");
		s4.setUser("admin");
		s4.setBucket("snapset-2142be1e-9efb-4bd2-8e61-6c586541a5b4");
		s4.setObjects(1);
		s4.setTotalSize(0);
		s4.setGBDays(0);
		reports.add(s4);
		report.setReports(reports);
		ret.setData(JSONMessage.assmebileS3Report(report));
		return ret;	
	}
	
	/**
	 * generate Ip report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public static EucaConsoleMessage genIpReport(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("genIpReport", true, null, 0);		
		IpReport report = new IpReport();
		report.setStartDay(startDay);
		report.setEndDay(endDay);
		java.util.Collection<IpReportData> reports = new ArrayList<IpReportData>();
		
		IpReportData i1 = new IpReportData();
		i1.setAccount("eucalyptus");
		i1.setUser("admin");
		i1.setInstanceId("i-9A6143B3");
		i1.setIp("10.101.1.200");
		i1.setDays(1);
		reports.add(i1);
		
		IpReportData i2 = new IpReportData();
		i2.setAccount("eucalyptus");
		i2.setUser("admin");
		i2.setInstanceId("i-09E13EF2");
		i2.setIp("10.101.1.201");
		i2.setDays(1);
		reports.add(i2);
		
		
		report.setReports(reports);
		ret.setData(JSONMessage.assmebileIpReport(report));
		return ret;	
	}
	
	/**
	 * generate Volume report
	 * @param startDay - reporting start day
	 * @param endDay   - reporting end day
	 * @return
	 */
	public static EucaConsoleMessage genVolumeReport(Date startDay, Date endDay) {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("genVolumeReport", true, null, 0);		
		VolumeReport report = new VolumeReport();
		report.setStartDay(startDay);
		report.setEndDay(endDay);
		java.util.Collection<VolumeReportData> reports = new ArrayList<VolumeReportData>();
		
		VolumeReportData v1 = new VolumeReportData();
		v1.setAccount("eucalyptus");
		v1.setUser("admin");
		v1.setVolume("vol-8AA93920");
		v1.setTotalSize(2);
		v1.setGBDays(2);
		reports.add(v1);
		
		VolumeReportData v2 = new VolumeReportData();
		v2.setAccount("eucalyptus");
		v2.setUser("admin");
		v2.setVolume("vol-F35A3D6B");
		v2.setTotalSize(2);
		v2.setGBDays(2);
		reports.add(v2);
		
		VolumeReportData v3 = new VolumeReportData();
		v3.setAccount("eucalyptus");
		v3.setUser("admin");
		v3.setVolume("vol-81513A43");
		v3.setTotalSize(2);
		v3.setGBDays(2);
		reports.add(v3);
		
		VolumeReportData v4 = new VolumeReportData();
		v4.setAccount("eucalyptus");
		v4.setUser("admin");
		v4.setVolume("vol-7E433E8D");
		v4.setTotalSize(2);
		v4.setGBDays(2);
		reports.add(v4);
		
		VolumeReportData v5 = new VolumeReportData();
		v5.setAccount("eucalyptus");
		v5.setUser("admin");
		v5.setVolume("vol-F7BB4138");
		v5.setTotalSize(2);
		v5.setGBDays(2);
		reports.add(v5);
		
		VolumeReportData v6 = new VolumeReportData();
		v6.setAccount("eucalyptus");
		v6.setUser("admin");
		v6.setVolume("vol-FF4D3E67");
		v6.setTotalSize(5);
		v6.setGBDays(5);
		reports.add(v6);
		
		report.setReports(reports);
		ret.setData(JSONMessage.assmebileVolumeReport(report));
		return ret;	
	}
	
	/**
	 * monitor the Component, return the component status and event
	 * @return
	 */
	public static EucaConsoleMessage monitorComponent() {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("monitoringComponents", true, null, 0);
		    ComponentsStatus result = new ComponentsStatus();
			
		    ComponentInfo clc = new ComponentInfo();
			clc.setName("clc01");
			clc.setHostName("192.168.1.100");
			clc.setPartition("eucalyptus");
			clc.setFullName("arn:euca:eucalyptus:::192.168.1.100/");
			clc.setState("enable");
			clc.setType("eucalyptus");
			clc.setDetail("");
			result.getComponents().add(clc);
		
			ComponentInfo walrus = new ComponentInfo();
			walrus.setName("walrus01");
			walrus.setHostName("192.168.1.100");
			walrus.setPartition("walrus");
			walrus.setFullName("arn:euca:bootstrap:walrus:walrus:walrus01/");
			walrus.setState("enable");
			walrus.setType("walrus");
			walrus.setDetail("");
			result.getComponents().add(walrus);
		
			ComponentInfo cc = new ComponentInfo();
			cc.setName("cc_01");
			cc.setHostName("192.168.1.100");
			cc.setPartition("cluster01");
			cc.setFullName("arn:euca:eucalyptus:cluster01:cluster:cc_01/");
			cc.setState("enable");
			cc.setType("cluster");
			cc.setDetail("");
			result.getComponents().add(cc);
		
			ComponentInfo sc = new ComponentInfo();
			sc.setName("sc_01");
			sc.setHostName("192.168.1.100");
			sc.setPartition("cluster01");
			sc.setFullName("arn:euca:eucalyptus:cluster01:storage:sc_01/");
			sc.setState("broken");
			sc.setType("storage");
			sc.setDetail("Error: The blockstoragemanager must be set");
			result.getComponents().add(sc);
		
			ComponentInfo node = new ComponentInfo();
			node.setName("node_01");
			node.setHostName("192.168.1.101");
			node.setPartition("cluster01");
			node.setFullName("arn:euca:eucalyptus:cluster01:node:node_01/");
			node.setState("enable");
			node.setType("node");
			node.setDetail("");
			result.getComponents().add(node);
		
			ComponentInfo node1 = new ComponentInfo();
			node1.setName("vmb_01");
			node1.setHostName("192.168.1.100");
			node1.setPartition("cluster01");
			node1.setFullName("arn:euca:eucalyptus:cluster01:vmwarebroker:vmb_01/");
			node1.setState("enable");
			node1.setType("vmwarebroker");
			node1.setDetail("");
			result.getComponents().add(node1);
		
		ret.setData(JSONMessage.assembleComponentsStatus(result));
		return ret;
	}
	
	/**
	 * retrieve the dashboard data
	 * @return
	 */
	public static EucaConsoleMessage getDashBoardData() {
		EucaConsoleMessage ret = JSONMessage.getEucaConsoleMessage("getDashBoard", true, null, 0);
		DashBoardData result = new DashBoardData();
		result.setAccounts(2);
		result.setUsers(4);
		result.setGroups(2);
		result.setTotalCpu(16);
		result.setTotalDisk(500);
		result.setTotalMem(40960);
		result.setTotalIp(100);
		result.setAvailableCpu(14);
		result.setAvailableIp(98);
		result.setAvailableMem(8192);
		result.setAvailableDisk(490);
		
		result.setRunInstances(2);
		result.setStoppedInstances(1);
		result.setPenddingInstances(0);
		
		result.setCreatedVolumes(4);
		result.setAttachedVolumes(2);
		
		ret.setData(JSONMessage.assembleDashBoardData(result));
		return ret;
	}
}
