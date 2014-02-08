package com.eucalyptus.admin.console.model.transform;

import com.eucalyptus.admin.console.model.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.services.securitytoken.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.eucalyptus.admin.util.*;

/**
 * Get Session Token Request Marshaller
 */
public class EucaGetSessionTokenRequestMarshaller implements Marshaller<Request<EucaGetSessionTokenRequest>, EucaGetSessionTokenRequest> {

    public Request<EucaGetSessionTokenRequest> marshall(EucaGetSessionTokenRequest getSessionTokenRequest) {

        if (getSessionTokenRequest == null) {
		    throw new AmazonClientException("Invalid argument passed to marshall(...)");
		}

        Request<EucaGetSessionTokenRequest> request = new DefaultRequest<EucaGetSessionTokenRequest>(getSessionTokenRequest, "AWSSecurityTokenService");
        request.addParameter("Action", "GetSessionToken");
        request.addParameter("Version", "2011-06-15");

        if (getSessionTokenRequest.getDurationSeconds() != null) {
            request.addParameter("DurationSeconds", StringUtils.fromInteger(getSessionTokenRequest.getDurationSeconds()));
        }
        String authStr = B64.standard.encString(B64.standard.encString(getSessionTokenRequest.getUser()) + "@" + B64.standard.encString(getSessionTokenRequest.getAccount()) + ":" + getSessionTokenRequest.getPassword());
        request.addHeader("Authorization", StringUtils.fromString("Basic " + authStr));
        return request;
    }
}
