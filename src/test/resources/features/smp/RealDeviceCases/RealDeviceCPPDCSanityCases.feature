@DCSanityCases  @testlocal @reals1 @realdevice1
Feature: Motive Bridge DC Sanity cases  

@MotiveBridge001
Scenario: MB_Sanity_DM_Upgrade-001- Motive Bridge Log In Page
  
	Given user is already on Login Page 
	#Then user enters username as "nokiamhlab_admin@motive.com" and password as "motiveAdmin@123" for realDevice testing
	Then user enters username and password for realDevice testing
	And user clicks on login button

	
@MotiveBridge019 
Scenario: MB_Sanity_DC_Upgrade-001-Motive Bridge UI validation 
	Given user verifies the "Manage_Firmware" button 
	Then user verifies the "Manage_Devices" button 
	Then user verifies the "Quick_Run" button 

@MotiveBridge019 
Scenario: MB_Sanity_DC_Upgrade-004-Adding test subscriptions for Lwm2m test cases 

    Given user runs the simulator "RealDevice"
	Then user switch back to parent window 
	Then user selects the "ActiveIcon" button 
	Then user select the "AutomationRealDevice" folder 
	Then user create a folder "Automation_TestDC" 
	Then user clicks on "VERIZON[LWM2M] - IOT" folder 
	Then user create a test suite for "Automation_TestDC" folder for "VERIZON[LWM2M]" 
	Then user search for "Device_search" 
	Then user add the below test sets 
		|VZW_Ch12_Observation_Tests|
		|VZW-Ch02-Server-Parameters-LWM2M|
		|VZW-Chapter03Cases|  
		|VZW-Ch11-FOTA|
	And user verifies the test suite created 
	
	
@MotiveBridge019 
Scenario: MB_Sanity_DC_Upgrade-004-VZW_Ch12_Observation_Tests 

	Given user switch back to parent window 
	Then user select the created test Suite folder 
	And user clicks on "VZW_Ch12_Observation_Tests" test set 
	Then user selects the "PlayButton" button 
	Then user is navigated to the "VZW_Ch12_Observation_Tests" testSet Page 
	Then user select the "Observation" folders for test case "12.01 Observation and Notification" 
	Then user wait for "60" seconds
	Then user selects the "Play" button 
	Then user wait for "60" seconds
	Then user select the "CONTINUE" from status to start the test 
	Then user selects the "Submit" button 
	Then user validates the "12.01 Observation and Notification" test results for "VZ_TC_LWM2MOTADM" test case 
	Then user select the "Cancel_Observations_Using_GET" folders for test case "12.02 Cancel Observations (Using GET with observe option = 1)" 
	Then user selects the "Play" button 
	Then user wait for "90" seconds
	Then user select the "CONTINUE" from status to start the test 
	Then user selects the "Submit" button 
	Then user wait for "90" seconds
	Then user select the "CONTINUE" from status to start the test 
	Then user selects the "Submit" button 
	Then user validates the "12.02 Cancel Observations (Using GET with observe option = 1)" test results for "VZ_TC_LWM2MOTADM" test case 
	Then user select the "Cancel_Observations_Using_RESET_Message" folders for test case "12.03 Cancel Observations (Using RESET Message)" 
	Then user selects the "Play" button 
	Then user wait for "90" seconds
	Then user select the "CONTINUE" from status to start the test 
	Then user selects the "Submit" button 
	Then user wait for "90" seconds
	Then user select the "CONTINUE" from status to start the test 
	Then user selects the "Submit" button 
	Then user validates the "12.03 Cancel Observations (Using RESET Message)" test results for "VZ_TC_LWM2MOTADM" test case 
	#Then user closes the current window 
	
@MotiveBridge019 
Scenario: MB_Sanity_DC_Upgrade-004-VZW-Ch02-Server-Parameters-LWM2M
  
     Given user closes the current window
    Then user switch back to parent window 
     And user clicks on "VZW-Ch02-Server-Parameters-LWM2M" test set 
	Then user selects the "PlayButton" button 
	Then user is navigated to the "VZW-Ch02-Server-Parameters-LWM2M" testSet Page 	 
	Then user select the "ReadAndWriteServerNodes2_06" folders for test case "2.06 Notification Storing When Disabled or Offline (DM server)" 
	Then user selects the "Play" button 	
	Then user wait for some time
	Then user validates the "2.06 Notification Storing When Disabled or Offline (DM server)" test results for "VZ_TC_LWM2MOTADM" test case 
    Then user select the "ReadAndWriteServerNodes2_14" folders for test case "2.14 Notification Storing When Disabled or Offline (Repository server)"
	Then user selects the "Play" button 	
	Then user wait for some time
	Then user validates the "2.14 Notification Storing When Disabled or Offline (Repository server)" test results for "VZ_TC_LWM2MOTADM" test case 	
   
 
# @MotiveBridge019
#Scenario: MB_Sanity_DC_Upgrade-004- VZW-Ch11-FOTA 
#    
#     Given user closes the current window
#    Then user switch back to parent window
#     And user clicks on "VZW-Ch11-FOTA" test set
#     Then user selects the "PlayButton" button
#     Then user is navigated to the "VZW-Ch11-FOTA" testSet Page
#     And user runs the simulator "CPP"
#     Then user select the "DownloadAndUpdate" folders for test case "11.01 Firmware Update (In-Band)"
#     Then user selects the "Play" button
#     And user runs the simulator "LWM2M"
#     Then user select the "CONTINUE" from status to start the test
#      Then user scroll to the right to "Submit" button
#     Then user selects the "Submit" button     
#     Then user selects the "CheckJobStatus" button
#     Then user wait for some time
#     Then user selects the "CheckJobStatus" button
#     Then user wait for some time
#     Then user selects the "Refresh" button
#     Then user wait for some time
#     Then user selects the "Refresh" button
#     Then user verifies the job details  
#      Then user scroll to the right to "Submit" button
#     Then user selects the "Submit" button
#     Then user wait for some time
#     Then user validates the "11.01 Firmware Update (In-Band)" test results for "VZ_TC_LWM2MOTADM" test case
#     Then user closes the simulator in port "5545"
	
@MotiveBridge019 
Scenario: Logout from Motive Bridge 
    Given user closes the current window
    Then user switch back to parent window
	Then user clicks logout for "REAL device User"
	 
    