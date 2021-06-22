
package com.nokia.atf.tef.common.utilities;

import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.vfs2.FileName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.jcraft.jsch.Channel;
import com.nokia.atf.tef.deviceSimulator.OMADMSimulator;
import com.nokia.atf.tef.smp.uiflow.scenarios.FTPExample;


//import org.testng.asserts.SoftAssert;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
//Import added by Jaya
import io.restassured.response.Response;
import junit.framework.Assert;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

@Component
@ContextConfiguration(locations = { "classpath:smp-config/smp-context.xml" })
public class Stepdef_CommonSteps extends WEB_Methods {
	private static final Logger logger = LoggerFactory.getLogger(Stepdef_CommonSteps.class);

	@Value("${smp.nbi.uiworkflow.url}")
	private String smpURL;

	@Value("${smp.nbi.uiworkflow.environment}")
	private String environment;
	
	@Value("${smp.nbi.uiworkflow.username}")
	private String smpUsername;

	@Value("${smp.nbi.uiworkflow.password}")
	private String smpPassword;

	@Value("${smp.nbi.uiworkflow.browser}")
	private String browserName;

	@Value("${smp.nbi.uiworkflow.language}")
	private String language;

	@Value("${smp.nbi.uiworkflow.dburl}")
	private String DB_URL;

	@Value("${smp.nbi.uiworkflow.dbusername}")
	private String USER;

	@Value("${smp.nbi.uiworkflow.dbpassword}")
	private String PASS;
	
	@Value("${smp.nbi.uiworkflow.realDeviceApplUSR}")
	private String realDeviceApplUSR;
	
	@Value("${smp.nbi.uiworkflow.realDeviceApplPass}")
	private String realDeviceApplPass;
	
