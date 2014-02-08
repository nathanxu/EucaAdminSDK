package com.eucalyptus.admin;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.*;
import com.amazonaws.auth.*;
import com.amazonaws.handlers.HandlerChainFactory;
import com.amazonaws.http.StaxResponseHandler;
import com.amazonaws.http.DefaultErrorResponseHandler;
import com.amazonaws.http.ExecutionContext;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.transform.StaxUnmarshallerContext;
import com.amazonaws.transform.StandardErrorUnmarshaller;

import com.amazonaws.services.securitytoken.model.*;
import com.amazonaws.services.securitytoken.model.transform.*;
import com.eucalyptus.admin.console.model.*;
import com.eucalyptus.admin.console.model.transform.*;

public class EucaSessionTokenClient extends AmazonWebServiceClient  {



    protected final List<Unmarshaller<AmazonServiceException, Node>> exceptionUnmarshallers
            = new ArrayList<Unmarshaller<AmazonServiceException, Node>>();

    
    /**
     * Constructs a new client to invoke service methods on
     * AWSSecurityTokenService.  A credentials provider chain will be used
     * that searches for credentials in this order:
     * <ul>
     *  <li> Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY </li>
     *  <li> Java System Properties - aws.accessKeyId and aws.secretKey </li>
     *  <li> Instance profile credentials delivered through the Amazon EC2 metadata service </li>
     * </ul>
     *
     * <p>
     * All service calls made using this new client object are blocking, and will not
     * return until the service call completes.
     *
     * @see DefaultAWSCredentialsProvider
     */
    public EucaSessionTokenClient() {
        this(new DefaultAWSCredentialsProviderChain(), new ClientConfiguration());
    }

    
    public EucaSessionTokenClient(ClientConfiguration clientConfiguration) {
        this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
    }


   
    public EucaSessionTokenClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
        super(clientConfiguration);
        //this.awsCredentialsProvider = awsCredentialsProvider;
        init();
    }

    private void init() {
        exceptionUnmarshallers.add(new ExpiredTokenExceptionUnmarshaller());
        exceptionUnmarshallers.add(new IDPCommunicationErrorExceptionUnmarshaller());
        exceptionUnmarshallers.add(new MalformedPolicyDocumentExceptionUnmarshaller());
        exceptionUnmarshallers.add(new PackedPolicyTooLargeExceptionUnmarshaller());
        exceptionUnmarshallers.add(new IDPRejectedClaimExceptionUnmarshaller());
        exceptionUnmarshallers.add(new InvalidIdentityTokenExceptionUnmarshaller());
        exceptionUnmarshallers.add(new InvalidAuthorizationMessageExceptionUnmarshaller());
        
        exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
        setEndpoint("sts.amazonaws.com");

        HandlerChainFactory chainFactory = new HandlerChainFactory();
		requestHandlers.addAll(chainFactory.newRequestHandlerChain(
                "/com/amazonaws/services/securitytoken/request.handlers"));
    }

    
    /**
     * <p>
     * Returns a set of temporary credentials for an AWS account or IAM
     * user. The credentials consist of an access key ID, a secret access
     * key, and a security token. Typically, you use
     * <code>GetSessionToken</code> if you want use MFA to protect
     * programmatic calls to specific AWS APIs like Amazon EC2
     * <code>StopInstances</code> . MFA-enabled IAM users would need to call
     * <code>GetSessionToken</code> and submit an MFA code that is associated
     * with their MFA device. Using the temporary security credentials that
     * are returned from the call, IAM users can then make programmatic calls
     * to APIs that require MFA authentication.
     * </p>
     * <p>
     * The <code>GetSessionToken</code> action must be called by using the
     * long-term AWS security credentials of the AWS account or an IAM user.
     * Credentials that are created by IAM users are valid for the duration
     * that you specify, between 900 seconds (15 minutes) and 129600 seconds
     * (36 hours); credentials that are created by using account credentials
     * have a maximum duration of 3600 seconds (1 hour).
     * </p>
     * <p>
     * The permissions that are granted to the federated user are the
     * intersection of the policy that is passed with the
     * <code>GetSessionToken</code> request and policies that are associated
     * with of the entity making the <code>GetSessionToken</code> call.
     * </p>
     * <p>
     * For more information about using <code>GetSessionToken</code> to
     * create temporary credentials, go to <a
     * /docs.aws.amazon.com/IAM/latest/UserGuide/CreatingSessionTokens.html">
     * Creating Temporary Credentials to Enable Access for IAM Users </a> in
     * <i>Using IAM</i> .
     * 
     * </p>
     *
     * @param getSessionTokenRequest Container for the necessary parameters
     *           to execute the GetSessionToken service method on
     *           AWSSecurityTokenService.
     * 
     * @return The response from the GetSessionToken service method, as
     *         returned by AWSSecurityTokenService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AWSSecurityTokenService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public GetSessionTokenResult getSessionToken(EucaGetSessionTokenRequest getSessionTokenRequest) 
            throws AmazonServiceException, AmazonClientException {
        Request<EucaGetSessionTokenRequest> request = new EucaGetSessionTokenRequestMarshaller().marshall(getSessionTokenRequest);
        return invoke(request, new GetSessionTokenResultStaxUnmarshaller());
    }
    
 
    /**
     * <p>
     * Returns a set of temporary credentials for an AWS account or IAM
     * user. The credentials consist of an access key ID, a secret access
     * key, and a security token. Typically, you use
     * <code>GetSessionToken</code> if you want use MFA to protect
     * programmatic calls to specific AWS APIs like Amazon EC2
     * <code>StopInstances</code> . MFA-enabled IAM users would need to call
     * <code>GetSessionToken</code> and submit an MFA code that is associated
     * with their MFA device. Using the temporary security credentials that
     * are returned from the call, IAM users can then make programmatic calls
     * to APIs that require MFA authentication.
     * </p>
     * <p>
     * The <code>GetSessionToken</code> action must be called by using the
     * long-term AWS security credentials of the AWS account or an IAM user.
     * Credentials that are created by IAM users are valid for the duration
     * that you specify, between 900 seconds (15 minutes) and 129600 seconds
     * (36 hours); credentials that are created by using account credentials
     * have a maximum duration of 3600 seconds (1 hour).
     * </p>
     * <p>
     * The permissions that are granted to the federated user are the
     * intersection of the policy that is passed with the
     * <code>GetSessionToken</code> request and policies that are associated
     * with of the entity making the <code>GetSessionToken</code> call.
     * </p>
     * <p>
     * For more information about using <code>GetSessionToken</code> to
     * create temporary credentials, go to <a
     * /docs.aws.amazon.com/IAM/latest/UserGuide/CreatingSessionTokens.html">
     * Creating Temporary Credentials to Enable Access for IAM Users </a> in
     * <i>Using IAM</i> .
     * 
     * </p>
     * 
     * @return The response from the GetSessionToken service method, as
     *         returned by AWSSecurityTokenService.
     * 
     *
     * @throws AmazonClientException
     *             If any internal errors are encountered inside the client while
     *             attempting to make the request or handle the response.  For example
     *             if a network connection is not available.
     * @throws AmazonServiceException
     *             If an error response is returned by AWSSecurityTokenService indicating
     *             either a problem with the data in the request, or a server side issue.
     */
    public GetSessionTokenResult getSessionToken() throws AmazonServiceException, AmazonClientException {
        return getSessionToken(new EucaGetSessionTokenRequest());
    }
    
    private <X, Y extends AmazonWebServiceRequest> X invoke(Request<Y> request, Unmarshaller<X, StaxUnmarshallerContext> unmarshaller) {
        request.setEndpoint(endpoint);
       // request.setTimeOffset(timeOffset);
        for (Entry<String, String> entry : request.getOriginalRequest().copyPrivateRequestParameters().entrySet()) {
            request.addParameter(entry.getKey(), entry.getValue());
        }

/*
        //AWSCredentials credentials = awsCredentialsProvider.getCredentials();
        AmazonWebServiceRequest originalRequest = request.getOriginalRequest();
        if (originalRequest != null && originalRequest.getRequestCredentials() != null) {
        	credentials = originalRequest.getRequestCredentials();
        }
*/
        ExecutionContext executionContext = createExecutionContext();
        //executionContext.setSigner(signer);
        //executionContext.setCredentials(credentials);
        
        StaxResponseHandler<X> responseHandler = new StaxResponseHandler<X>(unmarshaller);
        DefaultErrorResponseHandler errorResponseHandler = new DefaultErrorResponseHandler(exceptionUnmarshallers);

        return (X)client.execute(request, responseHandler, errorResponseHandler, executionContext);
    }
}
        