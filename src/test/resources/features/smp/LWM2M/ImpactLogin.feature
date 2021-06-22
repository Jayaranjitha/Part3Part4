@Impact
Feature: Motive Bridge DM Sanity cases  001-004 

@MotiveBridge001 
Scenario Outline: MB_Sanity_DC_Upgrade-001- Motive Bridge Log In Page 

	Given user login to Impact by hitting the url "<ImpactURL>"
    Then user enters user name as "<Username>" and password as "<Password>"
    Then user verifies the Impact Page
    Then User click on a button "Devices"
    Then User click on a button "ViewDevice"
    Then user clicks logout for "<Username>"
	Examples:
	|ImpactURL|Username|Password|
    |http://ivzwmdm.iot.motive.com/ui/#|kalaiman|Motive@123|
    |https://xvzwcdpvii.xdev.motive.com/ui/home.html#/login|kalaiman|Motive@123|
#    |http://xdompct.xdev.motive.com/ui/|kalaiman|Motive@123|