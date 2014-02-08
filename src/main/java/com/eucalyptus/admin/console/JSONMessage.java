package com.eucalyptus.admin.console;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.eucalyptus.admin.console.model.*;
import com.eucalyptus.admin.model.*;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Image;
//import com.eucalyptus.admin.console.model.
import com.amazonaws.services.securitytoken.model.*;
import com.amazonaws.services.identitymanagement.*;
import com.amazonaws.services.identitymanagement.model.*;
import java.text.SimpleDateFormat;

public class JSONMessage {
	
	public static EucaConsoleMessage getEucaConsoleMessage(
			String api, boolean status, String error,Integer errCode) {
		
		EucaConsoleMessage ret = new EucaConsoleMessage();
		ret.setApi(api);
		ret.setErrCode(errCode);
		ret.setStatus(status);
		ret.setErrMessage(error);
		return ret;
	}
	
	public static JSONArray assembleDescribeProperties(DescribePropertiesResult describePropertiesResult) {

		JSONArray properties = new JSONArray();
		ArrayList<CloudProperty> results = describePropertiesResult.getProperties();
		for (CloudProperty property: results){
			JSONObject obj= new JSONObject();
			obj.put("name", property.getName());
			obj.put("value", property.getValue());
			obj.put("description", property.getDescription());
			properties.add(obj);
		}
		return properties;	
	}
	
	public static JSONObject assembleModifyProperties(
			ModifyPropertyResult modifyPropertyResult) {

		if (modifyPropertyResult == null)
			return null;

		JSONObject obj = new JSONObject();

		obj.put("name", modifyPropertyResult.getName());
		obj.put("value", modifyPropertyResult.getName());
		obj.put("oldValue", modifyPropertyResult.getOldValue());
		return obj;
	}
	
	public static JSONArray assembleDescribeComponents(DescribeComponentsResult result) {

		JSONArray properties = new JSONArray();
		ArrayList<ComponentInfo> results = result.getComponents();
		for (ComponentInfo component: results){
			JSONObject obj= new JSONObject();
			obj.put("name", component.getName());
			obj.put("hostName", component.getHostName());
			obj.put("fullName", component.getFullName());
			obj.put("type", component.getType());
			obj.put("state", component.getState());
			obj.put("partition", component.getPartition());
			obj.put("detail", component.getDetail());
			properties.add(obj);
		}
		return properties;	
	}
	
	public static JSONArray assembleDescribeInstanceTypes(DescribeInstanceTypesResult result) {

		JSONArray array = new JSONArray();
		ArrayList<VmTypeDetail> results = result.getInstanceTypeDetails();
		for (VmTypeDetail vmType: results){
			JSONObject obj= new JSONObject();
			obj.put("name", vmType.getName());
			obj.put("cpu", vmType.getCpu());
			obj.put("memory", vmType.getMemory());
			obj.put("disk", vmType.getDisk());
			array.add(obj);
		}
		return array;	
	}
	
	public static JSONObject assembleModifyInstanceType(
			ModifyInstanceTypeResult result) {

		if (result == null)
			return null;

		JSONObject obj = new JSONObject();

		obj.put("name", result.getInstanceType().getName());
		obj.put("cpu", result.getInstanceType().getCpu());
		obj.put("disk",result.getInstanceType().getDisk());
		obj.put("memory",result.getInstanceType().getMemory());
		return obj;
	}
	
	public static JSONArray assembleDescribeImagesResult(DescribeImagesResult result) {

		JSONArray array = new JSONArray();
		java.util.List<Image> results = result.getImages();
		if (results ==null )
			return null;
		for (Image image: results){
			JSONObject obj= new JSONObject();
			obj.put("imageId", image.getImageId());
			obj.put("imageName", (image.getName()==null)?"":image.getName());
			obj.put("architecture", image.getArchitecture());
			obj.put("description", (image.getDescription()==null)?"":image.getDescription());
			obj.put("isPublic", image.isPublic());
			obj.put("kernel", (image.getKernelId()==null)?"":image.getKernelId());
			obj.put("ramdisk", image.getRamdiskId());
			obj.put("imageType", image.getRootDeviceType());
			obj.put("state", image.getState());
			array.add(obj);
		}
		return array;	
	}
	
