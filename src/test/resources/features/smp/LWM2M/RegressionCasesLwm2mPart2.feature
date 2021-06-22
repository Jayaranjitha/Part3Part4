@RegressionLwm2mPart2 @test @testjenkins
Feature: Motive Bridge LWM2M Regression Cases

@MotiveBridge001
Scenario: MB_Sanity_DM_Upgrade-001- Motive Bridge Log In Page
  
	Given user is already on Login Page 
	Then user enters user name and password
	And user clicks on login button

@MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-001-Motive Bridge UI validation
	Given user verifies the "Manage_Firmware" button
	Then user verifies the "Manage_Devices" button
	Then user verifies the "Quick_Run" button 

@MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-003- LWM2M Create Device in Bootstrap mode

    Given user clicks on "Manage Devices"
    Then user clicks on "VERIZON[LWM2M] - IOT" protocol
   Then user is navigated to the "LWM2M" create device page  
    #Then user clicks on "Delete Device List" button
    Then user select the "DeleteDevice" folders for test case "Delete Device List"
    Then user selects the "Play" button
    Then user scroll to the right to "Search" button
    Then user enters the "DeviceID" in the "Search" button
    And validate the "DeviceID" is unique for "LWM2M"
    And user selects the "STOP" button
    And user runs the simulator "LWM2M"
    Then user select the "LWM2MCreateDevice" folders for test case "Create Device Bootstrap"
    #Then user clicks on "Create Device Bootstrap" button
    Then user scroll to the left to "Play" button
    Then user selects the "Play" button
    Then user scrolls down the page
    Then user enters the "DeviceID" value
    Then user enters the "SubscriberID" value
    Then user enters the "ICCID" value
    Then user scroll to the right to "Submit" button
    Then user selects the "Submit" button
    Then user validates the "Create Device Bootstrap" test results
   Then user closes the current window
   
@MotiveBridge019 
Scenario: MB_Sanity_DC_Upgrade-004- Adding test subscriptions for Lwm2m test cases

	Given user switch back to parent window 
	Then user selects the "ActiveIcon" button 
	Then user select the "VERIZON[LWM2M] - IOT" folder 
	Then user create a folder "Automation_TestDC" 
	Then user clicks on "VERIZON[LWM2M] - IOT" folder 
	Then user create a test suite for "Automation_TestDC" folder for "VERIZON[LWM2M]" 
	Then user search for "Device_search" 
	Then user add the below test sets 
		|VZW_Ch12_Observation_Tests|
		|VZW-Ch02-Server-Parameters-LWM2M|
		|VZW-Chapter03Cases|  
	And user verifies the test suite created 
	
 @MotiveBridge019 
Scenario: MB_Regression_LWM2M-019-VZW-Chapter03Cases

     Given user switch back to parent window 
    Then user select the created test Suite folder 
     And user clicks on "VZW-Chapter03Cases" test set 
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "VZW-Chapter03Cases" testSet Page 
	Then user select the "CheckDeviceReady" folders for test case "CheckDeviceReady" 
	And user runs the simulator "LWM2M" 
	Then user selects the "Play" button
	Then user validates the "CheckDeviceReady" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "_3_03_ACL" folders for test case "3.03 ACL" 
	Then user selects the "Play" button
	Then user validates the "3.03 ACL" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "_3_04_Access_Control_Owner" folders for test case "3.04 Access Control Owner" 
	Then user selects the "Play" button
	Then user validates the "3.04 Access Control Owner" test results for "VZ_TC_LWM2MOTADM" test case
	
    
@MotiveBridge019 
Scenario: MB_Sanity_DC_Upgrade-004-VZW-Ch02-Server-Parameters-LWM2M

     Given user closes the current window 
     Then user switch back to parent window 
  #  Then user select the created test Suite folder 
     And user clicks on "VZW-Ch02-Server-Parameters-LWM2M" test set 
	Then user selects the "PlayButton" button 
	Then user is navigated to the "VZW-Ch02-Server-Parameters-LWM2M" testSet Page 	 
	Then user select the "ReadAndWriteServerNodes2_05" folders for test case "2.05 Disable Timeout (DM Server)" 
	And user runs the simulator "LWM2M" 
	Then user selects the "Play" button
	Then user wait for "3" seconds
	 Then user select the "PASS" from status to start the test
	 And user runs the simulator "LWM2M"	
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
	Then user validates the "2.05 Disable Timeout (DM Server)" test results for "VZ_TC_LWM2MOTADM" test case 
    Then user select the "ReadAndWriteServerNodes2_08" folders for test case "2.08 Registration Update (DM server)" 
    And user runs the simulator "LWM2M"
	Then user selects the "Play" button 	
	 Then user select the "CONTINUE" from status to start the test
	 And user runs the simulator "LWM2M"	
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user wait for "10" seconds
      Then user select the "CONTINUE" from status to start the test
       And user runs the simulator "LWM2M"	
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user wait for "10" seconds
     Then user validates the "2.08 Registration Update (DM server)" test results for "VZ_TC_LWM2MOTADM" test case
	 
	  
@MotiveBridge019
Scenario: Logout from Motive Bridge


     Given user closes the current window 
     Then user switch back to parent window 
    Then user Click on log off for the logged in user
    Then user closes the simulator in port "5545"
    