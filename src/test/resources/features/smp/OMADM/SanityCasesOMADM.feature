@SanityCasesomadm @test @testomadm @testlocal @testprodomadm
Feature: Motive Bridge OMADM Sanity cases  001-004

@MotiveBridge001g
Scenario: MB_Sanity_DM_Upgrade-001- Motive Bridge Log In Page
 
	Given user is already on Login Page 
	Then user enters user name and password
	And user clicks on login button
 
@MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-001-Motive Bridge UI validation
	Given user verifies the "Manage_Firmware" button
	Then user verifies the "Manage_Devices" button
	Then user verifies the "Quick_Run" button 
	
Scenario: MB_Sanity_DM_Upgrade-003- OMADM Create Device in LTE mode

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
    #Then user closes the current window
   
  
@MotiveBridge019
Scenario: OMADM-Create test suite
    
    Given user closes the current window
    Then user switch back to parent window
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
   And user verifies the test suite created
  
 @MotiveBridge019
Scenario: Single-Auto & VZW_GENERIC_AUTO
    
   Given user switch back to parent window 
	Then user select the created test Suite folder 
	And user clicks on "Single-Auto" test set 
	Then user clicks on the "PlayButton"
	Then user is navigated to the "Single-Auto" testSet Page 
	#MB_Regression_OMADM-009
	Then user select the "VerifyDMAccSubtree" folders for test case "[3.2] Verify DMAcc Subtree" 
	Then user selects the "Play" button 
	Then user runs the simulator "OMADM" 
	# Then user wait for some time
	Then user wait for "2" seconds
	Then user validates the "[3.2] Verify DMAcc Subtree" test results for "Auto" test case 
	Then user closes the current window 
	Then user closes the simulator in port "5546" 
	Then user switch back to parent window 
	And user clicks on "VZW_GENERIC_AUTO" test set
	Then user clicks on the "PlayButton"
	Then user is navigated to the "VZW_GENERIC_AUTO" testSet Page 
	Then user select the "VerifyDevDetailSubtree" folders for test case "[3.5] Verify DevDetail Subtree" 
	Then user selects the "Play" button 
	Then user runs the simulator "OMADM" 
	Then user validates the "[3.5] Verify DevDetail Subtree" test results for "Auto" test case 
	#Then user closes the current window 
	Then user closes the simulator in port "5546" 
	
     
 
 @MotiveBridge019
Scenario:  MB_Regression_OMADM-005 :VZW_GENERIC_UAT
    
    Given user closes the current window
    Then user switch back to parent window
   #Then user select the created test Suite folder
   And user clicks on "VZW_GENERIC_UAT" test set
  Then user selects the "PlayButton" button
   Then user is navigated to the "VZW_GENERIC_UAT" testSet Page 
   Then user select the "ScriptCommands" folders for test case "ScriptCommandsNI"
   Then user selects the "Play" button
   Then user enters the resource as "GET ./DevDetail/FwV"
   Then user selects the "Submit" button
   Then user runs the simulator "OMADM"
   Then user wait for some time
   Then user validates the "ScriptCommandsNI" test results for "UAT" test case
    Then user closes the simulator in port "5546"
    
   
@MotiveBridge019
Scenario: Logout from Motive Bridge
    Given user closes the current window
    Then user switch back to parent window
    Then user Click on log off for the logged in user
   # Then user closes the simulator in port "5545"
    