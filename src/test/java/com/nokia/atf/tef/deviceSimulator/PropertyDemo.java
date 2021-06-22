package com.nokia.atf.tef.deviceSimulator;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Properties;
import com.nokia.atf.tef.smp.uiflow.scenarios.FTPExample;

public class PropertyDemo {

	public static void createPropertyFile (String deviceId ) throws Exception {
		 String fpath=  System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\device\\profiles\\0.properties";

		 FileChannel.open(Paths.get(fpath), StandardOpenOption.WRITE).truncate(0).close();

		
		 FileOutputStream outputStrem = new FileOutputStream(fpath);
		 DefaultSimulatorProperties d = new DefaultSimulatorProperties(deviceId);
		 Properties prop = new Properties();
        prop.put("FW_V",d.FIRMWARE_VERSION);
        prop.put("imei",d.DEVICE_ID);
        prop.put("LRG_OBJ",d.LRG_OBJ);
        prop.put("URI_MAX_SEG_LEN",d.URI_MAX_SEG_LEN);
        prop.put("DEV_TYP",d.DEV_TYP);
        prop.put("client_nonce",d.CLIENT_NONCE);
        prop.put("model",d.ANNOUNCED_MODEL);
        prop.put("client_password",d.CLIENT_PASSWORD);
        prop.put("HW_V",d.HOSTDEVICE_HARDWARE_VERSION);
        prop.put("URI_MAX_TOT_LEN",d.URI_MAX_TOT_LEN);
        prop.put("URI_MAX_DEPTH",d.URI_MAX_DEPTH);
        prop.put("server_nonce",d.SERVER_NONCE);
        prop.put("OEM",d.OEM);
        prop.put("server_password",d.SERVER_PASSWORD);
        prop.put("language",d.language);
        prop.put("server_username",d.server_username );
        prop.put("client_username",d.client_username);
        prop.put("manufacturer",d.ANNOUNCED_MANUFACTURER);
        prop.put("SW_V",d.HOSTDEVICE_SOFTWARE_VERSION);
        prop.put("dm_version",d.dm_version);
        prop.put("ICCID",d.ICCID);
      prop.store(outputStrem,"OMADM Properties file");
        
  
        // checking what's in table now
        System.out.println("New Properties: "
                           + prop.toString());
	}
	
	
	public  static String createBatFile(String port,int tunnelPort) throws Exception {
	
	String classPath= System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\";
     String command = "java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:"+port+ " -cp " +classPath+"device\\profiles\\.;"+classPath+"device\\dmtree\\.;"+classPath+"device\\lib\\*;  com.mdm.wds.demo.InteractiveSoftwareDevice http://localhost:"+tunnelPort+"/southbound-connector/dm -update-trigger";
	 String simpath=  System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\device\\Sample.bat";
	 System.out.println("Command is" +command);
	 FileChannel.open(Paths.get(simpath), StandardOpenOption.WRITE).truncate(0).close();
	 FileWriter output = new FileWriter(simpath);
	 output.write(command);		 
	 output.close();
     return simpath;
	}

	
	public  static String createBatFileProd(String port) throws Exception {
		
		 String classPath= System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\";
	     String command = "java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:"+port+ " -cp " +classPath+"device\\profiles\\.;"+classPath+"device\\dmtree\\.;"+classPath+"device\\lib\\*;  com.mdm.wds.demo.InteractiveSoftwareDevice https://ivzwmdmv.iot.motive.com/southbound-connector/dm -update-trigger";
		 String simpath=  System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\device\\Sample.bat";
		 System.out.println("Command is" +command);
		 FileChannel.open(Paths.get(simpath), StandardOpenOption.WRITE).truncate(0).close();
		 FileWriter output = new FileWriter(simpath);
		 output.write(command);		 
		 output.close();
	     return simpath;
		}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		PropertyDemo p = new PropertyDemo();
		PropertyDemo.createPropertyFile("990000750004333");
        p.createBatFile("5546",5656);
        FTPExample ftp = new FTPExample("teflon.staging.ops.software.nokia.com", 22, "kalaiman", "InfyJaya@290593");
        
	  	int tunnelLocalPort=5656;
	      String tunnelRemoteHost="ivzw85.iot.motive.com";
		  int tunnelRemotePort=80;
	      ftp.connect(tunnelLocalPort,tunnelRemoteHost,tunnelRemotePort);
          
          
          OMADMSimulator n = new OMADMSimulator();
          n.runomadmSimulator("");
        
          OMADMSimulator.taskKillByPort(5546);
           ftp.disconnect();
	}

}