	public static JSONObject assembleAddUserType(
			CreateUserResult result, String account) {

		if (result == null)
			return null;
		JSONObject obj = new JSONObject();
		obj.put("userName",result.getUser().getUserName() );
		obj.put("userId", result.getUser().getUserId());
		obj.put("userPath",result.getUser().getPath());
		obj.put("userAccount",account);
		obj.put("userStat","enable");
		
		return obj;
	}
	
	public static JSONObject assembleUserType(
			EucaUser result) {

		if (result == null)
			return null;
		
		JSONObject obj = new JSONObject();
		obj.put("userName",result.getUserName() );
		obj.put("userId", result.getUserId());
		obj.put("userPath",result.getPath());
		obj.put("userAccount",result.getAccount());
		obj.put("userStat","enable");
		JSONArray array = new JSONArray();
		java.util.Collection<EucaGroup> groups = result.getGroups();
		for (EucaGroup group:groups) {
			JSONObject objGroup = new JSONObject();
			objGroup.put("groupId", group.getGroupId());
			objGroup.put("groupName", group.getGroupName());
			array.add(objGroup);
		}
		obj.put("userGroups",array);
		return obj;
	}
	
	public static JSONArray assembleUserListsType(
			java.util.Collection<EucaUser> result) {

		if (result == null)
			return null;

		JSONArray ret = new JSONArray();
		for (EucaUser user : result) {

			JSONObject obj = new JSONObject();
			obj.put("userName", user.getUserName());
			obj.put("userId", user.getUserId());
			obj.put("userPath", user.getPath());
			obj.put("userAccount", user.getAccount());
			obj.put("userStat", "enable");
			JSONArray array = new JSONArray();
			java.util.Collection<EucaGroup> groups = user.getGroups();
			for (EucaGroup group : groups) {
				JSONObject objGroup = new JSONObject();
				objGroup.put("groupId", group.getGroupId());
				objGroup.put("groupName", group.getGroupName());
				array.add(objGroup);
			}
			obj.put("userGroups", array);
			ret.add(obj);
		}
		return ret;
	}
	
	public static JSONArray assembleGroupListsType(
			java.util.Collection<EucaGroup> result) {

		if (result == null)
			return null;

		JSONArray ret = new JSONArray();
		for (EucaGroup group : result) {

			JSONObject obj = new JSONObject();
			obj.put("groupName", group.getGroupName());
			obj.put("groupId", group.getGroupId());
			obj.put("groupPath", group.getPath());
			obj.put("groupAccount", group.getAccount());
			ret.add(obj);
		}
		return ret;
	}
	
	public static JSONObject assembleGroupType(EucaGroup result) {

		if (result == null)
			return null;

		JSONObject obj = new JSONObject();
		obj.put("groupName", result.getGroupName());
		obj.put("groupId", result.getGroupId());
		obj.put("groupPath", result.getPath());
		obj.put("groupAccount", result.getAccount());

		return obj;
	}
	
	public static JSONObject assemblePolicyType(EucaPolicy result, boolean fullDisplay) {

		if (result == null)
			return null;

		JSONObject obj = new JSONObject();
		if (fullDisplay) {
			obj.put("accountName", result.getAccountName());
			obj.put("groupName", result.getGroupName());
			obj.put("userName", result.getUserName());
			obj.put("policyDocument", result.getPolicyDocument());
		}
		obj.put("policyName", result.getPolicyName());
		return obj;
	}
	
	public static JSONArray assemblePolicyListsType(
			java.util.Collection<EucaPolicy> results, boolean fullDisplay) {

		if (results == null)
			return null;
		JSONArray ret = new JSONArray();
		for (EucaPolicy result : results) {

			JSONObject obj = new JSONObject();
			if (fullDisplay) {
				obj.put("accountName", result.getAccountName());
				obj.put("groupName", result.getGroupName());
				obj.put("userName", result.getUserName());
				obj.put("policyDocument", result.getPolicyDocument());
			}
			obj.put("policyName", result.getPolicyName());
			ret.add(obj);
		}
		return ret;
	}
	
	public static JSONArray assembleAccountListsType(
			java.util.Collection<Account> results) {

		if (results == null)
			return null;
		JSONArray ret = new JSONArray();
		for (Account result : results) {
			JSONObject obj = new JSONObject();
			obj.put("accountName", result.getAccountName());
			obj.put("accountId", result.getAccountId());
			obj.put("accountStatus", result.getAccountStatus());
			ret.add(obj);
		}
		return ret;
	}