	private Scenario scenario;
	private static Connection conn = null;
	private static Statement stmt = null;
	public static String lwm2miccid="";
	public static String friendlyName="";
	public static String folderFriendlyName="";
	public static String lwm2mmsisdn="";
	public static String deviceIdImpact="";
	public static String parentGUID ;
	public static String parentWindow ;
	public static String fpath ="";

	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		// startRecording();
		WEB_Methods.driver = WEB_Methods.getDriver();
		WEB_Methods.strScenarioName = scenario.getName();
		WEB_Methods.strFeatureName = scenario.getId().split(";")[0].replace("-", " ");
		// strFeatureName=strFeatureName.split(".")[0];
		String name = new File(strFeatureName).getName();
		System.out.println(strFeatureName = name.split(".feature")[0]);
		logger.info(WEB_Methods.strFeatureName + " - Feature Started");
		logger.info(WEB_Methods.strScenarioName + " - Scenario Started");
		try {
			WEB_Methods.ScreenShotPath();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void afterScenario(Scenario scenario) throws Exception {
		// stopRecording();
		try {
			File file;
			WEB_Methods.strScenarioName = scenario.getName();
			WEB_Methods.strFeatureName = scenario.getId().split(";")[0].replace("-", " ");
			boolean scenarioValue = scenario.isFailed();
			System.out.println("Scenario is :" + scenarioValue);
//			if (scenarioValue == true) {
//				WEB_Methods.Quit();
//				file = new File(SpecializedScreenRecorder.fileNameVideo);
//
//				System.out.println("Run acceptanceTests " + file);
//				//file.delete();
//				System.out.println("File is deleted Successfully ");
//				stopRecording();
//
//			} else {
//				WEB_Methods.Quit();
//				file = new File(SpecializedScreenRecorder.fileNameVideo);
//				System.out.println("Source path " +file);
//
//				File copied = new File(System.getProperty("user.dir") + File.separator + "FailedFiles");
//				copied.mkdir();
//				System.out.println("Destination path " +copied);
//
//				FileUtils.copyFileToDirectory(file,copied);
//				//FileUtils.copyFile(file, copied);
//
//
//			}
			logger.info(WEB_Methods.strFeatureName + " - Feature Completed");
			logger.info(WEB_Methods.strScenarioName + " - Scenario Completed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//MB
	@Step
	public void user_already_on_login_page() throws Exception {
		Serenity.takeScreenshot();
		driver = AWSinitialization(driver, smpURL, browserName, language);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		Thread.sleep(2000);
	}

	
	@Step
	public void userloginImpact(String impactURL) throws Exception {
		Serenity.takeScreenshot();
		System.out.println("Impact url" +impactURL);
		driver = AWSinitialization(driver, impactURL, browserName, language);
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
	}

	
	
	public void getSessionObject() throws Exception{
		
	//	HttpSession session = request.getSession(true);
		
	}
	
	@Step
	public void title_of_login_page_is_CSC() throws Exception {

		String title = driver.getTitle();
		if (title.equalsIgnoreCase(WEB_Methods.WEB_getPropertyValue("title")))
			logger.info("Motive bridge Portal is launched successfully");
		else
			logger.info("Motive bridge Portal is not launched successfully");
	}

	//MB
	@Step
	public void user_enters_username_and_password() throws Exception {

		WEB_SendKeys(WEB_Methods.WEB_findElement("ID", "UserName_textbox"), smpUsername);
		WEB_SendKeys(WEB_Methods.WEB_findElement("ID", "Password_textbox"), smpPassword);
	}
//	@Step
//	public void user_enters_usernamePasswordForRealDevice(String username, String password) throws Exception {
//
//		WEB_SendKeys(WEB_Methods.WEB_findElement("ID", "UserName_textbox"), username);
//		WEB_SendKeys(WEB_Methods.WEB_findElement("ID", "Password_textbox"), password);
//		
//		
//	}
	
	@Step
	public void user_enters_usernamePasswordForRealDevice() throws Exception {

		WEB_SendKeys(WEB_Methods.WEB_findElement("ID", "UserName_textbox"), realDeviceApplUSR);
		WEB_SendKeys(WEB_Methods.WEB_findElement("ID", "Password_textbox"), realDeviceApplPass);
		
		
	}
	
	
	
	

	@Step
	public void user_enters_usernamePassword(String username, String password) throws Exception {
       
//		WEB_SendKeys(WEB_Methods.WEB_findElement("STRING", "//input[@name='j_username']"), username);
//		WEB_SendKeys(WEB_Methods.WEB_findElement("STRING", "//input[@name='j_password']"), password);
 	Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@name='j_username']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();

	}

	//MB
	@Step
	public void user_clicks_on_login_button() throws Exception {
		Report_getscreenShot("Login page");
		WEB_click(WEB_Methods.WEB_findElement("ID", "logon_button"));

		logger.info("Agent Login as ---- " + smpUsername);
		Thread.sleep(20000);
	   
		WEB_click(WEB_Methods.WEB_findElement("XPATH", "close_buttonOnLoad"));
		
		Serenity.takeScreenshot();
	}
	//MB
	@Step
	public void simulator_OMADM() throws Exception {
		// String value = "Search Execution";
		Thread.sleep(20000);
		fPath ="C:\\Users\\KALAIMAN\\Documents\\remote.exe";
		System.out.print("Path" + fPath);
		Process proc = Runtime.getRuntime().exec(fPath);
		Thread.sleep(2000);
		// proc = Runtime.getRuntime().exec(fPath).destroyForcibly();
	}
	

	
	//MB
	@Step

	public void simulatorLwM2M() throws Exception{
		
		Thread.sleep(20000);
		String result="";
		String line;
	    fPath = System.getProperty("user.dir") + "\\TestData\\lwm2mAutoITRecent.exe";
	    logger.info("Path" + fPath );
		Process proc = Runtime.getRuntime().exec(fPath);
      
		Thread.sleep(2000);
		proc.waitFor();
		
		BufferedReader bri = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		while ((line = bri.readLine()) != null) {
		    result+=line;
		}
		String[] data=result.split("-");
		lwm2miccid = data[0];
		lwm2mmsisdn= data[1];
	
	    System.out.println("Value "+result + "device id is " +lwm2miccid+ "msisdn is " +lwm2mmsisdn);
	    logger.info("Value "+result);
		proc = Runtime.getRuntime().exec(fPath).destroyForcibly();
	}
	//MB
	@Test
	  public void generateICCIDMSISDN(String value) throws Exception{
		  
		     SecureRandom random = new SecureRandom();
		  //  lwm2miccid="00086304"+ random.nextInt(10000000);
		  //  lwm2mmsisdn = "30453" + random.nextInt(100000);
		     if(value.equalsIgnoreCase("LWM2M")) {
		    lwm2miccid=  "00086304" + RandomStringUtils.randomNumeric(7).toUpperCase();
		    lwm2mmsisdn="30453" +RandomStringUtils.randomNumeric(5).toUpperCase();
		    logger.info("iccid" +lwm2miccid+ "msisdn" +lwm2mmsisdn);
		     }
		     
		     if(value.equalsIgnoreCase("OMADM")) {
		    	  lwm2miccid=  "91888999" + RandomStringUtils.randomNumeric(7).toUpperCase();
				    lwm2mmsisdn="75000" +RandomStringUtils.randomNumeric(5).toUpperCase();
				    logger.info("iccid" +lwm2miccid+ "msisdn" +lwm2mmsisdn);
		     }
		   
	  }
	//MB
	@SuppressWarnings("deprecation")
	@Step
	public void user_clicks_topmenu(String value) throws Exception {
	       
		WEB_click(WEB_Methods.WEB_findElement("STRING", "//a[contains(.,'"+value+"')]"));
	
	}
	
    //MB
	@Step
	public void user_scroll_left(String element) throws Exception {
		
		WEB_Methods.scrollLeft(element);
		Thread.sleep(2000);
	}
	
	 
	//MB
	@Step
	public void userSelectProtocol(String value) throws Exception {

	       parentGUID = driver.getWindowHandle();
			WEB_click(WEB_Methods.WEB_findElement("STRING", "(//a[contains(.,'"+value+"')])[2]"));
			logger.info("d"+driver.getTitle() + driver.getCurrentUrl()+driver.getWindowHandle());
			
	}
	  
	//MB
	
	  public void simulatorLwM2MinputCheck1() throws Exception{
			
		   Thread.sleep(20000);
		
			String fPath = System.getProperty("user.dir") + "\\TestData\\lwm2mAutoITCd.exe";
			System.out.println("Path" + fPath + " " + lwm2miccid + " " +lwm2mmsisdn );
			String command=""+fPath+" "+lwm2miccid+" "+lwm2mmsisdn+"";  
			
			Process proc =Runtime.getRuntime().exec(command);
			proc.waitFor();
			   System.out.println( "device id is " +lwm2miccid+ "msisdn is " +lwm2mmsisdn);
			    logger.info("device id is "+lwm2miccid+ "msisdn" +lwm2mmsisdn);
			    
		}
	  
//	public void lwm2mSimulatorRun(String port) throws Exception {
//
//			lwm2miccid= "000863040384457";
//			lwm2mmsisdn="3045349201";
//			String filePath = "\\TestData\\LWM2M\\";
//			String fileName = lwm2miccid + "-" + lwm2mmsisdn + "_bootstrap-QA.bat";
//
//			logger.info("device id is " + lwm2miccid + "msisdn" + lwm2mmsisdn);
//			createNewFile(filePath, fileName,port);
//
//			executeBatchFile(filePath);
//			Thread.sleep(2000);
//		}
	
	public void lwm2mSimulatorRun() throws Exception {

		closeSimulator("5549");
//		lwm2miccid= "000863047618011";
//	    lwm2mmsisdn="3045335628";
		String filePath = "\\TestData\\LWM2M\\";
		String fileName = lwm2miccid + "-" + lwm2mmsisdn + "_bootstrap-QA.bat";

		logger.info("device id is " + lwm2miccid + "msisdn" + lwm2mmsisdn);
		createNewFile(filePath, fileName);

		executeBatchFile(filePath);
		Thread.sleep(2000);
	}
	
	
		
		public void closeSimulator(String port) throws Exception {
   		
			if(port.contains("5546")) {
				int portint= Integer.parseInt(port);
				OMADMSimulator.taskKillByPort(portint);
				 FTPExample f = new FTPExample();
				 if(environment.equalsIgnoreCase("QA")) {
					 f.disconnect();
				 }
				 
			}
			else {
				
				  int portint= Integer.parseInt(port);
					OMADMSimulator.taskKillByPort(portint);
			}
		}
	   
	   
		public String getJavaPath() throws Exception {

			String oldPAth = System.getenv("JAVA_HOME");

			String newPath = "";
			if (oldPAth.contains("Program Files")) {

				String[] s = oldPAth.split("Program Files");

				System.out.println(s[0]);
				System.out.println(s[1]);

				newPath = s[0] + "PROGRA~1" + s[1];
			}

			System.out.println("new path" + newPath);

			return newPath;
		}
//	 public void createNewFile(String filePath,String fileName,String port) throws Exception{
//		 try {
//		
//			 fpath=	 System.getProperty("user.dir")+filePath+fileName;
//			 System.out.println("File "+fpath);
//		 File myObj = new File(fpath);
//		  if (myObj.createNewFile()) {
//		        System.out.println("File created: " + myObj.getName());
//		        writeIntoFile(port);
//		      } else {
//		        System.out.println("File already exists.");
//		      }
//		    } catch (IOException e) {
//		      System.out.println("An error occurred.");
//		      e.printStackTrace();
//		    }
//		  
//	 }
//	 
	 public void createNewFile(String filePath,String fileName) throws Exception{
		 try {
		
			 fpath=	 System.getProperty("user.dir")+filePath+fileName;
			 System.out.println("File "+fpath);
		 File myObj = new File(fpath);
		 if(myObj.exists()) {
			 System.out.println("File already exists in the path");
			 myObj.delete();
		 }
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
	 
	
//	 public void writeIntoFile(String port) throws Exception{
//		 String newPath= getJavaPath();
//		 
//		 FileWriter output = new FileWriter(fpath);
//		// output.write(newPath+"\\bin\\java -jar lwm2mTestClient-1.0.0.2-SNAPSHOT-jar-with-dependencies.jar -d urn:imei-msisdn:"+lwm2miccid+"-"+lwm2mmsisdn+ " -u coaps://xvzwcdpvi.xdev.motive.com:5684 -o bootstrap -b UQS -psk d6160c2e7c90399ee7d207a22611e3d3a87241b0462976b935341d000a91e747 -pskEncoding true -lifetime 60 -lwm2mModel ./CDP_JSON -p 10");		 
//		 
//		 output.write("java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:"+port+ "-jar lwm2mTestClient-1.0.0.2-SNAPSHOT-jar-with-dependencies.jar -d urn:imei-msisdn:"+lwm2miccid+"-"+lwm2mmsisdn+ " -u coaps://xvzwcdpvi.xdev.motive.com:5684 -o bootstrap -b UQS -psk d6160c2e7c90399ee7d207a22611e3d3a87241b0462976b935341d000a91e747 -pskEncoding true -lifetime 60 -lwm2mModel ./CDP_JSON -p 10");		 
//		 output.close();
//	      System.out.println("Successfully wrote to the file.");
//		 
//	 }
	 
	 public void writeIntoFile() throws Exception{
		 String newPath= getJavaPath();
		 
		 FileWriter output = new FileWriter(fpath);
		 logger.info("Environment to run Lwm2m Simulator is" +environment);
		 if(environment.equalsIgnoreCase("QA")) {
			 output.write("java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:5545 -jar lwm2mTestClient-1.0.0.2-SNAPSHOT-jar-with-dependencies.jar -d urn:imei-msisdn:"+lwm2miccid+"-"+lwm2mmsisdn+ " -u coaps://xvzwcdpvi.xdev.motive.com:5684 -o bootstrap -b UQS -psk d6160c2e7c90399ee7d207a22611e3d3a87241b0462976b935341d000a91e747 -pskEncoding true -lifetime 60 -lwm2mModel ./CDP_JSON -p 10");		  
		 }
		 if(environment.equalsIgnoreCase("PROD")) {
			 output.write("java -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=127.0.0.1:5549 -jar lwm2mTestClient-1.0.0.2-SNAPSHOT-jar-with-dependencies.jar -d urn:imei-msisdn:"+lwm2miccid+"-"+lwm2mmsisdn+ " -u coaps://ddocdpboot.do.motive.com:5684 -o bootstrap -b UQS -psk d6160c2e7c90399ee7d207a22611e3d3a87241b0462976b935341d000a91e747 -pskEncoding true -lifetime 300 -lwm2mModel ./CDP_JSON -p 20");		  

		 }
		 output.close();
	      System.out.println("Successfully wrote to the file.");
		 
	 }
	 
	 public void executeBatchFileold(String filePath) throws Exception {
	
	 String path=  System.getProperty("user.dir") + filePath;
		File dir = new File(path);
		System.out.println("Started");
	
	//	ProcessBuilder pb = new ProcessBuilder("cmd", "/c","Start", "/min" , fpath);
		ProcessBuilder pb = new ProcessBuilder("cmd", "/c","Start", "/B",fpath);
		pb.directory(dir);

		Process p = pb.start();
		pb.command("cmd.exe", "/c", "echo", "u");
	 }
	 
	 
	 
	 
		public void executeBatchFile(String filePath) throws Exception {

			String path = System.getProperty("user.dir") + filePath;
			File dir = new File(path);
			System.out.println("Started Lwm2m Simulator");

			// ProcessBuilder pb = new ProcessBuilder("cmd", "/c","Start", "/min" , fpath);
			ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "Start", "/B", fpath);
			pb.directory(dir);

			Process p = pb.start();
			pb.command("cmd.exe", "/c", "echo", "u");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			String s = null;
			logger.info("Commands for simulator");
			boolean errorCheck = false;

			while (stdError.ready() && (s = stdError.readLine()) != null) {
				logger.error(s);

				Thread.sleep(1000);
			}

			String line = "";
			int count = 0;
			Thread.sleep(2000);
			//if (!errorCheck) {
				while (input.ready() && (line = input.readLine()) != null) {
					logger.info(line);
					Thread.sleep(1000);

				}
		//	}

			Thread.sleep(5000);
			input.close();

			stdError.close();

			

		}
	//MB
	@Step
	public void textBoxValue(String textValue) throws Exception {
 
	        Thread.sleep(2000);
		 	if(textValue.equalsIgnoreCase("DeviceID")) {
		 		logger.info("device id is" +lwm2miccid);
		 		WEB_Methods.WEB_SendKeys(WEB_Methods.WEB_findElement("XPATH",textValue), lwm2miccid);
		 	}
	
		 	if(textValue.equalsIgnoreCase("SubscriberID")) {
		 		logger.info("subscriber id is" +lwm2mmsisdn);
		 		WEB_Methods.WEB_SendKeys(WEB_Methods.WEB_findElement("XPATH",textValue), lwm2mmsisdn);
		 	}
			
		 	if(textValue.equalsIgnoreCase("ICCID")) {
		 		logger.info("ICCID id is" +lwm2miccid+"00000");
		 		WEB_Methods.WEB_SendKeys(WEB_Methods.WEB_findElement("XPATH",textValue), lwm2miccid+"00000");
		 	}
			
		}
	
	
	//MB
	@Step
	public void searchbuttonChk(String textValue) throws Exception {
                 
		        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);	
		        WEB_findElement("XPATH",textValue).clear();
		 		WEB_Methods.WEB_SendKeys(WEB_Methods.WEB_findElement("XPATH",textValue), lwm2miccid);
		 		
	}
	//MB
	@Step
	public void playButton(String buttonToClick) throws Exception {
 
		try {
			Report_getscreenShot("Current Page Screenshot"+RandomStringUtils.randomAlphanumeric(4));
			WEB_click(WEB_Methods.WEB_findElement("XPATH",buttonToClick));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		}
	
	@Step
	public void deleteDevices() throws Exception {
 
		    driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
			List<WebElement> sort = driver.findElements(By.xpath("//td[@class='sorting_1']"));
			logger.info("Size of sort" +sort.size());
			if(sort.size()==0) {
				
				logger.info("No devices found to delete");
			}
			else {
				Actions builder = new Actions(driver);
		        builder.keyDown(Keys.CONTROL)
		               .click(sort.get(0))
		               .click(sort.get(1))
		               .click(sort.get(2))
		               .click(sort.get(3))
		               .click(sort.get(4))
		               .keyUp(Keys.CONTROL).build().perform();		}
	
		}
	
	
	
	@Step
	public void playButtons(String buttonToClick) throws Exception {
 
		    Thread.sleep(1000);
			driver.findElement(By.xpath("(//span[contains(@class,'fa fa-play')])[4]")).click();
			
		}
	 
	
	@Step
	public void createNewFolder(String folderName) throws Exception {
 
		     
			WEB_click(WEB_Methods.WEB_findElement("XPATH","Folder_creation"));
			WEB_click(WEB_Methods.WEB_findElement("XPATH","New_Folder"));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(.,'Folder')])[1]")));
			driver.findElement(By.xpath("(//a[contains(.,'Folder')])[1]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(.,'New Folder...')]")).click();
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("folderName")));
			driver.findElement(By.id("folderName")).sendKeys(folderName);
			driver.findElement(By.id("description")).sendKeys("Automation Purpose Creation of Folder");
			Report_getscreenShot("Input values for New Folder Creation Screenshot");
			driver.findElement(By.xpath("//span[contains(.,'Add New Folder')]")).click();
			Report_getscreenShot("New Folder Creation Screenshot");
			
		}
	
