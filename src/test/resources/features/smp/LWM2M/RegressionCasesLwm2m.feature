@RegressionLwm2mPart78 @test @testjenkins
Feature: Motive Bridge DM Regression Cases LwM2M

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

#@MotiveBridge019
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
Scenario: MB_Saity_DM_Upgrade-004-Adding test subscriptions for Lwm2m test cases
    
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
   |VZW-Ch11-FOTA-WIFI-ON|
   |VZW-Chapter11Cases|
   And user verifies the test suite created
 
    
 @MotiveBridge019
Scenario: MB_Regression_LWM2M-002- VZW-Ch11-FOTA-WIFI-ON
     #MB_Regression_LWM2M-002
     Given user switch back to parent window
     Then user select the created test Suite folder
     And user clicks on "VZW-Ch11-FOTA-WIFI-ON" test set
     Then user selects the "PlayButton" button
     Then user is navigated to the "VZW-Ch11-FOTA-WIFI-ON" testSet Page  
    Then user select the "Firmware_Update_OB_11_02_Wifi_On" folders for test case "11.02 Firmware Update (Out-Band) Wifi On"
     And user runs the simulator "LWM2M"
     Then user selects the "Play" button    
     Then user select the "CONTINUE" from status to start the test
      Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button   
      Then user selects the "CheckJobStatus" button
     Then user wait for some time
     Then user selects the "CheckJobStatus" button
     Then user wait for some time
     Then user selects the "Refresh" button
     Then user wait for some time
     Then user selects the "Refresh" button  
     Then user verifies the job details  
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button
     Then user wait for some time
     Then user validates the "11.02 Firmware Update (Out-Band) Wifi On" test results for "VZ_TC_LWM2MOTADM" test case
     Then user closes the simulator in port "5545"
     Then user select the "Device_Power_Off_During_FW_Download_OB_11_03_Wifi_On" folders for test case "11.03 Device powers off during FW Download OB Wifi On"    
      And user runs the simulator "LWM2M"
      Then user selects the "Play" button
      Then user select the "CONTINUE" from status to start the test
      Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button   
     Then user wait for "5" seconds
     Then user select the "CONTINUE" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button   
     Then user select the "PASS" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button
     Then user select the "PASS" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button
     Then user wait for some time
     Then user validates the "11.03 Device powers off during FW Download OB Wifi On" test results for "VZ_TC_LWM2MOTADM" test case
     Then user closes the simulator in port "5545"
  #   Then user closes the current window 

 @MotiveBridge019