	public static JSONObject assembleAccountType(Account result) {

		if (result == null)
			return null;
		JSONObject obj = new JSONObject();
		obj.put("accountName", result.getAccountName());
		obj.put("accountId", result.getAccountId());
		obj.put("accountStatus", result.getAccountStatus());

		return obj;
	}
	
	public static JSONArray assembleListCertificatesResult(ListSigningCertificatesResult result) {

		JSONArray array = new JSONArray();
		java.util.List<SigningCertificate> results = result.getCertificates();
		if (results ==null )
			return null;
		for (SigningCertificate cert: results){
			JSONObject obj= new JSONObject();
			obj.put("certId", cert.getCertificateId());
			obj.put("certBody", cert.getCertificateBody());
			obj.put("status", cert.getStatus());	
			obj.put("userName", cert.getUserName());
			array.add(obj);
		}
		return array;	
	}
	
	public static JSONArray assembleListAccessKeysResult(ListAccessKeysResult result) {

		JSONArray array = new JSONArray();
		java.util.List<AccessKeyMetadata> results = result.getAccessKeyMetadata();
		if (results ==null )
			return null;
		for (AccessKeyMetadata key: results){
			JSONObject obj= new JSONObject();
			obj.put("accessKeyId", key.getAccessKeyId());
			obj.put("userName", key.getUserName());
			obj.put("status", key.getStatus());		
			array.add(obj);
		}
		return array;	
	}
	
	public static JSONObject assembleCreateAccessKeyResult(CreateAccessKeyResult result) {

		if (result == null)
			return null;
		JSONObject obj = new JSONObject();
		AccessKey key = result.getAccessKey();
		obj.put("accessKeyId", key.getAccessKeyId());
		obj.put("secretAccessKey", key.getSecretAccessKey());
		obj.put("userName", key.getUserName());
		obj.put("status", key.getStatus());
		return obj;
	}
	
