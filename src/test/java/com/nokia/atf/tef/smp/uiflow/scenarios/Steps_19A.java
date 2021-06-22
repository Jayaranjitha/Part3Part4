package com.nokia.atf.tef.smp.uiflow.scenarios;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
//import org.testng.ITestContext;
//import org.testng.annotations.Parameters;

import com.nokia.atf.tef.common.utilities.WEB_Methods;
import com.nokia.atf.tef.common.utilities.Stepdef_19A;
import com.nokia.atf.tef.common.utilities.Stepdef_CommonSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;
//Import added by Jaya

import io.cucumber.datatable.DataTable;
import net.thucydides.core.annotations.Step;

@Component
@ContextConfiguration(locations = { "classpath:smp-config/smp-context.xml" })
public class Steps_19A extends WEB_Methods {
	private static final Logger logger = LoggerFactory.getLogger(Steps_19A.class);
	@Value("${smp.nbi.uiworkflow.url}")
	private String smpURL;
	public static String createdrec = "";
	@Value("${smp.nbi.uiworkflow.username}")
	private String smpUsername;

	@Value("${smp.nbi.uiworkflow.password}")
	private String smpPassword;

	@Value("${smp.nbi.uiworkflow.browser}")
	private String browserName;

	@Value("${smp.nbi.uiworkflow.language}")
	private String language;

	@Steps
	private Stepdef_19A Operations;

	@Steps
	private static Stepdef_CommonSteps commonSteps;

	@Steps
	private WEB_Methods web_Methods;
	public String plat = "";

	

	@Then("^user verify the error message$")
	public void user_verify_error() throws Exception {
		Operations.VerifyErrorMsg();
	}

	@Then("^user upload icon image$")
	public void user_upload_iconimage() throws Exception {
		Operations.uploadImage();
	}


	@Then("^user enters the StartDate and time as \"([^\"]*)\"$")
	public void enterStartDate(String value) throws Exception {

		Operations.enterStartDate(value);
	}

	@Then("^user scrolls down the page upto \"([^\"]*)\"$")
	public void user_scrolls_down(String btn) throws Exception {
		Operations.user_scrolls_down(btn);
	}


	
	@Then("^user scroll down the window$")
	public void user_scroll_down_page() throws Exception {
		Operations.user_scroll_down_page();
	}

	
	@Then("^user scrolls up the page$")
	public void user_scrolls_up() throws Exception {
		Operations.user_scrolls_up();
	}

	


	@Then("^user perform \"([^\"]*)\" on the list$")
	public void clicksonFn(String btn) throws Exception {
		Operations.clicksonFn(btn);

	}


	

	@Then("^user updates multiple row of M2M AfterUpdate textbox as \"([^\"]*)\" and \"([^\"]*)\"$")
	public void multipleM2MAfterUpdate(String data1, String data2) throws Exception {

		Operations.multipleM2MAfterUpdate(data1, data2);
	}

	
	

	

	@Then("^user takes screenshot for \"([^\"]*)\"$")
	public void takeScreenshot(String button) throws Exception {
		Operations.takeScreenshot(button);
	}

	//MB
	 @Then("^user is navigated to the \"([^\"]*)\" create device page$")
	 public void omadmORlwm2mPage(String value) throws Exception {
			Operations.omadmORlwm2mPage(value);

		}
	 
	 
	 @Then("^user is navigated to the \"([^\"]*)\" testSet Page$")
	 public void testSetPage(String value) throws Exception {
			Operations.testSetPage(value);

		}
	 
	
		
	 @Then("^user scrolls down the window$")
	 public void scrolldownwi() throws Exception {
		 commonSteps.scrolldownwi();

		}
	 
	 
	//MB
	@Then("^user clicks on \"([^\"]*)\" button$")
	public void user_clicks_on_Status(String button) throws Exception {
		Operations.user_clicks_on_Status(button);

	}
	
	//MB

