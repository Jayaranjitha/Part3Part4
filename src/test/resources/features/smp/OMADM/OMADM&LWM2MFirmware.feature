@Firmware @test @testlocal #@testprodomadm
Feature: Motive Bridge OMADM Firmware Specific Cases

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
Scenario: MB_Sanity_DM_Upgrade-003- Delete Firmware

    Given user clicks on "Manage Firmware"
    Then user is navigated to the "Manage Firmware" create device page
    Then user wait for "10" seconds
    Then user selects the "VERIZON_IOT" button
    Then user selects the "SAMSUNG-SM-G360V" radio button
    Then user wait for "2" seconds
    Then user selects the "Delete_Firmware" button
    Then user delete the firmware Image name "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto"
    Then user selects the "Delete" button
    Then user wait for "10" seconds
    Then user verifies the deleted firmware job details for "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto"
  #  Then user closes the current window
    
 @MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-003- Upload Firmware
    Given user closes the current window
    Then user switch back to parent window
    Then user clicks on "Manage Firmware"
    Then user is navigated to the "Manage Firmware" create device page
    Then user wait for "10" seconds
    Then user selects the "VERIZON_IOT" button
    Then user selects the "SAMSUNG-SM-G360V" radio button
    Then user selects the "Upload_Firmware" button
    Then user upload the file with filename "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto.xml" for "OMADM"
    Then user selects the "bestFirmwareCheck" button
    Then user wait for "2" seconds
    Then user selects the "Upload" button
    Then user wait for some time
    Then user scrolls down the window
    Then user verifies the firmware job details for OMADM
  #  Then user closes the current window


 @MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-003- Edit Firmware

     Given user closes the current window
    Then user switch back to parent window
    Then user clicks on "Manage Firmware"
    Then user is navigated to the "Manage Firmware" create device page
    Then user wait for "10" seconds
    Then user selects the "VERIZON_IOT" button
    Then user selects the "SAMSUNG-SM-G360V" radio button
    Then user selects the "Edit_Firmware" button
    Then user selects the image name "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto" to edit
    Then user wait for "2" seconds
    Then user selects the "Edit" button
    Then user edits the firmware image information
    Then user selects the "SubmitUpdate" button
    Then user verifies the success message
   # Then user closes the current window
   
 Scenario: MB_Sanity_DM_Upgrade-003- Clone_Firmware

     Given user closes the current window
    Then user switch back to parent window
    Then user clicks on "Manage Firmware"
    Then user is navigated to the "Manage Firmware" create device page
    Then user wait for "10" seconds
    Then user selects the "VERIZON_IOT" button
    Then user selects the "SAMSUNG-SM-G360V" radio button
    Then user selects the "Clone_Firmware" button
    Then user selects the image name "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto" to edit
    Then user wait for "2" seconds
    Then user selects the "Clone" button
    Then user edits the firmware image information to clone for "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto_01"
    Then user selects the "SubmitUpdate" button
    Then user verifies the success message for clone
  #  Then user closes the current window
    
    @MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-003- Delete cloned Firmware

    Given user closes the current window
    Then user switch back to parent window
    Then user clicks on "Manage Firmware"
    Then user is navigated to the "Manage Firmware" create device page
    Then user wait for "10" seconds
    Then user selects the "VERIZON_IOT" button
    Then user selects the "SAMSUNG-SM-G360V" radio button
    Then user selects the "Delete_Firmware" button
    Then user delete the firmware Image name "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto_01"
    Then user selects the "Delete" button
    Then user verifies the deleted firmware job details for "SAM_SMG360V_SmallUpdate1435025262_1_G360VVRE2BOF7_G360VVRE2BOF8_Auto_01"
    #Then user closes the current window
    
    
 @MotiveBridge019
Scenario: MB_Sanity_DM_Upgrade-003- Manage Firmware Lwm2m

     Given user closes the current window
    Then user switch back to parent window
    Then user clicks on "Manage Firmware"
    Then user is navigated to the "Manage Firmware" create device page
    Then user selects the "VERIZON[LWM2M]_IOT" button
    Then user selects the "Generic-LWM2MGenericDevice" button
    Then user selects the "Upload_Firmware" button
    Then user scrolls down the window 
    Then user upload the file with filename "Generic_1_2.bin" for "LWM2M"
    Then user enters the value of "Pre-Requisite"
    Then user enters the value of "Version"
    Then user selects the "Upload" button
     Then user wait for some time
     Then user verifies the firmware job details 
     
@MotiveBridge019
Scenario: Logout from Motive Bridge
  
    Given user closes the current window
    Then user switch back to parent window
    Then user Click on log off for the logged in user
  
    