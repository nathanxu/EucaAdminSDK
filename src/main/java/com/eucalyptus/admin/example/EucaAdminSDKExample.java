package com.eucalyptus.admin.example;
import java.net.URI;
import java.util.Date;
import java.util.Calendar;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
//import com.amazonaws.auth.STSSessionCredentials;
import com.amazonaws.auth.SessionCredentialsProviderFactory;
import com.eucalyptus.admin.*;
import com.eucalyptus.admin.model.*;
//import com.eucalyptus.admin.util.B64;
import com.eucalyptus.admin.console.model.*;
import com.amazonaws.services.securitytoken.model.*;
public class EucaAdminSDKExample {


	private final String host;
	private final String accessKey;
	private final String secretKey;
	private BasicSessionCredentials creds;
	private Date  credsExpireTime;
	private String password;

	private AWSCredentials credentials() {
		return new BasicAWSCredentials(accessKey, secretKey);
		
	}

	public EucaAdminSDKExample(final String host, final String accessKey,
			final String secretKey, final String passwd) {
		this.host = host;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.password = passwd;
		this.creds = null;
	}
    private AWSCredentials getSessionToken() {
    	//Date now = ;
    	Calendar laterNow = Calendar.getInstance();
    	laterNow.setTime(new Date());
    	laterNow.add(Calendar.SECOND, 5);
    	if (this.creds!=null & laterNow.before(this.credsExpireTime))
    		return this.creds;
    	
    	EucaSessionTokenClient euca = new EucaSessionTokenClient();
		String servicePath = "/services/Tokens/";
		String endpoint = URI.create("http://" + host + ":8773/").resolve(servicePath)
			.toString();
		euca.setEndpoint(endpoint);
		EucaGetSessionTokenRequest request = new EucaGetSessionTokenRequest();
		request.setDurationSeconds(60);
	    request.setAccount("eucalyptus");
	    request.setUser("admin");
	    request.setPassword(this.password);
	    GetSessionTokenResult result = euca.getSessionToken(request);
	    this.credsExpireTime = result.getCredentials().getExpiration();
	    this.creds = new BasicSessionCredentials(result.getCredentials().getAccessKeyId(),result.getCredentials().getSecretAccessKey(),result.getCredentials().getSessionToken());
	    //result.getCredentials().getExpiration()
	    return this.creds;
	    //return new BasicAWSCredentials(result.getCredentials().getAccessKeyId(),result.getCredentials().getSecretAccessKey());
    }
    
	private void testGetSessionToken()
    {
		AWSCredentials creds = getSessionToken();
		
		/*
		String encString ="WVdSdGFXND1AWlhWallXeDVjSFIxY3c9PTphZG1pbg==";
		final String[] basicUsernamePassword = B64.standard.decString(encString).split( ":",2);
        final String[] encodedAccountUsername = basicUsernamePassword[0].split( "@" , 2 );

		//System.out.println(B64.standard.decString(encString));
		//System.out.println(B64.standard.decString(encodedAccountUsername[0]));
		//System.out.println(B64.standard.decString(encodedAccountUsername[1]));
		//EucaGetSessionTokenRequest request = new EucaGetSessionTokenRequest();
		request.setDurationSeconds(120);
	    request.setAccount("eucalyptus");
	    request.setUser("admin");
	    request.setPassword("admin");
	    GetSessionTokenResult result = euca.getSessionToken(request); */
	    System.out.print("AccessKey:" + creds.getAWSAccessKeyId());
	    System.out.print("Security Key:" + creds.getAWSSecretKey());
    } 
	private String cloudUri(final String servicePath) {
		return URI.create("http://" + host + ":8773/").resolve(servicePath)
				.toString();
	}

	private EucaAdminClient getEucaAdminClient() {
		AWSCredentials creds = getSessionToken();
		SessionCredentialsProviderFactory providerFactory = new SessionCredentialsProviderFactory();
		//providerFactory.
		//STSSessionCredentialsProvider provider = new STSessionCredentialsProvider(creds);
		final EucaAdminClient euca = new EucaAdminClient(creds);
		//EucaAdminClient euca = new EucaAdminClient(provider);
		euca.setEndpoint(cloudUri("/services/Properties/"));
		return euca;
	}

	
	private void describeProperties(final EucaAdminClient euca) throws Exception {
	
		final DescribePropertiesResult result = euca.describeProperties();

		java.util.ArrayList<CloudProperty> properties = result.getProperties();
		for (CloudProperty property:properties ) {
			System.out.println(property.getName()+ ":" + property.getValue() + " " + property.getDescription()); 
		}
		
	}
	
