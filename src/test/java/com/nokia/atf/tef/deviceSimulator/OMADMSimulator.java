package com.nokia.atf.tef.deviceSimulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nokia.atf.tef.smp.uiflow.scenarios.Steps_19A;
import com.nokia.mtaf.lib.common.Stopwatch;
import com.nokia.mtaf.lib.common.exception.TestDataException;
import com.nokia.mtaf.lib.common.interfaces.ILoggable;
import com.nokia.mtaf.lib.utility.ProcessUtil;
import com.nokia.mtaf.lib.utility.StringUtil;
import com.nokia.mtaf.lib.utility.SystemUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class OMADMSimulator
{
	private static final Logger logger = LoggerFactory.getLogger(OMADMSimulator.class);
	
	
	
	public static void taskKillByPort(Integer port) throws InterruptedException {
		try {
			
			Long isProcessByPortAlive = getProcessIdByPort(port);
			
			Thread.sleep(5000);
			if (isProcessByPortAlive != null) {
				taskKill(isProcessByPortAlive);
				Stopwatch.sleep(1);
			}
		} catch (IOException | TestDataException | NumberFormatException var3) {
		System.out.println("Exception is" +var3);
		}

	}
	
	public static String runCmd(ArrayList<String> command) throws IOException {
		return runCmdInternal(command);
	}
	
	public static String runCmd(String commandArgument) throws IOException {
		ArrayList<String> command = new ArrayList();
		command.add("cmd");
		command.add("/C");
		command.add(commandArgument);
		return runCmdInternal(command);
	}
	

	private static String runCmdInternal(ArrayList<String> command) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(command);
		Process process = builder.start();
		return getProcessResponse(process);
	}
	
	private static String getProcessResponse(Process process) throws IOException {
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();
		
		String line;
		while((line = br.readLine()) != null) {
			System.out.println(line);
			sb.append(line + "\r\n");
		}

         br.close();
		String response = sb.toString();
		
		ILoggable.logDebug(ProcessUtil.class, response);
		return response;
	}

	private static String getProcessResponseError(Process process) throws IOException {
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder sb = new StringBuilder();

		String line;
		while ((line = br.readLine()) != null ) {
			sb.append(line + "\r\n");
		}

		String response = sb.toString();
		ILoggable.logDebug(ProcessUtil.class, response);
		return response;
	}
	
	public static String taskKill(Long processId) {
		String response = "";
		Process p;
		if (SystemUtil.isWindows()) {
			try {
				ILoggable.logInfo(ProcessUtil.class, "taskkill /f /pid  " + processId);
				p = Runtime.getRuntime().exec("taskkill /f /pid  " + processId);
				response = response + getProcessResponse(p);
				response = response + getProcessResponseError(p);
				System.out.println("response is "+response);
				return response;
			} catch (IOException var3) {
				;
			}
		} else {
			try {
				ILoggable.logInfo(ProcessUtil.class, "kill -9   " + processId);
				p = Runtime.getRuntime().exec("kill -9 " + processId);
				response = response + getProcessResponse(p);
				response = response + getProcessResponseError(p);
				return response;
			} catch (IOException var4) {
				;
			}
		}

		return null;
	}
	
	
	
	public static String taskKill(String processName) {
		String response = "";
		Process p;
		if (SystemUtil.isWindows()) {
			try {
				p = Runtime.getRuntime().exec("taskkill /f /im  " + processName);
				response = response + getProcessResponse(p);
				response = response + getProcessResponseError(p);
				return response;
			} catch (IOException var3) {
				;
			}
		} else {
			try {
				p = Runtime.getRuntime().exec("pkill " + processName.replace(".exe", ""));
				response = response + getProcessResponse(p);
				response = response + getProcessResponseError(p);
				return response;
			} catch (IOException var4) {
				;
			}
		}

		return null;
	}
	
	public static Long getProcessIdByPort(Integer port) throws IOException, NumberFormatException, TestDataException {
		if (SystemUtil.isWindows()) {
		//	String response = runCmd("netstat -a -n -o | find \"" + port + "\"");
			
			String response = runCmd("netstat -ano | findstr :"+port);
			
			logger.info("Response of command" +response);
			if (response != null) {
				String first = response.split("\r\n")[0];
				int indexOf = first.indexOf("LISTENING");
				if (indexOf != -1) {
					String result = first.substring(indexOf);
					return Long.valueOf(StringUtil.getFirstMatch(result, "\\d+"));
				}
			}
		}
		
		System.out.println("SystemUtil.isWindows()" +SystemUtil.isWindows());
		

		return null;
	}


    public static boolean runomadmSimulator(String fpath) {
    	boolean success=false;
    	  try
          {
            	 
           		  fpath=  System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\device\\Sample.bat";
            		 String path= System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\device\\";
            			File dir = new File(path);
            			System.out.println("Started");

            		ProcessBuilder pb = new ProcessBuilder("cmd", "/c","Start", "/B",fpath);

            		
            			pb.directory(dir);

            			Process p = pb.start();
           		
            			Thread.sleep(1000);
            			  BufferedReader input =
            		                new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
            			  BufferedReader stdError = new BufferedReader(new 
            					     InputStreamReader(p.getErrorStream()));
        			   
            			   String s= null;
    	                   logger.info("Commands for simulator");
    	                   boolean errorCheck=false;
    	                   
    	                    while (stdError.ready()&&(s = stdError.readLine())!= null ) {
    	                    	logger.info(s);
    	                    	if(s.contains("No transports initialized")){
    	                    		errorCheck=true;
    	                    	}
    	                    	Thread.sleep(1000);
    	                    }
    	                
    	                    String line="";  int count =0;
            		        Thread.sleep(1000);
    	              	              if(!errorCheck) {    
           		            		  while (input.ready()&&(line = input.readLine())!= null ) {
           		            			logger.info(line);    
          		                        Thread.sleep(2000);
          		                       if(line.contains("the device had handled")) {
          		                    	   
          		                    	   success=true;
          		                       }
            		                  }
    	              	              }
           		            		
               		
       		 		           Thread.sleep(4000);         		       
            		            input.close();
                               
            		            stdError.close();
            		          //  taskKillByPort(5545); 
  
            		            System.out.println("The simulator has started successfully is" +success);
            }
            catch (Exception e)
            {
                System.out.println("Exception occured in OMADM simulator");
                e.printStackTrace();
            }
    	  
    	  return success;
    }
    public static void main(String[] args) throws Exception
    {
    	OMADMSimulator n = new OMADMSimulator();
 String fpath=  System.getProperty("user.dir") + "\\TestData\\demo-code-17.10.6.2-20180529121348\\device\\Sample.bat";
//
    boolean status = n.runomadmSimulator(fpath);
//		 System.out.println("Simulator has started success is true/false: " +status);
		 
            OMADMSimulator.taskKillByPort(5545);
    }
}