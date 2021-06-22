package com.nokia.atf.tef.smp.uiflow.scenarios;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.CharStreams;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.nokia.atf.tef.common.utilities.Stepdef_CommonSteps;

public class FTPExample {
	private static final Logger logger = LoggerFactory.getLogger(FTPExample.class);
	private String host;
	private Integer port;
	private String user;
	private String password;
	private BufferedWriter p_stdin;
	private JSch jsch;
	private static Session session;
	private static Channel channel;
	//private static ChannelExec channel;
	private ChannelSftp sftpChannel = null;


	public FTPExample(String host, Integer port, String user, String password) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
	}

	public FTPExample() {
		// TODO Auto-generated constructor stub
	}
	

	public void connect(int tunnelLocalPort, String tunnelRemoteHost,int tunnelRemotePort) throws Exception {
		
		System.out.println("connecting..."+host);
		try {
			
			  
		
			jsch = new JSch();
			session = jsch.getSession(user, host,port);
			session.setPassword(password);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setConfig("Compression","yes");
			session.setConfig("ConnectionAttempts","2");
			
			  session.setConfig("PreferredAuthentications","publickey,keyboard-interactive,password");
			  session.setConfig("AUTHENTICATION","AUTHENTICATION_STYLE");
		    session.setPortForwardingL(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
			session.connect();
			System.out.println("Connected ...");
	   
			 channel = session.openChannel("shell");
			

			 
		} catch (JSchException e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	public static void printInputStream(String command) throws Exception {
		
		   
			OutputStream ops = channel.getOutputStream();
			PrintStream ps = new PrintStream(ops, true);
			channel.connect();
			InputStream input = channel.getInputStream();
			ps.println("ssh xdo31");
			ps.println("cd /opt/CPPSimulator/lwm2mCppClient/bin");
			ps.println(command);
			ps.close();

			    try {
			       printResult(input, channel);
			    	//reader(b,channel);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
	}
	
	
	
	
	  private static void printResult(InputStream input, Channel channel) throws Exception {
		    int SIZE = 1024;
		    byte[] tmp = new byte[SIZE];
		    int count=0;
		    outer: 
		    while (true) {
		        while (input.available() > 0 &&(count==0)) {
		            int i = input.read(tmp, 0, SIZE);
		            if (i < 0)
		                break;
		            System.out.print(new String(tmp, 0, i));
		           if(new String(tmp, 0, i).contains("New state is REGISTERED")) {
		        	   System.out.print("Entered here");
		        	   count++;
		        	   break outer;
		           }
		        }
		        if (channel.isClosed()) {
		            System.out.println("exit-status: " + channel.getExitStatus());
		            break;
		        }
		        try {
		            Thread.sleep(300);
		        } catch (Exception ee) {
		        }
		    }
		}
	 public void executeCommand(String command) {
	        try {
	            // single execution
	            p_stdin.write(command);
	            p_stdin.newLine();
	            p_stdin.flush();
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	    }
	 
	 
	
	public void disconnect() {
		System.out.println("disconnecting...");
		//sftpChannel.disconnect();
		if(!(channel==null && session==null)) {
		channel.disconnect();
		session.disconnect();
		}
		System.out.println("Disconnected Successfully");
	}
	

	
	
 	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BufferedWriter getP_stdin() {
		return p_stdin;
	}

	public void setP_stdin(BufferedWriter p_stdin) {
		this.p_stdin = p_stdin;
	}

	public JSch getJsch() {
		return jsch;
	}

	public void setJsch(JSch jsch) {
		this.jsch = jsch;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(ChannelExec channel) {
		this.channel =  channel;
	}

	public ChannelSftp getSftpChannel() {
		return sftpChannel;
	}

	public void setSftpChannel(ChannelSftp sftpChannel) {
		this.sftpChannel = sftpChannel;
	}

	public static void main(String[] args) throws Exception {
		
	
	  	FTPExample ftp = new FTPExample("teflon.staging.ops.software.nokia.com", 22, "kalaiman", "InfyJaya@290593");
 
//	  	int tunnelLocalPort=5656;
//	      String tunnelRemoteHost="ivzw85.iot.motive.com";
//		  int tunnelRemotePort=80;
//	    ftp.connect(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
//	    String command ="./run.sh -u coap://xdompct.xdev.motive.com:5683  -n 1 -d urn:imei-msisdn:000863043885664-3045332026 --sms 3045332026 --conn_timeout 300 -p 30 --lifetime 3600 --http 5589 --notify 10";
//	    ftp.printInputStream(command);
//      //  ftp.executeCommand("ssh  xdo31");   
//	   // FTPExample.runCommand("");
//	    ftp.disconnect();
           
	}

}