	@Then("^user select the \"([^\"]*)\" folder$")
	public void verifyText(String folderName) throws Exception {
		Operations.selectFolder(folderName);
	}
	

	
	@Then("^user creates a \"([^\"]*)\" main folder$")
	public void newFolderCreation(String newfolderName) throws Exception {
	Operations.newFolderCreation(newfolderName);
	
	}
	
	@Then("^user select the \"([^\"]*)\" folders for test case \"([^\"]*)\"$")
	public void verifyTexts(String folderName, String testCaseName) throws Exception {
	Operations.selectFolders(folderName,testCaseName);
	
	}
	

	@Then("^user select the \"([^\"]*)\" from status to start the test$")
	public void selectStatusToStartTest(String testStatus) throws Exception {
	Operations.selectStatusToStartTest(testStatus);
	}
	
	@Then("^user select the \"([^\"]*)\" from devicelist$")
	public void selectDeviceList(String testStatus) throws Exception {
	Operations.selectDeviceList(testStatus);
	}
	
	
	@Then("^user enters the value of \"([^\"]*)\" in the search box$")
	public void devicebyid(String deviceId) throws Exception {
	Operations.devicebyid(deviceId);
	}
	
	@Then("^user saves the first device id from the list$")
	public void devicedata() throws Exception {
	Operations.devicedata();
	}
	
	 
	
	@Then("^user hits the action \"([^\"]*)\" from the Impact$")
	public void actionFromImpact(String actionName) throws Exception {
	Operations.postRequestForInitiatingJOB();
	}
	
	@Then("^user verifies the Impact Page$")
	public void impactPage() throws Exception {
	Operations.impactPage();
	}
	
	
	
	
	@Then("^user clicks on \"([^\"]*)\" folder$")
	public void selectsfolderClicking(String folderName) throws Exception {
		Operations.selectsfolderClicking(folderName);
	}

	@Then("^user create a folder \"([^\"]*)\"$")
	public void createFolder(String folderName) throws Exception {
		Operations.createFolder(folderName);
	}
	
	
	
	@Then("^user create a test suite for \"([^\"]*)\" folder for \"([^\"]*)\"$")
	public void VZW_Ch12_Observation_Tests(String testSuitName,String productName) throws Exception {
		Operations.createTestSuite(testSuitName,productName);
	}
	
	@Then("^user search for \"([^\"]*)\"$")
	public void deviceIdSearch(String deviceSearch) throws Exception {
		Operations.deviceIdSearch(deviceSearch);
	}
	
	
	@Then("^user selects the \"([^\"]*)\" and \"([^\"]*)\"$")
	public void deviceQuickRunSearch(String deviceSearch,String postAction) throws Exception {
		Operations.devicequickRunSearch(deviceSearch,postAction);
	}
	
	
	
	
	@Then("^user add the below test sets$")
	public void testSets(DataTable testSets) throws Exception {
		Operations.testSets(testSets);
	}
	
	@Then("^user enters the resource as \"([^\"]*)\"$")
	public void resourceValue(String resourceValue) throws Exception {
		Operations.resourceValue(resourceValue);
	}
	
	@Then("^user verifies the test suite created$")
	public void verifyTestSuite() throws Exception {
		Operations.verifyTestSuite();
		Report_getscreenShot("Test Suite Created Page");
	}
	
	
	
	@Then("^user verifies the quickRun test suited created$")
	public void verifyQuickRunTestSuite() throws Exception {
		Operations.verifyQuickRunTestSuite();
		Report_getscreenShot("Quick Run Test Suite Created Page");
	}
	
	
	@Then("^user select the created test Suite folder$")
	public void selectTestsuite() throws Exception {
		Operations.selectTestSuiteFolder();
		
	}
	
	
	@Then("^user clicks on \"([^\"]*)\" test set$")
	public void selectTestSet(String testSet) throws Exception {
		Operations.selectTestSet(testSet);
		
	}
	