    //MB
	@Step
	public void user_scroll_right(String element) throws Exception {
		Thread.sleep(2000);
		WEB_Methods.ScrollHorizontally(element);
	}
	
	
	@Step
	public void deviceidchk(String textValue,String value) throws Exception {
    
		Thread.sleep(1000);
		
		while(!(driver.findElements(By.xpath("//td[contains(.,'No matching records found')]")).size()==1)) {
			
			generateICCIDMSISDN(value);
	    	searchbuttonChk("Search");
		}
		
		 Assert.assertEquals(true,driver.findElements(By.xpath("//td[contains(.,'No matching records found')]")).size()==1);
		 logger.info("No matching records found for the searched device id");
//		    
//	    if(driver.findElements(By.xpath("//td[contains(.,'No matching records found')]")).size()==1) {
//	    	
//	    	  Assert.assertEquals(true,driver.findElements(By.xpath("//td[contains(.,'No matching records found')]")).size()>1);
//	    	  logger.info("No matching records found for the searched device id");
//	    }
//	    else {
//	    	generateICCIDMSISDN();
//	    	searchbuttonChk("Search");
//	    }
	    	    
	}
	


	@Step
	public void user_Click_on_log_off() throws Throwable {
		
       driver.switchTo().window(parentWindow);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);	
        logger.info("smpusername is" +smpUsername);
        WEB_click(WEB_findElement("STRING",
				"//span[contains(.,'"+smpUsername+"')]"));

