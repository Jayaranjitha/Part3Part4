@RegressionLwm2mPart4 @qatest @prodtest  @Part3Part4
Feature: Motive Bridge DM Regression Cases VZW-Chapter04Cases,Chapter07Cases &Chapter15Cases

@MotiveBridge001
Scenario: MB_Sanity_DM_Upgrade-001- Motive Bridge Log In Page
  
	Given user is already on Login Page 
	Then user enters user name and password
	And user clicks on login button

 
Scenario: MB_Sanity_DM_Upgrade-001-Motive Bridge UI validation
	Given user verifies the "Manage_Firmware" button
	Then user verifies the "Manage_Devices" button
	Then user verifies the "Quick_Run" button 

 
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
   
  
Scenario: MB_Sanity_DC_Upgrade-004-Adding test subscriptions for Lwm2m test cases

	Given user switch back to parent window 
	Then user selects the "ActiveIcon" button 
	Then user select the "VERIZON[LWM2M] - IOT" folder 
	Then user create a folder "Automation_TestDC" 
	Then user clicks on "VERIZON[LWM2M] - IOT" folder 
	Then user create a test suite for "Automation_TestDC" folder for "VERIZON[LWM2M]" 
	Then user search for "Device_search" 
	Then user add the below test sets 
		   |LWM2M_GENERIC_UAT_MISC|
   		   |VZW_Ch14-HostDevice|
           |VZW_Ch16_NB-IoT|
	And user verifies the test suite created 
	
   
Scenario: MB_Regression_LWM2M-LWM2M_GENERIC_UAT_MISC

    Given user switch back to parent window 
    Then user select the created test Suite folder 
    And user clicks on "LWM2M_GENERIC_UAT_MISC" test set 
	Then user selects the "PlayButton" button 
	Then user is navigated to the "LWM2M_GENERIC_UAT_MISC" testSet Page 
	Then user select the "Set_APN_Configprimitive" folders for test case "5.07_08_1 CLASS 3-6 APN" 
	Then user selects the "Play" button
	Then user enters the "APN_NAME3" value as "VZWINTERNET"
	Then user enters the "APN_NAME6" value as "VZWAPP"	
    Then user scroll to the right to "Submit" button
    Then user selects the "Submit" button
	Then user validates the "5.07_08_1 CLASS 3-6 APN" test results for "VZ_TC_LWM2MOTADM" test case
	Then user select the "Get_Registration_Message" folders for test case "Get_Registration_Message" 
	Then user selects the "Play" button
	Then user select the "PASS" from status to start the test
    Then user scroll to the right to "Submit" button
    Then user selects the "Submit" button
    Then user validates the "Get_Registration_Message" test results for "VZ_TC_LWM2MOTADM" test case
    Then user select the "Observe_Operations_DM_Server" folders for test case "Observe_Operations_DM_Server" 
    Then user selects the "Play" button
	Then user enters the "ResourceValue" value as "/3/0/3"
	Then user scroll to the right to "Submit" button
    Then user selects the "Submit" button
    Then user validates the "Observe_Operations_DM_Server" test results for "VZ_TC_LWM2MOTADM" test case
    
	
 
    
Scenario: MB_Regression_LWM2M-VZW_Ch14-HostDevice
   
     Given user closes the current window 
     Then user switch back to parent window 
     And user clicks on "VZW_Ch14-HostDevice" test set 
	 Then user selects the "PlayButton" button 
	 Then user is navigated to the "VZW_Ch14-HostDevice" testSet Page 
	 Then user select the "HostDeviceInfo" folders for test case "OTADMLWM2M_41102 Host device info" 
	 Then user selects the "Play" button
	 Then user validates the "OTADMLWM2M_41102 Host device info" test results for "VZ_TC_LWM2MOTADM" test case 


  
Scenario: MB_Regression_LWM2M-VZW_Ch16_NB-IoT

     Given user closes the current window 
     Then user switch back to parent window 
#    Then user select the created test Suite folder 
    And user clicks on "VZW_Ch16_NB-IoT" test set 
	Then user selects the "PlayButton" button 
	Then user is navigated to the "VZW_Ch16_NB-IoT" testSet Page 
	Then user select the "NB_IoT_16_1" folders for test case "16.1 NB-IoT" 
	Then user selects the "Play" button
	 Then user select the "Continue" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user select the "Continue" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user select the "Continue" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user select the "Continue" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
	 Then user validates the "16.1 NB-IoT" test results for "VZ_TC_LWM2MOTADM" test case 
       

 
Scenario: Logout from Motive Bridge

      Given user closes the current window 
     Then user switch back to parent window 
    Then user Click on log off for the logged in user
    Then user closes the simulator in port "5545"
    
    