package com.eucalyptus.admin.util;

import java.util.*;
import java.io.*;

public class RemoteEucalyptusConfiguration {

	private EucalyptusConfiguration configuration;

	public String getValue(String key) {
		if (null != configuration)
			return configuration.getValue(key);
		return null;
	}

	public void setValue(String key, String value, String comments) {
		if (null != configuration)
			configuration.setValue(key, value, comments);
	}

	public void remove(String key) {

		if (null != configuration)
			configuration.remove(key);
	}

	public void load(String server, String user, String password)
			throws IOException {
		try {
			Ssh2Client sshClient = new Ssh2Client(server, user, password);
			boolean ret = sshClient.connect();
			if (!ret)
				throw new IOException("can't connect to  server:" + server);
			String lockTempDir = "/tmp/" + Tools.getRandomString(15);
			File dir = new File(lockTempDir);
			if (dir != null)
				dir.mkdir();
			String localFile = sshClient.downLoadFile(
					"/etc/eucalyptus/eucalyptus.conf", lockTempDir);
			if (localFile == null) {
				dir.delete();
				sshClient.close();
				throw new IOException(
						"can't download eucalyptus configuration file");
			}
			this.configuration = new EucalyptusConfiguration(localFile);
			sshClient.close();
			File file = new File(localFile);
			if (file != null)
				file.delete();
			dir.delete();
		} catch (IOException e) {
			throw e;
		}
	}

	//@SuppressWarnings("unused")
	public void save(String server, String user, String password) throws IOException{
		try {
			
			if (configuration == null)
				return;

			String lockTempDir = "/tmp/" + Tools.getRandomString(15);
			File dir = new File(lockTempDir);
			if (dir == null)
				throw new IOException("can't create temporary directory " + lockTempDir);
			
			dir.mkdir();
			String localFile = lockTempDir + "/eucalyptus.conf";
			this.configuration.save(localFile);
			Ssh2Client sshClient = new Ssh2Client(server, user, password);
			boolean ret = sshClient.connect();
			if (!ret) {
				Tools.deleteDir(dir);
				sshClient.close();
				throw new IOException("can't connect to  server:" + server);
			}
			String remoteFile = sshClient.upLoadFile(localFile,"/etc/eucalyptus/");
			sshClient.close();
			/*
			File file = new File(localFile);
			if (file != null)
				file.delete(); */
			Tools.deleteDir(dir);
			
			if (remoteFile == null) {
				throw new IOException("can't upload eucalyptus configuration file to server " + server);
			} 
		} catch (IOException e) {
			throw e;
		}

	}
}