	private void modifyProperty(final EucaAdminClient euca) throws Exception {
		
		ModifyPropertyRequest request = new ModifyPropertyRequest();
		request.setName("cloud.vmstate.shut_down_time");
		request.setValue(13);
		request.setReset(false);
		
		ModifyPropertyResult result = euca.modifyProperty(request);

		if (result!=null) {
			System.out.println(result.getName()+ ":" + result.getValue() + " was " + result.getOldValue()); 
		}
		
	}
	
	private void describeComponents(final EucaAdminClient euca) throws Exception {
		euca.setEndpoint(cloudUri("/services/Configuration/"));
		DescribeComponentsRequest request = new DescribeComponentsRequest ();
		request.setAction("DescribeEucalyptus");
		
		DescribeComponentsResult result = euca.describeComponents(request);
        java.util.ArrayList<ComponentInfo> components = result.getComponents();
		for (ComponentInfo component:components) {
			System.out.println(component.getName()+ ":" + component.getHostName() + ":" + component.getPartition() +":" + component.getState() +":" +component.getFullName()+":" +component.getDetail()); 
		}
		
		request.setAction("DescribeClusters");
		result = euca.describeComponents(request);
        components = result.getComponents();
		for (ComponentInfo component:components) {
			System.out.println(component.getName()+ ":" + component.getHostName() + ":" + component.getPartition() +":" + component.getState() +":" +component.getFullName()+":" +component.getDetail()); 
		}
		
		request.setAction("DescribeStorageControllers");
		result = euca.describeComponents(request);
        components = result.getComponents();
		for (ComponentInfo component:components) {
			System.out.println(component.getName()+ ":" + component.getHostName() + ":" + component.getPartition() +":" + component.getState() +":" +component.getFullName()+":" +component.getDetail()); 
		}
		
	}
	
	private void describeServices(final EucaAdminClient euca) throws Exception {
		
		euca.setEndpoint(cloudUri("/services/Empyrean/"));
		DescribeServicesRequest request = new DescribeServicesRequest();
		request.setShowEvents(true);
		//request.setShowEventStacks(true);
		
		final DescribeServicesResult result = euca.describeServices(request);

		java.util.ArrayList<ServiceStatus> services = result.getServices();
		for (ServiceStatus service:services ) {
			System.out.println(service.getName()+ ":" + ":" + service.getFullName() + service.getPartition() + ":" + service.getType()+ ":" + service.getUri() + ":" + service.getLocalState()); 
			System.out.println("events:");
			java.util.ArrayList<ServiceStatusDetail> details = service.getStatusDetails();
			for (ServiceStatusDetail detail:details) {
			  System.out.println(detail.uuid+ ":" + detail.serviceHost+ ":" + detail.timestamp + ":" + detail.message + ":" + detail.stackTrace);
			}
			System.out.println("=======================================================");
		}
		
	}
    
	private void modifyService(final EucaAdminClient euca) throws Exception {
		
		euca.setEndpoint(cloudUri("/services/Empyrean/"));
		ModifyServiceRequest request = new ModifyServiceRequest();
		request.setServiceName("sc_02");
		request.setState("enable");
		
		final ModifyServiceResult result = euca.modifyServices(request);
		System.out.println("Response:" + result.getResult());		
	}
	
	private void describeVmTypes(final EucaAdminClient euca) throws Exception {
		
		euca.setEndpoint(cloudUri("/services/Eucalyptus/"));
		DescribeInstanceTypesRequest request = new DescribeInstanceTypesRequest();
		request.setAvailability(true);
		
		final DescribeInstanceTypesResult result = euca.describeVmTypes(request);
		java.util.ArrayList<VmTypeDetail> types = result.getInstanceTypeDetails();
		for(VmTypeDetail type:types) {
			System.out.println( "name: " + type.getName() + " cpu: " + type.getCpu() + " mem:" + type.getMemory() + " disk:" + type.getDisk());
			java.util.ArrayList<VmTypeZoneStatus> zones = type.getAvailability();
			for (VmTypeZoneStatus zone: zones) {
				System.out.println("zone:" + zone.getZoneName() + " available:" + zone.getAvailable() + " max:" + zone.getMax());
			}
			System.out.println("=======================================================");
		}
		
	}
	
