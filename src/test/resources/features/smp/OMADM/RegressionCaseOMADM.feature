@RegressionCasePartOMADM @test @testomadm @testlocal @testprodomadm1
Feature: Motive Bridge Regression Cases for OMADM Part 01

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
Scenario: OMADM Create Device in LTE mode

    Given user clicks on "Manage Devices"
    Then user clicks on "VERIZON - IOT" protocol
    Then user is navigated to the "OMADM" create device page  
    Then user select the "DeleteDevice" folders for test case "DeleteDeviceList"
    Then user selects the "Play" button
    Then user scroll to the right to "Search" button
    Then user enters the "DeviceID" in the "Search" button
    And validate the "DeviceID" is unique for "OMADM"
    And user selects the "STOP" button
    Then user select the "createDevice" folders for test case "createDeviceLTE"
    Then user scroll to the left to "Play" button
    Then user selects the "Play" button
    Then user scrolls down the window
    Then user enters the "DeviceID" value
    Then user enters the "SubscriberID" value
    Then user enters the "ICCID" value
    Then user scroll to the right to "Submit" button
    Then user selects the "Submit" button
    Then user validates the "createDeviceLTE" test results for "UAT" test case
    Then user closes the current window
   
  
@MotiveBridge019
Scenario: Creating Test Suits for OMADM
    
   Given user switch back to parent window
   Then user selects the "ActiveIcon" button
   Then user select the "VERIZON - IOT" folder
   Then user create a folder "Automation_ReleaseOMADM"
   Then user clicks on "VERIZON - IOT" folder
   Then user create a test suite for "Automation_ReleaseOMADM" folder for "VERIZON"
   Then user search for "Device_search"
   Then user add the below test sets
   |Single-Auto|
   |VZW_GENERIC_AUTO|
   |VZW_GENERIC_UAT|
   |VZW_LTEFIELDOA_IOT_AUTO|
   And user verifies the test suite created

@MotiveBridge019
Scenario: MB_Regression_OMADM-008 & MB_Regression_OMADM-010
    
    Given user switch back to parent window 
 	Then user select the created test Suite folder 
    And user clicks on "VZW_GENERIC_UAT" test set
    Then user selects the "PlayButton" button
    Then user is navigated to the "VZW_GENERIC_UAT" testSet Page 
    Then user select the "ReplaceNodeValue" folders for test case "ReplaceNodeValueNI"
    Then user runs the simulator "OMADM"  
    Then user selects the "Play" button 	
	Then user enters the "Nodename" value as "./ManagedObjects/ConnMO/LTE/APN/3/Setting/Name"
	Then user enters the "Nodevalue" value as "VZWINTERNET"
	Then user selects the "Type" as "text/plain"
	Then user selects the "Format" as "chr"
	 Then user scroll to the right to "Submit" button
     Then user selects the "Submit" button 
     Then user closes the simulator in port "5546" 
     Then user runs the simulator "OMADM" 
	 Then user wait for "10" seconds
	 Then user validates the "ReplaceNodeValueNI" test results for "UAT" test case 	
    # Then user closes the current window 
	 Then user closes the simulator in port "5546" 
	
 @MotiveBridge019
Scenario: MB_Regression_OMADM-008 & MB_Regression_OMADM-010
    Given user closes the current window 
   Then user switch back to parent window 
	#Then user select the created test Suite folder 
	And user clicks on "Single-Auto" test set 
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "Single-Auto" testSet Page 
	#MB_Regression_OMADM-008 : Single-Auto-CheckDeviceReady
	Then user select the "CheckDeviceReady" folders for test case "CheckDeviceReady" 
	Then user selects the "Play" button 
	Then user runs the simulator "OMADM" 
	Then user wait for "5" seconds
	Then user validates the "CheckDeviceReady" test results for "Auto" test case 
	Then user closes the simulator in port "5546" 
	#MB_Regression_OMADM-010 : Single-Auto-VerifyDevInfoSubtree-[3.4] Verify DevInfo Subtree
	Then user select the "VerifyDevInfoSubtree" folders for test case "[3.4] Verify DevInfo Subtree" 
	Then user selects the "Play" button 
	Then user runs the simulator "OMADM" 
	Then user wait for "5" seconds
	Then user validates the "[3.4] Verify DevInfo Subtree" test results for "Auto" test case 
	#Then user closes the current window 
	Then user closes the simulator in port "5546" 
	
@MotiveBridge019
Scenario: VZW_GENERIC_AUTO :MB_Regression_OMADM-011, MB_Regression_OMADM-013,MB_Regression_OMADM-014

Given user closes the current window 
   Then user switch back to parent window 
   #Given user switch back to parent window 
	And user clicks on "VZW_GENERIC_AUTO" test set
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "VZW_GENERIC_AUTO" testSet Page 
	#MB_Regression_OMADM-011 : VZW_GENERIC_AUTO-CheckDeviceReady
	Then user select the "CheckDeviceReady" folders for test case "CheckDeviceReady"
	Then user selects the "Play" button 
	Then user runs the simulator "OMADM" 
	Then user wait for "3" seconds
	Then user validates the "CheckDeviceReady" test results for "Auto" test case 
	Then user closes the simulator in port "5546" 
	# NOT READY : MB_Regression_OMADM-012 : VZW_GENERIC_AUTO-AutoMismatchClientPwd-2.1.1_2.1.4_ClientAuthentication_ClientPasswordMismatch