		WEB_click(WEB_findElement("STRING", "//a[contains(.,'Logout')]"));
		logger.info("User Logged off successfully");
         
		
		driver.quit();
		
	}

	@Step
	public void user_Click_on_logOut(String user) throws Throwable {
	
		 driver.switchTo().window(parentWindow);
	        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);	
	        logger.info("smpusername is" +realDeviceApplUSR);
	        WEB_click(WEB_findElement("STRING",
					"//span[contains(.,'"+realDeviceApplUSR+"')]"));

			WEB_click(WEB_findElement("STRING", "//a[contains(.,'Logout')]"));
			logger.info("User Logged off successfully");
	         
			
			driver.quit();

		
	}


	@Step
	public void I_click_on_a_button(String Button) throws Throwable {
		WEB_click(WEB_Methods.WEB_findElement("", Button));
	}

	@Step
	public void user_switch_to_the_Iframe(String Workflow_Frame) throws Throwable {
		WebElement iframe = WEB_Methods.WEB_findElement("", Workflow_Frame);
		driver.switchTo().frame(iframe);

		System.out.println("User switch to the " + iframe);
	}
	
	@Step
	public void user_clicks_on_button(String value) throws Exception {
		// Thread.sleep(3000);
		if (driver.findElements(By.xpath("//*[@class='button__content']/span[contains(text(),'" + value + "')]"))
				.size() == 0) {
			WEB_scrolldown_eventfiring("div.container-content__main");
		}
		Report_getscreenShot(value);
		WEB_click(WEB_findElement("STRING", "//*[@class='button__content']/span[contains(text(),'" + value + "')]"));
		logger.info(value + "button is clicked successfully");
	}

	@Step
	public void user_clicks_on_confirmation(String value) throws Exception {
		// *[contains(@id,'alert')]
		WEB_click(WEB_Methods.WEB_findElement("STRING", "//span[contains(text(),'" + value + "')]"));
		logger.info(value + " button is clicked successfully");

	}

	@Step
	public void pagerefresh() throws Exception {
		driver.navigate().refresh();
	}

	@SuppressWarnings("deprecation")
	@Step
	public void QueueCheck_008() throws Exception {
		try {
			logger.info("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			logger.info("Connected database successfully...");
			stmt = conn.createStatement();
			ResultSet value = stmt.executeQuery(
					"SELECT COUNT(*) FROM `dsp`.`rm_terminal_log_collection_queue` WHERE transaction_status in ('IN_PROGRESS', 'ACCEPTED') and added_for_processing = 1");
			while (value.next()) {
				if (value.getString(1) != null && Integer.parseInt(value.getString(1)) >= (1)) {
					System.out.println(value.getString(1)
							+ " <---------------- Queue in process & waiting to END ------------------>");
					Thread.sleep((long) (Integer.parseInt(value.getString(1)) * 30 * 1000));
					ResultSet value2 = stmt.executeQuery(
							"SELECT COUNT(*) FROM `dsp`.`rm_terminal_log_collection_queue` WHERE transaction_status in ('IN_PROGRESS', 'ACCEPTED') and added_for_processing = 1");
					System.out.println(
							value2.getString(1) + " <---------------- Queue in process Re-check ------------------>");
					if (value2.getString(1) != null && Integer.parseInt(value.getString(1)) >= (1)) {
						Assert.fail(value2.getString(1)
								+ " <---------------- STILL Queue in process & EXIT Execution ------------------>");
					}
				} else {
					System.out
							.println(value.getString(1) + " <---------------- No Queue in process ------------------>");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//	@Step
//	public void user_scrolls_down_page() throws Exception {
//		Thread.sleep(3000);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.container-content__main")));
//		if (driver.findElements(By.cssSelector("div.container-content__main")).size() > 0) {
//			WEB_scrolldown_eventfirings("div.container-content__main", "1000");
//		}
//	}
	
	
	@Step
	public void user_scrolls_down_page() throws Exception {
		
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Click on the SUBMIT button')]")));
		
		WEB_Methods.scroll_down(driver.findElement(By.xpath("//*[contains(text(),'Verizon Device SKU number')]")));
   
		WEB_Methods.scrolldown();
		 
		Thread.sleep(2000);

	}
	
	
	@Step
	public void scrolldownwi() throws Exception {
	
	 
		WEB_Methods.scrolldown();
		 
		Thread.sleep(2000);

	}
	
	
	@Step
	public void user_scrolls_up_page() throws Exception {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.container-content__main")));
		if (driver.findElements(By.cssSelector("div.container-content__main")).size() > 0) {
			WEB_scrolldown_eventfirings("div.container-content__main", "0");
		}
	}

	@SuppressWarnings("deprecation")
	@Step
	public void DBexecuteQuery(String query) throws Throwable {

		try {
			logger.info("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			logger.info("Connected database successfully...");
			stmt = conn.createStatement();
			stmt.executeQuery(query);
			// closing DB Connection
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public Response postRequest(String URL, String xmlBody,String tenantValue,String impactUsername, String impactPassword ) {

//		impactUsername= "iotuser";
//		impactPassword="Motive@123";
		System.out.println("impactUsername" +impactUsername);
		final Response resp = given().auth().preemptive().basic(impactUsername,impactPassword)
				.contentType("application/json").accept("application/json")
				.header("DeviceTenant", tenantValue).body(xmlBody).when().post(URL).then()
				.extract().response();
		logger.info("Response Body" + resp.getBody());
		logger.info("StatusCode" + resp.getStatusCode());
		return resp;
	}
	
	public Response getRequest(String URL,String tenantValue,String impactUsername,String impactPassword) {

		final Response resp = given().auth().preemptive().basic(impactUsername,impactPassword).contentType("application/json").
				header("DeviceTenant", tenantValue).when().get(URL).then()
				.statusCode(200).extract().response();
		logger.info("Response Body" + resp.getBody());
		logger.info("StatusCode" + resp.getStatusCode());
		return resp;
	}

	
public static String jsonData(String jsonFileName) throws Exception {
		
		String json ="";
		 jsonFileName="Operations.json";
		try {
			
			String fPath = System.getProperty("user.dir") + "\\TestData\\RESTAPI\\" +jsonFileName;
	        JSONParser parser = new JSONParser();
	        //Use JSONObject for simple JSON and JSONArray for array of JSON.
	        JSONObject data = (JSONObject) parser.parse(
	              new FileReader(fPath));//path to the JSON file.

	         json = data.toJSONString();
	        
	        System.out.println("Json" +json);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return json;
	}

	@SuppressWarnings("deprecation")
	@Step
	public ResultSet DBexecuteQueryResult(String query) throws Throwable {
		try {
			logger.info("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			logger.info("Connected database successfully...");
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(query);
			// closing DB Connection
			conn.close();
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void userSwitchParentWindow() throws Exception {

	    	logger.info("Session id of parentwindow" +parentWindow);
		  driver.switchTo().window(parentWindow);
		  Thread.sleep(2000);
	}
	
	
	public void reloadsPage() throws Exception, InterruptedException {
	  
		  driver.navigate().refresh();
		  WEB_click(WEB_Methods.WEB_findElement("XPATH", "close_buttonOnLoad"));
		  Thread.sleep(8000);
	}
	

	public static void main(String[] args) throws Exception {
		
		
		Stepdef_CommonSteps s = new Stepdef_CommonSteps();
	//	s.lwm2mSimulatorRun();		
		//s.createNewFile("000863045334486-3045334444_bootstrap-QA.bat","");
		//s.executeBatchFile("");
	//
		//System.out.println("username" +WEB_Methods.WEB_getPropertyValue("LWM2M_Impact"));
		s.createNewFile("\\TestData\\LWM2M\\", "000863040516816-3045319600_bootstrap-QA.bat");
	}
	
}