	private void modifyVmType(final EucaAdminClient euca) throws Exception {
		euca.setEndpoint(cloudUri("/services/Eucalyptus/"));
		ModifyInstanceTypeRequest request = new ModifyInstanceTypeRequest();
		request.setName("m1.small");
		request.setCpu(1);
		request.setMemory(512);
		request.setDisk(5);
		try {
			ModifyInstanceTypeResult result = euca.modifyVmTypes(request);
			VmTypeDetail newType = result.getInstanceType();
			System.out.println("name: " + newType.getName() + " cpu: "
					+ newType.getCpu() + " mem:" + newType.getMemory()
					+ " disk:" + newType.getDisk());
			VmTypeDetail oldType = result.getOldInstanceType();
			System.out.println("name: " + oldType.getName() + " cpu: "
					+ oldType.getCpu() + " mem:" + oldType.getMemory()
					+ " disk:" + oldType.getDisk());

			// test reset
			request.setReset(true);
			result = euca.modifyVmTypes(request);
			newType = result.getInstanceType();
			System.out.println("name: " + newType.getName() + " cpu: "
					+ newType.getCpu() + " mem:" + newType.getMemory()
					+ " disk:" + newType.getDisk());
			oldType = result.getOldInstanceType();
			System.out.println("name: " + oldType.getName() + " cpu: "
					+ oldType.getCpu() + " mem:" + oldType.getMemory()
					+ " disk:" + oldType.getDisk());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
	}
	public void testDescribeProperties() throws Exception {
		final EucaAdminClient euca = getEucaAdminClient();
    	
		describeProperties(euca);
		//modifyProperty(euca);
		
	}
	public void testModifyProperty() throws Exception {
		final EucaAdminClient euca = getEucaAdminClient();
		modifyProperty(euca);	
	}
	
	public void testDescribeServices() throws Exception {
		final EucaAdminClient euca = getEucaAdminClient();
		
		describeServices(euca);	
	}
	
	public void testModifyService() throws Exception {
		final EucaAdminClient euca = getEucaAdminClient();
		
		modifyService(euca);	
	}
	
	public void testDescribeComponents() throws Exception {
		final EucaAdminClient euca = getEucaAdminClient();	
		describeComponents(euca);	
	}
	
	public void testDescribeVmTypes() throws Exception {
		final EucaAdminClient euca = getEucaAdminClient();	
		describeVmTypes(euca);	
	}
	
	public void testModifyVmType() throws Exception {
		final EucaAdminClient euca = getEucaAdminClient();	
		modifyVmType(euca);	
	}
	public static void main(final String[] args) throws Exception {

		EucaAdminSDKExample example;
		if (args.length == 3) {
			example = new EucaAdminSDKExample(args[1],"eucalyputs","admin",args[2]);
		} else {
			example = new EucaAdminSDKExample("192.168.1.100","eucalyputs","admin","test@demo");
	    }
	
		if(args[0].equals("ModifyProperty") || args[0].equals("all") ) {
			example.testModifyProperty();
		}
		
		if(args[0].equals("DescribeProperties") || args[0].equals("all") ) {
			example.testDescribeProperties();
		}
		
		if(args[0].equals("DescribeServices") || args[0].equals("all") ) {
			example.testDescribeServices();
		}
		
		if(args[0].equals("ModifyService") || args[0].equals("all") ) {
			example.testModifyService();
		}
		if(args[0].equals("DescribeComponents") || args[0].equals("all") ) {
			example.testDescribeComponents();
		}
		if(args[0].equals("DescribeVmTypes") || args[0].equals("all") ) {
			example.testDescribeVmTypes();
		}
		if(args[0].equals("ModifyVmType") || args[0].equals("all") ) {
			example.testModifyVmType();
		}
		
		if(args[0].equals("GetSessionToken") || args[0].equals("all") ) {
			example.testGetSessionToken();
		}
		
			
	}

}