#	Then user select the "AutoMismatchClientPwd" folders for test case "2.1.1_2.1.4_ClientAuthentication_ClientPasswordMismatch"
#	Then user selects the "Play" button 
#	Then user runs the simulator "OMADM"
#	Then user validates the "2.1.1_2.1.4_ClientAuthentication_ClientPasswordMismatch" test results for "Auto" test case 
#	Then user closes the simulator in port "5546" 

    # MB_Regression_OMADM-013: VZW_GENERIC_AUTO-VerifyDMAccSubtree-[3.2] Verify DMAcc Subtree
	Then user select the "VerifyDMAccSubtree" folders for test case "[3.2] Verify DMAcc Subtree"
	Then user selects the "Play" button
	Then user runs the simulator "OMADM"
	Then user wait for "5" seconds
	Then user validates the "[3.2] Verify DMAcc Subtree" test results for "Auto" test case 
	Then user closes the simulator in port "5546" 
	#MB_Regression_OMADM-014: VZW_GENERIC_AUTO-VerifyVoLTESubtree-[5.1] Verify VoLTE Subtree
	Then user select the "VerifyVoLTESubtree" folders for test case "[5.1] Verify VoLTE Subtree"
	Then user selects the "Play" button 
	Then user runs the simulator "OMADM"
	Then user wait for "8" seconds
	Then user validates the "[5.1] Verify VoLTE Subtree" test results for "Auto" test case 
	Then user closes the simulator in port "5546" 
	#Then user closes the current window 

@MotiveBridge019
Scenario: VZW_LTEFIELDOA_IOT_AUTO : MB_Regression_OMADM-015
    
    Given user closes the current window 
   Then user switch back to parent window   
   # Then user select the created test Suite folder 
	And user clicks on "VZW_LTEFIELDOA_IOT_AUTO" test set
	Then user clicks on the "AutomatedPlayButton"
	Then user is navigated to the "VZW_LTEFIELDOA_IOT_AUTO" testSet Page
    #MB_Regression_OMADM-015: VZW_LTEFIELDOA_IOT_AUTO-_0109_AUTHENTICATION_SECURITY_KEY_MIS_MATCH_VZ_TC_LTEFIELDOA_8541 - Authentication Security Mismatch
	Then user select the "_0109_AUTHENTICATION_SECURITY_KEY_MIS_MATCH_VZ_TC_LTEFIELDOA_8541" folders for test case "Authentication Security Mismatch"
	Then user selects the "Play" button
	Then user runs the simulator "OMADM"
	Then user wait for "2" minutes
	Then user validates the "Authentication Security Mismatch" test results for "Auto" test case 
	Then user closes the simulator in port "5546" 
	#MB_Regression_OMADM-016: VZW_LTEFIELDOA_IOT_AUTO-01_VERIFY_LTE_CONNMO_DM_TREES_VZW_TC_LTEFIELDOA_8540 - [4.1] Verify ConnMo_LTE Subtree
	Then user select the "_0101_VERIFY_LTE_CONNMO_DM_TREES_VZW_TC_LTEFIELDOA_8540" folders for test case "[4.1] Verify ConnMo_LTE Subtree"
	Then user selects the "Play" button
	Then user runs the simulator "OMADM"
	Then user closes the simulator in port "5546" 
	Then user checks the "- 1.- Execute TestModule GetIMSDetails"
	Then user wait for "5" seconds
	Then user validates the "[4.1] Verify ConnMo_LTE Subtree" test results for "Auto" test case 
	Then user closes the simulator in port "5546" 
	#Then user closes the current window 
	
@MotiveBridge019
Scenario: OMADM Delete Device
    
     Given user closes the current window 
   Then user switch back to parent window  
    Then user clicks on "Manage Devices"
    Then user clicks on "VERIZON - IOT" protocol
    Then user is navigated to the "OMADMDeleteDevice" create device page  
    Then user select the "DeleteDevice" folders for test case "DeleteDevice"
    Then user selects the "Play" button
     Then user enters the "DeviceID" value
    Then user enters the "SubscriberID" value
   # Then user enters the "ICCID" value
    Then user scroll to the right to "Submit" button
    Then user selects the "Submit" button
    Then user validates the "DeleteDevice" test results for "UAT" test case  
     Then user select the "DeleteDevice" folders for test case "DeleteDeviceList"
    Then user selects the "Play" button
    Then user selects the devices to delete
    Then user selects the "Submit" button
     Then user validates the "DeleteDeviceList" test results for "UAT" test case 
    # Then user closes the current window 

@MotiveBridge019
Scenario: Logout from Motive Bridge

    Given user closes the current window 
   Then user switch back to parent window  
    Then user Click on log off for the logged in user
    Then user closes the simulator in port "5546" 
 