@DMSanityCases @testjenkins @testprod @sampleRun
Feature: Motive Bridge DM Sanity cases  001-004

@MotiveBridge019
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
Scenario: MB_Sanity_DM_Upgrade-004-Adding test subscriptions for Lwm2m test cases
    
   Given user switch back to parent window
   Then user selects the "ActiveIcon" button
   Then user select the "VERIZON[LWM2M] - IOT" folder
   Then user create a folder "Automation_Test"
   Then user clicks on "VERIZON[LWM2M] - IOT" folder
   Then user create a test suite for "Automation_Test" folder for "VERIZON[LWM2M]"
   Then user search for "Device_search"
   Then user add the below test sets
   |LWM2M_GENERIC_UAT_MISC|
   |VZW-Chapter04Cases|
   |VZW-Ch13-UAT-Special-Tests|
   |VZW-Ch11-FOTA|
   And user verifies the test suite created
 


@MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-004-Creating Lwm2m device in Motive Bridge Portal
    
   Given user switch back to parent window
   Given user select the created test Suite folder
   And user clicks on "LWM2M_GENERIC_UAT_MISC" test set
   Then user selects the "PlayButton" button
   Then user is navigated to the "LWM2M_GENERIC_UAT_MISC" testSet Page
   Then user select the "GetDeviceFirmwareVersion" folders for test case "GetDeviceFirmwareVersion"
   Then user selects the "Play" button
   Then user validates the "GetDeviceFirmwareVersion" test results for "UAT" test case
    Then user select the "SingleNode_Operations_DM_Server" folders for test case "SingleNode_Operations_DM_Server"
     Then user selects the "Play" button
     Then user enters the resource as "READ /3/0/3"
     Then user selects the "Submit" button
    Then user validates the "SingleNode_Operations_DM_Server" test results for "203000" test case
  #  Then user closes the current window


 
@MotiveBridge019
Scenario: Logout from Motive Bridge

     Given user closes the current window
    Then user switch back to parent window
    Then user Click on log off for the logged in user
    Then user closes the simulator in port "5546"
    