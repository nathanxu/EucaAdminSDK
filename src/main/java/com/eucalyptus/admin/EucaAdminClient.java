package com.eucalyptus.admin;

import org.w3c.dom.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.*;
import com.amazonaws.auth.*;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.handlers.RequestHandler;
import com.amazonaws.http.StaxResponseHandler;
import com.amazonaws.http.DefaultErrorResponseHandler;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.LegacyErrorUnmarshaller;
import com.amazonaws.ClientConfiguration;
import com.eucalyptus.admin.model.*;
import com.eucalyptus.admin.model.transform.*;

public class EucaAdminClient extends AmazonWebServiceClient {
	
	//private AWSSessionCredentials sessionCredntials;
	private AWSCredentialsProvider awsCredentialsProvider;
	protected final List<Unmarshaller<AmazonServiceException, Node>> exceptionUnmarshallers = new ArrayList<Unmarshaller<AmazonServiceException, Node>>();
	
	private QueryStringSigner signer;
	
	
	public EucaAdminClient(AWSCredentials awsCredentials) {
		this(awsCredentials, new ClientConfiguration());
	}
	public EucaAdminClient(AWSCredentialsProvider awsCredentialsProvider) {
	        this(awsCredentialsProvider, new ClientConfiguration());
	}
	
	public EucaAdminClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        this.awsCredentialsProvider = awsCredentialsProvider;
        init();
    }
	
	public EucaAdminClient(AWSSessionCredentials provider) {
		this(provider, new ClientConfiguration());
	}
	
	public EucaAdminClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
		super(clientConfiguration);
		this.awsCredentialsProvider = new StaticCredentialsProvider(awsCredentials);
		init();
	}
	
    private void init() {
    	
    	exceptionUnmarshallers.add(new LegacyErrorUnmarshaller());
        setEndpoint("ec2.amazonaws.com");
        signer = new QueryStringSigner();
        
        HandlerChainFactory chainFactory = new HandlerChainFactory();
		requestHandlers.addAll(chainFactory.newRequestHandlerChain(
                "/com/amazonaws/services/ec2/request.handlers"));
    }
	/*
	public boolean login(String account, String User, String password) {
		AWSSecurityTokenServiceClient client = new AWSSecurityTokenServiceClient();
		stoken.
	}*/
	
	private <X, Y extends AmazonWebServiceRequest> X invoke(Request<Y> request, Unmarshaller<X, StaxUnmarshallerContext> unmarshaller) {
        request.setEndpoint(endpoint);
       // request.setTimeOffset(timeOffset);
        //request.setTimeOffset(timeOffset);
        
        for (Entry<String, String> entry : request.getOriginalRequest().copyPrivateRequestParameters().entrySet()) {
            request.addParameter(entry.getKey(), entry.getValue());
        }

        AWSCredentials credentials = awsCredentialsProvider.getCredentials();
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
        	credentials = originalRequest.getRequestCredentials();
        }
        ExecutionContext executionContext = createExecutionContext();
        executionContext.setSigner(signer);
        executionContext.setCredentials(credentials);
        
        StaxResponseHandler<X> responseHandler = new StaxResponseHandler<X>(unmarshaller);
        DefaultErrorResponseHandler errorResponseHandler = new DefaultErrorResponseHandler(exceptionUnmarshallers);

        return (X)client.execute(request, responseHandler, errorResponseHandler, executionContext);
    }
	
	public DescribePropertiesResult describeProperties(DescribePropertiesRequest describePropertiesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribePropertiesRequest> request = new DescribePropertiesRequestMarshaller().marshall(describePropertiesRequest);
        return invoke(request, new DescribePropertiesResultStaxUnmarshaller());
    }
	
	public DescribePropertiesResult describeProperties() 
            throws AmazonServiceException, AmazonClientException {
		return describeProperties(new DescribePropertiesRequest());
    }
	
	public ModifyPropertyResult modifyProperty(ModifyPropertyRequest modifyPropertyRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<ModifyPropertyRequest> request = new ModifyPropertyRequestMarshaller().marshall(modifyPropertyRequest);
        return invoke(request, new ModifyPropertyResultStaxUnmarshaller());
    }
	
	public DescribeServicesResult describeServices(DescribeServicesRequest describeServicesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeServicesRequest> request = new DescribeServicesRequestMarshaller().marshall(describeServicesRequest);
        return invoke(request, new DescribeServicesResultStaxUnmarshaller());
    }
	
	public DescribeServicesResult describeServices() 
            throws AmazonServiceException, AmazonClientException {
		return  describeServices(new DescribeServicesRequest());
    }
	
	public ModifyServiceResult modifyServices(ModifyServiceRequest modifyServiceRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<ModifyServiceRequest> request = new ModifyServiceRequestMarshaller().marshall(modifyServiceRequest);
        return invoke(request, new ModifyServiceResultStaxUnmarshaller());
    }
	
	public DescribeComponentsResult describeComponents(DescribeComponentsRequest describeComponentsRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeComponentsRequest> request = new DescribeComponentsRequestMarshaller().marshall(describeComponentsRequest);
        return invoke(request, new DescribeComponentsResultStaxUnmarshaller());
    }
	
	public DescribeInstanceTypesResult describeVmTypes(DescribeInstanceTypesRequest describeInstanceTypesRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DescribeInstanceTypesRequest> request = new DescribeInstanceTypesRequestMarshaller().marshall(describeInstanceTypesRequest);
        return invoke(request, new DescribeInstanceTypesResultStaxUnmarshaller());
    }
	
	public DescribeInstanceTypesResult describeVmTypes() 
            throws AmazonServiceException, AmazonClientException {
        return describeVmTypes (new DescribeInstanceTypesRequest());
    }
	
	public ModifyInstanceTypeResult modifyVmTypes(ModifyInstanceTypeRequest modifyInstanceTypeRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<ModifyInstanceTypeRequest> request = new ModifyInstanceTypeRequestMarshaller().marshall(modifyInstanceTypeRequest);
        return invoke(request, new ModifyInstanceTypeResultStaxUnmarshaller());
    }
	
	public RegisterComponentResult registerComponent(RegisterComponentRequest registerComponentRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<RegisterComponentRequest> request = new RegisterComponentRequestMarshaller().marshall(registerComponentRequest);
        return invoke(request, new RegisterComponentResultStaxUnmarshaller());
    }
	
	public DeregisterComponentResult deregisterComponent(DeregisterComponentRequest deregisterComponentRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<DeregisterComponentRequest> request = new DeregisterComponentRequestMarshaller().marshall(deregisterComponentRequest);
        return invoke(request, new DeregisterComponentResultStaxUnmarshaller());
    }
	
	public String generateReport (GenerateReportRequest generateReportRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<GenerateReportRequest> request = new GenerateReportRequestMarshaller().marshall(generateReportRequest);
        return invoke(request, new GenerateReportResultStaxUnmarshaller());
    }
}