	@Then("^user clicks on \"([^\"]*)\" test case$")
	public void selectTestCase(String testCase) throws Exception {
		Operations.selectTestCase(testCase);
		
	}
	
	
	
	
	@Then("^user enters the \"([^\"]*)\" value$")
	public void textBoxValue(String button) throws Exception {
		commonSteps.textBoxValue(button);

	}
	
	
	@Then("^user validates the \"([^\"]*)\" test results$")
	public void testResultCreateDeviceLwM2M(String button) throws Exception {
		Operations.testResultCreateDeviceLwM2M(button);
		Report_getscreenShot("Create Device Test Results");
	}
	
	@Then("^user closes the current window$")
	public void closeCurrentwindow() throws Exception {
		Operations.closeCurrentwindow();
		
	}
	
	
	@Then("^user validates the \"([^\"]*)\" test results for \"([^\"]*)\" test case$")
	public void testCaseTestRsult(String button,String tag) throws Exception {
		Operations.testCaseTestRsult(button,tag);
		Report_getscreenShot("Test Case"+RandomStringUtils.randomAlphanumeric(4));
	}
	
	@Then("^user validates the test results for \"([^\"]*)\" test case$")
	public void testCaseRealDevice(String testcase) throws Exception {
		Operations.testCaseRealDevice(testcase);
		Report_getscreenShot("Test Case"+RandomStringUtils.randomAlphanumeric(4));
	}
	
	
	
	@Then("^user enters the \"([^\"]*)\" value as \"([^\"]*)\"$")
	public void textFieldAndTextValue(String fieldName,String fieldValue) throws Exception {
		Operations.textFieldAndTextValue(fieldName,fieldValue);
		Report_getscreenShot("Test Case");
	}
	
	@Then("^user selects the \"([^\"]*)\" as \"([^\"]*)\"$")
	public void selectFromDropDown(String fieldName,String fieldValue) throws Exception {
		Operations.selectFromDropDown(fieldName,fieldValue);
		Report_getscreenShot("Current Page");
	}
	
	
	
	
	@Then("^user validates the \"([^\"]*)\" test results for \"([^\"]*)\" error message$")
	public void testCaset(String testcaselink,String expectedresult) throws Exception {
		Operations.testCaset(testcaselink,expectedresult);
		Report_getscreenShot("Test Case");
	}


	
	
	@Then("^user verifies the job details$")
	public void jobDetails() throws Exception {
		Operations.jobDetails();
		Report_getscreenShot("Job Details Case");
	}
	
	@Then("^user verifies the firmware job details$")
	public void firmwarejobDetails() throws Exception {
		Operations.firmwarejobDetails();
		Report_getscreenShot("Job Details Case");
	}
	
	@Then("^user verifies the firmware job details for OMADM$")
	public void firmwarejobDetailsOMADM() throws Exception {
		Operations.firmwarejobDetailsOMADM();
		Report_getscreenShot("Job Details Case");
	}
	
	@Then("^user verifies the deleted firmware job details for \"([^\"]*)\"$")
	public void firmwaredeletejobDetailsOMADM(String imageName) throws Exception {
		Operations.firmwaredeletejobDetailsOMADM(imageName);
		Report_getscreenShot("Job Details Case");
	}
	
	@Then("^user selects the \"([^\"]*)\" radio button$")
	public void selectFirmwareMakeModel(String modelName) throws Exception {
		Operations.selectFirmwareMakeModel(modelName);
		
	}
	
	
	
	//MB
	@Then("^user enters the \"([^\"]*)\" in the \"([^\"]*)\" button$")
	public void searchbuttonChk(String deviceid, String button) throws Exception {
		commonSteps.searchbuttonChk(button);

	}
	
	@And("^User checks the DB data$")
	public void checkDB() throws Throwable {

		Operations.checkDB();
	}


	@Then("^user verifies the \"([^\"]*)\" button$")
	public void verifiesUI(String button) throws Exception {
		Operations.verifiesUI(button);
		Report_getscreenShot("Motive Bridge Page Screenshot");
	}
	
	
	
	
	@And("^user creates the \"([^\"]*)\" file$")
	public void saveFile(String filename) throws Exception {

		Operations.saveFile(filename);
	}

	@And("^user close the simulator$")
	public void closeSimulator() throws Exception {

		Operations.closeSimulator();
	}



