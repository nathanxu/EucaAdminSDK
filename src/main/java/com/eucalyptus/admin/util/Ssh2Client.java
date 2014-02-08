package com.eucalyptus.admin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import ch.ethz.ssh2.SCPClient;

import java.util.Collection;

public class Ssh2Client {

	private String hostname = "127.0.0.1";
	private String username = "";
	private String password = "";
	private Connection conn;
	private String commandOutput;

	public Ssh2Client(String hostname, String username, String password) {
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.conn = null;
	}

	public void close() throws IOException {

		if (conn != null) {
			conn.close();
		}
		conn = null;
	}
	
	public boolean connect() throws IOException {
		try {
			if (conn != null) {
				conn.close();
			}
			conn = new Connection(hostname);
			conn.connect();
		} catch (IOException e) {
			throw e;
		}
		try {
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);

			if (isAuthenticated == false) {
				conn.close();
				throw new IOException("Authentication failed.");
			}
		} catch (IOException e) {
			conn.close();
			throw new IOException(e);
		}
		return true;
	}

	public String testConnectivity() throws IOException {
		String unameoutput = "";

		Connection conn = new Connection(hostname);
		try {
			conn.connect();
		} catch (IOException e) {
			throw new IOException(e);
		}

		Session sess = null;
		try {
			boolean isAuthenticated = conn.authenticateWithPassword(username,
					password);

			if (isAuthenticated == false) {
				conn.close();
				throw new IOException("Authentication failed.");
			}

			/* Create a session */
			sess = conn.openSession();
		} catch (IOException e) {
			conn.close();
			throw new IOException(e);
		}

		try {
			sess.execCommand("uname -a");
			InputStream stdout = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(
					new InputStreamReader(stdout));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				unameoutput += line;
			}
			br.close();
		} catch (IOException e) {
			sess.close();
			conn.close();
			throw new IOException(e);
		}
		sess.close();
		conn.close();
		return unameoutput;

	}

	public int execCommand(String command) throws IOException {
		this.commandOutput ="";
		String output = "";
		if (conn == null) {
			if (!connect()) {
				throw new IOException("can't connect to server");
			}
		}

		Session sess = conn.openSession();
		sess.execCommand(command);
		InputStream stdout = new StreamGobbler(sess.getStdout());
		BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
		while (true) {
			String line = br.readLine();
			if (line == null)
				break;
			output += line;
		}
		br.close();
		int existCode = sess.getExitStatus();
		sess.close();
		this.commandOutput = output;
		return existCode;
	}
	
	public String getLastComandOutput(){
		return this.commandOutput;
	}

	public String downLoadFile(String remoteFile, String localDirectory)
			throws IOException {

		if (conn == null) {
			if (!connect()) {
				throw new IOException("can't connect to server");
			}
		}
		SCPClient scp = conn.createSCPClient();
		scp.get(remoteFile, localDirectory);
		String[] names = remoteFile.split("/");
		String localFileName;
		if (names!= null && names.length > 0)
			localFileName = localDirectory + "/" + names[names.length - 1];
		else
			localFileName = localDirectory + "/" + remoteFile;
		//System.out.println(localFileName);
		File localFile = new File(localFileName);
		if (localFile !=null && localFile.exists())
			return localFileName;
		else
			return null;

	}
	
	public Collection<String> downLoadFiles(String[] remoteFiles, String localDirectory)
			throws IOException {

		Collection<String> localFiles = new java.util.ArrayList<String>();
		if (conn == null) {
			if (!connect()) {
				throw new IOException("can't connect to server");
			}
		}
		SCPClient scp = conn.createSCPClient();
		scp.get(remoteFiles, localDirectory);
		for (String remoteFile : remoteFiles) {
			String[] names = remoteFile.split("/");
			String localFileName;
			if (names!=null && names.length > 0)
				localFileName = localDirectory + "/" + names[names.length - 1];
			else
				localFileName = localDirectory + "/" + remoteFile;
			File localFile = new File(localFileName);
			if (localFile.exists())
				localFiles.add(localFileName);
	
		}
		return localFiles;

	}

	public String upLoadFile(String localFile, String remoteDirectory)
			throws IOException {

		if (conn == null) {
			if (!connect()) {
				throw new IOException("can't connect to server");
			}
		}
		String[] names = localFile.split("/");
		String remoteFileName;
		if (names!=null && names.length > 0)
			remoteFileName = remoteDirectory + "/" + names[names.length - 1];
		else
			remoteFileName = remoteDirectory + "/" + localFile;
		//System.out.println(remoteFileName);
		String command = "ls " + remoteFileName;
		SCPClient scp = conn.createSCPClient();
		scp.put(localFile, remoteDirectory);
		int ret = execCommand(command);
		if (ret != 0)
			return null;
		return remoteFileName;
	}
	
	public void upLoadFiles(String[] localFiles, String remoteDirectory)
			throws IOException {

		if (conn == null) {
			if (!connect()) {
				throw new IOException("can't connect to server");
			}
		}
		SCPClient scp = conn.createSCPClient();
		scp.put(localFiles, remoteDirectory);
	}
	
}