Scenario: MB_Regression_LWM2M-002- VZW-Ch11-FOTA-WIFI-ON
     #MB_Regression_LWM2M-002
     Given user closes the current window
    Then user switch back to parent window
   #  Given user switch back to parent window
    # Then user select the created test Suite folder
     And user clicks on "VZW-Ch11-FOTA-WIFI-ON" test set
     Then user selects the "PlayButton" button
     Then user is navigated to the "VZW-Ch11-FOTA-WIFI-ON" testSet Page  
     Then user select the "FOTA_Update_Mission_Critical_Device_11_17_Wifi_On" folders for test case "11.17 FOTA Update - Mission Critical Devices Wifi On"
     And user runs the simulator "LWM2M"
     Then user selects the "Play" button 
     Then user select the "CONTINUE" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button  
      Then user select the "PASS" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button
      Then user validates the "11.17 FOTA Update - Mission Critical Devices Wifi On" test results for "VZ_TC_LWM2MOTADM" test case
   #   Then user closes the current window 
 
  @MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-004- VZW-Ch11-FOTA :SU_During_RegistrationAndRegistrationUpdate_11_18
    
      Given user closes the current window
      Then user switch back to parent window
   #  Given user switch back to parent window
     And user clicks on "VZW-Ch11-FOTA" test set
     Then user selects the "PlayButton" button
     Then user is navigated to the "VZW-Ch11-FOTA" testSet Page   
     Then user select the "SU_During_RegistrationAndRegistrationUpdate_11_18" folders for test case "11.18 SU during Registration And RegistrationUpdate"
     And user runs the simulator "LWM2M"
     Then user selects the "Play" button  
     Then user select the "OUTBAND" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button  
     Then user select the "CONTINUE" from status to start the test
     And user runs the simulator "LWM2M"
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button      
     Then user wait for "6" minutes
     Then user validates the "11.18 SU during Registration And RegistrationUpdate" test results for "VZ_TC_LWM2MOTADM" test case
   #  Then user closes the current window 

  @MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-004- VZW-Ch11-FOTA :SU_During_RegistrationAndRegistrationUpdate_11_18_2
    
     Given user closes the current window
    Then user switch back to parent window
  #   Given user switch back to parent window
     #Then user select the created test Suite folder
     And user clicks on "VZW-Ch11-FOTA" test set
     Then user selects the "PlayButton" button
     Then user is navigated to the "VZW-Ch11-FOTA" testSet Page   
     Then user select the "SU_During_RegistrationAndRegistrationUpdate_11_18_2" folders for test case "11.18_2 SU during Registration And RegistrationUpdate"
     And user runs the simulator "LWM2M"
     Then user selects the "Play" button  
     Then user select the "OUTBAND" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button  
     Then user select the "CONTINUE" from status to start the test
      And user runs the simulator "LWM2M"
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button      
     Then user wait for some time
     Then user validates the "11.18_2 SU during Registration And RegistrationUpdate" test results for "VZ_TC_LWM2MOTADM" test case
  #   Then user closes the current window 
     
@MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-004- VZW-Ch11-FOTA 
    
     Given user closes the current window
    Then user switch back to parent window
   #  Given user switch back to parent window
   #  Then user select the created test Suite folder
     And user clicks on "VZW-Ch11-FOTA" test set
     Then user selects the "PlayButton" button
     Then user is navigated to the "VZW-Ch11-FOTA" testSet Page
     Then user select the "DownloadAndUpdate_NewRegistration_11_14" folders for test case "11.14 New Registration"
     And user runs the simulator "LWM2M"
     Then user selects the "Play" button  
     Then user wait for "10" seconds
     Then user select the "OUTBAND" from status to start the test
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button
     Then user wait for some time
     Then user selects the "Generic_LWM2M_Generic_Device_2.2_2.4" button
     Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button
     Then user checks the "Checking if the device came back after reboot" for LWM2M
      Then user wait for some times
      #Then user wait for job to complete "11.14 New Registration"
      Then user validates the "11.14 New Registration" test results for "VZ_TC_LWM2MOTADM" test case
     # Then user closes the current window 
  
 @MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-004- VZW-Ch11-FOTA :DownloadAndUpdate_MultipleFirmwares_11_15
    
     Given user closes the current window
    Then user switch back to parent window
   #  Given user switch back to parent window
     And user clicks on "VZW-Ch11-FOTA" test set
     Then user selects the "PlayButton" button
     Then user is navigated to the "VZW-Ch11-FOTA" testSet Page   
     Then user select the "DownloadAndUpdate_MultipleFirmwares_11_15" folders for test case "11.15 Multiple Updates"
     And user runs the simulator "LWM2M"
     Then user selects the "Play" button 
     Then user wait for some time
     Then user selects the "Generic_LWM2M_Generic_Device_2.2_2.4" button
      Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button
     Then user wait for "5" seconds
      Then user selects the "Generic_LWM2M_Generic_Device_2.4_2.6" button
      Then user scroll to the right to "Submit" button
      Then user selects the "Submit" button
      Then user wait for some time
    # Then user wait for job to complete "11.15 Multiple Updates"
      Then user validates the "11.15 Multiple Updates" test results for "Firmware operation failed. Please contact Motive Support Team" error message
    #  Then user closes the current window 
    
@MotiveBridge019
Scenario: Logout from Motive Bridge

     Given user closes the current window
    Then user switch back to parent window
    Then user Click on log off for the logged in user
    Then user closes the simulator in port "5545"
    