package com.nokia.atf.tef.common.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.jws.WebMethod;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.hadoop.hive.metastore.api.ThriftHiveMetastore.compact_result;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.syntax.jedit.InputHandler.repeat;

//import org.apache.commons.io.FileUtils.cleanDirectory
import com.gargoylesoftware.css.util.ThrowCssExceptionErrorHandler;
import com.nokia.atf.tef.common.utilities.WEB_Methods;
import com.nokia.atf.tef.deviceSimulator.OMADMSimulator;
import com.nokia.atf.tef.deviceSimulator.PropertyDemo;
import com.nokia.atf.tef.smp.uiflow.scenarios.FTPExample;
import com.nokia.atf.tef.smp.uiflow.scenarios.Steps_19A;

//import org.testng.asserts.SoftAssert;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import junit.framework.Assert;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

// Imports by Jaya
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import com.google.common.base.CharMatcher;

import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

@Component
@ContextConfiguration(locations = { "classpath:smp-config/smp-context.xml" })
public class Stepdef_19A extends WEB_Methods {
	private static final Logger logger = LoggerFactory.getLogger(Stepdef_19A.class);
	@Value("${smp.nbi.uiworkflow.dburl}")
	private String DB_URL;

	@Value("${smp.nbi.uiworkflow.dbusername}")
	private String USER;

	@Value("${smp.nbi.uiworkflow.dbpassword}")
	private String PASS;

	@Value("${smp.nbi.uiworkflow.tunnelURL}")
	private  String TUNNEL_URL;
	
	@Value("${smp.nbi.uiworkflow.tunnelPORT}")
	private  int TUNNEL_PORT;
	
	@Value("${smp.nbi.uiworkflow.tunnelUSER}")
	private  String TUNNEL_USER;

	@Value("${smp.nbi.uiworkflow.tunnelPASS}")
	private  String TUNNEL_PASS;
	
	@Value("${smp.nbi.uiworkflow.tunnelLocalPORT}")
	private  int tunnelLocalPORT;
	
	@Value("${smp.nbi.uiworkflow.tunnelRemoteHOST}")
	private  String tunnelRemoteHOST;
	
	@Value("${smp.nbi.uiworkflow.tunnelRemotePORT}")
	private  int tunnelRemotePORT;
	
	@Value("${smp.nbi.uiworkflow.impactusername}")
	private String impactUsername;
	
	@Value("${smp.nbi.uiworkflow.realDeviceID}")
	private String realDeviceID;
	

	@Value("${smp.nbi.uiworkflow.realMSISDN}")
	private String realMSISDN;

	@Value("${smp.nbi.uiworkflow.impactpassword}")
	private String impactPassword;
	