	public static JSONObject assembleDownloadCredsResult(String filePath) {

		JSONObject obj = new JSONObject();		
		obj.put("path", filePath);
		return obj;
	}
	
	
	public static JSONObject assmebileCapacityReport(CapacityReport report) {
		if (report == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		JSONObject obj = new JSONObject();
		obj.put("startDay", sdf.format(report.getStartDay()));
		obj.put("endDay", sdf.format(report.getEndDay()));
		
		JSONArray array = new JSONArray();
		java.util.Collection<CapacityReportData> details = report.getReports();;
		for (CapacityReportData data: details){
			JSONObject reportObj= new JSONObject();
			reportObj.put("resource", data.getResource());
			reportObj.put("total", data.getTotal());
			reportObj.put("available", data.getAvailable());	
			array.add(reportObj);
		}
		obj.put("reports", array);
		return obj;
	}
	
	public static JSONObject assmebileInstanceReport(InstanceReport report) {
		if (report == null)
			return null;
		JSONObject obj = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		obj.put("startDay", sdf.format(report.getStartDay()));
		obj.put("endDay", sdf.format(report.getEndDay()));
		
		JSONArray array = new JSONArray();
		java.util.Collection<InstanceReportData> details = report.getReports();;
		for (InstanceReportData data: details){
			JSONObject reportObj= new JSONObject();
			reportObj.put("zone", data.getPartition());
			reportObj.put("account", data.getAccount());
			reportObj.put("user", data.getUser());
			reportObj.put("instanceId", data.getInstanceId());
			reportObj.put("days", data.getDays());
			reportObj.put("cpuUsage", data.getCpuUsage());
			reportObj.put("totalNetIn", data.getNetTotalIn());
			reportObj.put("totalNetOut", data.getNetTotalOut());
			reportObj.put("externalNetIn", data.getNetExternalIn());
			reportObj.put("externalNetOut", data.getNetExternalOut());
			reportObj.put("diskRead", data.getDiskRead());
			reportObj.put("diskWrite", data.getDiskWrite());
			reportObj.put("diskIOPSRead", data.getDiskIOPSRead());
			reportObj.put("diskIOPSWrite", data.getDiskIOPSWrite());
			reportObj.put("diskTimeRead", data.getDiskTimeRead());
			reportObj.put("diskTimeWrite", data.getDiskTimeWrite());
			array.add(reportObj);
		}
		obj.put("reports", array);
		return obj;
	}
    
	public static JSONObject assmebileS3Report(S3Report report) {
		if (report == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		JSONObject obj = new JSONObject();
		obj.put("startDay", sdf.format(report.getStartDay()));
		obj.put("endDay", sdf.format(report.getEndDay()));
		
		JSONArray array = new JSONArray();
		java.util.Collection<S3ReportData> details = report.getReports();;
		for (S3ReportData data: details){
			JSONObject reportObj= new JSONObject();
			reportObj.put("account", data.getAccount());
			reportObj.put("user", data.getUser());
			reportObj.put("bucket", data.getBucket());
			reportObj.put("ojects", data.getObjects());
			reportObj.put("totalSize", data.getTotalSize());
			reportObj.put("GBDays", data.getGBDays());	
			array.add(reportObj);
		}
		obj.put("reports", array);
		return obj;
	}
	
	public static JSONObject assmebileIpReport(IpReport report) {
		if (report == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		JSONObject obj = new JSONObject();
		obj.put("startDay", sdf.format(report.getStartDay()));
		obj.put("endDay", sdf.format(report.getEndDay()));
		
		JSONArray array = new JSONArray();
		java.util.Collection<IpReportData> details = report.getReports();;
		for (IpReportData data: details){
			JSONObject reportObj= new JSONObject();
			reportObj.put("account", data.getAccount());
			reportObj.put("user", data.getUser());
			reportObj.put("ip", data.getIp());
			reportObj.put("instanceId", data.getInstanceId());
			reportObj.put("days", data.getDays());	
			array.add(reportObj);
		}
		obj.put("reports", array);
		return obj;
	}
	
	public static JSONObject assmebileVolumeReport(VolumeReport report) {
		if (report == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		JSONObject obj = new JSONObject();
		obj.put("startDay", sdf.format(report.getStartDay()));
		obj.put("endDay", sdf.format(report.getEndDay()));
		
		JSONArray array = new JSONArray();
		java.util.Collection<VolumeReportData> details = report.getReports();;
		for (VolumeReportData data: details){
			JSONObject reportObj= new JSONObject();
			reportObj.put("account", data.getAccount());
			reportObj.put("user", data.getUser());
			reportObj.put("volume", data.getVolume());
			reportObj.put("instanceId", data.getInstanceId());
			reportObj.put("totalSize", data.getTotalSize());
			reportObj.put("GBDays", data.getGBDays());	
			array.add(reportObj);
		}
		obj.put("reports", array);
		return obj;
	}
	
	public static JSONArray assembleComponentsStatus(ComponentsStatus result) {

		JSONArray array = new JSONArray();
		java.util.Collection<ComponentInfo> results = result.getComponents();
		if (results ==null )
			return null;
		for (ComponentInfo comp: results){
			JSONObject obj= new JSONObject();
			obj.put("partition", comp.getPartition());
			obj.put("type", comp.getType());
			obj.put("name", comp.getName());
			obj.put("state", comp.getState());
			obj.put("event", comp.getDetail());
			array.add(obj);
		}
		return array;	
	}
	
	public static JSONObject assembleDashBoardData(DashBoardData result) {

		if (result == null)
			return null;
		JSONObject obj = new JSONObject();

		obj.put("accounts", result.getAccounts());
		obj.put("users", result.getUsers());
		obj.put("groups", result.getGroups());
		obj.put("totalDisk", result.getTotalDisk());
		obj.put("totalCpu", result.getTotalCpu());
		obj.put("totalMem", result.getTotalMem());
		obj.put("totalIp", result.getTotalIp());
		
		obj.put("availableDisk", result.getAvailableDisk());
		obj.put("availableCpu", result.getAvailableCpu());
		obj.put("availableMem", result.getAvailableMem());
		obj.put("availableIp", result.getAvailableIp());
		
		obj.put("runInstances", result.getRunInstances());
		obj.put("stoppedInstances", result.getStoppedInstances());
		obj.put("penddingInstances", result.getPenddingInstances());
		
		obj.put("createdVolume", result.getCreatedVolumes());
		obj.put("attachedVolume", result.getAttachedVolumes());
		
		return obj;
	}
}