	@And("^user create a new file with filename \"([^\"]*)\" and \"([^\"]*)\"$")
	public void createAFile(String fileName, String operation) throws Exception {
		Operations.writefile(fileName, operation);
	}



	@And("^user scrolls down to view the \"([^\"]*)\"$")
	public void scrollIntoView(String button) throws Exception {
		Operations.scrollIntoView(button);
	}

	@And("^user upload the file with filename \"([^\"]*)\" for \"([^\"]*)\"$")
	public void uploadAFile(String fileName,String protocol) throws Exception {
		Operations.uploadBrowseFile(fileName,protocol);
	}
	
	@And("^user delete the firmware Image name \"([^\"]*)\"$")
	public void deleteFirmware(String imageName) throws Exception {
		Operations.deleteFirmware(imageName);
	}

	@And("^user selects the image name \"([^\"]*)\" to edit$")
	public void editFirmwareImage(String imageName) throws Exception {
		Operations.editFirmwareImage(imageName);
	}
	

	@And("^user edits the firmware image information$") 
		
		public void editFirmwareInformation() throws Exception {
			Operations.editFirmwareInformation();
		
	}
	

	@And("^user edits the firmware image information to clone for \\\"([^\\\"]*)\\\"$") 
	public void cloneFirmwareInformation(String imageName) throws Exception {
			Operations.cloneFirmwareInformation(imageName);}
	
	
	@And("^user verifies the success message$")
	public void editFirmwareSuccess() throws Exception{
		
		Operations.editFirmwareSuccess();
	}
	
	@And("^user verifies the success message for clone$")
	public void cloneFirmwareSuccess() throws Exception{
		
		Operations.cloneFirmwareSuccess();
	}
	
	@And("^user enters the value of \"([^\"]*)\"$")
	public void textFieldValue(String textBoxValue) throws Exception {
		Operations.textFieldValue(textBoxValue);
	}
	
	@Then("^user clicks on inside scroll down option$")
	public void user_inside_scroll_down() throws Exception {
		Operations.user_inside_scroll_down();
	}

	@Then("^user clicks on inside click scroll down option$")
	public void user_inside_click_scroll_down() throws Exception {
		Operations.user_inside_click_scroll_down();
	}

	@Then("^user clicks on inside scroll left option$")
	public void user_inside_scroll_left() throws Exception {
		Operations.user_inside_scroll_left();
	}


	@Then("^user wait for some time$")
	public void user_wait() throws Exception {
          
		Thread.sleep(7000);
		Report_getscreenShot("Current Page");
		// Thread.sleep(1*60*1000);
	}

	@Then("^user wait for \"([^\"]*)\" seconds$")
	public void user_wait_onemin(String seconds) throws Exception {
		Thread.sleep(Integer.parseInt(seconds)*1000);
		logger.info(driver.getCurrentUrl());
		Report_getscreenShot("Current Page");
	}

	@Then("^user wait for some times$")
	public void user_waits() throws Exception {
		Thread.sleep(3 * 60 * 1000);
		Report_getscreenShot("Current Page");
	}
	
	@Then("^user wait for \\\"([^\\\"]*)\\\" minutes$")
	public void user_waits_minutes(String minute) throws Exception {
		Thread.sleep(Integer.parseInt(minute) * 60 * 1000);
	}



	@Then("^user closes the window$")
	public void userclosedWindows() throws Exception {

		Operations.userclosedWindows();
		// driver.close();
	}


	@And("^user enters StartDateandTime as \"([^\"]*)\" and EndDateandTime as \"([^\"]*)\" for Export Results$")
	public void dateAndTimeEnterForExport(String start, String end) throws Exception {
		Operations.dateAndTimeEnterForExport(start, end);
	}



	@And("^user upload the reg file with filename \"([^\"]*)\"$")
	public void uploadregFile(String fileName) throws Exception {
		Operations.uploadregFile(fileName);
	}

	@And("^user is printing the data set$")
	public void printtEst(DataTable table) throws Exception {
		Operations.printtEst(table);
	}

	


}