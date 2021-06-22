package com.nokia.atf.tef.smp.uiflow.scenarios;

import java.sql.ResultSet;
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
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

@Component
@ContextConfiguration(locations = { "classpath:smp-config/smp-context.xml" })
public class CommonSteps extends WEB_Methods {
	private static final Logger logger = LoggerFactory.getLogger(CommonSteps.class);
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
	
	@Steps
	private Stepdef_CommonSteps CommonSteps;
	@Steps
	private Stepdef_19A stepDef_19A;

	//MB
	@Given("^user is already on Login Page$")
	public void user_already_on_login_page() throws Throwable {
		CommonSteps.user_already_on_login_page();

	}
	
	
	@Given("^user login to Impact by hitting the url \"([^\"]*)\"$")
	public void userloginImpact(String impactURL) throws Throwable {
		CommonSteps.userloginImpact(impactURL);

	}

	@When("^title of login page is CSC$")
	public void title_of_login_page_is_CSC() throws Exception {
		CommonSteps.title_of_login_page_is_CSC();
	}

	//MB
	@Then("^user enters user name and password$")
	public void user_enters_username_and_password() throws Exception {
		CommonSteps.user_enters_username_and_password();
	}
	

	
	@Then("^validate the \"([^\"]*)\" is unique for \"([^\"]*)\"$")
	public void deviceidchk(String deviceid,String value) throws Exception {
		CommonSteps.deviceidchk(deviceid,value);

	}


	@Then("user enters user name as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void user_enters_usernamePassword(String username, String password) throws Exception {
		CommonSteps.user_enters_usernamePassword(username, password);

	}
	
//	@Then("user enters username as \"([^\"]*)\" and password as \"([^\"]*)\" for realDevice testing$")
//	public void user_enters_usernamePasswordForRealDevice(String username, String password) throws Exception {
//		CommonSteps.user_enters_usernamePasswordForRealDevice(username, password);
//
//	}
	
	@Then("user enters username and password for realDevice testing$")
	public void user_enters_usernamePasswordForRealDevice() throws Exception {
		CommonSteps.user_enters_usernamePasswordForRealDevice();

	}
	

	//MB
	@Then("^user clicks on login button$")
	public void user_clicks_on_login_button() throws Exception {
		CommonSteps.user_clicks_on_login_button();
	}
	
	//MB
	@Then("^user clicks on \"([^\"]*)\"$")
	public void user_clicks_onDetail(String value) throws Exception {
		CommonSteps.user_clicks_topmenu(value);

	}
	
	//MB
	 @Then("^user clicks on \"([^\"]*)\" protocol$")
		public void userSelectProtocol(String value) throws Exception {
		 CommonSteps.userSelectProtocol(value);

		}
	 //MB
	 @Then("^user selects the \"([^\"]*)\" button$")
		public void playButton(String button) throws Exception {
		 CommonSteps.playButton(button);

		}
	 
	 @Then("^user selects the devices to delete$")
		public void deleteDevices() throws Exception {
		 CommonSteps.deleteDevices();

		}
	 


	 
	 @Then("^user clicks on the \"([^\"]*)\"$")
		public void playButtons(String button) throws Exception {
		 CommonSteps.playButtons(button);

		}
	 
	 @Then("^user creates a new folder \"([^\"]*)\"$")
		public void createNewFolder(String folderName) throws Exception {
		 CommonSteps.createNewFolder(folderName);

		}
	 
	 
	 
	 
	 
	 //MB
		@Then("^user scroll to the right to \"([^\"]*)\" button$")
		public void user_scroll_right(String element) throws Exception {
			CommonSteps.user_scroll_right(element);
		}
		
	//MB 
		 
		 @Then("^user switch back to parent window$")
		public void userSwitchParentWindow() throws Exception {
		 CommonSteps.userSwitchParentWindow();

		}
		 
		
		 @Then("^user reloads the page$")
			public void reloads() throws Exception {
			 CommonSteps.reloadsPage();

			}
			 

	@Then("^user runs the simulator \"([^\"]*)\"$")
	public void simulator_Run(String protocol) throws Exception {
		
		if(protocol.equalsIgnoreCase("OMADM")) {
			stepDef_19A.runsimulator_OMADM();
		}
		
		if(protocol.equalsIgnoreCase("LWM2M")) {

		CommonSteps.lwm2mSimulatorRun();
		}
		
		if(protocol.equalsIgnoreCase("CPP")) {		
			stepDef_19A.CPPSimulatorRun();
		}
		
		if(protocol.equalsIgnoreCase("RealDevice")) {		
			stepDef_19A.realDeviceRun();
		}
		
		
	}
	
	@Then("^user checks the \"([^\"]*)\"$")
	public void checksandStart(String stepname) throws Exception {
		stepDef_19A.checksandStart(stepname);
	}
	
	@Then("^user checks the \"([^\"]*)\" for LWM2M$")
	public void checksandStartLWM2M(String stepname) throws Exception {
		stepDef_19A.checksandStartLwm2m(stepname);
	}
	
	@Then("^user wait for job to complete \"([^\"]*)\"$")
	public void jobToComplete(String testCaseName) throws Exception {
		stepDef_19A.jobToComplete(testCaseName);
	}
	
	
	@Then("^user closes the simulator in port \"([^\"]*)\"$")
	public void closeSimulator(String port) throws Exception {
		
			CommonSteps.closeSimulator(port);
	
		}
		
		
	
	//MB
	@Then("^user scroll to the left to \"([^\"]*)\" button$")
	public void user_scroll_left(String element) throws Exception {
		CommonSteps.user_scroll_left(element);
	}

	@Then("^user Click on log off for the logged in user$")
	public void user_Click_on_log_off() throws Throwable {
		CommonSteps.user_Click_on_log_off();
		logger.info("User logged off successfully");
	}

	@Then("^user clicks logout for \"([^\"]*)\"$")
	public void user_Click_on_logOut(String user) throws Throwable {
		CommonSteps.user_Click_on_logOut(user);
	}
	

	@Then("^User click on a button \"([^\"]*)\"$")
	public void I_click_on_a_button(String Button) throws Throwable {
		CommonSteps.I_click_on_a_button(Button);
	}

//	@Then("^User click on a Link \"([^\"]*)\"$")
//	public void Click_Link(String Link) throws Throwable {
//		CommonSteps.Click_Link(Link);
//	}


	@Then("^user clicks on \"([^\"]*)\" button on confirmation$")
	public void user_clicks_confirmation(String button) throws Exception {
		CommonSteps.user_clicks_on_confirmation(button);

		// if (button.equalsIgnoreCase("Execution")) {
		// driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// }
	}


	//MB
	@Then("^user scrolls down the page$")
	public void user_scrolls_down_page() throws Exception {
		CommonSteps.user_scrolls_down_page();
	}

	@Then("^user wait for \"([^\"]*)\" minute for data display$")
	public void user_waits(double mte) throws Exception {

		Thread.sleep((long) (mte * 60 * 1000));
		Report_getscreenShot("Wait window");
	}

	@Then("^user logged in DB and execute \"([^\"]*)\" Query$")
	public void DBexecuteQuery(String query) throws Throwable {
		CommonSteps.DBexecuteQuery(query);
	}

	@Then("^user clicks on refresh page$")
	public void pagerefresh() throws Exception {
		CommonSteps.pagerefresh();
	}


	@Then("^user scrolls up the window$")
	public void user_scrolls_up_page() throws Exception {
		CommonSteps.user_scrolls_up_page();
	}

	
	
	
}