	public static String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());;

	public static String fileTypes = "";

	public static WebElement fileInput;

	private static Connection conn = null;
	private static Statement stmt = null;
	public static int folderSize=0;
    public static String impactDeviceID; 
	
	// Variables Added
	public static final String delimiter = ",";
	// Existing added Static


	@Value("${smp.nbi.uiworkflow.url}")
	private String smpURL;

	@Value("${smp.nbi.uiworkflow.username}")
	private String smpUsername;

	@Value("${smp.nbi.uiworkflow.password}")
	private String smpPassword;

	@Value("${smp.nbi.uiworkflow.browser}")
	private String browserName;

	@Value("${smp.nbi.uiworkflow.language}")
	private String language;

	@Value("${smp.nbi.uiworkflow.environment}")
	private String environment;
	
    private FTPExample ftp;

	@Steps
	private static Stepdef_CommonSteps CommonSteps;
	private static String childGUID="";

 
	@Step
	public void enterStartDate(String value) throws Exception {
		// String[] hr=WEB_Methods.currenttime().split(":");
		// hr[1].
		WEB_findElement("ID STRING", "uploadArchiveDocStartDateId-field-textInput").clear();
		WEB_findElement("ID STRING", "uploadArchiveDocStartDateId-field-textInput")
				.sendKeys(addDateToCurrentday(1).replace("-", "/") + " " + value);
	}

	@Step
	public void enterESStartDate() throws Exception {
		// String[] hr=WEB_Methods.currenttime().split(":");
		// hr[1].
		WEB_findElement("ID STRING", "emgencyStDateId-field-textInput").clear();
		WEB_findElement("ID STRING", "emgencyStDateId-field-textInput")
				.sendKeys(addDateToCurrentday(0).replace("-", "/") + " 01:10");
	}

	@SuppressWarnings({ "deprecation" })
	@Step
	public void verifyCRverError(String Module) throws Exception {
		Report_getscreenShot(Module + " ErrorMsg");
		String Error = WEB_findElement("STRING", "(//*[@id='fotaerrorText']//label)[1]").getText();
		if (Module.equalsIgnoreCase("Docomo"))
			Assert.assertEquals("CRVer range should be between 00000001 to 09999999 (or) 10000001 to 79999999", Error);
		else if (Module.equalsIgnoreCase("Maker"))
			Assert.assertEquals("CRVer Should be between 90000001 to 99999999", Error);
		if (Module.equalsIgnoreCase("NW"))
			Assert.assertEquals("CRVer Should be between 80000001 to 89999999", Error);
		logger.info(Error);
	}

	@SuppressWarnings({ "deprecation" })
	@Step
	public void verifyCRverErrorOSV(String Module) throws Exception {
		Report_getscreenShot(Module + " ErrorMsg");
		String Error = WEB_findElement("STRING", "//*[@id='customUploadErrorMsg']//label").getText();
		if (Module.equalsIgnoreCase("Docomo"))
			Assert.assertEquals("CRVer range should be between 00000001 to 09999999 (or) 10000001 to 79999999", Error);
		else if (Module.equalsIgnoreCase("Maker"))
			Assert.assertEquals("CRVer Should be between 90000001 to 99999999", Error);
		logger.info(Error);
	}

	@SuppressWarnings("deprecation")
	public void verifyModelError() throws Exception {
		String Error = WEB_findElement("STRING", "(//*[@id='fotaerrorText']//label)[2]").getText();
		logger.info(Error);
		Assert.assertNotNull(Error);

	}

	@SuppressWarnings("deprecation")
	public void verifyCropError() throws Exception {
		Thread.sleep(2000);
		Report_getscreenShot("Corporate Msg");
		String Error = WEB_findElement("STRING", "(//*[@id='errorMessage']//label)[2]").getText();
		logger.info(Error);
		Assert.assertNotNull(Error);

	}

	@SuppressWarnings("deprecation")
	public void verifyDate() throws Exception {
		Thread.sleep(2000);
		Report_getscreenShot("UM Date Msg");
		String Error = WEB_findElement("STRING", "(//*[@class='clamp-lines']/div)[1]").getText();
		logger.info(Error);
		Assert.assertNotNull(Error);
		Error = WEB_findElement("STRING", "(//*[@class='clamp-lines']/div)[2]").getText();
		logger.info(Error);
		Assert.assertNotNull(Error);

	}

	@SuppressWarnings("deprecation")
	public void DateOSVerrorMsg() throws Exception {
		Thread.sleep(1000);
		Report_getscreenShot("Operation-Modify Archive For Op");
		String Error = WEB_findElement("STRING", "(//*[@class='error']/div/div)[1]").getText();
		logger.info(Error);
		Assert.assertNotNull(Error);
		Error = WEB_findElement("STRING", "(//*[@class='error']/div/div)[2]").getText();
		logger.info(Error);
		Assert.assertNotNull(Error);

	}

	@SuppressWarnings("deprecation")
	public void verifyDateError() throws Exception {
		Report_getscreenShot("Corporate Msg");
		String Error = WEB_findElement("STRING", "(//*[@id='errorText']//label)[2]").getText();
		logger.info(Error);
		Assert.assertNotNull(Error);

	}


	@Step
	public void user_scrolls_down(String btn) throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.container-content__main")));
		System.out
				.println("Scroll present " + driver.findElements(By.cssSelector("div.container-content__main")).size());
		if (driver.findElements(By.cssSelector("div.container-content__main")).size() > 0) {
			WEB_scrolldown_eventfiring("div.container-content__main");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + btn + "']")));

	}



	@Step
	public void user_OSVBlackoutIregular(int index, String selection, int selVal) throws Exception {
		try {
			String Value = null;

			if (selection.equalsIgnoreCase("Date")) {
				index = index + 1;
				WEB_findElement("ID STRING", "fotaUmBlackListCal-" + index + "-field-textInput").clear();
				WEB_findElement("ID STRING", "fotaUmBlackListCal-" + index + "-field-textInput")
						.sendKeys(addDateToCurrentday(selVal).replace("-", "/"));
				Value = WEB_findElement("ID STRING", "fotaUmBlackListCal-" + index + "-field-textInput").getText();
				logger.info(Value);
//					Assert.assertNotNull(Value);
			}
			if (selection.equalsIgnoreCase("Start Hour")) {
				index = index + 1;
				selVal = selVal + 1;
				WEB_findElement("STRING", "(//span[@id='react-select-startHour--value'])[" + index + "]").click();
				WEB_findElement("ID STRING", "react-select-startHour--option-" + selVal).click();
				Value = WEB_findElement("STRING", "(//span[@id='react-select-startHour--value'])[" + index + "]")
						.getText();
				logger.info(Value);
				Assert.assertNotNull(Value);
			}
			if (selection.equalsIgnoreCase("Start Minute")) {
				index = index + 1;
				selVal = selVal + 1;
				WEB_findElement("STRING", "(//span[@id='react-select-startMinute--value'])[" + index + "]").click();
				WEB_findElement("ID STRING", "react-select-startMinute--option-" + selVal).click();
				Value = WEB_findElement("STRING", "(//span[@id='react-select-startMinute--value'])[" + index + "]")
						.getText();
				logger.info(Value);
				Assert.assertNotNull(Value);
			}
			if (selection.equalsIgnoreCase("End Hour")) {
				index = index + 1;
				selVal = selVal + 1;
				WEB_findElement("STRING", "(//span[@id='react-select-endHour--value'])[" + index + "]").click();
				WEB_findElement("ID STRING", "react-select-endHour--option-" + selVal).click();
				Value = WEB_findElement("STRING", "(//span[@id='react-select-endHour--value'])[" + index + "]")
						.getText();
				logger.info(Value);
				Assert.assertNotNull(Value);
			}
			if (selection.equalsIgnoreCase("End Minute")) {
				index = index + 1;
				selVal = selVal + 1;
				WEB_findElement("STRING", "(//span[@id='react-select-endMinute--value'])[" + index + "]").click();
				WEB_findElement("ID STRING", "react-select-endMinute--option-" + selVal).click();
				Value = WEB_findElement("STRING", "(//span[@id='react-select-endMinute--value'])[" + index + "]")
						.getText();
				logger.info(Value);
				Assert.assertNotNull(Value);
			}

			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Step
	public void clicksonFn(String btn) throws IOException {
		try {
			WEB_scrolldown_eventfirings("div.ag-body-viewport.ag-layout-normal", "8000");
			// WEB_Methods.WEB_findElement("STRING",
		
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Step
	public void user_scroll_down_page() throws Exception {
		Thread.sleep(2000);
		System.out.println("PageDown" + driver.findElements(By.cssSelector("div.container-content__main")).size());
		if (driver.findElements(By.cssSelector("div.container-content__main")).size() > 0) {
			WEB_scrolldown_eventfirings("div.container-content__main", "2000");

			// scroll_down(driver.findElement(By.xpath("//*[@class='button__content']/span[text()='Submit'])")));
		}
	}

	@Step
	public void user_scrolls_up() throws Exception {
		WEB_click(WEB_Methods.WEB_findElement("STRING", "//*[text()='Manage SMS Push Task']"));
		WEB_click(WEB_findElement("XPATH", "BlackOutWindowButton"));
		Thread.sleep(3000);
		// if (driver.findElements(By.cssSelector("div.container-content__main")).size()
		// > 0) {
		// WEB_scrollUp_eventfiring("div.container-content__main");
		// }
		// afterBKRegistration = checkRegisterData();
		// logger.info("After Registration" + afterBKRegistration);
	}

	@SuppressWarnings("deprecation")
	@Step
	public void validateErrorMsg(String msg) throws Exception {
		//String error = WEB_findElement("STRING", "(//*[@class='messageOnUi'])[4]").getText(); // #Existing tags changed
		String error = WEB_findElement("STRING","//*[text()='" + msg +"']").getText();
		System.out.println(error);
		Assert.assertEquals(msg + " : ", error, msg);
		Report_getscreenShot(msg);
	}

	@SuppressWarnings("deprecation")
	@Step
	public void APLErrorMsg(String msg) throws Exception {
		String error = WEB_findElement("STRING", "(//*[@class='messageOnUi'])[5]").getText();
		System.out.println(error);
		Assert.assertEquals(msg + " : ", error, msg);
		Report_getscreenShot(msg);
	}

	

	@Step
	public String checkRegisterData() throws Exception {
		Thread.sleep(3000);
		List<WebElement> values = new ArrayList<WebElement>();
		List<String> maxElement = new ArrayList<String>();
		values = driver.findElements(By.xpath("//*[@col-id='id']"));
		System.out.println("driver values is" + values.size());

		for (WebElement w : values) {
			if (w.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {
				maxElement.add(w.getText());
				System.out.print("Iteratie" + maxElement.size());
			}

		}
		return Collections.max(maxElement);

	}

	

	
	@Step
	public void takeScreenshot(String value) throws Exception {
		Thread.sleep(2000);
		Report_getscreenShot(value);
	}

	@Step
	public void user_clicks_on__button(String value) throws Exception {
		// Thread.sleep(3000);
		if (driver.findElements(By.xpath("//*[@class='button__content']/span[text()='" + value + "']")).size() == 0) {
			WEB_scrolldown_eventfiring("div.container-content__main");
		}
		Report_getscreenShot(value);
		WEB_click(WEB_findElement("STRING", "//*[@class='button__content']/span[text()='" + value + "']"));
		logger.info(value + "button is clicked successfully");
	}

	




//	@Step
//	public void user_scroll_left() {
//		try {
//			if (driver.findElements(By.cssSelector("div.ag-body-viewport.ag-layout-normal")).size() > 0) {
//				EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
//				eventFiringWebDriver.executeScript(
//						"document.querySelector('div.ag-body-viewport.ag-layout-normal').scrollLeft=1000");
//			}
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	
	//MB
		@Step
		public void testResultCreateDeviceLwM2M(String textValue) throws Exception {
		   
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Passed')]")));
		       Assert.assertEquals("Test case Status","Passed", driver.findElement(By.xpath("//span[contains(.,'Passed')]")).getText());
		       Thread.sleep(1000);
		    //   Assert.assertEquals("Test case name", "Create Device Bootstrap", driver.findElement(By.xpath("(//a[contains(.,'Create Device Bootstrap')])[2]")).getText());
		    
		       Assert.assertEquals("Test case name", textValue, driver.findElement(By.xpath("//td[contains(text(),'"+textValue+"')]")).getText());
		       Thread.sleep(1000);
		      
			}
		@Step
		public void closeCurrentwindow() throws Exception {		
			
			 for(String handle : driver.getWindowHandles()) {
			        if (!handle.equals(CommonSteps.parentWindow)) {
			            driver.switchTo().window(handle);
			            driver.close();
			        }
			    }
			//driver.close();
		}
		
		
	
		@Step
		public void testCaseRealDevice(String testcase) throws Exception {
		       try {
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(.,'Test Results')]")));
				 if(driver.findElements(By.xpath("//span[contains(.,'Passed')]")).size()>=1){
			        String text = driver.findElement(By.xpath("//span[contains(.,'Passed')]")).getText();
				   logger.info("Test Case is" +text);
				   
				 }
				 
				 if(driver.findElements(By.xpath("//span[contains(.,'Failed')]")).size()>=1){
					 
				logger.info("Test case is failed. Please look into logs for more details" +driver.findElement(By.xpath("//div[contains(@class,'test-case-error-message')]")).getText());
						  
				 }	
				  // Assert.assertEquals(true, tag.contains(driver.findElement(By.xpath("//td[contains(text(),'"+tag+"')]")).getText()));
				   Assert.assertEquals("Test case link", testcase, driver.findElement(By.xpath("(//a[contains(@class,'test-case-link')])[1]")).getText());
				   
			} catch (AssertionError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("Assertion error" +e);
			}
		     
			}
		
		@Step
		public void testCaseTestRsult(String textValue,String tag) throws Exception {
		       try {
		    	   Report_getscreenShot("Before Validation Page"+RandomStringUtils.randomAlphanumeric(8));
				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				if(driver.findElements(By.xpath("//h6[contains(.,'Test Results')]")).size()<1) {
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
				
				  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(.,'Test Results')]")));
				 if(driver.findElements(By.xpath("//span[contains(.,'Passed')]")).size()>=1){
				   Assert.assertEquals("Test case Status","Passed", driver.findElement(By.xpath("//span[contains(.,'Passed')]")).getText());
				   Thread.sleep(1000);
				   logger.info("Test Case is passed");
				   
				 }
				 
				 if(driver.findElements(By.xpath("//span[contains(.,'Failed')]")).size()>=1){
					 
				logger.error("Test case is failed. Please look into logs for more details" +driver.findElement(By.xpath("//div[contains(@class,'test-case-error-message')]")).getText());
						  
				 }	
				 Report_getscreenShot("Validation Page" +RandomStringUtils.randomAlphanumeric(8));
				  // Assert.assertEquals(true, tag.contains(driver.findElement(By.xpath("//td[contains(text(),'"+tag+"')]")).getText()));
				   Assert.assertEquals("Test case link", textValue, driver.findElement(By.xpath("(//a[contains(@class,'test-case-link')])[1]")).getText());
				   
				   Thread.sleep(1000);
			} catch (AssertionError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("Assertion error" +e);
			}
		     
			}
	
		@Step
		public void textFieldAndTextValue(String fieldName,String fieldValue) throws Exception {
		      
			WEB_SendKeys(WEB_Methods.WEB_findElement("XPATH", fieldName),
					fieldValue);
  
		    	   
		  }
		
		@Step
		public void selectFromDropDown(String fieldName,String fieldValue) throws Exception {
		     
		
				WEB_dropDown("XPATH", fieldName, fieldValue);
						
				//driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
		}
		
		
		
		@Step
		public void testCaset(String testcaseLink,String expectedResult) throws Exception {
		       try {
				driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
				Thread.sleep(7*60*1000); 
				
				
				   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[contains(.,'Test Results')]")));
				 if(driver.findElements(By.xpath("//span[contains(.,'Passed')]")).size()>=1){
				   Assert.assertEquals("Test case Status","Passed", driver.findElement(By.xpath("//span[contains(.,'Passed')]")).getText());
				   Thread.sleep(1000);
				   logger.info("Test Case is passed");
				   
				 }
				 
				 if(driver.findElements(By.xpath("//span[contains(.,'Failed')]")).size()>=1){
					 
				logger.info("Test case is failed. Please look into logs for more details" +driver.findElement(By.xpath("//div[contains(@class,'test-case-error-message')]")).getText());
				
				Assert.assertEquals(expectedResult, driver.findElement(By.xpath("//div[contains(@class,'test-case-error-message')]")).getText());
			  
				 }
				 
				 Report_getscreenShot("Validation Page Screenshot");
				
				  // Assert.assertEquals(true, tag.contains(driver.findElement(By.xpath("//td[contains(text(),'"+tag+"')]")).getText()));
				   Assert.assertEquals("Test case link", testcaseLink, driver.findElement(By.xpath("(//a[contains(@class,'test-case-link')])[1]")).getText());
				   
				   Thread.sleep(1000);
			} catch (AssertionError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.info("Assertion error" +e);
			}
		     
			}
		
		
			@Step
			public void jobDetails() throws Exception {
				driver.manage().timeouts().implicitlyWait(7000, TimeUnit.SECONDS);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//td[contains(.,'SUCCESS')])[2]")));

				Assert.assertEquals(Stepdef_CommonSteps.lwm2miccid,
						driver.findElement(By.xpath("(//td[contains(@class,'popup-overlay')])[1]")).getText());
				Assert.assertEquals(Stepdef_CommonSteps.lwm2mmsisdn,
						driver.findElement(By.xpath("(//td[contains(@class,'popup-overlay')])[2]")).getText());

				Assert.assertEquals("SUCCESS",
						driver.findElement(By.xpath("(//td[contains(@class,'popup-overlay')])[6]")).getText());
				Assert.assertEquals("SUCCESS",
						driver.findElement(By.xpath("(//td[contains(@class,'popup-overlay')])[7]")).getText());
				Assert.assertEquals("SENT",
						driver.findElement(By.xpath("(//td[contains(@class,'popup-overlay')])[9]")).getText());

			}
		
			
			@Step
			public void firmwarejobDetails() throws Exception {
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

				Assert.assertEquals("Generic_1_2.bin",
						driver.findElement(By.xpath("//td[contains(.,'Generic_1_2.bin')]")).getText());
			
				
				Assert.assertEquals("Firmware uploaded successfully.",
						driver.findElement(By.xpath("//td[contains(.,'Firmware uploaded successfully.')]")).getText());
			
				Assert.assertEquals("100%",
						driver.findElement(By.xpath("//td[contains(.,'100%')]")).getText());
			
			}
			
			@Step
			public void firmwarejobDetailsOMADM() throws Exception {
				driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

				Assert.assertEquals("SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto.xml",
						driver.findElement(By.xpath("//td[contains(.,'SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto.xml')]")).getText());
			
				
				Assert.assertEquals("Firmware uploaded successfully.",
						driver.findElement(By.xpath("//td[contains(.,'Firmware uploaded successfully.')]")).getText());
			
				Assert.assertEquals("100%",
						driver.findElement(By.xpath("//td[contains(.,'100%')]")).getText());
			
			}
	
       @Step
		public void firmwaredeletejobDetailsOMADM(String imageName) throws Exception {
			
    	   Thread.sleep(2000);
			if(driver.findElements(By.xpath("//input[contains(@value,'"+imageName+"')]")).size()==0) {
				logger.info("Firmware image is deleted successfully");
			}
			else {
				logger.info("Firmware image is not deleted successfully");
			}
		
		}
		
			
			
			
			
		@Step
		public void selectFirmwareMakeModel(String modelMake) throws Exception{
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(modelMake);
			logger.info("size of model" +driver.findElements(By.xpath("//input[@value='"+modelMake+"']")).size());
			if(driver.findElements(By.xpath("//input[@value='"+modelMake+"']")).size()==1) {
				driver.findElement(By.xpath("//input[@value='"+modelMake+"']")).click();
			}
			else {
				logger.info("Model Not found" );
			}
			
			
		}
		//MB
		
		@Step
		public void verifiesUI(String button) throws Exception {
	           
			   String propertyValue= WEB_Methods.WEB_getPropertyValue(button);
			   int value = driver.findElements(By.xpath(propertyValue)).size();
		       Assert.assertEquals("Passed",1, value);
		       
		       Stepdef_CommonSteps.parentWindow=driver.getWindowHandle();
		       logger.info("Stepdef_CommonSteps.parentWindow" +Stepdef_CommonSteps.parentWindow + driver.getCurrentUrl() + " " + driver.getTitle());
		
		
		}
		
		
	@Step
	public void user_scroll_right() {
		try {
			if (driver.findElements(By.cssSelector("div.ag-body-viewport.ag-layout-normal")).size() > 0) {
				EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
				eventFiringWebDriver.executeScript(
						"document.querySelector('div.ag-body-viewport.ag-layout-normal').scrollRight=1000");
			}
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Step
	public void scrollIntoView(String button) throws Exception {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				WEB_findElement("STRING", "//*[@class='button__content']/span[text()='" + button + "']"));

	}



	@Step
	public void clickNextbutton(String value) throws Exception {

		WEB_click(WEB_Methods.WEB_findElement("STRING", "(//*[text()='Next'])[2]"));
		logger.info(value + "button is clicked successfully");
		Thread.sleep(1000);
		// WEB_scrolldown_eventfiring("div.container-content__main");
		// driver.findElement(By.xpath("//div[@id='dmetargetnextBtn']//*[text()='Next']")).click();

	}

	//MB
	/**
	 * @param value
	 * @throws Exception
	 */
	@Step
	public void omadmORlwm2mPage(String value) throws Exception {
        
		Thread.sleep(1000);
		if(value.equalsIgnoreCase("Manage Firmware")) {
			Stepdef_CommonSteps.parentGUID = driver.getWindowHandle();
		}
		
	    WEB_Methods.switchWindow(Stepdef_CommonSteps.parentGUID);
	    logger.info(driver.getTitle() + driver.getCurrentUrl() +driver.getWindowHandle());
	    childGUID= driver.getWindowHandle();
	    if(!value.equalsIgnoreCase("Manage Firmware") || !value.equalsIgnoreCase("OMADMDeleteDevice")) {
	    CommonSteps.generateICCIDMSISDN(value);
	    }
	    
	
	    
	    
	   
			
	}
	
	
	@Step
	public void testSetPage(String value) throws Exception {

	    WEB_Methods.switchWindow(Stepdef_CommonSteps.parentGUID);
	    logger.info(driver.getTitle() + driver.getCurrentUrl() +driver.getWindowHandle());
	    childGUID= driver.getWindowHandle();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='dropdownMenu1']")));
	    Thread.sleep(2000);
			
	}
	
	


	
	@Step
	public void dateAndTimeEnterForExport(String start, String end) throws Exception {
	
		if (end.equals("CURRENT_DATE")) {
			end = WEB_Methods.currentDateinFormat();
		}
		

	}

	@Step
	public void file_downloaded() throws Exception {

		Robot robot;
		try {
			// pressing download button

			// button.sendKeys("&quot;&quot;");
			robot = new Robot();

			// Point coordinates= WEB_Methods.WEB_findElement("STRING",
			// "//*[@class='button__content']/span[text()='" + value + "']").getLocation();
			// System.out.println("Co-ordinates"+coordinates);
			//
			// robot.mouseMove(coordinates.getX()+100,coordinates.getY()-400);
			// Thread.sleep(2000);
			// robot.mousePress( InputEvent.BUTTON1_DOWN_MASK);
			// robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			//
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			// handling download
			Thread.sleep(1000);
			robot.setAutoDelay(250);
			robot.keyPress(KeyEvent.VK_ALT);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_S);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_S);

			// driver.switchTo().activeElement().sendKeys(Keys.chord(Keys.ALT+"S"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Step
      public void DoubleClick(int clk, int notif, int renotif, int ind) throws Exception {
		int Afrclk = clk;
		int Afrnotif = notif;
		int Afrrenotif = renotif;
		int Afrind = ind;
		// Thread.sleep(3000);
		WEB_action_doubleClick(WEB_Methods.WEB_findElement("STRING", "(//*[@col-id='hour']//*[text()='" + clk
				+ "']//following::div[@col-id='notificationAfter']//input)[1]"));
		WEB_action_sendKeys(
				WEB_Methods.WEB_findElement("STRING",
						"(//*[@col-id='hour']//*[text()='" + clk
								+ "']//following::div[@col-id='notificationAfter']//input)[1]"),
				Integer.toString(notif));

	}

	@Step
	public void editAPL() {
		try {
			WEB_scrolldown_eventfiring("div.container-content__main");
			WEB_action_doubleClick(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[1]"));
			WEB_action_clear(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[1]"));
			WEB_action_sendKeys(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[1]"), "11223344559");

			// WEB_Methods.WEB_findElement("STRING",
			// "(//*[@row-id='0']//input)[3]").click();

			WEB_action_doubleClick(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[4]"));
			WEB_action_clear(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[4]"));
			WEB_action_sendKeys(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[4]"), "AfterEdit");

			WEB_action_doubleClick(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[5]"));
			WEB_action_clear(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[5]"));
			WEB_action_sendKeys(WEB_Methods.WEB_findElement("STRING", "(//*[@row-id='0']//input)[5]"), "AfterEdit");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	



	
	@Step
	public void VerifyErrorMsg() throws IOException, InterruptedException {
		if (WEB_findElement("STRING", "//*[@class='required-msg-show']").isDisplayed())
			WEB_Methods.Report_getscreenShot("Hourly Push Rate error message");
		logger.info(WEB_findElement("STRING", "//*[@class='required-msg-show']").getText());
	}

	@Step
	public void uploadImage() throws Exception {
		try {
			Thread.sleep(3000);
			fileInput = WEB_findElement("STRING", "//*[@id='pvibrowseBtn']");
			WEB_ActionClick(fileInput);
			fPath = System.getProperty("user.dir") + "\\TestData\\009\\PVI\\" + "Bird.JPG";
			uploadFile(fPath);
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	@Step
	public void enterAPL(String Value) throws IOException, InterruptedException {
		Thread.sleep(2000);
		WEB_findElement("ID STRING", "aplidValue-textInput").clear();
		WEB_findElement("ID STRING", "aplidValue-textInput").sendKeys(Value);
		WEB_findElement("ID STRING", "aplNameValue-textInput").sendKeys("AutoTest");
	}

	@Step
	public void enterpackageName() throws IOException, InterruptedException {
		WEB_findElement("ID STRING", "packageNameValue-textInput").clear();
		WEB_findElement("ID STRING", "packageNameValue-textInput").sendKeys("AutoPackage");
	}

	@SuppressWarnings("deprecation")
	@Step
	public void multicast_pushTest(String MSISDN) throws Exception {

		boolean sendbutton1 = WEB_Methods.WEB_findElement("STRING",
				"//div[@id='user-during-RO-ButtonGroup-radioBtn-ro-option1-toggleInput']//div[@class='own-radio']")
				.isEnabled();
		logger.info("sendbutton1 " + sendbutton1);
		boolean sendbutton2 = WEB_Methods.WEB_findElement("STRING",
				"//div[@id='spam-sms-denied-user-ButtonGroup-radioBtn-spam-option1-toggleInput']/div[@class='own-radio']")
				.isEnabled();
		logger.info("sendbutton2 " + sendbutton2);
		boolean notSendButton3 = WEB_Methods.WEB_findElement("STRING",
				"//div[@id='user-during-ODB-ButtonGroup-radioBtn-spam-option2-toggleInput']/div[@class='own-radio']")
				.isEnabled();
		logger.info("notSendButton3 " + notSendButton3);

		logger.info("MSISDN :" + MSISDN);
		Assert.assertEquals(true, sendbutton1);
		Assert.assertEquals(true, sendbutton2);
		Assert.assertEquals(true, notSendButton3);

		WEB_Methods.WEB_findElement("STRING", "//input[@id='TextInputID1-textInput']").click();
		WEB_Methods.WEB_findElement("STRING", "//input[@id='TextInputID1-textInput']").sendKeys(MSISDN);
		Thread.sleep(3000);
		WEB_scrolldown_eventfiring("div.container-content__main");
		WEB_click(WEB_Methods.WEB_findElement("STRING", "//span[@class='button__text' and text()='Execute']"));

	}

	

	

	
	@Step
	public void user_bulkdata() throws Exception {

		try {
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			String status = "";
			int count = 0;
			for (int i = 0; i <= 30; i++) {
				if (i <= 16) {
					if (WEB_findElement("STRING", "//div[@row-id='" + i + "']//div[@col-id='status']").isDisplayed()) {
						logger.info("Status is" + status);
						if (status.equalsIgnoreCase("Scheduled") && count == 0) {

							WEB_findElement("STRING", "//div[@row-id='" + i + "']//div[@col-id='select']").click();
							count = count + 1;
						}
					}
				} else {

					WEB_scrolldown_eventfirings("div.ag-body-viewport.ag-layout-normal", "4000");
					status = WEB_findElement("STRING", "//div[@row-id='" + i + "']//div[@col-id='status']").getText();
					logger.info("Status is" + status);
					if (status.equalsIgnoreCase("Scheduled") && count == 0) {

						WEB_findElement("STRING", "//div[@row-id='" + i + "']//div[@col-id='select']").click();
						count = count + 1;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	


//MB
	@Step
	public void user_clicks_on_Status(String buttonToClick) throws Exception {
 
		  if(buttonToClick.equalsIgnoreCase("Create Device Bootstrap")) {
			WEB_click(WEB_Methods.WEB_findElement("XPATH","Create_Device_Bootstrap"));
		  }
          
		  if(buttonToClick.equalsIgnoreCase("Delete Device List")) {
				WEB_click(WEB_Methods.WEB_findElement("XPATH","Delete_Device_List"));
			  }
	  
			WEB_click(WEB_Methods.WEB_findElement("STRING","//a[contains(.,'"+buttonToClick+"')]"));		
			
			
			
		}
	 
	
	
	
	
	 //MB
		@Step
		public void selectFolder(String folderName) throws Exception {
			
			Thread.sleep(2000);
			int sizes= driver.findElements(By.xpath("//i[contains(@class,'jstree-icon jstree-ocl')]")).size();
			
			for(int i=1;i<=sizes;i++) {
				
				if(driver.findElement(By.xpath("(//a[contains(@class,'jstree-anchor')])["+i+"]")).getText().equalsIgnoreCase(folderName)){
					
					driver.findElement(By.xpath("(//i[contains(@class,'jstree-icon jstree-ocl')])["+i+"]")).click();
					logger.info("Foldername is "+driver.findElement(By.xpath("(//a[contains(@class,'jstree-anchor')])["+i+"]")).getText());
				    
					folderSize= i;
				}
				else {
					continue;
				}
				
			}
			
			Thread.sleep(2000);
		}
		
		@Step
		public void newFolderCreation(String newfolderName) throws Exception {
			
			WEB_click(WEB_findElement("STRING", "//span[@class='fa fa-folder']"));
			WEB_click(WEB_findElement("STRING", "//td[@class='menu-text'][contains(.,'New Folder...')]"));
			
			driver.findElement(By.id("folderName")).sendKeys(newfolderName);
			Report_getscreenShot("Input values for New Folder Creation Screenshot");
			driver.findElement(By.xpath("//span[contains(.,'Add New Folder')]")).click();
			Report_getscreenShot("New Folder Creation Screenshot"+ RandomStringUtils.randomAlphanumeric(6));
		
		}
		
		
		
		@Step
		public void selectFolders(String testCase,String testCaseName) throws Exception {

			 Thread.sleep(2000);
 			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jstree-anchor  jstree-disabled'][contains(@id,'anchor')][contains(.,'"+testCase+"')]")));
 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jstree-anchor  jstree-disabled'][contains(@id,'anchor')][text()='"+testCase+"']")));

			Actions actions = new Actions(driver);
			WebElement elementLocator= driver.findElement(By.xpath ("//a[@class='jstree-anchor  jstree-disabled'][contains(@id,'anchor')][text()='"+testCase+"']"));
			actions.doubleClick(elementLocator).perform();
			
 			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jstree-anchor'][contains(@id,'anchor')][contains(.,'"+testCaseName+"')]")));
			driver.findElement(By.xpath ("//a[@class='jstree-anchor'][contains(@id,'anchor')][contains(.,'"+testCaseName+"')]")).click();
		}
		
	
		
		
		
		
		@Step
		public void selectStatusToStartTest(String testStatus) throws Exception {
			
		    try {
				driver.manage().timeouts().implicitlyWait(2000,TimeUnit.SECONDS);
				WEB_dropDown("STRING", "//select[@class='form-control']", testStatus);
				Report_getscreenShot("Status Select Screenshot");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);	
		}
		
		@Step
		public void selectDeviceList(String deviceCriteria) throws Exception {
			
			
		    driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);
			WEB_dropDown("STRING", "//select[contains(@class,'form-control ng-pristine ng-valid ng-touched')]", deviceCriteria);
					
			driver.manage().timeouts().implicitlyWait(3000,TimeUnit.SECONDS);	
		}
		
		@Step
		public void devicebyid(String deviceId) throws Exception {
				
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath("(//input[@placeholder='"+deviceId+"'])[1]")).sendKeys(impactDeviceID);
		driver.findElement(By.xpath("//button[contains(.,'Search')]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(impactDeviceID, driver.findElement(By.xpath("//a[@ng-click='dc.showDeviceDetails(row.id,row.tenantId)']")).getText());
		
		}
		
		
		@Step
		public void devicedata() throws Exception {
			
			impactDeviceID= driver.findElement(By.xpath("(//a[@ng-click='dc.showDeviceDetails(row.id,row.tenantId)'])[1]")).getText();
			logger.info("Device id of impact is" +impactDeviceID);
			
		}
		
		
		@Step
		public void selectsfolderClicking(String folderName) throws Exception {
			
			Thread.sleep(2000);
			if(driver.findElements(By.xpath("//a[contains(@id,'anchor')][contains(.,'"+Stepdef_CommonSteps.folderFriendlyName+"')]")).size()<1){
				
				driver.findElement(By.xpath("(//i[@class='jstree-icon jstree-ocl'])["+folderSize+"]")).click();
			}
			
					
					
		}
		
		
		
		@Step
		public void selectTestSuiteFolder() throws Exception {
			Stepdef_CommonSteps.parentGUID=driver.getWindowHandle();
		//	Stepdef_CommonSteps.friendlyName="357862090060866_AT855";
			
			Thread.sleep(1000);
			int sizes= driver.findElements(By.xpath("//i[contains(@class,'jstree-icon jstree-ocl')]")).size();
			
			for(int i=1;i<=sizes;i++) {
				
				if(driver.findElement(By.xpath("(//a[contains(@class,'jstree-anchor')])["+i+"]")).getText().equalsIgnoreCase(Stepdef_CommonSteps.friendlyName)){
					
					driver.findElement(By.xpath("(//i[contains(@class,'jstree-icon jstree-ocl')])["+i+"]")).click();
					logger.info("Foldername is "+driver.findElement(By.xpath("(//a[contains(@class,'jstree-anchor')])["+i+"]")).getText());
				    
					folderSize= i;
				}
				else {
					continue;
				}
				
			}
			
			Thread.sleep(2000);
		}
		
		@Step
		public void selectTestSet(String testSet) throws Exception {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@id,'anchor')][contains(.,'"+testSet+"')]")));
			driver.findElement(By.xpath ("//a[contains(@id,'anchor')][contains(.,'"+testSet+"')]")).click();
		}
		
		
		
		@Step
		public void selectTestCase(String testCase) throws Exception {
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='jstree-anchor'][contains(@id,'anchor')][contains(.,'"+testCase+"')]")));
			driver.findElement(By.xpath ("//a[@class='jstree-anchor'][contains(@id,'anchor')][contains(.,'"+testCase+"')]")).click();
		}
		
		@Step
		public void radioButtonFirmware(String makemodelName) throws Exception {
			

            driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(makemodelName);
            driver.findElement(By.xpath("//input[contains(@name,'unique_id-select')]")).click();
            
            

		}
		
		
		
		 //MB
		@Step
		public void createFolder(String folderName) throws Exception {
	
	    Stepdef_CommonSteps.folderFriendlyName= folderName;
		logger.info("The folder size is" +folderSize);
		WEB_scrolldown_eventfirings("div.panel-body","8000");
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("(//a[contains(@class,'jstree-anchor')])["+folderSize+"]"))).contextClick().build().perform();
		Thread.sleep(2000);
		
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
		public void createTestSuite(String testSuite,String productName) throws Exception {
		
// 		Stepdef_CommonSteps.lwm2miccid="000863046018438";
//		Stepdef_CommonSteps.lwm2mmsisdn="3045357666";
//			
		//if(driver.findElements(By.xpath("//a[contains(text(),'"+Stepdef_CommonSteps.lwm2miccid+"')]")).size()<1) {
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[contains(.,'"+testSuite+"')]"))).contextClick().build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(.,'Folder')])[1]")));
		driver.findElement(By.xpath("(//a[contains(.,'Test Suite')])[1]")).click();
		driver.findElement(By.xpath("//a[contains(.,'New Test Suite...')]")).click();	

		Report_getscreenShot("New Test Suite Creation Screenshot");
		
		WEB_dropDown("XPATH", "ProductName",productName);
		Stepdef_CommonSteps.friendlyName= Stepdef_CommonSteps.lwm2miccid+"_AT"+new Random().nextInt(1000);
		driver.findElement(By.xpath("//input[@name='friendlyName']")).sendKeys(Stepdef_CommonSteps.friendlyName);
		driver.findElement(By.xpath("//button[contains(.,'Select Device >')]")).click();
		//}
		
		
		
		}
		
		@Step
		public void deviceIdSearch(String deviceSearch) throws Exception {
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Test Suite - Select Device')]")));
		WEB_Methods.WEB_findElement("XPATH", deviceSearch).clear();
		WEB_Methods.WEB_findElement("XPATH", deviceSearch).sendKeys(Stepdef_CommonSteps.lwm2miccid);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='"+Stepdef_CommonSteps.lwm2miccid+"']")));
		
		WEB_Methods.WEB_findElement("STRING", "//input[@value='"+Stepdef_CommonSteps.lwm2miccid+"']").click();
		
		Report_getscreenShot("Test Suite Creation Screenshot");
		driver.findElement(By.xpath("//span[contains(.,'Create Test Suite')]")).click();	
		
		Report_getscreenShot("New Test Suite final Creation Screenshot");
		}
		
		
		@Step
		public void devicequickRunSearch(String deviceSearch,String postAction) throws Exception {
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Test Suite - Select Device')]")));
		WEB_Methods.WEB_findElement("XPATH", deviceSearch).clear();
		WEB_Methods.WEB_findElement("XPATH", deviceSearch).sendKeys(Stepdef_CommonSteps.lwm2miccid);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='"+Stepdef_CommonSteps.lwm2miccid+"']")));
		
		WEB_Methods.WEB_findElement("STRING", "//input[@value='"+Stepdef_CommonSteps.lwm2miccid+"']").click();
		
		Report_getscreenShot("Test Suite Creation Screenshot");
	
		WEB_click(WEB_Methods.WEB_findElement("XPATH",postAction));
		
		driver.findElement(By.xpath("//span[contains(.,'Create')]")).click();	
		
		
		
		Report_getscreenShot("Quick Run creation Screenshot");
		}
		
		
		@Step
		public void testSets(DataTable table) throws Exception {
			List<String> datatestSets = table.asList();
			
			for (String d : datatestSets) {				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Filters for Available Subscriptions')]")));
				driver.findElement(By.xpath("//input[@id='searchNode']")).clear();
				driver.findElement(By.xpath("//input[@id='searchNode']")).sendKeys(d);
				Thread.sleep(3000);
				driver.findElement(By.xpath("//a[contains(@class,'jstree-anchor jstree-search')]")).click();	
				//a[contains(@class,'jstree-anchor jstree-search jstree-hovered')]
				logger.info("Test Set added is" +d);
				Thread.sleep(1000);
			}
			
			driver.findElement(By.xpath("//span[text()='Save']")).click();
		}
		
		
		//@Step
		@Test
		public void resourceValue(String resourceValue ) throws Exception {
			
			//driver.findElement(By.xpath("//textarea[contains(@class,'form-control')]")).sendKeys(resourceValue);
					
			WEB_SendKeys(WEB_Methods.WEB_findElement("STRING", "//textarea[contains(@class,'form-control')]"),
					resourceValue);
			
		}
		
		@Test
		public void verifyTestSuite() throws Exception {
			
			 Thread.sleep(2000);
			 String expected = "Test Suite Progress - " +Stepdef_CommonSteps.friendlyName;
			
			 Assert.assertEquals(expected,driver.findElement(By.xpath("//*[text()='Test Suite Progress - "+Stepdef_CommonSteps.friendlyName+"']")).getText());
			 
			 Assert.assertEquals(Stepdef_CommonSteps.friendlyName,driver.findElement(By.xpath("//a[contains(@id,'anchor')][contains(.,'"+Stepdef_CommonSteps.friendlyName+"')]")).getText());
		}
		
		
		
		
		@Test
		public void verifyQuickRunTestSuite() throws Exception {
			
			 Thread.sleep(2000);
			 String textExpected=Stepdef_CommonSteps.lwm2miccid+"_"+Stepdef_CommonSteps.lwm2mmsisdn+"_quickrun";
			 String expected = "Test Suite Progress - " +Stepdef_CommonSteps.lwm2miccid+"_"+Stepdef_CommonSteps.lwm2mmsisdn+"_quickrun";
			 
			 Assert.assertEquals(expected,driver.findElement(By.xpath("//*[contains(text(),'- "+Stepdef_CommonSteps.lwm2miccid+"_"+Stepdef_CommonSteps.lwm2mmsisdn+"_quickrun')]")).getText());
			 
			 Assert.assertEquals(textExpected,driver.findElement(By.xpath("//a[contains(@id,'anchor')][contains(.,'"+textExpected+"')]")).getText());
		}
		
		

	@Step
	public void multipleM2MAfterUpdate(String data1, String data2) throws Exception {

		WEB_findElement("STRING", "//div[@row-id='2']//div[@col-id='m2mAfter']").click();
		WEB_action_sendKeys(WEB_Methods.WEB_findElement("STRING", "//div[@row-id='2']//div[@col-id='m2mAfter']"),
				data1);

	}

	

	@SuppressWarnings("deprecation")
	@Step
	public void checkDB() throws Throwable {

		logger.info("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		logger.info("Connected database successfully...");
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery("SELECT * FROM sbp_message_request WHERE msisdn='8908998899'");

		while (res.next()) {
			logger.info(res.getString(15));
			if (!(res.getFetchSize() == 0)) {
				Assert.assertEquals("8908998899", res.getString(11));
				Assert.assertEquals("200", res.getString(15));
				Assert.assertEquals("200", res.getString(4));
			}

		}
		logger.info("No data in the table" + res.getFetchSize());

	}

	@SuppressWarnings("deprecation")
	@Step
	public ResultSet checkDB(String query) throws Throwable {
		logger.info("Connecting to a selected database...");
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		logger.info("Connected database successfully..." + DB_URL + USER + PASS);
		logger.info("Query is :" + query);
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(query);
		logger.info("res" + res.getFetchSize());
		return res;

	}



	@SuppressWarnings("deprecation")
	@Step
	public void deleteCorpAddedData(String type, String query) throws Throwable {
		try {
			String id = "";
			logger.info("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			logger.info("Connected database successfully...");
			stmt = conn.createStatement();

		

			ResultSet res = stmt.executeQuery(query);
			while (res.next()) {

				logger.info("ID" + res.getString(1));
				id = res.getString(1);
				stmt.executeQuery("");

			
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	@Step
	public void createNewItemString(String msdn) throws Exception {
		if (msdn.equalsIgnoreCase("10")) {
			//Msisdn = "9" + RandomStringUtils.randomNumeric(9).toUpperCase();
		} else if (msdn.equalsIgnoreCase("13")) {
			//Msisdn = "9" + RandomStringUtils.randomNumeric(12).toUpperCase();
		} else {
			//Msisdn = msdn;
		}

	}

	
	

	@Step
	public void copyContentsFromFile() throws Exception {

		String pathOfFile = System.getProperty("user.dir") + "\\UMarchivefile\\" + "FirmwareUpdate.txt";
		File inFile = new File(pathOfFile);
		StringBuilder targetString = new StringBuilder("");
		try {
			FileReader fr = new FileReader(inFile);
			BufferedReader br = new BufferedReader(fr);

			String s = null;
			while ((s = br.readLine()) != null) {
				targetString.append(s);
				targetString.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		WebElement dataToPAste = driver.findElement(By.id("new-item-value"));
		dataToPAste.sendKeys(targetString);

	}

	@SuppressWarnings("deprecation")
	@Step
	public void saveFile(String filename) throws Exception {
		String name = null;
		if (filename.equalsIgnoreCase("First"))
		name = "fileRan";
		else {
			name = "file2";}

		System.out.println("Created file " + name);
		Report_getscreenShot(name);

	}

	@Step
	public void closeSimulator() throws Exception {
		driver.close();
		driver.quit();
	}

	

	
	@SuppressWarnings("deprecation")
	@Step
	public void enterUMStartDate(String time) throws Exception {

		WebElement EndDate = WEB_Methods.WEB_findElement("ID STRING", "endDateId-field-textInput");
		WebElement StartDate = WEB_Methods.WEB_findElement("ID STRING", "startPubDtId-field-textInput");
		String EndDatePlaceHolderValue = EndDate.getAttribute("placeholder");
		String StartDatePlaceHolderValue = StartDate.getAttribute("placeholder");
		Assert.assertEquals("StartDate PlaceHolderValue Format:", StartDatePlaceHolderValue, "YYYY/MM/DD HH:mm");
		Assert.assertEquals("EndDate PlaceHolderValue Format: ", EndDatePlaceHolderValue, "YYYY/MM/DD HH:mm");
		EndDate.clear();
		StartDate.clear();
		WEB_Methods.WEB_findElement("ID STRING", "startPubDtId-field-textInput")
				.sendKeys(addDateToCurrentday(0).replace("-", "/") + " " + time);
		Thread.sleep(1000);
		System.out.println(addDateToCurrentday(0).replace("-", "/") + " " + time);
	}

	
	

	

	@Step
	public void writeIntoFile(String fileName, String msisdn, String imei) throws Exception {

		FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\FOTAUM-Operation\\"));

		BufferedWriter output = null;
		try {

			String filepath = System.getProperty("user.dir") + "\\FOTAUM-Operation\\" + fileName;

			File file = new File(filepath);
			output = new BufferedWriter(new FileWriter(file));
			output.write("#msisdnimei");
			output.write("\n");
			output.write(msisdn + "," + imei);
			fileInput = WEB_findElement("STRING", "//*[@id='browseBtn']");
			fileInput.click();

			uploadFile(filepath);
			Thread.sleep(3000);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	@Step
	public void writefile(String fileName, String operation) throws Exception {
		String fileRan="";
		if (operation.equalsIgnoreCase("Register")) {
			 fileRan = "0A" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
		}
		System.out.println(fileRan);
		String filepath = System.getProperty("user.dir") + "\\TestData\\009\\FOTAUM-Operation\\";
		File dir = new File(filepath);
		File[] foundFiles = dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(fileName);
			}
		});

		for (File file : foundFiles) {
			file.renameTo(new File(filepath + fileRan + fileName));
			fileInput = WEB_findElement("STRING", "//*[text()='Browse']");
			WEB_ActionClick(fileInput);
			uploadFile(filepath + fileRan + fileName);
			Thread.sleep(3000);
		}
	}
	
	@Step
	public void ngListIsEmpty() {
		String ngListArea = driver.findElement(By.xpath("(//*[contains(@id,'ListArea-textArea')])[1]")).getText();
		System.out.println("ngListArea");
		if (ngListArea.isEmpty() || ngListArea.equals("") || ngListArea.equals(null)) {
			logger.info("NG List is empty: No error found" + ngListArea);
		}
	}

	@Step
	public void saveFileName(String flowName) throws Exception {

			String rowValue = checkStatus(flowName);
			logger.info("Row value is" + rowValue);
	
	}

	
	@Step
	public void verifyFileStatus(String status) throws Exception {
         String rowValue="";
		WEB_scrolldown_eventfiring("div.container-content__main");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", WEB_findElement("STRING",
				"//div[contains(@col-id,'whiteList') and text()='makertest_" + rowValue + "']"));
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement resultType = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@row-index='" + rowValue + "']//div[@col-id='0']")));	
		WEB_scrollUp_eventfiring("div.container-content__main");
	}

	public String checkStatus(String fileCSV) {

		String[] filesplit = fileCSV.split("m2m");
		String res = "";
		for (int i = 0; i < filesplit[1].length(); i++) {
			char c = filesplit[1].charAt(i);
			if (c < '0' || c > '9')
				continue;
			res = res + c;
		}
		return res;
	}

	
    @Step
    public void uploadBrowseFile(String fileName,String protocol) throws Exception {
    	
    	String filepath = System.getProperty("user.dir") + "\\TestData\\"+protocol+"\\"+fileName;
    	logger.info("Filepath is" +filepath);
    	
    	
    	    
    			WebElement uploadElement = 
    			                driver.findElement(By.xpath("//span[@id='fileupload']/input"));
    			uploadElement.clear();
    			uploadElement.sendKeys(filepath);
    			  
    	//uploadAFile(fileName,filepath);
    }
    
    @Step
    public void deleteFirmware(String imageName) throws Exception {
    	
    	Report_getscreenShot("Current Page");
    	if(driver.findElements(By.xpath("//input[contains(@value,'"+imageName+"')]")).size()==1){
    	driver.findElement(By.xpath("//input[contains(@value,'"+imageName+"')]")).click();
    	}
    	else {
    		logger.info("No Firmware available to delete");
    	}
    	
    	
    	
    }
    
    @Step
    public void editFirmwareImage(String imageName) throws Exception{
    	
    	driver.findElement(By.xpath("(//td[contains(.,'"+imageName+"')])[1]")).click();
    }
    
    
    @Step
    public void editFirmwareInformation() throws Exception{
    	
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				WEB_findElement("STRING", "//b[contains(.,'Description')]"));
     
    WEB_findElement("STRING", "(//input[@id='exampleInputPassword1'])[1]").clear();
    String random= RandomStringUtils.randomAlphabetic(4); 
    WEB_findElement("STRING", "(//input[@id='exampleInputPassword1'])[1]").sendKeys("This software update includes enhancements and improvements to your Samsung device test Auto."+random);
   
    WEB_findElement("STRING", "(//input[contains(@id,'exampleInputPassword1')])[2]").clear();
    WEB_findElement("STRING", "(//input[@id='exampleInputPassword1'])[1]").sendKeys("This is to verify that the bulleted format works without any issues.^ Bulleted format must support special characters!@#$%*()-.,!= 0123456789[];?()!@#$%*()-.,!=.^ check to see if scrolling is working&..0123456789[];?()!@#$%*()-.,!=.0123456789[];?()!@#$%*()-.,!=.^ kdgjgsdgkljjgnsdknmnxdjkvnsdvgnsdhgkgfkshdgkhgg.^ qwertyuiop[]';lkjhgfdsazxcvbnm,./><^ Bulleted format must support special characters!@#$%*()-.,!= 0123456789[];?()!@#$%*()-.,!=.^^Bullet format ends and paragraph format begins. Verify that bulleted and paragraph formats work.^ check to see if scrolling is working&..0123456789[];?()!@#$%*()-.,!=.0123456789[];?()!@#$%*() ^ This software upgrade from Verizon Wireless includes enhancement and improvements to your Make and Model. kv kdgjgsdgkljjgnsdknmnxdjkvnsdvgnsdhgkgfkshdgkhgg kjsdgjsdklgjsdlgj. ^ This software upgrade from Verizon Wireless includes.^^Bullet format ends and paragraph format begins. Verify that bulleted and paragraph formats work"+random);
 
  
    
    }
    
    @Step
    public void cloneFirmwareInformation(String imageName) throws Exception{
    
    	 WEB_findElement("STRING", "//input[@id='name']").clear();
 
    	 WEB_findElement("STRING", "//input[@id='name']").sendKeys(imageName);
   
    }
    
    
    
    @Step
    public void editFirmwareSuccess() throws Exception{
   	
    	try {
    		Thread.sleep(1000);
			Assert.assertEquals("Properties have been updated", driver.findElement(By.xpath("//*[text()='Properties have been updated']")).getText());
			Assert.assertEquals("Success",driver.findElement(By.xpath("//*[text()='Success']")).getText());
			
			driver.findElement(By.xpath("//button[text()='Close']")).click();
    	
    	} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    @Step
    public void cloneFirmwareSuccess() throws Exception{
   	
    	try {
    		Thread.sleep(1000);
    		
			Assert.assertEquals("Success",driver.findElement(By.xpath("//*[text()='Success']")).getText());
			if(driver.findElement(By.xpath("//p[contains(text(),'Firmware have been cloned successfuly')]")).getText().contains("Firmware have been cloned successfuly")){
    			logger.info("Firmware is clone successfully");
    		}
			
			driver.findElement(By.xpath("//button[text()='Close']")).click();
    	
    	} catch (AssertionError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    	
    @Step
    public void textFieldValue(String textBoxValue) throws Exception {
    	String textValue="";
    	if(textBoxValue.equalsIgnoreCase("Pre-Requisite")) {
    		textValue = RandomStringUtils.randomAlphanumeric(8).toUpperCase();
    	}
    	if(textBoxValue.equalsIgnoreCase("Version")) {
    		textValue = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
    	}
    	
    	textField(textBoxValue,textValue);

    }
	
    
   
    public void textField(String textBoxValue, String textValue) throws Exception {
    	
		WEB_action_sendKeys(WEB_Methods.WEB_findElement("XPATH", textBoxValue), textValue);

    }
	
    
	
	public void uploadAFile(String fileName,String filepath) throws Exception {


		try {
			Thread.sleep(2000);
			fileInput= WEB_findElement("STRING", "//*[text()='Browse… ']");
			fileInput.click();
			//WEB_ActionClick(fileInput);

			uploadFile(filepath);
			Thread.sleep(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Step
	public void user_inside_scroll_down() throws IOException {

		try {
			Thread.sleep(1000);
			WEB_scrolldown_eventfirings("div.ag-body-viewport.ag-layout-normal", "4000");

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Step
    public void user_inside_click_scroll_down() throws IOException {

           try {
                  Thread.sleep(1000);
                  //WEB_findElement("STRING", "(//div[contains(@class,'ag-body-viewport ag-layout-normal')])[2]").click();
                  WEB_findElement("STRING", "//div[contains(@class,'ag-root-wrapper-body ag-layout-auto-height')]").click();

           } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
           }
    }


	@Step
	public void user_inside_scroll_left() {

		try {
			Thread.sleep(1000);
			EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
			eventFiringWebDriver
					.executeScript("document.querySelector('div.ag-body-viewport.ag-layout-normal').scrollLeft=1000");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	

	
	@Step
	public void uploadFOSVFile(String fileName) throws Exception {
		// FileUtils.cleanDirectory(new File (System.getProperty("user.dir") +
		// "\\FOTAUM-Operation\\"));
		try {
			String filepath = System.getProperty("user.dir") + "\\TestData\\009\\FOTAUM-Operation\\OSVPUSH\\ResvList\\" + fileName;
			fileInput = WEB_findElement("STRING", "//*[@id='browseButton']");
			WEB_ActionClick(fileInput);
			uploadFile(filepath);
			Thread.sleep(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Step
	public void user_inside_scroll_left_osvpush(String value) throws Exception {

		WEB_findElement("STRING", "(//div[contains(@class,'ag-body-viewport ag-layout-normal')])[5]").click();

		WEB_findElement("STRING", "(//div[contains(@class,'ag-body-viewport ag-layout-auto-height')])[2]").click();

		WEB_findElement("STRING", "(//div[contains(@class,'ag-body-viewport ag-layout-normal')])[3]").click();

	}



	@SuppressWarnings({ "deprecation" })
	@Step
	public void verifyshowstatus() throws IOException, InterruptedException {
		Thread.sleep(20000);
		String Verify1 = WEB_findElement("STRING", "//td[contains(text(),'101')]").getText();
		String Verify2 = WEB_findElement("STRING", "(//td[contains(.,'0')])[2]").getText();
		String Verify3 = WEB_findElement("STRING", "(//td[contains(.,'0')])[3]").getText();
		Assert.assertEquals("101", Verify1);
		Assert.assertEquals("0", Verify2);
		Assert.assertEquals("0", Verify3);
		logger.info(Verify1);
		logger.info(Verify2);
		logger.info(Verify3);
	}


	public void WEB_scrolldown_eventfirings() {
		EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
		logger.info("CSS is visible"
				+ driver.findElement(By.cssSelector("div.ag-body-viewport.ag-layout-normal")).isDisplayed());
		eventFiringWebDriver
				.executeScript("document.querySelector('div.ag-body-viewport.ag-layout-normal').scrollTop=4000");
	}

	
	public void deviceIDBySubscriberId() throws Exception {
		//Stepdef_CommonSteps.lwm2mmsisdn="3045396049";
	
		 String url ="https://xdompct.xdev.motive.com/rest/device/subscriber/"+Stepdef_CommonSteps.lwm2mmsisdn;
		 Response r = new Stepdef_CommonSteps().getRequest(url,"461",impactUsername,impactPassword);
		 logger.info("Response is" + r.getBody().toString() + "Status code" + r.getStatusCode());

		System.out.println("Response is" + r.getBody().toString() + "Status code" + r.getStatusCode());
		Assert.assertEquals(200, r.getStatusCode());

		Stepdef_CommonSteps.deviceIdImpact = new JSONArray(r.asString()).getJSONObject(0).getString("id");
	
		System.out.println("deviceID :" +Stepdef_CommonSteps.deviceIdImpact);
	}
	
	public void impactPage() throws Exception{
	    Thread.sleep(2000);
	    System.out.println("size is" +driver.findElements(By.xpath("//a[text()='Impersonate as Tenant']")).size());
		Assert.assertEquals(true, driver.findElements(By.xpath("//a[text()='Impersonate as Tenant']")).size()==1);
		Assert.assertEquals(true, driver.findElements(By.xpath("//img[contains(@class,'img-logo')]")).size()==1);
		
	}
	public void postRequestForInitiatingJOB() throws Exception {

		deviceIDBySubscriberId();
        String url ="https://xdompct.xdev.motive.com/rest/device/"+Stepdef_CommonSteps.deviceIdImpact+"/job";
        String jsonBody = Stepdef_CommonSteps.jsonData("Operations.json");
		Response r = new Stepdef_CommonSteps().postRequest(url,jsonBody,"461",impactUsername,impactPassword);
		logger.info("Response is" + r.getBody().toString() + "Status code" + r.getStatusCode());
        logger.info("url for next" +r.getHeader("Location"));
		System.out.println("Response is" + r.getBody().toString() + "Status code" + r.getStatusCode());
		Assert.assertEquals(201, r.getStatusCode());
		
	     Thread.sleep(70*1000);
		String jobURL[]= r.getHeader("Location").split("rest");
		String jobstatusURL="https://xdompct.xdev.motive.com/rest"+jobURL[1];
		 Response rp = new Stepdef_CommonSteps().getRequest(jobstatusURL,"461",impactUsername,impactPassword);
	
		 String jobStatus= new JSONObject(rp.asString()).getString("jobStatus");
		 System.out.println("jobStatus is" +jobStatus);
		 Assert.assertEquals("SUCCESS", jobStatus);
		 
	}
	
	
	



	@Step
	public void userclosedMenu(String submenu, String menu) throws Exception {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + submenu + "']")));
		WEB_click(WEB_findElement("STRING", "//*[text()='" + submenu + "']"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + menu + "']")));
		WEB_click(WEB_findElement("STRING", "//*[text()='" + menu + "']"));
	}

	@Step
	public void alert() throws Exception {
		boolean noalert = true;
		while (noalert) {
			try {
				Alert al = driver.switchTo().alert();
				noalert = false;
				al.accept();
			} catch (NoAlertPresentException e) {
			}
		}
	}

	@Step
	public void userclosedWindow() throws Exception {
		// String value = "Search Execution";
		fPath = System.getProperty("user.dir") + "\\TestData\\009\\AutoIT\\AutoIt3.exe";
		System.out.print("Path" + fPath);
		Process proc = Runtime.getRuntime().exec(fPath);
		Thread.sleep(2000);
		// proc = Runtime.getRuntime().exec(fPath).destroyForcibly();
	}

	@Step
	public void userclosedWindows() throws Exception {
		Process proc = Runtime.getRuntime().exec(fPath).destroyForcibly();
	}


	@Step
	public List<String> readcsvFile(String csvFile) {
		List<String> csvdata = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(csvFile)));
			String line = "";
			String[] tempArr = new String[10000];
			while ((line = br.readLine()) != null) {
				tempArr = line.split(delimiter);
				for (String tempStr : tempArr) {
					csvdata.add(tempStr);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvdata;
	}

	@Step
	public List<String> readcsvFilewthdelimiter(String csvFile, String de_limiter) {
		List<String> csvdata = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(csvFile)));
			String line = "";
			String[] tempArr = new String[100];
			while ((line = br.readLine()) != null) {
				tempArr = line.split(de_limiter);
				for (String tempStr : tempArr) {
					csvdata.add(tempStr);
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return csvdata;
	}

	@Step
	public List<String> findDuplicates(List<String> listContainingDuplicates) {
		final Set<String> setToReturn = new HashSet<>();
		final Set<String> set1 = new HashSet<>();
		List<String> mainList = new ArrayList<String>();
		for (String yourDupl : listContainingDuplicates) {
			if (!set1.add(yourDupl)) {
				// setToReturn.add(yourInt);
				mainList.add(yourDupl);
			}
		}
		// mainList.addAll(setToReturn);
		// Collections.sort(mainList);
		return mainList;
	}

	

	

	@Step
	public List<String> invalidDigits(List<String> listCon) {
		List<String> mainList = new ArrayList<String>();
		for (String iDig : listCon) {
			// if ((!(iDig.length() == 10 || iDig.length() == 13)) &&
			// (!(iDig.equals("#MSISDN"))) && iDig.length()<14) {
			if ((!(iDig.length() == 10 || iDig.length() == 13)) && (!(iDig.equals("#MSISDN")))) {
				mainList.add(iDig);
			}
		}
		return mainList;
	}

	@Step
	public List<String> invalidcharacters(List<String> listCon) {
		List<String> mainList = new ArrayList<String>();
		for (String iDig : listCon) {
			if (!(iDig.matches("[0-9]+")) && (!(iDig.equals("#MSISDN")))) {
				mainList.add(iDig);
			}
		}
		return mainList;
	}

	

	@Step
	public void writebulkDataIntoFile(String fileName, String msisdn, String imei) throws Exception {
		FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "\\FOTAUM-Operation\\"));
		System.out.println();
		BufferedWriter output = null;
		try {
			String filepath = System.getProperty("user.dir") + "\\FOTAUM-Operation\\"+ fileName;
			File file = new File(filepath);
			output = new BufferedWriter(new FileWriter(file));
			output.write("#msisdnimei");
			output.write("\n");
			output.write(msisdn + "," + imei);
			fileInput = WEB_findElement("STRING", "//*[@id='browseBtn']");
			fileInput.click();
			uploadFile(filepath);
			Thread.sleep(3000);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	
	

	



	@Step
	public void hitupdateQuery(String query) throws Throwable {

		String result = "";
		ResultSet rs = checkDB(query);
		while (rs.next()) {

			result = rs.getString(1);
		}
		logger.info("Result type is " + result);

	}

	public void fileDelete(String path) {
		try {
			File f = new File(path);
			f.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("File not found Exception");
		}
	}

	public void fileDeleteDirectory(String filepath) throws Exception {

		 String listPath
		 ="C:\\Users\\KALAIMAN\\Desktop\\DocomoReleaseNew\\DocomoTestAut\\test-framework\\TestData\\009\\FOTAUM-Operation\\WhiteList";
		File f = new File(listPath);

		FileUtils.cleanDirectory(f);
	}


	public void resultCorpWhiteValidation(String listType, String status) throws Exception {
		// fileDelete(listPath);
		List<String> c = new ArrayList<String>();

		String[] s = listType.split("_");
		String branchCode = CharMatcher.inRange('0', '9').retainFrom(s[3]);

	}

	
	@Step
	public void daycheckbox() throws Exception {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("Current Day of Week is: " + day);

	}

	

	@Step
	public void uploadregFile(String fileName) throws Exception {
		// FileUtils.cleanDirectory(new File (System.getProperty("user.dir") +
		// "\\FOTAUM-Operation\\"));
		try {
			String filepath = System.getProperty("user.dir") + "\\TestData\\009\\FOTAUM-Operation\\UMPUSH\\CorpList\\" + fileName;
		
			fileInput = WEB_findElement("STRING", "//*[text()='Browse']");
			WEB_ActionClick(fileInput);
			uploadFile(filepath);
			Thread.sleep(3000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Step
	public void runsimulator_OMADM() throws Exception {	

       try {
    	   
    //	  CommonSteps.lwm2miccid= "918889997613264";
		  PropertyDemo.createPropertyFile(CommonSteps.lwm2miccid);
		  
		 CommonSteps.closeSimulator("5546");
		  
		  if(environment.equalsIgnoreCase("QA")) {
		  String simPath= PropertyDemo.createBatFile("5546", tunnelLocalPORT);
		   System.out.println("Simulator file path" +simPath);
		  ftp = new FTPExample(TUNNEL_URL, TUNNEL_PORT,TUNNEL_USER,TUNNEL_PASS);		 
		  ftp.connect(tunnelLocalPORT,tunnelRemoteHOST,tunnelRemotePORT);	
		   boolean omadmSimulatorStatus= OMADMSimulator.runomadmSimulator(simPath);
		   logger.info("Status of OMADM" +omadmSimulatorStatus);
		  }
		  
		  if(environment.equalsIgnoreCase("PROD")) {
			  
			  String simPath = PropertyDemo.createBatFileProd("5546");
			  System.out.println("Simulator file path" +simPath);
			  boolean omadmSimulatorStatus= OMADMSimulator.runomadmSimulator(simPath);
			  logger.info("Status of OMADM" +omadmSimulatorStatus);
		  }
		  
		   
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
	}
	
    public void CPPSimulatorRun() throws Exception {

//    	Stepdef_CommonSteps.lwm2miccid="000863045310562";
//    	Stepdef_CommonSteps.lwm2mmsisdn="3045396049 ";
    	 ftp = new FTPExample(TUNNEL_URL, TUNNEL_PORT,TUNNEL_USER,TUNNEL_PASS);
    	 ftp.connect(tunnelLocalPORT,tunnelRemoteHOST,tunnelRemotePORT);
    	 String command ="./run.sh -u coap://xdompct.xdev.motive.com:5683  -n 1 -d urn:imei-msisdn:"+Stepdef_CommonSteps.lwm2miccid+"-"+Stepdef_CommonSteps.lwm2mmsisdn+" --sms "+Stepdef_CommonSteps.lwm2mmsisdn+ " --conn_timeout 300 -p 30 --lifetime 3600 --http 5589 --notify 10";
 	     FTPExample.printInputStream(command);
 	    // ftp.disconnect();
	}

    public void realDeviceRun() throws Exception {
    	
    	Stepdef_CommonSteps.lwm2miccid= realDeviceID;
    	Stepdef_CommonSteps.lwm2mmsisdn= realMSISDN;
    }
    
	
	
	 public void checksandStart(String stepname) throws Exception {
			
		
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 WebElement element = wait.until(
		         ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'"+stepname+"')]")));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(.,'"+stepname+"')])[9]")));
		 runsimulator_OMADM();
	 }
	 
	 public void checksandStartLwm2m(String stepname) throws Exception {
			
			
		 WebDriverWait wait = new WebDriverWait(driver, 10);
		 WebElement element = wait.until(
		         ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'"+stepname+"')]")));
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[contains(.,'"+stepname+"')])[9]")));
		 CommonSteps.lwm2mSimulatorRun();
	 }
	 
	 public void jobToComplete(String testName) throws Exception {
			
			
		 WebDriverWait wait = new WebDriverWait(driver, 20);
		 WebElement element = wait.until(
		         ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(.,'"+testName+"')]")));

		 
	 }
	 
    
	public void printtEst(DataTable table) throws Exception {
		List<List<String>> lists =  table.asLists(String.class);
		int i =0;
		for (List<String> list : lists) {
		 System.out.println(list.get(0));  
		 
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@row-id='"+i+"']//*[@col-id='beginning_of_month_device_count']")), list.get(0));
		// Assert.assertEquals(driver.findElement(By.xpath("//*[@row-id='"+i+"']//*[contains(@col-id,'registration_device_count')]")), list.get(1));
		 
		 System.out.println(list.get(1)); 
		 System.out.println(list.get(2)); 
		 System.out.println(list.get(3)); 
		 i++;
		}
		logger.info("TUNNEL URL" +TUNNEL_URL);
		logger.info("TUNNEL URL" +TUNNEL_PORT);
		logger.info("TUNNEL URL" +TUNNEL_PASS);
		logger.info("TUNNEL URL" +TUNNEL_USER);
	
	
	}


	public static void main (String args[]) throws Exception {
		
		Stepdef_19A s = new Stepdef_19A();
				s.runsimulator_OMADM();
	//	s.postRequestForInitiatingJOB();
		String protocol="OMADM";
		String fileName="SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto.xml";
		String filepath = System.getProperty("user.dir") + "\\TestData\\"+protocol+"\\" + fileName;
    	logger.info("Filepath is" +filepath);
		
	}
}
