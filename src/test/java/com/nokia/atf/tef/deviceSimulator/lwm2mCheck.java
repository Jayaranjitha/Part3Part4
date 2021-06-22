package com.nokia.atf.tef.deviceSimulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nokia.atf.tef.deviceSimulator.OMADMSimulator;

public class lwm2mCheck {
	private static final Logger logger = LoggerFactory.getLogger(lwm2mCheck.class);
	public static String fpath ="";
	public static String lwm2miccid ="";
	public static String lwm2mmsisdn ="";
	public void lwm2mSimulatorRun() throws Exception {

		 lwm2miccid="000863045334486";
		 lwm2mmsisdn="3045334444";
		String filePath = "\\TestData\\LWM2M\\";
		String fileName = lwm2miccid + "-" + lwm2mmsisdn + "_bootstrap-QA.bat";

		logger.info("device id is " + lwm2miccid + "msisdn" + lwm2mmsisdn);
		createNewFile(filePath, fileName);

		executeBatchFile(filePath);
		Thread.sleep(2000);
	}	
	 public void createNewFile(String filePath,String fileName) throws Exception{
		 try {
		
			 fpath=	 System.getProperty("user.dir")+filePath+fileName;
			 System.out.println("File "+fpath);
		 File myObj = new File(fpath);
		  if (myObj.createNewFile()) {
		        System.out.println("File created: " + myObj.getName());
		        writeIntoFile();
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		  
	 }
	
		
	 public void writeIntoFile() throws Exception{
		 //String newPath= getJavaPath();
		 
		 FileWriter output = new FileWriter(fpath);
		 output.write("java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:5545 -jar lwm2mTestClient-1.0.0.2-SNAPSHOT-jar-with-dependencies.jar -d urn:imei-msisdn:"+lwm2miccid+"-"+lwm2mmsisdn+ " -u coaps://xvzwcdpvi.xdev.motive.com:5684 -o bootstrap -b UQS -psk d6160c2e7c90399ee7d207a22611e3d3a87241b0462976b935341d000a91e747 -pskEncoding true -lifetime 60 -lwm2mModel ./CDP_JSON -p 10");		 
		 output.close();
	      System.out.println("Successfully wrote to the file.");
		 
	 }
	 
	 public void executeBatchFile(String filePath) throws Exception {
			
		 String path=  System.getProperty("user.dir") + filePath;
			File dir = new File(path);
			System.out.println("Started");
		 
		//	ProcessBuilder pb = new ProcessBuilder("cmd", "/c","Start", "/min" , fpath);
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c","Start", "/B",fpath);
			pb.directory(dir);

			Process p = pb.start();
			pb.command("cmd.exe", "/c", "echo", "u");
			 BufferedReader input =
		                new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
			  BufferedReader stdError = new BufferedReader(new 
					     InputStreamReader(p.getErrorStream()));
		   
			   String s= null;
            logger.info("Commands for simulator");
            boolean errorCheck=false;
            
             while (stdError.ready()&&(s = stdError.readLine())!= null ) {
             	logger.info(s);
             	
             	Thread.sleep(1000);
             }
         
             String line="";  int count =0;
		        Thread.sleep(2000);
       	              if(!errorCheck) {    
	            		  while (input.ready()&&(line = input.readLine())!= null ) {
	            			logger.info(line);    
	                        Thread.sleep(1000);
	                      
		                  }
       	              }
	            		
		
 		           Thread.sleep(10000);         		       
		            input.close();
                
		            stdError.close();
		        
		           OMADMSimulator.taskKillByPort(5545); 
		            
		 }
	 public static void main(String[] args) throws Exception {
	
	   lwm2mCheck l = new lwm2mCheck();
	   l.lwm2mSimulatorRun();
	
	 }
}

	 