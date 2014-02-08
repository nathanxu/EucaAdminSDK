package com.eucalyptus.admin.test;

import java.net.URI;
import java.util.Calendar;
import java.util.Date;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.auth.SessionCredentialsProviderFactory;
import com.amazonaws.services.securitytoken.model.GetSessionTokenResult;
import com.eucalyptus.admin.EucaAdminClient;
import com.eucalyptus.admin.EucaSessionTokenClient;
import com.eucalyptus.admin.console.model.EucaGetSessionTokenRequest;
import com.eucalyptus.admin.model.CloudProperty;
import com.eucalyptus.admin.model.ComponentInfo;
import com.eucalyptus.admin.model.DescribeComponentsRequest;
import com.eucalyptus.admin.model.DescribeComponentsResult;
import com.eucalyptus.admin.model.DescribeInstanceTypesRequest;
import com.eucalyptus.admin.model.DescribeInstanceTypesResult;
import com.eucalyptus.admin.model.DescribePropertiesResult;
import com.eucalyptus.admin.model.DescribeServicesRequest;
import com.eucalyptus.admin.model.DescribeServicesResult;
import com.eucalyptus.admin.model.ModifyInstanceTypeRequest;
import com.eucalyptus.admin.model.ModifyInstanceTypeResult;
import com.eucalyptus.admin.model.ModifyPropertyRequest;
import com.eucalyptus.admin.model.ModifyPropertyResult;
import com.eucalyptus.admin.model.ModifyServiceRequest;
import com.eucalyptus.admin.model.ModifyServiceResult;
import com.eucalyptus.admin.model.ServiceStatus;
import com.eucalyptus.admin.model.ServiceStatusDetail;
import com.eucalyptus.admin.model.VmTypeDetail;
import com.eucalyptus.admin.model.VmTypeZoneStatus;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class EucaAdminSDKTest extends TestCase {

	private String host = "192.";
	private String accessKey;
	private String secretKey;
	private BasicSessionCredentials creds;
	private Date credsExpireTime;
	private String password;

	public void setUp() throws Exception {

		this.host = "192.168.1.100";
		this.accessKey = "IU5NTJEYZJ1DMBB84UKAF";
		this.secretKey = "8Ek1e2tHO0D6gwaCNodKMSd3qUdWJ9wm3p2iqvEp";
		this.password = "test";
		this.creds = null;
	}

	private AWSCredentials credentials() {
		return new BasicAWSCredentials(accessKey, secretKey);

	}

	private AWSCredentials getSessionToken() {
		// Date now = ;
		Calendar laterNow = Calendar.getInstance();
		laterNow.setTime(new Date());
		laterNow.add(Calendar.SECOND, 5);
		if (this.creds != null & laterNow.before(this.credsExpireTime))
			return this.creds;

		EucaSessionTokenClient euca = new EucaSessionTokenClient();
		String servicePath = "/services/Tokens/";
		String endpoint = URI.create("http://" + host + ":8773/")
				.resolve(servicePath).toString();
		euca.setEndpoint(endpoint);
		EucaGetSessionTokenRequest request = new EucaGetSessionTokenRequest();
		request.setDurationSeconds(60);
		request.setAccount("eucalyptus");
		request.setUser("admin");
		request.setPassword(this.password);
		GetSessionTokenResult result = euca.getSessionToken(request);
		this.credsExpireTime = result.getCredentials().getExpiration();
		this.creds = new BasicSessionCredentials(result.getCredentials()
				.getAccessKeyId(),
				result.getCredentials().getSecretAccessKey(), result
						.getCredentials().getSessionToken());
		// result.getCredentials().getExpiration()
		return this.creds;

	}

	private String cloudUri(final String servicePath) {
		return URI.create("http://" + host + ":8773/").resolve(servicePath)
				.toString();
	}

	private EucaAdminClient getEucaAdminClient() {
		//AWSCredentials creds = getSessionToken();
		//SessionCredentialsProviderFactory providerFactory = new SessionCredentialsProviderFactory();
		final EucaAdminClient euca = new EucaAdminClient(credentials());
		// EucaAdminClient euca = new EucaAdminClient(provider);
		euca.setEndpoint(cloudUri("/services/Properties/"));
		return euca;
	}

	private void describeProperties(final EucaAdminClient euca)
			throws Exception {

		final DescribePropertiesResult result = euca.describeProperties();

		java.util.ArrayList<CloudProperty> properties = result.getProperties();
		for (CloudProperty property : properties) {
			System.out.println(property.getName() + ":" + property.getValue()
					+ " " + property.getDescription());
		}

	}

	private void modifyProperty(final EucaAdminClient euca) throws Exception {

		ModifyPropertyRequest request = new ModifyPropertyRequest();
		request.setName("cloud.vmstate.shut_down_time");
		request.setValue(13);
		request.setReset(false);

		ModifyPropertyResult result = euca.modifyProperty(request);
		if (result != null) {
			System.out.println(result.getName() + ":" + result.getValue()
					+ " was " + result.getOldValue());
		}

	}

	private void describeComponents(final EucaAdminClient euca)
			throws Exception {
		euca.setEndpoint(cloudUri("/services/Configuration/"));
		DescribeComponentsRequest request = new DescribeComponentsRequest();
		request.setAction("DescribeEucalyptus");

		DescribeComponentsResult result = euca.describeComponents(request);
		java.util.ArrayList<ComponentInfo> components = result.getComponents();
		for (ComponentInfo component : components) {
			System.out.println(component.getName() + ":"
					+ component.getHostName() + ":" + component.getPartition()
					+ ":" + component.getState() + ":"
					+ component.getFullName() + ":" + component.getDetail());
		}

		request.setAction("DescribeClusters");
		result = euca.describeComponents(request);
		components = result.getComponents();
		for (ComponentInfo component : components) {
			System.out.println(component.getName() + ":"
					+ component.getHostName() + ":" + component.getPartition()
					+ ":" + component.getState() + ":"
					+ component.getFullName() + ":" + component.getDetail());
		}

		request.setAction("DescribeStorageControllers");
		result = euca.describeComponents(request);
		components = result.getComponents();
		for (ComponentInfo component : components) {
			System.out.println(component.getName() + ":"
					+ component.getHostName() + ":" + component.getPartition()
					+ ":" + component.getState() + ":"
					+ component.getFullName() + ":" + component.getDetail());
		}

	}

	private void describeServices(final EucaAdminClient euca) throws Exception {

		euca.setEndpoint(cloudUri("/services/Empyrean/"));
		DescribeServicesRequest request = new DescribeServicesRequest();
		request.setShowEvents(true);
		// request.setShowEventStacks(true);

		final DescribeServicesResult result = euca.describeServices(request);

		java.util.ArrayList<ServiceStatus> services = result.getServices();
		for (ServiceStatus service : services) {
			System.out.println(service.getName() + ":" + ":"
					+ service.getFullName() + service.getPartition() + ":"
					+ service.getType() + ":" + service.getUri() + ":"
					+ service.getLocalState());
			System.out.println("events:");
			java.util.ArrayList<ServiceStatusDetail> details = service
					.getStatusDetails();
			for (ServiceStatusDetail detail : details) {
				System.out.println(detail.uuid + ":" + detail.serviceHost + ":"
						+ detail.timestamp + ":" + detail.message + ":"
						+ detail.stackTrace);
			}
			System.out
					.println("=======================================================");
		}

	}

	private void modifyService(final EucaAdminClient euca) throws Exception {

		euca.setEndpoint(cloudUri("/services/Empyrean/"));
		ModifyServiceRequest request = new ModifyServiceRequest();
		request.setServiceName("sc_01");
		request.setState("enable");

		final ModifyServiceResult result = euca.modifyServices(request);
		System.out.println("Response:" + result.getResult());
	}

	private void describeVmTypes(final EucaAdminClient euca) throws Exception {

		euca.setEndpoint(cloudUri("/services/Eucalyptus/"));
		DescribeInstanceTypesRequest request = new DescribeInstanceTypesRequest();
		request.setAvailability(true);

		final DescribeInstanceTypesResult result = euca
				.describeVmTypes(request);
		java.util.ArrayList<VmTypeDetail> types = result
				.getInstanceTypeDetails();
		for (VmTypeDetail type : types) {
			System.out.println("name: " + type.getName() + " cpu: "
					+ type.getCpu() + " mem:" + type.getMemory() + " disk:"
					+ type.getDisk());
			java.util.ArrayList<VmTypeZoneStatus> zones = type
					.getAvailability();
			for (VmTypeZoneStatus zone : zones) {
				System.out.println("zone:" + zone.getZoneName() + " available:"
						+ zone.getAvailable() + " max:" + zone.getMax());
			}
			System.out
					.println("=======================================================");
		}

	}

	private void modifyVmType(final EucaAdminClient euca) throws Exception {
		
		euca.setEndpoint(cloudUri("/services/Eucalyptus/"));
		ModifyInstanceTypeRequest request = new ModifyInstanceTypeRequest();
		request.setName("m1.xlarge");
		request.setCpu(2);
		request.setMemory(1024);
		request.setDisk(20);
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

			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void testGetSessionToken() {
		try {
			AWSCredentials creds = getSessionToken();
			if (creds != null) {
				System.out.println("getSessionToke test:");
				System.out.println("=================================================");
				System.out.println("AccessKey:" + creds.getAWSAccessKeyId());
				System.out.println("Security Key:" + creds.getAWSSecretKey());
				assertTrue(true);
			}
		} catch (Exception e) {
			assertTrue(false);
		}

	}

	public void testDescribeProperties() {

		try {
			final EucaAdminClient euca = this.getEucaAdminClient();
			System.out.println("describeProperties test:");
			System.out.println("==============================================");
			describeProperties(euca);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}

	}

	public void testModifyProperty() throws Exception {
		try {
			final EucaAdminClient euca = getEucaAdminClient();
			System.out.println("modifyProperty test:");
			System.out.println("==============================================");
			modifyProperty(euca);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testDescribeServices() throws Exception {
		try {
			final EucaAdminClient euca = getEucaAdminClient();
			System.out.println("describeServices test:");
			System.out.println("==============================================");
			describeServices(euca);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testModifyService() throws Exception {
		try {
			final EucaAdminClient euca = getEucaAdminClient();
			System.out.println("modifyService test:");
			System.out.println("==============================================");
			modifyService(euca);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testDescribeComponents() throws Exception {
		try {
			final EucaAdminClient euca = getEucaAdminClient();
			System.out.println("describeComponents test:");
			System.out.println("==============================================");
			describeComponents(euca);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testDescribeVmTypes() throws Exception {
		try {
			final EucaAdminClient euca = getEucaAdminClient();
			System.out.println("describeVmTypes test:");
			System.out.println("==============================================");
			describeVmTypes(euca);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	public void testModifyVmType() throws Exception {
		try {
			final EucaAdminClient euca = getEucaAdminClient();
			System.out.println("modifyVmType test:");
			System.out.println("==============================================");
			modifyVmType(euca);
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public EucaAdminSDKTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(EucaAdminSDKTest.class);
	}

}
