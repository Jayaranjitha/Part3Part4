@RegressionLwm2mPart3 @test @testjenkins @Part3Part4
Feature: Motive Bridge DM Regression Cases VZW-Chapter04Cases,Chapter07Cases &Chapter15Cases

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
   # Then user clicks on "Create Device Bootstrap" button
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
Scenario: MB_Sanity_DC_Upgrade-004-Adding test subscriptions for Lwm2m test cases

	Given user switch back to parent window 
	Then user selects the "ActiveIcon" button 
	Then user select the "VERIZON[LWM2M] - IOT" folder 
	Then user create a folder "Automation_TestDC" 
	Then user clicks on "VERIZON[LWM2M] - IOT" folder 
	Then user create a test suite for "Automation_TestDC" folder for "VERIZON[LWM2M]" 
	Then user search for "Device_search" 
	Then user add the below test sets 
		|VZW-Chapter04Cases| 
		|VZW-Chapter07Cases|
		|VZW-Chapter15Cases|
	And user verifies the test suite created 
	
 @MotiveBridge019 
Scenario: MB_Regression_LWM2M-VZW-Chapter04Cases

     Given user switch back to parent window 
    Then user select the created test Suite folder 
     And user clicks on "VZW-Chapter04Cases" test set 
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "VZW-Chapter04Cases" testSet Page 
	Then user select the "ReadAndWriteDeviceNodes_4_01" folders for test case "4.01 Manufactures Name" 
	Then user selects the "Play" button
	Then user validates the "4.01 Manufactures Name" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_02" folders for test case "4.02 Model Number" 
	Then user selects the "Play" button
	Then user validates the "4.02 Model Number" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_03" folders for test case "4.03 Serial Number (Device ID)" 
	Then user selects the "Play" button
	Then user validates the "4.03 Serial Number (Device ID)" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_04" folders for test case "4.04 Firmware Version" 
	Then user selects the "Play" button
	Then user validates the "4.04 Firmware Version" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_14" folders for test case "4.14 Memory Free" 
	Then user selects the "Play" button
	Then user validates the "4.14 Memory Free" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_15" folders for test case "4.15 Error Code" 
	Then user selects the "Play" button
	Then user validates the "4.15 Error Code" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_20" folders for test case "4.20 UTC Offset" 
	Then user selects the "Play" button
	Then user validates the "4.20 UTC Offset" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_22" folders for test case "4.22 Time Zone" 
	Then user selects the "Play" button
	Then user validates the "4.22 Time Zone" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "ReadAndWriteDeviceNodes_4_23" folders for test case "4.23 Supported Binding and Modes" 
	Then user selects the "Play" button
	Then user validates the "4.23 Supported Binding and Modes" test results for "VZ_TC_LWM2MOTADM" test case
	
 
  @MotiveBridge019 
Scenario: MB_Regression_LWM2M-VZW-Chapter07Cases

    
     Given user closes the current window 
     Then user switch back to parent window 
   # Then user select the created test Suite folder 
    And user clicks on "VZW-Chapter07Cases" test set 
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "VZW-Chapter07Cases" testSet Page 
	And user runs the simulator "LWM2M"
	Then user select the "ReadLocationNodes_7_01" folders for test case "7.01 Latitude" 
	Then user selects the "Play" button
	Then user validates the "7.01 Latitude" test results for "VZ_TC_LWM2MOTADM" test case 
	Then user select the "ReadLocationNodes_7_02" folders for test case "7.02 Longitude" 
	Then user selects the "Play" button
	Then user validates the "7.02 Longitude" test results for "VZ_TC_LWM2MOTADM" test case 
    Then user select the "ReadLocationNodes_7_05" folders for test case "7.05 Timestamp" 
	Then user selects the "Play" button
	Then user validates the "7.05 Timestamp" test results for "VZ_TC_LWM2MOTADM" test case 
	

@MotiveBridge019 
Scenario: MB_Regression_LWM2M-VZW-Chapter15Cases

     Given user closes the current window 
     Then user switch back to parent window 
    #Then user select the created test Suite folder 
    And user clicks on "VZW-Chapter15Cases" test set 
	Then user selects the "PlayButton" button 
	Then user is navigated to the "VZW-Chapter15Cases" testSet Page 
	Then user select the "_15_00_PSM_Timer" folders for test case "15.00 PSM Timer" 
	Then user selects the "Play" button
	Then user wait for "5" seconds
	 Then user select the "CONTINUE" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user select the "CONTINUE" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user wait for "2" minutes
	 Then user validates the "15.00 PSM Timer" test results for "VZ_TC_LWM2MOTADM" test case 
     Then user select the "_15_01_Active_Timer" folders for test case "15.01 Active Timer" 
     Then user selects the "Play" button
	 Then user validates the "15.01 Active Timer" test results for "VZ_TC_LWM2MOTADM" test case 
     

@MotiveBridge019
Scenario: Logout from Motive Bridge

      Given user closes the current window 
     Then user switch back to parent window 
    Then user Click on log off for the logged in user
    Then user closes the simulator in port "5549"
    
