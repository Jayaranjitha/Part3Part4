@testx @test @testomadm @testjenkins @realdevice @realdevice1w
Feature: Motive Bridge -Real device related LWM2M cases

@MotiveBridge001
Scenario: MB_Sanity_DM_Upgrade-001- Motive Bridge Log In Page
  
	Given user is already on Login Page 
	#Then user enters username as "nokiamhlab_admin@motive.com" and password as "motiveAdmin@123" for realDevice testing
	Then user enters username and password for realDevice testing
	And user clicks on login button


@MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-001-Motive Bridge UI validation
	Given user verifies the "Manage_Firmware" button
	Then user verifies the "Manage_Devices" button
	Then user verifies the "Quick_Run" button 

  
@MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-004-Adding test subscriptions for Lwm2m test cases
    
   Given user runs the simulator "RealDevice"
   Then user switch back to parent window
  # Then user creates a "AutomationRealDevice" main folder
   Then user selects the "ActiveIcon" button
   Then user select the "AutomationRealDevice" folder
   Then user create a folder "Automation_Test"
   Then user clicks on "VERIZON[LWM2M] - IOT" folder
   Then user create a test suite for "Automation_Test" folder for "VERIZON[LWM2M]"
   Then user search for "Device_search"
   Then user add the below test sets
   |LWM2M_GENERIC_UAT_MISC|
   |VZW-Ch02-Server-Parameters-LWM2M|
   |VZW-Chapter11Cases|
   |VZW-Chapter04Cases|
   |VZW-Chapter07Cases|
   |VZW-Chapter08Cases|
   And user verifies the test suite created
 

@MotiveBridge019
Scenario: LWM2M_GENERIC_UAT_MISC
    
   Given user switch back to parent window
   Given user select the created test Suite folder
   And user clicks on "LWM2M_GENERIC_UAT_MISC" test set
   Then user selects the "PlayButton" button
   Then user is navigated to the "LWM2M_GENERIC_UAT_MISC" testSet Page
   Then user select the "GetDeviceFirmwareVersion" folders for test case "GetDeviceFirmwareVersion"
   Then user selects the "Play" button
   Then user validates the "GetDeviceFirmwareVersion" test results for "UAT" test case
   # Then user closes the current window


     
 @MotiveBridge019
Scenario: VZW-Chapter11Cases: 11.09 FW Update Protocol Support
 
     Given user closes the current window
    Then user switch back to parent window 
   # Then user select the created test Suite folder 
     And user clicks on "VZW-Chapter11Cases" test set 
	 Then user clicks on the "AutomatedPlayButton"
	 Then user is navigated to the "VZW-Chapter11Cases" testSet Page 	 
	 Then user select the "_11_09_FW_Update_Protocol_Support" folders for test case "11.09 FW Update Protocol Support"   
     Then user selects the "Play" button
     Then user validates the "11.09 FW Update Protocol Support" test results for "VZ_TC_LWM2MOTADM" test case
     Then user closes the current window
  
  @MotiveBridge019
Scenario: VZW-Chapter11Cases: 11.10 FW Delivery Method  : Manual Intervention is required
 
        Given user closes the current window
    Then user switch back to parent window 
   # Then user select the created test Suite folder 
     And user clicks on "VZW-Chapter11Cases" test set 
	 Then user clicks on the "AutomatedPlayButton"
	 Then user is navigated to the "VZW-Chapter11Cases" testSet Page 	 
	 Then user select the "_11_10_FW_Delivery_Method" folders for test case "11.10 FW Delivery Method"   
     Then user selects the "Play" button
     Then user validates the test results for "11.10 FW Delivery Method" test case
     Then user closes the current window   
  

 @MotiveBridge019 
Scenario: MB_Regression_LWM2M-019-VZW-Chapter04Cases

     Given user closes the current window
    Then user switch back to parent window 
    And user clicks on "VZW-Chapter04Cases" test set 
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "VZW-Chapter04Cases" testSet Page 
	Then user select the "ICCID_4_33" folders for test case "4.33 ICCID" 
	Then user selects the "Play" button
	Then user validates the "4.33 ICCID" test results for "VZ_TC_LWM2MOTADM" test case
	
	
	@MotiveBridge019 
Scenario: MB_Regression_LWM2M-019-VZW-Chapter08Cases

     Given user closes the current window
    Then user switch back to parent window 
    And user clicks on "VZW-Chapter08Cases" test set 
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "VZW-Chapter08Cases" testSet Page 
	Then user select the "VZ_TC_LWM2MOTADM_10443" folders for test case "8.03 Tx Data" 
	Then user selects the "Play" button
	Then user wait for "5" seconds
	Then user validates the "8.03 Tx Data" test results for "VZ_TC_LWM2MOTADM" test case
    Then user select the "VZ_TC_LWM2MOTADM_10444" folders for test case "8.04 Rx Data" 
	Then user selects the "Play" button
	Then user wait for "5" seconds
	Then user validates the "8.04 Rx Data" test results for "VZ_TC_LWM2MOTADM" test case
	 Then user select the "VZ_TC_LWM2MOTADM_10445" folders for test case "8.05 Max Message Size" 
	Then user selects the "Play" button
	Then user wait for "5" seconds
	Then user validates the "8.05 Max Message Size" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "VZ_TC_LWM2MOTADM_10446" folders for test case "8.06 Average Message Size" 
	Then user selects the "Play" button
	Then user wait for "6" seconds
	Then user validates the "8.06 Average Message Size" test results for "VZ_TC_LWM2MOTADM" test case
	
	
	@MotiveBridge019
   Scenario: VZW-Ch02-Server-Parameters-LWM2M  //Failed cases
    Given user closes the current window
    Then user switch back to parent window 
   # Then user select the created test Suite folder 
     And user clicks on "VZW-Ch02-Server-Parameters-LWM2M" test set 
	 Then user selects the "PlayButton" button 
	 Then user is navigated to the "VZW-Ch02-Server-Parameters-LWM2M" testSet Page 	 
	 Then user select the "ReadAndWriteServerNodes2_13" folders for test case "2.13 Disable Timeout (Repository server)"   
     Then user selects the "Play" button
     Then user wait for "3" seconds
     Then user select the "PASS" from status to start the test
      Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button   
   #  Then user wait for "2" minutes
     Then user validates the "2.13 Disable Timeout (Repository server)" test results for "VZ_TC_LWM2MOTADM" test case
    
@MotiveBridge019
Scenario: Logout from Motive Bridge
    
     Given user closes the current window
    Then user switch back to parent window 
    Given user clicks logout for "REAL device User"
    
    